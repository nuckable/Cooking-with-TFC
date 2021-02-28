package straywolfe.cookingwithtfc.common.worldgen;

import com.dunk.tfc.Core.TFC_Climate;
import com.dunk.tfc.Core.TFC_Core;
import com.dunk.tfc.api.Constant.Global;
import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenerator;
import straywolfe.cookingwithtfc.api.EnumCWTFCFruitTree;

import java.util.Random;

public class WorldGenCWTFCPlants implements IWorldGenerator
{
	private int region;

	public WorldGenCWTFCPlants()
	{
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator,
			IChunkProvider chunkProvider)
	{
		chunkX *= 16;
		chunkZ *= 16;

		int xCoord;
		int yCoord;
		int zCoord;

		region = TFC_Climate.getRegionLayer(world, chunkX, Global.SEALEVEL, chunkZ);

		float evt = TFC_Climate.getCacheManager(world).getEVTLayerAt(chunkX, chunkZ).floatdata1;
		float rain = TFC_Climate.getRainfall(world, chunkX, 144, chunkZ);
		float bioTemperature;

		xCoord = chunkX + random.nextInt(16);
		zCoord = chunkZ + random.nextInt(16);
		yCoord = world.getTopSolidOrLiquidBlock(xCoord, zCoord);
		bioTemperature = TFC_Climate.getBioTemperatureHeight(world, xCoord, yCoord, zCoord);
		
		if (getNearWater(world, xCoord, yCoord, zCoord))
		{
			rain = Math.max(rain * 2, 200);
		}

		WorldGenerator gen0 = null;
		if (random.nextInt(120) < 15)
		{
			try
			{
				boolean success = false;
				int numTrees = EnumCWTFCFruitTree.REGIONS[region].length;
				int treeNum = random.nextInt(numTrees);
				for (int t = 0; t < numTrees; t++)
				{
					EnumCWTFCFruitTree tree = EnumCWTFCFruitTree.REGIONS[region][(t + treeNum)%numTrees];
					if (success)
					{
						break;
					}
					int i = 0;
					boolean foundPlant = false;
					for (; i < EnumCWTFCFruitTree.values().length; i++)
					{
						if (tree == EnumCWTFCFruitTree.values()[i])
						{
							foundPlant = true;
							break;
						}
					}
					if (!foundPlant)
					{
						continue;
					}
					switch (tree)
					{
						case ALMOND:
						{
							gen0 = CWTFCBiome.almondTree;
							break;
						}
						case CASHEW:
						{
							gen0 = CWTFCBiome.cashewTree;
							break;
						}
						case COCONUT:
						{
							gen0 = CWTFCBiome.coconutTree;
							break;
						}
						case HAZELNUT:
						{
							gen0 = CWTFCBiome.hazelnutTree;
							break;
						}
						case MACADAMIA:
						{
							gen0 = CWTFCBiome.macadamiaTree;
							break;
						}
						case PISTACHIO:
						{
							gen0 = CWTFCBiome.pistachioTree;
							break;
						}
					}
					if (gen0 != null && shouldPlantGrow(EnumCWTFCFruitTree.values()[i], rain, bioTemperature, evt,
							random) && world.getBlock(xCoord, yCoord, zCoord).isReplaceable(world, xCoord, yCoord,
									zCoord))
					{
						if (gen0 != null)
						{
							success = gen0.generate(world, random, xCoord, yCoord, zCoord);
						}
					}
				}
			}
			catch (IndexOutOfBoundsException e)
			{
			}
		}

	}

	public static boolean shouldPlantGrow(EnumCWTFCFruitTree plant, float rainfall, float temperatureAvg, float evt,
			Random rand)
	{
		// We want to calculate whether the tree should grow
		// We do this by seeing whether the conditions are ideal
		if (evt <= plant.maxEVT && rainfall >= plant.minRain && rainfall <= plant.maxRain && temperatureAvg >= plant.minTemp && temperatureAvg <= plant.maxTemp)
		{
			// This means our values are at least possible for this type of tree
			// We take the "ideal" zone, defined as the average of max and min
			float tempIdeal = (plant.maxTemp + plant.minTemp) / 2f;
			float evtIdeal = (plant.minEVT + plant.maxEVT) / 2f;

			float rainIdeal = (plant.minRain + plant.maxRain) / 2f;
			if (plant.maxRain == 16000)
			{
				rainIdeal = 3000;
			}
			float rainValue = Math.abs(rainfall - rainIdeal) / (rainIdeal - plant.minRain);
			if (plant.maxRain == 16000 && rainfall >= 3000)
			{
				rainValue = 1f;
			}
			float evtValue = Math.abs(evt - evtIdeal) / (evtIdeal - plant.minEVT);
			float tempValue = Math.abs(temperatureAvg - tempIdeal) / (tempIdeal - plant.minTemp);
			float totalValue = (rainValue + evtValue + tempValue) / 3f;
			if (totalValue > 0)
			{
				return rand.nextFloat() < totalValue;
			}
			else
			{
				return rand.nextInt(100) == 0;
			}
		}
		return false;
	}

	public boolean getNearWater(World world, int x, int y, int z)
	{
		for (int x1 = -4; x1 < 5; ++x1)
		{
			for (int z1 = -4; z1 < 5; ++z1)
			{
				for (int y1 = -2; y1 < 1; ++y1)
				{
					if (world.blockExists(x + x1, y + y1, z + z1) && TFC_Core.isFreshWater(world.getBlock(x + x1, y + y1, z + z1)))
						return true;
				}
			}
		}
		return false;
	}
}