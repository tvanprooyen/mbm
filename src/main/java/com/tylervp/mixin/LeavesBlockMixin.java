package com.tylervp.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

import com.tylervp.moreblocksmod;

import org.spongepowered.asm.mixin.Mixin;

@Mixin(LeavesBlock.class)
public abstract class LeavesBlockMixin extends Block {
    /*
	@Inject(at = @At("HEAD"), method = "init()V")
	private void init(CallbackInfo info) {
		System.out.println("This line is printed by an example mod mixin!");
    }
    //CallbackInfo info
    // @Inject(at = @At("INVOKE"), method = "onBlockAdded")
    */

    public LeavesBlockMixin(AbstractBlock.Settings settings) {
        super(settings);
    }

    
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos pos = ctx.getBlockPos();
        World world = ctx.getWorld();
        BlockState state = world.getBlockState(pos);
        

        if(state.isOf(moreblocksmod.THIN_OAK_LOG)){
            return (BlockState)moreblocksmod.THIN_OAK_LOG.getDefaultState().with(moreblocksmod.THIN_OAK_LOG.LEAVES, true).with(moreblocksmod.THIN_OAK_LOG.PRESISTANT, true).with(moreblocksmod.THIN_OAK_LOG.AXIS, state.get(moreblocksmod.THIN_OAK_LOG.AXIS));
        }

        if(state.isOf(moreblocksmod.THIN_SPRUCE_LOG)){
            return (BlockState)moreblocksmod.THIN_SPRUCE_LOG.getDefaultState().with(moreblocksmod.THIN_SPRUCE_LOG.LEAVES, true).with(moreblocksmod.THIN_SPRUCE_LOG.PRESISTANT, true).with(moreblocksmod.THIN_SPRUCE_LOG.AXIS, state.get(moreblocksmod.THIN_SPRUCE_LOG.AXIS));
        }

        if(state.isOf(moreblocksmod.THIN_ACACIA_LOG)){
            return (BlockState)moreblocksmod.THIN_ACACIA_LOG.getDefaultState().with(moreblocksmod.THIN_ACACIA_LOG.LEAVES, true).with(moreblocksmod.THIN_ACACIA_LOG.PRESISTANT, true).with(moreblocksmod.THIN_ACACIA_LOG.AXIS, state.get(moreblocksmod.THIN_ACACIA_LOG.AXIS));
        }

        if(state.isOf(moreblocksmod.THIN_BIRCH_LOG)){
            return (BlockState)moreblocksmod.THIN_BIRCH_LOG.getDefaultState().with(moreblocksmod.THIN_BIRCH_LOG.LEAVES, true).with(moreblocksmod.THIN_BIRCH_LOG.PRESISTANT, true).with(moreblocksmod.THIN_BIRCH_LOG.AXIS, state.get(moreblocksmod.THIN_BIRCH_LOG.AXIS));
        }

        if(state.isOf(moreblocksmod.THIN_DARK_OAK_LOG)){
            return (BlockState)moreblocksmod.THIN_DARK_OAK_LOG.getDefaultState().with(moreblocksmod.THIN_DARK_OAK_LOG.LEAVES, true).with(moreblocksmod.THIN_DARK_OAK_LOG.PRESISTANT, true).with(moreblocksmod.THIN_DARK_OAK_LOG.AXIS, state.get(moreblocksmod.THIN_DARK_OAK_LOG.AXIS));
        }

        if(state.isOf(moreblocksmod.THIN_JUNGLE_LOG)){
            return (BlockState)moreblocksmod.THIN_JUNGLE_LOG.getDefaultState().with(moreblocksmod.THIN_JUNGLE_LOG.LEAVES, true).with(moreblocksmod.THIN_JUNGLE_LOG.PRESISTANT, true).with(moreblocksmod.THIN_JUNGLE_LOG.AXIS, state.get(moreblocksmod.THIN_JUNGLE_LOG.AXIS));
        }
        
        return updateDistanceFromLogs(((BlockState)this.getDefaultState()).with(LeavesBlock.PERSISTENT, true), ctx.getWorld(), ctx.getBlockPos());

    }


    private static BlockState updateDistanceFromLogs(BlockState state, WorldAccess world, BlockPos pos) {
        int integer4 = 7;
        BlockPos.Mutable mutable5 = new BlockPos.Mutable();
        for (final Direction lv2 : Direction.values()) {
            mutable5.set(pos, lv2);
            integer4 = Math.min(integer4, getDistanceFromLog(world.getBlockState(mutable5)) + 1);
            if (integer4 == 1) {
                break;
            }
        }
        return state.with(LeavesBlock.DISTANCE, integer4);
    }

    private static int getDistanceFromLog(BlockState state) {
        if (BlockTags.LOGS.contains(state.getBlock())) {
            return 0;
        }
        if (state.getBlock() instanceof LeavesBlock) {
            return state.<Integer>get(LeavesBlock.DISTANCE);
        }
        return 7;
    }
    
    /*
    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        super.onBlockAdded(state, world, pos, oldState, notify);

        if(oldState.isOf(moreblocksmod.THIN_OAK_LOG) && state.isOf(Blocks.OAK_LEAVES)){
            world.setBlockState(pos, moreblocksmod.THIN_OAK_LOG.getDefaultState().with(moreblocksmod.THIN_OAK_LOG.LEAVES, true).with(moreblocksmod.THIN_OAK_LOG.AXIS, oldState.get(moreblocksmod.THIN_OAK_LOG.AXIS)));
        }

        if(oldState.isOf(moreblocksmod.THIN_SPRUCE_LOG) && state.isOf(Blocks.SPRUCE_LEAVES)){
            world.setBlockState(pos, moreblocksmod.THIN_SPRUCE_LOG.getDefaultState().with(moreblocksmod.THIN_SPRUCE_LOG.LEAVES, true).with(moreblocksmod.THIN_SPRUCE_LOG.AXIS, oldState.get(moreblocksmod.THIN_SPRUCE_LOG.AXIS)));
        }
    }
    */
}