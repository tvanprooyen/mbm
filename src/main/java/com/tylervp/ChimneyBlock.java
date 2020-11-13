package com.tylervp;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Direction.Axis;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.tag.FluidTags;

public class ChimneyBlock extends PillarBlock implements Waterloggable {
    protected static final VoxelShape OUTLINE_SHAPE_X, RAY_TRACE_SHAPE_X, OUTLINE_SHAPE_Y, RAY_TRACE_SHAPE_Y, OUTLINE_SHAPE_Z, RAY_TRACE_SHAPE_Z;
    public static final EnumProperty<Direction.Axis> AXIS;
    public static final BooleanProperty WATERLOGGED;
    
    protected ChimneyBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(Properties.WATERLOGGED, false));
    }

    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        switch (type) {
            case LAND: {
                return true;
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
        Axis axis = state.get(AXIS);
        
        
        if(axis == Axis.X){
            return ChimneyBlock.OUTLINE_SHAPE_X;

        } else if(axis == Axis.Y){
            return ChimneyBlock.OUTLINE_SHAPE_Y;

        } else if(axis == Axis.Z){
            return ChimneyBlock.OUTLINE_SHAPE_Z;

        } else {
            return ChimneyBlock.OUTLINE_SHAPE_Y;
        }
    }
    
    @Override
    public VoxelShape getRaycastShape(BlockState state, BlockView world, BlockPos pos) {
        Axis axis = state.get(AXIS);
        
        
        if(axis == Axis.X){
            return ChimneyBlock.RAY_TRACE_SHAPE_X;

        } else if(axis == Axis.Y){
            return ChimneyBlock.RAY_TRACE_SHAPE_Y;

        } else if(axis == Axis.Z){
            return ChimneyBlock.RAY_TRACE_SHAPE_Z;

        } else {
            return ChimneyBlock.RAY_TRACE_SHAPE_Y;
        }
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState newState, WorldAccess world, BlockPos pos, BlockPos posFrom) {
        if (state.<Boolean>get((Property<Boolean>)ChimneyBlock.WATERLOGGED)) {
            world.getFluidTickScheduler().schedule(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        
        return super.getStateForNeighborUpdate(state, direction, newState, world, pos, posFrom);
    }


    @Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		BlockPos pos = ctx.getBlockPos();
		FluidState fluidState5 = ctx.getWorld().getFluidState(pos);

		return (BlockState)this.getDefaultState().with(Properties.WATERLOGGED, fluidState5.getFluid() == Fluids.WATER).with(AXIS, ctx.getPlayerLookDirection().getAxis());
    }
    
    @Override
    public FluidState getFluidState(BlockState state) {
        if (state.<Boolean>get((Property<Boolean>)ChimneyBlock.WATERLOGGED)) {
            return Fluids.WATER.getStill(false);
        }
        return super.getFluidState(state);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(ChimneyBlock.AXIS, ChimneyBlock.WATERLOGGED);
    }

    static {
        RAY_TRACE_SHAPE_X = Block.createCuboidShape(0.0, 3.0, 3.0, 16.0, 13.0, 13.0);
        OUTLINE_SHAPE_X = VoxelShapes.combineAndSimplify(VoxelShapes.fullCube(), ChimneyBlock.RAY_TRACE_SHAPE_X, BooleanBiFunction.ONLY_FIRST);

        RAY_TRACE_SHAPE_Y = Block.createCuboidShape(3.0, 0.0, 3.0, 13.0, 16.0, 13.0);
        OUTLINE_SHAPE_Y = VoxelShapes.combineAndSimplify(VoxelShapes.fullCube(), ChimneyBlock.RAY_TRACE_SHAPE_Y, BooleanBiFunction.ONLY_FIRST);

        RAY_TRACE_SHAPE_Z = Block.createCuboidShape(3.0, 3.0, 0.0, 13.0, 13.0, 16.0);
        OUTLINE_SHAPE_Z = VoxelShapes.combineAndSimplify(VoxelShapes.fullCube(), ChimneyBlock.RAY_TRACE_SHAPE_Z, BooleanBiFunction.ONLY_FIRST);
       
        AXIS = Properties.AXIS;
        WATERLOGGED = Properties.WATERLOGGED;
    }
}
