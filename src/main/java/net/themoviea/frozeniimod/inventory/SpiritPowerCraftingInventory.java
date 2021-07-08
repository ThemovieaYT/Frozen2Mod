package net.themoviea.frozeniimod.inventory;

import java.util.Iterator;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeFinder;
import net.minecraft.recipe.RecipeInputProvider;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.util.collection.DefaultedList;

public class SpiritPowerCraftingInventory implements Inventory, RecipeInputProvider {
	private final DefaultedList<ItemStack> stacks;
	private final int width, height;
	private final ScreenHandler handler;
	
	public SpiritPowerCraftingInventory(ScreenHandler handler, int width, int height) {
		this.stacks = DefaultedList.ofSize(width * height, ItemStack.EMPTY);
		this.handler = handler;
		this.width = width;
		this.height = height;
	}
	
	@Override
	public void clear() {
		this.stacks.clear();
	}

	@Override
	public void provideRecipeInputs(RecipeFinder finder) {
		Iterator var2 = this.stacks.iterator();

	      while(var2.hasNext()) {
	         ItemStack itemStack = (ItemStack)var2.next();
	         finder.addNormalItem(itemStack);
	      }

	}

	@Override
	public int size() {
		return this.stacks.size();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ItemStack getStack(int slot) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack removeStack(int slot, int amount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack removeStack(int slot) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setStack(int slot, ItemStack stack) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void markDirty() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean canPlayerUse(PlayerEntity player) {
		// TODO Auto-generated method stub
		return false;
	}

}
