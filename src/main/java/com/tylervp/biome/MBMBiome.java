package com.tylervp.biome;

import com.tylervp.moreblocksmod;
import com.tylervp.block.MBMBlocks;

import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.DecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.EmeraldOreFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

public class MBMBiome {
    public static ConfiguredFeature<?, ?> RUBY_ORE_OVERWORLD = Feature.EMERALD_ORE.configure(new EmeraldOreFeatureConfig(Blocks.STONE.getDefaultState(), MBMBlocks.RUBY_ORE.getDefaultState())).decorate(Decorator.EMERALD_ORE.configure(DecoratorConfig.DEFAULT));


    public static final void biome(){
        String ModName = moreblocksmod.ModName;
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(ModName, "ruby_ore_overworld"), RUBY_ORE_OVERWORLD);
    }
}
