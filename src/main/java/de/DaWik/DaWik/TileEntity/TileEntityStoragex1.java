package de.DaWik.DaWik.TileEntity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class TileEntityStoragex1 extends BaseTileEntity implements IInventory {

	public static final String ID = "DaWik:storagex1";
	public ItemStack[] inventory;

	public TileEntityStoragex1(int size) {
		inventory = new ItemStack[size];
	}

	public TileEntityStoragex1() {
		inventory = new ItemStack[16];
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger("slots", inventory.length);
		NBTTagList camoStackTag = new NBTTagList();
		for (int i = 0; i < inventory.length; i++) {
			ItemStack stack = inventory[i];
			if (stack != null) {
				NBTTagCompound t = new NBTTagCompound();
				stack.writeToNBT(t);
				t.setInteger("index", i);
				camoStackTag.appendTag(t);
			}
		}
		nbt.setTag("inventory", camoStackTag);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		inventory = new ItemStack[nbt.getInteger("slots")];
		NBTTagList camoStackTag = nbt.getTagList("inventory", 10);

		for (int i = 0; i < camoStackTag.tagCount(); i++) {
			NBTTagCompound t = camoStackTag.getCompoundTagAt(i);
			int index = t.getInteger("index");
			if ((index >= 0) && (index < inventory.length)) {
				inventory[index] = ItemStack.loadItemStackFromNBT(t);
			}
		}
	}

	@Override
	public int getSizeInventory() {
		return inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return inventory[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int decreaseAmount) {
		if (inventory[slot] != null) {
			ItemStack itemstack;

			if (inventory[slot].stackSize <= decreaseAmount) {
				itemstack = inventory[slot];
				setInventorySlotContents(slot, null);
				markDirty();
				return itemstack;
			} else {
				itemstack = inventory[slot].splitStack(decreaseAmount);

				if (inventory[slot].stackSize == 0) {
					setInventorySlotContents(slot, null);
				}

				markDirty();
				return itemstack;
			}
		} else {
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		if (inventory[slot] != null) {
			ItemStack itemstack = inventory[slot];
			inventory[slot] = null;
			return itemstack;
		} else {
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		inventory[slot] = stack;

		if ((stack != null) && (stack.stackSize > getInventoryStackLimit())) {
			stack.stackSize = getInventoryStackLimit();
		}

		markDirty();
	}

	@Override
	public String getInventoryName() {
		return "Storagex1";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return worldObj.getTileEntity(xCoord, yCoord, zCoord) != this ? false : player.getDistanceSq(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public void openInventory() {
	}

	@Override
	public void closeInventory() {
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack item) {
		return true;
	}

}
