// Made with Blockbench 3.6.6
	// Exported for Minecraft version 1.15
	// Paste this class into your mod and generate all required imports

	package net.themoviea.frozeniimod.client.render.entity;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;
import net.themoviea.frozeniimod.entity.boss.GhostFifthSpiritElsaEntity;
import net.themoviea.frozeniimod.entity.passive.BruniEntity;

public class BruniEntityModel extends EntityModel<BruniEntity> {
private final ModelPart bone;
	private final ModelPart bone2;
	private final ModelPart bone3;
	private final ModelPart bone4;
	private final ModelPart bone5;
	private final ModelPart bb_main;
	private final ModelPart head;
	
	public BruniEntityModel() {
			textureWidth = 84;
			textureHeight = 84;
			
			//foot
			bone = new ModelPart(this);
			bone.setPivot(-3.0F, 25.0F, 3.0F);
			//setRotationAngle(bone, 0.6981F, 0.0F, 0.0F);
			bone.setTextureOffset(0, 48).addCuboid(-6.0F, -0.0601F, 8.5623F, 2.0F, 4.0F, 6.0F, 0.0F, false);

			//foot
			bone2 = new ModelPart(this);
			bone2.setPivot(-3.0F, 25.0F, 3.0F);
			//setRotationAngle(bone2, 0.6981F, 0.0F, 0.0F);
			bone2.setTextureOffset(32, 38).addCuboid(-6.0F, -7.7735F, -0.6302F, 2.0F, 4.0F, 6.0F, 0.0F, false);
			
			//foot
			bone3 = new ModelPart(this);
			bone3.setPivot(-3.0F, 25.0F, 3.0F);
			//setRotationAngle(bone3, 0.6981F, 0.0F, 0.0F);
			bone3.setTextureOffset(16, 38).addCuboid(4.0F, -0.0601F, 8.5623F, 2.0F, 4.0F, 6.0F, 0.0F, false);

			//foot
			bone4 = new ModelPart(this);
			bone4.setPivot(-3.0F, 25.0F, 3.0F);
			//setRotationAngle(bone4, 0.6981F, 0.0F, 0.0F);
			bone4.setTextureOffset(0, 38).addCuboid(4.0F, -7.7735F, -0.6302F, 2.0F, 4.0F, 6.0F, 0.0F, false);
			
			//tail
			bone5 = new ModelPart(this);
			bone5.setPivot(0.0F, 23.0F, 4.0F);
			setRotationAngle(bone5, 0.8727F, 0.0F, 0.0F);
			bone5.setTextureOffset(32, 22).addCuboid(-4.0F, 1.7963F, 12.2972F, 2.0F, 4.0F, 8.0F, 0.0F, false);
			
			//head
			head = new ModelPart(this);
			head.setPivot(0.0F, 15.0F, 0.0F);
			head.setTextureOffset(0, 22).addCuboid(-7.0F, -8.0F, -6.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);

			bb_main = new ModelPart(this);
			bb_main.setPivot(0.0F, 24.0F, 0.0F);
			bb_main.setTextureOffset(0, 0).addCuboid(-7.0F, -9.0F, 0.0F, 8.0F, 6.0F, 16.0F, 0.0F, false);
			bb_main.setTextureOffset(16, 48).addCuboid(-5.0F, -11.0F, -11.0F, 4.0F, 1.0F, 5.0F, 0.0F, false);
			bb_main.setTextureOffset(28, 63).addCuboid(-4.0F, -17.0F, 2.0F, 1.0F, 8.0F, 13.0F, 0.0F, false);
			bb_main.setTextureOffset(0, 63).addCuboid(-3.0F, -17.0F, 2.0F, 1.0F, 8.0F, 13.0F, 0.0F, false);
	}
	
	@Override
		public void setAngles(BruniEntity entity, float limbAngle, float limbDistance, float animationProgress,
				float headYaw, float headPitch) {
			this.head.pitch = headPitch * 0.017453292F;
			this.head.yaw = headYaw * 0.017453292F;
			this.bone.pitch = MathHelper.cos(limbAngle * 0.6662F);
			this.bone2.pitch = MathHelper.cos(limbAngle * 0.6662F);
			this.bone3.pitch = MathHelper.cos(limbAngle * 0.6662F);
			this.bone4.pitch = MathHelper.cos(limbAngle * 0.6662F);

		}
	
	@Override
	public void render(MatrixStack matrixStack, VertexConsumer	buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
			
			matrixStack.translate(0.1, 0.75, -0.15);
			matrixStack.scale(0.5f, 0.5f, 0.5f);
			bone.render(matrixStack, buffer, packedLight, packedOverlay);
			bone2.render(matrixStack, buffer, packedLight, packedOverlay);
			bone3.render(matrixStack, buffer, packedLight, packedOverlay);
			bone4.render(matrixStack, buffer, packedLight, packedOverlay);
			bone5.render(matrixStack, buffer, packedLight, packedOverlay);
			bb_main.render(matrixStack, buffer, packedLight, packedOverlay);
			head.render(matrixStack, buffer, packedLight, packedOverlay);
	}
	public void setRotationAngle(ModelPart bone, float x, float y, float z) {
			bone.pitch = x;
			bone.yaw = y;
			bone.roll = z;
	}

}