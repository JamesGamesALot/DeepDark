
package net.mcreator.armorfromthedark.block;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.common.property.Properties;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.IBlockReader;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.state.StateContainer;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.particles.IParticleData;
import net.minecraft.item.Rarity;
import net.minecraft.item.Items;
import net.minecraft.item.Item;
import net.minecraft.item.BucketItem;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.block.material.Material;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import net.mcreator.armorfromthedark.itemgroup.DeepDarkItemGroup;
import net.mcreator.armorfromthedark.ArmorFromTheDarkModElements;

@ArmorFromTheDarkModElements.ModElement.Tag
public class DeepdarkgooBlock extends ArmorFromTheDarkModElements.ModElement {
	@ObjectHolder("armor_from_the_dark:deepdarkgoo")
	public static final FlowingFluidBlock block = null;
	@ObjectHolder("armor_from_the_dark:deepdarkgoo_bucket")
	public static final Item bucket = null;
	public static FlowingFluid flowing = null;
	public static FlowingFluid still = null;
	private ForgeFlowingFluid.Properties fluidproperties = null;
	public DeepdarkgooBlock(ArmorFromTheDarkModElements instance) {
		super(instance, 14);
		FMLJavaModLoadingContext.get().getModEventBus().register(new FluidRegisterHandler());
	}
	private static class FluidRegisterHandler {
		@SubscribeEvent
		public void registerFluids(RegistryEvent.Register<Fluid> event) {
			event.getRegistry().register(still);
			event.getRegistry().register(flowing);
		}
	}
	@Override
	@OnlyIn(Dist.CLIENT)
	public void clientLoad(FMLClientSetupEvent event) {
		RenderTypeLookup.setRenderLayer(still, RenderType.getTranslucent());
		RenderTypeLookup.setRenderLayer(flowing, RenderType.getTranslucent());
	}

	@Override
	public void initElements() {
		fluidproperties = new ForgeFlowingFluid.Properties(() -> still, () -> flowing, FluidAttributes
				.builder(new ResourceLocation("armor_from_the_dark:blocks/deepdarkgoo"),
						new ResourceLocation("armor_from_the_dark:blocks/deepdarkgooflowing"))
				.luminosity(0).density(-1000).viscosity(1).temperature(300).rarity(Rarity.EPIC)
				.sound(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.dispenser.launch")))).explosionResistance(100f).canMultiply()
						.tickRate(1).levelDecreasePerBlock(1).slopeFindDistance(1).bucket(() -> bucket).block(() -> block);
		still = (FlowingFluid) new CustomFlowingFluid.Source(fluidproperties).setRegistryName("deepdarkgoo");
		flowing = (FlowingFluid) new CustomFlowingFluid.Flowing(fluidproperties).setRegistryName("deepdarkgoo_flowing");
		elements.blocks.add(() -> new FlowingFluidBlock(still, Block.Properties.create(Material.WATER).hardnessAndResistance(100f)
				.setNeedsPostProcessing((bs, br, bp) -> true).setEmmisiveRendering((bs, br, bp) -> true).setLightLevel(s -> 0)) {
			@Override
			public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
				return true;
			}
		}.setRegistryName("deepdarkgoo"));
		elements.items.add(() -> new BucketItem(still,
				new Item.Properties().containerItem(Items.BUCKET).maxStackSize(1).group(DeepDarkItemGroup.tab).rarity(Rarity.EPIC))
						.setRegistryName("deepdarkgoo_bucket"));
	}
	public static abstract class CustomFlowingFluid extends ForgeFlowingFluid {
		public CustomFlowingFluid(Properties properties) {
			super(properties);
		}

		@OnlyIn(Dist.CLIENT)
		@Override
		public IParticleData getDripParticleData() {
			return ParticleTypes.ENCHANT;
		}

		@Override
		public Vector3d getFlow(IBlockReader world, BlockPos pos, FluidState fluidstate) {
			return super.getFlow(world, pos, fluidstate).scale(-1);
		}
		public static class Source extends CustomFlowingFluid {
			public Source(Properties properties) {
				super(properties);
			}

			public int getLevel(FluidState state) {
				return 8;
			}

			public boolean isSource(FluidState state) {
				return true;
			}
		}

		public static class Flowing extends CustomFlowingFluid {
			public Flowing(Properties properties) {
				super(properties);
			}

			protected void fillStateContainer(StateContainer.Builder<Fluid, FluidState> builder) {
				super.fillStateContainer(builder);
				builder.add(LEVEL_1_8);
			}

			public int getLevel(FluidState state) {
				return state.get(LEVEL_1_8);
			}

			public boolean isSource(FluidState state) {
				return false;
			}
		}
	}
}
