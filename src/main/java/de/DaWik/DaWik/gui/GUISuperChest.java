package de.DaWik.DaWik.gui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;

import codechicken.nei.VisiblityData;
import codechicken.nei.api.INEIGuiHandler;
import codechicken.nei.api.TaggedInventoryArea;
import cpw.mods.fml.common.Optional;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.DaWik.DaWik.DaWik;
import de.DaWik.DaWik.TileEntity.TileSuperChest;
import de.DaWik.DaWik.container.ContainerSuperChest;
import de.DaWik.DaWik.network.NETWORKManager;
import de.DaWik.DaWik.util.GuiButtonAHeight;
import de.DaWik.DaWik.util.GuiHelper;
import de.DaWik.DaWik.util.References;

@SideOnly(Side.CLIENT)
@Optional.Interface(iface = "codechicken.nei.api.INEIGuiHandler", modid = "NotEnoughItems")
public class GUISuperChest extends GuiContainer implements INEIGuiHandler {
	public EntityPlayer player;
	private TileSuperChest tile;
	private static final ResourceLocation textureLeft = new ResourceLocation(References.MODID.toLowerCase(), "textures/gui/SuperChestLeft.png");
	private static final ResourceLocation textureRight = new ResourceLocation(References.MODID.toLowerCase(), "textures/gui/SuperChestRight.png");
	private int lastAutoFeed = -1;

	public GUISuperChest(InventoryPlayer invPlayer, TileSuperChest tile) {
		super(new ContainerSuperChest(invPlayer, tile));

		xSize = 481;
		ySize = 256;

		this.tile = tile;
		player = invPlayer.player;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
		GL11.glColor4f(1F, 1F, 1F, 1F);
		Minecraft.getMinecraft().getTextureManager().bindTexture(GUISuperChest.textureRight);
		drawTexturedModalRect(guiLeft + 256, guiTop, 0, 0, 225, ySize);
		Minecraft.getMinecraft().getTextureManager().bindTexture(GUISuperChest.textureLeft);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, 256, ySize);
		drawTexturedModalRect(guiLeft + 140, guiTop + 216, 3, 176, 16, 23);
		drawTexturedModalRect(guiLeft + 387, guiTop + 236, 44, 177, 90, 16);
		drawTexturedModalRect(guiLeft + 387, guiTop + 180, 44, 177, 90, 16);

		if (tile.lockOutputSlots) {
			mc.renderEngine.bindTexture(new ResourceLocation("textures/gui/Widgets.png"));
			GL11.glColor4f(1f, 1f, 1f, 1f);
			for (int i = 0; i < 5; i++) {
				drawTexturedModalRect(guiLeft + 385 + (i * 18), guiTop + 158, 138, 18, 18, 18);
			}
			Minecraft.getMinecraft().getTextureManager().bindTexture(GUISuperChest.textureLeft);
		}

		int arrowHight = (int) (((float) tile.smeltingProgressTime / (float) tile.smeltingCompleateTime) * 22f);
		drawTexturedModalRect(guiLeft + 140, (guiTop + 192 + 22) - arrowHight, 140, (216 + 22) - arrowHight, 16, arrowHight);

		Minecraft.getMinecraft().getTextureManager().bindTexture(GUISuperChest.textureRight);
		int energyWidth = (int) (((float) tile.getEnergyStored(ForgeDirection.DOWN) / (float) tile.getMaxEnergyStored(ForgeDirection.DOWN)) * 90f);
		drawTexturedModalRect(guiLeft + 44, guiTop + 235, 131, 236, energyWidth, 16);

		int flameHight = (int) (((float) tile.smeltingBurnSpeed / (float) tile.smeltingMaxBurnSpeed) * 13f);
		// flameHight = tile.smeltingProgressTime <= 0 ? 0 :
		// Math.min(flameHight, 13);
		drawTexturedModalRect(guiLeft + 45, (guiTop + 217 + 13) - flameHight, 132, (180 + 13) - flameHight, 88, flameHight);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y) {
		fontRendererObj.drawString(tile.getInventoryName(), 4, 4, 0x222222);
		// fontRendererObj.drawStringWithShadow(StatCollector.translateToLocal("button.de.chestAutoFeed.txt"),
		// 4, 180, 0x00FFFF);

		ArrayList<String> list = new ArrayList<String>();
		list.add(String.valueOf(tile.getEnergyStored(ForgeDirection.DOWN)) + "/" + String.valueOf(tile.getMaxEnergyStored(ForgeDirection.DOWN)) + "RF");
		if (GuiHelper.isInRect(44, 235, 90, 16, x - guiLeft, y - guiTop)) {
			drawHoveringText(list, x - guiLeft, y - guiTop, fontRendererObj);
		}
		list.clear();
		String s = "OFF.Auto Feed Disabled";
		if (GuiHelper.isInRect(4, 180, 38, 12, x - guiLeft, y - guiTop)) {
			list.add(s.substring(s.indexOf(".") + 1));
			drawHoveringText(list, x - guiLeft, y - guiTop, fontRendererObj);
		}
		if (GuiHelper.isInRect(4, 193, 38, 12, x - guiLeft, y - guiTop)) {
			s = "FILL.Keep the current stacks full";
			list.add(s.substring(s.indexOf(".") + 1));
			drawHoveringText(list, x - guiLeft, y - guiTop, fontRendererObj);
		}
		if (GuiHelper.isInRect(4, 206, 38, 12, x - guiLeft, y - guiTop)) {
			s = "LOCK.Only pull the currently smelting items from the inventory";
			list.add(s.substring(s.indexOf(".") + 1));
			drawHoveringText(list, x - guiLeft, y - guiTop, fontRendererObj);
		}
		if (GuiHelper.isInRect(4, 219, 38, 12, x - guiLeft, y - guiTop)) {
			s = "ALL.Pull any smeltable items from the inventory";
			list.add(s.substring(s.indexOf(".") + 1));
			drawHoveringText(list, x - guiLeft, y - guiTop, fontRendererObj);
		}
		if (GuiHelper.isInRect(398, 180, 70, 12, x - guiLeft, y - guiTop)) {
			s = "Lock Output.Blocks items being inserted into the last 5 slots";
			list.add(s.substring(s.indexOf(".") + 1));
			drawHoveringText(list, x - guiLeft, y - guiTop, fontRendererObj);
		}

		RenderHelper.enableGUIStandardItemLighting();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void initGui() {
		super.initGui();
		int posX = (width - xSize) / 2;
		int posY = (height - ySize) / 2;
		buttonList.clear();
		String s = "OFF.Auto Feed Disabled";
		buttonList.add(new GuiButtonAHeight(0, posX + 4, posY + 180, 38, 12, s.substring(0, s.indexOf("."))));
		s = "FILL.Keep the current stacks full";
		buttonList.add(new GuiButtonAHeight(1, posX + 4, posY + 193, 38, 12, s.substring(0, s.indexOf("."))));
		s = "LOCK.Only pull the currently smelting items from the inventory";
		buttonList.add(new GuiButtonAHeight(2, posX + 4, posY + 206, 38, 12, s.substring(0, s.indexOf("."))));
		s = "ALL.Pull any smeltable items from the inventory";
		buttonList.add(new GuiButtonAHeight(3, posX + 4, posY + 219, 38, 12, s.substring(0, s.indexOf("."))));
		s = "Lock Output.Blocks items being inserted into the last 5 slots";
		buttonList.add(new GuiButtonAHeight(4, posX + 398, posY + 180, 70, 12, s.substring(0, s.indexOf("."))));
	}

	@Override
	protected void actionPerformed(GuiButton button) {
		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("type", DaWik.networkManager.TYPE_ID_SUPERCHEST);
		tag.setString("player", player.getCommandSenderName());
		tag.setInteger("x", tile.xCoord);
		tag.setInteger("y", tile.yCoord);
		tag.setInteger("z", tile.zCoord);

		switch (button.id) {
		case 0:
			tag.setInteger("buttonID", NETWORKManager.ID_DRACONIUMCHEST0);
			DaWik.networkManager.getCommunication().sendToServer(tag);
			break;
		case 1:
			tag.setInteger("buttonID", NETWORKManager.ID_DRACONIUMCHEST1);
			DaWik.networkManager.getCommunication().sendToServer(tag);
			break;
		case 2:
			tag.setInteger("buttonID", NETWORKManager.ID_DRACONIUMCHEST2);
			DaWik.networkManager.getCommunication().sendToServer(tag);
			break;
		case 3:
			tag.setInteger("buttonID", NETWORKManager.ID_DRACONIUMCHEST3);
			DaWik.networkManager.getCommunication().sendToServer(tag);
			break;
		case 4:
			tag.setInteger("buttonID", NETWORKManager.ID_DRACONIUMCHEST4);
			DaWik.networkManager.getCommunication().sendToServer(tag);
			break;
		default:
			break;
		}

		// if (button.id == 0) {
		// DaWik.networkManager.sendToServer(new
		// ButtonPacket(ButtonPacket.ID_DRACONIUMCHEST0, false));
		// } else if (button.id == 1) {
		// DaWik.network.sendToServer(new
		// ButtonPacket(ButtonPacket.ID_DRACONIUMCHEST1, false));
		// } else if (button.id == 2) {
		// DaWik.network.sendToServer(new
		// ButtonPacket(ButtonPacket.ID_DRACONIUMCHEST2, false));
		// } else if (button.id == 3) {
		// DaWik.network.sendToServer(new
		// ButtonPacket(ButtonPacket.ID_DRACONIUMCHEST3, false));
		// } else if (button.id == 4) {
		// DaWik.network.sendToServer(new
		// ButtonPacket(ButtonPacket.ID_DRACONIUMCHEST4, false));
		// }
	}

	@Override
	public void updateScreen() {
		super.updateScreen();
		if (lastAutoFeed != tile.smeltingAutoFeed) {
			lastAutoFeed = tile.smeltingAutoFeed;
			((GuiButton) buttonList.get(0)).enabled = lastAutoFeed != 0;
			((GuiButton) buttonList.get(1)).enabled = lastAutoFeed != 1;
			((GuiButton) buttonList.get(2)).enabled = lastAutoFeed != 2;
			((GuiButton) buttonList.get(3)).enabled = lastAutoFeed != 3;
		}
	}

	@Override
	public VisiblityData modifyVisiblity(GuiContainer guiContainer, VisiblityData visiblityData) {
		return null;
	}

	@Override
	public Iterable<Integer> getItemSpawnSlots(GuiContainer guiContainer, ItemStack stack) {
		return null;
	}

	@Override
	public List<TaggedInventoryArea> getInventoryAreas(GuiContainer guiContainer) {
		return Collections.emptyList();
	}

	@Override
	public boolean handleDragNDrop(GuiContainer guiContainer, int i, int i2, ItemStack stack, int i3) {
		return false;
	}

	@Override
	public boolean hideItemPanelSlot(GuiContainer guiContainer, int i, int i2, int i3, int i4) {
		return false;
	}

}
