package net.themoviea.frozeniimod.item.powers.attack_methods;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.themoviea.frozeniimod.entity.PowerElement;
import net.themoviea.frozeniimod.entity.object.BoulderEntity;

public class ThrowRockMethod extends PowerAttackMethod {
	
	private boolean ifNeedDrop;
	
	public ThrowRockMethod(PowerElement element, int x, int y, int z, int maxValue, PowerElement powerStrength,
			PowerElement powerWeakness) {
		super(element, x, y, z, maxValue, powerStrength, powerWeakness);
		// TODO Auto-generated constructor stub
	}
	
	public void checkIfNeedDrop(boolean ifNeedDrop) {
		this.ifNeedDrop = ifNeedDrop;
	}
	
	@Override
	public void attackEntity(PlayerEntity user, LivingEntity entity, Hand hand) {
		BoulderEntity rockEntity = new BoulderEntity(user.getEntityWorld());
		BlockPos targetPos = entity.getBlockPos();
		BlockPos entityPos = user.getBlockPos();
		BlockPos middlePointPos = new BlockPos((entityPos.getX() + targetPos.getX()) / 2, (entityPos.getY() + targetPos.getY()) / 2, (entityPos.getZ() + targetPos.getZ()) / 2);
		int maxHeight = 3;
		user.getEntityWorld().spawnEntity(rockEntity);
		if(targetPos.getY() > entityPos.getY()) {
			maxHeight = targetPos.getY() + 3;
		}
		BlockPos throwMaxHeightPos = new BlockPos(middlePointPos.getX(), middlePointPos.getY() + maxHeight, middlePointPos.getZ());
		
		/*while(rockEntity.getBlockPos().getY() >= 1) {
			int i = 0;
			if(i == 0) {
				rockEntity.setVelocity(-2.0D, 2.0D, -2.0D);
				throwMaxHeightPos = new BlockPos(middlePointPos.getX(), (middlePointPos.getY() + maxHeight) / 2, middlePointPos.getZ());
			} else {
				throwMaxHeightPos = new BlockPos(middlePointPos.getX(), middlePointPos.getY() - (middlePointPos.getY() / 2), middlePointPos.getZ());
			}
			if(rockEntity.getBlockPos().getY() < throwMaxHeightPos.getY()) {
				rockEntity.setVelocity(rockEntity.getVelocity().x / 2, -(rockEntity.getVelocity().y / 2), rockEntity.getVelocity().z / 2);
			}
		}*/
		int i = 0;
		float distanceHalf = throwMaxHeightPos.getY() / 2;
		float travelDist = 0;
		double x = 0, y = 0, z = 0;
		while(rockEntity.getBlockPos().getY() <= throwMaxHeightPos.getY()) {
			travelDist = travelDist + distanceHalf;
			if(i == 0 || i == 1) {
				x = -2.0D;
				y = -2.0D;
				z = -2.0D;
			}
			if(rockEntity.getBlockPos().getY() < travelDist) {
				rockEntity.setVelocity(x, y, z);
			} else {
				distanceHalf = distanceHalf / 2;
				x = x / 2;
				y = y / 2;
				z = z / 2;
			}
			if(rockEntity.getBlockPos().getY() == throwMaxHeightPos.getY()) {
				
			}
			i = i + 1;
		}
	}
}
