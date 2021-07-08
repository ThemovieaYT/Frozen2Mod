package net.themoviea.frozeniimod.entity;

import org.jetbrains.annotations.Nullable;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public interface Communicator 
{
	void setCurrentPerson(@Nullable PlayerEntity person);
	
	@Nullable
	PlayerEntity getCurrentPerson();
	
	World getCommunicationWorld();
}
