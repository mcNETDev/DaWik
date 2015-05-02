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
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.DaWik.DaWik.Config.ConfigManager;
import de.DaWik.DaWik.Items.DaWikItems;
import de.DaWik.DaWik.proxy.DaWikProxy;

@Mod(modid = "DaWik", name = "DaWik", version = "0.0.1")
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
			return DaWikItems.pickaxeTest;
		}
	};

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		DaWik.proxy.preInit();
		config = new ConfigManager(event.getSuggestedConfigurationFile());
		config.load();
		StartRegister.registerPreInit(event);
	}

	@EventHandler
	public void load(FMLInitializationEvent event) {
		DaWik.proxy.init();
		StartRegister.registerLoad(event);
		new DaWikDimensionManager().init();

	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		DaWik.proxy.postInit();
		StartRegister.registerPostInit(event);
	}

	@EventHandler
	public void serverLoad(FMLServerStartingEvent event) {
		event.registerServerCommand(new DaWikTPCommand());
	}

}
