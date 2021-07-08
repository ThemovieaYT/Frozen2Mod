package net.themoviea.frozeniimod.entity.ai;

import net.minecraft.util.math.Vec3d;

public class Vec3dExtendedManager extends Vec3d {

	public Vec3dExtendedManager(double x, double y, double z) {
		super(x, y, z);
	}

	public Vec3d multiplyX(double multiX) {
		return new Vec3d(this.x * multiX, this.y, this.z);
	}
	
	public Vec3d multiplyY(double multiY) {
		return new Vec3d(this.x, this.y * multiY, this.z);
	}
	
	public Vec3d multiplyZ(double multiZ) {
		return new Vec3d(this.x, this.y, this.z * multiZ);
	}
	
	public Vec3d setY(double y) {
		return new Vec3d(this.x, y, this.z);
	}
}
