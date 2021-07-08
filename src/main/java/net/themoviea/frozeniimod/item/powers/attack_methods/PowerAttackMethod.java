package net.themoviea.frozeniimod.item.powers.attack_methods;

import java.util.List;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.themoviea.frozeniimod.client.powerItem.PowerItemManager;
import net.themoviea.frozeniimod.entity.PowerElement;
import net.themoviea.frozeniimod.entity.passive.MysticAnimalEntity;
import net.themoviea.frozeniimod.item.powers.PowerItem;

public class PowerAttackMethod {
	public int x, y, z;
	protected int maxValue;
	public Block block;
	public LivingEntity user, targetEntity;
	private PowerElement spiritPowerStrength, spiritPowerWeakness;
	private PowerElement element;
	
	public PowerAttackMethod(PowerElement element, int x, int y, int z, int maxValue, PowerElement powerStrength, PowerElement powerWeakness) {
		this(element, x, y, z, maxValue, powerStrength, powerWeakness, null);
	}
	
	public PowerAttackMethod(PowerElement element, int x, int y, int z, int maxValue, PowerElement powerStrength, PowerElement powerWeakness, Block block) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.element = element;
		this.maxValue = maxValue;
		this.spiritPowerStrength = powerStrength;
		this.spiritPowerWeakness = powerWeakness;
		this.block = block;
	}
	
	public Identifier getNetworkingIdentifier() {
		return new Identifier("frozeniimod", "networking");
	}
	
	public Text getDisplayName() {
		return new TranslatableText(this.toString());
	}
	
	public void setUser(LivingEntity entity) {
		this.user = entity;
	}
	
	public LivingEntity getUser() {
		return this.user;
	}
	
	public void setTargetEntity(LivingEntity entity) {
		this.targetEntity = entity;
	}
	
	public LivingEntity getTargetEntity() {
		return this.targetEntity;
	}
	
	public void placeBlock(World world) {
	
	}
	
	public void attackEntityRightClickBlock(World world) {
		
	}
	
	public void nonPlayerAttackEntityRightClickBlock(LivingEntity user) {
		
	}
	
	public void attackEntity(PlayerEntity user, LivingEntity entity, Hand hand) {

	}
	
	public ActionResult nonPlayerAttackEntity(LivingEntity entity) {
		if(getAttackMethodElement() == PowerElement.FIRE) {
			if(getEntityElement(entity) != PowerElement.WATER) {
				entity.setOnFireFor(5);
				return ActionResult.SUCCESS;
			} else {
				return ActionResult.FAIL;
			}
		}
		return ActionResult.FAIL;
	}
	
	public PowerElement getEntityElement(LivingEntity entity) {
		if(entity instanceof MysticAnimalEntity) {
			return ((MysticAnimalEntity)entity).getElement();
		} else {
			if(isFireTypeMob(entity)) {
				return PowerElement.FIRE;
			} else if(isEarthTypeMob(entity)) {
				return PowerElement.EARTH;
			} else if(isWindTypeMob(entity)) {
				return PowerElement.AIR;
			} else if(isWaterTypeMob(entity)) {
				return PowerElement.WATER;
			}
		}
		return PowerElement.NONE;
	}
	
	public PowerElement getAttackMethodElement() {
		return this.element;
	}
	
	public PowerElement getPowerStrength() {
		return this.spiritPowerStrength;
	}
	
	public PowerElement getPowerWeakness() {
		return this.spiritPowerWeakness;
	}
	
	public <T extends Entity> boolean isFireTypeMob(T entity) {
		return entity.getType() == EntityType.CREEPER || entity.getType() == EntityType.BLAZE
				|| entity.getType() == EntityType.GHAST || entity.getType() == EntityType.MAGMA_CUBE
				|| entity.getType() == EntityType.PIGLIN || entity.getType() == EntityType.PIGLIN_BRUTE
				|| entity.getType() == EntityType.ZOMBIFIED_PIGLIN || entity.getType() == EntityType.STRIDER
				|| entity.getType() == EntityType.WITHER_SKELETON || entity.getType() == EntityType.WITHER;
	}
	
	public <T extends Entity> boolean isEarthTypeMob(T entity) {
		return entity.getType() == EntityType.IRON_GOLEM || entity.getType() == EntityType.ENDER_DRAGON;
	}
	
	public <T extends Entity> boolean isWindTypeMob(T entity) {
		return entity.getType() == EntityType.BAT || entity.getType() == EntityType.PHANTOM
				|| entity.getType() == EntityType.VEX;
	}
	
	public <T extends Entity> boolean isWaterTypeMob(T entity) {
		return entity.getType() == EntityType.DOLPHIN || entity.getType() == EntityType.TROPICAL_FISH
				|| entity.getType() == EntityType.SQUID || entity.getType() == EntityType.DROWNED 
				|| entity.getType() == EntityType.GUARDIAN || entity.getType() == EntityType.ELDER_GUARDIAN;
	}
}
