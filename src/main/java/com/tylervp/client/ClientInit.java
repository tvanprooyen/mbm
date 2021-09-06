package com.tylervp.client;

import java.util.function.Function;

import com.tylervp.block.MBMBlocks;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandler;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockRenderView;

public class ClientInit implements ClientModInitializer
{
    // ...
    
    @Override
    public void onInitializeClient() {

		//Fluid
        setupFluidRendering(MBMBlocks.STILL_MUD, MBMBlocks.FLOWING_MUD, new Identifier("moreblocksmod", "mud"), 0Xd9d9d9); //MoreBlocksModClientIni. 0X523422
     	BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getSolid(), MBMBlocks.STILL_MUD, MBMBlocks.FLOWING_MUD);
		

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
		
		BlockRenderLayerMap.INSTANCE.putBlock(MBMBlocks.GRASS_SLAB, RenderLayer.getCutout());

		BlockRenderLayerMap.INSTANCE.putBlock(MBMBlocks.RICE, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(MBMBlocks.RICE_NO_OFF_SET, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(MBMBlocks.LANTERN_ROPE, RenderLayer.getCutout());
        
        BlockRenderLayerMap.INSTANCE.putBlock(MBMBlocks.VERTICAL_GLASS_PANE, RenderLayer.getCutout());

		BlockRenderLayerMap.INSTANCE.putBlock(MBMBlocks.SPIKE, RenderLayer.getCutout());

        //Colours
        ColorProviderRegistry.BLOCK.register((state,view,pos,tintIndex) -> BiomeColors.getFoliageColor(view, pos), MBMBlocks.THIN_ACACIA_LOG);
        ColorProviderRegistry.BLOCK.register((state,view,pos,tintIndex) -> FoliageColors.getSpruceColor(), MBMBlocks.THIN_SPRUCE_LOG);//0x619961
        ColorProviderRegistry.BLOCK.register((state,view,pos,tintIndex) -> BiomeColors.getFoliageColor(view, pos), MBMBlocks.THIN_OAK_LOG);
        ColorProviderRegistry.BLOCK.register((state,view,pos,tintIndex) -> BiomeColors.getFoliageColor(view, pos), MBMBlocks.THIN_JUNGLE_LOG);
        ColorProviderRegistry.BLOCK.register((state,view,pos,tintIndex) -> FoliageColors.getBirchColor(), MBMBlocks.THIN_BIRCH_LOG);
		ColorProviderRegistry.BLOCK.register((state,view,pos,tintIndex) -> BiomeColors.getFoliageColor(view, pos), MBMBlocks.THIN_DARK_OAK_LOG);
		
		ColorProviderRegistry.BLOCK.register((state,view,pos,tintIndex) -> BiomeColors.getGrassColor(view, pos), MBMBlocks.GRASS_SLAB);

		ColorProviderRegistry.BLOCK.register((state,view,pos,tintIndex) -> 0Xffaf4d, MBMBlocks.DEAD_GRASS_BLOCK); //Original 0Xffab44 Lighter 0Xffb75f Now 0Xffaf4d
		
		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
            return 0X91BD59;
		}, MBMBlocks.GRASS_SLAB);
		
		ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
            return 0Xffaf4d;
        }, MBMBlocks.DEAD_GRASS_BLOCK);
 
	}


	public static void setupFluidRendering(final Fluid still, final Fluid flowing, final Identifier textureFluidId, final int color) {
		final Identifier stillSpriteId = new Identifier(textureFluidId.getNamespace(), "block/" + textureFluidId.getPath() + "_still");
		final Identifier flowingSpriteId = new Identifier(textureFluidId.getNamespace(), "block/" + textureFluidId.getPath() + "_flow");
 
		// If they're not already present, add the sprites to the block atlas
		ClientSpriteRegistryCallback.event(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE).register((atlasTexture, registry) -> {
			registry.register(stillSpriteId);
			registry.register(flowingSpriteId);
		});
 
		final Identifier fluidId = Registry.FLUID.getId(still);
		final Identifier listenerId = new Identifier(fluidId.getNamespace(), fluidId.getPath() + "_reload_listener");
 
		final Sprite[] fluidSprites = { null, null };
 
		ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(new SimpleSynchronousResourceReloadListener() {
			@Override
			public Identifier getFabricId() {
				return listenerId;
			}
 
			/**
			 * Get the sprites from the block atlas when resources are reloaded
			 */
			@Override
			public void reload(ResourceManager resourceManager) {
				final Function<Identifier, Sprite> atlas = MinecraftClient.getInstance().getSpriteAtlas(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE);
				fluidSprites[0] = atlas.apply(stillSpriteId);
				fluidSprites[1] = atlas.apply(flowingSpriteId);
			}
		});
 
		// The FluidRenderer gets the sprites and color from a FluidRenderHandler during rendering
		final FluidRenderHandler renderHandler = new FluidRenderHandler()
		{
			@Override
			public Sprite[] getFluidSprites(BlockRenderView view, BlockPos pos, FluidState state) {
				return fluidSprites;
			}
 
			@Override
			public int getFluidColor(BlockRenderView view, BlockPos pos, FluidState state) {
				return color;
			}
		};
 
		FluidRenderHandlerRegistry.INSTANCE.register(still, renderHandler);
		FluidRenderHandlerRegistry.INSTANCE.register(flowing, renderHandler);
	}
}