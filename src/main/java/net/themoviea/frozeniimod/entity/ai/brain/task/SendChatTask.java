package net.themoviea.frozeniimod.entity.ai.brain.task;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.ai.brain.MemoryModuleState;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.task.Task;
import net.minecraft.server.world.ServerWorld;
import net.themoviea.frozeniimod.entity.passive.ArendellianEntity;
import net.themoviea.themovieapi_village.village.EntityProfession;

public class SendChatTask extends Task<ArendellianEntity> {

	public SendChatTask() {
		super(ImmutableMap.of(MemoryModuleType.DUMMY, MemoryModuleState.VALUE_PRESENT));
	}

	@Override
	protected boolean shouldRun(ServerWorld world, ArendellianEntity entity) {
		if(entity.getVillageEntityData().getProfession() == EntityProfession.NONE) {
			return true;
		}
		
		return false;
	}
	
	protected void run(ServerWorld world, ArendellianEntity entity, long time) {
		MinecraftClient.getInstance().player.sendChatMessage("OWOOWOWOWOW!!!!!");
	}
}
