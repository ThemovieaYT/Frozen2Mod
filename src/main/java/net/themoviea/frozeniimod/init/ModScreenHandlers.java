package net.themoviea.frozeniimod.init;

import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.themoviea.frozeniimod.screen.ElsaCommunicationScreenHandler;
import net.themoviea.frozeniimod.screen.SpiritPowerCraftingScreenHandler;

public class ModScreenHandlers 
{
	public static ScreenHandlerType<SpiritPowerCraftingScreenHandler> SPIRIT_POWER_SCREEN_HANDLER;
	public static ScreenHandlerType<ElsaCommunicationScreenHandler> ELSA_COMMS;
	
	static {
		SPIRIT_POWER_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(new Identifier("frozeniimod", "spirit_power_screen_handler"), SpiritPowerCraftingScreenHandler::new);
		ELSA_COMMS = ScreenHandlerRegistry.registerSimple(new Identifier("frozeniimod", "elsa_comms_screen_handler"), ElsaCommunicationScreenHandler::new);
	}
}
