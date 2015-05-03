package de.DaWik.DaWik.TileEntity;

import io.netty.buffer.ByteBuf;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import cpw.mods.fml.common.network.ByteBufUtils;

public class TileEntityCamoMine extends BaseTileEntity {
	public static final String ID = "DaWik:camoMine";
	private int timer = 10;
	private ItemStack camoStack;

	@Override
	public void updateEntity() {
		timer--;
		if ((timer == 0) && !worldObj.isRemote) {
			List<Entity> entitys = worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox(xCoord, yCoord, zCoord, xCoord + 1.0D, yCoord + 3, zCoord + 1.0D));
			if (entitys.size() > 0) {
				worldObj.createExplosion(null, xCoord, yCoord, zCoord, 3.0F, false);
			}
			timer = 10;

		}
	}

	@Override
	public void readFromPacket(ByteBuf buf) {
		camoStack = ByteBufUtils.readItemStack(buf);
		worldObj.markBlockRangeForRenderUpdate(xCoord, yCoord, zCoord, xCoord, yCoord, zCoord);
	}

	@Override
	public void writeToPacket(ByteBuf buf) {
		ByteBufUtils.writeItemStack(buf, camoStack);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		NBTTagCompound camo = new NBTTagCompound();
		camoStack.writeToNBT(camo);
		nbt.setTag("camoStack", camo);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		if (nbt.hasKey("camoStack")) {
			camoStack = ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("camoStack"));
		} else {
			camoStack = null;
		}
	}

	public ItemStack getCamoflage() {
		return camoStack;
	}

	public void setCamoflage(ItemStack camoStack) {
		this.camoStack = camoStack;
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		worldObj.markBlockRangeForRenderUpdate(xCoord, yCoord, zCoord, xCoord, yCoord, zCoord);
		System.out.println("send camo");
	}

}
