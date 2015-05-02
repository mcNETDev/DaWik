package de.DaWik.DaWik.Keys;

import net.minecraft.client.settings.KeyBinding;

import org.lwjgl.input.Keyboard;

public enum Keybinding {
	EXPLODE("key.dawik.explode", Keyboard.KEY_NUMPAD4);

	private final KeyBinding keybinding;

	private Keybinding(String keyName, int defaultKeyCode) {
		keybinding = new KeyBinding(keyName, defaultKeyCode, "key.categories.dawik");
	}

	public KeyBinding getKeybinding() {
		return keybinding;
	}

	public boolean isPressed() {
		return keybinding.isPressed();
	}

	public KeyBinding getKeybind() {
		return keybinding;
	}
}
