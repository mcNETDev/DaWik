package de.DaWik.DaWik;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import de.DaWik.DaWik.World.LakeEvent;
import de.DaWik.DaWik.World.MiningBiome;
import de.DaWik.DaWik.fuel.FuelManager;
import de.DaWik.DaWik.init.DaWikItems;

public class DaWikRegistry {

	public static void load(FMLInitializationEvent event) {
		GameRegistry.addShapedRecipe(new ItemStack(DaWikItems.pickaxeTest), "sw ", "wsw", " ws", Character.valueOf('s'), Items.stick, Character.valueOf('w'), Blocks.planks);

		// Hander
		GameRegistry.registerFuelHandler(new FuelManager());

		// Biome
		MinecraftForge.TERRAIN_GEN_BUS.register(new LakeEvent());
		MiningBiome.instance = new MiningBiome();
		BiomeManager.addStrongholdBiome(MiningBiome.instance);
		BiomeDictionary.registerBiomeType(MiningBiome.instance, BiomeDictionary.Type.MOUNTAIN);

	}

	public static void postInit(FMLPostInitializationEvent event) {

	}
}
