package de.DaWik.DaWik.Inv;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import de.DaWik.DaWik.TileEntity.TileSuperChest;

public class InventoryCraftingChest extends InventoryCrafting {
	/** the width of the crafting inventory */
	private int inventoryWidth;

	/**
	 * Class containing the callbacks for the events on_GUIClosed and
	 * on_CraftMaxtrixChanged.
	 */
	private Container eventHandler;
	private TileSuperChest tile;

	public InventoryCraftingChest(Container par1Container, int size, int height, TileSuperChest tile) {
		super(par1Container, size, height);
		eventHandler = par1Container;
		inventoryWidth = size;
		this.tile = tile;
	}

	/**
	 * Returns the number of slots in the inventory.
	 */
	@Override
	public int getSizeInventory() {
		return 9;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		// the 9 slots + 1 output slot that's not accessible, we therefore have
		// to add 1 to the slot accessed
		return slot >= getSizeInventory() ? null : tile.getStackInCraftingSlot(slot + 1);
	}

	@Override
	public ItemStack getStackInRowAndColumn(int row, int column) {
		if ((row >= 0) && (row < inventoryWidth)) {
			int k = row + (column * inventoryWidth);
			return getStackInSlot(k);
		} else {
			return null;
		}
	}

	public String getInvName() {
		return "";
	}

	public boolean isInvNameLocalized() {
		return false;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int par1) {
		return null;
	}

	/**
	 * Removes from an inventory slot (first arg) up to a specified number
	 * (second arg) of items and returns them in a new stack.
	 */
	@Override
	public ItemStack decrStackSize(int slotID, int par2) {
		ItemStack stack = tile.getStackInCraftingSlot(slotID + 1);
		if (stack != null) {
			ItemStack itemstack;

			if (stack.stackSize <= par2) {
				itemstack = stack.copy();
				stack = null;
				tile.setInventoryCraftingSlotContents(slotID + 1, null);
				eventHandler.onCraftMatrixChanged(this);
				return itemstack;
			} else {
				itemstack = stack.splitStack(par2);

				if (stack.stackSize == 0) {
					stack = null;
				}

				eventHandler.onCraftMatrixChanged(this);
				return itemstack;
			}
		} else {
			return null;
		}
	}

	/**
	 * Sets the given item stack to the specified slot in the inventory (can be
	 * crafting or armor sections).
	 */
	@Override
	public void setInventorySlotContents(int slot, ItemStack itemstack) {
		tile.setInventoryCraftingSlotContents(slot + 1, itemstack);
		eventHandler.onCraftMatrixChanged(this);
	}

	/**
	 * Returns the maximum stack size for a inventory slot. Seems to always be
	 * 64, possibly will be extended. *Isn't this more of a set than a get?*
	 */
	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	/**
	 * Called when an the contents of an Inventory change, usually
	 */
	@Override
	public void markDirty() {
	}

	/**
	 * Do not make give this method the name canInteractWith because it clashes
	 * with Container
	 */
	@Override
	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {
		return true;
	}

	public void openChest() {
	}

	public void closeChest() {
	}

	/**
	 * Returns true if automation is allowed to insert the given stack (ignoring
	 * stack size) into the given slot.
	 */
	public boolean isStackValidForSlot(int par1, ItemStack par2ItemStack) {
		return true;
	}
}
