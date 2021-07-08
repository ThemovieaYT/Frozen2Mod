package net.themoviea.frozeniimod.client.render.entity;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import net.themoviea.frozeniimod.entity.passive.GaleEntity;

public class GaleEntityRenderer extends MobEntityRenderer<GaleEntity, GaleEntityModel>{

	public GaleEntityRenderer(EntityRenderDispatcher dispatcher) {
		super(dispatcher, new GaleEntityModel(), 0.5f);
	}

	@Override
	public Identifier getTexture(GaleEntity entity) {
		return new Identifier("frozeniimod", "textures/entity/gale/gale.png");
	}
}
