package com.tylervp;

import net.fabricmc.api.ModInitializer;

import com.tylervp.biome.MBMBiome;
import com.tylervp.block.FlammableRegistry;
import com.tylervp.block.ItemFuelRegistry;
import com.tylervp.block.MBMBlocks;
import com.tylervp.item.CompostingRegistry;
import com.tylervp.item.MBMItems;

public class moreblocksmod implements ModInitializer {   
        public static final String ModName = "moreblocksmod";

         @Override
        public void onInitialize() {

                MBMBlocks.blocks();

                MBMItems.items();

                MBMBiome.biome();

                CompostingRegistry.register();
                
                FlammableRegistry.register();

                ItemFuelRegistry.register();
        }    
}