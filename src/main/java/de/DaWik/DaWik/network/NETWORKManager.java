package de.DaWik.DaWik.network;

import de.DaWik.DaWik.util.References;
import de.tisan.mcoref.communication.Communication;
import de.tisan.mcoref.communication.CommunicationManager;

public class NETWORKManager {

	public final int TYPE_ID_RELOADCOMMAND = 0;
	public final int TYPE_ID_SUPERCHEST = 1;

	public static final byte ID_DRACONIUMCHEST0 = 2;
	public static final byte ID_DRACONIUMCHEST1 = 3;
	public static final byte ID_DRACONIUMCHEST2 = 4;
	public static final byte ID_DRACONIUMCHEST3 = 5;
	public static final byte ID_DRACONIUMCHEST4 = 8;
	public static boolean state = false;

	private Communication com;

	private Communication createNetwork() {
		com = CommunicationManager.createCommunication(References.MODID);
		com.addEvent(new ButtonHandler());
		com.addEvent(new CommandHandler());
		return com;
	}

	public Communication getCommunication() {
		return (com == null ? (com = createNetwork()) : com);
	}
}
