package de.DaWik.DaWik.World;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;

public class MiningBiome extends BiomeGenBase {

	public static MiningBiome instance;

	public MiningBiome(int p_i1971_1_) {
		super(p_i1971_1_);
		setColor(2900485);
		setBiomeName("Mining World Biome");
		setDisableRain();
		this.spawnableMonsterList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableWaterCreatureList.clear();
		this.spawnableCaveCreatureList.clear();
		setTemperatureRainfall(0.8F, 0.0F);
		setHeight(new BiomeGenBase.Height(0.0F, 0.0F));
		this.topBlock = Blocks.grass;
		this.fillerBlock = Blocks.dirt;
		this.theBiomeDecorator.waterlilyPerChunk = -999;
		this.theBiomeDecorator.treesPerChunk = -999;
		this.theBiomeDecorator.flowersPerChunk = -999;
		this.theBiomeDecorator.grassPerChunk = -999;
		this.theBiomeDecorator.deadBushPerChunk = -999;
		this.theBiomeDecorator.mushroomsPerChunk = -999;
		this.theBiomeDecorator.cactiPerChunk = -999;
		this.theBiomeDecorator.sandPerChunk = -999;
		this.theBiomeDecorator.sandPerChunk2 = -999;
		this.theBiomeDecorator.clayPerChunk = -999;
		this.theBiomeDecorator.reedsPerChunk = -999;
		this.theBiomeDecorator.bigMushroomsPerChunk = -999;
		this.theBiomeDecorator.generateLakes = false;
	}

	@Override
	public void decorate(World par1World, Random par2Random, int par3, int par4) {
		super.decorate(par1World, par2Random, par3, par4);
		int var5 = par2Random.nextInt(2);
		WorldGenMinable gen = new WorldGenMinable(Blocks.emerald_ore, 0, 3, Blocks.stone);
		for (int var6 = 0; var6 < var5; var6++) {
			int var7 = par3 + par2Random.nextInt(16);
			int var8 = par2Random.nextInt(28) + 4;
			int var9 = par4 + par2Random.nextInt(16);
			Block var10 = par1World.getBlock(var7, var8, var9);
			gen.generate(par1World, par2Random, var7, var8, var9);
		}
		var5 = par2Random.nextInt(16);

	}

	@Override
	public TempCategory getTempCategory() {
		return TempCategory.MEDIUM;
	}

	@Override
	public BiomeDecorator createBiomeDecorator() {
		BiomeDecorator dec = new BiomeDecorator();
		dec.generateLakes = false;
		return dec;
	}

}
