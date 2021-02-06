package straywolfe.cookingwithtfc.common.block;

import java.util.Random;

import com.dunk.tfc.Blocks.Flora.BlockLogNatural;
import com.dunk.tfc.Core.TFC_Core;
import com.dunk.tfc.api.TFCItems;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import straywolfe.cookingwithtfc.api.CWTFCItems;
import straywolfe.cookingwithtfc.common.core.CWTFC_Core;
import straywolfe.cookingwithtfc.common.lib.Constants;
import straywolfe.cookingwithtfc.common.lib.ModInfo;

public class BlockCWTFCFruitLog extends BlockLogNatural
{
	private static int damage;
	private static int logs;
	
	public BlockCWTFCFruitLog()
	{
		super();
		woodNames = Constants.FRUITTREETYPES;
		sideIcons = new IIcon[woodNames.length];
		innerIcons = new IIcon[woodNames.length];
		setHardness(50.0F);
		setStepSound(Block.soundTypeWood);
		setBlockName("Log");
	}
	
	@Override
	public void registerBlockIcons(IIconRegister reg)
	{
		for(int i = 0; i < woodNames.length; i++)
		{
			sideIcons[i] = reg.registerIcon(ModInfo.ModID + ":Wood/Fruit Trees/" + woodNames[i] + " Log");
			innerIcons[i] = reg.registerIcon(ModInfo.ModID + ":Wood/Fruit Trees/" + woodNames[i] + " Log Top");
		}
	}
	
	@Override
	public void harvestBlock(World world, EntityPlayer entityplayer, int x, int y, int z, int meta)
	{
		if(!world.isRemote)
		{
			boolean isAxe = false;
			boolean isHammer = false;
			boolean isStone = false;
			ItemStack equip = entityplayer.getCurrentEquippedItem();

			if (equip != null)
			{
				int[] equipIDs = OreDictionary.getOreIDs(equip);
				for (int id : equipIDs)
				{
					String name = OreDictionary.getOreName(id);
					if (name.startsWith("itemAxe"))
					{
						isAxe = true;
						if (name.startsWith("itemAxeStone"))
						{
							isStone = true;
							break;
						}
					}
					else if (name.startsWith("itemHammer"))
					{
						isHammer = true;
						break;
					}
				}
				
				if (isAxe)
				{
					int damage = -1;
					processTree(world, entityplayer, x, y, z, meta, equip);
					
					if (damage + equip.getItemDamage() > equip.getMaxDamage())
					{
						int ind = entityplayer.inventory.currentItem;
						entityplayer.inventory.setInventorySlotContents(ind, null);
						world.setBlock(x, y, z, this, meta, 0x2);
					}
					else
						equip.damageItem(damage, entityplayer);
					
					int smallStack = logs % 16;
					dropBlockAsItem(world, x, y, z, new ItemStack(TFCItems.logs, smallStack, Constants.FRUITWOODID));
					logs -= smallStack;
					
					while (logs > 0)
					{
						dropBlockAsItem(world, x, y, z, new ItemStack(TFCItems.logs, 16, Constants.FRUITWOODID));
						logs -= 16;
					}
				}
				else if (isHammer)
				{
					EntityItem item = new EntityItem(world, x + 0.5, y + 0.5, z + 0.5, new ItemStack(TFCItems.stick, 1 + world.rand.nextInt(3)));
					world.spawnEntityInWorld(item);
				}
			}
			else
				world.setBlock(x, y, z, this, meta, 0x2);
		}
	}

	@Deprecated
	protected void processTree(World world, EntityPlayer entityplayer, int x, int y, int z, int meta, ItemStack is)
	{
		boolean[][][] checkArray = new boolean[searchDist * 2 + 1][256][searchDist * 2 + 1];
		for (int i = -2; i < 3; i++)
		{
			for (int j = -2; j < 3; j++)
			{
				if (world.getBlock(x + i, y, z + j) instanceof BlockCWTFCFruitLog)
				{
					scanLogs(world, entityplayer, x + i, y, z + j, meta, checkArray, (byte) 0, (byte) 0, (byte) 0, is);
				}
			}
		}
	}
	
	@Override
	public Item getItemDropped(int i, Random random, int j)
	{
		return TFCItems.logs;
	}
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
	{
		int meta = world.getBlockMetadata(x, y, z);
		boolean check = false;
		for(int h = -2; h <= 2; h++)
		{
			for(int g = -2; g <= 2; g++)
			{
				for(int f = -2; f <= 2; f++)
				{
					if(world.getBlock(x + h, y + g, z + f) == this && world.getBlockMetadata(x + h, y + g, z + f) == meta)
						check = true;
				}
			}
		}
		
		if(!check)
		{
			world.setBlockToAir(x, y, z);
			dropBlockAsItem(world, x, y, z, new ItemStack(TFCItems.logs, 1, Constants.FRUITWOODID));
		}
	}
	
	protected void scanLogs(World world, EntityPlayer entityplayer, int i, int j, int k, int meta,
			boolean[][][] checkArray, byte x, byte y, byte z, ItemStack stack)
	{
		for (; j + y < 256 && world.getBlock(i, j + y, k) == this; y++)
		{
			if (y >= 0 && j + y < 256)
			{
				int offsetX = 0;
				int offsetY = 0;
				int offsetZ = 0;
				checkArray[x + searchDist][y][z + searchDist] = true;

				for (int xDir = -1; xDir <= 1; xDir++)
				{
					for (int yDir = -1; yDir <= 1; yDir++)
					{
						for (int zDir = -1; zDir <= 1; zDir++)
						{
							if (!(xDir == 0 && yDir == 0 && zDir == 0))
							{
								Block b = null;
								Block match = world.getBlock(i + xDir, y + j + yDir, k + zDir);
								Block b2 = null;
								if (match instanceof BlockCWTFCFruitTreeBranch)
								{
									b = CWTFC_Core.getSourcedFruitBranchForBranch(this, xDir, yDir, zDir);
									b2 = CWTFC_Core.getSourcedTerminalFruitBranchForBranch(this, xDir, yDir, zDir);
								}
								if (b == match || b2 == match)
								{
									if (b == match)
									{
										if (entityplayer != null)
										{
											((BlockCWTFCFruitTreeBranch) b).harvestBlock(world, entityplayer, i + xDir, y + j + yDir,
													k + zDir, meta);
										}
										/*else
										{
											((BlockCWTFCFruitTreeBranch) b).scanLogsForHarvest(world, i + xDir, y + j + yDir,
													k + zDir, meta, (byte) (x + (byte) xDir), (byte) (y + (byte) yDir),
													(byte) (z + (byte) zDir));
										}*/
									}
									else
									{
										if (entityplayer != null)
										{
											((BlockCWTFCFruitTreeBranch) b2).harvestBlock(world, entityplayer, i + xDir, y + j + yDir,
													k + zDir, meta);
										}
										/*else
										{
											((BlockCWTFCFruitTreeBranch) b2).scanLogsForHarvest(world, i + xDir, y + j + yDir,
													k + zDir, meta, (byte) (x + (byte) xDir), (byte) (y + (byte) yDir),
													(byte) (z + (byte) zDir));
										}*/
									}
								}
							}
						}
					}
				}
			
				damage++;
				if(stack != null)
				{
					if(damage+stack.getItemDamage() <= stack.getMaxDamage())
					{
						world.setBlock(i + x, j + y, k + z, Blocks.air, 0, 0x2);
						if (!isStone || world.rand.nextInt(10) != 0)
							logs++;
						if (logs >= 16)
						{
							dropBlockAsItem(world, i + x, j + y, k + z, new ItemStack(TFCItems.logs, 16, Constants.FRUITWOODID));
							logs -= 16;						
						}
						notifyLeaves(world, i + x, j + y, k + z);
					}
				}
				else
				{
					world.setBlockToAir(i, j, k);
					logs++;
					if (logs >= 16)
					{
						dropBlockAsItem(world, i, j, k, new ItemStack(TFCItems.logs, 16, Constants.FRUITWOODID));
						logs -= 16;						
					}
					notifyLeaves(world, i + x, j + y, k + z);
				}
			}
		}
	}
	
	protected void notifyLeaves(World world, int x, int y, int z)
	{
		world.notifyBlockOfNeighborChange(x + 1, y, z, Blocks.air);
		world.notifyBlockOfNeighborChange(x - 1, y, z, Blocks.air);
		world.notifyBlockOfNeighborChange(x, y, z + 1, Blocks.air);
		world.notifyBlockOfNeighborChange(x, y, z - 1, Blocks.air);
		world.notifyBlockOfNeighborChange(x, y + 1, z, Blocks.air);
		world.notifyBlockOfNeighborChange(x, y - 1, z, Blocks.air);
	}
}
