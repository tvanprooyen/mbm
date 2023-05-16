package com.tylervp.mixin;

import net.minecraft.util.math.random.Random;

import com.google.common.collect.ImmutableMap;
import com.tylervp.block.MBMBlocks;
import com.tylervp.block.StairsBlockExtend;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FireBlock;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;


@Mixin(Block.class)
public abstract class BlockMixin extends AbstractBlock {
	public BlockMixin(Settings settings) {
		super(settings);
	}

	private boolean allowSpread(BlockState state) {
		return state.isOf(Blocks.GRASS_BLOCK) || state.isOf(MBMBlocks.DEAD_GRASS_BLOCK) || state.isOf(MBMBlocks.BURNT_GRASS_BLOCK);
	}

	@Override
	public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
		super.onStateReplaced(state, world, pos, newState, moved);
		
		if(state.isOf(Blocks.FIRE)){
			Random random = world.getRandom();

			if(state.get(FireBlock.AGE) > 3 && random.nextInt(2) == 0){
				ImmutableMap <Block, Block>immutableMap = new ImmutableMap.Builder<Block, Block>()
				.put(Blocks.GRASS_BLOCK, MBMBlocks.BURNT_GRASS_BLOCK)
				.put(MBMBlocks.DEAD_GRASS_BLOCK, MBMBlocks.BURNT_GRASS_BLOCK)
				.put(MBMBlocks.BURNT_GRASS_BLOCK, Blocks.DIRT)
				.put(MBMBlocks.GRASS_SLAB, MBMBlocks.BURNT_GRASS_BLOCK_SLAB)
				.put(MBMBlocks.DEAD_GRASS_BLOCK_SLAB, MBMBlocks.BURNT_GRASS_BLOCK_SLAB)
				.put(MBMBlocks.BURNT_GRASS_BLOCK_SLAB, MBMBlocks.DIRT_SLAB)
				.put(Blocks.ACACIA_PLANKS, MBMBlocks.BURNT_PLANKS)
				.put(Blocks.BIRCH_PLANKS, MBMBlocks.BURNT_PLANKS)
				.put(Blocks.DARK_OAK_PLANKS, MBMBlocks.BURNT_PLANKS)
				.put(Blocks.JUNGLE_PLANKS, MBMBlocks.BURNT_PLANKS)
				.put(Blocks.OAK_PLANKS, MBMBlocks.BURNT_PLANKS)
				.put(Blocks.SPRUCE_PLANKS, MBMBlocks.BURNT_PLANKS)
				.put(Blocks.MANGROVE_PLANKS, MBMBlocks.BURNT_PLANKS)
				.put(Blocks.ACACIA_STAIRS, MBMBlocks.BURNT_PLANKS_STAIRS)
				.put(Blocks.BIRCH_STAIRS, MBMBlocks.BURNT_PLANKS_STAIRS)
				.put(Blocks.DARK_OAK_STAIRS, MBMBlocks.BURNT_PLANKS_STAIRS)
				.put(Blocks.JUNGLE_STAIRS, MBMBlocks.BURNT_PLANKS_STAIRS)
				.put(Blocks.OAK_STAIRS, MBMBlocks.BURNT_PLANKS_STAIRS)
				.put(Blocks.SPRUCE_STAIRS, MBMBlocks.BURNT_PLANKS_STAIRS)
				.put(Blocks.MANGROVE_STAIRS, MBMBlocks.BURNT_PLANKS_STAIRS)
				.put(Blocks.ACACIA_SLAB, MBMBlocks.BURNT_PLANKS_SLAB)
				.put(Blocks.BIRCH_SLAB, MBMBlocks.BURNT_PLANKS_SLAB)
				.put(Blocks.DARK_OAK_SLAB, MBMBlocks.BURNT_PLANKS_SLAB)
				.put(Blocks.JUNGLE_SLAB, MBMBlocks.BURNT_PLANKS_SLAB)
				.put(Blocks.OAK_SLAB, MBMBlocks.BURNT_PLANKS_SLAB)
				.put(Blocks.SPRUCE_SLAB, MBMBlocks.BURNT_PLANKS_SLAB)
				.put(Blocks.MANGROVE_SLAB, MBMBlocks.BURNT_PLANKS_SLAB).build();

				/* 
ACACIA
DARK_OAK
BIRCH
OAK
JUNGLE
MANGROVE
SPRUCE
				 */

				BlockPos.Mutable mutable3 = pos.mutableCopy();
				for (final Direction lv3 : Direction.values()) {
					BlockState blockState = world.getBlockState(mutable3.set(pos, lv3));
					Block block = immutableMap.get(blockState.getBlock());
					if(block != null){
						if(block.getClass().getSuperclass() == StairsBlock.class || block.getClass().getSuperclass() == StairsBlockExtend.class){
							world.setBlockState(mutable3.set(pos, lv3), block.getDefaultState().with(StairsBlock.FACING, blockState.get(StairsBlock.FACING)).with(StairsBlock.HALF, blockState.get(StairsBlock.HALF)).with(StairsBlock.SHAPE, blockState.get(StairsBlock.SHAPE)).with(StairsBlock.WATERLOGGED, blockState.get(StairsBlock.WATERLOGGED)), 3);
						} else if(block.getClass().getSuperclass() == SlabBlock.class || block.getClass() == SlabBlock.class){
							world.setBlockState(mutable3.set(pos, lv3), block.getDefaultState().with(SlabBlock.TYPE, blockState.get(SlabBlock.TYPE)).with(SlabBlock.WATERLOGGED, blockState.get(SlabBlock.WATERLOGGED)), 3);
						} else {
							world.setBlockState(mutable3.set(pos, lv3), block.getDefaultState(), 3);
						}
						return;
					}
				}
			}


			if(state.get(FireBlock.AGE) > 3 && random.nextInt(5) == 0){
				ImmutableMap <Block, Block>immutableMap = new ImmutableMap.Builder<Block, Block>()
				.put(Blocks.ACACIA_LOG, MBMBlocks.BURNT_ACACIA_LOG)
				.put(Blocks.BIRCH_LOG, MBMBlocks.BURNT_BIRCH_LOG)
				.put(Blocks.DARK_OAK_LOG, MBMBlocks.BURNT_DARK_OAK_LOG)
				.put(Blocks.JUNGLE_LOG, MBMBlocks.BURNT_JUNGLE_LOG)
				.put(Blocks.OAK_LOG, MBMBlocks.BURNT_OAK_LOG)
				.put(Blocks.MANGROVE_LOG, MBMBlocks.BURNT_MANGROVE_LOG)
				.put(Blocks.SPRUCE_LOG, MBMBlocks.BURNT_SPRUCE_LOG)
				.put(MBMBlocks.ACACIA_HOLLOW_LOG, MBMBlocks.BURNT_ACACIA_HOLLOW_LOG)
				.put(MBMBlocks.DARK_OAK_HOLLOW_LOG, MBMBlocks.BURNT_DARK_OAK_HOLLOW_LOG)
				.put(MBMBlocks.BIRCH_HOLLOW_LOG, MBMBlocks.BURNT_BIRCH_HOLLOW_LOG)
				.put(MBMBlocks.OAK_HOLLOW_LOG, MBMBlocks.BURNT_OAK_HOLLOW_LOG)
				.put(MBMBlocks.JUNGLE_HOLLOW_LOG, MBMBlocks.BURNT_JUNGLE_HOLLOW_LOG)
				.put(MBMBlocks.MANGROVE_HOLLOW_LOG, MBMBlocks.BURNT_MANGROVE_HOLLOW_LOG)
				.put(MBMBlocks.SPRUCE_HOLLOW_LOG, MBMBlocks.BURNT_SPRUCE_HOLLOW_LOG)
				.put(Blocks.STRIPPED_ACACIA_LOG, MBMBlocks.STRIPPED_BURNT_ACACIA_LOG)
				.put(Blocks.STRIPPED_DARK_OAK_LOG, MBMBlocks.STRIPPED_BURNT_DARK_OAK_LOG)
				.put(Blocks.STRIPPED_BIRCH_LOG, MBMBlocks.STRIPPED_BURNT_BIRCH_LOG)
				.put(Blocks.STRIPPED_OAK_LOG, MBMBlocks.STRIPPED_BURNT_OAK_LOG)
				.put(Blocks.STRIPPED_JUNGLE_LOG, MBMBlocks.STRIPPED_BURNT_JUNGLE_LOG)
				.put(Blocks.STRIPPED_MANGROVE_LOG, MBMBlocks.STRIPPED_BURNT_MANGROVE_LOG)
				.put(Blocks.STRIPPED_SPRUCE_LOG, MBMBlocks.STRIPPED_BURNT_SPRUCE_LOG)
				.put(MBMBlocks.STRIPPED_ACACIA_HOLLOW_LOG, MBMBlocks.BURNT_STRIPPED_ACACIA_HOLLOW_LOG)
				.put(MBMBlocks.STRIPPED_DARK_OAK_HOLLOW_LOG, MBMBlocks.BURNT_STRIPPED_DARK_OAK_HOLLOW_LOG)
				.put(MBMBlocks.STRIPPED_BIRCH_HOLLOW_LOG, MBMBlocks.BURNT_STRIPPED_BIRCH_HOLLOW_LOG)
				.put(MBMBlocks.STRIPPED_OAK_HOLLOW_LOG, MBMBlocks.BURNT_STRIPPED_OAK_HOLLOW_LOG)
				.put(MBMBlocks.STRIPPED_JUNGLE_HOLLOW_LOG, MBMBlocks.BURNT_STRIPPED_JUNGLE_HOLLOW_LOG)
				.put(MBMBlocks.STRIPPED_MANGROVE_HOLLOW_LOG, MBMBlocks.BURNT_STRIPPED_MANGROVE_HOLLOW_LOG)
				.put(MBMBlocks.STRIPPED_SPRUCE_HOLLOW_LOG, MBMBlocks.BURNT_STRIPPED_SPRUCE_HOLLOW_LOG).build();

				BlockPos.Mutable mutable3 = pos.mutableCopy();
				for (final Direction lv3 : Direction.values()) {
					BlockState blockState = world.getBlockState(mutable3.set(pos, lv3));
					Block block = immutableMap.get(blockState.getBlock());
					if(block != null){
						world.setBlockState(mutable3.set(pos, lv3), block.getDefaultState().with(PillarBlock.AXIS, blockState.get(PillarBlock.AXIS)), 3);
						return;
					}
				}
			}


			if (!world.getGameRules().getBoolean(GameRules.DO_FIRE_TICK)) {
				return;
			}

			if(state.get(FireBlock.AGE) < 2 && !newState.isOf(Blocks.AIR)) {
				BlockPos.Mutable mutable2 = pos.mutableCopy();
				if(random.nextInt(2) == 0){
					for (final Direction lv3 : Direction.values()) {
						int spreadChance = random.nextInt(3);

						if(world.getBlockState(mutable2.set(pos.down(), lv3)).isOf(MBMBlocks.DEAD_GRASS_BLOCK) || world.getBlockState(mutable2.set(pos.down(), lv3)).isOf(MBMBlocks.DEAD_GRASS_BLOCK_SLAB)){
							spreadChance = random.nextInt(2);
						}

						if(spreadChance == 0){
							if(allowSpread(world.getBlockState(mutable2.set(pos.down(), lv3))) && world.getBlockState(mutable2.set(pos, lv3)).isAir()){
								world.setBlockState(mutable2.set(pos, lv3), Blocks.FIRE.getDefaultState(), 3);
							}
						}
					}
				}
			}
		}
	}
}