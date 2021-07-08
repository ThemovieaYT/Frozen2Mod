package net.themoviea.frozeniimod.item.powers.attack_methods;

import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.themoviea.frozeniimod.entity.PowerElement;
import net.themoviea.frozeniimod.init.ModItems;
import net.themoviea.frozeniimod.item.powers.PowerItem;

public class SetOnFireAttackMethod extends PowerAttackMethod {

	public SetOnFireAttackMethod(int x, int y, int z, int maxValue, Block block) {
		super(PowerElement.FIRE, x, y, z, maxValue, PowerElement.WATER, PowerElement.AIR, block);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void attackEntity(PlayerEntity user, LivingEntity entity, Hand hand) {
		if(this.getEntityElement(entity) == this.getPowerWeakness()) {
			entity.setOnFireFor(5);
		} else if(this.getEntityElement(entity) != PowerElement.FIRE) {
			entity.setOnFireFor(20);
		}
	}

}
