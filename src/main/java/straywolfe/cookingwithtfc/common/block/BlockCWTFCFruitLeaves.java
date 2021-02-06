package straywolfe.cookingwithtfc.common.block;

import java.util.List;
import java.util.Random;

import com.dunk.tfc.Reference;
import com.dunk.tfc.TerraFirmaCraft;
import com.dunk.tfc.Blocks.BlockTerraContainer;
import com.dunk.tfc.Blocks.Flora.BlockBranch;
import com.dunk.tfc.Blocks.Flora.BlockBranch2;
import com.dunk.tfc.Blocks.Flora.BlockFruitLeaves;
import com.dunk.tfc.Blocks.Vanilla.BlockCustomLeaves;
import com.dunk.tfc.Blocks.Vanilla.BlockCustomLeaves2;
import com.dunk.tfc.Core.TFC_Climate;
import com.dunk.tfc.Core.TFC_Time;
import com.dunk.tfc.Food.FloraIndex;
import com.dunk.tfc.Food.FloraManager;
import com.dunk.tfc.Food.ItemFoodTFC;
import com.dunk.tfc.TileEntities.TEFruitLeaves;
import com.dunk.tfc.api.TFCBlocks;
import com.dunk.tfc.api.TFCItems;
import com.dunk.tfc.api.TFCOptions;
import com.dunk.tfc.api.Constant.Global;
import com.dunk.tfc.api.Util.Helper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.oredict.OreDictionary;
import straywolfe.cookingwithtfc.api.CWTFCBlocks;
import straywolfe.cookingwithtfc.common.lib.Constants;
import straywolfe.cookingwithtfc.common.lib.ModInfo;
import straywolfe.cookingwithtfc.common.tileentity.TECWTFCFruitLeaves;

public class BlockCWTFCFruitLeaves extends BlockCustomLeaves implements ITileEntityProvider
{
	public static String[] fruitNames = Constants.FRUITTREETYPES;
	public static IIcon[] iconsFruit = new IIcon[6];
	public static IIcon[] iconsFlowers = new IIcon[6];
	public static IIcon[] iconsFancyFlowers = new IIcon[6];
	
	public BlockCWTFCFruitLeaves()
	{
		super();
		woodNames = Constants.FRUITTREETYPES;
		icons = new IIcon[woodNames.length];
		iconsOpaque = new IIcon[woodNames.length];
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegisterer)
	{
		super.registerBlockIcons(iconRegisterer);
		for (int i = 0; i < Constants.FRUITTREETYPES.length; i++)
		{
			this.icons[i] = iconRegisterer
					.registerIcon(ModInfo.ModID + ":" + "Wood/Fruit Trees/" + this.woodNames[i] + " Leaves");
			this.iconsOpaque[i] = iconRegisterer
					.registerIcon(ModInfo.ModID + ":" + "Wood/Fruit Trees/" + this.woodNames[i] + " Leaves Opaque");
			iconsFruit[i] = iconRegisterer
					.registerIcon(ModInfo.ModID + ":" + "Wood/Fruit Trees/" + fruitNames[i] + " Fruit");
			iconsFlowers[i] = iconRegisterer
					.registerIcon(ModInfo.ModID + ":" + "Wood/Fruit Trees/" + fruitNames[i] + " Flowers");
			iconsFancyFlowers[i] = iconRegisterer
					.registerIcon(ModInfo.ModID + ":" + "Wood/Fruit Trees/" + fruitNames[i] + " Flowers Fancy");			
		}
	}

	@Override
	public void onNeighborBlockChange(World world, int xOrig, int yOrig, int zOrig, Block b)
	{
		if (!world.isRemote)
		{
			int var6 = world.getBlockMetadata(xOrig, yOrig, zOrig);
	
			byte searchRadius = 4;
			int maxDist = searchRadius + 1;
			byte searchDistance = 11;
			int center = searchDistance / 2;
			this.adjacentTreeBlocks = new int[searchDistance][searchDistance][searchDistance];
	
			if (world.checkChunksExist(xOrig - maxDist, yOrig - maxDist, zOrig - maxDist, xOrig + maxDist,
					yOrig + maxDist, zOrig + maxDist))
			{
				for (int xd = -searchRadius; xd <= searchRadius; ++xd)
				{
					int searchY = searchRadius - Math.abs(xd);
					for (int yd = -searchY; yd <= searchY; ++yd)
					{
						int searchZ = searchY - Math.abs(yd);
						for (int zd = -searchZ; zd <= searchZ; ++zd)
						{
							Block block = world.getBlock(xOrig + xd, yOrig + yd, zOrig + zd);
	
							if (((block == CWTFCBlocks.fruitTreeWood && this instanceof BlockCWTFCFruitLeaves) || (((block instanceof BlockCWTFCFruitTreeBranch) == (this instanceof BlockCWTFCFruitLeaves)))) && var6 == world.getBlockMetadata(xOrig + xd, yOrig + yd,
											zOrig + zd))
								this.adjacentTreeBlocks[xd + center][yd + center][zd + center] = 0;
							else if (block == this && var6 == world.getBlockMetadata(xOrig + xd, yOrig + yd,
									zOrig + zd))
								this.adjacentTreeBlocks[xd + center][yd + center][zd + center] = -2;
							else
								this.adjacentTreeBlocks[xd + center][yd + center][zd + center] = -1;
						}
					}
				}
	
				for (int pass = 1; pass <= 4; ++pass)
				{
					for (int xd = -searchRadius; xd <= searchRadius; ++xd)
					{
						int searchY = searchRadius - Math.abs(xd);
						for (int yd = -searchY; yd <= searchY; ++yd)
						{
							int searchZ = searchY - Math.abs(yd);
							for (int zd = -searchZ; zd <= searchZ; ++zd)
							{
								if (this.adjacentTreeBlocks[xd + center][yd + center][zd + center] == pass - 1)
								{
									if (this.adjacentTreeBlocks[xd + center - 1][yd + center][zd + center] == -2)
										this.adjacentTreeBlocks[xd + center - 1][yd + center][zd + center] = pass;
	
									if (this.adjacentTreeBlocks[xd + center + 1][yd + center][zd + center] == -2)
										this.adjacentTreeBlocks[xd + center + 1][yd + center][zd + center] = pass;
	
									if (this.adjacentTreeBlocks[xd + center][yd + center - 1][zd + center] == -2)
										this.adjacentTreeBlocks[xd + center][yd + center - 1][zd + center] = pass;
	
									if (this.adjacentTreeBlocks[xd + center][yd + center + 1][zd + center] == -2)
										this.adjacentTreeBlocks[xd + center][yd + center + 1][zd + center] = pass;
	
									if (this.adjacentTreeBlocks[xd + center][yd + center][zd + center - 1] == -2)
										this.adjacentTreeBlocks[xd + center][yd + center][zd + center - 1] = pass;
	
									if (this.adjacentTreeBlocks[xd + center][yd + center][zd + center + 1] == -2)
										this.adjacentTreeBlocks[xd + center][yd + center][zd + center + 1] = pass;
								}
							}
						}
					}
				}
			}
	
			int res = this.adjacentTreeBlocks[center][center][center];
	
			if (res < 0)
			{
				if (world.getChunkFromBlockCoords(xOrig, zOrig) != null)
					this.destroyLeaves(world, xOrig, yOrig, zOrig);
			}
		}
		lifeCycle(world, xOrig, yOrig, zOrig);
	}

	@Override
	public Item getItemDropped(int i, Random rand, int j)
	{
		return null;
	}

	@Override
	protected void dropSapling(World world, int x, int y, int z, int meta)
	{
	}

	/* Left-Click Harvest Fruit */
	@Override
	public void onBlockClicked(World world, int x, int y, int z, EntityPlayer entityplayer)
	{
		int meta = world.getBlockMetadata(x, y, z);
		if (!world.isRemote)
		{
			TECWTFCFruitLeaves te = (TECWTFCFruitLeaves) world.getTileEntity(x, y, z);
			if (te != null) {
				int fruitIndex = te.fruitType;
				FloraManager manager = FloraManager.getInstance();
				FloraIndex fi = manager.findMatchingIndex(getType(this, fruitIndex));
		
				if (fi != null && (fi.inHarvest(TFC_Time.getSeasonAdjustedMonth(z)) || fi
						.inHarvest((TFC_Time.getSeasonAdjustedMonth(z) + 11) % 12)))
				{
		
					if (te != null && te.hasFruit)
					{
						te.hasFruit = false;
						te.dayHarvested = TFC_Time.getTotalDays();
						world.setBlockMetadataWithNotify(x, y, z, meta, 3);
						dropBlockAsItem(world, x, y, z, ItemFoodTFC.createTag(fi.getOutput(),
								Helper.roundNumber(4 + (world.rand.nextFloat() * 12), 10)));
					}
				}
			}
		}
	}

	/* Right-Click Harvest Fruit */
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityplayer, int side, float hitX,
			float hitY, float hitZ)
	{
		int meta = world.getBlockMetadata(x, y, z);
		if (!world.isRemote)
		{
			FloraManager manager = FloraManager.getInstance();
			TECWTFCFruitLeaves te = (TECWTFCFruitLeaves) world.getTileEntity(x, y, z);
			FloraIndex fi = manager.findMatchingIndex(getType(this, te.fruitType));
	
			if (fi != null && (fi.inHarvest(TFC_Time.getSeasonAdjustedMonth(z)) || fi
					.inHarvest((TFC_Time.getSeasonAdjustedMonth(z) + 11) % 12)))
			{
	
				if (te != null && te.hasFruit)
				{
					te.hasFruit = false;
					te.dayHarvested = TFC_Time.getTotalDays();
					world.setBlockMetadataWithNotify(x, y, z, meta, 3);
					world.markBlockForUpdate(x, y, z);
					dropBlockAsItem(world, x, y, z, ItemFoodTFC.createTag(fi.getOutput(),
							Helper.roundNumber(4 + (world.rand.nextFloat() * 12), 10)));
					return true;
				}
			}
		}
		return false;
	}

	public static String getType(Block block, int fruitType)
	{
		if (fruitType >= 0 && fruitType < Constants.FRUITTREETYPES.length)
		{
			return Constants.FRUITTREETYPES[fruitType];
		}
		return "";
	}

	private void lifeCycle(World world, int x, int y, int z)
	{
		if (!world.isRemote && world.getBlockMetadata(x, y, z) >= 0 && world.getBlockMetadata(x, y, z) < Constants.FRUITTREETYPES.length)
		{
			TECWTFCFruitLeaves te = (TECWTFCFruitLeaves) world.getTileEntity(x, y, z);
			Random rand = new Random();
			if (te != null) {
				int fruitType = te.fruitType;
		
				FloraManager manager = FloraManager.getInstance();
				FloraIndex fi = manager.findMatchingIndex(getType(this, fruitType));
		
				float temp = TFC_Climate.getHeightAdjustedTemp(world, x, y, z);
		
				if (te != null)
				{
					if (fi != null)
					{
						if (!fi.inHarvest(TFC_Time.getSeasonAdjustedMonth(z)))
						{
							if (te.hasFruit)
							{
								te.hasFruit = false;
								world.setBlockMetadataWithNotify(x, y, z, fruitType, 0x2);
							}
		
						}
						if (fi.inHarvest(TFC_Time.getSeasonAdjustedMonth(z)) && !te.hasFruit && TFC_Time
								.getMonthsSinceDay(te.dayHarvested) > 1)
						{
							te.hasFruit = true;
							te.dayFruited = TFC_Time.getTotalDays();
							world.setBlockMetadataWithNotify(x, y, z, fruitType, 0x2);
						}
					}
		
					if (rand.nextInt(100) > 50)
						world.markBlockForUpdate(x, y, z);
				}
			}
		}
	}

	@Override
	public int getRenderType()
	{
		return CWTFCBlocks.fruitTreeLeavesRenderID;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta)
	{
		return new TECWTFCFruitLeaves();
	}

	public boolean canBlockStay(World world, int x, int y, int z)
	{
		return !BlockCWTFCFruitTreeBranch.shouldDefinitelyLoseLeaf(world, x, y, z);
	}

	private void destroyLeaves(World world, int x, int y, int z)
	{
		world.setBlockToAir(x, y, z);
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{
		if (meta > woodNames.length - 1)
			meta = 0;
		if (TerraFirmaCraft.proxy.getGraphicsLevel())
			return this.icons[meta];
		else
			return this.iconsOpaque[meta];
	}

	public static boolean canStay(World world, int x, int y, int z)
	{
		// Only leaf blocks that are within one block and on the same level or 1
		// above a branch or the top of the trunk
		for (int i = 1; i >= -1; i--)
		{
			for (int j = 0; j >= -1; j--)
			{
				for (int k = 1; k >= -1; k--)
				{
					if (world.getBlock(i + x, j + y, k + z) == CWTFCBlocks.fruitTreeWood && world.getBlock(i + x,
							j + y + 1, k + z) != CWTFCBlocks.fruitTreeWood)
						return true;
				}
			}
		}

		return false;
	}
}
