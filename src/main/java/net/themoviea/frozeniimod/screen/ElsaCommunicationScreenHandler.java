package net.themoviea.frozeniimod.screen;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.themoviea.frozeniimod.entity.passive.ElsaEntity;
import net.themoviea.frozeniimod.init.ModScreenHandlers;

public class ElsaCommunicationScreenHandler extends ScreenHandler {
	private ElsaEntity elsa;
	private final ScreenHandlerContext context;
	private final PlayerEntity player;
	
	public ElsaCommunicationScreenHandler(int syncId, PlayerInventory playerInventory) {
		this(syncId, playerInventory, ScreenHandlerContext.EMPTY);
	}
	
	public ElsaCommunicationScreenHandler(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
		super(ModScreenHandlers.ELSA_COMMS, syncId);
		this.player = playerInventory.player;
		this.context = context;
	}
	
	public ElsaCommunicationScreenHandler setElsa(ElsaEntity elsa) {
		this.elsa = elsa;
		return this;
	}

	@Override
	public boolean canUse(PlayerEntity player) {
		return true;
	}

	@Override
	public void close(PlayerEntity player) {
		super.close(player);
	}
}
