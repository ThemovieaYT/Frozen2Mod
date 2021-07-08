package net.themoviea.frozeniimod.item.powers.protect_methods;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;
import net.themoviea.frozeniimod.item.powers.PowerItem;
import net.themoviea.frozeniimod.item.powers.attack_methods.PowerAttackMethod;

public class PowerProtectMethod {
	private PowerItem power;
	
	public PowerProtectMethod(PowerItem power) {
		this.power = power;
	}
	
	/**
	 * This method checks if the player is currently getting attacked.
	 * @param player
	 * @return
	 */
	public boolean powerProtectCondition(LivingEntity entity) {
		return true;
	}
	
	/**
	 * This method is used when opponent's power is currently attacking the player.
	 * The power protect condition must be true in order to protect the player.
	 * @param player
	 * @param world
	 */
	public void protectFromPower(PlayerEntity entity, World world) {
		
	}
	
	public void entityProtectFromPower(LivingEntity entity, World world) {
		
	}

	public PowerItem getPower() {
		return power;
	}
}
