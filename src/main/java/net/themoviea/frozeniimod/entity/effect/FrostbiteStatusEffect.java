package net.themoviea.frozeniimod.entity.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.entity.player.PlayerEntity;

public class FrostbiteStatusEffect extends StatusEffect
{
	public FrostbiteStatusEffect()
	{
		super(StatusEffectType.HARMFUL, 0x3AAAFF);
	}
	
	@Override
	public boolean canApplyUpdateEffect(int duration, int amplifier) 
	{
		int k = 80 >> amplifier;
		return duration % k == 0;
	}
	
	@Override
	public void applyUpdateEffect(LivingEntity entity, int amplifier) 
	{
		if(entity.getHealth() >= 5.0f)
		{
			if(entity instanceof PlayerEntity)
			{
				entity.damage(DamageSource.MAGIC, 2.0f);
			}
			entity.damage(DamageSource.MAGIC, 1.0f);
		}
	}
}
