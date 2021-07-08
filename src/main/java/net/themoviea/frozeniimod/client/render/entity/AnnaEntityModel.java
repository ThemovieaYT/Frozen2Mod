package net.themoviea.frozeniimod.client.render.entity;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.themoviea.frozeniimod.entity.passive.AnnaEntity;

public class AnnaEntityModel extends EntityModel<AnnaEntity> {
	private final ModelPart field_178734_a;
	private final ModelPart field_178731_d;
	private final ModelPart field_178732_b;
	private final ModelPart field_178720_f;
	private final ModelPart field_178733_c;
	private final ModelPart field_178723_h;
	private final ModelPart field_178721_j;
	private final ModelPart field_78116_c;
	private final ModelPart field_78115_e;
	private final ModelPart field_178724_i;
	private final ModelPart field_178722_k;
	private final ModelPart field_178730_v;
public AnnaEntityModel() {
		textureWidth = 64;
		textureHeight = 64;
		field_178734_a = new ModelPart(this);
		field_178734_a.setPivot(-5.0F, 2.0F, 0.0F);
		field_178734_a.setTextureOffset(48, 48).addCuboid(-2.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, 0.0F, false);

		field_178731_d = new ModelPart(this);
		field_178731_d.setPivot(1.9F, 12.0F, 0.0F);
		field_178731_d.setTextureOffset(0, 32).addCuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.25F, false);

		field_178732_b = new ModelPart(this);
		field_178732_b.setPivot(6.0F, 2.0F, 10.0F);
		field_178732_b.setTextureOffset(40, 32).addCuboid(-2.0F, -2.0F, -12.0F, 3.0F, 12.0F, 4.0F, 0.0F, false);

		field_178720_f = new ModelPart(this);
		field_178720_f.setPivot(0.0F, 0.0F, 0.0F);
		field_178720_f.setTextureOffset(32, 0).addCuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.25F, false);

		field_178733_c = new ModelPart(this);
		field_178733_c.setPivot(-1.9F, 12.0F, 0.0F);
		field_178733_c.setTextureOffset(0, 48).addCuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.25F, false);

		field_178723_h = new ModelPart(this);
		field_178723_h.setPivot(5.0F, 2.0F, 0.0F);
		field_178723_h.setTextureOffset(40, 16).addCuboid(-1.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, 0.0F, false);

		field_178721_j = new ModelPart(this);
		field_178721_j.setPivot(1.9F, 12.0F, 0.0F);
		field_178721_j.setTextureOffset(0, 16).addCuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

		field_78116_c = new ModelPart(this);
		field_78116_c.setPivot(0.0F, 0.0F, 0.0F);
		field_78116_c.setTextureOffset(0, 0).addCuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);

		field_78115_e = new ModelPart(this);
		field_78115_e.setPivot(0.0F, 0.0F, 0.0F);
		field_78115_e.setTextureOffset(16, 16).addCuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.0F, false);

		field_178724_i = new ModelPart(this);
		field_178724_i.setPivot(-5.0F, 2.0F, 0.0F);
		field_178724_i.setTextureOffset(32, 48).addCuboid(-2.0F, -2.0F, -2.0F, 3.0F, 12.0F, 4.0F, 0.0F, false);

		field_178722_k = new ModelPart(this);
		field_178722_k.setPivot(-1.9F, 12.0F, 0.0F);
		field_178722_k.setTextureOffset(16, 48).addCuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);

		field_178730_v = new ModelPart(this);
		field_178730_v.setPivot(0.0F, 0.0F, 0.0F);
		field_178730_v.setTextureOffset(16, 32).addCuboid(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.25F, false);
}
@Override
public void setAngles(AnnaEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		//previously the render function, render code was moved to a method below
}
@Override
public void render(MatrixStack matrixStack, VertexConsumer	buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		
		field_178734_a.render(matrixStack, buffer, packedLight, packedOverlay);
		field_178731_d.render(matrixStack, buffer, packedLight, packedOverlay);
		field_178732_b.render(matrixStack, buffer, packedLight, packedOverlay);
		field_178720_f.render(matrixStack, buffer, packedLight, packedOverlay);
		field_178733_c.render(matrixStack, buffer, packedLight, packedOverlay);
		field_178723_h.render(matrixStack, buffer, packedLight, packedOverlay);
		field_178721_j.render(matrixStack, buffer, packedLight, packedOverlay);
		field_78116_c.render(matrixStack, buffer, packedLight, packedOverlay);
		field_78115_e.render(matrixStack, buffer, packedLight, packedOverlay);
		field_178724_i.render(matrixStack, buffer, packedLight, packedOverlay);
		field_178722_k.render(matrixStack, buffer, packedLight, packedOverlay);
		field_178730_v.render(matrixStack, buffer, packedLight, packedOverlay);
}
public void setRotationAngle(ModelPart bone, float x, float y, float z) {
		bone.pitch = x;
		bone.yaw = y;
		bone.roll = z;
}

	}