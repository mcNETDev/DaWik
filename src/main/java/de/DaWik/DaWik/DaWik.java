package de.DaWik.DaWik;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.DaWik.DaWik.Config.ConfigManager;
import de.DaWik.DaWik.init.DaWikBlocks;
import de.DaWik.DaWik.init.DaWikItems;
import de.DaWik.DaWik.init.DaWikTileEntitys;
import de.DaWik.DaWik.mod.ModCompact;
import de.DaWik.DaWik.network.DescriptionHandler;
import de.DaWik.DaWik.network.NETWORKManager;
import de.DaWik.DaWik.network.NetworkHandler;
import de.DaWik.DaWik.proxy.DaWikProxy;
import de.DaWik.DaWik.util.Log;

@Mod(modid = "DaWik", name = "DaWik", version = "0.1.0", dependencies = "required-after:CoFHCore@[1.7.10R3.0.0,);after:MineFactoryReloaded")
public class DaWik {

	@Instance(value = "DaWik")
	public static DaWik instance;

	@SidedProxy(clientSide = "de.DaWik.DaWik.proxy.DaWikClientProxy", serverSide = "de.DaWik.DaWik.proxy.DaWikServerProxy")
	public static DaWikProxy proxy;

	// ConfigManager
	private ConfigManager config;
	// Tab
	public static CreativeTabs creativeTab = new CreativeTabs("testTab") {

		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem() {
			return DaWikItems.pickaxeBreaker;
		}
	};

	public static SimpleNetworkWrapper network;

	public static String networkChannelName = "DaWikNC";

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		DaWik.proxy.preInit();
		config = new ConfigManager(event.getSuggestedConfigurationFile());
		config.load();
		DaWikBlocks.init();
		DaWikItems.init();
		DaWikTileEntitys.init();
		NetworkHandler.init();
		DescriptionHandler.init();
		ModCompact.init();
		NETWORKManager.init();
		Log.info("Pre Init Complete");
	}

	@EventHandler
	public void load(FMLInitializationEvent event) {
		DaWik.proxy.init();
		DaWikItems.load();
		DaWikRegistry.load(event);
		DaWikDimensionManager.init();
		ModCompact.load();
		Log.info("Pre init Complete");
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		DaWik.proxy.postInit();
		DaWik.proxy.registerRenderers();
		ModCompact.postInit();
		DaWikRegistry.postInit(event);
		Log.info("Post Init Complete");
	}

	@EventHandler
	public void serverLoad(FMLServerStartingEvent event) {
		event.registerServerCommand(new DaWikTPCommand());
	}

}
