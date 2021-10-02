package com.tylervp.mixin;

import com.tylervp.block.MBMBlocks;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PlantBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

/* import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.block.Block;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.tag.BlockTags; */

/* @Mixin(PlantBlock.class)
public class PlantBlockMixin extends Block  {
    private static final BooleanProperty PLACEDBYPLAYER;

    public PlantBlockMixin(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState());
    }

    @Inject(method = "canPlantOnTop(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;)Z", at = @At("HEAD"), cancellable = true)
    protected void canPlantOnTop(BlockState floor, BlockView world, BlockPos pos, CallbackInfoReturnable<Boolean> ci) {
        Boolean blocks = floor.isIn(BlockTags.DIRT) || floor.isOf(Blocks.FARMLAND);
        //Boolean blocks = floor.isOf(Blocks.SAND) || floor.isOf(Blocks.GRASS_BLOCK) || floor.isOf(Blocks.DIRT) || floor.isOf(Blocks.COARSE_DIRT) || floor.isOf(Blocks.PODZOL) || floor.isOf(Blocks.FARMLAND) || floor.isOf(MBMBlocks.DEAD_GRASS_BLOCK);

        ci.setReturnValue(blocks);
    }

    @Inject(method = "canPlaceAt(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/WorldView;Lnet/minecraft/util/math/BlockPos;)Z", at = @At("HEAD"), cancellable = true)
    protected void canPlaceAt(BlockState state, WorldView world, BlockPos pos, CallbackInfoReturnable<Boolean> ci) {
        BlockPos blockPos5 = pos.down();
        BlockState floor = world.getBlockState(blockPos5);

        Boolean blocks = floor.isIn(BlockTags.DIRT) || floor.isOf(Blocks.FARMLAND);

        if(state.isOf(this)){
            boolean acceptableBlock = state.isOf(Blocks.GRASS);
            if(acceptableBlock) {
                if(state.get(PlantBlockMixin.PLACEDBYPLAYER)){
                    blocks = floor.isOf(Blocks.SAND) || floor.isOf(Blocks.GRASS_BLOCK) || floor.isOf(Blocks.DIRT) || floor.isOf(Blocks.COARSE_DIRT) || floor.isOf(Blocks.PODZOL) || floor.isOf(Blocks.FARMLAND) || floor.isOf(MBMBlocks.DEAD_GRASS_BLOCK);
                }
            }
        }

        ci.setReturnValue(blocks);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        World world = ctx.getWorld();
        BlockPos pos = ctx.getBlockPos();
        BlockState state = world.getBlockState(pos);
        BlockPos blockPos5 = pos.down();
        BlockState floor = world.getBlockState(blockPos5);
        Boolean blocks = floor.isOf(Blocks.SAND) || floor.isOf(Blocks.GRASS_BLOCK) || floor.isOf(Blocks.DIRT) || floor.isOf(Blocks.COARSE_DIRT) || floor.isOf(Blocks.PODZOL) || floor.isOf(Blocks.FARMLAND) || floor.isOf(MBMBlocks.DEAD_GRASS_BLOCK);
        boolean acceptableBlock = state.isOf(Blocks.GRASS);

        if(ctx.getPlayer().isSneaking() && blocks && acceptableBlock){
            world.setBlockState(ctx.getBlockPos(), this.getDefaultState().with(PlantBlockMixin.PLACEDBYPLAYER, true));
            ctx.getPlayer().swingHand(ctx.getHand());
            ctx.getStack().decrement(1);
        } else if(acceptableBlock){
            return this.getDefaultState().with(PlantBlockMixin.PLACEDBYPLAYER, false);
        }

        return super.getPlacementState(ctx);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(PlantBlockMixin.PLACEDBYPLAYER);
    }

    static {
        PLACEDBYPLAYER = BooleanProperty.of("placedbyplayer");
    }
} */



@Mixin(PlantBlock.class)
public class PlantBlockMixin {

    @Inject(method = "canPlantOnTop(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockPos;)Z", at = @At("HEAD"), cancellable = true)
    protected void canPlantOnTop(BlockState floor, BlockView world, BlockPos pos, CallbackInfoReturnable<Boolean> ci) {
        //Boolean blocks = floor.isOf(Blocks.SAND) || floor.isOf(Blocks.GRASS_BLOCK) || floor.isOf(Blocks.DIRT) || floor.isOf(Blocks.COARSE_DIRT) || floor.isOf(Blocks.PODZOL) || floor.isOf(Blocks.FARMLAND) || floor.isOf(MBMBlocks.DEAD_GRASS_BLOCK);
        //ci.setReturnValue(floor.isOf(Blocks.GRASS_BLOCK) || floor.isOf(Blocks.DIRT) || floor.isOf(MBMBlocks.PACKED_DIRT) || floor.isOf(Blocks.FARMLAND) || floor.isOf(MBMBlocks.DEAD_GRASS_BLOCK) || floor.isOf(MBMBlocks.BURNT_GRASS_BLOCK));
        
        if(floor.isOf(Blocks.GRASS_BLOCK) || floor.isOf(Blocks.DIRT) || floor.isOf(MBMBlocks.PACKED_DIRT) || floor.isOf(Blocks.FARMLAND) || floor.isOf(MBMBlocks.DEAD_GRASS_BLOCK) || floor.isOf(MBMBlocks.BURNT_GRASS_BLOCK)) {
            ci.setReturnValue(true);
        }
        
    }
}