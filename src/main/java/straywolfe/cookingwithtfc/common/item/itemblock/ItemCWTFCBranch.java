package straywolfe.cookingwithtfc.common.item.itemblock;

import com.dunk.tfc.Reference;
import com.dunk.tfc.Items.ItemBlocks.ItemBranch;
import com.dunk.tfc.Items.ItemBlocks.ItemTerraBlock;
import com.dunk.tfc.api.Constant.Global;
import com.dunk.tfc.api.Enums.EnumWeight;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import straywolfe.cookingwithtfc.common.lib.Constants;
import straywolfe.cookingwithtfc.common.lib.ModInfo;

public class ItemCWTFCBranch extends ItemTerraBlock
{
	public ItemCWTFCBranch(Block b)
	{
		super(b);
		this.metaNames = Constants.WOODTYPES;
		this.icons = new IIcon[metaNames.length];
	}

	@Override
	public IIcon getIconFromDamage(int index)
	{
		return icons[index];
	}

	@Override
	public void registerIcons(IIconRegister registerer)
	{
		for(int i = 0; i < this.metaNames.length; i++)
			icons[i] = registerer.registerIcon(ModInfo.ModID + ":" + "Wood/" + this.metaNames[i] + " Log");
	}

	@Override
	public EnumWeight getWeight(ItemStack is)
	{
		return EnumWeight.MEDIUM;
	}

}
