package com.tylervp.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class SideStairs extends HorizontalFacingBlock implements Waterloggable {
    protected static final VoxelShape DEGSHAPE0;
    protected static final VoxelShape DEGSHAPE90;
    protected static final VoxelShape DEGSHAPE180;
    protected static final VoxelShape DEGSHAPE270;

    protected SideStairs(AbstractBlock.Settings settings) {
        super(settings);
        setDefaultState(this.stateManager.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH).with(Properties.WATERLOGGED, false));
    }

    @Override
	public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
		Direction dir = state.get(FACING);

		switch(dir) {
			case NORTH:
				return DEGSHAPE0;
			case SOUTH:
                return DEGSHAPE90;
			case EAST:
                return DEGSHAPE180;
			case WEST:
                return DEGSHAPE270;
			default:
				return VoxelShapes.fullCube();
		}
	}

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return getOutlineShape(state, world, pos, context);
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

    private Direction placementDirection(ItemPlacementContext ctx) {
        BlockPos pos = ctx.getBlockPos();
        Double HitX = ctx.getHitPos().x - pos.getX();
        Double HitZ = ctx.getHitPos().z - pos.getZ();

        

        if(ctx.getSide() == Direction.UP || ctx.getSide() == Direction.DOWN){
            if(HitX <= 0.5 && HitZ <= 0.5) {
                return Direction.WEST;
            } else if(HitX >= 0.5 && HitZ <= 0.5) {
                return Direction.NORTH;
            } else if(HitX >= 0.5 && HitZ >= 0.5) {
                return Direction.EAST;
            } else if(HitX <= 0.5 && HitZ >= 0.5) {
                return Direction.SOUTH;
            }
        } else if(ctx.getSide() == Direction.NORTH){
            if(HitX <= 0.5){
                return Direction.SOUTH;
            } else {
                return Direction.EAST;
            }
        } else if(ctx.getSide() == Direction.EAST){
            if(HitZ <= 0.5){
                return Direction.WEST;
            } else {
                return Direction.SOUTH;
            }
        } else if(ctx.getSide() == Direction.SOUTH){
            if(HitX <= 0.5){
                return Direction.WEST;
            } else {
                return Direction.NORTH;
            }
        } else if(ctx.getSide() == Direction.WEST){
            if(HitZ <= 0.5){
                return Direction.NORTH;
            } else {
                return Direction.EAST;
            }
        }
        return Direction.NORTH;
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos pos = ctx.getBlockPos();
        World world = ctx.getWorld();
        FluidState fluidState = world.getFluidState(pos);
        
        return (BlockState)this.getDefaultState().with(Properties.HORIZONTAL_FACING, placementDirection(ctx)).with(Properties.WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
    }

    @Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
		stateManager.add(Properties.HORIZONTAL_FACING, Properties.WATERLOGGED);
	}

    @Override
    public FluidState getFluidState(BlockState state) {
        if (state.<Boolean>get((Property<Boolean>)SideSlab.WATERLOGGED)) {
            return Fluids.WATER.getStill(false);
        }
        return super.getFluidState(state);
    }

    static{
        DEGSHAPE180 = VoxelShapes.combineAndSimplify(VoxelShapes.fullCube(), VoxelShapes.cuboid(0, 0, 0, 0.5, 1, 0.5), BooleanBiFunction.ONLY_FIRST);
        DEGSHAPE0 = VoxelShapes.combineAndSimplify(VoxelShapes.fullCube(), VoxelShapes.cuboid(0, 0, 0.5, 0.5, 1, 1), BooleanBiFunction.ONLY_FIRST);
        DEGSHAPE90 = VoxelShapes.combineAndSimplify(VoxelShapes.fullCube(), VoxelShapes.cuboid(0.5, 0, 0, 1, 1, 0.5), BooleanBiFunction.ONLY_FIRST);
        DEGSHAPE270 = VoxelShapes.combineAndSimplify(VoxelShapes.fullCube(), VoxelShapes.cuboid(0.5, 0, 0.5, 1, 1, 1), BooleanBiFunction.ONLY_FIRST);
    }
}
