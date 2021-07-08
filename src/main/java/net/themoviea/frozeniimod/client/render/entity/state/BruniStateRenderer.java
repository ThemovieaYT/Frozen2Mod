package net.themoviea.frozeniimod.client.render.entity.state;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.SynchronousResourceReloadListener;
import net.minecraft.util.Identifier;
import net.themoviea.frozeniimod.entity.passive.BruniEntity;

public class BruniStateRenderer<T extends BruniEntity, M extends EntityModel<T>> extends FeatureRenderer<T, M> implements SynchronousResourceReloadListener {

	public BruniStateRenderer(FeatureRendererContext<T, M> context) {
		super(context);
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, T entity,
			float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw,
			float headPitch) {
		matrices.scale(2f, 2f, 2f);
		matrices.translate(-0.1f, -0.75f, 0.15f);
		if(!entity.isInvisible()) {
			M entityModel = this.getContextModel();
			if(entity.isAngry()) {
				Identifier identifier = this.findTexture("angry", "bruni_angry");
				renderModel(entityModel, identifier, matrices, vertexConsumers, light, entity, 1.0f, 1.0f, 1.0f);
			}
		}
		
	}
	
	private Identifier findTexture(String keyState, String keyId) {
		return new Identifier("frozeniimod", "textures/entity/bruni/" + keyState + "/" + keyId + ".png");
	}
	
	@Override
	public void apply(ResourceManager manager) {
		// TODO Auto-generated method stub
		
	}

}
