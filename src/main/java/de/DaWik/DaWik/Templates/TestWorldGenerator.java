//package de.DaWik.DaWik.Templates;
//
//import java.util.Random;
//
//import net.minecraft.block.Block;
//import net.minecraft.world.World;
//import net.minecraft.world.chunk.IChunkProvider;
//import net.minecraft.world.gen.feature.WorldGenMinable;
//import cpw.mods.fml.common.IWorldGenerator;
//import cpw.mods.fml.common.registry.GameRegistry;
//import de.DaWik.DaWik.DaWik;
//
//public class TestWorldGenerator implements IWorldGenerator {
////
////	//		GameRegistry.registerWorldGenerator(new TestWorldGenerator(), 1);
////	//POSTINIT
////	
////	@Override
////	public void generate(Random random, int chunkX, int chunkZ, World world,
////			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
////
////		switch (world.provider.dimensionId) {
////		case 0:
////			generateOverWorld(world, random, chunkX * 16, chunkZ * 16);
////			break;
////		case 1:
////			generateEndWorld(world, random, chunkX * 16, chunkZ * 16);
////			break;
////		case -1:
////			generateNetherWorld(world, random, chunkX * 16, chunkZ * 16);
////			break;
////
////		default:
////			break;
////		}
////
////	}
////
////	private void generateEndWorld(World world, Random random, int x, int z) {
////
////	}
////
////	private void generateNetherWorld(World world, Random random, int x, int z) {
////
////	}
////
////	private void generateOverWorld(World world, Random random, int x, int z) {
////		genOre(DaWik.blockTest, world, random, x, z, 16, 16, 6 + random.nextInt(4), 10, 20, 128);
////		genOre(DaWik.blockTest2, world, random, x, z, 16, 16, 6 + random.nextInt(4), 10, 20, 128);
////
////	}
////
////	public void genOre(Block block, World world, Random random, int posX, int posZ, int maxX,
////			int maxZ, int maxAderlenge, int anzahlAdern, int minY, int maxY) {
////		int differenzMinMaxY = maxY - minY;
////
////		for (int i = 0; i < anzahlAdern; i++) {
////			int posiX = posX + random.nextInt(maxX);
////			int posiY = minY + random.nextInt(differenzMinMaxY);
////			int posiZ = posZ + random.nextInt(maxZ);
////			WorldGenMinable gen = new WorldGenMinable(block, maxAderlenge);
////			gen.generate(world, random, posiX, posiY, posiZ);
////		}
////
////	}
//
//}
