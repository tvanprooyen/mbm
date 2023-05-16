package com.tylervp.mixin;

import com.mojang.logging.LogUtils;
import com.tylervp.block.MBMBlocks;
import com.tylervp.block.PineconeBlock;

import net.minecraft.block.Block;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.FallibleItemDispenserBehavior;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.event.GameEvent;

import org.slf4j.Logger;

public interface MBMDispenserBehavior {

    public static final Logger LOGGER = LogUtils.getLogger();
    public static final MBMDispenserBehavior NOOP = (pointer, stack) -> stack;

    public ItemStack dispense(BlockPointer var1, ItemStack var2);

    public static void registerDefaults() {
        DispenserBlock.registerBehavior(MBMBlocks.PINECONE, new FallibleItemDispenserBehavior(){
            @Override
            protected ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
                System.out.println("Hit dispenseSilently");
                ServerWorld world = pointer.getWorld();
                BlockPos blockPos = pointer.getPos().offset(pointer.getBlockState().get(DispenserBlock.FACING));
                PineconeBlock pineconeBlock = (PineconeBlock)MBMBlocks.PINECONE;
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
        });
    }
}