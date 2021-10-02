package com.tylervp.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import com.tylervp.block.MBMBlocks;
import com.tylervp.block.ThinLogBlock;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LeavesBlock.class)
public abstract class LeavesBlockMixin {

    @Inject(method = "getPlacementState(Lnet/minecraft/item/ItemPlacementContext;)Lnet/minecraft/block/BlockState;", at = @At("HEAD"), cancellable = true)
    private void getPlacementState(ItemPlacementContext ctx, CallbackInfoReturnable<BlockState> ci) {
        BlockPos pos = ctx.getBlockPos();
        World world = ctx.getWorld();
        BlockState state = world.getBlockState(pos);

        if(state.isOf(MBMBlocks.THIN_OAK_LOG)){
            ci.setReturnValue((BlockState)MBMBlocks.THIN_OAK_LOG.getDefaultState().with(ThinLogBlock.LEAVES, true).with(ThinLogBlock.PRESISTANT, true).with(ThinLogBlock.AXIS, state.get(ThinLogBlock.AXIS)).with(ThinLogBlock.CHAIN, state.get(ThinLogBlock.CHAIN)).with(ThinLogBlock.ROPE, state.get(ThinLogBlock.ROPE)));
        }

        if(state.isOf(MBMBlocks.THIN_SPRUCE_LOG)){
            ci.setReturnValue((BlockState)MBMBlocks.THIN_SPRUCE_LOG.getDefaultState().with(ThinLogBlock.LEAVES, true).with(ThinLogBlock.PRESISTANT, true).with(ThinLogBlock.AXIS, state.get(ThinLogBlock.AXIS)).with(ThinLogBlock.CHAIN, state.get(ThinLogBlock.CHAIN)).with(ThinLogBlock.ROPE, state.get(ThinLogBlock.ROPE)));
        }

        if(state.isOf(MBMBlocks.THIN_ACACIA_LOG)){
            ci.setReturnValue((BlockState)MBMBlocks.THIN_ACACIA_LOG.getDefaultState().with(ThinLogBlock.LEAVES, true).with(ThinLogBlock.PRESISTANT, true).with(ThinLogBlock.AXIS, state.get(ThinLogBlock.AXIS)).with(ThinLogBlock.CHAIN, state.get(ThinLogBlock.CHAIN)).with(ThinLogBlock.ROPE, state.get(ThinLogBlock.ROPE)));
        }

        if(state.isOf(MBMBlocks.THIN_BIRCH_LOG)){
            ci.setReturnValue((BlockState)MBMBlocks.THIN_BIRCH_LOG.getDefaultState().with(ThinLogBlock.LEAVES, true).with(ThinLogBlock.PRESISTANT, true).with(ThinLogBlock.AXIS, state.get(ThinLogBlock.AXIS)).with(ThinLogBlock.CHAIN, state.get(ThinLogBlock.CHAIN)).with(ThinLogBlock.ROPE, state.get(ThinLogBlock.ROPE)));
        }

        if(state.isOf(MBMBlocks.THIN_DARK_OAK_LOG)){
            ci.setReturnValue((BlockState)MBMBlocks.THIN_DARK_OAK_LOG.getDefaultState().with(ThinLogBlock.LEAVES, true).with(ThinLogBlock.PRESISTANT, true).with(ThinLogBlock.AXIS, state.get(ThinLogBlock.AXIS)).with(ThinLogBlock.CHAIN, state.get(ThinLogBlock.CHAIN)).with(ThinLogBlock.ROPE, state.get(ThinLogBlock.ROPE)));
        }

        if(state.isOf(MBMBlocks.THIN_JUNGLE_LOG)){
            ci.setReturnValue((BlockState)MBMBlocks.THIN_JUNGLE_LOG.getDefaultState().with(ThinLogBlock.LEAVES, true).with(ThinLogBlock.PRESISTANT, true).with(ThinLogBlock.AXIS, state.get(ThinLogBlock.AXIS)).with(ThinLogBlock.CHAIN, state.get(ThinLogBlock.CHAIN)).with(ThinLogBlock.ROPE, state.get(ThinLogBlock.ROPE)));
        }

    }

    /* @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack playerItem = player.getStackInHand(hand);
        Random random = world.getRandom();

        if(state.isOf(Blocks.OAK_LEAVES)) {
            if (playerItem.getItem() == Items.BONE_MEAL) {
                for (Direction direction : Direction.values()) {
                    if(random.nextInt(10) == 0){
                        BlockPos offsetPos = pos.offset(direction);
                        BlockState offsetState = world.getBlockState(pos.offset(direction));

                        if(offsetState.isAir()) {
                            if(!world.isClient()) {
                                world.setBlockState(offsetPos, MBMBlocks.APPLE.getDefaultState().with(Apple.FACE, direction.getOpposite()));
                            }
                            return ActionResult.success(world.isClient());
                        }
                    }
                }
            }
        }
        return super.onUse(state, world, pos, player, hand, hit);
    } */

    /* @Override
    public void onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, CallbackInfoReturnable<ActionResult> ci) {
        Random random = world.getRandom();
        ItemStack playerItem = player.getStackInHand(hand);

        if(state.isOf(Blocks.OAK_LEAVES)) {
            if (playerItem.getItem() == Items.BONE_MEAL) {
                System.out.println("OL");
                if(random.nextInt(30) == 0) {
                    System.out.println("Hit");
                    for (Direction direction : Direction.values()) {
                        if(random.nextInt(30) == 0) {
                            System.out.println("Hit2");
                            BlockPos offsetPos = pos.offset(direction);
                            BlockState offsetState = world.getBlockState(pos.offset(direction));

                            if(offsetState.isAir()) {
                                System.out.println("Hit3");
                                world.setBlockState(offsetPos, MBMBlocks.APPLE.getDefaultState().with(Apple.FACE, direction.getOpposite()));
                                ci.setReturnValue(ActionResult.success(world.isClient));
                            }
                        }
                    }
                }
            }
            ci.setReturnValue(ActionResult.FAIL);
        }
    } */
    

    /* @Inject(method = "randomTick(Lnet/minecraft/block/BlockState;Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;Ljava/util/Random;)V", at = @At("HEAD"), cancellable = true)
    private void randomTicks(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
        System.out.println("Random Tick");
        if(state.isOf(Blocks.OAK_LEAVES)) {
            System.out.println("OL");
            if(random.nextInt(30) == 0) {
                System.out.println("Hit");
                for (Direction direction : Direction.values()) {
                    if(random.nextInt(30) == 0) {
                        System.out.println("Hit2");
                        BlockPos offsetPos = pos.offset(direction);
                        BlockState offsetState = world.getBlockState(pos.offset(direction));

                        if(offsetState.isAir()) {
                            System.out.println("Hit3");
                            world.setBlockState(offsetPos, MBMBlocks.APPLE.getDefaultState().with(Apple.FACE, direction.getOpposite()));
                        }
                    }
                }
            }
        }
    } */
}