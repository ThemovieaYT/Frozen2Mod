package net.themoviea.frozeniimod.item.powers.attack_methods;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.themoviea.frozeniimod.entity.PowerElement;
import net.themoviea.frozeniimod.init.ModBlocks;

public class TsunamiAttackMethod extends PowerAttackMethod {

	public TsunamiAttackMethod(int x, int y, int z, int maxValue, Block block) {
		super(PowerElement.WATER, x, y, z, maxValue, PowerElement.FIRE, PowerElement.EARTH, block);
	}

	@Override
	public void attackEntityRightClickBlock(World world) {
		BlockState blockState = ModBlocks.WATER_SPIRIT_POWER_FLUID.getDefaultState();
		BlockPos pos = user.getBlockPos();
		
	}
}
