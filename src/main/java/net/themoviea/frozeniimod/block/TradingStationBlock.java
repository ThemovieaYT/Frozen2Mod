package net.themoviea.frozeniimod.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.world.BlockView;
import net.themoviea.frozeniimod.block.entity.TradingStationBlockEntity;

public class TradingStationBlock extends Block implements BlockEntityProvider {

	public TradingStationBlock(Settings settings) {
		super(settings);
		// TODO Auto-generated constructor stub
	}

	@Override
	public BlockEntity createBlockEntity(BlockView world) {
		return new TradingStationBlockEntity();
	}
}
