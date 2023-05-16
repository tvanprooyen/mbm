package com.tylervp.block;

import java.util.ArrayList;
import java.util.List;

import com.tylervp.particle.MBMParticle;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class PoisonSlimeTreeTop extends Block {

    public PoisonSlimeTreeTop(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return Block.createCuboidShape(0, 0, 0, 16, 8, 16);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return Block.createCuboidShape(1, 0, 1, 15, 7, 15);
    }

    @Override
    public VoxelShape getRaycastShape(BlockState state, BlockView world, BlockPos pos) {
        VoxelShape BuiltShape;
        VoxelShape MainShape = Block.createCuboidShape(2, 0, 2, 14, 16, 14);

        BuiltShape = VoxelShapes.combineAndSimplify(VoxelShapes.fullCube(), MainShape, BooleanBiFunction.FIRST);

        return BuiltShape;
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if(world.getBlockState(pos.down()).isAir()) {
            world.breakBlock(pos, false);
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }


    @Override
    public void onBlockBreakStart(BlockState state, World world, BlockPos pos, PlayerEntity player) {
        PoisonPlayer(state, world, pos, player, Hand.MAIN_HAND);

        super.onBlockBreakStart(state, world, pos, player);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        PoisonPlayer(state, world, pos, player, hand);

        return ActionResult.PASS;
    }

    private void PoisonPlayer(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand) {
        if (!world.isClient) {
            if(player.getStackInHand(Hand.MAIN_HAND).getItem() != Items.GOLDEN_HOE) { //(player.getStackInHand(hand).getItem() instanceof HoeItem)
                StatusEffect effect = StatusEffects.POISON;

                if((player).hasStatusEffect(effect)) {
                    if((player).getStatusEffect(effect).isDurationBelow(durationToSeconds(2))) {
                        (player).removeStatusEffect(effect);
                        StatusEffectInstance CurrEffectInstance = new StatusEffectInstance(effect, durationToSeconds(20));
                        (player).addStatusEffect(CurrEffectInstance);

                    }
                } else {
                    StatusEffectInstance CurrEffectInstance = new StatusEffectInstance(effect, durationToSeconds(20));
                    (player).addStatusEffect(CurrEffectInstance);
                }
            }
        }
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if(random.nextInt(3) == 0) {
            for (final Direction lv : Direction.values()) {
                Direction.Axis axis11 = lv.getAxis();
                double double12 = (axis11 == Direction.Axis.X) ? (0.5 + 0.5625 * lv.getOffsetX()) : random.nextFloat();
                double double14 = (axis11 == Direction.Axis.Y) ? (0.5 + 0.5625 * lv.getOffsetY()) : random.nextFloat();
                double double16 = (axis11 == Direction.Axis.Z) ? (0.5 + 0.5625 * lv.getOffsetZ()) : random.nextFloat();
                world.addParticle(MBMParticle.POISON_BUBBLE, pos.getX() + double12, pos.getY() + double14, pos.getZ() + double16, 0.0, 0.05, 0.0);
            }
        }
    }

    private int durationToSeconds(double duration) {
        return MathHelper.ceil(20.83 * duration);
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {

        if (world.isClient) {
            return;
        }

        if (world.random.nextInt(10) == 0) {

            List<Entity> EntityList = new ArrayList<Entity>();

            EntityList.add(entity);

            for (Entity entities : EntityList) {
                StatusEffect effect = StatusEffects.POISON;

                if(!(entities instanceof LivingEntity)) {
                    break;
                }

                if(((LivingEntity)entities).hasStatusEffect(effect)) {
                    if(((LivingEntity)entities).getStatusEffect(effect).isDurationBelow(durationToSeconds(2))) {
                        ((LivingEntity)entities).removeStatusEffect(effect);
                        StatusEffectInstance CurrEffectInstance = new StatusEffectInstance(effect, durationToSeconds(10));
                        ((LivingEntity)entities).addStatusEffect(CurrEffectInstance);

                    }
                    break;
                }

                StatusEffectInstance CurrEffectInstance = new StatusEffectInstance(effect, durationToSeconds(10));

                ((LivingEntity)entities).addStatusEffect(CurrEffectInstance);

            }
        }
    }

}
