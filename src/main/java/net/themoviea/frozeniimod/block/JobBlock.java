package net.themoviea.frozeniimod.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.themoviea.themovieapi_village.village.EntityProfession;

public class JobBlock extends Block {
	private final EntityProfession profession;
	public static final BooleanProperty OCCUPIED = BooleanProperty.of("OCCUPIED");

	public JobBlock(EntityProfession profession, Settings settings) {
		super(settings);
		this.profession = profession;
		setDefaultState(getStateManager().getDefaultState().with(OCCUPIED, false));
	}

	public EntityProfession getProfession() {
		return this.profession;
	}
	
	@Override
	protected void appendProperties(Builder<Block, BlockState> builder) {
		builder.add(OCCUPIED);
	}
	
	public void setOccupied(BlockState state, World world, BlockPos pos, boolean occupied) {
		if(occupied) {
			world.setBlockState(pos, state.with(OCCUPIED, true));
		} else {
			world.setBlockState(pos, state.with(OCCUPIED, false));
		}
	}
	
	public boolean getOccupied(BlockState state) {
		return state.get(OCCUPIED);
	}
}
