package de.DaWik.DaWik.init;

import cpw.mods.fml.common.registry.GameRegistry;
import de.DaWik.DaWik.Blocks.BlockAdvancedWood;
import de.DaWik.DaWik.Blocks.BlockCamoMine;
import de.DaWik.DaWik.Blocks.BlockStoragex;
import de.DaWik.DaWik.Blocks.BlockWorkSurface;
import de.DaWik.DaWik.Blocks.crops.WoodCrop;
import de.DaWik.DaWik.Config.ConfigManager;

public class DaWikBlocks {
	public static BlockCamoMine blockCamoMine;
	public static BlockStoragex blockStoragex16;
	public static BlockStoragex blockStoragex64;
	public static BlockStoragex blockStoragex128;
	public static BlockStoragex blockStoragex512;
	public static BlockStoragex blockStoragex2048;
	public static BlockStoragex blockStoragex20480;
	public static BlockStoragex blockStoragex1000000;
	public static WoodCrop woodCrop;
	public static BlockAdvancedWood advWood;
	public static BlockWorkSurface workSurface;

	public static void init() {
		if (ConfigManager.enableCamoMine) {
			DaWikBlocks.blockCamoMine = new BlockCamoMine();
			GameRegistry.registerBlock(DaWikBlocks.blockCamoMine, BlockCamoMine.UNLOCALNAME);
		}

		if (ConfigManager.enableStorageBlock) {
			DaWikBlocks.blockStoragex16 = new BlockStoragex(16);
			GameRegistry.registerBlock(DaWikBlocks.blockStoragex16, DaWikBlocks.blockStoragex16.UNLOCALNAME);
			DaWikBlocks.blockStoragex64 = new BlockStoragex(64);
			GameRegistry.registerBlock(DaWikBlocks.blockStoragex64, DaWikBlocks.blockStoragex64.UNLOCALNAME);
			DaWikBlocks.blockStoragex128 = new BlockStoragex(128);
			GameRegistry.registerBlock(DaWikBlocks.blockStoragex128, DaWikBlocks.blockStoragex128.UNLOCALNAME);
			DaWikBlocks.blockStoragex512 = new BlockStoragex(512);
			GameRegistry.registerBlock(DaWikBlocks.blockStoragex512, DaWikBlocks.blockStoragex512.UNLOCALNAME);
			DaWikBlocks.blockStoragex2048 = new BlockStoragex(2048);
			GameRegistry.registerBlock(DaWikBlocks.blockStoragex2048, DaWikBlocks.blockStoragex2048.UNLOCALNAME);
			DaWikBlocks.blockStoragex20480 = new BlockStoragex(20480);
			GameRegistry.registerBlock(DaWikBlocks.blockStoragex20480, DaWikBlocks.blockStoragex20480.UNLOCALNAME);
			DaWikBlocks.blockStoragex1000000 = new BlockStoragex(1000000);
			GameRegistry.registerBlock(DaWikBlocks.blockStoragex1000000, DaWikBlocks.blockStoragex1000000.UNLOCALNAME);
		}
		if (ConfigManager.enableWoodPlants) {
			DaWikBlocks.woodCrop = new WoodCrop();
			GameRegistry.registerBlock(DaWikBlocks.woodCrop, DaWikBlocks.woodCrop.UNLOCALNAME);
		}
		DaWikBlocks.advWood = new BlockAdvancedWood();
		GameRegistry.registerBlock(DaWikBlocks.advWood, BlockAdvancedWood.UNLOCALNAME);
		DaWikBlocks.workSurface = new BlockWorkSurface();
		GameRegistry.registerBlock(DaWikBlocks.workSurface, DaWikBlocks.workSurface.getUnlocalizedName());
	}
}
