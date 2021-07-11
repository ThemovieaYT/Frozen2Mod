package net.themoviea.frozeniimod.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.CompoundTag;

public class JobBlockEntity extends BlockEntity {
	private boolean isUsed;
	
	public JobBlockEntity(BlockEntityType<?> type) {
		super(type);
		this.isUsed = false;
	}
	
	public void setIsUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}
	
	@Override
	public CompoundTag toTag(CompoundTag tag) {
		if(this.isUsed) {
			tag.putInt("used", 1);
		} else {
			tag.putInt("used", 0);
		}
		return super.toTag(tag);
	}
	
	@Override
	public void fromTag(BlockState state, CompoundTag tag) {
		if(tag.getInt("used") == 0) {
			this.isUsed = false;
		}
		super.fromTag(state, tag);
	}
}
