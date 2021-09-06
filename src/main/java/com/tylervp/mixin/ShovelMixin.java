package com.tylervp.mixin;

import com.google.common.collect.ImmutableMap;
import com.tylervp.block.MBMBlocks;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.ShovelItem;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
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
        
        Block grassblockState = new ImmutableMap.Builder<Block, Block>().put(MBMBlocks.DEAD_GRASS_BLOCK, Blocks.DIRT_PATH).build().get(blockState.getBlock());

        if (grassblockState != null && world.getBlockState(blockPos.up()).isAir()) {
            world.playSound(playerEntity, blockPos, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0f, 1.0f);

            world.setBlockState(blockPos, grassblockState.getDefaultState(), 11);

            ci.setReturnValue(ActionResult.success(world.isClient));
        }
    }
}
