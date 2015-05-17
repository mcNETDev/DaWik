package de.DaWik.DaWik.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import de.DaWik.DaWik.container.ContainerWorkSurface;

public class GuiWorkSurface extends GuiContainer {
	private ResourceLocation texure = new ResourceLocation("DaWik:textures/gui/WorkSurface.png");

	public GuiWorkSurface(InventoryPlayer invPlayer, World world, int x, int y, int z) {
		super(new ContainerWorkSurface(invPlayer, world, x, y, z));
		xSize = 176;
		ySize = 188;

	}

	@Override
	public void onGuiClosed() {
		super.onGuiClosed();
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int i, int j) {
		fontRendererObj.drawString(StatCollector.translateToLocal("Work Surface"), 100, 5, 0x000066);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		GL11.glColor4f(1F, 1F, 1F, 1F);
		Minecraft.getMinecraft().getTextureManager().bindTexture(texure);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

	}

}
