// Made with Blockbench 3.8.2
	// Exported for Minecraft version 1.15
	// Paste this class into your mod and generate all required imports

package net.themoviea.frozeniimod.client.render.entity;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;
import net.themoviea.frozeniimod.entity.passive.GaleEntity;

public class GaleEntityModel extends EntityModel<GaleEntity> {
private final ModelPart LeaveMedium1;
	private final ModelPart LeaveMedium2;
	private final ModelPart LeaveMedium3;
	private final ModelPart LeaveMedium4;
	private final ModelPart LeaveMedium5;
	private final ModelPart LeaveMedium6;
	private final ModelPart LeaveMedium7;
	private final ModelPart LeaveMedium8;
	private final ModelPart LeaveMedium9;
	private final ModelPart LeaveMedium10;
	private final ModelPart LeaveMedium11;
	private final ModelPart LeaveSmall1;
	private final ModelPart leaves_r1;
	private final ModelPart LeaveSmall2;
	private final ModelPart LeaveSmall3;
	private final ModelPart LeaveSmall4;
	private final ModelPart LeaveSmall5;
	private final ModelPart LeaveSmall6;
	private final ModelPart LeaveSmall7;
	private final ModelPart LeaveSmall8;
	private final ModelPart LeaveSmall9;
	private final ModelPart LeaveSmall10;
	private final ModelPart LeaveSmall11;
	private final ModelPart LeaveSmall12;
	private final ModelPart LeaveSmall13;
	private final ModelPart LeaveSmall14;
	private final ModelPart LeaveSmall15;
	private final ModelPart leaves_r2;
public GaleEntityModel() {
		textureWidth = 32;
		textureHeight = 32;
		LeaveMedium1 = new ModelPart(this);
		LeaveMedium1.setPivot(0.0F, 24.0F, 0.0F);
		setRotationAngle(LeaveMedium1, 0.0F, 0.0F, 1.0036F);
		LeaveMedium1.setTextureOffset(4, 15).addCuboid(-7.0F, -3.0F, -1.0F, 0.0F, 3.0F, 2.0F, 0.0F, false);

		LeaveMedium2 = new ModelPart(this);
		LeaveMedium2.setPivot(0.0F, 24.0F, 0.0F);
		setRotationAngle(LeaveMedium2, 0.0F, 0.6109F, 0.3927F);
		LeaveMedium2.setTextureOffset(0, 15).addCuboid(-5.0F, -3.0F, -1.0F, 0.0F, 3.0F, 2.0F, 0.0F, false);

		LeaveMedium3 = new ModelPart(this);
		LeaveMedium3.setPivot(0.0F, 24.0F, 0.0F);
		setRotationAngle(LeaveMedium3, -0.5672F, -0.7418F, 0.5236F);
		LeaveMedium3.setTextureOffset(8, 10).addCuboid(-3.0F, -3.0F, -1.0F, 0.0F, 3.0F, 2.0F, 0.0F, false);

		LeaveMedium4 = new ModelPart(this);
		LeaveMedium4.setPivot(0.0F, 24.0F, 0.0F);
		setRotationAngle(LeaveMedium4, -0.5672F, -1.4399F, 0.6109F);
		LeaveMedium4.setTextureOffset(0, 5).addCuboid(-4.0F, -6.0F, -1.0F, 0.0F, 3.0F, 2.0F, 0.0F, false);

		LeaveMedium5 = new ModelPart(this);
		LeaveMedium5.setPivot(0.0F, 24.0F, 0.0F);
		setRotationAngle(LeaveMedium5, 0.0F, -0.7418F, 0.0F);
		LeaveMedium5.setTextureOffset(4, 10).addCuboid(3.0F, -3.0F, -1.0F, 0.0F, 3.0F, 2.0F, 0.0F, false);

		LeaveMedium6 = new ModelPart(this);
		LeaveMedium6.setPivot(0.0F, 24.0F, 0.0F);
		setRotationAngle(LeaveMedium6, 0.1745F, 0.5236F, 0.2618F);
		LeaveMedium6.setTextureOffset(0, 10).addCuboid(3.0F, -3.0F, -1.0F, 0.0F, 3.0F, 2.0F, 0.0F, false);

		LeaveMedium7 = new ModelPart(this);
		LeaveMedium7.setPivot(0.0F, 24.0F, 0.0F);
		setRotationAngle(LeaveMedium7, 0.3054F, -0.6545F, 0.5236F);
		LeaveMedium7.setTextureOffset(8, 5).addCuboid(5.0F, -3.0F, -1.0F, 0.0F, 3.0F, 2.0F, 0.0F, false);

		LeaveMedium8 = new ModelPart(this);
		LeaveMedium8.setPivot(0.0F, 24.0F, 0.0F);
		setRotationAngle(LeaveMedium8, 0.6981F, -1.1345F, -0.6981F);
		LeaveMedium8.setTextureOffset(4, 5).addCuboid(7.0F, -3.0F, -1.0F, 0.0F, 3.0F, 2.0F, 0.0F, false);

		LeaveMedium9 = new ModelPart(this);
		LeaveMedium9.setPivot(0.0F, 24.0F, 0.0F);
		setRotationAngle(LeaveMedium9, 0.6981F, 3.0543F, -1.6144F);
		LeaveMedium9.setTextureOffset(8, 0).addCuboid(3.0F, -2.0F, -1.0F, 0.0F, 3.0F, 2.0F, 0.0F, false);

		LeaveMedium10 = new ModelPart(this);
		LeaveMedium10.setPivot(0.0F, 24.0F, 0.0F);
		setRotationAngle(LeaveMedium10, 1.4835F, -2.0508F, -1.3526F);
		LeaveMedium10.setTextureOffset(4, 0).addCuboid(4.0F, -2.0F, -1.0F, 0.0F, 3.0F, 2.0F, 0.0F, false);

		LeaveMedium11 = new ModelPart(this);
		LeaveMedium11.setPivot(0.0F, 24.0F, 0.0F);
		setRotationAngle(LeaveMedium11, 0.1745F, 0.0436F, -0.7854F);
		LeaveMedium11.setTextureOffset(0, 0).addCuboid(6.0F, -3.0F, -1.0F, 0.0F, 3.0F, 2.0F, 0.0F, false);

		LeaveSmall1 = new ModelPart(this);
		LeaveSmall1.setPivot(0.0F, 24.0F, 0.0F);
		setRotationAngle(LeaveSmall1, 0.0F, -0.3491F, 0.5236F);
		

		leaves_r1 = new ModelPart(this);
		leaves_r1.setPivot(0.0F, 0.0F, 0.0F);
		LeaveSmall1.addChild(leaves_r1);
		setRotationAngle(leaves_r1, 0.0F, 0.2182F, -0.5236F);
		leaves_r1.setTextureOffset(0, 20).addCuboid(8.0F, -2.0F, 0.0F, 0.0F, 2.0F, 1.0F, 0.0F, false);

		LeaveSmall2 = new ModelPart(this);
		LeaveSmall2.setPivot(0.0F, 24.0F, 0.0F);
		setRotationAngle(LeaveSmall2, 0.0F, 2.2689F, 0.0F);
		LeaveSmall2.setTextureOffset(2, 25).addCuboid(8.0F, -1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, false);

		LeaveSmall3 = new ModelPart(this);
		LeaveSmall3.setPivot(0.0F, 24.0F, 0.0F);
		setRotationAngle(LeaveSmall3, 0.0F, -1.4835F, 0.0F);
		LeaveSmall3.setTextureOffset(0, 25).addCuboid(8.0F, -1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, false);

		LeaveSmall4 = new ModelPart(this);
		LeaveSmall4.setPivot(0.0F, 24.0F, 0.0F);
		setRotationAngle(LeaveSmall4, 0.0F, -3.098F, -0.4363F);
		LeaveSmall4.setTextureOffset(10, 23).addCuboid(8.0F, -1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, false);

		LeaveSmall5 = new ModelPart(this);
		LeaveSmall5.setPivot(0.0F, 24.0F, 0.0F);
		setRotationAngle(LeaveSmall5, 0.0F, 0.6981F, 0.5672F);
		LeaveSmall5.setTextureOffset(8, 23).addCuboid(8.0F, -1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, false);

		LeaveSmall6 = new ModelPart(this);
		LeaveSmall6.setPivot(0.0F, 24.0F, 0.0F);
		setRotationAngle(LeaveSmall6, 0.0F, 0.8727F, 1.4399F);
		LeaveSmall6.setTextureOffset(6, 23).addCuboid(8.0F, -1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, false);

		LeaveSmall7 = new ModelPart(this);
		LeaveSmall7.setPivot(0.0F, 24.0F, 0.0F);
		setRotationAngle(LeaveSmall7, 0.0F, -2.5307F, 0.3927F);
		LeaveSmall7.setTextureOffset(4, 23).addCuboid(8.0F, -1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, false);

		LeaveSmall8 = new ModelPart(this);
		LeaveSmall8.setPivot(0.0F, 24.0F, 0.0F);
		setRotationAngle(LeaveSmall8, 0.0F, -0.6981F, 0.6109F);
		LeaveSmall8.setTextureOffset(2, 23).addCuboid(8.0F, -1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, false);

		LeaveSmall9 = new ModelPart(this);
		LeaveSmall9.setPivot(0.0F, 24.0F, 0.0F);
		setRotationAngle(LeaveSmall9, 0.0F, 0.3927F, -0.3491F);
		LeaveSmall9.setTextureOffset(0, 23).addCuboid(8.0F, -1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, false);

		LeaveSmall10 = new ModelPart(this);
		LeaveSmall10.setPivot(0.0F, 24.0F, 0.0F);
		setRotationAngle(LeaveSmall10, 0.5672F, 1.6144F, 0.6109F);
		LeaveSmall10.setTextureOffset(10, 20).addCuboid(8.0F, -1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, false);

		LeaveSmall11 = new ModelPart(this);
		LeaveSmall11.setPivot(0.0F, 24.0F, 0.0F);
		setRotationAngle(LeaveSmall11, 0.0F, 2.9671F, 0.6109F);
		LeaveSmall11.setTextureOffset(8, 20).addCuboid(8.0F, -1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, false);

		LeaveSmall12 = new ModelPart(this);
		LeaveSmall12.setPivot(0.0F, 24.0F, 0.0F);
		setRotationAngle(LeaveSmall12, 0.0F, -0.6981F, 0.6109F);
		LeaveSmall12.setTextureOffset(6, 20).addCuboid(8.0F, -1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, false);

		LeaveSmall13 = new ModelPart(this);
		LeaveSmall13.setPivot(0.0F, 24.0F, 0.0F);
		setRotationAngle(LeaveSmall13, -0.9599F, 2.5744F, -0.9599F);
		LeaveSmall13.setTextureOffset(4, 20).addCuboid(8.0F, -1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, false);

		LeaveSmall14 = new ModelPart(this);
		LeaveSmall14.setPivot(0.0F, 24.0F, 0.0F);
		setRotationAngle(LeaveSmall14, 0.0F, -2.5744F, -0.5236F);
		LeaveSmall14.setTextureOffset(2, 20).addCuboid(8.0F, -1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, false);

		LeaveSmall15 = new ModelPart(this);
		LeaveSmall15.setPivot(0.0F, 24.0F, 0.0F);
		setRotationAngle(LeaveSmall15, 0.829F, -0.3491F, 0.5236F);
		

		leaves_r2 = new ModelPart(this);
		leaves_r2.setPivot(0.0F, 0.0F, 0.0F);
		LeaveSmall15.addChild(leaves_r2);
		setRotationAngle(leaves_r2, 0.0F, 0.2182F, -0.5236F);
		leaves_r2.setTextureOffset(8, 15).addCuboid(8.0F, -2.0F, 0.0F, 0.0F, 2.0F, 1.0F, 0.0F, false);
}

@Override
public void setAngles(GaleEntity entity, float limbAngle, float limbDistance, float animationProgress,
		float headYaw, float headPitch) {
	float f = animationProgress * 3.1415927F * -0.1F;
    LeaveMedium1.pivotY = -2.0F + MathHelper.cos(((float)(0 * 2) + animationProgress) * 0.25F);
    LeaveMedium1.pivotX = MathHelper.cos(f) * 9.0F;
    LeaveMedium1.pivotZ = MathHelper.sin(f) * 9.0F;
    ++f;
    LeaveMedium2.pivotY = -2.0F + MathHelper.cos(((float)(1 * 2) + animationProgress) * 0.25F);
    LeaveMedium2.pivotX = MathHelper.cos(f) * 9.0F;
    LeaveMedium2.pivotZ = MathHelper.sin(f) * 9.0F;
    ++f;
    LeaveMedium3.pivotY = -2.0F + MathHelper.cos(((float)(2 * 2) + animationProgress) * 0.25F);
    LeaveMedium3.pivotX = MathHelper.cos(f) * 9.0F;
    LeaveMedium3.pivotZ = MathHelper.sin(f) * 9.0F;
    ++f;
    LeaveMedium4.pivotY = -2.0F + MathHelper.cos(((float)(3 * 2) + animationProgress) * 0.25F);
    LeaveMedium4.pivotX = MathHelper.cos(f) * 9.0F;
    LeaveMedium4.pivotZ = MathHelper.sin(f) * 9.0F;
    ++f;
    LeaveMedium5.pivotY = -2.0F + MathHelper.cos(((float)(4 * 2) + animationProgress) * 0.25F);
    LeaveMedium5.pivotX = MathHelper.cos(f) * 9.0F;
    LeaveMedium5.pivotZ = MathHelper.sin(f) * 9.0F;
    ++f;
    LeaveMedium6.pivotY = -2.0F + MathHelper.cos(((float)(5 * 2) + animationProgress) * 0.25F);
    LeaveMedium6.pivotX = MathHelper.cos(f) * 9.0F;
    LeaveMedium6.pivotZ = MathHelper.sin(f) * 9.0F;
    ++f;
    LeaveMedium7.pivotY = -2.0F + MathHelper.cos(((float)(6 * 2) + animationProgress) * 0.25F);
    LeaveMedium7.pivotX = MathHelper.cos(f) * 9.0F;
    LeaveMedium7.pivotZ = MathHelper.sin(f) * 9.0F;
    ++f;
    LeaveMedium8.pivotY = -2.0F + MathHelper.cos(((float)(7 * 2) + animationProgress) * 0.25F);
    LeaveMedium8.pivotX = MathHelper.cos(f) * 9.0F;
    LeaveMedium8.pivotZ = MathHelper.sin(f) * 9.0F;
    ++f;
    LeaveMedium9.pivotY = -2.0F + MathHelper.cos(((float)(8 * 2) + animationProgress) * 0.25F);
    LeaveMedium9.pivotX = MathHelper.cos(f) * 9.0F;
    LeaveMedium9.pivotZ = MathHelper.sin(f) * 9.0F;
    ++f;
    LeaveMedium10.pivotY = -2.0F + MathHelper.cos(((float)(9 * 2) + animationProgress) * 0.25F);
    LeaveMedium10.pivotX = MathHelper.cos(f) * 9.0F;
    LeaveMedium10.pivotZ = MathHelper.sin(f) * 9.0F;
    ++f;
    LeaveMedium11.pivotY = -2.0F + MathHelper.cos(((float)(10 * 2) + animationProgress) * 0.25F);
    LeaveMedium11.pivotX = MathHelper.cos(f) * 9.0F;
    LeaveMedium11.pivotZ = MathHelper.sin(f) * 9.0F;
    ++f;
    
    f = 0.7853982F + animationProgress * 3.1415927F * 0.03F;
    LeaveSmall1.pivotY = -2.0F + MathHelper.cos(((float)(11 * 2) + animationProgress) * 0.25F);
    LeaveSmall1.pivotX = MathHelper.cos(f) * 7.0F;
    LeaveSmall1.pivotZ = MathHelper.sin(f) * 7.0F;
    ++f;
    LeaveSmall2.pivotY = -2.0F + MathHelper.cos(((float)(12 * 2) + animationProgress) * 0.25F);
    LeaveSmall2.pivotX = MathHelper.cos(f) * 7.0F;
    LeaveSmall2.pivotZ = MathHelper.sin(f) * 7.0F;
    ++f;
    LeaveSmall3.pivotY = -2.0F + MathHelper.cos(((float)(13 * 2) + animationProgress) * 0.25F);
    LeaveSmall3.pivotX = MathHelper.cos(f) * 7.0F;
    LeaveSmall3.pivotZ = MathHelper.sin(f) * 7.0F;
    ++f;
    LeaveSmall4.pivotY = -2.0F + MathHelper.cos(((float)(14 * 2) + animationProgress) * 0.25F);
    LeaveSmall4.pivotX = MathHelper.cos(f) * 7.0F;
    LeaveSmall4.pivotZ = MathHelper.sin(f) * 7.0F;
    ++f;
    LeaveSmall5.pivotY = -2.0F + MathHelper.cos(((float)(15 * 2) + animationProgress) * 0.25F);
    LeaveSmall5.pivotX = MathHelper.cos(f) * 7.0F;
    LeaveSmall5.pivotZ = MathHelper.sin(f) * 7.0F;
    ++f;
    LeaveSmall6.pivotY = -2.0F + MathHelper.cos(((float)(16 * 2) + animationProgress) * 0.25F);
    LeaveSmall6.pivotX = MathHelper.cos(f) * 7.0F;
    LeaveSmall6.pivotZ = MathHelper.sin(f) * 7.0F;
    ++f;
    LeaveSmall7.pivotY = -2.0F + MathHelper.cos(((float)(17 * 2) + animationProgress) * 0.25F);
    LeaveSmall7.pivotX = MathHelper.cos(f) * 7.0F;
    LeaveSmall7.pivotZ = MathHelper.sin(f) * 7.0F;
    ++f;
    LeaveSmall8.pivotY = -2.0F + MathHelper.cos(((float)(18 * 2) + animationProgress) * 0.25F);
    LeaveSmall8.pivotX = MathHelper.cos(f) * 7.0F;
    LeaveSmall8.pivotZ = MathHelper.sin(f) * 7.0F;
    ++f;
    LeaveSmall9.pivotY = -2.0F + MathHelper.cos(((float)(19 * 2) + animationProgress) * 0.25F);
    LeaveSmall9.pivotX = MathHelper.cos(f) * 7.0F;
    LeaveSmall9.pivotZ = MathHelper.sin(f) * 7.0F;
    ++f;
    LeaveSmall10.pivotY = -2.0F + MathHelper.cos(((float)(20 * 2) + animationProgress) * 0.25F);
    LeaveSmall10.pivotX = MathHelper.cos(f) * 7.0F;
    LeaveSmall10.pivotZ = MathHelper.sin(f) * 7.0F;
    ++f;
    LeaveSmall11.pivotY = -2.0F + MathHelper.cos(((float)(21 * 2) + animationProgress) * 0.25F);
    LeaveSmall11.pivotX = MathHelper.cos(f) * 7.0F;
    LeaveSmall11.pivotZ = MathHelper.sin(f) * 7.0F;
    ++f;
    LeaveSmall12.pivotY = -2.0F + MathHelper.cos(((float)(22 * 2) + animationProgress) * 0.25F);
    LeaveSmall12.pivotX = MathHelper.cos(f) * 7.0F;
    LeaveSmall12.pivotZ = MathHelper.sin(f) * 7.0F;
    ++f;
    LeaveSmall13.pivotY = -2.0F + MathHelper.cos(((float)(23 * 2) + animationProgress) * 0.25F);
    LeaveSmall13.pivotX = MathHelper.cos(f) * 7.0F;
    LeaveSmall13.pivotZ = MathHelper.sin(f) * 7.0F;
    ++f;
    LeaveSmall14.pivotY = -2.0F + MathHelper.cos(((float)(24 * 2) + animationProgress) * 0.25F);
    LeaveSmall14.pivotX = MathHelper.cos(f) * 7.0F;
    LeaveSmall14.pivotZ = MathHelper.sin(f) * 7.0F;
    ++f;
    LeaveSmall15.pivotY = -2.0F + MathHelper.cos(((float)(25 * 2) + animationProgress) * 0.25F);
    LeaveSmall15.pivotX = MathHelper.cos(f) * 7.0F;
    LeaveSmall15.pivotZ = MathHelper.sin(f) * 7.0F;
    ++f;
}

@Override
public void render(MatrixStack matrixStack, VertexConsumer	buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		
		LeaveMedium1.render(matrixStack, buffer, packedLight, packedOverlay);
		LeaveMedium2.render(matrixStack, buffer, packedLight, packedOverlay);
		LeaveMedium3.render(matrixStack, buffer, packedLight, packedOverlay);
		LeaveMedium4.render(matrixStack, buffer, packedLight, packedOverlay);
		LeaveMedium5.render(matrixStack, buffer, packedLight, packedOverlay);
		LeaveMedium6.render(matrixStack, buffer, packedLight, packedOverlay);
		LeaveMedium7.render(matrixStack, buffer, packedLight, packedOverlay);
		LeaveMedium8.render(matrixStack, buffer, packedLight, packedOverlay);
		LeaveMedium9.render(matrixStack, buffer, packedLight, packedOverlay);
		LeaveMedium10.render(matrixStack, buffer, packedLight, packedOverlay);
		LeaveMedium11.render(matrixStack, buffer, packedLight, packedOverlay);
		LeaveSmall1.render(matrixStack, buffer, packedLight, packedOverlay);
		LeaveSmall2.render(matrixStack, buffer, packedLight, packedOverlay);
		LeaveSmall3.render(matrixStack, buffer, packedLight, packedOverlay);
		LeaveSmall4.render(matrixStack, buffer, packedLight, packedOverlay);
		LeaveSmall5.render(matrixStack, buffer, packedLight, packedOverlay);
		LeaveSmall6.render(matrixStack, buffer, packedLight, packedOverlay);
		LeaveSmall7.render(matrixStack, buffer, packedLight, packedOverlay);
		LeaveSmall8.render(matrixStack, buffer, packedLight, packedOverlay);
		LeaveSmall9.render(matrixStack, buffer, packedLight, packedOverlay);
		LeaveSmall10.render(matrixStack, buffer, packedLight, packedOverlay);
		LeaveSmall11.render(matrixStack, buffer, packedLight, packedOverlay);
		LeaveSmall12.render(matrixStack, buffer, packedLight, packedOverlay);
		LeaveSmall13.render(matrixStack, buffer, packedLight, packedOverlay);
		LeaveSmall14.render(matrixStack, buffer, packedLight, packedOverlay);
		LeaveSmall15.render(matrixStack, buffer, packedLight, packedOverlay);
}
public void setRotationAngle(ModelPart bone, float x, float y, float z) {
		bone.pitch = x;
		bone.yaw = y;
		bone.roll = z;
}

	}