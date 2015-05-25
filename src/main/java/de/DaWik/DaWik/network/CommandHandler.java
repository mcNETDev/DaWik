package de.DaWik.DaWik.network;

import net.minecraft.nbt.NBTTagCompound;
import de.DaWik.DaWik.crafting.WorkSurfaceCraftingManager;
import de.tisan.mcoref.communication.CommunicationEvent;

public class CommandHandler implements CommunicationEvent {

	@Override
	public void onServerToClientMessageReceived(NBTTagCompound tag) {
		int type = tag.getInteger("type");
		switch (type) {
		case 0:
			WorkSurfaceCraftingManager.get().reset();
			break;

		default:
			break;
		}
	}

	@Override
	public void onClientToServerMessageReceived(NBTTagCompound tag) {

	}

}
