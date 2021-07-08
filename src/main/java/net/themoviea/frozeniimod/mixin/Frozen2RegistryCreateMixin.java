package net.themoviea.frozeniimod.mixin;

import java.util.function.Supplier;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.util.registry.DefaultedRegistry;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;

@Mixin(Registry.class)
public interface Frozen2RegistryCreateMixin {
	@Invoker("create")
	public static <T> DefaultedRegistry<T> create(RegistryKey<? extends Registry<T>> registryKey, String defaultId, Supplier<T> defaultEntry) {
		throw new AssertionError();
	}
}
