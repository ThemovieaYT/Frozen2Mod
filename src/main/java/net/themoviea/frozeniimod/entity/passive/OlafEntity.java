package net.themoviea.frozeniimod.entity.passive;

import java.util.EnumSet;
import java.util.function.Predicate;

import org.jetbrains.annotations.Nullable;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.goal.FollowMobGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.TrackTargetGoal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

public class OlafEntity extends AbstractCommunicationEntity {
	private LivingEntity partnerTarget;

	protected OlafEntity(EntityType<? extends AbstractCommunicationEntity> entityType, World world) {
		super(entityType, world);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initGoals() {
		this.goalSelector.add(1, new SwimGoal(this));
		this.goalSelector.add(6, new FollowMobGoal(this, 1.0D, 10.0F, 2.0F));
		this.targetSelector.add(4, new FollowPartnerTargetGoal<>(this, AnnaEntity.class, true, false));
		this.targetSelector.add(4, new FollowPartnerTargetGoal<>(this, ElsaEntity.class, true, false));
	}
	
	public void setPartnerTarget(LivingEntity partner) {
		this.partnerTarget = partner;
	}
	
	public LivingEntity getPartnerTarget() {
		return this.partnerTarget;
	}
	
	
	private class FollowPartnerTargetGoal<T extends LivingEntity> extends TrackTargetGoal {
		protected final Class<T> targetClass;
		protected final int reciprocalChance;
		protected LivingEntity targetEntity;
		protected TargetPredicate targetPredicate;
		protected OlafEntity olaf;

		public FollowPartnerTargetGoal(MobEntity mob, Class<T> targetClass, boolean checkVisibility, boolean checkCanNavigate) {
			this(mob, targetClass, 10, checkVisibility, checkCanNavigate, (Predicate)null);
		}

		public FollowPartnerTargetGoal(MobEntity mob, Class<T> targetClass, int reciprocalChance, boolean checkVisibility, boolean checkCanNavigate, @Nullable Predicate<LivingEntity> targetPredicate) {
			super(mob, checkVisibility, checkCanNavigate);
			this.olaf = (OlafEntity)mob;
			this.targetClass = targetClass;
			this.reciprocalChance = reciprocalChance;
			this.setControls(EnumSet.of(Goal.Control.TARGET));
			this.targetPredicate = (new TargetPredicate()).setBaseMaxDistance(this.getFollowRange()).setPredicate(targetPredicate);
		}

		@Override
		public boolean canStart() {
			if (this.reciprocalChance > 0 && this.olaf.getRandom().nextInt(this.reciprocalChance) != 0) {
		         return false;
		      } else {
		         this.findClosestTarget();
		         return this.targetEntity != null;
		      }
		}
		
		 protected Box getSearchBox(double distance) {
		      return this.olaf.getBoundingBox().expand(distance, 4.0D, distance);
		   }
		
		protected void findClosestTarget() {
			if (this.targetClass != PlayerEntity.class && this.targetClass != ServerPlayerEntity.class) {
				this.targetEntity = this.olaf.world.getClosestEntityIncludingUngeneratedChunks(this.targetClass, this.targetPredicate, this.olaf, this.olaf.getX(), this.olaf.getEyeY(), this.olaf.getZ(), this.getSearchBox(this.getFollowRange()));
			} else {
				this.targetEntity = this.olaf.world.getClosestPlayer(this.targetPredicate, this.olaf, this.olaf.getX(), this.olaf.getEyeY(), this.olaf.getZ());
			}
		}
		
		@Override
		public void start() {
			this.olaf.setPartnerTarget(this.targetEntity);
			super.start();
		}
		
		public void setPartnerTargetEntity(@Nullable LivingEntity targetEntity) {
			this.targetEntity = targetEntity;
		}
	}
}
