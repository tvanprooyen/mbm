/* package com.tylervp.mixin;

import com.tylervp.block.MBMBlocks;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.piston.PistonHandler;

@Mixin(PistonHandler.class)
public class PistonHandlerMixin {
    
    @Inject(method = "isBlockSticky(Lnet/minecraft/block/Block;)Z;", at = @At("HEAD"), cancellable = true)
    void isBlockSticky(Block block, CallbackInfoReturnable<Boolean> ci){
        ci.setReturnValue(block == Blocks.SLIME_BLOCK || block == Blocks.HONEY_BLOCK || block == MBMBlocks.SPIKE);
    }


    /* @Inject(method = "isAdjacentBlockStuck(Lnet/minecraft/block/Block;Lnet/minecraft/block/Block;)Z;", at = @At("HEAD"), cancellable = true)
    void isAdjacentBlockStuck(Block block1, Block block2, CallbackInfoReturnable<Boolean> ci){
        ci.setReturnValue((block1 != Blocks.HONEY_BLOCK || block2 != Blocks.SLIME_BLOCK) && (block1 != Blocks.SLIME_BLOCK || block2 != Blocks.HONEY_BLOCK) && (isBlockSticky(block1) || isBlockSticky(block2)));
    } */


    /* 
    private static boolean isAdjacentBlockStuck(Block block1, Block block2) {
        return (block1 != Blocks.HONEY_BLOCK || block2 != Blocks.SLIME_BLOCK) && (block1 != Blocks.SLIME_BLOCK || block2 != Blocks.HONEY_BLOCK) && (isBlockSticky(block1) || isBlockSticky(block2));
    }
    
}*/
