package net.themoviea.frozeniimod.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.themoviea.frozeniimod.entity.projectile.IcePowerEntity;

public class IcePowerItem extends Item
{

	public IcePowerItem(Settings settings) 
	{
		super(settings);
	}
	
	/**
	 * This power unlike any other power, is just a normal projectile that returns the
	 * Frost bite effect where the player gets damaged just like poison.
	 */
	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) 
	{
		ItemStack itemStack = user.getStackInHand(hand);
	      world.playSound((PlayerEntity)null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (RANDOM.nextFloat() * 0.4F + 0.8F));
	      if (!world.isClient) {
	         IcePowerEntity icePowerEntity = new IcePowerEntity(world, user);
	         icePowerEntity.setItem(itemStack);
	         icePowerEntity.setProperties(user, user.pitch, user.yaw, 0.0F, 1.5F, 1.0F);
	         world.spawnEntity(icePowerEntity);
	      }

	      user.incrementStat(Stats.USED.getOrCreateStat(this));
	      if (!user.abilities.creativeMode) {
	         itemStack.decrement(1);
	      }

	      return TypedActionResult.success(itemStack, world.isClient());
	}
}
