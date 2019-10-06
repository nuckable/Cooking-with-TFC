package straywolfe.cookingwithtfc.common.item.itemblock;

import com.dunk.tfc.Core.TFCTabs;
import com.dunk.tfc.Items.ItemTerra;
import com.dunk.tfc.api.Enums.EnumSize;
import com.dunk.tfc.api.Enums.EnumWeight;
import com.dunk.tfc.api.TFCBlocks;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import straywolfe.cookingwithtfc.api.CWTFCBlocks;
import straywolfe.cookingwithtfc.client.render.ItemLogRenderer;

import java.util.List;

public class ItemLog extends ItemTerra
{
	//private IIcon[] icons;

	public ItemLog()
	{
		super();
		setMaxDamage(0);
		setHasSubtypes(true);
		setCreativeTab(TFCTabs.TFC_MATERIALS);
		metaNames = new String[]{"Walnut"};
		//icons = new IIcon[metaNames.length];
		setWeight(EnumWeight.HEAVY);
		setSize(EnumSize.MEDIUM);
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List list)
	{
		for(int i = 0; i < metaNames.length; i++)
			list.add(new ItemStack(this, 1, i));
	}

	@Override
	public void registerIcons(IIconRegister registerer)
	{
		super.registerIcons(registerer);
		MinecraftForgeClient.registerItemRenderer(this, new ItemLogRenderer());
	}

	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
	{
		if(!world.isRemote)
		{
			int meta = itemstack.getItemDamage();
			Block block = CWTFCBlocks.woodVert;

			if(side == 0 && block.canPlaceBlockAt(world, x, y-1, z) && world.canPlaceEntityOnSide(TFCBlocks.woodVert, x, y-1, z, false, side, null, itemstack))
			{
				world.setBlock(x, y-1, z, block, meta, 3);
				itemstack.stackSize = itemstack.stackSize-1;
				playSound(world, x, y, z);
			}
			else if(side == 1 && block.canPlaceBlockAt(world, x, y+1, z) && world.canPlaceEntityOnSide(TFCBlocks.woodVert, x, y+1, z, false, side, null, itemstack))
			{
				world.setBlock(x, y+1, z, block, meta, 3);
				itemstack.stackSize = itemstack.stackSize-1;
				playSound(world, x, y, z);
			}
			else if(side == 2 && block.canPlaceBlockAt(world, x, y, z-1) && world.canPlaceEntityOnSide(TFCBlocks.woodVert, x, y, z-1, false, side, null, itemstack))
			{
				setSide(world, itemstack, meta, side, x, y, z-1);
			}
			else if(side == 3 && block.canPlaceBlockAt(world, x, y, z+1) && world.canPlaceEntityOnSide(TFCBlocks.woodVert, x, y, z+1, false, side, null, itemstack))
			{
				setSide(world, itemstack, meta, side, x, y, z+1);
			}
			else if(side == 4 && block.canPlaceBlockAt(world, x-1, y, z) && world.canPlaceEntityOnSide(TFCBlocks.woodVert, x-1, y, z, false, side, null, itemstack))
			{
				setSide(world, itemstack, meta, side, x-1, y, z);
			}
			else if(side == 5 && block.canPlaceBlockAt(world, x+1, y, z) && world.canPlaceEntityOnSide(TFCBlocks.woodVert, x+1, y, z, false, side, null, itemstack))
			{
				setSide(world, itemstack, meta, side, x+1, y, z);
			}
			return true;
		}
		return false;
	}

	private void playSound(World world, int x, int y, int z)
	{
		world.playSoundEffect(x + 0.5, y + 0.5, z + 0.5, TFCBlocks.logNatural.stepSound.func_150496_b(), (TFCBlocks.logNatural.stepSound.getVolume() + 1.0F) / 2.0F, TFCBlocks.logNatural.stepSound.getPitch() * 0.8F);
	}

	public void setSide(World world, ItemStack itemstack, int meta, int side, int x, int y, int z)
	{
		if (side == 2 || side == 3) {
			world.setBlock(x, y, z, CWTFCBlocks.woodHorizNS, meta, 3);
			itemstack.stackSize = itemstack.stackSize-1;
			playSound(world, x, y, z);
		}
		else if (side == 4 || side == 5) {
			world.setBlock(x, y, z, CWTFCBlocks.woodHorizEW, meta, 3);
			itemstack.stackSize = itemstack.stackSize-1;
			playSound(world, x, y, z);
		}
	}
}
