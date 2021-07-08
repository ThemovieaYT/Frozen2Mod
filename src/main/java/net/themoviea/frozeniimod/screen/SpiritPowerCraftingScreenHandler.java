package net.themoviea.frozeniimod.screen;

import java.util.Optional;

import net.fabricmc.api.EnvType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.ScreenHandlerSlotUpdateS2CPacket;
import net.minecraft.recipe.CraftingRecipe;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeFinder;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.screen.AbstractRecipeScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.CraftingResultSlot;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;
import net.themoviea.frozeniimod.init.ModBlocks;
import net.themoviea.frozeniimod.init.ModItems;
import net.themoviea.frozeniimod.init.ModScreenHandlers;
import net.themoviea.frozeniimod.recipe.SpiritPowerCraftingRecipe;
import net.themoviea.frozeniimod.screen.slot.SpiritCrystalSlot;
import net.themoviea.frozeniimod.screen.slot.SpiritNormalSlot;
import net.themoviea.frozeniimod.screen.slot.SpiritPowerResultSlot;

public class SpiritPowerCraftingScreenHandler extends AbstractRecipeScreenHandler<CraftingInventory> {
	private final CraftingInventory input;
	private final CraftingResultInventory result;
	private final ScreenHandlerContext context;
	private final PlayerEntity player;
	
	public SpiritPowerCraftingScreenHandler(int syncId, PlayerInventory playerInventory) {
	      this(syncId, playerInventory, ScreenHandlerContext.EMPTY);
	}
	
	public SpiritPowerCraftingScreenHandler(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
		super(ModScreenHandlers.SPIRIT_POWER_SCREEN_HANDLER, syncId);
		this.input = new CraftingInventory(this, 11, 1);
		this.result = new CraftingResultInventory();
		this.context = context;
		this.player = playerInventory.player;
		this.addSlot(new SpiritPowerResultSlot(playerInventory.player, this.input, this.result, 11, 80, 57));
		
		this.addSlot(new SpiritCrystalSlot(this, this.input, 0, 44, 21));
		this.addSlot(new SpiritCrystalSlot(this, this.input, 1, 116, 21));
		this.addSlot(new SpiritCrystalSlot(this, this.input, 2, 116, 93));
		this.addSlot(new SpiritCrystalSlot(this, this.input, 3, 44, 93));
		
		this.addSlot(new SpiritNormalSlot(this, this.input, 4, 26, 129));
		this.addSlot(new SpiritNormalSlot(this, this.input, 5, 44, 129));
		this.addSlot(new SpiritNormalSlot(this, this.input, 6, 62, 129));
		this.addSlot(new SpiritNormalSlot(this, this.input, 7, 80, 129));
		this.addSlot(new SpiritNormalSlot(this, this.input, 8, 98, 129));
		this.addSlot(new SpiritNormalSlot(this, this.input, 9, 116, 129));
		this.addSlot(new SpiritNormalSlot(this, 
				this.input, 10, 134, 129));
		
		int m;
		int l;
	
		for(m = 0; m < 3; ++m) {
	         for(l = 0; l < 9; ++l) {
	            this.addSlot(new Slot(playerInventory, l + m * 9 + 9, 8 + l * 18, 161 + m * 18));
	         }
	      }

	      for(m = 0; m < 9; ++m) {
	         this.addSlot(new Slot(playerInventory, m, 8 + m * 18, 219));
	      }
	}

	@Override
	public void populateRecipeFinder(RecipeFinder finder) {
		this.input.provideRecipeInputs(finder);
		
	}

	@Override
	public void clearCraftingSlots() {
		this.input.clear();
		this.result.clear();
	}

	@Override
	public boolean matches(Recipe<? super CraftingInventory> recipe) {
		return recipe.matches(this.input, this.player.world);
	}
	
	@Override
	public void fillInputSlots(boolean craftAll, Recipe<?> recipe, ServerPlayerEntity player) {
		super.fillInputSlots(craftAll, recipe, player);
	}

	protected static void updateResult(int syncId, World world, PlayerEntity player, CraftingInventory craftingInventory, CraftingResultInventory resultInventory) {
	      if (!world.isClient) {
	         ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity)player;
	         ItemStack itemStack = ItemStack.EMPTY;
	         Optional<SpiritPowerCraftingRecipe> optional = world.getServer().getRecipeManager().getFirstMatch(SpiritPowerCraftingRecipe.Type.SPIRIT_POWER_CRAFTING_TYPE, craftingInventory, world);
	         if (optional.isPresent()) {
	            SpiritPowerCraftingRecipe craftingRecipe = (SpiritPowerCraftingRecipe)optional.get();
	            if (resultInventory.shouldCraftRecipe(world, serverPlayerEntity, craftingRecipe)) {
	               itemStack = craftingRecipe.craft(craftingInventory);
	            }
	         }

	         resultInventory.setStack(0, itemStack);
	         serverPlayerEntity.networkHandler.sendPacket(new ScreenHandlerSlotUpdateS2CPacket(syncId, 0, itemStack));
	      }
	   }
	
	
	@Override
	public void onContentChanged(Inventory inventory) {
		this.context.run((world, blockPos) -> {
			updateResult(this.syncId, world, this.player, this.input, this.result);
		});
	}
	
	@Override
	public ItemStack transferSlot(PlayerEntity player, int index) {
		ItemStack itemStack = ItemStack.EMPTY;
	      Slot slot = (Slot)this.slots.get(index);
	      if (slot != null && slot.hasStack()) {
	         ItemStack itemStack2 = slot.getStack();
	         itemStack = itemStack2.copy();
	         if (index == 11) {
	            this.context.run((world, blockPos) -> {
	               itemStack2.getItem().onCraft(itemStack2, world, player);
	            });
	            if (!this.insertItem(itemStack2, 12, 48, true)) {
	               return ItemStack.EMPTY;
	            }

	            slot.onStackChanged(itemStack2, itemStack);
	         } else if (index >= 12 && index < 48) {
	        	 if(isCrystal(itemStack2)) {
	        		 if(!this.insertItem(itemStack2, 0, 5, false)) {
	        			 return ItemStack.EMPTY;
	        		 }
	        	 } else if(!isCrystal(itemStack2)) {
		            if (!this.insertItem(itemStack2, 5, 12, false)) {
		               if (index < 39) {
		                  if (!this.insertItem(itemStack2, 39, 48, false)) {
		                     return ItemStack.EMPTY;
		                  }
		               } else if (!this.insertItem(itemStack2, 12, 39, false)) {
		                  return ItemStack.EMPTY;
		               }
		            }
	        	 }
	         } else if (!this.insertItem(itemStack2, 12, 48, false)) {
	            return ItemStack.EMPTY;
	         }

	         if (itemStack2.isEmpty()) {
	            slot.setStack(ItemStack.EMPTY);
	         } else {
	            slot.markDirty();
	         }

	         if (itemStack2.getCount() == itemStack.getCount()) {
	            return ItemStack.EMPTY;
	         }

	         ItemStack itemStack3 = slot.onTakeItem(player, itemStack2);
	         if (index == 4) {
	            player.dropItem(itemStack3, false);
	         }
	      }

	      return itemStack;
	}
	
	public static boolean isCrystal(ItemStack itemStack) {
		if(itemStack.getItem() == ModItems.FIRE_SPIRIT_CRYSTAL || itemStack.getItem() == ModItems.WATER_SPIRIT_CRYSTAL || itemStack.getItem() == ModItems.WIND_SPIRIT_CRYSTAL || itemStack.getItem() == ModItems.EARTH_SPIRIT_CRYSTAL || itemStack.getItem() == ModItems.FIFTH_SPIRIT_CRYSTAL) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isCrystalString(String string) {
		if(string == "frozeniimod:fire_spirit_crystal" || string == "frozeniimod:water_spirit_crystal" || string == "frozeniimod:earth_spirit_crystal" || string == "frozeniimod:wind_spirit_crystal" || string == "frozeniimod:fifth_spirit_crystal") {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean canInsertIntoSlot(ItemStack stack, Slot slot) {
		return slot.inventory != this.result && super.canInsertIntoSlot(stack, slot);
	}
	
	@Override
	public int getCraftingResultSlotIndex() {
		// TODO Auto-generated method stub
		return 11;
	}

	@Override
	public int getCraftingWidth() {
		return this.input.getWidth();
	}

	@Override
	public int getCraftingHeight() {
		return this.input.getHeight();
	}

	@Override
	public int getCraftingSlotCount() {
		return 12;
	}

	@Override
	public RecipeBookCategory getCategory() {
		return null;
	}

	@Override
	public boolean canUse(PlayerEntity player) {
		return canUse(this.context, player, ModBlocks.SPIRIT_POWER_CRAFTER);
	}
	
	@Override
	public void close(PlayerEntity player) {
		super.close(player);
		this.context.run((world, blockPos) -> {
			this.dropInventory(player, world, this.input);
		});
	}
	
}
