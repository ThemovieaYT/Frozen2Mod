package net.themoviea.frozeniimod.entity.boss;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.text.TranslatableText;
import net.minecraft.world.World;

public class GhostFifthSpiritElsaEntity extends MobEntity
{
	@SuppressWarnings("unused")
	private final ServerBossBar bossBar;
	
	public GhostFifthSpiritElsaEntity(EntityType<? extends GhostFifthSpiritElsaEntity> entityType, World world)
	{
		super(entityType, world);
		this.bossBar = (ServerBossBar)(new ServerBossBar(new TranslatableText("entity.frozeniimod.ghost_fifth_spirit_elsa"), BossBar.Color.BLUE, BossBar.Style.PROGRESS)).setDarkenSky(true).setThickenFog(true);
		this.setHealth(this.getMaxHealth());
	}
}
