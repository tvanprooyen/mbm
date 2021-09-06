package com.tylervp.block;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.property.Property;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class LayerBlockPowder extends LayerBlockFalling {
    private final BlockState hardenedState;

    protected LayerBlockPowder(Block hardened,AbstractBlock.Settings settings) {
        super(settings);
        //this.setDefaultState(this.stateManager.getDefaultState().with(Properties.LAYERS, 1).with(Properties.WATERLOGGED, false));
        this.hardenedState = hardened.getDefaultState();
    }

    @Override
    public void onLanding(World world, BlockPos pos, BlockState fallingBlockState, BlockState currentStateInPos, FallingBlockEntity fallingBlockEntity) {

        int layers = fallingBlockState.<Integer>get((Property<Integer>)LayerBlockFalling.LAYERS);
        if (hardensOnAnySide(world, pos) && layers > 7) {
            world.setBlockState(pos, this.hardenedState, 3);
        }
        super.onLanding(world, pos, fallingBlockState, currentStateInPos, fallingBlockEntity);
    }

    @Override
    public void onLayerAddedByCollision(BlockState state, World world, BlockPos pos, int layers, boolean dirty) {
            if (hardensOnAnySide(world, pos) && layers > 7) {
                world.setBlockState(pos, this.hardenedState, 3);
            }
        super.onLayerAddedByCollision(state, world, pos, layers, dirty);
    }

    @Override
    public boolean allowBlockCollisionAdd(BlockState state, BlockState fEntity) {
        return (
                (fEntity.isOf(Blocks.BLACK_CONCRETE_POWDER) && state.isOf(MBMBlocks.BLACK_CONCRETE_POWDER_LAYER)) || 
                (fEntity.isOf(Blocks.RED_CONCRETE_POWDER) && state.isOf(MBMBlocks.RED_CONCRETE_POWDER_LAYER)) || 
                (fEntity.isOf(Blocks.GREEN_CONCRETE_POWDER) && state.isOf(MBMBlocks.GREEN_CONCRETE_POWDER_LAYER)) || 
                (fEntity.isOf(Blocks.BROWN_CONCRETE_POWDER) && state.isOf(MBMBlocks.BROWN_CONCRETE_POWDER_LAYER)) || 
                (fEntity.isOf(Blocks.BLUE_CONCRETE_POWDER) && state.isOf(MBMBlocks.BLUE_CONCRETE_POWDER_LAYER)) || 
                (fEntity.isOf(Blocks.PURPLE_CONCRETE_POWDER) && state.isOf(MBMBlocks.PURPLE_CONCRETE_POWDER_LAYER)) || 
                (fEntity.isOf(Blocks.LIGHT_GRAY_CONCRETE_POWDER) && state.isOf(MBMBlocks.LIGHT_GRAY_CONCRETE_POWDER_LAYER)) || 
                (fEntity.isOf(Blocks.CYAN_CONCRETE_POWDER) && state.isOf(MBMBlocks.CYAN_CONCRETE_POWDER_LAYER)) || 
                (fEntity.isOf(Blocks.GRAY_CONCRETE_POWDER) && state.isOf(MBMBlocks.GRAY_CONCRETE_POWDER_LAYER)) || 
                (fEntity.isOf(Blocks.PINK_CONCRETE_POWDER) && state.isOf(MBMBlocks.PINK_CONCRETE_POWDER_LAYER)) || 
                (fEntity.isOf(Blocks.LIME_CONCRETE_POWDER) && state.isOf(MBMBlocks.LIME_CONCRETE_POWDER_LAYER)) || 
                (fEntity.isOf(Blocks.YELLOW_CONCRETE_POWDER) && state.isOf(MBMBlocks.YELLOW_CONCRETE_POWDER_LAYER)) || 
                (fEntity.isOf(Blocks.LIGHT_BLUE_CONCRETE_POWDER) && state.isOf(MBMBlocks.LIGHT_BLUE_CONCRETE_POWDER_LAYER)) || 
                (fEntity.isOf(Blocks.MAGENTA_CONCRETE_POWDER) && state.isOf(MBMBlocks.MAGENTA_CONCRETE_POWDER_LAYER)) || 
                (fEntity.isOf(Blocks.ORANGE_CONCRETE_POWDER) && state.isOf(MBMBlocks.ORANGE_CONCRETE_POWDER_LAYER)) || 
                (fEntity.isOf(Blocks.WHITE_CONCRETE_POWDER) && state.isOf(MBMBlocks.WHITE_CONCRETE_POWDER_LAYER)) || 
                fEntity.isOf(this)
            );
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        super.onStateReplaced(state, world, pos, newState, moved);

        /* int layers = state.<Integer>get((Property<Integer>)LayerBlockFalling.LAYERS);
        if (hardensOnAnySide(world, pos) && layers > 6) {
            world.setBlockState(pos, this.hardenedState);
        } */

    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockView world = ctx.getWorld();
        BlockPos pos = ctx.getBlockPos();
        BlockState state = world.getBlockState(pos);

        if(state.isOf(this)){
                int layers = state.<Integer>get((Property<Integer>)LayerBlockFalling.LAYERS);
                if (shouldHarden(world, pos, state) && layers > 6) {
                    return this.hardenedState;
                }
        }
        
        return super.getPlacementState(ctx);
    }


    private static boolean shouldHarden(BlockView world, BlockPos pos, BlockState state) {
        return hardensIn(state) || hardensOnAnySide(world, pos);
    }
    
    private static boolean hardensOnAnySide(BlockView world, BlockPos pos) {
        boolean boolean3 = false;
        BlockPos.Mutable mutable4 = pos.mutableCopy();
        for (final Direction lv2 : Direction.values()) {
            BlockState blockState9 = world.getBlockState(mutable4);
            if (lv2 != Direction.DOWN || hardensIn(blockState9)) {
                mutable4.set(pos, lv2);
                blockState9 = world.getBlockState(mutable4);
                if (hardensIn(blockState9) && !blockState9.isSideSolidFullSquare(world, pos, lv2.getOpposite())) {
                    boolean3 = true;
                    break;
                }
            }
        }
        return boolean3;
    }
    
    private static boolean hardensIn(BlockState state) {
        return state.getFluidState().isIn(FluidTags.WATER);
    }


    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState newState, WorldAccess world, BlockPos pos, BlockPos posFrom) {
        if (state.<Boolean>get((Property<Boolean>)LayerBlockFalling.WATERLOGGED)) {
            world.getFluidTickScheduler().schedule(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        int layers = state.<Integer>get((Property<Integer>)LayerBlockFalling.LAYERS);
        if (hardensOnAnySide(world, pos) && layers > 7) {
            return this.hardenedState;
        }
        
        return super.getStateForNeighborUpdate(state, direction, newState, world, pos, posFrom);
    }

    @Environment(EnvType.CLIENT)
    @Override
    public int getColor(BlockState state, BlockView world, BlockPos pos) {
        return state.getTopMaterialColor(world, pos).color;
    }
    
}
