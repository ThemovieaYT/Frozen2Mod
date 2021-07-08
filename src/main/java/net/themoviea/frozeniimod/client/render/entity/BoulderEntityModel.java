package net.themoviea.frozeniimod.client.render.entity;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.themoviea.frozeniimod.entity.object.BoulderEntity;

public class BoulderEntityModel extends EntityModel<BoulderEntity> {
	private final ModelPart Boulder;
	public BoulderEntityModel() {
			textureWidth = 32;
			textureHeight = 32;
			Boulder = new ModelPart(this);
			Boulder.setPivot(0.0F, 24.0F, 0.0F);
			Boulder.setTextureOffset(0, 0).addCuboid(-3.0F, -5.0F, -3.0F, 6.0F, 5.0F, 6.0F, 0.0F, false);
	}
	@Override
	public void setAngles(BoulderEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
			//previously the render function, render code was moved to a method below
	}
	@Override
	public void render(MatrixStack matrixStack, VertexConsumer	buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
			
			Boulder.render(matrixStack, buffer, packedLight, packedOverlay);
	}
	public void setRotationAngle(ModelPart bone, float x, float y, float z) {
			bone.pitch = x;
			bone.yaw = y;
			bone.roll = z;
	}

}