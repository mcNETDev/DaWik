package de.DaWik.DaWik.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.inventory.Container;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import de.DaWik.DaWik.TileEntity.TileSuperChest;
import de.DaWik.DaWik.container.ContainerSuperChest;

public class ButtonPacket implements IMessage {
	public static final byte ID_DRACONIUMCHEST0 = 2;
	public static final byte ID_DRACONIUMCHEST1 = 3;
	public static final byte ID_DRACONIUMCHEST2 = 4;
	public static final byte ID_DRACONIUMCHEST3 = 5;
	public static final byte ID_DRACONIUMCHEST4 = 8;
	byte buttonId = 0;
	boolean state = false;

	public ButtonPacket() {
	}

	public ButtonPacket(byte buttonId, boolean state) {
		this.buttonId = buttonId;
		this.state = state;
	}

	@Override
	public void fromBytes(ByteBuf bytes) {
		buttonId = bytes.readByte();
		state = bytes.readBoolean();
	}

	@Override
	public void toBytes(ByteBuf bytes) {
		bytes.writeByte(buttonId);
		bytes.writeBoolean(state);
	}

	public static class Handler implements IMessageHandler<ButtonPacket, IMessage> {

		@Override
		public IMessage onMessage(ButtonPacket message, MessageContext ctx) {
			switch (message.buttonId) {
			case ID_DRACONIUMCHEST0: {
				Container container = ctx.getServerHandler().playerEntity.openContainer;
				if ((container != null) && (container instanceof ContainerSuperChest)) {
					TileSuperChest tile = ((ContainerSuperChest) container).getTile();
					tile.setAutoFeed(0);
				}
				break;
			}
			case ID_DRACONIUMCHEST1: {
				Container container = ctx.getServerHandler().playerEntity.openContainer;
				if ((container != null) && (container instanceof ContainerSuperChest)) {
					TileSuperChest tile = ((ContainerSuperChest) container).getTile();
					tile.setAutoFeed(1);
				}
				break;
			}
			case ID_DRACONIUMCHEST2: {
				Container container = ctx.getServerHandler().playerEntity.openContainer;
				if ((container != null) && (container instanceof ContainerSuperChest)) {
					TileSuperChest tile = ((ContainerSuperChest) container).getTile();
					tile.setAutoFeed(2);
				}
				break;
			}
			case ID_DRACONIUMCHEST3: {
				Container container = ctx.getServerHandler().playerEntity.openContainer;
				if ((container != null) && (container instanceof ContainerSuperChest)) {
					TileSuperChest tile = ((ContainerSuperChest) container).getTile();
					tile.setAutoFeed(3);
				}
				break;
			}
			case ID_DRACONIUMCHEST4: {
				Container container = ctx.getServerHandler().playerEntity.openContainer;
				if ((container != null) && (container instanceof ContainerSuperChest)) {
					TileSuperChest tile = ((ContainerSuperChest) container).getTile();
					tile.lockOutputSlots = !tile.lockOutputSlots;
				}
				break;
			}
			default:
				break;
			}
			return null;
		}
	}
}