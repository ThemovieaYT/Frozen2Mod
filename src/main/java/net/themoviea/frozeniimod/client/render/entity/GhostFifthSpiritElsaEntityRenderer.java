package net.themoviea.frozeniimod.client.render.entity;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import net.themoviea.frozeniimod.entity.boss.GhostFifthSpiritElsaEntity;

public class GhostFifthSpiritElsaEntityRenderer extends MobEntityRenderer<GhostFifthSpiritElsaEntity, GhostFifthSpiritElsaEntityModel>
{

	public GhostFifthSpiritElsaEntityRenderer(EntityRenderDispatcher entityRenderDispatcher) {
		super(entityRenderDispatcher, new GhostFifthSpiritElsaEntityModel(), 0.5f);
	}

	@Override
	public Identifier getTexture(GhostFifthSpiritElsaEntity entity) 
	{
		return new Identifier("frozeniimod", "textures/entity/elsa/ghost_fifth_spirit_elsa.png");
	}

}
