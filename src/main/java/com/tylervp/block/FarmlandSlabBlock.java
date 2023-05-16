package com.tylervp.block;

import net.minecraft.util.math.random.Random;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.AttachedStemBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.PistonExtensionBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StemBlock;
import net.minecraft.block.enums.SlabType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

public class FarmlandSlabBlock extends SlabBlock {
    public static final EnumProperty<SlabType> TYPE;
    public static final BooleanProperty WATERLOGGED;
    public static final IntProperty MOISTURE;
    protected static final VoxelShape SHAPE;
    
    protected FarmlandSlabBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(((BlockState)this.stateManager.getDefaultState()).with(FarmlandSlabBlock.MOISTURE, 0).with(TYPE, SlabType.BOTTOM).with(WATERLOGGED, false));
    }
    
    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState newState, WorldAccess world, BlockPos pos, BlockPos posFrom) {
        if (direction == Direction.UP && !state.canPlaceAt(world, pos)) {
            //world.getBlockTickScheduler().schedule(pos, this, 1);
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
            
        }
        return super.getStateForNeighborUpdate(state, direction, newState, world, pos, posFrom);
    }
    
    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockState blockState5 = world.getBlockState(pos.up());
        return !blockState5.getMaterial().isSolid() || blockState5.getBlock() instanceof FenceGateBlock || blockState5.getBlock() instanceof PistonExtensionBlock;
    }
    
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        if (!this.getDefaultState().canPlaceAt(ctx.getWorld(), ctx.getBlockPos())) {
            return MBMBlocks.DIRT_SLAB.getDefaultState();
        }
        return super.getPlacementState(ctx);
    }
    
    @Override
    public boolean hasSidedTransparency(BlockState state) {
        return true;
    }
    
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return FarmlandSlabBlock.SHAPE;
    }
    
    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!state.canPlaceAt(world, pos)) {
            setToDirt(state, world, pos);
        }
    }
    
    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        int integer6 = state.<Integer>get((Property<Integer>)FarmlandSlabBlock.MOISTURE);
        if (isWaterNearby(world, pos) || world.hasRain(pos.up())) {
            if (integer6 < 7) {
                world.setBlockState(pos, (BlockState)state.with(FarmlandSlabBlock.MOISTURE, 7), 2);
            }
        }
        else if (integer6 > 0) {
            world.setBlockState(pos, (BlockState)state.with(FarmlandSlabBlock.MOISTURE, integer6 - 1), 2);
        }
        else if (!hasCrop(world, pos)) {
            setToDirt(state, world, pos);
        }
    }

    @Override
    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        if (!world.isClient && world.random.nextFloat() < fallDistance - 0.5f && entity instanceof LivingEntity && (entity instanceof PlayerEntity || world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING)) && entity.getWidth() * entity.getWidth() * entity.getHeight() > 0.512f) {
            setToDirt(world.getBlockState(pos), world, pos);
        }
        super.onLandedUpon(world, state, pos, entity, fallDistance);
    }
    
    public static void setToDirt(BlockState state, World world, BlockPos pos) {
        world.setBlockState(pos, Block.pushEntitiesUpBeforeBlockChange(state, Blocks.DIRT.getDefaultState(), world, pos));
    }
    
    private static boolean hasCrop(BlockView world, BlockPos pos) {
        Block block3 = world.getBlockState(pos.up()).getBlock();
        return block3 instanceof CropBlock || block3 instanceof StemBlock || block3 instanceof AttachedStemBlock;
    }
    
    private static boolean isWaterNearby(WorldView world, BlockPos pos) {
        for (final BlockPos lv : BlockPos.iterate(pos.add(-4, 0, -4), pos.add(4, 1, 4))) {
            if (world.getFluidState(lv).isIn(FluidTags.WATER)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FarmlandSlabBlock.MOISTURE, FarmlandSlabBlock.TYPE, FarmlandSlabBlock.WATERLOGGED);
    }
    
    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }
    
    static {
        TYPE = Properties.SLAB_TYPE;
        WATERLOGGED = Properties.WATERLOGGED;
        MOISTURE = Properties.MOISTURE;
        SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 15.0, 16.0);
    }
}

