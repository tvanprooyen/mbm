package com.tylervp;

import net.fabricmc.api.ModInitializer;

import com.tylervp.block.MBMBlocks;
import com.tylervp.item.MBMItems;

public class moreblocksmod implements ModInitializer {   
        private static final String ModName = "moreblocksmod";

         @Override
        public void onInitialize() {

                MBMBlocks.blocks(ModName);

                MBMItems.items(ModName);
        }    
}