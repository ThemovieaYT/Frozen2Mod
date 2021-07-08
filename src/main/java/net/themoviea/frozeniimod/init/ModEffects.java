package net.themoviea.frozeniimod.init;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.themoviea.frozeniimod.entity.effect.FrostbiteStatusEffect;
import net.themoviea.themovieapi_base.exceptions.InputNotAnObjectException;
import net.themoviea.themovieapi_base.registering.EasyRegister;

public class ModEffects 
{
	public static final StatusEffect FROST_BITE = new FrostbiteStatusEffect();
	
	public static void registerFrozen2ModEffects() throws InputNotAnObjectException
	{
		EasyRegister.createEffectList(FROST_BITE, "frost_bite");
		EasyRegister.registerEffects("frozeniimod");
	}
}
