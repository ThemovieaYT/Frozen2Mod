package net.themoviea.frozeniimod.init;

import net.minecraft.world.gen.trunk.TrunkPlacerType;
import net.themoviea.frozeniimod.mixin.TrunkPlacerTypeAccessor;
import net.themoviea.frozeniimod.world.gen.placer.trunk.EnchantedTrunkPlacer;

public class ModTrunkPlacers {
	public static final TrunkPlacerType<EnchantedTrunkPlacer> ENCHANTED_TRUNK_PLACER;

	public static void registerFrozen2TrunkPlacers() {

	}
	
	static {
		ENCHANTED_TRUNK_PLACER = TrunkPlacerTypeAccessor.register("frozeniimod:enchanted_trunk_placer", EnchantedTrunkPlacer.CODEC);
	}
}
