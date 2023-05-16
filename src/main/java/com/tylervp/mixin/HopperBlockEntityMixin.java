package com.tylervp.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.tylervp.block.MBMBlocks;

import net.minecraft.block.entity.HopperBlockEntity;

@Mixin(HopperBlockEntity.class)
public class HopperBlockEntityMixin {
    @Shadow
    public int transferCooldown;

    @Inject(method = "setTransferCooldown(I)V", at = @At("HEAD"), cancellable = true)
    private void setTransferCooldown(int transferCooldown, CallbackInfo ci) {
        int hopperSpeed = MBMBlocks.CFR.getHopperSpeed();

        if(hopperSpeed < 0) {
            hopperSpeed = 0;
        }

        if(transferCooldown > 0) {
            if(hopperSpeed > 8) {
                hopperSpeed -= 8;
                transferCooldown += Math.abs(hopperSpeed);
            } else if(hopperSpeed < 8) {
                hopperSpeed -= 8;
                transferCooldown -= Math.abs(hopperSpeed);
            }
        }

        this.transferCooldown = transferCooldown;
        ci.cancel();
    }
}