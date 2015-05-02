package de.DaWik.DaWik;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import de.DaWik.DaWik.Items.ItemPickaxeTest;

public class StartRegister {

	public static void registerPreInit(FMLPreInitializationEvent event) {

		// TOOLS
		DaWik.pickaxeTest = new ItemPickaxeTest(null);
		GameRegistry.registerItem(DaWik.pickaxeTest, "pickaxeTest");

	}

	public static void registerLoad(FMLInitializationEvent event) {
		GameRegistry.addShapedRecipe(new ItemStack(DaWik.pickaxeTest), "sw ", "wsw", " ws", Character.valueOf('s'), Items.stick, Character.valueOf('w'), Blocks.planks);
	}

	public static void registerPostInit(FMLPostInitializationEvent event) {

	}
}
