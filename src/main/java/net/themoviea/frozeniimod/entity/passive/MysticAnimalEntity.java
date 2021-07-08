package net.themoviea.frozeniimod.entity.passive;

import java.util.UUID;

import org.jetbrains.annotations.Nullable;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.Angerable;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.themoviea.frozeniimod.entity.PowerElement;
import net.themoviea.frozeniimod.entity.ai.Vec3dExtendedManager;
import net.themoviea.frozeniimod.init.ModDamageSources;
import net.themoviea.frozeniimod.item.powers.attack_methods.NoneAttackMethod;
import net.themoviea.frozeniimod.item.powers.attack_methods.PowerAttackMethod;
import net.themoviea.frozeniimod.item.powers.protect_methods.NoneProtectMethod;
import net.themoviea.frozeniimod.item.powers.protect_methods.PowerProtectMethod;
import net.themoviea.frozeniimod.item.powers.use_methods.NoneUseMethod;
import net.themoviea.frozeniimod.item.powers.use_methods.PowerUseMethod;

public abstract class MysticAnimalEntity extends AnimalEntity implements Angerable {
	public PowerAttackMethod attackMethod, attackMethod2;
	public PowerProtectMethod protectMethod, protectMethod2;
	public PowerUseMethod useMethod, useMethod2;
	private LivingEntity playTarget;
	private boolean isLiking;
	private int angerTime;
	private int likingTicks;
	
	public MysticAnimalEntity(EntityType<? extends MysticAnimalEntity> entityType, World world) {
		this(entityType, world, new NoneAttackMethod(), new NoneAttackMethod());
	}
	
	public MysticAnimalEntity(EntityType<? extends MysticAnimalEntity> entityType, World world,
			PowerAttackMethod attackMethod, PowerAttackMethod attackMethod2) {
		this(entityType, world, attackMethod, attackMethod2,
				new NoneProtectMethod(), new NoneProtectMethod());
	}
	public MysticAnimalEntity(EntityType<? extends MysticAnimalEntity> entityType, World world, 
			PowerAttackMethod attackMethod, PowerAttackMethod attackMethod2,
			PowerProtectMethod protectMethod, PowerProtectMethod protectMethod2) {
		this(entityType, world, attackMethod, attackMethod2,
				protectMethod, protectMethod2,
				new NoneUseMethod(), new NoneUseMethod());
	}
	
	public MysticAnimalEntity(EntityType<? extends MysticAnimalEntity> entityType, World world, 
			PowerAttackMethod attackMethod, PowerAttackMethod attackMethod2,
			PowerProtectMethod protectMethod, PowerProtectMethod protectMethod2,
			PowerUseMethod useMethod, PowerUseMethod useMethod2) {
		super(entityType, world);
		this.attackMethod = attackMethod;
		this.attackMethod2 = attackMethod2;
		this.protectMethod = protectMethod;
		this.protectMethod2 = protectMethod2;
		this.useMethod = useMethod;
		this.useMethod2 = useMethod2;
	}
	
	public PowerElement getElement() {
		return PowerElement.NONE;
	}
	
	public void setPlayTarget(@Nullable LivingEntity playTarget) {
		this.playTarget = playTarget;
	}
	
	@Nullable
	public LivingEntity getPlayTarget() {
		return this.playTarget;
	}
	
	public Vec3dExtendedManager getManagerVelocity() {
		return new Vec3dExtendedManager(0, 0, 0);
	}
	
	public boolean isEatingItem(ItemStack stack) {
		return stack.getItem() == Items.WHEAT;
	}
	
	public boolean getLikingTime() {
		if(this.likingTicks > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public void setLikingTime(int ticks) {
		this.likingTicks = ticks;
	}
	@Override
	public ActionResult interactMob(PlayerEntity player, Hand hand) {
		ItemStack itemStack = player.getStackInHand(hand);
		if(this.isEatingItem(itemStack)) {
			this.eat(player, itemStack);
			this.setLikingTime(10);
			return ActionResult.SUCCESS;
		}
		
		if(this.world.isClient) {
			return ActionResult.CONSUME;
		}
		
		return ActionResult.PASS;
	}
	
	@Override
	public void tickMovement() {
		super.tickMovement();
		if(this.likingTicks > 0) {
			--this.likingTicks;
	       	MinecraftClient.getInstance().player.sendChatMessage("I am in love!");
		}
	}
	
	@Override
	public boolean tryAttack(Entity target) {
		boolean bl = target.damage(ModDamageSources.SPIRIT_POWER, 0);
			ActionResult result = attackMethod.nonPlayerAttackEntity((LivingEntity)target);
			if(result == ActionResult.FAIL) {
				attackMethod2.nonPlayerAttackEntity((LivingEntity)target);
			}
		return bl;
	}
	
	public void tryProtect() {
		if(this.getAttacker().getAttacking() == this) {
			if(protectMethod.powerProtectCondition(this) == true) {
				protectMethod.entityProtectFromPower(this, world);
			}
		}
	}
	
	public void tryUse() {
		if(useMethod.powerUseCondition(world, this) == true) {
			useMethod.usePower(this, world);
		}
	}
	
	

	@Override
	public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getAngerTime() {
		// TODO Auto-generated method stub
		return this.angerTime;
	}

	@Override
	public void setAngerTime(int ticks) {
		this.angerTime = ticks;
	}

	@Override
	public UUID getAngryAt() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAngryAt(UUID uuid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void chooseRandomAngerTime() {
		// TODO Auto-generated method stub
		
	}
}
