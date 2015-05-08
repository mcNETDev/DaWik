package de.DaWik.DaWik.enums;

import java.util.Random;

public enum SkyColors {
	DEFAULT(0), CYAN(1000000), YELLOW(100000000), BLUE(1234), DARKGREEN(14345), LIGHTGREEN(55555), LIGHTBLUE(85445), BLACK(1), LIGHTBROWN(10000000), PINK(63777754), RED(44444444), DARKRED(40444444), PURPLE(
			40044444), WHITE(65464566);

	private int color;

	private SkyColors(int color) {
		this.color = color;
	}

	public int getColor() {
		return color;
	}

	public static SkyColors getRandom() {
		Random rand = new Random();
		return SkyColors.values()[rand.nextInt(SkyColors.values().length)];
	}
}
