package com.tylervp.block;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HayBlock;
import net.minecraft.block.LanternBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.block.OreBlock;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item.Settings;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;

import java.util.function.ToIntFunction;

import com.tylervp.moreblocksmod;
import com.tylervp.block.entity.VaseBlockEntity;
import com.tylervp.fluid.MudFluid;
import com.tylervp.item.MBMItems;

public class MBMBlocks {

    private static ToIntFunction<BlockState> createLightLevelFromBlockState(int litLevel) {
        return blockState -> blockState.<Boolean>get((Property<Boolean>)Properties.LIT) ? litLevel : 0;
    }

    //Quick Settings
    public static final FabricBlockSettings CONCRETE_POWDER_SETTINGS = FabricBlockSettings.of(Material.AGGREGATE).requiresTool().breakByHand(false).breakByTool(FabricToolTags.SHOVELS).resistance(0.5f).hardness(0.5f).sounds(BlockSoundGroup.SAND);
    public static final FabricBlockSettings CONCRETE_SETTINGS = FabricBlockSettings.of(Material.STONE).requiresTool().breakByHand(false).breakByTool(FabricToolTags.PICKAXES).resistance(1.8f).hardness(1.8f).sounds(BlockSoundGroup.STONE);
    public static final FabricBlockSettings TERRACOTTA_SETTINGS = FabricBlockSettings.of(Material.STONE).requiresTool().breakByHand(false).breakByTool(FabricToolTags.PICKAXES).resistance(4.2f).hardness(1.25f).sounds(BlockSoundGroup.STONE);
    public static final FabricBlockSettings STONE_SETTINGS = FabricBlockSettings.of(Material.STONE).requiresTool().breakByHand(false).breakByTool(FabricToolTags.PICKAXES).resistance(6f).hardness(1.5f).sounds(BlockSoundGroup.STONE);
    public static final FabricBlockSettings THIN_LOG_SETTINGS = FabricBlockSettings.of(Material.WOOD).breakByTool(FabricToolTags.AXES).breakByHand(true).sounds(BlockSoundGroup.WOOD).resistance(2).hardness(2).ticksRandomly();
    public static final FabricBlockSettings GLASS_SETTINGS = FabricBlockSettings.of(Material.GLASS).breakByTool(FabricToolTags.PICKAXES).breakByHand(false).sounds(BlockSoundGroup.GLASS).resistance(0.3f).hardness(0.3f);
    public static final FabricBlockSettings PLANKS_SETTINGS = FabricBlockSettings.of(Material.WOOD).breakByTool(FabricToolTags.AXES).breakByHand(true).sounds(BlockSoundGroup.WOOD).resistance(3.0f).hardness(2.0f);
    public static final FabricBlockSettings NETHER_PLANKS_SETTINGS = FabricBlockSettings.of(Material.NETHER_WOOD).breakByTool(FabricToolTags.AXES).breakByHand(true).sounds(BlockSoundGroup.WOOD).resistance(3.0f).hardness(2.0f);
    public static final FabricBlockSettings SAND_STONE = FabricBlockSettings.of(Material.STONE).requiresTool().breakByHand(false).breakByTool(FabricToolTags.PICKAXES).resistance(0.8f).hardness(0.8f).sounds(BlockSoundGroup.STONE);
    public static final FabricBlockSettings IRON_SETTINGS = FabricBlockSettings.of(Material.STONE, MaterialColor.RED).requiresTool().strength(2.0f, 6.0f);
    public static final FabricBlockSettings JEWEL_BLOCK_SETTINGS = FabricBlockSettings.of(Material.METAL).requiresTool().strength(5.0f, 6.0f);
    public static final FabricBlockSettings DIRT_SETTINGS = FabricBlockSettings.of(Material.SOIL, MaterialColor.DIRT).strength(0.5f).sounds(BlockSoundGroup.GRAVEL);
    public static final FabricBlockSettings LOG_SETTINGS = FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).breakByTool(FabricToolTags.AXES).breakByHand(true).hardness(2f).resistance(2f);

    //Chimney
    public static final ChimneyBlock COBBLESTONE_CHIMNEY = new ChimneyBlock(STONE_SETTINGS);
    public static final ChimneyBlock ANDESITE_CHIMNEY = new ChimneyBlock(STONE_SETTINGS);
    public static final ChimneyBlock GRANITE_CHIMNEY = new ChimneyBlock(STONE_SETTINGS);
    public static final ChimneyBlock DIORITE_CHIMNEY = new ChimneyBlock(STONE_SETTINGS);
    public static final ChimneyBlock BRICKS_CHIMNEY = new ChimneyBlock(STONE_SETTINGS); //H6,R2

    //Vertical Galss Pane
    public static final HorizontalPaneBlock VERTICAL_GLASS_PANE = new HorizontalPaneBlock(GLASS_SETTINGS);

    //Carved Melon Block
    public static final CarvedMelonBlock CARVEDMELON = new CarvedMelonBlock(FabricBlockSettings.of(Material.GOURD).breakByHand(true).hardness(1).resistance(1).breakByTool(FabricToolTags.SWORDS).breakByTool(FabricToolTags.AXES).sounds(BlockSoundGroup.WOOD));

    //Spike
    public static final SpikeBlock SPIKE = new SpikeBlock(IRON_SETTINGS);
    
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


    public static final SideSlab DARK_STONE_VERTICAL_SLAB = new SideSlab(STONE_SETTINGS);
    public static final SideSlab DARK_STONE_BRICKS_VERTICAL_SLAB = new SideSlab(STONE_SETTINGS);
    public static final SideSlab DARK_STONE_TILES_VERTICAL_SLAB = new SideSlab(STONE_SETTINGS);
    public static final SideSlab DARK_COBBLESTONE_VERTICAL_SLAB = new SideSlab(STONE_SETTINGS);

    //Brick Tiles
    public static final Block POLISHED_ANDESITE_BRICK_TILES = new Block(STONE_SETTINGS);
    public static final Block POLISHED_DIORITE_BRICK_TILES = new Block(STONE_SETTINGS);
    public static final Block POLISHED_GRANITE_BRICK_TILES = new Block(STONE_SETTINGS);
    public static final Block POLISHED_BLACKSTONE_BRICK_TILES = new Block(STONE_SETTINGS);
    public static final SideSlab POLISHED_BLACKSTONE_BRICK_TILES_VERTICAL_SLAB = new SideSlab(STONE_SETTINGS);

    public static final Block POLISHED_ANDESITE_BRICKS = new Block(STONE_SETTINGS);
    public static final Block POLISHED_DIORITE_BRICKS = new Block(STONE_SETTINGS);
    public static final Block POLISHED_GRANITE_BRICKS = new Block(STONE_SETTINGS);

    //Pillar
    public static final PillarBlock ANDESITE_PILLAR = new PillarBlock(STONE_SETTINGS);
    public static final PillarBlock DIORITE_PILLAR = new PillarBlock(STONE_SETTINGS);
    public static final PillarBlock GRANITE_PILLAR = new PillarBlock(STONE_SETTINGS);
    public static final PillarBlock STONE_PILLAR = new PillarBlock(STONE_SETTINGS);

    //Thin Pillar
    public static final ThinPillarBlock THIN_ANDESITE_PILLAR = new ThinPillarBlock(STONE_SETTINGS);
    public static final ThinPillarBlock THIN_DIORITE_PILLAR = new ThinPillarBlock(STONE_SETTINGS);
    public static final ThinPillarBlock THIN_GRANITE_PILLAR = new ThinPillarBlock(STONE_SETTINGS);
    public static final ThinPillarBlock THIN_STONE_PILLAR = new ThinPillarBlock(STONE_SETTINGS);

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
    public static final SideSlab CRACKED_POLISHED_BLACKSTONE_BRICKS_VERTICAL_SLAB = new SideSlab(STONE_SETTINGS);
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

    public static final SideSlab POLISHED_ANDESITE_BRICK_TILES_VERTICAL_SLAB = new SideSlab(STONE_SETTINGS);
    public static final SideSlab POLISHED_DIORITE_BRICK_TILES_VERTICAL_SLAB = new SideSlab(STONE_SETTINGS);
    public static final SideSlab POLISHED_GRANITE_BRICK_TILES_VERTICAL_SLAB = new SideSlab(STONE_SETTINGS);
    public static final SideSlab POLISHED_ANDESITE_BRICKS_VERTICAL_SLAB = new SideSlab(STONE_SETTINGS);
    public static final SideSlab POLISHED_DIORITE_BRICKS_VERTICAL_SLAB = new SideSlab(STONE_SETTINGS);
    public static final SideSlab POLISHED_GRANITE_BRICKS_VERTICAL_SLAB = new SideSlab(STONE_SETTINGS);

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
    public static final ThinLogBlock THIN_ACACIA_LOG = new ThinLogBlock(THIN_LOG_SETTINGS);
    public static final ThinLogBlock THIN_STRIPPED_ACACIA_LOG = new ThinLogBlock(THIN_LOG_SETTINGS);

    public static final ThinLogBlock THIN_BIRCH_LOG = new ThinLogBlock(THIN_LOG_SETTINGS);
    public static final ThinLogBlock THIN_STRIPPED_BIRCH_LOG = new ThinLogBlock(THIN_LOG_SETTINGS);

    public static final ThinLogBlock THIN_DARK_OAK_LOG = new ThinLogBlock(THIN_LOG_SETTINGS);
    public static final ThinLogBlock THIN_STRIPPED_DARK_OAK_LOG = new ThinLogBlock(THIN_LOG_SETTINGS);

    public static final ThinLogBlock THIN_JUNGLE_LOG = new ThinLogBlock(THIN_LOG_SETTINGS);
    public static final ThinLogBlock THIN_STRIPPED_JUNGLE_LOG = new ThinLogBlock(THIN_LOG_SETTINGS);
    
    public static final ThinLogBlock THIN_OAK_LOG = new ThinLogBlock(THIN_LOG_SETTINGS);
    public static final ThinLogBlock THIN_STRIPPED_OAK_LOG = new ThinLogBlock(THIN_LOG_SETTINGS);
    
    public static final ThinLogBlock THIN_SPRUCE_LOG = new ThinLogBlock(THIN_LOG_SETTINGS);
    public static final ThinLogBlock THIN_STRIPPED_SPRUCE_LOG = new ThinLogBlock(THIN_LOG_SETTINGS);

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
    public static final VaseBlock LIGHT_GRAY_TERRACOTTA_VASE = new VaseBlock(TERRACOTTA_SETTINGS);
    public static final VaseBlock BLACK_TERRACOTTA_VASE = new VaseBlock(TERRACOTTA_SETTINGS);
    public static final VaseBlock SANDSTONE_VASE = new VaseBlock(TERRACOTTA_SETTINGS);

    //Transistion Blocks
    public static final Block CD_CS_LIGHT = new Block(STONE_SETTINGS);
    public static final Block CD_CS_HEAVY = new Block(STONE_SETTINGS);

    public static final Block SD_SB_HEAVY = new Block(STONE_SETTINGS);
    public static final Block SD_SB_LIGHT = new Block(STONE_SETTINGS);

    public static final Block SD_CSB_LIGHT = new Block(STONE_SETTINGS);

    public static final Block COARSE_DIRT_DARK_STONE_BRICK_TILES_HEAVY = new Block(STONE_SETTINGS);
    public static final Block COARSE_DIRT_DARK_STONE_BRICK_TILES_LIGHT = new Block(STONE_SETTINGS);
    public static final Block COARSE_DIRT_STONE_BRICK_TILES_HEAVY = new Block(STONE_SETTINGS);
    public static final Block COARSE_DIRT_STONE_BRICK_TILES_LIGHT = new Block(STONE_SETTINGS);
    public static final Block DIRT_DARK_STONE_BRICK_TILES_HEAVY = new Block(STONE_SETTINGS);
    public static final Block DIRT_DARK_STONE_BRICK_TILES_LIGHT = new Block(STONE_SETTINGS);
    public static final Block DIRT_STONE_BRICK_TILES_HEAVY = new Block(STONE_SETTINGS);
    public static final Block DIRT_STONE_BRICK_TILES_LIGHT = new Block(STONE_SETTINGS);
    public static final Block PACKED_DIRT_DARK_STONE_BRICK_TILES_HEAVY = new Block(STONE_SETTINGS);
    public static final Block PACKED_DIRT_DARK_STONE_BRICK_TILES_LIGHT = new Block(STONE_SETTINGS);
    public static final Block PACKED_DIRT_STONE_BRICK_TILES_HEAVY = new Block(STONE_SETTINGS);
    public static final Block PACKED_DIRT_STONE_BRICK_TILES_LIGHT = new Block(STONE_SETTINGS);
    public static final Block COARSE_DIRT_DARK_STONE_BRICKS_HEAVY = new Block(STONE_SETTINGS);
    public static final Block COARSE_DIRT_DARK_STONE_BRICKS_LIGHT = new Block(STONE_SETTINGS);
    public static final Block COARSE_DIRT_STONE_BRICKS_HEAVY = new Block(STONE_SETTINGS);
    public static final Block COARSE_DIRT_STONE_BRICKS_LIGHT = new Block(STONE_SETTINGS);
    public static final Block DIRT_DARK_STONE_BRICKS_HEAVY = new Block(STONE_SETTINGS);
    public static final Block DIRT_DARK_STONE_BRICKS_LIGHT = new Block(STONE_SETTINGS);
    public static final Block DIRT_STONE_BRICKS_HEAVY = new Block(STONE_SETTINGS);
    public static final Block DIRT_STONE_BRICKS_LIGHT = new Block(STONE_SETTINGS);
    public static final Block PACKED_DIRT_DARK_STONE_BRICKS_HEAVY = new Block(STONE_SETTINGS);
    public static final Block PACKED_DIRT_DARK_STONE_BRICKS_LIGHT = new Block(STONE_SETTINGS);
    public static final Block PACKED_DIRT_STONE_BRICKS_HEAVY = new Block(STONE_SETTINGS);
    public static final Block PACKED_DIRT_STONE_BRICKS_LIGHT = new Block(STONE_SETTINGS);

    public static final Block COARSE_DIRT_DARK_STONE_HEAVY = new Block(STONE_SETTINGS);
    public static final Block COARSE_DIRT_DARK_STONE_LIGHT = new Block(STONE_SETTINGS);
    public static final Block COARSE_DIRT_STONE_HEAVY = new Block(STONE_SETTINGS);
    public static final Block COARSE_DIRT_STONE_LIGHT = new Block(STONE_SETTINGS);
    public static final Block DIRT_DARK_STONE_HEAVY = new Block(STONE_SETTINGS);
    public static final Block DIRT_DARK_STONE_LIGHT = new Block(STONE_SETTINGS);
    public static final Block DIRT_STONE_HEAVY = new Block(STONE_SETTINGS);
    public static final Block DIRT_STONE_LIGHT = new Block(STONE_SETTINGS);
    public static final Block PACKED_DIRT_DARK_STONE_HEAVY = new Block(STONE_SETTINGS);
    public static final Block PACKED_DIRT_DARK_STONE_LIGHT = new Block(STONE_SETTINGS);
    public static final Block PACKED_DIRT_STONE_HEAVY = new Block(STONE_SETTINGS);
    public static final Block PACKED_DIRT_STONE_LIGHT = new Block(STONE_SETTINGS);

    //Dead Blocks
    public static final DeadGrassBlock DEAD_GRASS_BLOCK = new DeadGrassBlock(FabricBlockSettings.of(Material.SOIL).sounds(BlockSoundGroup.GRASS).breakByHand(true).hardness(0.6f).resistance(0.6f).ticksRandomly());
    
    public static final PillarBlock BURNT_ACACIA_LOG = new PillarBlock(LOG_SETTINGS);

    public static final Block BURNT_PLANKS = new Block(FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).breakByTool(FabricToolTags.AXES).breakByHand(true).hardness(2f).resistance(3f));
    public static final SlabBlock BURNT_PLANKS_SLAB = new SlabBlock(FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).breakByTool(FabricToolTags.AXES).breakByHand(true).hardness(2f).resistance(3f));
    public static final StairsBlockExtend BURNT_PLANKS_STAIRS = new StairsBlockExtend(BURNT_PLANKS.getDefaultState(), FabricBlockSettings.of(Material.WOOD).sounds(BlockSoundGroup.WOOD).breakByTool(FabricToolTags.AXES).breakByHand(true).hardness(2f).resistance(3f));

    //Layer Block
    public static final LayerBlockFalling SAND_LAYER = new LayerBlockFalling(FabricBlockSettings.of(Material.AGGREGATE).sounds(BlockSoundGroup.SAND).hardness(0.5f).resistance(0.5f));
    public static final LayerBlockFalling RED_SAND_LAYER = new LayerBlockFalling(FabricBlockSettings.of(Material.AGGREGATE).sounds(BlockSoundGroup.SAND).hardness(0.5f).resistance(0.5f));
    public static final LayerBlockFalling GRAVEL_LAYER = new LayerBlockFalling(FabricBlockSettings.of(Material.AGGREGATE).sounds(BlockSoundGroup.GRAVEL).hardness(0.5f).resistance(0.5f));
    public static final LayerBlock DIRT_LAYER = new LayerBlock(DIRT_SETTINGS);
    public static final LayerBlock COARSE_DIRT_LAYER = new LayerBlock(DIRT_SETTINGS);
    public static final LayerBlock PACKED_DIRT_LAYER = new LayerBlock(DIRT_SETTINGS);


    //LayerBlockPowder
    public static final LayerBlockPowder BLACK_CONCRETE_POWDER_LAYER = new LayerBlockPowder(Blocks.BLACK_CONCRETE,CONCRETE_POWDER_SETTINGS.materialColor(MaterialColor.BLACK));
    public static final LayerBlockPowder RED_CONCRETE_POWDER_LAYER = new LayerBlockPowder(Blocks.RED_CONCRETE,CONCRETE_POWDER_SETTINGS.materialColor(MaterialColor.RED));
    public static final LayerBlockPowder GREEN_CONCRETE_POWDER_LAYER = new LayerBlockPowder(Blocks.GREEN_CONCRETE,CONCRETE_POWDER_SETTINGS.materialColor(MaterialColor.GREEN));
    public static final LayerBlockPowder BROWN_CONCRETE_POWDER_LAYER = new LayerBlockPowder(Blocks.BROWN_CONCRETE,CONCRETE_POWDER_SETTINGS.materialColor(MaterialColor.BROWN));
    public static final LayerBlockPowder BLUE_CONCRETE_POWDER_LAYER = new LayerBlockPowder(Blocks.BLUE_CONCRETE,CONCRETE_POWDER_SETTINGS.materialColor(MaterialColor.BLUE));
    public static final LayerBlockPowder PURPLE_CONCRETE_POWDER_LAYER = new LayerBlockPowder(Blocks.PURPLE_CONCRETE,CONCRETE_POWDER_SETTINGS.materialColor(MaterialColor.PURPLE));
    public static final LayerBlockPowder LIGHT_GRAY_CONCRETE_POWDER_LAYER = new LayerBlockPowder(Blocks.LIGHT_GRAY_CONCRETE,CONCRETE_POWDER_SETTINGS.materialColor(MaterialColor.LIGHT_GRAY));
    public static final LayerBlockPowder CYAN_CONCRETE_POWDER_LAYER = new LayerBlockPowder(Blocks.CYAN_CONCRETE,CONCRETE_POWDER_SETTINGS.materialColor(MaterialColor.CYAN));
    public static final LayerBlockPowder GRAY_CONCRETE_POWDER_LAYER = new LayerBlockPowder(Blocks.GRAY_CONCRETE,CONCRETE_POWDER_SETTINGS.materialColor(MaterialColor.GRAY));
    public static final LayerBlockPowder PINK_CONCRETE_POWDER_LAYER = new LayerBlockPowder(Blocks.PINK_CONCRETE,CONCRETE_POWDER_SETTINGS.materialColor(MaterialColor.PINK));
    public static final LayerBlockPowder LIME_CONCRETE_POWDER_LAYER = new LayerBlockPowder(Blocks.LIME_CONCRETE,CONCRETE_POWDER_SETTINGS.materialColor(MaterialColor.LIME));
    public static final LayerBlockPowder YELLOW_CONCRETE_POWDER_LAYER = new LayerBlockPowder(Blocks.YELLOW_CONCRETE,CONCRETE_POWDER_SETTINGS.materialColor(MaterialColor.YELLOW));
    public static final LayerBlockPowder LIGHT_BLUE_CONCRETE_POWDER_LAYER = new LayerBlockPowder(Blocks.LIGHT_BLUE_CONCRETE,CONCRETE_POWDER_SETTINGS.materialColor(MaterialColor.LIGHT_BLUE));
    public static final LayerBlockPowder MAGENTA_CONCRETE_POWDER_LAYER = new LayerBlockPowder(Blocks.MAGENTA_CONCRETE,CONCRETE_POWDER_SETTINGS.materialColor(MaterialColor.MAGENTA));
    public static final LayerBlockPowder ORANGE_CONCRETE_POWDER_LAYER = new LayerBlockPowder(Blocks.ORANGE_CONCRETE,CONCRETE_POWDER_SETTINGS.materialColor(MaterialColor.ORANGE));
    public static final LayerBlockPowder WHITE_CONCRETE_POWDER_LAYER = new LayerBlockPowder(Blocks.WHITE_CONCRETE,CONCRETE_POWDER_SETTINGS.materialColor(MaterialColor.WHITE));

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


    public static final SideSlab TERRACOTTA_BRICKS_VERTICAL_SLAB = new SideSlab(STONE_SETTINGS);
    public static final SideSlab BLACK_TERRACOTTA_BRICKS_VERTICAL_SLAB = new SideSlab(STONE_SETTINGS.materialColor(MaterialColor.BLACK));
    public static final SideSlab RED_TERRACOTTA_BRICKS_VERTICAL_SLAB = new SideSlab(STONE_SETTINGS.materialColor(MaterialColor.RED));
    public static final SideSlab GREEN_TERRACOTTA_BRICKS_VERTICAL_SLAB = new SideSlab(STONE_SETTINGS.materialColor(MaterialColor.GREEN));
    public static final SideSlab BROWN_TERRACOTTA_BRICKS_VERTICAL_SLAB = new SideSlab(STONE_SETTINGS.materialColor(MaterialColor.BROWN));
    public static final SideSlab BLUE_TERRACOTTA_BRICKS_VERTICAL_SLAB = new SideSlab(STONE_SETTINGS.materialColor(MaterialColor.BLUE));
    public static final SideSlab PURPLE_TERRACOTTA_BRICKS_VERTICAL_SLAB = new SideSlab(STONE_SETTINGS.materialColor(MaterialColor.PURPLE));
    public static final SideSlab LIGHT_GRAY_TERRACOTTA_BRICKS_VERTICAL_SLAB = new SideSlab(STONE_SETTINGS.materialColor(MaterialColor.LIGHT_GRAY));
    public static final SideSlab CYAN_TERRACOTTA_BRICKS_VERTICAL_SLAB = new SideSlab(STONE_SETTINGS.materialColor(MaterialColor.CYAN));
    public static final SideSlab GRAY_TERRACOTTA_BRICKS_VERTICAL_SLAB = new SideSlab(STONE_SETTINGS.materialColor(MaterialColor.GRAY));
    public static final SideSlab PINK_TERRACOTTA_BRICKS_VERTICAL_SLAB = new SideSlab(STONE_SETTINGS.materialColor(MaterialColor.PINK));
    public static final SideSlab LIME_TERRACOTTA_BRICKS_VERTICAL_SLAB = new SideSlab(STONE_SETTINGS.materialColor(MaterialColor.LIME));
    public static final SideSlab YELLOW_TERRACOTTA_BRICKS_VERTICAL_SLAB = new SideSlab(STONE_SETTINGS.materialColor(MaterialColor.YELLOW));
    public static final SideSlab LIGHT_BLUE_TERRACOTTA_BRICKS_VERTICAL_SLAB = new SideSlab(STONE_SETTINGS.materialColor(MaterialColor.LIGHT_BLUE));
    public static final SideSlab MAGENTA_TERRACOTTA_BRICKS_VERTICAL_SLAB = new SideSlab(STONE_SETTINGS.materialColor(MaterialColor.MAGENTA));
    public static final SideSlab ORANGE_TERRACOTTA_BRICKS_VERTICAL_SLAB = new SideSlab(STONE_SETTINGS.materialColor(MaterialColor.ORANGE));
    public static final SideSlab WHITE_TERRACOTTA_BRICKS_VERTICAL_SLAB = new SideSlab(STONE_SETTINGS.materialColor(MaterialColor.WHITE));


    //Other Stone Variants
    public static final Block STONE_BRICK_SQUARE = new Block(FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES).breakByHand(false).hardness(1.5f).resistance(6f));
    public static final SlabBlock STONE_BRICK_SQUARE_SLAB = new SlabBlock(FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES).breakByHand(false).hardness(1.5f).resistance(6f));
    public static final SideSlab STONE_BRICK_TILES_VERTICAL_SLAB = new SideSlab(STONE_SETTINGS);
    public static final StairsBlockExtend STONE_BRICK_SQUARE_STAIRS = new StairsBlockExtend(STONE_BRICK_SQUARE.getDefaultState(), FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.PICKAXES).breakByHand(false).hardness(1.5f).resistance(6f));
    public static final Block LARGE_COBBLESTONE = new Block(STONE_SETTINGS);

    //Minecraft Stairs and Slabs
    public static final SlabBlock DIRT_SLAB = new SlabBlock(FabricBlockSettings.of(Material.SOIL).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.SHOVELS).breakByHand(true).hardness(0.5f).resistance(0.5f));
    public static final SlabBlock COARSE_DIRT_SLAB = new SlabBlock(FabricBlockSettings.of(Material.SOIL).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.SHOVELS).breakByHand(true).hardness(0.5f).resistance(0.5f));  
    public static final SlabBlock GRASS_SLAB = new SlabBlock(FabricBlockSettings.of(Material.SOIL).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.SHOVELS).breakByHand(true).hardness(0.5f).resistance(0.5f));  
    public static final SlabBlock SNOW_GRASS_SLAB = new SlabBlock(FabricBlockSettings.of(Material.SOIL).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.SHOVELS).breakByHand(true).hardness(0.5f).resistance(0.5f));  
    //public static final SlabBlock CRACKED_STONE_BRICK_SLAB = new SlabBlock(FabricBlockSettings.of(Material.SOIL).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.SHOVELS).breakByHand(true).hardness(0.5f).resistance(0.5f));  
    //public static final SlabBlock CRACKED_NETHER_BRICK_SLAB = new SlabBlock(FabricBlockSettings.of(Material.SOIL).sounds(BlockSoundGroup.STONE).breakByTool(FabricToolTags.SHOVELS).breakByHand(true).hardness(0.5f).resistance(0.5f));  


    public static final SlabBlock BLACK_CONCRETE_SLAB = new SlabBlock(CONCRETE_SETTINGS.materialColor(MaterialColor.BLACK));
    public static final SlabBlock RED_CONCRETE_SLAB = new SlabBlock(CONCRETE_SETTINGS.materialColor(MaterialColor.RED));
    public static final SlabBlock GREEN_CONCRETE_SLAB = new SlabBlock(CONCRETE_SETTINGS.materialColor(MaterialColor.GREEN));
    public static final SlabBlock BROWN_CONCRETE_SLAB = new SlabBlock(CONCRETE_SETTINGS.materialColor(MaterialColor.BROWN));
    public static final SlabBlock BLUE_CONCRETE_SLAB = new SlabBlock(CONCRETE_SETTINGS.materialColor(MaterialColor.BLUE));
    public static final SlabBlock PURPLE_CONCRETE_SLAB = new SlabBlock(CONCRETE_SETTINGS.materialColor(MaterialColor.PURPLE));
    public static final SlabBlock LIGHT_GRAY_CONCRETE_SLAB = new SlabBlock(CONCRETE_SETTINGS.materialColor(MaterialColor.LIGHT_GRAY));
    public static final SlabBlock GRAY_CONCRETE_SLAB = new SlabBlock(CONCRETE_SETTINGS.materialColor(MaterialColor.GRAY));
    public static final SlabBlock PINK_CONCRETE_SLAB = new SlabBlock(CONCRETE_SETTINGS.materialColor(MaterialColor.PINK));
    public static final SlabBlock LIME_CONCRETE_SLAB = new SlabBlock(CONCRETE_SETTINGS.materialColor(MaterialColor.LIME));
    public static final SlabBlock YELLOW_CONCRETE_SLAB = new SlabBlock(CONCRETE_SETTINGS.materialColor(MaterialColor.YELLOW));
    public static final SlabBlock LIGHT_BLUE_CONCRETE_SLAB = new SlabBlock(CONCRETE_SETTINGS.materialColor(MaterialColor.LIGHT_BLUE));
    public static final SlabBlock MAGENTA_CONCRETE_SLAB = new SlabBlock(CONCRETE_SETTINGS.materialColor(MaterialColor.MAGENTA));
    public static final SlabBlock ORANGE_CONCRETE_SLAB = new SlabBlock(CONCRETE_SETTINGS.materialColor(MaterialColor.ORANGE));
    public static final SlabBlock WHITE_CONCRETE_SLAB = new SlabBlock(CONCRETE_SETTINGS.materialColor(MaterialColor.WHITE));
    public static final SlabBlock CYAN_CONCRETE_SLAB = new SlabBlock(CONCRETE_SETTINGS.materialColor(MaterialColor.CYAN));

    public static final SideSlab BLACK_CONCRETE_VERTICAL_SLAB = new SideSlab(CONCRETE_SETTINGS.materialColor(MaterialColor.BLACK));
    public static final SideSlab RED_CONCRETE_VERTICAL_SLAB = new SideSlab(CONCRETE_SETTINGS.materialColor(MaterialColor.RED));
    public static final SideSlab GREEN_CONCRETE_VERTICAL_SLAB = new SideSlab(CONCRETE_SETTINGS.materialColor(MaterialColor.GREEN));
    public static final SideSlab BROWN_CONCRETE_VERTICAL_SLAB = new SideSlab(CONCRETE_SETTINGS.materialColor(MaterialColor.BROWN));
    public static final SideSlab BLUE_CONCRETE_VERTICAL_SLAB = new SideSlab(CONCRETE_SETTINGS.materialColor(MaterialColor.BLUE));
    public static final SideSlab PURPLE_CONCRETE_VERTICAL_SLAB = new SideSlab(CONCRETE_SETTINGS.materialColor(MaterialColor.PURPLE));
    public static final SideSlab LIGHT_GRAY_CONCRETE_VERTICAL_SLAB = new SideSlab(CONCRETE_SETTINGS.materialColor(MaterialColor.LIGHT_GRAY));
    public static final SideSlab GRAY_CONCRETE_VERTICAL_SLAB = new SideSlab(CONCRETE_SETTINGS.materialColor(MaterialColor.GRAY));
    public static final SideSlab PINK_CONCRETE_VERTICAL_SLAB = new SideSlab(CONCRETE_SETTINGS.materialColor(MaterialColor.PINK));
    public static final SideSlab LIME_CONCRETE_VERTICAL_SLAB = new SideSlab(CONCRETE_SETTINGS.materialColor(MaterialColor.LIME));
    public static final SideSlab YELLOW_CONCRETE_VERTICAL_SLAB = new SideSlab(CONCRETE_SETTINGS.materialColor(MaterialColor.YELLOW));
    public static final SideSlab LIGHT_BLUE_CONCRETE_VERTICAL_SLAB = new SideSlab(CONCRETE_SETTINGS.materialColor(MaterialColor.LIGHT_BLUE));
    public static final SideSlab MAGENTA_CONCRETE_VERTICAL_SLAB = new SideSlab(CONCRETE_SETTINGS.materialColor(MaterialColor.MAGENTA));
    public static final SideSlab ORANGE_CONCRETE_VERTICAL_SLAB = new SideSlab(CONCRETE_SETTINGS.materialColor(MaterialColor.ORANGE));
    public static final SideSlab WHITE_CONCRETE_VERTICAL_SLAB = new SideSlab(CONCRETE_SETTINGS.materialColor(MaterialColor.WHITE));
    public static final SideSlab CYAN_CONCRETE_VERTICAL_SLAB = new SideSlab(CONCRETE_SETTINGS.materialColor(MaterialColor.CYAN));

    public static final StairsBlockExtend BLACK_CONCRETE_STAIRS = new StairsBlockExtend(Blocks.BLACK_CONCRETE.getDefaultState(), CONCRETE_SETTINGS.materialColor(MaterialColor.BLACK));
    public static final StairsBlockExtend RED_CONCRETE_STAIRS = new StairsBlockExtend(Blocks.RED_CONCRETE.getDefaultState(), CONCRETE_SETTINGS.materialColor(MaterialColor.RED));
    public static final StairsBlockExtend GREEN_CONCRETE_STAIRS = new StairsBlockExtend(Blocks.GREEN_CONCRETE.getDefaultState(), CONCRETE_SETTINGS.materialColor(MaterialColor.GREEN));
    public static final StairsBlockExtend BROWN_CONCRETE_STAIRS = new StairsBlockExtend(Blocks.BROWN_CONCRETE.getDefaultState(), CONCRETE_SETTINGS.materialColor(MaterialColor.BROWN));
    public static final StairsBlockExtend BLUE_CONCRETE_STAIRS = new StairsBlockExtend(Blocks.BLUE_CONCRETE.getDefaultState(), CONCRETE_SETTINGS.materialColor(MaterialColor.BLUE));
    public static final StairsBlockExtend PURPLE_CONCRETE_STAIRS = new StairsBlockExtend(Blocks.PURPLE_CONCRETE.getDefaultState(), CONCRETE_SETTINGS.materialColor(MaterialColor.PURPLE));
    public static final StairsBlockExtend LIGHT_GRAY_CONCRETE_STAIRS = new StairsBlockExtend(Blocks.LIGHT_GRAY_CONCRETE.getDefaultState(), CONCRETE_SETTINGS.materialColor(MaterialColor.LIGHT_GRAY));
    public static final StairsBlockExtend GRAY_CONCRETE_STAIRS = new StairsBlockExtend(Blocks.GRAY_CONCRETE.getDefaultState(), CONCRETE_SETTINGS.materialColor(MaterialColor.GRAY));
    public static final StairsBlockExtend PINK_CONCRETE_STAIRS = new StairsBlockExtend(Blocks.PINK_CONCRETE.getDefaultState(), CONCRETE_SETTINGS.materialColor(MaterialColor.PINK));
    public static final StairsBlockExtend LIME_CONCRETE_STAIRS = new StairsBlockExtend(Blocks.LIME_CONCRETE.getDefaultState(), CONCRETE_SETTINGS.materialColor(MaterialColor.LIME));
    public static final StairsBlockExtend YELLOW_CONCRETE_STAIRS = new StairsBlockExtend(Blocks.YELLOW_CONCRETE.getDefaultState(), CONCRETE_SETTINGS.materialColor(MaterialColor.YELLOW));
    public static final StairsBlockExtend LIGHT_BLUE_CONCRETE_STAIRS = new StairsBlockExtend(Blocks.LIGHT_BLUE_CONCRETE.getDefaultState(), CONCRETE_SETTINGS.materialColor(MaterialColor.LIGHT_BLUE));
    public static final StairsBlockExtend MAGENTA_CONCRETE_STAIRS = new StairsBlockExtend(Blocks.MAGENTA_CONCRETE.getDefaultState(), CONCRETE_SETTINGS.materialColor(MaterialColor.MAGENTA));
    public static final StairsBlockExtend ORANGE_CONCRETE_STAIRS = new StairsBlockExtend(Blocks.ORANGE_CONCRETE.getDefaultState(), CONCRETE_SETTINGS.materialColor(MaterialColor.ORANGE));
    public static final StairsBlockExtend WHITE_CONCRETE_STAIRS = new StairsBlockExtend(Blocks.WHITE_CONCRETE.getDefaultState(), CONCRETE_SETTINGS.materialColor(MaterialColor.WHITE));
    public static final StairsBlockExtend CYAN_CONCRETE_STAIRS = new StairsBlockExtend(Blocks.CYAN_CONCRETE.getDefaultState(), CONCRETE_SETTINGS.materialColor(MaterialColor.CYAN));

    //Slabs
    public static final SlabBlock POLISHED_ANDESITE_BRICK_TILES_SLAB = new SlabBlock(STONE_SETTINGS);
    public static final SlabBlock POLISHED_DIORITE_BRICK_TILES_SLAB = new SlabBlock(STONE_SETTINGS);
    public static final SlabBlock POLISHED_GRANITE_BRICK_TILES_SLAB = new SlabBlock(STONE_SETTINGS);
    public static final SlabBlock POLISHED_BLACKSTONE_BRICK_TILES_SLAB = new SlabBlock(STONE_SETTINGS);
    public static final SlabBlock POLISHED_ANDESITE_BRICKS_SLAB = new SlabBlock(STONE_SETTINGS);
    public static final SlabBlock POLISHED_DIORITE_BRICKS_SLAB = new SlabBlock(STONE_SETTINGS);
    public static final SlabBlock POLISHED_GRANITE_BRICKS_SLAB = new SlabBlock(STONE_SETTINGS);


    public static final StairsBlockExtend POLISHED_ANDESITE_BRICK_TILES_STAIRS = new StairsBlockExtend(POLISHED_ANDESITE_BRICK_TILES.getDefaultState(), STONE_SETTINGS);
    public static final StairsBlockExtend POLISHED_DIORITE_BRICK_TILES_STAIRS = new StairsBlockExtend(POLISHED_DIORITE_BRICK_TILES.getDefaultState(), STONE_SETTINGS);
    public static final StairsBlockExtend POLISHED_GRANITE_BRICK_TILES_STAIRS = new StairsBlockExtend(POLISHED_GRANITE_BRICK_TILES.getDefaultState(), STONE_SETTINGS);
    public static final StairsBlockExtend POLISHED_BLACKSTONE_BRICK_TILES_STAIRS = new StairsBlockExtend(POLISHED_BLACKSTONE_BRICK_TILES.getDefaultState(), STONE_SETTINGS);
    public static final StairsBlockExtend POLISHED_ANDESITE_BRICKS_STAIRS = new StairsBlockExtend(POLISHED_ANDESITE_BRICKS.getDefaultState(), STONE_SETTINGS);
    public static final StairsBlockExtend POLISHED_DIORITE_BRICKS_STAIRS = new StairsBlockExtend(POLISHED_DIORITE_BRICKS.getDefaultState(), STONE_SETTINGS);
    public static final StairsBlockExtend POLISHED_GRANITE_BRICKS_STAIRS = new StairsBlockExtend(POLISHED_GRANITE_BRICKS.getDefaultState(), STONE_SETTINGS);

    //Oxidized Blocks
    public static final OxidizedBlock IRON_BLOCK = new OxidizedBlock(0,true,IRON_SETTINGS.ticksRandomly());
    public static final OxidizedBlock EXPOSED_IRON = new OxidizedBlock(1,false,IRON_SETTINGS.ticksRandomly());
    public static final OxidizedBlock DEGRADED_IRON = new OxidizedBlock(2,false,IRON_SETTINGS.ticksRandomly());
    public static final OxidizedBlock WEATHERED_IRON = new OxidizedBlock(3,false,IRON_SETTINGS.ticksRandomly());
    public static final OxidizedBlock RUSTED_IRON = new OxidizedBlock(4,false,IRON_SETTINGS.ticksRandomly());

    public static final Block WAXED_EXPOSED_IRON = new Block(IRON_SETTINGS);
    public static final Block WAXED_DEGRADED_IRON = new Block(IRON_SETTINGS);
    public static final Block WAXED_WEATHERED_IRON = new Block(IRON_SETTINGS);
    public static final Block WAXED_RUSTED_IRON = new Block(IRON_SETTINGS);

    public static final HardenedIron HARDENED_IRON = new HardenedIron(IRON_SETTINGS.ticksRandomly());

    public static final Block RUBY_BLOCK = new Block(JEWEL_BLOCK_SETTINGS);

    public static final HeatedBlock HEATED_IRON = new HeatedBlock(IRON_SETTINGS.ticksRandomly().luminance(createLightLevelFromBlockState(15)));

    //Framing
    public static final FramingBlock STRIPPED_OAK_FRAMING = new FramingBlock(PLANKS_SETTINGS);

    //Removed Blocks
    public static final Block WAX_BLOCK = new Block(FabricBlockSettings.of(Material.ORGANIC_PRODUCT).requiresTool().strength(0.6f, 0.6f));

    //Ore
    public static final OreBlock RUBY_ORE = new OreBlock(FabricBlockSettings.of(Material.STONE).requiresTool().strength(3.0f, 3.0f));


    public static final Block PACKED_DIRT = new Block(DIRT_SETTINGS);

    //
    public static final SideStairs COBBLESTONE_SIDE_STAIRS = new SideStairs(STONE_SETTINGS);

    //PotionInfusedBlocks
    public static final PotionInfusedBlock PIB = new PotionInfusedBlock(StatusEffects.JUMP_BOOST,STONE_SETTINGS.luminance(createLightLevelFromBlockState(12)));
    
    //Block Entity
    public static BlockEntityType<VaseBlockEntity> VASE_BLOCK_ENTITY;
    //public static BlockEntityType<VaseBlockEntity> BLACK_TERRACOTTA_VASE_BLOCK_ENTITY;/* , BLACK_TERRACOTTA_VASE_BLOCK_ENTITY */

    public static final PillarBlock BURNT_BIRCH_LOG = new PillarBlock(LOG_SETTINGS);
    public static final PillarBlock BURNT_DARK_OAK_LOG = new PillarBlock(LOG_SETTINGS);
    public static final PillarBlock BURNT_OAK_LOG = new PillarBlock(LOG_SETTINGS);
    public static final PillarBlock BURNT_JUNGLE_LOG = new PillarBlock(LOG_SETTINGS);
    public static final PillarBlock BURNT_SPRUCE_LOG = new PillarBlock(LOG_SETTINGS);

//##blockinit##//

    public static FlowableFluid STILL_MUD;
    public static FlowableFluid FLOWING_MUD;
    public static MudBlock MUD;

    public static final void blocks() {
        String ModName = moreblocksmod.ModName;

        VASE_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, ModName + ":vase", BlockEntityType.Builder.create(VaseBlockEntity::new, new Block[] {TERRACOTTA_VASE, BLACK_TERRACOTTA_VASE, LIGHT_GRAY_TERRACOTTA_VASE, SANDSTONE_VASE}).build(null));

        STILL_MUD = Registry.register(Registry.FLUID, new Identifier(ModName, "mud"), new MudFluid.Still());
        FLOWING_MUD = Registry.register(Registry.FLUID, new Identifier(ModName, "flowing_mud"), new MudFluid.Flowing());
        MUD = Registry.register(Registry.BLOCK, new Identifier(ModName, "mud"), new MudBlock(STILL_MUD, FabricBlockSettings.copy(Blocks.WATER)){});

        registerBlock("leather_block", LEATHER_BLOCK);
        registerBlock("rice_straw_bale", RICE_STRAW_BALE);
        registerBlock("rope", ROPE, MBMItems.TOOLS);
        registerBlockNoItem("rope_mid", ROPEMID);
        registerBlock("thin_acacia_log", THIN_ACACIA_LOG);
        registerBlock("thin_stripped_acacia_log", THIN_STRIPPED_ACACIA_LOG);
        registerBlock("thin_birch_log", THIN_BIRCH_LOG);
        registerBlock("thin_stripped_birch_log", THIN_STRIPPED_BIRCH_LOG);
        registerBlock("thin_dark_oak_log", THIN_DARK_OAK_LOG);
        registerBlock("thin_stripped_dark_oak_log", THIN_STRIPPED_DARK_OAK_LOG);
        registerBlock("thin_jungle_log", THIN_JUNGLE_LOG);
        registerBlock("thin_stripped_jungle_log", THIN_STRIPPED_JUNGLE_LOG);
        registerBlock("thin_oak_log", THIN_OAK_LOG);
        registerBlock("thin_stripped_oak_log", THIN_STRIPPED_OAK_LOG);
        registerBlock("thin_spruce_log", THIN_SPRUCE_LOG);
        registerBlock("thin_stripped_spruce_log", THIN_STRIPPED_SPRUCE_LOG);
        registerBlock("chopped_acacia_log", CHOPPED_ACACIA_LOG);
        registerBlock("chopped_stripped_acacia_log", CHOPPED_STRIPPED_ACACIA_LOG);
        registerBlock("chopped_birch_log", CHOPPED_BIRCH_LOG);
        registerBlock("chopped_stripped_birch_log", CHOPPED_STRIPPED_BIRCH_LOG);
        registerBlock("chopped_dark_oak_log", CHOPPED_DARK_OAK_LOG);
        registerBlock("chopped_stripped_dark_oak_log", CHOPPED_STRIPPED_DARK_OAK_LOG);
        registerBlock("chopped_jungle_log", CHOPPED_JUNGLE_LOG);
        registerBlock("chopped_stripped_jungle_log", CHOPPED_STRIPPED_JUNGLE_LOG);
        registerBlock("chopped_oak_log", CHOPPED_OAK_LOG);
        registerBlock("chopped_stripped_oak_log", CHOPPED_STRIPPED_OAK_LOG);
        registerBlock("chopped_spruce_log", CHOPPED_SPRUCE_LOG);
        registerBlock("chopped_stripped_spruce_log", CHOPPED_STRIPPED_SPRUCE_LOG);
        //Dead Grass Block
        registerBlock("dead_grass_block", DEAD_GRASS_BLOCK);
        //Carved Melon
        registerBlock("carved_melon", CARVEDMELON);
        //Crop Block
        registerBlockNoItem("rice", RICE);
        registerBlockNoItem("rice_center", RICE_NO_OFF_SET);
        //Dark Stone
        registerBlock("dark_stone", DARK_STONE);
        registerBlock("dark_stone_slab", DARK_STONE_SLAB);
        registerBlock("dark_stone_stairs", DARK_STONE_STAIRS);
        registerBlock("dark_stone_bricks", DARK_STONE_BRICK);
        registerBlock("dark_stone_brick_slab", DARK_STONE_BRICK_SLAB);
        registerBlock("dark_stone_brick_stairs", DARK_STONE_BRICK_STAIRS);
        registerBlock("dark_stone_tiles", DARK_STONE_TILES);
        registerBlock("dark_stone_tiles_slab", DARK_STONE_TILES_SLAB);
        registerBlock("dark_stone_tiles_stairs", DARK_STONE_TILES_STAIRS);
        registerBlock("dark_cobblestone", DARK_COBBLESTONE);
        registerBlock("dark_cobblestone_slab", DARK_COBBLESTONE_SLAB);
        registerBlock("dark_cobblestone_stairs", DARK_COBBLESTONE_STAIRS);
        registerBlock("emblemed_dark_stone", EMBLEMED_DARK_STONE);
        registerBlock("dark_stone_vertical_slab", DARK_STONE_VERTICAL_SLAB);
        registerBlock("dark_stone_bricks_vertical_slab", DARK_STONE_BRICKS_VERTICAL_SLAB);
        registerBlock("dark_stone_tiles_vertical_slab", DARK_STONE_TILES_VERTICAL_SLAB);
        registerBlock("dark_cobblestone_vertical_slab", DARK_COBBLESTONE_VERTICAL_SLAB);
        //Packed Terracotta
        registerBlock("black_packed_terracotta", BLACK_PACKED_TERRACOTTA);
        registerBlock("blue_packed_terracotta", BLUE_PACKED_TERRACOTTA);
        registerBlock("light_blue_packed_terracotta", LIGHT_BLUE_PACKED_TERRACOTTA);
        registerBlock("brown_packed_terracotta", BROWN_PACKED_TERRACOTTA);
        registerBlock("cyan_packed_terracotta", CYAN_PACKED_TERRACOTTA);
        registerBlock("gray_packed_terracotta", GRAY_PACKED_TERRACOTTA);
        registerBlock("green_packed_terracotta", GREEN_PACKED_TERRACOTTA);
        registerBlock("lime_packed_terracotta", LIME_PACKED_TERRACOTTA);
        registerBlock("light_gray_packed_terracotta", LIGHT_GRAY_PACKED_TERRACOTTA);
        registerBlock("magenta_packed_terracotta", MAGENTA_PACKED_TERRACOTTA);
        registerBlock("orange_packed_terracotta", ORANGE_PACKED_TERRACOTTA);
        registerBlock("packed_terracotta", PACKED_TERRACOTTA);
        registerBlock("pink_packed_terracotta", PINK_PACKED_TERRACOTTA);
        registerBlock("purple_packed_terracotta", PURPLE_PACKED_TERRACOTTA);
        registerBlock("red_packed_terracotta", RED_PACKED_TERRACOTTA);
        registerBlock("yellow_packed_terracotta", YELLOW_PACKED_TERRACOTTA);
        registerBlock("white_packed_terracotta", WHITE_PACKED_TERRACOTTA);
        registerBlock("yellow_terracotta_bricks", YELLOW_TERRACOTTA_BRICKS);
        registerBlock("yellow_terracotta_bricks_slab", YELLOW_TERRACOTTA_BRICKS_SLAB);
        registerBlock("yellow_terracotta_bricks_stairs", YELLOW_TERRACOTTA_BRICKS_STAIRS);
        registerBlock("orange_terracotta_bricks", ORANGE_TERRACOTTA_BRICKS);
        registerBlock("orange_terracotta_bricks_slab", ORANGE_TERRACOTTA_BRICKS_SLAB);
        registerBlock("orange_terracotta_bricks_stairs", ORANGE_TERRACOTTA_BRICKS_STAIRS);
        registerBlock("magenta_terracotta_bricks", MAGENTA_TERRACOTTA_BRICKS);
        registerBlock("magenta_terracotta_bricks_slab", MAGENTA_TERRACOTTA_BRICKS_SLAB);
        registerBlock("magenta_terracotta_bricks_stairs", MAGENTA_TERRACOTTA_BRICKS_STAIRS);
        registerBlock("lime_terracotta_bricks", LIME_TERRACOTTA_BRICKS);
        registerBlock("lime_terracotta_bricks_slab", LIME_TERRACOTTA_BRICKS_SLAB);
        registerBlock("lime_terracotta_bricks_stairs", LIME_TERRACOTTA_BRICKS_STAIRS);
        registerBlock("packed_mud", PACKEDMUD);
        registerBlock("petrified_wood", PETRIFIED_WOOD);
        //Side Slab
        registerBlock("acacia_planks_horizontal_slab", ACACIA_PLANKS_HORIZONTAL_SLAB);
        registerBlock("andesite_horizontal_slab", ANDESITE_HORIZONTAL_SLAB);
        registerBlock("birch_planks_horizontal_slab", BIRCH_HORIZONTAL_SLAB);
        registerBlock("black_terracotta_horizontal_slab", BLACK_TERRACOTTA_HORIZONTAL_SLAB);
        registerBlock("blackstone_horizontal_slab", BLACKSTONE_HORIZONTAL_SLAB);
        registerBlock("blackstone_bricks_horizontal_slab", BLACKSTONE_BRICKS_HORIZONTAL_SLAB);
        registerBlock("blue_terracotta_horizontal_slab", BLUE_TERRACOTTA_HORIZONTAL_SLAB);
        registerBlock("bricks_horizontal_slab", BRICKS_HORIZONTAL_SLAB);
        registerBlock("brown_terracotta_horizontal_slab", BROWN_TERRACOTTA_HORIZONTAL_SLAB);
        // Registry.register(Registry.BLOCK, new Identifier(ModName, "chiseled_nether_bricks_horizontal_slab"), CHISELED_NETHER_BRICKS_HORIZONTAL_SLAB);
        //Registry.register(Registry.BLOCK, new Identifier(ModName, "chiseled_polished_blackstone_horizontal_slab"), CHISELED_POLISHED_BLACKSTONE_HORIZONTAL_SLAB);
        //Registry.register(Registry.BLOCK, new Identifier(ModName, "chiseled_red_sandstone_horizontal_slab"), CHISELED_RED_SANDSTONE_HORIZONTAL_SLAB);
        //Registry.register(Registry.BLOCK, new Identifier(ModName, "chiseled_sandstone_horizontal_slab"), CHISELED_SANDSTONE_HORIZONTAL_SLAB);
        //Registry.register(Registry.BLOCK, new Identifier(ModName, "chiseled_stone_bricks_horizontal_slab"), CHISELED_STONE_BRICKS_HORIZONTAL_SLAB);
        registerBlock("cobblestone_horizontal_slab", COBBLESTONE_HORIZONTAL_SLAB);
        registerBlock("cracked_nether_bricks_horizontal_slab", CRACKED_NETHER_BRICKS_HORIZONTAL_SLAB);
        registerBlock("cracked_stone_bricks_horizontal_slab", CRACKED_STONE_BRICKS_HORIZONTAL_SLAB);
        registerBlock("cracked_polished_blackstone_bricks_vertical_slab", CRACKED_POLISHED_BLACKSTONE_BRICKS_VERTICAL_SLAB);
        registerBlock("crimson_planks_horizontal_slab", CRIMSON_PLANKS_HORIZONTAL_SLAB);
        registerBlock("cut_red_sandstone_horizontal_slab", CUT_RED_SANDSTONE_HORIZONTAL_SLAB);
        registerBlock("cut_sandstone_horizontal_slab", CUT_SANDSTONE_HORIZONTAL_SLAB);
        registerBlock("cyan_terracotta_horizontal_slab", CYAN_TERRACOTTA_HORIZONTAL_SLAB);
        registerBlock("dark_oak_planks_horizontal_slab", DARK_OAK_PLANKS_HORIZONTAL_SLAB);
        registerBlock("dark_prismarine_horizontal_slab", DARK_PRISMARINE_HORIZONTAL_SLAB);
        registerBlock("diorite_horizontal_slab", DIORITE_HORIZONTAL_SLAB);
        registerBlock("end_stone_bricks_horizontal_slab", END_STONE_BRICKS_HORIZONTAL_SLAB);
        registerBlock("granite_horizontal_slab", GRANITE_HORIZONTAL_SLAB);
        registerBlock("gray_terracotta_horizontal_slab", GRAY_TERRACOTTA_HORIZONTAL_SLAB);
        registerBlock("green_terracotta_horizontal_slab", GREEN_TERRACOTTA_HORIZONTAL_SLAB);
        registerBlock("jungle_planks_horizontal_slab", JUNGLE_PLANKS_HORIZONTAL_SLAB);
        registerBlock("light_blue_terracotta_horizontal_slab", LIGHT_BLUE_TERRACOTTA_HORIZONTAL_SLAB);
        registerBlock("light_gray_terracotta_horizontal_slab", LIGHT_GRAY_TERRACOTTA_HORIZONTAL_SLAB);
        registerBlock("lime_terracotta_horizontal_slab", LIME_TERRACOTTA_HORIZONTAL_SLAB);
        registerBlock("magenta_terracotta_horizontal_slab", MAGENTA_TERRACOTTA_HORIZONTAL_SLAB);
        registerBlock("mossy_cobblestone_horizontal_slab", MOSSY_COBBLESTONE_HORIZONTAL_SLAB);
        registerBlock("mossy_stone_bricks_horizontal_slab", MOSSY_STONE_BRICKS_HORIZONTAL_SLAB);
        registerBlock("nether_bricks_horizontal_slab", NETHER_BRICKS_HORIZONTAL_SLAB);
        registerBlock("oak_planks_horizontal_slab", OAK_PLANKS_HORIZONTAL_SLAB);
        registerBlock("orange_terracotta_horizontal_slab", ORANGE_TERRACOTTA_HORIZONTAL_SLAB);
        registerBlock("pink_terracotta_horizontal_slab", PINK_TERRACOTTA_HORIZONTAL_SLAB);
        registerBlock("polished_blackstone_horizontal_slab", POLISHED_BLACKSTONE_HORIZONTAL_SLAB);
        registerBlock("polished_andesite_horizontal_slab", POLISHED_ANDESITE_HORIZONTAL_SLAB);
        registerBlock("polished_diorite_horizontal_slab", POLISHED_DIORITE_HORIZONTAL_SLAB);
        registerBlock("polished_granite_horizontal_slab", POLISHED_GRANITE_HORIZONTAL_SLAB);
        registerBlock("prismarine_bricks_horizontal_slab", PRISMARINE_BRICKS_HORIZONTAL_SLAB);
        registerBlock("prismarine_horizontal_slab", PRISMARINE_HORIZONTAL_SLAB);
        registerBlock("purple_terracotta_horizontal_slab", PURPLE_TERRACOTTA_HORIZONTAL_SLAB);
        registerBlock("purpur_block_horizontal_slab", PURPUR_BLOCK_HORIZONTAL_SLAB);
        registerBlock("red_nether_bricks_horizontal_slab", RED_NETHER_BRICKS_HORIZONTAL_SLAB);
        registerBlock("red_sandstone_horizontal_slab", RED_SANDSTONE_HORIZONTAL_SLAB);
        registerBlock("red_terracotta_horizontal_slab", RED_TERRACOTTA_HORIZONTAL_SLAB);
        registerBlock("sandstone_horizontal_slab", SANDSTONE_HORIZONTAL_SLAB);
        registerBlock("smooth_red_sandstone_horizontal_slab", SMOOTH_RED_SANDSTONE_HORIZONTAL_SLAB);
        registerBlock("smooth_sandstone_horizontal_slab", SMOOTH_SANDSTONE_HORIZONTAL_SLAB);
        registerBlock("smooth_stone_horizontal_slab", SMOOTH_STONE_HORIZONTAL_SLAB);
        registerBlock("spruce_planks_horizontal_slab", SPRUCE_PLANKS_HORIZONTAL_SLAB);
        registerBlock("stone_bricks_horizontal_slab", STONE_BRICKS_HORIZONTAL_SLAB);
        registerBlock("stone_horizontal_slab", STONE_HORIZONTAL_SLAB);
        registerBlock("terracotta_horizontal_slab", TERRACOTTA_HORIZONTAL_SLAB);
        registerBlock("warped_planks_horizontal_slab", WARPED_PLANKS_HORIZONTAL_SLAB);
        registerBlock("white_terracotta_horizontal_slab", WHITE_TERRACOTTA_HORIZONTAL_SLAB);
        registerBlock("yellow_terracotta_horizontal_slab", YELLOW_TERRACOTTA_HORIZONTAL_SLAB);
        registerBlock("polished_andesite_brick_tiles_vertical_slab", POLISHED_ANDESITE_BRICK_TILES_VERTICAL_SLAB);
        registerBlock("polished_diorite_brick_tiles_vertical_slab", POLISHED_DIORITE_BRICK_TILES_VERTICAL_SLAB);
        registerBlock("polished_granite_brick_tiles_vertical_slab", POLISHED_GRANITE_BRICK_TILES_VERTICAL_SLAB);
        registerBlock("polished_andesite_bricks_vertical_slab", POLISHED_ANDESITE_BRICKS_VERTICAL_SLAB);
        registerBlock("polished_diorite_bricks_vertical_slab", POLISHED_DIORITE_BRICKS_VERTICAL_SLAB);
        registerBlock("polished_granite_bricks_vertical_slab", POLISHED_GRANITE_BRICKS_VERTICAL_SLAB);
        registerBlock("polished_blackstone_brick_tiles_slab", POLISHED_BLACKSTONE_BRICK_TILES_SLAB);
        registerBlock("polished_blackstone_brick_tiles_vertical_slab", POLISHED_BLACKSTONE_BRICK_TILES_VERTICAL_SLAB);
        registerBlock("polished_andesite_brick_tiles_stairs", POLISHED_ANDESITE_BRICK_TILES_STAIRS);
        registerBlock("polished_diorite_brick_tiles_stairs", POLISHED_DIORITE_BRICK_TILES_STAIRS);
        registerBlock("polished_granite_brick_tiles_stairs", POLISHED_GRANITE_BRICK_TILES_STAIRS);
        registerBlock("polished_blackstone_brick_tiles_stairs", POLISHED_BLACKSTONE_BRICK_TILES_STAIRS);
        registerBlock("polished_andesite_bricks_stairs", POLISHED_ANDESITE_BRICKS_STAIRS);
        registerBlock("polished_diorite_bricks_stairs", POLISHED_DIORITE_BRICKS_STAIRS);
        registerBlock("polished_granite_bricks_stairs", POLISHED_GRANITE_BRICKS_STAIRS);
        //Vase Block
        registerBlock("terracotta_vase", TERRACOTTA_VASE);
        registerBlock("light_gray_terracotta_vase", LIGHT_GRAY_TERRACOTTA_VASE);
        registerBlock("black_terracotta_vase", BLACK_TERRACOTTA_VASE);
        registerBlock("sandstone_vase", SANDSTONE_VASE);
        //Tarracotta Bricks
        registerBlock("terracotta_bricks", TERRACOTTA_BRICKS);
        registerBlock("terracotta_bricks_slab", TERRACOTTA_BRICKS_SLAB);
        registerBlock("terracotta_bricks_stairs", TERRACOTTA_BRICKS_STAIRS);
        registerBlock("red_terracotta_bricks", RED_TERRACOTTA_BRICKS);
        registerBlock("red_terracotta_bricks_slab", RED_TERRACOTTA_BRICKS_SLAB);
        registerBlock("red_terracotta_bricks_stairs", RED_TERRACOTTA_BRICKS_STAIRS);
        registerBlock("red_terracotta_slab", RED_TERRACOTTA_SLAB);
        registerBlock("red_terracotta_stairs", RED_TERRACOTTA_STAIRS);
        registerBlock("cyan_terracotta_slab", CYAN_TERRACOTTA_SLAB);
        registerBlock("cyan_terracotta_stairs", CYAN_TERRACOTTA_STAIRS);
        registerBlock("black_terracotta_slab", BLACK_TERRACOTTA_SLAB);
        registerBlock("black_terracotta_stairs", BLACK_TERRACOTTA_STAIRS);
        registerBlock("blue_terracotta_slab", BLUE_TERRACOTTA_SLAB);
        registerBlock("blue_terracotta_stairs", BLUE_TERRACOTTA_STAIRS);
        registerBlock("brown_terracotta_slab", BROWN_TERRACOTTA_SLAB);
        registerBlock("brown_terracotta_stairs", BROWN_TERRACOTTA_STAIRS);
        registerBlock("light_blue_terracotta_slab", LIGHT_BLUE_TERRACOTTA_SLAB);
        registerBlock("light_blue_terracotta_stairs", LIGHT_BLUE_TERRACOTTA_STAIRS);
        registerBlock("gray_terracotta_slab", GRAY_TERRACOTTA_SLAB);
        registerBlock("gray_terracotta_stairs", GRAY_TERRACOTTA_STAIRS);
        registerBlock("green_terracotta_slab", GREEN_TERRACOTTA_SLAB);
        registerBlock("green_terracotta_stairs", GREEN_TERRACOTTA_STAIRS);
        registerBlock("lime_terracotta_slab", LIME_TERRACOTTA_SLAB);
        registerBlock("lime_terracotta_stairs", LIME_TERRACOTTA_STAIRS);
        registerBlock("light_gray_terracotta_slab", LIGHT_GRAY_TERRACOTTA_SLAB);
        registerBlock("light_gray_terracotta_stairs", LIGHT_GRAY_TERRACOTTA_STAIRS);
        registerBlock("magenta_terracotta_slab", MAGENTA_TERRACOTTA_SLAB);
        registerBlock("magenta_terracotta_stairs", MAGENTA_TERRACOTTA_STAIRS);
        registerBlock("orange_terracotta_slab", ORANGE_TERRACOTTA_SLAB);
        registerBlock("orange_terracotta_stairs", ORANGE_TERRACOTTA_STAIRS);
        registerBlock("pink_terracotta_slab", PINK_TERRACOTTA_SLAB);
        registerBlock("pink_terracotta_stairs", PINK_TERRACOTTA_STAIRS);
        registerBlock("purple_terracotta_slab", PURPLE_TERRACOTTA_SLAB);
        registerBlock("purple_terracotta_stairs", PURPLE_TERRACOTTA_STAIRS);
        registerBlock("yellow_terracotta_slab", YELLOW_TERRACOTTA_SLAB);
        registerBlock("yellow_terracotta_stairs", YELLOW_TERRACOTTA_STAIRS);
        registerBlock("terracotta_slab", TERRACOTTA_SLAB);
        registerBlock("terracotta_stairs", TERRACOTTA_STAIRS);
        //Layer Block
        registerBlock("sand_layer", SAND_LAYER);
        registerBlock("red_sand_layer", RED_SAND_LAYER);
        registerBlock("gravel_layer", GRAVEL_LAYER);
        registerBlock("dirt_layer", DIRT_LAYER);
        registerBlock("coarse_dirt_layer", COARSE_DIRT_LAYER);
        registerBlock("packed_dirt_layer", PACKED_DIRT_LAYER);
        registerBlock("black_concrete_powder_layer", BLACK_CONCRETE_POWDER_LAYER);
        registerBlock("red_concrete_powder_layer", RED_CONCRETE_POWDER_LAYER);
        registerBlock("green_concrete_powder_layer", GREEN_CONCRETE_POWDER_LAYER);
        registerBlock("brown_concrete_powder_layer", BROWN_CONCRETE_POWDER_LAYER);
        registerBlock("blue_concrete_powder_layer", BLUE_CONCRETE_POWDER_LAYER);
        registerBlock("purple_concrete_powder_layer", PURPLE_CONCRETE_POWDER_LAYER);
        registerBlock("light_gray_concrete_powder_layer", LIGHT_GRAY_CONCRETE_POWDER_LAYER);
        registerBlock("cyan_concrete_powder_layer", CYAN_CONCRETE_POWDER_LAYER);
        registerBlock("gray_concrete_powder_layer", GRAY_CONCRETE_POWDER_LAYER);
        registerBlock("pink_concrete_powder_layer", PINK_CONCRETE_POWDER_LAYER);
        registerBlock("lime_concrete_powder_layer", LIME_CONCRETE_POWDER_LAYER);
        registerBlock("yellow_concrete_powder_layer", YELLOW_CONCRETE_POWDER_LAYER);
        registerBlock("light_blue_concrete_powder_layer", LIGHT_BLUE_CONCRETE_POWDER_LAYER);
        registerBlock("magenta_concrete_powder_layer", MAGENTA_CONCRETE_POWDER_LAYER);
        registerBlock("orange_concrete_powder_layer", ORANGE_CONCRETE_POWDER_LAYER);
        registerBlock("white_concrete_powder_layer", WHITE_CONCRETE_POWDER_LAYER);
        //Transistion Blocks
        registerBlock("coarse_dirt_cobble_stone_mix_light", CD_CS_LIGHT);
        registerBlock("coarse_dirt_cobble_stone_mix_heavy", CD_CS_HEAVY);
        registerBlock("sand_stone_brick_mix_light", SD_SB_LIGHT);
        registerBlock("sand_stone_brick_mix_heavy", SD_SB_HEAVY);
        registerBlock("sand_chiseled_stone_brick_mix", SD_CSB_LIGHT);
        registerBlock("coarse_dirt_dark_stone_brick_tiles_heavy", COARSE_DIRT_DARK_STONE_BRICK_TILES_HEAVY);
        registerBlock("coarse_dirt_dark_stone_brick_tiles_light", COARSE_DIRT_DARK_STONE_BRICK_TILES_LIGHT);
        registerBlock("coarse_dirt_stone_brick_tiles_heavy", COARSE_DIRT_STONE_BRICK_TILES_HEAVY);
        registerBlock("coarse_dirt_stone_brick_tiles_light", COARSE_DIRT_STONE_BRICK_TILES_LIGHT);
        registerBlock("dirt_dark_stone_brick_tiles_heavy", DIRT_DARK_STONE_BRICK_TILES_HEAVY);
        registerBlock("dirt_dark_stone_brick_tiles_light", DIRT_DARK_STONE_BRICK_TILES_LIGHT);
        registerBlock("dirt_stone_brick_tiles_heavy", DIRT_STONE_BRICK_TILES_HEAVY);
        registerBlock("dirt_stone_brick_tiles_light", DIRT_STONE_BRICK_TILES_LIGHT);
        registerBlock("packed_dirt_dark_stone_brick_tiles_heavy", PACKED_DIRT_DARK_STONE_BRICK_TILES_HEAVY);
        registerBlock("packed_dirt_dark_stone_brick_tiles_light", PACKED_DIRT_DARK_STONE_BRICK_TILES_LIGHT);
        registerBlock("packed_dirt_stone_brick_tiles_heavy", PACKED_DIRT_STONE_BRICK_TILES_HEAVY);
        registerBlock("packed_dirt_stone_brick_tiles_light", PACKED_DIRT_STONE_BRICK_TILES_LIGHT);
        registerBlock("coarse_dirt_dark_stone_bricks_heavy", COARSE_DIRT_DARK_STONE_BRICKS_HEAVY);
        registerBlock("coarse_dirt_dark_stone_bricks_light", COARSE_DIRT_DARK_STONE_BRICKS_LIGHT);
        registerBlock("coarse_dirt_stone_bricks_heavy", COARSE_DIRT_STONE_BRICKS_HEAVY);
        registerBlock("coarse_dirt_stone_bricks_light", COARSE_DIRT_STONE_BRICKS_LIGHT);
        registerBlock("dirt_dark_stone_bricks_heavy", DIRT_DARK_STONE_BRICKS_HEAVY);
        registerBlock("dirt_dark_stone_bricks_light", DIRT_DARK_STONE_BRICKS_LIGHT);
        registerBlock("dirt_stone_bricks_heavy", DIRT_STONE_BRICKS_HEAVY);
        registerBlock("dirt_stone_bricks_light", DIRT_STONE_BRICKS_LIGHT);
        registerBlock("packed_dirt_dark_stone_bricks_heavy", PACKED_DIRT_DARK_STONE_BRICKS_HEAVY);
        registerBlock("packed_dirt_dark_stone_bricks_light", PACKED_DIRT_DARK_STONE_BRICKS_LIGHT);
        registerBlock("packed_dirt_stone_bricks_heavy", PACKED_DIRT_STONE_BRICKS_HEAVY);
        registerBlock("packed_dirt_stone_bricks_light", PACKED_DIRT_STONE_BRICKS_LIGHT);
        //Terracotta Bricks
        registerBlock("blue_terracotta_bricks", BLUE_TERRACOTTA_BRICKS);
        registerBlock("blue_terracotta_bricks_slab", BLUE_TERRACOTTA_BRICKS_SLAB);
        registerBlock("blue_terracotta_bricks_stairs", BLUE_TERRACOTTA_BRICKS_STAIRS);
        registerBlock("white_terracotta_bricks", WHITE_TERRACOTTA_BRICKS);
        registerBlock("white_terracotta_bricks_slab", WHITE_TERRACOTTA_BRICKS_SLAB);
        registerBlock("white_terracotta_bricks_stairs", WHITE_TERRACOTTA_BRICKS_STAIRS);
        registerBlock("brown_terracotta_bricks", BROWN_TERRACOTTA_BRICKS);
        registerBlock("brown_terracotta_bricks_slab", BROWN_TERRACOTTA_BRICKS_SLAB);
        registerBlock("brown_terracotta_bricks_stairs", BROWN_TERRACOTTA_BRICKS_STAIRS);
        registerBlock("cyan_terracotta_bricks", CYAN_TERRACOTTA_BRICKS);
        registerBlock("cyan_terracotta_bricks_slab", CYAN_TERRACOTTA_BRICKS_SLAB);
        registerBlock("cyan_terracotta_bricks_stairs", CYAN_TERRACOTTA_BRICKS_STAIRS);
        registerBlock("white_terracotta_slab", WHITE_TERRACOTTA_SLAB);
        registerBlock("white_terracotta_stairs", WHITE_TERRACOTTA_STAIRS);
        registerBlock("black_terracotta_bricks", BLACK_TERRACOTTA_BRICKS);
        registerBlock("black_terracotta_bricks_slab", BLACK_TERRACOTTA_BRICKS_SLAB);
        registerBlock("black_terracotta_bricks_stairs", BLACK_TERRACOTTA_BRICKS_STAIRS);
        registerBlock("gray_terracotta_bricks", GRAY_TERRACOTTA_BRICKS);
        registerBlock("gray_terracotta_bricks_slab", GRAY_TERRACOTTA_BRICKS_SLAB);
        registerBlock("gray_terracotta_bricks_stairs", GRAY_TERRACOTTA_BRICKS_STAIRS);
        registerBlock("green_terracotta_bricks", GREEN_TERRACOTTA_BRICKS);
        registerBlock("green_terracotta_bricks_slab", GREEN_TERRACOTTA_BRICKS_SLAB);
        registerBlock("green_terracotta_bricks_stairs", GREEN_TERRACOTTA_BRICKS_STAIRS);
        registerBlock("light_blue_terracotta_bricks", LIGHT_BLUE_TERRACOTTA_BRICKS);
        registerBlock("light_blue_terracotta_bricks_slab", LIGHT_BLUE_TERRACOTTA_BRICKS_SLAB);
        registerBlock("light_blue_terracotta_bricks_stairs", LIGHT_BLUE_TERRACOTTA_BRICKS_STAIRS);
        registerBlock("light_gray_terracotta_bricks", LIGHT_GRAY_TERRACOTTA_BRICKS);
        registerBlock("light_gray_terracotta_bricks_slab", LIGHT_GRAY_TERRACOTTA_BRICKS_SLAB);
        registerBlock("light_gray_terracotta_bricks_stairs", LIGHT_GRAY_TERRACOTTA_BRICKS_STAIRS);
        registerBlock("pink_terracotta_bricks", PINK_TERRACOTTA_BRICKS);
        registerBlock("pink_terracotta_bricks_slab", PINK_TERRACOTTA_BRICKS_SLAB);
        registerBlock("pink_terracotta_bricks_stairs", PINK_TERRACOTTA_BRICKS_STAIRS);
        registerBlock("purple_terracotta_bricks", PURPLE_TERRACOTTA_BRICKS);
        registerBlock("purple_terracotta_bricks_slab", PURPLE_TERRACOTTA_BRICKS_SLAB);
        registerBlock("purple_terracotta_bricks_stairs", PURPLE_TERRACOTTA_BRICKS_STAIRS);
        registerBlock("terracotta_bricks_vertical_slab", TERRACOTTA_BRICKS_VERTICAL_SLAB);
        registerBlock("black_terracotta_bricks_vertical_slab", BLACK_TERRACOTTA_BRICKS_VERTICAL_SLAB);
        registerBlock("red_terracotta_bricks_vertical_slab", RED_TERRACOTTA_BRICKS_VERTICAL_SLAB);
        registerBlock("green_terracotta_bricks_vertical_slab", GREEN_TERRACOTTA_BRICKS_VERTICAL_SLAB);
        registerBlock("brown_terracotta_bricks_vertical_slab", BROWN_TERRACOTTA_BRICKS_VERTICAL_SLAB);
        registerBlock("blue_terracotta_bricks_vertical_slab", BLUE_TERRACOTTA_BRICKS_VERTICAL_SLAB);
        registerBlock("purple_terracotta_bricks_vertical_slab", PURPLE_TERRACOTTA_BRICKS_VERTICAL_SLAB);
        registerBlock("light_gray_terracotta_bricks_vertical_slab", LIGHT_GRAY_TERRACOTTA_BRICKS_VERTICAL_SLAB);
        registerBlock("cyan_terracotta_bricks_vertical_slab", CYAN_TERRACOTTA_BRICKS_VERTICAL_SLAB);
        registerBlock("gray_terracotta_bricks_vertical_slab", GRAY_TERRACOTTA_BRICKS_VERTICAL_SLAB);
        registerBlock("pink_terracotta_bricks_vertical_slab", PINK_TERRACOTTA_BRICKS_VERTICAL_SLAB);
        registerBlock("lime_terracotta_bricks_vertical_slab", LIME_TERRACOTTA_BRICKS_VERTICAL_SLAB);
        registerBlock("yellow_terracotta_bricks_vertical_slab", YELLOW_TERRACOTTA_BRICKS_VERTICAL_SLAB);
        registerBlock("light_blue_terracotta_bricks_vertical_slab", LIGHT_BLUE_TERRACOTTA_BRICKS_VERTICAL_SLAB);
        registerBlock("magenta_terracotta_bricks_vertical_slab", MAGENTA_TERRACOTTA_BRICKS_VERTICAL_SLAB);
        registerBlock("orange_terracotta_bricks_vertical_slab", ORANGE_TERRACOTTA_BRICKS_VERTICAL_SLAB);
        registerBlock("white_terracotta_bricks_vertical_slab", WHITE_TERRACOTTA_BRICKS_VERTICAL_SLAB);
        registerBlock("burnt_acacia_log", BURNT_ACACIA_LOG);
        registerBlock("burnt_planks", BURNT_PLANKS);
        registerBlock("burnt_planks_slab", BURNT_PLANKS_SLAB);
        registerBlock("burnt_planks_stairs", BURNT_PLANKS_STAIRS);
        registerBlock("stone_brick_tiles", STONE_BRICK_SQUARE);
        registerBlock("stone_brick_tiles_slab", STONE_BRICK_SQUARE_SLAB);
        registerBlock("stone_brick_tiles_vertical_slab", STONE_BRICK_TILES_VERTICAL_SLAB);
        registerBlock("stone_brick_tiles_stairs", STONE_BRICK_SQUARE_STAIRS);
        registerBlock("large_cobblestone", LARGE_COBBLESTONE);
        registerBlock("dirt_slab", DIRT_SLAB);
        registerBlock("coarse_dirt_slab", COARSE_DIRT_SLAB);
        registerBlock("grass_block_slab", GRASS_SLAB);
        registerBlock("snow_grass_block_slab", SNOW_GRASS_SLAB);
        //Chimney
        registerBlock("cobblestone_chimney", COBBLESTONE_CHIMNEY);
        registerBlock("andesite_chimney", ANDESITE_CHIMNEY);
        registerBlock("granite_chimney", GRANITE_CHIMNEY);
        registerBlock("diorite_chimney", DIORITE_CHIMNEY);
        registerBlock("bricks_chimney", BRICKS_CHIMNEY);
        //Vertical Glass Pane
        registerBlock("vertical_glass_pane", VERTICAL_GLASS_PANE, MBMItems.BATA);
        //Lantern
        registerBlockNoItem("lantern_rope", LANTERN_ROPE);
        registerBlock("pib", PIB, MBMItems.BATA);
        registerBlock("black_concrete_slab", BLACK_CONCRETE_SLAB);
        registerBlock("red_concrete_slab", RED_CONCRETE_SLAB);
        registerBlock("green_concrete_slab", GREEN_CONCRETE_SLAB);
        registerBlock("brown_concrete_slab", BROWN_CONCRETE_SLAB);
        registerBlock("blue_concrete_slab", BLUE_CONCRETE_SLAB);
        registerBlock("purple_concrete_slab", PURPLE_CONCRETE_SLAB);
        registerBlock("light_gray_concrete_slab", LIGHT_GRAY_CONCRETE_SLAB);
        registerBlock("gray_concrete_slab", GRAY_CONCRETE_SLAB);
        registerBlock("pink_concrete_slab", PINK_CONCRETE_SLAB);
        registerBlock("lime_concrete_slab", LIME_CONCRETE_SLAB);
        registerBlock("yellow_concrete_slab", YELLOW_CONCRETE_SLAB);
        registerBlock("light_blue_concrete_slab", LIGHT_BLUE_CONCRETE_SLAB);
        registerBlock("magenta_concrete_slab", MAGENTA_CONCRETE_SLAB);
        registerBlock("orange_concrete_slab", ORANGE_CONCRETE_SLAB);
        registerBlock("white_concrete_slab", WHITE_CONCRETE_SLAB);
        registerBlock("cyan_concrete_slab", CYAN_CONCRETE_SLAB);
        registerBlock("black_concrete_vertical_slab", BLACK_CONCRETE_VERTICAL_SLAB);
        registerBlock("red_concrete_vertical_slab", RED_CONCRETE_VERTICAL_SLAB);
        registerBlock("green_concrete_vertical_slab", GREEN_CONCRETE_VERTICAL_SLAB);
        registerBlock("brown_concrete_vertical_slab", BROWN_CONCRETE_VERTICAL_SLAB);
        registerBlock("blue_concrete_vertical_slab", BLUE_CONCRETE_VERTICAL_SLAB);
        registerBlock("purple_concrete_vertical_slab", PURPLE_CONCRETE_VERTICAL_SLAB);
        registerBlock("light_gray_concrete_vertical_slab", LIGHT_GRAY_CONCRETE_VERTICAL_SLAB);
        registerBlock("gray_concrete_vertical_slab", GRAY_CONCRETE_VERTICAL_SLAB);
        registerBlock("pink_concrete_vertical_slab", PINK_CONCRETE_VERTICAL_SLAB);
        registerBlock("lime_concrete_vertical_slab", LIME_CONCRETE_VERTICAL_SLAB);
        registerBlock("yellow_concrete_vertical_slab", YELLOW_CONCRETE_VERTICAL_SLAB);
        registerBlock("light_blue_concrete_vertical_slab", LIGHT_BLUE_CONCRETE_VERTICAL_SLAB);
        registerBlock("magenta_concrete_vertical_slab", MAGENTA_CONCRETE_VERTICAL_SLAB);
        registerBlock("orange_concrete_vertical_slab", ORANGE_CONCRETE_VERTICAL_SLAB);
        registerBlock("white_concrete_vertical_slab", WHITE_CONCRETE_VERTICAL_SLAB);
        registerBlock("cyan_concrete_vertical_slab", CYAN_CONCRETE_VERTICAL_SLAB);
        registerBlock("black_concrete_stairs", BLACK_CONCRETE_STAIRS);
        registerBlock("red_concrete_stairs", RED_CONCRETE_STAIRS);
        registerBlock("green_concrete_stairs", GREEN_CONCRETE_STAIRS);
        registerBlock("brown_concrete_stairs", BROWN_CONCRETE_STAIRS);
        registerBlock("blue_concrete_stairs", BLUE_CONCRETE_STAIRS);
        registerBlock("purple_concrete_stairs", PURPLE_CONCRETE_STAIRS);
        registerBlock("light_gray_concrete_stairs", LIGHT_GRAY_CONCRETE_STAIRS);
        registerBlock("gray_concrete_stairs", GRAY_CONCRETE_STAIRS);
        registerBlock("pink_concrete_stairs", PINK_CONCRETE_STAIRS);
        registerBlock("lime_concrete_stairs", LIME_CONCRETE_STAIRS);
        registerBlock("yellow_concrete_stairs", YELLOW_CONCRETE_STAIRS);
        registerBlock("light_blue_concrete_stairs", LIGHT_BLUE_CONCRETE_STAIRS);
        registerBlock("magenta_concrete_stairs", MAGENTA_CONCRETE_STAIRS);
        registerBlock("orange_concrete_stairs", ORANGE_CONCRETE_STAIRS);
        registerBlock("white_concrete_stairs", WHITE_CONCRETE_STAIRS);
        registerBlock("cyan_concrete_stairs", CYAN_CONCRETE_STAIRS);
        registerBlock("polished_andesite_brick_tiles_slab", POLISHED_ANDESITE_BRICK_TILES_SLAB);
        registerBlock("polished_diorite_brick_tiles_slab", POLISHED_DIORITE_BRICK_TILES_SLAB);
        registerBlock("polished_granite_brick_tiles_slab", POLISHED_GRANITE_BRICK_TILES_SLAB);
        registerBlock("polished_andesite_bricks_slab", POLISHED_ANDESITE_BRICKS_SLAB);
        registerBlock("polished_diorite_bricks_slab", POLISHED_DIORITE_BRICKS_SLAB);
        registerBlock("polished_granite_bricks_slab", POLISHED_GRANITE_BRICKS_SLAB);
        registerBlock("polished_andesite_brick_tiles", POLISHED_ANDESITE_BRICK_TILES);
        registerBlock("polished_diorite_brick_tiles", POLISHED_DIORITE_BRICK_TILES);
        registerBlock("polished_granite_brick_tiles", POLISHED_GRANITE_BRICK_TILES);
        registerBlock("polished_blackstone_brick_tiles", POLISHED_BLACKSTONE_BRICK_TILES);
        registerBlock("polished_andesite_bricks", POLISHED_ANDESITE_BRICKS);
        registerBlock("polished_diorite_bricks", POLISHED_DIORITE_BRICKS);
        registerBlock("polished_granite_bricks", POLISHED_GRANITE_BRICKS);
        registerBlock("andesite_pillar", ANDESITE_PILLAR);
        registerBlock("diorite_pillar", DIORITE_PILLAR);
        registerBlock("granite_pillar", GRANITE_PILLAR);
        registerBlock("stone_pillar", STONE_PILLAR);
        registerBlock("thin_andesite_pillar", THIN_ANDESITE_PILLAR);
        registerBlock("thin_diorite_pillar", THIN_DIORITE_PILLAR);
        registerBlock("thin_granite_pillar", THIN_GRANITE_PILLAR);
        registerBlock("thin_stone_pillar", THIN_STONE_PILLAR);
        registerBlock("stripped_oak_framing", STRIPPED_OAK_FRAMING, MBMItems.BATA);
        registerBlock("iron_block", IRON_BLOCK);
        registerBlock("exposed_iron", EXPOSED_IRON);
        registerBlock("degraded_iron", DEGRADED_IRON);
        registerBlock("weathered_iron", WEATHERED_IRON);
        registerBlock("rusted_iron", RUSTED_IRON);
        registerBlock("waxed_exposed_iron", WAXED_EXPOSED_IRON);
        registerBlock("waxed_degraded_iron", WAXED_DEGRADED_IRON);
        registerBlock("waxed_weathered_iron", WAXED_WEATHERED_IRON);
        registerBlock("waxed_rusted_iron", WAXED_RUSTED_IRON);
        registerBlock("heated_iron", HEATED_IRON);
        registerBlock("hardened_iron", HARDENED_IRON);
        registerBlock("ruby_block", RUBY_BLOCK);
        registerBlock("wax_block", WAX_BLOCK);
        registerBlock("ruby_ore", RUBY_ORE);
        registerBlock("packed_dirt", PACKED_DIRT);
        registerBlock("spike", SPIKE);
        registerBlock("burnt_birch_log", BURNT_BIRCH_LOG);
        registerBlock("burnt_dark_oak_log", BURNT_DARK_OAK_LOG);
        registerBlock("burnt_oak_log", BURNT_OAK_LOG);
        registerBlock("burnt_jungle_log", BURNT_JUNGLE_LOG);
        registerBlock("burnt_spruce_log", BURNT_SPRUCE_LOG);
        registerBlock("coarse_dirt_dark_stone_heavy", COARSE_DIRT_DARK_STONE_HEAVY);
        registerBlock("coarse_dirt_dark_stone_light", COARSE_DIRT_DARK_STONE_LIGHT);
        registerBlock("coarse_dirt_stone_heavy", COARSE_DIRT_STONE_HEAVY);
        registerBlock("coarse_dirt_stone_light", COARSE_DIRT_STONE_LIGHT);
        registerBlock("dirt_dark_stone_heavy", DIRT_DARK_STONE_HEAVY);
        registerBlock("dirt_dark_stone_light", DIRT_DARK_STONE_LIGHT);
        registerBlock("dirt_stone_heavy", DIRT_STONE_HEAVY);
        registerBlock("dirt_stone_light", DIRT_STONE_LIGHT);
        registerBlock("packed_dirt_dark_stone_heavy", PACKED_DIRT_DARK_STONE_HEAVY);
        registerBlock("packed_dirt_dark_stone_light", PACKED_DIRT_DARK_STONE_LIGHT);
        registerBlock("packed_dirt_stone_heavy", PACKED_DIRT_STONE_HEAVY);
        registerBlock("packed_dirt_stone_light", PACKED_DIRT_STONE_LIGHT);
        registerBlock("cobblestone_side_stairs", COBBLESTONE_SIDE_STAIRS, MBMItems.BATA);

        /* for (final Identifier id : Registry.BLOCK.getIds()){
            final Block entry = Registry.BLOCK.get(id);

            System.out.println(entry);

            entry.
            if (entry.getClass() == SlabBlock.class || entry.getClass().getSuperclass() == SlabBlock.class){
                BetterslabsClient.slabList.add(id);
                StateRefresher.INSTANCE.addBlockProperty(entry, Properties.AXIS, Direction.Axis.Y);
                StateRefresher.INSTANCE.reorderBlockStates();
            }
        }
        */
//##block##//
    }

    private static void registerBlockNoItem(String blockName, Block block){
        Registry.register(Registry.BLOCK, new Identifier(moreblocksmod.ModName, blockName), block);
    }

    private static void registerBlock(String blockName, Block block, Settings settings){
        registerBlockNoItem(blockName, block);
        Registry.register(Registry.ITEM, new Identifier(moreblocksmod.ModName, blockName), new BlockItem(block, settings));
    }

    private static void registerBlock(String blockName, Block block){
        Settings settings = MBMItems.BUILDING_BLOCKS;
        registerBlock(blockName, block, settings);
    }
}



