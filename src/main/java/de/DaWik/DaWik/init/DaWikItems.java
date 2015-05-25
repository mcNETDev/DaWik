package de.DaWik.DaWik.init;

import net.minecraft.item.ItemStack;
import cofh.lib.util.helpers.EnergyHelper;
import cpw.mods.fml.common.registry.GameRegistry;
import de.DaWik.DaWik.Items.ItemPickaxeBreaker;
import de.DaWik.DaWik.Items.ItemSuperFood;
import de.DaWik.DaWik.Items.ItemWoodSeed;

public class DaWikItems {
	// Tools
	public static ItemPickaxeBreaker pickaxeBreaker;
	public static ItemWoodSeed woodSeed;
	public static ItemSuperFood itemSuperFood;
	public static ItemStack stackSuperFood;

	public static void init() {
		DaWikItems.pickaxeBreaker = new ItemPickaxeBreaker();
		GameRegistry.registerItem(DaWikItems.pickaxeBreaker, ItemPickaxeBreaker.UNLOCALNAME);
		DaWikItems.woodSeed = new ItemWoodSeed();
		GameRegistry.registerItem(DaWikItems.woodSeed, ItemWoodSeed.UNLOCALNAME);
		DaWikItems.itemSuperFood = new ItemSuperFood();
		GameRegistry.registerItem(DaWikItems.itemSuperFood, DaWikItems.itemSuperFood.getUnlocalizedName());
	}

	public static void load() {
		DaWikItems.stackSuperFood = EnergyHelper.setDefaultEnergyTag(new ItemStack(DaWikItems.itemSuperFood), 0);
		GameRegistry.registerCustomItemStack("stackSuperFood", DaWikItems.stackSuperFood);
	}
}
