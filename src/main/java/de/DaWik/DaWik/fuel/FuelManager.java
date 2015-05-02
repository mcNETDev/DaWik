package de.DaWik.DaWik.fuel;

import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.IFuelHandler;
import de.DaWik.DaWik.init.DaWikItems;

public class FuelManager implements IFuelHandler {

	@Override
	public int getBurnTime(ItemStack fuel) {
		if (fuel.getItem().getUnlocalizedName().equals(DaWikItems.pickaxeTest.getUnlocalizedName())) {
			return 2000;
		}

		return 0;
	}

}
