package de.DaWik.DaWik.World;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.ChunkProviderGenerate;
import de.DaWik.DaWik.Config.ConfigManager;

public class DaWikMiningWorldChunkProvider extends ChunkProviderGenerate {

	public DaWikMiningWorldChunkProvider(World p_i2006_1_, long p_i2006_2_, boolean p_i2006_4_) {
		super(p_i2006_1_, p_i2006_2_, p_i2006_4_);

	}

	@Override
	public void func_147424_a(int p_147424_1_, int p_147424_2_, Block[] blocks) {
		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				for (int y = 0; (y <= 255) && (y >= 0); y++) {
					int code = (((x * 16) + z) * 256) + y;
					if (y < ConfigManager.miningWorldHeight) {
						blocks[code] = Blocks.stone;
					} else {
						blocks[code] = Blocks.air;
					}
				}
			}
		}

	}
}
