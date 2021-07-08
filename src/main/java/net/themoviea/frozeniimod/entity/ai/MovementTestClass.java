package net.themoviea.frozeniimod.entity.ai;

import java.util.concurrent.ThreadLocalRandom;

import net.minecraft.block.Blocks;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MovementTestClass {

	public static BlockPos findRandomBlockTarget(MobEntity entity, boolean includeY, int num) {
		double randY;
		double randX = ThreadLocalRandom.current().nextDouble(entity.getX() - num, entity.getX() + num);
		if(includeY == true) {
			randY = ThreadLocalRandom.current().nextDouble(entity.getY() - num, entity.getY() + num);
		} else {
			randY = entity.getY();
		}
		double randZ = ThreadLocalRandom.current().nextDouble(entity.getZ() - num, entity.getZ() + num);
		return new BlockPos(randX, randY, randZ);
	}
	
	public static void startMovingToPos(MobEntity entity, World world, BlockPos pos, double speed) {
		if(world.getBlockState(pos) == Blocks.AIR.getDefaultState()) {
			entity.getNavigation().startMovingTo(pos.getX(), pos.getY(), pos.getZ(), speed);
		} else {

		}
	}

}
