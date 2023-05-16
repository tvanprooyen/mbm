package com.tylervp.block.entity;

import com.tylervp.block.MBMBlocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.block.entity.ViewerCountManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.HopperScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class VaseBlockEntity extends LootableContainerBlockEntity {
   private DefaultedList<ItemStack> inventory;
   private final ViewerCountManager stateManager;

   public VaseBlockEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
      super(blockEntityType, blockPos, blockState);
        this.inventory = DefaultedList.<ItemStack>ofSize(5, ItemStack.EMPTY);
        this.stateManager = new ViewerCountManager() {
            @Override
            protected void onContainerOpen(World world, BlockPos pos, BlockState state) {
            }
            
            @Override
            protected void onContainerClose(World world, BlockPos pos, BlockState state) {
            }
            
            @Override
            protected void onViewerCountUpdate(World world, BlockPos pos, BlockState state, int oldViewerCount, int newViewerCount) {
            }
            
            @Override
            protected boolean isPlayerViewing(PlayerEntity player) {
                if (player.currentScreenHandler instanceof GenericContainerScreenHandler) {
                    Inventory inventory3 = ((GenericContainerScreenHandler)player.currentScreenHandler).getInventory();
                    return inventory3 == VaseBlockEntity.this;
                }
                return false;
            }
        };
   }

   public VaseBlockEntity(BlockPos pos, BlockState state) {
      this(MBMBlocks.VASE_BLOCK_ENTITY, pos, state);
   }

   /* public NbtCompound writeNbt(NbtCompound tag) {
      super.writeNbt(tag);
      if (!this.serializeLootTable(tag)) {
         Inventories.writeNbt(tag, this.inventory);
      }

      return tag;
   } */

   @Override
    public void writeNbt(NbtCompound tag) {
        if (!this.serializeLootTable(tag)) {
            Inventories.writeNbt(tag, this.inventory);
         }
 
        super.writeNbt(tag);
    }

   @Override
   public NbtCompound toInitialChunkDataNbt() {
      return createNbt();
   }

   public void readNbt(NbtCompound nbt) {
      super.readNbt(nbt);
      this.inventory = DefaultedList.ofSize(this.size(), ItemStack.EMPTY);
      if (!this.deserializeLootTable(nbt)) {
         Inventories.readNbt(nbt, this.inventory);
      }

   }

   public int size() {
      return 5;
   }

   protected DefaultedList<ItemStack> getInvStackList() {
      return this.inventory;
   }

   protected void setInvStackList(DefaultedList<ItemStack> list) {
      this.inventory = list;
   }

   protected Text getContainerName() {
      return Text.translatable("Vase");
   }

   protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
      return new HopperScreenHandler(syncId, playerInventory, this);
   }

   @Override
    public void onOpen(PlayerEntity player) {
        if (!this.removed && !player.isSpectator()) {
            this.stateManager.openContainer(player, this.getWorld(), this.getPos(), this.getCachedState());
        }
    }

   /* private void scheduleUpdate() {
      this.world.getBlockTickScheduler().schedule(this.getPos(), this.getCachedState().getBlock(), 5);
   } */

   public static int getPlayersLookingInChestCount(BlockView world, BlockPos pos) {
      BlockState blockState = world.getBlockState(pos);
      if (blockState.hasBlockEntity()) {
         BlockEntity blockEntity = world.getBlockEntity(pos);
         if (blockEntity instanceof VaseBlockEntity) {
            return ((VaseBlockEntity)blockEntity).stateManager.getViewerCount();
         }
      }
      return 0;
   }

   public void tick() {
      //this.viewerCount = VaseBlockEntity.getPlayersLookingInChestCount(this.world, this.pos);
      if (!this.removed) {
         this.stateManager.updateViewerCount(this.getWorld(), this.getPos(), this.getCachedState());
     }
      /* if (this.viewerCount > 0) {
         this.scheduleUpdate();
      } else {
         BlockState blockState = this.getCachedState();
         if (!blockState.isOf(blockState.getBlock())) {
            this.markRemoved();
            return;
         }
      } */
   }
   
   @Override
   public void onClose(PlayerEntity player) {
       if (!this.removed && !player.isSpectator()) {
           this.stateManager.closeContainer(player, this.getWorld(), this.getPos(), this.getCachedState());
       }
   }

   /* public void onClose(PlayerEntity player) {
      if (!player.isSpectator()) {
         --this.viewerCount;
      }
   } */

   //private void setOpen(BlockState state, boolean open) {
      //this.world.setBlockState(this.getPos(), (BlockState)state.with(BarrelBlock.OPEN, open), 3);
   //}

   //private void playSound(BlockState blockState, SoundEvent soundEvent) {
      /* Vec3i vec3i = ((Direction)blockState.get(BarrelBlock.FACING)).getVector();
      double d = (double)this.pos.getX() + 0.5D + (double)vec3i.getX() / 2.0D;
      double e = (double)this.pos.getY() + 0.5D + (double)vec3i.getY() / 2.0D;
      double f = (double)this.pos.getZ() + 0.5D + (double)vec3i.getZ() / 2.0D;
      this.world.playSound((PlayerEntity)null, d, e, f, soundEvent, SoundCategory.BLOCKS, 0.5F, this.world.random.nextFloat() * 0.1F + 0.9F); */
   //}
}
