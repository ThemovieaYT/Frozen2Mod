package net.themoviea.frozeniimod.init;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.themoviea.frozeniimod.recipe.SpiritPowerCraftingRecipe;
import net.themoviea.frozeniimod.recipe.SpiritPowerCraftingRecipeSerializer;

public class ModRecipes 
{	
	public static void registerFrozen2RecipeTypes() 
	{
		//Recipe Types
		Registry.register(Registry.RECIPE_TYPE, new Identifier("frozeniimod", SpiritPowerCraftingRecipe.Type.ID), SpiritPowerCraftingRecipe.Type.SPIRIT_POWER_CRAFTING_TYPE);
		
		//Recipe Serializer
		Registry.register(Registry.RECIPE_SERIALIZER, SpiritPowerCraftingRecipeSerializer.ID, SpiritPowerCraftingRecipeSerializer.INSTANCE);
	}
}
