package com.tylervp.block;

import net.minecraft.util.math.random.Random;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Fertilizable;
import net.minecraft.block.PlantBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class MBMFernBlock extends PlantBlock implements Fertilizable {
	protected static final float field_31261 = 6.0F;
	protected static final VoxelShape SHAPE;

	public MBMFernBlock(AbstractBlock.Settings settings) {
		super(settings);
	}

    @Override
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return SHAPE;
	}

    @Override
	public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state, boolean isClient) {
		return true;
	}

    @Override
	public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
		return true;
	}

    @Override
	public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        
        TallPlantBlock tallPlantBlock = (TallPlantBlock)(state.isOf(MBMBlocks.DEAD_GRASS) ? MBMBlocks.DEAD_TALL_GRASS : MBMBlocks.BURNT_TALL_GRASS);
		if (tallPlantBlock.getDefaultState().canPlaceAt(world, pos) && world.isAir(pos.up())) {
			TallPlantBlock.placeAt(world, tallPlantBlock.getDefaultState(), pos, 2);
		}
       
	}

    public AbstractBlock.OffsetType getOffsetType() {
        return AbstractBlock.OffsetType.XZ;
    }

    static {
        SHAPE = Block.createCuboidShape(2.0, 0.0, 2.0, 14.0, 13.0, 14.0);
    }
}
