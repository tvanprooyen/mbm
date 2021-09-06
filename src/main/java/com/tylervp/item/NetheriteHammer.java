package com.tylervp.item;

import java.util.Collection;
//import java.util.Set;

//import com.google.common.collect.ImmutableSet;

import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.AbstractRailBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DetectorRailBlock;
import net.minecraft.block.FacingBlock;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.Material;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.PoweredRailBlock;
import net.minecraft.block.RailBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.enums.RailShape;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

import net.minecraft.network.MessageType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Util;
import org.jetbrains.annotations.Nullable;

public class NetheriteHammer extends MiningToolItem {
    //private static final Set<Block> EFFECTIVE_BLOCKS;
    //private boolean ASCENDING_TOGGLE;

    public NetheriteHammer(ToolMaterial toolMaterial, Item.Settings settings) {
        super((float)4.0f, -3.5f, toolMaterial, BlockTags.PICKAXE_MINEABLE, settings);
        //super((float)4.0f, -3.5f, toolMaterial, NetheriteHammer.EFFECTIVE_BLOCKS, settings);
    }

    private boolean RailBlock(BlockState state){
        return state.getBlock().getClass().getSuperclass() == AbstractRailBlock.class;
    }

    private boolean ValidRailAscending(WorldAccess world, BlockPos pos){
        return !(world.getBlockState(pos).isAir() || RailBlock(world.getBlockState(pos)));
    }

    private boolean HorizontalDirectionalBlock(BlockState state){
        return state.getBlock().getClass() == HorizontalFacingBlock.class || state.getBlock().getClass() == AbstractFurnaceBlock.class || state.getBlock().getClass().getSuperclass() == HorizontalFacingBlock.class || state.isOf(Blocks.HOPPER) || state.isOf(Blocks.REPEATER) || state.isOf(Blocks.COMPARATOR)  || state.isOf(Blocks.CHEST) || state.isOf(Blocks.ANVIL);
    }

    private boolean OmniDirectionalBlock(BlockState state){
        return state.getBlock().getClass() == FacingBlock.class || state.getBlock().getClass().getSuperclass() == FacingBlock.class || state.isOf(Blocks.BARREL);
    }

    private boolean PillarBlock(BlockState state){
        return state.getBlock().getClass() == PillarBlock.class || state.getBlock().getClass().getSuperclass() == PillarBlock.class;
    }

    private boolean StairsBlock(BlockState state){
        return state.getBlock().getClass() == StairsBlock.class || state.getBlock().getClass().getSuperclass() == StairsBlock.class;
    }

     @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        PlayerEntity player = context.getPlayer();
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        BlockState state = world.getBlockState(pos);
        
        if (!world.isClient && player != null) {

            if((HorizontalDirectionalBlock(state) || OmniDirectionalBlock(state) || PillarBlock(state) || RailBlock(state) || StairsBlock(state)) && (!player.isSneaking() || player.getOffHandStack().isEmpty())) {
                this.use(player, world.getBlockState(pos), world, pos, context.getStack());
            }
        }
        if(( HorizontalDirectionalBlock(state) || OmniDirectionalBlock(state) || PillarBlock(state) || RailBlock(state) || StairsBlock(state)) && (!player.isSneaking() || player.getOffHandStack().isEmpty()) && player.getMainHandStack().getItem() == this) {
            if(world.isClient()){
                player.playSound(SoundEvents.BLOCK_ANVIL_HIT, SoundCategory.NEUTRAL, 1.0f, 1.0f);
            }
            return ActionResult.success(world.isClient);
        } else {
            return super.useOnBlock(context);
        }
    }
  
     private void use(PlayerEntity player, BlockState state, WorldAccess world, BlockPos pos, ItemStack stack) {
        Property<?> property = null;
        if(RailBlock(state)){
            RailShape CurrentShape = RailShape.NORTH_EAST;

            if(state.isOf(Blocks.RAIL)){
                CurrentShape = state.get(RailBlock.SHAPE);
            } else  if(state.isOf(Blocks.POWERED_RAIL) || state.isOf(Blocks.ACTIVATOR_RAIL)){
                CurrentShape = state.get(PoweredRailBlock.SHAPE);
            } else  if(state.isOf(Blocks.DETECTOR_RAIL)){
                CurrentShape = state.get(DetectorRailBlock.SHAPE);
            }

            RailShape FutureShape = CurrentShape;

            switch (CurrentShape) {
                case NORTH_SOUTH:
                    FutureShape = RailShape.EAST_WEST;
                    break;

                case EAST_WEST:
                    if(ValidRailAscending(world, pos.north())){
                        FutureShape = RailShape.ASCENDING_NORTH;
                    } else if(ValidRailAscending(world, pos.east())){
                        FutureShape = RailShape.ASCENDING_EAST;
                    } else if(ValidRailAscending(world, pos.south())){
                        FutureShape = RailShape.ASCENDING_SOUTH;
                    } else if(ValidRailAscending(world, pos.west())){
                        FutureShape = RailShape.ASCENDING_WEST;
                    } else {
                        if(state.isOf(Blocks.POWERED_RAIL) || state.isOf(Blocks.DETECTOR_RAIL) || state.isOf(Blocks.ACTIVATOR_RAIL)){
                            FutureShape = RailShape.NORTH_SOUTH;
                        } else {
                            FutureShape = RailShape.NORTH_EAST;
                        }
                    }
                    break;
                    
                case ASCENDING_NORTH:
                    if(ValidRailAscending(world, pos.east())){
                        FutureShape = RailShape.ASCENDING_EAST;
                    } else if(ValidRailAscending(world, pos.south())){
                        FutureShape = RailShape.ASCENDING_SOUTH;
                    } else {
                        if(state.isOf(Blocks.POWERED_RAIL) || state.isOf(Blocks.DETECTOR_RAIL) || state.isOf(Blocks.ACTIVATOR_RAIL)){
                            FutureShape = RailShape.NORTH_SOUTH;
                        } else {
                            FutureShape = RailShape.NORTH_EAST;
                        }
                    }
                    break;

                case ASCENDING_EAST:
                    if(ValidRailAscending(world, pos.south())){
                        FutureShape = RailShape.ASCENDING_SOUTH;
                    } else if(ValidRailAscending(world, pos.west())){
                        FutureShape = RailShape.ASCENDING_WEST;
                    } else {
                        if(state.isOf(Blocks.POWERED_RAIL) || state.isOf(Blocks.DETECTOR_RAIL) || state.isOf(Blocks.ACTIVATOR_RAIL)){
                            FutureShape = RailShape.NORTH_SOUTH;
                        } else {
                            FutureShape = RailShape.NORTH_EAST;
                        }
                    }
                    break;

                case ASCENDING_SOUTH:
                    if(ValidRailAscending(world, pos.west())){
                        FutureShape = RailShape.ASCENDING_WEST;
                    } else if(ValidRailAscending(world, pos.north())){
                        FutureShape = RailShape.ASCENDING_NORTH;
                    } else {
                        if(state.isOf(Blocks.POWERED_RAIL) || state.isOf(Blocks.DETECTOR_RAIL) || state.isOf(Blocks.ACTIVATOR_RAIL)){
                            FutureShape = RailShape.NORTH_SOUTH;
                        } else {
                            FutureShape = RailShape.NORTH_EAST;
                        }
                    }
                    break;

                case ASCENDING_WEST:
                    if(state.isOf(Blocks.POWERED_RAIL) || state.isOf(Blocks.DETECTOR_RAIL) || state.isOf(Blocks.ACTIVATOR_RAIL)){
                        FutureShape = RailShape.NORTH_SOUTH;
                    } else {
                        FutureShape = RailShape.NORTH_EAST;
                    }
                    break;

                case NORTH_EAST:
                    FutureShape = RailShape.NORTH_WEST;
                    break;
                
                case NORTH_WEST:
                    FutureShape = RailShape.SOUTH_WEST;
                    break;

                case SOUTH_WEST:
                    FutureShape = RailShape.SOUTH_EAST;
                    break;

                case SOUTH_EAST:
                    FutureShape = RailShape.NORTH_SOUTH;
                    break;
            
                default:
                    FutureShape = CurrentShape;
                    break;
            }

            if(state.isOf(Blocks.RAIL)){
                state = state.with(RailBlock.SHAPE, FutureShape);
                world.setBlockState(pos, state, 1);
                property = RailBlock.SHAPE;
            } else  if(state.isOf(Blocks.POWERED_RAIL) || state.isOf(Blocks.ACTIVATOR_RAIL)){
                state = state.with(PoweredRailBlock.SHAPE, FutureShape);
                world.setBlockState(pos, state, 1);
                property = PoweredRailBlock.SHAPE;
            } else  if(state.isOf(Blocks.DETECTOR_RAIL)){
                state = state.with(DetectorRailBlock.SHAPE, FutureShape);
                world.setBlockState(pos, state, 1);
                property = DetectorRailBlock.SHAPE;
            }

        } else {
            Block block = state.getBlock();
            StateManager<Block, BlockState> stateManager = block.getStateManager();
            Collection<Property<?>> collection = stateManager.getProperties();
            String string = Registry.BLOCK.getId(block).toString();
            if (!collection.isEmpty()) {
                NbtCompound compoundTag = stack.getOrCreateSubNbt("SaveProperties");
                String string2 = compoundTag.getString(string);
                property = stateManager.getProperty(string2);
                Property<?> selectedProperty = null;
                
                if(PillarBlock(state)){
                    selectedProperty = Properties.AXIS;
                } else if(OmniDirectionalBlock(state)){
                    selectedProperty = Properties.FACING;
                } else if(HorizontalDirectionalBlock(state)){
                    if(state.isOf(Blocks.HOPPER)){
                        selectedProperty = Properties.HOPPER_FACING;
                    } else {
                        selectedProperty = Properties.HORIZONTAL_FACING;
                    }
                    
                } else if(StairsBlock(state)){
                    selectedProperty = Properties.STAIR_SHAPE;
                }

                if(selectedProperty != null){
                    /* for (int i = 0; i < collection.size(); i++) {
                        System.out.println(collection.toArray()[i]);
                        if(collection.toArray()[i] == selectedProperty){
                            System.out.println(true);
                            property = cycle(collection, selectedProperty, false);
                            String string3 = property.getName();
                            compoundTag.putString(string, string3);
                            break;
                        }
                    } */
                    property = selectedProperty;
                    //property = cycle(collection, selectedProperty, false);
                    String string3 = selectedProperty.getName();
                    compoundTag.putString(string, string3);

                    BlockState blockState = cycle(state, selectedProperty, false);
                    world.setBlockState(pos, blockState, 18);
                    state = blockState;
                }
            }
        }

        if(player.isCreativeLevelTwoOp()){
            sendMessage(player, new TranslatableText("(" +property.getName().toUpperCase() + ") " + getValueString(state, property).toUpperCase()));
        }

        stack.<PlayerEntity>damage(2, player, player1 -> player1.sendToolBreakStatus(Hand.MAIN_HAND));
     }
  
     private static <T extends Comparable<T>> BlockState cycle(BlockState state, Property<T> property, boolean inverse) {
        return (BlockState)state.with(property, cycle(property.getValues(), state.get(property), inverse));
     }
  
     private static <T> T cycle(Iterable<T> elements, @Nullable T current, boolean inverse) {
        return inverse ? Util.previous(elements, current) : Util.next(elements, current);
     }
  
     private static void sendMessage(PlayerEntity player, Text message) {
        ((ServerPlayerEntity)player).sendMessage(message, MessageType.GAME_INFO, Util.NIL_UUID);
     }
  
     private static <T extends Comparable<T>> String getValueString(BlockState state, Property<T> property) {
        return property.name(state.get(property));
     }
    
    

    @Override
    public boolean isSuitableFor(BlockState state) {
        int integer3 = this.getMaterial().getMiningLevel();
        if (state.isOf(Blocks.OBSIDIAN) || state.isOf(Blocks.CRYING_OBSIDIAN) || state.isOf(Blocks.NETHERITE_BLOCK) || state.isOf(Blocks.RESPAWN_ANCHOR) || state.isOf(Blocks.ANCIENT_DEBRIS)) {
            return integer3 >= 3;
        }
        if (state.isOf(Blocks.DIAMOND_BLOCK) || state.isOf(Blocks.DIAMOND_ORE) || state.isOf(Blocks.EMERALD_ORE) || state.isOf(Blocks.EMERALD_BLOCK) || state.isOf(Blocks.GOLD_BLOCK) || state.isOf(Blocks.GOLD_ORE) || state.isOf(Blocks.REDSTONE_ORE)) {
            return integer3 >= 2;
        }
        if (state.isOf(Blocks.IRON_BLOCK) || state.isOf(Blocks.IRON_ORE) || state.isOf(Blocks.LAPIS_BLOCK) || state.isOf(Blocks.LAPIS_ORE)) {
            return integer3 >= 1;
        }
        Material material4 = state.getMaterial();
        return material4 == Material.STONE || material4 == Material.METAL || material4 == Material.REPAIR_STATION || state.isOf(Blocks.NETHER_GOLD_ORE);
    }
    
    @Override
    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
        Material material4 = state.getMaterial();
        if (material4 == Material.METAL || material4 == Material.REPAIR_STATION || material4 == Material.STONE) {
            return this.miningSpeed;
        }
        return super.getMiningSpeedMultiplier(stack, state);
    }
    
    static {
        //EFFECTIVE_BLOCKS = ImmutableSet.<Block>of(Blocks.ACTIVATOR_RAIL, Blocks.COAL_ORE, Blocks.COBBLESTONE, Blocks.DETECTOR_RAIL, Blocks.DIAMOND_BLOCK, Blocks.DIAMOND_ORE, Blocks.POWERED_RAIL, Blocks.GOLD_BLOCK, Blocks.GOLD_ORE, Blocks.NETHER_GOLD_ORE, Blocks.ICE, Blocks.IRON_BLOCK, Blocks.IRON_ORE, Blocks.LAPIS_BLOCK, Blocks.LAPIS_ORE, Blocks.MOSSY_COBBLESTONE, Blocks.NETHERRACK, Blocks.PACKED_ICE, Blocks.BLUE_ICE, Blocks.RAIL, Blocks.REDSTONE_ORE, Blocks.SANDSTONE, Blocks.CHISELED_SANDSTONE, Blocks.CUT_SANDSTONE, Blocks.CHISELED_RED_SANDSTONE, Blocks.CUT_RED_SANDSTONE, Blocks.RED_SANDSTONE, Blocks.STONE, Blocks.GRANITE, Blocks.POLISHED_GRANITE, Blocks.DIORITE, Blocks.POLISHED_DIORITE, Blocks.ANDESITE, Blocks.POLISHED_ANDESITE, Blocks.STONE_SLAB, Blocks.SMOOTH_STONE_SLAB, Blocks.SANDSTONE_SLAB, Blocks.PETRIFIED_OAK_SLAB, Blocks.COBBLESTONE_SLAB, Blocks.BRICK_SLAB, Blocks.STONE_BRICK_SLAB, Blocks.NETHER_BRICK_SLAB, Blocks.QUARTZ_SLAB, Blocks.RED_SANDSTONE_SLAB, Blocks.PURPUR_SLAB, Blocks.SMOOTH_QUARTZ, Blocks.SMOOTH_RED_SANDSTONE, Blocks.SMOOTH_SANDSTONE, Blocks.SMOOTH_STONE, Blocks.STONE_BUTTON, Blocks.STONE_PRESSURE_PLATE, Blocks.POLISHED_GRANITE_SLAB, Blocks.SMOOTH_RED_SANDSTONE_SLAB, Blocks.MOSSY_STONE_BRICK_SLAB, Blocks.POLISHED_DIORITE_SLAB, Blocks.MOSSY_COBBLESTONE_SLAB, Blocks.END_STONE_BRICK_SLAB, Blocks.SMOOTH_SANDSTONE_SLAB, Blocks.SMOOTH_QUARTZ_SLAB, Blocks.GRANITE_SLAB, Blocks.ANDESITE_SLAB, Blocks.RED_NETHER_BRICK_SLAB, Blocks.POLISHED_ANDESITE_SLAB, Blocks.DIORITE_SLAB, Blocks.SHULKER_BOX, Blocks.BLACK_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.LIGHT_GRAY_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.WHITE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX, Blocks.PISTON, Blocks.STICKY_PISTON, Blocks.PISTON_HEAD);
    }

}
