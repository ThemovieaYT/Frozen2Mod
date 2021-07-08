package net.themoviea.frozeniimod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.recipe.Ingredient;
import net.minecraft.item.ItemStack;

@Mixin(Ingredient.class)
public interface IngredientMixin {
	
	@Accessor
	ItemStack[] getMatchingStacks();
}
