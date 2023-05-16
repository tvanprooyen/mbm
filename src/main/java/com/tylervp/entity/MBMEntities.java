package com.tylervp.entity;

import com.tylervp.moreblocksmod;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class MBMEntities {

    public static final EntityType<AgentEntity> AGENT = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(moreblocksmod.ModName, "agent"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, AgentEntity::new).dimensions(EntityDimensions.fixed(0.6f, 0.95f)).build()
    );

    public static void register() {
        FabricDefaultAttributeRegistry.register(AGENT, AgentEntity.createMobAttributes());
    }
}
