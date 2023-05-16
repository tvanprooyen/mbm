package com.tylervp.client.partical;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.ParticleTextureSheet;
import net.minecraft.client.particle.SpriteBillboardParticle;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

@Environment(value=EnvType.CLIENT)
public class PoisonBubbleParticle
extends SpriteBillboardParticle {
    PoisonBubbleParticle(ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, boolean signal) {
        super(world, x, y, z);
        this.scale(0.5f);
        this.setBoundingBoxSpacing(0.25f, 0.25f);
        this.maxAge = signal ? this.random.nextInt(50) + 80 : this.random.nextInt(50) + 40;
        this.gravityStrength = 3.0E-5f;
        this.velocityX = velocityX;
        this.velocityY = velocityY + (double)(this.random.nextFloat() / 500.0f);
        this.velocityZ = velocityZ;
    }

    @Override
    public void tick() {
        this.prevPosX = this.x;
        this.prevPosY = this.y;
        this.prevPosZ = this.z;
        if (this.age++ >= this.maxAge || this.alpha <= 0.0f) {
            this.markDead();
            return;
        }
        this.velocityX += (double)(this.random.nextFloat() / 5000.0f * (float)(this.random.nextBoolean() ? 1 : -1));
        this.velocityZ += (double)(this.random.nextFloat() / 5000.0f * (float)(this.random.nextBoolean() ? 1 : -1));
        this.velocityY -= (double)this.gravityStrength;
        this.move(this.velocityX, this.velocityY, this.velocityZ);
        if (this.age >= this.maxAge - 60 && this.alpha > 0.01f) {
            this.alpha -= 0.015f;
        }
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Environment(value=EnvType.CLIENT)
    public static class SignalSmokeFactory
    implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider spriteProvider;

        public SignalSmokeFactory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Override
        public Particle createParticle(DefaultParticleType defaultParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
            PoisonBubbleParticle campfireSmokeParticle = new PoisonBubbleParticle(clientWorld, d, e, f, g, h, i, true);
            campfireSmokeParticle.setAlpha(0.95f);
            campfireSmokeParticle.setSprite(this.spriteProvider);
            return campfireSmokeParticle;
        }
    }

    @Environment(value=EnvType.CLIENT)
    public static class CosySmokeFactory
    implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider spriteProvider;

        public CosySmokeFactory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Override
        public Particle createParticle(DefaultParticleType defaultParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
            PoisonBubbleParticle campfireSmokeParticle = new PoisonBubbleParticle(clientWorld, d, e, f, g, h, i, false);
            campfireSmokeParticle.setAlpha(0.9f);
            campfireSmokeParticle.setSprite(this.spriteProvider);
            return campfireSmokeParticle;
        }
    }
}

