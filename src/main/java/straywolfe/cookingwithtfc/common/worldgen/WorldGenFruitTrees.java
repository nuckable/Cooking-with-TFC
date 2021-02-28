package straywolfe.cookingwithtfc.common.worldgen;

import java.util.Random;

import com.dunk.tfc.Blocks.Flora.BlockBranch;
import com.dunk.tfc.Core.TFC_Core;
import com.dunk.tfc.TileEntities.TEFruitLeaves;
import com.dunk.tfc.TileEntities.TEFruitTreeWood;
import com.dunk.tfc.WorldGen.Generators.Trees.WorldGenFruitTree;
import com.dunk.tfc.WorldGen.Generators.Trees.WorldGenTreeBase;
import com.dunk.tfc.api.TFCBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import straywolfe.cookingwithtfc.api.CWTFCBlocks;
import straywolfe.cookingwithtfc.common.block.BlockCWTFCFruitTreeBranch;
import straywolfe.cookingwithtfc.common.core.CWTFC_Core;
import straywolfe.cookingwithtfc.common.lib.Constants;
import straywolfe.cookingwithtfc.common.tileentity.TECWTFCFruitLeaves;
import straywolfe.cookingwithtfc.common.tileentity.TECWTFCFruitTreeWood;

public class WorldGenFruitTrees extends WorldGenTreeBase
{
	private int fruitId;
	private int treeId;

	public WorldGenFruitTrees(boolean bb, int id, boolean sap, int fId)
	{
		super(bb, id, sap);
		this.fruitId = fId;
		this.block2 = 0;
		this.treeId = fId;
		this.leafBlock = CWTFCBlocks.fruitTreeLeaves;
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z)
	{
		if (!fromSapling)
		{
			height = this.fruitId == Constants.COCONUTID? 2 + rand.nextInt(5):2 + rand.nextInt(2); // Coconuts trees are taller than other fruit trees
			
			for (int xD = -1; xD < 2; xD++)
			{
				for (int zD = -1; zD < 2; zD++)
				{
					for (int yD = 0; yD <= height + ((this.fruitId==Constants.COCONUTID)?1:0); yD++)
					{
						if (!world.getBlock(x + xD, y + yD, z + zD).isReplaceable(world, x + xD, y + yD, z + zD))
						{
							return false;
						}
					}
				}
			}
			if((!TFC_Core.isSoil(world.getBlock(x, y-1, z)) && !(TFC_Core.isSand(world.getBlock(x, y-1, z))&&fruitId==Constants.COCONUTID) )|| world.getBlock(x, y, z).getMaterial()== Material.water)
			{
				return false;
			}
			this.tempSourceX = x;
			this.tempSourceZ = z;
			for (int yD = 0; yD <= height; yD++)
			{
				world.setBlock(x, y + yD, z, CWTFCBlocks.fruitTreeBranch__y_,this.treeId,2);
			}
			
			// Coconut
			if(this.fruitId == Constants.COCONUTID)
			{
				world.setBlock(x, y + height + 1, z, CWTFCBlocks.fruitTreeBranchEnd__y_,this.treeId,2);
				TECWTFCFruitTreeWood te = (TECWTFCFruitTreeWood) world.getTileEntity(x, y + height + 1, z);
				if(te != null)
				{
					te.fruitType = fruitId;
				}
				return true;
			}
			
			if (rand.nextBoolean())
			{
				generateRandomBranches(world, rand, x, y + height - rand.nextInt(2), z, new int[] { -1, 1, -1 }, 1, 3);
			}
			else
			{
				generateRandomBranches(world, rand, x, y + height - rand.nextInt(2), z, new int[] { -1, 1, 0 }, 1, 3);
			}
			if (rand.nextBoolean())
			{
				generateRandomBranches(world, rand, x, y + height - rand.nextInt(2), z, new int[] { 1, 1, -1 }, 1, 3);
			}
			else
			{
				generateRandomBranches(world, rand, x, y + height - rand.nextInt(2), z, new int[] { 0, 1, -1 }, 1, 3);
			}
			if (rand.nextBoolean())
			{
				generateRandomBranches(world, rand, x, y + height - rand.nextInt(2), z, new int[] { 1, 1, 1 }, 1, 3);
			}
			else
			{
				generateRandomBranches(world, rand, x, y + height - rand.nextInt(2), z, new int[] { 1, 1, 0 }, 1, 3);
			}
			if (rand.nextBoolean())
			{
				generateRandomBranches(world, rand, x, y + height - rand.nextInt(2), z, new int[] { -1, 1, 1 }, 1, 3);
			}
			else
			{
				generateRandomBranches(world, rand, x, y + height - rand.nextInt(2), z, new int[] { 0, 1, 1 }, 1, 3);
			}
		}
		return false;

	}

	/**
	 * This is used to make fruit trees grow branches. Only call this if you
	 * know the branch is allowed to grow.
	 * 
	 * @param world
	 * @param rand
	 * @param x
	 * @param y
	 * @param z
	 */
	public void growBranch(World world, Random rand, int x, int y, int z)
	{
		if(y >= 255)
		{
			return;
		}
		Block b = world.getBlock(x, y, z);
		if (b instanceof BlockCWTFCFruitTreeBranch && ((BlockCWTFCFruitTreeBranch) b).isEnd())
		{
			if (b == CWTFCBlocks.fruitTreeBranchEnd__y_)
			{
				boolean complete = false;
				
				if (((BlockCWTFCFruitTreeBranch) b).getDistanceToTrunk(world, x, y, z, 0) > 2 && this.fruitId != Constants.COCONUTID)  // Other fruit trees
				{
					if (rand.nextBoolean())
					{
						generateRandomBranches(world, rand, x, y+ height - rand.nextInt(2), z, new int[] { -1, 1, -1 }, 1, 1);
					}
					else
					{
						generateRandomBranches(world, rand, x, y+ height - rand.nextInt(2) , z, new int[] { -1, 1, 0 }, 1, 1);
					}
					if (rand.nextBoolean())
					{
						generateRandomBranches(world, rand, x, y+ height - rand.nextInt(2) , z, new int[] { 1, 1, -1 }, 1, 1);
					}
					else
					{
						generateRandomBranches(world, rand, x, y+ height - rand.nextInt(2) , z, new int[] { 0, 1, -1 }, 1, 1);
					}
					if (rand.nextBoolean())
					{
						generateRandomBranches(world, rand, x, y+ height - rand.nextInt(2) , z, new int[] { 1, 1, 1 }, 1, 1);
					}
					else
					{
						generateRandomBranches(world, rand, x, y+ height - rand.nextInt(2) , z, new int[] { 1, 1, 0 }, 1, 1);
					}
					if (rand.nextBoolean())
					{
						generateRandomBranches(world, rand, x, y+ height - rand.nextInt(2) , z, new int[] { -1, 1, 1 }, 1, 1);
					}
					else
					{
						generateRandomBranches(world, rand, x, y+ height - rand.nextInt(2) , z, new int[] { 0, 1, 1 }, 1, 1);
					}
					complete = true;
				} else if(world.getBlock(x, y+1, z).isReplaceable(world, x, y+1, z))
				{
					world.setBlock(x, y+1, z, CWTFCBlocks.fruitTreeBranchEnd__y_, this.treeId, 2);
					TECWTFCFruitTreeWood te = (TECWTFCFruitTreeWood) world.getTileEntity(x, y+1, z);
					TECWTFCFruitTreeWood originalTE = (TECWTFCFruitTreeWood) world.getTileEntity(x, y, z);
					if(te != null && originalTE != null)
					{
						te.setBirthWood(originalTE.birthTimeWood + originalTE.BRANCH_GROW_TIME);
						te.fruitType = fruitId;
					}
					complete = true;
				}
				if(complete)
				{
					Block newB = CWTFC_Core.getSourcedFruitBranchForBranch(b, 0, 1, 0);
					world.setBlock(x, y, z, newB,this.treeId,2);
					return;
				}
			} 
			int[] direction = new int[3];
			direction[0] = ((BlockCWTFCFruitTreeBranch) b).getSourceX() * -1;
			direction[1] = ((BlockCWTFCFruitTreeBranch) b).getSourceY() * -1;
			direction[2] = ((BlockCWTFCFruitTreeBranch) b).getSourceZ() * -1;
			if (generateRandomBranches(world, rand, x, y, z, direction, 3, 1))
			{
				Block newB = CWTFC_Core.getSourcedFruitBranchForBranch(b, direction[0], direction[1], direction[2]);
				world.setBlock(x, y, z, newB,this.treeId,2);
				TECWTFCFruitTreeWood te = (TECWTFCFruitTreeWood) world.getTileEntity(x, y, z);
				if(te != null)
				{
					te.fruitType = fruitId;
				}
				return;
			}
		}
	}
	
	@Override
	protected boolean generateRandomBranches(World world, Random rand, int xCoord, int yCoord, int zCoord,
			int[] currentDirection, int numBranches, int remainingDistance)
	{
		if (remainingDistance < 1)
		{
			return true;
		}
		boolean[] validDirections = new boolean[] { false, false, false, false, false, false, false, false, false,
				false, false, false, false, false, false, false };

		int numValidDirections = 0;

		if (currentDirection[0] != 1)
		{
			if (currentDirection[2] != 1)
			{
				validDirections[0] = true;
				numValidDirections++;
				validDirections[0 + 8] = true;
				numValidDirections++;
			}
			validDirections[1] = true;
			numValidDirections++;
			validDirections[1 + 8] = true;
			numValidDirections++;
			if (currentDirection[2] != -1)
			{
				validDirections[2] = true;
				numValidDirections++;
				validDirections[2 + 8] = true;
				numValidDirections++;
			}
		}
		if (currentDirection[2] != 1)
		{
			if (currentDirection[0] != -1)
			{
				validDirections[3] = true;
				numValidDirections++;
				validDirections[3 + 8] = true;
				numValidDirections++;
			}
			validDirections[4] = true;
			numValidDirections++;
			validDirections[4 + 8] = true;
			numValidDirections++;
		}
		if (currentDirection[2] != -1)
		{
			if (currentDirection[0] != -1)
			{
				validDirections[5] = true;
				numValidDirections++;
				validDirections[5 + 8] = true;
				numValidDirections++;
			}
			validDirections[6] = true;
			numValidDirections++;
			validDirections[6 + 8] = true;
			numValidDirections++;
		}
		if (currentDirection[0] != -1)
		{
			validDirections[7] = true;
			numValidDirections++;
			validDirections[7 + 8] = true;
			numValidDirections++;
		}
		numBranches = Math.min(numBranches, numValidDirections);
		boolean placedBranch = false;
		int[] curDir = null;
		Block theBranch = null;
		for (int i = 0; i < numBranches; i++)
		{
			if (numValidDirections == 0)
			{
				break;
			}
			curDir = null;

			boolean ignoreBranching = false;
			if (remainingDistance > 1)
			{
				// 50/50 chance whether we keep going straight or start
				// branching.
				ignoreBranching = rand.nextBoolean();
			}

			if (!ignoreBranching)
			{
				int index = rand.nextInt(directions.length);
				while (!validDirections[index])
				{
					index = rand.nextInt(directions.length);
				}
				validDirections[index] = false;
				numValidDirections--;
				curDir = directions[index];
			}
			else
			{
				curDir = currentDirection;
			}

			if (curDir[0] * curDir[2] != 0 || curDir[0] * curDir[1] != 0 || curDir[1] * curDir[2] != 0)
			{
				if (shouldSubtractDistance(rand))
				{
					remainingDistance--;
				}
			}
			if (world.getBlock(xCoord + curDir[0], yCoord + curDir[1], zCoord + curDir[2]).isReplaceable(world, xCoord + curDir[0], yCoord + curDir[1], zCoord + curDir[2])
					|| world.getBlock(xCoord + curDir[0], yCoord + curDir[1], zCoord + curDir[2]).getMaterial() == Material.leaves)
			{
				theBranch = null;
				if (curDir[1] == 1)
				{
					if (curDir[0] == -1)
					{
						if (curDir[2] == -1)
						{
							theBranch = remainingDistance > 1 ? CWTFCBlocks.fruitTreeBranch_XyZ : CWTFCBlocks.fruitTreeBranchEnd_XyZ;
						}
						else if (curDir[2] == 0)
						{
							theBranch = remainingDistance > 1 ? CWTFCBlocks.fruitTreeBranch_Xy_ : CWTFCBlocks.fruitTreeBranchEnd_Xy_;
						}
						else
						{
							theBranch = remainingDistance > 1 ? CWTFCBlocks.fruitTreeBranch_Xyz : CWTFCBlocks.fruitTreeBranchEnd_Xyz;
						}
					}
					else if (curDir[0] == 0)
					{
						if (curDir[2] == -1)
						{
							theBranch = remainingDistance > 1 ? CWTFCBlocks.fruitTreeBranch__yZ : CWTFCBlocks.fruitTreeBranchEnd__yZ;
						}
						else
						{
							theBranch = remainingDistance > 1 ? CWTFCBlocks.fruitTreeBranch__yz : CWTFCBlocks.fruitTreeBranchEnd__yz;
						}
					}
					else
					{
						if (curDir[2] == -1)
						{
							theBranch = remainingDistance > 1 ? CWTFCBlocks.fruitTreeBranch_xyZ : CWTFCBlocks.fruitTreeBranchEnd_xyZ;
						}
						else if (curDir[2] == 0)
						{
							theBranch = remainingDistance > 1 ? CWTFCBlocks.fruitTreeBranch_xy_ : CWTFCBlocks.fruitTreeBranchEnd_xy_;
						}
						else
						{
							theBranch = remainingDistance > 1 ? CWTFCBlocks.fruitTreeBranch_xyz : CWTFCBlocks.fruitTreeBranchEnd_xyz;
						}
					}
				}
				else if (curDir[1] == 0)
				{
					if (curDir[0] == -1)
					{
						if (curDir[2] == -1)
						{
							theBranch = remainingDistance > 1 ? CWTFCBlocks.fruitTreeBranch_X_Z : CWTFCBlocks.fruitTreeBranchEnd_X_Z;
						}
						else if (curDir[2] == 0)
						{
							theBranch = remainingDistance > 1 ? CWTFCBlocks.fruitTreeBranch_X__ : CWTFCBlocks.fruitTreeBranchEnd_X__;
						}
						else
						{
							theBranch = remainingDistance > 1 ? CWTFCBlocks.fruitTreeBranch_X_z : CWTFCBlocks.fruitTreeBranchEnd_X_z;
						}
					}
					else if (curDir[0] == 0)
					{
						if (curDir[2] == -1)
						{
							theBranch = remainingDistance > 1 ? CWTFCBlocks.fruitTreeBranch___Z : CWTFCBlocks.fruitTreeBranchEnd___Z;
						}
						else
						{
							theBranch = remainingDistance > 1 ? CWTFCBlocks.fruitTreeBranch___z : CWTFCBlocks.fruitTreeBranchEnd___z;
						}
					}
					else
					{
						if (curDir[2] == -1)
						{
							theBranch = remainingDistance > 1 ? CWTFCBlocks.fruitTreeBranch_x_Z : CWTFCBlocks.fruitTreeBranchEnd_x_Z;
						}
						else if (curDir[2] == 0)
						{
							theBranch = remainingDistance > 1 ? CWTFCBlocks.fruitTreeBranch_x__ : CWTFCBlocks.fruitTreeBranchEnd_x__;
						}
						else
						{
							theBranch = remainingDistance > 1 ? CWTFCBlocks.fruitTreeBranch_x_z : CWTFCBlocks.fruitTreeBranchEnd_x_z;
						}
					}
				}
				// If the branch directly below is the same, it'll look ugly, so
				// skip it.
				if (world.getBlock(xCoord + curDir[0], yCoord - 1 + curDir[1], zCoord + curDir[2]) == theBranch
						&& !ignoreBranching)
				{
					i--;
					continue;
				}
				// We only want to place this branch here if this branch can
				// continue.
				if (generateRandomBranches(world, rand, xCoord + curDir[0], yCoord + curDir[1], zCoord + curDir[2],
						curDir, 3, remainingDistance - 1))
				{
					world.setBlock(xCoord + curDir[0], yCoord + curDir[1], zCoord + curDir[2], theBranch, treeId, 2);
					TECWTFCFruitTreeWood te = (TECWTFCFruitTreeWood) world.getTileEntity(xCoord + curDir[0], yCoord + curDir[1], zCoord + curDir[2]);
					if(te != null)
					{
						te.fruitType = fruitId;
						TileEntity originalTE = world.getTileEntity(xCoord, yCoord, zCoord);
						if(fromSapling && originalTE != null && originalTE instanceof TECWTFCFruitTreeWood)
						{
							//This means we're growing one at a time
							te.setBirthWood(((TECWTFCFruitTreeWood)originalTE).birthTimeWood + ((TECWTFCFruitTreeWood)originalTE).BRANCH_GROW_TIME);
						}
					}
					if (((BlockCWTFCFruitTreeBranch) theBranch).isEnd() && !fromSapling)
					{
						placeLeaves(world, rand, xCoord + curDir[0], yCoord + curDir[1], zCoord + curDir[2]);
					}
					placedBranch = true;
				}
			}
		}
		return placedBranch;
	}
	
	@Override
	public boolean placeLeaves(World world, Random random, int xCoord, int yCoord, int zCoord)
	{
		int radius = height > 4?3:2;
		boolean lost = BlockCWTFCFruitTreeBranch.shouldLoseLeaf(world, xCoord , yCoord, zCoord , random);
		boolean defLost = BlockCWTFCFruitTreeBranch.shouldDefinitelyLoseLeaf(world, xCoord, yCoord, zCoord);
		for (int i = -(radius+1); i <= (radius+1); i++)
		{
			for (int j = 0; j <= (radius+1); j++)
			{
				for (int k = -(radius+1); k <= (radius+1); k++)
				{
					if (i * i + j * j + k * k <= radius * radius
						&& world.blockExists(xCoord + i, yCoord + j, zCoord + k)
						&& world.getBlock(xCoord + i, yCoord + j, zCoord + k).isReplaceable(world, xCoord + i, yCoord + j, zCoord + k)
						&&  !defLost
						&& (!lost || random.nextInt(4)==0))
					{
						world.setBlock(xCoord + i, yCoord + j, zCoord + k, this.leafBlock, treeId, 2);
						
						TECWTFCFruitLeaves te = (TECWTFCFruitLeaves) world.getTileEntity(xCoord + i, yCoord + j, zCoord + k);
						if(te != null)
						{
							te.fruitType = fruitId;
						}
						
						if (!fromSapling && !conversionMatrix[16+(xCoord + i)-tempSourceX][16+(zCoord+k)-tempSourceZ])
						{
							convertGrassToDirt(world, xCoord + i, yCoord + j, zCoord + k, height);
							conversionMatrix[16+(xCoord + i)-tempSourceX][16+(zCoord+k)-tempSourceZ] = true;
						}
					}
				}
			}
		}
		return true;
	}
}
