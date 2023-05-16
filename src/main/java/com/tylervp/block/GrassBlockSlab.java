package com.tylervp.block;

//import java.util.List;
import java.util.Random;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.SnowBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.enums.SlabType;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldView;
import net.minecraft.world.chunk.light.ChunkLightProvider;

public class GrassBlockSlab extends SlabBlock {

   public GrassBlockSlab(AbstractBlock.Settings settings) {
      super(settings);
      this.setDefaultState((BlockState)((BlockState)this.getDefaultState().with(TYPE, SlabType.BOTTOM)).with(WATERLOGGED, false));
   }

   private static boolean canSurvive(BlockState state, WorldView world, BlockPos pos) {
    BlockPos blockPos = pos.up();
    BlockState blockState = world.getBlockState(blockPos);

     if (blockState.isOf(Blocks.SNOW) && (Integer)blockState.get(SnowBlock.LAYERS) == 1) {
        return true;
    } else if (blockState.getFluidState().getLevel() == 8 || state.get(WATERLOGGED)) {
        return false;
    } else {
        boolean isSlab = false;
        if(state.isOf(MBMBlocks.GRASS_SLAB)) {
            isSlab = state.get(SlabBlock.TYPE) == SlabType.BOTTOM || state.get(SlabBlock.TYPE) == SlabType.TOP;
        }
        
        if((blockState.getBlock().getClass() == SlabBlock.class || blockState.getBlock().getClass().getSuperclass() == SlabBlock.class)){
            if(blockState.get(SlabBlock.TYPE) == SlabType.BOTTOM && state.get(SlabBlock.TYPE) == SlabType.TOP){
                return false;
            }
        }

        int i = ChunkLightProvider.getRealisticOpacity(world, state, pos, blockState, blockPos, Direction.UP, blockState.getOpacity(world, blockPos));
        return i < world.getMaxLightLevel() || (!world.getBlockState(blockPos).isFullCube(world, blockPos) && !world.getBlockState(pos).isFullCube(world, pos) && isSlab);
    }
}

private static boolean canSpread(BlockState state, WorldView world, BlockPos pos) {
    BlockPos blockPos = pos.up();
    return canSurvive(state, world, pos) && !world.getFluidState(blockPos).isIn(FluidTags.WATER);
}

public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {

    if (!canSurvive(state, world, pos)) {
        if(world.getBlockState(pos).isOf(Blocks.GRASS_BLOCK)){
            world.setBlockState(pos, Blocks.DIRT.getDefaultState());
        } 
        
        world.setBlockState(pos, (BlockState)MBMBlocks.DIRT_SLAB.getDefaultState().with(SlabBlock.TYPE, world.getBlockState(pos).get(SlabBlock.TYPE)).with(SlabBlock.WATERLOGGED, world.getBlockState(pos).get(SlabBlock.WATERLOGGED)));
    } else {
        if (world.getLightLevel(pos.up()) >= 9) {
            BlockState blockState = this.getDefaultState();

            for(int i = 0; i < 4; ++i) {
                BlockPos blockPos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                if ((world.getBlockState(blockPos).isOf(MBMBlocks.DIRT_SLAB) || world.getBlockState(blockPos).isOf(Blocks.DIRT)) && canSpread(blockState, world, blockPos)) {
                    BlockState blockState2 = world.getBlockState(blockPos);
                    BlockState blockState3 = world.getBlockState(blockPos.up());
                    SlabType type;

                    if(world.getBlockState(blockPos).isOf(Blocks.DIRT)){
                        world.setBlockState(blockPos, Blocks.GRASS_BLOCK.getDefaultState());
                        return;
                    }  else {
                        type = world.getBlockState(blockPos).get(SlabBlock.TYPE);
                    }

                    boolean isTouchingSlab = false;
                    if((blockState3.getBlock().getClass() == SlabBlock.class || blockState3.getBlock().getClass().getSuperclass() == SlabBlock.class)){
                        if(blockState3.get(SlabBlock.TYPE) == SlabType.BOTTOM && blockState2.get(SlabBlock.TYPE) == SlabType.TOP){
                            isTouchingSlab = true;
                        }
                    }

                    if(!world.getBlockState(blockPos).get(SlabBlock.WATERLOGGED)) {
                        if(!isTouchingSlab) {
                            world.setBlockState(blockPos, (BlockState)blockState.with(SlabBlock.TYPE, type).with(SlabBlock.WATERLOGGED, world.getBlockState(blockPos).get(SlabBlock.WATERLOGGED)));
                        }
                    }
                    
                }
            }
        }

    }
}   
}