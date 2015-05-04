package de.DaWik.DaWik.init;

import cpw.mods.fml.common.registry.GameRegistry;
import de.DaWik.DaWik.Items.ItemPickaxeBreaker;
import de.DaWik.DaWik.Items.ItemWoodSeed;

public class DaWikItems {
	// Tools
	public static ItemPickaxeBreaker pickaxeBreaker;
	public static ItemWoodSeed woodSeed;

	public static void init() {
		DaWikItems.pickaxeBreaker = new ItemPickaxeBreaker();
		GameRegistry.registerItem(DaWikItems.pickaxeBreaker, ItemPickaxeBreaker.UNLOCALNAME);
		DaWikItems.woodSeed = new ItemWoodSeed();
		GameRegistry.registerItem(DaWikItems.woodSeed, ItemWoodSeed.UNLOCALNAME);

	}

}
