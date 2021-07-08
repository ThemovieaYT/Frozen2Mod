package net.themoviea.frozeniimod.block.inventory_blocks;

import java.util.Optional;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.screen.SmithingScreenHandler;
import net.minecraft.stat.Stats;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.themoviea.frozeniimod.recipe.SpiritPowerCraftingRecipe;
import net.themoviea.frozeniimod.screen.SpiritPowerCraftingScreenHandler;

public class SpiritPowerCraftingBlock extends Block
{
	private static final Text SCREEN_TITLE = new TranslatableText("container.upgrade");
	public SpiritPowerCraftingBlock(Settings settings) 
	{
		super(settings);
	}
	
	
	@SuppressWarnings("resource")
	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) 
	{
		
		if(world.isClient) {
			return ActionResult.SUCCESS;
		} else {
	    	//MinecraftClient.getInstance().openScreen(new TestCraftingScreen(new TestCraftingGui());
	    	player.openHandledScreen(state.createScreenHandlerFactory(world, pos));
	    	player.incrementStat(Stats.INTERACT_WITH_CRAFTING_TABLE);
	    	return ActionResult.CONSUME;
		}
    }
	
	@Override
	public NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World world, BlockPos pos) 
	{
		// TODO Auto-generated method stub
		return new SimpleNamedScreenHandlerFactory((i, playerInventory, playerEntity) -> {
	         return new SpiritPowerCraftingScreenHandler(i, playerInventory, ScreenHandlerContext.create(world, pos));
	      }, SCREEN_TITLE);
	}
}
