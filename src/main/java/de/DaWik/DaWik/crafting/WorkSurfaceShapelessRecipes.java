package de.DaWik.DaWik.crafting;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class WorkSurfaceShapelessRecipes implements IRecipe {
	/** Is the ItemStack that you get when craft the recipe. */
	private final ItemStack recipeOutput;
	/** Is a List of ItemStack that composes the recipe. */
	public final List recipeItems;

	public WorkSurfaceShapelessRecipes(ItemStack p_i1918_1_, List p_i1918_2_) {
		recipeOutput = p_i1918_1_;
		recipeItems = p_i1918_2_;
	}

	@Override
	public ItemStack getRecipeOutput() {
		return recipeOutput;
	}

	/**
	 * Used to check if a recipe matches current crafting inventory
	 */
	@Override
	public boolean matches(InventoryCrafting inv, World world) {
		ArrayList arraylist = new ArrayList(recipeItems);

		for (int i = 0; i < 5; ++i) {
			for (int j = 0; j < 5; ++j) {
				ItemStack itemstack = inv.getStackInRowAndColumn(j, i);

				if (itemstack != null) {
					boolean flag = false;
					Iterator iterator = arraylist.iterator();

					while (iterator.hasNext()) {
						ItemStack itemstack1 = (ItemStack) iterator.next();

						if ((itemstack.getItem() == itemstack1.getItem()) && ((itemstack1.getItemDamage() == 32767) || (itemstack.getItemDamage() == itemstack1.getItemDamage()))) {
							flag = true;
							arraylist.remove(itemstack1);
							break;
						}
					}

					if (!flag) {
						return false;
					}
				}
			}
		}

		return arraylist.isEmpty();
	}

	/**
	 * Returns an Item that is the result of this recipe
	 */
	@Override
	public ItemStack getCraftingResult(InventoryCrafting p_77572_1_) {
		return recipeOutput.copy();
	}

	/**
	 * Returns the size of the recipe area
	 */
	@Override
	public int getRecipeSize() {
		return recipeItems.size();
	}
}
