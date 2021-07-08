package net.themoviea.frozeniimod.block.sapling;

import java.util.Random;

import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.themoviea.frozeniimod.init.ModFeatures;

public class FrozenSaplingGenerator extends SaplingGenerator
{
	public ConfiguredFeature<TreeFeatureConfig, ?> createTreeFeature(Random rand, boolean bl)
    {
        return ModFeatures.ENCHANTED;
    }
}
