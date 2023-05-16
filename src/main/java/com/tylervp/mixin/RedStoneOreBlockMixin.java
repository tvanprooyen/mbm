package com.tylervp.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.RedstoneOreBlock;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.tylervp.block.MBMBlocks;

@Mixin(RedstoneOreBlock.class)
public class RedStoneOreBlockMixin extends Block {
    private static final BooleanProperty CORRUPT, PLAYER_PLACED;
    private static final IntProperty AGE;

    public RedStoneOreBlockMixin(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(PLAYER_PLACED, false).with(CORRUPT, false).with(AGE, 0).with(RedstoneOreBlock.LIT, false));
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(PLAYER_PLACED, true);
    }

    @Inject(method = "hasRandomTicks(Lnet/minecraft/block/BlockState;)Z", at = @At("HEAD"), cancellable = true)
    private void hasRandomTicks(BlockState state, CallbackInfoReturnable<Boolean> ci) {
        ci.setReturnValue((state.get(RedstoneOreBlock.LIT) && !state.get(PLAYER_PLACED)) && state.get(CORRUPT));
    }

    @Inject(method = "randomTick(Lnet/minecraft/block/BlockState;Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/random/Random;)V", at = @At("HEAD"), cancellable = true)
    private void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
        if(random.nextInt(2) == 0) {
            world.setBlockState(pos,
            this.getDefaultState()
            .with(AGE, state.get(AGE) + 1)
            .with(CORRUPT, state.get(CORRUPT))
            .with(PLAYER_PLACED, state.get(PLAYER_PLACED))
            .with(RedstoneOreBlock.LIT, state.get(RedstoneOreBlock.LIT))
            .with(AGE, state.get(AGE) + 1), NOTIFY_ALL);
        }

        if(state.get(AGE) < 3) {

            BlockPos.Mutable mutablePos = pos.mutableCopy();

            for (Direction direction : Direction.values()) {
                if(direction != Direction.DOWN) {
                    BlockPos offSetPos = mutablePos.offset(direction);

                    if(random.nextInt(2) == 0) {
                        break;
                    }

                    if(random.nextInt(20) == 0) {
                        if(world.getBlockState(pos).isOf(Blocks.DEEPSLATE_REDSTONE_ORE)) {
                            world.setBlockState(pos, Blocks.DEEPSLATE.getDefaultState());
                        } else {
                            world.setBlockState(pos, Blocks.STONE.getDefaultState());
                        }

                        ci.cancel();
                    } else {
                        if(world.getBlockState(offSetPos).isAir() && random.nextInt(4) == 0) {
                            world.setBlockState(offSetPos, MBMBlocks.REDSTONE_CRYSTAL.getDefaultState());
                            world.setBlockState(pos, 
                            this.getDefaultState()
                            .with(AGE, state.get(AGE) + 1)
                            .with(CORRUPT, state.get(CORRUPT))
                            .with(PLAYER_PLACED, state.get(PLAYER_PLACED))
                            .with(RedstoneOreBlock.LIT, state.get(RedstoneOreBlock.LIT))
                            .with(AGE, state.get(AGE) + 1), NOTIFY_ALL);
                            break;
                        }
                    }
                }
            }
        }

        if(!state.get(PLAYER_PLACED) && random.nextInt(3) == 0) {
            ci.cancel();
        }
    }

    @Inject(method = "onUse(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/util/Hand;Lnet/minecraft/util/hit/BlockHitResult;)Lnet/minecraft/util/ActionResult;", at = @At("HEAD"), cancellable = true)
    private void onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, CallbackInfoReturnable<ActionResult> ci) {
        if(!world.isClient) {
            if(state.get(AGE) == 0 && world.getRandom().nextInt(10) == 0) {
                world.setBlockState(pos, state.with(CORRUPT, true), NOTIFY_ALL);
            }
        }
    }

    @Inject(method = "onSteppedOn(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;Lnet/minecraft/entity/Entity;)V", at = @At("HEAD"), cancellable = true)
    private void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity, CallbackInfo ci) {
        if(!world.isClient) {
            if(state.get(AGE) == 0 && world.getRandom().nextInt(10) == 0) {
                world.setBlockState(pos, state.with(CORRUPT, true), NOTIFY_ALL);
            }
        }
    }

    @Inject(method = "onBlockBreakStart(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/entity/player/PlayerEntity;)V", at = @At("HEAD"), cancellable = true)
    private void onBlockBreakStart(BlockState state, World world, BlockPos pos, PlayerEntity player, CallbackInfo ci) {
        if(!world.isClient) {
            if(state.get(AGE) == 0 && world.getRandom().nextInt(10) == 0) {
                world.setBlockState(pos, state.with(CORRUPT, true), NOTIFY_ALL);
            }
        }
    }

    @Inject(method = "appendProperties(Lnet/minecraft/state/StateManager$Builder;)V", at = @At("HEAD"), cancellable = true)
    private void appendProperties(Builder<Block, BlockState> builder, CallbackInfo ci) {
        builder.add(CORRUPT, AGE, PLAYER_PLACED, RedstoneOreBlock.LIT);
        ci.cancel();
    }

    static {
        CORRUPT = BooleanProperty.of("corrupt");
        PLAYER_PLACED = BooleanProperty.of("playerplaced");
        AGE = IntProperty.of("age", 0, 3);
    }
}