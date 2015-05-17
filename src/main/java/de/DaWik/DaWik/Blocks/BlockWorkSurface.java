package de.DaWik.DaWik.Blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.DaWik.DaWik.DaWik;

public class BlockWorkSurface extends Block {
	private final Random random = new Random();
	private IIcon workSurfaceTop;

	public BlockWorkSurface() {
		super(Material.iron);
		setHardness(3.5F);
		setResistance(5.0F);
		setCreativeTab(DaWik.creativeTab);
		setBlockName("BlockWorkbenchDaWik");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata) {
		return side == 1 ? workSurfaceTop : blockIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg) {
		blockIcon = reg.registerIcon("DaWik:WorkSurfaceSide");
		workSurfaceTop = reg.registerIcon("DaWik:WorkSurfaceTop");
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
		if (!player.isSneaking()) {
			player.openGui(DaWik.instance, 1, world, x, y, z);
			return true;
		}
		return false;
	}
}