package net.themoviea.frozeniimod.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.PressurePlateBlock;

public class FrozenIIModPressurePlateBlock extends PressurePlateBlock
{
	public FrozenIIModPressurePlateBlock(ActivationRule type, Settings settings) 
	{
		super(type, settings);
		//this.setDefaultState((BlockState)((BlockState)this.stateManager.getDefaultState()).with(POWERED, false));
	}
}
