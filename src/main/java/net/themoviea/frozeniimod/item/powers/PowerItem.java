package net.themoviea.frozeniimod.item.powers;

import java.util.List;

import org.jetbrains.annotations.Nullable;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Vanishable;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.themoviea.frozeniimod.Frozen2Mod;
import net.themoviea.frozeniimod.ModNetworkingConstants;
import net.themoviea.frozeniimod.client.powerItem.PowerItemManager;
import net.themoviea.frozeniimod.entity.PowerElement;
import net.themoviea.frozeniimod.item.powers.attack_methods.NoneAttackMethod;
import net.themoviea.frozeniimod.item.powers.attack_methods.PowerAttackMethod;
import net.themoviea.frozeniimod.item.powers.protect_methods.NoneProtectMethod;
import net.themoviea.frozeniimod.item.powers.protect_methods.PowerProtectMethod;
import net.themoviea.frozeniimod.item.powers.use_methods.PowerUseMethod;

public class PowerItem extends Item implements Vanishable {
	private static PowerItem powerItem;
	private PowerElement powerElement;
	@Nullable
	private PowerElement spiritPowerStrength;
	@Nullable
	private PowerElement spiritPowerWeakness;
	private Block block;
	private PowerAttackMethod powerAttackMethod;
	private PowerProtectMethod powerProtectMethod;
	private PowerUseMethod powerUseMethod;
	
	private boolean usableOnBlock, usableOnEntity, attackEntityWithRightClick;
	private boolean protectingPower;
	private boolean isUsedForOtherPurpose;
	
	/*
	 * Constructor for powers that can be formed into one.
	 */
	public PowerItem(PowerProtectMethod protectMethod, PowerAttackMethod attackMethod, boolean attackEntityWithBlockClick, boolean usableOnBlock, boolean usableOnEntity) {
		super(new Item.Settings().group(Frozen2Mod.FROZEN_II_MOD_MISC));
		this.attackEntityWithRightClick = attackEntityWithBlockClick;
		this.usableOnBlock = usableOnBlock;
		this.usableOnEntity = usableOnEntity;
		this.powerProtectMethod = protectMethod;
		this.powerAttackMethod = attackMethod;
		this.powerElement = powerAttackMethod.getAttackMethodElement();
		this.spiritPowerStrength = powerAttackMethod.getPowerStrength();
		this.spiritPowerWeakness = powerAttackMethod.getPowerWeakness();
	}
	
	/*
	 * Constructors for powers that does not use right click attack method.
	 */
	public PowerItem(PowerAttackMethod attackMethod, boolean usableOnBlock, boolean usableOnEntity) {
		this(new NoneProtectMethod(), attackMethod, false, usableOnBlock, usableOnEntity);
	}
	
	/*
	 * Constructor for powers that uses right click attack method only.
	 */
	public PowerItem(PowerAttackMethod attackMethod, boolean attackEntityWithBlockClick) {
		this(new NoneProtectMethod(),  attackMethod, attackEntityWithBlockClick, false, false);
	}
	
	/*
	 * Constructor for powers used for protection.
	 */
	public PowerItem(PowerProtectMethod protectMethod, boolean protectingPower) {
		this(protectMethod, new NoneAttackMethod(), false, false, false);
		this.protectingPower = protectingPower;
	}
	
	/*
	 * Constructors for powers that is used for other purposes.
	 */
	public PowerItem(PowerUseMethod powerUseMethod, boolean isUsedForOtherPurpose) {
		this(new NoneProtectMethod(), new NoneAttackMethod(), false, false, false);
		this.powerUseMethod = powerUseMethod;
		this.isUsedForOtherPurpose = isUsedForOtherPurpose;
	}
	
	@Override
	public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
		// TODO Auto-generated method stub
		super.usageTick(world, user, stack, remainingUseTicks);
	}
	
	/*
	 * This method is usually for protecting the power user from opponent's attack.
	 * It checks if the current power is a protection power, then protects the user
	 * from opponent's power.
	 */
	
	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		ItemStack itemStack = user.getStackInHand(hand);
		user.getItemCooldownManager().set(this, 20);
		if(isAProtectingPower() == true) {
			this.powerProtectMethod.protectFromPower(user, world);
			return TypedActionResult.success(itemStack);
		} else if(isUsedForOtherPurpose() == true) {
			this.powerUseMethod.powerUseCondition(world, user);
			this.powerUseMethod.usePower(user, world);
			return TypedActionResult.success(itemStack);
		}
		return TypedActionResult.fail(itemStack);
	}
	
	
	/*
	 * This method is used when attacking entity that requires clicking on the block.
	 * It gets if the current power is usable on the block, then checks if the attack method
	 * uses block attack method.
	 * Then it will place block from the attack method's placeBlock method, and it will then
	 * attack the entity in some way.
	 */
	public ActionResult useOnBlock(ItemUsageContext context) {
		context.getPlayer().getItemCooldownManager().set(this, 20);
		World world = context.getWorld();
		if(isUsableOnBlock() == true) {
			powerAttackMethod.placeBlock(world);
			return ActionResult.SUCCESS;
		} else if(canAttackEntityWithBlockClick() == true) {
			powerAttackMethod.attackEntityRightClickBlock(world);
			return ActionResult.SUCCESS;
		}
		
		if(world.isClient) {
			PowerItemManager.sendHitBlockPos(powerAttackMethod.getNetworkingIdentifier(), world);
		}
		ServerPlayNetworking.registerGlobalReceiver(powerAttackMethod.getNetworkingIdentifier(), (server, player, handler, buf, sender) -> {
			BlockPos pos = buf.readBlockPos();
			powerAttackMethod.setUser((PlayerEntity)player);
			server.execute(() -> {
				List<Entity> entityList = world.getOtherEntities(player, new Box(new BlockPos(pos.getX() - 10, pos.getY() - 10, pos.getZ() + 10), new BlockPos(pos.getX() + 10, pos.getY() + 10, pos.getZ() - 10)));
				for(int i = 0; i < entityList.size(); i++) {
					if(entityList.get(i).isLiving()) {
						((LivingEntity)entityList.get(i)).setAttacker(powerAttackMethod.getUser());
					}
				}
			});
		});
		return ActionResult.PASS;
	}
	
	@Override
	/*
	 * This method is used when attacking on an entity directly.
	 * It gets if the current power is usable on entity, then checks if the attack method uses
	 * direct entity attack method.
	 * then it will get the element the opponent entity has, then checks if the entity has the
	 * stronger power or the weaker power.
	 */
	public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
		user.getItemCooldownManager().set(this, 20);
		if(isUsableOnEntity() == true) {
			powerAttackMethod.attackEntity(user, entity, hand);
			powerAttackMethod.setUser(user);
			entity.setAttacker(powerAttackMethod.getUser());
			return ActionResult.SUCCESS;
		} else
		return ActionResult.PASS;
	}
	
	public PowerElement getElement() {
		return this.powerElement;
	}
	
	@Override
	public boolean isEffectiveOn(BlockState state) {
		return state.isOf(this.block);
	}
	
	public boolean isEffectiveOnPower(PowerItem power) {
		return power.getPowerWeakness() == this.getPowerStrength();
	}
	
	public PowerAttackMethod getPowerAttackMethod() {
		return this.powerAttackMethod;
	}
	
	public boolean canAttackEntityWithBlockClick() {
		return this.attackEntityWithRightClick;
	}
	
	public boolean isUsedForOtherPurpose() {
		return this.isUsedForOtherPurpose;
	}
	
	public boolean isUsableOnBlock() {
		return this.usableOnBlock;
	}
	
	public boolean isUsableOnEntity() {
		return this.usableOnEntity;
	}
	
	public boolean isAProtectingPower() {
		return this.protectingPower;
	}
	
	public PowerElement getPowerStrength() {
		return this.spiritPowerStrength;
	}
	
	public PowerElement getPowerWeakness() {
		return this.spiritPowerWeakness;
	}
	
	public static PowerItem getPowerItem() {
		return powerItem;
	}
	
	
}
