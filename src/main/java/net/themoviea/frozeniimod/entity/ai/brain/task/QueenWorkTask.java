package net.themoviea.frozeniimod.entity.ai.brain.task;

import java.util.Map;

import net.minecraft.entity.ai.brain.MemoryModuleState;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.task.Task;
import net.themoviea.frozeniimod.entity.passive.ElsaEntity;

public class QueenWorkTask extends Task<ElsaEntity> {

	public QueenWorkTask(Map<MemoryModuleType<?>, MemoryModuleState> requiredMemoryState) {
		super(requiredMemoryState);
	}
	
}
