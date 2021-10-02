package com.tylervp.block;

import java.util.Random;

import com.tylervp.block.enums.WireSides;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class GrapeLog extends Block {
    public static final BooleanProperty LEAVES;

    public GrapeLog(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(LEAVES, true));
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        BlockState stateUp = world.getBlockState(pos.up());
        if(stateUp.isOf(MBMBlocks.GRAPE_LEAVES)) {
            if(!stateUp.get(GrapeLeaves.PLAYERPLACED) && stateUp.get(GrapeLeaves.GROWNDIRECTION) == Direction.DOWN) {
                GrapeLeaves.SetState(GrapeLeaves.StateOrdinals.PARENTREMOVED, true, world, pos.up(), stateUp);
            }
        }
        super.onBreak(world, pos, state, player);
    }

    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        BlockState stateUp = world.getBlockState(pos.up());
        if(stateUp.isOf(MBMBlocks.GRAPE_LEAVES)) {
            if(!stateUp.get(GrapeLeaves.PLAYERPLACED) && stateUp.get(GrapeLeaves.GROWNDIRECTION) == Direction.DOWN) {
                GrapeLeaves.SetState(GrapeLeaves.StateOrdinals.PARENTREMOVED, false, world, pos.up(), stateUp);
            }
        }
        super.onBlockAdded(state, world, pos, oldState, notify);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack playerItem = player.getStackInHand(hand);

        if (playerItem.getItem() == Items.SHEARS && state.get(LEAVES)) {
            world.setBlockState(pos, this.getDefaultState().with(LEAVES, false));
            playerItem.<PlayerEntity>damage(1, player, (p) -> p.sendToolBreakStatus(hand));

            return ActionResult.success(world.isClient);
        } else if(playerItem.getItem() == Items.BONE_MEAL && world.getBlockState(pos.up()).isAir()  || world.getBlockState(pos.up()).isOf(MBMBlocks.COPPER_WIRE)) {
            Random random = world.getRandom();
            if(!world.isClient()) {
                generate(state, (ServerWorld)world, pos, random);
            } else {
                spawnParticlesGrow(world, pos);
            }

            return ActionResult.success(world.isClient);
        }
        return super.onUse(state, world, pos, player, hand, hit);
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
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        super.randomTick(state, world, pos, random);

        generate(state, world, pos, random);
    }

    private void generate(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if(world.getBlockState(pos.up()).isAir() || world.getBlockState(pos.up()).isOf(MBMBlocks.COPPER_WIRE)) {
            if(!world.getBlockState(pos.up()).isOf(this) && !world.getBlockState(pos.up()).isOf(MBMBlocks.COPPER_WIRE)) {
                world.setBlockState(pos.up(), MBMBlocks.GRAPE_SPUR.getDefaultState());
                return;
            }

            if (world.getBlockState(pos.up()).isOf(MBMBlocks.COPPER_WIRE)) {
                if(world.getBlockState(pos.up()).get(Wire.AXIS) != Direction.Axis.Y && world.getBlockState(pos.up()).get(Wire.SIDE) == WireSides.MIDDLE) {
                    world.setBlockState(pos.up(), MBMBlocks.GRAPE_LEAVES.getDefaultState().with(GrapeLeaves.MAXGROW, GrapeLeaves.maxGrowRandomizer(random)).with(GrapeLeaves.SHOWBOTTOM, true).with(GrapeLeaves.GROWNDIRECTION, Direction.DOWN).with(GrapeLeaves.BEARFRUIT, GrapeLeaves.shouldBearFruit(random)).with(GrapeLeaves.GROWVINES, GrapeLeaves.shouldGrowVines(random)).with(GrapeLeaves.COPPERWIRE, true));
                }
            } else {
                world.setBlockState(pos.up(), MBMBlocks.GRAPE_LEAVES.getDefaultState().with(GrapeLeaves.MAXGROW, GrapeLeaves.maxGrowRandomizer(random)).with(GrapeLeaves.SHOWBOTTOM, true).with(GrapeLeaves.GROWNDIRECTION, Direction.DOWN).with(GrapeLeaves.BEARFRUIT, GrapeLeaves.shouldBearFruit(random)).with(GrapeLeaves.GROWVINES, GrapeLeaves.shouldGrowVines(random)));
            }
        }
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return Block.createCuboidShape(6, 0, 6, 10, 16, 10);
    }

    @Override
    protected void appendProperties(Builder<Block, BlockState> builder) {
        builder.add(LEAVES);
    }

    static {
        LEAVES = BooleanProperty.of("leaves");
    }
}
