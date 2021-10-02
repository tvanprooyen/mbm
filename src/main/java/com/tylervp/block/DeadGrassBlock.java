package com.tylervp.block;

//import java.util.List;
import java.util.Random;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Fertilizable;
import net.minecraft.block.SpreadableBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.enums.SlabType;
import net.minecraft.util.math.Direction;
import net.minecraft.world.chunk.light.ChunkLightProvider;
import net.minecraft.block.SnowBlock;
import net.minecraft.tag.FluidTags;
import net.minecraft.world.WorldView;
//import net.minecraft.world.gen.feature.ConfiguredFeature;
//import net.minecraft.world.gen.feature.FlowerFeature;

public class DeadGrassBlock extends SpreadableBlock implements Fertilizable {
   public DeadGrassBlock(AbstractBlock.Settings settings) {
      super(settings);
   }

   public boolean isFertilizable(BlockView world, BlockPos pos, BlockState state, boolean isClient) {
      return world.getBlockState(pos.up()).isAir();
   }

   public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
      return true;
   }

   public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
      BlockPos blockPos = pos.up();
      BlockState blockState = MBMBlocks.DEAD_GRASS.getDefaultState();
      BlockState grass = Blocks.GRASS.getDefaultState();

      label48:
      for(int i = 0; i < 128; ++i) {
         BlockPos blockPos2 = blockPos;

         for(int j = 0; j < i / 16; ++j) {
            blockPos2 = blockPos2.add(random.nextInt(3) - 1, (random.nextInt(3) - 1) * random.nextInt(3) / 2, random.nextInt(3) - 1);
            if (!world.getBlockState(blockPos2.down()).isOf(this) || world.getBlockState(blockPos2).isFullCube(world, blockPos2)) {
               continue label48;
            }
         }

         BlockState blockState2 = world.getBlockState(blockPos2);
         if (blockState2.isOf(blockState.getBlock()) && random.nextInt(10) == 0) {
            ((Fertilizable)blockState.getBlock()).grow(world, random, blockPos2, blockState2);
         }

         if (blockState2.isAir()) {
            BlockState blockState4;
            if (random.nextInt(8) == 0) {
               /* List<ConfiguredFeature<?, ?>> list = world.getBiome(blockPos2).getGenerationSettings().getFlowerFeatures();
               if (list.isEmpty()) {
                  continue;
               }

               ConfiguredFeature<?, ?> configuredFeature = (ConfiguredFeature)list.get(0);
               FlowerFeature flowerFeature = (FlowerFeature)configuredFeature.feature;
               blockState4 = flowerFeature.getFlowerState(random, blockPos2, configuredFeature.getConfig()); */
               blockState4 = grass;
            } else {
               if (random.nextInt(10) == 0) {
                  Random flipRandom = world.random;
                  blockState4 = MBMBlocks.RICE.getDefaultState().with(RiceCropBlock.AGE, 7).with(RiceCropBlock.FLIP, (!world.isClient && flipRandom.nextInt(2) == 0));
               } else{
                  if (random.nextInt(5) == 0) {
                     blockState4 = blockState;
                  } else{
                     blockState4 = Blocks.AIR.getDefaultState();
                  }
               }
            }

            if (blockState4.canPlaceAt(world, blockPos2) && blockState4 != Blocks.AIR.getDefaultState()) {
               world.setBlockState(blockPos2, blockState4, 3);
            }
         }
      }
   }


   private static boolean canSurvive(BlockState state, WorldView world, BlockPos pos) {
      BlockPos blockPos = pos.up();
      BlockState blockState = world.getBlockState(blockPos);
  
       if (blockState.isOf(Blocks.SNOW) && (Integer)blockState.get(SnowBlock.LAYERS) == 1) {
          return true;
      } else if (blockState.getFluidState().getLevel() == 8 || blockState.isOf(MBMBlocks.DEAD_GRASS_BLOCK_SLAB)) {
          if(blockState.isOf(MBMBlocks.DEAD_GRASS_BLOCK_SLAB)){
              if(blockState.get(SlabBlock.WATERLOGGED)){
                  return false;
              }
              return true;
          } else {
              return false;
          }
      } else {
          boolean isSlab = false;
          if(blockState.isOf(MBMBlocks.DEAD_GRASS_BLOCK_SLAB)) {
              isSlab = blockState.get(SlabBlock.TYPE) == SlabType.BOTTOM || blockState.get(SlabBlock.TYPE) == SlabType.TOP;
          }
  
          int i = ChunkLightProvider.getRealisticOpacity(world, state, pos, blockState, blockPos, Direction.UP, blockState.getOpacity(world, blockPos));
          return i < world.getMaxLightLevel() || (!world.getBlockState(blockPos).isFullCube(world, blockPos) && !world.getBlockState(pos).isFullCube(world, pos) && isSlab);
      }
  }
  
  private static boolean canSpread(BlockState state, WorldView world, BlockPos pos) {
      BlockPos blockPos = pos.up();
      return canSurvive(state, world, pos) && !world.getFluidState(blockPos).isIn(FluidTags.WATER);
  }


 @Override
  public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
      if (canSurvive(state, world, pos)) {
          if (world.getLightLevel(pos.up()) >= 9) {
              BlockState blockState = this.getDefaultState();
  
              for(int i = 0; i < 4; ++i) {
                  BlockPos blockPos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
                  if ((world.getBlockState(blockPos).isOf(MBMBlocks.DIRT_SLAB) && canSpread(blockState, world, blockPos))) {
                      BlockState blockState2 = world.getBlockState(blockPos);
                      BlockState blockState3 = world.getBlockState(blockPos.up());
                      SlabType type = world.getBlockState(blockPos).get(SlabBlock.TYPE);
  
                      boolean isTouchingSlab = false;
                      if((blockState3.getBlock().getClass() == SlabBlock.class || blockState3.getBlock().getClass().getSuperclass() == SlabBlock.class)){
                          if(blockState3.get(SlabBlock.TYPE) == SlabType.BOTTOM && blockState2.get(SlabBlock.TYPE) == SlabType.TOP){
                              isTouchingSlab = true;
                          }
                      }
  
                      if(!world.getBlockState(blockPos).get(SlabBlock.WATERLOGGED)) {
                          if(!isTouchingSlab) {
                              world.setBlockState(blockPos, (BlockState)MBMBlocks.DEAD_GRASS_BLOCK_SLAB.getDefaultState().with(SlabBlock.TYPE, type).with(SlabBlock.WATERLOGGED, world.getBlockState(blockPos).get(SlabBlock.WATERLOGGED)));
                              return;
                          }
                      }
                      
                  }
              }
          }
          
      }

      //Grass Spread
      super.randomTick(state, world, pos, random);
  }
}
