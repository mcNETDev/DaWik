package de.DaWik.DaWik.World.Admin;

import java.util.List;

import de.DaWik.DaWik.DaWik;
import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderGenerate;

public class DaWikMiningWorldChunkProvider extends ChunkProviderGenerate {

	public DaWikMiningWorldChunkProvider(World p_i2006_1_, long p_i2006_2_, boolean p_i2006_4_) {
		super(p_i2006_1_, p_i2006_2_, p_i2006_4_);

	}

	@Override
	public void func_147424_a(int p_147424_1_, int p_147424_2_, Block[] blocks) {
		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				for (int y = 0; (y <= 255) && (y >= 0); y++) {
					int code = (x * 16 + z) * 256 + y;
					if (y < DaWik.miningWorldHigh) {
						blocks[code] = Blocks.stone;
					} else {
						blocks[code] = Blocks.air;
					}
				}
			}
		}

	}

}
