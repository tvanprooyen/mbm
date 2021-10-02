package com.tylervp.mixin;

import com.tylervp.block.MBMBlocks;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.DeadBushBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

@Mixin(DeadBushBlock.class)
public class DeadBushMixin {

    @Inject(method = "canPlantOnTop(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;)Z", at = @At("HEAD"), cancellable = true)
    protected void canPlantOnTop(BlockState floor, BlockView world, BlockPos pos, CallbackInfoReturnable<Boolean> ci) {
      Block block = floor.getBlock(); 
      if(block == MBMBlocks.TERRACOTTA_VASE || block == MBMBlocks.BLACK_TERRACOTTA_VASE || block == MBMBlocks.SANDSTONE_VASE || block == MBMBlocks.LIGHT_GRAY_TERRACOTTA_VASE) {
         ci.setReturnValue(true);
      }
    }
}
