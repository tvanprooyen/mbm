package com.tylervp.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class PineconeBlock extends TreeFruitBlock {
    public static final VoxelShape PINECONE_SHAPE, PINECONE_SHAPE_UP;

    public PineconeBlock(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if(state.get(TreeFruitBlock.UP)) {
            return PINECONE_SHAPE_UP;
        } else {
            return PINECONE_SHAPE;
        }
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return getOutlineShape(state, world, pos, context);
    }

    static {
        PINECONE_SHAPE = Block.createCuboidShape(4, 0, 4, 12, 12, 12);

        PINECONE_SHAPE_UP = Block.createCuboidShape(4, 4, 4, 12, 16, 12);
    }
}