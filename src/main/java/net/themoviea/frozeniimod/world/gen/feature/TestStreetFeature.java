package net.themoviea.frozeniimod.world.gen.feature;

import com.mojang.serialization.Codec;

import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructureStart;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.JigsawFeature;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;
import net.themoviea.frozeniimod.world.gen.generator.TestGenerator;

public class TestStreetFeature extends JigsawFeature {

	public TestStreetFeature(Codec<StructurePoolFeatureConfig> codec) {
		super(codec, 0, true, true);
		// TODO Auto-generated constructor stub
	}
}
