package net.themoviea.frozeniimod.world.gen.generator;

import java.util.List;
import java.util.Map;
import java.util.Random;

import com.google.common.collect.ImmutableMap;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.structure.SimpleStructurePiece;
import net.minecraft.structure.Structure;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.processor.BlockIgnoreStructureProcessor;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ServerWorldAccess;
import net.themoviea.frozeniimod.init.ModFeatures;

public class TestHouseGenerator {
	public static final Identifier TEST_HOUSE = new Identifier("frozeniimod:test/house/testhouse");
	private static final Map<Identifier, BlockPos> mapThing = ImmutableMap.of(TEST_HOUSE, BlockPos.ORIGIN);
	public static void addPieces(StructureManager manager, BlockPos pos, BlockRotation rotation, List<StructurePiece> pieces, Random random) {
		 if (random.nextDouble() < 0.5D) {
	         int i = random.nextInt(8) + 4;
	         pieces.add(new TestHouseGenerator.TestHousePiece(manager, pos, TEST_HOUSE, rotation, i * 3));
	      }
		pieces.add(new TestHouseGenerator.TestHousePiece(manager, pos, TEST_HOUSE, rotation, 0));
	}
	
	public static class TestHousePiece extends SimpleStructurePiece {
		private final BlockRotation rotation;
		private final Identifier template;
		
		public TestHousePiece(StructureManager manager, CompoundTag compoundTag) {
			super(ModFeatures.TEST_HOUSE_PIECE, compoundTag);
			this.template = new Identifier(compoundTag.getString("Template"));
			this.rotation = BlockRotation.valueOf(compoundTag.getString("Rot"));
			this.initializeStructureData(manager);
		}
		
		public TestHousePiece(StructureManager manager, BlockPos pos, Identifier identifier, BlockRotation rotation, int yOffset) {
			super(ModFeatures.TEST_HOUSE_PIECE, 0);
			this.template = identifier;
			BlockPos blockPos = (BlockPos)TestHouseGenerator.mapThing.get(identifier);
			this.pos = pos.add(blockPos.getX(), blockPos.getY() - yOffset, blockPos.getZ());
			this.rotation = rotation;
			this.initializeStructureData(manager);
		}
		
		private void initializeStructureData(StructureManager manager) {
			Structure structure = manager.getStructureOrBlank(this.template);
			StructurePlacementData placementData = (new StructurePlacementData())
					.setRotation(this.rotation)
					.setMirror(BlockMirror.NONE)
					.addProcessor(BlockIgnoreStructureProcessor.IGNORE_STRUCTURE_BLOCKS);
			this.setStructureData(structure, this.pos, placementData);
		}
		
		protected void toNbt(CompoundTag tag) {
		    super.toNbt(tag);
		    tag.putString("Template", this.template.toString());
		    tag.putString("Rot", this.rotation.name());
		}
		
		@Override
		protected void handleMetadata(String metadata, BlockPos pos, ServerWorldAccess serverWorldAccess, Random random,
				BlockBox boundingBox) {	
		}
	}
}
