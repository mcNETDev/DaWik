package de.DaWik.DaWik.World;

import de.DaWik.DaWik.DimType;
import de.DaWik.DaWik.enums.SkyColors;

public class Dimension {
	private String name;
	private int id;
	private DimType type;
	private SkyColors sky;

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public DimType getType() {
		return type;
	}

	public Dimension(String name, int id, DimType type, SkyColors sky) {
		this.name = name;
		this.id = id;
		this.type = type;
		this.sky = sky;
	}

	public SkyColors getSky() {
		return sky;
	}

}
