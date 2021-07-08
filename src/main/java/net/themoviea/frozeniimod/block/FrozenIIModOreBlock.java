package net.themoviea.frozeniimod.block;

import java.util.Random;

import net.minecraft.block.OreBlock;

public class FrozenIIModOreBlock extends OreBlock
{

    public FrozenIIModOreBlock(Settings settings) 
    {
        super(settings);
    }

    @Override
    protected int getExperienceWhenMined(Random random) 
    {
        return 2;
    }
}