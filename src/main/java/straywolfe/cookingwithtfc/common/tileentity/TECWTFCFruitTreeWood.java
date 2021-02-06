package straywolfe.cookingwithtfc.common.tileentity;

import java.util.Random;

import com.dunk.tfc.Blocks.Flora.BlockBranch;
import com.dunk.tfc.Blocks.Flora.BlockFruitLeaves;
import com.dunk.tfc.Core.TFC_Climate;
import com.dunk.tfc.Core.TFC_Time;
import com.dunk.tfc.Food.FloraIndex;
import com.dunk.tfc.Food.FloraManager;
import com.dunk.tfc.TileEntities.NetworkTileEntity;
import com.dunk.tfc.TileEntities.TEFruitTreeWood;
import com.dunk.tfc.WorldGen.TFCBiome;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import straywolfe.cookingwithtfc.api.CWTFCBlocks;
import straywolfe.cookingwithtfc.common.block.BlockCWTFCFruitLeaves;
import straywolfe.cookingwithtfc.common.block.BlockCWTFCFruitTreeBranch;
import straywolfe.cookingwithtfc.common.lib.Constants;
import straywolfe.cookingwithtfc.common.worldgen.CWTFCBiome;

public class TECWTFCFruitTreeWood extends TEFruitTreeWood
{
	private static final long LEAF_GROWTH_RATE = 20;

	public TECWTFCFruitTreeWood()
	{
		super();
	}

	private int getGrowDistance()
	{
		if (this.fruitType != Constants.COCONUTID)
		{
			return 4;
		}
		return 8;
	}

	@Override
	public void updateEntity()
	{
		if (!worldObj.isRemote)
		{
			Block branch = worldObj.getBlock(this.xCoord, this.yCoord, this.zCoord);
			if (birthTimeWood + this.BRANCH_GROW_TIME <= TFC_Time.getTotalDays() && branch instanceof BlockCWTFCFruitTreeBranch && ((BlockCWTFCFruitTreeBranch) branch).isEnd())
			{
				int dist = ((BlockCWTFCFruitTreeBranch) branch).getDistanceToTrunk(worldObj, this.xCoord, this.yCoord, this.zCoord, 0);
				if (dist >= 0 && dist < getGrowDistance())
				{
					if (fruitType >= 0 && fruitType < CWTFCBiome.fruitTreesFromSapling.length)
					{
						CWTFCBiome.fruitTreesFromSapling[fruitType].growBranch(this.worldObj, new Random(), this.xCoord, this.yCoord, this.zCoord);
					}

				}
				birthTimeWood += this.BRANCH_GROW_TIME;
			}
		}
	}

	private boolean checkLeaves(int xCoord, int yCoord, int zCoord)
	{
		return worldObj.blockExists(xCoord, yCoord, zCoord) && worldObj.isAirBlock(xCoord, yCoord, zCoord) && worldObj
				.isAirBlock(xCoord, yCoord + 1, zCoord) && BlockCWTFCFruitLeaves.canStay(worldObj, xCoord, yCoord, zCoord);
	}

	public String getInventoryName()
	{
		return "Fruit Tree Wood";
	}

	public boolean canFruit()
	{
		return this.fruitType == Constants.COCONUTID;
	}
}
