package com.tylervp.biome;

import com.tylervp.moreblocksmod;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;

public class MBMBiome {

    public static final RegistryKey<PlacedFeature> RUBY_ORE_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(moreblocksmod.ModName,"ore_ruby"));

    public static final void biome(){

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, RUBY_ORE_KEY);
    }
}