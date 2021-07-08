package net.themoviea.frozeniimod.entity.passive;

import java.util.UUID;

import org.jetbrains.annotations.Nullable;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Dynamic;

import dev.onyxstudios.cca.api.v3.component.ComponentProvider;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.Durations;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.ai.brain.Activity;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.ai.brain.sensor.SensorType;
import net.minecraft.entity.ai.goal.FollowTargetGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.ProjectileAttackGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.UniversalAngerGoal;
import net.minecraft.entity.ai.goal.WanderAroundGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.Angerable;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.screen.MerchantScreenHandler;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.IntRange;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.themoviea.frozeniimod.client.gui.screen.ingame.elsaCommunication.ElsaCommunicationScreen;
import net.themoviea.frozeniimod.entity.Queenable;
import net.themoviea.frozeniimod.entity.ai.brain.task.ArendellianTaskListProvider;
import net.themoviea.frozeniimod.entity.ai.goal.ElsaRevengeGoal;
import net.themoviea.frozeniimod.entity.ai.goal.TrackElsaTargetGoal;
import net.themoviea.frozeniimod.entity.boss.GhostFifthSpiritElsaEntity;
import net.themoviea.frozeniimod.entity.projectile.IcePowerEntity;
import net.themoviea.frozeniimod.frozeniivillage.arendelle.ArendellianRepComponent;
import net.themoviea.frozeniimod.init.ModComponents;
import net.themoviea.frozeniimod.init.ModEntities;
import net.themoviea.frozeniimod.init.ModProfessions;
import net.themoviea.frozeniimod.screen.ElsaCommunicationScreenHandler;
import net.themoviea.frozeniimod.screen.SpiritPowerCraftingScreenHandler;
import net.themoviea.themovieapi_village.entity.data.TrackedDataHandlerRegister;
import net.themoviea.themovieapi_village.village.EntityProfession;
import net.themoviea.themovieapi_village.village.VillageEntityData;
import net.themoviea.themovieapi_village.village.VillageEntityType;
import net.themoviea.frozeniimod.item.powers.PowerItem;

public class ElsaEntity extends AbstractCommunicationEntity implements Angerable, RangedAttackMob
{
	private static final IntRange intRange;
	private static LivingEntity livingEntity;
	private UUID angryAt;
	private int angerTime;
	private boolean queen;
	private int attackTicksLeft;
	private static final TrackedData<VillageEntityData> VILLAGE_ENTITY_DATA = DataTracker.registerData(ElsaEntity.class, TrackedDataHandlerRegister.VILLAGE_ENTITY_DATA);
	protected static final ImmutableList<SensorType<? extends Sensor<? super ElsaEntity>>> SENSOR_TYPES;
	protected static final ImmutableList<MemoryModuleType<?>> MEMORY_MODULE_TYPES;
	
    public ElsaEntity(EntityType<? extends ElsaEntity> entityType, World world) {
        super(entityType, world);
        this.setQueen(true);
		this.setVillageEntityData(this.getVillageEntityData().withProfession(EntityProfession.NONE).withVillageEntityType(VillageEntityType.NONE));
    }
    
    public void setQueenProfession() {
    	if(this.getQueen() == true) {
    		this.setVillageEntityData(this.getVillageEntityData().withProfession(ModProfessions.QUEEN).withVillageEntityType(VillageEntityType.NONE));
    	}
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    protected void initGoals() 
    {
    	//This is where I add goals for Elsa. Please note that I could change the code here.
    	this.goalSelector.add(1, new SwimGoal(this));
    	this.goalSelector.add(1, new ProjectileAttackGoal(this, 1.0D, 20, 3.0f));
    	this.goalSelector.add(5, new WanderAroundGoal(this, 0.35D));
    	this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
    	this.targetSelector.add(1, new TrackElsaTargetGoal(this));
    	this.targetSelector.add(2, new ElsaRevengeGoal(this, new Class[0]));
    	this.targetSelector.add(3, new FollowTargetGoal<PlayerEntity>(this, PlayerEntity.class, 10, true, false, this::shouldAngerAt));
    	this.targetSelector.add(4, new UniversalAngerGoal(this, false));
    }
    
    @SuppressWarnings("unchecked")
	public Brain<ElsaEntity> getBrain() 
	{
	 	return (Brain<ElsaEntity>) super.getBrain();
	}

	protected Brain.Profile<ElsaEntity> createBrainProfile() 
	{
		return Brain.createProfile(MEMORY_MODULE_TYPES, SENSOR_TYPES);
	}
	
	public void reinitializeBrain(ServerWorld world) {
		Brain<ElsaEntity> brain = this.getBrain();
		brain.stopAllTasks(world, this);
		this.brain = brain.copy();
		this.initBrain(this.getBrain());
	}
	
	@Override
	protected Brain<?> deserializeBrain(Dynamic<?> dynamic) {
		@SuppressWarnings("unchecked")
		Brain<ElsaEntity> brain = (Brain<ElsaEntity>) this.createBrainProfile().deserialize(dynamic);
		this.initBrain(brain);
		return brain;
	}
	
	private void initBrain(Brain<ElsaEntity> brain) {
		EntityProfession entityProfession = this.getVillageEntityData().getProfession();
		
		brain.setTaskList(Activity.CORE, ArendellianTaskListProvider.createCoreElsaTasks(entityProfession, 0.5f));
	}
	
	public void setVillageEntityData(VillageEntityData villageEntityData) {
		this.dataTracker.set(VILLAGE_ENTITY_DATA, villageEntityData);
	}
	
	public VillageEntityData getVillageEntityData() {
		return (VillageEntityData)this.dataTracker.get(VILLAGE_ENTITY_DATA);
	}
	
	@Override
	protected void initDataTracker() {
		super.initDataTracker();
		this.dataTracker.startTracking(VILLAGE_ENTITY_DATA, new VillageEntityData(VillageEntityType.NONE, EntityProfession.NONE, 1));
	}
    
    @Override
    protected ActionResult interactMob(PlayerEntity player, Hand hand) 
    {
    	/* 	Code for implementing my add-on mods or add integration with other mods.
    	 	If you are a server owner, please ignore this code as of this will not be implemented
    	 	in the "for server" version of the mod.*/
    	/*
    	if(FabricLoader.getInstance().isModLoaded("example")) {
    		
    	}*/
    	
    	if(!this.hasPerson())
    	{
    		if (hand == Hand.MAIN_HAND)
    		{
    			player.incrementStat(Stats.TALKED_TO_VILLAGER);
    		}
    		
    		if(!this.world.isClient)
    		{
    			if(!(player.getItemsHand() instanceof PowerItem))
    				this.setCurrentPerson(player);
    				player.openHandledScreen(this.createScreenHandlerFactory());
    		}
    		return ActionResult.success(this.world.isClient);
    	} else
    	{
    		return ActionResult.PASS;
    	}
    }
    
	public NamedScreenHandlerFactory createScreenHandlerFactory() {
		return new SimpleNamedScreenHandlerFactory((i, playerInventory, playerEntity) -> {
			return new ElsaCommunicationScreenHandler(i, playerInventory);
		}, new TranslatableText("gui.elsa_chat"));
	}
    @Override
    public boolean canTarget(EntityType<?> type) 
    {
    	return super.canTarget(type);
    }
    
    @Override
    public float getHealth() 
    {
        return super.getHealth();
    }

    public static DefaultAttributeContainer.Builder createElsaAttributes()
    {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 70D);
    }

    @Override
    public int getAngerTime() 
    {
        return this.angerTime;
    }

    @Override
    public void setAngerTime(int ticks) 
    {
    	this.angerTime = ticks;
    }

    @Override
    public UUID getAngryAt() 
    {
        return this.angryAt = uuid;
    }

    @Override
    public void setAngryAt(UUID uuid) 
    {
    	this.angryAt = uuid;
    }
    
    public int getAttackTicksLeft() 
    {
		return this.attackTicksLeft;
	}
    
    @Override
    public CompoundTag toTag(CompoundTag tag) 
    {
    	return super.toTag(tag);
    }
    
    @Override
    public void fromTag(CompoundTag tag) 
    {
    	super.fromTag(tag);
    }
    
    @Override
    public void setTarget(LivingEntity target) 
    {
    	super.setTarget(target);
    }
    
    @Override
    public void tickMovement() 
    {
    	super.tickMovement();
    	if (this.attackTicksLeft > 0)
    	{
    		--this.attackTicksLeft;
    	}
    	
    	if (!this.world.isClient)
    	{
    		this.tickAngerLogic((ServerWorld)this.world, true);
    	}
    }
    
    @Override
    public boolean tryAttack(Entity target) 
    {
    	boolean bl = target.damage(DamageSource.mobProjectile(new IcePowerEntity(this.world, this), this), 0.0f);
    	if(bl)
    	{
    		attack((LivingEntity)target, 1.0f);
    	}
    	return bl;
    }
    
    @Override
    public void attack(LivingEntity target, float pullProgress) 
    {
    	this.attackTicksLeft = 20;
    	IcePowerEntity icePowerEntity = new IcePowerEntity(this.world, this);
    	double d = target.getEyeY() - 1.100000023841858D;
    	double e = target.getX() - this.getX();
    	double f = d - icePowerEntity.getY();
        double g = target.getZ() - this.getZ();
        float h = MathHelper.sqrt(e * e + g * g) * 0.2F;
        icePowerEntity.setVelocity(e, f + (double)h, g, 1.6F, 12.0F);
        this.playSound(SoundEvents.ENTITY_SNOW_GOLEM_SHOOT, 1.0F, 0.4F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
        this.world.spawnEntity(icePowerEntity);
    }
    
    @Override
    public void chooseRandomAngerTime()
    {
    	this.setAngerTime(intRange.choose(this.random));
    }

	@SuppressWarnings({ "resource", "deprecation" })
	@Override
    public void setAttacker(LivingEntity attacker) 
    {
    	if (attacker != null && attacker instanceof PlayerEntity)
    	{
    		ArendellianRepComponent reputationComponent = ModComponents.ARENDELLIAN_REP_COMP.get(ComponentProvider.fromEntity(attacker));
    		reputationComponent.decrease(1);
    		int i;
			i = reputationComponent.getReputation();
			if(this.world.isClient) {
				MinecraftClient.getInstance().player.sendChatMessage("Reputation has been decayed to: " + i);
			}
    	}
    	super.setAttacker(attacker);
    }
    
    @Override
    public void onDeath(DamageSource source, LivingEntity livingEntity) 
    {
        LOGGER.info("Elsa died. Anna will cry now., message: '{}'", this, source.getDeathMessage(this).getString());
        super.onDeath(source);
    }
    
    @Override
    protected void onKilledBy(LivingEntity adversary) 
    {
    	super.onKilledBy(adversary);
    	ServerWorld serverWorld = (ServerWorld)this.world;
    	if(serverWorld.getDifficulty() == Difficulty.EASY || serverWorld.getDifficulty() == Difficulty.NORMAL || serverWorld.getDifficulty() == Difficulty.HARD)
    	{
    		GhostFifthSpiritElsaEntity ghostFifthSpiritElsaEntity = (GhostFifthSpiritElsaEntity)this.method_29243(ModEntities.GHOST_FIFTH_SPIRIT_ELSA, false);
    		ghostFifthSpiritElsaEntity.initialize(serverWorld, serverWorld.getLocalDifficulty(ghostFifthSpiritElsaEntity.getBlockPos()), SpawnReason.CONVERSION, null, (CompoundTag)null);
    		ghostFifthSpiritElsaEntity.setAttacker(this.getAttacker());
    	}
    }
    
    static {
    	intRange = Durations.betweenSeconds(20, 39);
    	SENSOR_TYPES = ImmutableList.of();
		MEMORY_MODULE_TYPES = ImmutableList.of(MemoryModuleType.HOME, MemoryModuleType.JOB_SITE, MemoryModuleType.POTENTIAL_JOB_SITE);
    }
}