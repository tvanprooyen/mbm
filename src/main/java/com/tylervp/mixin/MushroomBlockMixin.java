package com.tylervp.mixin;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.MushroomBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@Mixin(MushroomBlock.class)
public class MushroomBlockMixin extends Block {

    public MushroomBlockMixin(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack playerItem = player.getStackInHand(hand);
        BlockState ReplaceState = null;

        if(playerItem.getItem() == Items.WOODEN_AXE || playerItem.getItem() == Items.STONE_AXE || playerItem.getItem() == Items.GOLDEN_AXE || playerItem.getItem() == Items.IRON_AXE || playerItem.getItem() == Items.DIAMOND_AXE || playerItem.getItem() == Items.NETHERITE_AXE) {

            switch (hit.getSide()) {
                case NORTH: if(state.get(MushroomBlock.NORTH)) {ReplaceState = state.with(MushroomBlock.NORTH, false).with(MushroomBlock.EAST, state.get(MushroomBlock.EAST)).with(MushroomBlock.SOUTH, state.get(MushroomBlock.SOUTH)).with(MushroomBlock.WEST, state.get(MushroomBlock.WEST)).with(MushroomBlock.UP, state.get(MushroomBlock.UP)).with(MushroomBlock.DOWN, state.get(MushroomBlock.DOWN));}  break;
                case EAST: if(state.get(MushroomBlock.EAST)) {ReplaceState = state.with(MushroomBlock.NORTH, state.get(MushroomBlock.NORTH)).with(MushroomBlock.EAST, false).with(MushroomBlock.SOUTH, state.get(MushroomBlock.SOUTH)).with(MushroomBlock.WEST, state.get(MushroomBlock.WEST)).with(MushroomBlock.UP, state.get(MushroomBlock.UP)).with(MushroomBlock.DOWN, state.get(MushroomBlock.DOWN));}  break;
                case SOUTH: if(state.get(MushroomBlock.SOUTH)) {ReplaceState = state.with(MushroomBlock.NORTH, state.get(MushroomBlock.NORTH)).with(MushroomBlock.EAST, state.get(MushroomBlock.EAST)).with(MushroomBlock.SOUTH, false).with(MushroomBlock.WEST, state.get(MushroomBlock.WEST)).with(MushroomBlock.UP, state.get(MushroomBlock.UP)).with(MushroomBlock.DOWN, state.get(MushroomBlock.DOWN));}  break;
                case WEST: if(state.get(MushroomBlock.WEST)) {ReplaceState = state.with(MushroomBlock.NORTH, state.get(MushroomBlock.NORTH)).with(MushroomBlock.EAST, state.get(MushroomBlock.EAST)).with(MushroomBlock.SOUTH, state.get(MushroomBlock.SOUTH)).with(MushroomBlock.WEST, false).with(MushroomBlock.UP, state.get(MushroomBlock.UP)).with(MushroomBlock.DOWN, state.get(MushroomBlock.DOWN));}  break;
                case UP: if(state.get(MushroomBlock.UP)) {ReplaceState = state.with(MushroomBlock.NORTH, state.get(MushroomBlock.NORTH)).with(MushroomBlock.EAST, state.get(MushroomBlock.EAST)).with(MushroomBlock.SOUTH, state.get(MushroomBlock.SOUTH)).with(MushroomBlock.WEST, state.get(MushroomBlock.WEST)).with(MushroomBlock.UP, false).with(MushroomBlock.DOWN, state.get(MushroomBlock.DOWN));}  break;
                case DOWN: if(state.get(MushroomBlock.DOWN)) {ReplaceState = state.with(MushroomBlock.NORTH, state.get(MushroomBlock.NORTH)).with(MushroomBlock.EAST, state.get(MushroomBlock.EAST)).with(MushroomBlock.SOUTH, state.get(MushroomBlock.SOUTH)).with(MushroomBlock.WEST, state.get(MushroomBlock.WEST)).with(MushroomBlock.UP, state.get(MushroomBlock.UP)).with(MushroomBlock.DOWN, false);}  break;
            }
        }

        if(ReplaceState != null) {
            world.setBlockState(pos, ReplaceState);
            return ActionResult.success(world.isClient);
        } else {
            return ActionResult.PASS;
        }
    }
}
