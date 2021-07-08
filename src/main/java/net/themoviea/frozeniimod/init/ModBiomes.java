package net.themoviea.frozeniimod.init;

import net.fabricmc.fabric.api.biome.v1.OverworldBiomes;
import net.fabricmc.fabric.api.biome.v1.OverworldClimate;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.themoviea.frozeniimod.world.biome.FrozenIIBiomeCreator;

public class ModBiomes 
{
	public static final RegistryKey<Biome> ENCHANTED_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier("frozeniimod", "enchanted_forest"));
	
	@SuppressWarnings("deprecation")
	public static void registerFrozen2ModBiomes()
	{
		//BiomeFeatures
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier("frozeniimod", "enchanted"), ModFeatures.ENCHANTED);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier("frozeniimod", "tree_enchanted"), ModFeatures.TREE_ENCHANTED);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier("frozeniimod", "tree_enchanted_tall"), ModFeatures.TALL_ENCHANTED);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier("frozeniimod", "super_enchanted_tree"), ModFeatures.SUPER_ENCHANTED_TREE);

		//Ore Features
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier("frozeniimod", "ore_fire_spirit"), ModFeatures.FIRE_SPIRIT_ORE);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier("frozeniimod", "ore_wind_spirit"), ModFeatures.WIND_SPIRIT_ORE);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier("frozeniimod", "ore_water_spirit"), ModFeatures.WATER_SPIRIT_ORE);
		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier("frozeniimod", "ore_earth_spirit"), ModFeatures.EARTH_SPIRIT_ORE);
		
		//Forest
		Registry.register(BuiltinRegistries.BIOME, ENCHANTED_KEY.getValue(), FrozenIIBiomeCreator.createEnchantedForest(0.24F, 0.2F));
		Registry.register(BuiltinRegistries.BIOME, new Identifier("frozeniimod", "enchanted_forest_tall"), FrozenIIBiomeCreator.createTallEnchantedForest(0.24F, 0.2F));

		OverworldBiomes.addContinentalBiome(ENCHANTED_KEY, OverworldClimate.TEMPERATE, 4D);
	}
}
