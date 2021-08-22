package net.themoviea.frozeniimod.entity.ai.brain.task;

import java.util.Optional;

import com.google.common.collect.ImmutableMap;

import net.minecraft.entity.ai.brain.BlockPosLookTarget;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.MemoryModuleState;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.task.Task;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.dynamic.GlobalPos;
import net.themoviea.frozeniimod.entity.passive.ArendellianEntity;

public class ArendellianWorkTask extends Task<ArendellianEntity> {
	   private long lastCheckedTime;

	   public ArendellianWorkTask() {
	      super(ImmutableMap.of(MemoryModuleType.JOB_SITE, MemoryModuleState.VALUE_PRESENT, MemoryModuleType.LOOK_TARGET, MemoryModuleState.REGISTERED));
	   }

	   protected boolean shouldRun(ServerWorld serverWorld, VillagerEntity villagerEntity) {
	      if (serverWorld.getTime() - this.lastCheckedTime < 300L) {
	         return false;
	      } else if (serverWorld.random.nextInt(2) != 0) {
	         return false;
	      } else {
	         this.lastCheckedTime = serverWorld.getTime();
	         GlobalPos globalPos = (GlobalPos)villagerEntity.getBrain().getOptionalMemory(MemoryModuleType.JOB_SITE).get();
	         return globalPos.getDimension() == serverWorld.getRegistryKey() && globalPos.getPos().isWithinDistance(villagerEntity.getPos(), 1.73D);
	      }
	   }

	   protected void run(ServerWorld serverWorld, ArendellianEntity villagerEntity, long l) {
		   System.out.println("Currently Working!");
	      //Brain<ArendellianEntity> brain = villagerEntity.getBrain();
	      //brain.remember(MemoryModuleType.LAST_WORKED_AT_POI, l);
	     // brain.getOptionalMemory(MemoryModuleType.JOB_SITE).ifPresent((globalPos) -> {
	      //   brain.remember(MemoryModuleType.LOOK_TARGET, (new BlockPosLookTarget(globalPos.getPos())));
	     // });
	      //this.performAdditionalWork(serverWorld, villagerEntity);

	   }

	   protected void performAdditionalWork(ServerWorld world, ArendellianEntity villagerEntity) {
	   }

	   protected boolean shouldKeepRunning(ServerWorld serverWorld, VillagerEntity villagerEntity, long l) {
	      Optional<GlobalPos> optional = villagerEntity.getBrain().getOptionalMemory(MemoryModuleType.JOB_SITE);
	      if (!optional.isPresent()) {
	         return false;
	      } else {
	         GlobalPos globalPos = (GlobalPos)optional.get();
	         return globalPos.getDimension() == serverWorld.getRegistryKey() && globalPos.getPos().isWithinDistance(villagerEntity.getPos(), 1.73D);
	      }
	   }
	}
