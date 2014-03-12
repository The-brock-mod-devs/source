package comdotgame.com.search;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGeneration implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		// TODO Auto-generated method stub
		  switch (world.provider.dimensionId) {
		  case 1:
		   generateNether(world, random, chunkX * 16, chunkZ * 16);
		   
		  case 0:
		   generateSurface(world, random, chunkX * 16, chunkZ * 16);
		   
		  case -1:
		   generateEnd(world, random, chunkX * 16, chunkZ * 16);
		  }
	}

	private void generateEnd(World world, Random random, int i, int j) {
		// TODO Auto-generated method stub
		
	}

	private void generateSurface(World world, Random random, int i, int j) {
		  for (int i1 = 0; i1 < 10; i1++) {
			   int xCoord = i + random.nextInt(16);
			   int yCoord = random.nextInt(60);
			   int zCoord = j + random.nextInt(16);
			   
			   (new WorldGenMinable(Mylittlepony.brock, 16)).generate(world, random, xCoord, yCoord, zCoord);
			  }
		// TODO Auto-generated method stub
		
	}

	private void generateNether(World world, Random random, int i, int j) {
		// TODO Auto-generated method stub
		
	}

}
