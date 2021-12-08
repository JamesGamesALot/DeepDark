
package net.mcreator.armorfromthedark.itemgroup;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.block.Blocks;

import net.mcreator.armorfromthedark.ArmorFromTheDarkModElements;

@ArmorFromTheDarkModElements.ModElement.Tag
public class DeepDarkItemGroup extends ArmorFromTheDarkModElements.ModElement {
	public DeepDarkItemGroup(ArmorFromTheDarkModElements instance) {
		super(instance, 2);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabdeep_dark") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(Blocks.END_GATEWAY);
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return true;
			}
		}.setBackgroundImageName("item_search.png");
	}
	public static ItemGroup tab;
}
