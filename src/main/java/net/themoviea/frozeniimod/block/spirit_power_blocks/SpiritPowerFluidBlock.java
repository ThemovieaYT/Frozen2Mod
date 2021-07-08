package net.themoviea.frozeniimod.block.spirit_power_blocks;

import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.FlowableFluid;
import net.themoviea.frozeniimod.item.powers.PowerItem;

public class SpiritPowerFluidBlock extends FluidBlock {
	public boolean isUsedForAttacking, isUsedForProtecting;
	public PowerItem item;
	protected SpiritPowerFluidBlock(FlowableFluid fluid, Settings settings, boolean isUsedForAttacking, boolean isUsedForProtecting) {
		super(fluid, settings);
		this.isUsedForAttacking = isUsedForAttacking;
		this.isUsedForProtecting = isUsedForProtecting;
	}

}
