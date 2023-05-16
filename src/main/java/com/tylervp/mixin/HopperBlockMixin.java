package com.tylervp.mixin;

import org.spongepowered.asm.mixin.Mixin;

import com.tylervp.block.MBMBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HopperBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.Hopper;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

@Mixin(HopperBlock.class)
public class HopperBlockMixin extends Block {

    public HopperBlockMixin(Settings settings) {
        super(settings);
    }

    private static final VoxelShape TOP_SHAPE = Block.createCuboidShape(0.0, 10.0, 0.0, 16.0, 16.0, 16.0);
    private static final VoxelShape MIDDLE_SHAPE = Block.createCuboidShape(4.0, 4.0, 4.0, 12.0, 10.0, 12.0);
    private static final VoxelShape OUTSIDE_SHAPE = VoxelShapes.union(MIDDLE_SHAPE, TOP_SHAPE);
    private static final VoxelShape DEFAULT_SHAPE = VoxelShapes.combineAndSimplify(OUTSIDE_SHAPE, Hopper.INSIDE_SHAPE, BooleanBiFunction.ONLY_FIRST);
    private static final VoxelShape DOWN_SHAPE = VoxelShapes.union(DEFAULT_SHAPE, Block.createCuboidShape(6.0, 0.0, 6.0, 10.0, 4.0, 10.0));
    private static final VoxelShape EAST_SHAPE = VoxelShapes.union(DEFAULT_SHAPE, Block.createCuboidShape(12.0, 4.0, 6.0, 16.0, 8.0, 10.0));
    private static final VoxelShape NORTH_SHAPE = VoxelShapes.union(DEFAULT_SHAPE, Block.createCuboidShape(6.0, 4.0, 0.0, 10.0, 8.0, 4.0));
    private static final VoxelShape SOUTH_SHAPE = VoxelShapes.union(DEFAULT_SHAPE, Block.createCuboidShape(6.0, 4.0, 12.0, 10.0, 8.0, 16.0));
    private static final VoxelShape WEST_SHAPE = VoxelShapes.union(DEFAULT_SHAPE, Block.createCuboidShape(0.0, 4.0, 6.0, 4.0, 8.0, 10.0));

    private static final VoxelShape DISABLED_DEFAULT_SHAPE = OUTSIDE_SHAPE;
    private static final VoxelShape DISABLED_DOWN_SHAPE = VoxelShapes.union(DISABLED_DEFAULT_SHAPE, Block.createCuboidShape(6.0, 0.0, 6.0, 10.0, 4.0, 10.0));
    private static final VoxelShape DISABLED_EAST_SHAPE = VoxelShapes.union(DISABLED_DEFAULT_SHAPE, Block.createCuboidShape(12.0, 4.0, 6.0, 16.0, 8.0, 10.0));
    private static final VoxelShape DISABLED_NORTH_SHAPE = VoxelShapes.union(DISABLED_DEFAULT_SHAPE, Block.createCuboidShape(6.0, 4.0, 0.0, 10.0, 8.0, 4.0));
    private static final VoxelShape DISABLED_SOUTH_SHAPE = VoxelShapes.union(DISABLED_DEFAULT_SHAPE, Block.createCuboidShape(6.0, 4.0, 12.0, 10.0, 8.0, 16.0));
    private static final VoxelShape DISABLED_WEST_SHAPE = VoxelShapes.union(DISABLED_DEFAULT_SHAPE, Block.createCuboidShape(0.0, 4.0, 6.0, 4.0, 8.0, 10.0));

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if(world.getFluidState(pos.up()).isIn(FluidTags.WATER) && MBMBlocks.CFR.isHopperTopCollisionDisabled()) {
            switch (state.get(HopperBlock.FACING)) {
                case DOWN: {
                    return DISABLED_DOWN_SHAPE;
                }
                case NORTH: {
                    return DISABLED_NORTH_SHAPE;
                }
                case SOUTH: {
                    return DISABLED_SOUTH_SHAPE;
                }
                case WEST: {
                    return DISABLED_WEST_SHAPE;
                }
                case EAST: {
                    return DISABLED_EAST_SHAPE;
                }
            }
            return DISABLED_DEFAULT_SHAPE;
        } else {
            switch (state.get(HopperBlock.FACING)) {
                case DOWN: {
                    return DOWN_SHAPE;
                }
                case NORTH: {
                    return NORTH_SHAPE;
                }
                case SOUTH: {
                    return SOUTH_SHAPE;
                }
                case WEST: {
                    return WEST_SHAPE;
                }
                case EAST: {
                    return EAST_SHAPE;
                }
            }
            return DEFAULT_SHAPE;
        }
    }
}
