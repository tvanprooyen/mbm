package com.tylervp.mixin;

import java.util.Random;

import com.tylervp.block.MBMBlocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.GrassBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.SpreadableBlock;
import net.minecraft.block.enums.SlabType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.chunk.light.ChunkLightProvider;
import net.minecraft.block.SnowBlock;
import net.minecraft.tag.FluidTags;
import net.minecraft.world.WorldView;

import org.spongepowered.asm.mixin.Mixin;


@Mixin(GrassBlock.class)
public abstract class GrassBlockMixin extends SpreadableBlock {
	public GrassBlockMixin(Settings settings) {
		super(settings);
	}


    private static boolean canSurvive(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockPos = pos.up();
        BlockState blockState = world.getBlockState(blockPos);
    
         if (blockState.isOf(Blocks.SNOW) && (Integer)blockState.get(SnowBlock.LAYERS) == 1) {
            return true;
        } else if (blockState.getFluidState().getLevel() == 8 || blockState.isOf(MBMBlocks.GRASS_SLAB)) {
            if(blockState.isOf(MBMBlocks.GRASS_SLAB)){
                if(blockState.get(SlabBlock.WATERLOGGED)){
                    return false;
                }
                return true;
            } else {
                return false;
            }
        } else {
            boolean isSlab = false;
            if(blockState.isOf(MBMBlocks.GRASS_SLAB)) {
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
                                world.setBlockState(blockPos, (BlockState)MBMBlocks.GRASS_SLAB.getDefaultState().with(SlabBlock.TYPE, type).with(SlabBlock.WATERLOGGED, world.getBlockState(blockPos).get(SlabBlock.WATERLOGGED)));
                                return;
                            }
                        }
                        
                    }
                }
            }
            
        }

        //Grass Spread and Fire
        super.randomTick(state, world, pos, random);
        if(world.getBlockState(pos.down()).isOf(Blocks.FIRE)){
            world.setBlockState(pos, MBMBlocks.DEAD_GRASS_BLOCK.getDefaultState());
        }
    }
}