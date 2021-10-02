package com.tylervp.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;

public class Apple extends Block {
    public static final DirectionProperty FACE;
    public static final BooleanProperty PLAYERPLACED;

    public Apple(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)this.getDefaultState().with(FACE, Direction.NORTH).with(PLAYERPLACED, false));
    }

    @Override
   public PistonBehavior getPistonBehavior(BlockState state) {
      return PistonBehavior.DESTROY;
   }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if(!state.get(PLAYERPLACED)){
            BlockPos.Mutable mutable = pos.mutableCopy();
            BlockPos futurePos = mutable.set(pos, state.get(FACE));

            if(world.getBlockState(futurePos).isAir() || world.getBlockState(futurePos).isOf(Blocks.PISTON_HEAD)){
                return Blocks.AIR.getDefaultState();
            }
        }
        
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
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

        /* if(face == Direction.UP || face == Direction.DOWN) {
            face = face.getOpposite();
        } */
        
        return (BlockState)this.getDefaultState().with(FACE, face).with(PLAYERPLACED, true);
    }

    @Override
    protected void appendProperties(Builder<Block, BlockState> builder) {
        builder.add(FACE, PLAYERPLACED);
    }
    
    static {
        FACE = Properties.FACING;
        PLAYERPLACED = BooleanProperty.of("playerplaced");
    }
}
