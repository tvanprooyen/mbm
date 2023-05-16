package com.tylervp.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.tylervp.block.MBMBlocks;

import net.minecraft.block.AnvilBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;

@Mixin(AnvilBlock.class)
public class AnvilBlockMixin {
    
    @Inject(method = "getLandingState(Lnet/minecraft/block/BlockState;)Lnet/minecraft/block/BlockState;", at = @At("HEAD"), cancellable = true)
    private static void getLandingState(BlockState fallingState,  CallbackInfoReturnable<BlockState> ci){
        if (fallingState.isOf(MBMBlocks.HARDENED_ANVIL)) {
            ci.setReturnValue((BlockState)Blocks.ANVIL.getDefaultState().with(AnvilBlock.FACING, fallingState.get(AnvilBlock.FACING)));
        }
    }
}
