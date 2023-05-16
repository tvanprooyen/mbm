package com.tylervp.block;

import net.minecraft.util.math.random.Random;

import com.tylervp.item.MBMItems;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Fertilizable;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.mob.RavagerEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

public class CottonCropBlock extends Block implements Fertilizable {
    public static final IntProperty AGE;
    private static final VoxelShape[] AGE_TO_SHAPE;
    
    protected CottonCropBlock(AbstractBlock.Settings settings) {
        super(settings.offset(OffsetType.XZ));
        this.setDefaultState(((BlockState)this.stateManager.getDefaultState()).with(this.getAgeProperty(), 0));
    }
    
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return CottonCropBlock.AGE_TO_SHAPE[state.<Integer>get((Property<Integer>)this.getAgeProperty())];
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {

        return (BlockState)this.getDefaultState();
    }

    /* @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        
        
        return super.onUse(state, world, pos, player, hand, hit);
    } */

    
    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState newState, WorldAccess world, BlockPos pos, BlockPos posFrom) {
        if (!state.canPlaceAt(world, pos)) {
            return Blocks.AIR.getDefaultState();
        }
        return super.getStateForNeighborUpdate(state, direction, newState, world, pos, posFrom);
    }

    @Override
    public boolean isTransparent(BlockState state, BlockView world, BlockPos pos) {
        return state.getFluidState().isEmpty();
    }
    
    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return (type == NavigationType.AIR && !this.collidable) || super.canPathfindThrough(state, world, pos, type);
    }
    
    
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isOf(MBMBlocks.PACKEDMUD);
    }

    protected boolean canPlantOnTopSlow(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isOf(MBMBlocks.DEAD_GRASS_BLOCK) || floor.isOf(Blocks.GRASS_BLOCK) || floor.isOf(Blocks.DIRT) || floor.isOf(Blocks.COARSE_DIRT) || floor.isOf(Blocks.PODZOL);
    }
    
    public IntProperty getAgeProperty() {
        return CottonCropBlock.AGE;
    }
    
    public int getMaxAge() {
        return 8;
    }
    
    protected int getAge(BlockState state) {
        return state.<Integer>get((Property<Integer>)this.getAgeProperty());
    }
    
    public BlockState withAge(int age, BlockState state) {
        return ((BlockState)this.getDefaultState()).with(this.getAgeProperty(), age);
    }
    
    public boolean isMature(BlockState state) {
        return state.<Integer>get((Property<Integer>)this.getAgeProperty()) >= this.getMaxAge();
    }
    
    @Override
    public boolean hasRandomTicks(BlockState state) {
        return !this.isMature(state);
    }
    
    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.getBaseLightLevel(pos, 0) >= 9) {
            int integer6 = this.getAge(state);
            if (integer6 < this.getMaxAge()) {

                if(canPlantOnTopSlow(state,world,pos)){
                    if (random.nextInt((int)(75.0f) + 1) == 0) {
                        world.setBlockState(pos, this.withAge(integer6 + 1, state), 2);
                    }
                } else {
                    if (random.nextInt((int)(25.0f) + 1) == 0) {
                        world.setBlockState(pos, this.withAge(integer6 + 1, state), 2);
                    }
                }
                
            }
        }
    }
    
    public void applyGrowth(World world, BlockPos pos, BlockState state) {
        int integer5 = this.getAge(state) + this.getGrowthAmount(world);
        int integer6 = this.getMaxAge();
        if (integer5 > integer6) {
            integer5 = integer6;
        }
        world.setBlockState(pos, this.withAge(integer5,state), 2);
    }
    
    protected int getGrowthAmount(World world) {
        return MathHelper.nextInt(world.random, 2, 5);
    }
    
    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockPos5 = pos.down();
        return (world.getBaseLightLevel(pos, 0) >= 8 || world.isSkyVisible(pos)) && (this.canPlantOnTop(world.getBlockState(blockPos5), world, blockPos5) || this.canPlantOnTopSlow(world.getBlockState(blockPos5), world, blockPos5));
    }
    
    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (entity instanceof RavagerEntity && world.getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING)) {
            world.breakBlock(pos, true, entity);
        }
        super.onEntityCollision(state, world, pos, entity);
    }
    
    @Environment(EnvType.CLIENT)
    protected ItemConvertible getSeedsItem() {
        return MBMItems.RICE_SEEDS;
    }
    
    @Environment(EnvType.CLIENT)
    @Override
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        return new ItemStack(this.getSeedsItem());
    }
    
    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state, boolean isClient) {
        return !this.isMature(state);
    }
    
    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }
    
    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        this.applyGrowth(world, pos, state);
    }
    
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(CottonCropBlock.AGE);
    }
    
    static {
        AGE = IntProperty.of("age", 0, 8);
        AGE_TO_SHAPE = new VoxelShape[] { Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 2.0, 16.0), Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 4.0, 16.0), Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 6.0, 16.0), Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 8.0, 16.0), Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 10.0, 16.0), Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 12.0, 16.0), Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 14.0, 16.0), Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 16.0), Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 16.0) };
    }
}
