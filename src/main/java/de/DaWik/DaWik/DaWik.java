package de.DaWik.DaWik;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.DaWik.DaWik.Items.ItemPickaxeTest;
import de.DaWik.DaWik.World.LakeEvent;
import de.DaWik.DaWik.World.MiningBiome;
import de.DaWik.DaWik.fuel.FuelManager;
import de.DaWik.DaWik.proxy.DaWikProxy;

@Mod(modid = "DaWik", name = "DaWik", version = "0.0.1")
public class DaWik {

	@Instance(value = "DaWik")
	public static DaWik instance;

	@SidedProxy(clientSide = "de.DaWik.DaWik.proxy.DaWikClientProxy", serverSide = "de.DaWik.DaWik.proxy.DaWikProxy")
	public static DaWikProxy proxy;

	// Tools
	public static ItemPickaxeTest pickaxeTest;

	// Config
	public static boolean DEVMODE;
	private static String[] LISTPLAYERS;

	// Tab
	public static CreativeTabs creativeTab = new CreativeTabs("testTab") {

		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem() {
			return pickaxeTest;
		}
	};

	public static String[] DIMLIST;

	public static int miningWorldBiomeID;

	public static int miningWorldHigh;

	public static boolean miningWorldDisableLakes;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		System.out.println("Config Loaded!");
		DEVMODE = config.get("DevStuff", "DEVMODE", false, "Activate the dev mode").getBoolean();
		LISTPLAYERS = config.get("Permissions", "ExtraPermissions", new String[] { "mcNET", "konstantantin", "SirFlip", "Rcon", "Console" }, "This Players are alowed for use /tpDim").getStringList();
		config.addCustomCategoryComment("worlds", "1 line for each dim: WORLDNAME;DIMID;TYPE    AdminAge;123;MINING  or  MiningWorld;-123;MINING");
		DIMLIST = config.get("worlds", "DimensionManager", new String[] { "Admin;123;NORMAL", "Mining;124;MINING" }, "se up").getStringList();
		miningWorldBiomeID = config.get("world", "miningWorldBiomeID", 75, "The id from the Mining World Biome").getInt();
		miningWorldHigh = config.get("world", "miningWorldHeight", 80).getInt();
		miningWorldDisableLakes = config.get("world", "miningWorldDisableLakes", true).getBoolean();

		if (config.hasChanged()) {
			config.save();
		}
		StartRegister.registerPreInit(event);
	}

	@EventHandler
	public void load(FMLInitializationEvent event) {
		proxy.registerRenderers();
		StartRegister.registerLoad(event);
		MinecraftForge.TERRAIN_GEN_BUS.register(new LakeEvent());
		GameRegistry.registerFuelHandler(new FuelManager());
		MiningBiome.instance = new MiningBiome(miningWorldBiomeID);
		new DaWikDimensionManager().init();
		BiomeManager.addStrongholdBiome(MiningBiome.instance);
		BiomeDictionary.registerBiomeType(MiningBiome.instance, BiomeDictionary.Type.MOUNTAIN);

	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		StartRegister.registerPostInit(event);
	}

	@EventHandler
	public void serverLoad(FMLServerStartingEvent event) {
		event.registerServerCommand(new DaWikTPCommand());
	}

	public static boolean isExtraPlayer(String name) {
		for (String element : LISTPLAYERS) {
			if (element.equalsIgnoreCase(name)) {
				return true;
			}
		}
		return true;
	}
}
