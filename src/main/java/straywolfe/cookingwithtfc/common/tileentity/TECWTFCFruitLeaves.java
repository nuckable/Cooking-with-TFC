package straywolfe.cookingwithtfc.common.tileentity;

import com.dunk.tfc.TileEntities.NetworkTileEntity;
import com.dunk.tfc.TileEntities.TEFruitLeaves;

import net.minecraft.nbt.NBTTagCompound;

public class TECWTFCFruitLeaves extends NetworkTileEntity {
	public int dayHarvested = -1000;
	public int dayFruited = -1000;
	public boolean hasFruit;
	public int fruitType;

	public TECWTFCFruitLeaves()
	{
		this.shouldSendInitData = true;
	}

	@Override
	public boolean canUpdate()
	{
		return false;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		dayHarvested = nbt.getInteger("dayHarvested");
		dayFruited = nbt.getInteger("dayFruited");
		hasFruit = nbt.getBoolean("hasFruit");
		fruitType = nbt.getInteger("fruitType");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		nbt.setInteger("dayHarvested", dayHarvested);
		nbt.setInteger("dayFruited", dayFruited);
		nbt.setBoolean("hasFruit", hasFruit);
		nbt.setInteger("fruitType", fruitType);
	}

	@Override
	public void handleInitPacket(NBTTagCompound nbt)
	{
		hasFruit = nbt.getBoolean("hasFruit");
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		fruitType = nbt.getInteger("fruitType");
	}

	@Override
	public void handleDataPacket(NBTTagCompound nbt)
	{
		hasFruit = nbt.getBoolean("hasFruit");
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		fruitType = nbt.getInteger("fruitType");
	}

	@Override
	public void createDataNBT(NBTTagCompound nbt)
	{
		nbt.setBoolean("hasFruit", hasFruit);
		nbt.setInteger("fruitType", fruitType);
	}

	@Override
	public void createInitNBT(NBTTagCompound nbt)
	{
		nbt.setBoolean("hasFruit", hasFruit);
		nbt.setInteger("fruitType", fruitType);
	}
}
