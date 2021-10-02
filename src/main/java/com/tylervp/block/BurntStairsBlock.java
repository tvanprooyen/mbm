package com.tylervp.block;

import java.util.Random;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.block.enums.StairShape;
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
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3f;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class BurntStairsBlock extends StairsBlockExtend {
    public static final IntProperty AGE;
    public static final BooleanProperty PERSISTENT;

    public BurntStairsBlock(BlockState baseBlockState, AbstractBlock.Settings settings) {
        super(baseBlockState, settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(BurntPillarBlock.AGE, 0).with(BurntPillarBlock.PERSISTENT, false).with(StairsBlock.FACING, Direction.NORTH).with(StairsBlock.HALF, BlockHalf.BOTTOM).with(StairsBlock.SHAPE, StairShape.STRAIGHT).with(StairsBlock.WATERLOGGED, false));
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
        Direction direction = ctx.getSide();
		BlockPos blockPos = ctx.getBlockPos();
		FluidState fluidState = ctx.getWorld().getFluidState(blockPos);

        int age = 0;
            if(fluidState.getFluid() == Fluids.WATER){
                age = 3;
            }

        spawnParticles(ctx.getWorld(), blockPos);
        return this.getDefaultState().with(BurntPillarBlock.AGE, age).with(BurntPillarBlock.PERSISTENT, ctx.getPlayer().isSneaking()).with(StairsBlock.FACING, ctx.getPlayerFacing()).with(StairsBlock.HALF, direction != Direction.DOWN && (direction == Direction.UP || !(ctx.getHitPos().y - (double)blockPos.getY() > 0.5D)) ? BlockHalf.BOTTOM : BlockHalf.TOP).with(StairsBlock.WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
		if ((Boolean)state.get(WATERLOGGED)) {
			world.getFluidTickScheduler().schedule(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
		}

		return direction.getAxis().isHorizontal() ? (BlockState)state.with(StairsBlock.SHAPE, getStairShape(state, world, pos)).with(BurntPillarBlock.AGE, state.get(BurntPillarBlock.AGE)).with(BurntPillarBlock.PERSISTENT, state.get(BurntPillarBlock.PERSISTENT)) : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
	}

    private static StairShape getStairShape(BlockState state, BlockView world, BlockPos pos) {
		Direction direction = (Direction)state.get(FACING);
		BlockState blockState = world.getBlockState(pos.offset(direction));
		if (isStairs(blockState) && state.get(HALF) == blockState.get(HALF)) {
			Direction direction2 = (Direction)blockState.get(FACING);
			if (direction2.getAxis() != ((Direction)state.get(FACING)).getAxis() && isDifferentOrientation(state, world, pos, direction2.getOpposite())) {
				if (direction2 == direction.rotateYCounterclockwise()) {
					return StairShape.OUTER_LEFT;
				}

				return StairShape.OUTER_RIGHT;
			}
		}

		BlockState blockState2 = world.getBlockState(pos.offset(direction.getOpposite()));
		if (isStairs(blockState2) && state.get(HALF) == blockState2.get(HALF)) {
			Direction direction3 = (Direction)blockState2.get(FACING);
			if (direction3.getAxis() != ((Direction)state.get(FACING)).getAxis() && isDifferentOrientation(state, world, pos, direction3)) {
				if (direction3 == direction.rotateYCounterclockwise()) {
					return StairShape.INNER_LEFT;
				}

				return StairShape.INNER_RIGHT;
			}
		}

		return StairShape.STRAIGHT;
	}

    private static boolean isDifferentOrientation(BlockState state, BlockView world, BlockPos pos, Direction dir) {
		BlockState blockState = world.getBlockState(pos.offset(dir));
		return !isStairs(blockState) || blockState.get(FACING) != state.get(FACING) || blockState.get(HALF) != state.get(HALF);
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
                DustParticleEffect dirtPartical = new DustParticleEffect(new Vec3f(Vec3d.unpackRgb(3684408)), 1.0f);
                world.addParticle(dirtPartical, pos.getX() + double12, pos.getY() + double14, pos.getZ() + double16, 0.0, 0.0, 0.0);
            }
        }
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if(state.get(BurntPillarBlock.AGE) < 3 && !state.get(BurntPillarBlock.PERSISTENT)){
            world.setBlockState(pos, state.with(BurntPillarBlock.AGE, state.get(BurntPillarBlock.AGE) + 1).with(BurntPillarBlock.PERSISTENT, state.get(BurntPillarBlock.PERSISTENT)).with(StairsBlock.FACING, state.get(StairsBlock.FACING)).with(StairsBlock.HALF, state.get(StairsBlock.HALF)).with(StairsBlock.WATERLOGGED, state.get(StairsBlock.WATERLOGGED)));
        }
        super.randomTick(state, world, pos, random);
    }

    @Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
		stateManager.add(BurntPillarBlock.AGE, BurntPillarBlock.PERSISTENT, StairsBlock.FACING, StairsBlock.HALF, StairsBlock.SHAPE, StairsBlock.WATERLOGGED);
        
	}

    @Override
    public FluidState getFluidState(BlockState state) {
        if (state.<Boolean>get((Property<Boolean>)StairsBlock.WATERLOGGED)) {
            return Fluids.WATER.getStill(false);
        }
        return super.getFluidState(state);
    }

    static {
        AGE = IntProperty.of("age", 0, 3);
        PERSISTENT = BooleanProperty.of("persistent");
    }
}