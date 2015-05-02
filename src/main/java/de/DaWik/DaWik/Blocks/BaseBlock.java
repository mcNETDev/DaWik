package de.DaWik.DaWik.Blocks;

import de.DaWik.DaWik.DaWik;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSponge;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.SpawnerAnimals;

public class BaseBlock extends Block{

	public BaseBlock(Material material) {
		super(material);
		setCreativeTab(DaWik.creativeTab);
		setBlockTextureName("DaWik:BlockTest");
		setBlockName("blockTest");
		setHardness(10);
		setHarvestLevel("pickaxe", 1);
	}

	
	
	
	
	
}
