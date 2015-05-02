package de.DaWik.DaWik.Blocks;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import de.DaWik.DaWik.DaWik;
import de.DaWik.DaWik.TileEntity.TileEntityCamoMine;

public class BlockCamoMine extends BaseTileEntity {

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
}
