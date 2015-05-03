package de.DaWik.DaWik.Blocks;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import de.DaWik.DaWik.DaWik;
import de.DaWik.DaWik.TileEntity.TileEntityStoragex1;

public class BlockStoragex extends BaseBlockTileEntity {
	public String UNLOCALNAME = "blockStoragex16";
	private int slots = 16;

	public BlockStoragex(int slots) {
		this.slots = slots;
		UNLOCALNAME = "blockStoragex" + slots;
		setBlockName(UNLOCALNAME);
		setBlockTextureName("DaWik:" + UNLOCALNAME);
		setCreativeTab(DaWik.creativeTab);
		setResistance(200.0F);
		setHardness(20.0F);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		return new TileEntityStoragex1(slots);
	}

}
