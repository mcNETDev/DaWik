package de.DaWik.DaWik.World;

import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.eventhandler.Event.Result;
import de.DaWik.DaWik.DaWik;
import de.DaWik.DaWik.DaWikDimensionManager;

public class LakeEvent {
	@SubscribeEvent
	public void onPopulateChunk(PopulateChunkEvent.Populate event) {
		if (DaWikDimensionManager.isMiningDim(event.world.provider.dimensionId)) {
			if (event.type == EventType.LAKE && DaWik.miningWorldDisableLakes) {
				event.setResult(Result.DENY);
			}
		}
	}
}
