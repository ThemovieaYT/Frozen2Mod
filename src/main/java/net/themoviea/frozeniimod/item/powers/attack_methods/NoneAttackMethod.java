package net.themoviea.frozeniimod.item.powers.attack_methods;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.themoviea.frozeniimod.entity.PowerElement;
import net.themoviea.frozeniimod.init.ModItems;
import net.themoviea.frozeniimod.item.powers.PowerItem;

public class NoneAttackMethod extends PowerAttackMethod {

	public NoneAttackMethod() {
		super(PowerElement.NONE, 0, 0, 0, 0, PowerElement.NONE, PowerElement.NONE);
	}
	
	@Override
	public void placeBlock(World world) {
		
	}
	
	@Override
	public void attackEntity(PlayerEntity user, LivingEntity entity, Hand hand) {
		
	}

}
