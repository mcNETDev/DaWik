package de.DaWik.DaWik.Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockContainerDA extends BlockDA implements ITileEntityProvider {

	public BlockContainerDA() {
		super();
		isBlockContainer = true;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return null;
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		super.onBlockAdded(world, x, y, z);
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		super.breakBlock(world, x, y, z, block, meta);
		world.removeTileEntity(x, y, z);
	}

	@Override
	public boolean onBlockEventReceived(World world, int x, int y, int z, int p_149696_5_, int p_149696_6_) {
		super.onBlockEventReceived(world, x, y, z, p_149696_5_, p_149696_6_);
		TileEntity tileentity = world.getTileEntity(x, y, z);
		return tileentity != null ? tileentity.receiveClientEvent(p_149696_5_, p_149696_6_) : false;
	}
}
