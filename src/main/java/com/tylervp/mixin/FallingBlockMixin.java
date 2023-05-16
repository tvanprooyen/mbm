package com.tylervp.mixin;

import org.spongepowered.asm.mixin.Mixin;

import com.tylervp.block.MBMBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FallingBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@Mixin(FallingBlock.class)
public class FallingBlockMixin extends Block {

    public FallingBlockMixin(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(state.isOf(Blocks.SAND)) {
            ItemStack playerItem = player.getStackInHand(hand);
            if(playerItem.getItem() == Items.WATER_BUCKET ) {/* PotionUtil.getPotion(playerItem) == Potions.WATER */
                world.playSound(player, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0f, 1.0f);
                if(!player.isCreative()) {
                    player.setStackInHand(hand, ItemStack.EMPTY);
                    player.giveItemStack(new ItemStack(Items.BUCKET, 1));
                }
                world.setBlockState(pos, (BlockState)MBMBlocks.WET_SAND.getDefaultState());
                return ActionResult.success(world.isClient);
            }
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }
}