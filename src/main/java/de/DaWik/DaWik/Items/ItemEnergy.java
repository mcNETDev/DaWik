package de.DaWik.DaWik.Items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cofh.api.energy.IEnergyContainerItem;
import cofh.lib.util.helpers.EnergyHelper;
import cofh.lib.util.helpers.MathHelper;
import cofh.lib.util.helpers.StringHelper;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

public class ItemEnergy extends Item implements IEnergyContainerItem {
	public int maxEnergy = 160000;
	public int maxTransfer = 1600;
	public int energyPerUse = 2000;
	int damage = 0;

	public ItemEnergy() {

	}

	public ItemEnergy setEnergyParams(int maxEnergy, int maxTransfer, int energyPerUse) {
		this.maxEnergy = maxEnergy;
		this.maxTransfer = maxTransfer;
		this.energyPerUse = energyPerUse;
		return this;
	}

	protected int useEnergy(ItemStack itemStack, boolean paramBoolean) {
		int i = MathHelper.clampI(EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, itemStack), 0, 4);
		return extractEnergy(itemStack, (energyPerUse * (5 - i)) / 5, paramBoolean);
	}

	protected int getEnergyPerUse(ItemStack itemStack) {
		int i = MathHelper.clampI(EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, itemStack), 0, 4);
		return (((energyPerUse) * (5 - i)) / 5);
	}

	@Override
	public EnumRarity getRarity(ItemStack item) {
		return EnumRarity.uncommon;
	}

	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		list.add(EnergyHelper.setDefaultEnergyTag(new ItemStack(item, 1, 0), 0));
		list.add(EnergyHelper.setDefaultEnergyTag(new ItemStack(item, 1, 0), maxEnergy));
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean check) {
		if (StringHelper.displayShiftForDetail && !StringHelper.isShiftKeyDown()) {
			list.add(StringHelper.shiftForDetails());
		}
		if (!StringHelper.isShiftKeyDown()) {
			return;
		}
		if (stack.stackTagCompound == null) {
			EnergyHelper.setDefaultEnergyTag(stack, 0);
		}
		list.add(StringHelper.localize("info.cofh.charge") + ": " + stack.stackTagCompound.getInteger("Energy") + " / " + maxEnergy + " RF");
		if (getEnergyStored(stack) >= getEnergyPerUse(stack)) {
			list.add("");
			list.add(StringHelper.LIGHT_BLUE + "+" + damage + " " + StringHelper.localize("info.cofh.damageAttack") + StringHelper.END);
		}
	}

	@Override
	public void setDamage(ItemStack stack, int damage) {
		super.setDamage(stack, 0);

	}

	@Override
	public int getDisplayDamage(ItemStack stack) {
		if (stack.stackTagCompound == null) {
			EnergyHelper.setDefaultEnergyTag(stack, 0);
		}
		return maxEnergy - stack.stackTagCompound.getInteger("Energy");
	}

	@Override
	public int getMaxDamage(ItemStack stack) {
		return maxEnergy;
	}

	@Override
	public boolean showDurabilityBar(ItemStack stack) {
		return (stack.stackTagCompound == null) || !stack.stackTagCompound.getBoolean("CreativeTab");
	}

	@Override
	public boolean isDamaged(ItemStack stack) {
		return true;
	}

	@Override
	public Multimap getItemAttributeModifiers() {
		return HashMultimap.create();
	}

	@Override
	public IIcon getIconIndex(ItemStack stack) {
		return getIcon(stack, 0);
	}

	@Override
	public IIcon getIcon(ItemStack stack, int pass) {

		return itemIcon;
	}

	@Override
	public void registerIcons(IIconRegister ir) {
		itemIcon = ir.registerIcon(getIconString());
	}

	@Override
	public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {
		if (container.stackTagCompound == null) {
			EnergyHelper.setDefaultEnergyTag(container, 0);
		}
		int stored = container.stackTagCompound.getInteger("Energy");
		int receive = Math.min(maxReceive, Math.min(maxEnergy - stored, maxTransfer));

		if (!simulate) {
			stored += receive;
			container.stackTagCompound.setInteger("Energy", stored);
		}
		return receive;
	}

	@Override
	public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {
		if (container.stackTagCompound == null) {
			EnergyHelper.setDefaultEnergyTag(container, 0);
		}
		if (container.stackTagCompound.hasKey("Unbreakable")) {
			container.stackTagCompound.removeTag("Unbreakable");
		}
		int stored = container.stackTagCompound.getInteger("Energy");
		int extract = Math.min(maxExtract, stored);

		if (!simulate) {
			stored -= extract;
			container.stackTagCompound.setInteger("Energy", stored);
		}
		return extract;
	}

	@Override
	public int getEnergyStored(ItemStack container) {

		if (container.stackTagCompound == null) {
			EnergyHelper.setDefaultEnergyTag(container, 0);
		}
		return container.stackTagCompound.getInteger("Energy");
	}

	@Override
	public int getMaxEnergyStored(ItemStack container) {

		return maxEnergy;
	}

}
