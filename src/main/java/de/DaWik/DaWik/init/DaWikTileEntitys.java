package de.DaWik.DaWik.init;

import cpw.mods.fml.common.registry.GameRegistry;
import de.DaWik.DaWik.TileEntity.TileEntityCamoMine;

public class DaWikTileEntitys {

	public static void init() {
		GameRegistry.registerTileEntity(TileEntityCamoMine.class, TileEntityCamoMine.ID);
	}

}