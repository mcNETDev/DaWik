package de.DaWik.DaWik.Items;

import de.DaWik.DaWik.DaWik;
import de.DaWik.DaWik.init.DaWikBlocks;

public class ItemWoodSeed extends BaseItemSeed {

	public static final String UNLOCALNAME = "itemWoodSeed";

	public ItemWoodSeed() {
		super(DaWikBlocks.woodCrop, DaWikBlocks.advWood);
		setUnlocalizedName(ItemWoodSeed.UNLOCALNAME);
		setTextureName("DaWik:" + ItemWoodSeed.UNLOCALNAME);
		setCreativeTab(DaWik.creativeTab);
	}

}
