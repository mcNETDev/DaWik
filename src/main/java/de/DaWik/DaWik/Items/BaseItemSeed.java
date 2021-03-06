package de.DaWik.DaWik.Items;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class BaseItemSeed extends Item implements IPlantable {

	private final Block theBlockPlant;

	public BaseItemSeed(Block parBlockPlant) {
		theBlockPlant = parBlockPlant;
	}

	@Override
	public boolean onItemUse(ItemStack parItemStack, EntityPlayer parPlayer, World parWorld, int parX, int parY, int parZ, int par7, float par8, float par9, float par10) {
		if (par7 != 1) {
			return false;
		} else if (parPlayer.canPlayerEdit(parX, parY + 1, parZ, par7, parItemStack)) {
			if (parWorld.getBlock(parX, parY, parZ).canSustainPlant(parWorld, parX, parY, parZ, ForgeDirection.UP, this) && parWorld.isAirBlock(parX, parY + 1, parZ)) {
				parWorld.setBlock(parX, parY + 1, parZ, theBlockPlant);
				--parItemStack.stackSize;
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
		return EnumPlantType.Crop;
	}

	@Override
	public Block getPlant(IBlockAccess world, int x, int y, int z) {
		return theBlockPlant;
	}

	@Override
	public int getPlantMetadata(IBlockAccess world, int x, int y, int z) {
		return 0;
	}

	public void onFallenUpon(World p_149746_1_, int p_149746_2_, int p_149746_3_, int p_149746_4_, Entity p_149746_5_, float p_149746_6_) {
		if (!p_149746_1_.isRemote && (p_149746_1_.rand.nextFloat() < (p_149746_6_ - 0.5F))) {
			if (!(p_149746_5_ instanceof EntityPlayer) && !p_149746_1_.getGameRules().getGameRuleBooleanValue("mobGriefing")) {
				return;
			}

			p_149746_1_.setBlock(p_149746_2_, p_149746_3_, p_149746_4_, Blocks.dirt);
		}
	}

}
