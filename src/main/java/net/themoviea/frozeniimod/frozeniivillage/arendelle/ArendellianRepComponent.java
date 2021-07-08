package net.themoviea.frozeniimod.frozeniivillage.arendelle;

import net.minecraft.nbt.CompoundTag;
import net.themoviea.frozeniimod.frozeniivillage.Frozen2ReputationComponent;

public class ArendellianRepComponent implements Frozen2ReputationComponent
{
	private int reputation;
	
	public ArendellianRepComponent()
	{
		this.reputation = 0;
	}
	
	@Override
	public void setReputation(int reputation) 
	{
		this.reputation = reputation;	
	}
	
	public void increase(int amount)
	{
		checkReputation();
		this.reputation += amount;
	}
	
	public void decrease(int amount)
	{
		checkReputation();
		this.reputation -= amount;
	}
	
	public int getReputation()
	{
		return this.reputation;
	}
	
	public void checkReputation()
	{
		if(this.reputation < -24)
		{
			this.reputation = -15;
		}
		if(this.reputation > 24)
		{
			this.reputation = 15;
		}
	}
	/*
	@Override
	public void fromTag(CompoundTag tag) 
	{
		this.reputation = tag.getInt("reputation");
	}

	@Override
	public CompoundTag toTag(CompoundTag tag) 
	{
		tag.putInt("reputation", this.reputation);
		return tag;
	}*/

	@Override
	public void readFromNbt(CompoundTag tag) {
		this.reputation = tag.getInt("reputation");
	}

	@Override
	public void writeToNbt(CompoundTag tag) {
		tag.putInt("reputation", this.reputation);
	}
}
