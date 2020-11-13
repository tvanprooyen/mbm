package com.tylervp;

import net.fabricmc.api.ModInitializer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ToolMaterials;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.GrassBlock;
import net.minecraft.block.LanternBlock;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.sound.BlockSoundGroup;

//! Block Names START
//CARVEDMELON
//DARK_STONE
//DARK_STONE_SLAB
//DARK_STONE_STAIRS
//DARK_STONE_BRICK
//DARK_STONE_BRICK_SLAB
//DARK_STONE_BRICK_STAIRS
//DARK_STONE_TILES
//DARK_STONE_TILES_SLAB
//DARK_STONE_TILES_STAIRS
//POLISHED_ANDESITE_HORIZONTAL_SLAB
//PACKEDMUD
//LEATHER_BLOCK
//PETRIFIED_WOOD
//ROPE
//ROPEMID
//THIN_ACACIA_LOG
//
//! Block Names END

public class moreblocksmod implements ModInitializer {

        //Quick Settings
        private static final FabricBlockSettings TERRACOTTA_SETTINGS = FabricBlockSettings.of(Material.STONE).requiresTool().breakByHand(false).breakByTool(FabricToolTags.PICKAXES).resistance(4.2f).hardness(1.25f).sounds(BlockSoundGroup.STONE);
        private static final FabricBlockSettings STONE_SETTINGS = FabricBlockSettings.of(Material.STONE).requiresTool().breakByHand(false).breakByTool(FabricToolTags.PICKAXES).resistance(6f).hardness(1.5f).sounds(BlockSoundGroup.STONE);
        private static final FabricBlockSettings THIN_LOG_SETTINGS = FabricBlockSettings.of(Material.WOOD).breakByTool(FabricToolTags.AXES).breakByHand(true).sounds(BlockSoundGroup.WOOD).resistance(2).hardness(2).ticksRandomly();
        private static final FabricBlockSettings GLASS_SETTINGS = FabricBlockSettings.of(Material.GLASS).breakByTool(FabricToolTags.PICKAXES).breakByHand(false).sounds(BlockSoundGroup.GLASS).resistance(0.3f).hardness(0.3f);
        private static final FabricBlockSettings PLANKS_SETTINGS = FabricBlockSettings.of(Material.WOOD).breakByTool(FabricToolTags.AXES).breakByHand(true).sounds(BlockSoundGroup.WOOD).resistance(3.0f).hardness(2.0f);
        private static final FabricBlockSettings NETHER_PLANKS_SETTINGS = FabricBlockSettings.of(Material.NETHER_WOOD).breakByTool(FabricToolTags.AXES).breakByHand(true).sounds(BlockSoundGroup.WOOD).resistance(3.0f).hardness(2.0f);
        private static final FabricBlockSettings SAND_STONE = FabricBlockSettings.of(Material.STONE).requiresTool().breakByHand(false).breakByTool(FabricToolTags.PICKAXES).resistance(0.8f).hardness(0.8f).sounds(BlockSoundGroup.STONE);

        //Chimney
        public static final ChimneyBlock COBBLESTONE_CHIMNEY = new ChimneyBlock(STONE_SETTINGS);
        public static final ChimneyBlock ANDESITE_CHIMNEY = new ChimneyBlock(STONE_SETTINGS);
        public static final ChimneyBlock GRANITE_CHIMNEY = new ChimneyBlock(STONE_SETTINGS);
        public static final ChimneyBlock DIORITE_CHIMNEY = new ChimneyBlock(STONE_SETTINGS);
        public static final ChimneyBlock BRICKS_CHIMNEY = new ChimneyBlock(STONE_SETTINGS); //H6,R2

        //Vertical Galss Pane
        public static final VerticalPaneBlock VERTICAL_GLASS_PANE = new VerticalPaneBlock(GLASS_SETTINGS);

        //Carved Melon Block
        public static final CarvedMelonBlock CARVEDMELON = new CarvedMelonBlock(FabricBlockSettings.of(Material.GOURD).breakByHand(true).hardness(1).resistance(1).breakByTool(FabricToolTags.SWORDS).breakByTool(FabricToolTags.AXES).sounds(BlockSoundGroup.WOOD));

        //Dark Stone
        public static final Block DARK_STONE = new Block(STONE_SETTINGS);
        public static final SlabBlock DARK_STONE_SLAB = new SlabBlock(STONE_SETTINGS);
        public static final StairsBlockExtend DARK_STONE_STAIRS = new StairsBlockExtend(DARK_STONE.getDefaultState(), STONE_SETTINGS);
    
        public static final Block DARK_STONE_BRICK = new Block(STONE_SETTINGS);
        public static final SlabBlock DARK_STONE_BRICK_SLAB = new SlabBlock(STONE_SETTINGS);
        public static final StairsBlockExtend DARK_STONE_BRICK_STAIRS = new StairsBlockExtend(DARK_STONE_BRICK.getDefaultState(), STONE_SETTINGS);

        public static final Block DARK_STONE_TILES = new Block(STONE_SETTINGS);
        public static final SlabBlock DARK_STONE_TILES_SLAB = new SlabBlock(STONE_SETTINGS);
        public static final StairsBlockExtend DARK_STONE_TILES_STAIRS = new StairsBlockExtend(DARK_STONE_TILES.getDefaultState(), STONE_SETTINGS);

        public static final Block DARK_COBBLESTONE = new Block(STONE_SETTINGS);
        public static final SlabBlock DARK_COBBLESTONE_SLAB = new SlabBlock(STONE_SETTINGS);
        public static final StairsBlockExtend DARK_COBBLESTONE_STAIRS = new StairsBlockExtend(DARK_COBBLESTONE.getDefaultState(), STONE_SETTINGS);
        
        public static final Block EMBLEMED_DARK_STONE = new Block(STONE_SETTINGS);

        //HorizontalBlocks
        public static final SideSlab ACACIA_PLANKS_HORIZONTAL_SLAB = new SideSlab(PLANKS_SETTINGS);
        public static final SideSlab ANDESITE_HORIZONTAL_SLAB = new SideSlab(STONE_SETTINGS);
        public static final SideSlab BIRCH_HORIZONTAL_SLAB = new SideSlab(PLANKS_SETTINGS);
        public static final SideSlab BLACK_TERRACOTTA_HORIZONTAL_SLAB = new SideSlab(TERRACOTTA_SETTINGS.materialColor(MaterialColor.BLACK_TERRACOTTA));
        public static final SideSlab BLACKSTONE_HORIZONTAL_SLAB = new SideSlab(STONE_SETTINGS);
        public static final SideSlab BLUE_TERRACOTTA_HORIZONTAL_SLAB = new SideSlab(TERRACOTTA_SETTINGS.materialColor(MaterialColor.BLUE_TERRACOTTA));
        public static final SideSlab BRICKS_HORIZONTAL_SLAB = new SideSlab(STONE_SETTINGS);
        public static final SideSlab BROWN_TERRACOTTA_HORIZONTAL_SLAB = new SideSlab(TERRACOTTA_SETTINGS.materialColor(MaterialColor.BROWN_TERRACOTTA));
        //public static final SideSlab CHISELED_NETHER_BRICKS_HORIZONTAL_SLAB = new SideSlab(STONE_SETTINGS);
        //public static final SideSlab CHISELED_POLISHED_BLACKSTONE_HORIZONTAL_SLAB = new SideSlab(STONE_SETTINGS);
        //public static final SideSlab CHISELED_RED_SANDSTONE_HORIZONTAL_SLAB = new SideSlab(SAND_STONE);
        //public static final SideSlab CHISELED_HORIZONTAL_SLAB = new SideSlab(STONE_SETTINGS);
        //public static final SideSlab CHISELED_SANDSTONE_HORIZONTAL_SLAB = new SideSlab(SAND_STONE);
        //public static final SideSlab CHISELED_STONE_BRICKS_HORIZONTAL_SLAB = new SideSlab(STONE_SETTINGS);
        public static final SideSlab COBBLESTONE_HORIZONTAL_SLAB = new SideSlab(STONE_SETTINGS);
        public static final SideSlab CRACKED_NETHER_BRICKS_HORIZONTAL_SLAB = new SideSlab(STONE_SETTINGS);
        public static final SideSlab CRACKED_STONE_BRICKS_HORIZONTAL_SLAB = new SideSlab(STONE_SETTINGS);
        public static final SideSlab CRIMSON_PLANKS_HORIZONTAL_SLAB = new SideSlab(NETHER_PLANKS_SETTINGS);
        public static final SideSlab CUT_RED_SANDSTONE_HORIZONTAL_SLAB = new SideSlab(SAND_STONE);
        public static final SideSlab CUT_SANDSTONE_HORIZONTAL_SLAB = new SideSlab(SAND_STONE);
        public static final SideSlab CYAN_TERRACOTTA_HORIZONTAL_SLAB = new SideSlab(TERRACOTTA_SETTINGS.materialColor(MaterialColor.CYAN_TERRACOTTA));
        public static final SideSlab DARK_OAK_PLANKS_HORIZONTAL_SLAB = new SideSlab(PLANKS_SETTINGS);
        public static final SideSlab DARK_PRISMARINE_HORIZONTAL_SLAB = new SideSlab(STONE_SETTINGS);
        public static final SideSlab DIORITE_HORIZONTAL_SLAB = new SideSlab(STONE_SETTINGS);
        public static final SideSlab END_STONE_BRICKS_HORIZONTAL_SLAB = new SideSlab(STONE_SETTINGS);
        public static final SideSlab GRANITE_HORIZONTAL_SLAB = new SideSlab(STONE_SETTINGS);
        public static final SideSlab GRAY_TERRACOTTA_HORIZONTAL_SLAB = new SideSlab(TERRACOTTA_SETTINGS.materialColor(MaterialColor.GRAY_TERRACOTTA));
        public static final SideSlab GREEN_TERRACOTTA_HORIZONTAL_SLAB = new SideSlab(TERRACOTTA_SETTINGS.materialColor(MaterialColor.GREEN_TERRACOTTA));
        public static final SideSlab JUNGLE_PLANKS_HORIZONTAL_SLAB = new SideSlab(PLANKS_SETTINGS);
        public static final SideSlab LIGHT_BLUE_TERRACOTTA_HORIZONTAL_SLAB = new SideSlab(TERRACOTTA_SETTINGS.materialColor(MaterialColor.LIGHT_BLUE_TERRACOTTA));
        public static final SideSlab LIGHT_GRAY_TERRACOTTA_HORIZONTAL_SLAB = new SideSlab(TERRACOTTA_SETTINGS.materialColor(MaterialColor.LIGHT_GRAY_TERRACOTTA));
        public static final SideSlab LIME_TERRACOTTA_HORIZONTAL_SLAB = new SideSlab(TERRACOTTA_SETTINGS.materialColor(MaterialColor.LIME_TERRACOTTA));
        public static final SideSlab MAGENTA_TERRACOTTA_HORIZONTAL_SLAB = new SideSlab(TERRACOTTA_SETTINGS.materialColor(MaterialColor.MAGENTA_TERRACOTTA));
        public static final SideSlab MOSSY_COBBLESTONE_HORIZONTAL_SLAB = new SideSlab(STONE_SETTINGS);
        public static final SideSlab MOSSY_STONE_BRICKS_HORIZONTAL_SLAB = new SideSlab(STONE_SETTINGS);
        public static final SideSlab NETHER_BRICKS_HORIZONTAL_SLAB = new SideSlab(STONE_SETTINGS);
        public static final SideSlab OAK_PLANKS_HORIZONTAL_SLAB = new SideSlab(PLANKS_SETTINGS);
        public static final SideSlab ORANGE_TERRACOTTA_HORIZONTAL_SLAB = new SideSlab(TERRACOTTA_SETTINGS.materialColor(MaterialColor.ORANGE_TERRACOTTA));
        public static final SideSlab PINK_TERRACOTTA_HORIZONTAL_SLAB = new SideSlab(TERRACOTTA_SETTINGS.materialColor(MaterialColor.PINK_TERRACOTTA));
        public static final SideSlab POLISHED_BLACKSTONE_HORIZONTAL_SLAB = new SideSlab(FabricBlockSettings.of(Material.STONE).requiresTool().breakByHand(false).breakByTool(FabricToolTags.PICKAXES).resistance(6.0f).hardness(2.0f).sounds(BlockSoundGroup.STONE));
        public static final SideSlab POLISHED_ANDESITE_HORIZONTAL_SLAB = new SideSlab(STONE_SETTINGS);
        public static final SideSlab POLISHED_DIORITE_HORIZONTAL_SLAB = new SideSlab(STONE_SETTINGS);
        public static final SideSlab POLISHED_GRANITE_HORIZONTAL_SLAB = new SideSlab(STONE_SETTINGS);
        public static final SideSlab PRISMARINE_BRICKS_HORIZONTAL_SLAB = new SideSlab(STONE_SETTINGS);
        public static final SideSlab PRISMARINE_HORIZONTAL_SLAB = new SideSlab(STONE_SETTINGS);
        public static final SideSlab PURPLE_TERRACOTTA_HORIZONTAL_SLAB = new SideSlab(TERRACOTTA_SETTINGS.materialColor(MaterialColor.PURPLE_TERRACOTTA));
        public static final SideSlab PURPUR_BLOCK_HORIZONTAL_SLAB = new SideSlab(STONE_SETTINGS);
        public static final SideSlab RED_NETHER_BRICKS_HORIZONTAL_SLAB = new SideSlab(STONE_SETTINGS);
        public static final SideSlab RED_SANDSTONE_HORIZONTAL_SLAB = new SideSlab(SAND_STONE);
        public static final SideSlab RED_TERRACOTTA_HORIZONTAL_SLAB = new SideSlab(TERRACOTTA_SETTINGS.materialColor(MaterialColor.RED_TERRACOTTA));
        public static final SideSlab SANDSTONE_HORIZONTAL_SLAB = new SideSlab(SAND_STONE);
        public static final SideSlab SMOOTH_RED_SANDSTONE_HORIZONTAL_SLAB = new SideSlab(FabricBlockSettings.of(Material.STONE).requiresTool().breakByHand(false).breakByTool(FabricToolTags.PICKAXES).resistance(6f).hardness(2.0f).sounds(BlockSoundGroup.STONE));
        public static final SideSlab SMOOTH_SANDSTONE_HORIZONTAL_SLAB = new SideSlab(FabricBlockSettings.of(Material.STONE).requiresTool().breakByHand(false).breakByTool(FabricToolTags.PICKAXES).resistance(6f).hardness(2.0f).sounds(BlockSoundGroup.STONE));
        public static final SideSlab SMOOTH_STONE_HORIZONTAL_SLAB = new SideSlab(STONE_SETTINGS);
        public static final SideSlab SPRUCE_PLANKS_HORIZONTAL_SLAB = new SideSlab(STONE_SETTINGS);
        public static final SideSlab STONE_BRICKS_HORIZONTAL_SLAB = new SideSlab(PLANKS_SETTINGS);
        public static final SideSlab STONE_HORIZONTAL_SLAB = new SideSlab(STONE_SETTINGS);
        public static final SideSlab TERRACOTTA_HORIZONTAL_SLAB = new SideSlab(TERRACOTTA_SETTINGS);
        public static final SideSlab WARPED_PLANKS_HORIZONTAL_SLAB = new SideSlab(NETHER_PLANKS_SETTINGS);
        public static final SideSlab WHITE_TERRACOTTA_HORIZONTAL_SLAB = new SideSlab(TERRACOTTA_SETTINGS.materialColor(MaterialColor.WHITE_TERRACOTTA));
        public static final SideSlab YELLOW_TERRACOTTA_HORIZONTAL_SLAB = new SideSlab(TERRACOTTA_SETTINGS.materialColor(MaterialColor.YELLOW_TERRACOTTA));


        //public static final SideSlab WHITE_TERRACOTTA_BRICK_WINDOW = new SideSlab(Block.Settings.of(Material.STONE));

        public static final LanternBlock LANTERN_ROPE = new LanternBlock(FabricBlockSettings.copy(Blocks.LANTERN));
        
        public static final PackedMudBlock PACKEDMUD = new PackedMudBlock(FabricBlockSettings.of(Material.SOIL).breakByHand(true).breakByTool(FabricToolTags.SHOVELS).sounds(BlockSoundGroup.SOUL_SOIL).hardness(2).resistance(5));

        //PillarBlocks
        public static final PillarBlock LEATHER_BLOCK = new PillarBlock(FabricBlockSettings.of(Material.WOOD).breakByHand(true).breakByTool(FabricToolTags.AXES).sounds(BlockSoundGroup.WOOD).hardness(2).resistance(5));
        public static final PetrifiedPillarBlock PETRIFIED_WOOD = new PetrifiedPillarBlock(FabricBlockSettings.of(Material.WOOD).breakByHand(true).breakByTool(FabricToolTags.AXES).sounds(BlockSoundGroup.WOOD).hardness(2).resistance(5));

        //Rope
        public static final RopeBlock ROPE = new RopeBlock(FabricBlockSettings.of(Material.WOOL).breakInstantly().breakByHand(true).sounds(BlockSoundGroup.WOOL).hardness(0).resistance(5));
        public static final RopeBlockMid ROPEMID = new RopeBlockMid(FabricBlockSettings.of(Material.WOOL).breakInstantly().breakByHand(true).sounds(BlockSoundGroup.WOOL).hardness(0).resistance(5));

        //Thin Logs and Log Related Items
        public static final ThinPillarBlock THIN_ACACIA_LOG = new ThinPillarBlock(THIN_LOG_SETTINGS);
        public static final ThinPillarBlock THIN_STRIPPED_ACACIA_LOG = new ThinPillarBlock(THIN_LOG_SETTINGS);
        public static final Item ACACIA_BARK_FRAGMENT = new Item(new Item.Settings().group(ItemGroup.MISC));

        public static final ThinPillarBlock THIN_BIRCH_LOG = new ThinPillarBlock(THIN_LOG_SETTINGS);
        public static final ThinPillarBlock THIN_STRIPPED_BIRCH_LOG = new ThinPillarBlock(THIN_LOG_SETTINGS);
        public static final Item BIRCH_BARK_FRAGMENT = new Item(new Item.Settings().group(ItemGroup.MISC));

        public static final ThinPillarBlock THIN_DARK_OAK_LOG = new ThinPillarBlock(THIN_LOG_SETTINGS);
        public static final ThinPillarBlock THIN_STRIPPED_DARK_OAK_LOG = new ThinPillarBlock(THIN_LOG_SETTINGS);
        public static final Item DARK_OAK_BARK_FRAGMENT = new Item(new Item.Settings().group(ItemGroup.MISC));

        public static final ThinPillarBlock THIN_JUNGLE_LOG = new ThinPillarBlock(THIN_LOG_SETTINGS);
        public static final ThinPillarBlock THIN_STRIPPED_JUNGLE_LOG = new ThinPillarBlock(THIN_LOG_SETTINGS);
        public static final Item JUNGLE_BARK_FRAGMENT = new Item(new Item.Settings().group(ItemGroup.MISC));
        
        public static final ThinPillarBlock THIN_OAK_LOG = new ThinPillarBlock(THIN_LOG_SETTINGS);
        public static final ThinPillarBlock THIN_STRIPPED_OAK_LOG = new ThinPillarBlock(THIN_LOG_SETTINGS);
        public static final Item OAK_BARK_FRAGMENT = new Item(new Item.Settings().group(ItemGroup.MISC));
        
        public static final ThinPillarBlock THIN_SPRUCE_LOG = new ThinPillarBlock(THIN_LOG_SETTINGS);
        public static final ThinPillarBlock THIN_STRIPPED_SPRUCE_LOG = new ThinPillarBlock(THIN_LOG_SETTINGS);
        public static final Item SPRUCE_BARK_FRAGMENT = new Item(new Item.Settings().group(ItemGroup.MISC));

        //Chopped Logs
        public static final ChoppedLog CHOPPED_ACACIA_LOG = new ChoppedLog(PLANKS_SETTINGS);
        public static final ChoppedLog CHOPPED_STRIPPED_ACACIA_LOG = new ChoppedLog(PLANKS_SETTINGS);

        public static final ChoppedLog CHOPPED_BIRCH_LOG = new ChoppedLog(PLANKS_SETTINGS);
        public static final ChoppedLog CHOPPED_STRIPPED_BIRCH_LOG = new ChoppedLog(PLANKS_SETTINGS);

        public static final ChoppedLog CHOPPED_DARK_OAK_LOG = new ChoppedLog(PLANKS_SETTINGS);
        public static final ChoppedLog CHOPPED_STRIPPED_DARK_OAK_LOG = new ChoppedLog(PLANKS_SETTINGS);

        public static final ChoppedLog CHOPPED_JUNGLE_LOG = new ChoppedLog(PLANKS_SETTINGS);
        public static final ChoppedLog CHOPPED_STRIPPED_JUNGLE_LOG = new ChoppedLog(PLANKS_SETTINGS);

        public static final ChoppedLog CHOPPED_OAK_LOG = new ChoppedLog(PLANKS_SETTINGS);
        public static final ChoppedLog CHOPPED_STRIPPED_OAK_LOG = new ChoppedLog(PLANKS_SETTINGS);

        public static final ChoppedLog CHOPPED_SPRUCE_LOG = new ChoppedLog(PLANKS_SETTINGS);
        public static final ChoppedLog CHOPPED_STRIPPED_SPRUCE_LOG = new ChoppedLog(PLANKS_SETTINGS);
        

        //Custom Tools
        //public static final NetheriteHammer NETHERITE_HAMMER = new NetheriteHammer(new Item.Settings().group(ItemGroup.TOOLS).fireproof());
        public static final NetheriteHammer NETHERITE_HAMMER = new NetheriteHammer(ToolMaterials.NETHERITE, new Item.Settings().group(ItemGroup.TOOLS).fireproof());

        //Transistion Blocks
        public static final Block CD_CS_LIGHT = new Block(STONE_SETTINGS);
        public static final Block CD_CS_HEAVY = new Block(STONE_SETTINGS);

        public static final Block SD_SB_HEAVY = new Block(STONE_SETTINGS);
        public static final Block SD_SB_LIGHT = new Block(STONE_SETTINGS);

        public static final Block SD_CSB_LIGHT = new Block(STONE_SETTINGS);

        //Dead Blocks
        public static final GrassBlock DEAD_GRASS_BLOCK = new GrassBlock(FabricBlockSettings.of(Material.SOIL).sounds(BlockSoundGroup.GRASS));
        
        public static final PillarBlock BURNT_LOG = new PillarBlock(FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).breakByTool(FabricToolTags.AXES).breakByHand(true).hardness(2f).resistance(2f));

        public static final Block BURNT_PLANKS = new Block(FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).breakByTool(FabricToolTags.AXES).breakByHand(true).hardness(2f).resistance(3f));
        public static final SlabBlock BURNT_PLANKS_SLAB = new SlabBlock(FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).breakByTool(FabricToolTags.AXES).breakByHand(true).hardness(2f).resistance(3f));
        public static final StairsBlockExtend BURNT_PLANKS_STAIRS = new StairsBlockExtend(BURNT_PLANKS.getDefaultState(), FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).breakByTool(FabricToolTags.AXES).breakByHand(true).hardness(2f).resistance(3f));


        //LayerBlock
        public static final LayerBlock SAND_LAYER = new LayerBlock(FabricBlockSettings.of(Material.AGGREGATE).sounds(BlockSoundGroup.SAND).hardness(0.5f).resistance(0.5f));
        public static final LayerBlock RED_SAND_LAYER = new LayerBlock(FabricBlockSettings.of(Material.AGGREGATE).sounds(BlockSoundGroup.SAND).hardness(0.5f).resistance(0.5f));
        public static final LayerBlock GRAVEL_LAYER = new LayerBlock(FabricBlockSettings.of(Material.AGGREGATE).sounds(BlockSoundGroup.GRAVEL).hardness(0.5f).resistance(0.5f));

        //Terracotta Stairs and Slabs
        public static final SlabBlock WHITE_TERRACOTTA_SLAB = new SlabBlock(TERRACOTTA_SETTINGS);
        public static final StairsBlockExtend WHITE_TERRACOTTA_STAIRS = new StairsBlockExtend(Blocks.WHITE_TERRACOTTA.getDefaultState(), TERRACOTTA_SETTINGS);

        public static final SlabBlock RED_TERRACOTTA_SLAB = new SlabBlock(TERRACOTTA_SETTINGS);
        public static final StairsBlockExtend RED_TERRACOTTA_STAIRS = new StairsBlockExtend(Blocks.RED_TERRACOTTA.getDefaultState(), TERRACOTTA_SETTINGS);

        public static final SlabBlock CYAN_TERRACOTTA_SLAB = new SlabBlock(TERRACOTTA_SETTINGS);
        public static final StairsBlockExtend CYAN_TERRACOTTA_STAIRS = new StairsBlockExtend(Blocks.CYAN_TERRACOTTA.getDefaultState(), TERRACOTTA_SETTINGS);

        //Packed Terracotta
        public static final Block YELLOW_PACKED_TERRACOTTA = new Block(TERRACOTTA_SETTINGS.materialColor(MaterialColor.YELLOW_TERRACOTTA));
        
        //Terracotta Bricks
        public static final Block TERRACOTTA_BRICKS = new Block(TERRACOTTA_SETTINGS);
        public static final SlabBlock TERRACOTTA_BRICKS_SLAB = new SlabBlock(TERRACOTTA_SETTINGS);
        public static final StairsBlockExtend TERRACOTTA_BRICKS_STAIRS = new StairsBlockExtend(TERRACOTTA_BRICKS.getDefaultState(), TERRACOTTA_SETTINGS);

        public static final Block WHITE_TERRACOTTA_BRICKS= new Block(TERRACOTTA_SETTINGS.materialColor(MaterialColor.WHITE_TERRACOTTA));
        public static final SlabBlock WHITE_TERRACOTTA_BRICKS_SLAB = new SlabBlock(TERRACOTTA_SETTINGS.materialColor(MaterialColor.WHITE_TERRACOTTA));
        public static final StairsBlockExtend WHITE_TERRACOTTA_BRICKS_STAIRS = new StairsBlockExtend(WHITE_TERRACOTTA_BRICKS.getDefaultState(), TERRACOTTA_SETTINGS.materialColor(MaterialColor.WHITE_TERRACOTTA));

        public static final Block RED_TERRACOTTA_BRICKS= new Block(TERRACOTTA_SETTINGS.materialColor(MaterialColor.RED_TERRACOTTA));
        public static final StairsBlockExtend RED_TERRACOTTA_BRICKS_STAIRS = new StairsBlockExtend(RED_TERRACOTTA_BRICKS.getDefaultState(), TERRACOTTA_SETTINGS.materialColor(MaterialColor.RED_TERRACOTTA));
        public static final SlabBlock RED_TERRACOTTA_BRICKS_SLAB = new SlabBlock(TERRACOTTA_SETTINGS.materialColor(MaterialColor.RED_TERRACOTTA));

        public static final Block BROWN_TERRACOTTA_BRICKS = new Block(TERRACOTTA_SETTINGS.materialColor(MaterialColor.BROWN_TERRACOTTA));
        public static final SlabBlock BROWN_TERRACOTTA_BRICKS_SLAB = new SlabBlock(TERRACOTTA_SETTINGS.materialColor(MaterialColor.BROWN_TERRACOTTA));
        public static final StairsBlockExtend BROWN_TERRACOTTA_BRICKS_STAIRS = new StairsBlockExtend(BROWN_TERRACOTTA_BRICKS.getDefaultState(), TERRACOTTA_SETTINGS.materialColor(MaterialColor.BROWN_TERRACOTTA));

        public static final Block CYAN_TERRACOTTA_BRICKS = new Block(TERRACOTTA_SETTINGS.materialColor(MaterialColor.CYAN_TERRACOTTA));
        public static final SlabBlock CYAN_TERRACOTTA_BRICKS_SLAB = new SlabBlock(TERRACOTTA_SETTINGS.materialColor(MaterialColor.CYAN_TERRACOTTA));
        public static final StairsBlockExtend CYAN_TERRACOTTA_BRICKS_STAIRS = new StairsBlockExtend(CYAN_TERRACOTTA_BRICKS.getDefaultState(), TERRACOTTA_SETTINGS.materialColor(MaterialColor.CYAN_TERRACOTTA));

        public static final Block BLACK_TERRACOTTA_BRICKS = new Block(TERRACOTTA_SETTINGS.materialColor(MaterialColor.BLACK_TERRACOTTA));
        public static final SlabBlock BLACK_TERRACOTTA_BRICKS_SLAB = new SlabBlock(TERRACOTTA_SETTINGS.materialColor(MaterialColor.BLACK_TERRACOTTA));
        public static final StairsBlockExtend BLACK_TERRACOTTA_BRICKS_STAIRS = new StairsBlockExtend(BLACK_TERRACOTTA_BRICKS.getDefaultState(), TERRACOTTA_SETTINGS.materialColor(MaterialColor.BLACK_TERRACOTTA));

        public static final Block GRAY_TERRACOTTA_BRICKS = new Block(TERRACOTTA_SETTINGS.materialColor(MaterialColor.GRAY_TERRACOTTA));
        public static final SlabBlock GRAY_TERRACOTTA_BRICKS_SLAB = new SlabBlock(TERRACOTTA_SETTINGS.materialColor(MaterialColor.GRAY_TERRACOTTA));
        public static final StairsBlockExtend GRAY_TERRACOTTA_BRICKS_STAIRS = new StairsBlockExtend(GRAY_TERRACOTTA_BRICKS.getDefaultState(), TERRACOTTA_SETTINGS.materialColor(MaterialColor.GRAY_TERRACOTTA));

        public static final Block GREEN_TERRACOTTA_BRICKS = new Block(TERRACOTTA_SETTINGS.materialColor(MaterialColor.GREEN_TERRACOTTA));
        public static final SlabBlock GREEN_TERRACOTTA_BRICKS_SLAB = new SlabBlock(TERRACOTTA_SETTINGS.materialColor(MaterialColor.GREEN_TERRACOTTA));
        public static final StairsBlockExtend GREEN_TERRACOTTA_BRICKS_STAIRS = new StairsBlockExtend(GREEN_TERRACOTTA_BRICKS.getDefaultState(), TERRACOTTA_SETTINGS.materialColor(MaterialColor.GREEN_TERRACOTTA));

        public static final Block LIGHT_BLUE_TERRACOTTA_BRICKS = new Block(TERRACOTTA_SETTINGS.materialColor(MaterialColor.LIGHT_BLUE_TERRACOTTA));
        public static final SlabBlock LIGHT_BLUE_TERRACOTTA_BRICKS_SLAB = new SlabBlock(TERRACOTTA_SETTINGS.materialColor(MaterialColor.LIGHT_BLUE_TERRACOTTA));
        public static final StairsBlockExtend LIGHT_BLUE_TERRACOTTA_BRICKS_STAIRS = new StairsBlockExtend(LIGHT_BLUE_TERRACOTTA_BRICKS.getDefaultState(), TERRACOTTA_SETTINGS.materialColor(MaterialColor.LIGHT_BLUE_TERRACOTTA));

        public static final Block LIGHT_GRAY_TERRACOTTA_BRICKS = new Block(TERRACOTTA_SETTINGS.materialColor(MaterialColor.LIGHT_GRAY_TERRACOTTA));
        public static final SlabBlock LIGHT_GRAY_TERRACOTTA_BRICKS_SLAB = new SlabBlock(TERRACOTTA_SETTINGS.materialColor(MaterialColor.LIGHT_GRAY_TERRACOTTA));
        public static final StairsBlockExtend LIGHT_GRAY_TERRACOTTA_BRICKS_STAIRS = new StairsBlockExtend(LIGHT_GRAY_TERRACOTTA_BRICKS.getDefaultState(), TERRACOTTA_SETTINGS.materialColor(MaterialColor.LIGHT_GRAY_TERRACOTTA));

        public static final Block PINK_TERRACOTTA_BRICKS = new Block(TERRACOTTA_SETTINGS.materialColor(MaterialColor.PINK_TERRACOTTA));
        public static final SlabBlock PINK_TERRACOTTA_BRICKS_SLAB = new SlabBlock(TERRACOTTA_SETTINGS.materialColor(MaterialColor.PINK_TERRACOTTA));
        public static final StairsBlockExtend PINK_TERRACOTTA_BRICKS_STAIRS = new StairsBlockExtend(PINK_TERRACOTTA_BRICKS.getDefaultState(), TERRACOTTA_SETTINGS.materialColor(MaterialColor.PINK_TERRACOTTA));

        public static final Block PURPLE_TERRACOTTA_BRICKS = new Block(TERRACOTTA_SETTINGS.materialColor(MaterialColor.PURPLE_TERRACOTTA));
        public static final SlabBlock PURPLE_TERRACOTTA_BRICKS_SLAB = new SlabBlock(TERRACOTTA_SETTINGS.materialColor(MaterialColor.PURPLE_TERRACOTTA));
        public static final StairsBlockExtend PURPLE_TERRACOTTA_BRICKS_STAIRS = new StairsBlockExtend(PURPLE_TERRACOTTA_BRICKS.getDefaultState(), TERRACOTTA_SETTINGS.materialColor(MaterialColor.PURPLE_TERRACOTTA));

        public static final Block BLUE_TERRACOTTA_BRICKS= new Block(TERRACOTTA_SETTINGS.materialColor(MaterialColor.BLUE_TERRACOTTA));
        public static final SlabBlock BLUE_TERRACOTTA_BRICKS_SLAB = new SlabBlock(TERRACOTTA_SETTINGS.materialColor(MaterialColor.BLUE_TERRACOTTA));
        public static final StairsBlockExtend BLUE_TERRACOTTA_BRICKS_STAIRS = new StairsBlockExtend(BLUE_TERRACOTTA_BRICKS.getDefaultState(), TERRACOTTA_SETTINGS.materialColor(MaterialColor.BLUE_TERRACOTTA));

        public static final Block YELLOW_TERRACOTTA_BRICKS = new Block(TERRACOTTA_SETTINGS.materialColor(MaterialColor.YELLOW_TERRACOTTA));
        public static final SlabBlock YELLOW_TERRACOTTA_BRICKS_SLAB = new SlabBlock(TERRACOTTA_SETTINGS.materialColor(MaterialColor.YELLOW_TERRACOTTA));
        public static final StairsBlockExtend YELLOW_TERRACOTTA_BRICKS_STAIRS = new StairsBlockExtend(YELLOW_TERRACOTTA_BRICKS.getDefaultState(), TERRACOTTA_SETTINGS.materialColor(MaterialColor.YELLOW_TERRACOTTA));

        public static final Block ORANGE_TERRACOTTA_BRICKS = new Block(TERRACOTTA_SETTINGS.materialColor(MaterialColor.ORANGE_TERRACOTTA));
        public static final SlabBlock ORANGE_TERRACOTTA_BRICKS_SLAB = new SlabBlock(TERRACOTTA_SETTINGS.materialColor(MaterialColor.ORANGE_TERRACOTTA));
        public static final StairsBlockExtend ORANGE_TERRACOTTA_BRICKS_STAIRS = new StairsBlockExtend(ORANGE_TERRACOTTA_BRICKS.getDefaultState(), TERRACOTTA_SETTINGS.materialColor(MaterialColor.ORANGE_TERRACOTTA));

        public static final Block MAGENTA_TERRACOTTA_BRICKS = new Block(TERRACOTTA_SETTINGS.materialColor(MaterialColor.MAGENTA_TERRACOTTA));
        public static final SlabBlock MAGENTA_TERRACOTTA_BRICKS_SLAB = new SlabBlock(TERRACOTTA_SETTINGS.materialColor(MaterialColor.MAGENTA_TERRACOTTA));
        public static final StairsBlockExtend MAGENTA_TERRACOTTA_BRICKS_STAIRS = new StairsBlockExtend(MAGENTA_TERRACOTTA_BRICKS.getDefaultState(), TERRACOTTA_SETTINGS.materialColor(MaterialColor.MAGENTA_TERRACOTTA));

        public static final Block LIME_TERRACOTTA_BRICKS = new Block(TERRACOTTA_SETTINGS.materialColor(MaterialColor.LIME_TERRACOTTA));
        public static final SlabBlock LIME_TERRACOTTA_BRICKS_SLAB = new SlabBlock(TERRACOTTA_SETTINGS.materialColor(MaterialColor.LIME_TERRACOTTA));
        public static final StairsBlockExtend LIME_TERRACOTTA_BRICKS_STAIRS = new StairsBlockExtend(LIME_TERRACOTTA_BRICKS.getDefaultState(), TERRACOTTA_SETTINGS.materialColor(MaterialColor.LIME_TERRACOTTA));

        //Other Stone Variants
        public static final Block STONE_BRICK_SQUARE = new Block(FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES).breakByHand(false).hardness(1.5f).resistance(6f));
        public static final SlabBlock STONE_BRICK_SQUARE_SLAB = new SlabBlock(FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES).breakByHand(false).hardness(1.5f).resistance(6f));
        public static final StairsBlockExtend STONE_BRICK_SQUARE_STAIRS = new StairsBlockExtend(STONE_BRICK_SQUARE.getDefaultState(), FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES).breakByHand(false).hardness(1.5f).resistance(6f));

        //Minecraft Stairs and Slabs
        public static final SlabBlock DIRT_SLAB = new SlabBlock(FabricBlockSettings.of(Material.SOIL).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.SHOVELS).breakByHand(true).hardness(0.5f).resistance(0.5f));
        public static final SlabBlock COARSE_DIRT_SLAB = new SlabBlock(FabricBlockSettings.of(Material.SOIL).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.SHOVELS).breakByHand(true).hardness(0.5f).resistance(0.5f));  

        //public static MBMBlocks BLOCKS = new MBMBlocks();

//! End Block Definition


     //public static final GourdBlock CARVED_MELON = new GourdBlock(FabricBlockSettings.of(Material.PUMPKIN));

     public static FlowableFluid STILL_MUD;
     public static FlowableFluid FLOWING_MUD;
     public static Item MUD_BUCKET;
     public static MudBlock MUD;

     @Override
     public void onInitialize() {

                STILL_MUD = Registry.register(Registry.FLUID, new Identifier("moreblocksmod", "mud"), new MudFluid.Still());
                FLOWING_MUD = Registry.register(Registry.FLUID, new Identifier("moreblocksmod", "flowing_mud"), new MudFluid.Flowing());
                MUD = Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "mud"), new MudBlock(STILL_MUD, FabricBlockSettings.copy(Blocks.WATER)){});
 
                MoreBlocksModClientIni.setupFluidRendering(moreblocksmod.STILL_MUD, moreblocksmod.FLOWING_MUD, new Identifier("moreblocksmod", "mud"), -1);
		BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getSolid(), moreblocksmod.STILL_MUD, moreblocksmod.FLOWING_MUD);
                
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "leather_block"), LEATHER_BLOCK);

                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "rope"), ROPE);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "rope_mid"), ROPEMID);

                BlockRenderLayerMap.INSTANCE.putBlock(ROPE, RenderLayer.getCutout());
                BlockRenderLayerMap.INSTANCE.putBlock(ROPEMID, RenderLayer.getCutout());

                
                

                //ACACIA
                //Regular
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "thin_acacia_log"), THIN_ACACIA_LOG);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "thin_stripped_acacia_log"), THIN_STRIPPED_ACACIA_LOG);
                
                //Cutout Empty Space
                BlockRenderLayerMap.INSTANCE.putBlock(THIN_STRIPPED_ACACIA_LOG, RenderLayer.getCutout());
                BlockRenderLayerMap.INSTANCE.putBlock(THIN_ACACIA_LOG, RenderLayer.getCutout());
                
                ColorProviderRegistry.BLOCK.register((state,view,pos,tintIndex) -> BiomeColors.getFoliageColor(view, pos), THIN_ACACIA_LOG);
                //END ACACIA


                //BIRCH
                //Regular
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "thin_birch_log"), THIN_BIRCH_LOG);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "thin_stripped_birch_log"), THIN_STRIPPED_BIRCH_LOG);
                
                //Cutout Empty Space
                BlockRenderLayerMap.INSTANCE.putBlock(THIN_STRIPPED_BIRCH_LOG, RenderLayer.getCutout());
                BlockRenderLayerMap.INSTANCE.putBlock(THIN_BIRCH_LOG, RenderLayer.getCutout());
                
                ColorProviderRegistry.BLOCK.register((state,view,pos,tintIndex) -> FoliageColors.getBirchColor(), THIN_BIRCH_LOG);
                //END BIRCH


                //DARK OAK
                //Regular
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "thin_dark_oak_log"), THIN_DARK_OAK_LOG);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "thin_stripped_dark_oak_log"), THIN_STRIPPED_DARK_OAK_LOG);
                
                //Cutout Empty Space
                BlockRenderLayerMap.INSTANCE.putBlock(THIN_STRIPPED_DARK_OAK_LOG, RenderLayer.getCutout());
                BlockRenderLayerMap.INSTANCE.putBlock(THIN_DARK_OAK_LOG, RenderLayer.getCutout());
                
                ColorProviderRegistry.BLOCK.register((state,view,pos,tintIndex) -> BiomeColors.getFoliageColor(view, pos), THIN_DARK_OAK_LOG);
                //END DARK OAK


                //JUNGLE
                //Regular
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "thin_jungle_log"), THIN_JUNGLE_LOG);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "thin_stripped_jungle_log"), THIN_STRIPPED_JUNGLE_LOG);

                //Cutout Empty Space
                BlockRenderLayerMap.INSTANCE.putBlock(THIN_STRIPPED_JUNGLE_LOG, RenderLayer.getCutout());
                BlockRenderLayerMap.INSTANCE.putBlock(THIN_JUNGLE_LOG, RenderLayer.getCutout());
                
                ColorProviderRegistry.BLOCK.register((state,view,pos,tintIndex) -> BiomeColors.getFoliageColor(view, pos), THIN_JUNGLE_LOG);
                //END JUNGLE


                //OAK
                //Regular
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "thin_oak_log"), THIN_OAK_LOG);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "thin_stripped_oak_log"), THIN_STRIPPED_OAK_LOG);

                //Cutout Empty Space
                BlockRenderLayerMap.INSTANCE.putBlock(THIN_STRIPPED_OAK_LOG, RenderLayer.getCutout());
                BlockRenderLayerMap.INSTANCE.putBlock(THIN_OAK_LOG, RenderLayer.getCutout());

                ColorProviderRegistry.BLOCK.register((state,view,pos,tintIndex) -> BiomeColors.getFoliageColor(view, pos), THIN_OAK_LOG);
                //END OAK


                //SPRUCE
                //Regular
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "thin_spruce_log"), THIN_SPRUCE_LOG);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "thin_stripped_spruce_log"), THIN_STRIPPED_SPRUCE_LOG);
                
                //When Stripped Drop Bark Fragment

                //Cutout Empty Space
                BlockRenderLayerMap.INSTANCE.putBlock(THIN_SPRUCE_LOG, RenderLayer.getCutout());
                BlockRenderLayerMap.INSTANCE.putBlock(THIN_STRIPPED_SPRUCE_LOG, RenderLayer.getCutout());

                ColorProviderRegistry.BLOCK.register((state,view,pos,tintIndex) -> FoliageColors.getSpruceColor(), THIN_SPRUCE_LOG);//0x619961
                //END SPRUCE

                
                //Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "white_terracotta_brick_window"), WHITE_TERRACOTTA_BRICK_WINDOW);
                //Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "white_terracotta_brick_window"), new BlockItem(WHITE_TERRACOTTA_BRICK_WINDOW, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
                
                //Chopped Log
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "chopped_acacia_log"), CHOPPED_ACACIA_LOG);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "chopped_stripped_acacia_log"), CHOPPED_STRIPPED_ACACIA_LOG);

                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "chopped_birch_log"), CHOPPED_BIRCH_LOG);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "chopped_stripped_birch_log"), CHOPPED_STRIPPED_BIRCH_LOG);

                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "chopped_dark_oak_log"), CHOPPED_DARK_OAK_LOG);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "chopped_stripped_dark_oak_log"), CHOPPED_STRIPPED_DARK_OAK_LOG);

                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "chopped_jungle_log"), CHOPPED_JUNGLE_LOG);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "chopped_stripped_jungle_log"), CHOPPED_STRIPPED_JUNGLE_LOG);

                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "chopped_oak_log"), CHOPPED_OAK_LOG);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "chopped_stripped_oak_log"), CHOPPED_STRIPPED_OAK_LOG);

                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "chopped_spruce_log"), CHOPPED_SPRUCE_LOG);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "chopped_stripped_spruce_log"), CHOPPED_STRIPPED_SPRUCE_LOG);

                //Dead Grass Block
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "dead_grass_block"), DEAD_GRASS_BLOCK);
                
                ColorProviderRegistry.BLOCK.register((state,view,pos,tintIndex) -> 0Xffab44, DEAD_GRASS_BLOCK);
                BlockRenderLayerMap.INSTANCE.putBlock(DEAD_GRASS_BLOCK, RenderLayer.getCutout());
                

                //Carved Melon
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "carved_melon"), CARVEDMELON);
                
                //Dark Stone
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "dark_stone"), DARK_STONE);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "dark_stone_slab"), DARK_STONE_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "dark_stone_stairs"), DARK_STONE_STAIRS);

                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "dark_stone_bricks"), DARK_STONE_BRICK);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "dark_stone_brick_slab"), DARK_STONE_BRICK_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "dark_stone_brick_stairs"), DARK_STONE_BRICK_STAIRS);

                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "dark_stone_tiles"), DARK_STONE_TILES);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "dark_stone_tiles_slab"), DARK_STONE_TILES_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "dark_stone_tiles_stairs"), DARK_STONE_TILES_STAIRS);

                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "dark_cobblestone"), DARK_COBBLESTONE);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "dark_cobblestone_slab"), DARK_COBBLESTONE_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "dark_cobblestone_stairs"), DARK_COBBLESTONE_STAIRS);

                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "emblemed_dark_stone"), EMBLEMED_DARK_STONE);
                
                //Packed Terracotta
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "yellow_packed_terracotta"), YELLOW_PACKED_TERRACOTTA);
                
                
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "yellow_terracotta_bricks"), YELLOW_TERRACOTTA_BRICKS);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "yellow_terracotta_bricks_slab"), YELLOW_TERRACOTTA_BRICKS_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "yellow_terracotta_bricks_stairs"), YELLOW_TERRACOTTA_BRICKS_STAIRS);
        
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "orange_terracotta_bricks"), ORANGE_TERRACOTTA_BRICKS);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "orange_terracotta_bricks_slab"), ORANGE_TERRACOTTA_BRICKS_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "orange_terracotta_bricks_stairs"), ORANGE_TERRACOTTA_BRICKS_STAIRS);
                
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "magenta_terracotta_bricks"), MAGENTA_TERRACOTTA_BRICKS);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "magenta_terracotta_bricks_slab"), MAGENTA_TERRACOTTA_BRICKS_SLAB);
                
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "magenta_terracotta_bricks_stairs"), MAGENTA_TERRACOTTA_BRICKS_STAIRS);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "lime_terracotta_bricks"), LIME_TERRACOTTA_BRICKS);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "lime_terracotta_bricks_slab"), LIME_TERRACOTTA_BRICKS_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "lime_terracotta_bricks_stairs"), LIME_TERRACOTTA_BRICKS_STAIRS);
                
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "packed_mud"), PACKEDMUD);
                
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "petrified_wood"), PETRIFIED_WOOD);
                
                //Side Slab
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "acacia_planks_horizontal_slab"), ACACIA_PLANKS_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "andesite_horizontal_slab"), ANDESITE_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "birch_planks_horizontal_slab"), BIRCH_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "black_terracotta_horizontal_slab"), BLACK_TERRACOTTA_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "blackstone_horizontal_slab"), BLACKSTONE_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "blue_terracotta_horizontal_slab"), BLUE_TERRACOTTA_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "bricks_horizontal_slab"), BRICKS_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "brown_terracotta_horizontal_slab"), BROWN_TERRACOTTA_HORIZONTAL_SLAB);
               // Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "chiseled_nether_bricks_horizontal_slab"), CHISELED_NETHER_BRICKS_HORIZONTAL_SLAB);
                //Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "chiseled_polished_blackstone_horizontal_slab"), CHISELED_POLISHED_BLACKSTONE_HORIZONTAL_SLAB);
                //Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "chiseled_red_sandstone_horizontal_slab"), CHISELED_RED_SANDSTONE_HORIZONTAL_SLAB);
                //Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "chiseled_sandstone_horizontal_slab"), CHISELED_SANDSTONE_HORIZONTAL_SLAB);
                //Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "chiseled_stone_bricks_horizontal_slab"), CHISELED_STONE_BRICKS_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "cobblestone_horizontal_slab"), COBBLESTONE_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "cracked_nether_bricks_horizontal_slab"), CRACKED_NETHER_BRICKS_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "cracked_stone_bricks_horizontal_slab"), CRACKED_STONE_BRICKS_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "crimson_planks_horizontal_slab"), CRIMSON_PLANKS_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "cut_red_sandstone_horizontal_slab"), CUT_RED_SANDSTONE_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "cut_sandstone_horizontal_slab"), CUT_SANDSTONE_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "cyan_terracotta_horizontal_slab"), CYAN_TERRACOTTA_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "dark_oak_planks_horizontal_slab"), DARK_OAK_PLANKS_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "dark_prismarine_horizontal_slab"), DARK_PRISMARINE_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "diorite_horizontal_slab"), DIORITE_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "end_stone_bricks_horizontal_slab"), END_STONE_BRICKS_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "granite_horizontal_slab"), GRANITE_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "gray_terracotta_horizontal_slab"), GRAY_TERRACOTTA_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "green_terracotta_horizontal_slab"), GREEN_TERRACOTTA_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "jungle_planks_horizontal_slab"), JUNGLE_PLANKS_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "light_blue_terracotta_horizontal_slab"), LIGHT_BLUE_TERRACOTTA_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "light_gray_terracotta_horizontal_slab"), LIGHT_GRAY_TERRACOTTA_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "lime_terracotta_horizontal_slab"), LIME_TERRACOTTA_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "magenta_terracotta_horizontal_slab"), MAGENTA_TERRACOTTA_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "mossy_cobblestone_horizontal_slab"), MOSSY_COBBLESTONE_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "mossy_stone_bricks_horizontal_slab"), MOSSY_STONE_BRICKS_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "nether_bricks_horizontal_slab"), NETHER_BRICKS_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "oak_planks_horizontal_slab"), OAK_PLANKS_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "orange_terracotta_horizontal_slab"), ORANGE_TERRACOTTA_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "pink_terracotta_horizontal_slab"), PINK_TERRACOTTA_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "polished_blackstone_horizontal_slab"), POLISHED_BLACKSTONE_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "polished_andesite_horizontal_slab"), POLISHED_ANDESITE_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "polished_diorite_horizontal_slab"), POLISHED_DIORITE_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "polished_granite_horizontal_slab"), POLISHED_GRANITE_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "prismarine_bricks_horizontal_slab"), PRISMARINE_BRICKS_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "prismarine_horizontal_slab"), PRISMARINE_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "purple_terracotta_horizontal_slab"), PURPLE_TERRACOTTA_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "purpur_block_horizontal_slab"), PURPUR_BLOCK_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "red_nether_bricks_horizontal_slab"), RED_NETHER_BRICKS_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "red_sandstone_horizontal_slab"), RED_SANDSTONE_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "red_terracotta_horizontal_slab"), RED_TERRACOTTA_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "sandstone_horizontal_slab"), SANDSTONE_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "smooth_red_sandstone_horizontal_slab"), SMOOTH_RED_SANDSTONE_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "smooth_sandstone_horizontal_slab"), SMOOTH_SANDSTONE_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "smooth_stone_horizontal_slab"), SMOOTH_STONE_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "spruce_planks_horizontal_slab"), SPRUCE_PLANKS_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "stone_bricks_horizontal_slab"), STONE_BRICKS_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "stone_horizontal_slab"), STONE_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "terracotta_horizontal_slab"), TERRACOTTA_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "warped_planks_horizontal_slab"), WARPED_PLANKS_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "white_terracotta_horizontal_slab"), WHITE_TERRACOTTA_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "yellow_terracotta_horizontal_slab"), YELLOW_TERRACOTTA_HORIZONTAL_SLAB);


                //Tarracotta Bricks
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "terracotta_bricks"), TERRACOTTA_BRICKS);
                
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "red_terracotta_bricks"), RED_TERRACOTTA_BRICKS);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "red_terracotta_bricks_slab"), RED_TERRACOTTA_BRICKS_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "red_terracotta_bricks_stairs"), RED_TERRACOTTA_BRICKS_STAIRS);
                
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "red_terracotta_slab"), RED_TERRACOTTA_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "red_terracotta_stairs"), RED_TERRACOTTA_STAIRS);
                
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "cyan_terracotta_slab"), CYAN_TERRACOTTA_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "cyan_terracotta_stairs"), CYAN_TERRACOTTA_STAIRS);
                
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "terracotta_bricks_slab"), TERRACOTTA_BRICKS_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "terracotta_bricks_stairs"), TERRACOTTA_BRICKS_STAIRS);
                
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "sand_layer"), SAND_LAYER);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "red_sand_layer"), RED_SAND_LAYER);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "gravel_layer"), GRAVEL_LAYER);
                
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "coarse_dirt_cobble_stone_mix_light"), CD_CS_LIGHT);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "coarse_dirt_cobble_stone_mix_heavy"), CD_CS_HEAVY);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "sand_stone_brick_mix_light"), SD_SB_LIGHT);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "sand_stone_brick_mix_heavy"), SD_SB_HEAVY);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "sand_chiseled_stone_brick_mix"), SD_CSB_LIGHT);
                
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "blue_terracotta_bricks"), BLUE_TERRACOTTA_BRICKS);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "blue_terracotta_bricks_slab"), BLUE_TERRACOTTA_BRICKS_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "blue_terracotta_bricks_stairs"), BLUE_TERRACOTTA_BRICKS_STAIRS);
                
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "white_terracotta_bricks"), WHITE_TERRACOTTA_BRICKS);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "white_terracotta_bricks_slab"), WHITE_TERRACOTTA_BRICKS_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "white_terracotta_bricks_stairs"), WHITE_TERRACOTTA_BRICKS_STAIRS);

                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "brown_terracotta_bricks"), BROWN_TERRACOTTA_BRICKS);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "brown_terracotta_bricks_slab"), BROWN_TERRACOTTA_BRICKS_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "brown_terracotta_bricks_stairs"), BROWN_TERRACOTTA_BRICKS_STAIRS);

                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "cyan_terracotta_bricks"), CYAN_TERRACOTTA_BRICKS);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "cyan_terracotta_bricks_slab"), CYAN_TERRACOTTA_BRICKS_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "cyan_terracotta_bricks_stairs"), CYAN_TERRACOTTA_BRICKS_STAIRS);

                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "white_terracotta_slab"), WHITE_TERRACOTTA_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "white_terracotta_stairs"), WHITE_TERRACOTTA_STAIRS);

                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "black_terracotta_bricks"), BLACK_TERRACOTTA_BRICKS);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "black_terracotta_bricks_slab"), BLACK_TERRACOTTA_BRICKS_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "black_terracotta_bricks_stairs"), BLACK_TERRACOTTA_BRICKS_STAIRS);

                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "gray_terracotta_bricks"), GRAY_TERRACOTTA_BRICKS);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "gray_terracotta_bricks_slab"), GRAY_TERRACOTTA_BRICKS_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "gray_terracotta_bricks_stairs"), GRAY_TERRACOTTA_BRICKS_STAIRS);

                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "green_terracotta_bricks"), GREEN_TERRACOTTA_BRICKS);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "green_terracotta_bricks_slab"), GREEN_TERRACOTTA_BRICKS_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "green_terracotta_bricks_stairs"), GREEN_TERRACOTTA_BRICKS_STAIRS);

                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "light_blue_terracotta_bricks"), LIGHT_BLUE_TERRACOTTA_BRICKS);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "light_blue_terracotta_bricks_slab"), LIGHT_BLUE_TERRACOTTA_BRICKS_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "light_blue_terracotta_bricks_stairs"), LIGHT_BLUE_TERRACOTTA_BRICKS_STAIRS);

                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "light_gray_terracotta_bricks"), LIGHT_GRAY_TERRACOTTA_BRICKS);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "light_gray_terracotta_bricks_slab"), LIGHT_GRAY_TERRACOTTA_BRICKS_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "light_gray_terracotta_bricks_stairs"), LIGHT_GRAY_TERRACOTTA_BRICKS_STAIRS);

                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "pink_terracotta_bricks"), PINK_TERRACOTTA_BRICKS);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "pink_terracotta_bricks_slab"), PINK_TERRACOTTA_BRICKS_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "pink_terracotta_bricks_stairs"), PINK_TERRACOTTA_BRICKS_STAIRS);

                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "purple_terracotta_bricks"), PURPLE_TERRACOTTA_BRICKS);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "purple_terracotta_bricks_slab"), PURPLE_TERRACOTTA_BRICKS_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "purple_terracotta_bricks_stairs"), PURPLE_TERRACOTTA_BRICKS_STAIRS);

                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "burnt_log"), BURNT_LOG);

                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "burnt_planks"), BURNT_PLANKS);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "burnt_planks_slab"), BURNT_PLANKS_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "burnt_planks_stairs"), BURNT_PLANKS_STAIRS);

                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "stone_brick_tiles"), STONE_BRICK_SQUARE);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "stone_brick_tiles_slab"), STONE_BRICK_SQUARE_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "stone_brick_tiles_stairs"), STONE_BRICK_SQUARE_STAIRS);

                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "dirt_slab"), DIRT_SLAB);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "coarse_dirt_slab"), COARSE_DIRT_SLAB);

                //Chimney
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "cobblestone_chimney"), COBBLESTONE_CHIMNEY);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "andesite_chimney"), ANDESITE_CHIMNEY);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "granite_chimney"), GRANITE_CHIMNEY);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "diorite_chimney"), DIORITE_CHIMNEY);
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "bricks_chimney"), BRICKS_CHIMNEY);

                //Vertical Glass Pane
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "vertical_glass_pane"), VERTICAL_GLASS_PANE);
                BlockRenderLayerMap.INSTANCE.putBlock(VERTICAL_GLASS_PANE, RenderLayer.getCutout());

                //Lantern
                Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "lantern_rope"), LANTERN_ROPE);
                BlockRenderLayerMap.INSTANCE.putBlock(LANTERN_ROPE, RenderLayer.getCutout());

//! End Block Register

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
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "rope"), new BlockItem(ROPE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "leather_block"), new BlockItem(LEATHER_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        MUD_BUCKET = Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "mud_bucket"), new BucketItem(STILL_MUD, new Item.Settings().group(ItemGroup.MISC)));
        
        //Thin Logs
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "thin_acacia_log"), new BlockItem(THIN_ACACIA_LOG, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "thin_birch_log"), new BlockItem(THIN_BIRCH_LOG, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "thin_dark_oak_log"), new BlockItem(THIN_DARK_OAK_LOG, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "thin_stripped_jungle_log"), new BlockItem(THIN_STRIPPED_JUNGLE_LOG, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "thin_oak_log"), new BlockItem(THIN_OAK_LOG, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "thin_spruce_log"), new BlockItem(THIN_SPRUCE_LOG, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "thin_stripped_acacia_log"), new BlockItem(THIN_STRIPPED_ACACIA_LOG, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "thin_stripped_birch_log"), new BlockItem(THIN_STRIPPED_BIRCH_LOG, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "thin_stripped_dark_oak_log"), new BlockItem(THIN_STRIPPED_DARK_OAK_LOG, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "thin_jungle_log"), new BlockItem(THIN_JUNGLE_LOG, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "thin_stripped_oak_log"), new BlockItem(THIN_STRIPPED_OAK_LOG, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "thin_stripped_spruce_log"), new BlockItem(THIN_STRIPPED_SPRUCE_LOG, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

        //Chopped Log
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "chopped_acacia_log"), new BlockItem(CHOPPED_ACACIA_LOG, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "chopped_stripped_acacia_log"), new BlockItem(CHOPPED_STRIPPED_ACACIA_LOG, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "chopped_birch_log"), new BlockItem(CHOPPED_BIRCH_LOG, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "chopped_stripped_birch_log"), new BlockItem(CHOPPED_STRIPPED_BIRCH_LOG, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "chopped_dark_oak_log"), new BlockItem(CHOPPED_DARK_OAK_LOG, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "chopped_stripped_dark_oak_log"), new BlockItem(CHOPPED_STRIPPED_DARK_OAK_LOG, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "chopped_jungle_log"), new BlockItem(CHOPPED_JUNGLE_LOG, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "chopped_stripped_jungle_log"), new BlockItem(CHOPPED_STRIPPED_JUNGLE_LOG, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "chopped_oak_log"), new BlockItem(CHOPPED_OAK_LOG, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "chopped_stripped_oak_log"), new BlockItem(CHOPPED_STRIPPED_OAK_LOG, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "chopped_spruce_log"), new BlockItem(CHOPPED_SPRUCE_LOG, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "chopped_stripped_spruce_log"), new BlockItem(CHOPPED_STRIPPED_SPRUCE_LOG, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

        //Custom Tools
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "netherite_hammer"), NETHERITE_HAMMER);
        
        //Bark Fragments
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "acacia_bark_fragment"), ACACIA_BARK_FRAGMENT);
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "birch_bark_fragment"), BIRCH_BARK_FRAGMENT);
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "dark_oak_bark_fragment"), DARK_OAK_BARK_FRAGMENT);
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "jungle_bark_fragment"), JUNGLE_BARK_FRAGMENT);
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "oak_bark_fragment"), OAK_BARK_FRAGMENT);
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "spruce_bark_fragment"), SPRUCE_BARK_FRAGMENT);

        //Terracotta Bricks
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "terracotta_bricks"), new BlockItem(TERRACOTTA_BRICKS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "terracotta_bricks_slab"), new BlockItem(TERRACOTTA_BRICKS_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "terracotta_bricks_stairs"), new BlockItem(TERRACOTTA_BRICKS_STAIRS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "white_terracotta_bricks"), new BlockItem(WHITE_TERRACOTTA_BRICKS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "white_terracotta_bricks_slab"), new BlockItem(WHITE_TERRACOTTA_BRICKS_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "white_terracotta_bricks_stairs"), new BlockItem(WHITE_TERRACOTTA_BRICKS_STAIRS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "red_terracotta_bricks"), new BlockItem(RED_TERRACOTTA_BRICKS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "red_terracotta_bricks_slab"), new BlockItem(RED_TERRACOTTA_BRICKS_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "red_terracotta_bricks_stairs"), new BlockItem(RED_TERRACOTTA_BRICKS_STAIRS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "orange_terracotta_bricks"), new BlockItem(ORANGE_TERRACOTTA_BRICKS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "orange_terracotta_bricks_slab"), new BlockItem(ORANGE_TERRACOTTA_BRICKS_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "orange_terracotta_bricks_stairs"), new BlockItem(ORANGE_TERRACOTTA_BRICKS_STAIRS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "pink_terracotta_bricks"), new BlockItem(PINK_TERRACOTTA_BRICKS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "pink_terracotta_bricks_slab"), new BlockItem(PINK_TERRACOTTA_BRICKS_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "pink_terracotta_bricks_stairs"), new BlockItem(PINK_TERRACOTTA_BRICKS_STAIRS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "yellow_terracotta_bricks"), new BlockItem(YELLOW_TERRACOTTA_BRICKS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "yellow_terracotta_bricks_slab"), new BlockItem(YELLOW_TERRACOTTA_BRICKS_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "yellow_terracotta_bricks_stairs"), new BlockItem(YELLOW_TERRACOTTA_BRICKS_STAIRS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "lime_terracotta_bricks"), new BlockItem(LIME_TERRACOTTA_BRICKS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "lime_terracotta_bricks_slab"), new BlockItem(LIME_TERRACOTTA_BRICKS_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "lime_terracotta_bricks_stairs"), new BlockItem(LIME_TERRACOTTA_BRICKS_STAIRS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "green_terracotta_bricks"), new BlockItem(GREEN_TERRACOTTA_BRICKS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "green_terracotta_bricks_slab"), new BlockItem(GREEN_TERRACOTTA_BRICKS_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "green_terracotta_bricks_stairs"), new BlockItem(GREEN_TERRACOTTA_BRICKS_STAIRS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "light_blue_terracotta_bricks"), new BlockItem(LIGHT_BLUE_TERRACOTTA_BRICKS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "light_blue_terracotta_bricks_slab"), new BlockItem(LIGHT_BLUE_TERRACOTTA_BRICKS_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "light_blue_terracotta_bricks_stairs"), new BlockItem(LIGHT_BLUE_TERRACOTTA_BRICKS_STAIRS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "cyan_terracotta_bricks"), new BlockItem(CYAN_TERRACOTTA_BRICKS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "cyan_terracotta_bricks_slab"), new BlockItem(CYAN_TERRACOTTA_BRICKS_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "cyan_terracotta_bricks_stairs"), new BlockItem(CYAN_TERRACOTTA_BRICKS_STAIRS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "blue_terracotta_bricks"), new BlockItem(BLUE_TERRACOTTA_BRICKS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "blue_terracotta_bricks_slab"), new BlockItem(BLUE_TERRACOTTA_BRICKS_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "blue_terracotta_bricks_stairs"), new BlockItem(BLUE_TERRACOTTA_BRICKS_STAIRS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "magenta_terracotta_bricks"), new BlockItem(MAGENTA_TERRACOTTA_BRICKS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "magenta_terracotta_bricks_slab"), new BlockItem(MAGENTA_TERRACOTTA_BRICKS_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "magenta_terracotta_bricks_stairs"), new BlockItem(MAGENTA_TERRACOTTA_BRICKS_STAIRS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "purple_terracotta_bricks"), new BlockItem(PURPLE_TERRACOTTA_BRICKS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "purple_terracotta_bricks_slab"), new BlockItem(PURPLE_TERRACOTTA_BRICKS_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "purple_terracotta_bricks_stairs"), new BlockItem(PURPLE_TERRACOTTA_BRICKS_STAIRS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "brown_terracotta_bricks"), new BlockItem(BROWN_TERRACOTTA_BRICKS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "brown_terracotta_bricks_slab"), new BlockItem(BROWN_TERRACOTTA_BRICKS_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "brown_terracotta_bricks_stairs"), new BlockItem(BROWN_TERRACOTTA_BRICKS_STAIRS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "gray_terracotta_bricks"), new BlockItem(GRAY_TERRACOTTA_BRICKS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "gray_terracotta_bricks_slab"), new BlockItem(GRAY_TERRACOTTA_BRICKS_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "gray_terracotta_bricks_stairs"), new BlockItem(GRAY_TERRACOTTA_BRICKS_STAIRS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "light_gray_terracotta_bricks"), new BlockItem(LIGHT_GRAY_TERRACOTTA_BRICKS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "light_gray_terracotta_bricks_slab"), new BlockItem(LIGHT_GRAY_TERRACOTTA_BRICKS_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "light_gray_terracotta_bricks_stairs"), new BlockItem(LIGHT_GRAY_TERRACOTTA_BRICKS_STAIRS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "black_terracotta_bricks"), new BlockItem(BLACK_TERRACOTTA_BRICKS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "black_terracotta_bricks_slab"), new BlockItem(BLACK_TERRACOTTA_BRICKS_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "black_terracotta_bricks_stairs"), new BlockItem(BLACK_TERRACOTTA_BRICKS_STAIRS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        
        //Packed Terracotta
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "yellow_packed_terracotta"), new BlockItem(YELLOW_PACKED_TERRACOTTA, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

        //Terracotta Slabs and Stairs
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "white_terracotta_slab"), new BlockItem(WHITE_TERRACOTTA_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "white_terracotta_stairs"), new BlockItem(WHITE_TERRACOTTA_STAIRS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "red_terracotta_stairs"), new BlockItem(RED_TERRACOTTA_STAIRS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "red_terracotta_slab"), new BlockItem(RED_TERRACOTTA_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "cyan_terracotta_stairs"), new BlockItem(CYAN_TERRACOTTA_STAIRS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "cyan_terracotta_slab"), new BlockItem(CYAN_TERRACOTTA_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

        //Dark Stone
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "dark_stone"), new BlockItem(DARK_STONE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "dark_stone_slab"), new BlockItem(DARK_STONE_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "dark_stone_stairs"), new BlockItem(DARK_STONE_STAIRS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "dark_stone_bricks"), new BlockItem(DARK_STONE_BRICK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "dark_stone_brick_slab"), new BlockItem(DARK_STONE_BRICK_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "dark_stone_brick_stairs"), new BlockItem(DARK_STONE_BRICK_STAIRS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "dark_stone_tiles"), new BlockItem(DARK_STONE_TILES, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "dark_stone_tiles_slab"), new BlockItem(DARK_STONE_TILES_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "dark_stone_tiles_stairs"), new BlockItem(DARK_STONE_TILES_STAIRS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "dark_cobblestone"), new BlockItem(DARK_COBBLESTONE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "dark_cobblestone_slab"), new BlockItem(DARK_COBBLESTONE_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "dark_cobblestone_stairs"), new BlockItem(DARK_COBBLESTONE_STAIRS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "emblemed_dark_stone"), new BlockItem(EMBLEMED_DARK_STONE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        
        //Misc
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "carved_melon"), new BlockItem(CARVEDMELON, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        
        //Dead Blocks
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "dead_grass_block"), new BlockItem(DEAD_GRASS_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "burnt_log"), new BlockItem(BURNT_LOG, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "burnt_planks"), new BlockItem(BURNT_PLANKS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "burnt_planks_slab"), new BlockItem(BURNT_PLANKS_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "burnt_planks_stairs"), new BlockItem(BURNT_PLANKS_STAIRS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

        //Transistion Blocks
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "sand_chiseled_stone_brick_mix"), new BlockItem(SD_CSB_LIGHT, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "sand_stone_brick_mix_heavy"), new BlockItem(SD_SB_HEAVY, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "sand_stone_brick_mix_light"), new BlockItem(SD_SB_LIGHT, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "coarse_dirt_cobble_stone_mix_heavy"), new BlockItem(CD_CS_HEAVY, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "coarse_dirt_cobble_stone_mix_light"), new BlockItem(CD_CS_LIGHT, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        
        //Horizontal Slab
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "acacia_planks_horizontal_slab"), new BlockItem(ACACIA_PLANKS_HORIZONTAL_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "andesite_horizontal_slab"), new BlockItem(ANDESITE_HORIZONTAL_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "birch_planks_horizontal_slab"), new BlockItem(BIRCH_HORIZONTAL_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "black_terracotta_horizontal_slab"), new BlockItem(BLACK_TERRACOTTA_HORIZONTAL_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "blackstone_horizontal_slab"), new BlockItem(BLACKSTONE_HORIZONTAL_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "blue_terracotta_horizontal_slab"), new BlockItem(BLUE_TERRACOTTA_HORIZONTAL_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "bricks_horizontal_slab"), new BlockItem(BRICKS_HORIZONTAL_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "brown_terracotta_horizontal_slab"), new BlockItem(BROWN_TERRACOTTA_HORIZONTAL_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        //Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "chiseled_nether_bricks_horizontal_slab"), new BlockItem(CHISELED_NETHER_BRICKS_HORIZONTAL_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        //Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "chiseled_polished_blackstone_horizontal_slab"), new BlockItem(CHISELED_POLISHED_BLACKSTONE_HORIZONTAL_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        //Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "chiseled_red_sandstone_horizontal_slab"), new BlockItem(CHISELED_RED_SANDSTONE_HORIZONTAL_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        //Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "chiseled_sandstone_horizontal_slab"), new BlockItem(CHISELED_SANDSTONE_HORIZONTAL_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        //Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "chiseled_stone_bricks_horizontal_slab"), new BlockItem(CHISELED_STONE_BRICKS_HORIZONTAL_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "cobblestone_horizontal_slab"), new BlockItem(COBBLESTONE_HORIZONTAL_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "cracked_nether_bricks_horizontal_slab"), new BlockItem(CRACKED_NETHER_BRICKS_HORIZONTAL_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "cracked_stone_bricks_horizontal_slab"), new BlockItem(CRACKED_STONE_BRICKS_HORIZONTAL_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "crimson_planks_horizontal_slab"), new BlockItem(CRIMSON_PLANKS_HORIZONTAL_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "cut_red_sandstone_horizontal_slab"), new BlockItem(CUT_RED_SANDSTONE_HORIZONTAL_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "cut_sandstone_horizontal_slab"), new BlockItem(CUT_SANDSTONE_HORIZONTAL_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "cyan_terracotta_horizontal_slab"), new BlockItem(CYAN_TERRACOTTA_HORIZONTAL_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "dark_oak_planks_horizontal_slab"), new BlockItem(DARK_OAK_PLANKS_HORIZONTAL_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "dark_prismarine_horizontal_slab"), new BlockItem(DARK_PRISMARINE_HORIZONTAL_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "diorite_horizontal_slab"), new BlockItem(DIORITE_HORIZONTAL_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "end_stone_bricks_horizontal_slab"), new BlockItem(END_STONE_BRICKS_HORIZONTAL_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "granite_horizontal_slab"), new BlockItem(GRANITE_HORIZONTAL_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "gray_terracotta_horizontal_slab"), new BlockItem(GRAY_TERRACOTTA_HORIZONTAL_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "green_terracotta_horizontal_slab"), new BlockItem(GREEN_TERRACOTTA_HORIZONTAL_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "jungle_planks_horizontal_slab"), new BlockItem(JUNGLE_PLANKS_HORIZONTAL_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "light_blue_terracotta_horizontal_slab"), new BlockItem(LIGHT_BLUE_TERRACOTTA_HORIZONTAL_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "light_gray_terracotta_horizontal_slab"), new BlockItem(LIGHT_GRAY_TERRACOTTA_HORIZONTAL_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "lime_terracotta_horizontal_slab"), new BlockItem(LIME_TERRACOTTA_HORIZONTAL_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "magenta_terracotta_horizontal_slab"), new BlockItem(MAGENTA_TERRACOTTA_HORIZONTAL_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "mossy_cobblestone_horizontal_slab"), new BlockItem(MOSSY_COBBLESTONE_HORIZONTAL_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "mossy_stone_bricks_horizontal_slab"), new BlockItem(MOSSY_STONE_BRICKS_HORIZONTAL_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "nether_bricks_horizontal_slab"), new BlockItem(NETHER_BRICKS_HORIZONTAL_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "oak_planks_horizontal_slab"), new BlockItem(OAK_PLANKS_HORIZONTAL_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "orange_terracotta_horizontal_slab"), new BlockItem(ORANGE_TERRACOTTA_HORIZONTAL_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "pink_terracotta_horizontal_slab"), new BlockItem(PINK_TERRACOTTA_HORIZONTAL_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "polished_blackstone_horizontal_slab"), new BlockItem(POLISHED_BLACKSTONE_HORIZONTAL_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "polished_andesite_horizontal_slab"), new BlockItem(POLISHED_ANDESITE_HORIZONTAL_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "polished_diorite_horizontal_slab"), new BlockItem(POLISHED_DIORITE_HORIZONTAL_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "polished_granite_horizontal_slab"), new BlockItem(POLISHED_GRANITE_HORIZONTAL_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "prismarine_bricks_horizontal_slab"), new BlockItem(PRISMARINE_BRICKS_HORIZONTAL_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "prismarine_horizontal_slab"), new BlockItem(PRISMARINE_HORIZONTAL_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "purple_terracotta_horizontal_slab"), new BlockItem(PURPLE_TERRACOTTA_HORIZONTAL_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "purpur_block_horizontal_slab"), new BlockItem(PURPUR_BLOCK_HORIZONTAL_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "red_nether_bricks_horizontal_slab"), new BlockItem(RED_NETHER_BRICKS_HORIZONTAL_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "red_sandstone_horizontal_slab"), new BlockItem(RED_SANDSTONE_HORIZONTAL_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "red_terracotta_horizontal_slab"), new BlockItem(RED_TERRACOTTA_HORIZONTAL_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "sandstone_horizontal_slab"), new BlockItem(SANDSTONE_HORIZONTAL_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "smooth_red_sandstone_horizontal_slab"), new BlockItem(SMOOTH_RED_SANDSTONE_HORIZONTAL_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "smooth_sandstone_horizontal_slab"), new BlockItem(SMOOTH_SANDSTONE_HORIZONTAL_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "smooth_stone_horizontal_slab"), new BlockItem(SMOOTH_STONE_HORIZONTAL_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "spruce_planks_horizontal_slab"), new BlockItem(SPRUCE_PLANKS_HORIZONTAL_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "stone_bricks_horizontal_slab"), new BlockItem(STONE_BRICKS_HORIZONTAL_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "stone_horizontal_slab"), new BlockItem(STONE_HORIZONTAL_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "terracotta_horizontal_slab"), new BlockItem(TERRACOTTA_HORIZONTAL_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "warped_planks_horizontal_slab"), new BlockItem(WARPED_PLANKS_HORIZONTAL_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "white_terracotta_horizontal_slab"), new BlockItem(WHITE_TERRACOTTA_HORIZONTAL_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "yellow_terracotta_horizontal_slab"), new BlockItem(YELLOW_TERRACOTTA_HORIZONTAL_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

        //Misc
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "petrified_wood"), new BlockItem(PETRIFIED_WOOD, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "packed_mud"), new BlockItem(PACKEDMUD, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

        //Layer
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "sand_layer"), new BlockItem(SAND_LAYER, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "red_sand_layer"), new BlockItem(RED_SAND_LAYER, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "gravel_layer"), new BlockItem(GRAVEL_LAYER, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        
        //Stone Variants
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "stone_brick_tiles"), new BlockItem(STONE_BRICK_SQUARE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "stone_brick_tiles_slab"), new BlockItem(STONE_BRICK_SQUARE_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "stone_brick_tiles_stairs"), new BlockItem(STONE_BRICK_SQUARE_STAIRS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        
        //Minecraft Slabs and Stairs
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "dirt_slab"), new BlockItem(DIRT_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "coarse_dirt_slab"), new BlockItem(COARSE_DIRT_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

        //Chimney
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "cobblestone_chimney"), new BlockItem(COBBLESTONE_CHIMNEY, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "andesite_chimney"), new BlockItem(ANDESITE_CHIMNEY, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "granite_chimney"), new BlockItem(GRANITE_CHIMNEY, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "diorite_chimney"), new BlockItem(DIORITE_CHIMNEY, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "bricks_chimney"), new BlockItem(BRICKS_CHIMNEY, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

        //Vertical Glass Pane
        Registry.register(Registry.ITEM, new Identifier("moreblocksmod", "vertical_glass_pane"), new BlockItem(VERTICAL_GLASS_PANE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

//! End Item Register
        }
        
}