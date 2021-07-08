package net.themoviea.frozeniimod.recipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

import it.unimi.dsi.fastutil.ints.IntList;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeFinder;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.themoviea.frozeniimod.init.ModRecipes;
import net.themoviea.frozeniimod.mixin.IngredientInvoker;
import net.themoviea.frozeniimod.mixin.IngredientMixin;
import net.themoviea.frozeniimod.screen.SpiritPowerCraftingScreenHandler;

public class SpiritPowerCraftingRecipe implements Recipe<CraftingInventory> {
	/*private final Ingredient inputA;
	private final Ingredient inputB;
	private final Ingredient inputC;
	private final Ingredient inputD;*/
	final DefaultedList<Ingredient> input;
	final DefaultedList<Ingredient> subInput1;
	final DefaultedList<Ingredient> subInput2;
	final String group;
	final ItemStack result;
	private final Identifier id;
	
	public SpiritPowerCraftingRecipe(Identifier id, String group, ItemStack result, DefaultedList<Ingredient> input) {
		this.id = id;
		/*this.inputA =inputA;
		this.inputB = inputB;
		this.inputC = inputC;
		this.inputD = inputD;*/
		this.group = group;
		this.input = input;
		this.subInput1 = DefaultedList.of();
		this.subInput2 = DefaultedList.of();
		for(int i = 0; i < this.input.size(); i++) {
			ItemStack[] stack = this.getMatchingStacks(this.input.get(i));
			if(SpiritPowerCraftingScreenHandler.isCrystal(stack[0])) {
				this.subInput1.add(this.input.get(i));
			} else {
				this.subInput2.add(this.input.get(i));
			}
		}
		this.result = result;
	}
	
	public ItemStack[] getMatchingStacks(Ingredient ingredient) {
		((IngredientInvoker)(Object)ingredient).cacheSpiritMatchingStacks();
		return ((IngredientMixin)(Object)ingredient).getMatchingStacks();
	}
	
	/*
	public Ingredient getInputA() {
		return inputA;
	}
	
	public Ingredient getInputB() {
		return inputB;
	}
	
	public Ingredient getInputC() {
		return inputC;
	}
	
	public Ingredient getInputD() {
		return inputD;
	}*/
	@Override
	public String getGroup() {
		return this.group;
	}
	
	@Override
	public DefaultedList<Ingredient> getPreviewInputs() {
		return this.input;
	}
	
	public DefaultedList<Ingredient> getInput1() {
		return this.subInput1;
	}
	
	public DefaultedList<Ingredient> getInput2() {
		return this.subInput2;
	}
	
	@Override
	public boolean matches(CraftingInventory inv, World world) {
		RecipeFinder recipeFinder = new RecipeFinder();
		
		int i = 0;
		for(int j = 0; j < inv.size(); ++j) {
			ItemStack itemStack = inv.getStack(j);
			if(!itemStack.isEmpty()) {
				++i;
				recipeFinder.method_20478(itemStack, 1);
			}
		}
		return i == this.input.size() && recipeFinder.findRecipe(this, (IntList)null);
		//return inputA.test(inv.getStack(0)) && inputB.test(inv.getStack(1)) && inputC.test(inv.getStack(2)) && inputD.test(inv.getStack(3));
	}

	@Override
	public ItemStack craft(CraftingInventory inv) {
		return this.result.copy();
	}

	@Override
	public boolean fits(int width, int height) {
		return width * height >= this.input.size();
	}

	@Override
	public ItemStack getOutput() {
		return this.result;
	}

	@Override
	public Identifier getId() {
		return this.id;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return SpiritPowerCraftingRecipeSerializer.INSTANCE;
	}

	public static class Type implements RecipeType<SpiritPowerCraftingRecipe> {
		private Type() {}
		public static final Type SPIRIT_POWER_CRAFTING_TYPE = new Type();
		
		public static final String ID = "spirit_power_crafting_recipe";
	}
	
	public static ItemStack getItemStack(JsonObject json) {
	      String string = JsonHelper.getString(json, "item");
	      Item item = (Item)Registry.ITEM.getOrEmpty(new Identifier(string)).orElseThrow(() -> {
	         return new JsonSyntaxException("Unknown item '" + string + "'");
	      });
	      if (json.has("data")) {
	         throw new JsonParseException("Disallowed data tag found");
	      } else {
	         int i = JsonHelper.getInt(json, "count", 1);
	         return new ItemStack(item, i);
	      }
	   }
	
	@Override
	public RecipeType<?> getType() {
		return Type.SPIRIT_POWER_CRAFTING_TYPE;
	}
	
	class SpiritPowerCraftingRecipeFormat {
		JsonObject inputA;
		JsonObject inputB;
		JsonObject inputC;
		JsonObject inputD;
		String outputItem;
		int outputAmount;
	}
}
