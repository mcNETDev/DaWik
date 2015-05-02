package de.DaWik.DaWik.Config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class ConfigManager {

	// Config Stuff
	public static String[] dimList;
	public static String[] players;

	public static int miningWorldBiomeID;
	public static int miningWorldHeight;

	public static boolean miningWorldDisableLakes = true;
	public static boolean devMode = false;

	private Configuration config;

	public ConfigManager(File f) {
		config = new Configuration(f);
		config.load();

	}

	public void load() {
		System.out.println("Config Loading..");
		ConfigManager.dimList = config.get("worlds", "Dimensions", new String[] { "MiningWorld;124;MINING", "Admin;123;NORMAL" }).getStringList();
		ConfigManager.players = config.get("Permissions", "ExtraPermissions", new String[] { "mcNET", "you", "youFriend" }, "This Players are alowed for use /tpDim").getStringList();

		ConfigManager.miningWorldBiomeID = config.get("world", "miningWorldBiomeID", 75, "The id from the Mining World Biome").getInt();
		ConfigManager.miningWorldHeight = config.get("world", "miningWorldHeight", 80).getInt();

		ConfigManager.miningWorldDisableLakes = config.get("world", "miningWorldDisableLakes", true).getBoolean();

		ConfigManager.devMode = config.get("DevStuff", "DEVMODE", false, "Activate the dev mode").getBoolean();
		if (config.hasChanged()) {
			config.save();
		}
		System.out.println("Config Loaded!");

	}

}
