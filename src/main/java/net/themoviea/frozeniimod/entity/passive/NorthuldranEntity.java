package net.themoviea.frozeniimod.entity.passive;

import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.village.TradeOffer;
import net.minecraft.world.World;
import net.themoviea.themovieapi_village.village.VillageEntityData;
import net.themoviea.themovieapi_village.village.VillageEntityDataContainer;

public class NorthuldranEntity extends AbstractCommunicationTraderEntity implements VillageEntityDataContainer 
{

	protected NorthuldranEntity(EntityType<? extends AbstractCommunicationEntity> entityType, World world) 
	{
		super(entityType, world);
	}

	@Override
	public void onSellingItem(ItemStack stack) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SoundEvent getYesSound() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VillageEntityData getVillageEntityData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void afterUsing(TradeOffer offer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void fillRecipes() {
		// TODO Auto-generated method stub
		
	}
	
}
