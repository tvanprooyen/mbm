package com.tylervp.block;

import java.util.Random;

//import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.state.property.Properties;

public class PetrifiedPillarBlock  extends PillarBlock {
    public static final IntProperty BREAKSTATE;
    public static final EnumProperty<Direction.Axis> AXIS;
    //public static FlammableBlockRegistry fRegistry = FlammableBlockRegistry.getDefaultInstance();

    protected PetrifiedPillarBlock (AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(BREAKSTATE, 4));
        //fRegistry.add(this, 5, 5);
    }
    
    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        if(!(entity instanceof ItemEntity)){
            tryBreakPetrifiedBlock(world, pos, entity, 100);
        }
        super.onSteppedOn(world, pos, state, entity);
    }

    @Override
    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        if(!(entity instanceof ItemEntity)){
            int landingMutiplyer = Math.abs((int) Math.round((100 / fallDistance)));
            //System.out.println("landingMutiplyer: " + landingMutiplyer);
            //System.out.println("distance: " + distance);
            tryBreakPetrifiedBlock(world, pos, entity, landingMutiplyer);
        }
        super.onLandedUpon(world, state, pos, entity, fallDistance);
    }


    private static void spawnParticles(World world, BlockPos pos) {
        //double double3 = 0.5625;
        Random random5 = world.random;
        for (final Direction lv : Direction.values()) {
            BlockPos blockPos10 = pos.offset(lv);
            if (!world.getBlockState(blockPos10).isOpaqueFullCube(world, blockPos10)) {
                Direction.Axis axis11 = lv.getAxis();
                double double12 = (axis11 == Direction.Axis.X) ? (0.5 + 0.5625 * lv.getOffsetX()) : random5.nextFloat();
                double double14 = (axis11 == Direction.Axis.Y) ? (0.5 + 0.5625 * lv.getOffsetY()) : random5.nextFloat();
                double double16 = (axis11 == Direction.Axis.Z) ? (0.5 + 0.5625 * lv.getOffsetZ()) : random5.nextFloat();
                world.addParticle(ParticleTypes.DAMAGE_INDICATOR, pos.getX() + double12, pos.getY() + double14, pos.getZ() + double16, 0.0, 0.0, 0.0);
            }
        }
    }

    private void tryBreakPetrifiedBlock(World world, BlockPos blockPos, Entity entity, int inverseChance) {
        if (!world.isClient && world.random.nextInt(inverseChance) == 0) {
            BlockState blockState6 = world.getBlockState(blockPos);
            if (blockState6.isOf(this)) {
                this.breakPetrifiedBlock(world, blockPos, blockState6);
            }
        }
    }
    
    private void breakPetrifiedBlock(World world, BlockPos pos, BlockState state) {
        world.playSound(null, pos, SoundEvents.BLOCK_WOOD_BREAK, SoundCategory.BLOCKS, 0.7f, 0.9f + world.random.nextFloat() * 0.2f);
        int integer5 = state.<Integer>get((Property<Integer>)PetrifiedPillarBlock.BREAKSTATE);
        //Direction.Axis directionAxis = state.get(PillarBlock.AXIS);
        if (integer5 <= 1) {
            world.breakBlock(pos, false);
        }
        else {
            world.setBlockState(pos, (BlockState)state.with(BREAKSTATE, integer5 - 1), 2);
            spawnParticles(world, pos);
            world.syncWorldEvent(2001, pos, Block.getRawIdFromState(state));
        }
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(PillarBlock.AXIS,BREAKSTATE);
    }

    static {
        BREAKSTATE = IntProperty.of("breakstate", 0, 4);
        AXIS = Properties.AXIS;
    }
}