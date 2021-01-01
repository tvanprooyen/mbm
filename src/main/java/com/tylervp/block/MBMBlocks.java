package com.tylervp.block;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.GrassBlock;
import net.minecraft.block.HayBlock;
import net.minecraft.block.LanternBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.sound.BlockSoundGroup;

import com.tylervp.block.entity.VaseBlockEntity;
import com.tylervp.fluid.MudFluid;

public class MBMBlocks {

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

    //Crop Blocks
    public static final RiceCropBlock RICE = new RiceCropBlock(FabricBlockSettings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP));
    public static final RiceCropBlockNoOffset RICE_NO_OFF_SET = new RiceCropBlockNoOffset(FabricBlockSettings.copyOf(RICE));

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
    public static final SideSlab BLACKSTONE_BRICKS_HORIZONTAL_SLAB = new SideSlab(STONE_SETTINGS);
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
    public static final HayBlock RICE_STRAW_BALE = new HayBlock(FabricBlockSettings.of(Material.SOLID_ORGANIC, MaterialColor.YELLOW).strength(0.5f).sounds(BlockSoundGroup.GRASS));
    public static final PillarBlock LEATHER_BLOCK = new PillarBlock(FabricBlockSettings.of(Material.WOOD).breakByHand(true).breakByTool(FabricToolTags.AXES).sounds(BlockSoundGroup.WOOD).hardness(2).resistance(5));
    public static final PetrifiedPillarBlock PETRIFIED_WOOD = new PetrifiedPillarBlock(FabricBlockSettings.of(Material.WOOD).breakByHand(true).breakByTool(FabricToolTags.AXES).sounds(BlockSoundGroup.WOOD).hardness(2).resistance(5));

    //Rope
    public static final RopeBlock ROPE = new RopeBlock(FabricBlockSettings.of(Material.WOOL).breakInstantly().breakByHand(true).sounds(BlockSoundGroup.WOOL).hardness(0).resistance(5));
    public static final RopeBlockMid ROPEMID = new RopeBlockMid(FabricBlockSettings.of(Material.WOOL).breakInstantly().breakByHand(true).sounds(BlockSoundGroup.WOOL).hardness(0).resistance(5));

    //Thin Logs and Log Related Items
    public static final ThinPillarBlock THIN_ACACIA_LOG = new ThinPillarBlock(THIN_LOG_SETTINGS);
    public static final ThinPillarBlock THIN_STRIPPED_ACACIA_LOG = new ThinPillarBlock(THIN_LOG_SETTINGS);

    public static final ThinPillarBlock THIN_BIRCH_LOG = new ThinPillarBlock(THIN_LOG_SETTINGS);
    public static final ThinPillarBlock THIN_STRIPPED_BIRCH_LOG = new ThinPillarBlock(THIN_LOG_SETTINGS);

    public static final ThinPillarBlock THIN_DARK_OAK_LOG = new ThinPillarBlock(THIN_LOG_SETTINGS);
    public static final ThinPillarBlock THIN_STRIPPED_DARK_OAK_LOG = new ThinPillarBlock(THIN_LOG_SETTINGS);

    public static final ThinPillarBlock THIN_JUNGLE_LOG = new ThinPillarBlock(THIN_LOG_SETTINGS);
    public static final ThinPillarBlock THIN_STRIPPED_JUNGLE_LOG = new ThinPillarBlock(THIN_LOG_SETTINGS);
    
    public static final ThinPillarBlock THIN_OAK_LOG = new ThinPillarBlock(THIN_LOG_SETTINGS);
    public static final ThinPillarBlock THIN_STRIPPED_OAK_LOG = new ThinPillarBlock(THIN_LOG_SETTINGS);
    
    public static final ThinPillarBlock THIN_SPRUCE_LOG = new ThinPillarBlock(THIN_LOG_SETTINGS);
    public static final ThinPillarBlock THIN_STRIPPED_SPRUCE_LOG = new ThinPillarBlock(THIN_LOG_SETTINGS);

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

    //Vase Block
    public static final VaseBlock TERRACOTTA_VASE = new VaseBlock(TERRACOTTA_SETTINGS);
    //public static final VaseBlock BLACK_TERRACOTTA_VASE = new VaseBlock(TERRACOTTA_SETTINGS);

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

    public static final SlabBlock BLACK_TERRACOTTA_SLAB = new SlabBlock(TERRACOTTA_SETTINGS);
    public static final StairsBlockExtend BLACK_TERRACOTTA_STAIRS = new StairsBlockExtend(Blocks.BLACK_TERRACOTTA.getDefaultState(), TERRACOTTA_SETTINGS);

    public static final SlabBlock BLUE_TERRACOTTA_SLAB = new SlabBlock(TERRACOTTA_SETTINGS);
    public static final StairsBlockExtend BLUE_TERRACOTTA_STAIRS = new StairsBlockExtend(Blocks.BLUE_TERRACOTTA.getDefaultState(), TERRACOTTA_SETTINGS);

    public static final SlabBlock BROWN_TERRACOTTA_SLAB = new SlabBlock(TERRACOTTA_SETTINGS);
    public static final StairsBlockExtend BROWN_TERRACOTTA_STAIRS = new StairsBlockExtend(Blocks.BROWN_TERRACOTTA.getDefaultState(), TERRACOTTA_SETTINGS);

    public static final SlabBlock LIGHT_BLUE_TERRACOTTA_SLAB = new SlabBlock(TERRACOTTA_SETTINGS);
    public static final StairsBlockExtend LIGHT_BLUE_TERRACOTTA_STAIRS = new StairsBlockExtend(Blocks.LIGHT_BLUE_TERRACOTTA.getDefaultState(), TERRACOTTA_SETTINGS);

    public static final SlabBlock GRAY_TERRACOTTA_SLAB = new SlabBlock(TERRACOTTA_SETTINGS);
    public static final StairsBlockExtend GRAY_TERRACOTTA_STAIRS = new StairsBlockExtend(Blocks.GRAY_TERRACOTTA.getDefaultState(), TERRACOTTA_SETTINGS);

    public static final SlabBlock GREEN_TERRACOTTA_SLAB = new SlabBlock(TERRACOTTA_SETTINGS);
    public static final StairsBlockExtend GREEN_TERRACOTTA_STAIRS = new StairsBlockExtend(Blocks.GREEN_TERRACOTTA.getDefaultState(), TERRACOTTA_SETTINGS);

    public static final SlabBlock LIME_TERRACOTTA_SLAB = new SlabBlock(TERRACOTTA_SETTINGS);
    public static final StairsBlockExtend LIME_TERRACOTTA_STAIRS = new StairsBlockExtend(Blocks.LIME_TERRACOTTA.getDefaultState(), TERRACOTTA_SETTINGS);

    public static final SlabBlock LIGHT_GRAY_TERRACOTTA_SLAB = new SlabBlock(TERRACOTTA_SETTINGS);
    public static final StairsBlockExtend LIGHT_GRAY_TERRACOTTA_STAIRS = new StairsBlockExtend(Blocks.LIGHT_GRAY_TERRACOTTA.getDefaultState(), TERRACOTTA_SETTINGS);

    public static final SlabBlock MAGENTA_TERRACOTTA_SLAB = new SlabBlock(TERRACOTTA_SETTINGS);
    public static final StairsBlockExtend MAGENTA_TERRACOTTA_STAIRS = new StairsBlockExtend(Blocks.MAGENTA_TERRACOTTA.getDefaultState(), TERRACOTTA_SETTINGS);

    public static final SlabBlock ORANGE_TERRACOTTA_SLAB = new SlabBlock(TERRACOTTA_SETTINGS);
    public static final StairsBlockExtend ORANGE_TERRACOTTA_STAIRS = new StairsBlockExtend(Blocks.ORANGE_TERRACOTTA.getDefaultState(), TERRACOTTA_SETTINGS);

    public static final SlabBlock PINK_TERRACOTTA_SLAB = new SlabBlock(TERRACOTTA_SETTINGS);
    public static final StairsBlockExtend PINK_TERRACOTTA_STAIRS = new StairsBlockExtend(Blocks.PINK_TERRACOTTA.getDefaultState(), TERRACOTTA_SETTINGS);

    public static final SlabBlock PURPLE_TERRACOTTA_SLAB = new SlabBlock(TERRACOTTA_SETTINGS);
    public static final StairsBlockExtend PURPLE_TERRACOTTA_STAIRS = new StairsBlockExtend(Blocks.PURPLE_TERRACOTTA.getDefaultState(), TERRACOTTA_SETTINGS);

    public static final SlabBlock YELLOW_TERRACOTTA_SLAB = new SlabBlock(TERRACOTTA_SETTINGS);
    public static final StairsBlockExtend YELLOW_TERRACOTTA_STAIRS = new StairsBlockExtend(Blocks.YELLOW_TERRACOTTA.getDefaultState(), TERRACOTTA_SETTINGS);

    public static final SlabBlock TERRACOTTA_SLAB = new SlabBlock(TERRACOTTA_SETTINGS);
    public static final StairsBlockExtend TERRACOTTA_STAIRS = new StairsBlockExtend(Blocks.TERRACOTTA.getDefaultState(), TERRACOTTA_SETTINGS);

    //Packed Terracotta
    public static final Block BLACK_PACKED_TERRACOTTA = new Block(TERRACOTTA_SETTINGS.materialColor(MaterialColor.BLACK_TERRACOTTA));
    public static final Block BLUE_PACKED_TERRACOTTA = new Block(TERRACOTTA_SETTINGS.materialColor(MaterialColor.BLUE_TERRACOTTA));
    public static final Block LIGHT_BLUE_PACKED_TERRACOTTA = new Block(TERRACOTTA_SETTINGS.materialColor(MaterialColor.LIGHT_BLUE_TERRACOTTA));
    public static final Block BROWN_PACKED_TERRACOTTA = new Block(TERRACOTTA_SETTINGS.materialColor(MaterialColor.BROWN_TERRACOTTA));
    public static final Block CYAN_PACKED_TERRACOTTA = new Block(TERRACOTTA_SETTINGS.materialColor(MaterialColor.CYAN_TERRACOTTA));
    public static final Block GRAY_PACKED_TERRACOTTA = new Block(TERRACOTTA_SETTINGS.materialColor(MaterialColor.GRAY_TERRACOTTA));
    public static final Block GREEN_PACKED_TERRACOTTA = new Block(TERRACOTTA_SETTINGS.materialColor(MaterialColor.GREEN_TERRACOTTA));
    public static final Block LIME_PACKED_TERRACOTTA = new Block(TERRACOTTA_SETTINGS.materialColor(MaterialColor.LIME_TERRACOTTA));
    public static final Block LIGHT_GRAY_PACKED_TERRACOTTA = new Block(TERRACOTTA_SETTINGS.materialColor(MaterialColor.LIGHT_GRAY_TERRACOTTA));
    public static final Block MAGENTA_PACKED_TERRACOTTA = new Block(TERRACOTTA_SETTINGS.materialColor(MaterialColor.LIME_TERRACOTTA));
    public static final Block ORANGE_PACKED_TERRACOTTA = new Block(TERRACOTTA_SETTINGS.materialColor(MaterialColor.MAGENTA_TERRACOTTA));
    public static final Block PACKED_TERRACOTTA = new Block(TERRACOTTA_SETTINGS);
    public static final Block PINK_PACKED_TERRACOTTA = new Block(TERRACOTTA_SETTINGS.materialColor(MaterialColor.PINK_TERRACOTTA));
    public static final Block PURPLE_PACKED_TERRACOTTA = new Block(TERRACOTTA_SETTINGS.materialColor(MaterialColor.PURPLE_TERRACOTTA));
    public static final Block RED_PACKED_TERRACOTTA = new Block(TERRACOTTA_SETTINGS.materialColor(MaterialColor.RED_TERRACOTTA));
    public static final Block YELLOW_PACKED_TERRACOTTA = new Block(TERRACOTTA_SETTINGS.materialColor(MaterialColor.YELLOW_TERRACOTTA));
    public static final Block WHITE_PACKED_TERRACOTTA = new Block(TERRACOTTA_SETTINGS.materialColor(MaterialColor.WHITE_TERRACOTTA));

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
    public static final SlabBlock GRASS_SLAB = new SlabBlock(FabricBlockSettings.of(Material.SOIL).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.SHOVELS).breakByHand(true).hardness(0.5f).resistance(0.5f));  
    public static final SlabBlock SNOW_GRASS_SLAB = new SlabBlock(FabricBlockSettings.of(Material.SOIL).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.SHOVELS).breakByHand(true).hardness(0.5f).resistance(0.5f));  
    //public static final SlabBlock CRACKED_STONE_BRICK_SLAB = new SlabBlock(FabricBlockSettings.of(Material.SOIL).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.SHOVELS).breakByHand(true).hardness(0.5f).resistance(0.5f));  
    //public static final SlabBlock CRACKED_NETHER_BRICK_SLAB = new SlabBlock(FabricBlockSettings.of(Material.SOIL).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.SHOVELS).breakByHand(true).hardness(0.5f).resistance(0.5f));  

    //Block Entity
    public static BlockEntityType<VaseBlockEntity> TERRACOTTA_VASE_BLOCK_ENTITY/* , BLACK_TERRACOTTA_VASE_BLOCK_ENTITY */;

    //! End Block Definition

    public static FlowableFluid STILL_MUD;
    public static FlowableFluid FLOWING_MUD;
    public static MudBlock MUD;

    public static final void blocks(String ModName) {

                TERRACOTTA_VASE_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, ModName + ":terracotta_vase", BlockEntityType.Builder.create(VaseBlockEntity::new, TERRACOTTA_VASE).build(null));
                //BLACK_TERRACOTTA_VASE_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, "moreblocksmod:black_terracotta_vase", BlockEntityType.Builder.create(VaseBlockEntity::new, BLACK_TERRACOTTA_VASE).build(null));

                STILL_MUD = Registry.register(Registry.FLUID, new Identifier(ModName, "mud"), new MudFluid.Still());
                FLOWING_MUD = Registry.register(Registry.FLUID, new Identifier(ModName, "flowing_mud"), new MudFluid.Flowing());
                MUD = Registry.register(Registry.BLOCK, new Identifier(ModName, "mud"), new MudBlock(STILL_MUD, FabricBlockSettings.copy(Blocks.WATER)){});

                Registry.register(Registry.BLOCK, new Identifier(ModName, "leather_block"), LEATHER_BLOCK);

                Registry.register(Registry.BLOCK, new Identifier(ModName, "rice_straw_bale"), RICE_STRAW_BALE);

                Registry.register(Registry.BLOCK, new Identifier(ModName, "rope"), ROPE);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "rope_mid"), ROPEMID);


                Registry.register(Registry.BLOCK, new Identifier(ModName, "thin_acacia_log"), THIN_ACACIA_LOG);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "thin_stripped_acacia_log"), THIN_STRIPPED_ACACIA_LOG);
                
                Registry.register(Registry.BLOCK, new Identifier(ModName, "thin_birch_log"), THIN_BIRCH_LOG);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "thin_stripped_birch_log"), THIN_STRIPPED_BIRCH_LOG);
                

                Registry.register(Registry.BLOCK, new Identifier(ModName, "thin_dark_oak_log"), THIN_DARK_OAK_LOG);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "thin_stripped_dark_oak_log"), THIN_STRIPPED_DARK_OAK_LOG);
                

                Registry.register(Registry.BLOCK, new Identifier(ModName, "thin_jungle_log"), THIN_JUNGLE_LOG);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "thin_stripped_jungle_log"), THIN_STRIPPED_JUNGLE_LOG);


                Registry.register(Registry.BLOCK, new Identifier(ModName, "thin_oak_log"), THIN_OAK_LOG);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "thin_stripped_oak_log"), THIN_STRIPPED_OAK_LOG);

                Registry.register(Registry.BLOCK, new Identifier(ModName, "thin_spruce_log"), THIN_SPRUCE_LOG);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "thin_stripped_spruce_log"), THIN_STRIPPED_SPRUCE_LOG);
                

                Registry.register(Registry.BLOCK, new Identifier(ModName, "chopped_acacia_log"), CHOPPED_ACACIA_LOG);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "chopped_stripped_acacia_log"), CHOPPED_STRIPPED_ACACIA_LOG);

                Registry.register(Registry.BLOCK, new Identifier(ModName, "chopped_birch_log"), CHOPPED_BIRCH_LOG);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "chopped_stripped_birch_log"), CHOPPED_STRIPPED_BIRCH_LOG);

                Registry.register(Registry.BLOCK, new Identifier(ModName, "chopped_dark_oak_log"), CHOPPED_DARK_OAK_LOG);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "chopped_stripped_dark_oak_log"), CHOPPED_STRIPPED_DARK_OAK_LOG);

                Registry.register(Registry.BLOCK, new Identifier(ModName, "chopped_jungle_log"), CHOPPED_JUNGLE_LOG);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "chopped_stripped_jungle_log"), CHOPPED_STRIPPED_JUNGLE_LOG);

                Registry.register(Registry.BLOCK, new Identifier(ModName, "chopped_oak_log"), CHOPPED_OAK_LOG);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "chopped_stripped_oak_log"), CHOPPED_STRIPPED_OAK_LOG);

                Registry.register(Registry.BLOCK, new Identifier(ModName, "chopped_spruce_log"), CHOPPED_SPRUCE_LOG);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "chopped_stripped_spruce_log"), CHOPPED_STRIPPED_SPRUCE_LOG);

                //Dead Grass Block
                Registry.register(Registry.BLOCK, new Identifier(ModName, "dead_grass_block"), DEAD_GRASS_BLOCK);
                

                //Carved Melon
                Registry.register(Registry.BLOCK, new Identifier(ModName, "carved_melon"), CARVEDMELON);

                //Crop Block
                Registry.register(Registry.BLOCK, new Identifier(ModName, "rice"), RICE);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "rice_center"), RICE_NO_OFF_SET);

                //Dark Stone
                Registry.register(Registry.BLOCK, new Identifier(ModName, "dark_stone"), DARK_STONE);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "dark_stone_slab"), DARK_STONE_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "dark_stone_stairs"), DARK_STONE_STAIRS);

                Registry.register(Registry.BLOCK, new Identifier(ModName, "dark_stone_bricks"), DARK_STONE_BRICK);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "dark_stone_brick_slab"), DARK_STONE_BRICK_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "dark_stone_brick_stairs"), DARK_STONE_BRICK_STAIRS);

                Registry.register(Registry.BLOCK, new Identifier(ModName, "dark_stone_tiles"), DARK_STONE_TILES);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "dark_stone_tiles_slab"), DARK_STONE_TILES_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "dark_stone_tiles_stairs"), DARK_STONE_TILES_STAIRS);

                Registry.register(Registry.BLOCK, new Identifier(ModName, "dark_cobblestone"), DARK_COBBLESTONE);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "dark_cobblestone_slab"), DARK_COBBLESTONE_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "dark_cobblestone_stairs"), DARK_COBBLESTONE_STAIRS);

                Registry.register(Registry.BLOCK, new Identifier(ModName, "emblemed_dark_stone"), EMBLEMED_DARK_STONE);

                //Packed Terracotta
                Registry.register(Registry.BLOCK, new Identifier(ModName, "black_packed_terracotta"), BLACK_PACKED_TERRACOTTA);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "blue_packed_terracotta"), BLUE_PACKED_TERRACOTTA);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "light_blue_packed_terracotta"), LIGHT_BLUE_PACKED_TERRACOTTA);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "brown_packed_terracotta"), BROWN_PACKED_TERRACOTTA);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "cyan_packed_terracotta"), CYAN_PACKED_TERRACOTTA);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "gray_packed_terracotta"), GRAY_PACKED_TERRACOTTA);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "green_packed_terracotta"), GREEN_PACKED_TERRACOTTA);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "lime_packed_terracotta"), LIME_PACKED_TERRACOTTA);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "light_gray_packed_terracotta"), LIGHT_GRAY_PACKED_TERRACOTTA);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "magenta_packed_terracotta"), MAGENTA_PACKED_TERRACOTTA);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "orange_packed_terracotta"), ORANGE_PACKED_TERRACOTTA);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "packed_terracotta"), PACKED_TERRACOTTA);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "pink_packed_terracotta"), PINK_PACKED_TERRACOTTA);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "purple_packed_terracotta"), PURPLE_PACKED_TERRACOTTA);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "red_packed_terracotta"), RED_PACKED_TERRACOTTA);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "yellow_packed_terracotta"), YELLOW_PACKED_TERRACOTTA);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "white_packed_terracotta"), WHITE_PACKED_TERRACOTTA);
                
                
                Registry.register(Registry.BLOCK, new Identifier(ModName, "yellow_terracotta_bricks"), YELLOW_TERRACOTTA_BRICKS);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "yellow_terracotta_bricks_slab"), YELLOW_TERRACOTTA_BRICKS_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "yellow_terracotta_bricks_stairs"), YELLOW_TERRACOTTA_BRICKS_STAIRS);
        
                Registry.register(Registry.BLOCK, new Identifier(ModName, "orange_terracotta_bricks"), ORANGE_TERRACOTTA_BRICKS);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "orange_terracotta_bricks_slab"), ORANGE_TERRACOTTA_BRICKS_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "orange_terracotta_bricks_stairs"), ORANGE_TERRACOTTA_BRICKS_STAIRS);
                
                Registry.register(Registry.BLOCK, new Identifier(ModName, "magenta_terracotta_bricks"), MAGENTA_TERRACOTTA_BRICKS);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "magenta_terracotta_bricks_slab"), MAGENTA_TERRACOTTA_BRICKS_SLAB);
                
                Registry.register(Registry.BLOCK, new Identifier(ModName, "magenta_terracotta_bricks_stairs"), MAGENTA_TERRACOTTA_BRICKS_STAIRS);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "lime_terracotta_bricks"), LIME_TERRACOTTA_BRICKS);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "lime_terracotta_bricks_slab"), LIME_TERRACOTTA_BRICKS_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "lime_terracotta_bricks_stairs"), LIME_TERRACOTTA_BRICKS_STAIRS);
                
                Registry.register(Registry.BLOCK, new Identifier(ModName, "packed_mud"), PACKEDMUD);
                
                Registry.register(Registry.BLOCK, new Identifier(ModName, "petrified_wood"), PETRIFIED_WOOD);
                
                //Side Slab
                Registry.register(Registry.BLOCK, new Identifier(ModName, "acacia_planks_horizontal_slab"), ACACIA_PLANKS_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "andesite_horizontal_slab"), ANDESITE_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "birch_planks_horizontal_slab"), BIRCH_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "black_terracotta_horizontal_slab"), BLACK_TERRACOTTA_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "blackstone_horizontal_slab"), BLACKSTONE_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "blackstone_bricks_horizontal_slab"), BLACKSTONE_BRICKS_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "blue_terracotta_horizontal_slab"), BLUE_TERRACOTTA_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "bricks_horizontal_slab"), BRICKS_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "brown_terracotta_horizontal_slab"), BROWN_TERRACOTTA_HORIZONTAL_SLAB);
                // Registry.register(Registry.BLOCK, new Identifier(ModName, "chiseled_nether_bricks_horizontal_slab"), CHISELED_NETHER_BRICKS_HORIZONTAL_SLAB);
                //Registry.register(Registry.BLOCK, new Identifier(ModName, "chiseled_polished_blackstone_horizontal_slab"), CHISELED_POLISHED_BLACKSTONE_HORIZONTAL_SLAB);
                //Registry.register(Registry.BLOCK, new Identifier(ModName, "chiseled_red_sandstone_horizontal_slab"), CHISELED_RED_SANDSTONE_HORIZONTAL_SLAB);
                //Registry.register(Registry.BLOCK, new Identifier(ModName, "chiseled_sandstone_horizontal_slab"), CHISELED_SANDSTONE_HORIZONTAL_SLAB);
                //Registry.register(Registry.BLOCK, new Identifier(ModName, "chiseled_stone_bricks_horizontal_slab"), CHISELED_STONE_BRICKS_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "cobblestone_horizontal_slab"), COBBLESTONE_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "cracked_nether_bricks_horizontal_slab"), CRACKED_NETHER_BRICKS_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "cracked_stone_bricks_horizontal_slab"), CRACKED_STONE_BRICKS_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "crimson_planks_horizontal_slab"), CRIMSON_PLANKS_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "cut_red_sandstone_horizontal_slab"), CUT_RED_SANDSTONE_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "cut_sandstone_horizontal_slab"), CUT_SANDSTONE_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "cyan_terracotta_horizontal_slab"), CYAN_TERRACOTTA_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "dark_oak_planks_horizontal_slab"), DARK_OAK_PLANKS_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "dark_prismarine_horizontal_slab"), DARK_PRISMARINE_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "diorite_horizontal_slab"), DIORITE_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "end_stone_bricks_horizontal_slab"), END_STONE_BRICKS_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "granite_horizontal_slab"), GRANITE_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "gray_terracotta_horizontal_slab"), GRAY_TERRACOTTA_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "green_terracotta_horizontal_slab"), GREEN_TERRACOTTA_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "jungle_planks_horizontal_slab"), JUNGLE_PLANKS_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "light_blue_terracotta_horizontal_slab"), LIGHT_BLUE_TERRACOTTA_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "light_gray_terracotta_horizontal_slab"), LIGHT_GRAY_TERRACOTTA_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "lime_terracotta_horizontal_slab"), LIME_TERRACOTTA_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "magenta_terracotta_horizontal_slab"), MAGENTA_TERRACOTTA_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "mossy_cobblestone_horizontal_slab"), MOSSY_COBBLESTONE_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "mossy_stone_bricks_horizontal_slab"), MOSSY_STONE_BRICKS_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "nether_bricks_horizontal_slab"), NETHER_BRICKS_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "oak_planks_horizontal_slab"), OAK_PLANKS_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "orange_terracotta_horizontal_slab"), ORANGE_TERRACOTTA_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "pink_terracotta_horizontal_slab"), PINK_TERRACOTTA_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "polished_blackstone_horizontal_slab"), POLISHED_BLACKSTONE_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "polished_andesite_horizontal_slab"), POLISHED_ANDESITE_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "polished_diorite_horizontal_slab"), POLISHED_DIORITE_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "polished_granite_horizontal_slab"), POLISHED_GRANITE_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "prismarine_bricks_horizontal_slab"), PRISMARINE_BRICKS_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "prismarine_horizontal_slab"), PRISMARINE_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "purple_terracotta_horizontal_slab"), PURPLE_TERRACOTTA_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "purpur_block_horizontal_slab"), PURPUR_BLOCK_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "red_nether_bricks_horizontal_slab"), RED_NETHER_BRICKS_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "red_sandstone_horizontal_slab"), RED_SANDSTONE_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "red_terracotta_horizontal_slab"), RED_TERRACOTTA_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "sandstone_horizontal_slab"), SANDSTONE_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "smooth_red_sandstone_horizontal_slab"), SMOOTH_RED_SANDSTONE_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "smooth_sandstone_horizontal_slab"), SMOOTH_SANDSTONE_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "smooth_stone_horizontal_slab"), SMOOTH_STONE_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "spruce_planks_horizontal_slab"), SPRUCE_PLANKS_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "stone_bricks_horizontal_slab"), STONE_BRICKS_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "stone_horizontal_slab"), STONE_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "terracotta_horizontal_slab"), TERRACOTTA_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "warped_planks_horizontal_slab"), WARPED_PLANKS_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "white_terracotta_horizontal_slab"), WHITE_TERRACOTTA_HORIZONTAL_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "yellow_terracotta_horizontal_slab"), YELLOW_TERRACOTTA_HORIZONTAL_SLAB);

                //Vase Block
                Registry.register(Registry.BLOCK, new Identifier(ModName, "terracotta_vase"), TERRACOTTA_VASE);

                //Tarracotta Bricks
                Registry.register(Registry.BLOCK, new Identifier(ModName, "terracotta_bricks"), TERRACOTTA_BRICKS);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "terracotta_bricks_slab"), TERRACOTTA_BRICKS_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "terracotta_bricks_stairs"), TERRACOTTA_BRICKS_STAIRS);
                
                Registry.register(Registry.BLOCK, new Identifier(ModName, "red_terracotta_bricks"), RED_TERRACOTTA_BRICKS);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "red_terracotta_bricks_slab"), RED_TERRACOTTA_BRICKS_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "red_terracotta_bricks_stairs"), RED_TERRACOTTA_BRICKS_STAIRS);
                
                Registry.register(Registry.BLOCK, new Identifier(ModName, "red_terracotta_slab"), RED_TERRACOTTA_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "red_terracotta_stairs"), RED_TERRACOTTA_STAIRS);
                
                Registry.register(Registry.BLOCK, new Identifier(ModName, "cyan_terracotta_slab"), CYAN_TERRACOTTA_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "cyan_terracotta_stairs"), CYAN_TERRACOTTA_STAIRS);

                Registry.register(Registry.BLOCK, new Identifier(ModName, "black_terracotta_slab"), BLACK_TERRACOTTA_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "black_terracotta_stairs"), BLACK_TERRACOTTA_STAIRS);

                Registry.register(Registry.BLOCK, new Identifier(ModName, "blue_terracotta_slab"), BLUE_TERRACOTTA_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "blue_terracotta_stairs"), BLUE_TERRACOTTA_STAIRS);

                Registry.register(Registry.BLOCK, new Identifier(ModName, "brown_terracotta_slab"), BROWN_TERRACOTTA_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "brown_terracotta_stairs"), BROWN_TERRACOTTA_STAIRS);

                Registry.register(Registry.BLOCK, new Identifier(ModName, "light_blue_terracotta_slab"), LIGHT_BLUE_TERRACOTTA_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "light_blue_terracotta_stairs"), LIGHT_BLUE_TERRACOTTA_STAIRS);
                
                Registry.register(Registry.BLOCK, new Identifier(ModName, "gray_terracotta_slab"), GRAY_TERRACOTTA_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "gray_terracotta_stairs"), GRAY_TERRACOTTA_STAIRS);

                Registry.register(Registry.BLOCK, new Identifier(ModName, "green_terracotta_slab"), GREEN_TERRACOTTA_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "green_terracotta_stairs"), GREEN_TERRACOTTA_STAIRS);

                Registry.register(Registry.BLOCK, new Identifier(ModName, "lime_terracotta_slab"), LIME_TERRACOTTA_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "lime_terracotta_stairs"), LIME_TERRACOTTA_STAIRS);

                Registry.register(Registry.BLOCK, new Identifier(ModName, "light_gray_terracotta_slab"), LIGHT_GRAY_TERRACOTTA_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "light_gray_terracotta_stairs"), LIGHT_GRAY_TERRACOTTA_STAIRS);

                Registry.register(Registry.BLOCK, new Identifier(ModName, "magenta_terracotta_slab"), MAGENTA_TERRACOTTA_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "magenta_terracotta_stairs"), MAGENTA_TERRACOTTA_STAIRS);

                Registry.register(Registry.BLOCK, new Identifier(ModName, "orange_terracotta_slab"), ORANGE_TERRACOTTA_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "orange_terracotta_stairs"), ORANGE_TERRACOTTA_STAIRS);

                Registry.register(Registry.BLOCK, new Identifier(ModName, "pink_terracotta_slab"), PINK_TERRACOTTA_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "pink_terracotta_stairs"), PINK_TERRACOTTA_STAIRS);

                Registry.register(Registry.BLOCK, new Identifier(ModName, "purple_terracotta_slab"), PURPLE_TERRACOTTA_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "purple_terracotta_stairs"), PURPLE_TERRACOTTA_STAIRS);
                
                Registry.register(Registry.BLOCK, new Identifier(ModName, "yellow_terracotta_slab"), YELLOW_TERRACOTTA_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "yellow_terracotta_stairs"), YELLOW_TERRACOTTA_STAIRS);

                Registry.register(Registry.BLOCK, new Identifier(ModName, "terracotta_slab"), TERRACOTTA_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "terracotta_stairs"), TERRACOTTA_STAIRS);

                
                Registry.register(Registry.BLOCK, new Identifier(ModName, "sand_layer"), SAND_LAYER);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "red_sand_layer"), RED_SAND_LAYER);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "gravel_layer"), GRAVEL_LAYER);
                
                Registry.register(Registry.BLOCK, new Identifier(ModName, "coarse_dirt_cobble_stone_mix_light"), CD_CS_LIGHT);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "coarse_dirt_cobble_stone_mix_heavy"), CD_CS_HEAVY);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "sand_stone_brick_mix_light"), SD_SB_LIGHT);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "sand_stone_brick_mix_heavy"), SD_SB_HEAVY);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "sand_chiseled_stone_brick_mix"), SD_CSB_LIGHT);
                
                Registry.register(Registry.BLOCK, new Identifier(ModName, "blue_terracotta_bricks"), BLUE_TERRACOTTA_BRICKS);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "blue_terracotta_bricks_slab"), BLUE_TERRACOTTA_BRICKS_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "blue_terracotta_bricks_stairs"), BLUE_TERRACOTTA_BRICKS_STAIRS);
                
                Registry.register(Registry.BLOCK, new Identifier(ModName, "white_terracotta_bricks"), WHITE_TERRACOTTA_BRICKS);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "white_terracotta_bricks_slab"), WHITE_TERRACOTTA_BRICKS_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "white_terracotta_bricks_stairs"), WHITE_TERRACOTTA_BRICKS_STAIRS);

                Registry.register(Registry.BLOCK, new Identifier(ModName, "brown_terracotta_bricks"), BROWN_TERRACOTTA_BRICKS);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "brown_terracotta_bricks_slab"), BROWN_TERRACOTTA_BRICKS_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "brown_terracotta_bricks_stairs"), BROWN_TERRACOTTA_BRICKS_STAIRS);

                Registry.register(Registry.BLOCK, new Identifier(ModName, "cyan_terracotta_bricks"), CYAN_TERRACOTTA_BRICKS);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "cyan_terracotta_bricks_slab"), CYAN_TERRACOTTA_BRICKS_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "cyan_terracotta_bricks_stairs"), CYAN_TERRACOTTA_BRICKS_STAIRS);

                Registry.register(Registry.BLOCK, new Identifier(ModName, "white_terracotta_slab"), WHITE_TERRACOTTA_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "white_terracotta_stairs"), WHITE_TERRACOTTA_STAIRS);

                Registry.register(Registry.BLOCK, new Identifier(ModName, "black_terracotta_bricks"), BLACK_TERRACOTTA_BRICKS);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "black_terracotta_bricks_slab"), BLACK_TERRACOTTA_BRICKS_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "black_terracotta_bricks_stairs"), BLACK_TERRACOTTA_BRICKS_STAIRS);

                Registry.register(Registry.BLOCK, new Identifier(ModName, "gray_terracotta_bricks"), GRAY_TERRACOTTA_BRICKS);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "gray_terracotta_bricks_slab"), GRAY_TERRACOTTA_BRICKS_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "gray_terracotta_bricks_stairs"), GRAY_TERRACOTTA_BRICKS_STAIRS);

                Registry.register(Registry.BLOCK, new Identifier(ModName, "green_terracotta_bricks"), GREEN_TERRACOTTA_BRICKS);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "green_terracotta_bricks_slab"), GREEN_TERRACOTTA_BRICKS_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "green_terracotta_bricks_stairs"), GREEN_TERRACOTTA_BRICKS_STAIRS);

                Registry.register(Registry.BLOCK, new Identifier(ModName, "light_blue_terracotta_bricks"), LIGHT_BLUE_TERRACOTTA_BRICKS);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "light_blue_terracotta_bricks_slab"), LIGHT_BLUE_TERRACOTTA_BRICKS_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "light_blue_terracotta_bricks_stairs"), LIGHT_BLUE_TERRACOTTA_BRICKS_STAIRS);

                Registry.register(Registry.BLOCK, new Identifier(ModName, "light_gray_terracotta_bricks"), LIGHT_GRAY_TERRACOTTA_BRICKS);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "light_gray_terracotta_bricks_slab"), LIGHT_GRAY_TERRACOTTA_BRICKS_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "light_gray_terracotta_bricks_stairs"), LIGHT_GRAY_TERRACOTTA_BRICKS_STAIRS);

                Registry.register(Registry.BLOCK, new Identifier(ModName, "pink_terracotta_bricks"), PINK_TERRACOTTA_BRICKS);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "pink_terracotta_bricks_slab"), PINK_TERRACOTTA_BRICKS_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "pink_terracotta_bricks_stairs"), PINK_TERRACOTTA_BRICKS_STAIRS);

                Registry.register(Registry.BLOCK, new Identifier(ModName, "purple_terracotta_bricks"), PURPLE_TERRACOTTA_BRICKS);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "purple_terracotta_bricks_slab"), PURPLE_TERRACOTTA_BRICKS_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "purple_terracotta_bricks_stairs"), PURPLE_TERRACOTTA_BRICKS_STAIRS);

                Registry.register(Registry.BLOCK, new Identifier(ModName, "burnt_log"), BURNT_LOG);

                Registry.register(Registry.BLOCK, new Identifier(ModName, "burnt_planks"), BURNT_PLANKS);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "burnt_planks_slab"), BURNT_PLANKS_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "burnt_planks_stairs"), BURNT_PLANKS_STAIRS);

                Registry.register(Registry.BLOCK, new Identifier(ModName, "stone_brick_tiles"), STONE_BRICK_SQUARE);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "stone_brick_tiles_slab"), STONE_BRICK_SQUARE_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "stone_brick_tiles_stairs"), STONE_BRICK_SQUARE_STAIRS);

                Registry.register(Registry.BLOCK, new Identifier(ModName, "dirt_slab"), DIRT_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "coarse_dirt_slab"), COARSE_DIRT_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "grass_block_slab"), GRASS_SLAB);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "snow_grass_block_slab"), SNOW_GRASS_SLAB);

                //Chimney
                Registry.register(Registry.BLOCK, new Identifier(ModName, "cobblestone_chimney"), COBBLESTONE_CHIMNEY);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "andesite_chimney"), ANDESITE_CHIMNEY);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "granite_chimney"), GRANITE_CHIMNEY);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "diorite_chimney"), DIORITE_CHIMNEY);
                Registry.register(Registry.BLOCK, new Identifier(ModName, "bricks_chimney"), BRICKS_CHIMNEY);

                //Vertical Glass Pane
                Registry.register(Registry.BLOCK, new Identifier(ModName, "vertical_glass_pane"), VERTICAL_GLASS_PANE);

                //Lantern
                Registry.register(Registry.BLOCK, new Identifier(ModName, "lantern_rope"), LANTERN_ROPE);
    }

}
