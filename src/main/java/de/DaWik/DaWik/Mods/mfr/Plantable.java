package de.DaWik.DaWik.Mods.mfr;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import powercrystals.minefactoryreloaded.api.IFactoryPlantable;
import powercrystals.minefactoryreloaded.api.ReplacementBlock;
import de.DaWik.DaWik.Items.BaseItemSeed;

public class Plantable implements IFactoryPlantable {
	private BaseItemSeed seed;

	public Plantable(BaseItemSeed seed) {
		this.seed = seed;
	}

	@Override
	public Item getSeed() {
		return seed;
	}

	@Override
	public boolean canBePlanted(ItemStack stack, boolean forFermenting) {
		return true;
	}

	@Override
	public ReplacementBlock getPlantedBlock(World world, int x, int y, int z, ItemStack stack) {
		return new ReplacementBlock(seed.getPlant(world, x, y, z));
	}

	@Override
	public boolean canBePlantedHere(World world, int x, int y, int z, ItemStack stack) {
		Block plant = seed.getPlant(world, x, y, z);
		if ((!world.isAirBlock(x, y, z)) || (plant == null)) {
			return false;
		}
		Block ground = world.getBlock(x, y - 1, z);
		boolean correctGround = (ground != null) && ((ground.equals(Blocks.farmland)) || (ground.equals(Blocks.grass)) || (ground.equals(Blocks.dirt)));
		boolean canPlace = (plant.canPlaceBlockAt(world, x, y, z)) && (plant.canReplace(world, x, y, z, 0, stack));
		return (correctGround) && (canPlace);
	}

	@Override
	public void prePlant(World world, int x, int y, int z, ItemStack stack) {
		Block ground = world.getBlock(x, y - 1, z);
		if ((ground.equals(Blocks.grass)) || (ground.equals(Blocks.dirt))) {
			world.setBlock(x, y - 1, z, Blocks.farmland);
		}
	}

	@Override
	public void postPlant(World world, int x, int y, int z, ItemStack stack) {
	}
}
