package com.tylervp.block.entity;

import com.tylervp.block.MBMBlocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.screen.HopperScreenHandler;
import net.minecraft.screen.ScreenHandler;
//import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.collection.DefaultedList;

public class VaseBlockEntity extends LootableContainerBlockEntity {
   private DefaultedList<ItemStack> inventory;
   private int viewerCount;

   private VaseBlockEntity(BlockEntityType<?> type) {
      super(type);
      this.inventory = DefaultedList.ofSize(5, ItemStack.EMPTY);
   }

   public VaseBlockEntity() {
         this(MBMBlocks.TERRACOTTA_VASE_BLOCK_ENTITY);
   }

   public CompoundTag toTag(CompoundTag tag) {
      super.toTag(tag);
      if (!this.serializeLootTable(tag)) {
         Inventories.toTag(tag, this.inventory);
      }

      return tag;
   }

   public void fromTag(BlockState state, CompoundTag tag) {
      super.fromTag(state, tag);
      this.inventory = DefaultedList.ofSize(this.size(), ItemStack.EMPTY);
      if (!this.deserializeLootTable(tag)) {
         Inventories.fromTag(tag, this.inventory);
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
      return new TranslatableText("Vase");
   }

   protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
      return new HopperScreenHandler(syncId, playerInventory, this);
   }

   public void onOpen(PlayerEntity player) {
      if (!player.isSpectator()) {
         if (this.viewerCount < 0) {
            this.viewerCount = 0;
         }

         ++this.viewerCount;
         //BlockState blockState = this.getCachedState();
         /* boolean bl = (Boolean)blockState.get(BarrelBlock.OPEN);
         if (!bl) {
            this.playSound(blockState, SoundEvents.BLOCK_BARREL_OPEN);
            this.setOpen(blockState, true);
         } */

         this.scheduleUpdate();
      }

   }

   private void scheduleUpdate() {
      this.world.getBlockTickScheduler().schedule(this.getPos(), this.getCachedState().getBlock(), 5);
   }

   public void tick() {
      int i = this.pos.getX();
      int j = this.pos.getY();
      int k = this.pos.getZ();
      this.viewerCount = ChestBlockEntity.countViewers(this.world, this, i, j, k);
      if (this.viewerCount > 0) {
         this.scheduleUpdate();
      } else {
         BlockState blockState = this.getCachedState();
         if (!blockState.isOf(MBMBlocks.TERRACOTTA_VASE)) {
            this.markRemoved();
            return;
         }

         /* boolean bl = (Boolean)blockState.get(BarrelBlock.OPEN);
         if (bl) {
            this.playSound(blockState, SoundEvents.BLOCK_BARREL_CLOSE);
            this.setOpen(blockState, false);
         } */
      }

   }

   public void onClose(PlayerEntity player) {
      if (!player.isSpectator()) {
         --this.viewerCount;
      }

   }

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
