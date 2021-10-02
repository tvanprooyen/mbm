package com.tylervp.item;

import com.tylervp.block.MBMBlocks;

import net.fabricmc.fabric.impl.content.registry.CompostingChanceRegistryImpl;

public class CompostingRegistry {
    public static final CompostingChanceRegistryImpl compostRegistry = new CompostingChanceRegistryImpl();
    
    public static void register(){
        compostRegistry.add(MBMItems.RICE_SEEDS, 0.3f);
        compostRegistry.add(MBMItems.RICE_LEAF_SHEATH, 0.65f);
        compostRegistry.add(MBMBlocks.RICE_STRAW_BALE.asItem(), 0.85f);
        compostRegistry.add(MBMBlocks.GREEN_GRAPES.asItem(), 0.6f);
        compostRegistry.add(MBMBlocks.PURPLE_GRAPES.asItem(), 0.6f);
        compostRegistry.add(MBMItems.GRAPE_SEEDS, 0.3f);
        compostRegistry.add(MBMBlocks.GRAPE_LEAVES.asItem(), 0.4f);
        compostRegistry.add(MBMBlocks.GRAPE_LEAVES_HANGING.asItem(), 0.3f);
    }
}
