package de.DaWik.DaWik.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import de.DaWik.DaWik.Config.ConfigManager;

public class NetworkHandler {
	private static SimpleNetworkWrapper INSTANCE;

	public static void init() {
		NetworkHandler.INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("DaWik");
		if (ConfigManager.keyExplode) {
			NetworkHandler.INSTANCE.registerMessage(MessageExplode.class, MessageExplode.class, 0, Side.SERVER);
		}
	}

	public static void sendToServer(IMessage message) {
		NetworkHandler.INSTANCE.sendToServer(message);
	}

}
