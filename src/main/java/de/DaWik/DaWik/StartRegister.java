package de.DaWik.DaWik;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import de.DaWik.DaWik.Items.DaWikItems;
import de.DaWik.DaWik.Items.ItemPickaxeTest;
import de.DaWik.DaWik.World.LakeEvent;
import de.DaWik.DaWik.World.MiningBiome;
import de.DaWik.DaWik.fuel.FuelManager;

public class StartRegister {

	public static void registerPreInit(FMLPreInitializationEvent event) {

		// TOOLS
		DaWikItems.pickaxeTest = new ItemPickaxeTest(null);
		GameRegistry.registerItem(DaWikItems.pickaxeTest, "pickaxeTest");

	}

	public static void registerLoad(FMLInitializationEvent event) {
		GameRegistry.addShapedRecipe(new ItemStack(DaWikItems.pickaxeTest), "sw ", "wsw", " ws", Character.valueOf('s'), Items.stick, Character.valueOf('w'), Blocks.planks);

		// Hander
		GameRegistry.registerFuelHandler(new FuelManager());

		// Biome
		MinecraftForge.TERRAIN_GEN_BUS.register(new LakeEvent());
		MiningBiome.instance = new MiningBiome();
		BiomeManager.addStrongholdBiome(MiningBiome.instance);
		BiomeDictionary.registerBiomeType(MiningBiome.instance, BiomeDictionary.Type.MOUNTAIN);

	}

	public static void registerPostInit(FMLPostInitializationEvent event) {

	}
}
