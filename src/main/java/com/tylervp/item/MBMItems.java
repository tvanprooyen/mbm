package com.tylervp.item;

import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterials;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;

import com.tylervp.moreblocksmod;
import com.tylervp.block.MBMBlocks;

public class MBMItems extends moreblocksmod {

    public static final ItemGroup MBM_BUILDING_BLOCKS = FabricItemGroupBuilder.build(
		new Identifier("moreblocksmod", "general"),
		() -> new ItemStack(MBMBlocks.TERRACOTTA_BRICKS));

    public static final ItemGroup MBM_TOOLS = FabricItemGroupBuilder.build(
        new Identifier("moreblocksmod", "tools"),
        () -> new ItemStack(MBMItems.NETHERITE_HAMMER));

    public static final ItemGroup MBM_MISC = FabricItemGroupBuilder.build(
        new Identifier("moreblocksmod", "misc"),
        () -> new ItemStack(MBMItems.RICE_SEEDS));

    public static final ItemGroup MBM_BATA = FabricItemGroupBuilder.build(
        new Identifier("moreblocksmod", "experimental"),
        () -> new ItemStack(MBMBlocks.PIB.asItem()));


    //Quick Settings
    public static final Item.Settings MISC = new Item.Settings().group(MBM_MISC);
    public static final Item.Settings BUILDING_BLOCKS = new Item.Settings().group(MBM_BUILDING_BLOCKS);
    public static final Item.Settings MATERIALS = new Item.Settings().group(ItemGroup.MATERIALS);
    public static final Item.Settings DECORATIONS = new Item.Settings().group(ItemGroup.DECORATIONS);
    public static final Item.Settings TOOLS = new Item.Settings().group(MBM_TOOLS);
    public static final Item.Settings BATA = new Item.Settings().group(MBM_BATA);

    //Custom Tools
    public static final NetheriteHammer NETHERITE_HAMMER = new NetheriteHammer(ToolMaterials.NETHERITE, new Item.Settings().group(MBM_TOOLS).fireproof());

    //Rice
    public static final Item RICE_SEEDS = new AliasedBlockItem(MBMBlocks.RICE, MISC);
    public static final Item RICE_LEAF_SHEATH = new Item(MISC);

    public static final Item GRAPE_SEEDS = new AliasedBlockItem(MBMBlocks.GRAPE_SPUR, MISC);


    //Bucket
    public static Item MUD_BUCKET;

    //Bark Fragment
    public static final Item ACACIA_BARK_FRAGMENT = new Item(MISC);
    public static final Item BIRCH_BARK_FRAGMENT = new Item(MISC);
    public static final Item DARK_OAK_BARK_FRAGMENT = new Item(MISC);
    public static final Item JUNGLE_BARK_FRAGMENT = new Item(MISC);
    public static final Item OAK_BARK_FRAGMENT = new Item(MISC);
    public static final Item SPRUCE_BARK_FRAGMENT = new Item(MISC);

    //Meterial
    public static final Item RUBY = new Item(MISC);


    public static final void items(){
        String ModName = moreblocksmod.ModName;

        //----------ITEMS----------
        //Buckets
        MUD_BUCKET = Registry.register(Registry.ITEM, new Identifier(ModName, "mud_bucket"), new BucketItem(MBMBlocks.STILL_MUD, new Item.Settings().group(MBM_MISC).maxCount(1)));

        //Custom Tools
        Registry.register(Registry.ITEM, new Identifier(ModName, "netherite_hammer"), NETHERITE_HAMMER);
        
        //Bark Fragments
        Registry.register(Registry.ITEM, new Identifier(ModName, "acacia_bark_fragment"), ACACIA_BARK_FRAGMENT);
        Registry.register(Registry.ITEM, new Identifier(ModName, "birch_bark_fragment"), BIRCH_BARK_FRAGMENT);
        Registry.register(Registry.ITEM, new Identifier(ModName, "dark_oak_bark_fragment"), DARK_OAK_BARK_FRAGMENT);
        Registry.register(Registry.ITEM, new Identifier(ModName, "jungle_bark_fragment"), JUNGLE_BARK_FRAGMENT);
        Registry.register(Registry.ITEM, new Identifier(ModName, "oak_bark_fragment"), OAK_BARK_FRAGMENT);
        Registry.register(Registry.ITEM, new Identifier(ModName, "spruce_bark_fragment"), SPRUCE_BARK_FRAGMENT);

        //Rice
        Registry.register(Registry.ITEM, new Identifier(ModName, "rice_seeds"), RICE_SEEDS);
        Registry.register(Registry.ITEM, new Identifier(ModName, "rice_leaf_sheath"), RICE_LEAF_SHEATH);

        Registry.register(Registry.ITEM, new Identifier(ModName, "grape_seeds"), GRAPE_SEEDS);

        //Meterial
        Registry.register(Registry.ITEM, new Identifier(ModName, "ruby"), RUBY);
//##item##//

    }
}


