package net.themoviea.frozeniimod.entity.projectile;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.themoviea.frozeniimod.init.ModDamageSources;
import net.themoviea.frozeniimod.init.ModEffects;
import net.themoviea.frozeniimod.init.ModEntities;
import net.themoviea.frozeniimod.init.ModItems;

public class IcePowerEntity extends ThrownItemEntity
{
	public IcePowerEntity(EntityType<? extends IcePowerEntity> entityType, World world)
	{
		super(entityType, world);
	}
	
	public IcePowerEntity(World world, LivingEntity owner)
	{
		super(ModEntities.ICE_POWER, owner, world);
	}
	
	public IcePowerEntity(World world, double x, double y, double z) 
	{
	      super(ModEntities.ICE_POWER, x, y, z, world);
    }
	
	@Override
	protected Item getDefaultItem() 
	{
		return ModItems.ICE_POWER;
	}
	
	@Environment(EnvType.CLIENT)
	   private ParticleEffect getParticleParameters() {
	      ItemStack itemStack = this.getItem();
	      return (ParticleEffect)(itemStack.isEmpty() ? ParticleTypes.ITEM_SNOWBALL : new ItemStackParticleEffect(ParticleTypes.ITEM, itemStack));
	   }

	   @Environment(EnvType.CLIENT)
	   public void handleStatus(byte status) {
	      if (status == 3) {
	         ParticleEffect particleEffect = this.getParticleParameters();

	         for(int i = 0; i < 8; ++i) {
	            this.world.addParticle(particleEffect, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
	         }
	      }

	   }
	
	@Override
	protected void onEntityHit(EntityHitResult entityHitResult) 
	{
		super.onEntityHit(entityHitResult);
		if (!this.world.isClient)
		{
			Entity entity = entityHitResult.getEntity();
			Entity ownerEntity = this.getOwner();
			boolean bl2;
			if (ownerEntity instanceof LivingEntity)
			{
				LivingEntity livingEntity = (LivingEntity)ownerEntity;
				bl2 = entity.damage(ModDamageSources.elsaMagic(this, livingEntity), 4.0F);
				if (bl2)
				{
					if (entity.isAlive())
					{
						this.dealDamage(livingEntity, entity);
					} else
					{
						livingEntity.heal(2.5F);
					}
				}
			} else
			{
				bl2 = entity.damage(ModDamageSources.ELSA_MAGIC, 4.0F);
			}
			
			if (bl2 && entity instanceof LivingEntity)
			{
				int i = 0;
				if (this.world.getDifficulty() == Difficulty.EASY)
				{
					i = 5;
				} else if (this.world.getDifficulty() == Difficulty.NORMAL)
				{
					i = 10;
				} else if (this.world.getDifficulty() == Difficulty.HARD)
				{
					i = 30;
				}
				
				if (i > 0)
				{
					((LivingEntity)entity).addStatusEffect(new StatusEffectInstance(ModEffects.FROST_BITE, 15 * i, 1));
				}
			}
		}
	}
	
	@Override
	protected void onCollision(HitResult hitResult) 
	{
		super.onCollision(hitResult);
		if (!this.world.isClient)
		{
			this.world.sendEntityStatus(this, (byte)3);
			this.remove();
		}
	}
}
