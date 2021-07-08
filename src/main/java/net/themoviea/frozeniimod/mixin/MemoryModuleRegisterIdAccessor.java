package net.themoviea.frozeniimod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.entity.ai.brain.MemoryModuleType;

@Mixin(MemoryModuleType.class)
public interface MemoryModuleRegisterIdAccessor 
{
	@Invoker("register")
	public static <U> MemoryModuleType<U> frozen2ModRegister(String id)
	{
		throw new AssertionError();
	}
}
