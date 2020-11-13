package com.tylervp;

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
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;

public class SideSlab extends HorizontalFacingBlock implements Waterloggable{

	public static final BooleanProperty HALF;
	public static final BooleanProperty WATERLOGGED;
 
	public SideSlab(Settings settings) {
		super(settings);
		setDefaultState(this.stateManager.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH).with(HALF, true).with(Properties.WATERLOGGED, false));
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
	public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
		Direction dir = state.get(FACING);
		
		if(!isHalf(state)){
			return VoxelShapes.fullCube();
		}

		switch(dir) {
			case NORTH:
				return VoxelShapes.cuboid(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.5f);
			case SOUTH:
				return VoxelShapes.cuboid(0.0f, 0.0f, 0.5f, 1.0f, 1.0f, 1.0f);
			case EAST:
				return VoxelShapes.cuboid(0.5f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
			case WEST:
				return VoxelShapes.cuboid(0.0f, 0.0f, 0.0f, 0.5f, 1.0f, 1.0f);
			default:
				return VoxelShapes.fullCube();
		}
	}

	@Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState newState, WorldAccess world, BlockPos pos, BlockPos posFrom) {
        if (state.<Boolean>get((Property<Boolean>)SideSlab.WATERLOGGED)) {
            world.getFluidTickScheduler().schedule(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        
        return super.getStateForNeighborUpdate(state, direction, newState, world, pos, posFrom);
    }
	
	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		BlockPos pos = ctx.getBlockPos();
		BlockState state = ctx.getWorld().getBlockState(pos);
		FluidState fluidState5 = ctx.getWorld().getFluidState(pos);
		//PlayerEntity player = ctx.getPlayer();
		
		if(state.isOf(this)){
			return (BlockState)this.getDefaultState().with(FACING, state.get(FACING)).with(HALF, false).with(Properties.WATERLOGGED, false);
		}

		//player.sendMessage(new LiteralText("X Top Hit:" + Boolean.toString((ctx.getHitPos().x - pos.getX() > 0.5))), false);
		//player.sendMessage(new LiteralText("Z Top Hit:" + Boolean.toString((ctx.getHitPos().z - pos.getZ() > 0.5))), false);
		//player.sendMessage(new LiteralText("Player Facing:" + ctx.getPlayerFacing()), false);

		if(ctx.getPlayerFacing() == Direction.EAST && !(ctx.getHitPos().x - pos.getX() > 0.5)){
			return (BlockState)this.getDefaultState().with(FACING, Direction.WEST).with(HALF, true).with(Properties.WATERLOGGED, fluidState5.getFluid() == Fluids.WATER);
		}
		if(ctx.getPlayerFacing() == Direction.WEST && (ctx.getHitPos().x - pos.getX() > 0.5)){
			return (BlockState)this.getDefaultState().with(FACING, Direction.EAST).with(HALF, true).with(Properties.WATERLOGGED, fluidState5.getFluid() == Fluids.WATER);
		}

		if(ctx.getPlayerFacing() == Direction.SOUTH && !(ctx.getHitPos().z - pos.getZ() > 0.5)){
			return (BlockState)this.getDefaultState().with(FACING, Direction.NORTH).with(HALF, true).with(Properties.WATERLOGGED, fluidState5.getFluid() == Fluids.WATER);
		}
		if(ctx.getPlayerFacing() == Direction.NORTH && (ctx.getHitPos().z - pos.getZ() > 0.5)){
			return (BlockState)this.getDefaultState().with(FACING, Direction.SOUTH).with(HALF, true).with(Properties.WATERLOGGED, fluidState5.getFluid() == Fluids.WATER);
		}

		return (BlockState)this.getDefaultState().with(FACING, ctx.getPlayerFacing()).with(HALF, true).with(Properties.WATERLOGGED, fluidState5.getFluid() == Fluids.WATER);
	}

	private boolean isHalf(BlockState state){
		return state.get(HALF);
	}
	
	@Override
	public boolean canReplace(BlockState state, ItemPlacementContext context) {
		if(isHalf(state) && context.getStack().getItem() == this.asItem()) {
			if(state.get(FACING) == context.getSide().getOpposite() && (context.getPlayerFacing() != Direction.DOWN || context.getPlayerFacing() != Direction.UP)){
				return context.canReplaceExisting();
			}
		}
		return false;

		//context.getPlayerFacing()
	}
	
	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
		stateManager.add(Properties.HORIZONTAL_FACING, SideSlab.WATERLOGGED, SideSlab.HALF);
	}

	@Override
    public FluidState getFluidState(BlockState state) {
        if (state.<Boolean>get((Property<Boolean>)SideSlab.WATERLOGGED)) {
            return Fluids.WATER.getStill(false);
        }
        return super.getFluidState(state);
    }

	static{
		HALF = BooleanProperty.of("half");
		WATERLOGGED = Properties.WATERLOGGED;
	}
}