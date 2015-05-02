package de.DaWik.DaWik.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;

public class MessageExplode extends BaseMessage<MessageExplode> {

	private float size;

	public MessageExplode() {
	}

	public MessageExplode(float size) {
		this.size = size;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		size = buf.readFloat();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeFloat(size);
		// ByteBufUtils can be used
	}

	@Override
	public void handleClientSide(MessageExplode message, Object object) {

	}

	@Override
	public void handleServerSide(MessageExplode message, EntityPlayerMP player) {
		player.worldObj.createExplosion(player, player.posX, player.posY, player.posZ, message.size, true);
	}

}
