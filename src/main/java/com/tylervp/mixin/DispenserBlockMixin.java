package com.tylervp.mixin;

import java.util.Map;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.block.DispenserBlock;
import net.minecraft.block.PlantBlock;
import net.minecraft.block.PropaguleBlock;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.block.entity.DispenserBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPointerImpl;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldEvents;
import net.minecraft.world.event.GameEvent;

import com.tylervp.block.MBMBlocks;
import com.tylervp.block.TreeFruitBlock;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropBlock;
import net.minecraft.block.dispenser.FallibleItemDispenserBehavior;
import net.minecraft.util.math.BlockPointer;

@Mixin(DispenserBlock.class)
public class DispenserBlockMixin {

    @Inject(method = "dispense(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;)V", at = @At("HEAD"), cancellable = true)
    private void dispense(ServerWorld world, BlockPos pos, CallbackInfo ci) {
        BlockPointerImpl blockPointerImpl = new BlockPointerImpl(world, pos);
        DispenserBlockEntity dispenserBlockEntity = (DispenserBlockEntity)blockPointerImpl.getBlockEntity();
        int i = dispenserBlockEntity.chooseNonEmptySlot(world.random);
        if (i < 0) {
            world.syncWorldEvent(WorldEvents.DISPENSER_FAILS, pos, 0);
            world.emitGameEvent(null, GameEvent.DISPENSE_FAIL, pos);
            return;
        }
        ItemStack itemStack = dispenserBlockEntity.getStack(i);

        DispenserBehavior dispenserBehavior = getDispenserBehavor(itemStack);

        if (dispenserBehavior != null) {
            dispenserBlockEntity.setStack(i, dispenserBehavior.dispense(blockPointerImpl, itemStack));
            ci.cancel();
        }
    }

    private DispenserBehavior getDispenserBehavor(ItemStack itemStack) {
        DispenserBehavior dispenserBehavior = null;

        if(itemStack.isOf(MBMBlocks.PINECONE.asItem())) {
            dispenserBehavior = new FallibleItemDispenserBehavior(){
                @Override
                protected ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
                    ServerWorld world = pointer.getWorld();
                    BlockPos blockPos = pointer.getPos().offset(pointer.getBlockState().get(DispenserBlock.FACING));
                    TreeFruitBlock treeFruiltBlock = (TreeFruitBlock)MBMBlocks.PINECONE;
                    if (world.isAir(blockPos) /* && carvedPumpkinBlock.canDispense(world, blockPos) */) {
                        if (!world.isClient) {
                            BlockState replacedState = treeFruiltBlock.getDefaultState();
                            if(!world.getBlockState(blockPos.up()).isOf(Blocks.AIR)) {
                                replacedState = replacedState.with(TreeFruitBlock.UP, true);
                            }
                            world.setBlockState(blockPos, replacedState, Block.NOTIFY_ALL);
                            world.emitGameEvent(null, GameEvent.BLOCK_PLACE, blockPos);
                        }
                        stack.decrement(1);
                        this.setSuccess(true);
                    }
                    return stack;
                }
            };
        } else if(itemStack.isOf(MBMBlocks.OAK_ACORN.asItem())) {
            dispenserBehavior = new FallibleItemDispenserBehavior(){
                @Override
                protected ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
                    ServerWorld world = pointer.getWorld();
                    BlockPos blockPos = pointer.getPos().offset(pointer.getBlockState().get(DispenserBlock.FACING));
                    TreeFruitBlock treeFruiltBlock = (TreeFruitBlock)MBMBlocks.OAK_ACORN;
                    if (world.isAir(blockPos) /* && carvedPumpkinBlock.canDispense(world, blockPos) */) {
                        if (!world.isClient) {
                            BlockState replacedState = treeFruiltBlock.getDefaultState();
                            if(!world.getBlockState(blockPos.up()).isOf(Blocks.AIR)) {
                                replacedState = replacedState.with(TreeFruitBlock.UP, true);
                            }
                            world.setBlockState(blockPos, replacedState, Block.NOTIFY_ALL);
                            world.emitGameEvent(null, GameEvent.BLOCK_PLACE, blockPos);
                        }
                        stack.decrement(1);
                        this.setSuccess(true);
                    }
                    return stack;
                }
            };
        } else if(itemStack.isOf(MBMBlocks.DARK_OAK_ACORN.asItem())) {
            dispenserBehavior = new FallibleItemDispenserBehavior(){
                @Override
                protected ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
                    ServerWorld world = pointer.getWorld();
                    BlockPos blockPos = pointer.getPos().offset(pointer.getBlockState().get(DispenserBlock.FACING));
                    TreeFruitBlock treeFruiltBlock = (TreeFruitBlock)MBMBlocks.DARK_OAK_ACORN;
                    if (world.isAir(blockPos) /* && carvedPumpkinBlock.canDispense(world, blockPos) */) {
                        if (!world.isClient) {
                            BlockState replacedState = treeFruiltBlock.getDefaultState();
                            if(!world.getBlockState(blockPos.up()).isOf(Blocks.AIR)) {
                                replacedState = replacedState.with(TreeFruitBlock.UP, true);
                            }
                            world.setBlockState(blockPos, replacedState, Block.NOTIFY_ALL);
                            world.emitGameEvent(null, GameEvent.BLOCK_PLACE, blockPos);
                        }
                        stack.decrement(1);
                        this.setSuccess(true);
                    }
                    return stack;
                }
            };
        } else if(itemStack.isOf(MBMBlocks.BIRCH_CATKIN.asItem())) {
            dispenserBehavior = new FallibleItemDispenserBehavior(){
                @Override
                protected ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
                    ServerWorld world = pointer.getWorld();
                    BlockPos blockPos = pointer.getPos().offset(pointer.getBlockState().get(DispenserBlock.FACING));
                    TreeFruitBlock pineconeBlock = (TreeFruitBlock)MBMBlocks.BIRCH_CATKIN;
                    if (world.isAir(blockPos) /* && carvedPumpkinBlock.canDispense(world, blockPos) */) {
                        if (!world.isClient) {
                            world.setBlockState(blockPos, pineconeBlock.getDefaultState(), Block.NOTIFY_ALL);
                            world.emitGameEvent(null, GameEvent.BLOCK_PLACE, blockPos);
                        }
                        stack.decrement(1);
                        this.setSuccess(true);
                    }
                    return stack;
                }
            };
        } else if(
                itemStack.isOf(Items.OAK_SAPLING.asItem()) ||
                itemStack.isOf(Items.SPRUCE_SAPLING.asItem()) ||
                itemStack.isOf(Items.DARK_OAK_SAPLING.asItem()) ||
                itemStack.isOf(Items.JUNGLE_SAPLING.asItem()) ||
                itemStack.isOf(Items.ACACIA_SAPLING.asItem()) ||
                itemStack.isOf(Items.BIRCH_SAPLING.asItem()) ||
                itemStack.isOf(Items.MANGROVE_PROPAGULE.asItem())
            ) {
            dispenserBehavior = new FallibleItemDispenserBehavior(){
                @Override
                protected ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
                    BlockPos blockPos = pointer.getPos().offset(pointer.getBlockState().get(DispenserBlock.FACING));

                    if(pointer.getBlockState().get(DispenserBlock.FACING) == Direction.UP) {
                        blockPos = pointer.getPos().offset(pointer.getBlockState().get(DispenserBlock.FACING)).up();
                    }

                    ServerWorld world = pointer.getWorld();
                    Block placeBlock = (Block)Blocks.OAK_SAPLING;

                    if(itemStack.isOf(Items.SPRUCE_SAPLING.asItem())) {
                        placeBlock = (SaplingBlock)Blocks.SPRUCE_SAPLING;
                    } else if(itemStack.isOf(Items.DARK_OAK_SAPLING.asItem())) {
                        placeBlock = (SaplingBlock)Blocks.DARK_OAK_SAPLING;
                    } else if(itemStack.isOf(Items.JUNGLE_SAPLING.asItem())) {
                        placeBlock = (SaplingBlock)Blocks.JUNGLE_SAPLING;
                    } else if(itemStack.isOf(Items.ACACIA_SAPLING.asItem())) {
                        placeBlock = (SaplingBlock)Blocks.ACACIA_SAPLING;
                    } else if(itemStack.isOf(Items.MANGROVE_PROPAGULE.asItem())) {
                        placeBlock = (PropaguleBlock)Blocks.MANGROVE_PROPAGULE;
                    } else if(itemStack.isOf(Items.BIRCH_SAPLING.asItem())) {
                        placeBlock = (SaplingBlock)Blocks.BIRCH_SAPLING;
                    }

                    BlockState floor = world.getBlockState(blockPos.down());

                    if (world.isAir(blockPos) && floor.isIn(BlockTags.DIRT)) {
                        if (!world.isClient) {
                            world.setBlockState(blockPos, placeBlock.getDefaultState(), Block.NOTIFY_ALL);
                            world.emitGameEvent(null, GameEvent.BLOCK_PLACE, blockPos);
                        }
                        stack.decrement(1);
                        this.setSuccess(true);
                    }
                    return stack;
                }
            };
        } else if(
                itemStack.isOf(Items.CARROT.asItem()) ||
                itemStack.isOf(Items.WHEAT_SEEDS.asItem()) ||
                itemStack.isOf(Items.BEETROOT_SEEDS.asItem()) ||
                itemStack.isOf(Items.POTATO.asItem())
            ) {
            dispenserBehavior = new FallibleItemDispenserBehavior(){
                @Override
                protected ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
                    BlockPos blockPos = pointer.getPos().offset(pointer.getBlockState().get(DispenserBlock.FACING));

                    if(pointer.getBlockState().get(DispenserBlock.FACING) == Direction.UP) {
                        blockPos = pointer.getPos().offset(pointer.getBlockState().get(DispenserBlock.FACING)).up();
                    }

                    ServerWorld world = pointer.getWorld();
                    Block placeBlock = (Block)Blocks.CARROTS;

                    if(itemStack.isOf(Items.WHEAT_SEEDS.asItem())) {
                        placeBlock = (Block)Blocks.WHEAT;
                    } else if(itemStack.isOf(Items.BEETROOT_SEEDS.asItem())) {
                        placeBlock = (Block)Blocks.BEETROOTS;
                    } else if(itemStack.isOf(Items.POTATO.asItem())) {
                        placeBlock = (Block)Blocks.POTATOES;
                    }

                    BlockState floor = world.getBlockState(blockPos.down());

                    if (world.isAir(blockPos) && floor.isOf(Blocks.FARMLAND)) {
                        if (!world.isClient) {
                            world.setBlockState(blockPos, placeBlock.getDefaultState(), Block.NOTIFY_ALL);
                            world.emitGameEvent(null, GameEvent.BLOCK_PLACE, blockPos);
                        }
                        stack.decrement(1);
                        this.setSuccess(true);
                    }
                    return stack;
                }
            };
        }

        return dispenserBehavior;
    }
}
