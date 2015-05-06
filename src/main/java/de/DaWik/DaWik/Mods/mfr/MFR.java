package de.DaWik.DaWik.Mods.mfr;

import powercrystals.minefactoryreloaded.api.FactoryRegistry;
import de.DaWik.DaWik.init.DaWikBlocks;
import de.DaWik.DaWik.init.DaWikItems;

public class MFR {

	public static void init() {
		Plantable plantable = new Plantable(DaWikItems.woodSeed);
		FactoryRegistry.sendMessage("registerPlantable", plantable);
		Harvestable harvestable = new Harvestable(DaWikBlocks.woodCrop);
		FactoryRegistry.sendMessage("registerHarvestable", harvestable);

	}
}
