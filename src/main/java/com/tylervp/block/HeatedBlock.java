package com.tylervp.block;

import java.util.Random;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class HeatedBlock extends Block {
    public static final BooleanProperty LIT;
    public static final BooleanProperty HARDEN;

    public HeatedBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(((BlockState)this.getDefaultState()).with(HeatedBlock.LIT, true).with(HeatedBlock.HARDEN, false));
    }

    private static boolean heatOnAnySide(BlockView world, BlockPos pos, Block block) {
        return world.getBlockState(pos.north()).isOf(block) || world.getBlockState(pos.east()).isOf(block) || world.getBlockState(pos.south()).isOf(block) || world.getBlockState(pos.west()).isOf(block) || world.getBlockState(pos.up()).isOf(block) || world.getBlockState(pos.down()).isOf(block);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (random.nextFloat() < 0.05688889F) {
            world.setBlockState(pos, this.getDefaultState().with(HeatedBlock.HARDEN, true));
        }
        
        if(!(heatOnAnySide(world, pos, Blocks.LAVA) || heatOnAnySide(world, pos, Blocks.FIRE) || heatOnAnySide(world, pos, Blocks.CAMPFIRE) || heatOnAnySide(world, pos, Blocks.SOUL_CAMPFIRE))){
            world.syncWorldEvent(1501, pos, 0);
            if(state.get(HeatedBlock.HARDEN)){
                world.setBlockState(pos, MBMBlocks.HARDENED_IRON.getDefaultState());
            } else {
                world.setBlockState(pos, MBMBlocks.IRON_BLOCK.getDefaultState());
            }
        }
        
        super.randomTick(state, world, pos, random);
    }


    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState newState, WorldAccess world, BlockPos pos, BlockPos posFrom) {
        //!(heatOnAnySide(world, pos, Blocks.LAVA) || heatOnAnySide(world, pos, Blocks.FIRE) || heatOnAnySide(world, pos, Blocks.CAMPFIRE) || heatOnAnySide(world, pos, Blocks.SOUL_CAMPFIRE))
        if(world.getBlockState(pos.up()).isOf(Blocks.WATER) || world.getBlockState(pos.down()).isOf(Blocks.WATER) || world.getBlockState(pos.north()).isOf(Blocks.WATER) || world.getBlockState(pos.east()).isOf(Blocks.WATER) || world.getBlockState(pos.south()).isOf(Blocks.WATER) || world.getBlockState(pos.west()).isOf(Blocks.WATER)){
            world.syncWorldEvent(1501, pos, 0);
            if(state.get(HeatedBlock.HARDEN)){
                return MBMBlocks.HARDENED_IRON.getDefaultState();
            } else {
                return MBMBlocks.IRON_BLOCK.getDefaultState();
            }
        }
        return super.getStateForNeighborUpdate(state, direction, newState, world, pos, posFrom);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        
        if(state.get(HeatedBlock.HARDEN) && random.nextInt(10) == 0){
            spawnParticles(world, pos);
        }

        super.randomDisplayTick(state, world, pos, random);
    }

    private static void spawnParticles(World world, BlockPos pos) {
        //double double3 = 0.5625;
        //for (int i = 0; i < 4; i++) {
            Random random5 = world.random;
            for (final Direction lv : Direction.values()) {
                BlockPos blockPos10 = pos.offset(lv);
                if (!world.getBlockState(blockPos10).isOpaqueFullCube(world, blockPos10)) {
                    Direction.Axis axis11 = lv.getAxis();
                    double double12 = (axis11 == Direction.Axis.X) ? (0.5 + 0.5625 * lv.getOffsetX()) : random5.nextFloat();
                    double double14 = (axis11 == Direction.Axis.Y) ? (0.5 + 0.5625 * lv.getOffsetY()) : random5.nextFloat();
                    double double16 = (axis11 == Direction.Axis.Z) ? (0.5 + 0.5625 * lv.getOffsetZ()) : random5.nextFloat();
                    //DustParticleEffect dirtPartical = new DustParticleEffect(0.93f, 0.63f, 0.45f, 1.0f);
                    
                    world.addParticle(ParticleTypes.LAVA, pos.getX() + double12, pos.getY() + double14, pos.getZ() + double16, 0.0, 0.0, 0.0);
                }
            }   
        //}
        
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, Entity entity) {
        if (world.random.nextInt(10) == 0) {
            if (!entity.isFireImmune() && entity instanceof LivingEntity && !EnchantmentHelper.hasFrostWalker((LivingEntity)entity)) {
                entity.damage(DamageSource.HOT_FLOOR, 1.0f);
            }
        }
        super.onSteppedOn(world, pos, entity);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(HeatedBlock.LIT, HeatedBlock.HARDEN);
    }

    static {
        LIT = BooleanProperty.of("lit");
        HARDEN = BooleanProperty.of("harden");
    }
}