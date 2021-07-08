package net.themoviea.frozeniimod.block.spirit_power_blocks;

import net.minecraft.block.Block;

public class SpiritPowerBlock extends Block {
	public boolean isUsedForAttacking, isUsedForProtecting;
	public SpiritPowerBlock(Settings settings, boolean isUsedForAttacking, boolean isUsedForProtecting) {
		super(settings);
		this.isUsedForAttacking = isUsedForAttacking;
		this.isUsedForProtecting = isUsedForProtecting;
	}

}
