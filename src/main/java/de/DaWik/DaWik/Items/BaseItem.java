package de.DaWik.DaWik.Items;

import de.DaWik.DaWik.DaWik;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BaseItem extends Item {

	public String UNLOCALNAME;
	public String name;

	public BaseItem(String unlocalNAME, String name, int stackSize) {
		this.UNLOCALNAME = unlocalNAME;
		this.name = name;
		setCreativeTab(DaWik.creativeTab);
		setMaxStackSize(stackSize);
		setUnlocalizedName(unlocalNAME);
		setTextureName("DaWik:" + unlocalNAME);
	}
}
