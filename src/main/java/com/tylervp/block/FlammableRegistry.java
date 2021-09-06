package com.tylervp.block;

import net.fabricmc.fabric.impl.content.registry.FlammableBlockRegistryImpl;
import net.minecraft.block.Blocks;

public class FlammableRegistry {
    
    public static void register(){
        FlammableBlockRegistryImpl.getInstance(Blocks.FIRE).add(MBMBlocks.BURNT_ACACIA_LOG, 5, 5);
        FlammableBlockRegistryImpl.getInstance(Blocks.FIRE).add(MBMBlocks.BURNT_BIRCH_LOG, 5, 5);
        FlammableBlockRegistryImpl.getInstance(Blocks.FIRE).add(MBMBlocks.BURNT_DARK_OAK_LOG, 5, 5);
        FlammableBlockRegistryImpl.getInstance(Blocks.FIRE).add(MBMBlocks.BURNT_JUNGLE_LOG, 5, 5);
        FlammableBlockRegistryImpl.getInstance(Blocks.FIRE).add(MBMBlocks.BURNT_OAK_LOG, 5, 5);
        FlammableBlockRegistryImpl.getInstance(Blocks.FIRE).add(MBMBlocks.BURNT_SPRUCE_LOG, 5, 5);
    }
}
