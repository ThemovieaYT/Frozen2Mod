package net.themoviea.frozeniimod.entity.passive;

import java.util.UUID;

import com.google.common.collect.ImmutableList;

import dev.onyxstudios.cca.api.v3.component.ComponentProvider;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.Durations;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.ai.brain.sensor.SensorType;
import net.minecraft.entity.ai.goal.AttackGoal;
import net.minecraft.entity.ai.goal.FollowTargetGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WanderAroundGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.Angerable;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.IntRange;
import net.minecraft.world.World;
import net.themoviea.frozeniimod.entity.Queenable;
import net.themoviea.frozeniimod.frozeniivillage.arendelle.ArendellianRepComponent;
import net.themoviea.frozeniimod.init.ModComponents;
import net.themoviea.frozeniimod.init.ModEntities;
import net.themoviea.themovieapi_entity.entity.ai.goals.RandomGoal;

public class AnnaEntity extends AbstractCommunicationEntity implements Angerable, Queenable
{
	private boolean queen;
	private int angerTime;
	private static final IntRange angerInt = Durations.betweenSeconds(100, 200);
	private UUID angryAt;
	protected static final ImmutableList<SensorType<? extends Sensor<? super AnnaEntity>>> SENSOR_TYPES;
	protected static final ImmutableList<MemoryModuleType<?>> MEMORY_MODULE_TYPES;
	
	public AnnaEntity(EntityType<? extends AnnaEntity> entityType, World world) 
	{
		super(entityType, world);
		this.setQueen(false);
	}
	
	/*protected void initGoals() 
	{
		//this.goalSelector.add(0, new SwimGoal(this));
		this.goalSelector.add(5, new WanderAroundGoal(this, 0.35D));
		this.goalSelector.add(6, new LookAtEntityGoal(this, ElsaEntity.class, 6.0F));
		this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));		
	}*/
	
	@SuppressWarnings("unchecked")
	@Override
	protected void initScheduledGoals() {
		this.customGoalSelector.addScheduled(2000, 600, new SwimGoal(this));
		//this.customGoalSelector.addScheduled(2300, 300, new AttackGoal(this));
		//this.customGoalSelector.addScheduled(2300, 300, new FollowTargetGoal(this, PigEntity.class, true));
		this.customGoalSelector.addScheduled(2300, 300, new RandomGoal(ImmutableList.of(new SwimGoal(this), new FollowTargetGoal(this, PlayerEntity.class, false), new WanderAroundGoal(this, 0.11D)), 60));
	}
	
	@SuppressWarnings("unchecked")
	public Brain<AnnaEntity> getBrain() 
	{
	      return (Brain<AnnaEntity>) super.getBrain();
	}

	protected Brain.Profile<AnnaEntity> createBrainProfile() 
	{
	      return Brain.createProfile(MEMORY_MODULE_TYPES, SENSOR_TYPES);
	}
	
	@Override
	public boolean getQueen() 
	{
		return queen;
	}
	
	@Override
	public void setQueen(boolean queen) 
	{
		this.queen = queen;
	}
	
	@Override
	public boolean canTarget(EntityType<?> type) 
	{
		return type == ModEntities.ELSA ? false : super.canTarget(type);
	}
	
	@Override
	public float getHealth() 
	{
		return super.getHealth();
	}
	
	public static DefaultAttributeContainer.Builder createAnnaAttributes()
	{
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 70D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 0.1D);
	}
	
	public void chooseAngerTime()
	{
		this.setAngerTime(angerInt.choose(this.random));
	}
	
	@Override
	public int getAngerTime() 
	{
		return this.angerTime;
	}

	@Override
	public void setAngerTime(int ticks) 
	{
		this.angerTime = ticks;
	}

	@Override
	public UUID getAngryAt() 
	{
		return this.angryAt;
	}

	@Override
	public void setAngryAt(UUID uuid) 
	{
		this.angryAt = uuid;
	}

	@Override
	public void chooseRandomAngerTime() 
	{
		// TODO Auto-generated method stub
		
	}
	
	@SuppressWarnings("resource")
	@Override
	public void setAttacker(LivingEntity attacker) 
	{
		if (attacker != null && attacker instanceof PlayerEntity)
		{
			ArendellianRepComponent reputationComponent = ModComponents.ARENDELLIAN_REP_COMP.get(ComponentProvider.fromEntity(attacker));
			reputationComponent.decrease(1);
			int i;
			i = reputationComponent.getReputation();
			if(this.world.isClient) {
				if(FabricLoader.getInstance().isDevelopmentEnvironment())
					MinecraftClient.getInstance().player.sendChatMessage("Reputation has been decreased to: " + i);
			}
		}
		super.setAttacker(attacker);
	}
	
	@Override
	public void onDeath(DamageSource source) 
	{
		LOGGER.info("Anna died. Elsa will cry now., message: '{}'", this, source.getDeathMessage(this).getString());
		super.onDeath(source);
	}
	
	static 
	{
	      SENSOR_TYPES = ImmutableList.of();
	      MEMORY_MODULE_TYPES = ImmutableList.of();
	}
}
