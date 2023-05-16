package com.tylervp.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeveledCauldronBlock;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.state.property.Property;

import net.minecraft.util.math.random.Random;

import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LeveledCauldronBlock.class)
public abstract class CauldronBlockMixin extends Block {
    

    public CauldronBlockMixin(AbstractBlock.Settings settings) {
        super(settings);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if(state.isOf(Blocks.WATER_CAULDRON)) {
            int waterLevel = state.<Integer>get((Property<Integer>)LeveledCauldronBlock.LEVEL);

            if(world.getBlockState(pos.down()).isOf(Blocks.FIRE) && waterLevel > 0){

                float waterLevelPos = (pos.getY() + (6.0f + 3 * waterLevel) / 16.0f);

                world.addParticle(ParticleTypes.BUBBLE_POP, (pos.getX() + 0.3) + (random.nextDouble() -0.3),  waterLevelPos + (random.nextDouble()-(6.0f + 3 * waterLevel) / 16.0f), (pos.getZ() + 0.3) + (random.nextDouble() -0.3), 0.0, 0.05, 0.0);


                if (random.nextInt(10) == 0) {
                    world.playSound(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, SoundEvents.BLOCK_BUBBLE_COLUMN_BUBBLE_POP, SoundCategory.BLOCKS, 0.5f + random.nextFloat(), random.nextFloat() * 0.7f + 0.6f, false);
                }
                //if (random.nextInt(5) == 0) {
                        Random random5 = world.getRandom();
                        //DefaultParticleType defaultParticleType6 = ParticleTypes.CLOUD;
                        DustParticleEffect SteamPartical = new DustParticleEffect(new Vector3f(Vec3d.unpackRgb(16777215).toVector3f()), 2.0F);
                        world.addImportantParticle(SteamPartical, true, pos.getX() + 0.5 + random5.nextDouble() / 3.0 * (random5.nextBoolean() ? 1 : -1), (pos.getY()) + random5.nextDouble() + random5.nextDouble(), pos.getZ() + 0.5 + random5.nextDouble() / 3.0 * (random5.nextBoolean() ? 1 : -1), 0.0, 0.09, 0.0);
                //}
            }
        }
    }


    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        int waterLevel = state.<Integer>get((Property<Integer>)LeveledCauldronBlock.LEVEL);
        float float7 = pos.getY() + (6.0f + 3 * waterLevel) / 16.0f;
        if (!world.isClient && entity.isOnFire() && waterLevel > 0 && entity.getY() <= float7) {
            entity.extinguish();
            this.setLevel(world, pos, state, waterLevel - 1);
        }
    
        if (world.random.nextInt(10) == 0 && world.getBlockState(pos.down()).isOf(Blocks.FIRE)) {
            if (waterLevel > 0 && entity.getY() <= float7) {
                if (!entity.isFireImmune() && entity instanceof LivingEntity && !EnchantmentHelper.hasFrostWalker((LivingEntity)entity)) {
                    entity.damage(world.getDamageSources().hotFloor(), 1.0f);
                }
            }
        }
    }


    public void setLevel(World world, BlockPos pos, BlockState state, int level) {
        world.setBlockState(pos, ((BlockState)state).with(LeveledCauldronBlock.LEVEL, MathHelper.clamp(level, 0, 3)), 2);
        world.updateComparators(pos, this);
    }
}