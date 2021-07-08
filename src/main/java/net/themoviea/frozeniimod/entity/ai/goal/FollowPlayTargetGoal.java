package net.themoviea.frozeniimod.entity.ai.goal;

import java.util.EnumSet;
import java.util.function.Predicate;

import org.jetbrains.annotations.Nullable;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.TrackTargetGoal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Box;
import net.themoviea.frozeniimod.entity.passive.MysticAnimalEntity;

public class FollowPlayTargetGoal<T extends LivingEntity> extends TrackTargetGoal {
	protected final Class<T> targetClass;
	   protected final int reciprocalChance;
	   protected LivingEntity targetEntity;
	   protected TargetPredicate targetPredicate;

	   public FollowPlayTargetGoal(MobEntity mob, Class<T> targetClass, boolean checkVisibility) {
	      this(mob, targetClass, checkVisibility, false);
	   }

	   @SuppressWarnings({ "unchecked", "rawtypes" })
	   public FollowPlayTargetGoal(MobEntity mob, Class<T> targetClass, boolean checkVisibility, boolean checkCanNavigate) {
	      this(mob, targetClass, 10, checkVisibility, checkCanNavigate, (Predicate)null);
	   }

	   public FollowPlayTargetGoal(MobEntity mob, Class<T> targetClass, int reciprocalChance, boolean checkVisibility, boolean checkCanNavigate, @Nullable Predicate<LivingEntity> targetPredicate) {
	      super(mob, checkVisibility, checkCanNavigate);
	      this.targetClass = targetClass;
	      this.reciprocalChance = reciprocalChance;
	      this.setControls(EnumSet.of(Goal.Control.TARGET));
	      this.targetPredicate = (new TargetPredicate()).setBaseMaxDistance(this.getFollowRange()).setPredicate(targetPredicate);
	   }

	   public boolean canStart() {
		   if(this.mob.getAttacker() == null) {
		      if (this.reciprocalChance > 0 && this.mob.getRandom().nextInt(this.reciprocalChance) != 0) {
		         return false;
		      } else {
		         this.findClosestTarget();
		         return this.targetEntity != null;
		      }
		   } else {
			   return false;
		   }
	   }

	   protected Box getSearchBox(double distance) {
	      return this.mob.getBoundingBox().expand(distance, 4.0D, distance);
	   }

	   protected void findClosestTarget() {
	      if (this.targetClass != PlayerEntity.class && this.targetClass != ServerPlayerEntity.class) {
	         this.targetEntity = this.mob.world.getClosestEntityIncludingUngeneratedChunks(this.targetClass, this.targetPredicate, this.mob, this.mob.getX(), this.mob.getEyeY(), this.mob.getZ(), this.getSearchBox(this.getFollowRange()));
	      } else {
	         this.targetEntity = this.mob.world.getClosestPlayer(this.targetPredicate, this.mob, this.mob.getX(), this.mob.getEyeY(), this.mob.getZ());
	      }

	   }

	   public void start() {
		   if(this.mob instanceof MysticAnimalEntity) {
			   ((MysticAnimalEntity)this.mob).setPlayTarget(this.targetEntity);
		   }
		   super.start();
	   }

	   public void setTargetEntity(@Nullable LivingEntity targetEntity) {
	      this.targetEntity = targetEntity;
	   }
}
