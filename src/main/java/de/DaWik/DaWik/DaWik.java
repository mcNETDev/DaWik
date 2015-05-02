package de.DaWik.DaWik;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.MinecraftForge;
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
import de.DaWik.DaWik.Config.ConfigManager;
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

	// ConfigManager
	private ConfigManager config;

	// Tab
	public static CreativeTabs creativeTab = new CreativeTabs("testTab") {

		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem() {
			return DaWik.pickaxeTest;
		}
	};

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		config = new ConfigManager(event.getSuggestedConfigurationFile());
		config.load();
		StartRegister.registerPreInit(event);
	}

	@EventHandler
	public void load(FMLInitializationEvent event) {
		DaWik.proxy.registerRenderers();
		StartRegister.registerLoad(event);
		MinecraftForge.TERRAIN_GEN_BUS.register(new LakeEvent());
		GameRegistry.registerFuelHandler(new FuelManager());
		MiningBiome.instance = new MiningBiome();
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

}
