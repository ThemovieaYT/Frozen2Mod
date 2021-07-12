package net.themoviea.frozeniimod.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.world.BlockView;
import net.themoviea.frozeniimod.Frozen2Mod;
import net.themoviea.frozeniimod.block.entity.JobBlockEntity;
import net.themoviea.frozeniimod.init.ModBlocks;
import net.themoviea.themovieapi_village.village.EntityProfession;

public class JobBlock extends Block {
	private final EntityProfession profession;

	public JobBlock(EntityProfession profession, Settings settings) {
		super(settings);
		this.profession = profession;
	}

	public EntityProfession getProfession() {
		return this.profession;
	}
}
