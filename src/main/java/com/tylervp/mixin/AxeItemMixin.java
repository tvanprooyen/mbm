package com.tylervp.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PillarBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.Random;

import com.google.common.collect.ImmutableMap;
import com.tylervp.block.MBMBlocks;
import com.tylervp.item.MBMItems;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AxeItem.class)
public class AxeItemMixin {


    @Inject(method = "useOnBlock(Lnet/minecraft/item/ItemUsageContext;)Lnet/minecraft/util/ActionResult;", at = @At("HEAD"), cancellable = true)
    void useOnBlock(ItemUsageContext context,  CallbackInfoReturnable<ActionResult> ci){
        World world3 = context.getWorld();
        BlockPos blockPos4 = context.getBlockPos();
        BlockState blockState5 = world3.getBlockState(blockPos4);
        Block block6 = new ImmutableMap.Builder<Block, Block>().put(Blocks.OAK_WOOD, Blocks.STRIPPED_OAK_WOOD).put(Blocks.OAK_LOG, Blocks.STRIPPED_OAK_LOG).put(Blocks.DARK_OAK_WOOD, Blocks.STRIPPED_DARK_OAK_WOOD).put(Blocks.DARK_OAK_LOG, Blocks.STRIPPED_DARK_OAK_LOG).put(Blocks.ACACIA_WOOD, Blocks.STRIPPED_ACACIA_WOOD).put(Blocks.ACACIA_LOG, Blocks.STRIPPED_ACACIA_LOG).put(Blocks.BIRCH_WOOD, Blocks.STRIPPED_BIRCH_WOOD).put(Blocks.BIRCH_LOG, Blocks.STRIPPED_BIRCH_LOG).put(Blocks.JUNGLE_WOOD, Blocks.STRIPPED_JUNGLE_WOOD).put(Blocks.JUNGLE_LOG, Blocks.STRIPPED_JUNGLE_LOG).put(Blocks.SPRUCE_WOOD, Blocks.STRIPPED_SPRUCE_WOOD).put(Blocks.SPRUCE_LOG, Blocks.STRIPPED_SPRUCE_LOG).put(Blocks.WARPED_STEM, Blocks.STRIPPED_WARPED_STEM).put(Blocks.WARPED_HYPHAE, Blocks.STRIPPED_WARPED_HYPHAE).put(Blocks.CRIMSON_STEM, Blocks.STRIPPED_CRIMSON_STEM).put(Blocks.CRIMSON_HYPHAE, Blocks.STRIPPED_CRIMSON_HYPHAE).build().get(blockState5.getBlock());
        Block iron = new ImmutableMap.Builder<Block, Block>().put(Blocks.IRON_BLOCK, MBMBlocks.IRON_BLOCK).put(MBMBlocks.WAXED_EXPOSED_IRON, MBMBlocks.EXPOSED_IRON).put(MBMBlocks.WAXED_DEGRADED_IRON, MBMBlocks.DEGRADED_IRON).put(MBMBlocks.WAXED_WEATHERED_IRON, MBMBlocks.WEATHERED_IRON).put(MBMBlocks.WAXED_RUSTED_IRON, MBMBlocks.RUSTED_IRON).build().get(blockState5.getBlock());
        Block oxidized = new ImmutableMap.Builder<Block, Block>().put(MBMBlocks.HARDENED_IRON, MBMBlocks.IRON_BLOCK).put(MBMBlocks.EXPOSED_IRON, MBMBlocks.IRON_BLOCK).put(MBMBlocks.DEGRADED_IRON, MBMBlocks.EXPOSED_IRON).put(MBMBlocks.WEATHERED_IRON, MBMBlocks.DEGRADED_IRON).put(MBMBlocks.RUSTED_IRON, MBMBlocks.WEATHERED_IRON).build().get(blockState5.getBlock());


        if (iron != null) {
            PlayerEntity playerEntity7 = context.getPlayer();
            world3.playSound(playerEntity7, blockPos4, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0f, 1.0f);
            if (!world3.isClient) {
                world3.setBlockState(blockPos4, ((BlockState)iron.getDefaultState()), 11);


                if (playerEntity7 != null) {
                    context.getStack().<PlayerEntity>damage(1, playerEntity7, p -> p.sendToolBreakStatus(context.getHand()));
                }
            }
            if (world3.isClient) {
               spawnParticlesStrip(world3, blockPos4);
            }
            ci.setReturnValue(ActionResult.success(world3.isClient));
        } else if (block6 != null) {
            PlayerEntity playerEntity7 = context.getPlayer();
            world3.playSound(playerEntity7, blockPos4, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0f, 1.0f);
            if (!world3.isClient) {
                world3.setBlockState(blockPos4, ((BlockState)block6.getDefaultState()).with(PillarBlock.AXIS, blockState5.get(PillarBlock.AXIS)), 11);

                ItemConvertible bark_item = MBMItems.ACACIA_BARK_FRAGMENT.asItem();
                Boolean pass = false;

                if(blockState5.isOf(Blocks.ACACIA_LOG)) {
                    bark_item = MBMItems.ACACIA_BARK_FRAGMENT.asItem();
                    pass = true;
                } else if(blockState5.isOf(Blocks.BIRCH_LOG)) {
                    bark_item = MBMItems.BIRCH_BARK_FRAGMENT.asItem();
                    pass = true;
                } else if(blockState5.isOf(Blocks.DARK_OAK_LOG)) {
                    bark_item = MBMItems.DARK_OAK_BARK_FRAGMENT.asItem();
                    pass = true;
                } else if(blockState5.isOf(Blocks.JUNGLE_LOG)) {
                    bark_item = MBMItems.JUNGLE_BARK_FRAGMENT.asItem();
                    pass = true;
                } else if(blockState5.isOf(Blocks.OAK_LOG)) {
                    bark_item = MBMItems.OAK_BARK_FRAGMENT.asItem();
                    pass = true;
                } else if(blockState5.isOf(Blocks.SPRUCE_LOG)) {
                    bark_item = MBMItems.SPRUCE_BARK_FRAGMENT.asItem();
                    pass = true;
                }

                if(pass){
                    Block.dropStack(context.getWorld(), blockPos4, new ItemStack(bark_item, 4));
                    if (playerEntity7 != null) {
                        context.getStack().<PlayerEntity>damage(1, playerEntity7, p -> p.sendToolBreakStatus(context.getHand()));
                    }
                }
            }
            ci.setReturnValue(ActionResult.success(world3.isClient));
        } else if (oxidized != null) {
            PlayerEntity playerEntity7 = context.getPlayer();
            world3.playSound(playerEntity7, blockPos4, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0f, 1.0f);
            if (!world3.isClient) {
                world3.setBlockState(blockPos4, ((BlockState)oxidized.getDefaultState()), 11);


                if (playerEntity7 != null) {
                    context.getStack().<PlayerEntity>damage(1, playerEntity7, p -> p.sendToolBreakStatus(context.getHand()));
                }
            }
            if (world3.isClient) {
               spawnParticlesStrip(world3, blockPos4);
            }
            ci.setReturnValue(ActionResult.success(world3.isClient));
        }
        ci.setReturnValue(ActionResult.PASS);
    }

    private static void spawnParticlesStrip(World world, BlockPos pos) {
        for (int i = 0; i < 4; i++) {
            Random random5 = world.random;
            for (final Direction lv : Direction.values()) {
                BlockPos blockPos10 = pos.offset(lv);
                if (!world.getBlockState(blockPos10).isOpaqueFullCube(world, blockPos10)) {
                    Direction.Axis axis11 = lv.getAxis();
                    double double12 = (axis11 == Direction.Axis.X) ? (0.5 + 0.5625 * lv.getOffsetX()) : random5.nextFloat();
                    double double14 = (axis11 == Direction.Axis.Y) ? (0.5 + 0.5625 * lv.getOffsetY()) : random5.nextFloat();
                    double double16 = (axis11 == Direction.Axis.Z) ? (0.5 + 0.5625 * lv.getOffsetZ()) : random5.nextFloat();
                    
                    world.addParticle(ParticleTypes.CRIT, pos.getX() + double12, pos.getY() + double14, pos.getZ() + double16, 0.0, 0.0, 0.0);
                }
            }   
        }
    }
}