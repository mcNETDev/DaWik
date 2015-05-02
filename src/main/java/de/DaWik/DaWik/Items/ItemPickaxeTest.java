package de.DaWik.DaWik.Items;

import de.DaWik.DaWik.DaWik;
import net.minecraft.item.ItemPickaxe;
import net.minecraftforge.common.util.EnumHelper;

public class ItemPickaxeTest extends ItemPickaxe{

	public ItemPickaxeTest(ToolMaterial material) {
		super(EnumHelper.addToolMaterial("DaWikMaterial", 5, 987654, 20.0F, 20.0F, 30));
		setCreativeTab(DaWik.creativeTab);
		setTextureName("DaWik:PickaxeTest");
		setUnlocalizedName("itemPickaxeTest");
	}

}
