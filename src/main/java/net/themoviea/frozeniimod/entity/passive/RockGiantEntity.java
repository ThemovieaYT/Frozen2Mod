package net.themoviea.frozeniimod.entity.passive;

import java.util.UUID;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.Angerable;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.World;
import net.themoviea.frozeniimod.entity.PowerElement;

public class RockGiantEntity extends MysticAnimalEntity implements Angerable {
	/*
	 * Rock Giant Entity Note:
	 * What should it do?
	 * 1. Throw rocks to person when the person angers them
	 * 2. Sleep in Enchanted Forest Lake XL Biome
	 * 3. Walk Around near the Lake XL Biome and leave giant footprints (But not too often because it might cause bad things)
	 * 
	 * Where does it spawn?
	 * In the Enchanted Forest Lake XL Biome, duh
	 * 
	 * Alright, i think that's all for it. Time to code!
	 */

	protected RockGiantEntity(EntityType<? extends MysticAnimalEntity> entityType, World world) {
		super(entityType, world);
	}
	
	@Override
	protected void initGoals() {
		//this.goalSelector.add(0, new );
	}
	
	@Override
	public PowerElement getElement() {
		return PowerElement.EARTH;
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

}
