package straywolfe.cookingwithtfc.common.block;

import java.util.List;
import java.util.Random;

import com.dunk.tfc.Reference;
import com.dunk.tfc.Blocks.BlockTerra;
import com.dunk.tfc.Blocks.Flora.BlockBranch;
import com.dunk.tfc.Blocks.Flora.BlockBranch2;
import com.dunk.tfc.Blocks.Flora.BlockLogNatural;
import com.dunk.tfc.Core.TFC_Climate;
import com.dunk.tfc.Core.TFC_Core;
import com.dunk.tfc.Core.TFC_Sounds;
import com.dunk.tfc.Core.TFC_Time;
import com.dunk.tfc.Food.FloraIndex;
import com.dunk.tfc.Food.FloraManager;
import com.dunk.tfc.Food.ItemFoodTFC;
import com.dunk.tfc.TileEntities.TEFruitLeaves;
import com.dunk.tfc.TileEntities.TEFruitTreeWood;
import com.dunk.tfc.api.TFCBlocks;
import com.dunk.tfc.api.TFCItems;
import com.dunk.tfc.api.TFCOptions;
import com.dunk.tfc.api.Constant.Global;
import com.dunk.tfc.api.Enums.EnumTree;
import com.dunk.tfc.api.Util.Helper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import straywolfe.cookingwithtfc.api.CWTFCBlocks;
import straywolfe.cookingwithtfc.api.CWTFCItems;
import straywolfe.cookingwithtfc.common.core.CWTFC_Core;
import straywolfe.cookingwithtfc.common.lib.Constants;
import straywolfe.cookingwithtfc.common.lib.ModInfo;

public class BlockCWTFCBranch extends BlockBranch
{
	protected String[] woodNames;
	protected int searchDist = 10;
	protected static int damage;
	protected static int logs;
	protected boolean isStone;
	public IIcon[] sideIcons;
	public IIcon[] innerIcons;
	public IIcon[] rotatedSideIcons;

	protected boolean isTwo = false;

	private int sourceX = 0;
	private int sourceZ = 0;
	private int sourceY = 0;

	private boolean end = false;

	public BlockCWTFCBranch()
	{
		super();
		this.setTickRandomly(true);
		this.woodNames = Constants.WOODTYPES;
		this.sideIcons = new IIcon[woodNames.length];
		this.innerIcons = new IIcon[woodNames.length];
		this.rotatedSideIcons = new IIcon[woodNames.length];
	}

	public BlockCWTFCBranch setEnd(boolean e)
	{
		end = e;
		return this;
	}

	public boolean isEnd()
	{
		return end;
	}

	/**
	 * Given the meta and the blockNum, determine if this type of tree
	 * grows/loses its leaves
	 * 
	 * @param meta
	 *            the metadata of the block
	 * @param blockNum
	 *            the number of the block, ex branch = 0, branch2 = 1, etc.
	 * @return
	 */
	public static boolean canLoseLeaves(int meta)
	{
		return true;
	}

	private void dropLeaves(World world, int x, int y, int z, int height)
	{
		if (!world.isRemote)
		{
			Block b;
			Block bUp;
			int meta;

			int myMeta = world.getBlockMetadata(x, y, z);
			for (int i = 0; i > -(height + 7) && i + y > 0; i--)
			{
				b = world.getBlock(x, i + y, z);
				bUp = world.getBlock(x, i + y + 1, z);
				if (TFC_Core.isGrass(b))
				{
					meta = world.getBlockMetadata(x, y + i, z);
					world.setBlock(x, y + i, z, TFC_Core.getTypeForDirtFromGrass(b), meta, 2);
					if (bUp.isReplaceable(world, x, y + i + 1, z))
					{
						world.setBlock(x, y + i + 1, z, TFCBlocks.leafLitter, 0, 2);
					}
					return;
				}
				else if (b.isBlockSolid(world, x, y + i, z, 1) && !(bUp instanceof BlockCWTFCLeaves))
				{
					if (bUp.isReplaceable(world, x, y + i + 1, z))
					{
						world.setBlock(x, y + i + 1, z, TFCBlocks.leafLitter, 0, 2);
					}
					return;
				}
			}
		}
	}

	/**
	 * Returns whether this branch renders as a small conifer, ie it renders
	 * small leaf blocks on its ends
	 * 
	 * @param meta
	 * @param blockNum
	 *            the number of the block. branch = 0, branch2 = 1, etc.
	 * @return
	 */
	public static boolean renderSmallConiferBranch(int meta, int blockNum)
	{
		return false;
	}

	public static boolean canLoseLeaves(World world, int x, int y, int z)
	{
		Block b = world.getBlock(x, y, z);
		int meta = world.getBlockMetadata(x, y, z);
		if (b instanceof BlockCWTFCBranch)
		{
			return canLoseLeaves(meta);

		}
		return true;
	}

	// If this leaf is adjacent to an existing leaf block of the same type or a
	// branch of the right type, ie right metadata, 1 or 2 and isEnd
	protected boolean validLeafLocation(World world, int x, int y, int z, int myMeta)
	{
		if (world.isRemote)
		{
			return false;
		}
		Block b = world.getBlock(x - 1, y, z);
		int blockMeta = world.getBlockMetadata(x - 1, y, z);
		boolean b2 = false;

		if (((b == CWTFCBlocks.treeLeaves) || (b instanceof BlockCWTFCBranch) && ((BlockCWTFCBranch) b).isEnd()) && blockMeta == myMeta)
		{
			return true;
		}
		b = world.getBlock(x + 1, y, z);
		blockMeta = world.getBlockMetadata(x + 1, y, z);
		if (((b == CWTFCBlocks.treeLeaves) || (b instanceof BlockCWTFCBranch) && ((BlockCWTFCBranch) b).isEnd()) && blockMeta == myMeta)
		{
			return true;
		}
		b = world.getBlock(x, y + 1, z);
		blockMeta = world.getBlockMetadata(x, y + 1, z);
		if (((b == CWTFCBlocks.treeLeaves) || (b instanceof BlockCWTFCBranch) && ((BlockCWTFCBranch) b).isEnd()) && blockMeta == myMeta)
		{
			return true;
		}
		b = world.getBlock(x, y - 1, z);
		blockMeta = world.getBlockMetadata(x, y - 1, z);
		if (((b == CWTFCBlocks.treeLeaves) || (b instanceof BlockCWTFCBranch) && ((BlockCWTFCBranch) b).isEnd()) && blockMeta == myMeta)
		{
			return true;
		}
		b = world.getBlock(x, y, z - 1);
		blockMeta = world.getBlockMetadata(x, y, z - 1);
		if (((b == CWTFCBlocks.treeLeaves) || (b instanceof BlockCWTFCBranch) && ((BlockCWTFCBranch) b).isEnd()) && blockMeta == myMeta)
		{
			return true;
		}
		b = world.getBlock(x, y, z + 1);
		blockMeta = world.getBlockMetadata(x, y, z + 1);
		if (((b == CWTFCBlocks.treeLeaves) || (b instanceof BlockCWTFCBranch) && ((BlockCWTFCBranch) b).isEnd()) && blockMeta == myMeta)
		{
			return true;
		}
		return false;
	}

	private void handleLeafLossAndGrowth(World world, int x, int y, int z, Random rand, int dayDelta)
	{
		int randomChance = 100;
		if (isEnd() && rand.nextInt(randomChance) == 0 && canLoseLeaves(world, x, y, z) && !world.isRemote)
		{
			// Temporarily stop some trees from growing leaves back
			int meta = world.getBlockMetadata(x, y, z);
			int region = TFC_Climate.getRegionLayer(world, x, Global.SEALEVEL, z);
			// Regrow leaves in the spring
			int season = TFC_Time.getSeasonAdjustedMonth(z);
			float lostDaysMultiplier = 0;
			float temp;
			int days = TFC_Time.getTotalDays();
			if (dayDelta > 0 && season >= TFC_Time.OCTOBER)
			{
				// This means we've skipped some time and it's currently time to
				// lose or gain leaves
				// we want to calculate how many losing days it's been since
				// last
				// load.
				int daySkip = TFC_Time.daysInMonth / 4;
				for (int i = -dayDelta; i <= 0; i += daySkip)
				{
					temp = TFC_Climate.getHeightAdjustedBioTemp(world, days + i, x, y, z);
					if (temp <= 10 && TFC_Time.getSeasonFromDayOfYear(days + i, z) >= TFC_Time.OCTOBER)
					{
						lostDaysMultiplier++;
					}
				}
			}
			else if (dayDelta > 0 && season < TFC_Time.OCTOBER)
			{
				int daySkip = TFC_Time.daysInMonth / 4;
				for (int i = -dayDelta; i <= 0; i += daySkip)
				{
					temp = TFC_Climate.getHeightAdjustedBioTemp(world, days + i, x, y, z);
					if (temp > 10 && TFC_Time.getSeasonFromDayOfYear(days + i, z) <= TFC_Time.AUGUST)
					{
						lostDaysMultiplier++;
					}
				}
			}
			temp = TFC_Climate.getHeightAdjustedTemp(world, x, y, z);
			int numToLoseOrGain = (int) (2 + (lostDaysMultiplier * 8));
			int numTries = 0;
			int oldNumToLoseOrGain = numToLoseOrGain;
			while (numToLoseOrGain > 0 && numTries < 10)
			{
				if (season <= TFC_Time.AUGUST && temp > 10)
				{
					if (numToLoseOrGain > 0)
					{
						int num = Math.min(numTries / 2, 3);
						int i = rand.nextInt(3 + num * 2) - (1 + num);
						int j = rand.nextInt(((2 * (num / 2))) + 1);
						int k = rand.nextInt(3 + num * 2) - (1 + num);
						if (world.isAirBlock(x + i, y + j, z + k) && validLeafLocation(world, x + i, y + j, z + k, meta))
						{
							world.setBlock(x + i, y + j, z + k, CWTFCBlocks.treeLeaves, meta, 2);
							numToLoseOrGain--;
						}
					}
				}
				else if (season >= TFC_Time.OCTOBER && shouldLoseLeaf(world, x, y, z, rand, season, temp, meta))
				{
					int i = rand.nextInt(9) - 4;
					int j = rand.nextInt(5);
					int k = rand.nextInt(9) - 4;

					int meta2 = world.getBlockMetadata(x + i, y + j, z + k);
					Block b = world.getBlock(x + i, y + j, z + k);
					if (b instanceof BlockCWTFCLeaves && BlockCWTFCBranch.canLoseLeaves(meta2) && rand.nextBoolean())
					{
						if (rand.nextInt(4) != 0)
						{
							dropLeaves(world, x + i, y + j, z + k, 24);
						}
						world.setBlockToAir(x + i, y + j, z + k);
						numToLoseOrGain--;
					}
				}
				if (oldNumToLoseOrGain == numToLoseOrGain)
				{
					numTries++;
				}
				oldNumToLoseOrGain = numToLoseOrGain;
			}
		}
	}

	@Override
	public void onBlockClicked(World world, int x, int y, int z, EntityPlayer entityplayer)
	{
		int meta = world.getBlockMetadata(x, y, z);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityplayer, int side, float hitX,
			float hitY, float hitZ)
	{
		int meta = world.getBlockMetadata(x, y, z);
		world.markBlockForUpdate(x, y, z);
		return false;
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand)
	{
		if (!world.isRemote)
		{
			if (world.getChunkFromBlockCoords(x, z).lastSaveTime > 0 && world.isAirBlock(x + this.sourceX,
					y + this.sourceY, z + this.sourceZ))
			{
				world.setBlockToAir(x, y, z);
			}
			if (isEnd())
			{
				handleLeafLossAndGrowth(world, x, y, z, rand, 0);
			}

		}
	}

	@Override
	public int tickRate(World world)
	{
		return 50;
	}

	public static boolean shouldLoseLeaf(World world, int x, int y, int z, Random rand, int season, float temp, int meta)
	{
		if (season >= TFC_Time.OCTOBER && temp <= 10 && BlockCWTFCBranch.canLoseLeaves(meta) && rand
				.nextBoolean())
		{
			if (rand.nextInt(11) > temp)
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * this method allows us to update branches to grow/lose leaves based on how
	 * long it's been since the chunk loaded.
	 * 
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 * @param monthsSinceLastUpdate
	 */
	public void updateBranchTime(World world, int x, int y, int z, int daysSinceLastVisit)
	{
		if (canLoseLeaves(world, x, y, z))
		{
			// If we can lose or gain leaves
			int currentMonth = TFC_Time.currentMonth;
			int lastUpdateMonth = ((currentMonth + 12) - ((daysSinceLastVisit / TFC_Time.daysInMonth) % 12)) % 12;
			// This means we should be losing leaves
			handleLeafLossAndGrowth(world, x, y, z, new Random(), daysSinceLastVisit);
		}
	}

	public static boolean shouldDefinitelyLoseLeaf(World world, int x, int y, int z, int season, float temp, int meta)
	{
		if (season >= TFC_Time.OCTOBER && temp < 0 && BlockCWTFCBranch.canLoseLeaves(meta))
		{
			return true;
		}
		return false;
	}

	public static boolean shouldDefinitelyLoseLeaf(World world, int x, int y, int z)
	{
		int season = TFC_Time.getSeasonAdjustedMonth(z);
		float temp = TFC_Climate.getHeightAdjustedBioTemp(world, TFC_Time.getTotalDays(), x, y, z);
		int meta = world.getBlockMetadata(x, y, z);
		return shouldDefinitelyLoseLeaf(world, x, y, z, season, temp, meta);
	}

	public static boolean shouldLoseLeaf(World world, int x, int y, int z, Random rand)
	{
		int season = TFC_Time.getSeasonAdjustedMonth(z);
		float temp = TFC_Climate.getHeightAdjustedBioTemp(world, TFC_Time.getTotalDays(), x, y, z);
		int meta = world.getBlockMetadata(x, y, z);
		return shouldLoseLeaf(world, x, y, z, rand, season, temp, meta);
	}

	public int getSourceY()
	{
		return sourceY;
	}

	public int getSourceX()
	{
		return sourceX;
	}

	public int getSourceZ()
	{
		return sourceZ;
	}

	public Block getSourceBlock(IBlockAccess world, int x, int y, int z, int depth)
	{
		if (depth > 1)
		{
			Block b = world.getBlock(x + sourceX, y + sourceY, z + sourceZ);

			if (b instanceof BlockCWTFCBranch)
			{
				return ((BlockCWTFCBranch) b).getSourceBlock(world, x + sourceX, y + sourceY, z + sourceZ, depth - 1);
			}
		}
		return world.getBlock(x + sourceX, y + sourceY, z + sourceZ);
	}

	public BlockCWTFCBranch setSourceXYZ(int x, int y, int z)
	{
		sourceX = x;
		sourceY = y;
		sourceZ = z;
		return this;
	}

	public int getDistanceToTrunk(IBlockAccess world, int x, int y, int z, int total)
	{
		Block b = getSourceBlock(world, x, y, z, 1);
		if (total > 100)
		{
			// Obviously we have something circular;
			if (world instanceof World && !((World) world).isRemote)
			{
				((World) world).setBlockToAir(x, y, z);
			}
			return -1000;
		}
		if (b instanceof BlockCWTFCBranch)
		{
			int totalX = ((BlockCWTFCBranch) b).getSourceX() + getSourceX();
			int totalY = ((BlockCWTFCBranch) b).getSourceY() + getSourceY();
			int totalZ = ((BlockCWTFCBranch) b).getSourceZ() + getSourceZ();
			if (totalZ == 0 && totalY == 0 && totalX == 0)
			{
				return -1000;
			}
			if (b != this && !isEnd())
			{
				total += 6;
				return ((BlockCWTFCBranch) b).getDistanceToTrunk(world, x + sourceX, y + sourceY, z + sourceZ, total);
			}
			total += 1;
			return ((BlockCWTFCBranch) b).getDistanceToTrunk(world, x + sourceX, y + sourceY, z + sourceZ, total);
		}
		if ((b instanceof BlockCWTFCLog) || TFC_Core.isSoil(b) || TFC_Core.isSand(b))
		{
			return 1 + total;
		}
		if (world instanceof World && !((World) world).isRemote)
		{
			((World) world).setBlockToAir(x, y, z);
		}
		return -1000;
	}

	@Override
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z)
	{

		// if (this instanceof BlockBranch2 && world.getBlockMetadata(x, y, z)
		// == 10 && isEnd())
		// this.setBlockBounds(0.3f, 0.3F, 0.3f, 0.7f, 0.7F, 0.7f);
		this.setBlockBounds(0f, 0F, 0f, 1f, 1F, 1f);
		return super.getSelectedBoundingBoxFromPool(world, x, y, z);
	}

	@Override
	public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB aaBB, List list, Entity entity)
	{
		// if (this.isEnd())
		// {
		this.setBlockBounds(0.3f, 0.3F, 0.3f, 0.7f, 0.7F, 0.7f);
		super.addCollisionBoxesToList(world, x, y, z, aaBB, list, entity);

		// This should allow us to add our collisions properly
		this.setBlockBounds(0.3f + (getSourceX() * 0.25f), 0.3F + (getSourceY() * 0.25f), 0.3f + (getSourceZ() * 0.25f),
				0.7f + (getSourceX() * 0.25f), 0.7F + (getSourceY() * 0.25f), 0.7f + (getSourceZ() * 0.25f));
		super.addCollisionBoxesToList(world, x, y, z, aaBB, list, entity);

		this.setBlockBounds(0.3f + (getSourceX() * 0.5f), 0.3F + (getSourceY() * 0.5f), 0.3f + (getSourceZ() * 0.5f),
				0.7f + (getSourceX() * 0.5f), 0.7F + (getSourceY() * 0.5f), 0.7f + (getSourceZ() * 0.5f));
		super.addCollisionBoxesToList(world, x, y, z, aaBB, list, entity);
		/*
		 * } / else { this.setBlockBounds(0.3f, 0.3F, 0.3f, 0.7f, 0.7F, 0.7f);
		 * super.addCollisionBoxesToList(world, x, y, z, aaBB, list, entity);
		 * 
		 * // This should allow us to add our collisions properly
		 * this.setBlockBounds(0.3f + (getSourceX() * 0.75f), 0.3F +
		 * (getSourceY() * 0.75f), 0.3f + (getSourceZ() * 0.75f), 0.7f +
		 * (getSourceX() * 0.75f), 0.7F + (getSourceY() * 0.75f), 0.7f +
		 * (getSourceZ() * 0.75f)); super.addCollisionBoxesToList(world, x, y,
		 * z, aaBB, list, entity); }
		 */
		// this.setBlockBounds(0, 0, 0, 1, 1, 1);
	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess p_149646_1_, int p_149646_2_, int p_149646_3_, int p_149646_4_,
			int p_149646_5_)
	{
		return true;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	/**
	 * The type of render function that is called for this block
	 */
	@Override
	public int getRenderType()
	{
		return CWTFCBlocks.branchRenderID;
	}

	protected boolean noLogsNearby(World world, int x, int y, int z)
	{
		return world.blockExists(x, y, z) && (world.getBlock(x, y, z) != this && world.getBlock(x, y, z) != CWTFCBlocks.treeLog);
	}

	@SideOnly(Side.CLIENT)
	@Override
	/**
	 * returns a list of blocks with the same ID, but different meta (eg: wood
	 * returns 4 blocks)
	 */
	public void getSubBlocks(Item item, CreativeTabs tabs, List list)
	{
		for (int i = 0; i < woodNames.length; i++)
			list.add(new ItemStack(this, 1, i));
	}

	@Override
	public float getBlockHardness(World world, int x, int y, int z)
	{
		return this.blockHardness;
	}

	private boolean checkOut(World world, int x, int y, int z, int meta)
	{
		return world.getBlock(x, y, z) == this && world.getBlockMetadata(x, y, z) == meta;
	}

	@Override
	public int damageDropped(int dmg)
	{
		return dmg;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		if (meta < sideIcons.length) {
			if (isEnd())
			{
				return sideIcons[meta];
			}
			if (side == 0 || side == 1)
				return innerIcons[meta];
			return sideIcons[meta];
		} else {
			return sideIcons[0];
		}
	}

	@Override
	public void registerBlockIcons(IIconRegister reg)
	{
		for (int i = 0; i < woodNames.length; i++)
		{
			sideIcons[i] = reg.registerIcon(ModInfo.ModID + ":" + "Wood/" + woodNames[i] + " Log");
			innerIcons[i] = reg.registerIcon(ModInfo.ModID + ":" + "Wood/" + woodNames[i] + " Log Top");
			rotatedSideIcons[i] = reg.registerIcon(ModInfo.ModID + ":" + "Wood/" + woodNames[i] + " Log Side");
		}
	}

	@Override
	public void harvestBlock(World world, EntityPlayer entityplayer, int x, int y, int z, int meta)
	{
		if (world.isRemote)
		{
			return;
		}
		// we need to make sure the player has the correct tool out
		boolean isAxe = false;
		boolean isHammer = false;
		ItemStack equip = entityplayer.getCurrentEquippedItem();
		if (!world.isRemote)
		{
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
					damage = 0;
					// processTree(world, x, y, z, meta, equip);
					scanLogsForHarvest(world, entityplayer, x, y, z, meta, (byte) 0, (byte) 0, (byte) 0);

					if (damage + equip.getItemDamage() > equip.getMaxDamage())
					{
						int ind = entityplayer.inventory.currentItem;
						entityplayer.inventory.setInventorySlotContents(ind, null);
						world.setBlock(x, y, z, this, meta, 0x2);
					}
					else
						equip.damageItem(damage, entityplayer);

					int smallStack = logs % 16;
					// dropBlockAsItem(world, x, y, z, new
					// ItemStack(TFCItems.logs, smallStack, damageDropped(meta)
					// * 2));
					logs -= smallStack;

					// In theory this section should never be triggered since
					// the full stacks are dropped higher up the tree, but
					// keeping it here just in case.
					while (logs > 0)
					{
						// dropBlockAsItem(world, x, y, z, new
						// ItemStack(TFCItems.logs, 16, damageDropped(meta)));
						logs -= 16;
					}

				}
				else if (isHammer)
				{
					// EntityItem item = new EntityItem(world, x + 0.5, y + 0.5,
					// z + 0.5,
					// new ItemStack(TFCItems.stick, 1 +
					// world.rand.nextInt(3)));
					// world.spawnEntityInWorld(item);
				}
			}
			else
				world.setBlock(x, y, z, this, meta, 0x2);
		}
	}

	@Override
	public void onBlockHarvested(World world, int x, int y, int z, int side, EntityPlayer entityplayer)
	{
		if (world.isRemote)
		{
			return;
		}
		int meta = world.getBlockMetadata(x, y, z);
		harvestBlock(world, entityplayer, x, y, z, meta);
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z)
	{
		return true;
	}

	@Override
	public boolean canBeReplacedByLeaves(IBlockAccess world, int x, int y, int z)
	{
		return false;
	}

	@Override
	public void onBlockDestroyedByExplosion(World world, int x, int y, int z, Explosion ex)
	{
		processTree(world, x, y, z, world.getBlockMetadata(x, y, z), null);
	}

	/*
	 * private void processTree(World world, int x, int y, int z, ItemStack is)
	 * { //TODO Rewrite the treecap algorithm using a list of coords instead of
	 * the ugly array. Shoudl also use a maxmium list size to prevent //any
	 * memory issues and should take shortcuts to find the top of the tree and
	 * search down }
	 */

	@Deprecated
	protected void processTree(World world, int x, int y, int z, int meta, ItemStack is)
	{
		boolean[][][] checkArray = new boolean[searchDist * 2 + 1][256][searchDist * 2 + 1];
		// scanLogs(world, x, y, z, meta, checkArray, (byte) 0, (byte) 0, (byte)
		// 0, is);
	}

	public boolean isLongDiagonal()
	{
		return this.sourceY != 0 && this.sourceX != 0 && this.sourceZ != 0;
	}

	public boolean isShortDiagonal()
	{
		return ((this.sourceY != 0 && this.sourceX != 0) || (this.sourceX != 0 && this.sourceZ != 0) || (this.sourceY != 0 && this.sourceZ != 0)) && !isLongDiagonal();
	}

	@Override
	public Item getItemDropped(int i, Random random, int j)
	{
		return null;// TFCItems.logs;
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
	{

		if (world.getChunkFromBlockCoords(x, z).lastSaveTime > 0 && getDistanceToTrunk(world, x, y, z, 0) < 0)
		{
			world.setBlockToAir(x, y, z);
			return;
		}
		/*
		 * boolean check = false; for (int h = -2; h <= 2; h++) { for (int g =
		 * -2; g <= 2; g++) { for (int f = -2; f <= 2; f++) { if
		 * (world.getBlock(x + h, y + g, z + f) == this &&
		 * world.getBlockMetadata(x + h, y + g, z + f) == meta) check = true; }
		 * } } if (!check) { world.setBlockToAir(x, y, z); //
		 * dropBlockAsItem(world, x, y, z, new ItemStack(TFCItems.logs, 1, //
		 * damageDropped(meta))); }
		 */
	}

	@Override
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side)
	{
		int meta = world.getBlockMetadata(x, y, z);
		return this.getIcon(side, meta);
	}

	public boolean scanLogsForHarvest(World world, EntityPlayer player, int i, int j, int k, int meta, byte x, byte y, byte z)
	{
		if (!world.isRemote)
		{
			// What we do here is look in all directions for a block that points
			// at
			// us and then that's how we find everything
			for (int xDir = -1; xDir <= 1; xDir++)
			{
				for (int yDir = -1; yDir <= 1; yDir++)
				{
					for (int zDir = -1; zDir <= 1; zDir++)
					{
						// If we're not looking at our self and we're not
						// looking at
						// our source
						if (!(xDir == 0 && yDir == 0 && zDir == 0) && !(xDir == sourceX && yDir == sourceY && zDir == sourceZ))
						{
							Block b = CWTFC_Core.getSourcedBranchForBranch(this, xDir, yDir, zDir);
							Block match = world.getBlock(i + xDir, j + yDir, k + zDir);
							Block b2 = CWTFC_Core.getSourcedTerminalBranchForBranch(this, xDir, yDir, zDir);
							if (b == match || b2 == match)
							{
								// This means we've found a block which is
								// pointing
								// at us, meaning a block that thinks it is
								// growing
								// out of the current block, which would be its
								// "source"
								if (b == match)
								{
									if (!((BlockCWTFCBranch) b).scanLogsForHarvest(world, player, i + xDir, j + yDir, k + zDir, meta, (byte) (x + (byte) xDir), (byte) (y + (byte) yDir),
									(byte) (z + (byte) zDir)))
									{
										return false;
									}
								}
								else
								{
									if (!((BlockCWTFCBranch) b2).scanLogsForHarvest(world, player, i + xDir, j + yDir, k + zDir, meta, (byte) (x + (byte) xDir),
									(byte) (y + (byte) yDir), (byte) (z + (byte) zDir)))
									{
										return false;
									}
								}
							}
						}
					}
				}
			}

			BlockCWTFCBranch b = null;
			if (world.getBlock(i, j, k) instanceof BlockCWTFCBranch)
			{
				b = (BlockCWTFCBranch) world.getBlock(i, j, k);
			}
			damage++;
			// This is how we damage the tool.
			if (b != null && b.isEnd())
			{

				world.setBlock(i, j, k, Blocks.air, 0, 0x2);
				int r = (i + j << 4 + k << 8);
				Random random = new Random(r);
				int n = random.nextInt(4);
				if (n == 0)
				{
					if (random.nextInt(3) == 0)
					{
						world.playSoundEffect(i, j, k, TFC_Sounds.BRANCHSNAP, 0.5f + (random.nextFloat() * 0.7f),
								0.6f + (random.nextFloat() * 0.3f));
					}
					else
					{
						world.playSoundEffect(i, j, k, TFC_Sounds.TWIGSNAP, 0.3f + (random.nextFloat() * 0.7f),
								0.4f + (random.nextFloat() * 0.3f));
					}
				}

				int randNum = 100;
				if ((meta == 8 || meta == 10 || meta == 11))
				{
					randNum = 30;
				}
				if (random.nextInt(randNum) == 0 && meta == 10)
				{
					dropBlockAsItem(world, i, j, k, new ItemStack(CWTFCBlocks.treeSapling, 1, meta));
				}
				else
				{
					if (random.nextInt(10) > 0)
					{
						dropBlockAsItem(world, i, j, k, new ItemStack(TFCItems.stick, /* random.nextInt(random.nextInt(2) + 1) */1, 0));
					}
					else
					{
						dropBlockAsItem(world, i, j, k, new ItemStack(TFCItems.pole, 1, 0));
					}
					notifyLeaves(world, i, j, k);
				}
			}
			else
			{
				world.setBlockToAir(i, j, k);

				dropBlockAsItem(world, i, j, k, new ItemStack(CWTFCItems.logs, 1, damageDropped(meta) * 2));

				notifyLeaves(world, i, j, k);
			}
			return true;
		}
		return false;
		/*
		 * if(y >= 0 && j + y < 256) { int offsetX = 0;int offsetY = 0;int
		 * offsetZ = 0; checkArray[x + searchDist][y][z + searchDist] = true;
		 * 
		 * for (offsetX = -3; offsetX <= 3; offsetX++) { for (offsetZ = -3;
		 * offsetZ <= 3; offsetZ++) { for (offsetY = 0; offsetY <= 2; offsetY++)
		 * { if(Math.abs(x + offsetX) <= searchDist && j + y + offsetY < 256 &&
		 * Math.abs(z + offsetZ) <= searchDist) { if(checkOut(world, i + x +
		 * offsetX, j + y + offsetY, k + z + offsetZ, meta) && !(offsetX == 0 &&
		 * offsetY == 0 && offsetZ == 0) && !checkArray[x + offsetX +
		 * searchDist][y + offsetY][z + offsetZ + searchDist]) scanLogs(world,i,
		 * j, k, meta, checkArray, (byte)(x + offsetX),(byte)(y +
		 * offsetY),(byte)(z + offsetZ), stack); } } } }
		 * 
		 * damage++; if(stack != null) { if(damage+stack.getItemDamage() <=
		 * stack.getMaxDamage()) { world.setBlock(i + x, j + y, k + z,
		 * Blocks.air, 0, 0x2); if (!isStone || world.rand.nextInt(10) != 0)
		 * logs++; if (logs >= 16) { dropBlockAsItem(world, i + x, j + y, k + z,
		 * new ItemStack(TFCItems.logs, 16, damageDropped(meta))); logs -= 16; }
		 * notifyLeaves(world, i + x, j + y, k + z); } } else {
		 * world.setBlockToAir(i, j, k); logs++; if (logs >= 16) {
		 * dropBlockAsItem(world, i, j, k, new ItemStack(TFCItems.logs, 16,
		 * damageDropped(meta))); logs -= 16; } notifyLeaves(world, i + x, j +
		 * y, k + z); } }
		 */
	}

	protected void notifyLeaves(World world, int x, int y, int z)
	{
		if (!world.isRemote)
		{
			/*
			 * for (int i = -1; i < 2; i++) { for (int j = -1; i < 2; j++) { for
			 * (int k = -1; i < 2; k++) { if (i != 0 || j != 0 || k != 0) {
			 * world.notifyBlockOfNeighborChange(x + i, y + j, z + k,
			 * Blocks.air); } } } }
			 */

			world.notifyBlockOfNeighborChange(x + 1, y, z, Blocks.air);
			world.notifyBlockOfNeighborChange(x - 1, y, z, Blocks.air);
			world.notifyBlockOfNeighborChange(x, y, z + 1, Blocks.air);
			world.notifyBlockOfNeighborChange(x, y, z - 1, Blocks.air);
			world.notifyBlockOfNeighborChange(x, y + 1, z, Blocks.air);
			world.notifyBlockOfNeighborChange(x, y - 1, z, Blocks.air);

		}
	}
}
