package de.DaWik.DaWik.Config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import de.DaWik.DaWik.util.Log;

public class ConfigManager {

	// Config Stuff
	public static String[] dimList;
	public static String[] players;

	public static int miningWorldBiomeID;
	public static int miningWorldHeight;

	public static boolean miningWorldDisableLakes;
	public static boolean devMode;
	public static boolean keyExplode;
	public static boolean enableStorageBlockCrafting;
	public static boolean enableStorageBlock;
	public static boolean enableCamoMine;
	public static boolean enableCamoMineCrafting;
	public static boolean enableVillageInMiningWorld;
	public static boolean enableStrongholdInMiningWorld;
	public static boolean allowBoneMealonWoodPlants;
	public static boolean enableWoodPlants;
	private Configuration config;

	public ConfigManager(File f) {
		config = new Configuration(f);
		config.load();

	}

	public void load() {
		System.out.println("Config Loading..");
		config.addCustomCategoryComment("worlds",
				"Format: WORLDNAME;DIMID;WORLDTYPE;SKYCOLOR	WorldTypes are: MINING and NORMAL	SkyColors are DEFAULT,CYAN,YELLOW,BLUE,DARKGREEN,LIGHTGREEN,LIGHTBLUE,BLACK,LIGHTBROWN,PINK,RED,DARKRED,PURPLE and WHITE");
		ConfigManager.dimList = config.get("worlds", "Dimensions", new String[] { "MiningWorld;124;MINING;DEFAULT", "Admin;123;NORMAL;BLUE" }).getStringList();
		ConfigManager.players = config.get("Permissions", "ExtraPermissions", new String[] { "mcNET", "you", "youFriend" }, "This Players are alowed for use /tpDim").getStringList();

		ConfigManager.miningWorldBiomeID = config.get("world", "miningWorldBiomeID", 75, "The id from the Mining World Biome").getInt();
		ConfigManager.miningWorldHeight = config.get("world", "miningWorldHeight", 80).getInt();
		ConfigManager.enableVillageInMiningWorld = config.get("world", "enableVillageInMiningWorld", true).getBoolean();
		ConfigManager.enableStrongholdInMiningWorld = config.get("world", "enableStrongholdInMiningWorld", true).getBoolean();

		ConfigManager.miningWorldDisableLakes = config.get("world", "miningWorldDisableLakes", true).getBoolean();
		ConfigManager.keyExplode = config.get("keys", "EnableExplodeKey", false).getBoolean();

		ConfigManager.enableCamoMine = config.get("blocks", "camoMineEnable", true).getBoolean();
		ConfigManager.enableStorageBlock = config.get("blocks", "storageBlockEnable", true).getBoolean();

		ConfigManager.enableStorageBlockCrafting = config.get("crafting", "storageBlockEnableCrafting", true).getBoolean();
		ConfigManager.enableCamoMineCrafting = config.get("crafting", "camoMineEnableCrafting", true).getBoolean();

		ConfigManager.enableWoodPlants = config.get("world", "enableWoodPlants", true).getBoolean();
		ConfigManager.allowBoneMealonWoodPlants = config.get("world", "allowBoneMealonWoodPlants", true).getBoolean();

		ConfigManager.devMode = config.get("DevStuff", "DEVMODE", false, "Activate the dev mode").getBoolean();
		if (config.hasChanged()) {
			config.save();
		}
		Log.info("Config Loaded");

	}
}
