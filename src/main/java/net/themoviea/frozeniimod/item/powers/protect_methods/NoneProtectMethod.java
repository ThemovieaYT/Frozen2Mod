package net.themoviea.frozeniimod.item.powers.protect_methods;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;
import net.themoviea.frozeniimod.init.ModItems;
import net.themoviea.frozeniimod.item.powers.PowerItem;

public class NoneProtectMethod extends PowerProtectMethod {

	public NoneProtectMethod() {
		super(ModItems.NONE);
	}
	
	@Override
	public boolean powerProtectCondition(LivingEntity entity) {
		return false;
	}
	
	@Override
	public void protectFromPower(PlayerEntity entity, World world) {

	}

}
