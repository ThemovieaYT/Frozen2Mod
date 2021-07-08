package net.themoviea.frozeniimod.frozeniivillage;

import java.util.Map;
import java.util.Random;

import org.jetbrains.annotations.Nullable;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Util;
import net.minecraft.village.TradeOffer;
import net.themoviea.frozeniimod.init.ModProfessions;
import net.themoviea.themovieapi_village.village.EntityProfession;
import net.themoviea.themovieapi_village.village.ThemovieAPITradeOffers;
import net.themoviea.themovieapi_village.village.ThemovieAPITradeOffers.Factory;

public class Frozen2Offers {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static final Map<EntityProfession, Int2ObjectMap<ThemovieAPITradeOffers.Factory[]>> PROFESSION_TRADES = (Map)Util.make(Maps.newHashMap(), (map) -> {
		map.put(ModProfessions.SPIRIT_POWER_CRAFTER_MASON, copyToFastUtilMap(ImmutableMap.of(1, new ThemovieAPITradeOffers.Factory[]{new ThemovieAPITradeOffers.OneEmeraldFactory(Items.OAK_PLANKS, 2, 30, 10), new BuyReturnItemFactory(Items.ACACIA_DOOR, Items.ACACIA_BUTTON, 2, 4, 30, 10)})));
	});
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static Int2ObjectMap<ThemovieAPITradeOffers.Factory[]> copyToFastUtilMap(ImmutableMap<Integer, ThemovieAPITradeOffers.Factory[]> map) {
	      return new Int2ObjectOpenHashMap(map);
	}
	
	public static class BuyReturnItemFactory implements ThemovieAPITradeOffers.Factory {
		private final Item buy, sell;
		private final int price, sellAmt;
		private final int maxUses;
		private final int experience;
		private final float multiplier;
		
		public BuyReturnItemFactory(ItemConvertible buyItem, ItemConvertible sellItem, int price, int sellAmt, int maxUses, int experience) {
			this.buy = buyItem.asItem();
			this.sell = buyItem.asItem();
			this.price = price;
			this.sellAmt = sellAmt;
			this.maxUses = maxUses;
			this.experience = experience;
			this.multiplier = 0.05F;
		}
		@Override
		public @Nullable TradeOffer create(Entity entity, Random random) {
			ItemStack itemStack = new ItemStack(this.buy, this.price);
			ItemStack itemStackSell = new ItemStack(this.sell, this.sellAmt);
			return new TradeOffer(itemStack, itemStackSell, this.maxUses, this.experience, this.multiplier);
		}
		
	}
}
