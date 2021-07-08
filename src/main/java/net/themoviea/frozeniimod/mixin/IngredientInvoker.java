package net.themoviea.frozeniimod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;

@Mixin(Ingredient.class)
public interface IngredientInvoker {

	@Invoker("cacheMatchingStacks")
	public void cacheSpiritMatchingStacks();
}
