package com.tylervp.block;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
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

public class LayerBlockPowder extends LayerBlock {
    private final BlockState hardenedState;

    protected LayerBlockPowder(Block hardened,AbstractBlock.Settings settings) {
        super(settings);
        //this.setDefaultState(this.stateManager.getDefaultState().with(Properties.LAYERS, 1).with(Properties.WATERLOGGED, false));
        this.hardenedState = hardened.getDefaultState();
    }


    @Override
    public void onLanding(World world, BlockPos pos, BlockState fallingBlockState, BlockState currentStateInPos, FallingBlockEntity fallingBlockEntity) {
        int layers = fallingBlockState.<Integer>get((Property<Integer>)LayerBlock.LAYERS);
        if (shouldHarden(world, pos, currentStateInPos) && layers == 8) {
            world.setBlockState(pos, this.hardenedState, 3);
        }
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockView world = ctx.getWorld();
        BlockPos pos = ctx.getBlockPos();
        BlockState state = world.getBlockState(pos);

        if(state.isOf(this)){
                int layers = state.<Integer>get((Property<Integer>)LayerBlock.LAYERS);
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
        if (state.<Boolean>get((Property<Boolean>)LayerBlock.WATERLOGGED)) {
            world.getFluidTickScheduler().schedule(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }

        int layers = state.<Integer>get((Property<Integer>)LayerBlock.LAYERS);
        if (hardensOnAnySide(world, pos) && layers > 6) {
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
