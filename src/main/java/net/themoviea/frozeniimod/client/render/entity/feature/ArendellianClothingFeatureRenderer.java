package net.themoviea.frozeniimod.client.render.entity.feature;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.SynchronousResourceReloadListener;
import net.minecraft.util.Identifier;
import net.themoviea.themovieapi_village.ThemovieAPIVillage;
import net.themoviea.themovieapi_village.village.EntityProfession;
import net.themoviea.themovieapi_village.village.VillageEntityData;
import net.themoviea.themovieapi_village.village.VillageEntityDataContainer;

public class ArendellianClothingFeatureRenderer<T extends LivingEntity & VillageEntityDataContainer, M extends EntityModel<T>> extends FeatureRenderer<T, M> implements SynchronousResourceReloadListener {

	private final String entityType;
	public ArendellianClothingFeatureRenderer(FeatureRendererContext<T, M> context, String entityType) {
		super(context);
		this.entityType = entityType;
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, T entity,
			float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw,
			float headPitch) {
		if(!entity.isInvisible()) {
			VillageEntityData villageEntityData = ((VillageEntityDataContainer)entity).getVillageEntityData();
			EntityProfession entityProfession = villageEntityData.getProfession();
			M entityModel = this.getContextModel();
			if(entityProfession != EntityProfession.NONE) {
				Identifier identifier2 = this.findTexture("profession", ThemovieAPIVillage.ENTITY_PROFESSION.getId(entityProfession));
				renderModel(entityModel, identifier2, matrices, vertexConsumers, light, entity, 1.0f, 1.0f, 1.0f);
			}
		}
	}
	
	private Identifier findTexture(String keyType, Identifier keyId) {
		return new Identifier(keyId.getNamespace(), "textures/entity/" + this.entityType + "/" + keyType + "/" + keyId.getPath() + ".png");
	}

	@Override
	public void apply(ResourceManager manager) {
		
	}
}
