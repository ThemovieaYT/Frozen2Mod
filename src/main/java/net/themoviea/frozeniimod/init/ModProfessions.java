package net.themoviea.frozeniimod.init;

import java.util.ArrayList;
import java.util.Random;

import org.jetbrains.annotations.Nullable;

import com.google.common.collect.ImmutableMap;

import net.minecraft.entity.Entity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.village.TradeOffer;
import net.minecraft.world.poi.PointOfInterestType;
import net.themoviea.frozeniimod.Frozen2Mod;
import net.themoviea.frozeniimod.entity.passive.ArendellianEntity;
import net.themoviea.frozeniimod.init.ModProfessions.GetNeededItemFactory;
import net.themoviea.themovieapi_base.exceptions.InputNotAnObjectException;
import net.themoviea.themovieapi_base.registering.CustomEasyRegister;
import net.themoviea.themovieapi_village.ThemovieAPIVillage;
import net.themoviea.themovieapi_village.village.EntityProfession;
import net.themoviea.themovieapi_village.village.ThemovieAPITradeOffers;

public class ModProfessions {
//	public static final PointOfInterestType QUEENPOI = PointOfInterestHelper.register(new Identifier("frozeniimod", "queenpoi"), 1, 45, ModBlocks.SPIRIT_POWER_CRAFTER);
	public static final EntityProfession STUFF = EntityProfession.register(new Identifier("frozeniimod", "stuff").toString(), PointOfInterestType.ARMORER, SoundEvents.ENTITY_VILLAGER_WORK_ARMORER);
	public static final EntityProfession SPIRIT_POWER_CRAFTER_MASON = EntityProfession.register(new Identifier("frozeniimod", "spirit_power_crafter_mason").toString(), ModPointOfInterestTypes.SPIRIT_POWER_CRAFTER, SoundEvents.ENTITY_VILLAGER_WORK_MASON);
	public static final EntityProfession QUEEN = EntityProfession.register(new Identifier("frozeniimod", "queen").toString(), ModPointOfInterestTypes.SPIRIT_POWER_CRAFTER, null);
	public static void registerCustomProfessions() {
		ThemovieAPITradeOffers.addTradeOffersToProfessions(ModProfessions.SPIRIT_POWER_CRAFTER_MASON, ImmutableMap.of(1, new ThemovieAPITradeOffers.Factory[]{new ThemovieAPITradeOffers.OneEmeraldFactory(Items.OAK_PLANKS, 2, 30, 10),new GetNeededItemFactory(Items.ANDESITE, Items.DIORITE, 2, 10)}));
	}
	
	public static class GetNeededItemFactory implements ThemovieAPITradeOffers.Factory {
		private final Item buy;
		private final Item sell;
		private final int price;
		private final int experience;
		private final float multiplier;
		
		public GetNeededItemFactory(ItemConvertible buyItem, ItemConvertible sellItem, int price, int experience) {
			this.buy = buyItem.asItem();
			this.sell = sellItem.asItem();
			this.price = price;
			this.experience = experience;
			this.multiplier = 0.5f;
		}
		@Override
		public @Nullable TradeOffer create(Entity entity, Random random) {
			SimpleInventory inventory = ((ArendellianEntity)entity).getInventory();
			ItemStack buyItemStack = new ItemStack(this.buy, this.price);
			ItemStack sellItemStack = new ItemStack(this.sell, 3);
			return new TradeOffer(buyItemStack, sellItemStack, 30, this.experience, this.multiplier);
		}
		
	}
}
