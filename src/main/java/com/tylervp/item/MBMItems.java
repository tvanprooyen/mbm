package com.tylervp.item;

import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ToolMaterials;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

//import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.impl.content.registry.CompostingChanceRegistryImpl;

import com.tylervp.moreblocksmod;
import com.tylervp.block.MBMBlocks;

public class MBMItems extends moreblocksmod {
    //Quick Settings
    public static final Item.Settings MISC = new Item.Settings().group(ItemGroup.MISC);
    public static final Item.Settings BUILDING_BLOCKS = new Item.Settings().group(ItemGroup.BUILDING_BLOCKS);
    public static final Item.Settings MATERIALS = new Item.Settings().group(ItemGroup.MATERIALS);
    public static final Item.Settings DECORATIONS = new Item.Settings().group(ItemGroup.DECORATIONS);

    public static final CompostingChanceRegistryImpl compostRegistry = new CompostingChanceRegistryImpl();


    //Custom Tools
    public static final NetheriteHammer NETHERITE_HAMMER = new NetheriteHammer(ToolMaterials.NETHERITE, new Item.Settings().group(ItemGroup.TOOLS).fireproof());

    //Rice
    public static final Item RICE_SEEDS = new AliasedBlockItem(MBMBlocks.RICE, MATERIALS);
    public static final Item RICE_LEAF_SHEATH = new Item(MISC);
    
    //Bucket
    public static Item MUD_BUCKET;

    //Bark Fragment
    public static final Item ACACIA_BARK_FRAGMENT = new Item(MISC);
    public static final Item BIRCH_BARK_FRAGMENT = new Item(MISC);
    public static final Item DARK_OAK_BARK_FRAGMENT = new Item(MISC);
    public static final Item JUNGLE_BARK_FRAGMENT = new Item(MISC);
    public static final Item OAK_BARK_FRAGMENT = new Item(MISC);
    public static final Item SPRUCE_BARK_FRAGMENT = new Item(MISC);

    public static final void items(String ModName){

        /*
        Color Order
        White
        Red
        Orange
        Pink
        Yellow
        Lime
        Green
        Light Blue
        Cyan
        Blue
        Magenta
        Purple
        Brown
        Gray
        Light Gray
        Black 
        */


        //Items
        //Misc
        Registry.register(Registry.ITEM, new Identifier(ModName, "rope"), new BlockItem(MBMBlocks.ROPE, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "leather_block"), new BlockItem(MBMBlocks.LEATHER_BLOCK, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "rice_straw_bale"), new BlockItem(MBMBlocks.RICE_STRAW_BALE, BUILDING_BLOCKS));
        MUD_BUCKET = Registry.register(Registry.ITEM, new Identifier(ModName, "mud_bucket"), new BucketItem(MBMBlocks.STILL_MUD, MISC));

        //Thin Logs
        Registry.register(Registry.ITEM, new Identifier(ModName, "thin_acacia_log"), new BlockItem(MBMBlocks.THIN_ACACIA_LOG, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "thin_birch_log"), new BlockItem(MBMBlocks.THIN_BIRCH_LOG, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "thin_dark_oak_log"), new BlockItem(MBMBlocks.THIN_DARK_OAK_LOG, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "thin_stripped_jungle_log"), new BlockItem(MBMBlocks.THIN_STRIPPED_JUNGLE_LOG, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "thin_oak_log"), new BlockItem(MBMBlocks.THIN_OAK_LOG, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "thin_spruce_log"), new BlockItem(MBMBlocks.THIN_SPRUCE_LOG, BUILDING_BLOCKS));

        Registry.register(Registry.ITEM, new Identifier(ModName, "thin_stripped_acacia_log"), new BlockItem(MBMBlocks.THIN_STRIPPED_ACACIA_LOG, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "thin_stripped_birch_log"), new BlockItem(MBMBlocks.THIN_STRIPPED_BIRCH_LOG, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "thin_stripped_dark_oak_log"), new BlockItem(MBMBlocks.THIN_STRIPPED_DARK_OAK_LOG, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "thin_jungle_log"), new BlockItem(MBMBlocks.THIN_JUNGLE_LOG, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "thin_stripped_oak_log"), new BlockItem(MBMBlocks.THIN_STRIPPED_OAK_LOG, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "thin_stripped_spruce_log"), new BlockItem(MBMBlocks.THIN_STRIPPED_SPRUCE_LOG, BUILDING_BLOCKS));

        //Chopped Log
        Registry.register(Registry.ITEM, new Identifier(ModName, "chopped_acacia_log"), new BlockItem(MBMBlocks.CHOPPED_ACACIA_LOG, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "chopped_stripped_acacia_log"), new BlockItem(MBMBlocks.CHOPPED_STRIPPED_ACACIA_LOG, BUILDING_BLOCKS));

        Registry.register(Registry.ITEM, new Identifier(ModName, "chopped_birch_log"), new BlockItem(MBMBlocks.CHOPPED_BIRCH_LOG, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "chopped_stripped_birch_log"), new BlockItem(MBMBlocks.CHOPPED_STRIPPED_BIRCH_LOG, BUILDING_BLOCKS));

        Registry.register(Registry.ITEM, new Identifier(ModName, "chopped_dark_oak_log"), new BlockItem(MBMBlocks.CHOPPED_DARK_OAK_LOG, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "chopped_stripped_dark_oak_log"), new BlockItem(MBMBlocks.CHOPPED_STRIPPED_DARK_OAK_LOG, BUILDING_BLOCKS));

        Registry.register(Registry.ITEM, new Identifier(ModName, "chopped_jungle_log"), new BlockItem(MBMBlocks.CHOPPED_JUNGLE_LOG, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "chopped_stripped_jungle_log"), new BlockItem(MBMBlocks.CHOPPED_STRIPPED_JUNGLE_LOG, BUILDING_BLOCKS));

        Registry.register(Registry.ITEM, new Identifier(ModName, "chopped_oak_log"), new BlockItem(MBMBlocks.CHOPPED_OAK_LOG, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "chopped_stripped_oak_log"), new BlockItem(MBMBlocks.CHOPPED_STRIPPED_OAK_LOG, BUILDING_BLOCKS));

        Registry.register(Registry.ITEM, new Identifier(ModName, "chopped_spruce_log"), new BlockItem(MBMBlocks.CHOPPED_SPRUCE_LOG, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "chopped_stripped_spruce_log"), new BlockItem(MBMBlocks.CHOPPED_STRIPPED_SPRUCE_LOG, BUILDING_BLOCKS));

        //Custom Tools
        Registry.register(Registry.ITEM, new Identifier(ModName, "netherite_hammer"), NETHERITE_HAMMER);
        
        //Bark Fragments
        Registry.register(Registry.ITEM, new Identifier(ModName, "acacia_bark_fragment"), ACACIA_BARK_FRAGMENT);
        Registry.register(Registry.ITEM, new Identifier(ModName, "birch_bark_fragment"), BIRCH_BARK_FRAGMENT);
        Registry.register(Registry.ITEM, new Identifier(ModName, "dark_oak_bark_fragment"), DARK_OAK_BARK_FRAGMENT);
        Registry.register(Registry.ITEM, new Identifier(ModName, "jungle_bark_fragment"), JUNGLE_BARK_FRAGMENT);
        Registry.register(Registry.ITEM, new Identifier(ModName, "oak_bark_fragment"), OAK_BARK_FRAGMENT);
        Registry.register(Registry.ITEM, new Identifier(ModName, "spruce_bark_fragment"), SPRUCE_BARK_FRAGMENT);

        //Terracotta Bricks
        Registry.register(Registry.ITEM, new Identifier(ModName, "terracotta_bricks"), new BlockItem(MBMBlocks.TERRACOTTA_BRICKS, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "terracotta_bricks_slab"), new BlockItem(MBMBlocks.TERRACOTTA_BRICKS_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "terracotta_bricks_stairs"), new BlockItem(MBMBlocks.TERRACOTTA_BRICKS_STAIRS, BUILDING_BLOCKS));

        Registry.register(Registry.ITEM, new Identifier(ModName, "white_terracotta_bricks"), new BlockItem(MBMBlocks.WHITE_TERRACOTTA_BRICKS, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "white_terracotta_bricks_slab"), new BlockItem(MBMBlocks.WHITE_TERRACOTTA_BRICKS_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "white_terracotta_bricks_stairs"), new BlockItem(MBMBlocks.WHITE_TERRACOTTA_BRICKS_STAIRS, BUILDING_BLOCKS));

        Registry.register(Registry.ITEM, new Identifier(ModName, "red_terracotta_bricks"), new BlockItem(MBMBlocks.RED_TERRACOTTA_BRICKS, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "red_terracotta_bricks_slab"), new BlockItem(MBMBlocks.RED_TERRACOTTA_BRICKS_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "red_terracotta_bricks_stairs"), new BlockItem(MBMBlocks.RED_TERRACOTTA_BRICKS_STAIRS, BUILDING_BLOCKS));

        Registry.register(Registry.ITEM, new Identifier(ModName, "orange_terracotta_bricks"), new BlockItem(MBMBlocks.ORANGE_TERRACOTTA_BRICKS, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "orange_terracotta_bricks_slab"), new BlockItem(MBMBlocks.ORANGE_TERRACOTTA_BRICKS_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "orange_terracotta_bricks_stairs"), new BlockItem(MBMBlocks.ORANGE_TERRACOTTA_BRICKS_STAIRS, BUILDING_BLOCKS));
        
        Registry.register(Registry.ITEM, new Identifier(ModName, "pink_terracotta_bricks"), new BlockItem(MBMBlocks.PINK_TERRACOTTA_BRICKS, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "pink_terracotta_bricks_slab"), new BlockItem(MBMBlocks.PINK_TERRACOTTA_BRICKS_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "pink_terracotta_bricks_stairs"), new BlockItem(MBMBlocks.PINK_TERRACOTTA_BRICKS_STAIRS, BUILDING_BLOCKS));

        Registry.register(Registry.ITEM, new Identifier(ModName, "yellow_terracotta_bricks"), new BlockItem(MBMBlocks.YELLOW_TERRACOTTA_BRICKS, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "yellow_terracotta_bricks_slab"), new BlockItem(MBMBlocks.YELLOW_TERRACOTTA_BRICKS_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "yellow_terracotta_bricks_stairs"), new BlockItem(MBMBlocks.YELLOW_TERRACOTTA_BRICKS_STAIRS, BUILDING_BLOCKS));

        Registry.register(Registry.ITEM, new Identifier(ModName, "lime_terracotta_bricks"), new BlockItem(MBMBlocks.LIME_TERRACOTTA_BRICKS, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "lime_terracotta_bricks_slab"), new BlockItem(MBMBlocks.LIME_TERRACOTTA_BRICKS_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "lime_terracotta_bricks_stairs"), new BlockItem(MBMBlocks.LIME_TERRACOTTA_BRICKS_STAIRS, BUILDING_BLOCKS));

        Registry.register(Registry.ITEM, new Identifier(ModName, "green_terracotta_bricks"), new BlockItem(MBMBlocks.GREEN_TERRACOTTA_BRICKS, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "green_terracotta_bricks_slab"), new BlockItem(MBMBlocks.GREEN_TERRACOTTA_BRICKS_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "green_terracotta_bricks_stairs"), new BlockItem(MBMBlocks.GREEN_TERRACOTTA_BRICKS_STAIRS, BUILDING_BLOCKS));

        Registry.register(Registry.ITEM, new Identifier(ModName, "light_blue_terracotta_bricks"), new BlockItem(MBMBlocks.LIGHT_BLUE_TERRACOTTA_BRICKS, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "light_blue_terracotta_bricks_slab"), new BlockItem(MBMBlocks.LIGHT_BLUE_TERRACOTTA_BRICKS_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "light_blue_terracotta_bricks_stairs"), new BlockItem(MBMBlocks.LIGHT_BLUE_TERRACOTTA_BRICKS_STAIRS, BUILDING_BLOCKS));

        Registry.register(Registry.ITEM, new Identifier(ModName, "cyan_terracotta_bricks"), new BlockItem(MBMBlocks.CYAN_TERRACOTTA_BRICKS, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "cyan_terracotta_bricks_slab"), new BlockItem(MBMBlocks.CYAN_TERRACOTTA_BRICKS_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "cyan_terracotta_bricks_stairs"), new BlockItem(MBMBlocks.CYAN_TERRACOTTA_BRICKS_STAIRS, BUILDING_BLOCKS));
        
        Registry.register(Registry.ITEM, new Identifier(ModName, "blue_terracotta_bricks"), new BlockItem(MBMBlocks.BLUE_TERRACOTTA_BRICKS, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "blue_terracotta_bricks_slab"), new BlockItem(MBMBlocks.BLUE_TERRACOTTA_BRICKS_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "blue_terracotta_bricks_stairs"), new BlockItem(MBMBlocks.BLUE_TERRACOTTA_BRICKS_STAIRS, BUILDING_BLOCKS));

        Registry.register(Registry.ITEM, new Identifier(ModName, "magenta_terracotta_bricks"), new BlockItem(MBMBlocks.MAGENTA_TERRACOTTA_BRICKS, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "magenta_terracotta_bricks_slab"), new BlockItem(MBMBlocks.MAGENTA_TERRACOTTA_BRICKS_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "magenta_terracotta_bricks_stairs"), new BlockItem(MBMBlocks.MAGENTA_TERRACOTTA_BRICKS_STAIRS, BUILDING_BLOCKS));

        Registry.register(Registry.ITEM, new Identifier(ModName, "purple_terracotta_bricks"), new BlockItem(MBMBlocks.PURPLE_TERRACOTTA_BRICKS, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "purple_terracotta_bricks_slab"), new BlockItem(MBMBlocks.PURPLE_TERRACOTTA_BRICKS_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "purple_terracotta_bricks_stairs"), new BlockItem(MBMBlocks.PURPLE_TERRACOTTA_BRICKS_STAIRS, BUILDING_BLOCKS));

        Registry.register(Registry.ITEM, new Identifier(ModName, "brown_terracotta_bricks"), new BlockItem(MBMBlocks.BROWN_TERRACOTTA_BRICKS, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "brown_terracotta_bricks_slab"), new BlockItem(MBMBlocks.BROWN_TERRACOTTA_BRICKS_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "brown_terracotta_bricks_stairs"), new BlockItem(MBMBlocks.BROWN_TERRACOTTA_BRICKS_STAIRS, BUILDING_BLOCKS));

        Registry.register(Registry.ITEM, new Identifier(ModName, "gray_terracotta_bricks"), new BlockItem(MBMBlocks.GRAY_TERRACOTTA_BRICKS, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "gray_terracotta_bricks_slab"), new BlockItem(MBMBlocks.GRAY_TERRACOTTA_BRICKS_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "gray_terracotta_bricks_stairs"), new BlockItem(MBMBlocks.GRAY_TERRACOTTA_BRICKS_STAIRS, BUILDING_BLOCKS));

        Registry.register(Registry.ITEM, new Identifier(ModName, "light_gray_terracotta_bricks"), new BlockItem(MBMBlocks.LIGHT_GRAY_TERRACOTTA_BRICKS, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "light_gray_terracotta_bricks_slab"), new BlockItem(MBMBlocks.LIGHT_GRAY_TERRACOTTA_BRICKS_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "light_gray_terracotta_bricks_stairs"), new BlockItem(MBMBlocks.LIGHT_GRAY_TERRACOTTA_BRICKS_STAIRS, BUILDING_BLOCKS));
        
        Registry.register(Registry.ITEM, new Identifier(ModName, "black_terracotta_bricks"), new BlockItem(MBMBlocks.BLACK_TERRACOTTA_BRICKS, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "black_terracotta_bricks_slab"), new BlockItem(MBMBlocks.BLACK_TERRACOTTA_BRICKS_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "black_terracotta_bricks_stairs"), new BlockItem(MBMBlocks.BLACK_TERRACOTTA_BRICKS_STAIRS, BUILDING_BLOCKS));
        

        //Packed Terracotta
        Registry.register(Registry.ITEM, new Identifier(ModName, "packed_terracotta"), new BlockItem(MBMBlocks.PACKED_TERRACOTTA, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "white_packed_terracotta"), new BlockItem(MBMBlocks.WHITE_PACKED_TERRACOTTA, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "red_packed_terracotta"), new BlockItem(MBMBlocks.RED_PACKED_TERRACOTTA, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "orange_packed_terracotta"), new BlockItem(MBMBlocks.ORANGE_PACKED_TERRACOTTA, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "pink_packed_terracotta"), new BlockItem(MBMBlocks.PINK_PACKED_TERRACOTTA, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "yellow_packed_terracotta"), new BlockItem(MBMBlocks.YELLOW_PACKED_TERRACOTTA, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "lime_packed_terracotta"), new BlockItem(MBMBlocks.LIME_PACKED_TERRACOTTA, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "green_packed_terracotta"), new BlockItem(MBMBlocks.GREEN_PACKED_TERRACOTTA, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "light_blue_packed_terracotta"), new BlockItem(MBMBlocks.LIGHT_BLUE_PACKED_TERRACOTTA, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "cyan_packed_terracotta"), new BlockItem(MBMBlocks.CYAN_PACKED_TERRACOTTA, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "blue_packed_terracotta"), new BlockItem(MBMBlocks.BLUE_PACKED_TERRACOTTA, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "magenta_packed_terracotta"), new BlockItem(MBMBlocks.MAGENTA_PACKED_TERRACOTTA, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "purple_packed_terracotta"), new BlockItem(MBMBlocks.PURPLE_PACKED_TERRACOTTA, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "brown_packed_terracotta"), new BlockItem(MBMBlocks.BROWN_PACKED_TERRACOTTA, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "gray_packed_terracotta"), new BlockItem(MBMBlocks.GRAY_PACKED_TERRACOTTA, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "light_gray_packed_terracotta"), new BlockItem(MBMBlocks.LIGHT_GRAY_PACKED_TERRACOTTA, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "black_packed_terracotta"), new BlockItem(MBMBlocks.BLACK_PACKED_TERRACOTTA, BUILDING_BLOCKS));


        //Terracotta Slabs and Stairs
        Registry.register(Registry.ITEM, new Identifier(ModName, "white_terracotta_stairs"), new BlockItem(MBMBlocks.WHITE_TERRACOTTA_STAIRS, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "white_terracotta_slab"), new BlockItem(MBMBlocks.WHITE_TERRACOTTA_SLAB, BUILDING_BLOCKS));

        Registry.register(Registry.ITEM, new Identifier(ModName, "red_terracotta_stairs"), new BlockItem(MBMBlocks.RED_TERRACOTTA_STAIRS, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "red_terracotta_slab"), new BlockItem(MBMBlocks.RED_TERRACOTTA_SLAB, BUILDING_BLOCKS));
        
        Registry.register(Registry.ITEM, new Identifier(ModName, "cyan_terracotta_stairs"), new BlockItem(MBMBlocks.CYAN_TERRACOTTA_STAIRS, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "cyan_terracotta_slab"), new BlockItem(MBMBlocks.CYAN_TERRACOTTA_SLAB, BUILDING_BLOCKS));

        Registry.register(Registry.ITEM, new Identifier(ModName, "brown_terracotta_slab"), new BlockItem(MBMBlocks.BROWN_TERRACOTTA_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "brown_terracotta_stairs"), new BlockItem(MBMBlocks.BROWN_TERRACOTTA_STAIRS, BUILDING_BLOCKS));

        Registry.register(Registry.ITEM, new Identifier(ModName, "black_terracotta_slab"), new BlockItem(MBMBlocks.BLACK_TERRACOTTA_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "black_terracotta_stairs"), new BlockItem(MBMBlocks.BLACK_TERRACOTTA_STAIRS, BUILDING_BLOCKS));

        Registry.register(Registry.ITEM, new Identifier(ModName, "blue_terracotta_slab"), new BlockItem(MBMBlocks.BLUE_TERRACOTTA_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "blue_terracotta_stairs"), new BlockItem(MBMBlocks.BLUE_TERRACOTTA_STAIRS, BUILDING_BLOCKS));

        Registry.register(Registry.ITEM, new Identifier(ModName, "light_blue_terracotta_slab"), new BlockItem(MBMBlocks.LIGHT_BLUE_TERRACOTTA_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "light_blue_terracotta_stairs"), new BlockItem(MBMBlocks.LIGHT_BLUE_TERRACOTTA_STAIRS, BUILDING_BLOCKS));

        Registry.register(Registry.ITEM, new Identifier(ModName, "gray_terracotta_slab"), new BlockItem(MBMBlocks.GRAY_TERRACOTTA_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "gray_terracotta_stairs"), new BlockItem(MBMBlocks.GRAY_TERRACOTTA_STAIRS, BUILDING_BLOCKS));

        Registry.register(Registry.ITEM, new Identifier(ModName, "green_terracotta_slab"), new BlockItem(MBMBlocks.GREEN_TERRACOTTA_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "green_terracotta_stairs"), new BlockItem(MBMBlocks.GREEN_TERRACOTTA_STAIRS, BUILDING_BLOCKS));

        Registry.register(Registry.ITEM, new Identifier(ModName, "lime_terracotta_slab"), new BlockItem(MBMBlocks.LIME_TERRACOTTA_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "lime_terracotta_stairs"), new BlockItem(MBMBlocks.LIME_TERRACOTTA_STAIRS, BUILDING_BLOCKS));

        Registry.register(Registry.ITEM, new Identifier(ModName, "light_gray_terracotta_slab"), new BlockItem(MBMBlocks.LIGHT_GRAY_TERRACOTTA_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "light_gray_terracotta_stairs"), new BlockItem(MBMBlocks.LIGHT_GRAY_TERRACOTTA_STAIRS, BUILDING_BLOCKS));

        Registry.register(Registry.ITEM, new Identifier(ModName, "magenta_terracotta_slab"), new BlockItem(MBMBlocks.MAGENTA_TERRACOTTA_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "magenta_terracotta_stairs"), new BlockItem(MBMBlocks.MAGENTA_TERRACOTTA_STAIRS, BUILDING_BLOCKS));

        Registry.register(Registry.ITEM, new Identifier(ModName, "orange_terracotta_slab"), new BlockItem(MBMBlocks.ORANGE_TERRACOTTA_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "orange_terracotta_stairs"), new BlockItem(MBMBlocks.ORANGE_TERRACOTTA_STAIRS, BUILDING_BLOCKS));

        Registry.register(Registry.ITEM, new Identifier(ModName, "pink_terracotta_slab"), new BlockItem(MBMBlocks.PINK_TERRACOTTA_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "pink_terracotta_stairs"), new BlockItem(MBMBlocks.PINK_TERRACOTTA_STAIRS, BUILDING_BLOCKS));

        Registry.register(Registry.ITEM, new Identifier(ModName, "purple_terracotta_slab"), new BlockItem(MBMBlocks.PURPLE_TERRACOTTA_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "purple_terracotta_stairs"), new BlockItem(MBMBlocks.PURPLE_TERRACOTTA_STAIRS, BUILDING_BLOCKS));

        Registry.register(Registry.ITEM, new Identifier(ModName, "yellow_terracotta_slab"), new BlockItem(MBMBlocks.YELLOW_TERRACOTTA_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "yellow_terracotta_stairs"), new BlockItem(MBMBlocks.YELLOW_TERRACOTTA_STAIRS, BUILDING_BLOCKS));

        Registry.register(Registry.ITEM, new Identifier(ModName, "terracotta_slab"), new BlockItem(MBMBlocks.TERRACOTTA_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "terracotta_stairs"), new BlockItem(MBMBlocks.TERRACOTTA_STAIRS, BUILDING_BLOCKS));


        //Dark Stone
        Registry.register(Registry.ITEM, new Identifier(ModName, "dark_stone"), new BlockItem(MBMBlocks.DARK_STONE, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "dark_stone_slab"), new BlockItem(MBMBlocks.DARK_STONE_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "dark_stone_stairs"), new BlockItem(MBMBlocks.DARK_STONE_STAIRS, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "dark_stone_bricks"), new BlockItem(MBMBlocks.DARK_STONE_BRICK, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "dark_stone_brick_slab"), new BlockItem(MBMBlocks.DARK_STONE_BRICK_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "dark_stone_brick_stairs"), new BlockItem(MBMBlocks.DARK_STONE_BRICK_STAIRS, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "dark_stone_tiles"), new BlockItem(MBMBlocks.DARK_STONE_TILES, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "dark_stone_tiles_slab"), new BlockItem(MBMBlocks.DARK_STONE_TILES_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "dark_stone_tiles_stairs"), new BlockItem(MBMBlocks.DARK_STONE_TILES_STAIRS, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "dark_cobblestone"), new BlockItem(MBMBlocks.DARK_COBBLESTONE, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "dark_cobblestone_slab"), new BlockItem(MBMBlocks.DARK_COBBLESTONE_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "dark_cobblestone_stairs"), new BlockItem(MBMBlocks.DARK_COBBLESTONE_STAIRS, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "emblemed_dark_stone"), new BlockItem(MBMBlocks.EMBLEMED_DARK_STONE, BUILDING_BLOCKS));
        
        //Misc
        Registry.register(Registry.ITEM, new Identifier(ModName, "carved_melon"), new BlockItem(MBMBlocks.CARVEDMELON, BUILDING_BLOCKS));
        
        //Dead Blocks
        Registry.register(Registry.ITEM, new Identifier(ModName, "dead_grass_block"), new BlockItem(MBMBlocks.DEAD_GRASS_BLOCK, BUILDING_BLOCKS));
        
        Registry.register(Registry.ITEM, new Identifier(ModName, "burnt_log"), new BlockItem(MBMBlocks.BURNT_LOG, BUILDING_BLOCKS));

        Registry.register(Registry.ITEM, new Identifier(ModName, "burnt_planks"), new BlockItem(MBMBlocks.BURNT_PLANKS, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "burnt_planks_slab"), new BlockItem(MBMBlocks.BURNT_PLANKS_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "burnt_planks_stairs"), new BlockItem(MBMBlocks.BURNT_PLANKS_STAIRS, BUILDING_BLOCKS));

        //Transistion Blocks
        Registry.register(Registry.ITEM, new Identifier(ModName, "sand_chiseled_stone_brick_mix"), new BlockItem(MBMBlocks.SD_CSB_LIGHT, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "sand_stone_brick_mix_heavy"), new BlockItem(MBMBlocks.SD_SB_HEAVY, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "sand_stone_brick_mix_light"), new BlockItem(MBMBlocks.SD_SB_LIGHT, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "coarse_dirt_cobble_stone_mix_heavy"), new BlockItem(MBMBlocks.CD_CS_HEAVY, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "coarse_dirt_cobble_stone_mix_light"), new BlockItem(MBMBlocks.CD_CS_LIGHT, BUILDING_BLOCKS));
        
        //Horizontal Slab
        Registry.register(Registry.ITEM, new Identifier(ModName, "acacia_planks_horizontal_slab"), new BlockItem(MBMBlocks.ACACIA_PLANKS_HORIZONTAL_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "andesite_horizontal_slab"), new BlockItem(MBMBlocks.ANDESITE_HORIZONTAL_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "birch_planks_horizontal_slab"), new BlockItem(MBMBlocks.BIRCH_HORIZONTAL_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "black_terracotta_horizontal_slab"), new BlockItem(MBMBlocks.BLACK_TERRACOTTA_HORIZONTAL_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "blackstone_horizontal_slab"), new BlockItem(MBMBlocks.BLACKSTONE_HORIZONTAL_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "blackstone_bricks_horizontal_slab"), new BlockItem(MBMBlocks.BLACKSTONE_BRICKS_HORIZONTAL_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "blue_terracotta_horizontal_slab"), new BlockItem(MBMBlocks.BLUE_TERRACOTTA_HORIZONTAL_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "bricks_horizontal_slab"), new BlockItem(MBMBlocks.BRICKS_HORIZONTAL_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "brown_terracotta_horizontal_slab"), new BlockItem(MBMBlocks.BROWN_TERRACOTTA_HORIZONTAL_SLAB, BUILDING_BLOCKS));
        //Registry.register(Registry.ITEM, new Identifier(ModName, "chiseled_nether_bricks_horizontal_slab"), new BlockItem(MBMBlocks.CHISELED_NETHER_BRICKS_HORIZONTAL_SLAB, BUILDING_BLOCKS));
        //Registry.register(Registry.ITEM, new Identifier(ModName, "chiseled_polished_blackstone_horizontal_slab"), new BlockItem(MBMBlocks.CHISELED_POLISHED_BLACKSTONE_HORIZONTAL_SLAB, BUILDING_BLOCKS));
        //Registry.register(Registry.ITEM, new Identifier(ModName, "chiseled_red_sandstone_horizontal_slab"), new BlockItem(MBMBlocks.CHISELED_RED_SANDSTONE_HORIZONTAL_SLAB, BUILDING_BLOCKS));
        //Registry.register(Registry.ITEM, new Identifier(ModName, "chiseled_sandstone_horizontal_slab"), new BlockItem(MBMBlocks.CHISELED_SANDSTONE_HORIZONTAL_SLAB, BUILDING_BLOCKS));
        //Registry.register(Registry.ITEM, new Identifier(ModName, "chiseled_stone_bricks_horizontal_slab"), new BlockItem(MBMBlocks.CHISELED_STONE_BRICKS_HORIZONTAL_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "cobblestone_horizontal_slab"), new BlockItem(MBMBlocks.COBBLESTONE_HORIZONTAL_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "cracked_nether_bricks_horizontal_slab"), new BlockItem(MBMBlocks.CRACKED_NETHER_BRICKS_HORIZONTAL_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "cracked_stone_bricks_horizontal_slab"), new BlockItem(MBMBlocks.CRACKED_STONE_BRICKS_HORIZONTAL_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "crimson_planks_horizontal_slab"), new BlockItem(MBMBlocks.CRIMSON_PLANKS_HORIZONTAL_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "cut_red_sandstone_horizontal_slab"), new BlockItem(MBMBlocks.CUT_RED_SANDSTONE_HORIZONTAL_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "cut_sandstone_horizontal_slab"), new BlockItem(MBMBlocks.CUT_SANDSTONE_HORIZONTAL_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "cyan_terracotta_horizontal_slab"), new BlockItem(MBMBlocks.CYAN_TERRACOTTA_HORIZONTAL_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "dark_oak_planks_horizontal_slab"), new BlockItem(MBMBlocks.DARK_OAK_PLANKS_HORIZONTAL_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "dark_prismarine_horizontal_slab"), new BlockItem(MBMBlocks.DARK_PRISMARINE_HORIZONTAL_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "diorite_horizontal_slab"), new BlockItem(MBMBlocks.DIORITE_HORIZONTAL_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "end_stone_bricks_horizontal_slab"), new BlockItem(MBMBlocks.END_STONE_BRICKS_HORIZONTAL_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "granite_horizontal_slab"), new BlockItem(MBMBlocks.GRANITE_HORIZONTAL_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "gray_terracotta_horizontal_slab"), new BlockItem(MBMBlocks.GRAY_TERRACOTTA_HORIZONTAL_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "green_terracotta_horizontal_slab"), new BlockItem(MBMBlocks.GREEN_TERRACOTTA_HORIZONTAL_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "jungle_planks_horizontal_slab"), new BlockItem(MBMBlocks.JUNGLE_PLANKS_HORIZONTAL_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "light_blue_terracotta_horizontal_slab"), new BlockItem(MBMBlocks.LIGHT_BLUE_TERRACOTTA_HORIZONTAL_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "light_gray_terracotta_horizontal_slab"), new BlockItem(MBMBlocks.LIGHT_GRAY_TERRACOTTA_HORIZONTAL_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "lime_terracotta_horizontal_slab"), new BlockItem(MBMBlocks.LIME_TERRACOTTA_HORIZONTAL_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "magenta_terracotta_horizontal_slab"), new BlockItem(MBMBlocks.MAGENTA_TERRACOTTA_HORIZONTAL_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "mossy_cobblestone_horizontal_slab"), new BlockItem(MBMBlocks.MOSSY_COBBLESTONE_HORIZONTAL_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "mossy_stone_bricks_horizontal_slab"), new BlockItem(MBMBlocks.MOSSY_STONE_BRICKS_HORIZONTAL_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "nether_bricks_horizontal_slab"), new BlockItem(MBMBlocks.NETHER_BRICKS_HORIZONTAL_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "oak_planks_horizontal_slab"), new BlockItem(MBMBlocks.OAK_PLANKS_HORIZONTAL_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "orange_terracotta_horizontal_slab"), new BlockItem(MBMBlocks.ORANGE_TERRACOTTA_HORIZONTAL_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "pink_terracotta_horizontal_slab"), new BlockItem(MBMBlocks.PINK_TERRACOTTA_HORIZONTAL_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "polished_blackstone_horizontal_slab"), new BlockItem(MBMBlocks.POLISHED_BLACKSTONE_HORIZONTAL_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "polished_andesite_horizontal_slab"), new BlockItem(MBMBlocks.POLISHED_ANDESITE_HORIZONTAL_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "polished_diorite_horizontal_slab"), new BlockItem(MBMBlocks.POLISHED_DIORITE_HORIZONTAL_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "polished_granite_horizontal_slab"), new BlockItem(MBMBlocks.POLISHED_GRANITE_HORIZONTAL_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "prismarine_bricks_horizontal_slab"), new BlockItem(MBMBlocks.PRISMARINE_BRICKS_HORIZONTAL_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "prismarine_horizontal_slab"), new BlockItem(MBMBlocks.PRISMARINE_HORIZONTAL_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "purple_terracotta_horizontal_slab"), new BlockItem(MBMBlocks.PURPLE_TERRACOTTA_HORIZONTAL_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "purpur_block_horizontal_slab"), new BlockItem(MBMBlocks.PURPUR_BLOCK_HORIZONTAL_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "red_nether_bricks_horizontal_slab"), new BlockItem(MBMBlocks.RED_NETHER_BRICKS_HORIZONTAL_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "red_sandstone_horizontal_slab"), new BlockItem(MBMBlocks.RED_SANDSTONE_HORIZONTAL_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "red_terracotta_horizontal_slab"), new BlockItem(MBMBlocks.RED_TERRACOTTA_HORIZONTAL_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "sandstone_horizontal_slab"), new BlockItem(MBMBlocks.SANDSTONE_HORIZONTAL_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "smooth_red_sandstone_horizontal_slab"), new BlockItem(MBMBlocks.SMOOTH_RED_SANDSTONE_HORIZONTAL_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "smooth_sandstone_horizontal_slab"), new BlockItem(MBMBlocks.SMOOTH_SANDSTONE_HORIZONTAL_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "smooth_stone_horizontal_slab"), new BlockItem(MBMBlocks.SMOOTH_STONE_HORIZONTAL_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "spruce_planks_horizontal_slab"), new BlockItem(MBMBlocks.SPRUCE_PLANKS_HORIZONTAL_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "stone_bricks_horizontal_slab"), new BlockItem(MBMBlocks.STONE_BRICKS_HORIZONTAL_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "stone_horizontal_slab"), new BlockItem(MBMBlocks.STONE_HORIZONTAL_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "terracotta_horizontal_slab"), new BlockItem(MBMBlocks.TERRACOTTA_HORIZONTAL_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "warped_planks_horizontal_slab"), new BlockItem(MBMBlocks.WARPED_PLANKS_HORIZONTAL_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "white_terracotta_horizontal_slab"), new BlockItem(MBMBlocks.WHITE_TERRACOTTA_HORIZONTAL_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "yellow_terracotta_horizontal_slab"), new BlockItem(MBMBlocks.YELLOW_TERRACOTTA_HORIZONTAL_SLAB, BUILDING_BLOCKS));

        //Misc
        Registry.register(Registry.ITEM, new Identifier(ModName, "petrified_wood"), new BlockItem(MBMBlocks.PETRIFIED_WOOD, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "packed_mud"), new BlockItem(MBMBlocks.PACKEDMUD, BUILDING_BLOCKS));

        //Vase Block
        Registry.register(Registry.ITEM, new Identifier(ModName, "terracotta_vase"), new BlockItem(MBMBlocks.TERRACOTTA_VASE, BUILDING_BLOCKS));
        //Registry.register(Registry.ITEM, new Identifier(ModName, "black_terracotta_vase"), new BlockItem(MBMBlocks.BLACK_TERRACOTTA_VASE, BUILDING_BLOCKS));


        //Layer
        Registry.register(Registry.ITEM, new Identifier(ModName, "sand_layer"), new BlockItem(MBMBlocks.SAND_LAYER, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "red_sand_layer"), new BlockItem(MBMBlocks.RED_SAND_LAYER, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "gravel_layer"), new BlockItem(MBMBlocks.GRAVEL_LAYER, BUILDING_BLOCKS));


        Registry.register(Registry.ITEM, new Identifier(ModName, "black_concrete_powder_layer"), new BlockItem(MBMBlocks.BLACK_CONCRETE_POWDER_LAYER, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "red_concrete_powder_layer"), new BlockItem(MBMBlocks.RED_CONCRETE_POWDER_LAYER, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "green_concrete_powder_layer"), new BlockItem(MBMBlocks.GREEN_CONCRETE_POWDER_LAYER, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "brown_concrete_powder_layer"), new BlockItem(MBMBlocks.BROWN_CONCRETE_POWDER_LAYER, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "blue_concrete_powder_layer"), new BlockItem(MBMBlocks.BLUE_CONCRETE_POWDER_LAYER, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "purple_concrete_powder_layer"), new BlockItem(MBMBlocks.PURPLE_CONCRETE_POWDER_LAYER, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "light_gray_concrete_powder_layer"), new BlockItem(MBMBlocks.LIGHT_GRAY_CONCRETE_POWDER_LAYER, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "cyan_concrete_powder_layer"), new BlockItem(MBMBlocks.CYAN_CONCRETE_POWDER_LAYER, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "gray_concrete_powder_layer"), new BlockItem(MBMBlocks.GRAY_CONCRETE_POWDER_LAYER, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "pink_concrete_powder_layer"), new BlockItem(MBMBlocks.PINK_CONCRETE_POWDER_LAYER, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "lime_concrete_powder_layer"), new BlockItem(MBMBlocks.LIME_CONCRETE_POWDER_LAYER, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "yellow_concrete_powder_layer"), new BlockItem(MBMBlocks.YELLOW_CONCRETE_POWDER_LAYER, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "light_blue_concrete_powder_layer"), new BlockItem(MBMBlocks.LIGHT_BLUE_CONCRETE_POWDER_LAYER, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "magenta_concrete_powder_layer"), new BlockItem(MBMBlocks.MAGENTA_CONCRETE_POWDER_LAYER, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "orange_concrete_powder_layer"), new BlockItem(MBMBlocks.ORANGE_CONCRETE_POWDER_LAYER, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "white_concrete_powder_layer"), new BlockItem(MBMBlocks.WHITE_CONCRETE_POWDER_LAYER, BUILDING_BLOCKS));


        
        //Stone Variants
        Registry.register(Registry.ITEM, new Identifier(ModName, "stone_brick_tiles"), new BlockItem(MBMBlocks.STONE_BRICK_SQUARE, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "stone_brick_tiles_slab"), new BlockItem(MBMBlocks.STONE_BRICK_SQUARE_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "stone_brick_tiles_stairs"), new BlockItem(MBMBlocks.STONE_BRICK_SQUARE_STAIRS, BUILDING_BLOCKS));
        
        //Minecraft Slabs and Stairs
        Registry.register(Registry.ITEM, new Identifier(ModName, "dirt_slab"), new BlockItem(MBMBlocks.DIRT_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "coarse_dirt_slab"), new BlockItem(MBMBlocks.COARSE_DIRT_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "grass_block_slab"), new BlockItem(MBMBlocks.GRASS_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "snow_grass_block_slab"), new BlockItem(MBMBlocks.SNOW_GRASS_SLAB, BUILDING_BLOCKS));
        //Registry.register(Registry.ITEM, new Identifier(ModName, "cracked_stone_bricks_slab"), new BlockItem(MBMBlocks.COARSE_DIRT_SLAB, BUILDING_BLOCKS));
        //Registry.register(Registry.ITEM, new Identifier(ModName, "cracked_nether_bricks_slab"), new BlockItem(MBMBlocks.COARSE_DIRT_SLAB, BUILDING_BLOCKS));

        //Chimney
        Registry.register(Registry.ITEM, new Identifier(ModName, "cobblestone_chimney"), new BlockItem(MBMBlocks.COBBLESTONE_CHIMNEY, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "andesite_chimney"), new BlockItem(MBMBlocks.ANDESITE_CHIMNEY, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "granite_chimney"), new BlockItem(MBMBlocks.GRANITE_CHIMNEY, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "diorite_chimney"), new BlockItem(MBMBlocks.DIORITE_CHIMNEY, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "bricks_chimney"), new BlockItem(MBMBlocks.BRICKS_CHIMNEY, BUILDING_BLOCKS));


        Registry.register(Registry.ITEM, new Identifier(ModName, "rice_seeds"), RICE_SEEDS);
        Registry.register(Registry.ITEM, new Identifier(ModName, "rice_leaf_sheath"), RICE_LEAF_SHEATH);
        
        //Vertical Glass Pane
        Registry.register(Registry.ITEM, new Identifier(ModName, "vertical_glass_pane"), new BlockItem(MBMBlocks.VERTICAL_GLASS_PANE, BUILDING_BLOCKS));

        compostRegistry.add(RICE_SEEDS, 0.3f);
        compostRegistry.add(RICE_LEAF_SHEATH, 0.65f);
        compostRegistry.add(MBMBlocks.RICE_STRAW_BALE.asItem(), 0.85f);


        //Registry.register(Registry.ITEM, new Identifier(ModName, "pib"), new BlockItem(MBMBlocks.PIB, MISC));


        Registry.register(Registry.ITEM, new Identifier(ModName, "black_concrete_slab"), new BlockItem(MBMBlocks.BLACK_CONCRETE_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "red_concrete_slab"), new BlockItem(MBMBlocks.RED_CONCRETE_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "green_concrete_slab"), new BlockItem(MBMBlocks.GREEN_CONCRETE_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "brown_concrete_slab"), new BlockItem(MBMBlocks.BROWN_CONCRETE_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "blue_concrete_slab"), new BlockItem(MBMBlocks.BLUE_CONCRETE_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "purple_concrete_slab"), new BlockItem(MBMBlocks.PURPLE_CONCRETE_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "light_gray_concrete_slab"), new BlockItem(MBMBlocks.LIGHT_GRAY_CONCRETE_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "gray_concrete_slab"), new BlockItem(MBMBlocks.GRAY_CONCRETE_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "pink_concrete_slab"), new BlockItem(MBMBlocks.PINK_CONCRETE_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "lime_concrete_slab"), new BlockItem(MBMBlocks.LIME_CONCRETE_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "yellow_concrete_slab"), new BlockItem(MBMBlocks.YELLOW_CONCRETE_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "light_blue_concrete_slab"), new BlockItem(MBMBlocks.LIGHT_BLUE_CONCRETE_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "magenta_concrete_slab"), new BlockItem(MBMBlocks.MAGENTA_CONCRETE_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "orange_concrete_slab"), new BlockItem(MBMBlocks.ORANGE_CONCRETE_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "white_concrete_slab"), new BlockItem(MBMBlocks.WHITE_CONCRETE_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "cyan_concrete_slab"), new BlockItem(MBMBlocks.CYAN_CONCRETE_SLAB, BUILDING_BLOCKS));


        Registry.register(Registry.ITEM, new Identifier(ModName, "black_concrete_vertical_slab"), new BlockItem(MBMBlocks.BLACK_CONCRETE_VERTICAL_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "red_concrete_vertical_slab"), new BlockItem(MBMBlocks.RED_CONCRETE_VERTICAL_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "green_concrete_vertical_slab"), new BlockItem(MBMBlocks.GREEN_CONCRETE_VERTICAL_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "brown_concrete_vertical_slab"), new BlockItem(MBMBlocks.BROWN_CONCRETE_VERTICAL_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "blue_concrete_vertical_slab"), new BlockItem(MBMBlocks.BLUE_CONCRETE_VERTICAL_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "purple_concrete_vertical_slab"), new BlockItem(MBMBlocks.PURPLE_CONCRETE_VERTICAL_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "light_gray_concrete_vertical_slab"), new BlockItem(MBMBlocks.LIGHT_GRAY_CONCRETE_VERTICAL_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "gray_concrete_vertical_slab"), new BlockItem(MBMBlocks.GRAY_CONCRETE_VERTICAL_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "pink_concrete_vertical_slab"), new BlockItem(MBMBlocks.PINK_CONCRETE_VERTICAL_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "lime_concrete_vertical_slab"), new BlockItem(MBMBlocks.LIME_CONCRETE_VERTICAL_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "yellow_concrete_vertical_slab"), new BlockItem(MBMBlocks.YELLOW_CONCRETE_VERTICAL_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "light_blue_concrete_vertical_slab"), new BlockItem(MBMBlocks.LIGHT_BLUE_CONCRETE_VERTICAL_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "magenta_concrete_vertical_slab"), new BlockItem(MBMBlocks.MAGENTA_CONCRETE_VERTICAL_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "orange_concrete_vertical_slab"), new BlockItem(MBMBlocks.ORANGE_CONCRETE_VERTICAL_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "white_concrete_vertical_slab"), new BlockItem(MBMBlocks.WHITE_CONCRETE_VERTICAL_SLAB, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "cyan_concrete_vertical_slab"), new BlockItem(MBMBlocks.CYAN_CONCRETE_VERTICAL_SLAB, BUILDING_BLOCKS));


        Registry.register(Registry.ITEM, new Identifier(ModName, "black_concrete_stairs"), new BlockItem(MBMBlocks.BLACK_CONCRETE_STAIRS, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "red_concrete_stairs"), new BlockItem(MBMBlocks.RED_CONCRETE_STAIRS, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "green_concrete_stairs"), new BlockItem(MBMBlocks.GREEN_CONCRETE_STAIRS, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "brown_concrete_stairs"), new BlockItem(MBMBlocks.BROWN_CONCRETE_STAIRS, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "blue_concrete_stairs"), new BlockItem(MBMBlocks.BLUE_CONCRETE_STAIRS, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "purple_concrete_stairs"), new BlockItem(MBMBlocks.PURPLE_CONCRETE_STAIRS, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "light_gray_concrete_stairs"), new BlockItem(MBMBlocks.LIGHT_GRAY_CONCRETE_STAIRS, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "gray_concrete_stairs"), new BlockItem(MBMBlocks.GRAY_CONCRETE_STAIRS, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "pink_concrete_stairs"), new BlockItem(MBMBlocks.PINK_CONCRETE_STAIRS, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "lime_concrete_stairs"), new BlockItem(MBMBlocks.LIME_CONCRETE_STAIRS, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "yellow_concrete_stairs"), new BlockItem(MBMBlocks.YELLOW_CONCRETE_STAIRS, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "light_blue_concrete_stairs"), new BlockItem(MBMBlocks.LIGHT_BLUE_CONCRETE_STAIRS, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "magenta_concrete_stairs"), new BlockItem(MBMBlocks.MAGENTA_CONCRETE_STAIRS, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "orange_concrete_stairs"), new BlockItem(MBMBlocks.ORANGE_CONCRETE_STAIRS, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "white_concrete_stairs"), new BlockItem(MBMBlocks.WHITE_CONCRETE_STAIRS, BUILDING_BLOCKS));
        Registry.register(Registry.ITEM, new Identifier(ModName, "cyan_concrete_stairs"), new BlockItem(MBMBlocks.CYAN_CONCRETE_STAIRS, BUILDING_BLOCKS));


    }

}
