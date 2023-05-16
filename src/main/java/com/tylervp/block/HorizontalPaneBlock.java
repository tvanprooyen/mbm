package com.tylervp.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Direction.Axis;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class HorizontalPaneBlock extends PillarBlock implements Waterloggable {
    protected static final VoxelShape X_POLE_SHAPE, X_NORTH_SHAPE, X_UP_SHAPE, X_SOUTH_SHAPE, X_DOWN_SHAPE, Y_POLE_SHAPE, Y_NORTH_SHAPE, Y_EAST_SHAPE, Y_SOUTH_SHAPE, Y_WEST_SHAPE, Z_POLE_SHAPE, Z_UP_SHAPE, Z_EAST_SHAPE, Z_DOWN_SHAPE, Z_WEST_SHAPE;
    protected static final BooleanProperty NORTH_MODELSHAPEDIRECTION, EAST_MODELSHAPEDIRECTION, SOUTH_MODELSHAPEDIRECTION, WEST_MODELSHAPEDIRECTION, UP_MODELSHAPEDIRECTION, DOWN_MODELSHAPEDIRECTION;
    public static final EnumProperty<Direction.Axis> AXIS;
    protected static final BooleanProperty WATERLOGGED;

    
    protected HorizontalPaneBlock (AbstractBlock.Settings settings) {
        super(settings.nonOpaque());
        this.setDefaultState(this.stateManager.getDefaultState().with(AXIS, Direction.Axis.X).with(NORTH_MODELSHAPEDIRECTION, false).with(EAST_MODELSHAPEDIRECTION, false).with(SOUTH_MODELSHAPEDIRECTION, false).with(WEST_MODELSHAPEDIRECTION, false).with(Properties.WATERLOGGED, false));
    }

    @Override
    public VoxelShape getCameraCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.empty();
    }

    @Override
    public boolean isTransparent(BlockState state, BlockView world, BlockPos pos) {
        return !state.<Boolean>get(HorizontalPaneBlock.WATERLOGGED);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        VoxelShape SHAPE = VoxelShapes.fullCube();
        boolean NORTH = state.get(NORTH_MODELSHAPEDIRECTION);
        boolean EAST = state.get(EAST_MODELSHAPEDIRECTION);
        boolean SOUTH = state.get(SOUTH_MODELSHAPEDIRECTION);
        boolean WEST = state.get(WEST_MODELSHAPEDIRECTION);
        boolean UP = state.get(UP_MODELSHAPEDIRECTION);
        boolean DOWN = state.get(DOWN_MODELSHAPEDIRECTION);

        switch (state.get(AXIS)) {
            case X:
                SHAPE = X_POLE_SHAPE;
                if(NORTH) {
                    SHAPE = VoxelShapes.union(SHAPE, X_NORTH_SHAPE);
                }
                if(UP) {
                    SHAPE = VoxelShapes.union(SHAPE, X_UP_SHAPE);
                }
                if(SOUTH) {
                    SHAPE = VoxelShapes.union(SHAPE, X_SOUTH_SHAPE);
                }
                if(DOWN) {
                    SHAPE = VoxelShapes.union(SHAPE, X_DOWN_SHAPE);
                }
                break;
            case Y:
                SHAPE = Y_POLE_SHAPE;
                if(NORTH) {
                    SHAPE = VoxelShapes.union(SHAPE, Y_NORTH_SHAPE);
                }
                if(EAST) {
                    SHAPE = VoxelShapes.union(SHAPE, Y_EAST_SHAPE);
                }
                if(SOUTH) {
                    SHAPE = VoxelShapes.union(SHAPE, Y_SOUTH_SHAPE);
                }
                if(WEST) {
                    SHAPE = VoxelShapes.union(SHAPE, Y_WEST_SHAPE);
                }
                break;
            case Z:
                SHAPE = Z_POLE_SHAPE;
                if(UP) {
                    SHAPE = VoxelShapes.union(SHAPE, Z_UP_SHAPE);
                }
                if(EAST) {
                    SHAPE = VoxelShapes.union(SHAPE, Z_EAST_SHAPE);
                }
                if(DOWN) {
                    SHAPE = VoxelShapes.union(SHAPE, Z_DOWN_SHAPE);
                }
                if(WEST) {
                    SHAPE = VoxelShapes.union(SHAPE, Z_WEST_SHAPE);
                }
                break;
        }

        return SHAPE;
    }

    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }

    private boolean connect(BlockState state){
        return canConnect(state) || cantConnect(state);
    }

    private boolean canConnect(BlockState state){
        Block block = state.getBlock();
        return block instanceof HorizontalPaneBlock;
    }

    private boolean cantConnect(BlockState state){
        Block block = state.getBlock();
        return !(state.isAir() || state.isOf(Blocks.WATER) || state.isOf(Blocks.LAVA) || block instanceof FenceBlock);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        World world = ctx.getWorld();
        BlockPos pos = ctx.getBlockPos();
        Axis axis = ctx.getSide().getAxis();
        FluidState fluidState = ctx.getWorld().getFluidState(pos);

        Boolean connectNorth = connect(world.getBlockState(pos.north()));
        Boolean connectEast = connect(world.getBlockState(pos.east()));
        Boolean connectSouth = connect(world.getBlockState(pos.south()));
        Boolean connectWest = connect(world.getBlockState(pos.west()));
        Boolean connectUp = connect(world.getBlockState(pos.up()));
        Boolean connectDown = connect(world.getBlockState(pos.down()));

        if(ctx.getPlayer().isSneaking()){
            axis = Axis.Y;
        }

        switch (axis) {
            case X:
                connectEast = false;
                connectWest = false;
                break;
            case Y:
                connectUp = false;
                connectDown = false;
                break;
            case Z:
                connectNorth = false;
                connectSouth = false;
                break;
        }

        return (BlockState)this.getDefaultState().with(AXIS, axis).with(NORTH_MODELSHAPEDIRECTION, connectNorth).with(EAST_MODELSHAPEDIRECTION, connectEast).with(SOUTH_MODELSHAPEDIRECTION, connectSouth).with(WEST_MODELSHAPEDIRECTION, connectWest).with(UP_MODELSHAPEDIRECTION, connectUp).with(DOWN_MODELSHAPEDIRECTION, connectDown).with(Properties.WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
        Axis axis = state.get(AXIS);

        Boolean connectNorth = connect(world.getBlockState(pos.north()));
        Boolean connectEast = connect(world.getBlockState(pos.east()));
        Boolean connectSouth = connect(world.getBlockState(pos.south()));
        Boolean connectWest = connect(world.getBlockState(pos.west()));
        Boolean connectUp = connect(world.getBlockState(pos.up()));
        Boolean connectDown = connect(world.getBlockState(pos.down()));

        switch (axis) {
            case X:
                connectEast = false;
                connectWest = false;
                break;
            case Y:
                connectUp = false;
                connectDown = false;
                break;
            case Z:
                connectNorth = false;
                connectSouth = false;
                break;
        }
        

        world.setBlockState(pos, (BlockState)this.getDefaultState().with(AXIS, axis).with(NORTH_MODELSHAPEDIRECTION, connectNorth).with(EAST_MODELSHAPEDIRECTION, connectEast).with(SOUTH_MODELSHAPEDIRECTION, connectSouth).with(WEST_MODELSHAPEDIRECTION, connectWest).with(UP_MODELSHAPEDIRECTION, connectUp).with(DOWN_MODELSHAPEDIRECTION, connectDown).with(HorizontalPaneBlock.WATERLOGGED, state.get(HorizontalPaneBlock.WATERLOGGED)));
        super.neighborUpdate(state, world, pos, block, fromPos, notify);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState newState, WorldAccess world, BlockPos pos, BlockPos posFrom) {
        if (state.<Boolean>get((Property<Boolean>)HorizontalPaneBlock.WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        
        return super.getStateForNeighborUpdate(state, direction, newState, world, pos, posFrom);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        if (state.<Boolean>get((Property<Boolean>)HorizontalPaneBlock.WATERLOGGED)) {
            return Fluids.WATER.getStill(false);
        }
        return super.getFluidState(state);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(
            HorizontalPaneBlock.NORTH_MODELSHAPEDIRECTION,
            HorizontalPaneBlock.EAST_MODELSHAPEDIRECTION,
            HorizontalPaneBlock.SOUTH_MODELSHAPEDIRECTION,
            HorizontalPaneBlock.WEST_MODELSHAPEDIRECTION,
            HorizontalPaneBlock.UP_MODELSHAPEDIRECTION,
            HorizontalPaneBlock.DOWN_MODELSHAPEDIRECTION,
            HorizontalPaneBlock.AXIS,
            HorizontalPaneBlock.WATERLOGGED
          );
    }

    static {
        AXIS = Properties.AXIS;

        //GOOD
        X_POLE_SHAPE = createCuboidShape(0.0f, 7.0f, 7.0f, 16f, 9f, 9f);
        X_NORTH_SHAPE = createCuboidShape(0.0f, 7.0f, 0.0f, 16f, 9f, 7f);
        X_UP_SHAPE = createCuboidShape(0.0f, 9.0f, 7.0f, 16f, 16f, 9f);
        X_SOUTH_SHAPE = createCuboidShape(0.0f, 7.0f, 9.0f, 16f, 9f, 16f);
        X_DOWN_SHAPE = createCuboidShape(0.0f, 0.0f, 7.0f, 16f, 7f, 9f);

        //GOOD
        Y_POLE_SHAPE = createCuboidShape(7.0f, 0.0f, 7.0f, 9f, 16f, 9f);
        Y_NORTH_SHAPE = createCuboidShape(7.0f, 0.0f, 0.0f, 9f, 16f, 7f);
        Y_EAST_SHAPE = createCuboidShape(7.0f, 0.0f, 7.0f, 16f, 16f, 9f);
        Y_SOUTH_SHAPE = createCuboidShape(7.0f, 0.0f, 7.0f, 9f, 16f, 16f);
        Y_WEST_SHAPE = createCuboidShape(0.0f, 0.0f, 7.0f, 7f, 16f, 9f);
        
        //GOOD
        Z_POLE_SHAPE = createCuboidShape(7.0f, 7.0f, 0.0f, 9f, 9f, 16f);
        Z_UP_SHAPE = createCuboidShape(7.0f, 9.0f, 0.0f, 9f, 16f, 16f);
        Z_EAST_SHAPE = createCuboidShape(9.0f, 7.0f, 0.0f, 16f, 9f, 16f);
        Z_DOWN_SHAPE = createCuboidShape(7.0f, 0.0f, 0.0f, 9f, 7f, 16f);
        Z_WEST_SHAPE = createCuboidShape(0.0f, 7.0f, 0.0f, 7f, 9f, 16f);

        NORTH_MODELSHAPEDIRECTION = BooleanProperty.of("north");
        EAST_MODELSHAPEDIRECTION = BooleanProperty.of("east");
        SOUTH_MODELSHAPEDIRECTION = BooleanProperty.of("south");
        WEST_MODELSHAPEDIRECTION = BooleanProperty.of("west");
        UP_MODELSHAPEDIRECTION = BooleanProperty.of("up");
        DOWN_MODELSHAPEDIRECTION = BooleanProperty.of("down");

        WATERLOGGED = Properties.WATERLOGGED;
    }
}