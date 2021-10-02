package com.tylervp.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class GrapeLeavesHanging extends Block {

    public GrapeLeavesHanging(AbstractBlock.Settings settings) {
        super(settings.noCollision().nonOpaque());
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {

        /* if(state.isOf(MBMBlocks.GRAPE_LEAVES)) {
            if(!state.get(GrapeLeaves.PLAYERPLACED)){
                BlockPos.Mutable mutable = pos.mutableCopy();
                BlockPos futurePos = mutable.set(pos, state.get(GrapeLeaves.GROWNDIRECTION));
                BlockState futureState = world.getBlockState(futurePos);

                if(futureState.isOf(MBMBlocks.GRAPE_LEAVES)) {
                    int currentgrow = futureState.get(GrapeLeaves.CURRENTGROW);
                    
                    if(currentgrow > 0) {
                        GrapeLeaves.SetState(GrapeLeaves.StateOrdinals.CURRENTGROW, currentgrow - 1, world, futurePos, futureState);
                    }
                }
            }
        }
         */


        BlockPos futurePos = pos.up();
        BlockState futureState = world.getBlockState(futurePos);

        if(futureState.isOf(MBMBlocks.GRAPE_LEAVES)) {
            int currentgrow = futureState.get(GrapeLeaves.CURRENTGROW);
            
            if(currentgrow > 0) {
                GrapeLeaves.SetState(GrapeLeaves.StateOrdinals.CURRENTGROW, currentgrow - 1, world, futurePos, futureState);
            }
        }

        super.onBreak(world, pos, state, player);
    }

    @Override
    public PistonBehavior getPistonBehavior(BlockState state) {
        return PistonBehavior.DESTROY;
    }

    @Override
    public VoxelShape getCameraCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.empty();
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        
        return Block.createCuboidShape(0, 5, 0, 16, 16, 16);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if(world.getBlockState(pos.up()).isAir()|| world.getBlockState(pos.up()).isOf(MBMBlocks.COPPER_WIRE) || world.getBlockState(pos.up()).isOf(Blocks.PISTON_HEAD)) {
            return Blocks.AIR.getDefaultState();
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        World world = ctx.getWorld();
        BlockPos pos = ctx.getBlockPos();
        BlockState stateUp = world.getBlockState(pos.up());

        if(!stateUp.isAir() || !stateUp.isOf(MBMBlocks.COPPER_WIRE)) {
            return super.getPlacementState(ctx);
        }
        return Blocks.AIR.getDefaultState();
    }
}
