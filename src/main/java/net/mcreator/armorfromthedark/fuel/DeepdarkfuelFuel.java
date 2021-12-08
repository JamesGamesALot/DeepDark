
package net.mcreator.armorfromthedark.fuel;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;

import net.mcreator.armorfromthedark.item.DeepdarkessenceItem;

@Mod.EventBusSubscriber
public class DeepdarkfuelFuel {
	@SubscribeEvent
	public static void furnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
		if (event.getItemStack().getItem() == DeepdarkessenceItem.block)
			event.setBurnTime(69420);
	}
}
