package net.themoviea.frozeniimod.client.render.entity;

import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.themoviea.frozeniimod.entity.object.BoulderEntity;

public class BoulderEntityRenderer extends EntityRenderer<BoulderEntity> {

	protected final BoulderEntityModel model = new BoulderEntityModel();

	public BoulderEntityRenderer(EntityRenderDispatcher dispatcher) {
		super(dispatcher);
	}
	
	@Override
	public void render(BoulderEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
		matrices.push();
		VertexConsumer vertexConsumer = vertexConsumers.getBuffer(this.model.getLayer(this.getTexture(entity)));
		this.model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
		super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
	}

	@Override
	public Identifier getTexture(BoulderEntity entity) {
		// TODO Auto-generated method stub
		return new Identifier("frozeniimod", "textures/entity/object/boulder/boulderentity.png");
	}

}
