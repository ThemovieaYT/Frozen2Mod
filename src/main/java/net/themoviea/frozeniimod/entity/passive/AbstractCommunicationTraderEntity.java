package net.themoviea.frozeniimod.entity.passive;

import java.util.OptionalInt;
import java.util.Set;

import org.jetbrains.annotations.Nullable;

import com.google.common.collect.Sets;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.screen.MerchantScreenHandler;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.village.Merchant;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOfferList;
import net.minecraft.village.TradeOffers;
import net.minecraft.world.World;
import net.themoviea.themovieapi_village.village.ThemovieAPITradeOffers;

public abstract class AbstractCommunicationTraderEntity extends AbstractCommunicationEntity implements Merchant {

	@Nullable
	protected TradeOfferList offers;
	
	protected AbstractCommunicationTraderEntity(EntityType<? extends AbstractCommunicationEntity> entityType,
			World world) {
		super(entityType, world);
	}
	
	@Override
	public TradeOfferList getOffers() {
		if(this.offers == null) {
			this.offers = new TradeOfferList();
			this.fillRecipes();
		}
		
		return this.offers;
	}
	
	@Override
	public void setCurrentCustomer(PlayerEntity customer) {
		this.setCurrentPerson(customer);
	}
	
	@Override
	public PlayerEntity getCurrentCustomer() {
		return this.getCurrentPerson();
	}
	
	@Override
	public void trade(TradeOffer offer) {
		offer.use();
		this.afterUsing(offer);
	}
	
	@Override
	public void setOffersFromServer(TradeOfferList offers) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setExperienceFromServer(int experience) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean isLeveledMerchant() {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public Entity moveToWorld(ServerWorld destination) {
		this.resetPerson();
		return super.moveToWorld(destination);
	}
	
	@Override
	public void onDeath(DamageSource source) {
		super.onDeath(source);
		this.resetPerson();
	}
	
	@Override
	public World getMerchantWorld() {
		return getCommunicationWorld();
	}
	
	@Override
	public void sendOffers(PlayerEntity playerEntity, Text text, int i) {
		OptionalInt optionalInt = playerEntity.openHandledScreen(new SimpleNamedScreenHandlerFactory((ix, playerInventory, playerEntityx) -> {
	         return new MerchantScreenHandler(ix, playerInventory, this);
	      }, text));
	      if (optionalInt.isPresent()) {
	         TradeOfferList tradeOfferList = this.getOffers();
	         if (!tradeOfferList.isEmpty()) {
	            playerEntity.sendTradeOffers(optionalInt.getAsInt(), tradeOfferList, i, this.getExperience(), this.isLeveledMerchant(), this.canRefreshTrades());
	         }
	      }
	}
	
	protected abstract void afterUsing(TradeOffer offer);
	
	public void writeCustomDataToTag(CompoundTag tag) {
	      super.writeCustomDataToTag(tag);
	      TradeOfferList tradeOfferList = this.getOffers();
	      if (!tradeOfferList.isEmpty()) {
	         tag.put("Offers", tradeOfferList.toTag());
	      }

	      tag.put("Inventory", this.inventory.getTags());
	}
	
	public void readCustomDataFromTag(CompoundTag tag) {
		super.readCustomDataFromTag(tag);
		if (tag.contains("Offers", 10)) {
			this.offers = new TradeOfferList(tag.getCompound("Offers"));
		}

		this.inventory.readTags(tag.getList("Inventory", 10));
	}
	
	protected abstract void fillRecipes();

	protected void fillRecipesFromPool(TradeOfferList recipeList, ThemovieAPITradeOffers.Factory[] pool, int count) {
		Set<Integer> set = Sets.newHashSet();
		if (pool.length > count) {
			while(set.size() < count) {
	            set.add(this.random.nextInt(pool.length));
	         }
	      } else {
	         for(int i = 0; i < pool.length; ++i) {
	            set.add(i);
	         }
	      }
	}
}
