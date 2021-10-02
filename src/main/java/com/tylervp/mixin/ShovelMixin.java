package com.tylervp.mixin;

import com.google.common.collect.ImmutableMap;
import com.tylervp.block.BurntBlock;
import com.tylervp.block.BurntPillarBlock;
import com.tylervp.block.BurntSlabBlock;
import com.tylervp.block.BurntStairsBlock;
import com.tylervp.block.MBMBlocks;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.WallTorchBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.ShovelItem;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@Mixin(ShovelItem.class)
public class ShovelMixin {
    
    @Inject(method = "useOnBlock(Lnet/minecraft/item/ItemUsageContext;)Lnet/minecraft/util/ActionResult;", at = @At("HEAD"), cancellable = true)
    void useOnBlock(ItemUsageContext context,  CallbackInfoReturnable<ActionResult> ci){
        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();
        BlockState blockState = world.getBlockState(blockPos);
        PlayerEntity playerEntity = context.getPlayer();

        if(blockState.isOf(MBMBlocks.BURNT_GRASS_BLOCK) || blockState.getBlock().getClass() == BurntBlock.class || blockState.getBlock().getClass() == BurntPillarBlock.class || blockState.getBlock().getClass() == BurntStairsBlock.class || blockState.getBlock().getClass() == BurntSlabBlock.class){
            if(world.getBlockState(blockPos).get(BurntBlock.AGE) < 3) {
                world.playSound(playerEntity, blockPos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0f, 1.0f);
                world.playSound(playerEntity, blockPos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 1.0f, 1.0f);
                world.syncWorldEvent(1501, blockPos, 0);

                if(blockState.getBlock().getClass() == BurntSlabBlock.class){
                    world.setBlockState(blockPos, blockState.with(BurntSlabBlock.AGE, 3).with(BurntSlabBlock.PERSISTENT, false).with(SlabBlock.TYPE, blockState.get(SlabBlock.TYPE)).with(SlabBlock.WATERLOGGED, blockState.get(SlabBlock.WATERLOGGED)));
                } else if(blockState.getBlock().getClass() == BurntStairsBlock.class){
                    world.setBlockState(blockPos, blockState.with(BurntStairsBlock.AGE, 3).with(BurntStairsBlock.PERSISTENT, false).with(StairsBlock.FACING, blockState.get(StairsBlock.FACING)).with(StairsBlock.HALF, blockState.get(StairsBlock.HALF)).with(StairsBlock.WATERLOGGED, blockState.get(StairsBlock.WATERLOGGED)));
                } else if(blockState.getBlock().getClass() == BurntPillarBlock.class){
                    world.setBlockState(blockPos, blockState.with(Properties.AXIS, blockState.get(Properties.AXIS)).with(BurntPillarBlock.AGE, 3).with(BurntPillarBlock.PERSISTENT, false));
                } else {
                    world.setBlockState(blockPos, blockState.with(BurntBlock.AGE, 3).with(BurntBlock.PERSISTENT, false));
                }

                ci.setReturnValue(ActionResult.success(world.isClient));
            } else if(world.getBlockState(blockPos).get(BurntBlock.AGE) == 3 && blockState.isOf(MBMBlocks.BURNT_GRASS_BLOCK)) {
                world.playSound(playerEntity, blockPos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0f, 1.0f);
                world.setBlockState(blockPos, Blocks.DIRT.getDefaultState());
                ci.setReturnValue(ActionResult.success(world.isClient));
            } else if(world.getBlockState(blockPos).get(BurntBlock.AGE) == 3 && blockState.isOf(MBMBlocks.BURNT_GRASS_BLOCK_SLAB)) {
                world.playSound(playerEntity, blockPos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0f, 1.0f);
                world.setBlockState(blockPos, MBMBlocks.DIRT_SLAB.getDefaultState().with(SlabBlock.TYPE, world.getBlockState(blockPos).get(SlabBlock.TYPE)).with(SlabBlock.WATERLOGGED, world.getBlockState(blockPos).get(SlabBlock.WATERLOGGED)));
                ci.setReturnValue(ActionResult.success(world.isClient));
            }
        }

        if(blockState.isOf(Blocks.TORCH)) {
            world.playSound(playerEntity, blockPos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 1.0f, 1.0f);
            world.setBlockState(blockPos, MBMBlocks.UNLIT_TORCH.getDefaultState(), 11);
            ci.setReturnValue(ActionResult.success(world.isClient));
        }

        if(blockState.isOf(Blocks.WALL_TORCH)) {
            world.playSound(playerEntity, blockPos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 1.0f, 1.0f);
            world.setBlockState(blockPos, MBMBlocks.UNLIT_WALL_TORCH.getDefaultState().with(WallTorchBlock.FACING, world.getBlockState(blockPos).get(WallTorchBlock.FACING)), 11);
            ci.setReturnValue(ActionResult.success(world.isClient));
        }

        if(blockState.isOf(Blocks.SOUL_TORCH)) {
            world.playSound(playerEntity, blockPos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 1.0f, 1.0f);
            world.setBlockState(blockPos, MBMBlocks.UNLIT_TORCH.getDefaultState(), 11);
            ci.setReturnValue(ActionResult.success(world.isClient));
        }

        if(blockState.isOf(Blocks.SOUL_WALL_TORCH)) {
            world.playSound(playerEntity, blockPos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 1.0f, 1.0f);
            world.setBlockState(blockPos, MBMBlocks.UNLIT_WALL_TORCH.getDefaultState().with(WallTorchBlock.FACING, world.getBlockState(blockPos).get(WallTorchBlock.FACING)), 11);
            ci.setReturnValue(ActionResult.success(world.isClient));
        }
        
        Block grassblockState = new ImmutableMap.Builder<Block, Block>().put(MBMBlocks.DEAD_GRASS_BLOCK, Blocks.DIRT_PATH).build().get(blockState.getBlock());

        if (grassblockState != null && world.getBlockState(blockPos.up()).isAir()) {
            world.playSound(playerEntity, blockPos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0f, 1.0f);

            world.setBlockState(blockPos, grassblockState.getDefaultState(), 11);

            ci.setReturnValue(ActionResult.success(world.isClient));
        }
    }
}
