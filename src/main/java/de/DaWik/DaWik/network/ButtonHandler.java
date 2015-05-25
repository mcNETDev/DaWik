package de.DaWik.DaWik.network;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import de.DaWik.DaWik.DaWik;
import de.DaWik.DaWik.TileEntity.TileSuperChest;
import de.tisan.mcoref.communication.CommunicationEvent;
import de.tisan.mcoref.helpers.Bukkit;

public class ButtonHandler implements CommunicationEvent {

	@Override
	public void onClientToServerMessageReceived(NBTTagCompound tag) {
		if (tag.getInteger("type") == DaWik.networkManager.TYPE_ID_SUPERCHEST) {

			EntityPlayerMP player = (EntityPlayerMP) Bukkit.getPlayer(tag.getString("player"));
			World w = DimensionManager.getWorld(player.dimension);
			int x = tag.getInteger("x");
			int y = tag.getInteger("y");
			int z = tag.getInteger("z");
			TileEntity tile2 = w.getTileEntity(x, y, z);
			if (!(tile2 instanceof TileSuperChest)) {
				return;
			}
			TileSuperChest tile = (TileSuperChest) tile2;
			switch (tag.getByte("buttonID")) {

			case NETWORKManager.ID_DRACONIUMCHEST0:
				tile.setAutoFeed(0);
				break;
			case NETWORKManager.ID_DRACONIUMCHEST1:
				tile.setAutoFeed(1);
				break;
			case NETWORKManager.ID_DRACONIUMCHEST2:
				tile.setAutoFeed(2);
				break;
			case NETWORKManager.ID_DRACONIUMCHEST3:
				tile.setAutoFeed(3);
				break;
			case NETWORKManager.ID_DRACONIUMCHEST4:
				tile.lockOutputSlots = !tile.lockOutputSlots;
				break;
			default:
				break;
			}

		}

	}

	@Override
	public void onServerToClientMessageReceived(NBTTagCompound tag) {

	}

}
