package com.tylervp;


/* import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.SlabBlock;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry; */

public class MBMBlocks {
/*
    public static final Block CARVEDMELON;
    public static final Block DARK_STONE;
    public static final Block DARK_STONE_SLAB;
    public static final Block DARK_STONE_STAIRS;
    public static final Block DARK_STONE_BRICK;
    public static final Block DARK_STONE_BRICK_SLAB;
    public static final Block DARK_STONE_BRICK_STAIRS;
    public static final Block DARK_STONE_TILES;
    public static final Block DARK_STONE_TILES_SLAB;
    public static final Block DARK_STONE_TILES_STAIRS;
    public static final Block EMBLEMED_DARK_STONE;


    static {
        //Carved Melon
        CARVEDMELON = Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "carved_melon"), new CarvedMelonBlock(FabricBlockSettings.of(Material.GOURD).breakByHand(true).hardness(1).resistance(1).breakByTool(FabricToolTags.SWORDS).breakByTool(FabricToolTags.AXES).sounds(BlockSoundGroup.WOOD)));
        
        //Dark Stone
        DARK_STONE = Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "dark_stone"), new Block(FabricBlockSettings.of(Material.STONE).requiresTool().breakByHand(false).breakByTool(FabricToolTags.PICKAXES).hardness(1.5f).resistance(6f)));
        DARK_STONE_SLAB = Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "dark_stone_slab"), new SlabBlock(FabricBlockSettings.of(Material.STONE).requiresTool().breakByHand(false).breakByTool(FabricToolTags.PICKAXES).hardness(1.5f).resistance(6f)));
        DARK_STONE_STAIRS = Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "dark_stone_stairs"), new StairsBlockExtend(DARK_STONE.getDefaultState(), FabricBlockSettings.of(Material.STONE).requiresTool().breakByHand(false).breakByTool(FabricToolTags.PICKAXES).hardness(1.5f).resistance(6f)));
        DARK_STONE_BRICK= Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "dark_stone_bricks"), new Block(FabricBlockSettings.of(Material.STONE).requiresTool().breakByHand(false).breakByTool(FabricToolTags.PICKAXES).hardness(1.5f).resistance(6f)));
        DARK_STONE_BRICK_SLAB = Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "dark_stone_brick_slab"), new SlabBlock(FabricBlockSettings.of(Material.STONE).requiresTool().breakByHand(false).breakByTool(FabricToolTags.PICKAXES).hardness(1.5f).resistance(6f)));
        DARK_STONE_BRICK_STAIRS = Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "dark_stone_brick_stairs"), new StairsBlockExtend(DARK_STONE_BRICK.getDefaultState(), FabricBlockSettings.of(Material.STONE).requiresTool().breakByHand(false).breakByTool(FabricToolTags.PICKAXES).hardness(1.5f).resistance(6f)));
        DARK_STONE_TILES = Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "dark_stone_tiles"), new Block(FabricBlockSettings.of(Material.STONE).requiresTool().breakByHand(false).breakByTool(FabricToolTags.PICKAXES).hardness(1.5f).resistance(6f)));
        DARK_STONE_TILES_SLAB = Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "dark_stone_tiles_slab"), new SlabBlock(FabricBlockSettings.of(Material.STONE).requiresTool().breakByHand(false).breakByTool(FabricToolTags.PICKAXES).hardness(1.5f).resistance(6f)));
        DARK_STONE_TILES_STAIRS = Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "dark_stone_tiles_stairs"), new StairsBlockExtend(DARK_STONE_TILES.getDefaultState(), FabricBlockSettings.of(Material.STONE).requiresTool().breakByHand(false).breakByTool(FabricToolTags.PICKAXES).hardness(1.5f).resistance(6f)));
        EMBLEMED_DARK_STONE = Registry.register(Registry.BLOCK, new Identifier("moreblocksmod", "emblemed_dark_stone"), new Block(FabricBlockSettings.of(Material.STONE).requiresTool().breakByHand(false).breakByTool(FabricToolTags.PICKAXES).hardness(1.5f).resistance(6f)));
    }








    //Packed Terracotta
    public static final Block YELLOW_PACKED_TERRACOTTA = new Block(FabricBlockSettings.of(Material.STONE).breakByHand(false).breakByTool(FabricToolTags.PICKAXES).hardness(1.25f).resistance(4.2f));

    //HorizontalBlocks
    public static final Block POLISHED_ANDESITE_HORIZONTAL_SLAB = new SideSlab(FabricBlockSettings.of(Material.STONE).breakByHand(false).breakByTool(FabricToolTags.PICKAXES).hardness(1.5f).resistance(6f));
    //public static final SideSlab WHITE_TERRACOTTA_BRICK_WINDOW = new SideSlab(Block.Settings.of(Material.STONE));

    //Directional Block
    //public static final GlazedTerracottaBlock C_TRAN = new GlazedTerracottaBlock(FabricBlockSettings.of(Material.STONE).breakByHand(false).breakByTool(FabricToolTags.PICKAXES).hardness(1.25f).resistance(4.2f));
    
    public static final Block PACKEDMUD = new PackedMudBlock(FabricBlockSettings.of(Material.SOIL).breakByHand(true).breakByTool(FabricToolTags.SHOVELS).sounds(BlockSoundGroup.SOUL_SOIL).hardness(2).resistance(5));

    //PillarBlocks
    public static final Block LEATHER_BLOCK = new PillarBlock(FabricBlockSettings.of(Material.WOOD).breakByHand(true).breakByTool(FabricToolTags.AXES).sounds(BlockSoundGroup.WOOD).hardness(2).resistance(5));
    public static final Block PETRIFIED_WOOD = new PetrifiedPillarBlock(FabricBlockSettings.of(Material.WOOD).breakByHand(true).breakByTool(FabricToolTags.AXES).sounds(BlockSoundGroup.WOOD).hardness(2).resistance(5));

    //Rope
    public static final Block ROPE = new RopeBlock(FabricBlockSettings.of(Material.WOOL).breakInstantly().breakByHand(true).sounds(BlockSoundGroup.WOOL).hardness(0).resistance(5));
    public static final Block ROPEMID = new RopeBlockMid(FabricBlockSettings.of(Material.WOOL).breakInstantly().breakByHand(true).sounds(BlockSoundGroup.WOOL).hardness(0).resistance(5));

    //Thin Logs and Log Related Items
    public static final Block THIN_ACACIA_LOG = new ThinPillarBlock(FabricBlockSettings.of(Material.WOOD).breakByTool(FabricToolTags.AXES).breakByHand(true).sounds(BlockSoundGroup.WOOD).hardness(2).resistance(2).ticksRandomly());
    public static final Block THIN_STRIPPED_ACACIA_LOG = new ThinPillarBlock(FabricBlockSettings.of(Material.WOOD).breakByTool(FabricToolTags.AXES).breakByHand(true).sounds(BlockSoundGroup.WOOD).hardness(2).resistance(2).ticksRandomly());
    public static final Item ACACIA_BARK_FRAGMENT = new Item(new Item.Settings().group(ItemGroup.MISC));

    public static final Block THIN_BIRCH_LOG = new ThinPillarBlock(FabricBlockSettings.of(Material.WOOD).breakByTool(FabricToolTags.AXES).breakByHand(true).sounds(BlockSoundGroup.WOOD).hardness(2).resistance(2).ticksRandomly());
    public static final Block THIN_STRIPPED_BIRCH_LOG = new ThinPillarBlock(FabricBlockSettings.of(Material.WOOD).breakByTool(FabricToolTags.AXES).breakByHand(true).sounds(BlockSoundGroup.WOOD).hardness(2).resistance(2).ticksRandomly());
    public static final Item BIRCH_BARK_FRAGMENT = new Item(new Item.Settings().group(ItemGroup.MISC));

    public static final Block THIN_DARK_OAK_LOG = new ThinPillarBlock(FabricBlockSettings.of(Material.WOOD).breakByTool(FabricToolTags.AXES).breakByHand(true).sounds(BlockSoundGroup.WOOD).hardness(2).resistance(2).ticksRandomly());
    public static final Block THIN_STRIPPED_DARK_OAK_LOG = new ThinPillarBlock(FabricBlockSettings.of(Material.WOOD).breakByTool(FabricToolTags.AXES).breakByHand(true).sounds(BlockSoundGroup.WOOD).hardness(2).resistance(2).ticksRandomly());
    public static final Item DARK_OAK_BARK_FRAGMENT = new Item(new Item.Settings().group(ItemGroup.MISC));

    public static final Block THIN_JUNGLE_LOG = new ThinPillarBlock(FabricBlockSettings.of(Material.WOOD).breakByTool(FabricToolTags.AXES).breakByHand(true).sounds(BlockSoundGroup.WOOD).hardness(2).resistance(2).ticksRandomly());
    public static final Block THIN_STRIPPED_JUNGLE_LOG = new ThinPillarBlock(FabricBlockSettings.of(Material.WOOD).breakByTool(FabricToolTags.AXES).breakByHand(true).sounds(BlockSoundGroup.WOOD).hardness(2).resistance(2).ticksRandomly());
    public static final Item JUNGLE_BARK_FRAGMENT = new Item(new Item.Settings().group(ItemGroup.MISC));
    
    public static final Block THIN_OAK_LOG = new ThinPillarBlock(FabricBlockSettings.of(Material.WOOD).breakByTool(FabricToolTags.AXES).breakByHand(true).hardness(2).resistance(2).ticksRandomly());
    public static final Block THIN_STRIPPED_OAK_LOG = new ThinPillarBlock(FabricBlockSettings.of(Material.WOOD).breakByTool(FabricToolTags.AXES).breakByHand(true).sounds(BlockSoundGroup.WOOD).hardness(2).resistance(2).ticksRandomly());
    public static final Item OAK_BARK_FRAGMENT = new Item(new Item.Settings().group(ItemGroup.MISC));
    
    public static final Block THIN_SPRUCE_LOG = new ThinPillarBlock(FabricBlockSettings.of(Material.WOOD).breakByTool(FabricToolTags.AXES).breakByHand(true).sounds(BlockSoundGroup.WOOD).hardness(2).resistance(2).ticksRandomly());
    public static final Block THIN_STRIPPED_SPRUCE_LOG = new ThinPillarBlock(FabricBlockSettings.of(Material.WOOD).breakByTool(FabricToolTags.AXES).breakByHand(true).sounds(BlockSoundGroup.WOOD).hardness(2).resistance(2).ticksRandomly());
    public static final Item SPRUCE_BARK_FRAGMENT = new Item(new Item.Settings().group(ItemGroup.MISC));

    //Blocks
    public static final Block TERRACOTTA_BRICKS = new Block(FabricBlockSettings.of(Material.STONE).breakByHand(false).breakByTool(FabricToolTags.PICKAXES).hardness(1.25f).resistance(4.2f));
    public static final Block RED_TERRACOTTA_BRICKS = new Block(FabricBlockSettings.of(Material.STONE).breakByHand(false).breakByTool(FabricToolTags.PICKAXES).hardness(1.25f).resistance(4.2f));
    public static final Block WHITE_TERRACOTTA_BRICKS = new Block(FabricBlockSettings.of(Material.STONE).breakByHand(false).breakByTool(FabricToolTags.PICKAXES).hardness(1.25f).resistance(4.2f));
    public static final Block BLUE_TERRACOTTA_BRICKS = new Block(FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES));


    public static final Block CD_CS_LIGHT = new Block(FabricBlockSettings.of(Material.STONE).breakByHand(false).breakByTool(FabricToolTags.PICKAXES).hardness(1.25f).resistance(4.2f));
    public static final Block CD_CS_HEAVY = new Block(FabricBlockSettings.of(Material.STONE).breakByHand(false).breakByTool(FabricToolTags.PICKAXES).hardness(1.25f).resistance(4.2f));

    public static final Block SD_SB_HEAVY = new Block(FabricBlockSettings.of(Material.STONE).breakByHand(false).breakByTool(FabricToolTags.PICKAXES).hardness(1.25f).resistance(4.2f));
    public static final Block SD_SB_LIGHT = new Block(FabricBlockSettings.of(Material.STONE).breakByHand(false).breakByTool(FabricToolTags.PICKAXES).hardness(1.25f).resistance(4.2f));

    public static final Block SD_CSB_LIGHT = new Block(FabricBlockSettings.of(Material.STONE).breakByHand(false).breakByTool(FabricToolTags.PICKAXES).hardness(1.25f).resistance(4.2f));


    //Slabs
    public static final Block TERRACOTTA_BRICKS_SLAB = new SlabBlock(FabricBlockSettings.of(Material.STONE).breakByHand(false).breakByTool(FabricToolTags.PICKAXES).hardness(1.25f).resistance(4.2f));
    public static final Block RED_TERRACOTTA_SLAB = new SlabBlock(FabricBlockSettings.of(Material.STONE).breakByHand(false).breakByTool(FabricToolTags.PICKAXES).hardness(1.25f).resistance(4.2f));
    public static final Block WHITE_TERRACOTTA_BRICKS_SLAB = new SlabBlock(FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE));
    public static final Block RED_TERRACOTTA_BRICKS_SLAB = new SlabBlock(FabricBlockSettings.of(Material.STONE).breakByHand(false).breakByTool(FabricToolTags.PICKAXES).hardness(1.25f).resistance(4.2f));
    public static final Block CYAN_TERRACOTTA_SLAB = new SlabBlock(FabricBlockSettings.of(Material.STONE).breakByHand(false).breakByTool(FabricToolTags.PICKAXES).hardness(1.25f).resistance(4.2f));


    //LayerBlock
    public static final Block SAND_LAYER = new LayerBlock(FabricBlockSettings.of(Material.AGGREGATE).sounds(BlockSoundGroup.SAND).hardness(0.5f).resistance(0.5f));
    public static final Block RED_SAND_LAYER = new LayerBlock(FabricBlockSettings.of(Material.AGGREGATE).sounds(BlockSoundGroup.SAND).hardness(0.5f).resistance(0.5f));
    public static final Block GRAVEL_LAYER = new LayerBlock(FabricBlockSettings.of(Material.AGGREGATE).sounds(BlockSoundGroup.GRAVEL).hardness(0.5f).resistance(0.5f));

    //Stairs
    public static final StairsBlockExtend TERRACOTTA_BRICKS_STAIRS = new StairsBlockExtend(TERRACOTTA_BRICKS.getDefaultState(), FabricBlockSettings.of(Material.STONE).hardness(1.25f).resistance(4.2f));
    public static final StairsBlockExtend RED_TERRACOTTA_BRICKS_STAIRS = new StairsBlockExtend(RED_TERRACOTTA_BRICKS.getDefaultState(), FabricBlockSettings.of(Material.STONE).hardness(1.25f).resistance(4.2f));
    public static final StairsBlockExtend RED_TERRACOTTA_STAIRS = new StairsBlockExtend(Blocks.RED_TERRACOTTA.getDefaultState(), FabricBlockSettings.of(Material.STONE).hardness(1.25f).resistance(4.2f));
    public static final StairsBlockExtend CYAN_TERRACOTTA_STAIRS = new StairsBlockExtend(Blocks.CYAN_TERRACOTTA.getDefaultState(), FabricBlockSettings.of(Material.STONE).hardness(1.25f).resistance(4.2f));


    public static final GrassBlock DEAD_GRASS_BLOCK = new GrassBlock(FabricBlockSettings.of(Material.SOIL).sounds(BlockSoundGroup.GRASS));

    
    //public static final FernBlock DEAD_GRASS = new FernBlock(FabricBlockSettings.of(Material.REPLACEABLE_PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS));

    public static final StairsBlockExtend WHITE_TERRACOTTA_BRICKS_STAIRS = new StairsBlockExtend(WHITE_TERRACOTTA_BRICKS.getDefaultState(), FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE));

    public static final Block BROWN_TERRACOTTA_BRICKS = new Block(FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE));

    public static final SlabBlock BROWN_TERRACOTTA_BRICKS_SLAB = new SlabBlock(FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE));

    public static final StairsBlockExtend BROWN_TERRACOTTA_BRICKS_STAIRS = new StairsBlockExtend(BROWN_TERRACOTTA_BRICKS.getDefaultState(), FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE));

    public static final Block CYAN_TERRACOTTA_BRICKS = new Block(FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES).breakByHand(false).hardness(1.25f).resistance(4.2f));

    public static final SlabBlock CYAN_TERRACOTTA_BRICKS_SLAB = new SlabBlock(FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES).breakByHand(false).hardness(1.25f).resistance(4.2f));

    public static final StairsBlockExtend CYAN_TERRACOTTA_BRICKS_STAIRS = new StairsBlockExtend(CYAN_TERRACOTTA_BRICKS.getDefaultState(), FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES).breakByHand(false).hardness(1.25f).resistance(4.2f));

    public static final SlabBlock WHITE_TERRACOTTA_SLAB = new SlabBlock(FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES).breakByHand(false).hardness(1.25f).resistance(4.2f));

    public static final StairsBlockExtend WHITE_TERRACOTTA_STAIRS = new StairsBlockExtend(Blocks.WHITE_TERRACOTTA.getDefaultState(), FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES).breakByHand(false).hardness(1.25f).resistance(4.2f));

    public static final Block BLACK_TERRACOTTA_BRICKS = new Block(FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES).breakByHand(false).hardness(1.25f).resistance(4.2f));

    public static final SlabBlock BLACK_TERRACOTTA_BRICKS_SLAB = new SlabBlock(FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES).breakByHand(false).hardness(1.25f).resistance(4.2f));

    public static final StairsBlockExtend BLACK_TERRACOTTA_BRICKS_STAIRS = new StairsBlockExtend(BLACK_TERRACOTTA_BRICKS.getDefaultState(), FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES).breakByHand(false).hardness(1.25f).resistance(4.2f));

    public static final Block GRAY_TERRACOTTA_BRICKS = new Block(FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES).breakByHand(false).hardness(1.25f).resistance(4.2f));

    public static final SlabBlock GRAY_TERRACOTTA_BRICKS_SLAB = new SlabBlock(FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES).breakByHand(false).hardness(1.25f).resistance(4.2f));

    public static final StairsBlockExtend GRAY_TERRACOTTA_BRICKS_STAIRS = new StairsBlockExtend(GRAY_TERRACOTTA_BRICKS.getDefaultState(), FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES).breakByHand(false).hardness(1.25f).resistance(4.2f));

    public static final Block GREEN_TERRACOTTA_BRICKS = new Block(FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES).breakByHand(false).hardness(1.25f).resistance(4.2f));

    public static final SlabBlock GREEN_TERRACOTTA_BRICKS_SLAB = new SlabBlock(FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES).breakByHand(false).hardness(1.25f).resistance(4.2f));

    public static final StairsBlockExtend GREEN_TERRACOTTA_BRICKS_STAIRS = new StairsBlockExtend(GREEN_TERRACOTTA_BRICKS.getDefaultState(), FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES).breakByHand(false).hardness(1.25f).resistance(4.2f));

    public static final Block LIGHT_BLUE_TERRACOTTA_BRICKS = new Block(FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES).breakByHand(false).hardness(1.25f).resistance(4.2f));

    public static final SlabBlock LIGHT_BLUE_TERRACOTTA_BRICKS_SLAB = new SlabBlock(FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES).breakByHand(false).hardness(1.25f).resistance(4.2f));

    public static final StairsBlockExtend LIGHT_BLUE_TERRACOTTA_BRICKS_STAIRS = new StairsBlockExtend(LIGHT_BLUE_TERRACOTTA_BRICKS.getDefaultState(), FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES).breakByHand(false).hardness(1.25f).resistance(4.2f));

    public static final Block LIGHT_GRAY_TERRACOTTA_BRICKS = new Block(FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES).breakByHand(false).hardness(1.25f).resistance(4.2f));

    public static final SlabBlock LIGHT_GRAY_TERRACOTTA_BRICKS_SLAB = new SlabBlock(FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES).breakByHand(false).hardness(1.25f).resistance(4.2f));

    public static final StairsBlockExtend LIGHT_GRAY_TERRACOTTA_BRICKS_STAIRS = new StairsBlockExtend(LIGHT_GRAY_TERRACOTTA_BRICKS.getDefaultState(), FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES).breakByHand(false).hardness(1.25f).resistance(4.2f));

    public static final Block PINK_TERRACOTTA_BRICKS = new Block(FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES).breakByHand(false).hardness(1.25f).resistance(4.2f));

    public static final SlabBlock PINK_TERRACOTTA_BRICKS_SLAB = new SlabBlock(FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES).breakByHand(false).hardness(1.25f).resistance(4.2f));

    public static final StairsBlockExtend PINK_TERRACOTTA_BRICKS_STAIRS = new StairsBlockExtend(PINK_TERRACOTTA_BRICKS.getDefaultState(), FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES).breakByHand(false).hardness(1.25f).resistance(4.2f));

    public static final Block PURPLE_TERRACOTTA_BRICKS = new Block(FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES).breakByHand(false).hardness(1.25f).resistance(4.2f));

    public static final SlabBlock PURPLE_TERRACOTTA_BRICKS_SLAB = new SlabBlock(FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES).breakByHand(false).hardness(1.25f).resistance(4.2f));

    public static final StairsBlockExtend PURPLE_TERRACOTTA_BRICKS_STAIRS = new StairsBlockExtend(PURPLE_TERRACOTTA_BRICKS.getDefaultState(), FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES).breakByHand(false).hardness(1.25f).resistance(4.2f));

    public static final SlabBlock BLUE_TERRACOTTA_BRICKS_SLAB = new SlabBlock(FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES).breakByHand(false).hardness(1.25f).resistance(4.2f));

    public static final StairsBlockExtend BLUE_TERRACOTTA_BRICKS_STAIRS = new StairsBlockExtend(BLUE_TERRACOTTA_BRICKS.getDefaultState(), FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES).breakByHand(false).hardness(1.25f).resistance(4.2f));

    public static final PillarBlock BURNT_LOG = new PillarBlock(FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).breakByTool(FabricToolTags.AXES).breakByHand(true).hardness(2f).resistance(2f));

    public static final Block BURNT_PLANKS = new Block(FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).breakByTool(FabricToolTags.AXES).breakByHand(true).hardness(2f).resistance(3f));

    public static final SlabBlock BURNT_PLANKS_SLAB = new SlabBlock(FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).breakByTool(FabricToolTags.AXES).breakByHand(true).hardness(2f).resistance(3f));

    public static final StairsBlockExtend BURNT_PLANKS_STAIRS = new StairsBlockExtend(BURNT_PLANKS.getDefaultState(), FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).breakByTool(FabricToolTags.AXES).breakByHand(true).hardness(2f).resistance(3f));

    public static final Block STONE_BRICK_SQUARE = new Block(FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES).breakByHand(false).hardness(1.5f).resistance(6f));

    public static final SlabBlock STONE_BRICK_SQUARE_SLAB = new SlabBlock(FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES).breakByHand(false).hardness(1.5f).resistance(6f));

    public static final StairsBlockExtend STONE_BRICK_SQUARE_STAIRS = new StairsBlockExtend(STONE_BRICK_SQUARE.getDefaultState(), FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES).breakByHand(false).hardness(1.5f).resistance(6f));

    public static final SlabBlock DIRT_SLAB = new SlabBlock(FabricBlockSettings.of(Material.SOIL).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.SHOVELS).breakByHand(true).hardness(0.5f).resistance(0.5f));

    public static final SlabBlock COARSE_DIRT_SLAB = new SlabBlock(FabricBlockSettings.of(Material.SOIL).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.SHOVELS).breakByHand(true).hardness(0.5f).resistance(0.5f));  

    public static final Block YELLOW_TERRACOTTA_BRICKS = new Block(FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES).breakByHand(false).hardness(1.25f).resistance(4.2f));

    public static final SlabBlock YELLOW_TERRACOTTA_BRICKS_SLAB = new SlabBlock(FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES).breakByHand(false).hardness(1.25f).resistance(4.2f));

    public static final StairsBlockExtend YELLOW_TERRACOTTA_BRICKS_STAIRS = new StairsBlockExtend(YELLOW_TERRACOTTA_BRICKS.getDefaultState(), FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES).breakByHand(false).hardness(1.25f).resistance(4.2f));

    public static final Block ORANGE_TERRACOTTA_BRICKS = new Block(FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES).breakByHand(false).hardness(1.25f).resistance(4.2f));

    public static final SlabBlock ORANGE_TERRACOTTA_BRICKS_SLAB = new SlabBlock(FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES).breakByHand(false).hardness(1.25f).resistance(4.2f));

    public static final StairsBlockExtend ORANGE_TERRACOTTA_BRICKS_STAIRS = new StairsBlockExtend(ORANGE_TERRACOTTA_BRICKS.getDefaultState(), FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES).breakByHand(false).hardness(1.25f).resistance(4.2f));

    public static final Block MAGENTA_TERRACOTTA_BRICKS = new Block(FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES).breakByHand(false).hardness(1.25f).resistance(4.2f));

    public static final SlabBlock MAGENTA_TERRACOTTA_BRICKS_SLAB = new SlabBlock(FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES).breakByHand(false).hardness(1.25f).resistance(4.2f));

    public static final StairsBlockExtend MAGENTA_TERRACOTTA_BRICKS_STAIRS = new StairsBlockExtend(MAGENTA_TERRACOTTA_BRICKS.getDefaultState(), FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES).breakByHand(false).hardness(1.25f).resistance(4.2f));

    public static final Block LIME_TERRACOTTA_BRICKS = new Block(FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES).breakByHand(false).hardness(1.25f).resistance(4.2f));

    public static final SlabBlock LIME_TERRACOTTA_BRICKS_SLAB = new SlabBlock(FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES).breakByHand(false).hardness(1.25f).resistance(4.2f));

    public static final StairsBlockExtend LIME_TERRACOTTA_BRICKS_STAIRS = new StairsBlockExtend(LIME_TERRACOTTA_BRICKS.getDefaultState(), FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES).breakByHand(false).hardness(1.25f).resistance(4.2f));
*/
    






}
