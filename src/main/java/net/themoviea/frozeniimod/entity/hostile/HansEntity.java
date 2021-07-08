package net.themoviea.frozeniimod.entity.hostile;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.AttackGoal;
import net.minecraft.entity.ai.goal.FollowTargetGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.stat.Stats;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.themoviea.frozeniimod.entity.passive.AbstractCommunicationEntity;
import net.themoviea.frozeniimod.entity.passive.AnnaEntity;
import net.themoviea.frozeniimod.entity.passive.ElsaEntity;
import net.themoviea.frozeniimod.item.powers.PowerItem;
import net.themoviea.frozeniimod.screen.ElsaCommunicationScreenHandler;

public class HansEntity extends AbstractCommunicationEntity {

	protected HansEntity(EntityType<? extends AbstractCommunicationEntity> entityType, World world) {
		super(entityType, world);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initGoals() {
		this.goalSelector.add(2, new AttackGoal(this));
		this.targetSelector.add(2, new FollowTargetGoal<>(this, ElsaEntity.class, true));
		this.targetSelector.add(2, new FollowTargetGoal<>(this, AnnaEntity.class, true));
	}
	
	@Override
	protected ActionResult interactMob(PlayerEntity player, Hand hand) {
		if(!this.hasPerson()) {
			if(hand == Hand.MAIN_HAND) {
				player.incrementStat(Stats.TALKED_TO_VILLAGER);
			}
			
			if(!this.world.isClient) {
				if(!(player.getItemsHand() instanceof PowerItem)) {
					this.setCurrentPerson(player);
					player.openHandledScreen(this.createScreenHandlerFactory());
				}
			}
			return ActionResult.success(this.world.isClient);
		} else {
			return ActionResult.PASS;
		}
	}
	
	public NamedScreenHandlerFactory createScreenHandlerFactory() {
		return new SimpleNamedScreenHandlerFactory((i, playerInventory, playerEntity) -> {
			return new ElsaCommunicationScreenHandler(i, playerInventory);
		}, new TranslatableText("gui.hans_chat"));
	}
	
	public static DefaultAttributeContainer.Builder createHansAttributes()
    {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 70D);
    }
}
