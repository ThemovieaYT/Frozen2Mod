package net.themoviea.frozeniimod.init;

import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.ProjectileDamageSource;
import net.themoviea.frozeniimod.entity.projectile.IcePowerEntity;

public class ModDamageSources extends DamageSource
{
	public static final DamageSource ELSA_MAGIC = (new ModDamageSources("frozeniimod:elsa_magic")).setBypassesArmor();
	public static final DamageSource SPIRIT_POWER = (new ModDamageSources("frozeniimod:spirit_power")).setBypassesArmor();
	protected ModDamageSources(String name) 
	{
		super(name);
	}
	
	public static DamageSource elsaMagic(IcePowerEntity icePower, Entity attacker)
	{
		return (new ProjectileDamageSource("icePower", icePower, attacker)).setProjectile();
	}
}
