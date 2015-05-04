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
import de.DaWik.DaWik.Config.ConfigManager;
import de.DaWik.DaWik.World.LakeEvent;
import de.DaWik.DaWik.World.MiningBiome;
import de.DaWik.DaWik.fuel.FuelManager;
import de.DaWik.DaWik.init.DaWikBlocks;
import de.DaWik.DaWik.init.DaWikItems;

public class DaWikRegistry {

	public static void load(FMLInitializationEvent event) {
		GameRegistry.addShapedRecipe(new ItemStack(DaWikItems.pickaxeBreaker), "sw ", "wsw", " ws", Character.valueOf('s'), Items.iron_ingot, Character.valueOf('w'), Items.flint);
		if (ConfigManager.enableStorageBlock && ConfigManager.enableCamoMineCrafting) {
			GameRegistry.addShapedRecipe(new ItemStack(DaWikBlocks.blockStoragex16), "kkk", "kek", "kkk", Character.valueOf('k'), Blocks.chest, Character.valueOf('e'), Blocks.iron_block);
			GameRegistry.addShapedRecipe(new ItemStack(DaWikBlocks.blockStoragex64), "ccc", "ccc", "ccc", Character.valueOf('c'), DaWikBlocks.blockStoragex16);
			GameRegistry.addShapedRecipe(new ItemStack(DaWikBlocks.blockStoragex128), "ccc", "ccc", "ccc", Character.valueOf('c'), DaWikBlocks.blockStoragex64);
			GameRegistry.addShapedRecipe(new ItemStack(DaWikBlocks.blockStoragex512), "ccc", "ccc", "ccc", Character.valueOf('c'), DaWikBlocks.blockStoragex128);
			GameRegistry.addShapedRecipe(new ItemStack(DaWikBlocks.blockStoragex2048), "ccc", "ccc", "ccc", Character.valueOf('c'), DaWikBlocks.blockStoragex512);
			GameRegistry.addShapedRecipe(new ItemStack(DaWikBlocks.blockStoragex20480), "ccc", "ccc", "ccc", Character.valueOf('c'), DaWikBlocks.blockStoragex2048);
			GameRegistry.addShapedRecipe(new ItemStack(DaWikBlocks.blockStoragex1000000), "ccc", "ccc", "ccc", Character.valueOf('c'), DaWikBlocks.blockStoragex20480);
		}
		if (ConfigManager.enableCamoMine && ConfigManager.enableCamoMineCrafting) {
			GameRegistry.addShapedRecipe(new ItemStack(DaWikBlocks.blockCamoMine), "ttt", "tst", "ttt", Character.valueOf('t'), Blocks.tnt, Character.valueOf('s'), Blocks.stone);
		}

		GameRegistry.addShapedRecipe(new ItemStack(DaWikBlocks.advWood), "ttt", "twt", "ttt", Character.valueOf('t'), Blocks.leaves, Character.valueOf('w'), Blocks.log);
		GameRegistry.addShapedRecipe(new ItemStack(DaWikBlocks.advWood), "ttt", "twt", "ttt", Character.valueOf('t'), Blocks.leaves2, Character.valueOf('w'), Blocks.log);
		GameRegistry.addShapedRecipe(new ItemStack(DaWikItems.woodSeed), "ttt", "twt", "ttt", Character.valueOf('t'), Items.wheat_seeds, Character.valueOf('w'), Blocks.sapling);

		// Hander
		GameRegistry.registerFuelHandler(new FuelManager());

		// Biome
		MinecraftForge.TERRAIN_GEN_BUS.register(new LakeEvent());
		MiningBiome.instance = new MiningBiome();
		if (ConfigManager.enableStrongholdInMiningWorld) {
			BiomeManager.addStrongholdBiome(MiningBiome.instance);
		}
		if (ConfigManager.enableVillageInMiningWorld) {
			BiomeManager.addVillageBiome(MiningBiome.instance, true);
		}
		BiomeDictionary.registerBiomeType(MiningBiome.instance, BiomeDictionary.Type.MOUNTAIN);

	}

	public static void postInit(FMLPostInitializationEvent event) {

	}
}
