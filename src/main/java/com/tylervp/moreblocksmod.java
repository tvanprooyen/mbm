package com.tylervp;

import net.fabricmc.api.ModInitializer;

import com.tylervp.biome.MBMBiome;
import com.tylervp.block.FlammableRegistry;
import com.tylervp.block.ItemFuelRegistry;
import com.tylervp.block.MBMBlocks;
import com.tylervp.block.MBMBlocksAfterItem;
import com.tylervp.command.MBMCommands;
import com.tylervp.entity.MBMEntities;
import com.tylervp.gamerule.MBMGameRule;
import com.tylervp.item.CompostingRegistry;
import com.tylervp.item.MBMItems;
import com.tylervp.particle.MBMParticle;

public class moreblocksmod implements ModInitializer {
        public static final String ModName = "moreblocksmod";

        @Override
        public void onInitialize() {

                System.out.println("[More Blocks Mod] MBM Particle");
                MBMParticle.register();

                System.out.println("[More Blocks Mod] MBM Game Rules");
                MBMGameRule.register();

                System.out.println("[More Blocks Mod] MBM Blocks");
                MBMBlocks.blocks();

                System.out.println("[More Blocks Mod] MBM Items");
                MBMItems.items();

                System.out.println("[More Blocks Mod] MBM Blocks (Item Dependent)");
                MBMBlocksAfterItem.register();

                System.out.println("[More Blocks Mod] MBM Item Groups");
                MBMItems.registerItemGroup();

                System.out.println("[More Blocks Mod] MBM Biome");
                MBMBiome.biome();

                System.out.println("[More Blocks Mod] MBM Compost");
                CompostingRegistry.register();

                System.out.println("[More Blocks Mod] MBM Falmmable");
                FlammableRegistry.register();

                System.out.println("[More Blocks Mod] MBM Item Fuel");
                ItemFuelRegistry.register();

                System.out.println("[More Blocks Mod] MBM Commands");
                MBMCommands.register();

                System.out.println("[More Blocks Mod] MBM Entities");
                MBMEntities.register();
        }
}