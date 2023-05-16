package com.tylervp.block;

import net.minecraft.util.math.random.Random;

import org.joml.Vector3f;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
//import net.minecraft.world.WorldAccess;

public class OxidizedBlock extends Block {
    protected int oxidizedState;
    protected boolean infectable;

    protected final static BooleanProperty INFECTABLE;

    public OxidizedBlock(int oxidizedState, boolean infectable, AbstractBlock.Settings settings) {
        super(settings);
        this.oxidizedState = oxidizedState;
        this.infectable = infectable;
        this.setDefaultState(((BlockState)this.stateManager.getDefaultState().with(INFECTABLE, infectable)));
    }

    /* private boolean allowOxidize(ServerWorld world, BlockPos pos, Block block){
        return (
                    world.getBlockState(pos.north()).isOf(block) ||
                    world.getBlockState(pos.east()).isOf(block) ||
                    world.getBlockState(pos.south()).isOf(block) |
                    world.getBlockState(pos.west()).isOf(block) ||
                    world.getBlockState(pos.up()).isOf(block) ||
                    world.getBlockState(pos.down()).isOf(block)
                );
    } */

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(state.isOf(this)) {
            ItemStack playerItem = player.getStackInHand(hand);
            if(playerItem.getItem() == Items.HONEYCOMB){
                if(state.isOf(MBMBlocks.IRON_BLOCK)) {
                    world.setBlockState(pos, Blocks.IRON_BLOCK.getDefaultState());
                } else if(state.isOf(MBMBlocks.EXPOSED_IRON)) {
                    world.setBlockState(pos, MBMBlocks.WAXED_EXPOSED_IRON.getDefaultState());
                } else if(state.isOf(MBMBlocks.DEGRADED_IRON)) {
                    world.setBlockState(pos, MBMBlocks.WAXED_DEGRADED_IRON.getDefaultState());
                } else if(state.isOf(MBMBlocks.WEATHERED_IRON)) {
                    world.setBlockState(pos, MBMBlocks.WAXED_WEATHERED_IRON.getDefaultState());
                } else if(state.isOf(MBMBlocks.RUSTED_IRON)) {
                    world.setBlockState(pos, MBMBlocks.WAXED_RUSTED_IRON.getDefaultState());
                }
                if(world.isClient){ spawnParticlesWax(world, pos); }
                return ActionResult.success(world.isClient);
            }
        }
        return ActionResult.PASS;
    }


    private static boolean oxidizeOnAnySide(BlockView world, BlockPos pos) {
        boolean boolean3 = false;
        BlockPos.Mutable mutable4 = pos.mutableCopy();
        for (final Direction lv2 : Direction.values()) {
            BlockState blockState9 = world.getBlockState(mutable4);
            if (lv2 != Direction.DOWN || oxidizeIn(blockState9)) {
                mutable4.set(pos, lv2);
                blockState9 = world.getBlockState(mutable4);
                if (oxidizeIn(blockState9) && !blockState9.isSideSolidFullSquare(world, pos, lv2.getOpposite())) {
                    boolean3 = true;
                    break;
                }
            }
        }
        return boolean3;
    }
    
    private static boolean oxidizeIn(BlockState state) {
        return state.getFluidState().isIn(FluidTags.WATER);
    }

    private static boolean heatOnAnySide(BlockView world, BlockPos pos, Block block) {
        return world.getBlockState(pos.north()).isOf(block) || world.getBlockState(pos.east()).isOf(block) || world.getBlockState(pos.south()).isOf(block) || world.getBlockState(pos.west()).isOf(block) || world.getBlockState(pos.up()).isOf(block) || world.getBlockState(pos.down()).isOf(block);
    }

    public int oxidizeState() {
        return this.oxidizedState;
    }

    public boolean infectable() {
        return this.infectable;
    }

    private boolean allowInfection(BlockState state, BlockView world, BlockPos pos){

        if(state.get(OxidizedBlock.INFECTABLE)){
            if(world.getBlockState(pos.north()).getBlock().getClass().getName() == this.getClass().getName()){
                if(!world.getBlockState(pos.north()).get(OxidizedBlock.INFECTABLE)){
                    return true;
                }
            }
            if(world.getBlockState(pos.east()).getBlock().getClass().getName() == this.getClass().getName()){
                if(!world.getBlockState(pos.east()).get(OxidizedBlock.INFECTABLE)){
                    return true;
                }
            }
            if(world.getBlockState(pos.south()).getBlock().getClass().getName() == this.getClass().getName()){
                if(!world.getBlockState(pos.south()).get(OxidizedBlock.INFECTABLE)){
                    return true;
                }
            }
            if(world.getBlockState(pos.west()).getBlock().getClass().getName() == this.getClass().getName()){
                if(!world.getBlockState(pos.west()).get(OxidizedBlock.INFECTABLE)){
                    return true;
                }
            }
            if(world.getBlockState(pos.up()).getBlock().getClass().getName() == this.getClass().getName()){
                if(!world.getBlockState(pos.up()).get(OxidizedBlock.INFECTABLE)){
                    return true;
                }
            }
            if(world.getBlockState(pos.down()).getBlock().getClass().getName() == this.getClass().getName()){
                if(!world.getBlockState(pos.down()).get(OxidizedBlock.INFECTABLE)){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {

        if((heatOnAnySide(world, pos, Blocks.LAVA) || heatOnAnySide(world, pos, Blocks.FIRE) || heatOnAnySide(world, pos, Blocks.CAMPFIRE) || heatOnAnySide(world, pos, Blocks.SOUL_CAMPFIRE))){
                switch (oxidizeState() - 1) {
                    case 0:
                        world.syncWorldEvent(1501, pos, 0);
                        world.setBlockState(pos, MBMBlocks.HEATED_IRON.getDefaultState());    
                        //world.setBlockState(pos, MBMBlocks.IRON_BLOCK.getDefaultState());
                        break;
                    case 1:
                        world.setBlockState(pos, MBMBlocks.EXPOSED_IRON.getDefaultState());
                        break;
                    case 2:
                        world.setBlockState(pos, MBMBlocks.DEGRADED_IRON.getDefaultState());
                        break;
                    case 3:
                        world.setBlockState(pos, MBMBlocks.WEATHERED_IRON.getDefaultState());
                        break;
                    case 4:
                        world.setBlockState(pos, MBMBlocks.RUSTED_IRON.getDefaultState());
                        break;
                    case 5:
                        world.setBlockState(pos, MBMBlocks.RUSTED_IRON.getDefaultState());
                        break;
                    default:
                        world.syncWorldEvent(1501, pos, 0);
                        world.setBlockState(pos, MBMBlocks.HEATED_IRON.getDefaultState());
                        return;
                }
        } else if(((oxidizeOnAnySide(world, pos) || world.isRaining()) || !infectable()) || allowInfection(state, world, pos)){
            if (random.nextFloat() < 0.05688889F) {
                switch (oxidizeState() + 1) {
                    case 1:
                        world.setBlockState(pos, MBMBlocks.EXPOSED_IRON.getDefaultState());
                        break;
                    case 2:
                        world.setBlockState(pos, MBMBlocks.DEGRADED_IRON.getDefaultState());
                        break;
                    case 3:
                        world.setBlockState(pos, MBMBlocks.WEATHERED_IRON.getDefaultState());
                        break;
                    case 4:
                        world.setBlockState(pos, MBMBlocks.RUSTED_IRON.getDefaultState());
                        break;
                    case 5:
                        world.setBlockState(pos, MBMBlocks.RUSTED_IRON.getDefaultState());
                        break;
                    default:
                        return;
                }
             }
        }
    }

    /* @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState newState, WorldAccess world, BlockPos pos, BlockPos posFrom) {
        if((heatOnAnySide(world, pos, Blocks.LAVA) || heatOnAnySide(world, pos, Blocks.FIRE) || heatOnAnySide(world, pos, Blocks.CAMPFIRE) || heatOnAnySide(world, pos, Blocks.SOUL_CAMPFIRE))){
            if(state.isOf(MBMBlocks.IRON_BLOCK)){
                world.syncWorldEvent(1501, pos, 0);  
                return MBMBlocks.HEATED_IRON.getDefaultState();
            }
        }
        return super.getStateForNeighborUpdate(state, direction, newState, world, pos, posFrom);
    } */

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        if(world.isClient){
            if(oxidizeState() != 0 && entity.isSprinting()){
                spawnParticles(world, pos);
            }
        }
        super.onSteppedOn(world, pos, state, entity);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        if(world.isClient){
            if(oxidizeState() != 0){
                spawnParticles(world, pos);
            }
        }
        super.onPlaced(world, pos, state, placer, itemStack);
    }

    private static void spawnParticlesWax(World world, BlockPos pos) {
        //double double3 = 0.5625;
        for (int i = 0; i < 4; i++) {
            Random random5 = world.random;
            for (final Direction lv : Direction.values()) {
                BlockPos blockPos10 = pos.offset(lv);
                if (!world.getBlockState(blockPos10).isOpaqueFullCube(world, blockPos10)) {
                    Direction.Axis axis11 = lv.getAxis();
                    double double12 = (axis11 == Direction.Axis.X) ? (0.5 + 0.5625 * lv.getOffsetX()) : random5.nextFloat();
                    double double14 = (axis11 == Direction.Axis.Y) ? (0.5 + 0.5625 * lv.getOffsetY()) : random5.nextFloat();
                    double double16 = (axis11 == Direction.Axis.Z) ? (0.5 + 0.5625 * lv.getOffsetZ()) : random5.nextFloat();
                    //DustParticleEffect dirtPartical = new DustParticleEffect(0.93f, 0.63f, 0.45f, 1.0f);
                    world.addParticle(ParticleTypes.HAPPY_VILLAGER, pos.getX() + double12, pos.getY() + double14, pos.getZ() + double16, 0.0, 0.0, 0.0);
                }
            }
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
                DustParticleEffect dirtPartical = new DustParticleEffect(new Vector3f(Vec3d.unpackRgb(0x56402A).toVector3f()), 1.0f);
                world.addParticle(dirtPartical, pos.getX() + double12, pos.getY() + double14, pos.getZ() + double16, 0.0, 0.0, 0.0);
            }
        }
    }

    @Override
    protected void appendProperties(Builder<Block, BlockState> builder) {
        builder.add(OxidizedBlock.INFECTABLE);
    }

    static {
        INFECTABLE = BooleanProperty.of("infectable");
    }
}