package de.DaWik.DaWik.Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import de.DaWik.DaWik.DaWik;

public class BaseBlock extends Block {
	public static String unlocalNAME;
	public static String name;

	public BaseBlock(String unlocalNAME, String name) {
		super(Material.rock);
		unlocalNAME = BaseBlock.unlocalNAME;
		name = BaseBlock.name;
		setCreativeTab(DaWik.creativeTab);
		setBlockTextureName("DaWik:" + unlocalNAME);
		setBlockName(unlocalNAME);
		setHardness(10);
		setHarvestLevel("pickaxe", 1);
	}

}
