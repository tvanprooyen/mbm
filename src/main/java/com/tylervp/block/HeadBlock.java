package com.tylervp.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class HeadBlock extends Block {

    public static final DirectionProperty FACE;

    public HeadBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState());
    }

    @Override
   public PistonBehavior getPistonBehavior(BlockState state) {
      return PistonBehavior.DESTROY;
   }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction face = state.get(FACE);

        VoxelShape BuildShape = VoxelShapes.empty();

        switch (face) {
            case UP:
                BuildShape = Block.createCuboidShape(4, 8, 4, 12, 16, 12);
            case DOWN:
                BuildShape = Block.createCuboidShape(4, 0, 4, 12, 8, 12);
            case NORTH:
                BuildShape = Block.createCuboidShape(4, 4, 0, 12, 12, 8);
            case EAST:
                BuildShape = Block.createCuboidShape(8, 4, 4, 16, 12, 12);
            case SOUTH:
                BuildShape = Block.createCuboidShape(4, 4, 8, 12, 12, 16);
            case WEST:
                BuildShape = Block.createCuboidShape(0, 4, 4, 8, 12, 12);
        }

        return BuildShape;
    }


    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        Direction face = ctx.getSide().getOpposite();

        return (BlockState)this.getDefaultState().with(FACE, face);
    }

    @Override
    protected void appendProperties(Builder<Block, BlockState> builder) {
        builder.add(FACE);
    }

    static {
        FACE = DirectionProperty.of("face");
    }
}
