package net.themoviea.frozeniimod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.screen.slot.CraftingResultSlot;

@Mixin(CraftingResultSlot.class)
public interface CraftingResultSlotAccessor {
	@Accessor("input")
	public CraftingInventory getInput();
	
	@Accessor("player")
	public PlayerEntity getPlayer();
}
