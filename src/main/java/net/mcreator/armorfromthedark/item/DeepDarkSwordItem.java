
package net.mcreator.armorfromthedark.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.SwordItem;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;

import net.mcreator.armorfromthedark.itemgroup.DeepDarkItemGroup;
import net.mcreator.armorfromthedark.ArmorFromTheDarkModElements;

@ArmorFromTheDarkModElements.ModElement.Tag
public class DeepDarkSwordItem extends ArmorFromTheDarkModElements.ModElement {
	@ObjectHolder("armor_from_the_dark:deep_dark_sword")
	public static final Item block = null;
	public DeepDarkSwordItem(ArmorFromTheDarkModElements instance) {
		super(instance, 15);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new SwordItem(new IItemTier() {
			public int getMaxUses() {
				return 1;
			}

			public float getEfficiency() {
				return 4f;
			}

			public float getAttackDamage() {
				return 2f;
			}

			public int getHarvestLevel() {
				return 50;
			}

			public int getEnchantability() {
				return 2;
			}

			public Ingredient getRepairMaterial() {
				return Ingredient.fromStacks(new ItemStack(Items.TOTEM_OF_UNDYING));
			}
		}, 3, -3f, new Item.Properties().group(DeepDarkItemGroup.tab).isImmuneToFire()) {
		}.setRegistryName("deep_dark_sword"));
	}
}
