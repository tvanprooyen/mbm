package com.tylervp.block;

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
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Direction.Axis;

public class ThinPillarBlock extends PillarBlock implements Waterloggable {
    protected static final VoxelShape SHAPEX,SHAPEY,SHAPEZ;
    public static final EnumProperty<Direction.Axis> AXIS;
    public static final BooleanProperty WATERLOGGED;

    protected ThinPillarBlock (AbstractBlock.Settings settings) {
        super(settings.nonOpaque());
        this.setDefaultState(this.stateManager.getDefaultState().with(Properties.WATERLOGGED, false));
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

        if(axis == Axis.X){
            return ThinPillarBlock.SHAPEX;

        } else if(axis == Axis.Y){
            return ThinPillarBlock.SHAPEY;

        } else if(axis == Axis.Z){
            return ThinPillarBlock.SHAPEZ;

        } else {
            return ThinPillarBlock.SHAPEZ;
        }
    }


    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos pos = ctx.getBlockPos();
        FluidState fuildstate = ctx.getWorld().getFluidState(pos);

        return (BlockState)this.getDefaultState().with(AXIS, ctx.getSide().getAxis()).with(Properties.WATERLOGGED, fuildstate.getFluid() == Fluids.WATER);
    }


    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState newState, WorldAccess world, BlockPos pos, BlockPos posFrom) {
        if (state.<Boolean>get((Property<Boolean>)ThinPillarBlock.WATERLOGGED)) {
            world.getFluidTickScheduler().schedule(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        return super.getStateForNeighborUpdate(state, direction, newState, world, pos, posFrom);
    }
    
    /* @Override
    public int getOpacity(BlockState state, BlockView world, BlockPos pos) {
        return 1;
    } */

    @Override
    public FluidState getFluidState(BlockState state) {
        if (state.<Boolean>get((Property<Boolean>)WATERLOGGED)) {
            return Fluids.WATER.getStill(false);
        }
        return super.getFluidState(state);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(ThinLogBlock.AXIS,WATERLOGGED);
    }

    static {
        SHAPEZ = Block.createCuboidShape(4.0, 4.0, 0.0, 12.0, 12.0, 16.0);
        SHAPEX = Block.createCuboidShape(0.0, 4.0, 4.0, 16.0, 12.0, 12.0);
        SHAPEY = Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 16.0, 12.0);
        AXIS = Properties.AXIS;
        WATERLOGGED = Properties.WATERLOGGED;
    }
}