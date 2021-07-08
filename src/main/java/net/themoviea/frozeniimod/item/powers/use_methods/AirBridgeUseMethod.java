package net.themoviea.frozeniimod.item.powers.use_methods;

import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.themoviea.frozeniimod.entity.PowerElement;

public class AirBridgeUseMethod extends PowerUseMethod {

	public AirBridgeUseMethod() {
		super(PowerElement.AIR);
	}

	@Override
	public boolean powerUseCondition(World world, LivingEntity entity) {
		BlockPos pos = entity.getBlockPos();
		//BlockPos airPos = new BlockPos();
		if(entity.world.getBlockState(pos) == Blocks.AIR.getDefaultState()) {
			return true;
		}
		
		return false;
	}
	
	@Override
	public void usePower(LivingEntity entity, World world) {
		if(entity.getHorizontalFacing() == Direction.NORTH) {
			
		}
	}
}
