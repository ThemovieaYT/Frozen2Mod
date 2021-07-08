package net.themoviea.frozeniimod.entity.object;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Packet;
import net.minecraft.world.World;
import net.themoviea.frozeniimod.init.ModEntities;

public class BoulderEntity extends Entity {

	public BoulderEntity(EntityType<?> type, World world) {
		super(type, world);
		// TODO Auto-generated constructor stub
	}
	
	public BoulderEntity(World world) {
		super(ModEntities.BOULDER_ENTITY, world);
	}

	@Override
	protected void initDataTracker() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void readCustomDataFromTag(CompoundTag tag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void writeCustomDataToTag(CompoundTag tag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Packet<?> createSpawnPacket() {
		// TODO Auto-generated method stub
		return null;
	}

}
