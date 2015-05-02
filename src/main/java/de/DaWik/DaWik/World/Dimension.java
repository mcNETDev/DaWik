package de.DaWik.DaWik.World;

import de.DaWik.DaWik.DimType;

public class Dimension {
	private String name;
	private int id;
	private DimType type;

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public DimType getType() {
		return type;
	}

	public Dimension(String name, int id, DimType type) {
		this.name = name;
		this.id = id;
		this.type = type;
	}

}
