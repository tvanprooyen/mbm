package com.tylervp.block;

import com.tylervp.block.enums.WireSides;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Direction.Axis;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;

public class Wire extends Block implements Waterloggable {
    public static final EnumProperty<Direction.Axis> AXIS;
    public static final EnumProperty<WireSides> SIDE;
    public static final BooleanProperty WATERLOGGED;

    public Wire(Settings settings) {
        super(settings.nonOpaque());
        this.setDefaultState((BlockState)this.getDefaultState().with(AXIS, Direction.Axis.Y).with(SIDE, WireSides.MIDDLE).with(WATERLOGGED, false));
	}

	public BlockState rotate(BlockState state, BlockRotation rotation) {
		return changeRotation(state, rotation);
	}

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Axis axis = state.get(AXIS);
        WireSides side = state.get(SIDE);

        switch (side) {
            case TOP:
                switch (axis) {
                    case X:
                        return Block.createCuboidShape(0.0D, 12.0D, 6D, 16.0D, 16.0D, 10D);
                    case Y:
                        return Block.createCuboidShape(6D, 0.0D, 6D, 10D, 16D, 10D);
                    case Z:
                        return Block.createCuboidShape(6D, 12.0D, 0.0D, 10D, 16.0D, 16.0D);
                }
            case MIDDLE:
                switch (axis) {
                    case X:
                        return Block.createCuboidShape(0.0D, 6D, 6D, 16.0D, 10D, 10D);
                    case Y:
                        return Block.createCuboidShape(6D, 0.0D, 6D, 10D, 16D, 10D);
                    case Z:
                        return Block.createCuboidShape(6D, 6D, 0.0D, 10D, 10D, 16.0D);
                }
            case BOTTOM:
                switch (axis) {
                    case X:
                        return Block.createCuboidShape(0.0D, 0.0D, 6D, 16.0D, 4.0D, 10D);
                    case Y:
                        return Block.createCuboidShape(6D, 0.0D, 6D, 10D, 16D, 10D);
                    case Z:
                        return Block.createCuboidShape(6D, 0.0D, 0.0D, 10D, 4.0D, 16.0D);
                }
        }

        return VoxelShapes.fullCube();
    }

    public static BlockState changeRotation(BlockState state, BlockRotation rotation) {
		switch(rotation) {
		case COUNTERCLOCKWISE_90:
		case CLOCKWISE_90:
			switch((Direction.Axis)state.get(AXIS)) {
			case X:
				return (BlockState)state.with(AXIS, Direction.Axis.Z);
			case Z:
				return (BlockState)state.with(AXIS, Direction.Axis.X);
			default:
				return state;
			}
		default:
			return state;
		}
	}

    @Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
        WireSides side = WireSides.MIDDLE;
        Direction.Axis axis = ctx.getSide().getAxis();
        BlockPos pos = ctx.getBlockPos();
        FluidState fluidState = ctx.getWorld().getFluidState(pos);

        if(!ctx.getPlayer().isSneaking()) {
            if((ctx.getHitPos().y - ctx.getBlockPos().getY()) * 10 < 3){
                side = WireSides.BOTTOM;
            } else if((ctx.getHitPos().y - ctx.getBlockPos().getY()) * 10 > 7) {
                side = WireSides.TOP;
            }
        } else {
            if(ctx.getSide() == Direction.UP) {
                side = WireSides.BOTTOM;
            } else if(ctx.getSide() == Direction.DOWN) {
                side = WireSides.TOP;
            }
            axis = ctx.getPlayerLookDirection().getAxis();

            if(axis == Axis.Y) {
                axis = ctx.getPlayerFacing().getAxis();
            }
        }

		return (BlockState)this.getDefaultState().with(AXIS, axis).with(SIDE, side).with(Properties.WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
	}


    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(AXIS, SIDE, WATERLOGGED);
	}

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState newState, WorldAccess world, BlockPos pos, BlockPos posFrom) {
        if (state.<Boolean>get((Property<Boolean>)WATERLOGGED)) {
            world.getFluidTickScheduler().schedule(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        
        return super.getStateForNeighborUpdate(state, direction, newState, world, pos, posFrom);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        if (state.<Boolean>get((Property<Boolean>)WATERLOGGED)) {
            return Fluids.WATER.getStill(false);
        }
        return super.getFluidState(state);
    }

	static {
		AXIS = Properties.AXIS;
        SIDE = EnumProperty.of("side", WireSides.class);
        WATERLOGGED = Properties.WATERLOGGED;
	}
}
