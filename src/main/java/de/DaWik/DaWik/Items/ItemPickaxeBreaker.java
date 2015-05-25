package de.DaWik.DaWik.Items;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import de.DaWik.DaWik.DaWik;

public class ItemPickaxeBreaker extends ItemPickaxe {

	public static final String UNLOCALNAME = "Breaker";

	public ItemPickaxeBreaker() {
		super(EnumHelper.addToolMaterial("dawik", 100, 10000, 6000.0F, 1234.0F, 50));
		setCreativeTab(DaWik.creativeTab);
		setTextureName("DaWik:" + ItemPickaxeBreaker.UNLOCALNAME);
		setUnlocalizedName(ItemPickaxeBreaker.UNLOCALNAME);
	}

	@Override
	public float getDigSpeed(ItemStack stack, Block block, int meta) {
		return efficiencyOnProperMaterial;
	}

	@Override
	public void setDamage(ItemStack stack, int damage) {
		super.setDamage(stack, 0);
	}

	@Override
	public boolean onBlockStartBreak(ItemStack itemstack, int X, int Y, int Z, EntityPlayer player) {
		setDamage(itemstack, 0);
		return false;
	}

	@Override
	public boolean onBlockDestroyed(ItemStack itemstack, World p_150894_2_, Block p_150894_3_, int p_150894_4_, int p_150894_5_, int p_150894_6_, EntityLivingBase p_150894_7_) {
		setDamage(itemstack, 0);
		return true;
	}

	@Override
	public boolean hitEntity(ItemStack p_77644_1_, EntityLivingBase p_77644_2_, EntityLivingBase p_77644_3_) {
		setDamage(p_77644_1_, 0);
		return true;
	}
}
