package net.themoviea.frozeniimod.client.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.ParticleTextureSheet;
import net.minecraft.client.particle.SpriteBillboardParticle;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;

//Code inspired / copied from Falling Leaves Mod made by RandomMCSomethin
public class LeafFallParticle extends SpriteBillboardParticle {

	private final float rotateFactor;

    private LeafFallParticle(ClientWorld clientWorld, double x, double y, double z, double r, double g, double b, SpriteProvider provider) {
        super(clientWorld, x, y, z, r, g, b); // Note: will set velocity to (r, g, b)
        this.setSprite(provider);

        this.collidesWithWorld = true;
        this.gravityStrength = 0.1F;
        this.maxAge = 80;

        this.velocityX *= 0.3F;
        this.velocityY *= 0.0F;
        this.velocityZ *= 0.3F;
      
        this.rotateFactor = ((float) Math.random() - 0.5F) * 0.01F;
        this.scale = 0.1f;
    }

    public void tick() {
        super.tick();

        if (this.age < 2) {
            this.velocityY = 0;
        }

        if (this.age > this.maxAge - 1 / 0.06F) {
            if (this.colorAlpha > 0.06F) {
                this.colorAlpha -= 0.06F;
            } else {
                this.markDead();
            }
        }

        this.prevAngle = this.angle;

        if (!this.onGround && !this.world.getFluidState(new BlockPos(this.x, this.y, this.z)).isIn(FluidTags.WATER)) {
            this.angle += Math.PI * Math.sin(this.rotateFactor * this.age) / 2;
        }

        if (this.world.getFluidState(new BlockPos(this.x, this.y, this.z)).isIn(FluidTags.WATER)) {
            this.velocityY = 0;
            this.gravityStrength = 0;
        } else {
            this.gravityStrength = 0.1F;
        }
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Environment(EnvType.CLIENT)
    public static class DefaultFactory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider provider;

        public DefaultFactory(SpriteProvider provider) {
            this.provider = provider;
        }

        @Override
        public Particle createParticle(DefaultParticleType parameters, ClientWorld world, double x, double y, double z, double r, double g, double b) {
            return new LeafFallParticle(world, x, y, z, r, g, b, this.provider);
        }
    }

}
