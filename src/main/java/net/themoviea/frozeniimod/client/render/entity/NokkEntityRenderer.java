package net.themoviea.frozeniimod.client.render.entity;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import net.themoviea.frozeniimod.entity.passive.NokkEntity;

public class NokkEntityRenderer extends MobEntityRenderer<NokkEntity, NokkEntityModel>{

	public NokkEntityRenderer(EntityRenderDispatcher entityRenderDispatcher) {
		super(entityRenderDispatcher, new NokkEntityModel(), 0.6f);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Identifier getTexture(NokkEntity entity) {
		// TODO Auto-generated method stub
		return new Identifier("minecraft", "textures/entity/horse/horse_black.png");
	}

	/*public NokkEntityRenderer() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Identifier getTexture(NokkEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}*/

}
