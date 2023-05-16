/* package com.tylervp.mixin;

import com.tylervp.biome.MBMBiome;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;

@Mixin(DefaultBiomeFeatures.class)
public class DefaultBiomeFeaturesMixin {

    @Inject(method = "composeEndSpawnSettings(Lnet/minecraft/world/biome/GenerationSettings$Builder;)Lnet/minecraft/world/biome/Biome;", at = @At("HEAD"))
    private static void addDefaultOres(GenerationSettings.Builder builder, CallbackInfo ci) {
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, MBMBiome.RUBY_ORE_OVERWORLD);
    }
}
 */