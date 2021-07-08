package net.themoviea.frozeniimod.entity.ai.brain.task;

import java.util.Map;
import java.util.Optional;

import com.google.common.collect.ImmutableMap;

import net.minecraft.entity.ai.brain.MemoryModuleState;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.task.Task;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.dynamic.GlobalPos;
import net.minecraft.util.math.BlockPos;
import net.themoviea.frozeniimod.entity.passive.ArendellianEntity;
import net.themoviea.themovieapi_village.ThemovieAPIVillage;
import net.themoviea.themovieapi_village.village.EntityProfession;

public class EntityGoToWorkTask extends Task<ArendellianEntity> {

	public EntityGoToWorkTask() {
		super(ImmutableMap.of(MemoryModuleType.POTENTIAL_JOB_SITE, MemoryModuleState.VALUE_PRESENT));
	}
	
	@Override
	protected boolean shouldRun(ServerWorld world, ArendellianEntity entity) {
		BlockPos blockPos = ((GlobalPos)entity.getBrain().getOptionalMemory(MemoryModuleType.POTENTIAL_JOB_SITE).get()).getPos();
		return blockPos.isWithinDistance(entity.getPos(), 2.0d); //|| entity.isNatural()
	}
	
	@Override
	protected void run(ServerWorld world, ArendellianEntity entity, long time) {
		GlobalPos globalPos = (GlobalPos)entity.getBrain().getOptionalMemory(MemoryModuleType.POTENTIAL_JOB_SITE).get();
		entity.getBrain().forget(MemoryModuleType.POTENTIAL_JOB_SITE);
		entity.getBrain().remember(MemoryModuleType.JOB_SITE, globalPos);
		world.sendEntityStatus(entity, (byte)14);
		if(entity.getVillageEntityData().getProfession() == EntityProfession.NONE) {
			MinecraftServer minecraftServer = world.getServer();
			Optional.ofNullable(minecraftServer.getWorld(globalPos.getDimension())).flatMap((serverWorldx) -> {
				return serverWorldx.getPointOfInterestStorage().getType(globalPos.getPos());
			}).flatMap((pointOfInterestType) -> {
				return ThemovieAPIVillage.ENTITY_PROFESSION.stream().filter((entityProfession) -> {
					return entityProfession.getWorkStation() == pointOfInterestType;
				}).findFirst();
			}).ifPresent((entityProfession) -> {
				entity.setVillageEntityData(entity.getVillageEntityData().withProfession(entityProfession));
				entity.reinitializeBrain(world);
			});
		}
	}

}
