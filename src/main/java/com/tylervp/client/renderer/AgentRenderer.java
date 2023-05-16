package com.tylervp.client.renderer;

import com.tylervp.moreblocksmod;
import com.tylervp.client.ClientInit;
import com.tylervp.client.model.AgentEntityModel;
import com.tylervp.entity.AgentEntity;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

/*
 * A renderer is used to provide an entity model, shadow size, and texture.
 */
public class AgentRenderer extends MobEntityRenderer<AgentEntity, AgentEntityModel> {

    public AgentRenderer(EntityRendererFactory.Context context) {
        super(context, new AgentEntityModel(context.getPart(ClientInit.MODEL_AGENT_LAYER)), 0.25f);
        this.addFeature(new AgentEntityRendererTurnOn(this));
    }
 
    @Override
    public Identifier getTexture(AgentEntity entity) {
        return new Identifier(moreblocksmod.ModName, "textures/entity/agent/agent_off.png");
    }
}
