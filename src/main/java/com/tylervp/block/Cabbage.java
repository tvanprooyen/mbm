package com.tylervp.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class Cabbage extends Block {
    /* public static final DirectionProperty FACE;
    public static final BooleanProperty PLAYERPLACED; */

    public Cabbage(AbstractBlock.Settings settings) {
        super(settings.dynamicBounds());
        this.setDefaultState((BlockState)this.getDefaultState()/* .with(FACE, Direction.NORTH).with(PLAYERPLACED, false) */);
    }
    
    @Override
    public AbstractBlock.OffsetType getOffsetType() {
        return AbstractBlock.OffsetType.XZ;
    }

    @Override
    public float getMaxModelOffset() {
        return 0.1f;
    }

    @Override
   public PistonBehavior getPistonBehavior(BlockState state) {
      return PistonBehavior.DESTROY;
   }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {

        return VoxelShapes.union(Block.createCuboidShape(0, 0, 0, 16, 2, 16), Block.createCuboidShape(2, 0,  2, 14, 8,  14));
    }
}
