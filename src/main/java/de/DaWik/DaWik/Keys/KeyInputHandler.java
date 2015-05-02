package de.DaWik.DaWik.Keys;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import de.DaWik.DaWik.Config.ConfigManager;
import de.DaWik.DaWik.network.MessageExplode;
import de.DaWik.DaWik.network.NetworkHandler;

public class KeyInputHandler {
	private Keybinding getPressedKey() {
		for (Keybinding key : Keybinding.values()) {
			if (key.isPressed()) {
				return key;
			}
		}
		return null;
	}

	@SubscribeEvent
	public void handleKeyInputEvent(InputEvent.KeyInputEvent event) {
		Keybinding key = getPressedKey();
		if (key != null) {
			switch (key) {
			case EXPLODE:
				if (ConfigManager.keyExplode) {
					NetworkHandler.sendToServer(new MessageExplode(3));
				}
				break;
			default:
				break;
			}
		}

	}

}
