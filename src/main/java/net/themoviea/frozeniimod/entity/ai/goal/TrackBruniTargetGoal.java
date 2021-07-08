package net.themoviea.frozeniimod.entity.ai.goal;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.goal.Goal;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.ai.goal.TrackTargetGoal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;
import net.themoviea.frozeniimod.entity.passive.AnnaEntity;
import net.themoviea.frozeniimod.entity.passive.BruniEntity;

public class TrackBruniTargetGoal extends TrackTargetGoal {
	private final BruniEntity bruni;
	private LivingEntity target;
	   private final TargetPredicate targetPredicate = (new TargetPredicate()).setBaseMaxDistance(64.0D);

	
	public TrackBruniTargetGoal(BruniEntity mob) {
		super(mob, false, true);
		this.bruni = mob;
		this.setControls(EnumSet.of(Goal.Control.TARGET));
	}

	@Override
	public boolean canStart() {
		Box box = this.bruni.getBoundingBox().expand(10.0D, 8.0D, 10.0D);
	      List<LivingEntity> list = this.bruni.world.getTargets(AnnaEntity.class, this.targetPredicate, this.bruni, box);
	      List<PlayerEntity> list2 = this.bruni.world.getPlayers(this.targetPredicate, this.bruni, box);
	      Iterator<LivingEntity> var4 = list.iterator();

	      while(var4.hasNext()) {
	         LivingEntity livingEntity = (LivingEntity)var4.next();
	         AnnaEntity annaEntity = (AnnaEntity)livingEntity;
	         Iterator<PlayerEntity> var7 = list2.iterator();

	         while(var7.hasNext()) {
	            PlayerEntity playerEntity = (PlayerEntity)var7.next();
	            if(annaEntity.getAttacker() == playerEntity)
	            {
	            	this.target = playerEntity;
	            	if(playerEntity.isDead() == true)
	            	{
	            		this.target = null;
	            	}
	            }
	         }
	      }

	      if (this.target == null) {
	         return false;
	      } else if (!(this.target instanceof PlayerEntity) || !this.target.isSpectator() && !((PlayerEntity)this.target).isCreative()) {
	         return true;
	      } else {
	         return false;
	      }
	   }

	   public void start() {
	      this.bruni.setTarget(this.target);
	      super.start();
	   }

}
