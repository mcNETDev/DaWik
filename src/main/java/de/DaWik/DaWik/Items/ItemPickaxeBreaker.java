package de.DaWik.DaWik.Items;

import net.minecraft.item.ItemPickaxe;
import net.minecraftforge.common.util.EnumHelper;
import de.DaWik.DaWik.DaWik;

public class ItemPickaxeBreaker extends ItemPickaxe {

	public static final String UNLOCALNAME = "Breaker";

	public ItemPickaxeBreaker() {
		super(EnumHelper.addToolMaterial("DaWikMaterial", 5, 987654, 20.0F, 20.0F, 30));
		setCreativeTab(DaWik.creativeTab);
		setTextureName("DaWik:" + ItemPickaxeBreaker.UNLOCALNAME);
		setUnlocalizedName(ItemPickaxeBreaker.UNLOCALNAME);
	}

}
