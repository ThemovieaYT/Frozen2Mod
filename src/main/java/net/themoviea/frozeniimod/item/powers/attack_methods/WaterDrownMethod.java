package net.themoviea.frozeniimod.item.powers.attack_methods;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.themoviea.frozeniimod.entity.PowerElement;
import net.themoviea.frozeniimod.init.ModBlocks;
import net.themoviea.frozeniimod.init.ModItems;

public class WaterDrownMethod extends PowerAttackMethod {

	public WaterDrownMethod(int x, int y, int z, int maxValue) {
		super(PowerElement.WATER, x, y, z, maxValue, PowerElement.FIRE, PowerElement.EARTH);
	}

	@Override
	public void attackEntity(PlayerEntity user, LivingEntity entity, Hand hand) {
		World world = user.getEntityWorld();
		BlockState blockState = ModBlocks.WATER_SPIRIT_POWER_FLUID.getDefaultState();
		BlockPos pos = entity.getBlockPos();
		BlockPos waterPos1 = new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ());
		BlockPos waterPos2 = new BlockPos(pos.getZ(), pos.getY() + 2, pos.getZ());
		world.setBlockState(waterPos1, blockState);
		world.setBlockState(waterPos2, blockState);
		world.getBlockTickScheduler().schedule(waterPos1, ModBlocks.WATER_SPIRIT_POWER_FLUID, 20);
		world.getBlockTickScheduler().schedule(waterPos2, ModBlocks.WATER_SPIRIT_POWER_FLUID, 200);
		this.setTargetEntity(entity);
	}
}
