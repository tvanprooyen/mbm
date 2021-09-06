package com.tylervp.mixin;

import java.util.Random;

import com.google.common.collect.ImmutableMap;
import com.tylervp.block.MBMBlocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FireBlock;
import net.minecraft.block.PillarBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;


@Mixin(Block.class)
public abstract class BlockMixin extends AbstractBlock {
	public BlockMixin(Settings settings) {
		super(settings);
	}

	@Override
	public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
		super.onStateReplaced(state, world, pos, newState, moved);
		
		if(state.isOf(Blocks.FIRE)){
			Random random = world.getRandom();

			if(state.get(FireBlock.AGE) > 3 && random.nextInt(5) == 0){
				ImmutableMap <Block, Block>immutableMap = new ImmutableMap.Builder<Block, Block>().put(Blocks.ACACIA_LOG, MBMBlocks.BURNT_ACACIA_LOG).put(Blocks.BIRCH_LOG, MBMBlocks.BURNT_BIRCH_LOG).put(Blocks.DARK_OAK_LOG, MBMBlocks.BURNT_DARK_OAK_LOG).put(Blocks.JUNGLE_LOG, MBMBlocks.BURNT_JUNGLE_LOG).put(Blocks.OAK_LOG, MBMBlocks.BURNT_OAK_LOG).put(Blocks.SPRUCE_LOG, MBMBlocks.BURNT_SPRUCE_LOG).build();

				BlockPos.Mutable mutable3 = pos.mutableCopy();
				for (final Direction lv3 : Direction.values()) {
					BlockState blockState = world.getBlockState(mutable3.set(pos, lv3));
					//Block block = new ImmutableMap.Builder<Block, Block>().put(Blocks.ACACIA_LOG, MBMBlocks.BURNT_ACACIA_LOG).put(Blocks.BIRCH_LOG, MBMBlocks.BURNT_BIRCH_LOG).put(Blocks.DARK_OAK_LOG, MBMBlocks.BURNT_DARK_OAK_LOG).put(Blocks.JUNGLE_LOG, MBMBlocks.BURNT_JUNGLE_LOG).put(Blocks.OAK_LOG, MBMBlocks.BURNT_OAK_LOG).put(Blocks.SPRUCE_LOG, MBMBlocks.BURNT_SPRUCE_LOG).build().get(blockState.getBlock());
					Block block = immutableMap.get(blockState.getBlock());
					if(block != null){
						world.setBlockState(mutable3.set(pos, lv3), block.getDefaultState().with(PillarBlock.AXIS, blockState.get(PillarBlock.AXIS)), 3);
						return;
					}
				}
			}
		}
	}
}