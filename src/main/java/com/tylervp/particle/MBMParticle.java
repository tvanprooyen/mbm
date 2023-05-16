package com.tylervp.particle;

import com.tylervp.moreblocksmod;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class MBMParticle {
    public static final DefaultParticleType POISON_BUBBLE = FabricParticleTypes.simple();

    public static final void register() {
        Registry.register(Registries.PARTICLE_TYPE, new Identifier(moreblocksmod.ModName, "poison_bubble"), POISON_BUBBLE);
    }
}