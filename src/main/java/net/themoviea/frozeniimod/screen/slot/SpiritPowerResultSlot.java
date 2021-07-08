package net.themoviea.frozeniimod.screen.slot;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.CraftingResultSlot;
import net.minecraft.util.collection.DefaultedList;
import net.themoviea.frozeniimod.recipe.SpiritPowerCraftingRecipe;
import net.themoviea.frozeniimod.mixin.CraftingResultSlotAccessor;

public class SpiritPowerResultSlot extends CraftingResultSlot {

	private CraftingResultSlot resultSlot;
	public SpiritPowerResultSlot(PlayerEntity player, CraftingInventory input, Inventory inventory, int index, int x,
			int y) {
		super(player, input, inventory, index, x, y);
		this.resultSlot = new CraftingResultSlot(player, input, inventory, index, x, y);
	}

	@Override
	public ItemStack onTakeItem(PlayerEntity player, ItemStack stack) {
		this.onCrafted(stack);
		CraftingInventory input = ((CraftingResultSlotAccessor)this.resultSlot).getInput();
		PlayerEntity player1 = ((CraftingResultSlotAccessor)this.resultSlot).getPlayer();
	      DefaultedList<ItemStack> defaultedList = player.world.getRecipeManager().getRemainingStacks(SpiritPowerCraftingRecipe.Type.SPIRIT_POWER_CRAFTING_TYPE, input, player.world);

	      for(int i = 0; i < defaultedList.size(); ++i) {
	         ItemStack itemStack = input.getStack(i);
	         ItemStack itemStack2 = (ItemStack)defaultedList.get(i);
	         if (!itemStack.isEmpty()) {
	            input.removeStack(i, 1);
	            itemStack = input.getStack(i);
	         }

	         if (!itemStack2.isEmpty()) {
	            if (itemStack.isEmpty()) {
	               input.setStack(i, itemStack2);
	            } else if (ItemStack.areItemsEqualIgnoreDamage(itemStack, itemStack2) && ItemStack.areTagsEqual(itemStack, itemStack2)) {
	               itemStack2.increment(itemStack.getCount());
	               input.setStack(i, itemStack2);
	            } else if (!player1.inventory.insertStack(itemStack2)) {
	            	player1.dropItem(itemStack2, false);
	            }
	         }
	      }

	      return stack;
	}
}
