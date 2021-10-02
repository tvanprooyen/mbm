package com.tylervp.block;

import java.util.Random;

import net.minecraft.block.PillarBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3f;
import net.minecraft.world.World;

public class BurntPillarBlock extends PillarBlock {
    public static final IntProperty AGE;
    public static final BooleanProperty PERSISTENT;

    public BurntPillarBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(BurntPillarBlock.AGE, 0).with(BurntPillarBlock.PERSISTENT, false));
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        if(world.isClient){
            if(entity.isSprinting()){
                spawnParticles(world, pos);
            }
        }
        super.onSteppedOn(world, pos, state, entity);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        spawnParticles(ctx.getWorld(), ctx.getBlockPos());
        return this.getDefaultState().with(BurntPillarBlock.AGE, 0).with(Properties.AXIS, ctx.getSide().getAxis()).with(BurntPillarBlock.PERSISTENT, ctx.getPlayer().isSneaking());
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (random.nextInt(5) == 0 && world.getBlockState(pos.up()).isAir() && state.get(BurntPillarBlock.AGE) < 3) {
            DefaultParticleType defaultParticleType6 = ParticleTypes.CAMPFIRE_COSY_SMOKE;
            world.addImportantParticle(defaultParticleType6, true, pos.getX() + 0.5 + random.nextDouble() / 3.0 * (random.nextBoolean() ? 1 : -1), pos.getY() + 0.25 + random.nextDouble() + random.nextDouble(), pos.getZ() + 0.5 + random.nextDouble() / 3.0 * (random.nextBoolean() ? 1 : -1), 0.0, 0.02, 0.0);
        }
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
                DustParticleEffect dirtPartical = new DustParticleEffect(new Vec3f(Vec3d.unpackRgb(3684408)), 1.0f);
                world.addParticle(dirtPartical, pos.getX() + double12, pos.getY() + double14, pos.getZ() + double16, 0.0, 0.0, 0.0);
            }
        }
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if(state.get(BurntPillarBlock.AGE) < 3 && !state.get(BurntPillarBlock.PERSISTENT)){
            world.setBlockState(pos, state.with(Properties.AXIS, state.get(Properties.AXIS)).with(BurntPillarBlock.AGE, state.get(BurntPillarBlock.AGE) + 1).with(BurntPillarBlock.PERSISTENT, state.get(BurntPillarBlock.PERSISTENT)));
        }
        super.randomTick(state, world, pos, random);
    }

    @Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
		stateManager.add(BurntPillarBlock.AGE, Properties.AXIS, BurntPillarBlock.PERSISTENT);
	}

    static {
        AGE = IntProperty.of("age", 0, 3);
        PERSISTENT = BooleanProperty.of("persistent");
    }
}