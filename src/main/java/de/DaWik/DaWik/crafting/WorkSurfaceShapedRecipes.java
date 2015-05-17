package de.DaWik.DaWik.crafting;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class WorkSurfaceShapedRecipes implements IRecipe {
	/** How many horizontal slots this recipe is wide. */
	public final int recipeWidth;
	/** How many vertical slots this recipe uses. */
	public final int recipeHeight;
	/** Is a array of ItemStack that composes the recipe. */
	public final ItemStack[] recipeItems;
	/** Is the ItemStack that you get when craft the recipe. */
	private ItemStack recipeOutput;
	private boolean field_92101_f;
	private static final String __OBFID = "CL_00000093";

	public WorkSurfaceShapedRecipes(int recipeWidth, int recipeHeight, ItemStack[] recipeItems, ItemStack recipeOutput) {
		this.recipeWidth = recipeWidth;
		this.recipeHeight = recipeHeight;
		this.recipeItems = recipeItems;
		this.recipeOutput = recipeOutput;
	}

	@Override
	public ItemStack getRecipeOutput() {
		return recipeOutput;
	}

	/**
	 * Used to check if a recipe matches current crafting inventory
	 */
	@Override
	public boolean matches(InventoryCrafting p_77569_1_, World p_77569_2_) {
		for (int i = 0; i <= (5 - recipeWidth); ++i) {
			for (int j = 0; j <= (5 - recipeHeight); ++j) {
				if (checkMatch(p_77569_1_, i, j, true)) {
					return true;
				}

				if (checkMatch(p_77569_1_, i, j, false)) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Checks if the region of a crafting inventory is match for the recipe.
	 */
	private boolean checkMatch(InventoryCrafting p_77573_1_, int p_77573_2_, int p_77573_3_, boolean p_77573_4_) {
		for (int k = 0; k < 5; ++k) {
			for (int l = 0; l < 5; ++l) {
				int i1 = k - p_77573_2_;
				int j1 = l - p_77573_3_;
				ItemStack itemstack = null;

				if ((i1 >= 0) && (j1 >= 0) && (i1 < recipeWidth) && (j1 < recipeHeight)) {
					if (p_77573_4_) {
						itemstack = recipeItems[(recipeWidth - i1 - 1) + (j1 * recipeWidth)];
					} else {
						itemstack = recipeItems[i1 + (j1 * recipeWidth)];
					}
				}

				ItemStack itemstack1 = p_77573_1_.getStackInRowAndColumn(k, l);

				if ((itemstack1 != null) || (itemstack != null)) {
					if (((itemstack1 == null) && (itemstack != null)) || ((itemstack1 != null) && (itemstack == null))) {
						return false;
					}

					if (itemstack.getItem() != itemstack1.getItem()) {
						return false;
					}

					if ((itemstack.getItemDamage() != 32767) && (itemstack.getItemDamage() != itemstack1.getItemDamage())) {
						return false;
					}
				}
			}
		}

		return true;
	}

	/**
	 * Returns an Item that is the result of this recipe
	 */
	@Override
	public ItemStack getCraftingResult(InventoryCrafting p_77572_1_) {
		ItemStack itemstack = getRecipeOutput().copy();

		if (field_92101_f) {
			for (int i = 0; i < p_77572_1_.getSizeInventory(); ++i) {
				ItemStack itemstack1 = p_77572_1_.getStackInSlot(i);

				if ((itemstack1 != null) && itemstack1.hasTagCompound()) {
					itemstack.setTagCompound((NBTTagCompound) itemstack1.stackTagCompound.copy());
				}
			}
		}

		return itemstack;
	}

	/**
	 * Returns the size of the recipe area
	 */
	@Override
	public int getRecipeSize() {
		return recipeWidth * recipeHeight;
	}

	public WorkSurfaceShapedRecipes func_92100_c() {
		field_92101_f = true;
		return this;
	}
}
