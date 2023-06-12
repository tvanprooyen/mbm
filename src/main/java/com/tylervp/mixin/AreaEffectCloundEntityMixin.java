package com.tylervp.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.AreaEffectCloudEntity;
/* import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Ownable;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World; */

@Mixin(AreaEffectCloudEntity.class)
public class AreaEffectCloundEntityMixin {

    @Inject(method = "tick()V", at = @At("HEAD"), cancellable = true)
    private void tick(CallbackInfo ci) {

    }
}
