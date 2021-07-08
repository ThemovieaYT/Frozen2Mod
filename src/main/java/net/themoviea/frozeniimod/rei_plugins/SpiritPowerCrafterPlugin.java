package net.themoviea.frozeniimod.rei_plugins;

import me.shedaniel.math.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.jetbrains.annotations.NotNull;

import com.google.common.collect.Lists;

import it.unimi.dsi.fastutil.ints.IntList;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.AutoTransferHandler;
import me.shedaniel.rei.api.BaseBoundsHandler;
import me.shedaniel.rei.api.DisplayHelper;
import me.shedaniel.rei.api.EntryStack;
import me.shedaniel.rei.api.REIHelper;
import me.shedaniel.rei.api.RecipeCategory;
import me.shedaniel.rei.api.RecipeDisplay;
import me.shedaniel.rei.api.RecipeHelper;
import me.shedaniel.rei.api.TransferRecipeCategory;
import me.shedaniel.rei.api.TransferRecipeDisplay;
import me.shedaniel.rei.api.plugins.REIPluginV0;
import me.shedaniel.rei.api.widgets.Widgets;
import me.shedaniel.rei.gui.widget.Widget;
import me.shedaniel.rei.plugin.DefaultPlugin;
import me.shedaniel.rei.plugin.autocrafting.DefaultRecipeBookHandler;
import me.shedaniel.rei.plugin.containers.CraftingContainerInfoWrapper;
import me.shedaniel.rei.plugin.crafting.DefaultCraftingCategory;
import me.shedaniel.rei.server.ContainerInfo;
import me.shedaniel.rei.server.ContainerInfoHandler;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.MathHelper;
import net.themoviea.frozeniimod.client.gui.screen.ingame.spiritPowerCrafting.SpiritPowerCraftingScreen;
import net.themoviea.frozeniimod.init.ModBlocks;
import net.themoviea.frozeniimod.init.ModItems;
import net.themoviea.frozeniimod.recipe.SpiritPowerCraftingRecipe;
import net.themoviea.frozeniimod.screen.SpiritPowerCraftingScreenHandler;

public class SpiritPowerCrafterPlugin implements REIPluginV0 {

	public static final Identifier SPIRIT_POWER_CRAFTING = new Identifier("frozeniimod", "plugins/spirit_power_crafting");
	@Override
	public Identifier getPluginIdentifier() {
		return new Identifier("frozeniimod:spirit_power_crafter_plugin");
	}

	@Override
	public void registerOthers(RecipeHelper recipeHelper) {
		recipeHelper.registerAutoCraftingHandler(new DefaultRecipeBookHandler());
		recipeHelper.registerWorkingStations(SPIRIT_POWER_CRAFTING, EntryStack.create(ModItems.SPIRIT_POWER_CRAFTER));
	}
	
	@Override
	public void registerBounds(DisplayHelper displayHelper) {
		BaseBoundsHandler baseBoundsHandler = BaseBoundsHandler.getInstance();
		baseBoundsHandler.registerExclusionZones(SpiritPowerCraftingScreen.class, () -> {
			SpiritPowerCraftingScreen screen = (SpiritPowerCraftingScreen) MinecraftClient.getInstance().currentScreen;
			return new ArrayList<>();
		});
	}
	
	@Override
	public void registerPluginCategories(RecipeHelper recipeHelper) {
		recipeHelper.registerCategories(
				new SpiritPowerCraftingCategory()
		);
	}
	
	@Override
	public void registerRecipeDisplays(RecipeHelper recipeHelper) {
		recipeHelper.registerRecipes(SPIRIT_POWER_CRAFTING, SpiritPowerCraftingRecipe.class, SpiritPowerCraftingDisplay::new);
	}
	
	public static class SpiritPowerCraftingCategory implements RecipeCategory<SpiritPowerCraftingDisplay> {

		@Override
		public @NotNull Identifier getIdentifier() {
			return SPIRIT_POWER_CRAFTING;
		}
		
		@Override
		public @NotNull EntryStack getLogo() {
			return EntryStack.create(ModBlocks.SPIRIT_POWER_CRAFTER);
		}

		@Override
		public @NotNull String getCategoryName() {
			return I18n.translate("category.rei.spirit_power_crafting");
		}
		
		@Override
		public @NotNull List<Widget> setupDisplay(SpiritPowerCraftingDisplay display, Rectangle bounds) {
			Point startPoint = new Point(bounds.getCenterX(), bounds.getCenterY());
			List<Widget> widgets = Lists.newArrayList();
			widgets.add(Widgets.createRecipeBase(bounds));
	        widgets.add(Widgets.createResultSlotBackground(new Point(startPoint.x - 8, startPoint.y - 21)));
	        if(display.display.getInput1().size() > 0) {
		        widgets.add(Widgets.createSlot(new Point(startPoint.x - 40, startPoint.y - 4)).entries(display.getInputs1().get(0)).disableBackground().markInput());
				if(display.display.getInput1().size() > 1) {
					widgets.add(Widgets.createSlot(new Point(startPoint.x - 40, startPoint.y - 40)).entries(display.getInputs1().get(1)).disableBackground().markInput());
					if(display.display.getInput1().size() > 2) {
						widgets.add(Widgets.createSlot(new Point(startPoint.x + 24, startPoint.y - 4)).entries(display.getInputs1().get(2)).disableBackground().markInput());
					 	if(display.display.getInput1().size() > 3) {
					    	widgets.add(Widgets.createSlot(new Point(startPoint.x + 24, startPoint.y - 40)).entries(display.getInputs1().get(3)).disableBackground().markInput());
					 	}
					}
				}
	        }
	        for(int i = 0; i < display.display.getInput2().size(); i++) {
		        widgets.add(Widgets.createSlot(new Point(startPoint.x - 62 + (i * 18), startPoint.y + 30)).entries(display.getInputs2().get(i)).markInput());
		    }
	        widgets.add(Widgets.createSlot(new Point(startPoint.x - 8, startPoint.y - 21)).entries(display.getResultingEntries().get(0)).disableBackground().markOutput());
	        
	        widgets.add(Widgets.createSlotBackground(new Point(startPoint.x - 40, startPoint.y - 4)));
	        widgets.add(Widgets.createSlotBackground(new Point(startPoint.x - 40, startPoint.y - 40)));
	        widgets.add(Widgets.createSlotBackground(new Point(startPoint.x + 24, startPoint.y - 4)));
	        widgets.add(Widgets.createSlotBackground(new Point(startPoint.x + 24, startPoint.y - 40)));
	        return widgets;
		}
		
		@Override
		public int getDisplayHeight() {
			return 131;
		}
		
		@Override
		public int getDisplayWidth(SpiritPowerCraftingDisplay display) {
			return 150;
		}
	}
	
	@Environment(EnvType.CLIENT)
	public static class SpiritPowerCraftingDisplay implements RecipeDisplay {
		private List<List<EntryStack>> inputs;
		private List<List<EntryStack>> inputs2;
		private List<List<EntryStack>> totalInputs;
		private List<EntryStack> output;
		private SpiritPowerCraftingRecipe display;
		
		public SpiritPowerCraftingDisplay(SpiritPowerCraftingRecipe recipe) {
			this(recipe.getInput1(), recipe.getInput2(), recipe.getOutput());
			this.display = recipe;
		}
		
		public SpiritPowerCraftingDisplay(DefaultedList<Ingredient> ingredients, DefaultedList<Ingredient> ingredients2, ItemStack output) {
			this.inputs = EntryStack.ofIngredients(ingredients);
			this.inputs2 = EntryStack.ofIngredients(ingredients2);
			this.totalInputs = new ArrayList<>();
			this.totalInputs.addAll(inputs);
			this.totalInputs.addAll(inputs2);
			this.output = Collections.singletonList(EntryStack.create(output));
		}

		@Override
		public @NotNull Optional<Identifier> getRecipeLocation() {
			return Optional.ofNullable(display).map(SpiritPowerCraftingRecipe::getId);
		}
		
		@Override
		public @NotNull List<List<EntryStack>> getInputEntries() {
			return totalInputs;
		}
		
		public @NotNull List<List<EntryStack>> getInputs1() {
			return inputs;
		}
		
		public @NotNull List<List<EntryStack>> getInputs2() {
			return inputs2;
		}
		
		@Override
		public @NotNull List<List<EntryStack>> getResultingEntries() {
			return Collections.singletonList(output);
		}

		@Override
		public @NotNull Identifier getRecipeCategory() {
			// TODO Auto-generated method stub
			return SPIRIT_POWER_CRAFTING;
		}
		
		@Override
		public @NotNull List<List<EntryStack>> getRequiredEntries() {
			return getInputEntries();
		}
	}
}
