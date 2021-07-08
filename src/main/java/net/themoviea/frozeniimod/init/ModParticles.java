package net.themoviea.frozeniimod.init;

import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.fabricmc.fabric.impl.client.particle.ParticleFactoryRegistryImpl;
import net.minecraft.client.particle.BlockLeakParticle;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.client.particle.BlockLeakParticle.DrippingWaterFactory;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.themoviea.frozeniimod.client.particle.LeafFallParticle;

public class ModParticles {

	public static final DefaultParticleType LEAF_FALL_PARTICLE = FabricParticleTypes.simple();
	
	public static void registerFrozen2ModParticles() {
		Registry.register(Registry.PARTICLE_TYPE, new Identifier("frozeniimod", "leaf_fall"), LEAF_FALL_PARTICLE);
	}
	
	public static void registerFrozen2ModParticleFactories() {
		ParticleFactoryRegistry.getInstance().register(LEAF_FALL_PARTICLE, LeafFallParticle.DefaultFactory::new);
	}
}
