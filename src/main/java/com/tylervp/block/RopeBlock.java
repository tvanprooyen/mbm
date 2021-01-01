package com.tylervp.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.property.Properties;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

public class RopeBlock extends Block implements Waterloggable {
    protected static final VoxelShape SHAPE;
    public static final BooleanProperty WATERLOGGED;

    protected RopeBlock (AbstractBlock.Settings settings) {
        super(settings.nonOpaque());
        this.setDefaultState(this.stateManager.getDefaultState().with(Properties.WATERLOGGED, false));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return RopeBlock.SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.empty();
    }
    
    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if(entity instanceof ArrowEntity){

            if(state.<Boolean>get((Property<Boolean>)LayerBlock.WATERLOGGED)) {
                world.setBlockState(pos, Blocks.AIR.getDefaultState());
                world.setBlockState(pos, Blocks.WATER.getDefaultState());
            } else {
                world.setBlockState(pos, Blocks.AIR.getDefaultState());
            }
            entity.dropItem(this);
        }
        super.onEntityCollision(state, world, pos, entity);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos currentBlockPos = ctx.getBlockPos();
        BlockState blockStateCurrent = ctx.getWorld().getBlockState(ctx.getBlockPos());
        BlockState blockStateUp = ctx.getWorld().getBlockState(ctx.getBlockPos().up());
        BlockState blockStateDown = ctx.getWorld().getBlockState(ctx.getBlockPos().down());
        BlockState blockStateROPEMID = MBMBlocks.ROPEMID.getDefaultState();
        FluidState fluidState5 = ctx.getWorld().getFluidState(currentBlockPos);
        FluidState fluidState6 = ctx.getWorld().getFluidState(currentBlockPos.up());
        FluidState fluidState8 = ctx.getWorld().getFluidState(currentBlockPos.down());
        World world4 = ctx.getWorld();
        
        if(canReplace(blockStateCurrent, ctx)){
             if((blockStateDown.isOf(Blocks.AIR)  || blockStateDown.isOf(Blocks.WATER)) && blockStateCurrent.isOf(this)){
                
                BlockState blockStatePending = this.getDefaultState().with(Properties.WATERLOGGED, (fluidState8.getFluid() == Fluids.WATER || fluidState6.getFluid() == Fluids.WATER));
                world4.setBlockState(currentBlockPos.down(), blockStatePending);
                return (BlockState)blockStateROPEMID.with(Properties.WATERLOGGED, fluidState5.getFluid() == Fluids.WATER);
            }
        }

        if(blockStateUp.isOf(this)){
            world4.setBlockState(currentBlockPos.up(), MBMBlocks.ROPEMID.getDefaultState().with(Properties.WATERLOGGED, fluidState6.getFluid() == Fluids.WATER));
        }
        return (BlockState)this.getDefaultState().with(Properties.WATERLOGGED, fluidState5.getFluid() == Fluids.WATER);
    }

    @Override
    public boolean canReplace(BlockState state, ItemPlacementContext ctx) {
        if (ctx.getStack().getItem() == this.asItem() && !(ctx.getSide() == Direction.DOWN)){
            BlockPos currentBlockPos = ctx.getBlockPos();
            BlockState blockStateCurrent = ctx.getWorld().getBlockState(ctx.getBlockPos());
            //BlockState blockStateUp = ctx.getWorld().getBlockState(ctx.getBlockPos().up());
            BlockState blockStateDown = ctx.getWorld().getBlockState(ctx.getBlockPos().down());

            if(!(blockStateDown.isOf(Blocks.AIR) || blockStateDown.isOf(Blocks.WATER)) && blockStateCurrent.isOf(this) && !blockStateDown.isOf(MBMBlocks.ROPEMID) && !blockStateDown.isOf(this)){
                BlockState blockStateROPEMID = MBMBlocks.ROPEMID.getDefaultState();
                World world4 = ctx.getWorld();
                BlockState blockStateNorth = ctx.getWorld().getBlockState(ctx.getBlockPos().north());
                BlockState blockStateSouth = ctx.getWorld().getBlockState(ctx.getBlockPos().south());
                BlockState blockStateEast = ctx.getWorld().getBlockState(ctx.getBlockPos().east());
                BlockState blockStateWest = ctx.getWorld().getBlockState(ctx.getBlockPos().west());
                FluidState fluidState5 = ctx.getWorld().getFluidState(currentBlockPos);
                if((!blockStateNorth.isOf(this) && !blockStateNorth.isOf(MBMBlocks.ROPEMID) && ctx.getSide() == Direction.NORTH) || (!blockStateSouth.isOf(this) && !blockStateSouth.isOf(MBMBlocks.ROPEMID) && ctx.getSide() == Direction.SOUTH) || (!blockStateEast.isOf(this) && !blockStateEast.isOf(MBMBlocks.ROPEMID) && ctx.getSide() == Direction.EAST) || (!blockStateWest.isOf(this) && !blockStateWest.isOf(MBMBlocks.ROPEMID) && ctx.getSide() == Direction.WEST)) {
                    world4.setBlockState(currentBlockPos, blockStateROPEMID.with(Properties.WATERLOGGED, fluidState5.getFluid() == Fluids.WATER));
                }
               
                return false;
            }
            return ctx.canReplaceExisting();
        }
        return false;
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockState blockState5 = world.getBlockState(pos.up());
        return (!(blockState5.isOf(Blocks.AIR)) && !(blockState5.isOf(Blocks.WATER)));
    }

    

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState newState, WorldAccess world, BlockPos pos, BlockPos posFrom) {    
        
        if (!state.canPlaceAt(world, pos)) {
            
            /*if(state.<Boolean>get((Property<Boolean>)LayerBlock.WATERLOGGED)) {
                
                return Blocks.AIR.getDefaultState();
            } else {
                return Blocks.AIR.getDefaultState();
            }*/
            
            return Blocks.AIR.getDefaultState();
        }

        if (state.<Boolean>get((Property<Boolean>)LayerBlock.WATERLOGGED)) {
            world.getFluidTickScheduler().schedule(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        return super.getStateForNeighborUpdate(state, direction, newState, world, pos, posFrom);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(RopeBlock.WATERLOGGED);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        if (state.<Boolean>get((Property<Boolean>)RopeBlock.WATERLOGGED)) {
            return Fluids.WATER.getStill(false);
        }
        return super.getFluidState(state);
    }

    static {
        SHAPE = Block.createCuboidShape(3.0, 6.0, 3.0, 13.0, 16.0, 13.0);
        WATERLOGGED = Properties.WATERLOGGED;
    }
}