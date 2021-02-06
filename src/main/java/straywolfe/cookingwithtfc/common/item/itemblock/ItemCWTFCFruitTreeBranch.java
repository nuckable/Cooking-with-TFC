package straywolfe.cookingwithtfc.common.item.itemblock;

import com.dunk.tfc.Blocks.Flora.BlockBranch;
import com.dunk.tfc.Core.TFC_Core;
import com.dunk.tfc.Items.ItemBlocks.ItemTerraBlock;
import com.dunk.tfc.TileEntities.TEFruitTreeWood;
import com.dunk.tfc.api.TFCBlocks;
import com.dunk.tfc.api.TFCOptions;
import com.dunk.tfc.api.Enums.EnumWeight;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import straywolfe.cookingwithtfc.api.CWTFCBlocks;
import straywolfe.cookingwithtfc.common.block.BlockCWTFCFruitTreeBranch;
import straywolfe.cookingwithtfc.common.lib.Constants;
import straywolfe.cookingwithtfc.common.lib.ModInfo;
import straywolfe.cookingwithtfc.common.tileentity.TECWTFCFruitTreeWood;

public class ItemCWTFCFruitTreeBranch extends ItemTerraBlock {
	public ItemCWTFCFruitTreeBranch(Block b)
	{
		super(b);
		this.metaNames = Constants.FRUITTREETYPES;
		this.icons = new IIcon[metaNames.length];
	}

	@Override
	public void registerIcons(IIconRegister registerer)
	{
		for(int i = 0; i < this.metaNames.length; i++)
			icons[i] = registerer.registerIcon(ModInfo.ModID + ":" + "Wood/Fruit Trees/" + this.metaNames[i] + " Wood");
	}

	@Override
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
	{
		//int meta = MathHelper.floor_double(player.rotationYaw * 4F / 360F + 0.5D) & 3;
		if(side == 1 && ((world.getBlock(x, y, z).isNormalCube() && world.getBlock(x, y, z).isOpaqueCube() &&
				(TFC_Core.isSoil(world.getBlock(x, y, z))) || (TFC_Core.isSand(world.getBlock(x, y, z))))
				|| (world.getBlock(x, y, z) instanceof BlockCWTFCFruitTreeBranch && TFCOptions.enableDebugMode)) &&
				world.isAirBlock(x, y + 1, z) && !world.isRemote)
		{

			int damage = stack.getItemDamage();
			if (damage >= metaNames.length)
			{
				stack.setItemDamage(damage);
			}
			world.setBlock(x, y + 1, z, CWTFCBlocks.fruitTreeBranchEnd__y_, damage, 0x2);
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
	public EnumWeight getWeight(ItemStack is)
	{
		return EnumWeight.MEDIUM;
	}
}
