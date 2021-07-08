package net.themoviea.frozeniimod.init;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.themoviea.frozeniimod.entity.boss.GhostFifthSpiritElsaEntity;
import net.themoviea.frozeniimod.entity.object.BoulderEntity;
import net.themoviea.frozeniimod.entity.passive.AnnaEntity;
import net.themoviea.frozeniimod.entity.passive.ArendellianEntity;
import net.themoviea.frozeniimod.entity.passive.BruniEntity;
import net.themoviea.frozeniimod.entity.passive.ElsaEntity;
import net.themoviea.frozeniimod.entity.passive.GaleEntity;
import net.themoviea.frozeniimod.entity.passive.NokkEntity;
import net.themoviea.frozeniimod.entity.projectile.IcePowerEntity;

public class ModEntities 
{
	public static final EntityType<ElsaEntity> ELSA = Registry.register(Registry.ENTITY_TYPE, new Identifier("frozeniimod", "elsa"), FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, ElsaEntity::new).dimensions(EntityDimensions.fixed(0.6f, 1.8f)).build());
	public static final EntityType<AnnaEntity> ANNA = Registry.register(Registry.ENTITY_TYPE, new Identifier("frozeniimod", "anna"), FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, AnnaEntity::new).dimensions(EntityDimensions.fixed(0.6f, 1.8f)).build());
	public static final EntityType<ArendellianEntity> ARENDELLIAN = Registry.register(Registry.ENTITY_TYPE, new Identifier("frozeniimod", "arendellian"), FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, ArendellianEntity::new).dimensions(EntityDimensions.fixed(0.6f, 1.8f)).build());
	public static final EntityType<BruniEntity> BRUNI = Registry.register(Registry.ENTITY_TYPE, new Identifier("frozeniimod", "bruni"), FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, BruniEntity::new).dimensions(EntityDimensions.fixed(0.6f,  0.6f)).build());
	public static final EntityType<GaleEntity> GALE = Registry.register(Registry.ENTITY_TYPE, new Identifier("frozeniimod", "gale"), FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, GaleEntity::new).dimensions(EntityDimensions.fixed(0.6f, 1.8f)).build());
	public static final EntityType<NokkEntity> NOKK = Registry.register(Registry.ENTITY_TYPE, new Identifier("frozeniimod", "nokk"), FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, NokkEntity::new).dimensions(EntityDimensions.fixed(0.6f, 1.8f)).build());
	
	public static final EntityType<GhostFifthSpiritElsaEntity> GHOST_FIFTH_SPIRIT_ELSA = Registry.register(Registry.ENTITY_TYPE, new Identifier("frozeniimod", "ghost_fifth_spirit_elsa"), FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, GhostFifthSpiritElsaEntity::new).fireImmune().dimensions(EntityDimensions.fixed(0.6f, 1.8f)).build());
	public static final EntityType<IcePowerEntity> ICE_POWER = Registry.register(Registry.ENTITY_TYPE, new Identifier("frozeniimod", "ice_power"), FabricEntityTypeBuilder.<IcePowerEntity>create(SpawnGroup.MISC, IcePowerEntity::new).dimensions(EntityDimensions.fixed(0.25F, 0.25F)).build());
	
	//Object Entity
	public static final EntityType<BoulderEntity> BOULDER_ENTITY = Registry.register(Registry.ENTITY_TYPE, new Identifier("frozeniimod", "boulder"), FabricEntityTypeBuilder.<BoulderEntity>create().dimensions(EntityDimensions.fixed(0.50F, 0.50F)).build());
	public static void registerFrozen2ModEntityAttributes()
	{
		FabricDefaultAttributeRegistry.register(ELSA, ElsaEntity.createElsaAttributes());
		FabricDefaultAttributeRegistry.register(ANNA, AnnaEntity.createAnnaAttributes());
		FabricDefaultAttributeRegistry.register(ARENDELLIAN, ArendellianEntity.createArendellianAttributes());
		FabricDefaultAttributeRegistry.register(GHOST_FIFTH_SPIRIT_ELSA, GhostFifthSpiritElsaEntity.createMobAttributes());
		FabricDefaultAttributeRegistry.register(BRUNI, BruniEntity.createMobAttributes());
		FabricDefaultAttributeRegistry.register(GALE, GaleEntity.createMobAttributes());
		FabricDefaultAttributeRegistry.register(NOKK, NokkEntity.createMobAttributes());
	}
}
