package de.DaWik.DaWik.Blocks;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import de.DaWik.DaWik.DaWik;
import de.DaWik.DaWik.TileEntity.TileEntityCamoMine;

public class BlockCamoMine extends BaseBlockTileEntity {

	public static String UNLOCALNAME = "blockCamoMine";

	public BlockCamoMine() {
		setBlockName(BlockCamoMine.UNLOCALNAME);
		setBlockTextureName("DaWik:" + BlockCamoMine.UNLOCALNAME);
		setCreativeTab(DaWik.creativeTab);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileEntityCamoMine();
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			TileEntityCamoMine te = (TileEntityCamoMine) world.getTileEntity(x, y, z);
			te.setCamoflage(player.getCurrentEquippedItem());
		}
		return true;
	}

	@Override
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
		System.out.println("setIcon");
		TileEntityCamoMine te = (TileEntityCamoMine) world.getTileEntity(x, y, z);
		ItemStack stack = te.getCamoflage();
		if ((stack != null) && (stack.getItem() instanceof ItemBlock)) {
			Block block = ((ItemBlock) stack.getItem()).field_150939_a;
			return block.getIcon(side, stack.getItemDamage());
		} else {
			return super.getIcon(world, x, y, z, side);
		}
	}
}
