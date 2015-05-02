package de.DaWik.DaWik.Blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;

public abstract class BaseTileEntity extends BlockContainer {

	public BaseTileEntity(Material mat) {
		super(mat);
	}

	public BaseTileEntity() {
		super(Material.rock);
	}
}
