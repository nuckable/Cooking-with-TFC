package straywolfe.cookingwithtfc.common.item;

import java.util.List;

import com.dunk.tfc.Core.TFC_Core;
import com.dunk.tfc.Items.ItemTerra;
import com.dunk.tfc.api.TFCOptions;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import straywolfe.cookingwithtfc.api.CWTFCBlocks;
import straywolfe.cookingwithtfc.common.block.BlockCWTFCFruitTreeBranch;
import straywolfe.cookingwithtfc.common.core.Tabs;
import straywolfe.cookingwithtfc.common.lib.Constants;
import straywolfe.cookingwithtfc.common.lib.ModInfo;
import straywolfe.cookingwithtfc.common.tileentity.TECWTFCFruitTreeWood;

public class ItemCWTFCFruitTreeSapling extends ItemTerra {
	private IIcon[] icons;

	public ItemCWTFCFruitTreeSapling()
	{
		super();
		setMaxDamage(0);
		setHasSubtypes(true);
		this.setCreativeTab(Tabs.MAINTAB);
		this.metaNames = Constants.FRUITTREETYPES;
		this.icons = new IIcon[metaNames.length];
	}

	@Override
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
	{
		//int meta = MathHelper.floor_double(player.rotationYaw * 4F / 360F + 0.5D) & 3;
		if(side == 1
			&& ((world.getBlock(x, y, z).isNormalCube() && world.getBlock(x, y, z).isOpaqueCube() && (TFC_Core.isSoil(world.getBlock(x, y, z)))
				|| (stack.getItemDamage()==Constants.COCONUTID && TFC_Core.isSand(world.getBlock(x, y, z))))
				|| (world.getBlock(x, y, z) instanceof BlockCWTFCFruitTreeBranch && TFCOptions.enableDebugMode))
			&& world.isAirBlock(x, y + 1, z) && !world.isRemote)
		{

			int damage = stack.getItemDamage();
			if (damage >= metaNames.length)
			{
				stack.setItemDamage(damage);
			}
			world.setBlock(x, y + 1, z, CWTFCBlocks.fruitTreeBranchEnd__y_, damage, 2);
			((TECWTFCFruitTreeWood)world.getTileEntity(x, y + 1, z)).fruitType = damage;
			((TECWTFCFruitTreeWood)world.getTileEntity(x, y + 1, z)).setTrunk(true);
			((TECWTFCFruitTreeWood)world.getTileEntity(x, y + 1, z)).setHeight(0);
			((TECWTFCFruitTreeWood)world.getTileEntity(x, y + 1, z)).initBirth();
			((TECWTFCFruitTreeWood)world.getTileEntity(x, y + 1, z)).broadcastPacketInRange();
			world.markBlockForUpdate(x, y+1, z);
			stack.stackSize = stack.stackSize - 1;
			return true;
		}

		return false;
	}

	@Override
	public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List list)
	{
		for(int i = 0; i < metaNames.length; i++)
		{
			list.add(new ItemStack(this, 1, i));
		}
	}

	@Override
	public void registerIcons(IIconRegister registerer)
	{
		for(int i = 0; i < metaNames.length; i++)
			icons[i] = registerer.registerIcon(ModInfo.ModID + ":" + "wood/Fruit Trees/" + metaNames[i] + " Sapling");
	}

	@Override
	public IIcon getIconFromDamage(int meta)
	{
		return icons[meta];
	}

	// Check for and correct invalid saplings from prior plum sapling bug
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconIndex(ItemStack par1ItemStack)
	{
		int damage = par1ItemStack.getItemDamage();
		if (damage >= metaNames.length)
		{
			damage -= 8;
			par1ItemStack.setItemDamage(damage);
		}
		return this.getIconFromDamage(damage);
	}

	@Override
	public int getMetadata(int i)
	{
		return i;
	}
}
