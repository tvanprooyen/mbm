package com.tylervp.block;

import com.tylervp.util.VolxelEdit;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

public class SideMushroomBlock extends HorizontalFacingBlock {
    protected static final VoxelShape SHAPE1,SHAPE2,SHAPE3,SHAPE4,SHAPE5,SHAPE6,SHAPE7,SHAPE8,SHAPE9,SHAPE10,SHAPE11,SHAPE12,SHAPE13,SHAPE14;
    public static final IntProperty MUSHROOMSTATE;

    protected SideMushroomBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH).with(MUSHROOMSTATE, 2));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        VoxelShape processShape = VoxelShapes.fullCube();

        switch (state.get(MUSHROOMSTATE)) {
            case 1: processShape = SHAPE1; break;
            case 2: processShape = SHAPE2; break;
            case 3: processShape = SHAPE3; break;
            case 4: processShape = SHAPE4; break;
            case 5: processShape = SHAPE5; break;
            case 6: processShape = SHAPE6; break;
            case 7: processShape = SHAPE7; break;
            case 8: processShape = SHAPE8; break;
            case 9: processShape = SHAPE9; break;
            case 10: processShape = SHAPE10; break;
            case 11: processShape = SHAPE11; break;
            case 12: processShape = SHAPE12; break;
            case 13: processShape = SHAPE13; break;
            case 14: processShape = SHAPE14; break;
            default: processShape = SHAPE1; break;
        }

        switch (state.get(FACING)) {
            case EAST:
                processShape = VolxelEdit.rotateShape(Direction.NORTH, Direction.EAST, processShape);
            break;
            case SOUTH:
                processShape = VolxelEdit.rotateShape(Direction.NORTH, Direction.SOUTH, processShape);
            break;
            case WEST:
                processShape = VolxelEdit.rotateShape(Direction.NORTH, Direction.WEST, processShape);
            break;

            default: break;
        }

         return processShape;
    }

    /* @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        if(!(entity instanceof ItemEntity)){
            world.breakBlock(pos, true);
        }
        super.onSteppedOn(world, pos, state, entity);
    }

    @Override
    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        if(!(entity instanceof ItemEntity)){
            world.breakBlock(pos, true);
        }
        super.onLandedUpon(world, state, pos, entity, fallDistance);
    } */

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        //return getOutlineShape(state, world, pos, context);
        return VoxelShapes.empty();
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return Block.sideCoversSmallSquare(world, pos.offset(state.get(FACING)), state.get(FACING).getOpposite());
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        Direction placementDirection = ctx.getPlayerLookDirection();

        if (placementDirection == Direction.UP || placementDirection == Direction.DOWN) {
            placementDirection = ctx.getHorizontalPlayerFacing();
        }

        return (BlockState)this.getDefaultState().with(FACING, placementDirection).with(MUSHROOMSTATE, ctx.getWorld().getRandom().nextBetween(2, 14));
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState newState, WorldAccess world, BlockPos pos, BlockPos posFrom) {

        if (!state.canPlaceAt(world, pos) || newState.isOf(Blocks.WATER)) {
            return Blocks.AIR.getDefaultState();
        }


        return super.getStateForNeighborUpdate(state, direction, newState, world, pos, posFrom);
    }

    @Override
    protected void appendProperties(Builder<Block, BlockState> builder) {
        builder.add(Properties.HORIZONTAL_FACING, SideMushroomBlock.MUSHROOMSTATE);
    }

    static {
        SHAPE1 = Block.createCuboidShape(5, 0, 0, 11, 1, 6); // x
        SHAPE2 = Block.createCuboidShape(5, 0, 0, 11, 2, 6); // x
        SHAPE3 = Block.createCuboidShape(3, 2, 0, 9, 4, 6); // x
        SHAPE4 = Block.createCuboidShape(8, 5, 0, 14, 7, 6); // x
        SHAPE5 = Block.createCuboidShape(4, 5, 0, 10, 7, 6); // x
        SHAPE6 = VoxelShapes.combine(Block.createCuboidShape(7, 5, 0, 13, 7, 6), Block.createCuboidShape(5, 3, 0, 8, 4, 3), BooleanBiFunction.OR); // x
        SHAPE7 = VoxelShapes.combine(Block.createCuboidShape(5, 4, 0, 11, 6, 6), Block.createCuboidShape(9, 1, 0, 12, 2, 3), BooleanBiFunction.OR); // x
        SHAPE8 = VoxelShapes.combine(Block.createCuboidShape(2, 6, 0, 8, 8, 6), VoxelShapes.combine(Block.createCuboidShape(8, 2, 0, 14, 4, 6), Block.createCuboidShape(12, 5, 0, 15, 6, 3), BooleanBiFunction.OR), BooleanBiFunction.OR); // x
        SHAPE9 = VoxelShapes.combine(Block.createCuboidShape(11, 2, 0, 14, 3, 3), Block.createCuboidShape(2 ,5 ,0 ,8 ,7 ,6), BooleanBiFunction.OR); // x
        SHAPE10 = VoxelShapes.combine(Block.createCuboidShape(9, 6, 0, 12, 7, 3), VoxelShapes.combine(Block.createCuboidShape(2, 1, 0, 5, 2, 3), Block.createCuboidShape(4 ,3 ,0 ,10 ,5 ,6), BooleanBiFunction.OR), BooleanBiFunction.OR); // x
        SHAPE11 = VoxelShapes.combine(Block.createCuboidShape(9, 6, 0, 12, 7, 3), VoxelShapes.combine(Block.createCuboidShape(1, 1, 0, 3, 2, 2), Block.createCuboidShape(4 ,3 ,0 ,10 ,5 ,6), BooleanBiFunction.OR), BooleanBiFunction.OR);
        SHAPE12 = VoxelShapes.combine(Block.createCuboidShape(4, 1, 0, 6, 2, 2), Block.createCuboidShape(9, 5, 0, 12, 6, 3), BooleanBiFunction.OR); // x
        SHAPE13 = VoxelShapes.combine(Block.createCuboidShape(9, 5, 0, 12, 6, 3), VoxelShapes.combine(Block.createCuboidShape(7, 3, 0, 10, 4, 3), VoxelShapes.combine(Block.createCuboidShape(6, 6, 0, 8, 7, 2), Block.createCuboidShape(4, 4, 0, 6, 5, 2), BooleanBiFunction.OR), BooleanBiFunction.OR), BooleanBiFunction.OR);
        SHAPE14 = VoxelShapes.combine(Block.createCuboidShape(9, 2, 0, 12, 3, 3), VoxelShapes.combine(Block.createCuboidShape(5, 3, 0, 8, 4, 3), VoxelShapes.combine(Block.createCuboidShape(6, 1, 0, 8, 2, 2), Block.createCuboidShape(11, 4, 0, 13, 5, 2), BooleanBiFunction.OR), BooleanBiFunction.OR), BooleanBiFunction.OR);


        MUSHROOMSTATE = IntProperty.of("mushroomstate", 1, 14);
    }
}

