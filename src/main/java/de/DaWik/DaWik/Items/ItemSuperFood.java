package de.DaWik.DaWik.Items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import de.DaWik.DaWik.DaWik;

public class ItemSuperFood extends ItemEnergy {

	public ItemSuperFood() {
		super();
		setTextureName("DaWik:ItemSuperFood");
		setUnlocalizedName("itemSuperFood");
		setCreativeTab(DaWik.creativeTab);
	}

	@Override
	public void onUpdate(ItemStack item, World world, Entity ent, int meta, boolean bo) {
		if (!(ent instanceof EntityPlayer)) {
			return;
		}
		EntityPlayer player = (EntityPlayer) ent;
		if (player.getFoodStats().needFood()) {
			int en = useEnergy(item, true);
			if (en == energyPerUse) {
				useEnergy(item, false);
				player.getFoodStats().setFoodLevel(player.getFoodStats().getFoodLevel() + 1);
				System.out.println("UseEnergy");
			}
		}
	}
}
