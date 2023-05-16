package com.tylervp.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.tylervp.block.MBMBlocks;

import net.minecraft.block.Blocks;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.SuspiciousStewRecipe;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.world.World;

@Mixin(SuspiciousStewRecipe.class)
public class SuspiciousStewRecipeMixin {

    @Inject(method = "matches(Lnet/minecraft/inventory/CraftingInventory;Lnet/minecraft/world/World;)Z", at = @At("HEAD"), cancellable = true)
    private void matches(CraftingInventory inventory, World world, CallbackInfoReturnable<Boolean> ci) {
        boolean bl = false;
        boolean bl2 = false;
        boolean bl3 = false;
        boolean bl4 = false;
        for (int i = 0; i < inventory.size(); ++i) {
            ItemStack lv = inventory.getStack(i);
            if (lv.isEmpty()) continue;
            if ((lv.isOf(Blocks.BROWN_MUSHROOM.asItem()) || lv.isOf(MBMBlocks.BROWN_MUSHROOM.asItem()) || lv.isOf(MBMBlocks.BROWN_SIDE_MUSHROOM.asItem())) && !bl3) {
                bl3 = true;
                continue;
            }
            if ((lv.isOf(Blocks.RED_MUSHROOM.asItem()) || lv.isOf(MBMBlocks.RED_MUSHROOM.asItem()) || lv.isOf(MBMBlocks.RED_SIDE_MUSHROOM.asItem())) && !bl2) {
                bl2 = true;
                continue;
            }
            if (lv.isIn(ItemTags.SMALL_FLOWERS) && !bl) {
                bl = true;
                continue;
            }
            if (lv.isOf(Items.BOWL) && !bl4) {
                bl4 = true;
                continue;
            }
            ci.setReturnValue(false);
        }
        ci.setReturnValue(bl && bl3 && bl2 && bl4);
    }
}
