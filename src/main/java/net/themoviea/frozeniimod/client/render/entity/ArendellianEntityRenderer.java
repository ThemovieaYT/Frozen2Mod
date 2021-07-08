package net.themoviea.frozeniimod.client.render.entity;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.resource.ReloadableResourceManager;
import net.minecraft.util.Identifier;
import net.themoviea.frozeniimod.client.render.entity.feature.ArendellianClothingFeatureRenderer;
import net.themoviea.frozeniimod.entity.passive.ArendellianEntity;

public class ArendellianEntityRenderer extends MobEntityRenderer<ArendellianEntity, ArendellianModel> {

	public ArendellianEntityRenderer(EntityRenderDispatcher entityRenderDispatcher) {
		super(entityRenderDispatcher, new ArendellianModel(), 0.5f);
		this.addFeature(new ArendellianClothingFeatureRenderer<ArendellianEntity, ArendellianModel>(this, "arendellian"));
	}

	@Override
	public Identifier getTexture(ArendellianEntity entity) {
		return new Identifier("frozeniimod", "textures/entity/arendellian/arendellian.png");
	}

	

}
