package com.tylervp.item;

import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;

import java.util.ArrayList;

import com.tylervp.moreblocksmod;
import com.tylervp.block.MBMBlocks;

public class MBMItems extends moreblocksmod {

         private static final ItemGroup MBM_BUILDING_BLOCKS = FabricItemGroup.builder(
            new Identifier("moreblocksmod", "general")
        )
        .icon(() -> new ItemStack(MBMBlocks.TERRACOTTA_BRICKS))
        .build();

        private static ArrayList<ItemStack> MBM_BUILDING_BLOCKS_COLLECTION = new ArrayList<ItemStack>();

        private static final ItemGroup MBM_TOOLS = FabricItemGroup.builder(
            new Identifier("moreblocksmod", "tools")
        )
        .icon(() -> new ItemStack(MBMItems.NETHERITE_HAMMER))
        .build();

        private static ArrayList<ItemStack> MBM_TOOLS_COLLECTION = new ArrayList<ItemStack>();

        private static final ItemGroup MBM_MISC = FabricItemGroup.builder(
            new Identifier("moreblocksmod", "misc")
        )
        .icon(() -> new ItemStack(MBMItems.RICE_SEEDS))
        .build();

        private static ArrayList<ItemStack> MBM_MISC_COLLECTION = new ArrayList<ItemStack>();

        private static final ItemGroup MBM_BATA = FabricItemGroup.builder(
            new Identifier("moreblocksmod", "experimental")
        )
        .icon(() -> new ItemStack(MBMBlocks.PIB.asItem()))
        .build();

        private static ArrayList<ItemStack> MBM_BATA_COLLECTION = new ArrayList<ItemStack>();

        public static enum MBM_ITEMGROUPS {
            MBM_BUILDING_BLOCKS,
            MBM_TOOLS,
            MBM_MISC,
            MBM_BATA
        }

    //Custom Tools
    public static final NetheriteHammer NETHERITE_HAMMER = new NetheriteHammer(ToolMaterials.NETHERITE, new FabricItemSettings().fireproof());

    //Rice
    public static final Item RICE_SEEDS = new AliasedBlockItem(MBMBlocks.RICE, new FabricItemSettings());
    public static final Item RICE_LEAF_SHEATH = new Item(new FabricItemSettings());

    public static final Item GRAPE_SEEDS = new AliasedBlockItem(MBMBlocks.GRAPE_SPUR, new FabricItemSettings());


    //Bucket
    public static Item MUD_BUCKET;

    //Bark Fragment
    public static final Item ACACIA_BARK_FRAGMENT = new Item(new FabricItemSettings());
    public static final Item BIRCH_BARK_FRAGMENT = new Item(new FabricItemSettings());
    public static final Item DARK_OAK_BARK_FRAGMENT = new Item(new FabricItemSettings());
    public static final Item JUNGLE_BARK_FRAGMENT = new Item(new FabricItemSettings());
    public static final Item OAK_BARK_FRAGMENT = new Item(new FabricItemSettings());
    public static final Item SPRUCE_BARK_FRAGMENT = new Item(new FabricItemSettings());
    public static final Item MANGROVE_BARK_FRAGMENT = new Item(new FabricItemSettings());

    public static final Item LEATHER_BLOCK = new BlockItem(MBMBlocks.LEATHER_BLOCK, new FabricItemSettings());

    public static final Item STICK_BUNDLE = new BlockItem(MBMBlocks.STICK_BUNDLE, new FabricItemSettings());


    //Meterial
    public static final Item RUBY = new Item(new FabricItemSettings());


    public static void registerItemGroup() {
        ItemGroupEvents.modifyEntriesEvent(MBM_BUILDING_BLOCKS).register(content -> {
            content.addAll(MBM_BUILDING_BLOCKS_COLLECTION);
        });

        ItemGroupEvents.modifyEntriesEvent(MBM_TOOLS).register(content -> {
            content.addAll(MBM_TOOLS_COLLECTION);
        });

        ItemGroupEvents.modifyEntriesEvent(MBM_MISC).register(content -> {
            content.addAll(MBM_MISC_COLLECTION);
        });

        ItemGroupEvents.modifyEntriesEvent(MBM_BATA).register(content -> {
            content.addAll(MBM_BATA_COLLECTION);
        });
    }

    public static void addItemCollection(MBM_ITEMGROUPS group, ItemStack item) {
        switch (group) {
            case MBM_BUILDING_BLOCKS: MBM_BUILDING_BLOCKS_COLLECTION.add(item); break;
            case MBM_TOOLS: MBM_TOOLS_COLLECTION.add(item); break;
            case MBM_MISC: MBM_MISC_COLLECTION.add(item); break;
            case MBM_BATA: MBM_BATA_COLLECTION.add(item); break;
        }
    }


    public static final void items(){
        //----------ITEMS----------
        //Buckets
        //MUD_BUCKET = registerItem("mud_bucket", new BucketItem(MBMBlocks.STILL_MUD, new FabricItemSettings().maxCount(1)));

        MUD_BUCKET = registerItem("mud_bucket", new BucketItem(MBMBlocks.STILL_MUD, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));

        //MUD_BUCKET = Registry.register(Registries.ITEM, new Identifier(moreblocksmod.ModName, "mud_bucket"), new BucketItem(MBMBlocks.STILL_MUD, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));

        //Custom Tools
        //Registry.register(Registries.ITEM, new Identifier(moreblocksmod.ModName, "netherite_hammer"), NETHERITE_HAMMER);
        registerItem("netherite_hammer", (NetheriteHammer)NETHERITE_HAMMER);

        //Bark Fragments
        /* Registry.register(Registries.ITEM, new Identifier(moreblocksmod.ModName, "acacia_bark_fragment"), ACACIA_BARK_FRAGMENT);
        Registry.register(Registries.ITEM, new Identifier(moreblocksmod.ModName, "birch_bark_fragment"), BIRCH_BARK_FRAGMENT);
        Registry.register(Registries.ITEM, new Identifier(moreblocksmod.ModName, "dark_oak_bark_fragment"), DARK_OAK_BARK_FRAGMENT);
        Registry.register(Registries.ITEM, new Identifier(moreblocksmod.ModName, "jungle_bark_fragment"), JUNGLE_BARK_FRAGMENT);
        Registry.register(Registries.ITEM, new Identifier(moreblocksmod.ModName, "oak_bark_fragment"), OAK_BARK_FRAGMENT);
        Registry.register(Registries.ITEM, new Identifier(moreblocksmod.ModName, "spruce_bark_fragment"), SPRUCE_BARK_FRAGMENT);
        Registry.register(Registries.ITEM, new Identifier(moreblocksmod.ModName, "mangrove_bark_fragment"), MANGROVE_BARK_FRAGMENT); */
        registerItem("acacia_bark_fragment", ACACIA_BARK_FRAGMENT);
        registerItem("birch_bark_fragment", BIRCH_BARK_FRAGMENT);
        registerItem("dark_oak_bark_fragment", DARK_OAK_BARK_FRAGMENT);
        registerItem("jungle_bark_fragment", JUNGLE_BARK_FRAGMENT);
        registerItem("oak_bark_fragment", OAK_BARK_FRAGMENT);
        registerItem("spruce_bark_fragment", SPRUCE_BARK_FRAGMENT);
        registerItem("mangrove_bark_fragment", MANGROVE_BARK_FRAGMENT);

        /* Registry.register(Registries.ITEM, new Identifier(moreblocksmod.ModName, "leather_block"), LEATHER_BLOCK);
        Registry.register(Registries.ITEM, new Identifier(moreblocksmod.ModName, "stick_bundle"), STICK_BUNDLE); */
        registerItem("leather_block", (BlockItem)LEATHER_BLOCK);
        registerItem("stick_bundle", (BlockItem)STICK_BUNDLE);

        /* Registry.register(Registries.ITEM, new Identifier(moreblocksmod.ModName, "rice_seeds"), RICE_SEEDS);
        Registry.register(Registries.ITEM, new Identifier(moreblocksmod.ModName, "rice_leaf_sheath"), RICE_LEAF_SHEATH); */
        registerItem("rice_seeds", (AliasedBlockItem)RICE_SEEDS);
        registerItem("rice_leaf_sheath", RICE_LEAF_SHEATH);

        //Registry.register(Registries.ITEM, new Identifier(moreblocksmod.ModName, "grape_seeds"), GRAPE_SEEDS);
        registerItem("grape_seeds", (AliasedBlockItem)GRAPE_SEEDS);

        //Meterial
        //Registry.register(Registries.ITEM, new Identifier(moreblocksmod.ModName, "ruby"), RUBY);
        registerItem("ruby", RUBY);
//##item##//

        //registerItemGroup();
    }

    public static void registerItem(String ItemName, Item item) {
        Registry.register(Registries.ITEM, new Identifier(moreblocksmod.ModName, ItemName), item);
        addItemCollection(MBM_ITEMGROUPS.MBM_MISC, item.getDefaultStack());
    }

    public static void registerItem(String ItemName, Item item, MBM_ITEMGROUPS group) {
        Registry.register(Registries.ITEM, new Identifier(moreblocksmod.ModName, ItemName), item);
        addItemCollection(group, item.getDefaultStack());
    }

    public static void registerItem(String ItemName, BlockItem item) {
        Registry.register(Registries.ITEM, new Identifier(moreblocksmod.ModName, ItemName), item);
        addItemCollection(MBM_ITEMGROUPS.MBM_BUILDING_BLOCKS, item.getDefaultStack());
    }

    public static void registerItem(String ItemName, BlockItem item, MBM_ITEMGROUPS group) {
        Registry.register(Registries.ITEM, new Identifier(moreblocksmod.ModName, ItemName), item);
        addItemCollection(group, item.getDefaultStack());
    }

    public static void registerItem(String ItemName, NetheriteHammer item) {
        Registry.register(Registries.ITEM, new Identifier(moreblocksmod.ModName, ItemName), item);
        addItemCollection(MBM_ITEMGROUPS.MBM_TOOLS, item.getDefaultStack());
    }

    public static void registerItem(String ItemName, NetheriteHammer item, MBM_ITEMGROUPS group) {
        Registry.register(Registries.ITEM, new Identifier(moreblocksmod.ModName, ItemName), item);
        addItemCollection(group, item.getDefaultStack());
    }

    public static BucketItem registerItem(String ItemName, BucketItem item) {
        addItemCollection(MBM_ITEMGROUPS.MBM_TOOLS, item.getDefaultStack());
        return Registry.register(Registries.ITEM, new Identifier(moreblocksmod.ModName, ItemName), item);
    }

    public static void registerItem(String ItemName, BucketItem item, MBM_ITEMGROUPS group) {
        Registry.register(Registries.ITEM, new Identifier(moreblocksmod.ModName, ItemName), item);
        addItemCollection(group, item.getDefaultStack());
    }

    public static void registerItem(String ItemName, AliasedBlockItem item) {
        Registry.register(Registries.ITEM, new Identifier(moreblocksmod.ModName, ItemName), item);
        addItemCollection(MBM_ITEMGROUPS.MBM_MISC, item.getDefaultStack());
    }

    public static void registerItem(String ItemName, AliasedBlockItem item, MBM_ITEMGROUPS group) {
        Registry.register(Registries.ITEM, new Identifier(moreblocksmod.ModName, ItemName), item);
        addItemCollection(group, item.getDefaultStack());
    }

    /* public static Item registerItem(String ItemName, Item item, MBM_ITEMGROUPS itemGroup) {
        Item finalItem = Registry.register(Registries.ITEM, new Identifier(moreblocksmod.ModName, ItemName), item);
        //addItemCollection(itemGroup, item.getDefaultStack());
        return finalItem;
    } */
    
}


