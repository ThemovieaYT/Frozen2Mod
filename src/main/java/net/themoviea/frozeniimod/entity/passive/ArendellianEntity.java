package net.themoviea.frozeniimod.entity.passive;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.BiPredicate;

import org.apache.logging.log4j.Logger;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.Dynamic;

import dev.onyxstudios.cca.api.v3.component.ComponentProvider;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.Activity;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.Brain.Profile;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.ai.brain.sensor.SensorType;
import net.minecraft.entity.ai.brain.task.VillagerTaskListProvider;
import net.minecraft.entity.ai.pathing.MobNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.dynamic.GlobalPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOfferList;
import net.minecraft.village.VillagerData;
import net.minecraft.world.World;
import net.minecraft.world.poi.PointOfInterestType;
import net.themoviea.frozeniimod.entity.ai.brain.ArendellianSchedule;
import net.themoviea.frozeniimod.entity.ai.brain.task.ArendellianTaskListProvider;
import net.themoviea.frozeniimod.frozeniivillage.arendelle.ArendellianRepComponent;
import net.themoviea.frozeniimod.init.ModComponents;
import net.themoviea.frozeniimod.init.ModItems;
import net.themoviea.frozeniimod.init.ModProfessions;
import net.themoviea.frozeniimod.init.ModProfessions.GetNeededItemFactory;
import net.themoviea.themovieapi_village.entity.data.TrackedDataHandlerRegister;
import net.themoviea.themovieapi_village.village.EntityProfession;
import net.themoviea.themovieapi_village.village.ThemovieAPITradeOffers;
import net.themoviea.themovieapi_village.village.ThemovieAPITradeOffers.Factory;
import net.themoviea.themovieapi_village.village.VillageEntityData;
import net.themoviea.themovieapi_village.village.VillageEntityDataContainer;
import net.themoviea.themovieapi_village.village.VillageEntityType;
import net.themoviea.themovieapi_village.ThemovieAPIVillage;

public class ArendellianEntity extends AbstractCommunicationTraderEntity implements VillageEntityDataContainer
{
	public static final Map<MemoryModuleType<GlobalPos>, BiPredicate<ArendellianEntity, PointOfInterestType>> POINTS_OF_INTEREST;
	private static final TrackedData<VillageEntityData> VILLAGE_ENTITY_DATA;
	protected static final ImmutableList<SensorType<? extends Sensor<? super ArendellianEntity>>> SENSOR_TYPES;
	protected static final ImmutableList<MemoryModuleType<?>> MEMORY_MODULE_TYPES;
	
	public ArendellianEntity(EntityType<? extends ArendellianEntity> entityType, World world) 
	{
		super(entityType, world);
		((MobNavigation)this.getNavigation()).setCanPathThroughDoors(true);
		this.getNavigation().setCanSwim(true);
		this.setVillageEntityData(this.getVillageEntityData().withProfession(EntityProfession.NONE).withVillageEntityType(VillageEntityType.NONE));
	}
	
	@Override
	public SimpleInventory getInventory() {
		// TODO Auto-generated method stub
		return super.getInventory();
	}
	
	@SuppressWarnings("unchecked")
	public Brain<ArendellianEntity> getBrain() {
		return (Brain<ArendellianEntity>) super.getBrain();
	}
	
	public void reinitializeBrain(ServerWorld world) {
		Brain<ArendellianEntity> brain = this.getBrain();
		brain.stopAllTasks(world, this);
		this.brain = brain.copy();
		this.initBrain(this.getBrain());
	}

	@Override
	protected Brain<ArendellianEntity> deserializeBrain(Dynamic<?> dynamic) {
		@SuppressWarnings("unchecked")
		Brain<ArendellianEntity> brain = (Brain<ArendellianEntity>) this.createBrainProfile().deserialize(dynamic);
		this.initBrain(brain);
		return brain;
	}
	
	private void initBrain(Brain<ArendellianEntity> brain) {
		EntityProfession entityProfession = this.getVillageEntityData().getProfession();
		
		if(this.isBaby()) {
			brain.setSchedule(ArendellianSchedule.CITIZEN_CHILDREN);
		} else {
			brain.setSchedule(ArendellianSchedule.CITIZEN);
			brain.setTaskList(Activity.WORK, ArendellianTaskListProvider.createWorkTask(entityProfession, 0.5f));

		}
		brain.setTaskList(Activity.CORE, ArendellianTaskListProvider.createCoreTasks(entityProfession, 0.5f));
	    brain.setTaskList(Activity.IDLE, ArendellianTaskListProvider.createIdleTasks(entityProfession, 0.5F));

		brain.setCoreActivities(ImmutableSet.of(Activity.CORE));
		brain.setDefaultActivity(Activity.IDLE);
	    brain.doExclusively(Activity.IDLE);
		brain.refreshActivities(this.world.getTimeOfDay(), this.world.getTime());
	}
	
	public void setVillageEntityData(VillageEntityData villageEntityData) {
		VillageEntityData villageEntityData2 = this.getVillageEntityData();
		if(villageEntityData2.getProfession() != villageEntityData.getProfession()) {
			this.offers = null;
		}
		
		this.dataTracker.set(VILLAGE_ENTITY_DATA, villageEntityData);
	}
	
	@Override
	protected void initDataTracker() {
		super.initDataTracker();
		this.dataTracker.startTracking(VILLAGE_ENTITY_DATA, new VillageEntityData(VillageEntityType.NONE, EntityProfession.NONE, 1));
	}
	
	@Override
	protected void mobTick() {
		this.world.getProfiler().push("arendellianBrain");
	    this.getBrain().tick((ServerWorld)this.world, this);
	    this.world.getProfiler().pop();
	    super.mobTick();
	}
	
	@Override
	public void tickMovement() {
		// TODO Auto-generated method stub
		super.tickMovement();
	}
	
	@Override
	public void tick() {
		super.tick();
	}
	
	@Override
	protected ActionResult interactMob(PlayerEntity player, Hand hand) {
		ItemStack itemStack = player.getStackInHand(hand);
		if(itemStack.getItem() != ModItems.ARENDELLIAN_SPAWN && this.isAlive()) {
			boolean bl = this.getOffers().isEmpty();
			if (hand == Hand.MAIN_HAND) {
	               if (bl && this.world.isClient) {
	            	   if(FabricLoader.getInstance().isDevelopmentEnvironment()) {
	            		   MinecraftClient.getInstance().player.sendChatMessage("Nope! Don't have anything to trade!");
	            	   }
	               }

	               player.incrementStat(Stats.TALKED_TO_VILLAGER);
	            }
			if(bl) {
				return ActionResult.success(this.world.isClient);
			} else {
				if(!this.world.isClient && !this.offers.isEmpty()) {
					this.beginTradeWith(player);
				}
				
				return ActionResult.success(this.world.isClient);
			}
		} else {
			return super.interactMob(player, hand);
		}
	}
	
	private void beginTradeWith(PlayerEntity player) {
		this.setCurrentCustomer(player);
		this.sendOffers(player, this.getDisplayName(), 1);
	}
	
	private void prepareRecipesFor(PlayerEntity player) {
		Iterator<TradeOffer> var3 = this.getOffers().iterator();
		
		while(var3.hasNext()) {
			TradeOffer tradeOffer = (TradeOffer)var3.next();
            tradeOffer.increaseSpecialPrice(-MathHelper.floor((float)1 * tradeOffer.getPriceMultiplier()));

		}
	}
	
	public VillageEntityData getVillageEntityData() {
		return (VillageEntityData)this.dataTracker.get(VILLAGE_ENTITY_DATA);
	}
	
	
	
	@Override
	protected Profile<?> createBrainProfile() {
		return Brain.createProfile(MEMORY_MODULE_TYPES, SENSOR_TYPES);
	}

	public static DefaultAttributeContainer.Builder createArendellianAttributes()
	{
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 70D);
	}
	
	@Override
	protected void afterUsing(TradeOffer offer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void fillRecipes() {
		ItemStack neededItemStack = new ItemStack(Items.DIORITE, 2);
		MinecraftClient.getInstance().player.sendChatMessage("Inventory of this entity: " + this.inventory.toString());
		VillageEntityData villageEntityData = this.getVillageEntityData();
		Int2ObjectMap<ThemovieAPITradeOffers.Factory[]> int2ObjMap = (Int2ObjectMap<Factory[]>)ThemovieAPITradeOffers.PROFESSION_TRADE_OFFERS.get(villageEntityData.getProfession());
		if(int2ObjMap != null && !int2ObjMap.isEmpty()) {
			ThemovieAPITradeOffers.Factory[] factorys = (ThemovieAPITradeOffers.Factory[])int2ObjMap.get(villageEntityData.getLevel());
			if(factorys != null) {
				TradeOfferList tradeOfferList = this.getOffers();
				this.fillRecipesFromPool(tradeOfferList, factorys, 2);
			}
		}
		}
		
		protected void fillRecipesFromPool(TradeOfferList recipeList, ThemovieAPITradeOffers.Factory[] pool, int count) {
		      Set<Integer> set = Sets.newHashSet();
		      if (pool.length > count) {
		         while(set.size() < count) {
		            set.add(this.random.nextInt(pool.length));
		         }
		      } else {
		         for(int i = 0; i < pool.length; ++i) {
		            set.add(i);
		         }
		      }

		      Iterator var9 = set.iterator();

		      while(var9.hasNext()) {
		         Integer integer = (Integer)var9.next();
		         ThemovieAPITradeOffers.Factory factory = pool[integer];
		         TradeOffer tradeOffer = factory.create(this, this.random);
		         if (tradeOffer != null) {
		            recipeList.add(tradeOffer);
		         }
		      }

		}

	//@Override
	public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void writeCustomDataToTag(CompoundTag tag) {
		super.writeCustomDataToTag(tag);
		DataResult<Tag> dataResult = VillageEntityData.CODEC.encodeStart(NbtOps.INSTANCE, this.getVillageEntityData());
		Logger logger = LOGGER;
		logger.getClass();
		dataResult.resultOrPartial(logger::error).ifPresent((tagx) -> {
			tag.put("ArendellianData", tagx);
		});
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void readCustomDataFromTag(CompoundTag tag) {
		super.readCustomDataFromTag(tag);
		if(tag.contains("ArendellianData", 10)) {
			DataResult<VillageEntityData> dataResult = VillageEntityData.CODEC.parse(new Dynamic(NbtOps.INSTANCE, tag.get("ArendellianData")));
	         Logger var10001 = LOGGER;
	         var10001.getClass();
	         dataResult.resultOrPartial(var10001::error).ifPresent(this::setVillageEntityData);
		}
	}
	
	@Override
	public void setAttacker(LivingEntity attacker) {
		if (attacker != null && attacker instanceof PlayerEntity)
    	{
    		ArendellianRepComponent reputationComponent = ModComponents.ARENDELLIAN_REP_COMP.get(ComponentProvider.fromEntity(attacker));
    		reputationComponent.decrease(1);
    		int i;
			i = reputationComponent.getReputation();
			if(this.world.isClient) {
				if(FabricLoader.getInstance().isDevelopmentEnvironment()) {
					MinecraftClient.getInstance().player.sendChatMessage("Reputation has been decayed to: " + i);
					MinecraftClient.getInstance().player.sendChatMessage("Profession: " + this.getVillageEntityData().getProfession().getId());
					MinecraftClient.getInstance().player.sendChatMessage("Potential job site: " + this.getBrain().getOptionalMemory(MemoryModuleType.POTENTIAL_JOB_SITE).toString());
					MinecraftClient.getInstance().player.sendChatMessage("Job site: " + this.getBrain().getOptionalMemory(MemoryModuleType.JOB_SITE).toString());
				}
				
				if(this.getVillageEntityData().getProfession() == ModProfessions.SPIRIT_POWER_CRAFTER_MASON) {
					MinecraftClient.getInstance().player.sendChatMessage("hi! i use spirit power crafter!");
				}
			}
    	}
    	super.setAttacker(attacker);
	}
	
	static {
		VILLAGE_ENTITY_DATA = DataTracker.registerData(ArendellianEntity.class, TrackedDataHandlerRegister.VILLAGE_ENTITY_DATA);
		SENSOR_TYPES = ImmutableList.of();
		MEMORY_MODULE_TYPES = ImmutableList.of(MemoryModuleType.HOME, MemoryModuleType.JOB_SITE, MemoryModuleType.POTENTIAL_JOB_SITE, MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE, MemoryModuleType.WALK_TARGET);
		POINTS_OF_INTEREST = ImmutableMap.of(MemoryModuleType.HOME, (arendellianEntity, pointOfInterestType) -> {
			return pointOfInterestType == PointOfInterestType.HOME;
		}, MemoryModuleType.POTENTIAL_JOB_SITE, (arendellianEntity, pointOfInterestType) -> {
			return ThemovieAPIVillage.IS_USED_BY_ENTITY_PROFESSION.test(pointOfInterestType);
		},MemoryModuleType.JOB_SITE, (arendellianEntity, pointOfInterestType) -> {
			return arendellianEntity.getVillageEntityData().getProfession().getWorkStation() == pointOfInterestType;
		});
	}

	@Override
	public void onSellingItem(ItemStack stack) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SoundEvent getYesSound() {
		// TODO Auto-generated method stub
		return null;
	}
}
