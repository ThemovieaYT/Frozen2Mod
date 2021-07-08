package net.themoviea.frozeniimod.init;

import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.Item.Settings;
import net.themoviea.frozeniimod.Frozen2Mod;
import net.themoviea.frozeniimod.entity.PowerElement;
import net.themoviea.frozeniimod.item.IcePowerItem;
import net.themoviea.frozeniimod.item.TokenItem;
import net.themoviea.frozeniimod.item.powers.PowerItem;
import net.themoviea.frozeniimod.item.powers.attack_methods.AirExplodeAttackMethod;
import net.themoviea.frozeniimod.item.powers.attack_methods.EarthSpikeAttackMethod;
import net.themoviea.frozeniimod.item.powers.attack_methods.NoneAttackMethod;
import net.themoviea.frozeniimod.item.powers.attack_methods.SetOnFireAttackMethod;
import net.themoviea.frozeniimod.item.powers.attack_methods.WaterDrownMethod;
import net.themoviea.frozeniimod.item.powers.protect_methods.NoneProtectMethod;
import net.themoviea.frozeniimod.item.powers.protect_methods.WaterShowerProtectMethod;
import net.themoviea.frozeniimod.item.powers.use_methods.AirBridgeUseMethod;
import net.themoviea.themovieapi_base.exceptions.InputNotAnObjectException;
import net.themoviea.themovieapi_base.registering.EasyRegister;

public class ModItems 
{
    public static final Item FIRE_SPIRIT_CRYSTAL = new Item(new Item.Settings().group(Frozen2Mod.FROZEN_II_MOD_ITEMS));
    public static final Item EARTH_SPIRIT_CRYSTAL = new Item(new Item.Settings().group(Frozen2Mod.FROZEN_II_MOD_ITEMS));
    public static final Item WIND_SPIRIT_CRYSTAL = new Item(new Item.Settings().group(Frozen2Mod.FROZEN_II_MOD_ITEMS));
    public static final Item WATER_SPIRIT_CRYSTAL = new Item(new Item.Settings().group(Frozen2Mod.FROZEN_II_MOD_ITEMS));
    public static final Item FIFTH_SPIRIT_CRYSTAL = new Item(new Item.Settings().group(Frozen2Mod.FROZEN_II_MOD_ITEMS));
    public static final IcePowerItem ICE_POWER = new IcePowerItem(new Item.Settings().group(Frozen2Mod.FROZEN_II_MOD_MISC));
    public static final SpawnEggItem ELSA_SPAWN = new SpawnEggItem(ModEntities.ELSA, 14343131, 6594815, new Item.Settings().group(Frozen2Mod.FROZEN_II_MOD_MISC));
    public static final SpawnEggItem ANNA_SPAWN = new SpawnEggItem(ModEntities.ANNA, 14343131, 6594815, new Item.Settings().group(Frozen2Mod.FROZEN_II_MOD_MISC));
    public static final SpawnEggItem ARENDELLIAN_SPAWN = new SpawnEggItem(ModEntities.ARENDELLIAN, 14343131, 6594815, new Item.Settings().group(Frozen2Mod.FROZEN_II_MOD_MISC));
    
    //Powers
    public static final PowerItem SET_ENTITY_ON_FIRE_POWER = new PowerItem(new SetOnFireAttackMethod(0, 0, 0, 0, null), false, true);
    public static final PowerItem WATER_POWER = new PowerItem(new EarthSpikeAttackMethod(0, 2, 0, 3, Blocks.STONE), true, false);
    public static final PowerItem AIR_POWER = new PowerItem(new AirExplodeAttackMethod(), true);
    public static final PowerItem EARTH_SPIKE_POWER = new PowerItem(new EarthSpikeAttackMethod(0, 1, 0, 10, ModBlocks.EARTH_POWER_STONE), true, false);
    
    public static final PowerItem WATER_DROWN_POWER = new PowerItem(new WaterDrownMethod(0, 0, 0, 0), false, true);
    
    //Protecting powers
    public static final PowerItem WATER_PROTECT = new PowerItem(new WaterShowerProtectMethod(ModItems.SET_ENTITY_ON_FIRE_POWER), true);
    
    //Powers for other purpose
    public static final PowerItem AIR_BRIDGE = new PowerItem(new AirBridgeUseMethod(), true);
    
    //None power
    public static final PowerItem NONE = new PowerItem(new NoneProtectMethod(), new NoneAttackMethod(), false, false, false);
    //BlockItems
      	
    //Enchanted Tree
    public static final BlockItem ENCHANTED_TREE_SAPLING = new BlockItem(ModBlocks.ENCHANTED_TREE_SAPLING, new Item.Settings().group(Frozen2Mod.FROZEN_II_MOD_BUILDING_BLOCKS));
    public static final BlockItem ENCHANTED_TREE_LOG = new BlockItem(ModBlocks.ENCHANTED_TREE_LOG, new Item.Settings().group(Frozen2Mod.FROZEN_II_MOD_BUILDING_BLOCKS));
    public static final BlockItem ENCHANTED_TREE_LEAVES = new BlockItem(ModBlocks.ENCHANTED_TREE_LEAVES, new Item.Settings().group(Frozen2Mod.FROZEN_II_MOD_BUILDING_BLOCKS));
    public static final BlockItem ENCHANTED_PLANKS = new BlockItem(ModBlocks.ENCHANTED_PLANKS, new Item.Settings().group(Frozen2Mod.FROZEN_II_MOD_BUILDING_BLOCKS));
    public static final BlockItem ENCHANTED_FENCE = new BlockItem(ModBlocks.ENCHANTED_FENCE, new Item.Settings().group(Frozen2Mod.FROZEN_II_MOD_DECORATION_BLOCKS));
    public static final BlockItem ENCHANTED_PRESSURE_PLATE = new BlockItem(ModBlocks.ENCHANTED_PRESSURE_PLATE, new Item.Settings().group(Frozen2Mod.FROZEN_II_MOD_REDSTONE));
    public static final BlockItem ENCHANTED_STAIRS = new BlockItem(ModBlocks.ENCHANTED_STAIRS, new Item.Settings().group(Frozen2Mod.FROZEN_II_MOD_DECORATION_BLOCKS));
    public static final BlockItem ENCHANTED_SLAB = new BlockItem(ModBlocks.ENCHANTED_SLAB, new Item.Settings().group(Frozen2Mod.FROZEN_II_MOD_DECORATION_BLOCKS));
    public static final BlockItem ENCHANTED_FENCE_GATE = new BlockItem(ModBlocks.ENCHANTED_FENCE_GATE, new Item.Settings().group(Frozen2Mod.FROZEN_II_MOD_DECORATION_BLOCKS));
    public static final BlockItem ENCHANTED_TRAPDOOR = new BlockItem(ModBlocks.ENCHANTED_TRAPDOOR, new Item.Settings().group(Frozen2Mod.FROZEN_II_MOD_DECORATION_BLOCKS));
        	
    //Frozen Tree
    public static final BlockItem FROZEN_SAPLING = new BlockItem(ModBlocks.FROZEN_SAPLING, new Item.Settings().group(Frozen2Mod.FROZEN_II_MOD_BUILDING_BLOCKS));
    public static final BlockItem FROZEN_LOG = new BlockItem(ModBlocks.FROZEN_LOG, new Item.Settings().group(Frozen2Mod.FROZEN_II_MOD_BUILDING_BLOCKS));
    public static final BlockItem FROZEN_LEAVES = new BlockItem(ModBlocks.FROZEN_LEAVES, new Item.Settings().group(Frozen2Mod.FROZEN_II_MOD_BUILDING_BLOCKS));
    public static final BlockItem FROZEN_PLANKS = new BlockItem(ModBlocks.FROZEN_PLANKS, new Item.Settings().group(Frozen2Mod.FROZEN_II_MOD_BUILDING_BLOCKS));
    public static final BlockItem FROZEN_FENCE = new BlockItem(ModBlocks.FROZEN_FENCE, new Item.Settings().group(Frozen2Mod.FROZEN_II_MOD_DECORATION_BLOCKS));
    public static final BlockItem FROZEN_PRESSURE_PLATE = new BlockItem(ModBlocks.FROZEN_PRESSURE_PLATE, new Item.Settings().group(Frozen2Mod.FROZEN_II_MOD_REDSTONE));
    public static final BlockItem FROZEN_STAIRS = new BlockItem(ModBlocks.FROZEN_STAIRS, new Item.Settings().group(Frozen2Mod.FROZEN_II_MOD_DECORATION_BLOCKS));
    public static final BlockItem FROZEN_SLAB = new BlockItem(ModBlocks.FROZEN_SLAB, new Item.Settings().group(Frozen2Mod.FROZEN_II_MOD_DECORATION_BLOCKS));
    public static final BlockItem FROZEN_FENCE_GATE = new BlockItem(ModBlocks.FROZEN_FENCE_GATE, new Item.Settings().group(Frozen2Mod.FROZEN_II_MOD_DECORATION_BLOCKS));
    public static final BlockItem FROZEN_TRAPDOOR = new BlockItem(ModBlocks.FROZEN_TRAPDOOR, new Item.Settings().group(Frozen2Mod.FROZEN_II_MOD_DECORATION_BLOCKS));
    
    //Ice Blocks
    public static final BlockItem ICE_WALL = new BlockItem(ModBlocks.ICE_WALL, new Item.Settings().group(Frozen2Mod.FROZEN_II_MOD_DECORATION_BLOCKS));
    public static final BlockItem ICE_PRESSURE_PLATE = new BlockItem(ModBlocks.ICE_PRESSURE_PLATE, new Item.Settings().group(Frozen2Mod.FROZEN_II_MOD_REDSTONE));
    public static final BlockItem ICE_STAIRS = new BlockItem(ModBlocks.ICE_STAIRS, new Item.Settings().group(Frozen2Mod.FROZEN_II_MOD_DECORATION_BLOCKS));
    public static final BlockItem ICE_SLAB = new BlockItem(ModBlocks.ICE_SLAB, new Item.Settings().group(Frozen2Mod.FROZEN_II_MOD_DECORATION_BLOCKS));
    public static final BlockItem ICE_GATE = new BlockItem(ModBlocks.ICE_GATE, new Item.Settings().group(Frozen2Mod.FROZEN_II_MOD_DECORATION_BLOCKS));
    
    //Ice Blocks
    public static final BlockItem PACKED_ICE_WALL = new BlockItem(ModBlocks.PACKED_ICE_WALL, new Item.Settings().group(Frozen2Mod.FROZEN_II_MOD_DECORATION_BLOCKS));
    public static final BlockItem PACKED_ICE_PRESSURE_PLATE = new BlockItem(ModBlocks.PACKED_ICE_PRESSURE_PLATE, new Item.Settings().group(Frozen2Mod.FROZEN_II_MOD_REDSTONE));
    public static final BlockItem PACKED_ICE_STAIRS = new BlockItem(ModBlocks.PACKED_ICE_STAIRS, new Item.Settings().group(Frozen2Mod.FROZEN_II_MOD_DECORATION_BLOCKS));
    public static final BlockItem PACKED_ICE_SLAB = new BlockItem(ModBlocks.PACKED_ICE_SLAB, new Item.Settings().group(Frozen2Mod.FROZEN_II_MOD_DECORATION_BLOCKS));
    public static final BlockItem PACKED_ICE_GATE = new BlockItem(ModBlocks.PACKED_ICE_GATE, new Item.Settings().group(Frozen2Mod.FROZEN_II_MOD_DECORATION_BLOCKS));
    
    //Ores
    public static final BlockItem FIRE_SPIRIT_ORE = new BlockItem(ModBlocks.FIRE_SPIRIT_ORE, new Item.Settings().group(Frozen2Mod.FROZEN_II_MOD_BUILDING_BLOCKS));
    public static final BlockItem EARTH_SPIRIT_ORE = new BlockItem(ModBlocks.EARTH_SPIRIT_ORE, new Item.Settings().group(Frozen2Mod.FROZEN_II_MOD_BUILDING_BLOCKS));
    public static final BlockItem WIND_SPIRIT_ORE = new BlockItem(ModBlocks.WIND_SPIRIT_ORE, new Item.Settings().group(Frozen2Mod.FROZEN_II_MOD_BUILDING_BLOCKS));
    public static final BlockItem WATER_SPIRIT_ORE = new BlockItem(ModBlocks.WATER_SPIRIT_ORE, new Item.Settings().group(Frozen2Mod.FROZEN_II_MOD_BUILDING_BLOCKS));
    		
    //OreBlocks
    public static final BlockItem FIRE_SPIRIT_CRYSTAL_BLOCK = new BlockItem(ModBlocks.FIRE_SPIRIT_CRYSTAL_BLOCK, new Item.Settings().group(Frozen2Mod.FROZEN_II_MOD_BUILDING_BLOCKS));
    public static final BlockItem EARTH_SPIRIT_CRYSTAL_BLOCK = new BlockItem(ModBlocks.EARTH_SPIRIT_CRYSTAL_BLOCK, new Item.Settings().group(Frozen2Mod.FROZEN_II_MOD_BUILDING_BLOCKS));
    public static final BlockItem WIND_SPIRIT_CRYSTAL_BLOCK = new BlockItem(ModBlocks.WIND_SPIRIT_CRYSTAL_BLOCK, new Item.Settings().group(Frozen2Mod.FROZEN_II_MOD_BUILDING_BLOCKS));
    public static final BlockItem WATER_SPIRIT_CRYSTAL_BLOCK = new BlockItem(ModBlocks.WATER_SPIRIT_CRYSTAL_BLOCK, new Item.Settings().group(Frozen2Mod.FROZEN_II_MOD_BUILDING_BLOCKS));
    public static final BlockItem FIFTH_SPIRIT_CRYSTAL_BLOCK = new BlockItem(ModBlocks.FIFTH_SPIRIT_CRYSTAL_BLOCK, new Item.Settings().group(Frozen2Mod.FROZEN_II_MOD_BUILDING_BLOCKS));
    		
    //Dark Stone
    public static final BlockItem DARK_STONE = new BlockItem(ModBlocks.DARK_STONE, new Item.Settings().group(Frozen2Mod.FROZEN_II_MOD_BUILDING_BLOCKS));
    public static final BlockItem DARK_STONE_PEBBLES = new BlockItem(ModBlocks.DARK_STONE_PEBBLES, new Item.Settings().group(Frozen2Mod.FROZEN_II_MOD_BUILDING_BLOCKS));
    public static final BlockItem DARK_STONE_SLAB = new BlockItem(ModBlocks.DARK_STONE_SLAB, new Item.Settings().group(Frozen2Mod.FROZEN_II_MOD_DECORATION_BLOCKS));
    public static final BlockItem DARK_STONE_WALL = new BlockItem(ModBlocks.DARK_STONE_WALL, new Item.Settings().group(Frozen2Mod.FROZEN_II_MOD_DECORATION_BLOCKS));
    public static final BlockItem DARK_STONE_STAIRS = new BlockItem(ModBlocks.DARK_STONE_STAIRS, new Item.Settings().group(Frozen2Mod.FROZEN_II_MOD_DECORATION_BLOCKS));
	
    //TestBlock
    public static final BlockItem TEST_BLOCK = new BlockItem(ModBlocks.TEST_BLOCK, new Item.Settings().group(Frozen2Mod.FROZEN_II_MOD_BUILDING_BLOCKS));
    
    //Blocks with inventory
    public static final BlockItem SPIRIT_POWER_CRAFTER = new BlockItem(ModBlocks.SPIRIT_POWER_CRAFTER, new Item.Settings().group(Frozen2Mod.FROZEN_II_MOD_MISC));
    public static void registerFrozen2ModItems() throws InputNotAnObjectException
    {
    	EasyRegister.createItemList(
    			//Crystals
    			FIRE_SPIRIT_CRYSTAL, "fire_spirit_crystal", EARTH_SPIRIT_CRYSTAL, "earth_spirit_crystal", WIND_SPIRIT_CRYSTAL, "wind_spirit_crystal",
    			WATER_SPIRIT_CRYSTAL, "water_spirit_crystal", FIFTH_SPIRIT_CRYSTAL, "fifth_spirit_crystal",
    			
    			//Spawn Eggs
    			ELSA_SPAWN, "elsa_spawn_egg", ANNA_SPAWN, "anna_spawn_egg", ARENDELLIAN_SPAWN, "arendellian_spawn_egg",
    			
    			//Powers
    			SET_ENTITY_ON_FIRE_POWER, "set_entity_on_fire_power", AIR_POWER, "wind_power", EARTH_SPIKE_POWER, "earth_spike_power", WATER_POWER, "water_power",
    			WATER_DROWN_POWER, "water_drown",
    			
    			//Protecting powers
    			WATER_PROTECT, "water_shower_protect_power",
    			
    			//Powers for other purposes
    			AIR_BRIDGE, "air_bridge",
    			
    			//Projectiles
    			ICE_POWER, "ice_power",
    			
    			//Enchanted Tree
    			ENCHANTED_TREE_SAPLING, "enchanted_sapling", ENCHANTED_TREE_LOG, "enchanted_log", ENCHANTED_TREE_LEAVES, "enchanted_leaves", ENCHANTED_PLANKS, "enchanted_planks", 
    			ENCHANTED_FENCE, "enchanted_fence", ENCHANTED_PRESSURE_PLATE, "enchanted_pressure_plate", ENCHANTED_STAIRS, "enchanted_stairs", ENCHANTED_SLAB, "enchanted_slab",
    			ENCHANTED_FENCE_GATE, "enchanted_fence_gate", ENCHANTED_TRAPDOOR, "enchanted_trapdoor",
    			
    			//Frozen Tree
    			FROZEN_SAPLING, "frozen_sapling", FROZEN_LOG, "frozen_log", FROZEN_LEAVES, "frozen_leaves", FROZEN_PLANKS, "frozen_planks",
    			FROZEN_FENCE, "frozen_fence", FROZEN_PRESSURE_PLATE, "frozen_pressure_plate", FROZEN_STAIRS, "frozen_stairs", FROZEN_SLAB, "frozen_slab",
    			FROZEN_FENCE_GATE, "frozen_fence_gate", FROZEN_TRAPDOOR, "frozen_trapdoor",
    			
    			//Ice Blocks
    			ICE_WALL, "ice_wall", ICE_PRESSURE_PLATE, "ice_pressure_plate", ICE_STAIRS, "ice_stairs", ICE_SLAB, "ice_slab", ICE_GATE, "ice_gate",
    			
    			//Packed Ice Blocks
    			PACKED_ICE_WALL, "packed_ice_wall", PACKED_ICE_PRESSURE_PLATE, "packed_ice_pressure_plate", PACKED_ICE_STAIRS, "packed_ice_stairs", PACKED_ICE_SLAB, "packed_ice_slab", 
    			PACKED_ICE_GATE, "packed_ice_gate",
    			
    			//Ores
    			FIRE_SPIRIT_ORE, "fire_spirit_ore", EARTH_SPIRIT_ORE, "earth_spirit_ore", WIND_SPIRIT_ORE, "wind_spirit_ore", WATER_SPIRIT_ORE, "water_spirit_ore",
    			
    			//OreBlocks
    			FIRE_SPIRIT_CRYSTAL_BLOCK, "fire_spirit_crystal_block", EARTH_SPIRIT_CRYSTAL_BLOCK, "earth_spirit_crystal_block", WIND_SPIRIT_CRYSTAL_BLOCK, "wind_spirit_crystal_block",
    			WATER_SPIRIT_CRYSTAL_BLOCK, "water_spirit_crystal_block", FIFTH_SPIRIT_CRYSTAL_BLOCK, "fifth_spirit_crystal_block",
    			
    			//Dark Stone
    			DARK_STONE, "dark_stone", DARK_STONE_PEBBLES, "dark_stone_pebbles", DARK_STONE_SLAB, "dark_stone_slab", DARK_STONE_WALL, "dark_stone_wall", DARK_STONE_STAIRS, "dark_stone_stairs",
    			
    			//Test block
    			TEST_BLOCK, "test_block",
    			
    			//Spirit Power Crafter
    			SPIRIT_POWER_CRAFTER, "spirit_power_crafter"
    			);
    	EasyRegister.registerItems("frozeniimod");
    	
    }
    
}