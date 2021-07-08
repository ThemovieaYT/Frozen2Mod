package net.themoviea.frozeniimod.client.render.entity;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import net.themoviea.frozeniimod.entity.passive.AnnaEntity;

public class AnnaEntityRenderer extends MobEntityRenderer<AnnaEntity, AnnaEntityModel> 
{

	public AnnaEntityRenderer(EntityRenderDispatcher entityRenderDispatcher) 
	{
		super(entityRenderDispatcher, new AnnaEntityModel(), 0.5f);
	}
	
	@Override
	public Identifier getTexture(AnnaEntity entity)
	{
		return new Identifier("frozeniimod", "textures/entity/anna/anna.png");
	}
}
