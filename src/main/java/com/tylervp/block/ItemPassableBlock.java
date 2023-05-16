package com.tylervp.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class ItemPassableBlock extends Block {
    protected static final VoxelShape OUTLINE_SHAPE_Y, RAY_TRACE_SHAPE_Y;

    public ItemPassableBlock(AbstractBlock.Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {

        if(world.getBlockState(pos.up()).isOf(Blocks.DROPPER) || world.getBlockState(pos.up()).isOf(Blocks.DISPENSER) || world.getBlockState(pos.up()).isOf(this)) {
            return OUTLINE_SHAPE_Y;
        } else if (world.getBlockState(pos.down()).isOf(Blocks.HOPPER)) {
            return Block.createCuboidShape(0.0, 8.0, 0.0, 16.0, 15.0, 16.0);
        } else {
            return Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 15.0, 16.0);
        }
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.fullCube();
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if(entity instanceof ItemEntity && !(world.getBlockState(pos.up()).isOf(Blocks.DROPPER) || world.getBlockState(pos.up()).isOf(Blocks.DISPENSER) || world.getBlockState(pos.up()).isOf(this))) {
            if(Math.abs(pos.getY() - entity.getPos().y) > 0.8 ){
                ItemEntity Ient = (ItemEntity)entity;
                double posX = Ient.getPos().x;
                double posY = pos.getY() - 0.7f;
                double posZ = Ient.getPos().z;

                if(world.getBlockState(pos.down()).isOf(Blocks.HOPPER)) {
                    posX = pos.getX() + 0.5f;
                    posY = pos.getY() - 0.3f;
                    posZ = pos.getZ() + 0.5f;
                }

                entity.setPosition(posX, posY, posZ);
            }
        }
    }

    static {
        RAY_TRACE_SHAPE_Y = Block.createCuboidShape(1.0, 0.0, 1.0, 15.0, 15.0, 15.0);
        OUTLINE_SHAPE_Y = VoxelShapes.combineAndSimplify(VoxelShapes.fullCube(), RAY_TRACE_SHAPE_Y, BooleanBiFunction.ONLY_FIRST);
    }
}
