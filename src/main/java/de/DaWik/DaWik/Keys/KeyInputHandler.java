package de.DaWik.DaWik.Keys;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;

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
				System.out.println("Booooom");
				break;

			default:
				break;
			}
		}

	}

}
