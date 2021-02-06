package straywolfe.cookingwithtfc.common.block;

import java.util.List;
import java.util.Random;

import com.dunk.tfc.Reference;
import com.dunk.tfc.TerraFirmaCraft;
import com.dunk.tfc.Blocks.Flora.BlockBranch;
import com.dunk.tfc.Blocks.Flora.BlockBranch2;
import com.dunk.tfc.Blocks.Vanilla.BlockCustomLeaves;
import com.dunk.tfc.Food.CropIndex;
import com.dunk.tfc.Food.ItemFoodTFC;
import com.dunk.tfc.api.TFCBlocks;
import com.dunk.tfc.api.TFCItems;
import com.dunk.tfc.api.TFCOptions;
import com.dunk.tfc.api.Constant.Global;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.oredict.OreDictionary;
import straywolfe.cookingwithtfc.api.CWTFCBlocks;
import straywolfe.cookingwithtfc.api.CWTFCItems;
import straywolfe.cookingwithtfc.common.CookingWithTFC;
import straywolfe.cookingwithtfc.common.lib.Constants;
import straywolfe.cookingwithtfc.common.lib.ModInfo;

public class BlockCWTFCLeaves extends BlockLeaves implements IShearable
{
	protected int adjacentTreeBlocks[][][];
	protected String[] woodNames;
	protected IIcon[] icons;
	protected IIcon[] iconsOpaque;

	public BlockCWTFCLeaves()
	{
		super();
		this.canBlockGrass = true;
		this.woodNames = Constants.WOODTYPES;
		this.icons = new IIcon[woodNames.length];
		this.iconsOpaque = new IIcon[woodNames.length];
	}

	@Override
	public int tickRate(World world)
	{
		return 200;
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z)
	{
		return !BlockCWTFCBranch.shouldDefinitelyLoseLeaf(world, x, y, z) && super.canBlockStay(world, x, y, z);
	}

	@Override
	public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side)
	{
		if (side == ForgeDirection.DOWN)
		{
			return true;
		}
		return false;
	}

	@Override
	public void getSubBlocks(Item item, CreativeTabs tabs, List list)
	{
		// Leaves are not added to the creative tab
	}

	@Override
	public boolean getBlocksMovement(IBlockAccess bAccess, int x, int y, int z)
	{
		return true;
	}

	@Override
	public int colorMultiplier(IBlockAccess bAccess, int x, int y, int z)
	{
		return TerraFirmaCraft.proxy.foliageColorMultiplier(bAccess, x, y, z);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		return null;
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		entity.motionX *= 0.1D;
		entity.motionZ *= 0.1D;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side)
	{
		Block block = world.getBlock(x, y, z);
		/*
		 * if(!Minecraft.isFancyGraphicsEnabled() && block == this) return
		 * false;
		 */
		if (side == 0 && this.minY > 0.0D)
			return true;
		else if (side == 1 && this.maxY < 1.0D)
			return true;
		else if (side == 2 && this.minZ > 0.0D)
			return true;
		else if (side == 3 && this.maxZ < 1.0D)
			return true;
		else if (side == 4 && this.minX > 0.0D)
			return true;
		else if (side == 5 && this.maxX < 1.0D)
			return true;
		else
			return !block.isOpaqueCube();
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand)
	{
		if (rand.nextInt(10) == 0 && !world.isRemote)
		{
			onNeighborBlockChange(world, x, y, z, null);
		}
	}

	@Override
	public void beginLeavesDecay(World world, int x, int y, int z)
	{
		// We don't do vanilla leaves decay
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

							if (((block == CWTFCBlocks.treeLog || (block instanceof BlockCWTFCBranch))) && var6 == world.getBlockMetadata(xOrig + xd, yOrig + yd,
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
	}

	private void destroyLeaves(World world, int x, int y, int z)
	{
		world.setBlockToAir(x, y, z);
	}

	private void removeLeaves(World world, int x, int y, int z)
	{
		dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
		if (world.rand.nextInt(100) < 30)
			dropBlockAsItem(world, x, y, z, new ItemStack(TFCItems.stick, 1));
		world.setBlockToAir(x, y, z);
	}

	@Override
	public int quantityDropped(Random rand)
	{
		return rand.nextInt(20) != 0 ? 0 : 1;
	}

	@Override
	public Item getItemDropped(int i, Random rand, int j)
	{
		return Item.getItemFromBlock(CWTFCBlocks.treeSapling);
	}

	@Override
	public void dropBlockAsItemWithChance(World world, int x, int y, int z, int meta, float f, int i1)
	{
		// Do Nothing
	}

	@Override
	public void harvestBlock(World world, EntityPlayer entityplayer, int i, int j, int k, int meta)
	{
		if (!world.isRemote)
		{
			ItemStack itemstack = entityplayer.inventory.getCurrentItem();
			int[] equipIDs = OreDictionary.getOreIDs(itemstack);
			for (int id : equipIDs)
			{
				String name = OreDictionary.getOreName(id);
				if (name.startsWith("itemScythe"))
				{
					for (int x = -1; x < 2; x++)
					{
						for (int z = -1; z < 2; z++)
						{
							for (int y = -1; y < 2; y++)
							{
								if (world.getBlock(i + x, j + y, k + z)
										.getMaterial() == Material.leaves && entityplayer.inventory
												.getStackInSlot(entityplayer.inventory.currentItem) != null)
								{
									entityplayer.addStat(StatList.mineBlockStatArray[getIdFromBlock(this)], 1);
									entityplayer.addExhaustion(0.045F);
									if (world.rand.nextInt(100) < 11)
										dropBlockAsItem(world, i + x, j + y, k + z, new ItemStack(TFCItems.stick, 1));
									else if (world.rand.nextInt(100) < 4 && TFCOptions.enableSaplingDrops)
										dropSapling(world, i + x, j + y, k + z, meta);
									removeLeaves(world, i + x, j + y, k + z);
									addDrops(entityplayer, world, i, j, k, meta, false);
									super.harvestBlock(world, entityplayer, i + x, j + y, k + z, meta);

									itemstack.damageItem(1, entityplayer);
									if (itemstack.stackSize == 0)
										entityplayer.inventory
												.setInventorySlotContents(entityplayer.inventory.currentItem, null);
								}
							}
						}
					}
					return;
				}
			}

			// Only executes if scythe wasn't found
			entityplayer.addStat(StatList.mineBlockStatArray[getIdFromBlock(this)], 1);
			entityplayer.addExhaustion(0.025F);
			if (world.rand.nextInt(100) < 28)
				dropBlockAsItem(world, i, j, k, new ItemStack(TFCItems.stick, 1));
			else if (world.rand.nextInt(100) < 6 && TFCOptions.enableSaplingDrops)
				dropSapling(world, i, j, k, meta);
			addDrops(entityplayer, world, i, j, k, meta, false);
			super.harvestBlock(world, entityplayer, i, j, k, meta);

		}
	}
	
	private void addDrops(EntityPlayer entityplayer, World world, int x,int y, int z, int meta, boolean isScythe)
	{
		if(world.rand.nextInt(100) < (isScythe ? 8 : 6))
		{
			Item nut = null;
			
			switch(meta)
			{
				case 0: nut = CWTFCItems.walnut;
			}
			
			if(nut != null)
				dropBlockAsItem(world, x, y, z, ItemFoodTFC.createTag(new ItemStack(nut), CropIndex.getWeight(4, world.rand)));
		}
			
		
		if(isScythe)
		{
			dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
			if(world.rand.nextInt(100) < 30)
				dropBlockAsItem(world, x, y, z, new ItemStack(TFCItems.stick, 1));
			world.setBlockToAir(x, y, z);
		}
	}

	protected void dropSapling(World world, int x, int y, int z, int meta)
	{
		if (meta != 9 && meta != 15)
			dropBlockAsItem(world, x, y, z, new ItemStack(this.getItemDropped(0, null, 0), 1, meta));
	}

	@Override
	public int damageDropped(int dmg)
	{
		return dmg;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
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

	@Override
	public void registerBlockIcons(IIconRegister iconRegisterer)
	{
		for (int i = 0; i < this.woodNames.length; i++)
		{
			this.icons[i] = iconRegisterer
					.registerIcon(ModInfo.ModID + ":" + "Wood/" + this.woodNames[i] + " Leaves Fancy");
			this.iconsOpaque[i] = iconRegisterer
					.registerIcon(ModInfo.ModID + ":" + "Wood/" + this.woodNames[i] + " Leaves");
		}
	}

	@Override
	public String[] func_150125_e()
	{
		return this.woodNames.clone();
	}

	@Override
	public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z)
	{
		return false;
	}
}
