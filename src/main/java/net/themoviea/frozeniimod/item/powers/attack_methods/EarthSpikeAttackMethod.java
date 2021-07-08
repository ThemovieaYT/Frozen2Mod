package net.themoviea.frozeniimod.item.powers.attack_methods;


import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.themoviea.frozeniimod.ModNetworkingConstants;
import net.themoviea.frozeniimod.client.powerItem.PowerItemManager;
import net.themoviea.frozeniimod.entity.PowerElement;
import net.themoviea.frozeniimod.init.ModBlocks;

public class EarthSpikeAttackMethod extends PowerAttackMethod {
	
	public EarthSpikeAttackMethod(int x, int y, int z, int maxValue, Block block) {
		super(PowerElement.EARTH, x, y, z, maxValue, PowerElement.FIRE, PowerElement.FIRE, block);
	}
	
	@Override
	public void placeBlock(World world) {
		if(world.isClient) {
			PowerItemManager.sendHitBlockPos(ModNetworkingConstants.STONE_SPIKE_BLOCK_POS_NETWORK, world);
		}
		ServerPlayNetworking.registerGlobalReceiver(ModNetworkingConstants.STONE_SPIKE_BLOCK_POS_NETWORK, (server, player, handler, buf, sender) -> {
			BlockPos pos = buf.readBlockPos();
			server.execute(() -> {
				for(int i = 0; i < maxValue; ++i) {
					BlockPos newBlockPos = new BlockPos(pos.getX() + x + i, pos.getY() + 1, pos.getZ() + z);
					if(!world.isClient) {
						if(world.getBlockState(newBlockPos) == Blocks.BEDROCK.getDefaultState()) {
							break;
						}
						if(world.getBlockState(newBlockPos) != Blocks.AIR.getDefaultState()) {
						}
						world.setBlockState(newBlockPos, block.getDefaultState());
						world.getBlockTickScheduler().schedule(newBlockPos, ModBlocks.EARTH_POWER_STONE, 20 + (i * 10));
					}
				}
			});
		});
	}
	
	@Override
	public Identifier getNetworkingIdentifier() {
		return ModNetworkingConstants.STONE_SPIKE_BLOCK_POS_NETWORK;
	}
}
