package com.tylervp.block;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Waterloggable;
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
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class PoisonSlimeTreeVinesLarge extends Block implements Waterloggable {

    public static final BooleanProperty WATERLOGGED;

    public static final BooleanProperty PLAYER_PLACED;

    public static final IntProperty BRANCH, DISTANCE, AGE;

    public PoisonSlimeTreeVinesLarge(Settings settings) {
        super(settings.velocityMultiplier(0.1f));
        this.setDefaultState(this.stateManager.getDefaultState().with(PLAYER_PLACED, false).with(WATERLOGGED, false).with(DISTANCE, 0).with(AGE, 0).with(BRANCH, 0));
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


    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        World blockView = ctx.getWorld();
        BlockPos blockPos = ctx.getBlockPos();

        FluidState fuildstate = ctx.getWorld().getFluidState(blockPos);

        Random random = blockView.getRandom();

        int branchChance = 1;

        branchChance += random.nextBetween(0, 1);
        branchChance += random.nextBetween(0, 1);

        return (BlockState)this.getDefaultState()
        .with(PoisonSlimeTreeVines.BRANCH, branchChance)
        .with(Properties.WATERLOGGED, fuildstate.getFluid() == Fluids.WATER)
        .with(PoisonSlimeTreeVines.PLAYER_PLACED, true);


        /* .with(PoisonSlimeTreeVines.OPEN_DIRECTION_NORTH, northState.isOf(this))
        .with(PoisonSlimeTreeVines.OPEN_DIRECTION_EAST, eastState.isOf(this))
        .with(PoisonSlimeTreeVines.OPEN_DIRECTION_SOUTH, southState.isOf(this))
        .with(PoisonSlimeTreeVines.OPEN_DIRECTION_WEST, westState.isOf(this)) */
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        //world.scheduleBlockTick(pos, this, 100);

        if(world.getBlockState(pos.down()).isAir() || world.getFluidState(pos.down()).getFluid() == Fluids.WATER || !Block.sideCoversSmallSquare(world, pos.down(), Direction.UP)) {
            world.breakBlock(pos, false);
        }

        if (state.<Boolean>get((Property<Boolean>)ThinLogBlock.WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    private boolean testCorners(Direction direction, ServerWorld world , BlockPos.Mutable startPosMutable) {
        Boolean pass = false;

        BlockState northwest = world.getBlockState(startPosMutable.add(-1, 0, -1));
        BlockState southeast = world.getBlockState(startPosMutable.add(1, 0, 1));
        BlockState southwest = world.getBlockState(startPosMutable.add(-1, 0, 1));
        BlockState northeast = world.getBlockState(startPosMutable.add(1, 0, -1));

        if(direction == Direction.NORTH) {
            pass = (northwest.isOf(this) || northeast.isOf(this)) ||
             (northwest.isOf(MBMBlocks.POISON_SLIME_TREE_VINES) || northeast.isOf(MBMBlocks.POISON_SLIME_TREE_VINES)) ||
             (northwest.isOf(Blocks.SLIME_BLOCK) || northeast.isOf(Blocks.SLIME_BLOCK));
        } else if(direction == Direction.EAST) {
            pass = northeast.isOf(this) || southeast.isOf(this) ||
            (northwest.isOf(MBMBlocks.POISON_SLIME_TREE_VINES) || southeast.isOf(MBMBlocks.POISON_SLIME_TREE_VINES)) ||
            (northwest.isOf(Blocks.SLIME_BLOCK) || southeast.isOf(Blocks.SLIME_BLOCK));
        } else if(direction == Direction.SOUTH) {
            pass = southeast.isOf(this) || southwest.isOf(this) ||
            (southeast.isOf(MBMBlocks.POISON_SLIME_TREE_VINES) || southwest.isOf(MBMBlocks.POISON_SLIME_TREE_VINES)) ||
            (southeast.isOf(Blocks.SLIME_BLOCK) || southwest.isOf(Blocks.SLIME_BLOCK));
        } else if(direction == Direction.WEST) {
            pass = northwest.isOf(this) || southwest.isOf(this) ||
            (northwest.isOf(MBMBlocks.POISON_SLIME_TREE_VINES) || southwest.isOf(MBMBlocks.POISON_SLIME_TREE_VINES)) ||
            (northwest.isOf(Blocks.SLIME_BLOCK) || southwest.isOf(Blocks.SLIME_BLOCK));
        }

        return pass;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {

        if(state.get(PLAYER_PLACED)) {
            return;
        }

        if(state.get(AGE) == 10) {
            return;
        }

        if(state.get(AGE) > 8) {
            if(state.get(PoisonSlimeTreeVinesLarge.WATERLOGGED)) {
                    BlockPos.Mutable startPosMutable = pos.mutableCopy();

                    for (Direction direction : Direction.values()) {

                        if(!(direction == Direction.DOWN) && state.get(BRANCH) != 0) {
                            BlockPos DifPos = startPosMutable.offset(direction);

                            if(world.getBlockState(DifPos).isOf(Blocks.WATER) && random.nextInt(4) == 0) { /* !world.getBlockState(DifPos).isOf(this) &&  *//* world.getFluidState(DifPos).getFluid() == Fluids.WATER && */
                                world.setBlockState(DifPos, Blocks.SLIME_BLOCK.getDefaultState());

                                BlockState replaceState = state
                                .with(PoisonSlimeTreeVinesLarge.BRANCH, state.get(PoisonSlimeTreeVinesLarge.BRANCH) - 1)
                                .with(PoisonSlimeTreeVinesLarge.AGE, state.get(PoisonSlimeTreeVinesLarge.AGE))
                                .with(PoisonSlimeTreeVinesLarge.DISTANCE, state.get(PoisonSlimeTreeVinesLarge.DISTANCE))
                                .with(PoisonSlimeTreeVinesLarge.WATERLOGGED, state.get(PoisonSlimeTreeVinesLarge.WATERLOGGED));

                                world.setBlockState(pos, replaceState);
                            }
                        }
                    }
                }
        }

        if(random.nextInt(3) == 0 && state.get(AGE) < 10) {
            BlockState replaceState = state
            .with(PoisonSlimeTreeVinesLarge.BRANCH, state.get(PoisonSlimeTreeVinesLarge.BRANCH))
            .with(PoisonSlimeTreeVinesLarge.AGE, state.get(PoisonSlimeTreeVinesLarge.AGE) + 1)
            .with(PoisonSlimeTreeVinesLarge.DISTANCE, state.get(PoisonSlimeTreeVinesLarge.DISTANCE))
            .with(PoisonSlimeTreeVinesLarge.WATERLOGGED, state.get(PoisonSlimeTreeVinesLarge.WATERLOGGED));

            world.setBlockState(pos, replaceState);
        }

        if(state.get(DISTANCE) == 5 || state.get(BRANCH) == 0) {
            return;
        }

        if(state.get(DISTANCE) < 5) {
            BlockPos.Mutable startPosMutable = pos.mutableCopy();

            for (Direction direction : Direction.values()) {

                if(!(direction == Direction.UP || direction == Direction.DOWN)) {
                    BlockPos DifPos = startPosMutable.offset(direction);

                    if(!testCorners(direction, world, startPosMutable)) {
                        if(world.getBlockState(DifPos.down()).isOf(this)) {
                            return;
                        } else if(world.getBlockState(DifPos.down(2)).isOf(this)) {
                            return;
                        }

                        if(world.getBlockState(DifPos.down()).isOf(Blocks.AIR) || world.getBlockState(DifPos.down()).isOf(Blocks.WATER)) {
                            DifPos = startPosMutable.offset(direction).down();
                            if(world.getBlockState(DifPos.down()).isOf(Blocks.AIR) || world.getBlockState(DifPos.down()).isOf(Blocks.WATER)) {
                                return;
                            }
                        }

                        if(((world.getBlockState(DifPos).isOf(Blocks.AIR) || world.getBlockState(DifPos).isOf(Blocks.WATER)) && !world.getBlockState(DifPos).isOf(this)) && random.nextInt(2) == 0) {
                            FluidState fuildstate = world.getFluidState(DifPos);

                            int branchChance = 0;

                            branchChance += random.nextBetween(0, 1);
                            branchChance += random.nextBetween(0, 1);
                            branchChance += random.nextBetween(0, 1);

                            if(random.nextInt(3) == 0) {
                                world.setBlockState(
                                DifPos,
                                this.getDefaultState()
                                    .with(PoisonSlimeTreeVinesLarge.DISTANCE, state.get(PoisonSlimeTreeVinesLarge.DISTANCE) + 1)
                                    .with(PoisonSlimeTreeVinesLarge.BRANCH, branchChance)
                                    .with(PoisonSlimeTreeVinesLarge.WATERLOGGED, fuildstate.getFluid() == Fluids.WATER)
                                );
                            } else {
                                BlockPos northPos = DifPos.north();
                                BlockPos eastPos = DifPos.east();
                                BlockPos southPos = DifPos.south();
                                BlockPos westPos = DifPos.west();
                                BlockState northState = world.getBlockState(northPos);
                                BlockState eastState = world.getBlockState(eastPos);
                                BlockState southState = world.getBlockState(southPos);
                                BlockState westState = world.getBlockState(westPos);


                                world.setBlockState(
                                DifPos,
                                MBMBlocks.POISON_SLIME_TREE_VINES.getDefaultState()
                                    .with(PoisonSlimeTreeVines.CLOSED_DIRECTION_NORTH, !northState.isOf(Blocks.AIR) && !northState.isOf(Blocks.WATER))
                                    .with(PoisonSlimeTreeVines.CLOSED_DIRECTION_EAST, !eastState.isOf(Blocks.AIR) && !eastState.isOf(Blocks.WATER))
                                    .with(PoisonSlimeTreeVines.CLOSED_DIRECTION_SOUTH, !southState.isOf(Blocks.AIR) && !southState.isOf(Blocks.WATER))
                                    .with(PoisonSlimeTreeVines.CLOSED_DIRECTION_WEST, !westState.isOf(Blocks.AIR) && !westState.isOf(Blocks.WATER))
                                    .with(PoisonSlimeTreeVines.DISTANCE, state.get(PoisonSlimeTreeVines.DISTANCE) + 1)
                                    .with(PoisonSlimeTreeVines.BRANCH, branchChance)
                                    .with(Properties.WATERLOGGED, fuildstate.getFluid() == Fluids.WATER)
                                );
                            }

                            BlockState replaceState = state
                            .with(PoisonSlimeTreeVinesLarge.BRANCH, state.get(PoisonSlimeTreeVinesLarge.BRANCH) - 1)
                            .with(PoisonSlimeTreeVinesLarge.AGE, state.get(PoisonSlimeTreeVinesLarge.AGE))
                            .with(PoisonSlimeTreeVinesLarge.DISTANCE, state.get(PoisonSlimeTreeVinesLarge.DISTANCE))
                            .with(PoisonSlimeTreeVinesLarge.WATERLOGGED, state.get(PoisonSlimeTreeVinesLarge.WATERLOGGED));

                            world.setBlockState(pos, replaceState);
                        }
                    }
                }
            }
        }
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        if (state.<Boolean>get((Property<Boolean>)WATERLOGGED)) {
            return Fluids.WATER.getStill(false);
        }
        return super.getFluidState(state);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(PoisonSlimeTreeVinesLarge.PLAYER_PLACED, PoisonSlimeTreeVinesLarge.WATERLOGGED, PoisonSlimeTreeVinesLarge.BRANCH, PoisonSlimeTreeVinesLarge.DISTANCE, PoisonSlimeTreeVinesLarge.AGE);
    }


    static {
        DISTANCE = IntProperty.of("distance", 0, 5);
        AGE = IntProperty.of("age", 0, 10);
        BRANCH = IntProperty.of("branch", 0, 3);

        PLAYER_PLACED = BooleanProperty.of("playerplaced");

        WATERLOGGED = Properties.WATERLOGGED;
    }
}
