package net.themoviea.frozeniimod.world.biome;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.DefaultBiomeCreator;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.biome.SpawnSettings.SpawnEntry;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import net.minecraft.world.gen.feature.ConfiguredStructureFeatures;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilders;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;
import net.themoviea.frozeniimod.init.ModEntities;
import net.themoviea.frozeniimod.world.gen.feature.FrozenIIModBiomeFeatures;

public class FrozenIIBiomeCreator
{

    public static Biome createEnchantedForest(float depth, float scale) 
    {
    //  super(new Biome.Settings().configureSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_CONFIG).precipitation(Biome.Precipitation.RAIN).category(Biome.Category.FOREST).depth(0.24F).scale(0.2F).temperature(0.8f).downfall(0.7F).effects((new BiomeEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(16765629).build()).parent((String)null));
        SpawnSettings.Builder spawnSettingsBuilder = new SpawnSettings.Builder();
        spawnSettingsBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.SHEEP, 12, 4, 4));
        spawnSettingsBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.PIG, 12, 4, 4));
        spawnSettingsBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.COW, 12, 4, 4));
        spawnSettingsBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.CHICKEN, 12, 4, 4));
        spawnSettingsBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(ModEntities.BRUNI, 12, 4, 4));
        spawnSettingsBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(ModEntities.GALE, 12, 4, 4));
        spawnSettingsBuilder.playerSpawnFriendly();

        GenerationSettings.Builder generationSettingsBuilder = (new GenerationSettings.Builder()).surfaceBuilder(ConfiguredSurfaceBuilders.GRASS);

        generationSettingsBuilder.structureFeature(ConfiguredStructureFeatures.PILLAGER_OUTPOST);
        generationSettingsBuilder.structureFeature(ConfiguredStructureFeatures.RUINED_PORTAL);

        FrozenIIModBiomeFeatures.addEnchantedTrees(generationSettingsBuilder);
        FrozenIIModBiomeFeatures.addSpiritOres(generationSettingsBuilder);
        DefaultBiomeFeatures.addMineables(generationSettingsBuilder);
        DefaultBiomeFeatures.addLandCarvers(generationSettingsBuilder);
        DefaultBiomeFeatures.addDungeons(generationSettingsBuilder);
        DefaultBiomeFeatures.addPlainsTallGrass(generationSettingsBuilder);
        DefaultBiomeFeatures.addPlainsFeatures(generationSettingsBuilder);
        DefaultBiomeFeatures.addDefaultMushrooms(generationSettingsBuilder);
        DefaultBiomeFeatures.addSprings(generationSettingsBuilder);
        DefaultBiomeFeatures.addFrozenTopLayer(generationSettingsBuilder);

        BiomeEffects.Builder biomeEffectsBuilder = new BiomeEffects.Builder();
        biomeEffectsBuilder.skyColor(12371711);
        biomeEffectsBuilder.waterColor(4159204);
        biomeEffectsBuilder.waterFogColor(329011);
        biomeEffectsBuilder.fogColor(16765629);
        biomeEffectsBuilder.foliageColor(14575901);
        biomeEffectsBuilder.grassColor(14575901);

        Biome.Builder biomeBuilder = new Biome.Builder();
        biomeBuilder.precipitation(Biome.Precipitation.RAIN);
        biomeBuilder.depth(depth);
        biomeBuilder.scale(scale);
        biomeBuilder.temperature(0.8F);
        biomeBuilder.downfall(0.7F);
        biomeBuilder.category(Biome.Category.FOREST);
        biomeBuilder.effects(biomeEffectsBuilder.build());
        biomeBuilder.generationSettings(generationSettingsBuilder.build());
        biomeBuilder.spawnSettings(spawnSettingsBuilder.build());

        return biomeBuilder.build();
    }

    public static Biome createTallEnchantedForest(float depth, float scale) 
    {
        GenerationSettings.Builder generationSettingsBuilder = (new GenerationSettings.Builder()).surfaceBuilder(ConfiguredSurfaceBuilders.GRASS);
        FrozenIIModBiomeFeatures.addTallEnchantedTrees(generationSettingsBuilder);

        return createEnchantedForest(depth, scale);
    }
    
    private static GenerationSettings.Builder createOceanGenerationSettings(ConfiguredSurfaceBuilder<TernarySurfaceConfig> configuredSurfaceBuilder, boolean bl, boolean bl2, boolean bl3) {
        GenerationSettings.Builder builder = (new GenerationSettings.Builder()).surfaceBuilder(configuredSurfaceBuilder);
        ConfiguredStructureFeature<?, ?> configuredStructureFeature = bl2 ? ConfiguredStructureFeatures.OCEAN_RUIN_WARM : ConfiguredStructureFeatures.OCEAN_RUIN_COLD;
        if (bl3) {
           if (bl) {
              builder.structureFeature(ConfiguredStructureFeatures.MONUMENT);
           }

           DefaultBiomeFeatures.addOceanStructures(builder);
           builder.structureFeature(configuredStructureFeature);
        } else {
           builder.structureFeature(configuredStructureFeature);
           if (bl) {
              builder.structureFeature(ConfiguredStructureFeatures.MONUMENT);
           }

           DefaultBiomeFeatures.addOceanStructures(builder);
        }

        builder.structureFeature(ConfiguredStructureFeatures.RUINED_PORTAL_OCEAN);
        DefaultBiomeFeatures.addOceanCarvers(builder);
        DefaultBiomeFeatures.addDefaultLakes(builder);
        DefaultBiomeFeatures.addDungeons(builder);
        DefaultBiomeFeatures.addMineables(builder);
        DefaultBiomeFeatures.addDefaultOres(builder);
        DefaultBiomeFeatures.addDefaultDisks(builder);
        DefaultBiomeFeatures.addWaterBiomeOakTrees(builder);
        DefaultBiomeFeatures.addDefaultFlowers(builder);
        DefaultBiomeFeatures.addDefaultGrass(builder);
        DefaultBiomeFeatures.addDefaultMushrooms(builder);
        DefaultBiomeFeatures.addDefaultVegetation(builder);
        DefaultBiomeFeatures.addSprings(builder);
        return builder;
    }
    
    private static Biome createOcean(SpawnSettings.Builder builder, int waterColor, int waterFogColor, boolean deep, GenerationSettings.Builder builder2) {
        return (new Biome.Builder()).precipitation(Biome.Precipitation.RAIN).category(Biome.Category.OCEAN).depth(deep ? -1.8F : -1.0F).scale(0.1F).temperature(0.5F).downfall(0.5F).effects((new BiomeEffects.Builder()).waterColor(waterColor).waterFogColor(waterFogColor).fogColor(12638463).skyColor(12371711).moodSound(BiomeMoodSound.CAVE).build()).spawnSettings(builder.build()).generationSettings(builder2.build()).build();
    }
    
    public static Biome createDarkSea(float depth, float scale, boolean deep) {
    	SpawnSettings.Builder spawnSettingsBuilder = new SpawnSettings.Builder();
    	boolean bl = !deep;
    	GenerationSettings.Builder generationSettingsBuilder = createOceanGenerationSettings(ConfiguredSurfaceBuilders.GRASS, deep, false, bl);
    	return createOcean(spawnSettingsBuilder, 4159204, 329011, deep, generationSettingsBuilder);
    }
}