package com.tylervp.block;

import java.util.Random;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FallingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.Entity.RemovalReason;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

public class LayerBlockFalling extends FallingBlock implements Waterloggable {
    public static final IntProperty LAYERS;
    protected static final VoxelShape[] LAYERS_TO_SHAPE;
    public static final BooleanProperty WATERLOGGED, DIRTY;
    
    
    protected LayerBlockFalling(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(Properties.LAYERS, 1).with(Properties.WATERLOGGED, false).with(LayerBlockFalling.DIRTY, false));
    }
    
    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        switch (type) {
            case LAND: {
                return state.<Integer>get((Property<Integer>)LayerBlockFalling.LAYERS) < 5;
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
        return LayerBlockFalling.LAYERS_TO_SHAPE[state.<Integer>get((Property<Integer>)LayerBlockFalling.LAYERS)];
    }
    
    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return LayerBlockFalling.LAYERS_TO_SHAPE[state.<Integer>get((Property<Integer>)LayerBlockFalling.LAYERS)];
    }
    
    @Override
    public VoxelShape getSidesShape(BlockState state, BlockView world, BlockPos pos) {
        return LayerBlockFalling.LAYERS_TO_SHAPE[state.<Integer>get((Property<Integer>)LayerBlockFalling.LAYERS)];
    }
    
    @Override
    public VoxelShape getCameraCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return LayerBlockFalling.LAYERS_TO_SHAPE[state.<Integer>get((Property<Integer>)LayerBlockFalling.LAYERS)];
    }
    
    @Override
    public boolean hasSidedTransparency(BlockState state) {
        return true;
    }
    
    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockState blockState5 = world.getBlockState(pos.down());
        return (blockState5.isOf(Blocks.HONEY_BLOCK) || blockState5.isOf(Blocks.SOUL_SAND) || Block.isFaceFullSquare(blockState5.getCollisionShape(world, pos.down()), Direction.UP) || (blockState5.getBlock() == this && blockState5.<Integer>get((Property<Integer>)LayerBlockFalling.LAYERS) == 8));
    }
    
    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState newState, WorldAccess world, BlockPos pos, BlockPos posFrom) {
        if (state.<Boolean>get((Property<Boolean>)LayerBlockFalling.WATERLOGGED)) {
            world.getFluidTickScheduler().schedule(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        
        return super.getStateForNeighborUpdate(state, direction, newState, world, pos, posFrom);
    }
    
    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        /*if (world.getLightLevel(LightType.BLOCK, pos) > 11) {
            Block.dropStacks(state, world, pos);
            world.removeBlock(pos, false);
        }*/
    }
    
    @Override
    public boolean canReplace(BlockState state, ItemPlacementContext context) {
        int integer4 = state.<Integer>get((Property<Integer>)LayerBlockFalling.LAYERS);
        if (context.getStack().getItem() == this.asItem() && integer4 < 8) {
            return !context.canReplaceExisting() || context.getSide() == Direction.UP;
        }
        return integer4 == 0;
    }
    
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos blockPos3 = ctx.getBlockPos();
        BlockState blockState3 = ctx.getWorld().getBlockState(ctx.getBlockPos());
        FluidState fluidState5 = ctx.getWorld().getFluidState(blockPos3);
        if (blockState3.isOf(this)) {
            int currentLayer = blockState3.<Integer>get((Property<Integer>)LayerBlockFalling.LAYERS);
            return blockState3.with(Properties.LAYERS, Math.min(8, currentLayer + 1)).with(Properties.WATERLOGGED, fluidState5.getFluid() == Fluids.WATER);
        }
        
        return (BlockState)this.getDefaultState().with(Properties.LAYERS, 1).with(Properties.WATERLOGGED, fluidState5.getFluid() == Fluids.WATER);
    }

    public boolean allowBlockCollisionAdd(BlockState state, BlockState fEntity){
        return (
                (fEntity.isOf(Blocks.SAND) && state.isOf(MBMBlocks.SAND_LAYER)) || 
                (fEntity.isOf(Blocks.GRAVEL) && state.isOf(MBMBlocks.GRAVEL_LAYER)) || 
                fEntity.isOf(this)
            );
    }
    
    public void onLayerAddedByCollision(BlockState state, World world, BlockPos pos, int layers, boolean dirty){

    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        super.onEntityCollision(state, world, pos, entity);
        if(entity instanceof FallingBlockEntity && !world.isClient) {
            FallingBlockEntity fBlockEntity = (FallingBlockEntity)entity;
            if(allowBlockCollisionAdd(state, fBlockEntity.getBlockState())) {
                fBlockEntity.dropItem = false;
                int initLayers = 8;

                if(fBlockEntity.getBlockState().isOf(this)) {
                    initLayers = fBlockEntity.getBlockState().get(LayerBlockFalling.LAYERS);
                }

                int layersToApply = initLayers + state.get(LayerBlockFalling.LAYERS);
                fBlockEntity.setRemoved(RemovalReason.DISCARDED);
                if(layersToApply > 8) {
                    if(fBlockEntity.getBlockState().isOf(this)) {
                        if(!state.get(LayerBlockFalling.DIRTY)){
                            layersToApply -= 8;
                            world.setBlockState(pos.up(), this.getDefaultState().with(LayerBlockFalling.LAYERS, layersToApply).with(LayerBlockFalling.DIRTY, true));
                            world.setBlockState(pos, state.with(LayerBlockFalling.LAYERS, 8));
                            onLayerAddedByCollision(state, world, pos, 8, true);
                        } else {
                            world.setBlockState(pos, this.getDefaultState().with(LayerBlockFalling.DIRTY, false).with(LayerBlockFalling.LAYERS, state.get(LayerBlockFalling.LAYERS)));
                        }
                    } else {
                        if(!state.get(LayerBlockFalling.DIRTY)){
                            layersToApply -= 8;
                            world.setBlockState(pos.up(), this.getDefaultState().with(LayerBlockFalling.LAYERS, layersToApply).with(LayerBlockFalling.DIRTY, true));
                            world.setBlockState(pos, this.getDefaultState().with(LayerBlockFalling.LAYERS, 8).with(LayerBlockFalling.DIRTY, true));
                            onLayerAddedByCollision(state, world, pos, 8, true);
                        } else {
                            world.setBlockState(pos, this.getDefaultState().with(LayerBlockFalling.DIRTY, false).with(LayerBlockFalling.LAYERS, state.get(LayerBlockFalling.LAYERS)));
                        }
                        
                    }
                } else {
                    if(!state.get(LayerBlockFalling.DIRTY)){
                        world.setBlockState(pos, this.getDefaultState().with(LayerBlockFalling.LAYERS, layersToApply));
                        onLayerAddedByCollision(state, world, pos, layersToApply, false);
                    } else {
                        world.setBlockState(pos, this.getDefaultState().with(LayerBlockFalling.DIRTY, false).with(LayerBlockFalling.LAYERS, state.get(LayerBlockFalling.LAYERS)));
                    }
                }
            }
        }
    }
    
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LayerBlockFalling.LAYERS, LayerBlockFalling.WATERLOGGED, LayerBlockFalling.DIRTY);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        if (state.<Boolean>get((Property<Boolean>)LayerBlockFalling.WATERLOGGED)) {
            return Fluids.WATER.getStill(false);
        }
        return super.getFluidState(state);
    }
    
    static {
        LAYERS = Properties.LAYERS;
        WATERLOGGED = Properties.WATERLOGGED;
        DIRTY = BooleanProperty.of("dirty");
        LAYERS_TO_SHAPE = new VoxelShape[] { VoxelShapes.empty(), Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 2.0, 16.0), Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 4.0, 16.0), Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 6.0, 16.0), Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 8.0, 16.0), Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 10.0, 16.0), Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 12.0, 16.0), Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 14.0, 16.0), Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 16.0) };
    }
}