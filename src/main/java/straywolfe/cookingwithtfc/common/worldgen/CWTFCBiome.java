package straywolfe.cookingwithtfc.common.worldgen;

import java.util.Random;

import com.dunk.tfc.WorldGen.TFCBiome;
import com.dunk.tfc.WorldGen.Generators.Trees.WorldGenCustomBigTree;
import com.dunk.tfc.WorldGen.Generators.Trees.WorldGenCustomTallTrees;
import com.dunk.tfc.WorldGen.Generators.Trees.WorldGenFruitTree;
import com.dunk.tfc.api.Enums.EnumTree;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenerator;
import straywolfe.cookingwithtfc.api.EnumCWTFCFruitTree;
import straywolfe.cookingwithtfc.api.EnumCWTFCTree;

public class CWTFCBiome extends BiomeGenBase {

	protected static WorldGenBigTrees worldGenWalnutTrees;
	
	public static final WorldGenFruitTrees almondTreeFromSapling = new WorldGenFruitTrees(false,0,true,0);
	public static final WorldGenFruitTrees almondTree = new WorldGenFruitTrees(false,0,false,0);
	
	public static final WorldGenFruitTrees cashewTreeFromSapling = new WorldGenFruitTrees(false,1,true,1);
	public static final WorldGenFruitTrees cashewTree = new WorldGenFruitTrees(false,1,false,1);
	
	public static final WorldGenFruitTrees coconutTreeFromSapling = new WorldGenFruitTrees(false,2,true,2);
	public static final WorldGenFruitTrees coconutTree = new WorldGenFruitTrees(false,2,false,2);
	
	public static final WorldGenFruitTrees hazelnutTreeFromSapling = new WorldGenFruitTrees(false,3,true,3);
	public static final WorldGenFruitTrees hazelnutTree = new WorldGenFruitTrees(false,3,false,3);
	
	public static final WorldGenFruitTrees macadamiaTreeFromSapling = new WorldGenFruitTrees(false,4,true,4);
	public static final WorldGenFruitTrees macadamiaTree = new WorldGenFruitTrees(false,4,false,4);
	
	public static final WorldGenFruitTrees pistachioTreeFromSapling = new WorldGenFruitTrees(false,5,true,5);
	public static final WorldGenFruitTrees pistachioTree = new WorldGenFruitTrees(false,5,false,5);
	
	public static final WorldGenFruitTrees[] fruitTreesFromSapling = {almondTreeFromSapling, cashewTreeFromSapling, coconutTreeFromSapling,
			hazelnutTreeFromSapling, macadamiaTreeFromSapling, pistachioTreeFromSapling};

	public CWTFCBiome(int par1) {
		super(par1);
		
		worldGenWalnutTrees = new WorldGenBigTrees(false, 0, false);
	}
	
	public static EnumCWTFCTree getEnumTreeFromId(int id)
	{
		return EnumCWTFCTree.values()[id];
	}
	
	public static WorldGenerator getTreeGen(Boolean j, boolean fromSapling, EnumCWTFCTree tree)
	{
		Random r = new Random();
		if(tree == null)
		{
			return null;
		}
		if (fromSapling)
		{
			switch (tree)
			{
			case WALNUT:
			{
				return worldGenWalnutTrees;
			}
			}
			return null;
		}
		switch (tree)
		{
		case WALNUT:
		{
			return worldGenWalnutTrees;
		}
		}
		return null;
	}
	
	public static EnumCWTFCFruitTree getEnumFruitTreeFromId(int id)
	{
		return EnumCWTFCFruitTree.values()[id];
	}
	
	public static WorldGenerator getFruitTreeGen(Boolean j, boolean fromSapling, EnumCWTFCFruitTree tree)
	{
		Random r = new Random();
		if(tree == null)
		{
			return null;
		}
		if (fromSapling)
		{
			switch (tree)
			{
			case ALMOND:
				return almondTreeFromSapling;
			case CASHEW:
				return cashewTreeFromSapling;
			case COCONUT:
				return coconutTreeFromSapling;
			case HAZELNUT:
				return hazelnutTreeFromSapling;
			case MACADAMIA:
				return macadamiaTreeFromSapling;
			case PISTACHIO:
				return pistachioTreeFromSapling;
			default:
				return null;
			}
		}
		switch (tree)
		{
		case ALMOND:
			return almondTree;
		case CASHEW:
			return cashewTree;
		case COCONUT:
			return coconutTree;
		case HAZELNUT:
			return hazelnutTree;
		case MACADAMIA:
			return macadamiaTree;
		case PISTACHIO:
			return pistachioTree;
		default:
			return null;
		}
	}

}
