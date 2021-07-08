package net.themoviea.frozeniimod.entity.passive;

import java.util.UUID;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.Durations;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.ai.goal.AttackGoal;
import net.minecraft.entity.ai.goal.FollowTargetGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.UniversalAngerGoal;
import net.minecraft.entity.mob.Angerable;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.IntRange;
import net.minecraft.world.World;
import net.themoviea.frozeniimod.Frozen2Mod;
import net.themoviea.frozeniimod.entity.PowerElement;
import net.themoviea.frozeniimod.entity.ai.goal.BruniRevengeGoal;
import net.themoviea.frozeniimod.entity.ai.goal.TrackBruniTargetGoal;
import net.themoviea.frozeniimod.item.powers.PowerItem;
import net.themoviea.frozeniimod.item.powers.attack_methods.AirExplodeAttackMethod;
import net.themoviea.frozeniimod.item.powers.attack_methods.SetOnFireAttackMethod;

public class BruniEntity extends MysticAnimalEntity implements Angerable, RangedAttackMob {
	private static final Ingredient EATING_INGREDIENT;
	private static final IntRange intRange;
	private int angerTime;
	private boolean angry;
	private boolean hasFire;
	private UUID angryAt;
	
	public BruniEntity(EntityType<? extends BruniEntity> entityType, World world) {
		super(entityType, world, new SetOnFireAttackMethod(0, 0, 0, 0, null), new SetOnFireAttackMethod(0, 0, 0, 0, null));
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected void initGoals() {
		this.goalSelector.add(1, new SwimGoal(this));
		this.goalSelector.add(2, new AttackGoal(this));
		this.goalSelector.add(3, new TemptGoal(this, 1.1D, EATING_INGREDIENT, false));
		this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 6.0f));
		this.targetSelector.add(1, new TrackBruniTargetGoal(this));
		this.targetSelector.add(2, new BruniRevengeGoal(this, new Class[0]));
		this.targetSelector.add(3, new FollowTargetGoal<PlayerEntity>(this, PlayerEntity.class, 10, true, false, this::shouldAngerAt));
		this.targetSelector.add(4, new UniversalAngerGoal(this, true));
	}
	
	/*public boolean tryAttack(Entity target) {
		// TODO Auto-generated method stub
		return super.tryAttack(target);
	}*/
	
	@Override
	public PowerElement getElement() {
		return PowerElement.FIRE;
	}
	
	@Override
	public boolean isEatingItem(ItemStack stack) {
		return EATING_INGREDIENT.test(stack);
	}
	
	public void setAngry(boolean angry) {
		this.angry = angry;
	}
	
	public boolean isAngry() {
		if(this.getAngerTime() > 0) {
			return true;
		} else {
			return this.angry;
		}
	}
	
	@Override
	public void tickMovement() {
			if(this.isAngry() == true) {
				this.world.addParticle(ParticleTypes.FLAME, this.getPos().x, this.getPos().y + 0.3D, this.getPos().z, 0.0D, 0.0D, 0.0D);
			}
		super.tickMovement();
	}
	
	public void setHasFire(boolean hasFire) {
		this.hasFire = hasFire;
	}

	@Override
	public int getAngerTime() {
		return this.angerTime;
	}

	@Override
	public void setAngerTime(int ticks) {
		this.angerTime = ticks;
	}

	@Override
	public UUID getAngryAt() {
		// TODO Auto-generated method stub
		return this.angryAt = uuid;
	}

	@Override
	public void setAngryAt(UUID uuid) {
		this.angryAt = uuid;
	}

	@Override
	public void chooseRandomAngerTime() {
		this.setAngerTime(intRange.choose(this.random));
		
	}

	@Override
	public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@SuppressWarnings("resource")
	@Override
	public void setAttacker(LivingEntity attacker) {
		if(this.world.isClient) {
			if(FabricLoader.getInstance().isDevelopmentEnvironment()) {
				if(attacker != null) {
					MinecraftClient.getInstance().player.sendChatMessage("Attacker that used the attack method: " + attacker.getEntityName().toString());
					this.setAngry(true);
				}
				MinecraftClient.getInstance().player.sendChatMessage("Attacker that used the attack method: ");
			}
		}
		if(attacker != null) {
			this.setAngry(true);
		}

		super.setAttacker(attacker);
	}
	
	static {
		intRange = Durations.betweenSeconds(20, 39);
		EATING_INGREDIENT = Ingredient.ofItems(Items.OAK_PLANKS, Items.ACACIA_PLANKS);
	}

	@Override
	public void attack(LivingEntity target, float pullProgress) {
		// TODO Auto-generated method stub
		
	}
}
