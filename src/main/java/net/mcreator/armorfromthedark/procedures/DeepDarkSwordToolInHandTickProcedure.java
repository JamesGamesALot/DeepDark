package net.mcreator.armorfromthedark.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.Hand;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.EnchantmentHelper;

import net.mcreator.armorfromthedark.item.DeepDarkSwordItem;
import net.mcreator.armorfromthedark.ArmorFromTheDarkMod;

import java.util.Map;
import java.util.HashMap;

public class DeepDarkSwordToolInHandTickProcedure {
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void onItemDestroyed(PlayerDestroyItemEvent event) {
			Entity entity = event.getPlayer();
			double i = entity.getPosX();
			double j = entity.getPosY();
			double k = entity.getPosZ();
			ItemStack itemstack = event.getOriginal();
			Map<String, Object> dependencies = new HashMap<>();
			dependencies.put("x", i);
			dependencies.put("y", j);
			dependencies.put("z", k);
			dependencies.put("world", entity.world);
			dependencies.put("entity", entity);
			dependencies.put("event", event);
			dependencies.put("itemstack", itemstack);
			executeProcedure(dependencies);
		}
	}
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				ArmorFromTheDarkMod.LOGGER.warn("Failed to load dependency entity for procedure DeepDarkSwordToolInHandTick!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				ArmorFromTheDarkMod.LOGGER.warn("Failed to load dependency x for procedure DeepDarkSwordToolInHandTick!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				ArmorFromTheDarkMod.LOGGER.warn("Failed to load dependency y for procedure DeepDarkSwordToolInHandTick!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				ArmorFromTheDarkMod.LOGGER.warn("Failed to load dependency z for procedure DeepDarkSwordToolInHandTick!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				ArmorFromTheDarkMod.LOGGER.warn("Failed to load dependency world for procedure DeepDarkSwordToolInHandTick!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((entity.isOnGround())) {
			if (entity instanceof LivingEntity) {
				ItemStack _setstack = ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY);
				_setstack.setCount((int) 1);
				((LivingEntity) entity).setHeldItem(Hand.MAIN_HAND, _setstack);
				if (entity instanceof ServerPlayerEntity)
					((ServerPlayerEntity) entity).inventory.markDirty();
			}
			if (world instanceof World && !world.isRemote()) {
				ItemEntity entityToSpawn = new ItemEntity((World) world, x, y, z, (new ItemStack(DeepDarkSwordItem.block).copy()));
				entityToSpawn.setPickupDelay((int) 0);
				entityToSpawn.setNoDespawn();
				world.addEntity(entityToSpawn);
			}
			if (((EnchantmentHelper.getEnchantmentLevel(Enchantments.PROTECTION, new ItemStack(DeepDarkSwordItem.block))) == 0)) {
				(((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY)).addEnchantment(
						Enchantments.SHARPNESS,
						(int) Math.max(1, Math.pow(
								(1 + Math.round(((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY))
										.getDamage()))),
								((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY))
										.getDamage()))));
				(((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY))
						.addEnchantment(Enchantments.SHARPNESS, (int) (1 + (EnchantmentHelper.getEnchantmentLevel(Enchantments.SHARPNESS,
								((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY)))));
			}
		}
	}
}
