package com.tylervp.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LandingBlock;
import net.minecraft.block.Material;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

public class TreeFruitBlock extends Block implements LandingBlock {

    public static final BooleanProperty UP, WAXED;

    public TreeFruitBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(UP, false).with(WAXED, false));
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        if(!ctx.getWorld().getBlockState(ctx.getBlockPos().down()).isOf(Blocks.HOPPER)) {
            return this.getDefaultState().with(UP, ctx.getSide().getOpposite() == Direction.UP);
        }
        return Blocks.AIR.getDefaultState();
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(state.isOf(this)) {
            ItemStack playerItem = player.getStackInHand(hand);
            if(playerItem.getItem() == Items.HONEYCOMB){
                world.setBlockState(pos, state.with(WAXED, true), NOTIFY_ALL);
                if(world.isClient){ spawnParticlesWax(world, pos); }
                return ActionResult.success(world.isClient);
            } else if(playerItem.getItem() == Items.BONE_MEAL) {
                if(!world.isClient) {
                    if(world.getRandom().nextInt(2) == 0) {
                        Block saplingBlock = Blocks.OAK_SAPLING;

                        if(this == MBMBlocks.PINECONE) {
                            saplingBlock = Blocks.SPRUCE_SAPLING;
                        } else if(this == MBMBlocks.DARK_OAK_ACORN) {
                            saplingBlock = Blocks.DARK_OAK_SAPLING;
                        } else if(this == MBMBlocks.OAK_ACORN) {
                            saplingBlock = Blocks.OAK_SAPLING;
                        } else if(this == MBMBlocks.BIRCH_CATKIN) {
                            saplingBlock = Blocks.BIRCH_SAPLING;
                        }

                        world.setBlockState(pos, saplingBlock.getDefaultState(), NOTIFY_ALL);
                    }
                }

                if(world.isClient){ spawnParticlesGrow(world, pos); }
                return ActionResult.success(world.isClient);
            }
        }
        return ActionResult.PASS;
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
                    world.addParticle(ParticleTypes.WAX_ON, pos.getX() + double12, pos.getY() + double14, pos.getZ() + double16, 0.0, 0.0, 0.0);
                }
            }
        }
    }

    private static void spawnParticlesGrow(World world, BlockPos pos) {
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

    @Override
    protected void appendProperties(Builder<Block, BlockState> builder) {
        builder.add(UP, WAXED);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        if(state.get(UP)) {
            if(world.getBlockState(pos.up()).isOf(Blocks.AIR)) {
                return false;
            }
        } else {
            if(!world.getBlockState(pos.down()).isIn(BlockTags.LEAVES) && !world.getBlockState(pos.down()).isOf(Blocks.HOPPER) && (world.getBlockState(pos.down()).isOf(Blocks.AIR) || !Block.sideCoversSmallSquare(world, pos.down(), Direction.UP))) {
                return false;
            }
        }
        return true;
    }

    @Override
   public PistonBehavior getPistonBehavior(BlockState state) {
      return PistonBehavior.DESTROY;
   }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {

        if(state.get(UP)) {
            if(world.getBlockState(pos.up()).isOf(Blocks.AIR)) {
                if(world.getBlockState(pos.down()).isOf(Blocks.AIR)){
                    world.setBlockState(pos, state.with(UP, false), NOTIFY_ALL);
                    world.scheduleBlockTick(pos, this, this.getFallDelay());
                } else {
                    if(!world.getBlockState(pos.down()).isIn(BlockTags.LEAVES) && !Block.sideCoversSmallSquare(world, pos.down(), Direction.UP)) {
                        if(state.get(WAXED)/*  || world.getBlockState(pos.down()).isOf(Blocks.HOPPER) */) {
                            world.breakBlock(pos, true);
                        } else {
                            world.breakBlock(pos, false);
                        }
                    } else {
                        world.setBlockState(pos, state.with(UP, false), NOTIFY_ALL);
                    }
                }
            }
        } else {
            if(world.getBlockState(pos.down()).isOf(Blocks.AIR)) {
                world.scheduleBlockTick(pos, this, this.getFallDelay());
            }
        }

        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return !state.get(WAXED);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if(!state.get(UP)) {
            if(canGrowOn(world.getBlockState(pos.down())) && world.getBaseLightLevel(pos, 0) >= 9) {
                int growChance = 7;
                int breakChance = 5;

                if(world.isRaining()) {
                    growChance = 3;
                    breakChance = 10;
                }

                if(random.nextInt(breakChance) == 0) {
                    world.breakBlock(pos, false);
                    return;
                }

                if(random.nextInt(growChance) == 0) {
                    BlockState sapling;

                    if(this == MBMBlocks.PINECONE) {
                        sapling = Blocks.SPRUCE_SAPLING.getDefaultState();
                    } else if(this == MBMBlocks.DARK_OAK_ACORN) {
                        sapling = Blocks.DARK_OAK_SAPLING.getDefaultState();
                    } else if(this == MBMBlocks.OAK_ACORN) {
                        sapling = Blocks.OAK_SAPLING.getDefaultState();
                    } else if(this == MBMBlocks.BIRCH_CATKIN) {
                        sapling = Blocks.BIRCH_SAPLING.getDefaultState();
                    } else {
                        sapling = Blocks.DARK_OAK_SAPLING.getDefaultState();
                    }

                    world.setBlockState(pos, sapling);
                }
            }
        }
    }

    public boolean canGrowOn(BlockState state) {
        return state.isIn(BlockTags.DIRT);
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!TreeFruitBlock.canFallThrough(world.getBlockState(pos.down())) || pos.getY() < world.getBottomY()) {
            return;
        }
        FallingBlockEntity fallingBlockEntity = FallingBlockEntity.spawnFromBlock(world, pos, state);
        this.configureFallingBlockEntity(fallingBlockEntity, world, state);
    }

    protected void configureFallingBlockEntity(FallingBlockEntity entity, ServerWorld world, BlockState state) {
        entity.dropItem = state.get(WAXED);
    }

    @Override
    public void onLanding(World world, BlockPos pos, BlockState fallingBlockState, BlockState currentStateInPos, FallingBlockEntity fallingBlockEntity) {
        if(world.getBlockState(pos.down()).isOf(Blocks.HOPPER)) {
            world.breakBlock(pos, true);
        }
        LandingBlock.super.onLanding(world, pos, fallingBlockState, currentStateInPos, fallingBlockEntity);
    }

    protected int getFallDelay() {
        return 2;
    }

    public static boolean canFallThrough(BlockState state) {
        Material material = state.getMaterial();
        return state.isAir() || state.isIn(BlockTags.FIRE) || material.isLiquid() || state.isReplaceable();
    }

    static {
        UP = BooleanProperty.of("up");

        WAXED = BooleanProperty.of("waxed");
    }
}
