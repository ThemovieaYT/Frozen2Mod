package net.themoviea.frozeniimod.client.render.entity;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import net.themoviea.frozeniimod.entity.passive.ElsaEntity;

public class ElsaEntityRenderer extends MobEntityRenderer<ElsaEntity, ElsaEntityModel> {

    public ElsaEntityRenderer(EntityRenderDispatcher entityRenderDispatcher) 
    {
        super(entityRenderDispatcher, new ElsaEntityModel(), 0.5f);
    }

    @Override
    public Identifier getTexture(ElsaEntity entity) 
    {
    	System.out.println();
        return new Identifier("frozeniimod", "textures/entity/elsa/elsa.png");
    }
    
}