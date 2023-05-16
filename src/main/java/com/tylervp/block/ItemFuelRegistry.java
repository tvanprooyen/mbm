package com.tylervp.block;

import net.fabricmc.fabric.api.registry.FuelRegistry;

public class ItemFuelRegistry {
    
    public static void register(){
        FuelRegistry.INSTANCE.add(MBMBlocks.BURNT_ACACIA_LOG, 200);
        FuelRegistry.INSTANCE.add(MBMBlocks.BURNT_BIRCH_LOG, 200);
        FuelRegistry.INSTANCE.add(MBMBlocks.BURNT_DARK_OAK_LOG, 200);
        FuelRegistry.INSTANCE.add(MBMBlocks.BURNT_JUNGLE_LOG, 200);
        FuelRegistry.INSTANCE.add(MBMBlocks.BURNT_OAK_LOG, 200);
        FuelRegistry.INSTANCE.add(MBMBlocks.BURNT_SPRUCE_LOG, 200);
        FuelRegistry.INSTANCE.add(MBMBlocks.BURNT_PLANKS, 200);
        FuelRegistry.INSTANCE.add(MBMBlocks.BURNT_PLANKS_SLAB, 100);
        FuelRegistry.INSTANCE.add(MBMBlocks.BURNT_PLANKS_STAIRS, 200);
        FuelRegistry.INSTANCE.add(MBMBlocks.STICK_BUNDLE, 900);
    }
}
