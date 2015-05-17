package de.DaWik.DaWik.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import de.DaWik.DaWik.crafting.WorkSurfaceCraftingManager;
import de.DaWik.DaWik.init.DaWikBlocks;

public class ContainerWorkSurface extends Container {

	public InventoryCrafting craftMatrix;
	public IInventory craftResult;
	public World worldObj;
	public int posX;
	public int posY;
	public int posZ;

	public ContainerWorkSurface(InventoryPlayer invPlayer, World world, int x, int y, int z) {
		craftMatrix = new InventoryCrafting(this, 5, 5);
		craftResult = new InventoryCraftResult();
		worldObj = world;
		posX = x;
		posY = y;
		posZ = z;
		addSlotToContainer(new SlotCrafting(invPlayer.player, craftMatrix, craftResult, 0, 141, 43));

		for (int i = 0; i < 5; i++) {
			for (int k = 0; k < 5; k++) {
				addSlotToContainer(new Slot(craftMatrix, k + (i * 5), 8 + (k * 18), 7 + (i * 18)));
			}
		}

		for (int i = 0; i < 3; i++) {
			for (int k = 0; k < 9; k++) {
				addSlotToContainer(new Slot(invPlayer, k + (i * 9) + 9, 8 + (k * 18), 106 + (i * 18)));
			}
		}
		for (int i = 0; i < 9; i++) {
			addSlotToContainer(new Slot(invPlayer, i, 8 + (i * 18), 164));
		}
		onCraftMatrixChanged(craftMatrix);
	}

	@Override
	public void onCraftMatrixChanged(IInventory iinventory) {
		craftResult.setInventorySlotContents(0, WorkSurfaceCraftingManager.get().findMatchingRecipe(craftMatrix, worldObj));
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		if (worldObj.getBlock(posX, posY, posZ) != DaWikBlocks.workSurface) {
			return false;
		} else {
			return player.getDistanceSq(posX + 0.5D, posY + 0.5D, posZ + 0.5D) <= 64D;
		}
	}

	@Override
	public void onContainerClosed(EntityPlayer player) {
		super.onContainerClosed(player);

		if (!worldObj.isRemote) {
			for (int i = 0; i < 9; ++i) {
				ItemStack itemstack = craftMatrix.getStackInSlotOnClosing(i);

				if (itemstack != null) {
					player.dropPlayerItemWithRandomChoice(itemstack, false);
				}
			}
		}
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int varSlot) {
		ItemStack itemstack = null;
		Slot slot = (Slot) inventorySlots.get(varSlot);

		if ((slot != null) && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (varSlot == 0) {
				if (!mergeItemStack(itemstack1, 10, 46, true)) {
					return null;
				}

				slot.onSlotChange(itemstack1, itemstack);
			} else if ((varSlot >= 10) && (varSlot < 37)) {
				if (!mergeItemStack(itemstack1, 37, 46, false)) {
					return null;
				}
			} else if ((varSlot >= 37) && (varSlot < 46)) {
				if (!mergeItemStack(itemstack1, 10, 37, false)) {
					return null;
				}
			} else if (!mergeItemStack(itemstack1, 10, 46, false)) {
				return null;
			}

			if (itemstack1.stackSize == 0) {
				slot.putStack((ItemStack) null);
			} else {
				slot.onSlotChanged();
			}

			if (itemstack1.stackSize == itemstack.stackSize) {
				return null;
			}

			slot.onPickupFromSlot(player, itemstack1);
		}

		return itemstack;
	}
}
