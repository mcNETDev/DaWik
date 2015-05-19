package de.DaWik.DaWik.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.OpenGlHelper;

import org.lwjgl.opengl.GL11;

public class GuiButtonAHeight extends GuiButton {

	public GuiButtonAHeight(int id, int xPos, int yPos, int width, int hight, String displayString) {
		super(id, xPos, yPos, width, hight, displayString);
	}

	@Override
	public void drawButton(Minecraft minecraft, int mouseX, int mouseY) {
		if (visible) {
			FontRenderer fontrenderer = minecraft.fontRenderer;
			minecraft.getTextureManager().bindTexture(buttonTextures);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			field_146123_n = (mouseX >= xPosition) && (mouseY >= yPosition) && (mouseX < (xPosition + width)) && (mouseY < (yPosition + height));
			int k = this.getHoverState(field_146123_n);
			GL11.glEnable(GL11.GL_BLEND);
			OpenGlHelper.glBlendFunc(770, 771, 1, 0);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			this.drawTexturedModalRect(xPosition, yPosition, 0, 46 + (k * 20), width / 2, height);
			this.drawTexturedModalRect(xPosition + (width / 2), yPosition, 200 - (width / 2), 46 + (k * 20), width / 2, height);
			if (height < 20) {

				this.drawTexturedModalRect(xPosition, yPosition + 3, 0, (((46 + (k * 20)) + 20) - height) + 3, width / 2, height - 3);
				this.drawTexturedModalRect(xPosition + (width / 2), yPosition + 3, 200 - (width / 2), (((46 + (k * 20)) + 20) - height) + 3, width / 2, height - 3);
			}
			this.mouseDragged(minecraft, mouseX, mouseY);
			int l = 14737632;

			if (packedFGColour != 0) {
				l = packedFGColour;
			} else if (!enabled) {
				l = 10526880;
			} else if (field_146123_n) {
				l = 16777120;
			}
			this.drawCenteredString(fontrenderer, displayString, xPosition + (width / 2), yPosition + ((height - 8) / 2), l);
		}
	}
}