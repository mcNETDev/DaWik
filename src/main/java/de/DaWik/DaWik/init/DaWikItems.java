package de.DaWik.DaWik.init;

import cpw.mods.fml.common.registry.GameRegistry;
import de.DaWik.DaWik.Items.ItemPickaxeTest;

public class DaWikItems {
	// Tools
	public static ItemPickaxeTest pickaxeTest;

	public static void init() {
		DaWikItems.pickaxeTest = new ItemPickaxeTest();
		GameRegistry.registerItem(DaWikItems.pickaxeTest, ItemPickaxeTest.UNLOCALNAME);
	}

}
