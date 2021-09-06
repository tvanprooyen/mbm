package com.tylervp.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PaneBlock;
import net.minecraft.tag.BlockTags;

import com.tylervp.block.HorizontalPaneBlock;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PaneBlock.class)
public class PaneBlockMixin {

    @Inject(method = "connectsTo(Lnet/minecraft/block/BlockState;Z)Z", at = @At("HEAD"), cancellable = true)
    void connectsTo(BlockState state, boolean boolean3, CallbackInfoReturnable<Boolean> ci) {
        Block block4 = state.getBlock();
        ci.setReturnValue((!Block.cannotConnect(state) && boolean3) || block4 instanceof PaneBlock || block4 instanceof HorizontalPaneBlock || state.isIn(BlockTags.WALLS));
    }
}