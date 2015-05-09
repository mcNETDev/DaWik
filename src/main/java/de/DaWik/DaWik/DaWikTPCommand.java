package de.DaWik.DaWik;

import java.util.Arrays;
import java.util.List;

import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import de.DaWik.DaWik.World.DaWikTeleporter;
import de.DaWik.DaWik.util.Player;

/**
 * This Command is very very buggy
 */
public class DaWikTPCommand implements ICommand {

	@Override
	public int compareTo(Object o) {
		return 0;
	}

	@Override
	public String getCommandName() {
		return "tpDim";
	}

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "tpDim <DimID>";
	}

	@Override
	public List getCommandAliases() {
		return Arrays.asList("DaWik_tpDIM");
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) {
		if (Player.isExtraPlayer(sender.getCommandSenderName())) {
			if ((args.length == 1) && (sender instanceof EntityPlayerMP)) {
				EntityPlayerMP player = (EntityPlayerMP) sender;
				if (player.dimension != Integer.valueOf(args[0])) {
					player.mcServer.getConfigurationManager().transferPlayerToDimension(player, Integer.valueOf(args[0]),
							new DaWikTeleporter(player.mcServer.worldServerForDimension(Integer.valueOf(args[0])), Integer.valueOf(args[0])));
				}
			} else {
				sender.addChatMessage(new ChatComponentText("You are not a player or invalid Usage || Usage: /tpDim <DimID>"));
			}

		} else {
			sender.addChatMessage(new ChatComponentText("Du darfst das nicht"));
		}
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender sender) {
		return Player.isExtraPlayer(sender.getCommandSenderName());
	}

	@Override
	public List addTabCompletionOptions(ICommandSender sender, String[] array) {
		return Arrays.asList("");
	}

	@Override
	public boolean isUsernameIndex(String[] array, int id) {
		return false;
	}

}
