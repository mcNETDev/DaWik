package de.DaWik.DaWik.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import de.DaWik.DaWik.container.ContainerWorkSurface;
import de.DaWik.DaWik.init.DaWikBlocks;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == 1) {
			return world.getBlock(x, y, z) == DaWikBlocks.workSurface ? new ContainerWorkSurface(player.inventory, world, x, y, z) : null;
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == 1) {
			return world.getBlock(x, y, z) == DaWikBlocks.workSurface ? new GuiWorkSurface(player.inventory, world, x, y, z) : null;
		}
		return null;
	}

}
