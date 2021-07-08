package net.themoviea.frozeniimod.init;

import nerdhub.cardinal.components.api.ComponentRegistry;
import nerdhub.cardinal.components.api.ComponentType;
import nerdhub.cardinal.components.api.event.EntityComponentCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.themoviea.frozeniimod.frozeniivillage.arendelle.ArendellianRepComponent;

@SuppressWarnings("deprecation")
public class ModComponents 
{
	public static final ComponentType<ArendellianRepComponent> ARENDELLIAN_REP_COMP = ComponentRegistry.INSTANCE.registerIfAbsent(new Identifier("frozeniimod:arendellian_reputation"), ArendellianRepComponent.class);

	public static void registerFrozen2ModComponents()
	{
		//EntityComponent
		EntityComponentCallback.event(PlayerEntity.class).register((player, components) -> components.put(ARENDELLIAN_REP_COMP, new ArendellianRepComponent()));
	}
}
