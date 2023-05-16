package com.tylervp.block;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.pathing.NavigationType;
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
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

public class PoisonSlimeTreeVines extends Block implements Waterloggable {
    //public static final BooleanProperty OPEN_DIRECTION_NORTH, OPEN_DIRECTION_EAST, OPEN_DIRECTION_SOUTH, OPEN_DIRECTION_WEST;
    public static final BooleanProperty CLOSED_DIRECTION_NORTH, CLOSED_DIRECTION_EAST, CLOSED_DIRECTION_SOUTH, CLOSED_DIRECTION_WEST;

    public static final BooleanProperty WATERLOGGED;

    public static final BooleanProperty PLAYER_PLACED;

    public static final IntProperty BRANCH, DISTANCE, AGE;

    public static final VoxelShape BASE_SHAPE, NORTH_SHAPE, EAST_SHAPE, SOUTH_SHAPE, WEST_SHAPE;

    public static final VoxelShape BASE_SHAPE_COLLISION, NORTH_SHAPE_COLLISION, EAST_SHAPE_COLLISION, SOUTH_SHAPE_COLLISION, WEST_SHAPE_COLLISION;

    public PoisonSlimeTreeVines(Settings settings) {
        super(settings.velocityMultiplier(0.1f));
        this.setDefaultState(this.stateManager.getDefaultState().with(PLAYER_PLACED, false).with(WATERLOGGED, false).with(PoisonSlimeTreeVines.CLOSED_DIRECTION_NORTH, false).with(PoisonSlimeTreeVines.CLOSED_DIRECTION_EAST, false).with(PoisonSlimeTreeVines.CLOSED_DIRECTION_SOUTH, false).with(PoisonSlimeTreeVines.CLOSED_DIRECTION_WEST, false).with(PoisonSlimeTreeVines.DISTANCE, 0).with(PoisonSlimeTreeVines.AGE, 0).with(PoisonSlimeTreeVines.BRANCH, 0));
        /* .with(PoisonSlimeTreeVines.OPEN_DIRECTION_NORTH, false).with(PoisonSlimeTreeVines.OPEN_DIRECTION_EAST, false).with(PoisonSlimeTreeVines.OPEN_DIRECTION_SOUTH, false).with(PoisonSlimeTreeVines.OPEN_DIRECTION_WEST, false) */
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        VoxelShape buildShape = BASE_SHAPE;

        if(/* state.get(OPEN_DIRECTION_NORTH) ||  */state.get(CLOSED_DIRECTION_NORTH)) {
            buildShape = VoxelShapes.union(buildShape, NORTH_SHAPE);
        }

        if(/* state.get(OPEN_DIRECTION_EAST) ||  */state.get(CLOSED_DIRECTION_EAST)) {
            buildShape = VoxelShapes.union(buildShape, EAST_SHAPE);
        }

        if(/* state.get(OPEN_DIRECTION_SOUTH) ||  */state.get(CLOSED_DIRECTION_SOUTH)) {
            buildShape = VoxelShapes.union(buildShape, SOUTH_SHAPE);
        }

        if(/* state.get(OPEN_DIRECTION_WEST) ||  */state.get(CLOSED_DIRECTION_WEST)) {
            buildShape = VoxelShapes.union(buildShape, WEST_SHAPE);
        }

        return buildShape;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        VoxelShape buildShape = BASE_SHAPE_COLLISION;

        if(/* state.get(OPEN_DIRECTION_NORTH) ||  */state.get(CLOSED_DIRECTION_NORTH)) {
            buildShape = VoxelShapes.union(buildShape, NORTH_SHAPE_COLLISION);
        }

        if(/* state.get(OPEN_DIRECTION_EAST) ||  */state.get(CLOSED_DIRECTION_EAST)) {
            buildShape = VoxelShapes.union(buildShape, EAST_SHAPE_COLLISION);
        }

        if(/* state.get(OPEN_DIRECTION_SOUTH) ||  */state.get(CLOSED_DIRECTION_SOUTH)) {
            buildShape = VoxelShapes.union(buildShape, SOUTH_SHAPE_COLLISION);
        }

        if(/* state.get(OPEN_DIRECTION_WEST) ||  */state.get(CLOSED_DIRECTION_WEST)) {
            buildShape = VoxelShapes.union(buildShape, WEST_SHAPE_COLLISION);
        }

        return buildShape;
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
        //FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
        BlockPos northPos = blockPos.north();
        BlockPos eastPos = blockPos.east();
        BlockPos southPos = blockPos.south();
        BlockPos westPos = blockPos.west();
        BlockState northState = blockView.getBlockState(northPos);
        BlockState eastState = blockView.getBlockState(eastPos);
        BlockState southState = blockView.getBlockState(southPos);
        BlockState westState = blockView.getBlockState(westPos);

        FluidState fuildstate = ctx.getWorld().getFluidState(blockPos);

        Random random = blockView.getRandom();

        int branchChance = 1;

        branchChance += random.nextBetween(0, 1);
        branchChance += random.nextBetween(0, 1);

        return (BlockState)this.getDefaultState()
        .with(PoisonSlimeTreeVines.CLOSED_DIRECTION_NORTH, !northState.isOf(Blocks.AIR) && !northState.isOf(Blocks.WATER))
        .with(PoisonSlimeTreeVines.CLOSED_DIRECTION_EAST, !eastState.isOf(Blocks.AIR) && !eastState.isOf(Blocks.WATER))
        .with(PoisonSlimeTreeVines.CLOSED_DIRECTION_SOUTH, !southState.isOf(Blocks.AIR) && !southState.isOf(Blocks.WATER))
        .with(PoisonSlimeTreeVines.CLOSED_DIRECTION_WEST, !westState.isOf(Blocks.AIR) && !westState.isOf(Blocks.WATER))
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

        BlockPos northPos = pos.north();
        BlockPos eastPos = pos.east();
        BlockPos southPos = pos.south();
        BlockPos westPos = pos.west();
        BlockState northState = world.getBlockState(northPos);
        BlockState eastState = world.getBlockState(eastPos);
        BlockState southState = world.getBlockState(southPos);
        BlockState westState = world.getBlockState(westPos);

        BlockState replaceState = state
        .with(PoisonSlimeTreeVines.CLOSED_DIRECTION_NORTH, !northState.isOf(Blocks.AIR) && !northState.isOf(Blocks.WATER))
        .with(PoisonSlimeTreeVines.CLOSED_DIRECTION_EAST, !eastState.isOf(Blocks.AIR) && !eastState.isOf(Blocks.WATER))
        .with(PoisonSlimeTreeVines.CLOSED_DIRECTION_SOUTH, !southState.isOf(Blocks.AIR) && !southState.isOf(Blocks.WATER))
        .with(PoisonSlimeTreeVines.CLOSED_DIRECTION_WEST, !westState.isOf(Blocks.AIR) && !westState.isOf(Blocks.WATER))
        .with(PoisonSlimeTreeVines.BRANCH, state.get(PoisonSlimeTreeVines.BRANCH))
        .with(PoisonSlimeTreeVines.AGE, state.get(PoisonSlimeTreeVines.AGE))
        .with(PoisonSlimeTreeVines.DISTANCE, state.get(PoisonSlimeTreeVines.DISTANCE))
        .with(PoisonSlimeTreeVines.WATERLOGGED, state.get(PoisonSlimeTreeVines.WATERLOGGED))
        .with(PoisonSlimeTreeVines.PLAYER_PLACED, state.get(PoisonSlimeTreeVines.PLAYER_PLACED));

        /* .with(PoisonSlimeTreeVines.OPEN_DIRECTION_NORTH, northState.isOf(this))
        .with(PoisonSlimeTreeVines.OPEN_DIRECTION_EAST, eastState.isOf(this))
        .with(PoisonSlimeTreeVines.OPEN_DIRECTION_SOUTH, southState.isOf(this))
        .with(PoisonSlimeTreeVines.OPEN_DIRECTION_WEST, westState.isOf(this)) */

        //return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
        return replaceState;
    }

    private boolean testCorners(Direction direction, ServerWorld world , BlockPos.Mutable startPosMutable) {
        Boolean pass = false;

        BlockState northwest = world.getBlockState(startPosMutable.add(-1, 0, -1));
        BlockState southeast = world.getBlockState(startPosMutable.add(1, 0, 1));
        BlockState southwest = world.getBlockState(startPosMutable.add(-1, 0, 1));
        BlockState northeast = world.getBlockState(startPosMutable.add(1, 0, -1));

        if(direction == Direction.NORTH) {
            pass = (northwest.isOf(this) || northeast.isOf(this)) ||
             (northwest.isOf(MBMBlocks.POISON_SLIME_TREE_VINES_LARGE) || northeast.isOf(MBMBlocks.POISON_SLIME_TREE_VINES_LARGE)) ||
             (northwest.isOf(Blocks.SLIME_BLOCK) || northeast.isOf(Blocks.SLIME_BLOCK));
        } else if(direction == Direction.EAST) {
            pass = northeast.isOf(this) || southeast.isOf(this) ||
            (northwest.isOf(MBMBlocks.POISON_SLIME_TREE_VINES_LARGE) || southeast.isOf(MBMBlocks.POISON_SLIME_TREE_VINES_LARGE)) ||
            (northwest.isOf(Blocks.SLIME_BLOCK) || southeast.isOf(Blocks.SLIME_BLOCK));
        } else if(direction == Direction.SOUTH) {
            pass = southeast.isOf(this) || southwest.isOf(this) ||
            (southeast.isOf(MBMBlocks.POISON_SLIME_TREE_VINES_LARGE) || southwest.isOf(MBMBlocks.POISON_SLIME_TREE_VINES_LARGE)) ||
            (southeast.isOf(Blocks.SLIME_BLOCK) || southwest.isOf(Blocks.SLIME_BLOCK));
        } else if(direction == Direction.WEST) {
            pass = northwest.isOf(this) || southwest.isOf(this) ||
            (northwest.isOf(MBMBlocks.POISON_SLIME_TREE_VINES_LARGE) || southwest.isOf(MBMBlocks.POISON_SLIME_TREE_VINES_LARGE)) ||
            (northwest.isOf(Blocks.SLIME_BLOCK) || southwest.isOf(Blocks.SLIME_BLOCK));
        }

        return pass;
    }

    /* private Direction findDirection(int x, int z) {

        if(x == 0 && z == -1) {
            return Direction.NORTH;
        } else if(x == 1 && z == 0) {
            return Direction.EAST;
        } else if(x == 0 && z == 1) {
            return Direction.SOUTH;
        } else if(x == -1 && z == 0) {
            return Direction.WEST;
        }

        return null;
    } */

    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        switch (type) {
            case WATER: return false;
            default: return true;
        }
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return Block.sideCoversSmallSquare(world, pos.down(), Direction.UP);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {

        if(state.get(PLAYER_PLACED)) {
            return;
        }

        if(state.get(AGE) == 10) {
            return;
        }

        if(random.nextInt(3) == 0 && state.get(AGE) < 10) {
            BlockState replaceState = state
            .with(PoisonSlimeTreeVines.CLOSED_DIRECTION_NORTH, state.get(PoisonSlimeTreeVines.CLOSED_DIRECTION_NORTH))
            .with(PoisonSlimeTreeVines.CLOSED_DIRECTION_EAST, state.get(PoisonSlimeTreeVines.CLOSED_DIRECTION_EAST))
            .with(PoisonSlimeTreeVines.CLOSED_DIRECTION_SOUTH, state.get(PoisonSlimeTreeVines.CLOSED_DIRECTION_SOUTH))
            .with(PoisonSlimeTreeVines.CLOSED_DIRECTION_WEST, state.get(PoisonSlimeTreeVines.CLOSED_DIRECTION_WEST))
            .with(PoisonSlimeTreeVines.BRANCH, state.get(PoisonSlimeTreeVines.BRANCH))
            .with(PoisonSlimeTreeVines.AGE, state.get(PoisonSlimeTreeVines.AGE) + 1)
            .with(PoisonSlimeTreeVines.DISTANCE, state.get(PoisonSlimeTreeVines.DISTANCE))
            .with(PoisonSlimeTreeVines.WATERLOGGED, state.get(PoisonSlimeTreeVines.WATERLOGGED));

            /* .with(PoisonSlimeTreeVines.OPEN_DIRECTION_NORTH, state.get(PoisonSlimeTreeVines.OPEN_DIRECTION_NORTH))
            .with(PoisonSlimeTreeVines.OPEN_DIRECTION_EAST, state.get(PoisonSlimeTreeVines.OPEN_DIRECTION_EAST))
            .with(PoisonSlimeTreeVines.OPEN_DIRECTION_SOUTH, state.get(PoisonSlimeTreeVines.OPEN_DIRECTION_SOUTH))
            .with(PoisonSlimeTreeVines.OPEN_DIRECTION_WEST, state.get(PoisonSlimeTreeVines.OPEN_DIRECTION_WEST)) */

            world.setBlockState(pos, replaceState);
        }

        if(state.get(AGE) > 8) {
            if(state.get(PoisonSlimeTreeVines.WATERLOGGED)) {
                BlockPos.Mutable startPosMutable = pos.mutableCopy();

                for (Direction direction : Direction.values()) {

                    if(!(direction == Direction.DOWN) && state.get(BRANCH) != 0) {
                        BlockPos DifPos = startPosMutable.offset(direction);

                        if(world.getBlockState(DifPos).isOf(Blocks.WATER) && random.nextInt(4) == 0) { /* !world.getBlockState(DifPos).isOf(this) &&  *//* world.getFluidState(DifPos).getFluid() == Fluids.WATER */ 
                            world.setBlockState(DifPos, Blocks.SLIME_BLOCK.getDefaultState());

                            BlockPos northPos = pos.north();
                            BlockPos eastPos = pos.east();
                            BlockPos southPos = pos.south();
                            BlockPos westPos = pos.west();
                            BlockState northState = world.getBlockState(northPos);
                            BlockState eastState = world.getBlockState(eastPos);
                            BlockState southState = world.getBlockState(southPos);
                            BlockState westState = world.getBlockState(westPos);

                            BlockState replaceState = state
                            .with(PoisonSlimeTreeVines.CLOSED_DIRECTION_NORTH, !northState.isOf(Blocks.AIR) && !northState.isOf(Blocks.WATER))
                            .with(PoisonSlimeTreeVines.CLOSED_DIRECTION_EAST, !eastState.isOf(Blocks.AIR) && !eastState.isOf(Blocks.WATER))
                            .with(PoisonSlimeTreeVines.CLOSED_DIRECTION_SOUTH, !southState.isOf(Blocks.AIR) && !southState.isOf(Blocks.WATER))
                            .with(PoisonSlimeTreeVines.CLOSED_DIRECTION_WEST, !westState.isOf(Blocks.AIR) && !westState.isOf(Blocks.WATER))
                            .with(PoisonSlimeTreeVines.BRANCH, state.get(PoisonSlimeTreeVines.BRANCH) - 1)
                            .with(PoisonSlimeTreeVines.AGE, state.get(PoisonSlimeTreeVines.AGE))
                            .with(PoisonSlimeTreeVines.DISTANCE, state.get(PoisonSlimeTreeVines.DISTANCE))
                            .with(PoisonSlimeTreeVines.WATERLOGGED, state.get(PoisonSlimeTreeVines.WATERLOGGED));

                            world.setBlockState(pos, replaceState);

                            return;
                        }
                    }
                }
                }
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

                            BlockPos northPos = DifPos.north();
                            BlockPos eastPos = DifPos.east();
                            BlockPos southPos = DifPos.south();
                            BlockPos westPos = DifPos.west();
                            BlockState northState = world.getBlockState(northPos);
                            BlockState eastState = world.getBlockState(eastPos);
                            BlockState southState = world.getBlockState(southPos);
                            BlockState westState = world.getBlockState(westPos);

                            FluidState fuildstate = world.getFluidState(DifPos);

                            int branchChance = 0;

                            branchChance += random.nextBetween(0, 1);
                            branchChance += random.nextBetween(0, 1);
                            branchChance += random.nextBetween(0, 1);

                            world.setBlockState(
                            DifPos,
                            this.getDefaultState()
                                .with(PoisonSlimeTreeVines.CLOSED_DIRECTION_NORTH, !northState.isOf(Blocks.AIR) && !northState.isOf(Blocks.WATER))
                                .with(PoisonSlimeTreeVines.CLOSED_DIRECTION_EAST, !eastState.isOf(Blocks.AIR) && !eastState.isOf(Blocks.WATER))
                                .with(PoisonSlimeTreeVines.CLOSED_DIRECTION_SOUTH, !southState.isOf(Blocks.AIR) && !southState.isOf(Blocks.WATER))
                                .with(PoisonSlimeTreeVines.CLOSED_DIRECTION_WEST, !westState.isOf(Blocks.AIR) && !westState.isOf(Blocks.WATER))
                                .with(PoisonSlimeTreeVines.DISTANCE, state.get(PoisonSlimeTreeVines.DISTANCE) + 1)
                                .with(PoisonSlimeTreeVines.BRANCH, branchChance)
                                .with(Properties.WATERLOGGED, fuildstate.getFluid() == Fluids.WATER)
                            );

                            /* .with(PoisonSlimeTreeVines.OPEN_DIRECTION_NORTH, northState.isOf(this))
                            .with(PoisonSlimeTreeVines.OPEN_DIRECTION_EAST, eastState.isOf(this))
                            .with(PoisonSlimeTreeVines.OPEN_DIRECTION_SOUTH, southState.isOf(this))
                            .with(PoisonSlimeTreeVines.OPEN_DIRECTION_WEST, westState.isOf(this)) */

                            northPos = pos.north();
                            eastPos = pos.east();
                            southPos = pos.south();
                            westPos = pos.west();
                            northState = world.getBlockState(northPos);
                            eastState = world.getBlockState(eastPos);
                            southState = world.getBlockState(southPos);
                            westState = world.getBlockState(westPos);

                            BlockState replaceState = state
                            .with(PoisonSlimeTreeVines.CLOSED_DIRECTION_NORTH, !northState.isOf(Blocks.AIR) && !northState.isOf(Blocks.WATER))
                            .with(PoisonSlimeTreeVines.CLOSED_DIRECTION_EAST, !eastState.isOf(Blocks.AIR) && !eastState.isOf(Blocks.WATER))
                            .with(PoisonSlimeTreeVines.CLOSED_DIRECTION_SOUTH, !southState.isOf(Blocks.AIR) && !southState.isOf(Blocks.WATER))
                            .with(PoisonSlimeTreeVines.CLOSED_DIRECTION_WEST, !westState.isOf(Blocks.AIR) && !westState.isOf(Blocks.WATER))
                            .with(PoisonSlimeTreeVines.BRANCH, state.get(PoisonSlimeTreeVines.BRANCH) - 1)
                            .with(PoisonSlimeTreeVines.AGE, state.get(PoisonSlimeTreeVines.AGE))
                            .with(PoisonSlimeTreeVines.DISTANCE, state.get(PoisonSlimeTreeVines.DISTANCE))
                            .with(PoisonSlimeTreeVines.WATERLOGGED, state.get(PoisonSlimeTreeVines.WATERLOGGED));

                            /* .with(PoisonSlimeTreeVines.OPEN_DIRECTION_NORTH, northState.isOf(this))
                            .with(PoisonSlimeTreeVines.OPEN_DIRECTION_EAST, eastState.isOf(this))
                            .with(PoisonSlimeTreeVines.OPEN_DIRECTION_SOUTH, southState.isOf(this))
                            .with(PoisonSlimeTreeVines.OPEN_DIRECTION_WEST, westState.isOf(this)) */

                            world.setBlockState(pos, replaceState);
                        }
                    }
                }
            }
        }

        /* if(random.nextInt(2) == 0) {
            BlockPos.Mutable mutable = pos.mutableCopy();

            for (Direction offSetDirection: DIRECTIONS) {
                BlockState checkState =  world.getBlockState(mutable.offset(offSetDirection));
                if(checkState.isOf(Blocks.AIR)) {
                    System.out.println("[POISON SLIME TREE VINES] Could Change at " + offSetDirection.toString());
                }
            }
        } */

    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {

        BlockPos northPos = pos.north();
        BlockPos eastPos = pos.east();
        BlockPos southPos = pos.south();
        BlockPos westPos = pos.west();
        BlockState northState = world.getBlockState(northPos);
        BlockState eastState = world.getBlockState(eastPos);
        BlockState southState = world.getBlockState(southPos);
        BlockState westState = world.getBlockState(westPos);

        BlockState replaceState = state
        .with(PoisonSlimeTreeVines.CLOSED_DIRECTION_NORTH, !northState.isOf(Blocks.AIR) && !northState.isOf(Blocks.WATER))
        .with(PoisonSlimeTreeVines.CLOSED_DIRECTION_EAST, !eastState.isOf(Blocks.AIR) && !eastState.isOf(Blocks.WATER))
        .with(PoisonSlimeTreeVines.CLOSED_DIRECTION_SOUTH, !southState.isOf(Blocks.AIR) && !southState.isOf(Blocks.WATER))
        .with(PoisonSlimeTreeVines.CLOSED_DIRECTION_WEST, !westState.isOf(Blocks.AIR) && !westState.isOf(Blocks.WATER));

        /* .with(PoisonSlimeTreeVines.OPEN_DIRECTION_NORTH, northState.isOf(this))
        .with(PoisonSlimeTreeVines.OPEN_DIRECTION_NORTH, eastState.isOf(this))
        .with(PoisonSlimeTreeVines.OPEN_DIRECTION_NORTH, southState.isOf(this))
        .with(PoisonSlimeTreeVines.OPEN_DIRECTION_NORTH, westState.isOf(this)) */

        world.setBlockState(pos, replaceState);
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
        builder.add(PoisonSlimeTreeVines.PLAYER_PLACED, PoisonSlimeTreeVines.WATERLOGGED, PoisonSlimeTreeVines.CLOSED_DIRECTION_NORTH, PoisonSlimeTreeVines.CLOSED_DIRECTION_EAST, PoisonSlimeTreeVines.CLOSED_DIRECTION_SOUTH, PoisonSlimeTreeVines.CLOSED_DIRECTION_WEST, PoisonSlimeTreeVines.BRANCH, PoisonSlimeTreeVines.DISTANCE, PoisonSlimeTreeVines.AGE);
        /* PoisonSlimeTreeVines.OPEN_DIRECTION_NORTH, PoisonSlimeTreeVines.OPEN_DIRECTION_EAST, PoisonSlimeTreeVines.OPEN_DIRECTION_SOUTH, PoisonSlimeTreeVines.OPEN_DIRECTION_WEST,  */
    }


    static {
        /* OPEN_DIRECTION_NORTH = BooleanProperty.of("opendirectionnorth");
        OPEN_DIRECTION_EAST = BooleanProperty.of("opendirectioneast");
        OPEN_DIRECTION_SOUTH = BooleanProperty.of("opendirectionsouth");
        OPEN_DIRECTION_WEST = BooleanProperty.of("opendirectionwest"); */
        CLOSED_DIRECTION_NORTH = BooleanProperty.of("closeddirectionnorth");
        CLOSED_DIRECTION_EAST = BooleanProperty.of("closeddirectioneast");
        CLOSED_DIRECTION_SOUTH = BooleanProperty.of("closeddirectionsouth");
        CLOSED_DIRECTION_WEST = BooleanProperty.of("closeddirectionwest");

        DISTANCE = IntProperty.of("distance", 0, 5);
        AGE = IntProperty.of("age", 0, 10);
        BRANCH = IntProperty.of("branch", 0, 3);

        PLAYER_PLACED = BooleanProperty.of("playerplaced");

        WATERLOGGED = Properties.WATERLOGGED;

        BASE_SHAPE = Block.createCuboidShape(4, 0, 4, 12, 8, 12);
        NORTH_SHAPE = Block.createCuboidShape(4, 0, 0, 12, 8, 4);
        EAST_SHAPE = Block.createCuboidShape(12, 0, 4, 16, 8, 12);
        SOUTH_SHAPE = Block.createCuboidShape(4, 0, 12, 12, 8, 16);
        WEST_SHAPE = Block.createCuboidShape(0, 0, 4, 4, 8, 12);

        BASE_SHAPE_COLLISION = Block.createCuboidShape(3, 0, 3, 13, 9, 13);
        NORTH_SHAPE_COLLISION = Block.createCuboidShape(3, 0, 0, 13, 9, 3);
        EAST_SHAPE_COLLISION = Block.createCuboidShape(13, 0, 3, 16, 9, 13);
        SOUTH_SHAPE_COLLISION = Block.createCuboidShape(3, 0, 13, 13, 9, 16);
        WEST_SHAPE_COLLISION = Block.createCuboidShape(0, 0, 3, 3, 9, 13);
    }
}
