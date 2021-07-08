package net.themoviea.frozeniimod.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.themoviea.frozeniimod.entity.passive.ArendellianEntity;
import net.themoviea.themovieapi_village.village.EntityProfession;

public class TokenItem extends Item {
	private EntityProfession profession;

	public TokenItem(Settings settings, EntityProfession profession) {
		super(settings);
		this.profession = profession;
	}

	@Override
	public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
		if(entity instanceof ArendellianEntity) {
			ArendellianEntity arendellian = (ArendellianEntity)entity;
			arendellian.setVillageEntityData(arendellian.getVillageEntityData().withProfession(profession));
			stack.decrement(1);
			return ActionResult.SUCCESS;
		}
		return ActionResult.FAIL;
	}
}
