package net.themoviea.frozeniimod.block.spirit_power_blocks;

import java.util.Random;

import org.apache.logging.log4j.Level;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.themoviea.frozeniimod.Frozen2Mod;
import net.themoviea.frozeniimod.init.ModBlocks;
import net.themoviea.frozeniimod.init.ModItems;
import net.themoviea.frozeniimod.item.powers.attack_methods.WaterDrownMethod;

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
