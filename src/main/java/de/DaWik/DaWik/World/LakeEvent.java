package de.DaWik.DaWik.World;

import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import de.DaWik.DaWik.DaWikDimensionManager;
import de.DaWik.DaWik.Config.ConfigManager;

public class LakeEvent {
	@SubscribeEvent
	public void onPopulateChunk(PopulateChunkEvent.Populate event) {
		if (DaWikDimensionManager.isMiningDim(event.world.provider.dimensionId)) {
			if ((event.type == EventType.LAKE) && ConfigManager.miningWorldDisableLakes) {
				event.setResult(Result.DENY);
			}
		}
	}
}
