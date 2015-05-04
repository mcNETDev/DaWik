package de.DaWik.DaWik.Blocks.crops;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.DaWik.DaWik.DaWik;

public class BaseBlockPlant extends BlockBush implements IGrowable {
	protected int maxGrowthStage = 7;

	@SideOnly(Side.CLIENT)
	protected IIcon[] iIcon;

	protected BaseBlockPlant() {
		setTickRandomly(true);
		float f = 0.5F;
		setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
		setCreativeTab(DaWik.creativeTab);
		setHardness(0.0F);
		setStepSound(Block.soundTypeGrass);
		disableStats();
	}

	public void incrementGrowStage(World world, Random random, int x, int y, int z) {
		int growStage = world.getBlockMetadata(x, y, z) + MathHelper.getRandomIntegerInRange(random, 2, 5);

		if (growStage > maxGrowthStage) {
			growStage = maxGrowthStage;
		}

		world.setBlockMetadataWithNotify(x, y, z, growStage, 2);
	}

	@Override
	public Item getItemDropped(int p_149650_1_, Random parRand, int parFortune) {
		return Item.getItemFromBlock(this);
	}

	@Override
	public int getRenderType() {
		return 1;
	}

	@Override
	public void updateTick(World parWorld, int parX, int parY, int parZ, Random parRand) {
		super.updateTick(parWorld, parX, parY, parZ, parRand);
		int growStage = parWorld.getBlockMetadata(parX, parY, parZ) + 1;

		if (growStage > 7) {
			growStage = 7;
		}

		parWorld.setBlockMetadataWithNotify(parX, parY, parZ, growStage, 2);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int parSide, int state) {
		return iIcon[state];
	}

	@Override
	public boolean func_149851_a(World parWorld, int parX, int parY, int parZ, boolean p_149851_5_) {
		return parWorld.getBlockMetadata(parX, parY, parZ) != 7;
	}

	@Override
	public boolean func_149852_a(World p_149852_1_, Random parRand, int p_149852_3_, int p_149852_4_, int p_149852_5_) {
		return true;
	}

	@Override
	public void func_149853_b(World parWorld, Random parRand, int parX, int parY, int parZ) {
		incrementGrowStage(parWorld, parRand, parX, parY, parZ);
	}

}
