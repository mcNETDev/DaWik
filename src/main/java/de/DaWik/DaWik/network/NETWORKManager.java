package de.DaWik.DaWik.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;
import de.DaWik.DaWik.DaWik;

public class NETWORKManager {

	public static void init() {
		DaWik.network = NetworkRegistry.INSTANCE.newSimpleChannel(DaWik.networkChannelName);
		DaWik.network.registerMessage(ButtonPacket.Handler.class, ButtonPacket.class, 0, Side.SERVER);

	}

}
