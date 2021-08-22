package net.themoviea.frozeniimod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.object.builder.v1.villager.VillagerProfessionBuilder;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.ai.brain.Activity;
import net.minecraft.entity.ai.brain.Schedule;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.themoviea.frozeniimod.block.entity.TradingStationBlockEntity;
import net.themoviea.frozeniimod.entity.ai.brain.ArendellianSchedule;
import net.themoviea.frozeniimod.init.ModBiomes;
import net.themoviea.frozeniimod.init.ModBlocks;
import net.themoviea.frozeniimod.init.ModComponents;
import net.themoviea.frozeniimod.init.ModEffects;
import net.themoviea.frozeniimod.init.ModEntities;
import net.themoviea.frozeniimod.init.ModFeatures;
import net.themoviea.frozeniimod.init.ModItems;
import net.themoviea.frozeniimod.init.ModParticles;
import net.themoviea.frozeniimod.init.ModProfessions;
import net.themoviea.frozeniimod.init.ModRecipes;
import net.themoviea.frozeniimod.init.ModTrunkPlacers;
import net.themoviea.frozeniimod.mixin.ActivityAccessor;
import net.themoviea.themovieapi_base.exceptions.InputNotAnObjectException;
//import net.themoviea.frozeniimod.world.gen.feature_pieces.TestPiece;
public class Frozen2Mod implements ModInitializer
{
	//Custom Registries
	//public static final Registry<ArendellianVillagerProfession> ARENDELLIAN_VILLAGER_PROFESSION = ;		//Structure Pieces
	//public static final StructurePieceType TEST_PIECE = TestPiece::new;
	//Structure Pieces
	//Schedules
	public static final String MOD_ID = "frozeniimod";
	public static final Logger LOGGER = LogManager.getLogger("Frozen 2 Mod");

	//Activities
	public static final Activity HOME_ROUTINE = ActivityAccessor.register("frozeniimod:home_routine");
	public static final Activity KID_HOME_ROUTINE = ActivityAccessor.register("frozeniimod:kid_home_routine");
	public static final Activity SCHOOL = ActivityAccessor.register("frozeniimod:school");
	
	//ItemGroup
	public static final ItemGroup FROZEN_II_MOD_MISC = FabricItemGroupBuilder.build(new Identifier("frozeniimod", "frozeniimisc"), () -> new ItemStack(ModItems.ELSA_SPAWN));
	public static final ItemGroup FROZEN_II_MOD_BUILDING_BLOCKS = FabricItemGroupBuilder.build(new Identifier("frozeniimod", "frozeniiblocks"), () -> new ItemStack(ModBlocks.FIRE_SPIRIT_ORE));
	public static final ItemGroup FROZEN_II_MOD_ITEMS = FabricItemGroupBuilder.build(new Identifier("frozeniimod", "frozeniiitems"), () -> new ItemStack(Items.STICK));
	public static final ItemGroup FROZEN_II_MOD_DECORATION_BLOCKS = FabricItemGroupBuilder.build(new Identifier("frozeniimod", "frozeniidecorationblocks"), () -> new ItemStack(ModBlocks.ENCHANTED_FENCE));
	public static final ItemGroup FROZEN_II_MOD_REDSTONE = FabricItemGroupBuilder.build(new Identifier("frozeniimod", "frozeniiredstone"), () -> new ItemStack(ModBlocks.ENCHANTED_PRESSURE_PLATE));
	
	public static BlockEntityType<TradingStationBlockEntity> TRADING_STATION;
	
	@Override
	public void onInitialize() 
	{
		ModBiomes.registerFrozen2ModBiomes();
		try {
			ModBlocks.registerFrozen2ModBlocks();
			ModItems.registerFrozen2ModItems();
			ModEffects.registerFrozen2ModEffects();
		} catch (InputNotAnObjectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ModProfessions.registerCustomProfessions();
		ModComponents.registerFrozen2ModComponents();
		ModEntities.registerFrozen2ModEntityAttributes();
		//ModScreenHandlers.registerScreenHandlers();
		ModRecipes.registerFrozen2RecipeTypes();
		ModFeatures.registerFrozen2ModFeatures();
		ModTrunkPlacers.registerFrozen2TrunkPlacers();
		ArendellianSchedule.registerArendellianSchedule();
		
		TRADING_STATION = Registry.register(Registry.BLOCK_ENTITY_TYPE, "frozeniimod:trading_station_entity", BlockEntityType.Builder.create(TradingStationBlockEntity::new).build(null));

		//BlockRenderLayers
		//Registry.register(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, "frozeniimod:test_feature", FrozenIIModConfiguredFeatures.FEATURE_CONFIGURED);
		//FabricStructureBuilder.create("frozeniimod:test_structure", FrozenIIModConfiguredFeatures.FEATURE);
		LOGGER.info("Initialized Frozen 2 Mod contents! You can see Elsa and Anna!");
	}
}
