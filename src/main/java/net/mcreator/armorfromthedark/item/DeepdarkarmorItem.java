
package net.mcreator.armorfromthedark.item;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.util.ResourceLocation;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ArmorItem;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.Entity;

import net.mcreator.armorfromthedark.itemgroup.DeepDarkItemGroup;
import net.mcreator.armorfromthedark.ArmorFromTheDarkModElements;

@ArmorFromTheDarkModElements.ModElement.Tag
public class DeepdarkarmorItem extends ArmorFromTheDarkModElements.ModElement {
	@ObjectHolder("armor_from_the_dark:deepdarkarmor_helmet")
	public static final Item helmet = null;
	@ObjectHolder("armor_from_the_dark:deepdarkarmor_chestplate")
	public static final Item body = null;
	@ObjectHolder("armor_from_the_dark:deepdarkarmor_leggings")
	public static final Item legs = null;
	@ObjectHolder("armor_from_the_dark:deepdarkarmor_boots")
	public static final Item boots = null;
	public DeepdarkarmorItem(ArmorFromTheDarkModElements instance) {
		super(instance, 1);
	}

	@Override
	public void initElements() {
		IArmorMaterial armormaterial = new IArmorMaterial() {
			@Override
			public int getDurability(EquipmentSlotType slot) {
				return new int[]{13, 15, 16, 11}[slot.getIndex()] * 52;
			}

			@Override
			public int getDamageReductionAmount(EquipmentSlotType slot) {
				return new int[]{6, 12, 16, 6}[slot.getIndex()];
			}

			@Override
			public int getEnchantability() {
				return 69;
			}

			@Override
			public net.minecraft.util.SoundEvent getSoundEvent() {
				return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.bell.use"));
			}

			@Override
			public Ingredient getRepairMaterial() {
				return Ingredient.fromStacks(new ItemStack(Items.END_CRYSTAL));
			}

			@OnlyIn(Dist.CLIENT)
			@Override
			public String getName() {
				return "deepdarkarmor";
			}

			@Override
			public float getToughness() {
				return 6f;
			}

			@Override
			public float getKnockbackResistance() {
				return 0.1f;
			}
		};
		elements.items
				.add(() -> new ArmorItem(armormaterial, EquipmentSlotType.HEAD, new Item.Properties().group(DeepDarkItemGroup.tab).isImmuneToFire()) {
					@Override
					public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
						return "armor_from_the_dark:textures/models/armor/darkarmortext_layer_" + (slot == EquipmentSlotType.LEGS ? "2" : "1")
								+ ".png";
					}
				}.setRegistryName("deepdarkarmor_helmet"));
		elements.items.add(
				() -> new ArmorItem(armormaterial, EquipmentSlotType.CHEST, new Item.Properties().group(DeepDarkItemGroup.tab).isImmuneToFire()) {
					@Override
					public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
						return "armor_from_the_dark:textures/models/armor/darkarmortext_layer_" + (slot == EquipmentSlotType.LEGS ? "2" : "1")
								+ ".png";
					}
				}.setRegistryName("deepdarkarmor_chestplate"));
		elements.items
				.add(() -> new ArmorItem(armormaterial, EquipmentSlotType.LEGS, new Item.Properties().group(DeepDarkItemGroup.tab).isImmuneToFire()) {
					@Override
					public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
						return "armor_from_the_dark:textures/models/armor/darkarmortext_layer_" + (slot == EquipmentSlotType.LEGS ? "2" : "1")
								+ ".png";
					}
				}.setRegistryName("deepdarkarmor_leggings"));
		elements.items
				.add(() -> new ArmorItem(armormaterial, EquipmentSlotType.FEET, new Item.Properties().group(DeepDarkItemGroup.tab).isImmuneToFire()) {
					@Override
					public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
						return "armor_from_the_dark:textures/models/armor/darkarmortext_layer_" + (slot == EquipmentSlotType.LEGS ? "2" : "1")
								+ ".png";
					}
				}.setRegistryName("deepdarkarmor_boots"));
	}
}
