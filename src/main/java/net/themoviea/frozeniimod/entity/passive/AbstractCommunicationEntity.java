package net.themoviea.frozeniimod.entity.passive;

import java.util.UUID;

import org.jetbrains.annotations.Nullable;

import dev.onyxstudios.cca.api.v3.component.ComponentProvider;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Npc;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.themoviea.frozeniimod.entity.Communicator;
import net.themoviea.frozeniimod.entity.Queenable;
import net.themoviea.frozeniimod.frozeniivillage.arendelle.ArendellianRepComponent;
import net.themoviea.frozeniimod.init.ModComponents;
import net.themoviea.themovieapi_entity.entity.ai.CustomGoalSelector;


public abstract class AbstractCommunicationEntity extends PathAwareEntity implements Npc, Communicator, Queenable
{
    @Nullable
    private static AbstractCommunicationEntity instance;
    private boolean queen;
    public PlayerEntity person;
    protected final SimpleInventory inventory = new SimpleInventory(36);
    protected final CustomGoalSelector customGoalSelector;
    
    protected AbstractCommunicationEntity(EntityType<? extends AbstractCommunicationEntity> entityType, World world) 
    {
        super(entityType, world);
        this.setPathfindingPenalty(PathNodeType.DAMAGE_FIRE, 16.0F);
        this.customGoalSelector = new CustomGoalSelector(world.getProfilerSupplier());
        if (world != null && !world.isClient) {
            this.initScheduledGoals();
         }
    }
    
    protected void initScheduledGoals() {
    }

    @Override
    public EntityData initialize(ServerWorldAccess serverWorldAccess, LocalDifficulty difficulty, SpawnReason spawnReason, EntityData entityData, CompoundTag entityTag) 
    {
        if(entityData == null)
        {
            entityData = new PassiveEntity.PassiveData(false);
        }
        return super.initialize(serverWorldAccess, difficulty, spawnReason, entityData, entityTag);
    }
	
    public SimpleInventory getInventory() {
    	return this.inventory;
    }
    
    @Override
    public void setQueen(boolean queen) 
    {
    	this.queen = queen;
    }
    
    @Override
    public boolean getQueen() 
    {
    	return queen;
    }
    
    public int getExperience()
    {
        return 0;
    }

    @Override
    protected float getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions) 
    {
        return 1.6F;
    }

    @Override
    public void tickMovement() {
    	System.out.println(world.getTimeOfDay());
    	this.tickNewAi();
    	if(this != null) {
    		this.customGoalSelector.tick(this.world);
    	}
    	super.tickMovement();
    }
    public void setCurrentPerson(@Nullable PlayerEntity person)
    {
        this.person = person;
    }

    @Nullable
    public PlayerEntity getCurrentPerson()
    {
        return this.person;
    }

    public boolean hasPerson()
    {
        return getCurrentPerson() != null;
    }

    public void resetPerson()
    {
        this.setCurrentPerson((PlayerEntity)null);
    }

    public void onDeath(DamageSource source)
    {
        super.onDeath(source);
        this.resetPerson();
    }

    public boolean canBeLeashedBy(PlayerEntity player)
    {
        return false;
    }

    public World getCommunicationWorld()
    {
        return this.world;
    }

	public void onDeath(DamageSource source, LivingEntity livingEntity) 
	{
		// TODO Auto-generated method stub
	}
}