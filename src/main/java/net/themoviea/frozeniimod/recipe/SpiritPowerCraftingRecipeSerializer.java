package net.themoviea.frozeniimod.recipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.registry.Registry;
import net.themoviea.frozeniimod.recipe.SpiritPowerCraftingRecipe.SpiritPowerCraftingRecipeFormat;

public class SpiritPowerCraftingRecipeSerializer implements RecipeSerializer<SpiritPowerCraftingRecipe> {
	
	HashMap<String, String> hash = new HashMap<>();
	private SpiritPowerCraftingRecipeSerializer() {	
	}
	
	/*
	 * Plan for changing logic:
	 * 1. create a list in the serializer.
	 * 2. in the read method (right at the bottom of this text), get each index of the array 
	 * and get the value of the dictionary in the array.
	 * 3. Compare the value to the name of all crystals.
	 * 4. get the index of the dictionary and store that to the list
	 */
	
	public static final SpiritPowerCraftingRecipeSerializer INSTANCE = new SpiritPowerCraftingRecipeSerializer();
	
	public static final Identifier ID = new Identifier("frozeniimod:spirit_power_crafting");
	@Override
	public SpiritPowerCraftingRecipe read(Identifier id, JsonObject json) {
		/*SpiritPowerCraftingRecipeFormat recipeJson = new Gson().fromJson(json, SpiritPowerCraftingRecipeFormat.class);
		if(recipeJson.inputA == null || recipeJson.inputB == null || recipeJson.inputC == null || recipeJson.inputD == null || recipeJson.outputItem == null) {
			throw new JsonSyntaxException("A required attribute is missing!");
		}
		Ingredient inputA = Ingredient.fromJson(recipeJson.inputA);
		Ingredient inputB = Ingredient.fromJson(recipeJson.inputB);
		Ingredient inputC = Ingredient.fromJson(recipeJson.inputC);
		Ingredient inputD = Ingredient.fromJson(recipeJson.inputD);
		
		Item outputItem = Registry.ITEM.getOrEmpty(new Identifier(recipeJson.outputItem))
				.orElseThrow(() -> new JsonSyntaxException("No such item called: " + recipeJson.outputItem));
		ItemStack output = new ItemStack(outputItem, recipeJson.outputAmount);
		
		return new SpiritPowerCraftingRecipe(id, inputA, inputB, inputC, inputD, output);
	*/
		String string = JsonHelper.getString(json, "group", "");
		DefaultedList<Ingredient> defaultedList = getIngredients(JsonHelper.getArray(json, "ingredients"));
		/*boolean bool = JsonHelper.getBoolean(json, "isShapedRequired");
		if(bool == true) {
			
		}*/
		/*JsonArray array = JsonHelper.getArray(json, "ingredients");
		String sepStr = array.toString().replace("[", "").replace("]", "");
		String dictionary[] = sepStr.split(",");
		for(int i = 0; i < dictionary.length; i++) {
			String dictionaryYes = dictionary[i].replace("{", "").replace("}", "");
			String[] dictSep = dictionaryYes.split(":", 2);
			System.out.println(dictSep[0].replace("\"", "").replace("\"", ""));
			System.out.println(dictSep[1].replace("\"", "").replace("\"", ""));
			hash.put(dictSep[0].replace("\"", "").replace("\"", ""), dictSep[1].replace("\"", "").replace("\"", ""));
		}*/
		

		if(defaultedList.isEmpty()) {
			throw new JsonParseException("No crystals for Spirit Power Crafter!");
		} else if (defaultedList.size() > 11) {
			throw new JsonParseException("Too many ingredients for Spirit Power Crafter to handle!");
		} else {
			ItemStack itemStack = SpiritPowerCraftingRecipe.getItemStack(JsonHelper.getObject(json, "result"));
			return new SpiritPowerCraftingRecipe(id, string, itemStack, defaultedList);
		}
	}
	
	private static DefaultedList<Ingredient> getIngredients(JsonArray json) {
		DefaultedList<Ingredient> defaultedList = DefaultedList.of();
		for(int i = 0; i < json.size(); ++i) {
			Ingredient ingredient = Ingredient.fromJson(json.get(i));
			if(!ingredient.isEmpty()) {
				defaultedList.add(ingredient);
			}
		}
		
		return defaultedList;
	}

	@Override
	public void write(PacketByteBuf buf, SpiritPowerCraftingRecipe recipe) {
		/*recipe.getInputA().write(buf);
		recipe.getInputB().write(buf);
		recipe.getInputC().write(buf);
		recipe.getInputD().write(buf);
		buf.writeItemStack(recipe.getOutput());*/
		buf.writeString(recipe.group);
        buf.writeVarInt(recipe.input.size());
        Iterator<Ingredient> var3 = recipe.input.iterator();

        while(var3.hasNext()) {
           Ingredient ingredient = (Ingredient)var3.next();
           ingredient.write(buf);
        }

        buf.writeItemStack(recipe.result);
	}
	
	@Override
	public SpiritPowerCraftingRecipe read(Identifier id, PacketByteBuf buf) {
		/*Ingredient inputA = Ingredient.fromPacket(buf);
		Ingredient inputB = Ingredient.fromPacket(buf);
		Ingredient inputC = Ingredient.fromPacket(buf);
		Ingredient inputD = Ingredient.fromPacket(buf);
		
		ItemStack output = buf.readItemStack();*/
		String string = buf.readString(32767);
        int i = buf.readVarInt();
        DefaultedList<Ingredient> defaultedList = DefaultedList.ofSize(i, Ingredient.EMPTY);
        for(int j = 0; j < defaultedList.size(); ++j) {
           defaultedList.set(j, Ingredient.fromPacket(buf));
        }

        ItemStack itemStack = buf.readItemStack();
        return new SpiritPowerCraftingRecipe(id, string, itemStack, defaultedList);
		
	}
}
