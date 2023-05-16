package com.tylervp.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.MushroomPlantBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.registry.RegistryKey;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.gen.feature.ConfiguredFeature;

public class Mushroom extends MushroomPlantBlock {
    public static final VoxelShape NORMALSHAPE;
    public static final  BooleanProperty LIT;

    public Mushroom(Settings settings, RegistryKey<ConfiguredFeature<?, ?>> feature) {
        super(settings.offset(OffsetType.XZ), feature);
        this.setDefaultState(this.stateManager.getDefaultState().with(Mushroom.LIT, true));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Vec3d modelOffset = state.getModelOffset(world, pos);

        return NORMALSHAPE.offset(modelOffset.x, modelOffset.y, modelOffset.z);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.empty();
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(Mushroom.LIT);
    }

    static {
        NORMALSHAPE = VoxelShapes.combine(Block.createCuboidShape(5, 4, 5, 11, 8, 11), Block.createCuboidShape(7, 0, 7, 9, 4, 9), BooleanBiFunction.OR);
        LIT = BooleanProperty.of("lit");
    }
}
