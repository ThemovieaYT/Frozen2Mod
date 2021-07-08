package net.themoviea.frozeniimod.mixin;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

@Mixin(FluidBlock.class)
public class FluidBlockMixin extends Block {
	@Shadow
	public final FlowableFluid fluid;

	public FluidBlockMixin(Settings settings, FlowableFluid fluid) {
		super(settings);
		this.fluid = fluid;
	}
	
	@Override
	public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		world.setBlockState(pos, Blocks.AIR.getDefaultState());
	}

}
