package com.tylervp.block;

import java.util.Random;

import com.tylervp.block.enums.WireSides;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class GrapeLeaves extends Block {
    public static final BooleanProperty SHOWBOTTOM, PLAYERPLACED, BEARFRUIT, GROWVINES, PARENTREMOVED, COPPERWIRE;
    public static final IntProperty MAXGROW, CURRENTGROW, DISTANCE;
    public static final DirectionProperty GROWNDIRECTION;

    public GrapeLeaves(Settings settings) {
        super(settings.nonOpaque());
        this.setDefaultState(this.getDefaultState().with(SHOWBOTTOM, false).with(MAXGROW, 5).with(CURRENTGROW, 0).with(PLAYERPLACED, false).with(GROWNDIRECTION, Direction.NORTH).with(BEARFRUIT, true).with(PARENTREMOVED, false).with(DISTANCE, 0).with(GROWVINES, true).with(COPPERWIRE, false));
    }

    @Override
    public VoxelShape getSidesShape(BlockState state, BlockView world, BlockPos pos) {
		return VoxelShapes.empty();
	}

    @Override
    public int getOpacity(BlockState state, BlockView world, BlockPos pos) {
        return 1;
    }

    @Override
    public PistonBehavior getPistonBehavior(BlockState state) {
        return PistonBehavior.DESTROY;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack playerItem = player.getStackInHand(hand);
        Random random = world.getRandom();

        //playerItem.getItem() == Items.BONE_MEAL && (state.get(CURRENTGROW) < state.get(MAXGROW)) && (state.get(DISTANCE) < 6 && hasSpaceToGrow(world, state, pos)) && (state.get(BEARFRUIT) || (world.getBlockState(pos.down()).isAir() && state.get(GROWVINES)))
        if (playerItem.getItem() == Items.BONE_MEAL && canGrow(world, state, pos)) {
            if(!world.isClient()) {
                generate(state, (ServerWorld)world, pos, random, true, true);
            } else {
                spawnParticlesGrow(world, pos);
            }
            return ActionResult.success(world.isClient());
        }

        return super.onUse(state, world, pos, player, hand, hit);
    }

    private boolean canGrow(World world, BlockState state, BlockPos pos) {
        BlockPos.Mutable mutable = pos.mutableCopy();
        if(state.get(MAXGROW) != 0 && !state.get(PLAYERPLACED) ) {
            int currentgrow = state.get(CURRENTGROW);
            
            if(state.get(MAXGROW) > currentgrow){
                for (final Direction lv3 : Direction.values()) {
                    if(world.getBlockState(mutable.set(pos, lv3)).isAir() || world.getBlockState(mutable.set(pos, lv3)).isOf(MBMBlocks.COPPER_WIRE)) {
                        int distance = state.get(DISTANCE);

                        if(world.getBlockState(mutable.set(pos, lv3)).isOf(MBMBlocks.COPPER_WIRE)){
                            return true;
                        }

                        BlockState stateDownDown = world.getBlockState(mutable.set(pos, lv3).down());
                        if(!world.getBlockState(mutable.set(pos, lv3).up()).isAir() && !(stateDownDown.isOf(MBMBlocks.GRAPE_LEAVES) || stateDownDown.isOf(MBMBlocks.GRAPE_LEAVES_HANGING) || stateDownDown.isOf(MBMBlocks.PURPLE_GRAPES) || stateDownDown.isOf(MBMBlocks.GREEN_GRAPES) || stateDownDown.isAir())) {
                            return true;
                        } else {
                            if( distance < 6 ) {
                                BlockPos pos2 = mutable.set(pos, lv3).mutableCopy();
                                BlockPos.Mutable mutable2 = pos2.mutableCopy();
                                for (final Direction lv4 : Direction.values()) {
                                    if(lv4 != Direction.DOWN && (!world.getBlockState(mutable2.set(pos2, lv4)).isOf(this) && Block.isFaceFullSquare(world.getBlockState(mutable2.set(pos2, lv4)).getSidesShape(world, pos2), lv4.getOpposite()))) {
                                        return true;
                                    }
                                }
                            } else if( distance < 7 ) {
                                if((state.get(GROWVINES) && world.getBlockState(pos.down()).isAir())/*  || state.get(BEARFRUIT) */) {
                                    for (Direction direction : DIRECTIONS) {
                                        if(direction != Direction.UP && world.getBlockState(pos.offset(direction)).isAir()) {
                                            return true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }


    private static void spawnParticlesGrow(World world, BlockPos pos) {
        //double double3 = 0.5625;
        for (int i = 0; i < 4; i++) {
            Random random5 = world.random;
            for (final Direction lv : Direction.values()) {
                BlockPos blockPos10 = pos.offset(lv);
                if (!world.getBlockState(blockPos10).isOpaqueFullCube(world, blockPos10)) {
                    Direction.Axis axis11 = lv.getAxis();
                    double double12 = (axis11 == Direction.Axis.X) ? (0.5 + 0.5625 * lv.getOffsetX()) : random5.nextFloat();
                    double double14 = (axis11 == Direction.Axis.Y) ? (0.5 + 0.5625 * lv.getOffsetY()) : random5.nextFloat();
                    double double16 = (axis11 == Direction.Axis.Z) ? (0.5 + 0.5625 * lv.getOffsetZ()) : random5.nextFloat();
                    //DustParticleEffect dirtPartical = new DustParticleEffect(0.93f, 0.63f, 0.45f, 1.0f);
                    
                    world.addParticle(ParticleTypes.HAPPY_VILLAGER, pos.getX() + double12, pos.getY() + double14, pos.getZ() + double16, 0.0, 0.0, 0.0);
                }
            }   
        }
        
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {

        if(!state.get(PLAYERPLACED)){
            BlockPos.Mutable mutable = pos.mutableCopy();
            BlockPos futurePos = mutable.set(pos, state.get(GROWNDIRECTION));
            BlockState futureState = world.getBlockState(futurePos);

            if(futureState.isOf(MBMBlocks.GRAPE_LEAVES)) {
                //Handle Current Grow
                int currentgrow = futureState.get(GrapeLeaves.CURRENTGROW);
                if(currentgrow > 0) {
                    SetState(StateOrdinals.CURRENTGROW, currentgrow - 1, world, futurePos, futureState);
                }
            }

            SetState(StateOrdinals.PARENTREMOVED, true, world, pos, state);
        }

        super.onBreak(world, pos, state, player);
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, BlockEntity blockEntity, ItemStack stack) {
        if(state.get(COPPERWIRE)) {
            Direction.Axis axis = state.get(GROWNDIRECTION).getAxis();

            if(axis == Direction.Axis.Y) {
                if(world.getBlockState(pos.north()).isOf(MBMBlocks.COPPER_WIRE)) {
                    axis = world.getBlockState(pos.north()).get(Wire.AXIS);
                } else if(world.getBlockState(pos.east()).isOf(MBMBlocks.COPPER_WIRE)) {
                    axis = world.getBlockState(pos.east()).get(Wire.AXIS);
                } else if(world.getBlockState(pos.south()).isOf(MBMBlocks.COPPER_WIRE)) {
                    axis = world.getBlockState(pos.south()).get(Wire.AXIS);
                } else if(world.getBlockState(pos.west()).isOf(MBMBlocks.COPPER_WIRE)) {
                    axis = world.getBlockState(pos.west()).get(Wire.AXIS);
                } else if(world.getBlockState(pos.north()).isOf(this)) {
                    axis = world.getBlockState(pos.north()).get(GROWNDIRECTION).getAxis();
                } else if(world.getBlockState(pos.east()).isOf(this)) {
                    axis = world.getBlockState(pos.east()).get(GROWNDIRECTION).getAxis();
                } else if(world.getBlockState(pos.south()).isOf(this)) {
                    axis = world.getBlockState(pos.south()).get(GROWNDIRECTION).getAxis();
                } else if(world.getBlockState(pos.west()).isOf(this)) {
                    axis = world.getBlockState(pos.west()).get(GROWNDIRECTION).getAxis();
                }
            }
                world.setBlockState(pos, MBMBlocks.COPPER_WIRE.getDefaultState().with(Wire.AXIS, axis));
        }

        super.afterBreak(world, player, pos, state, blockEntity, stack);
    }

    public static boolean allowBottom(BlockState state) {
        return state.isAir() || state.isOf(MBMBlocks.PURPLE_GRAPES) || state.isOf(MBMBlocks.GREEN_GRAPES) || state.isOf(MBMBlocks.GRAPE_LOG) || state.isOf(MBMBlocks.GRAPE_LEAVES_HANGING) || state.isOf(MBMBlocks.GRAPE_SPUR);
    }

    public static int maxGrowRandomizer(Random random) {
        if(random.nextInt(2) == 0) {
            return 3;
        } else if(random.nextInt(3) == 0) {
            return 4;
        } else if(random.nextInt(4) == 0) {
            return 5;
        } else if(random.nextInt(5) == 0) {
            return 2;
        } else if(random.nextInt(6) == 0) {
            return 0;
        }

        return 1;
    }

    public static boolean shouldBearFruit(Random random) {
        return random.nextInt(3) == 0;
    }

    public static boolean shouldGrowVines(Random random) {
        return random.nextInt(3) == 0;
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        Random random = ctx.getWorld().getRandom();
        int maxgrow = maxGrowRandomizer(random);

        return this.getDefaultState().with(SHOWBOTTOM, allowBottom(ctx.getWorld().getBlockState(ctx.getBlockPos().down()))).with(MAXGROW, maxgrow).with(CURRENTGROW, 0).with(GROWNDIRECTION, ctx.getSide().getOpposite()).with(PLAYERPLACED, true).with(BEARFRUIT, shouldBearFruit(random)).with(GROWVINES, shouldGrowVines(random));
    }

    /* @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        super.onStateReplaced(state, world, pos, newState, moved);
    } */

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {

        if(neighborState.isOf(Blocks.MOVING_PISTON)) {
            //Calculate Current Grow
            int currentgrow = 0;
            for (Direction offSetDirection : DIRECTIONS) {
                BlockState checkState =  world.getBlockState(pos.offset(offSetDirection));
                if(offSetDirection != state.get(GROWNDIRECTION) && (checkState.isOf(MBMBlocks.GREEN_GRAPES) || checkState.isOf(MBMBlocks.PURPLE_GRAPES) || checkState.isOf(MBMBlocks.GRAPE_LEAVES) || checkState.isOf(MBMBlocks.GRAPE_LEAVES_HANGING))) {
                    if(checkState.isOf(MBMBlocks.GREEN_GRAPES) || checkState.isOf(MBMBlocks.PURPLE_GRAPES)) {
                        if(checkState.get(Grapes.FACE).getOpposite() == offSetDirection) {
                            currentgrow += 1;
                        }
                    } else if(checkState.isOf(MBMBlocks.GRAPE_LEAVES)) {
                        if(checkState.get(GrapeLeaves.GROWNDIRECTION) == offSetDirection && !checkState.get(PLAYERPLACED)) {
                            currentgrow += 1;
                        }
                    } else {
                        currentgrow += 1;
                    }
                }
            }

            if(currentgrow > state.get(MAXGROW)){
                currentgrow = state.get(MAXGROW);
            }

            return SetState(StateOrdinals.CURRENTGROW, currentgrow, StateOrdinals.SHOWBOTTOM, allowBottom(world.getBlockState(pos.down())), state);
        }

        /* if(neighborState.isOf(this)) {
            //System.out.println(world.getBlockState(pos.offset(state.get(GROWNDIRECTION))).getBlock());
            if(world.getBlockState(pos.offset(state.get(GROWNDIRECTION))).isOf(this)) {
                if(world.getBlockState(pos.offset(state.get(GROWNDIRECTION))).get(PARENTREMOVED)) {
                    if(!state.get(PLAYERPLACED)) {
                        return SetState(StateOrdinals.PARENTREMOVED, true, state);
                    }
                }
            }
        }
        */

        if(neighborState.isOf(this)) {
            if(world.getBlockState(pos.offset(state.get(GROWNDIRECTION))).isOf(this)) {
                if(world.getBlockState(pos.offset(state.get(GROWNDIRECTION))).get(PARENTREMOVED)) {
                    if(!state.get(PLAYERPLACED) && !world.getBlockState(pos.offset(state.get(GROWNDIRECTION))).get(PLAYERPLACED)) {
                        return SetState(StateOrdinals.PARENTREMOVED, true, state);
                    }
                } else if(!world.getBlockState(pos.offset(state.get(GROWNDIRECTION))).get(PARENTREMOVED)) {
                    if(!state.get(PLAYERPLACED) && !world.getBlockState(pos.offset(state.get(GROWNDIRECTION))).get(PLAYERPLACED)) {
                        //return SetState(StateOrdinals.PARENTREMOVED, false, state);
                        return SetState(StateOrdinals.PARENTREMOVED, false, StateOrdinals.SHOWBOTTOM, allowBottom(world.getBlockState(pos.down())), state);
                    }
                }
            }
        }


        return SetState(StateOrdinals.SHOWBOTTOM, allowBottom(world.getBlockState(pos.down())), state);

        //return this.getDefaultState().with(SHOWBOTTOM, allowBottom(world.getBlockState(pos.down()))).with(MAXGROW, state.get(MAXGROW)).with(CURRENTGROW, currentgrow);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        super.randomTick(state, world, pos, random);

        generate(state, world, pos, random, false, false);
    }


    private void generate(BlockState state, ServerWorld world, BlockPos pos, Random random, boolean skipRandom, boolean skipGrapeBlock) {
        if(/* random.nextInt(2) == 0 &&  */state.get(PARENTREMOVED) && !state.get(PLAYERPLACED)){
            if(state.get(COPPERWIRE)) {
                Direction.Axis axis = state.get(GROWNDIRECTION).getAxis();

                if(axis == Direction.Axis.Y) {
                    if(world.getBlockState(pos.north()).isOf(MBMBlocks.COPPER_WIRE)) {
                        axis = world.getBlockState(pos.north()).get(Wire.AXIS);
                    } else if(world.getBlockState(pos.east()).isOf(MBMBlocks.COPPER_WIRE)) {
                        axis = world.getBlockState(pos.east()).get(Wire.AXIS);
                    } else if(world.getBlockState(pos.south()).isOf(MBMBlocks.COPPER_WIRE)) {
                        axis = world.getBlockState(pos.south()).get(Wire.AXIS);
                    } else if(world.getBlockState(pos.west()).isOf(MBMBlocks.COPPER_WIRE)) {
                        axis = world.getBlockState(pos.west()).get(Wire.AXIS);
                    } else if(world.getBlockState(pos.north()).isOf(this)) {
                        axis = world.getBlockState(pos.north()).get(GROWNDIRECTION).getAxis();
                    } else if(world.getBlockState(pos.east()).isOf(this)) {
                        axis = world.getBlockState(pos.east()).get(GROWNDIRECTION).getAxis();
                    } else if(world.getBlockState(pos.south()).isOf(this)) {
                        axis = world.getBlockState(pos.south()).get(GROWNDIRECTION).getAxis();
                    } else if(world.getBlockState(pos.west()).isOf(this)) {
                        axis = world.getBlockState(pos.west()).get(GROWNDIRECTION).getAxis();
                    }
                }

                world.setBlockState(pos, MBMBlocks.COPPER_WIRE.getDefaultState().with(Wire.AXIS, axis));
            } else {
                world.setBlockState(pos, Blocks.AIR.getDefaultState());
            }
            return;
        }

        BlockPos.Mutable mutable = pos.mutableCopy();
        if((random.nextInt(5) == 0 || skipRandom) && state.get(MAXGROW) != 0 && !state.get(PLAYERPLACED) ) {
            int currentgrow = state.get(CURRENTGROW);
            
            if(state.get(MAXGROW) > currentgrow){
                for (final Direction lv3 : Direction.values()) {
                    if(world.getBlockState(mutable.set(pos, lv3)).isAir() || world.getBlockState(mutable.set(pos, lv3)).isOf(MBMBlocks.COPPER_WIRE)) {
                        int distance = state.get(DISTANCE);
                        if(distance < 7) {
                            distance += 1;
                        }

                        BlockState grapeleaves = MBMBlocks.GRAPE_LEAVES.getDefaultState().with(MAXGROW, maxGrowRandomizer(random)).with(GrapeLeaves.SHOWBOTTOM, allowBottom(world.getBlockState(mutable.set(pos, lv3).down()))).with(GROWNDIRECTION, lv3.getOpposite()).with(BEARFRUIT, shouldBearFruit(random)).with(DISTANCE, distance).with(GROWVINES, shouldGrowVines(random));

                        if(world.getBlockState(mutable.set(pos, lv3)).isOf(MBMBlocks.COPPER_WIRE)){
                            if(world.getBlockState(mutable.set(pos, lv3)).get(Wire.AXIS) != Direction.Axis.Y && world.getBlockState(mutable.set(pos, lv3)).get(Wire.SIDE) == WireSides.MIDDLE) {
                                world.setBlockState(mutable.set(pos, lv3), grapeleaves.with(COPPERWIRE, true), 3);
                                SetState(StateOrdinals.CURRENTGROW, currentgrow + 1, world, pos, state);
                            }
                            return;
                        }

                        if(random.nextInt(10) == 0 || skipRandom) {
                            boolean updateCount = false;
                            if(random.nextInt(10) == 0 ) {
                                if(random.nextInt(2) == 0 && lv3 == Direction.DOWN && state.get(GROWVINES)) {
                                    world.setBlockState(mutable.set(pos, lv3), MBMBlocks.GRAPE_LEAVES_HANGING.getDefaultState(), 3); 
                                    updateCount = true;
                                } else {
                                    if(lv3 != Direction.UP && (state.get(BEARFRUIT) || skipGrapeBlock)) {
                                        world.setBlockState(mutable.set(pos, lv3), MBMBlocks.GREEN_GRAPES.getDefaultState().with(Grapes.FACE, lv3.getOpposite()).with(Grapes.PLAYERPLACED, false), 3);
                                        updateCount = true;
                                    }
                                }
                            } else {
                                if(random.nextInt(3) == 0) {
                                    if(random.nextInt(2) == 0 && lv3 == Direction.DOWN && state.get(GROWVINES)) {
                                        world.setBlockState(mutable.set(pos, lv3), MBMBlocks.GRAPE_LEAVES_HANGING.getDefaultState(), 3); 
                                        updateCount = true;
                                    } else {
                                        if(lv3 != Direction.UP && (state.get(BEARFRUIT) || skipGrapeBlock)) {
                                            world.setBlockState(mutable.set(pos, lv3), MBMBlocks.PURPLE_GRAPES.getDefaultState().with(Grapes.FACE, lv3.getOpposite()).with(Grapes.PLAYERPLACED, false), 3);
                                            updateCount = true;
                                        }
                                    }
                                    

                                } else {
                                    BlockState stateDownDown = world.getBlockState(mutable.set(pos, lv3).down());
                                    if(!world.getBlockState(mutable.set(pos, lv3).up()).isAir() && !(stateDownDown.isOf(MBMBlocks.GRAPE_LEAVES) || stateDownDown.isOf(MBMBlocks.GRAPE_LEAVES_HANGING) || stateDownDown.isOf(MBMBlocks.PURPLE_GRAPES) || stateDownDown.isOf(MBMBlocks.GREEN_GRAPES) || stateDownDown.isAir())) {
                                        if(world.getBlockState(mutable.set(pos, lv3).up()).isOf(this)) {
                                           world.setBlockState(mutable.set(pos, lv3), MBMBlocks.GRAPE_LEAVES_HANGING.getDefaultState(), 3); 
                                            updateCount = true; 
                                        }
                                        
                                    } else {
                                        if(distance < 7 ) {
                                            BlockPos pos2 = mutable.set(pos, lv3).mutableCopy();
                                            BlockPos.Mutable mutable2 = pos2.mutableCopy();
                                            for (final Direction lv4 : Direction.values()) {
                                                if(lv4 != Direction.DOWN && (!world.getBlockState(mutable2.set(pos2, lv4)).isOf(this) && Block.isFaceFullSquare(world.getBlockState(mutable2.set(pos2, lv4)).getSidesShape(world, pos2), lv4.getOpposite()))) {
                                                    world.setBlockState(mutable.set(pos, lv3), grapeleaves, 3);
                                                    updateCount = true;
                                                }
                                            }
                                        }
                                        
                                    }
                                }
                            }

                            if(updateCount) {
                                SetState(StateOrdinals.CURRENTGROW, currentgrow + 1, world, pos, state);
                                return;
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    protected void appendProperties(Builder<Block, BlockState> builder) {
        builder.add(SHOWBOTTOM, MAXGROW, CURRENTGROW, GROWNDIRECTION, PLAYERPLACED, BEARFRUIT, PARENTREMOVED, DISTANCE, GROWVINES, COPPERWIRE);
    }

    static {
        SHOWBOTTOM = BooleanProperty.of("showbottom");
        MAXGROW = IntProperty.of("maxgrow", 0, 5);
        CURRENTGROW = IntProperty.of("currentgrow", 0, 5);
        DISTANCE = IntProperty.of("distance", 0, 7);
        GROWNDIRECTION = Properties.FACING;
        PLAYERPLACED = BooleanProperty.of("playerplaced");
        BEARFRUIT = BooleanProperty.of("bearfruit");
        PARENTREMOVED = BooleanProperty.of("parentremoved");
        GROWVINES = BooleanProperty.of("growvines");
        COPPERWIRE = BooleanProperty.of("copperwire");
    }

    public static enum StateOrdinals {
        GROWNDIRECTION(1), PLAYERPLACED(2), MAXGROW(3), CURRENTGROW(4), SHOWBOTTOM(5), BEARFRUIT(6), PARENTREMOVED(7), DISTANCE(8), GROWVINES(9), COPPERWIRE(10);
        private int value;
        private StateOrdinals(int value) {
            this.value = value;
        }
    };

    public static void SetState(StateOrdinals ordinal, Object value, World world, BlockPos pos, BlockState state) {
        world.setBlockState(pos, SetState(ordinal, value, state));
    }

    public static BlockState SetState(StateOrdinals ordinal, Object value, BlockState state) {
        /* 
        Ordinal
        GROWNDIRECTION = 1
        PLAYERPLACED = 2
        MAXGROW = 3
        CURRENTGROW = 4
        SHOWBOTTOM = 5
        PARENTREMOVED = 6
        DISTANCE = 8
        GROWVINES = 9
        COPPERWIRE = 10
        */
        int maxgrow = state.get(GrapeLeaves.MAXGROW);
        int currentgrow = state.get(GrapeLeaves.CURRENTGROW);
        Direction growndirection = state.get(GrapeLeaves.GROWNDIRECTION);
        boolean playerplaced = state.get(GrapeLeaves.PLAYERPLACED);
        boolean showbottom = state.get(GrapeLeaves.SHOWBOTTOM);
        boolean bearfruit = state.get(GrapeLeaves.BEARFRUIT);
        boolean parentremoved = state.get(GrapeLeaves.PARENTREMOVED);
        int distance = state.get(GrapeLeaves.DISTANCE);
        boolean growvines = state.get(GrapeLeaves.GROWVINES);
        boolean copperwire = state.get(GrapeLeaves.COPPERWIRE);

        switch (ordinal.value) {
            case 1:
                growndirection = (Direction)value;
                break;
            case 2:
                playerplaced = (boolean)value;
                break;
            case 3:
                maxgrow = (int)value;
                break;
            case 4:
                currentgrow = (int)value;
                break;
            case 5:
                showbottom = (boolean)value;
                break;
            case 6:
                bearfruit = (boolean)value;
                break;
            case 7:
                parentremoved = (boolean)value;
                break;
            case 8:
                distance = (int)value;
                break;
            case 9:
                growvines = (boolean)value;
                break;
            case 10:
                copperwire = (boolean)value;
                break;
        }

        return MBMBlocks.GRAPE_LEAVES.getDefaultState().with(GrapeLeaves.GROWNDIRECTION, growndirection).with(GrapeLeaves.PLAYERPLACED, playerplaced).with(GrapeLeaves.MAXGROW, maxgrow).with(GrapeLeaves.SHOWBOTTOM, showbottom).with(GrapeLeaves.CURRENTGROW, currentgrow).with(GrapeLeaves.BEARFRUIT, bearfruit).with(GrapeLeaves.PARENTREMOVED, parentremoved).with(DISTANCE, distance).with(GROWVINES, growvines).with(COPPERWIRE, copperwire);
    }

    public static BlockState SetState(StateOrdinals ordinal, Object value, StateOrdinals ordinal2, Object value2, BlockState state) {
        /* 
        Ordinal
        GROWNDIRECTION = 1
        PLAYERPLACED = 2
        MAXGROW = 3
        CURRENTGROW = 4
        SHOWBOTTOM = 5
        PARENTREMOVED = 6
        DISTANCE = 8
        GROWVINES = 9
        COPPERWIRE = 10
        */
        int maxgrow = state.get(GrapeLeaves.MAXGROW);
        int currentgrow = state.get(GrapeLeaves.CURRENTGROW);
        Direction growndirection = state.get(GrapeLeaves.GROWNDIRECTION);
        boolean playerplaced = state.get(GrapeLeaves.PLAYERPLACED);
        boolean showbottom = state.get(GrapeLeaves.SHOWBOTTOM);
        boolean bearfruit = state.get(GrapeLeaves.BEARFRUIT);
        boolean parentremoved = state.get(GrapeLeaves.PARENTREMOVED);
        int distance = state.get(GrapeLeaves.DISTANCE);
        boolean growvines = state.get(GrapeLeaves.GROWVINES);
        boolean copperwire = state.get(GrapeLeaves.COPPERWIRE);

        switch (ordinal.value) {
            case 1:
                growndirection = (Direction)value;
                break;
            case 2:
                playerplaced = (boolean)value;
                break;
            case 3:
                maxgrow = (int)value;
                break;
            case 4:
                currentgrow = (int)value;
                break;
            case 5:
                showbottom = (boolean)value;
                break;
            case 6:
                bearfruit = (boolean)value;
                break;
            case 7:
                parentremoved = (boolean)value;
                break;
            case 8:
                distance = (int)value;
                break;
            case 9:
                growvines = (boolean)value;
                break;
            case 10:
                copperwire = (boolean)value;
                break;
        }

        switch (ordinal2.value) {
            case 1:
                growndirection = (Direction)value2;
                break;
            case 2:
                playerplaced = (boolean)value2;
                break;
            case 3:
                maxgrow = (int)value2;
                break;
            case 4:
                currentgrow = (int)value2;
                break;
            case 5:
                showbottom = (boolean)value2;
                break;
            case 6:
                bearfruit = (boolean)value2;
                break;
            case 7:
                parentremoved = (boolean)value2;
                break;
            case 8:
                distance = (int)value2;
                break;
            case 9:
                growvines = (boolean)value2;
                break;
            case 10:
                copperwire = (boolean)value2;
                break;
        }

        return MBMBlocks.GRAPE_LEAVES.getDefaultState().with(GrapeLeaves.GROWNDIRECTION, growndirection).with(GrapeLeaves.PLAYERPLACED, playerplaced).with(GrapeLeaves.MAXGROW, maxgrow).with(GrapeLeaves.SHOWBOTTOM, showbottom).with(GrapeLeaves.CURRENTGROW, currentgrow).with(GrapeLeaves.BEARFRUIT, bearfruit).with(GrapeLeaves.PARENTREMOVED, parentremoved).with(DISTANCE, distance).with(GROWVINES, growvines).with(COPPERWIRE, copperwire);
    }

    public static BlockState SetState(StateOrdinals ordinal, Object value, StateOrdinals ordinal2, Object value2, StateOrdinals ordinal3, Object value3, BlockState state) {
        /* 
        Ordinal
        GROWNDIRECTION = 1
        PLAYERPLACED = 2
        MAXGROW = 3
        CURRENTGROW = 4
        SHOWBOTTOM = 5
        PARENTREMOVED = 6
        DISTANCE = 8
        GROWVINES = 9
        COPPERWIRE = 10
        */
        int maxgrow = state.get(GrapeLeaves.MAXGROW);
        int currentgrow = state.get(GrapeLeaves.CURRENTGROW);
        Direction growndirection = state.get(GrapeLeaves.GROWNDIRECTION);
        boolean playerplaced = state.get(GrapeLeaves.PLAYERPLACED);
        boolean showbottom = state.get(GrapeLeaves.SHOWBOTTOM);
        boolean bearfruit = state.get(GrapeLeaves.BEARFRUIT);
        boolean parentremoved = state.get(GrapeLeaves.PARENTREMOVED);
        int distance = state.get(GrapeLeaves.DISTANCE);
        boolean growvines = state.get(GrapeLeaves.GROWVINES);
        boolean copperwire = state.get(GrapeLeaves.COPPERWIRE);

        switch (ordinal.value) {
            case 1:
                growndirection = (Direction)value;
                break;
            case 2:
                playerplaced = (boolean)value;
                break;
            case 3:
                maxgrow = (int)value;
                break;
            case 4:
                currentgrow = (int)value;
                break;
            case 5:
                showbottom = (boolean)value;
                break;
            case 6:
                bearfruit = (boolean)value;
                break;
            case 7:
                parentremoved = (boolean)value;
                break;
            case 8:
                distance = (int)value;
                break;
            case 9:
                growvines = (boolean)value;
                break;
            case 10:
                copperwire = (boolean)value;
                break;
        }

        switch (ordinal2.value) {
            case 1:
                growndirection = (Direction)value2;
                break;
            case 2:
                playerplaced = (boolean)value2;
                break;
            case 3:
                maxgrow = (int)value2;
                break;
            case 4:
                currentgrow = (int)value2;
                break;
            case 5:
                showbottom = (boolean)value2;
                break;
            case 6:
                bearfruit = (boolean)value2;
                break;
            case 7:
                parentremoved = (boolean)value2;
                break;
            case 8:
                distance = (int)value2;
                break;
            case 9:
                growvines = (boolean)value2;
                break;
            case 10:
                copperwire = (boolean)value2;
                break;
        }

        switch (ordinal3.value) {
            case 1:
                growndirection = (Direction)value3;
                break;
            case 2:
                playerplaced = (boolean)value3;
                break;
            case 3:
                maxgrow = (int)value3;
                break;
            case 4:
                currentgrow = (int)value3;
                break;
            case 5:
                showbottom = (boolean)value3;
                break;
            case 6:
                bearfruit = (boolean)value3;
                break;
            case 7:
                parentremoved = (boolean)value3;
                break;
            case 8:
                distance = (int)value3;
                break;
            case 9:
                growvines = (boolean)value3;
                break;
            case 10:
                copperwire = (boolean)value3;
                break;
        }

        return MBMBlocks.GRAPE_LEAVES.getDefaultState().with(GrapeLeaves.GROWNDIRECTION, growndirection).with(GrapeLeaves.PLAYERPLACED, playerplaced).with(GrapeLeaves.MAXGROW, maxgrow).with(GrapeLeaves.SHOWBOTTOM, showbottom).with(GrapeLeaves.CURRENTGROW, currentgrow).with(GrapeLeaves.BEARFRUIT, bearfruit).with(GrapeLeaves.PARENTREMOVED, parentremoved).with(DISTANCE, distance).with(GROWVINES, growvines).with(COPPERWIRE, copperwire);
    }
}