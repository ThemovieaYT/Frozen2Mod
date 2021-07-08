package net.themoviea.frozeniimod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.entity.ai.brain.Activity;

@Mixin(Activity.class)
public interface ActivityAccessor 
{
	@Invoker("register")
	public static Activity register(String id)
	{
		throw new AssertionError();
	}
}
