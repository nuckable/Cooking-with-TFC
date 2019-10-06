package straywolfe.cookingwithtfc.common.block;

import com.dunk.tfc.Core.TFC_Core;
import com.dunk.tfc.TerraFirmaCraft;
import com.dunk.tfc.api.TFCOptions;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.texture.IIconRegister;
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
import net.minecraftforge.oredict.OreDictionary;
import straywolfe.cookingwithtfc.api.CWTFCBlocks;
import straywolfe.cookingwithtfc.api.managers.CWTFCCropIndex;
import straywolfe.cookingwithtfc.api.managers.CropManager;
import straywolfe.cookingwithtfc.common.lib.ModInfo;
import straywolfe.cookingwithtfc.common.registries.PlantRegistry;
import straywolfe.cookingwithtfc.common.tileentity.TileCrop;

import java.util.Random;

public class BlockCrop extends BlockContainer
{
	private IIcon[] iconsGourds = new IIcon[6];
	private IIcon[] iconCelery = new IIcon[7];
	private IIcon[] iconLettuce = new IIcon[6];
	private IIcon[] iconPeanut = new IIcon[6];
	
	
	public BlockCrop() 
	{
		super(Material.plants);
		setBlockName("customCrop");
		blockHardness = 0.5F;
	}	
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register)
	{
		for(int i = 1; i <= 6; i++)
		{
			iconLettuce[i - 1] = register.registerIcon(ModInfo.ModID + ":Crops/Lettuce_" + i);
			iconPeanut[i - 1] = register.registerIcon(ModInfo.ModID + ":Crops/Peanut_" + i);
		}
		
		for(int i = 1; i <= 7; i++)
		{
			iconCelery[i - 1] = register.registerIcon(ModInfo.ModID + ":Crops/Celery_" + i);
		}
		
	}
	
	@SideOnly(Side.CLIENT)
	public IIcon getCropIcon(String s)
	{
		return null;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess access, int x, int y, int z, int meta)
	{
		TileCrop te = (TileCrop)access.getTileEntity(x, y, z);
		int cropID = te.getCropID();
		CWTFCCropIndex crop = CropManager.getInstance().getCropFromId(cropID);
		
		if(crop != null)
		{
			int stage = (int) Math.floor(te.growth);
			if(stage >= crop.numGrowthStages)
				stage = crop.numGrowthStages - 1;
			
			switch(cropID)
			{
				case PlantRegistry.CELERY:
					return iconCelery[stage];
				case PlantRegistry.LETTUCE:
					return iconLettuce[stage];
				case PlantRegistry.PEANUT:
					return iconPeanut[stage];
				default: 
					return iconsGourds[2];
			}
		}
		
		return iconsGourds[2];
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityplayer, int side, float hitX, float hitY, float hitZ)
	{
		if(!world.isRemote)
		{
			TileCrop te = (TileCrop)world.getTileEntity(x, y, z);
			CWTFCCropIndex crop = CropManager.getInstance().getCropFromId(te.getCropID());
			
			if(TFCOptions.enableDebugMode)
			{
				TerraFirmaCraft.LOG.info("Crop ID: " + te.getCropID());
				TerraFirmaCraft.LOG.info("Est Growth: " + te.getEstimatedGrowth(crop));
			}
		}
		
		return false;
	}
	
	@Override
	public void onBlockHarvested(World world, int x, int y, int z, int l, EntityPlayer player)
	{
		if (!world.isRemote)
		{
			ItemStack itemstack = player.inventory.getCurrentItem();
			int[] equipIDs = OreDictionary.getOreIDs(itemstack);

			for (int id : equipIDs)
			{
				String name = OreDictionary.getOreName(id);
				if (name.startsWith("itemScythe"))
				{
					for (int i = -1; i < 2; i++)
					{
						for (int j = -1; j < 2; j++)
						{
							if (world.getBlock(x + i, y, z + j) == this && player.inventory.getStackInSlot(player.inventory.currentItem) != null)
							{
								player.addStat(StatList.mineBlockStatArray[getIdFromBlock(this)], 1);
								TileCrop teCrop = (TileCrop)world.getTileEntity(x + i, y, z + j);
								
								teCrop.onHarvest(world, player);

								world.setBlockToAir(x + i, y, z + j);

								itemstack.damageItem(1, player);
								if (itemstack.stackSize == 0)
									player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
							}
						}
					}
					return;
				}
			}
			
			((TileCrop)world.getTileEntity(x, y, z)).onHarvest(world, player);
		}
	}
	
	@Override
	public int getRenderType()
	{
		return CWTFCBlocks.customCropRenderID;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) 
	{
		return new TileCrop();
	}
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
	{		
		if(!canBlockStay(world, x, y, z))
			world.setBlockToAir(x, y, z);
	}
	
	@Override
	public boolean canBlockStay(World world, int x, int y, int z)
	{
		return TFC_Core.isFarmland(world.getBlock(x, y - 1, z)) || TFC_Core.isSoil(world.getBlock(x, y - 1, z));
	}

	@Override
	public boolean getBlocksMovement(IBlockAccess bAccess, int x, int y, int z)
	{
		return true;
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		TileCrop te = (TileCrop)world.getTileEntity(x, y, z);
		int meta = world.getBlockMetadata(x, y, z);
		int stage = (int) Math.floor(te.growth);
		
		switch(te.getCropID())
		{
			default: return null;
		}
	}

	@Override
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z)
	{
		return AxisAlignedBB.getBoundingBox(x, y, z, x + 1, y + 0.2, z + 1);
	}
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) 
	{
		if(world.getTileEntity(x, y, z) instanceof TileCrop)
		{
			setBlockBounds(0, 0, 0, 1, 0.2f, 1);
		}
	}

	@Override
	public Item getItemDropped(int metadata, Random rand, int fortune)
	{
		return null;
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
	@SideOnly(Side.CLIENT)
	public boolean addDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer effectRenderer)
	{
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean addHitEffects(World worldObj, MovingObjectPosition target, EffectRenderer effectRenderer)
	{
		return true;
	}
}
