package net.themoviea.frozeniimod.entity.ai.brain.task;

import com.google.common.collect.ImmutableMap;

import net.minecraft.entity.EntityData;
import net.minecraft.entity.ai.brain.MemoryModuleState;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.task.Task;
import net.minecraft.server.world.ServerWorld;
import net.themoviea.frozeniimod.entity.passive.ArendellianEntity;
import net.themoviea.themovieapi_village.village.EntityProfession;
import net.themoviea.themovieapi_village.village.VillageEntityData;

public class LooseEntityJobOnSiteLossTask extends Task<ArendellianEntity> {

	public LooseEntityJobOnSiteLossTask() {
		super(ImmutableMap.of(MemoryModuleType.JOB_SITE, MemoryModuleState.VALUE_ABSENT));
	}
	
	@Override
	protected boolean shouldRun(ServerWorld world, ArendellianEntity entity) {
		VillageEntityData villageEntityData = entity.getVillageEntityData();
		return villageEntityData.getProfession() != EntityProfession.NONE;
	}
	
	@Override
	protected void run(ServerWorld world, ArendellianEntity entity, long time) {
		entity.setVillageEntityData(entity.getVillageEntityData().withProfession(EntityProfession.NONE));
		entity.reinitializeBrain(world);
	}
}
