package de.DaWik.DaWik.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import de.DaWik.DaWik.DaWik;
import de.DaWik.DaWik.TileEntity.TileEntityCamoMine;

@Sharable
public class DescriptionHandler extends SimpleChannelInboundHandler<FMLProxyPacket> {
	public static final String CHANNEL = "DaWikDescription";

	static {
		NetworkRegistry.INSTANCE.newChannel(DescriptionHandler.CHANNEL, new DescriptionHandler());
	}

	public static void init() {
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, FMLProxyPacket msg) throws Exception {
		ByteBuf buf = msg.payload();
		int x = buf.readInt();
		int y = buf.readInt();
		int z = buf.readInt();
		TileEntity te = DaWik.proxy.getClientPlayer().worldObj.getTileEntity(x, y, z);
		if (te instanceof TileEntityCamoMine) {
			((TileEntityCamoMine) te).readFromPacket(buf);
		}
	}
}
