package de.DaWik.DaWik.World.Admin;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import de.DaWik.DaWik.DaWikDimensionManager;
import de.DaWik.DaWik.World.MiningBiome;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldProviderEnd;
import net.minecraft.world.WorldProviderHell;
import net.minecraft.world.WorldProviderSurface;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderEnd;

public class DaWikMiningWorldProvider extends WorldProvider {

	@Override
	public String getDimensionName() {
		return DaWikDimensionManager.getName(dimensionId);
	}

	// TODO MINING

	@Override
	public boolean canRespawnHere() {
		return false;
	}

	@SideOnly(Side.CLIENT)
	public boolean doesXZShowFog(int p_76568_1_, int p_76568_2_) {
		return false;
	}

	public boolean isSurfaceWorld() {
		return true;
	}

	@SideOnly(Side.CLIENT)
	public boolean isSkyColored() {
		return true;
	}

	@Override
	public boolean canDoLightning(Chunk chunk) {
		return true;
	}

	public IChunkProvider createChunkGenerator() {
		return new DaWikMiningWorldChunkProvider(this.worldObj, this.worldObj.getSeed(), false);
	}

	public void registerWorldChunkManager() {
		this.worldChunkMgr = new WorldChunkManagerHell(MiningBiome.instance, 0.0F);
		this.hasNoSky = true;
	}

	@Override
	protected void generateLightBrightnessTable() {
		for (int i = 0; i < this.lightBrightnessTable.length; i++) {
			this.lightBrightnessTable[i] = 4.0F;
		}
	}
	@Override
	public float calculateCelestialAngle(long p_76563_1_, float p_76563_3_) {
		return 1.0F;
	}
}
