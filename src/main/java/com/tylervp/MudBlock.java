package com.tylervp;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.block.FluidDrainable;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContext;
//import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Property;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.state.property.Properties;

public class MudBlock extends Block implements FluidDrainable {
    public static final IntProperty LEVEL;
    protected final FlowableFluid fluid;
    private final List<FluidState> statesByLevel;
    public static final VoxelShape COLLISION_SHAPE;
    protected boolean firstUpdate;
    protected boolean touchingWater;
    
    protected MudBlock(FlowableFluid fluid, AbstractBlock.Settings settings) {
        super(settings);
        this.fluid = fluid;
        this.firstUpdate = true;
        this.touchingWater = true;
        (this.statesByLevel = Lists.newArrayList()).add(fluid.getStill(false));
        for (int i = 1; i < 8; ++i) {
            this.statesByLevel.add(fluid.getFlowing(8 - i, false));
        }
        this.statesByLevel.add(fluid.getFlowing(8, true));
        this.setDefaultState(((BlockState)this.stateManager.getDefaultState()).with(FluidBlock.LEVEL, 0));
    }
    
    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (context.isAbove(FluidBlock.COLLISION_SHAPE, pos, true) && state.<Integer>get((Property<Integer>)FluidBlock.LEVEL) == 0 && context.method_27866(world.getFluidState(pos.up()), this.fluid)) {
            return FluidBlock.COLLISION_SHAPE;
        }
        return VoxelShapes.empty();
    }
    
    @Override
    public boolean hasRandomTicks(BlockState state) {
        return state.getFluidState().hasRandomTicks();
    }
    
    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        state.getFluidState().onRandomTick(world, pos, random);
    }
    
    @Override
    public boolean isTranslucent(BlockState state, BlockView world, BlockPos pos) {
        return false;
    }
    
    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return !this.fluid.isIn(FluidTags.LAVA);
    }
    
    @Override
    public FluidState getFluidState(BlockState state) {
        int integer3 = state.<Integer>get((Property<Integer>)FluidBlock.LEVEL);
        return this.statesByLevel.get(Math.min(integer3, 8));
    }
    
    //@Environment(EnvType.CLIENT)
    @Override
    public boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
        return stateFrom.getFluidState().getFluid().matchesType(this.fluid);
    }
    
    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.INVISIBLE;
    }
    
    @Override
    public List<ItemStack> getDroppedStacks(BlockState state, LootContext.Builder builder) {
        return Collections.<ItemStack>emptyList();
    }
    
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.empty();
    }
    
    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        if (this.receiveNeighborFluids(world, pos, state)) {
            world.getFluidTickScheduler().schedule(pos, state.getFluidState().getFluid(), this.fluid.getTickRate(world));
        }

        if (world.getDimension().isUltrawarm()) {
            world.setBlockState(pos, Blocks.DIRT.getDefaultState());
            this.playExtinguishEvent(world, pos);
        }
    }

    private void playExtinguishEvent(WorldAccess world, BlockPos pos) {
        world.syncWorldEvent(1501, pos, 0);
    }
    
    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState newState, WorldAccess world, BlockPos pos, BlockPos posFrom) {
        if (state.getFluidState().isStill() || newState.getFluidState().isStill()) {
            world.getFluidTickScheduler().schedule(pos, state.getFluidState().getFluid(), this.fluid.getTickRate(world));
        }
        return super.getStateForNeighborUpdate(state, direction, newState, world, pos, posFrom);
    }
    
    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
        if (this.receiveNeighborFluids(world, pos, state)) {
            world.getFluidTickScheduler().schedule(pos, state.getFluidState().getFluid(), this.fluid.getTickRate(world));
        }
        
        BlockState blockstatedown = world.getBlockState(pos.down());
        FluidState fluidstatedown = world.getFluidState(pos.down());
        if(fluidstatedown.isIn(FluidTags.WATER)){
            world.setBlockState(pos.down(), this.getDefaultState().with(FluidBlock.LEVEL, 8));
         } else if(blockstatedown.isOf(this) && !blockstatedown.isOf(Blocks.WATER)){
           world.setBlockState(pos.down(), moreblocksmod.PACKEDMUD.getDefaultState());
        }
    }
    
    private boolean receiveNeighborFluids(World world, BlockPos pos, BlockState state) {

        for (final Direction lv : Direction.values()) {
            if (lv != Direction.DOWN) {
                BlockPos blockPos10 = pos.offset(lv);
                if (world.getFluidState(blockPos10).isIn(FluidTags.LAVA)) {
                    world.setBlockState(pos, Blocks.DIRT.getDefaultState());
                    this.playExtinguishSound(world, pos);
                    return false;
                }
            }
        }

        return true;
    }
    
    private void playExtinguishSound(WorldAccess world, BlockPos pos) {
        world.syncWorldEvent(1501, pos, 0);
    }
    
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FluidBlock.LEVEL);
    }

    
    
    @Override
    public Fluid tryDrainFluid(WorldAccess world, BlockPos pos, BlockState state) {
        if (state.<Integer>get((Property<Integer>)FluidBlock.LEVEL) == 0) {
            world.setBlockState(pos, Blocks.AIR.getDefaultState(), 11);
            return this.fluid;
        }
        return Fluids.EMPTY;
    }
    
    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
                
                //this.updateVelocity(state, entity);
                
                //this.updateEffects(state, entity, world);
                //checkWaterState(state, entity, world);
                //entity.updateMovementInFluid(MudTags.MUD, 0.014);
                this.updateMovementInMud(entity, 0.014);
                this.updateVelocity(state, entity, pos);
                //applyWaterMovement(entity);
                //StatusEffects.BLINDNESS

    }


    public boolean updateMovementInMud(Entity entity, double double3) {
        Box box5 = entity.getBoundingBox().contract(0.001);
        int integer6 = MathHelper.floor(box5.minX);
        int integer7 = MathHelper.ceil(box5.maxX);
        int integer8 = MathHelper.floor(box5.minY);
        int integer9 = MathHelper.ceil(box5.maxY);
        int integer10 = MathHelper.floor(box5.minZ);
        int integer11 = MathHelper.ceil(box5.maxZ);
        if (!entity.world.isRegionLoaded(integer6, integer8, integer10, integer7, integer9, integer11)) {
            return false;
        }
        double double12 = 0.0;
        boolean boolean14 = entity.canFly();
        boolean boolean15 = false;
        Vec3d vec3d16 = Vec3d.ZERO;
        int integer17 = 0;
        BlockPos.Mutable mutable18 = new BlockPos.Mutable();
        for (int p = integer6; p < integer7; ++p) {
            for (int q = integer8; q < integer9; ++q) {
                for (int r = integer10; r < integer11; ++r) {
                    mutable18.set(p, q, r);
                    FluidState fluidState22 = entity.world.getFluidState(mutable18);
                    if (fluidState22.getFluid() == moreblocksmod.FLOWING_MUD || fluidState22.getFluid() == moreblocksmod.STILL_MUD) {
                        double double23 = q + fluidState22.getHeight(entity.world, mutable18);
                        if (double23 >= box5.minY) {
                            boolean15 = true;
                            double12 = Math.max(double23 - box5.minY, double12)  / 5;
                            if (boolean14) {
                                Vec3d vec3d25 = fluidState22.getVelocity(entity.world, mutable18);
                                if (double12 < 0.4) {
                                    vec3d25 = vec3d25.multiply(double12);
                                }
                                vec3d16 = vec3d16.add(vec3d25);
                                ++integer17;
                            }
                        }
                    }
                }
            }
        }
        if (vec3d16.length() > 0.0) {
            if (integer17 > 0) {
                vec3d16 = vec3d16.multiply(1.0 / integer17);
            }
            if (!(entity instanceof PlayerEntity)) {
                vec3d16 = vec3d16.normalize();
            }
            Vec3d vec3d19 = entity.getVelocity();
            vec3d16 = vec3d16.multiply(double3 * 1.0);
            //double double20 = 0.003;
            if (Math.abs(vec3d19.x) < 0.003 && Math.abs(vec3d19.z) < 0.003 && vec3d16.length() < 0.0045000000000000005) {
                vec3d16 = vec3d16.normalize().multiply(0.0045000000000000005);
            }
            entity.setVelocity(entity.getVelocity().add(vec3d16));
        }
        return boolean15;
    }

    protected SoundEvent getSwimSound() {
        return SoundEvents.ENTITY_GENERIC_SWIM;
    }
    
    protected SoundEvent getSplashSound() {
        return SoundEvents.ENTITY_GENERIC_SPLASH;
    }
    
    protected SoundEvent getHighSpeedSplashSound() {
        return SoundEvents.ENTITY_GENERIC_SPLASH;
    }

    private void updateVelocity(BlockState state, Entity entity, BlockPos pos) {

        double velocityX = entity.getVelocity().x;
        double velocityY = entity.getVelocity().y;
        double velocityZ = entity.getVelocity().z;

        if(!(entity instanceof ItemEntity)){
            if(entity.getVelocity().y < 0){
                velocityY = (double)MathHelper.abs((float)(velocityY * 0.001));
            }

            if(state.getFluidState().getFluid() == moreblocksmod.STILL_MUD) {
                velocityX = velocityX / 5;
                velocityZ = velocityZ / 5;
            }

            entity.setVelocity(velocityX, velocityY, velocityZ);
            entity.fallDistance = 0.0f;
        }
    }

    //On Break Without Silktouch replacewith mud. Silktouch will yield this block
    //Slow down tick speed, or forward speed in flowing mud


    
    /*private void updateEffects(BlockState state, Entity entity, World world){
        Entity entity2 = (entity.hasPassengers() && entity.getPrimaryPassenger() != null) ? entity.getPrimaryPassenger() : entity;
        float float3 = (entity2 == entity) ? 0.2f : 0.9f;
        Vec3d vec3d4 = entity2.getVelocity();
        float float5 = MathHelper.sqrt(vec3d4.x * vec3d4.x * 0.20000000298023224 + vec3d4.y * vec3d4.y + vec3d4.z * vec3d4.z * 0.20000000298023224) * float3;
        if (float5 > 1.0f) {
            float5 = 1.0f;
        }
        if (float5 < 0.25) {
            entity.playSound(this.getSplashSound(), float5, 1.0f + (world.random.nextFloat() - world.random.nextFloat()) * 0.4f);
        }
        else {
            entity.playSound(this.getHighSpeedSplashSound(), float5, 1.0f + (world.random.nextFloat() - world.random.nextFloat()) * 0.4f);
        }


        float float6 = (float)MathHelper.floor(entity.getY());
        float dim1 = entity.getDimensions(entity.getPose()).width;
        for (int i = 0; i < 1.0f + dim1 * 20.0f; ++i) {
            double double8 = (world.random.nextDouble() * 2.0 - 1.0) * dim1;
            double double10 = (world.random.nextDouble() * 2.0 - 1.0) * dim1;
            entity.world.addParticle(ParticleTypes.BUBBLE, entity.getX() + double8, float6 + 1.0f, entity.getZ() + double10, vec3d4.x, vec3d4.y - world.random.nextDouble() * 0.20000000298023224, vec3d4.z);
        }
        for (int i = 0; i < 1.0f + dim1 * 20.0f; ++i) {
            double double8 = (world.random.nextDouble() * 2.0 - 1.0) * dim1;
            double double10 = (world.random.nextDouble() * 2.0 - 1.0) * dim1;
            world.addParticle(ParticleTypes.SPLASH, entity.getX() + double8, float6 + 1.0f, entity.getZ() + double10, vec3d4.x, vec3d4.y, vec3d4.z);
        }
    }*/
    
    static {
        LEVEL = Properties.LEVEL_15;
        COLLISION_SHAPE = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 8.0, 16.0);
    }
}
