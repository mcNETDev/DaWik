package de.DaWik.DaWik;

import java.util.List;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.nbt.NBTTagCompound;
import de.DaWik.DaWik.crafting.WorkSurfaceCraftingManager;
import de.DaWik.DaWik.util.Player;

public class DaWikCommand implements ICommand {

	@Override
	public int compareTo(Object arg0) {
		return 0;
	}

	@Override
	public String getCommandName() {
		return "dawik";
	}

	@Override
	public String getCommandUsage(ICommandSender player) {
		return "/dawik <args>";
	}

	@Override
	public List getCommandAliases() {
		return null;
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		switch (args[0]) {
		case "reload":
			WorkSurfaceCraftingManager.get().reset();
			NBTTagCompound tag = new NBTTagCompound();
			tag.setInteger("type", 0);
			DaWik.networkManager.getCommunication().sendToAllClients(tag);
			break;
		default:
			break;
		}
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender player) {
		return Player.isExtraPlayer(player.getCommandSenderName());
	}

	@Override
	public List addTabCompletionOptions(ICommandSender p_71516_1_, String[] p_71516_2_) {
		return null;
	}

	@Override
	public boolean isUsernameIndex(String[] p_82358_1_, int p_82358_2_) {
		return false;
	}

}
