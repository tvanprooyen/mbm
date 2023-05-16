package com.tylervp.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class OakAcornBlock extends TreeFruitBlock {

    public static final VoxelShape ACORN_SHAPE, ACORN_SHAPE_UP;

    public OakAcornBlock(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if(state.get(TreeFruitBlock.UP)) {
            return ACORN_SHAPE_UP;
        } else {
            return ACORN_SHAPE;
        }
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return getOutlineShape(state, world, pos, context);
    }

    static {

        ACORN_SHAPE = VoxelShapes.union(Block.createCuboidShape(5, 0, 5, 11, 6, 11), Block.createCuboidShape(4, 6, 4, 12, 11, 12));

        ACORN_SHAPE_UP = VoxelShapes.union(Block.createCuboidShape(4, 11, 4, 12, 16, 12), Block.createCuboidShape(5, 5, 5, 11, 11, 11));
    }
}
