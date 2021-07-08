package net.themoviea.frozeniimod.entity.passive;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

import dev.onyxstudios.cca.api.v3.component.ComponentProvider;
import io.netty.util.internal.ThreadLocalRandom;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.ai.TargetFinder;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.goal.AttackGoal;
import net.minecraft.entity.ai.goal.FollowTargetGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.ai.goal.TrackTargetGoal;
import net.minecraft.entity.ai.goal.UniversalAngerGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.ai.goal.WanderAroundGoal;
import net.minecraft.entity.ai.pathing.BirdNavigation;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.Angerable;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.themoviea.frozeniimod.entity.PowerElement;
import net.themoviea.frozeniimod.entity.ai.MovementTestClass;
import net.themoviea.frozeniimod.entity.ai.Vec3dExtendedManager;
import net.themoviea.frozeniimod.entity.ai.goal.FollowPlayTargetGoal;
import net.themoviea.frozeniimod.frozeniivillage.arendelle.ArendellianRepComponent;
import net.themoviea.frozeniimod.init.ModComponents;
import net.themoviea.frozeniimod.init.ModDamageSources;
import net.themoviea.frozeniimod.init.ModParticles;
import net.themoviea.frozeniimod.item.powers.attack_methods.AirExplodeAttackMethod;

public class GaleEntity extends MysticAnimalEntity implements Angerable {
	private boolean isFollowingTarget;
	private int angerTime;
	
	public GaleEntity(EntityType<? extends MysticAnimalEntity> entityType, World world) {
		super(entityType, world, new AirExplodeAttackMethod(), new AirExplodeAttackMethod());
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected void initGoals() {
		//this.goalSelector.add(5, new WanderAroundFarGoal(this, 1.000012D));
		this.goalSelector.add(2, new AttackGoal(this));
		this.goalSelector.add(3, new GaleEntity.GalePlayGoal(this));
		this.goalSelector.add(5, new GaleEntity.GaleWanderAroundGoal(this, 1.000012D));
		this.targetSelector.add(1, new FollowTargetGoal<PlayerEntity>(this, PlayerEntity.class, 10, true, false, this::shouldAngerAt));
		this.targetSelector.add(2, new GaleEntity.GaleRevengeGoal(this, new Class[0]));
		this.targetSelector.add(3, new FollowPlayTargetGoal<PlayerEntity>(this, PlayerEntity.class, 10, true, false, this::shouldAngerAt));
		this.targetSelector.add(4, new FollowPlayTargetGoal<PigEntity>(this, PigEntity.class, true));
		this.targetSelector.add(5, new UniversalAngerGoal(this, false));
	
	}
	
	@Override
	protected EntityNavigation createNavigation(World world) {
	      BirdNavigation birdNavigation = new BirdNavigation(this, world);
	      birdNavigation.setCanPathThroughDoors(false);
	      birdNavigation.setCanSwim(true);
	      birdNavigation.setCanEnterOpenDoors(true);
	      return birdNavigation;
	   }
	
	@Override
	public PowerElement getElement() {
		return PowerElement.AIR;
	}
	
	public void setIsFollowingTarget(boolean isFollowingTarget) {
		this.isFollowingTarget = isFollowingTarget;
	}
	
	public boolean getIsFollowingTarget() {
		return this.isFollowingTarget;
	}
	
	@Override
	public boolean handleFallDamage(float fallDistance, float damageMultiplier) {
		return false;
	}

	public void attack(LivingEntity target, float pullProgress) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void tickMovement() {
		BlockPos pos = new BlockPos(this.getBlockPos().getX(), this.getBlockPos().getY() - 1, this.getBlockPos().getZ());
		if(world.getBlockState(pos) != Blocks.AIR.getDefaultState()) {
			this.setOnGround(true);
		} else {
			this.setOnGround(false);
		}
		if(this.world.isClient) {
			if(FabricLoader.getInstance().isDevelopmentEnvironment()) {
				//System.out.println("Velocity: " + this.getVelocity().x + " " + this.getVelocity().y + " " + this.getVelocity().z);
				//System.out.println("Is on ground: " + this.onGround);
			}
		}
		if (!this.onGround && this.getVelocity().y < 0.0D) {
			if(this.getIsFollowingTarget() == true) {
				if(this.getPlayTarget() != null) {
					double x, y, z;
					System.out.println(this.getVelocity().toString());
					/*if(this.getPlayTarget().getBlockPos().getX() < this.getBlockPos().getX()) {
						x = -1.0D;
					} else {
						x = 1.0D;
					}*/
					if(this.getPlayTarget().getBlockPos().getY() < this.getBlockPos().getY()) {
						this.setVelocity(this.getVelocity().multiply(1.0D, 1.0D, 1.0D));
					} else {
						this.setVelocity(this.getVelocity().multiply(1.0D, -1.0D, 1.0D));
					}
					/*if(this.getPlayTarget().getBlockPos().getZ() < this.getBlockPos().getZ()) {
						z = 1.0D;
					} else {
						z = -1.0D;
					}*/
				} else {
					this.setVelocity(this.getVelocity().multiply(1.0D, 0, 1.0D));
				}
			} else {
				this.setVelocity(this.getVelocity().multiply(1.0D, 0, 1.0D));
			}
		}
		
		if(this.world.isClient) {
			this.world.addParticle(ModParticles.LEAF_FALL_PARTICLE, this.getParticleX(0.5D), this.getRandomBodyY(), this.getParticleZ(0.5D), 0.0D, 0.0D, 0.0D);
		}
		
		super.tickMovement();
	}
	
	@Override
	public boolean tryAttack(Entity target) {
		boolean bl = target.damage(ModDamageSources.SPIRIT_POWER, 0);
		this.attackMethod.nonPlayerAttackEntityRightClickBlock(this);
		return bl;
	}

	@Override
	public int getAngerTime() {
		// TODO Auto-generated method stub
		return this.angerTime;
	}

	@Override
	public void setAngerTime(int ticks) {
		// TODO Auto-generated method stub
		
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
	
	@Override
	public void setAttacker(LivingEntity attacker) {
		if (attacker != null && attacker instanceof PlayerEntity)
    	{
			if(this.getTarget() != null) {
				if(this.world.isClient) {
					if(FabricLoader.getInstance().isDevelopmentEnvironment()) {
						MinecraftClient.getInstance().player.sendChatMessage("The target: " + this.getTarget().getEntityName());
					}
				}
			}
    	}
		super.setAttacker(attacker);
	}
	
	class GalePlayGoal extends Goal {
		private final GaleEntity gale;
		private int playCooldown, playCount, moveUpTime;
		private boolean hasPlayed;
		
		public GalePlayGoal(GaleEntity gale) {
			this.gale = gale; 
		}
		@Override
		public boolean canStart() {
			if(gale.getPlayTarget() != null) {
				return true;
			} else
				return false;
		}
		
		@Override
		public void start() {
			super.start();
		}
		
		@Override
		public void stop() {
			this.gale.getNavigation().stop();
		    super.stop();
		}
		
		public void moveToTarget(LivingEntity target) {
		    this.gale.getNavigation().startMovingTo(target.getX(), target.getY(), target.getZ(), 1.000012D);
		    this.gale.getLookControl().lookAt(target.getVelocity());
			this.gale.setIsFollowingTarget(true);
			if(this.gale.getBoundingBox().expand(0.20000000298023224D).intersects(target.getBoundingBox())) {
				this.gale.setIsFollowingTarget(false);
				target.setVelocity(target.getVelocity().getX(), target.getVelocity().getY() + 2, target.getVelocity().getZ());
				target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 100));
				this.gale.setVelocity(this.gale.getVelocity().multiply(1.0D, -2.0D, 1.0D));
				++this.playCount;
				this.hasPlayed = true;
			}
		}
		
		@Override
		public void tick() {
			--this.moveUpTime;
			System.out.println(this.playCooldown);

			LivingEntity target = gale.getPlayTarget();
			if(target != null) {
				--this.playCooldown;
				if(this.moveUpTime <= 0) {
					this.moveUpTime = 20;
				}
				
				if(this.playCooldown <= -100) {
					this.playCooldown = 100;
				}
				if(this.playCooldown <= 0) {
					moveToTarget(target);
					if(this.hasPlayed == true) {
						this.playCooldown = 100;
						this.hasPlayed = false;
					}
				}
			} else {
				if(this.playCooldown <= -100) {
					this.playCooldown = 100;
				}
			}
			super.tick();
		}
	}
	
	class GaleWanderAroundGoal extends Goal {
		private final GaleEntity gale;
		protected final double speed;
		private int cooldown;
		private BlockPos pos;
		
		public GaleWanderAroundGoal(GaleEntity entity, double speed) {
			this.gale = entity;
			this.speed = speed;
		}
		
		@Override
		public boolean canStart() {
			if(this.cooldown <= 0) {
				if(this.gale.hasPassengers()) {
					return false;
				} else {
					this.pos = MovementTestClass.findRandomBlockTarget(this.gale, false, 10);
					if(pos == null) {
						return false;
					} else {
						return true;
					}
				}
			} else {
				--this.cooldown;
				return false;
			}
		}
		
		@Override
		public boolean shouldContinue() {
			return !this.gale.getNavigation().isIdle() && !this.gale.hasPassengers();
		}
		
		@Override
		public void start() {
			this.cooldown = this.randomInt(this.randomInt(10, 20), this.randomInt(21, 40));
			MovementTestClass.startMovingToPos(this.gale, this.gale.getEntityWorld(), pos, speed);
		}
		
		@Override
		public void stop() {
			this.gale.getNavigation().stop();
		    super.stop();
		}
		
		public int randomInt(int min, int max) {
			return ThreadLocalRandom.current().nextInt(min, max);
		}
	}
	
	static class GaleRevengeGoal extends TrackTargetGoal {
		private static final TargetPredicate VALID_AVOIDABLES_PREDICATE = (new TargetPredicate()).includeHidden().ignoreDistanceScalingFactor();
		   private boolean groupRevenge;
		   private int lastAttackedTime;
		   private final Class<?>[] noRevengeTypes;
		   private Class<?>[] noHelpTypes;

		   public GaleRevengeGoal(PathAwareEntity mob, Class<?>... noRevengeTypes) {
		      super(mob, true);
		      this.noRevengeTypes = noRevengeTypes;
		      this.setControls(EnumSet.of(Goal.Control.TARGET));
		   }

		   public boolean canStart() {
		      int i = this.mob.getLastAttackedTime();
		      LivingEntity livingEntity = this.mob.getAttacker();
		      if (i != this.lastAttackedTime && livingEntity != null) {
		         if (livingEntity.getType() == EntityType.PLAYER && this.mob.world.getGameRules().getBoolean(GameRules.UNIVERSAL_ANGER)) {
		            return false;
		         } else {
		            Class[] var3 = this.noRevengeTypes;
		            int var4 = var3.length;

		            for(int var5 = 0; var5 < var4; ++var5) {
		               Class<?> class_ = var3[var5];
		               if (class_.isAssignableFrom(livingEntity.getClass())) {
		                  return false;
		               }
		            }

		            return this.canTrack(livingEntity, VALID_AVOIDABLES_PREDICATE);
		         }
		      } else {
		         return false;
		      }
		   }

		   public GaleRevengeGoal setGroupRevenge(Class<?>... noHelpTypes) {
		      this.groupRevenge = true;
		      this.noHelpTypes = noHelpTypes;
		      return this;
		   }

		   public void start() {
			   if(!(this.mob.getAttacker() instanceof GaleEntity) && !(this.mob.getAttacker() instanceof ElsaEntity) && !(this.mob.getAttacker() instanceof AnnaEntity)) {
			      this.mob.setTarget(this.mob.getAttacker());
			      this.target = this.mob.getTarget();
			      this.lastAttackedTime = this.mob.getLastAttackedTime();
			      this.maxTimeWithoutVisibility = 300;
			      if (this.groupRevenge) {
			         this.callSameTypeForRevenge();
			      }
	
			      super.start();
			   }
		   }

		   protected void callSameTypeForRevenge() {
		      double d = this.getFollowRange();
		      Box box = Box.method_29968(this.mob.getPos()).expand(d, 10.0D, d);
		      List<MobEntity> list = this.mob.world.getEntitiesIncludingUngeneratedChunks(this.mob.getClass(), box);
		      Iterator var5 = list.iterator();

		      while(true) {
		         MobEntity mobEntity;
		         boolean bl;
		         do {
		            do {
		               do {
		                  do {
		                     do {
		                        if (!var5.hasNext()) {
		                           return;
		                        }

		                        mobEntity = (MobEntity)var5.next();
		                     } while(this.mob == mobEntity);
		                  } while(mobEntity.getTarget() != null);
		               } while(this.mob instanceof TameableEntity && ((TameableEntity)this.mob).getOwner() != ((TameableEntity)mobEntity).getOwner());
		            } while(mobEntity.isTeammate(this.mob.getAttacker()));

		            if (this.noHelpTypes == null) {
		               break;
		            }

		            bl = false;
		            Class[] var8 = this.noHelpTypes;
		            int var9 = var8.length;

		            for(int var10 = 0; var10 < var9; ++var10) {
		               Class<?> class_ = var8[var10];
		               if (mobEntity.getClass() == class_) {
		                  bl = true;
		                  break;
		               }
		            }
		         } while(bl);

		         this.setMobEntityTarget(mobEntity, this.mob.getAttacker());
		      }
		   }

		   protected void setMobEntityTarget(MobEntity mob, LivingEntity target) {
		      mob.setTarget(target);
		   }
	}
}
