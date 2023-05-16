package com.tylervp.client.renderer;

/* import com.tylervp.moreblocksmod;
import com.tylervp.client.model.AgentEntityModel;
import com.tylervp.entity.AgentEntity;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.IronGolemEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class AgentEntityRendererTurnOn extends FeatureRenderer<AgentEntity, AgentEntityModel> {
    private static final Identifier OffTexture, OnTexture;
    
    public AgentEntityRendererTurnOn(FeatureRendererContext<AgentEntity, AgentEntityModel> context) {
        super(context);
    }
    
    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, AgentEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        if (entity.isInvisible()) {
            return;
        }

        Identifier identifier13;

        if(entity.getOnStatus()){
            identifier13 = AgentEntityRendererTurnOn.OnTexture;
        } else {
            identifier13 = AgentEntityRendererTurnOn.OffTexture;
        }
        FeatureRenderer.<AgentEntity>renderModel((this).getContextModel(), identifier13, matrices, vertexConsumers, light, entity, 1.0f, 1.0f, 1.0f);
    }
    
    static {
        OffTexture = new Identifier(moreblocksmod.ModName, "textures/entity/agent/agent_off.png");
        OnTexture = new Identifier(moreblocksmod.ModName, "textures/entity/agent/agent_on.png");
    }
} */


import com.tylervp.moreblocksmod;
import com.tylervp.client.model.AgentEntityModel;
import com.tylervp.entity.AgentEntity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

@Environment(value=EnvType.CLIENT)
public class AgentEntityRendererTurnOn
extends FeatureRenderer<AgentEntity, AgentEntityModel> {
    private static final Identifier OffTexture, OnTexture;

    public AgentEntityRendererTurnOn(FeatureRendererContext<AgentEntity, AgentEntityModel> featureRendererContext) {
        super(featureRendererContext);
    }

    @Override
    public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, AgentEntity entity, float f, float g, float h, float j, float k, float l) {
        if (entity.isInvisible()) {
            return;
        }

        Identifier identifier;

        if(entity.getOnStatus()){
            identifier = AgentEntityRendererTurnOn.OnTexture;
        } else {
            identifier = AgentEntityRendererTurnOn.OffTexture;
        }
        
        AgentEntityRendererTurnOn.renderModel(this.getContextModel(), identifier, matrixStack, vertexConsumerProvider, i, entity, 1.0f, 1.0f, 1.0f);
    }

    static {
        OffTexture = new Identifier(moreblocksmod.ModName, "textures/entity/agent/agent_off.png");
        OnTexture = new Identifier(moreblocksmod.ModName, "textures/entity/agent/agent_on.png");
    }
}


