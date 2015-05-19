package de.DaWik.DaWik.Blocks;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.DaWik.DaWik.DaWik;
import de.DaWik.DaWik.TileEntity.TileSuperChest;
import de.DaWik.DaWik.gui.GuiHandler;
import de.DaWik.DaWik.init.DaWikBlocks;
import de.DaWik.DaWik.util.ICustomItemData;
import de.DaWik.DaWik.util.References;
import de.DaWik.DaWik.util.Strings;

public class BlockSuperChest extends BlockCustomeDropp {

	private static final String[] STORAGE_BLACKLIST = new String[] { "tile.thermalexpansion.strongbox", "item.thermalexpansion.satchel", "tile.draconicevolution:draconiumChest" };

	public BlockSuperChest() {
		super();
		setBlockName(Strings.superChestName);
		setCreativeTab(DaWik.creativeTab);
		setStepSound(Block.soundTypeStone);
		setBlockBounds(0.0625F, 0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
		setResistance(2000F);

	}

	public static boolean isStackValid(ItemStack stack) {
		for (String element : BlockSuperChest.STORAGE_BLACKLIST) {
			if (stack.getUnlocalizedName().contains(element)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float cx, float cy, float cz) {
		System.out.println("Block");
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if (!(tileEntity instanceof TileSuperChest)) {
			return false;
		}
		TileSuperChest te = (TileSuperChest) tileEntity;

		if (player.isSneaking()) {
			te.editMode = !te.editMode;
			if (world.isRemote) {
				player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.DARK_GREEN + "" + StatCollector.translateToLocal("msg.draconiumChestEditmode.txt")
						+ EnumChatFormatting.DARK_AQUA + " " + String.valueOf(te.editMode)));
			}
			if (te.editMode) {
				if (world.isRemote) {
					player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.GOLD + "" + StatCollector.translateToLocal("msg.draconiumChestEditL1.txt")));
				}
				if (world.isRemote) {
					player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.GOLD + "" + StatCollector.translateToLocal("msg.draconiumChestEditL2.txt")));
				}
				if (world.isRemote) {
					player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.GOLD + "" + StatCollector.translateToLocal("msg.draconiumChestEditL3.txt")));
				}
				if (world.isRemote) {
					player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.GOLD + "" + StatCollector.translateToLocal("msg.draconiumChestEditL4.txt")));
				}
			}
			return false;
		}

		if (te.editMode && (isDye(player.getHeldItem(), "dyeRed") || isDye(player.getHeldItem(), "dyeGreen") || isDye(player.getHeldItem(), "dyeBlue"))) {
			int increment = cy > 0.5F ? 5 : -5;

			if (isDye(player.getHeldItem(), "dyeRed")) {
				te.red += increment;
				if (te.red > 255) {
					te.red = 255;
				}
				if (te.red < 0) {
					te.red = 0;
				}
			}
			if (isDye(player.getHeldItem(), "dyeGreen")) {
				te.green += increment;
				if (te.green > 255) {
					te.green = 255;
				}
				if (te.green < 0) {
					te.green = 0;
				}
			}
			if (isDye(player.getHeldItem(), "dyeBlue")) {
				te.blue += increment;
				if (te.blue > 255) {
					te.blue = 255;
				}
				if (te.blue < 0) {
					te.blue = 0;
				}
			}
			if (world.isRemote) {
				player.addChatComponentMessage(new ChatComponentText(StatCollector.translateToLocal("msg.draconiumChestRed.txt") + " " + te.red));
			}
			if (world.isRemote) {
				player.addChatComponentMessage(new ChatComponentText(StatCollector.translateToLocal("msg.draconiumChestGreen.txt") + " " + te.green));
			}
			if (world.isRemote) {
				player.addChatComponentMessage(new ChatComponentText(StatCollector.translateToLocal("msg.draconiumChestBlue.txt") + " " + te.blue));
			}
			return true;
		}

		if (te.editMode && (player.getHeldItem() != null) && player.getHeldItem().getItem().equals(Items.paper)) {
			if (player.getHeldItem().stackSize != 1) {
				if (world.isRemote) {
					player.addChatComponentMessage(new ChatComponentTranslation("msg.draconiumChestEdit2.txt"));
				}
				return true;
			}
			ItemStack paper = player.getHeldItem();
			NBTTagCompound nbt = paper.getTagCompound();
			if (nbt == null) {
				nbt = new NBTTagCompound();
			}
			if (nbt.hasKey("Is Super Chest Saved Colour")) {
				te.red = nbt.getInteger("R");
				te.green = nbt.getInteger("G");
				te.blue = nbt.getInteger("B");
			} else {
				NBTTagList lore = new NBTTagList();
				lore.appendTag(new NBTTagString("Red: " + te.red));
				lore.appendTag(new NBTTagString("Green: " + te.green));
				lore.appendTag(new NBTTagString("Blue: " + te.blue));

				NBTTagCompound display = new NBTTagCompound();
				display.setTag("Lore", lore);
				display.setString("Name", "Super Chest Saved Colour");

				nbt.setTag("display", display);
				nbt.setInteger("R", te.red);
				nbt.setInteger("G", te.green);
				nbt.setInteger("B", te.blue);
				nbt.setBoolean("Is Super Chest Saved Colour", true);
				paper.setTagCompound(nbt);
			}
			return true;
		}

		if (!world.isRemote) {
			System.out.println("oPEN");
			player.openGui(DaWik.instance, GuiHandler.GUIIDSuperChest, world, x, y, z);
			// FMLNetworkHandler.openGui(player, DaWik.instance,
			// GuiHandler.GUIIDSuperChest, world, x, y, z);
		}
		return true;
	}

	public boolean isDye(ItemStack stack, String oreName) {
		if (stack == null) {
			return false;
		}
		ArrayList<ItemStack> items = OreDictionary.getOres(oreName);
		for (ItemStack ore : items) {
			if (ore.getItem().equals(stack.getItem()) && (stack.getItemDamage() == ore.getItemDamage())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		super.breakBlock(world, x, y, z, block, meta);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileSuperChest();
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public int getRenderType() {
		return References.idSuperChest;
	}

	@Override
	public void onBlockPlacedBy(World world, int i, int j, int k, EntityLivingBase entityliving, ItemStack itemStack) {
		byte chestFacing = 0;
		int facing = MathHelper.floor_double(((entityliving.rotationYaw * 4F) / 360F) + 0.5D) & 3;
		if (facing == 0) {
			chestFacing = 2;
		}
		if (facing == 1) {
			chestFacing = 5;
		}
		if (facing == 2) {
			chestFacing = 3;
		}
		if (facing == 3) {
			chestFacing = 4;
		}
		TileEntity te = world.getTileEntity(i, j, k);
		if ((te != null) && (te instanceof TileSuperChest)) {
			TileSuperChest tedc = (TileSuperChest) te;
			tedc.setFacing(chestFacing);
			if (itemStack.hasDisplayName()) {
				tedc.setCustomName(itemStack.getDisplayName());
			}
			world.markBlockForUpdate(i, j, k);
		}
	}

	@Override
	public boolean hasComparatorInputOverride() {
		return true;
	}

	@Override
	public int getComparatorInputOverride(World par1World, int par2, int par3, int par4, int par5) {
		TileEntity te = par1World.getTileEntity(par2, par3, par4);
		if (te instanceof IInventory) {
			return Container.calcRedstoneFromInventory((IInventory) te);
		}
		return 0;
	}

	@Override
	public boolean rotateBlock(World worldObj, int x, int y, int z, ForgeDirection axis) {
		if (worldObj.isRemote) {
			return false;
		}
		if ((axis == ForgeDirection.UP) || (axis == ForgeDirection.DOWN)) {
			TileEntity tileEntity = worldObj.getTileEntity(x, y, z);
			if (tileEntity instanceof TileSuperChest) {
				TileSuperChest te = (TileSuperChest) tileEntity;
				te.rotateAround(axis);
			}
			return true;
		}
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		blockIcon = par1IconRegister.registerIcon(References.RESOURCESPREFIX + "superChest");
	}

	@Override
	protected boolean dropInventory() {
		return false;
	}

	@Override
	protected boolean hasCustomDropps() {
		return true;
	}

	@Override
	protected void getCustomTileEntityDrops(TileEntity te, List<ItemStack> droppes) {
		ItemStack stack = new ItemStack(DaWikBlocks.superChest);
		if (te instanceof ICustomItemData) {
			ICustomItemData chest = (ICustomItemData) te;
			NBTTagCompound tileTag = new NBTTagCompound();
			if (!stack.hasTagCompound()) {
				stack.setTagCompound(new NBTTagCompound());
			}
			chest.writeDataToItem(tileTag, stack);
			stack.getTagCompound().setTag(ICustomItemData.tagName, tileTag);
		}
		droppes.add(stack);
	}

}
