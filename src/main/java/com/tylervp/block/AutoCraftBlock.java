package com.tylervp.block;

import org.jetbrains.annotations.Nullable;

import com.tylervp.block.enums.ItemList;
import com.tylervp.item.MBMItems;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.InventoryProvider;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.HopperBlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

 public class AutoCraftBlock extends Block implements InventoryProvider {
     public static final IntProperty LEVEL;
     public static final IntProperty FULL_LEVEL;
     public static final DirectionProperty FACING;
     //public static final BooleanProperty EMPTY;
     //public static final EnumProperty<ItemList> SELECTEDITEM;
     public static final EnumProperty<ItemList> SELECTEDITEM;
     public static final Object2IntMap<ItemConvertible> INPUT_ITEM = new Object2IntOpenHashMap<>();
     public static final Object2IntMap<ItemConvertible> OUTPUT_ITEM = new Object2IntOpenHashMap<>();
     public static final Object2ObjectMap<ItemConvertible, ItemConvertible> ITEM_EXCHANGE = new Object2ObjectOpenHashMap<>();
     public static final Object2ObjectMap<ItemConvertible, ItemConvertible> ITEM_EXCHANGE_OPP = new Object2ObjectOpenHashMap<>();
     public static final Object2ObjectMap<ItemConvertible, ItemList> ITEM_LIST = new Object2ObjectOpenHashMap<>();
 
     public static void registerDefaultItems() {
        int fullGridAmount = 9;

        //Default Values
        INPUT_ITEM.defaultReturnValue(0);
        OUTPUT_ITEM.defaultReturnValue(0);
        ITEM_EXCHANGE.defaultReturnValue(Items.AIR);
        ITEM_EXCHANGE_OPP.defaultReturnValue(Items.AIR);

         //Register
         AutoCraftBlock.registerItems(Items.RAW_IRON, fullGridAmount, Items.RAW_IRON_BLOCK, 1);
         AutoCraftBlock.registerItems(Items.IRON_INGOT, fullGridAmount, Items.IRON_BLOCK, 1);
         AutoCraftBlock.registerItems(Items.IRON_NUGGET, fullGridAmount, Items.IRON_INGOT, 1);
         AutoCraftBlock.registerItems(Items.RAW_GOLD, fullGridAmount, Items.RAW_GOLD_BLOCK, 1);
         AutoCraftBlock.registerItems(Items.GOLD_INGOT, fullGridAmount, Items.GOLD_BLOCK, 1);
         AutoCraftBlock.registerItems(Items.GOLD_NUGGET, fullGridAmount, Items.GOLD_INGOT, 1);
         AutoCraftBlock.registerItems(Items.RAW_COPPER, fullGridAmount, Items.RAW_COPPER_BLOCK, 1);
         AutoCraftBlock.registerItems(Items.COPPER_INGOT, fullGridAmount, Items.COPPER_BLOCK, 1);
         AutoCraftBlock.registerItems(Items.DIAMOND, fullGridAmount, Items.DIAMOND_BLOCK, 1);
         AutoCraftBlock.registerItems(Items.LAPIS_LAZULI, fullGridAmount, Items.LAPIS_BLOCK, 1);
         AutoCraftBlock.registerItems(Items.COAL, fullGridAmount, Items.COAL_BLOCK, 1);
         AutoCraftBlock.registerItems(Items.QUARTZ, 4, Items.QUARTZ_BLOCK, 1);
         AutoCraftBlock.registerItems(Items.NETHERITE_INGOT, fullGridAmount, Items.NETHERITE_BLOCK, 1);
         AutoCraftBlock.registerItems(Items.EMERALD, fullGridAmount, Items.EMERALD_BLOCK, 1);
         AutoCraftBlock.registerItems(Items.STRING, 4, Items.WHITE_WOOL, 1);
         AutoCraftBlock.registerItems(Items.SLIME_BALL, fullGridAmount, Items.SLIME_BLOCK, 1);
         AutoCraftBlock.registerItems(Items.LEATHER, fullGridAmount, MBMItems.LEATHER_BLOCK, 1);
         AutoCraftBlock.registerItems(Items.BAMBOO, 2, Items.STICK, 1);
         AutoCraftBlock.registerItems(Items.REDSTONE, fullGridAmount, Items.REDSTONE_BLOCK, 1);
         AutoCraftBlock.registerItems(Items.BONE_MEAL, fullGridAmount, Items.BONE_BLOCK, 1);
         AutoCraftBlock.registerItems(Items.STICK, fullGridAmount, MBMItems.STICK_BUNDLE, 1);
         /* AutoCraftBlock.registerItems(Items.STICK, fullGridAmount, MBMItems.STICK_BUNDLE, 1); */

     }
 
     //ItemConvertible IputItem, ItemConvertible OutputItem, float InAmount, float OutAmount
     private static void registerItems(ItemConvertible InputItem, int InAmount, ItemConvertible OutputItem, int OutAmount) {
        INPUT_ITEM.put(InputItem.asItem(), InAmount);
        OUTPUT_ITEM.put(OutputItem.asItem(), OutAmount);
        ITEM_EXCHANGE.put(InputItem.asItem(), OutputItem.asItem());
        ITEM_EXCHANGE_OPP.put(OutputItem.asItem(), InputItem.asItem());
     }

     public static void registerItemList() {
        ITEM_LIST.defaultReturnValue(ItemList.NONE);

        for (ItemList il : ItemList.values()) {
            AutoCraftBlock.registerItemList(il.toItem(), il);
        }
     }

     private static void registerItemList(ItemConvertible InputItem, ItemList ListedItem) {
        ITEM_LIST.put(InputItem.asItem(), ListedItem);
     }
 
     public AutoCraftBlock(AbstractBlock.Settings settings) {
         super(settings);
         this.setDefaultState((BlockState)((BlockState)this.stateManager.getDefaultState()).with(LEVEL, 0).with(FULL_LEVEL, 0).with(SELECTEDITEM, ItemList.NONE).with(FACING, Direction.NORTH)/* .with(EMPTY, false) */);
     }
 
     public static void playEffects(World world, BlockPos pos, boolean fill) {
         BlockState lv = world.getBlockState(pos);
         world.playSound(pos.getX(), pos.getY(), pos.getZ(), fill ? SoundEvents.BLOCK_NETHERITE_BLOCK_PLACE : SoundEvents.BLOCK_NETHER_ORE_BREAK, SoundCategory.BLOCKS, 1.0f, 1.0f, false);
         double d = lv.getOutlineShape(world, pos).getEndingCoord(Direction.Axis.Y, 0.5, 0.5) + 0.03125;
         double e = 0.13125f;
         double f = 0.7375f;
         Random lv2 = world.getRandom();
         for (int i = 0; i < 10; ++i) {
             double g = lv2.nextGaussian() * 0.02;
             double h = lv2.nextGaussian() * 0.02;
             double j = lv2.nextGaussian() * 0.02;
             world.addParticle(ParticleTypes.ASH, (double)pos.getX() + (double)e + (double)f * (double)lv2.nextFloat(), (double)pos.getY() + d + (double)lv2.nextFloat() * (1.0 - d), (double)pos.getZ() + (double)e + (double)f * (double)lv2.nextFloat(), g, h, j);
         }
     }

     @Override
     public BlockState getPlacementState(ItemPlacementContext ctx) {
        Direction PlayerDirection = ctx.getHorizontalPlayerFacing().getOpposite();
        if(PlayerDirection == Direction.WEST) {
            PlayerDirection = Direction.EAST;
        } else if(PlayerDirection == Direction.SOUTH) {
            PlayerDirection = Direction.NORTH;
        }
         return (BlockState)this.getDefaultState().with(FACING, PlayerDirection);
     }
 
     @Override
     public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
         return VoxelShapes.fullCube();
     }
 
     @Override
     public VoxelShape getRaycastShape(BlockState state, BlockView world, BlockPos pos) {
         return VoxelShapes.fullCube();
     }
 
     @Override
     public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
         return VoxelShapes.fullCube();
     }
 
     @Override
     public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
         if (state.get(LEVEL) == 1) {
            world.scheduleBlockTick(pos, state.getBlock(), 20);
         }
     }
 
     @Override
     public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
         int level = state.get(LEVEL);
         ItemStack handItem = player.getStackInHand(hand);
         int fullLevel = state.get(FULL_LEVEL);
         ItemList selectedItem =  state.get(SELECTEDITEM);

         //Check full level and make sure the item in hand is acceptable
         if(fullLevel == 0 && ITEM_EXCHANGE.containsKey((Object)handItem.getItem())) {
            fullLevel = INPUT_ITEM.getInt(handItem.getItem()) + 1;
            selectedItem = ITEM_LIST.get(ITEM_EXCHANGE.get((Object)handItem.getItem()));
        } else if(fullLevel == 0) {
            return ActionResult.PASS;
        }

         if(selectedItem == ITEM_LIST.get(ITEM_EXCHANGE.get((Object)handItem.getItem()))) {
            if (level < fullLevel) {
                if (level < (fullLevel - 1) && !world.isClient) {
                    BlockState lv2 = AutoCraftBlock.addToComposter(state, world, pos, handItem, level, fullLevel, selectedItem);
                    //world.syncWorldEvent(1500, pos, state != lv2 ? 1 : 0);
                    playEffects(world, pos, true);
                    player.incrementStat(Stats.USED.getOrCreateStat(handItem.getItem()));
                    if (!player.getAbilities().creativeMode) {
                        handItem.decrement(1);
                    }
                }
                return ActionResult.success(world.isClient);
            }
         }

         if (level == fullLevel) {
            AutoCraftBlock.emptyFullComposter(state, world, pos);
            return ActionResult.success(world.isClient);
        }

         return ActionResult.PASS;
     }

     @Override
     public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if(state.get(FULL_LEVEL) != 0) {
            if (!world.isClient) {
                float f = 0.7f;
                double d = (double)(world.random.nextFloat() * f) + (double)0.15f;
                double e = (double)(world.random.nextFloat() * f) + 0.06000000238418579 + 0.5;
                double g = (double)(world.random.nextFloat() * f) + (double)0.15f;

                int returnAmount = state.get(LEVEL);

                if(state.get(FULL_LEVEL) == state.get(LEVEL)) {
                    returnAmount -= 1;
                }

                ItemEntity lv = new ItemEntity(world, (double)pos.getX() + d, (double)pos.getY() + e, (double)pos.getZ() + g, new ItemStack(ITEM_EXCHANGE_OPP.get(state.get(SELECTEDITEM).toItem()).asItem(), returnAmount));
                lv.setToDefaultPickupDelay();
                world.spawnEntity(lv);
            }
        }
        
         super.onBreak(world, pos, state, player);
     }

    /*  @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        this.updateEnabled(world, pos, state);
    }

    private void updateEnabled(World world, BlockPos pos, BlockState state) {
        boolean bl = !world.isReceivingRedstonePower(pos);
        if (bl != state.get(EMPTY)) {
            world.setBlockState(pos, (BlockState)state.with(EMPTY, bl), Block.NO_REDRAW);
        }
    } */
 
     public static BlockState compost(BlockState state, ServerWorld world, ItemStack stack, BlockPos pos) {
         int level = state.get(LEVEL);
         int fullLevel = state.get(FULL_LEVEL);
         ItemList selectedItem =  state.get(SELECTEDITEM);

         if (level < (fullLevel - 1) && fullLevel != 0) {
             BlockState lv = AutoCraftBlock.addToComposter(state, world, pos, stack, level, fullLevel, selectedItem);
             stack.decrement(1);
             return lv;
         }
         return state;
     }
 
     public static BlockState emptyFullComposter(BlockState state, World world, BlockPos pos) {
         if (!world.isClient) {
             float f = 0.7f;
             double d = (double)(world.random.nextFloat() * f) + (double)0.15f;
             double e = (double)(world.random.nextFloat() * f) + 0.06000000238418579 + 0.9;
             double g = (double)(world.random.nextFloat() * f) + (double)0.15f;
             ItemEntity lv = new ItemEntity(world, (double)pos.getX() + d, (double)pos.getY() + e, (double)pos.getZ() + g, new ItemStack(state.get(SELECTEDITEM).toItem().asItem(), OUTPUT_ITEM.getInt(state.get(SELECTEDITEM).toItem())));
             lv.setToDefaultPickupDelay();
             world.spawnEntity(lv);
         }
         BlockState lv2 = AutoCraftBlock.emptyComposter(state, world, pos);
         world.playSound(null, pos, SoundEvents.BLOCK_COMPOSTER_EMPTY, SoundCategory.BLOCKS, 1.0f, 1.0f);
         return lv2;
     }
 
     static BlockState emptyComposter(BlockState state, WorldAccess world, BlockPos pos) {
         BlockState lv = (BlockState)state.with(LEVEL, 0).with(FULL_LEVEL, 0).with(SELECTEDITEM, ItemList.NONE);
         world.setBlockState(pos, lv, 3);
         return lv;
     }
 
     static BlockState addToComposter(BlockState state, WorldAccess world, BlockPos pos, ItemStack item, int level, int fullLevel, ItemList selectedItem) {
         if (level < fullLevel) {
             int futureLevel = level + 1;
             BlockState lv = (BlockState)state.with(LEVEL, futureLevel).with(FULL_LEVEL, fullLevel).with(SELECTEDITEM, selectedItem);
             world.setBlockState(pos, lv, 3);
             if (futureLevel == (state.get(FULL_LEVEL) - 1)) {
                 world.scheduleBlockTick(pos, state.getBlock(), 20);
             }
             return lv;
         }
         return state;
     }
 
     @Override
     public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
         if (state.get(LEVEL) == (state.get(FULL_LEVEL) - 1)) {
             world.setBlockState(pos, (BlockState)state.cycle(LEVEL), 3);
             world.playSound(null, pos, SoundEvents.BLOCK_COMPOSTER_READY, SoundCategory.BLOCKS, 1.0f, 1.0f);
         }
     }
 
     @Override
     public boolean hasComparatorOutput(BlockState state) {
         return true;
     }
 
     @Override
     public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
         //return (int)(Math.ceil(state.get(LEVEL) / state.get(FULL_LEVEL)));
         return state.get(LEVEL);
     }
 
     @Override
     protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
         builder.add(LEVEL, FULL_LEVEL, SELECTEDITEM, FACING/* , EMPTY */);
     }
 
     @Override
     public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
         return false;
     }

     private boolean IsHopperEmpty(BlockState state, WorldAccess world, BlockPos pos) {
        boolean pass = false;
        if(world.getBlockState(pos.down()).isOf(Blocks.HOPPER)) {
            HopperBlockEntity bEntity = (HopperBlockEntity)world.getBlockEntity(pos.down());
            for (int index = 0; index < 5; index++) {
                if(bEntity.getStack(index).isOf(Items.AIR)) {
                    pass = true;
                    break;
                } if(bEntity.getStack(index).isOf(state.get(SELECTEDITEM).toItem().asItem())) {
                    if(bEntity.getStack(index).getCount() < state.get(SELECTEDITEM).toItem().asItem().getMaxCount()) {
                        pass = true;
                        break;
                    }
                }
            }
        }

        return pass;
     }
 
     @Override
     public SidedInventory getInventory(BlockState state, WorldAccess world, BlockPos pos) {
         int level = state.get(LEVEL);
         int fullLevel = state.get(FULL_LEVEL);

         if (level == fullLevel && fullLevel != 0) {
            if(IsHopperEmpty(state, world, pos)) {
                /* if(this.FULL_INVENTORY == null) {
                    this.FULL_INVENTORY = new AutoCraftBlock.FullComposterInventory(state, world, pos, new ItemStack(state.get(SELECTEDITEM).toItem(), OUTPUT_ITEM.getInt(state.get(SELECTEDITEM).toItem())));
                }

                if(this.FULL_INVENTORY.getStack(0).getCount() == 0) {
                    this.FULL_INVENTORY = null;
                } else {
                    return this.FULL_INVENTORY;
                } */
                //this.FULL_INVENTORY = new AutoCraftBlock.FullComposterInventory(state, world, pos, new ItemStack(state.get(SELECTEDITEM).toItem(), OUTPUT_ITEM.getInt(state.get(SELECTEDITEM).toItem())));
                return new AutoCraftBlock.FullComposterInventory(state, world, pos, new ItemStack(state.get(SELECTEDITEM).toItem(), OUTPUT_ITEM.getInt(state.get(SELECTEDITEM).toItem())));
            }
         }
         if (level < (fullLevel - 1) || fullLevel == 0) {
             return new AutoCraftBlock.ComposterInventory(state, world, pos);
         }
         return new AutoCraftBlock.DummyInventory();
     }
 
     static class FullComposterInventory extends SimpleInventory implements SidedInventory {
         private final BlockState state;
         private final WorldAccess world;
         private final BlockPos pos;
         private boolean dirty;
 
         public FullComposterInventory(BlockState state, WorldAccess world, BlockPos pos, ItemStack outputItem) {
             super(outputItem);
             this.state = state;
             this.world = world;
             this.pos = pos;
         }
 
         @Override
         public int getMaxCountPerStack() {
             return 1;
         }

         @Override
         public int[] getAvailableSlots(Direction side) {
             int[] nArray;
             if (side == Direction.DOWN) {
                 int[] nArray2 = new int[1];
                 nArray = nArray2;
                 nArray2[0] = 0;
             } else {
                 nArray = new int[]{};
             }
             return nArray;
         }

         /* public ItemStack getUseingStack() {
            return this.getStack(0);
         } */

         @Override
         public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {
             return false;
         }

         @Override
         public boolean canExtract(int slot, ItemStack stack, Direction dir) {
            //System.out.println(stack.getCount());
             return !this.dirty && dir == Direction.DOWN && stack.isOf((this.state.get(SELECTEDITEM).toItem().asItem()));
         }

         @Override
         public void markDirty() {
            /* System.out.println(this.getStack(0).getCount());
            if(this.getStack(0).getCount() == 1) {
                AutoCraftBlock.emptyComposter(this.state, this.world, this.pos);
                this.dirty = true;
            } else {
                this.getStack(0).decrement(1);
            } */

            AutoCraftBlock.emptyComposter(this.state, this.world, this.pos);
            this.dirty = true;
         }
     }

     static class ComposterInventory extends SimpleInventory implements SidedInventory {
         private final BlockState state;
         private final WorldAccess world;
         private final BlockPos pos;
         private boolean dirty;

         public ComposterInventory(BlockState state, WorldAccess world, BlockPos pos) {
             super(1);
             this.state = state;
             this.world = world;
             this.pos = pos;
         }
 
         @Override
         public int getMaxCountPerStack() {
             return 1;
         }
 
         @Override
         public int[] getAvailableSlots(Direction side) {
             int[] nArray;
             if (side == Direction.UP) {
                 int[] nArray2 = new int[1];
                 nArray = nArray2;
                 nArray2[0] = 0;
             } else {
                 nArray = new int[]{};
             }
             return nArray;
         }
 
         @Override
         public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {
             return !this.dirty && dir == Direction.UP && ((this.state.get(FULL_LEVEL) == 0 && INPUT_ITEM.containsKey(stack.getItem())) || (this.state.get(FULL_LEVEL) > 0 && this.state.get(SELECTEDITEM) == ITEM_LIST.get(ITEM_EXCHANGE.get((Object)stack.getItem())))) /*  && this.state.get(FULL_LEVEL) != 0 */;
         }
 
         @Override
         public boolean canExtract(int slot, ItemStack stack, Direction dir) {
             return false;
         }
 
         @Override
         public void markDirty() {
             ItemStack lv = this.getStack(0);
             if (!lv.isEmpty()) {
                this.dirty = true;
                int fullLevel = state.get(FULL_LEVEL);
                ItemList selectedItem =  state.get(SELECTEDITEM);

                 if(this.state.get(AutoCraftBlock.FULL_LEVEL) == 0 && INPUT_ITEM.containsKey(lv.getItem())) {
                    fullLevel = INPUT_ITEM.getInt(lv.getItem()) + 1;
                    selectedItem = ITEM_LIST.get(ITEM_EXCHANGE.get((Object)lv.getItem()));
                } else {
                    fullLevel = this.state.get(AutoCraftBlock.FULL_LEVEL);
                    selectedItem = this.state.get(AutoCraftBlock.SELECTEDITEM);
                }
                 BlockState lv2 = AutoCraftBlock.addToComposter(this.state, this.world, this.pos, lv, this.state.get(AutoCraftBlock.LEVEL), fullLevel, selectedItem);
                 //this.world.syncWorldEvent(1500, this.pos, lv2 != this.state ? 1 : 0);
                 playEffects((World)world, pos, true);
                 this.removeStack(0);
             }
         }
     }
 
     static class DummyInventory extends SimpleInventory implements SidedInventory {
         public DummyInventory() {
             super(0);
         }
 
         @Override
         public int[] getAvailableSlots(Direction side) {
             return new int[0];
         }
 
         @Override
         public boolean canInsert(int slot, ItemStack stack, @Nullable Direction dir) {
             return false;
         }

         /* ItemStack FirstSlotStack() {
            return this.getStack(0);
         } */
 
         @Override
         public boolean canExtract(int slot, ItemStack stack, Direction dir) {
             return false;
         }
     }

     static {
        LEVEL = IntProperty.of("level", 0, 10);
        FULL_LEVEL = IntProperty.of("fulllevel", 0, 10);
        SELECTEDITEM = EnumProperty.of("selecteditem", ItemList.class);
        FACING = DirectionProperty.of("facing", new Direction[]{Direction.NORTH, Direction.EAST});
        //EMPTY = BooleanProperty.of("empty");
     }
 }