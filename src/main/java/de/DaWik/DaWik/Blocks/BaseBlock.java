package de.DaWik.DaWik.Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import de.DaWik.DaWik.DaWik;

public class BaseBlock extends Block {

	public BaseBlock(String unlocalNAME) {
		super(Material.rock);
		setCreativeTab(DaWik.creativeTab);
		setBlockTextureName("DaWik:" + unlocalNAME);
		setHardness(10);
		setHarvestLevel("pickaxe", 1);
	}

}
