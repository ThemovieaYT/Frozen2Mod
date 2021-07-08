package net.themoviea.frozeniimod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.themoviea.frozeniimod.client.gui.screen.ingame.elsaCommunication.ElsaCommunicationScreen;
import net.themoviea.frozeniimod.client.gui.screen.ingame.spiritPowerCrafting.SpiritPowerCraftingScreen;
import net.themoviea.frozeniimod.client.render.entity.AnnaEntityRenderer;
import net.themoviea.frozeniimod.client.render.entity.ArendellianEntityRenderer;
import net.themoviea.frozeniimod.client.render.entity.BoulderEntityRenderer;
import net.themoviea.frozeniimod.client.render.entity.BruniEntityRenderer;
import net.themoviea.frozeniimod.client.render.entity.ElsaEntityRenderer;
import net.themoviea.frozeniimod.client.render.entity.GaleEntityRenderer;
import net.themoviea.frozeniimod.client.render.entity.GhostFifthSpiritElsaEntityRenderer;
import net.themoviea.frozeniimod.client.render.entity.IcePowerEntityRenderer;
import net.themoviea.frozeniimod.client.render.entity.NokkEntityRenderer;
import net.themoviea.frozeniimod.init.ModBlocks;
import net.themoviea.frozeniimod.init.ModEntities;
import net.themoviea.frozeniimod.init.ModParticles;
import net.themoviea.frozeniimod.init.ModScreenHandlers;

@Environment(EnvType.CLIENT)
public class Frozen2ModClient implements ClientModInitializer
{
    @Override
    public void onInitializeClient() 
    {
    	ModParticles.registerFrozen2ModParticles();
    	ModParticles.registerFrozen2ModParticleFactories();
    	
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ENCHANTED_TREE_LEAVES, RenderLayer.getCutoutMipped());

    	ScreenRegistry.register(ModScreenHandlers.SPIRIT_POWER_SCREEN_HANDLER, SpiritPowerCraftingScreen::new);
        ScreenRegistry.register(ModScreenHandlers.ELSA_COMMS, ElsaCommunicationScreen::new);
    	EntityRendererRegistry.INSTANCE.register(ModEntities.BOULDER_ENTITY, (dispatcher, context) -> {
    		return new BoulderEntityRenderer(dispatcher);
    	});
        EntityRendererRegistry.INSTANCE.register(ModEntities.ELSA, (dispatcher, context) -> {
            return new ElsaEntityRenderer(dispatcher);
        });
        EntityRendererRegistry.INSTANCE.register(ModEntities.ANNA, (dispatcher, context) -> {
        	return new AnnaEntityRenderer(dispatcher);
        });
        EntityRendererRegistry.INSTANCE.register(ModEntities.ARENDELLIAN, (dispatcher, context) -> {
        	return new ArendellianEntityRenderer(dispatcher);
        });
        EntityRendererRegistry.INSTANCE.register(ModEntities.BRUNI, (dispatcher, context) -> {
        	return new BruniEntityRenderer(dispatcher);
        });
        EntityRendererRegistry.INSTANCE.register(ModEntities.GALE, (dispatcher, context) -> {
        	return new GaleEntityRenderer(dispatcher);
        });
        EntityRendererRegistry.INSTANCE.register(ModEntities.GHOST_FIFTH_SPIRIT_ELSA, (dispatcher, context) -> {
        	return new GhostFifthSpiritElsaEntityRenderer(dispatcher);
        });
        EntityRendererRegistry.INSTANCE.register(ModEntities.ICE_POWER, (dispatcher, context) -> {
        	return new IcePowerEntityRenderer(dispatcher, MinecraftClient.getInstance().getItemRenderer());
        });
        EntityRendererRegistry.INSTANCE.register(ModEntities.NOKK, (dispatcher, context) -> {
        	return new NokkEntityRenderer(dispatcher);
        });
        
    }
}