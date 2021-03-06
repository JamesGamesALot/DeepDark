
package net.mcreator.armorfromthedark.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.block.BlockState;

import net.mcreator.armorfromthedark.itemgroup.DeepDarkItemGroup;
import net.mcreator.armorfromthedark.ArmorFromTheDarkModElements;

@ArmorFromTheDarkModElements.ModElement.Tag
public class DeepdarkessenceItem extends ArmorFromTheDarkModElements.ModElement {
	@ObjectHolder("armor_from_the_dark:deepdarkessence")
	public static final Item block = null;
	public DeepdarkessenceItem(ArmorFromTheDarkModElements instance) {
		super(instance, 8);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(DeepDarkItemGroup.tab).maxStackSize(64).isImmuneToFire().rarity(Rarity.EPIC));
			setRegistryName("deepdarkessence");
		}

		@Override
		public int getItemEnchantability() {
			return 0;
		}

		@Override
		public int getUseDuration(ItemStack itemstack) {
			return 0;
		}

		@Override
		public float getDestroySpeed(ItemStack par1ItemStack, BlockState par2Block) {
			return 1F;
		}
	}
}
