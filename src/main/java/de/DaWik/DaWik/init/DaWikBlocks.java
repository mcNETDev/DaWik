package de.DaWik.DaWik.init;

import cpw.mods.fml.common.registry.GameRegistry;
import de.DaWik.DaWik.Blocks.BlockCamoMine;

public class DaWikBlocks {
	public static BlockCamoMine blockCamoMine;

	public static void init() {
		DaWikBlocks.blockCamoMine = new BlockCamoMine();
		GameRegistry.registerBlock(DaWikBlocks.blockCamoMine, BlockCamoMine.UNLOCALNAME);
	}
}
