package de.DaWik.DaWik.util;

import de.DaWik.DaWik.Config.ConfigManager;

public class Player {
	public static boolean isExtraPlayer(String name) {
		for (String name2 : ConfigManager.players) {
			if (name2.equalsIgnoreCase(name)) {
				return true;
			}
		}
		return false;
	}
}
