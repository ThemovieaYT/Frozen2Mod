package net.themoviea.frozeniimod.init;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.PressurePlateBlock.ActivationRule;
import net.minecraft.fluid.Fluids;
import net.minecraft.block.WallBlock;
import net.minecraft.sound.BlockSoundGroup;
import net.themoviea.frozeniimod.block.FrozenIIModDoorBlock;
import net.themoviea.frozeniimod.block.FrozenIIModOreBlock;
import net.themoviea.frozeniimod.block.FrozenIIModPressurePlateBlock;
import net.themoviea.frozeniimod.block.FrozenIIModSaplingBlock;
import net.themoviea.frozeniimod.block.FrozenIIModStairsBlock;
import net.themoviea.frozeniimod.block.FrozenIIModTrapdoorBlock;
import net.themoviea.frozeniimod.block.SpiritPowerCrafterBlock;
import net.themoviea.frozeniimod.block.inventory_blocks.SpiritPowerCraftingBlock;
import net.themoviea.frozeniimod.block.sapling.EnchantedSaplingGenerator;
import net.themoviea.frozeniimod.block.sapling.FrozenSaplingGenerator;
import net.themoviea.frozeniimod.block.spirit_power_blocks.EarthPowerStoneBlock;
import net.themoviea.frozeniimod.block.spirit_power_blocks.WaterSpiritPowerFluidBlock;
import net.themoviea.themovieapi_base.exceptions.InputNotAnObjectException;
import net.themoviea.themovieapi_base.registering.EasyRegister;

public class ModBlocks 
{
	//This is for registering all the blocks that are currently implemented in the mod.
	
	
	//Enchanted Tree
    public static final PillarBlock ENCHANTED_TREE_LOG = new PillarBlock(FabricBlockSettings.of(Material.WOOD, MaterialColor.WHITE).hardness(2.0f).resistance(2.0f).sounds(BlockSoundGroup.WOOD));
    public static final LeavesBlock ENCHANTED_TREE_LEAVES = new LeavesBlock(FabricBlockSettings.of(Material.LEAVES, MaterialColor.PINK).hardness(0.2f).resistance(0.2f).sounds(BlockSoundGroup.GRASS).nonOpaque().ticksRandomly());
    public static final FrozenIIModSaplingBlock ENCHANTED_TREE_SAPLING = new FrozenIIModSaplingBlock(new EnchantedSaplingGenerator(), FabricBlockSettings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS));
    public static final Block ENCHANTED_PLANKS = new Block(FabricBlockSettings.of(Material.WOOD, MaterialColor.BROWN_TERRACOTTA).hardness(2.0f).resistance(2.0f).sounds(BlockSoundGroup.WOOD));
    public static final FenceBlock ENCHANTED_FENCE = new FenceBlock(FabricBlockSettings.copyOf(ENCHANTED_PLANKS));
    public static final FrozenIIModPressurePlateBlock ENCHANTED_PRESSURE_PLATE = new FrozenIIModPressurePlateBlock(ActivationRule.EVERYTHING, FabricBlockSettings.copyOf(ENCHANTED_PLANKS));
    public static final FrozenIIModStairsBlock ENCHANTED_STAIRS = new FrozenIIModStairsBlock(ENCHANTED_PLANKS.getDefaultState(), FabricBlockSettings.copyOf(ENCHANTED_PLANKS));
    public static final SlabBlock ENCHANTED_SLAB = new SlabBlock(FabricBlockSettings.copyOf(ENCHANTED_PLANKS));
    public static final FrozenIIModDoorBlock ENCHANTED_DOOR = new FrozenIIModDoorBlock(FabricBlockSettings.copyOf(ENCHANTED_PLANKS));
    public static final FenceGateBlock ENCHANTED_FENCE_GATE = new FenceGateBlock(FabricBlockSettings.copyOf(ENCHANTED_PLANKS));
    public static final FrozenIIModTrapdoorBlock ENCHANTED_TRAPDOOR = new FrozenIIModTrapdoorBlock(FabricBlockSettings.copyOf(ENCHANTED_PLANKS));
    
    //Frozen Tree
    public static final PillarBlock FROZEN_LOG = new PillarBlock(FabricBlockSettings.of(Material.WOOD, MaterialColor.LIGHT_BLUE).slipperiness(0.45f).hardness(1.7f).resistance(1.7f).sounds(BlockSoundGroup.WOOD));
    public static final LeavesBlock FROZEN_LEAVES = new LeavesBlock(FabricBlockSettings.of(Material.LEAVES, MaterialColor.LIGHT_BLUE).hardness(0.17f).resistance(0.17f).sounds(BlockSoundGroup.GRASS));
    public static final FrozenIIModSaplingBlock FROZEN_SAPLING = new FrozenIIModSaplingBlock(new FrozenSaplingGenerator(), FabricBlockSettings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS));
    public static final Block FROZEN_PLANKS = new Block(FabricBlockSettings.copyOf(FROZEN_LOG));
    public static final FenceBlock FROZEN_FENCE = new FenceBlock(FabricBlockSettings.copyOf(FROZEN_LOG));
    public static final FrozenIIModPressurePlateBlock FROZEN_PRESSURE_PLATE = new FrozenIIModPressurePlateBlock(ActivationRule.EVERYTHING, FabricBlockSettings.copyOf(FROZEN_LOG));
    public static final FrozenIIModStairsBlock FROZEN_STAIRS = new FrozenIIModStairsBlock(FROZEN_PLANKS.getDefaultState(), FabricBlockSettings.copyOf(FROZEN_LOG));
    public static final SlabBlock FROZEN_SLAB = new SlabBlock(FabricBlockSettings.copyOf(FROZEN_LOG));
    public static final FrozenIIModDoorBlock FROZEN_DOOR = new FrozenIIModDoorBlock(FabricBlockSettings.copyOf(FROZEN_LOG));
    public static final FenceGateBlock FROZEN_FENCE_GATE = new FenceGateBlock(FabricBlockSettings.copyOf(FROZEN_LOG));
    public static final FrozenIIModTrapdoorBlock FROZEN_TRAPDOOR = new FrozenIIModTrapdoorBlock(FabricBlockSettings.copyOf(FROZEN_LOG));
    
    //Ice Blocks
    public static final WallBlock ICE_WALL = new WallBlock(FabricBlockSettings.copyOf(Blocks.ICE));
    public static final FrozenIIModPressurePlateBlock ICE_PRESSURE_PLATE = new FrozenIIModPressurePlateBlock(ActivationRule.EVERYTHING, FabricBlockSettings.copyOf(Blocks.ICE));
    public static final FrozenIIModStairsBlock ICE_STAIRS = new FrozenIIModStairsBlock(Blocks.ICE.getDefaultState(), FabricBlockSettings.copyOf(Blocks.ICE));
    public static final SlabBlock ICE_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.ICE));
    public static final FrozenIIModDoorBlock ICE_DOOR = new FrozenIIModDoorBlock(FabricBlockSettings.copyOf(Blocks.ICE));
    public static final FenceGateBlock ICE_GATE = new FenceGateBlock(FabricBlockSettings.copyOf(Blocks.ICE));
    
    //Packed Ice Blocks
    public static final WallBlock PACKED_ICE_WALL = new WallBlock(FabricBlockSettings.copyOf(Blocks.PACKED_ICE));
    public static final FrozenIIModPressurePlateBlock PACKED_ICE_PRESSURE_PLATE = new FrozenIIModPressurePlateBlock(ActivationRule.EVERYTHING, FabricBlockSettings.copyOf(Blocks.PACKED_ICE));
    public static final FrozenIIModStairsBlock PACKED_ICE_STAIRS = new FrozenIIModStairsBlock(Blocks.PACKED_ICE.getDefaultState(), FabricBlockSettings.copyOf(Blocks.PACKED_ICE));
    public static final SlabBlock PACKED_ICE_SLAB = new SlabBlock(FabricBlockSettings.copyOf(Blocks.PACKED_ICE));
    public static final FrozenIIModDoorBlock PACKED_ICE_DOOR = new FrozenIIModDoorBlock(FabricBlockSettings.copyOf(Blocks.PACKED_ICE));
    public static final FenceGateBlock PACKED_ICE_GATE = new FenceGateBlock(FabricBlockSettings.copyOf(Blocks.PACKED_ICE));
    
    //Ores
    public static final FrozenIIModOreBlock FIRE_SPIRIT_ORE = new FrozenIIModOreBlock(FabricBlockSettings.of(Material.STONE, MaterialColor.PURPLE).hardness(3.5f).resistance(900.0f));
    public static final FrozenIIModOreBlock EARTH_SPIRIT_ORE = new FrozenIIModOreBlock(FabricBlockSettings.of(Material.STONE, MaterialColor.LIME).hardness(3.5f).resistance(900.0f));
    public static final FrozenIIModOreBlock WIND_SPIRIT_ORE = new FrozenIIModOreBlock(FabricBlockSettings.of(Material.STONE, MaterialColor.ORANGE).hardness(3.5f).resistance(900.0f));
    public static final FrozenIIModOreBlock WATER_SPIRIT_ORE = new FrozenIIModOreBlock(FabricBlockSettings.of(Material.STONE, MaterialColor.LIGHT_BLUE).hardness(3.5f).resistance(1200.0f));

    //OreBlocks
    public static final Block FIRE_SPIRIT_CRYSTAL_BLOCK = new Block(FabricBlockSettings.of(Material.METAL, MaterialColor.PURPLE).hardness(4.0f).resistance(1000.0f));
    public static final Block EARTH_SPIRIT_CRYSTAL_BLOCK = new Block(FabricBlockSettings.of(Material.METAL, MaterialColor.LIME).hardness(4.0f).resistance(1000.0f));
    public static final Block WIND_SPIRIT_CRYSTAL_BLOCK = new Block(FabricBlockSettings.of(Material.METAL, MaterialColor.ORANGE).hardness(4.0f).resistance(1000.0f));
    public static final Block WATER_SPIRIT_CRYSTAL_BLOCK = new Block(FabricBlockSettings.of(Material.METAL, MaterialColor.LIGHT_BLUE).hardness(4.0f).resistance(1000.0f));
    public static final Block FIFTH_SPIRIT_CRYSTAL_BLOCK = new Block(FabricBlockSettings.of(Material.METAL, MaterialColor.LIGHT_GRAY).hardness(4.0f).resistance(1300.0f));
    
    //Dark Stone
    public static final Block DARK_STONE = new Block(FabricBlockSettings.of(Material.STONE, MaterialColor.BLACK).hardness(3.5f).resistance(6.0f).requiresTool().breakByTool(FabricToolTags.PICKAXES, 2));
    public static final Block DARK_STONE_PEBBLES = new Block(FabricBlockSettings.of(Material.STONE, MaterialColor.BLACK).hardness(3.5f).resistance(6.0f).requiresTool().breakByTool(FabricToolTags.PICKAXES, 2));
    public static final SlabBlock DARK_STONE_SLAB = new SlabBlock(FabricBlockSettings.copyOf(DARK_STONE));
    public static final WallBlock DARK_STONE_WALL = new WallBlock(FabricBlockSettings.copyOf(DARK_STONE));
    public static final FrozenIIModPressurePlateBlock DARK_STONE_PRESSURE_PLATE = new FrozenIIModPressurePlateBlock(ActivationRule.MOBS, FabricBlockSettings.copyOf(DARK_STONE));
    public static final FrozenIIModStairsBlock DARK_STONE_STAIRS = new FrozenIIModStairsBlock(DARK_STONE.getDefaultState(), FabricBlockSettings.copyOf(DARK_STONE));
    
    //Job Blocks
    
    //Test Block
    public static final Block TEST_BLOCK = new Block(FabricBlockSettings.of(Material.STONE, MaterialColor.LIGHT_BLUE).hardness(3.5f));

    //Blocks that can be generated through spirit powers
    public static final WaterSpiritPowerFluidBlock WATER_SPIRIT_POWER_FLUID = new WaterSpiritPowerFluidBlock(Fluids.WATER, FabricBlockSettings.copyOf(Blocks.WATER), false, true);
    public static final EarthPowerStoneBlock EARTH_POWER_STONE = new EarthPowerStoneBlock(true, false);
    
    //Blocks with inventory
    public static final SpiritPowerCrafterBlock SPIRIT_POWER_CRAFTER = new SpiritPowerCrafterBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK));
    public static void registerFrozen2ModBlocks() throws InputNotAnObjectException
    {
    	EasyRegister.createBlockList(
 
    			//Enchanted Tree
    			ENCHANTED_TREE_SAPLING, "enchanted_tree_sapling", ENCHANTED_TREE_LOG, "enchanted_log", ENCHANTED_TREE_LEAVES, "enchanted_leaves", 
    			ENCHANTED_PLANKS, "enchanted_planks", ENCHANTED_FENCE, "enchanted_fence",ENCHANTED_PRESSURE_PLATE, "enchanted_pressure_plate", 
    			ENCHANTED_STAIRS, "enchanted_stairs", ENCHANTED_SLAB, "enchanted_slab", ENCHANTED_DOOR, "enchanted_door", ENCHANTED_FENCE_GATE, "enchanted_fence_gate",
    			ENCHANTED_TRAPDOOR, "enchanted_trapdoor",
    			
    			//Frozen Tree
    			FROZEN_SAPLING, "frozen_sapling", FROZEN_LOG, "frozen_log", FROZEN_LEAVES, "frozen_leaves", FROZEN_PLANKS, "frozen_planks",
    			FROZEN_FENCE, "frozen_fence", FROZEN_PRESSURE_PLATE, "frozen_pressure_plate", FROZEN_STAIRS, "frozen_stairs", FROZEN_SLAB, "frozen_slab",
    			FROZEN_DOOR, "frozen_door", FROZEN_FENCE_GATE, "frozen_fence_gate", FROZEN_TRAPDOOR, "frozen_trapdoor",
    			
    			//Ice Blocks
    			ICE_WALL, "ice_wall", ICE_PRESSURE_PLATE, "ice_pressure_plate", ICE_STAIRS, "ice_stairs", ICE_SLAB, "ice_slab", ICE_DOOR, "ice_door", ICE_GATE, "ice_gate",
    			
    			//Packed Ice Blocks
    			PACKED_ICE_WALL, "packed_ice_wall", PACKED_ICE_PRESSURE_PLATE, "packed_ice_pressure_plate", PACKED_ICE_STAIRS, "packed_ice_stairs", PACKED_ICE_SLAB, "packed_ice_slab", 
    			PACKED_ICE_DOOR, "packed_ice_door", PACKED_ICE_GATE, "packed_ice_gate",
    			
    			//Ores
    			FIRE_SPIRIT_ORE, "fire_spirit_ore", EARTH_SPIRIT_ORE, "earth_spirit_ore", WIND_SPIRIT_ORE, "wind_spirit_ore", WATER_SPIRIT_ORE, "water_spirit_ore",
    			
    			//OreBlocks
    			FIRE_SPIRIT_CRYSTAL_BLOCK, "fire_spirit_crystal_block", EARTH_SPIRIT_CRYSTAL_BLOCK, "earth_spirit_crystal_block", WIND_SPIRIT_CRYSTAL_BLOCK, "wind_spirit_crystal_block", 
    			WATER_SPIRIT_CRYSTAL_BLOCK, "water_spirit_crystal_block", FIFTH_SPIRIT_CRYSTAL_BLOCK, "fifth_spirit_crystal_block",
    			
    			//Dark Stone
    			DARK_STONE, "dark_stone", DARK_STONE_PEBBLES, "dark_stone_pebbles", DARK_STONE_SLAB, "dark_stone_slab", DARK_STONE_WALL, "dark_stone_wall", 
    			DARK_STONE_PRESSURE_PLATE, "dark_stone_pressure_plate", DARK_STONE_STAIRS, "dark_stone_stairs",
    			
    			//TestBlock
    			TEST_BLOCK, "test_block",
    			
    			EARTH_POWER_STONE, "earth_power_stone", WATER_SPIRIT_POWER_FLUID, "water_spirit_power_water",
    			
    			//Blocks with Inventory
    			SPIRIT_POWER_CRAFTER, "spirit_power_crafter");
    	EasyRegister.registerBlocks("frozeniimod");
    }

}