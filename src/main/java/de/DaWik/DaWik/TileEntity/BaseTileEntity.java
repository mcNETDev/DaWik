package de.DaWik.DaWik.TileEntity;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import de.DaWik.DaWik.network.DescriptionHandler;

public class BaseTileEntity extends TileEntity {

	@Override
	public Packet getDescriptionPacket() {
		System.out.println("getPacket");
		ByteBuf buf = Unpooled.buffer();
		buf.writeInt(xCoord);
		buf.writeInt(yCoord);
		buf.writeInt(zCoord);
		writeToPacket(buf);
		return new FMLProxyPacket(buf, DescriptionHandler.CHANNEL);
	}

	public void writeToPacket(ByteBuf buf) {

	}

	public void readFromPacket(ByteBuf buf) {

	}

}
