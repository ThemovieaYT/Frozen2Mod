package net.themoviea.frozeniimod.frozeniivillage.northuldra;

import java.util.Map;
import java.util.UUID;

import org.jetbrains.annotations.Nullable;

import com.google.common.collect.Maps;

import net.minecraft.entity.LivingEntity;

public class NorthuldranReputations 
{
	private static final Map<UUID, Integer> northuldranReputation = Maps.newHashMap();
    public static void setReputation(LivingEntity entity, int rep)
    {
    	northuldranReputation.put(entity.getUuid(), rep);
    }
    
    public static int getReputation(@Nullable LivingEntity entity)
    {
    	return northuldranReputation.getOrDefault(entity.getUuid(), 0);
    }
}