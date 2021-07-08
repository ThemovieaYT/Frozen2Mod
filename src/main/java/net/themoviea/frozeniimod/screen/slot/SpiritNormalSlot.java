package net.themoviea.frozeniimod.screen.slot;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.themoviea.frozeniimod.screen.SpiritPowerCraftingScreenHandler;

public class SpiritNormalSlot extends Slot {
	private final SpiritPowerCraftingScreenHandler handler;

	public SpiritNormalSlot(SpiritPowerCraftingScreenHandler handler, Inventory inventory, int index, int x, int y) {
		super(inventory, index, x, y);
		this.handler = handler;
	}

	@Override
	public boolean canInsert(ItemStack stack) {
		return !this.handler.isCrystal(stack);
	}
}
