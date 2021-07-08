package net.themoviea.frozeniimod.entity.passive;

import java.util.UUID;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Saddleable;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.Angerable;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.themoviea.frozeniimod.entity.PowerElement;

public class NokkEntity extends MysticAnimalEntity implements Angerable, RangedAttackMob, Saddleable {
	private static final TrackedData<Byte> NOKK_FLAGS;
	protected float jumpStrength;
	
	public NokkEntity(EntityType<? extends MysticAnimalEntity> entityType, World world) {
		super(entityType, world);
	}
	
	@Override
	protected void initDataTracker() {
		super.initDataTracker();
		this.dataTracker.startTracking(NOKK_FLAGS, (byte)0);
	}
	
	protected boolean getNokkFlag(int bitmask) {
		return ((Byte)this.dataTracker.get(NOKK_FLAGS) & bitmask) != 0;
	}
	
	protected void setNokkFlag(int bitmask, boolean flag) {
		byte b = (Byte)this.dataTracker.get(NOKK_FLAGS);
		if(flag) {
			this.dataTracker.set(NOKK_FLAGS, (byte)(b | bitmask));
		} else {
			this.dataTracker.set(NOKK_FLAGS, (byte)(b &~ bitmask));
		}
	}
	
	@Override
	public PowerElement getElement() {
		return PowerElement.WATER;
	}
	
	@Override
	public boolean canBeSaddled() {
		return this.isAlive();
	}

	@Override
	public void saddle(SoundCategory sound) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isSaddled() {
		// TODO Auto-generated method stub
		return false;
	}
	
	private boolean isOnWater() {
		BlockPos pos = new BlockPos(this.getBlockPos().getX(), this.getBlockPos().getY() - 1, this.getBlockPos().getZ());
		if(world.getBlockState(pos) == Blocks.WATER.getDefaultState()) {
			return true;
		} else {
			return false;
		}
	}
	
	private float getJumpStrength() {
		return this.jumpStrength;
	}
	
	@Override
	public void tickMovement() {
		if(this.isOnWater()) {
			this.setVelocity(this.getVelocity().multiply(1.0D, 0, 1.0D));
		}
	}
	
	@Override
	public void travel(Vec3d movementInput) {
		if (this.isAlive()) {
	         if (this.hasPassengers() && this.canBeControlledByRider() && this.isSaddled()) {
	            LivingEntity livingEntity = (LivingEntity)this.getPrimaryPassenger();
	            this.yaw = livingEntity.yaw;
	            this.prevYaw = this.yaw;
	            this.pitch = livingEntity.pitch * 0.5F;
	            this.setRotation(this.yaw, this.pitch);
	            this.bodyYaw = this.yaw;
	            this.headYaw = this.bodyYaw;
	            float f = livingEntity.sidewaysSpeed * 0.5F;
	            float g = livingEntity.forwardSpeed;
	            if (g <= 0.0F) {
	               g *= 0.25F;
	            }

	            if (this.onGround || this.isOnWater() && this.jumpStrength == 0.0F && !this.jumping) {
	               f = 0.0F;
	               g = 0.0F;
	            }

	            if (this.jumpStrength > 0.0F && (this.onGround || this.isOnWater())) {
	               double d = this.getJumpStrength() * (double)this.jumpStrength * (double)this.getJumpVelocityMultiplier();
	               double h;
	               if (this.hasStatusEffect(StatusEffects.JUMP_BOOST)) {
	                  h = d + (double)((float)(this.getStatusEffect(StatusEffects.JUMP_BOOST).getAmplifier() + 1) * 0.1F);
	               } else {
	                  h = d;
	               }

	               Vec3d vec3d = this.getVelocity();
	               this.setVelocity(vec3d.x, h, vec3d.z);
	               this.velocityDirty = true;
	               if (g > 0.0F) {
	                  float i = MathHelper.sin(this.yaw * 0.017453292F);
	                  float j = MathHelper.cos(this.yaw * 0.017453292F);
	                  this.setVelocity(this.getVelocity().add((double)(-0.4F * i * this.jumpStrength), 0.0D, (double)(0.4F * j * this.jumpStrength)));
	               }

	               this.jumpStrength = 0.0F;
	            }

	            this.flyingSpeed = this.getMovementSpeed() * 0.1F;
	            if (this.isLogicalSideForUpdatingMovement()) {
	               this.setMovementSpeed((float)this.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED));
	               super.travel(new Vec3d((double)f, movementInput.y, (double)g));
	            } else if (livingEntity instanceof PlayerEntity) {
	               this.setVelocity(Vec3d.ZERO);
	            }

	            if (this.onGround) {
	               this.jumpStrength = 0.0F;
	            }

	            this.method_29242(this, false);
	         } else {
	            this.flyingSpeed = 0.02F;
	            super.travel(movementInput);
	         }
	      }
	}
	
	@Override
	public void attack(LivingEntity target, float pullProgress) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getAngerTime() {
		// TODO Auto-generated method stub
		return 0;
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
	
	static {
		NOKK_FLAGS = DataTracker.registerData(NokkEntity.class, TrackedDataHandlerRegistry.BYTE);
	}
}
