package com.tylervp.item;

import com.tylervp.block.MBMBlocks;

import net.fabricmc.fabric.impl.content.registry.CompostingChanceRegistryImpl;

public class CompostingRegistry {
    public static final CompostingChanceRegistryImpl compostRegistry = new CompostingChanceRegistryImpl();
    
    public static void register(){
        compostRegistry.add(MBMItems.RICE_SEEDS, 0.3f);
        compostRegistry.add(MBMItems.RICE_LEAF_SHEATH, 0.65f);
        compostRegistry.add(MBMBlocks.RICE_STRAW_BALE.asItem(), 0.85f);
    }
}
