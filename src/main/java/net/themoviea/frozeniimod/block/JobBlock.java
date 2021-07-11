package net.themoviea.frozeniimod.block;

import net.minecraft.block.Block;
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
