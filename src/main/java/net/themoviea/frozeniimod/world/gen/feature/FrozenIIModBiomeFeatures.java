package net.themoviea.frozeniimod.world.gen.feature;

import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.themoviea.frozeniimod.init.ModFeatures;

public class FrozenIIModBiomeFeatures 
{
    public static void addEnchantedTrees(GenerationSettings.Builder builder)
    {
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ModFeatures.TREE_ENCHANTED);
    }

    public static void addTallEnchantedTrees(GenerationSettings.Builder builder)
    {
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ModFeatures.TALL_ENCHANTED);
    }
    
    public static void addSpiritOres(GenerationSettings.Builder builder)
    {
    	builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ModFeatures.FIRE_SPIRIT_ORE);
    	builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ModFeatures.EARTH_SPIRIT_ORE);
    	builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ModFeatures.WIND_SPIRIT_ORE);
    	builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ModFeatures.WATER_SPIRIT_ORE);
    	builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ConfiguredFeatures.ORE_COAL);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ConfiguredFeatures.ORE_IRON);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ConfiguredFeatures.ORE_GOLD);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ConfiguredFeatures.ORE_REDSTONE);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ConfiguredFeatures.ORE_DIAMOND);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ConfiguredFeatures.ORE_LAPIS);
    }
}