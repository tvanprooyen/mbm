package com.tylervp.block;

import com.tylervp.block.enums.ThinLogSide;
import com.tylervp.item.MBMItems;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ChainBlock;
import net.minecraft.block.LanternBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Direction.Axis;

public class ThinLogBlockExp extends Block implements Waterloggable {
    protected static final VoxelShape SHAPEX, SHAPEY, SHAPEZ/* , CONNECTNORTH, CONNECTEAST, CONNECTSOUTH, CONNECTWEST, CONNECTUP, CONNECTDOWN, RCNORTH, RCEAST, RCSOUTH, RCWEST, RCUP, RCDOWN */;
    public static final BooleanProperty PRESISTANT,CONNECT,LEAVES;
    /* public static final BooleanProperty MDNORTH, MDEAST, MDSOUTH, MDWEST, MDUP, MDDOWN;
    public static final BooleanProperty CNNORTH, CNEAST, CNSOUTH, CNWEST, CNUP, CNDOWN;
    public static final BooleanProperty RPNORTH, RPEAST, RPSOUTH, RPWEST, RPUP, RPDOWN; */
    public static final BooleanProperty WATERLOGGED;
    public static final EnumProperty<Direction.Axis> AXIS;
    public static final EnumProperty<ThinLogSide> NORTHTYPE,EASTTYPE,SOUTHTYPE,WESTTYPE, UPTYPE, DOWNTYPE;


    protected ThinLogBlockExp (AbstractBlock.Settings settings) {
        super(settings.nonOpaque());
        this.setDefaultState(this.stateManager.getDefaultState()
        .with(NORTHTYPE, ThinLogSide.NONE)
        .with(EASTTYPE, ThinLogSide.NONE)
        .with(SOUTHTYPE, ThinLogSide.NONE)
        .with(WESTTYPE, ThinLogSide.NONE)
        .with(UPTYPE, ThinLogSide.NONE)
        .with(DOWNTYPE, ThinLogSide.NONE)
        .with(PRESISTANT, false)
        .with(CONNECT, true)
        .with(Properties.WATERLOGGED, false));


        /* .with(CNNORTH, false)
        .with(CNEAST, false)
        .with(CNSOUTH, false)
        .with(CNWEST, false)
        .with(RPNORTH, false)
        .with(RPEAST, false)
        .with(RPSOUTH, false)
        .with(RPWEST, false) */
    }

    @Override
    public int getOpacity(BlockState state, BlockView world, BlockPos pos) {
        return 1;
    }

    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        switch (type) {
            case LAND: {
                return false;
            }
            case WATER: {
                return world.getFluidState(pos).isIn(FluidTags.WATER);
            }
            case AIR: {
                return false;
            }
            default: {
                return false;
            }
        }
    }


    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        //super.getOutlineShape(state, world, pos, context);
        Axis axis = state.get(AXIS);
        VoxelShape BUILDSHAPE;

        if(state.get(LEAVES)){
            return VoxelShapes.fullCube();
        } else {
            if(axis == Axis.X){
                BUILDSHAPE = ThinLogBlockExp.SHAPEX;
            } else if(axis == Axis.Y){
                BUILDSHAPE = ThinLogBlockExp.SHAPEY;
            } else if(axis == Axis.Z){
                BUILDSHAPE = ThinLogBlockExp.SHAPEZ;
            } else {
                BUILDSHAPE = VoxelShapes.empty();
            }

            /* if(state.get(CONNECT)) {
                if(state.get(NORTHTYPE) == ThinLogSide.LOG) {
                    BUILDSHAPE = VoxelShapes.union(BUILDSHAPE, ThinLogBlockExp.CONNECTNORTH);
                }
                if(state.get(EASTTYPE) == ThinLogSide.LOG) {
                    BUILDSHAPE = VoxelShapes.union(BUILDSHAPE, ThinLogBlockExp.CONNECTEAST);
                }
                if(state.get(SOUTHTYPE) == ThinLogSide.LOG) {
                    BUILDSHAPE = VoxelShapes.union(BUILDSHAPE, ThinLogBlockExp.CONNECTSOUTH);
                }
                if(state.get(WESTTYPE) == ThinLogSide.LOG) {
                    BUILDSHAPE = VoxelShapes.union(BUILDSHAPE, ThinLogBlockExp.CONNECTWEST);
                }
                if(state.get(UPTYPE) == ThinLogSide.LOG) {
                    BUILDSHAPE = VoxelShapes.union(BUILDSHAPE, ThinLogBlockExp.CONNECTUP);
                }
                if(state.get(DOWNTYPE) == ThinLogSide.LOG) {
                    BUILDSHAPE = VoxelShapes.union(BUILDSHAPE, ThinLogBlockExp.CONNECTDOWN);
                }
            } */

            /* if(state.get(CNNORTH)) {
                BUILDSHAPE = VoxelShapes.union(BUILDSHAPE, ThinLogBlockExp.RCNORTH);
            }
            if(state.get(CNEAST)) {
                BUILDSHAPE = VoxelShapes.union(BUILDSHAPE, ThinLogBlockExp.RCEAST);
            }
            if(state.get(CNSOUTH)) {
                BUILDSHAPE = VoxelShapes.union(BUILDSHAPE, ThinLogBlockExp.RCSOUTH);
            }
            if(state.get(CNWEST)) {
                BUILDSHAPE = VoxelShapes.union(BUILDSHAPE, ThinLogBlockExp.RCWEST);
            }
            if(state.get(CNUP)) {
                BUILDSHAPE = VoxelShapes.union(BUILDSHAPE, ThinLogBlockExp.RCUP);
            }
            if(state.get(CNDOWN)) {
                BUILDSHAPE = VoxelShapes.union(BUILDSHAPE, ThinLogBlockExp.RCDOWN);
            } */

            return BUILDSHAPE;
        }
    }

    private ThinLogSide isConnected(WorldAccess world, BlockPos pos, Axis axis, Direction direction) {
        BlockState stateDirection = getStateDirection(world, pos, direction);
        BlockState state = world.getBlockState(pos);
        ThinLogSide connected = ThinLogSide.NONE;

        if(allowedDirection(axis, direction)) {
            if(stateDirection.getBlock() == this) {
                if(state.getBlock() == this) {
                    if(state.get(CONNECT)) {
                        connected = ThinLogSide.LOG;
                    }
                }
            }
        }

        return connected;
    }

    private ThinLogSide isConnected(WorldAccess world, BlockPos pos, Axis axis, Direction direction, boolean connect) {
        BlockState stateDirection = getStateDirection(world, pos, direction);
        BlockState state = world.getBlockState(pos);
        ThinLogSide connected = ThinLogSide.NONE;

        if(allowedDirection(axis, direction)) {
            if(stateDirection.getBlock() == this) {
                if(connect) {
                    connected = ThinLogSide.LOG;
                }
            }
        }

        return connected;
    }

    /* private boolean isConnected(WorldAccess world, BlockPos pos, Axis axis, Direction direction) {
        BlockState stateDirection = getStateDirection(world, pos, direction);
        BlockState state = world.getBlockState(pos);
        boolean connected = false;

        if(allowedDirection(axis, direction)) {
            if(stateDirection.getBlock() == this) {
                if(state.getBlock() == this) {
                    connected = state.get(CONNECT);
                }
            }
        }

        return connected;
    }

    private boolean isConnected(WorldAccess world, BlockPos pos, Axis axis, Direction direction, boolean connect) {
        BlockState stateDirection = getStateDirection(world, pos, direction);
        BlockState state = world.getBlockState(pos);
        boolean connected = false;

        if(allowedDirection(axis, direction)) {
            if(stateDirection.getBlock() == this) {
                connected = connect;
            }
        }

        return connected;
    } */


    private BlockState getStateDirection(WorldAccess world, BlockPos pos, Direction direction) {
        BlockState state =  world.getBlockState(pos);

        switch (direction) {
            case NORTH:
            state = world.getBlockState(pos.north());
            break;
            case EAST:
                state = world.getBlockState(pos.east());
                break;
            case SOUTH:
                state = world.getBlockState(pos.south());
                break;
            case WEST:
                state = world.getBlockState(pos.west());
                break;
            case UP:
                state = world.getBlockState(pos.up());
                break;
            case DOWN:
                state = world.getBlockState(pos.down());
                break;
        }
        return state;
    }


    private boolean allowedDirection(Axis axis, Direction direction) {
        boolean isAllowed = false;
            switch (axis) {
                case X:
                    /*
                     * NSUD
                     */
                    if(direction == Direction.NORTH || direction == Direction.UP || direction == Direction.SOUTH || direction == Direction.DOWN) {
                        isAllowed = true;
                    }
                    break;
                case Y:
                    /*
                     * NESW
                     */
                    if(direction == Direction.NORTH || direction == Direction.EAST || direction == Direction.SOUTH || direction == Direction.WEST) {
                        isAllowed = true;
                    }
                    break;
                case Z:
                    /*
                     * EWUD
                     */
                    if(direction == Direction.UP || direction == Direction.EAST || direction == Direction.DOWN || direction == Direction.WEST) {
                        isAllowed = true;
                    }
                    break;
            }
            return isAllowed;
    }


    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState newState, WorldAccess world, BlockPos pos, BlockPos posFrom) {
         if (state.<Boolean>get((Property<Boolean>)ThinLogBlockExp.WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        return (BlockState)this.getDefaultState()
        .with(AXIS, state.get(AXIS))
        .with(CONNECT, state.get(CONNECT))
        .with(NORTHTYPE, isConnected(world, pos, state.get(AXIS), Direction.NORTH))
        .with(EASTTYPE, isConnected(world, pos, state.get(AXIS), Direction.EAST))
        .with(SOUTHTYPE, isConnected(world, pos, state.get(AXIS), Direction.SOUTH))
        .with(WESTTYPE, isConnected(world, pos, state.get(AXIS), Direction.WEST))
        .with(UPTYPE, isConnected(world, pos, state.get(AXIS), Direction.UP))
        .with(DOWNTYPE, isConnected(world, pos, state.get(AXIS), Direction.DOWN))
        .with(Properties.WATERLOGGED, state.get(ThinLogBlock.WATERLOGGED));
    }


    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos pos = ctx.getBlockPos();
        World world = ctx.getWorld();
        PlayerEntity player = ctx.getPlayer();
        FluidState fuildstate = ctx.getWorld().getFluidState(pos);

        return (BlockState)this.getDefaultState()
        .with(AXIS, ctx.getSide().getAxis())
        .with(CONNECT, !ctx.getPlayer().isSneaking())
        .with(NORTHTYPE, isConnected(world, pos, ctx.getSide().getAxis(), Direction.NORTH, !ctx.getPlayer().isSneaking()))
        .with(EASTTYPE, isConnected(world, pos, ctx.getSide().getAxis(), Direction.EAST, !ctx.getPlayer().isSneaking()))
        .with(SOUTHTYPE, isConnected(world, pos, ctx.getSide().getAxis(), Direction.SOUTH, !ctx.getPlayer().isSneaking()))
        .with(WESTTYPE, isConnected(world, pos, ctx.getSide().getAxis(), Direction.WEST, !ctx.getPlayer().isSneaking()))
        .with(UPTYPE, isConnected(world, pos, ctx.getSide().getAxis(), Direction.UP, !ctx.getPlayer().isSneaking()))
        .with(DOWNTYPE, isConnected(world, pos, ctx.getSide().getAxis(), Direction.DOWN, !ctx.getPlayer().isSneaking()))
        .with(Properties.WATERLOGGED, fuildstate.getFluid() == Fluids.WATER);
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
        builder.add(LEAVES,PRESISTANT,CONNECT,WATERLOGGED, AXIS, NORTHTYPE, EASTTYPE, SOUTHTYPE, WESTTYPE, UPTYPE, DOWNTYPE/* , RPNORTH, RPEAST, RPSOUTH, RPWEST, RPUP, RPDOWN, CNNORTH, CNEAST, CNSOUTH, CNWEST, CNUP, CNDOWN */);
    }

    static {
        SHAPEZ = Block.createCuboidShape(4.0, 4.0, 0.0, 12.0, 12.0, 16.0);
        SHAPEX = Block.createCuboidShape(0.0, 4.0, 4.0, 16.0, 12.0, 12.0);
        SHAPEY = Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 16.0, 12.0);

        /* RCNORTH = Block.createCuboidShape(7, 7, 0, 9, 9, 4);
        RCEAST = Block.createCuboidShape(12, 7, 7, 16, 9, 9);
        RCSOUTH = Block.createCuboidShape(7, 7, 12, 9, 9, 16);
        RCWEST = Block.createCuboidShape(0, 7, 7, 4, 9, 9);
        RCUP = Block.createCuboidShape(6.0, 0.0, 6.0, 10.0, 4, 10.0);
        RCDOWN = Block.createCuboidShape(6.0, 12.0, 6.0, 10.0, 16.0, 10.0);

        CONNECTNORTH = Block.createCuboidShape(4, 4, 0, 12, 12, 4);
        CONNECTEAST = Block.createCuboidShape(12, 4, 4, 16, 12, 12);
        CONNECTSOUTH = Block.createCuboidShape(4, 4, 12, 12, 12, 16);
        CONNECTWEST = Block.createCuboidShape(0, 4, 4, 4, 12, 12);
        CONNECTUP = Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 4, 12.0);
        CONNECTDOWN = Block.createCuboidShape(4.0, 12.0, 4.0, 12.0, 16.0, 12.0); */

        AXIS = Properties.AXIS;

        PRESISTANT = BooleanProperty.of("presistant");
        CONNECT = BooleanProperty.of("connect");
        LEAVES = BooleanProperty.of("leaves");

        NORTHTYPE = EnumProperty.of("northtype", ThinLogSide.class);
        EASTTYPE = EnumProperty.of("easttype", ThinLogSide.class);
        SOUTHTYPE = EnumProperty.of("southtype", ThinLogSide.class);
        WESTTYPE = EnumProperty.of("westtype", ThinLogSide.class);
        UPTYPE = EnumProperty.of("uptype", ThinLogSide.class);
        DOWNTYPE = EnumProperty.of("downtype", ThinLogSide.class);

        /* CNNORTH = BooleanProperty.of("chainnorth");
        CNEAST = BooleanProperty.of("chaineast");
        CNSOUTH = BooleanProperty.of("chainsouth");
        CNWEST = BooleanProperty.of("chainwest");
        CNUP = BooleanProperty.of("chainup");
        CNDOWN = BooleanProperty.of("chaindown");

        RPNORTH = BooleanProperty.of("ropenorth");
        RPEAST = BooleanProperty.of("ropeeast");
        RPSOUTH = BooleanProperty.of("ropesouth");
        RPWEST = BooleanProperty.of("ropewest");
        RPUP = BooleanProperty.of("ropedown");
        RPDOWN = BooleanProperty.of("ropeup"); */


        WATERLOGGED = Properties.WATERLOGGED;
    }
}