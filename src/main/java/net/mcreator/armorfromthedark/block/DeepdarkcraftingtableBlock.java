
package net.mcreator.armorfromthedark.block;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.common.ToolType;

import net.minecraft.world.World;
import net.minecraft.world.IBlockReader;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Hand;
import net.minecraft.util.Direction;
import net.minecraft.util.ActionResultType;
import net.minecraft.loot.LootContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import net.mcreator.armorfromthedark.procedures.DeepdarkcraftingtableOnBlockRightClickedProcedure;
import net.mcreator.armorfromthedark.itemgroup.DeepDarkItemGroup;
import net.mcreator.armorfromthedark.ArmorFromTheDarkModElements;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Collections;

@ArmorFromTheDarkModElements.ModElement.Tag
public class DeepdarkcraftingtableBlock extends ArmorFromTheDarkModElements.ModElement {
	@ObjectHolder("armor_from_the_dark:deepdarkcraftingtable")
	public static final Block block = null;
	public DeepdarkcraftingtableBlock(ArmorFromTheDarkModElements instance) {
		super(instance, 9);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items.add(() -> new BlockItem(block, new Item.Properties().group(DeepDarkItemGroup.tab)).setRegistryName(block.getRegistryName()));
	}
	public static class CustomBlock extends Block {
		public CustomBlock() {
			super(Block.Properties.create(Material.LEAVES).sound(SoundType.WOOD).hardnessAndResistance(1f, 10f).setLightLevel(s -> 0).harvestLevel(2)
					.harvestTool(ToolType.AXE).setRequiresTool());
			setRegistryName("deepdarkcraftingtable");
		}

		@Override
		public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
			return true;
		}

		@Override
		public int getOpacity(BlockState state, IBlockReader worldIn, BlockPos pos) {
			return 0;
		}

		@Override
		public int getFlammability(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
			return 6;
		}

		@Override
		public Block.OffsetType getOffsetType() {
			return Block.OffsetType.XYZ;
		}

		@Override
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
			if (!dropsOriginal.isEmpty())
				return dropsOriginal;
			return Collections.singletonList(new ItemStack(this, 1));
		}

		@Override
		public ActionResultType onBlockActivated(BlockState blockstate, World world, BlockPos pos, PlayerEntity entity, Hand hand,
				BlockRayTraceResult hit) {
			super.onBlockActivated(blockstate, world, pos, entity, hand, hit);
			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();
			double hitX = hit.getHitVec().x;
			double hitY = hit.getHitVec().y;
			double hitZ = hit.getHitVec().z;
			Direction direction = hit.getFace();
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				DeepdarkcraftingtableOnBlockRightClickedProcedure.executeProcedure($_dependencies);
			}
			return ActionResultType.SUCCESS;
		}
	}
}
