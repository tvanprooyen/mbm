package com.tylervp.biome;

import com.tylervp.moreblocksmod;
import com.tylervp.block.MBMBlocks;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.heightprovider.UniformHeightProvider;

public class MBMBiome {
    public static ConfiguredFeature<?, ?> RUBY_ORE_OVERWORLD = Feature.ORE
    .configure(new OreFeatureConfig(
      OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,
      MBMBlocks.RUBY_ORE.getDefaultState(),
      9)) // Vein size
    .range(new RangeDecoratorConfig(
      UniformHeightProvider.create(YOffset.aboveBottom(0), YOffset.fixed(40)))) //Allowed Hight
    .spreadHorizontally()
    .repeat(2);

    


    public static final void biome(){
        String ModName = moreblocksmod.ModName;
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(ModName, "ruby_ore_overworld"), RUBY_ORE_OVERWORLD);
    }
}
