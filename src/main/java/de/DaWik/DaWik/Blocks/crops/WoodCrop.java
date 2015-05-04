package de.DaWik.DaWik.Blocks.crops;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.DaWik.DaWik.init.DaWikItems;

public class WoodCrop extends BaseBlockPlant {
	public static final String UNLOCALNAME = "cropWood";

	public WoodCrop() {
		setBlockName(WoodCrop.UNLOCALNAME);
		setBlockTextureName("DaWik:" + WoodCrop.UNLOCALNAME + "_stage_0");
	}

	@Override
	public int quantityDropped(int parMetadata, int parFortune, Random rand) {
		if (parMetadata >= 6) {
			return rand.nextInt(3) + 1;
		}
		return 0;
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		ArrayList<ItemStack> items = super.getDrops(world, x, y, z, metadata, fortune);
		if (world.rand.nextInt(10) < 2) {
			items.add(new ItemStack(DaWikItems.woodSeed));
		}
		items.add(new ItemStack(DaWikItems.woodSeed));
		return items;
	}

	@Override
	public Item getItemDropped(int parMetadata, Random parRand, int parFortune) {
		return ((new ItemStack(Blocks.log)).getItem());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister parIIconRegister) {
		iIcon = new IIcon[maxGrowthStage + 1];
		iIcon[0] = parIIconRegister.registerIcon("DaWik:" + WoodCrop.UNLOCALNAME + "_stage_0");
		iIcon[1] = parIIconRegister.registerIcon("DaWik:" + WoodCrop.UNLOCALNAME + "_stage_0");
		iIcon[2] = parIIconRegister.registerIcon("DaWik:" + WoodCrop.UNLOCALNAME + "_stage_1");
		iIcon[3] = parIIconRegister.registerIcon("DaWik:" + WoodCrop.UNLOCALNAME + "_stage_1");
		iIcon[4] = parIIconRegister.registerIcon("DaWik:" + WoodCrop.UNLOCALNAME + "_stage_2");
		iIcon[5] = parIIconRegister.registerIcon("DaWik:" + WoodCrop.UNLOCALNAME + "_stage_2");
		iIcon[6] = parIIconRegister.registerIcon("DaWik:" + WoodCrop.UNLOCALNAME + "_stage_3");
		iIcon[7] = parIIconRegister.registerIcon("DaWik:" + WoodCrop.UNLOCALNAME + "_stage_3");
	}
}
