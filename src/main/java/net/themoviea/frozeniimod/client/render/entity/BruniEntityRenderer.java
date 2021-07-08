package net.themoviea.frozeniimod.client.render.entity;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.themoviea.frozeniimod.client.render.entity.state.BruniStateRenderer;
import net.themoviea.frozeniimod.entity.passive.BruniEntity;

public class BruniEntityRenderer extends MobEntityRenderer<BruniEntity, BruniEntityModel> {

	public BruniEntityRenderer(EntityRenderDispatcher entityRenderDispatcher) {
		super(entityRenderDispatcher, new BruniEntityModel(), 0.5f);
		this.addFeature(new BruniStateRenderer<BruniEntity, BruniEntityModel>(this));
	}

	@Override
	protected int getBlockLight(BruniEntity entity, BlockPos blockPos) {
		if(!entity.isAngry()) {
			return 0;
		} else {
			return 15;
		}
	}
	
	@Override
	public Identifier getTexture(BruniEntity entity) {
		// TODO Auto-generated method stub
		return new Identifier("frozeniimod", "textures/entity/bruni/bruni.png");
	}
}
