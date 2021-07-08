package net.themoviea.frozeniimod.item.powers.use_methods;

import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import net.themoviea.frozeniimod.entity.PowerElement;

public class PowerUseMethod {
	private PowerElement element;
	private int duration;
	
	
	
	public PowerUseMethod(PowerElement element) {
		this.element = element;
	}
	
	/**
	 * Gets condition if the power is really necessary to be used.
	 * If it returns false, the power will not be used and will give
	 * the player a message that it does not meet condition.
	 */
	public boolean powerUseCondition(World world, LivingEntity entity) {
		return true;
	}
	
	/**
	 * If the power use condition returns true, then this method will be
	 * run to use the power when necessary.
	 */
	public void usePower(LivingEntity entity, World world) {
		
	}
	
	@SuppressWarnings("unused")
	private int updateDuration() {
		return --this.duration;
	}
	
	public PowerElement getElement() {
		return this.element;
	}
}
