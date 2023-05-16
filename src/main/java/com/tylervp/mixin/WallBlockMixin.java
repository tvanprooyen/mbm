package com.tylervp.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.PaneBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.Direction;

import com.tylervp.block.HorizontalPaneBlock;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WallBlock.class)
public class WallBlockMixin {

    @Inject(method = "shouldConnectTo(Lnet/minecraft/block/BlockState;ZLnet/minecraft/util/math/Direction;)Z", at = @At("HEAD"), cancellable = true)
    void shouldConnectTo(BlockState state, boolean faceFullSquare, Direction side, CallbackInfoReturnable<Boolean> ci) {
        Block block5 = state.getBlock();
        boolean boolean6 = block5 instanceof FenceGateBlock && FenceGateBlock.canWallConnect(state, side);
        ci.setReturnValue(state.isIn(BlockTags.WALLS) || (!Block.cannotConnect(state) && faceFullSquare) || block5 instanceof PaneBlock || boolean6 || block5 instanceof HorizontalPaneBlock);
    }
}