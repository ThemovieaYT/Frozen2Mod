package net.themoviea.frozeniimod.block.spirit_power_blocks;

import java.util.Random;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public class WaterSpiritPowerFluidBlock extends SpiritPowerFluidBlock {

	
	public WaterSpiritPowerFluidBlock(FlowableFluid fluid, Settings settings, boolean isUsedForAttacking, boolean isUsedForProtecting) {
		super(fluid, settings, isUsedForAttacking, isUsedForProtecting);
	}
	
	@Override
	public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		if(this.isUsedForProtecting == true) {
			world.setBlockState(pos, Blocks.AIR.getDefaultState());
		}
		super.scheduledTick(state, world, pos, random);
	}

}
