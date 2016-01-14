package com.JAWolfe.cookingwithtfc.GUIs.Containers.Slots;

import com.JAWolfe.cookingwithtfc.items.ItemTFCMeatTransform;
import com.bioxx.tfc.Food.ItemFoodTFC;
import com.bioxx.tfc.api.Food;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotFoodPrep extends Slot
{

	public SlotFoodPrep(EntityPlayer entityplayer, IInventory inventory, int slotIndex, int xDisplayPosition, int yDisplayPosition) 
	{
		super(inventory, slotIndex, xDisplayPosition, yDisplayPosition);
	}
	
	@Override
	public boolean isItemValid(ItemStack is)
	{
		if(is.getItem() instanceof ItemFoodTFC)
		{
			if(is.getItem() instanceof ItemTFCMeatTransform && !Food.isCooked(is))
				return false;
			
			return true;
		}
		
		return false;
	}

	@Override
	public int getSlotStackLimit()
	{
		return 1;
	}
	
	@Override
	public void putStack(ItemStack is)
	{
		if (is != null)
			is.stackSize = 1;
		if (this.inventory != null)
			super.putStack(is);
	}
}
