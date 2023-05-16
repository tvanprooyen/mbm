package com.tylervp.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Material;

/**
 * This is for item dependent blocks, basically blocks that require the full regisration of items
 */
public class MBMBlocksAfterItem {
    
    public static final AutoCraftBlock CRAFTBLOCK = new AutoCraftBlock(FabricBlockSettings.of(Material.STONE).requiresTool().strength(3.5f));

    public static void register() {
        MBMBlocks.registerBlock("constructor", CRAFTBLOCK);

        System.out.println("[More Blocks Mod] Constructor Default Items");
        AutoCraftBlock.registerDefaultItems();
        System.out.println("[More Blocks Mod] Constructor Item List");
        AutoCraftBlock.registerItemList();
    }
}
