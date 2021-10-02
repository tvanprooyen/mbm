package com.tylervp.block;

import java.util.Random;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Fertilizable;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class BurntGrassBlock extends BurntBlock implements Fertilizable {

    public BurntGrassBlock(Settings settings) {
        super(settings);
    }

   public boolean isFertilizable(BlockView world, BlockPos pos, BlockState state, boolean isClient) {
      return world.getBlockState(pos.up()).isAir();
   }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        BlockPos blockPos = pos.up();
      BlockState blockState = MBMBlocks.BURNT_GRASS.getDefaultState();

      world.setBlockState(pos, Blocks.GRASS_BLOCK.getDefaultState());

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
            if (random.nextInt(18) == 0) {
                blockState4 = blockState;
            } else {
               blockState4 = Blocks.AIR.getDefaultState();
            }

            if (!world.getBlockState(blockPos2.down()).isOf(Blocks.GRASS_BLOCK) && blockState4.canPlaceAt(world, blockPos2) && blockState4 != Blocks.AIR.getDefaultState()) {
               world.setBlockState(blockPos2, blockState4, 3);
            }
         }
      }
   }
}
