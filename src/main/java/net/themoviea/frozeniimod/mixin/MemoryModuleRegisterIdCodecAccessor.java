package net.themoviea.frozeniimod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import com.mojang.serialization.Codec;

import net.minecraft.entity.ai.brain.MemoryModuleType;

@Mixin(MemoryModuleType.class)
public interface MemoryModuleRegisterIdCodecAccessor 
{
	@Invoker("register")
	public static <U> MemoryModuleType<U> register(String id, Codec<U> codec)
	{
		throw new AssertionError();
	}
}
