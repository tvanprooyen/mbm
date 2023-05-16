package com.tylervp.block;

import java.util.Map;

import org.jetbrains.annotations.Nullable;

import com.google.common.collect.Maps;
import com.google.common.collect.ImmutableMap;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Property;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

public class UnlitWallTorch extends Block {
    public static final DirectionProperty FACING;
    protected static final float field_31285 = 2.5f;
    private static final Map<Direction, VoxelShape> BOUNDING_SHAPES;
    
    protected UnlitWallTorch(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)((BlockState)this.stateManager.getDefaultState()).with(FACING, Direction.NORTH));
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack playerItem = player.getStackInHand(hand);

        if (playerItem.getItem() == Items.FLINT_AND_STEEL || playerItem.getItem() == Items.TORCH){
            world.setBlockState(pos, Blocks.WALL_TORCH.getDefaultState().with(FACING, state.get(FACING)));
            return ActionResult.success(world.isClient);
        }

        if (playerItem.getItem() == Items.SOUL_TORCH){
            world.setBlockState(pos, Blocks.SOUL_WALL_TORCH.getDefaultState());
            return ActionResult.success(world.isClient);
        }

        return super.onUse(state, world, pos, player, hand, hit);
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        super.onEntityCollision(state, world, pos, entity);

        if(entity instanceof SmallFireballEntity || entity instanceof FireballEntity || entity.isOnFire()) {
            world.setBlockState(pos, Blocks.WALL_TORCH.getDefaultState().with(FACING, state.get(FACING)));
        }
    }
    
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return getBoundingShape(state);
    }
    
    public static VoxelShape getBoundingShape(BlockState state) {
        return UnlitWallTorch.BOUNDING_SHAPES.get(state.get(UnlitWallTorch.FACING));
    }
    
    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        Direction direction5 = state.<Direction>get((Property<Direction>)UnlitWallTorch.FACING);
        BlockPos blockPos6 = pos.offset(direction5.getOpposite());
        BlockState blockState7 = world.getBlockState(blockPos6);
        return blockState7.isSideSolidFullSquare(world, blockPos6, direction5);
    }
    
    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState blockState3 = this.getDefaultState();
        WorldView worldView4 = ctx.getWorld();
        BlockPos blockPos5 = ctx.getBlockPos();
        Direction[] method_7718 = ctx.getPlacementDirections();
        //Direction[] lvs = method_7718 = ctx.getPlacementDirections();

        for (final Direction lv4 : method_7718) {
            if (lv4.getAxis().isHorizontal()) {
                Direction direction11 = lv4.getOpposite();
                blockState3 = (BlockState)blockState3.with(FACING, direction11);
                if (blockState3.canPlaceAt(worldView4, blockPos5)) {
                    return blockState3;
                }
            }
        }

        return null;
    }
    
    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (direction.getOpposite() == state.<Direction>get((Property<Direction>)UnlitWallTorch.FACING) && !state.canPlaceAt(world, pos)) {
            return Blocks.AIR.getDefaultState();
        }
        return state;
    }
    
    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
		return (BlockState)state.with(FACING, rotation.rotate((Direction)state.get(FACING)));
    }
    
    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
		return state.rotate(mirror.getRotation((Direction)state.get(FACING)));
    }
    
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(UnlitWallTorch.FACING);
    }
    
    static {
        FACING = HorizontalFacingBlock.FACING;
		BOUNDING_SHAPES = Maps.newEnumMap(ImmutableMap.of(Direction.NORTH, Block.createCuboidShape(5.5D, 3.0D, 11.0D, 10.5D, 13.0D, 16.0D), Direction.SOUTH, Block.createCuboidShape(5.5D, 3.0D, 0.0D, 10.5D, 13.0D, 5.0D), Direction.WEST, Block.createCuboidShape(11.0D, 3.0D, 5.5D, 16.0D, 13.0D, 10.5D), Direction.EAST, Block.createCuboidShape(0.0D, 3.0D, 5.5D, 5.0D, 13.0D, 10.5D)));
    }
}
