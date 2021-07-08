package net.themoviea.frozeniimod.world.gen.placer.trunk;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.BlockPos.Mutable;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacer.TreeNode;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;
import net.themoviea.frozeniimod.init.ModTrunkPlacers;

public class EnchantedTrunkPlacer extends TrunkPlacer {
	
	public static final Codec<EnchantedTrunkPlacer> CODEC = RecordCodecBuilder.create((instance) -> {
		return method_28904(instance).apply(instance, EnchantedTrunkPlacer::new);
	});
	
	public EnchantedTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight) {
		super(baseHeight, firstRandomHeight, secondRandomHeight);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected TrunkPlacerType<?> getType() {
		return ModTrunkPlacers.ENCHANTED_TRUNK_PLACER;
	}

	@Override
	public List<TreeNode> generate(ModifiableTestableWorld world, Random random, int trunkHeight, BlockPos pos, Set<BlockPos> placedStates, BlockBox box, TreeFeatureConfig config) {
		setToDirt(world, pos.down());
		List<TreeNode> list = Lists.newArrayList();
		for(int i = 0; i < trunkHeight; ++i) {
			getAndSetState(world, random, pos.up(i), placedStates, box, config);
		}
		Direction direction = Direction.Type.HORIZONTAL.random(random);
		

		setBranchBox(direction, list, new BlockPos(pos.getX() + direction.getOffsetX(), ThreadLocalRandom.current().nextInt(pos.getY() + 3, pos.getY() + this.baseHeight), pos.getZ() + direction.getOffsetZ()), world, random, placedStates, box, config);
		list.add(new FoliagePlacer.TreeNode(pos.up(trunkHeight + 3), 0, false));
		return list;
	}
	
	private void setBranchBox(Direction direction, List<TreeNode> treeNodeList, BlockPos pos, ModifiableTestableWorld world, Random random, Set<BlockPos> placedStates, BlockBox box, TreeFeatureConfig config) {
		int x = 0, y = 0, z = 0;

		if(direction == Direction.WEST) {
			while(x < 1) {
				BlockPos branchBlockPos = new BlockPos(pos.getX() - x, y + pos.getY(), pos.getZ());
				getAndSetState(world, random, branchBlockPos, placedStates, box, config);
				x++;
			}
		} else if(direction == Direction.EAST) {
			while(x < 1) {
				BlockPos branchBlockPos = new BlockPos(pos.getX() + x, y + pos.getY(), pos.getZ());
				getAndSetState(world, random, branchBlockPos, placedStates, box, config);
				x++;
			}
		} else if(direction == Direction.NORTH) {
			while(z < 2) {
				BlockPos branchBlockPos = new BlockPos(pos.getX(), pos.getY(), pos.getZ() - z);
				getAndSetState(world, random, branchBlockPos, placedStates, box, config);
				z++;
			}
		} else {
			while(z < 2) {
				BlockPos branchBlockPos = new BlockPos(pos.getX(), pos.getY(), pos.getZ() - z);
				getAndSetState(world, random, branchBlockPos, placedStates, box, config);
				z++;
			}
		}
		
		while(y < 3) {
			BlockPos branchBlockPos;
			if(direction == Direction.NORTH) {
				branchBlockPos = new BlockPos(x + pos.getX(), y + pos.getY(), pos.getZ() - z + 1);
			} else if(direction == Direction.SOUTH) {
				branchBlockPos = new BlockPos(x + pos.getX(), y + pos.getY(), pos.getZ() + 1);
			} else if(direction == Direction.WEST) {
				branchBlockPos = new BlockPos(pos.getX() - 1, y + pos.getY(), pos.getZ());
			} else {
				branchBlockPos = new BlockPos(x + pos.getX(), y + pos.getY(), pos.getZ());
			}
			if(getAndSetState(world, random, branchBlockPos, placedStates, box, config)) {
				if(y == 2) {
					treeNodeList.add(new FoliagePlacer.TreeNode(branchBlockPos.up(4), 0, false));
				}
			}
			y++;
		}
	}
}
