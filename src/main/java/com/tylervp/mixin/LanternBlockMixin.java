package com.tylervp.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LanternBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

import com.tylervp.block.MBMBlocks;

import org.spongepowered.asm.mixin.Mixin;

@Mixin(LanternBlock.class)
public abstract class LanternBlockMixin extends Block {

    public LanternBlockMixin(AbstractBlock.Settings settings) {
        super(settings);
    }

    
    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        Direction attachedDirection = state.<Boolean>get(LanternBlock.HANGING) ? Direction.DOWN : Direction.UP;

        Direction direction5 = attachedDirection.getOpposite();
        return (Block.sideCoversSmallSquare(world, pos.offset(direction5), direction5.getOpposite()) || (world.getBlockState(pos.up()).isOf(MBMBlocks.THIN_OAK_LOG) || world.getBlockState(pos.up()).isOf(MBMBlocks.THIN_ACACIA_LOG)) || world.getBlockState(pos.up()).isOf(MBMBlocks.THIN_BIRCH_LOG) || world.getBlockState(pos.up()).isOf(MBMBlocks.THIN_DARK_OAK_LOG) || world.getBlockState(pos.up()).isOf(MBMBlocks.THIN_JUNGLE_LOG) || world.getBlockState(pos.up()).isOf(MBMBlocks.THIN_SPRUCE_LOG) || world.getBlockState(pos.up()).isOf(MBMBlocks.THIN_STRIPPED_ACACIA_LOG) || world.getBlockState(pos.up()).isOf(MBMBlocks.THIN_STRIPPED_BIRCH_LOG)  || world.getBlockState(pos.up()).isOf(MBMBlocks.THIN_STRIPPED_DARK_OAK_LOG) || world.getBlockState(pos.up()).isOf(MBMBlocks.THIN_STRIPPED_JUNGLE_LOG) || world.getBlockState(pos.up()).isOf(MBMBlocks.THIN_STRIPPED_OAK_LOG) || world.getBlockState(pos.up()).isOf(MBMBlocks.THIN_STRIPPED_SPRUCE_LOG) || world.getBlockState(pos.up()).isOf(MBMBlocks.ROPE)  || world.getBlockState(pos.up()).isOf(MBMBlocks.ROPEMID));
    }

    
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        FluidState fluidState3 = ctx.getWorld().getFluidState(ctx.getBlockPos());
        for (final Direction lv2 : ctx.getPlacementDirections()) {
            if (lv2.getAxis() == Direction.Axis.Y) {
                BlockState blockState8 = (BlockState)this.getDefaultState().with(LanternBlock.HANGING, lv2 == Direction.UP);
                if((ctx.getWorld().getBlockState(ctx.getBlockPos().up()).isOf(MBMBlocks.ROPEMID) || ctx.getWorld().getBlockState(ctx.getBlockPos().up()).isOf(MBMBlocks.ROPE)) && lv2 == Direction.UP){
                    ctx.getWorld().setBlockState(ctx.getBlockPos().up(), MBMBlocks.ROPEMID.getDefaultState());
                    blockState8 = (BlockState)MBMBlocks.LANTERN_ROPE.getDefaultState().with(LanternBlock.HANGING, lv2 == Direction.UP);
                }
                if (blockState8.canPlaceAt(ctx.getWorld(), ctx.getBlockPos())) {
                    return (BlockState)blockState8.with(LanternBlock.HANGING, fluidState3.getFluid() == Fluids.WATER);
                }
            }
        }
        return null;
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if(world.getBlockState(pos.up()).isOf(MBMBlocks.ROPEMID) || world.getBlockState(pos.up()).isOf(MBMBlocks.ROPE)){
            world.setBlockState(pos.up(), MBMBlocks.ROPE.getDefaultState());
        }
        super.onBreak(world, pos, state, player);
    }

    
    /* protected static Direction attachedDirection(BlockState state) {
        return state.<Boolean>get(LanternBlock.HANGING) ? Direction.DOWN : Direction.UP;
    } */
}