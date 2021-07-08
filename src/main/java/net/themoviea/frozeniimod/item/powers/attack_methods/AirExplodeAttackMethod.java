package net.themoviea.frozeniimod.item.powers.attack_methods;

import java.util.List;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.themoviea.frozeniimod.ModNetworkingConstants;
import net.themoviea.frozeniimod.client.powerItem.PowerItemManager;
import net.themoviea.frozeniimod.entity.PowerElement;
import net.themoviea.frozeniimod.init.ModDamageSources;
import net.themoviea.frozeniimod.init.ModItems;

public class AirExplodeAttackMethod extends PowerAttackMethod {

	public AirExplodeAttackMethod() {
		super(PowerElement.AIR, 0, 0, 0, 1, PowerElement.FIRE, PowerElement.WATER, null);
	}
	
	@Override
	public void attackEntityRightClickBlock(World world) {
		if(world.isClient) {
			PowerItemManager.sendHitBlockPos(ModNetworkingConstants.AIR_EXP_BLOCK_POS_NETWORK, world);
		}
		ServerPlayNetworking.registerGlobalReceiver(ModNetworkingConstants.AIR_EXP_BLOCK_POS_NETWORK, (server, player, handler, buf, sender) -> {
			BlockPos pos = buf.readBlockPos();
			server.execute(() -> {
				List<Entity> list = world.getOtherEntities(player, new Box((double)pos.getX()-6, (double)pos.getY()-6, (double)pos.getZ()-6, (double)pos.getX()+6, (double)pos.getY()+6, (double)pos.getZ()+6));
				for(int i = 0; i < list.size(); ++i) {
					Entity entity = (Entity)list.get(i);
						double x = entity.getX() - pos.getX();
						double y = entity.getY() - pos.getY() - 0.5d;
						double z = entity.getZ() - pos.getZ();
						entity.damage(ModDamageSources.SPIRIT_POWER, 1.5f);
						entity.setVelocity(entity.getVelocity().add(player.getMovementSpeed() + x, y, player.getMovementSpeed() + z));
						entity.handleAttack(player);
				}
			});
		});
	}
	
	@Override
	public void nonPlayerAttackEntityRightClickBlock(LivingEntity user) {
		World world = user.getEntityWorld();
		BlockPos pos = new BlockPos(user.getX(), user.getY() - 1, user.getZ());
		List<Entity> list = world.getOtherEntities(user, new Box((double)pos.getX()-6, (double)pos.getY()-6, (double)pos.getZ()-6, (double)pos.getX()+6, (double)pos.getY()+6, (double)pos.getZ()+6));
		for(int i = 0; i < list.size(); ++i) {
			Entity entity = (Entity)list.get(i);
				/*double x = entity.getX() - pos.getX();
				double y = entity.getY() - pos.getY() - 0.5d;
				double z = entity.getZ() - pos.getZ();*/
				double x = (entity.getX() - 10d) - pos.getX();
				double y = entity.getY() - pos.getY() - 0.5d;
				double z = (entity.getZ() - 10d) - pos.getZ();
				entity.damage(ModDamageSources.SPIRIT_POWER, 1.5f);
				entity.setVelocity(entity.getVelocity().add(x, y, z));
				entity.handleAttack(user);
		}
	}
	
	@Override
	public Identifier getNetworkingIdentifier() {
		return ModNetworkingConstants.AIR_EXP_BLOCK_POS_NETWORK;
	}

}
