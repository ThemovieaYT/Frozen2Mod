package net.themoviea.frozeniimod.entity.ai.brain.task;

import java.util.Optional;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.task.BoneMealTask;
import net.minecraft.entity.ai.brain.task.CompositeTask;
import net.minecraft.entity.ai.brain.task.FarmerVillagerTask;
import net.minecraft.entity.ai.brain.task.FindEntityTask;
import net.minecraft.entity.ai.brain.task.FindInteractionTargetTask;
import net.minecraft.entity.ai.brain.task.FindPointOfInterestTask;
import net.minecraft.entity.ai.brain.task.FindWalkTargetTask;
import net.minecraft.entity.ai.brain.task.GatherItemsVillagerTask;
import net.minecraft.entity.ai.brain.task.GiveGiftsToHeroTask;
import net.minecraft.entity.ai.brain.task.GoToIfNearbyTask;
import net.minecraft.entity.ai.brain.task.GoToNearbyPositionTask;
import net.minecraft.entity.ai.brain.task.GoToSecondaryPositionTask;
import net.minecraft.entity.ai.brain.task.GoTowardsLookTarget;
import net.minecraft.entity.ai.brain.task.HoldTradeOffersTask;
import net.minecraft.entity.ai.brain.task.JumpInBedTask;
import net.minecraft.entity.ai.brain.task.OpenDoorsTask;
import net.minecraft.entity.ai.brain.task.RandomTask;
import net.minecraft.entity.ai.brain.task.ScheduleActivityTask;
import net.minecraft.entity.ai.brain.task.StayAboveWaterTask;
import net.minecraft.entity.ai.brain.task.Task;
import net.minecraft.entity.ai.brain.task.VillagerBreedTask;
import net.minecraft.entity.ai.brain.task.VillagerWalkTowardsTask;
import net.minecraft.entity.ai.brain.task.WaitTask;
import net.minecraft.entity.ai.brain.task.WanderAroundTask;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.village.VillagerProfession;
import net.themoviea.frozeniimod.entity.passive.ArendellianEntity;
import net.themoviea.frozeniimod.entity.passive.ElsaEntity;
import net.themoviea.frozeniimod.init.ModEntities;
import net.themoviea.themovieapi_village.village.EntityProfession;

public class ArendellianTaskListProvider {
	public static ImmutableList<Pair<Integer, ? extends Task<? super ArendellianEntity>>> createCoreTasks(EntityProfession profession, float f) {
		return ImmutableList.of(Pair.of(0, new StayAboveWaterTask(0.8F)), Pair.of(1, new WanderAroundTask()), Pair.of(5, new OpenDoorsTask()), Pair.of(6, new FindPointOfInterestTask(profession.getWorkStation(), MemoryModuleType.JOB_SITE, MemoryModuleType.POTENTIAL_JOB_SITE, true, Optional.empty())), Pair.of(10, new EntityGoToWorkTask()), Pair.of(10, new LooseEntityJobOnSiteLossTask()));
	}
	
	public static ImmutableList<Pair<Integer, ? extends Task<? super ElsaEntity>>> createCoreElsaTasks(EntityProfession profession, float f) {
		return ImmutableList.of(Pair.of(5, new OpenDoorsTask()));
	}
	
	public static ImmutableList<Pair<Integer, ? extends Task<? super ArendellianEntity>>> createWorkTask(EntityProfession profession, float f) {
	      return ImmutableList.of(Pair.of(5, new RandomTask(ImmutableList.of(Pair.of(new ArendellianWorkTask(), 7), Pair.of(new GoToIfNearbyTask(MemoryModuleType.JOB_SITE, 0.4F, 4), 2), Pair.of(new GoToNearbyPositionTask(MemoryModuleType.JOB_SITE, 0.4F, 1, 10), 5), Pair.of(new GoToSecondaryPositionTask(MemoryModuleType.SECONDARY_JOB_SITE, f, 1, 6, MemoryModuleType.JOB_SITE), 5)))), Pair.of(10, new FindInteractionTargetTask(EntityType.PLAYER, 4)), Pair.of(2, new ArendellianWalkTowardsTask(MemoryModuleType.JOB_SITE, f, 9, 100, 1200)), Pair.of(99, new ScheduleActivityTask()));
	}
	
	public static ImmutableList<Pair<Integer, ? extends Task<? super ArendellianEntity>>> createIdleTasks(EntityProfession profession, float f) {
	      return ImmutableList.of(Pair.of(2, new RandomTask(ImmutableList.of(Pair.of(FindEntityTask.create(EntityType.VILLAGER, 8, MemoryModuleType.INTERACTION_TARGET, f, 2), 2), Pair.of(new FindEntityTask(ModEntities.ARENDELLIAN, 8, null, null, MemoryModuleType.BREED_TARGET, f, 2), 1), Pair.of(FindEntityTask.create(EntityType.CAT, 8, MemoryModuleType.INTERACTION_TARGET, f, 2), 1), Pair.of(new FindWalkTargetTask(f), 1), Pair.of(new GoTowardsLookTarget(f, 2), 1), Pair.of(new JumpInBedTask(f), 1), Pair.of(new WaitTask(30, 60), 1)))), Pair.of(3, new FindInteractionTargetTask(EntityType.PLAYER, 4)), Pair.of(99, new ScheduleActivityTask()));
	}
}
