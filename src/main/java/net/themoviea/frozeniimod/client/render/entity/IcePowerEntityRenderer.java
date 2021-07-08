package net.themoviea.frozeniimod.client.render.entity;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import net.themoviea.frozeniimod.entity.projectile.IcePowerEntity;

public class IcePowerEntityRenderer extends FlyingItemEntityRenderer<IcePowerEntity>
{
	   
	public IcePowerEntityRenderer(EntityRenderDispatcher entityRenderDispatcher, ItemRenderer itemRenderer) 
	{
		super(entityRenderDispatcher, itemRenderer);
	}
}
