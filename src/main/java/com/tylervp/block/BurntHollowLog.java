package com.tylervp.block;

import org.joml.Vector3f;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class BurntHollowLog extends ChimneyBlock {
    public static final IntProperty AGE;
    public static final BooleanProperty PERSISTENT;

    public BurntHollowLog(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(AGE, 0).with(Properties.AXIS, Direction.Axis.Y).with(PERSISTENT, false).with(Properties.WATERLOGGED, false).with(ChimneyBlock.LARGE, false));
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
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(player.isSneaking()) {
            boolean newLargeVal = state.get(ChimneyBlock.LARGE).booleanValue();

            if(newLargeVal) {
                newLargeVal = false;
            } else {
                newLargeVal = true;
            }

            BlockState newState = (BlockState)this.getDefaultState().with(AGE, state.get(AGE)).with(Properties.AXIS, state.get(Properties.AXIS)).with(PERSISTENT, state.get(PERSISTENT)).with(Properties.WATERLOGGED, state.get(Properties.WATERLOGGED)).with(ChimneyBlock.LARGE, newLargeVal);

            world.setBlockState(pos, newState);

            return ActionResult.success(world.isClient);
        }

        return ActionResult.PASS;
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos pos = ctx.getBlockPos();
        FluidState fluidState5 = ctx.getWorld().getFluidState(pos);

        spawnParticles(ctx.getWorld(), ctx.getBlockPos());
        return (BlockState)this.getDefaultState().with(AGE, 0).with(Properties.AXIS, ctx.getSide().getAxis()).with(PERSISTENT, ctx.getPlayer().isSneaking()).with(Properties.WATERLOGGED, fluidState5.getFluid() == Fluids.WATER).with(ChimneyBlock.LARGE, false);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (random.nextInt(5) == 0 && world.getBlockState(pos.up()).isAir() && state.get(AGE).intValue() < 3) {
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
                DustParticleEffect dirtPartical = new DustParticleEffect(new Vector3f(Vec3d.unpackRgb(0x383838).toVector3f()), 1.0f);
                world.addParticle(dirtPartical, pos.getX() + double12, pos.getY() + double14, pos.getZ() + double16, 0.0, 0.0, 0.0);
            }
        }
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return state.get(BurntPillarBlock.AGE) < 3 && !state.get(BurntPillarBlock.PERSISTENT);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        world.setBlockState(pos, state.with(Properties.AXIS, state.get(Properties.AXIS)).with(ChimneyBlock.WATERLOGGED, state.get(ChimneyBlock.WATERLOGGED).booleanValue()).with(ChimneyBlock.LARGE, state.get(ChimneyBlock.LARGE).booleanValue()).with(AGE, state.get(AGE).intValue() + 1).with(PERSISTENT, state.get(PERSISTENT).booleanValue()));
    }

    @Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
		stateManager.add(AGE, Properties.AXIS, PERSISTENT, ChimneyBlock.LARGE, ChimneyBlock.WATERLOGGED);
	}

    static {
        AGE = IntProperty.of("age", 0, 3);
        PERSISTENT = BooleanProperty.of("persistent");
    }
}
