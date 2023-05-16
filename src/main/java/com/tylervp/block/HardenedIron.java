package com.tylervp.block;

import net.minecraft.util.math.random.Random;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public class HardenedIron extends Block {

    public HardenedIron(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(((BlockState)this.getDefaultState()));
    }

    private static boolean heatOnAnySide(BlockView world, BlockPos pos, Block block) {
        return world.getBlockState(pos.north()).isOf(block) || world.getBlockState(pos.east()).isOf(block) || world.getBlockState(pos.south()).isOf(block) || world.getBlockState(pos.west()).isOf(block) || world.getBlockState(pos.up()).isOf(block) || world.getBlockState(pos.down()).isOf(block);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (random.nextFloat() < 0.05688889F) {
            if((heatOnAnySide(world, pos, Blocks.LAVA) || heatOnAnySide(world, pos, Blocks.FIRE) || heatOnAnySide(world, pos, Blocks.CAMPFIRE) || heatOnAnySide(world, pos, Blocks.SOUL_CAMPFIRE))){
                world.setBlockState(pos, MBMBlocks.HEATED_IRON.getDefaultState().with(HeatedBlock.HARDEN, true));
            }
        }
        super.randomTick(state, world, pos, random);
    }
}