
package net.themoviea.frozeniimod.item.powers.protect_methods;

import java.util.Iterator;
import java.util.UUID;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.block.Material;
import net.minecraft.block.ShapeContext;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Tickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.themoviea.frozeniimod.entity.PowerElement;
import net.themoviea.frozeniimod.init.ModBlocks;
import net.themoviea.frozeniimod.item.powers.PowerItem;
import net.themoviea.frozeniimod.item.powers.attack_methods.PowerAttackMethod;

public class WaterShowerProtectMethod extends PowerProtectMethod {

	public WaterShowerProtectMethod(PowerItem power) {
		super(power);
	}
	
	@Override
	public boolean powerProtectCondition(LivingEntity entity) {
		if(entity.isOnFire()) {
			return true;
		}
		return false;
	}
	
	/*
	 * This protect method protects from set entity on fire attack method, by
	 * placing water source block on top of the player. The water source block
	 * will disappear after 20 ticks, so the water doesn't clog up the pvp
	 * arena.
	 * You know the water trick, right? In pvp, they can slow down the opponent chasing
	 * you. You can use this strategy by setting yourself on fire and use the power.
	 */
	@Override
	public void protectFromPower(PlayerEntity entity, World world) {
		BlockState blockState = ModBlocks.WATER_SPIRIT_POWER_FLUID.getDefaultState();
		PowerAttackMethod attackMethod = this.getPower().getPowerAttackMethod();
		if(powerProtectCondition(entity) == true) {
			if(attackMethod.getAttackMethodElement() == PowerElement.FIRE) {
				BlockPos playerPos = entity.getBlockPos();
				BlockPos waterPos = new BlockPos(playerPos.getX(), playerPos.getY() + 2, playerPos.getZ());
				world.setBlockState(waterPos, blockState);
				world.getBlockTickScheduler().schedule(waterPos, ModBlocks.WATER_SPIRIT_POWER_FLUID, 20);
			}
		} else {
				entity.sendMessage(new TranslatableText("cannotusepower.reason.notonfire"), true);
		}
	}
	
	@Override
	public void entityProtectFromPower(LivingEntity entity, World world) {
		BlockState blockState = ModBlocks.WATER_SPIRIT_POWER_FLUID.getDefaultState();
		PowerAttackMethod attackMethod = this.getPower().getPowerAttackMethod();
		if(powerProtectCondition(entity) == true) {
			if(attackMethod.getAttackMethodElement() == PowerElement.FIRE) {
				BlockPos playerPos = entity.getBlockPos();
				BlockPos waterPos = new BlockPos(playerPos.getX(), playerPos.getY() + 2, playerPos.getZ());
				world.setBlockState(waterPos, blockState);
				world.getBlockTickScheduler().schedule(waterPos, ModBlocks.WATER_SPIRIT_POWER_FLUID, 20);
			}
		}
	}
}
