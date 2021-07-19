package net.themoviea.frozeniimod.block.spirit_power_blocks;

import java.util.Random;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public class EarthPowerStoneBlock extends SpiritPowerBlock {
	
	public EarthPowerStoneBlock(boolean isUsedForAttacking, boolean isUsedForProtecting) {
		super(AbstractBlock.Settings.of(Material.STONE, MaterialColor.STONE).requiresTool().strength(1.5F, 6.0F), isUsedForAttacking, isUsedForProtecting);
	}
	
	@Override
	public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		if(this.isUsedForAttacking = true) {
			world.setBlockState(pos, Blocks.AIR.getDefaultState());			
		}
		super.scheduledTick(state, world, pos, random);
	}
}
