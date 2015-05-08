package de.DaWik.DaWik.proxy;

import net.minecraft.entity.player.EntityPlayer;

public abstract class DaWikProxy {
	public abstract void preInit();

	public abstract void init();

	public abstract void postInit();

	public abstract EntityPlayer getClientPlayer();

	public abstract void registerRenderers();
}