package de.DaWik.DaWik.init;

import cpw.mods.fml.common.registry.GameRegistry;
import de.DaWik.DaWik.TileEntity.TileEntityCamoMine;
import de.DaWik.DaWik.TileEntity.TileEntityStoragex1;
import de.DaWik.DaWik.TileEntity.TileSuperChest;

public class DaWikTileEntitys {

	public static void init() {
		GameRegistry.registerTileEntity(TileEntityCamoMine.class, TileEntityCamoMine.ID);
		GameRegistry.registerTileEntity(TileEntityStoragex1.class, TileEntityStoragex1.ID);
		GameRegistry.registerTileEntity(TileSuperChest.class, "TileSuperChest");

	}

}
