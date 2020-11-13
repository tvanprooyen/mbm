package com.tylervp.mixin;

import com.tylervp.moreblocksmod;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.GourdBlock;
import net.minecraft.block.MelonBlock;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

@Mixin(MelonBlock.class)
public abstract class MelonBlockMixin extends GourdBlock {
    

    public MelonBlockMixin(AbstractBlock.Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack itemStack8 = player.getStackInHand(hand);

        if (itemStack8.getItem() == Items.SHEARS) {
            if (!world.isClient) {
                Direction direction9 = hit.getSide();
                Direction direction10 = (direction9.getAxis() == Direction.Axis.Y) ? player.getHorizontalFacing().getOpposite() : direction9;
                world.playSound(null, pos, SoundEvents.BLOCK_PUMPKIN_CARVE, SoundCategory.BLOCKS, 1.0f, 1.0f);
                world.setBlockState(pos,  (BlockState)moreblocksmod.CARVEDMELON.getDefaultState().with(moreblocksmod.CARVEDMELON.FACING, player.getHorizontalFacing().getOpposite()), 11);
                ItemEntity itemEntity11 = new ItemEntity(world, pos.getX() + 0.5 + direction10.getOffsetX() * 0.65, pos.getY() + 0.1, pos.getZ() + 0.5 + direction10.getOffsetZ() * 0.65, new ItemStack(Items.MELON_SEEDS, 4));
                itemEntity11.setVelocity(0.05 * direction10.getOffsetX() + world.random.nextDouble() * 0.02, 0.05, 0.05 * direction10.getOffsetZ() + world.random.nextDouble() * 0.02);
                world.spawnEntity(itemEntity11);
                itemStack8.<PlayerEntity>damage(1, player, playerEntity -> playerEntity.sendToolBreakStatus(hand));
            }
            return ActionResult.success(true);
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }
}
