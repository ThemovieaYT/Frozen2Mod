package net.themoviea.frozeniimod.frozeniivillage.arendelle;

import java.util.Map;
import java.util.UUID;

import org.jetbrains.annotations.Nullable;

import com.google.common.collect.Maps;

import net.minecraft.entity.LivingEntity;
import net.minecraft.world.PersistentStateManager;

public class ArendellianReputations 
{
    private static final Map<UUID, Integer> arendellianReputation = Maps.newHashMap();
    static PersistentStateManager stateManager;
    public static void setReputation(LivingEntity entity, int rep)
    {
    	arendellianReputation.put(entity.getUuid(), rep);
    }
    
    public static int getReputation(@Nullable LivingEntity entity)
    {
    	return arendellianReputation.getOrDefault(entity.getUuid(), 0);
    }
}