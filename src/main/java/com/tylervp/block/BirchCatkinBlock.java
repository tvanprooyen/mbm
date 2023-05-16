package com.tylervp.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class BirchCatkinBlock extends TreeFruitBlock {
    public static final VoxelShape CATKIN_SHAPE, CATKIN_SHAPE_UP;

    public BirchCatkinBlock(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if(state.get(TreeFruitBlock.UP)) {
            return CATKIN_SHAPE_UP;
        } else {
            return CATKIN_SHAPE;
        }
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return getOutlineShape(state, world, pos, context);
    }

    static {
        CATKIN_SHAPE = Block.createCuboidShape(5, 0, 6, 11, 12, 12);

        CATKIN_SHAPE_UP = Block.createCuboidShape(5, 4, 6, 11, 16, 12);
    }
}