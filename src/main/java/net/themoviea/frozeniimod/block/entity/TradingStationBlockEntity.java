package net.themoviea.frozeniimod.block.entity;

import java.util.Map;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.util.math.BlockPos;
import net.themoviea.frozeniimod.Frozen2Mod;
import net.themoviea.frozeniimod.block.JobBlock;

public class TradingStationBlockEntity extends BlockEntity {
	private BlockState jobBlockState;

	public TradingStationBlockEntity() {
		super(Frozen2Mod.TRADING_STATION);
	}
	
	public BlockState getJobBlockState() {
		return jobBlockState;
	}

	public void setJobBlockState(BlockState jobBlockState) {
		this.jobBlockState = jobBlockState;
	}
	
	public void getJobBlock() {
		int x = this.getPos().getX();
		int y = this.getPos().getY();
		int z = this.getPos().getZ();
		BlockPos xBack = new BlockPos(x - 1, y, z);
		BlockPos xForward = new BlockPos(x + 1, y, z);
		BlockPos zBack = new BlockPos(x, y, z - 1);
		BlockPos zForward = new BlockPos(x, y, z + 1);
		if(this.world.getBlockState(xBack).getBlock() instanceof JobBlock) {
			this.setJobBlockState(this.world.getBlockState(xBack));
		} else if(this.world.getBlockState(xForward).getBlock() instanceof JobBlock) {
			this.setJobBlockState(this.world.getBlockState(xForward));
		} else if(this.world.getBlockState(zBack).getBlock() instanceof JobBlock) {
			this.setJobBlockState(this.world.getBlockState(zBack));
		} else if(this.world.getBlockState(zForward).getBlock() instanceof JobBlock) {
			this.setJobBlockState(this.world.getBlockState(zForward));
		}
	}
	
	@Override
	public CompoundTag toTag(CompoundTag tag) {
		if(this.jobBlockState == null) {
			tag.put("JobBlock", NbtHelper.fromBlockState(this.jobBlockState));
		}
		return tag;
	}
	
	@Override
	public void fromTag(BlockState state, CompoundTag tag) {
		this.jobBlockState = NbtHelper.toBlockState(tag.getCompound("JobBlock"));
	}
}
