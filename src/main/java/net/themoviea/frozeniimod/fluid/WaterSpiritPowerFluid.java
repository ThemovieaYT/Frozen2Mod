package net.themoviea.frozeniimod.fluid;

import java.util.Random;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.WaterFluid;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

public class WaterSpiritPowerFluid extends WaterFluid {

	@Override
	public boolean isStill(FluidState state) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int getLevel(FluidState state) {
		// TODO Auto-generated method stub
		return 8;
	}

	@Override
	public void onScheduledTick(World world, BlockPos pos, FluidState state) {
		// TODO Auto-generated method stub
		super.onScheduledTick(world, pos, state);
	}
}
