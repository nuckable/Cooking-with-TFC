package straywolfe.cookingwithtfc.common.block;

import java.util.Random;

import com.dunk.tfc.Reference;
import com.dunk.tfc.Blocks.BlockTerraContainer;
import com.dunk.tfc.Core.TFC_Core;
import com.dunk.tfc.TileEntities.TEFruitTreeWood;
import com.dunk.tfc.api.TFCBlocks;
import com.dunk.tfc.api.TFCItems;
import com.dunk.tfc.api.Constant.Global;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import straywolfe.cookingwithtfc.api.CWTFCBlocks;
import straywolfe.cookingwithtfc.api.CWTFCItems;
import straywolfe.cookingwithtfc.common.lib.Constants;
import straywolfe.cookingwithtfc.common.lib.ModInfo;
import straywolfe.cookingwithtfc.common.tileentity.TECWTFCFruitTreeWood;

public class BlockCWTFCFruitWood extends BlockTerraContainer {
	private IIcon[] icons = new IIcon[Constants.FRUITTREETYPES.length];

	public BlockCWTFCFruitWood()
	{
		super(Material.wood);
		this.setBlockBounds(0.3f, 0, 0.3f, 0.7f, 1, 0.7f); //Default block bounds set to trunk state
	}

	private boolean checkOut(World world, int i, int j, int k, int l)
	{
		return world.getBlock(i, j, k) == this && world.getBlockMetadata(i, j, k) == l;
	}

	@Override
	public int damageDropped(int j)
	{
		return j;
	}

	@Override
	public IIcon getIcon(int i, int j)
	{
		return icons[j];
	}

	@Override
	public void registerBlockIcons(IIconRegister registerer)
	{
		for(int i = 0; i < icons.length; i++)
			icons[i] = registerer.registerIcon(ModInfo.ModID + ":" + "Wood/Fruit Trees/" + Constants.FRUITTREETYPES[i] + " Wood");
	}

	@Override
	public void harvestBlock(World world, EntityPlayer entityplayer, int i, int j, int k, int l)
	{
		//we need to make sure the player has the correct tool out
		boolean isAxeorSaw = false;
		ItemStack equip = entityplayer.getCurrentEquippedItem();
		if (equip != null)
		{
			int[] equipIDs = OreDictionary.getOreIDs(equip);
			for (int id : equipIDs)
			{
				String name = OreDictionary.getOreName(id);
				if (name.startsWith("itemAxe") || name.startsWith("itemSaw"))
				{
					isAxeorSaw = true;
					break;
				}
			}
		}
		if(isAxeorSaw)
		{
			int x = i;
			int y = 0;
			int z = k;

			if(world.getBlock(i, j+1, k) == this || world.getBlock(i, j-1, k) == this)
			{
				boolean checkArray[][][] = new boolean[11][50][11];

				if(TFC_Core.isGrass(world.getBlock(i, j+y-1, k)) || TFC_Core.isDirt(world.getBlock(i, j+y-1, k)))
				{
					boolean reachedTop = false;
					while(!reachedTop)
					{
						if (world.isAirBlock(x, j+y+1, z))
						{
							reachedTop = true;
						}
						scanLogs(world,i,j+y,k,l,checkArray,6,y,6);
						y++;
					}
				}
			}
			else if(world.getBlock(i + 1, j, k) == this ||
					world.getBlock(i - 1, j, k) == this ||
					world.getBlock(i, j, k + 1) == this ||
					world.getBlock(i, j, k - 1) == this)
			{
				Random r = new Random();
				if(r.nextInt(100) > 50 && isAxeorSaw)
				{
					if(l < 8 && (
							world.getBlock(i + 1, j, k) == CWTFCBlocks.fruitTreeLeaves ||
							world.getBlock(i - 1, j, k) == CWTFCBlocks.fruitTreeLeaves ||
							world.getBlock(i, j, k + 1) == CWTFCBlocks.fruitTreeLeaves ||
							world.getBlock(i, j, k - 1) == CWTFCBlocks.fruitTreeLeaves ||
							world.getBlock(i, j + 1, k) == CWTFCBlocks.fruitTreeLeaves ||
							world.getBlock(i, j - 1, k) == CWTFCBlocks.fruitTreeLeaves))
					{
						l += 8;
					}
					dropBlockAsItem(world, i, j, k, new ItemStack(CWTFCItems.fruitTreeSapling, 1, l));
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
	public void onNeighborBlockChange(World world, int i, int j, int k, Block block)
	{
		boolean check = false;
		for(int h = -1; h <= 1; h++)
		{
			for(int g = -1; g <= 1; g++)
			{
				for(int f = -1; f <= 1; f++)
				{
					if(world.getBlock(i + h, j + g, k + f) == this && world.getBlockMetadata(i + h, j + g, k + f) == world.getBlockMetadata(i, j, k))
						check = true;
				}
			}
		}
		if(!check)
			world.setBlockToAir(i, j, k);
	}

	private void scanLogs(World world, int i, int j, int k, int l, boolean[][][] checkArray,int x, int y, int z)
	{
		if(y >= 0)
		{
			checkArray[x][y][z] = true;
			int offsetX = 0;
			int offsetY = 0;
			int offsetZ = 0;

			for (offsetY = 0; offsetY <= 1; offsetY++)
			{
				for (offsetX = -1; offsetX <= 1; offsetX++)
				{
					for (offsetZ = -1; offsetZ <= 1; offsetZ++)
					{
						if(x + offsetX < 11 && x + offsetX >= 0 && z + offsetZ < 11 && z + offsetZ >= 0 && y + offsetY < 50 && y + offsetY >= 0)
						{
							if(checkOut(world, i + offsetX, j + offsetY, k + offsetZ, l) && !checkArray[x + offsetX][y + offsetY][z + offsetZ])
								scanLogs(world,i + offsetX, j + offsetY, k + offsetZ, l, checkArray, x + offsetX, y + offsetY, z + offsetZ);
						}
					}
				}
			}
			world.setBlockToAir(i, j, k);
		}
	}

	@Override
	public int getRenderType()
	{
		return CWTFCBlocks.woodFruitRenderId;
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

	@Override
	public boolean shouldSideBeRendered(IBlockAccess par1iBlockAccess, int par2, int par3, int par4, int par5)
	{
		return true;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k)
	{
		if(world.getBlock(i, j-1, k) == this || world.getBlock(i, j-1, k).isOpaqueCube())
			return AxisAlignedBB.getBoundingBox(i+0.3, j, k+0.3, i+0.7, j+1, k+0.7);
		return AxisAlignedBB.getBoundingBox(i, j + 0.4, k, i + 1, j + 0.6, k + 1);
	}

	@Override
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int i, int j, int k)
	{
		if(world.getBlock(i, j-1, k) == this || world.getBlock(i, j-1, k).isOpaqueCube())
			return AxisAlignedBB.getBoundingBox(i+0.3, j, k+0.3, i+0.7, j+1, k+0.7);
		return AxisAlignedBB.getBoundingBox(i, j+0.4, k, i+1, j+0.6, k+1);
	}

	/**
	 * Updates the blocks bounds based on its current state. Args: world, x, y, z
	 */
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int i, int j, int k)
	{
		if (world.getBlock(i, j - 1, k) == this || world.getBlock(i, j - 1, k).isOpaqueCube())
			this.setBlockBounds(0.3f, 0, 0.3f, 0.7f, 1, 0.7f);
		else
			this.setBlockBounds(0, 0.4f, 0, 1, 0.6f, 1);
	}

	@Override
	public void updateTick(World world, int i, int j, int k, Random rand)
	{

	}

	public static String getType(int fruitType)
	{
		switch(fruitType)
		{
		case 0: return Constants.FRUITTREETYPES[0];
		case 1: return Constants.FRUITTREETYPES[1];
		case 2: return Constants.FRUITTREETYPES[2];
		case 3: return Constants.FRUITTREETYPES[3];
		case 4: return Constants.FRUITTREETYPES[4];
		case 5: return Constants.FRUITTREETYPES[5];
		}
		return "";
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2)
	{
		return new TECWTFCFruitTreeWood();
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int metadata)
	{
		if(!world.isRemote && checkOut(world,x,y-1,z,metadata) && world.getTileEntity(x, y-1, z) != null)
			((TECWTFCFruitTreeWood)world.getTileEntity(x, y-1, z)).initBirth();
		super.breakBlock(world, x, y, z, block, metadata);
	}

	@Override
	public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
	{
		return null;
	}
}
