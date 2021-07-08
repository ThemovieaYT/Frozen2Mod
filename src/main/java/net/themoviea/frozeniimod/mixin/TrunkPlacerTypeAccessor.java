package net.themoviea.frozeniimod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import com.mojang.serialization.Codec;

import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

@Mixin(TrunkPlacerType.class)
public interface TrunkPlacerTypeAccessor {
	@Invoker("register")
	public static <P extends TrunkPlacer> TrunkPlacerType<P> register(String id, Codec<P> codec) {
		throw new AssertionError();
	}
}
