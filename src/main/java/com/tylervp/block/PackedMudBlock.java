package com.tylervp.block;


import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class PackedMudBlock extends Block {
    protected static final VoxelShape COLLISION_SHAPE;

    protected PackedMudBlock(AbstractBlock.Settings settings) {
        super(settings.slipperiness(0.98f));
        this.setDefaultState(this.stateManager.getDefaultState());
    }

    @Override
    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        super.onLandedUpon(world, state, pos, entity, fallDistance);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return PackedMudBlock.COLLISION_SHAPE;
    }
    
    @Override
    public VoxelShape getSidesShape(BlockState state, BlockView world, BlockPos pos) {
        return VoxelShapes.fullCube();
    }
    
    @Override
    public VoxelShape getCameraCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.fullCube();
    }

    /*@Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
        BlockState blockstateup = world.getBlockState(pos.up());
        if(blockstateup.isOf(Blocks.AIR)){
           world.setBlockState(pos, moreblocksmod.MUD.getDefaultState());
        }
    }


    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        ItemStack MainHand = player.getStackInHand(Hand.MAIN_HAND);
        ItemStack OffHand = player.getStackInHand(Hand.OFF_HAND);

        
        super.onBreak(world, pos, state, player);
    }*/


    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, BlockEntity blockEntity, ItemStack stack) {
        super.afterBreak(world, player, pos, state, blockEntity, stack);
        
       if (EnchantmentHelper.getLevel(Enchantments.SILK_TOUCH, stack) == 0) {
            if (world.getDimension().isUltrawarm()) {
                world.setBlockState(pos, Blocks.DIRT.getDefaultState());  
                return;
            }
             world.setBlockState(pos, MBMBlocks.MUD.getDefaultState());  
        }
    }

   /*private static void spawnParticles(World world, BlockPos pos) {
        // double double3 = 0.5625;
        Random random5 = world.random;
        for (final Direction lv : Direction.values()) {
            BlockPos blockPos10 = pos.offset(lv);
            if (!world.getBlockState(blockPos10).isOpaqueFullCube(world, blockPos10)) {
                Direction.Axis axis11 = lv.getAxis();
                double double12 = (axis11 == Direction.Axis.X) ? (0.5 + 0.5625 * lv.getOffsetX()) : random5.nextFloat();
                double double14 = (axis11 == Direction.Axis.Y) ? (0.5 + 0.5625 * lv.getOffsetY()) : random5.nextFloat();
                double double16 = (axis11 == Direction.Axis.Z) ? (0.5 + 0.5625 * lv.getOffsetZ()) : random5.nextFloat();
                world.addParticle(ParticleTypes.DAMAGE_INDICATOR, pos.getX() + double12, pos.getY() + double14,
                        pos.getZ() + double16, 0.0, 0.0, 0.0);
            }
        }
    }*/

    static {
        COLLISION_SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 14.0, 16.0);
    }
}