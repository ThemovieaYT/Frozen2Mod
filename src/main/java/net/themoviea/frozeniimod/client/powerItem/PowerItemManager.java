package net.themoviea.frozeniimod.client.powerItem;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.themoviea.frozeniimod.ModNetworkingConstants;
import net.themoviea.frozeniimod.init.ModBlocks;

@Environment(EnvType.CLIENT)
public class PowerItemManager {
	public static void sendHitBlockPos(Identifier identifier, World world) {
		MinecraftClient client = MinecraftClient.getInstance();
		Entity entity = client.getCameraEntity();
		if(entity != null && client.world != null) {
			HitResult hit = entity.raycast(128.0f, client.getTickDelta(), false);
			if(hit.getType() == HitResult.Type.BLOCK) {
				BlockPos pos = ((BlockHitResult) hit).getBlockPos();
				
				PacketByteBuf buf = PacketByteBufs.create();
				buf.writeBlockPos(pos);
				ClientPlayNetworking.send(identifier, buf);
			}
		}
	}
}
