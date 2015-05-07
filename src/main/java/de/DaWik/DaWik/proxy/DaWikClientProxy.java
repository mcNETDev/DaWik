package de.DaWik.DaWik.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import de.DaWik.DaWik.Keys.KeyInputHandler;
import de.DaWik.DaWik.Keys.Keybinding;

public class DaWikClientProxy extends DaWikProxy {

	@Override
	public void preInit() {
		registerKeybinds();
	}

	private void registerKeybinds() {
		FMLCommonHandler.instance().bus().register(new KeyInputHandler());
		for (Keybinding key : Keybinding.values()) {
			ClientRegistry.registerKeyBinding(key.getKeybind());
		}
	}

	@Override
	public void init() {

	}

	@Override
	public void postInit() {

	}

	@Override
	public EntityPlayer getClientPlayer() {
		return Minecraft.getMinecraft().thePlayer;
	}

	public void registerRender() {

	}
}
