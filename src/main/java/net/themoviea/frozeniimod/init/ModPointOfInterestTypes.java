package net.themoviea.frozeniimod.init;

import java.util.Set;

import com.google.common.collect.ImmutableSet;

import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.fabricmc.fabric.mixin.object.builder.PointOfInterestTypeAccessor;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Identifier;
import net.minecraft.world.poi.PointOfInterestType;

public class ModPointOfInterestTypes {
	public static final PointOfInterestType SPIRIT_POWER_CRAFTER = PointOfInterestHelper.register(new Identifier("frozeniimod", "spirit_power_crafter_job"), 1, 1, ModBlocks.SPIRIT_POWER_CRAFTER);

	public ModPointOfInterestTypes()
	{
		
	}
}
