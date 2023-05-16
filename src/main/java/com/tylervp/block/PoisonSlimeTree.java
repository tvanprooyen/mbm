package com.tylervp.block;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Direction.Axis;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class PoisonSlimeTree extends PillarBlock {
    public static final BooleanProperty SHOULD_GROW_TREE, SHOULD_GROW_VINES, PLAYER_PLACED;

    public static final IntProperty AGE, DISTANCE;


    public PoisonSlimeTree(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(PLAYER_PLACED, false).with(SHOULD_GROW_TREE, true).with(SHOULD_GROW_VINES, true).with(PillarBlock.AXIS, Direction.Axis.Y).with(PoisonSlimeTree.AGE, 0).with(PoisonSlimeTree.DISTANCE, 0));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.fullCube();
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return Block.createCuboidShape(1, 1, 1, 15, 15, 15);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(PLAYER_PLACED, true).with(PillarBlock.AXIS, ctx.getSide().getAxis());
    }


    @Override
    public void onBlockBreakStart(BlockState state, World world, BlockPos pos, PlayerEntity player) {
        PoisonPlayer(state, world, pos, player, Hand.MAIN_HAND);

        super.onBlockBreakStart(state, world, pos, player);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        PoisonPlayer(state, world, pos, player, hand);

        return ActionResult.PASS;
    }

    private void PoisonPlayer(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand) {
        if (!world.isClient) {
            if(player.getStackInHand(Hand.MAIN_HAND).getItem() != Items.GOLDEN_HOE) { //(player.getStackInHand(hand).getItem() instanceof HoeItem)
                StatusEffect effect = StatusEffects.POISON;

                if((player).hasStatusEffect(effect)) {
                    if((player).getStatusEffect(effect).isDurationBelow(durationToSeconds(2))) {
                        (player).removeStatusEffect(effect);
                        StatusEffectInstance CurrEffectInstance = new StatusEffectInstance(effect, durationToSeconds(10));
                        (player).addStatusEffect(CurrEffectInstance);

                    }
                } else {
                    StatusEffectInstance CurrEffectInstance = new StatusEffectInstance(effect, durationToSeconds(10));
                    (player).addStatusEffect(CurrEffectInstance);
                }
            }
        }
    }

    private BlockState generateBlockState(BlockState state, ServerWorld world, BlockPos pos, Random random) {

        BlockState randomState = Blocks.AIR.getDefaultState();

        if(random.nextInt(4) == 0 && state.get(PoisonSlimeTree.SHOULD_GROW_VINES)) {

            FluidState fuildstate = world.getFluidState(pos);
            int branchChance = 1;

                branchChance += random.nextBetween(0, 1);
                branchChance += random.nextBetween(0, 1);

            randomState = MBMBlocks.POISON_SLIME_TREE_VINES_LARGE.getDefaultState()
            .with(Properties.WATERLOGGED, fuildstate.getFluid() == Fluids.WATER)
            .with(PoisonSlimeTreeVinesLarge.DISTANCE, 0)
            .with(PoisonSlimeTreeVinesLarge.BRANCH, branchChance)
            .with(PoisonSlimeTreeVinesLarge.PLAYER_PLACED, false);

        } else {

            if(random.nextInt(10) == 0 && state.get(PoisonSlimeTree.SHOULD_GROW_TREE)) {

                randomState = this.getDefaultState().with(PoisonSlimeTree.SHOULD_GROW_TREE, false);

            } else if(state.get(PoisonSlimeTree.SHOULD_GROW_VINES)) {
                BlockPos northPos = pos.north();
                BlockPos eastPos = pos.east();
                BlockPos southPos = pos.south();
                BlockPos westPos = pos.west();
                BlockState northState = world.getBlockState(northPos);
                BlockState eastState = world.getBlockState(eastPos);
                BlockState southState = world.getBlockState(southPos);
                BlockState westState = world.getBlockState(westPos);

                FluidState fuildstate = world.getFluidState(pos);

                int branchChance = 1;

                branchChance += random.nextBetween(0, 1);
                branchChance += random.nextBetween(0, 1);

                randomState = MBMBlocks.POISON_SLIME_TREE_VINES.getDefaultState()
                .with(PoisonSlimeTreeVines.CLOSED_DIRECTION_NORTH, !northState.isOf(Blocks.AIR) && !northState.isOf(Blocks.WATER))
                .with(PoisonSlimeTreeVines.CLOSED_DIRECTION_EAST, !eastState.isOf(Blocks.AIR) && !eastState.isOf(Blocks.WATER))
                .with(PoisonSlimeTreeVines.CLOSED_DIRECTION_SOUTH, !southState.isOf(Blocks.AIR) && !southState.isOf(Blocks.WATER))
                .with(PoisonSlimeTreeVines.CLOSED_DIRECTION_WEST, !westState.isOf(Blocks.AIR) && !westState.isOf(Blocks.WATER))
                .with(PoisonSlimeTreeVines.DISTANCE, 0)
                .with(PoisonSlimeTreeVines.BRANCH, branchChance)
                .with(PoisonSlimeTreeVines.PLAYER_PLACED, false)
                .with(Properties.WATERLOGGED, fuildstate.getFluid() == Fluids.WATER);

                /* .with(PoisonSlimeTreeVines.OPEN_DIRECTION_NORTH, northState.isOf(this))
                .with(PoisonSlimeTreeVines.OPEN_DIRECTION_EAST, eastState.isOf(this))
                .with(PoisonSlimeTreeVines.OPEN_DIRECTION_SOUTH, southState.isOf(this))
                .with(PoisonSlimeTreeVines.OPEN_DIRECTION_WEST, westState.isOf(this)) */
            }
        }

        return randomState;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if(state.get(PillarBlock.AXIS) == Axis.Y && !state.get(PLAYER_PLACED)) {

            if(state.get(DISTANCE) == 4) {
                return;
            }

            if((state.get(DISTANCE) < 4 && state.get(DISTANCE) == random.nextBetween(2, 3)) && world.getBlockState(pos.up()).isAir()) {
                if(random.nextInt(2) == 0) {
                    world.setBlockState(pos.up(), MBMBlocks.POISON_SLIME_TREE_TOP.getDefaultState());

                    if(world.getBlockState(pos.down()).isOf(MBMBlocks.POISON_SLIME_TREE_MID)){
                        world.setBlockState(
                            pos.down(),
                            MBMBlocks.POISON_SLIME_TREE_MID_SEMI_DRIP.getDefaultState()
                            .with(PillarBlock.AXIS, state.get(PillarBlock.AXIS))
                            .with(PoisonSlimeTree.PLAYER_PLACED, state.get(PoisonSlimeTree.PLAYER_PLACED))
                            .with(PoisonSlimeTree.SHOULD_GROW_TREE, state.get(PoisonSlimeTree.SHOULD_GROW_TREE))
                            .with(PoisonSlimeTree.SHOULD_GROW_VINES, state.get(PoisonSlimeTree.SHOULD_GROW_VINES))
                            .with(PoisonSlimeTree.DISTANCE, state.get(PoisonSlimeTree.DISTANCE))
                            .with(PoisonSlimeTree.AGE, state.get(PoisonSlimeTree.AGE))
                        );

                        world.setBlockState(
                            pos,
                            MBMBlocks.POISON_SLIME_TREE_MID_DRIP.getDefaultState()
                            .with(PillarBlock.AXIS, state.get(PillarBlock.AXIS))
                            .with(PoisonSlimeTree.PLAYER_PLACED, state.get(PoisonSlimeTree.PLAYER_PLACED))
                            .with(PoisonSlimeTree.SHOULD_GROW_TREE, state.get(PoisonSlimeTree.SHOULD_GROW_TREE))
                            .with(PoisonSlimeTree.SHOULD_GROW_VINES, state.get(PoisonSlimeTree.SHOULD_GROW_VINES))
                            .with(PoisonSlimeTree.DISTANCE, state.get(PoisonSlimeTree.DISTANCE))
                            .with(PoisonSlimeTree.AGE, state.get(PoisonSlimeTree.AGE))
                        );
                    } else {
                        world.setBlockState(
                            pos,
                            MBMBlocks.POISON_SLIME_TREE_MID_SEMI_DRIP.getDefaultState()
                            .with(PillarBlock.AXIS, state.get(PillarBlock.AXIS))
                            .with(PoisonSlimeTree.PLAYER_PLACED, state.get(PoisonSlimeTree.PLAYER_PLACED))
                            .with(PoisonSlimeTree.SHOULD_GROW_TREE, state.get(PoisonSlimeTree.SHOULD_GROW_TREE))
                            .with(PoisonSlimeTree.SHOULD_GROW_VINES, state.get(PoisonSlimeTree.SHOULD_GROW_VINES))
                            .with(PoisonSlimeTree.DISTANCE, state.get(PoisonSlimeTree.DISTANCE))
                            .with(PoisonSlimeTree.AGE, state.get(PoisonSlimeTree.AGE))
                        );
                    }

                    return;
                }
            }

            if(state.get(AGE) == 6) {
                return;
            }

            if(random.nextInt(2) == 0 && state.get(AGE) < 6) {
                world.setBlockState(
                    pos,
                    this.getDefaultState()
                    .with(PillarBlock.AXIS, state.get(PillarBlock.AXIS))
                    .with(PoisonSlimeTree.PLAYER_PLACED, state.get(PoisonSlimeTree.PLAYER_PLACED))
                    .with(PoisonSlimeTree.SHOULD_GROW_TREE, state.get(PoisonSlimeTree.SHOULD_GROW_TREE))
                    .with(PoisonSlimeTree.SHOULD_GROW_VINES, state.get(PoisonSlimeTree.SHOULD_GROW_VINES))
                    .with(PoisonSlimeTree.DISTANCE, state.get(PoisonSlimeTree.DISTANCE))
                    .with(PoisonSlimeTree.AGE, state.get(PoisonSlimeTree.AGE) + 1)
                );
            }

            if(random.nextInt(2) == 0 && state.get(PoisonSlimeTree.DISTANCE) < 3) {
                if(world.getBlockState(pos.up()).isAir()) {
                    world.setBlockState(
                        pos.up(),
                        MBMBlocks.POISON_SLIME_TREE_MID.getDefaultState()
                        .with(PillarBlock.AXIS, state.get(PillarBlock.AXIS))
                        .with(PoisonSlimeTree.PLAYER_PLACED, state.get(PoisonSlimeTree.PLAYER_PLACED))
                        .with(PoisonSlimeTree.SHOULD_GROW_TREE, state.get(PoisonSlimeTree.SHOULD_GROW_TREE))
                        .with(PoisonSlimeTree.SHOULD_GROW_VINES, state.get(PoisonSlimeTree.SHOULD_GROW_VINES))
                        .with(PoisonSlimeTree.DISTANCE, state.get(PoisonSlimeTree.DISTANCE) + 1)
                        .with(PoisonSlimeTree.AGE, state.get(PoisonSlimeTree.AGE))
                    );
                }
            }

            if(world.getBlockState(pos).isOf(MBMBlocks.POISON_SLIME_TREE_BOTTOM)) {
                BlockPos.Mutable startPosMutable = pos.mutableCopy();

                for (Direction direction : Direction.values()) {
                    BlockPos DifPos = startPosMutable.offset(direction);

                    if(random.nextInt(2) == 0) {
                        break;
                    }

                    if(!(direction == Direction.UP || direction == Direction.DOWN) && (state.get(PoisonSlimeTree.SHOULD_GROW_TREE) || state.get(PoisonSlimeTree.SHOULD_GROW_VINES))) {

                        if(world.getBlockState(DifPos.down(2)).isAir()) {
                            break;
                        }

                        if((world.getBlockState(DifPos.down()).getBlock() instanceof PoisonSlimeTree || world.getBlockState(DifPos.down(2)).getBlock() instanceof PoisonSlimeTree) ||(world.getBlockState(DifPos).isOf(MBMBlocks.POISON_SLIME_TREE_VINES) || world.getBlockState(DifPos.down()).isOf(MBMBlocks.POISON_SLIME_TREE_VINES))){
                            break;
                        }

                        if(world.getBlockState(DifPos).isAir() || world.getFluidState(DifPos).getFluid() == Fluids.WATER) {
                            if(world.getBlockState(DifPos.down()).isAir() || world.getFluidState(DifPos.down()).getFluid() == Fluids.WATER) {

                                world.setBlockState(DifPos.down(), generateBlockState(state, world, DifPos.down(), random));
                            } else {
                                world.setBlockState(DifPos, generateBlockState(state, world, DifPos, random));
                            }
                        }
                    } /* else if(direction == Direction.UP && state.get(PoisonSlimeTree.DISTANCE) != 5) {
                        if(world.getBlockState(pos.up()).isAir()) {
                            world.setBlockState(
                                DifPos,
                                MBMBlocks.POISON_SLIME_TREE_MID.getDefaultState()
                                .with(PillarBlock.AXIS, state.get(PillarBlock.AXIS))
                                .with(PoisonSlimeTree.PLAYER_PLACED, state.get(PoisonSlimeTree.PLAYER_PLACED))
                                .with(PoisonSlimeTree.SHOULD_GROW_TREE, state.get(PoisonSlimeTree.SHOULD_GROW_TREE))
                                .with(PoisonSlimeTree.SHOULD_GROW_VINES, state.get(PoisonSlimeTree.SHOULD_GROW_VINES))
                                .with(PoisonSlimeTree.DISTANCE, state.get(PoisonSlimeTree.DISTANCE) + 1)
                                .with(PoisonSlimeTree.AGE, state.get(PoisonSlimeTree.AGE))
                            );
                        }
                    } */
                }
            }


            if(
                !(world.getBlockState(pos.down()).getBlock() instanceof PoisonSlimeTree) &&
                !world.getBlockState(pos.down()).isAir()
            ) {
                world.setBlockState(
                    pos,
                    MBMBlocks.POISON_SLIME_TREE_BOTTOM.getDefaultState()
                    .with(PillarBlock.AXIS, state.get(PillarBlock.AXIS))
                    .with(PoisonSlimeTree.PLAYER_PLACED, state.get(PoisonSlimeTree.PLAYER_PLACED))
                    .with(PoisonSlimeTree.SHOULD_GROW_TREE, state.get(PoisonSlimeTree.SHOULD_GROW_TREE))
                    .with(PoisonSlimeTree.SHOULD_GROW_VINES, state.get(PoisonSlimeTree.SHOULD_GROW_VINES))
                    .with(PoisonSlimeTree.DISTANCE, state.get(PoisonSlimeTree.DISTANCE))
                    .with(PoisonSlimeTree.AGE, state.get(PoisonSlimeTree.AGE))
                );
            }

            /* if(world.getBlockState(pos.up()).isOf(MBMBlocks.POISON_SLIME_TREE_TOP)) {
                world.setBlockState(pos, MBMBlocks.POISON_SLIME_TREE_MID_SEMI_DRIP.getDefaultState());
            } else if(world.getBlockState(pos.up(2)).isOf(MBMBlocks.POISON_SLIME_TREE_TOP)) {
                world.setBlockState(pos, MBMBlocks.POISON_SLIME_TREE_MID_SEMI_DRIP.getDefaultState());
            } else if(world.getBlockState(pos.up()).isOf(MBMBlocks.POISON_SLIME_TREE_MID_SEMI_DRIP) && world.getBlockState(pos.up(2)).isOf(MBMBlocks.POISON_SLIME_TREE_TOP)) {
                world.setBlockState(pos, MBMBlocks.POISON_SLIME_TREE_MID_SEMI_DRIP.getDefaultState());
            } else  */
        }
    }

    private int durationToSeconds(double duration) {
        return MathHelper.ceil(20.83 * duration);
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {

        if (world.isClient) {
            return;
        }

        if (world.random.nextInt(10) == 0) {

            List<Entity> EntityList = new ArrayList<Entity>();

            EntityList.add(entity);

            for (Entity entities : EntityList) {
                StatusEffect effect = StatusEffects.POISON;

                if(!(entities instanceof LivingEntity)) {
                    break;
                }

                if(((LivingEntity)entities).hasStatusEffect(effect)) {
                    if(((LivingEntity)entities).getStatusEffect(effect).isDurationBelow(durationToSeconds(2))) {
                        ((LivingEntity)entities).removeStatusEffect(effect);
                        StatusEffectInstance CurrEffectInstance = new StatusEffectInstance(effect, durationToSeconds(10));
                        ((LivingEntity)entities).addStatusEffect(CurrEffectInstance);

                    }
                    break;
                }

                StatusEffectInstance CurrEffectInstance = new StatusEffectInstance(effect, durationToSeconds(10));

                ((LivingEntity)entities).addStatusEffect(CurrEffectInstance);

            }
        }
    }

    @Override
    protected void appendProperties(Builder<Block, BlockState> builder) {
        builder.add(PillarBlock.AXIS, PoisonSlimeTree.PLAYER_PLACED, PoisonSlimeTree.SHOULD_GROW_TREE, PoisonSlimeTree.SHOULD_GROW_VINES, PoisonSlimeTree.AGE, PoisonSlimeTree.DISTANCE);
    }

    static {
        PLAYER_PLACED = BooleanProperty.of("playerplaced");

        SHOULD_GROW_TREE = BooleanProperty.of("growtree");

        SHOULD_GROW_VINES = BooleanProperty.of("growvines");

        AGE = IntProperty.of("age", 0, 6);

        DISTANCE = IntProperty.of("distance", 0, 10);
    }
}
