package com.tylervp.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.ai.pathing.NavigationType;
//import net.minecraft.fluid.FluidState;
//import net.minecraft.item.ItemPlacementContext;
//import net.minecraft.state.property.Property;
//import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
//import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
//import net.minecraft.world.WorldAccess;
//import net.minecraft.fluid.Fluids;
//import net.minecraft.state.StateManager;
//import net.minecraft.util.shape.VoxelShapes;



/* public class VerticalPaneBlock extends VerticalConnectingBlock {
    
    protected VerticalPaneBlock(AbstractBlock.Settings settings) {
        super(1.0f, 1.0f, 16.0f, 16.0f, 16.0f, settings);
        this.setDefaultState(((((((BlockState)this.stateManager.getDefaultState()).with(VerticalPaneBlock.UP, false)).with(VerticalPaneBlock.DOWN, false)).with(VerticalPaneBlock.WATERLOGGED, false))));
    }
    
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockView blockView3 = ctx.getWorld();
        BlockPos blockPos4 = ctx.getBlockPos();
        FluidState fluidState5 = ctx.getWorld().getFluidState(ctx.getBlockPos());
        BlockPos blockPos6 = blockPos4.north();
        BlockPos blockPos7 = blockPos4.south();
        BlockPos blockPos8 = blockPos4.west();
        BlockPos blockPos9 = blockPos4.east();
        BlockState blockState10 = blockView3.getBlockState(blockPos6);
        BlockState blockState11 = blockView3.getBlockState(blockPos7);
        BlockState blockState12 = blockView3.getBlockState(blockPos8);
        BlockState blockState13 = blockView3.getBlockState(blockPos9);
        return ((((((BlockState)this.getDefaultState()).with(VerticalPaneBlock.UP, this.connectsTo(blockState10, blockState10.isSideSolidFullSquare(blockView3, blockPos6, Direction.DOWN)))).with(VerticalPaneBlock.DOWN, this.connectsTo(blockState11, blockState11.isSideSolidFullSquare(blockView3, blockPos7, Direction.UP)))).with(VerticalPaneBlock.WATERLOGGED, fluidState5.getFluid() == Fluids.WATER)));
    }
    
    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState newState, WorldAccess world, BlockPos pos, BlockPos posFrom) {
        if (state.<Boolean>get((Property<Boolean>)VerticalPaneBlock.WATERLOGGED)) {
            world.getFluidTickScheduler().schedule(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        if (direction.getAxis().isHorizontal()) {
            return this.getDefaultState().with(VerticalPaneBlock.FACING_PROPERTIES.get(direction), this.connectsTo(newState, newState.isSideSolidFullSquare(world, posFrom, direction.getOpposite())));
        }
        return super.getStateForNeighborUpdate(state, direction, newState, world, pos, posFrom);
    }
    
    @Override
    public VoxelShape getVisualShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.empty();
    }
    
    //@Environment(EnvType.CLIENT)
    @Override
    public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
        if (stateFrom.isOf(this)) {
            if (!direction.getAxis().isHorizontal()) {
                return true;
            }
            if (state.<Boolean>get((Property<Boolean>)VerticalPaneBlock.FACING_PROPERTIES.get(direction)) && stateFrom.<Boolean>get((Property<Boolean>)VerticalPaneBlock.FACING_PROPERTIES.get(direction.getOpposite()))) {
                return true;
            }
        }
        return super.isSideInvisible(state, stateFrom, direction);
    }
    
    public final boolean connectsTo(BlockState state, boolean boolean3) {
        Block block4 = state.getBlock();
        return (!Block.cannotConnect(block4) && boolean3) || block4 instanceof VerticalPaneBlock || block4.isIn(BlockTags.WALLS);
    }
    
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(VerticalPaneBlock.UP, VerticalPaneBlock.DOWN, VerticalPaneBlock.WATERLOGGED);
    }
} */



public class VerticalPaneBlock extends Block {
    protected static final VoxelShape SHAPE;
    
    protected VerticalPaneBlock (AbstractBlock.Settings settings) {
        super(settings.nonOpaque());
        this.setDefaultState(this.stateManager.getDefaultState());
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }

    static {
        SHAPE = createCuboidShape(0.0f, 7.0f, 0.0f, 16f, 9f, 16f);
    }
}
