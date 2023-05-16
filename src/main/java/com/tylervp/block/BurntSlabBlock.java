package com.tylervp.block;

import net.minecraft.util.math.random.Random;

import org.joml.Vector3f;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.enums.SlabType;
import net.minecraft.entity.Entity;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class BurntSlabBlock extends SlabBlock {
    public static final IntProperty AGE;
    public static final BooleanProperty PERSISTENT;

    public BurntSlabBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(BurntPillarBlock.AGE, 0).with(BurntPillarBlock.PERSISTENT, false).with(SlabBlock.TYPE, SlabType.BOTTOM).with(SlabBlock.WATERLOGGED, false));
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
        BlockPos blockPos = ctx.getBlockPos();
		BlockState blockState = ctx.getWorld().getBlockState(blockPos);

        spawnParticles(ctx.getWorld(), blockPos);

        
		if (blockState.isOf(this)) {
			return blockState.with(SlabBlock.TYPE, SlabType.DOUBLE).with(SlabBlock.WATERLOGGED, false).with(BurntPillarBlock.AGE, blockState.get(BurntPillarBlock.AGE)).with(BurntPillarBlock.PERSISTENT, blockState.get(BurntPillarBlock.PERSISTENT));
		} else {
			FluidState fluidState = ctx.getWorld().getFluidState(blockPos);
            int age = 0;
            if(fluidState.getFluid() == Fluids.WATER){
                age = 3;
            }

			BlockState blockState2 = (BlockState)((BlockState)this.getDefaultState().with(SlabBlock.TYPE, SlabType.BOTTOM)).with(SlabBlock.WATERLOGGED, fluidState.getFluid() == Fluids.WATER).with(BurntPillarBlock.AGE, age).with(BurntPillarBlock.PERSISTENT, ctx.getPlayer().isSneaking());
			Direction direction = ctx.getSide();
			return direction != Direction.DOWN && (direction == Direction.UP || !(ctx.getHitPos().y - (double)blockPos.getY() > 0.5D)) ? blockState2 : (BlockState)blockState2.with(SlabBlock.TYPE, SlabType.TOP).with(BurntPillarBlock.AGE, age).with(BurntPillarBlock.PERSISTENT, ctx.getPlayer().isSneaking());
		}
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
		if ((Boolean)state.get(SlabBlock.WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
		}

		return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
	}

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (random.nextInt(5) == 0 && (world.getBlockState(pos.up()).isAir() || world.getBlockState(pos.up()).isOf(Blocks.FIRE)) && state.get(BurntPillarBlock.AGE) < 3) {
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
            world.setBlockState(pos, state.with(BurntPillarBlock.AGE, state.get(BurntPillarBlock.AGE) + 1).with(BurntPillarBlock.PERSISTENT, state.get(BurntPillarBlock.PERSISTENT)).with(SlabBlock.TYPE, state.get(SlabBlock.TYPE)).with(SlabBlock.WATERLOGGED, state.get(SlabBlock.WATERLOGGED)));
    }

    @Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
		stateManager.add(BurntPillarBlock.AGE, BurntPillarBlock.PERSISTENT, SlabBlock.TYPE, SlabBlock.WATERLOGGED);
	}

    @Override
    public FluidState getFluidState(BlockState state) {
        if (state.get(StairsBlock.WATERLOGGED).booleanValue()) {
            return Fluids.WATER.getStill(false);
        }
        return super.getFluidState(state);
    }

    static {
        AGE = IntProperty.of("age", 0, 3);
        PERSISTENT = BooleanProperty.of("persistent");
    }
}