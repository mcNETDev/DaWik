package de.DaWik.DaWik.util;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;

public class OreDoublingRegistry {
	private static Map<String, ItemStack> oreResults = new HashMap<String, ItemStack>();
	private static String[] names = { "oreGold", "oreIron", "oreAluminum", "oreCopper", "oreLead", "oreSilver", "oreTin", "oreUranium", "orePlatinum", "oreNickel", "oreMithril", "oreCobalt",
			"oreArdite" };
	public static Map<String, ItemStack> resultOverrides = new HashMap<String, ItemStack>();

	public static void init() {

		for (String oreName : OreDoublingRegistry.names) {
			String ingotName = "ingot" + oreName.substring(oreName.indexOf("ore") + 3);

			ItemStack resultIngot = GameRegistry.findItemStack("ThermalFoundation", ingotName, 1);

			if ((resultIngot == null) && (OreDictionary.getOres(ingotName).size() > 0)) {
				resultIngot = OreDictionary.getOres(ingotName).get(0);
			}

			OreDoublingRegistry.oreResults.put(oreName, resultIngot);
		}

	}

	public static ItemStack getOreResult(ItemStack stack) {
		if (stack == null) {
			return null;
		}
		if (OreDoublingRegistry.resultOverrides.containsKey(stack.getItem().getUnlocalizedName(stack))) {
			System.out.println("resultOverrides");
			return OreDoublingRegistry.resultOverrides.get(stack.getItem().getUnlocalizedName(stack)).copy();
		}
		if (FurnaceRecipes.smelting().getSmeltingResult(stack) == null) {
			System.out.println("null");
			return null;
		}
		if (stack.getItem() == Item.getItemFromBlock(Blocks.cobblestone)) {
			return new ItemStack(Blocks.stone, 2);
		} else if (stack.getItem() == Item.getItemFromBlock(Blocks.sand)) {
			return new ItemStack(Blocks.glass, 2);
		} else if (stack.getItem() == Item.getItemFromBlock(Blocks.cactus)) {
			return new ItemStack(Items.dye, 2, 2);
		} else if (stack.getItem() == Items.clay_ball) {
			return new ItemStack(Items.brick, 2);
		}

		int[] ids = OreDictionary.getOreIDs(stack);
		String name;
		ItemStack resultStack = null;

		if (ids.length > 0) {
			name = OreDictionary.getOreName(ids[0]);
			resultStack = OreDoublingRegistry.oreResults.get(name);
		}

		if (resultStack != null) {
			ItemStack doubledStack = resultStack.copy();
			doubledStack.stackSize = FurnaceRecipes.smelting().getSmeltingResult(stack).stackSize * 3;
			return doubledStack;
		} else {
			ItemStack ret = FurnaceRecipes.smelting().getSmeltingResult(stack).copy();
			ret.stackSize = ret.stackSize * 3;
			return ret;
		}
	}
}