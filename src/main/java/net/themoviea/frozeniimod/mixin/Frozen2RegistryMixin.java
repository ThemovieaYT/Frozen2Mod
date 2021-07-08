package net.themoviea.frozeniimod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;

@Mixin(Registry.class)
public interface Frozen2RegistryMixin {
	@Invoker("createRegistryKey")
	public static <T> RegistryKey<Registry<T>> createRegistryKey(String registryId) {
		throw new AssertionError();
	}
}
