package de.DaWik.DaWik.init;

import cpw.mods.fml.common.registry.GameRegistry;
import de.DaWik.DaWik.Items.ItemPickaxeBreaker;

public class DaWikItems {
	// Tools
	public static ItemPickaxeBreaker pickaxeBreaker;

	public static void init() {
		DaWikItems.pickaxeBreaker = new ItemPickaxeBreaker();
		GameRegistry.registerItem(DaWikItems.pickaxeBreaker, ItemPickaxeBreaker.UNLOCALNAME);
	}

}
