package de.DaWik.DaWik.Blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;

public abstract class BaseBlockTileEntity extends BlockContainer {

	public BaseBlockTileEntity(Material mat) {
		super(mat);
	}

	public BaseBlockTileEntity() {
		super(Material.rock);
	}
}
