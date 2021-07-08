package net.themoviea.frozeniimod.world.gen.generator;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;

import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.structure.pool.StructurePools;
import net.minecraft.structure.processor.StructureProcessorLists;
import net.minecraft.util.Identifier;
import net.themoviea.frozeniimod.init.ModFeatures;

public class TestNameGenerator {
		public static StructurePool structures;
		public static void init() {}
		static {
			structures = StructurePools.register(
				  	new StructurePool(ModFeatures.NAME_POOL, new Identifier("empty"), 
				    	ImmutableList.of(
				    				Pair.of(StructurePoolElement.method_30426("frozeniimod:name/testthing/name1structure", StructureProcessorLists.STREET_PLAINS), 2),
				    				Pair.of(StructurePoolElement.method_30426("frozeniimod:name/testthing/name2structure", StructureProcessorLists.STREET_PLAINS), 2), 
				    		  		Pair.of(StructurePoolElement.method_30426("frozeniimod:name/testthing/name3structure", StructureProcessorLists.STREET_PLAINS), 2), 
				      				Pair.of(StructurePoolElement.method_30426("frozeniimod:name/testthing/name4structure", StructureProcessorLists.STREET_PLAINS), 2)), 
				    	 StructurePool.Projection.RIGID));
	}
}
