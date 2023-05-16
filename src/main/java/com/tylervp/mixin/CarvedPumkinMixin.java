package com.tylervp.mixin;

import java.util.function.Predicate;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.tylervp.block.MBMBlocks;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CarvedPumpkinBlock;
import net.minecraft.block.Material;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.block.pattern.BlockPatternBuilder;
import net.minecraft.block.pattern.CachedBlockPosition;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.predicate.block.BlockStatePredicate;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.function.MaterialPredicate;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import net.minecraft.world.WorldView;

@Mixin(CarvedPumpkinBlock.class)
public class CarvedPumkinMixin {
    @Nullable
    private BlockPattern ironGolemDispenserPatternMBM;
    @Nullable
    private BlockPattern ironGolemPatternMBM;

    /* @Nullable
    private BlockPattern ironGolemDispenserPatternExposed;
    @Nullable
    private BlockPattern ironGolemPatternExposed;

    @Nullable
    private BlockPattern ironGolemDispenserPatternDegraded;
    @Nullable
    private BlockPattern ironGolemPatternDegraded;

    @Nullable
    private BlockPattern ironGolemDispenserPatternWeathered;
    @Nullable
    private BlockPattern ironGolemPatternWeathered;

    @Nullable
    private BlockPattern ironGolemDispenserPatternRusted;
    @Nullable
    private BlockPattern ironGolemPatternRusted;

    @Nullable
    private BlockPattern ironGolemDispenserPatternWaxedExposed;
    @Nullable
    private BlockPattern ironGolemPatternWaxedExposed;

    @Nullable
    private BlockPattern ironGolemDispenserPatternWaxedDegraded;
    @Nullable
    private BlockPattern ironGolemPatternWaxedDegraded;

    @Nullable
    private BlockPattern ironGolemDispenserPatternWaxedWeathered;
    @Nullable
    private BlockPattern ironGolemPatternWaxedWeathered;

    @Nullable
    private BlockPattern ironGolemDispenserPatternWaxedRusted;
    @Nullable
    private BlockPattern ironGolemPatternWaxedRusted; */

    private static final Predicate<BlockState> IS_GOLEM_HEAD_PREDICATE = state -> state != null && (state.isOf(Blocks.CARVED_PUMPKIN) || state.isOf(Blocks.JACK_O_LANTERN));

    @Inject(method = "canDispense(Lnet/minecraft/world/WorldView;Lnet/minecraft/util/math/BlockPos;)Z", at = @At("HEAD"), cancellable = true)
    void canDispense(WorldView world, BlockPos pos, CallbackInfoReturnable<Boolean> ci) {

        if(world.getBlockState(pos.down()).isOf(MBMBlocks.IRON_BLOCK)) {
            ci.setReturnValue(this.getIronGolemDispenserPatternMBM().searchAround(world, pos) != null);
        }
    }

    @Inject(method = "onBlockAdded(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;Z)V", at = @At("HEAD"), cancellable = true)
    void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify, CallbackInfo ci) {
        if (oldState.isOf(state.getBlock())) {
            ci.cancel();
        }
        this.trySpawnIronGolemEntity(world, pos, this.getIronGolemPatternMBM());
    }


    private void trySpawnIronGolemEntity(World world, BlockPos pos, BlockPattern GolemPattern) {
        block9: {
            BlockPattern.Result result;
            result = GolemPattern.searchAround(world, pos);
            if (result == null) break block9;
            for (int i = 0; i < GolemPattern.getWidth(); ++i) {
                for (int k = 0; k < GolemPattern.getHeight(); ++k) {
                    CachedBlockPosition cachedBlockPosition3 = result.translate(i, k, 0);
                    world.setBlockState(cachedBlockPosition3.getBlockPos(), Blocks.AIR.getDefaultState(), Block.NOTIFY_LISTENERS);
                    world.syncWorldEvent(WorldEvents.BLOCK_BROKEN, cachedBlockPosition3.getBlockPos(), Block.getRawIdFromState(cachedBlockPosition3.getBlockState()));
                }
            }
            BlockPos blockPos2 = result.translate(1, 2, 0).getBlockPos();
            IronGolemEntity ironGolemEntity = EntityType.IRON_GOLEM.create(world);
            ironGolemEntity.setPlayerCreated(true);
            ironGolemEntity.refreshPositionAndAngles((double)blockPos2.getX() + 0.5, (double)blockPos2.getY() + 0.05, (double)blockPos2.getZ() + 0.5, 0.0f, 0.0f);
            world.spawnEntity(ironGolemEntity);
            for (ServerPlayerEntity serverPlayerEntity : world.getNonSpectatingEntities(ServerPlayerEntity.class, ironGolemEntity.getBoundingBox().expand(5.0))) {
                Criteria.SUMMONED_ENTITY.trigger(serverPlayerEntity, ironGolemEntity);
            }
            for (int j = 0; j < GolemPattern.getWidth(); ++j) {
                for (int l = 0; l < GolemPattern.getHeight(); ++l) {
                    CachedBlockPosition cachedBlockPosition4 = result.translate(j, l, 0);
                    world.updateNeighbors(cachedBlockPosition4.getBlockPos(), Blocks.AIR);
                }
            }
        }
    }



    private BlockPattern getIronGolemDispenserPatternMBM() {
        if (this.ironGolemDispenserPatternMBM == null) {
            this.ironGolemDispenserPatternMBM = BlockPatternBuilder.start().aisle("~ ~", "###", "~#~").where('#', CachedBlockPosition.matchesBlockState(BlockStatePredicate.forBlock(MBMBlocks.IRON_BLOCK))).where('~', CachedBlockPosition.matchesBlockState(MaterialPredicate.create(Material.AIR))).build();
        }
        return this.ironGolemDispenserPatternMBM;
    }

    private BlockPattern getIronGolemPatternMBM() {
        if (this.ironGolemPatternMBM == null) {
            this.ironGolemPatternMBM = BlockPatternBuilder.start().aisle("~^~", "###", "~#~").where('^', CachedBlockPosition.matchesBlockState(IS_GOLEM_HEAD_PREDICATE)).where('#', CachedBlockPosition.matchesBlockState(BlockStatePredicate.forBlock(MBMBlocks.IRON_BLOCK))).where('~', CachedBlockPosition.matchesBlockState(MaterialPredicate.create(Material.AIR))).build();
        }
        return this.ironGolemPatternMBM;
    }
}
