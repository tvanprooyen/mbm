package com.tylervp.client;

import com.tylervp.moreblocksmod;
import com.tylervp.block.MBMBlocks;
import com.tylervp.client.model.AgentEntityModel;
import com.tylervp.client.partical.PoisonBubbleParticle;
import com.tylervp.client.renderer.AgentRenderer;
import com.tylervp.entity.MBMEntities;
import com.tylervp.particle.MBMParticle;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.client.particle.BubbleColumnUpParticle;
import net.minecraft.client.particle.CampfireSmokeParticle;
import net.minecraft.client.particle.WaterBubbleParticle;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
//import net.minecraft.util.registry.Registry;

public class ClientInit implements ClientModInitializer
{

	public static final EntityModelLayer MODEL_AGENT_LAYER = new EntityModelLayer(new Identifier(moreblocksmod.ModName, "agent"), "main");

    @Override
    public void onInitializeClient() {

        EntityRendererRegistry.register(MBMEntities.AGENT, (context) -> {
            return new AgentRenderer(context);
        });
 
        EntityModelLayerRegistry.registerModelLayer(MODEL_AGENT_LAYER, AgentEntityModel::getTexturedModelData);

		FluidRenderHandlerRegistry.INSTANCE.register(MBMBlocks.STILL_MUD, MBMBlocks.FLOWING_MUD, new SimpleFluidRenderHandler(
				new Identifier(moreblocksmod.ModName + ":block/mud_still"),
				new Identifier(moreblocksmod.ModName + ":block/mud_flow"),
				new Identifier(moreblocksmod.ModName + ":block/mud_overlay"),
				0Xd9d9d9//0X523422
		));

		BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), MBMBlocks.STILL_MUD, MBMBlocks.FLOWING_MUD);

		FluidRenderHandlerRegistry.INSTANCE.setBlockTransparency(MBMBlocks.MUD, true);

		//Fluid
        //setupFluidRendering(MBMBlocks.STILL_MUD, MBMBlocks.FLOWING_MUD, new Identifier("moreblocksmod", "mud"), 0Xd9d9d9); //MoreBlocksModClientIni. 0X523422
     	//BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getSolid(), MBMBlocks.STILL_MUD, MBMBlocks.FLOWING_MUD);

		//Partical
		ParticleFactoryRegistry.getInstance().register(MBMParticle.POISON_BUBBLE, PoisonBubbleParticle.CosySmokeFactory::new);

		/* ClientSpriteRegistryCallback.event(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE).register(((atlasTexture, registry) -> {
            registry.register(new Identifier("modid", "particle/green_flame"));
        })); */

		//Cutouts
        BlockRenderLayerMap.INSTANCE.putBlock(MBMBlocks.ROPE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(MBMBlocks.ROPEMID, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(MBMBlocks.THIN_STRIPPED_ACACIA_LOG, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(MBMBlocks.THIN_ACACIA_LOG, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(MBMBlocks.THIN_STRIPPED_BIRCH_LOG, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(MBMBlocks.THIN_BIRCH_LOG, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(MBMBlocks.THIN_STRIPPED_DARK_OAK_LOG, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(MBMBlocks.THIN_DARK_OAK_LOG, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(MBMBlocks.THIN_STRIPPED_JUNGLE_LOG, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(MBMBlocks.THIN_JUNGLE_LOG, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(MBMBlocks.THIN_STRIPPED_OAK_LOG, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(MBMBlocks.THIN_OAK_LOG, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(MBMBlocks.THIN_SPRUCE_LOG, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(MBMBlocks.THIN_STRIPPED_SPRUCE_LOG, RenderLayer.getCutout());

		BlockRenderLayerMap.INSTANCE.putBlock(MBMBlocks.DEAD_GRASS_BLOCK, RenderLayer.getCutout());

		BlockRenderLayerMap.INSTANCE.putBlock(MBMBlocks.BURNT_GRASS_BLOCK, RenderLayer.getCutout());

		BlockRenderLayerMap.INSTANCE.putBlock(MBMBlocks.DEAD_GRASS_BLOCK_SLAB, RenderLayer.getCutout());

		BlockRenderLayerMap.INSTANCE.putBlock(MBMBlocks.BURNT_GRASS_BLOCK_SLAB, RenderLayer.getCutout());

		BlockRenderLayerMap.INSTANCE.putBlock(MBMBlocks.GRASS_SLAB, RenderLayer.getCutout());

		BlockRenderLayerMap.INSTANCE.putBlock(MBMBlocks.RICE, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(MBMBlocks.RICE_NO_OFF_SET, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(MBMBlocks.LANTERN_ROPE, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(MBMBlocks.VERTICAL_GLASS_PANE, RenderLayer.getCutout());

		BlockRenderLayerMap.INSTANCE.putBlock(MBMBlocks.SPIKE, RenderLayer.getCutout());

		BlockRenderLayerMap.INSTANCE.putBlock(MBMBlocks.DEAD_GRASS, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(MBMBlocks.BURNT_GRASS, RenderLayer.getCutout());

		BlockRenderLayerMap.INSTANCE.putBlock(MBMBlocks.DEAD_TALL_GRASS, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(MBMBlocks.BURNT_TALL_GRASS, RenderLayer.getCutout());

		BlockRenderLayerMap.INSTANCE.putBlock(MBMBlocks.UNLIT_TORCH, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(MBMBlocks.UNLIT_WALL_TORCH, RenderLayer.getCutout());

		BlockRenderLayerMap.INSTANCE.putBlock(MBMBlocks.PURPLE_GRAPES, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(MBMBlocks.GREEN_GRAPES, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(MBMBlocks.GRAPE_LEAVES, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(MBMBlocks.GRAPE_LOG, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(MBMBlocks.GRAPE_SPUR, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(MBMBlocks.GRAPE_LEAVES_HANGING, RenderLayer.getCutout());

		BlockRenderLayerMap.INSTANCE.putBlock(MBMBlocks.APPLE, RenderLayer.getCutout());

		BlockRenderLayerMap.INSTANCE.putBlock(MBMBlocks.CABBAGE, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(MBMBlocks.RED_CABBAGE, RenderLayer.getCutout());

		BlockRenderLayerMap.INSTANCE.putBlock(MBMBlocks.WATER_BUCKET_BLOCK, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(MBMBlocks.LAVA_BUCKET_BLOCK, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(MBMBlocks.BUCKET_BLOCK, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(MBMBlocks.MUD_BUCKET_BLOCK, RenderLayer.getCutout());

		BlockRenderLayerMap.INSTANCE.putBlock(MBMBlocks.COTTON, RenderLayer.getCutout());

		BlockRenderLayerMap.INSTANCE.putBlock(MBMBlocks.PINECONE, RenderLayer.getCutout());

		BlockRenderLayerMap.INSTANCE.putBlock(MBMBlocks.DARK_OAK_ACORN, RenderLayer.getCutout());

		BlockRenderLayerMap.INSTANCE.putBlock(MBMBlocks.OAK_ACORN, RenderLayer.getCutout());

		BlockRenderLayerMap.INSTANCE.putBlock(MBMBlocks.BIRCH_CATKIN, RenderLayer.getCutout());


        //Colours
        ColorProviderRegistry.BLOCK.register((state,view,pos,tintIndex) -> BiomeColors.getFoliageColor(view, pos), MBMBlocks.THIN_ACACIA_LOG);
        ColorProviderRegistry.BLOCK.register((state,view,pos,tintIndex) -> FoliageColors.getSpruceColor(), MBMBlocks.THIN_SPRUCE_LOG);//0x619961
        ColorProviderRegistry.BLOCK.register((state,view,pos,tintIndex) -> BiomeColors.getFoliageColor(view, pos), MBMBlocks.THIN_OAK_LOG);
        ColorProviderRegistry.BLOCK.register((state,view,pos,tintIndex) -> BiomeColors.getFoliageColor(view, pos), MBMBlocks.THIN_JUNGLE_LOG);
        ColorProviderRegistry.BLOCK.register((state,view,pos,tintIndex) -> FoliageColors.getBirchColor(), MBMBlocks.THIN_BIRCH_LOG);
		ColorProviderRegistry.BLOCK.register((state,view,pos,tintIndex) -> BiomeColors.getFoliageColor(view, pos), MBMBlocks.THIN_DARK_OAK_LOG);
		
		ColorProviderRegistry.BLOCK.register((state,view,pos,tintIndex) -> BiomeColors.getGrassColor(view, pos), MBMBlocks.GRASS_SLAB);

		ColorProviderRegistry.BLOCK.register((state,view,pos,tintIndex) -> 0Xffaf4d, MBMBlocks.DEAD_GRASS_BLOCK);

		ColorProviderRegistry.BLOCK.register((state,view,pos,tintIndex) -> 0X383838, MBMBlocks.BURNT_GRASS_BLOCK);

		ColorProviderRegistry.BLOCK.register((state,view,pos,tintIndex) -> 0Xffaf4d, MBMBlocks.DEAD_GRASS_BLOCK_SLAB);

		ColorProviderRegistry.BLOCK.register((state,view,pos,tintIndex) -> 0X383838, MBMBlocks.BURNT_GRASS_BLOCK_SLAB);

		ColorProviderRegistry.BLOCK.register((state,view,pos,tintIndex) -> 0Xffaf4d, MBMBlocks.DEAD_GRASS);

		ColorProviderRegistry.BLOCK.register((state,view,pos,tintIndex) -> 0X383838, MBMBlocks.BURNT_GRASS);

		ColorProviderRegistry.BLOCK.register((state,view,pos,tintIndex) -> 0Xffaf4d, MBMBlocks.DEAD_TALL_GRASS);

		ColorProviderRegistry.BLOCK.register((state,view,pos,tintIndex) -> 0X383838, MBMBlocks.BURNT_TALL_GRASS);

		ColorProviderRegistry.BLOCK.register((state,view,pos,tintIndex) -> 0X759c40, MBMBlocks.GRAPE_LEAVES);
		ColorProviderRegistry.BLOCK.register((state,view,pos,tintIndex) -> 0X759c40, MBMBlocks.GRAPE_LOG);

		ColorProviderRegistry.BLOCK.register((state,view,pos,tintIndex) -> 0X759c40, MBMBlocks.GRAPE_SPUR); //67502c
		ColorProviderRegistry.BLOCK.register((state,view,pos,tintIndex) -> 0X759c40, MBMBlocks.GRAPE_LEAVES_HANGING);


		ColorProviderRegistry.BLOCK.register((state,view,pos,tintIndex) -> 0Xa7c899, MBMBlocks.CABBAGE);
		ColorProviderRegistry.BLOCK.register((state,view,pos,tintIndex) -> 0Xc580b9, MBMBlocks.RED_CABBAGE);

		
		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
            return 0X91BD59;
		}, MBMBlocks.GRASS_SLAB);
		
		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
            return 0Xffaf4d;
        }, MBMBlocks.DEAD_GRASS_BLOCK);

		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
            return 0X383838;
        }, MBMBlocks.BURNT_GRASS_BLOCK);

		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
            return 0Xffaf4d;
        }, MBMBlocks.DEAD_GRASS_BLOCK_SLAB);

		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
            return 0X383838;
        }, MBMBlocks.BURNT_GRASS_BLOCK_SLAB);

		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
            return 0Xffaf4d;
        }, MBMBlocks.DEAD_GRASS);

		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
            return 0X383838;
        }, MBMBlocks.BURNT_GRASS);

		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
            return 0Xffaf4d;
        }, MBMBlocks.DEAD_TALL_GRASS);

		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
            return 0X383838;
        }, MBMBlocks.BURNT_TALL_GRASS);

		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
            return 0X759c40;
        }, MBMBlocks.GRAPE_LEAVES);

		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
            return 0X759c40;
        }, MBMBlocks.GRAPE_LOG);

		/* ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
            return 0X759c40;
        }, MBMBlocks.GRAPE_SPUR); */

		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
            return 0X759c40;
        }, MBMBlocks.GRAPE_LEAVES_HANGING);


		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
            return 0Xa7c899;
        }, MBMBlocks.CABBAGE);

		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
            return 0Xc580b9;
        }, MBMBlocks.RED_CABBAGE);
	}
}