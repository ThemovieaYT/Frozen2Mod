package net.themoviea.frozeniimod.init;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.structure.v1.FabricStructureBuilder;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.UniformIntDistribution;
import net.minecraft.world.gen.decorator.CountExtraDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.trunk.ForkingTrunkPlacer;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;
import net.themoviea.frozeniimod.world.gen.feature.TestHouseStructureFeature;
import net.themoviea.frozeniimod.world.gen.feature.TestNameFeature;
//import net.themoviea.frozeniimod.world.gen.feature.TestNameFeature;
import net.themoviea.frozeniimod.world.gen.feature.TestStreetFeature;
import net.themoviea.frozeniimod.world.gen.generator.TestGenerator;
import net.themoviea.frozeniimod.world.gen.generator.TestHouseGenerator;
//import net.themoviea.frozeniimod.world.gen.generator.TestNameGenerator;
import net.themoviea.frozeniimod.world.gen.generator.TestNameGenerator;
import net.themoviea.frozeniimod.world.gen.placer.trunk.EnchantedTrunkPlacer;

/**
 * 
 * @author Themoviea
 *
 */
@SuppressWarnings("deprecation")
public class ModFeatures {
	//Pools
	public static final Identifier TEST_POOL = new Identifier("frozeniimod", "test_pool");
	public static final Identifier NAME_POOL = new Identifier("frozeniimod", "name_pool");

	//Pieces
	public static final StructurePieceType TEST_HOUSE_PIECE = TestHouseGenerator.TestHousePiece::new;
	
	//Structure Features
	private static final StructureFeature<DefaultFeatureConfig> TEST_HOUSE = new TestHouseStructureFeature(DefaultFeatureConfig.CODEC);
	private static final StructureFeature<StructurePoolFeatureConfig> TEST_STREET = new TestStreetFeature(StructurePoolFeatureConfig.CODEC);
	private static final StructureFeature<StructurePoolFeatureConfig> TEST_NAME = new TestNameFeature(StructurePoolFeatureConfig.CODEC);
	
	//Configured Structure Features
	private static final ConfiguredStructureFeature<?, ?> TEST_HOUSE_CONFIGURED = TEST_HOUSE.configure(DefaultFeatureConfig.DEFAULT);
	private static final ConfiguredStructureFeature<StructurePoolFeatureConfig, ? extends StructureFeature<StructurePoolFeatureConfig>> TEST_STREET_CONFIGURED = TEST_STREET.configure(new StructurePoolFeatureConfig(() -> TestGenerator.structures, 7));
	private static final ConfiguredStructureFeature<StructurePoolFeatureConfig, ? extends StructureFeature<StructurePoolFeatureConfig>> TEST_NAME_CONFIGURED = TEST_NAME.configure(new StructurePoolFeatureConfig(() -> TestNameGenerator.structures, 7));
	
	//Configured Biome Features
	public static final ConfiguredFeature<TreeFeatureConfig, ?> ENCHANTED;
    public static final ConfiguredFeature<TreeFeatureConfig, ?> SUPER_ENCHANTED_TREE;
    public static final ConfiguredFeature<?, ?> TALL_ENCHANTED;
    public static final ConfiguredFeature<?, ?> TREE_ENCHANTED;
    public static final ConfiguredFeature<?, ?> FIRE_SPIRIT_ORE;
    public static final ConfiguredFeature<?, ?> EARTH_SPIRIT_ORE;
    public static final ConfiguredFeature<?, ?> WIND_SPIRIT_ORE;
    public static final ConfiguredFeature<?, ?> WATER_SPIRIT_ORE;

    static
    {
        //ENCHANTED_TREE = Feature.TREE.configure(new TreeFeatureConfig(new SimpleBlockStateProvider(ModBlocks.ENCHANTED_TREE_LOGS.getDefaultState()), new SimpleBlockStateProvider(ModBlocks.ENCHANTED_TREE_LEAVES.getDefaultState()), new BlobFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 3), new StraightTrunkPlacer(5, 2, 2), new TwoLayersFeatureSize(1, 0, 2)).ignoreVines().build())
        ENCHANTED = Feature.TREE.configure((new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.ENCHANTED_TREE_LOG.getDefaultState()), new SimpleBlockStateProvider(ModBlocks.ENCHANTED_TREE_LEAVES.getDefaultState()), new BlobFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 3), new EnchantedTrunkPlacer(5, 2, 2), new TwoLayersFeatureSize(1, 0, 2))).ignoreVines().build());
        SUPER_ENCHANTED_TREE = Feature.TREE.configure((new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(ModBlocks.ENCHANTED_TREE_LOG.getDefaultState()), new SimpleBlockStateProvider(ModBlocks.ENCHANTED_TREE_LEAVES.getDefaultState()), new BlobFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 3), new StraightTrunkPlacer(5, 2, 6), new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build());
        TALL_ENCHANTED = SUPER_ENCHANTED_TREE.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP).decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(10, 0.1F, 1)));
        TREE_ENCHANTED = ENCHANTED.decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP).decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(10, 0.1F, 1)));
        
        //Ores
        EARTH_SPIRIT_ORE = (ConfiguredFeature)((ConfiguredFeature)((ConfiguredFeature)Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, ModBlocks.EARTH_SPIRIT_ORE.getDefaultState(), 4)).rangeOf(16)).spreadHorizontally()).repeat(3);
        WIND_SPIRIT_ORE = (ConfiguredFeature)((ConfiguredFeature)((ConfiguredFeature)Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, ModBlocks.WIND_SPIRIT_ORE.getDefaultState(), 4)).rangeOf(16)).spreadHorizontally()).repeat(3);
        WATER_SPIRIT_ORE = (ConfiguredFeature)((ConfiguredFeature)((ConfiguredFeature)Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, ModBlocks.WATER_SPIRIT_ORE.getDefaultState(), 4)).rangeOf(64)).spreadHorizontally()).repeat(3);
        FIRE_SPIRIT_ORE = (ConfiguredFeature)((ConfiguredFeature)((ConfiguredFeature)Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, ModBlocks.FIRE_SPIRIT_ORE.getDefaultState(), 4)).rangeOf(64)).spreadHorizontally()).repeat(3);

    }
    
	public static void registerFrozen2ModFeatures() {
		
		Registry.register(Registry.STRUCTURE_PIECE, new Identifier("frozeniimod", "test_house_piece"), TEST_HOUSE_PIECE);
		buildDefaultStructureFeatures("frozeniimod", "test_house", TEST_HOUSE, GenerationStep.Feature.SURFACE_STRUCTURES, 32, 8, 12345, TEST_HOUSE_CONFIGURED);
		//buildDefaultStructureFeatures()
		
		FabricStructureBuilder.create(new Identifier("frozeniimod", "test_street"), TEST_STREET)
			.step(GenerationStep.Feature.SURFACE_STRUCTURES)
			.defaultConfig(32, 8, 12345)
			.adjustsSurface()
			.register();
		
		RegistryKey<ConfiguredStructureFeature<?, ?>> testStreetConfigured = RegistryKey.of(Registry.CONFIGURED_STRUCTURE_FEATURE_WORLDGEN, new Identifier("frozeniimod", "test_street"));
		BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, testStreetConfigured.getValue(), TEST_STREET_CONFIGURED);
		BiomeModifications.addStructure(BiomeSelectors.all(), testStreetConfigured);
		
		
		FabricStructureBuilder.create(new Identifier("frozeniimod", "test_name"), TEST_NAME)
			.step(GenerationStep.Feature.SURFACE_STRUCTURES)
			.defaultConfig(64, 8, 12345)
			.adjustsSurface()
			.register();
		
		RegistryKey<ConfiguredStructureFeature<?, ?>> testNameConfigured = RegistryKey.of(Registry.CONFIGURED_STRUCTURE_FEATURE_WORLDGEN, new Identifier("frozeniimod", "test_name"));
		BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, testNameConfigured.getValue(), TEST_NAME_CONFIGURED);
		BiomeModifications.addStructure(BiomeSelectors.all(), testNameConfigured);
	
	}
	
	public static void buildDefaultStructureFeatures(String modid, String name, StructureFeature<DefaultFeatureConfig> feature, GenerationStep.Feature generationStep, int spacing, int separation, int salt, ConfiguredStructureFeature<?, ?> configuredStructure) {
		/**
		 * @param This method
		 */
		FabricStructureBuilder.create(new Identifier(modid, name), feature)
			.step(generationStep)
			.defaultConfig(spacing, separation, salt)
			.adjustsSurface()
			.register();
		
		RegistryKey<ConfiguredStructureFeature<?, ?>> configured = RegistryKey.of(Registry.CONFIGURED_STRUCTURE_FEATURE_WORLDGEN, new Identifier(modid, name));
		BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, configured.getValue(), configuredStructure);
		BiomeModifications.addStructure(BiomeSelectors.all(), configured);
	}
}
