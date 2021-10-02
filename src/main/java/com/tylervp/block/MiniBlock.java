package com.tylervp.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class MiniBlock extends Block {
    public static final DirectionProperty FACE;

    public MiniBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)this.getDefaultState().with(FACE, Direction.DOWN));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction face = state.get(FACE);

        switch (face) {
            case UP:
                return Block.createCuboidShape(4, 8, 4, 12, 16, 12);
            case DOWN:
                return Block.createCuboidShape(4, 0, 4, 12, 8, 12);
            case NORTH:
                return Block.createCuboidShape(4, 4, 0, 12, 12, 8);
            case EAST:
                return Block.createCuboidShape(8, 4, 4, 16, 12, 12);
            case SOUTH:
                return Block.createCuboidShape(4, 4, 8, 12, 12, 16);
            case WEST:
                return Block.createCuboidShape(0, 4, 4, 8, 12, 12);
        }

        return super.getOutlineShape(state, world, pos, context);
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
        FACE = Properties.FACING;
    }
}
