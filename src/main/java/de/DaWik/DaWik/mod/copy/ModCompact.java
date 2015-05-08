package de.DaWik.DaWik.mod.copy;

import cpw.mods.fml.common.Loader;
import de.DaWik.DaWik.Mods.mfr.MFR;

public class ModCompact {

	public static void init() {
		if (Loader.isModLoaded("MineFactoryReloaded")) {
			MFR.init();
		}
	}

	public static void postInit() {
	}

	public static void load() {
	}

}
