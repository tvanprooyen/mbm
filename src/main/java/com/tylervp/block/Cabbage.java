package com.tylervp.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class Cabbage extends Block {
    public static final DirectionProperty FACE;
    public static final BooleanProperty PLAYERPLACED;
    public static final VoxelShape NORMALSHAPE;

    public Cabbage(AbstractBlock.Settings settings) {
        super(settings.dynamicBounds().offset((OffsetType.XZ)));
        this.setDefaultState((BlockState)this.getDefaultState()/* .with(FACE, Direction.NORTH).with(PLAYERPLACED, false) */);
    }

    @Override
   public PistonBehavior getPistonBehavior(BlockState state) {
      return PistonBehavior.DESTROY;
   }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {

        Vec3d modelOffset = state.getModelOffset(world, pos);

        return NORMALSHAPE.offset(modelOffset.x, modelOffset.y, modelOffset.z);
    }

    static {
        FACE = Properties.FACING;
        PLAYERPLACED = BooleanProperty.of("playerplaced");
        NORMALSHAPE = VoxelShapes.union(Block.createCuboidShape(0, 0, 0, 16, 2, 16), Block.createCuboidShape(2, 0,  2, 14, 8,  14));
    }
}
