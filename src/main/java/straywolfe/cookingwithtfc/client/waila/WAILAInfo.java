package straywolfe.cookingwithtfc.client.waila;

import com.dunk.tfc.Core.TFC_Core;
import com.dunk.tfc.Core.TFC_Time;
import com.dunk.tfc.Food.FloraIndex;
import com.dunk.tfc.Food.FloraManager;
import com.dunk.tfc.Food.ItemFoodTFC;
import com.dunk.tfc.WAILA.WAILAData;
import com.dunk.tfc.api.TFCItems;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.IWailaRegistrar;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import straywolfe.cookingwithtfc.api.CWTFCBlocks;
import straywolfe.cookingwithtfc.api.CWTFCItems;
import straywolfe.cookingwithtfc.api.managers.CWTFCCropIndex;
import straywolfe.cookingwithtfc.api.managers.CropManager;
import straywolfe.cookingwithtfc.common.block.BlockBowl;
import straywolfe.cookingwithtfc.common.block.BlockCWTFCFruitLeaves;
import straywolfe.cookingwithtfc.common.block.BlockCWTFCFruitTreeBranch;
import straywolfe.cookingwithtfc.common.block.BlockCWTFCFruitWood;
import straywolfe.cookingwithtfc.common.block.BlockMeat;
import straywolfe.cookingwithtfc.common.block.BlockMixBowl;
import straywolfe.cookingwithtfc.common.block.BlockPrepTable;
import straywolfe.cookingwithtfc.common.block.BlockPrepTable2;
import straywolfe.cookingwithtfc.common.block.BlockSandwich;
import straywolfe.cookingwithtfc.common.core.helper.Helper;
import straywolfe.cookingwithtfc.common.item.ItemTFCMealTransform;
import straywolfe.cookingwithtfc.common.tileentity.TECWTFCFruitLeaves;
import straywolfe.cookingwithtfc.common.tileentity.TECWTFCFruitTreeWood;
import straywolfe.cookingwithtfc.common.tileentity.TileBowl;
import straywolfe.cookingwithtfc.common.tileentity.TileCookingPot;
import straywolfe.cookingwithtfc.common.tileentity.TileCrop;
import straywolfe.cookingwithtfc.common.tileentity.TileGourd;
import straywolfe.cookingwithtfc.common.tileentity.TileGrains;
import straywolfe.cookingwithtfc.common.tileentity.TileMeat;
import straywolfe.cookingwithtfc.common.tileentity.TileMixBowl;

import java.util.List;

public class WAILAInfo implements IWailaDataProvider
{
	public static void callbackRegister(IWailaRegistrar reg)
	{
		reg.registerStackProvider(new WAILAInfo(), TileGrains.class);
		reg.registerBodyProvider(new WAILAInfo(), TileGrains.class);
		reg.registerNBTProvider(new WAILAInfo(), TileGrains.class);
		
		reg.registerStackProvider(new WAILAInfo(), BlockMixBowl.class);
		reg.registerHeadProvider(new WAILAInfo(), BlockMixBowl.class);
		reg.registerNBTProvider(new WAILAInfo(), TileMixBowl.class);
		
		reg.registerStackProvider(new WAILAInfo(), BlockPrepTable.class);
		reg.registerStackProvider(new WAILAInfo(), BlockPrepTable2.class);
		
		reg.registerBodyProvider(new WAILAInfo(), TileCookingPot.class);
		reg.registerNBTProvider(new WAILAInfo(), TileCookingPot.class);
		
		reg.registerStackProvider(new WAILAInfo(), BlockMeat.class);
		reg.registerHeadProvider(new WAILAInfo(), BlockMeat.class);
		
		reg.registerStackProvider(new WAILAInfo(), BlockSandwich.class);
		reg.registerHeadProvider(new WAILAInfo(), BlockSandwich.class);
		
		reg.registerStackProvider(new WAILAInfo(), BlockBowl.class);
		reg.registerHeadProvider(new WAILAInfo(), BlockBowl.class);
		
		reg.registerStackProvider(new WAILAInfo(), TileGourd.class);
		reg.registerHeadProvider(new WAILAInfo(), TileGourd.class);
		
		reg.registerStackProvider(new WAILAInfo(), TileCrop.class);
		reg.registerHeadProvider(new WAILAInfo(), TileCrop.class);
		reg.registerBodyProvider(new WAILAInfo(), TileCrop.class);

		reg.registerStackProvider(new WAILAInfo(), TECWTFCFruitLeaves.class);
		reg.registerHeadProvider(new WAILAInfo(), TECWTFCFruitLeaves.class);
		reg.registerBodyProvider(new WAILAInfo(), TECWTFCFruitLeaves.class);
		reg.registerNBTProvider(new WAILAInfo(), TECWTFCFruitLeaves.class);

		reg.registerStackProvider(new WAILAData(), TECWTFCFruitTreeWood.class);
		reg.registerHeadProvider(new WAILAData(), TECWTFCFruitTreeWood.class);
		
		reg.registerStackProvider(new WAILAInfo(), BlockCWTFCFruitTreeBranch.class);
		reg.registerHeadProvider(new WAILAInfo(), BlockCWTFCFruitTreeBranch.class);
		
	}
	
	@Override
	public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config) 
	{
		Block block = accessor.getBlock();
		TileEntity tileEntity = accessor.getTileEntity();
		
		if(tileEntity instanceof TileGrains)
			return grainStack(accessor, config);
		else if(block instanceof BlockMixBowl)
			return mixBowlStack(accessor, config);
		else if(block instanceof BlockPrepTable)
			return prepTableStack(accessor, config);
		else if(block instanceof BlockPrepTable2)
			return prepTable2Stack(accessor, config);
		else if(block instanceof BlockMeat)
			return meatStack(accessor, config);
		else if(block instanceof BlockSandwich)
			return sandwichStack(accessor, config);
		else if(block instanceof BlockBowl)
			return bowlStack(accessor, config);
		else if(block instanceof BlockCWTFCFruitLeaves || tileEntity instanceof TECWTFCFruitLeaves)
			return fruitLeavesStack(accessor, config);
		else if(block instanceof BlockCWTFCFruitTreeBranch)
			return fruitTreeStack(accessor, config);
		else if(tileEntity instanceof TileCrop)
			return cropStack(accessor, config);
			
		return accessor.getStack();
	}

	@Override
	public List<String> getWailaHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor,	IWailaConfigHandler config) 
	{
		Block block = accessor.getBlock();
		TileEntity tileEntity = accessor.getTileEntity();
		
		if(block instanceof BlockMixBowl)
			currenttip = mixBowlHead(itemStack, currenttip, accessor, config);
		else if(block instanceof BlockMeat)
			currenttip = meatHead(itemStack, currenttip, accessor, config);
		else if(block instanceof BlockSandwich)
			currenttip = sandwichHead(itemStack, currenttip, accessor, config);
		else if(block instanceof BlockBowl)
			currenttip = bowlHead(itemStack, currenttip, accessor, config);
		else if(block instanceof BlockCWTFCFruitLeaves || tileEntity instanceof TECWTFCFruitLeaves)
			currenttip = fruitLeavesHead(itemStack, currenttip, accessor, config);
		else if(block instanceof BlockCWTFCFruitTreeBranch)
			currenttip = fruitTreeHead(itemStack, currenttip, accessor, config);
		else if(tileEntity instanceof TileCrop)
			currenttip = cropHead(itemStack, currenttip, accessor, config);
		
		return currenttip;
	}

	@Override
	public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) 
	{
		Block block = accessor.getBlock();
		TileEntity tileEntity = accessor.getTileEntity();
		
		if (tileEntity instanceof TileGrains)
			currenttip = grainBody(itemStack, currenttip, accessor, config);
		else if(tileEntity instanceof TileCookingPot)
			currenttip = cookingPotBody(itemStack, currenttip, accessor, config);
		else if(tileEntity instanceof TileCrop)
			currenttip = cropBody(itemStack, currenttip, accessor, config);
		else if(block instanceof BlockCWTFCFruitLeaves || tileEntity instanceof TECWTFCFruitLeaves)
			currenttip = fruitLeavesBody(itemStack, currenttip, accessor, config);
		
		return currenttip;
	}

	@Override
	public List<String> getWailaTail(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor,	IWailaConfigHandler config) 
	{
		return currenttip;
	}

	@Override
	public NBTTagCompound getNBTData(EntityPlayerMP player, TileEntity te, NBTTagCompound tag, World world, int x, int y, int z) 
	{
		if (te != null)
			te.writeToNBT(tag);
		return tag;
	}
	
	public ItemStack mixBowlStack(IWailaDataAccessor accessor, IWailaConfigHandler config) 
	{
		return new ItemStack(CWTFCBlocks.mixingBowl, 1, 1);
	}
	
	public List<String> mixBowlHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) 
	{
		currenttip.set(0, EnumChatFormatting.WHITE.toString() + itemStack.getDisplayName());

		return currenttip;
	}
	
	public ItemStack prepTableStack(IWailaDataAccessor accessor, IWailaConfigHandler config) 
	{
		return new ItemStack(accessor.getBlock(), 1, accessor.getMetadata());
	}
	
	public ItemStack prepTable2Stack(IWailaDataAccessor accessor, IWailaConfigHandler config) 
	{
		return new ItemStack(accessor.getBlock(), 1, accessor.getMetadata());
	}
	
	public ItemStack meatStack(IWailaDataAccessor accessor, IWailaConfigHandler config) 
	{
		TileMeat te = (TileMeat)accessor.getTileEntity();
		
		if(te.getplacedMeat() != null)
			return ItemFoodTFC.createTag(new ItemStack(te.getplacedMeat().getItem()), 999, -24);
		else
			return ItemFoodTFC.createTag(new ItemStack(TFCItems.beefRaw), 999, -24);
	}
	
	public List<String> meatHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) 
	{
		ItemStack meat = ((TileMeat)accessor.getTileEntity()).getplacedMeat();
		
		if(meat == null)
			currenttip.set(0, EnumChatFormatting.WHITE.toString() + "Meat");
		else
			currenttip.set(0, EnumChatFormatting.WHITE.toString() + Helper.translate(meat.getUnlocalizedName() + ".name"));

		return currenttip;
	}
	
	public ItemStack bowlStack(IWailaDataAccessor accessor, IWailaConfigHandler config) 
	{
		TileBowl te = (TileBowl)accessor.getTileEntity();
		
		if(te.getSaladContents()[0] == null)
			return new ItemStack(TFCItems.potteryBowl, 1, 1);
		else if(te.getSaladType() == 2)
			return ItemFoodTFC.createTag(new ItemStack(CWTFCItems.PotatoSalad), 999, -24);
		else if(te.getSaladType() == 1)
			return ItemFoodTFC.createTag(new ItemStack(CWTFCItems.FruitSalad), 999, -24);
		else
			return ItemFoodTFC.createTag(new ItemStack(CWTFCItems.VeggySalad), 999, -24);
	}
	
	public List<String> bowlHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) 
	{
		TileBowl te = (TileBowl)accessor.getTileEntity();
		
		if(te.getSaladContents()[0] == null)
			currenttip.set(0, EnumChatFormatting.WHITE.toString() + Helper.translate(TFCItems.potteryBowl.getUnlocalizedName() + ".Ceramic Bowl.name"));
		else if(te.getSaladType() == 2)
			currenttip.set(0, EnumChatFormatting.WHITE.toString() + Helper.translate(CWTFCItems.PotatoSalad.getUnlocalizedName() + ".name"));
		else if(te.getSaladType() == 1)
			currenttip.set(0, EnumChatFormatting.WHITE.toString() + Helper.translate(CWTFCItems.FruitSalad.getUnlocalizedName() + ".name"));
		else
			currenttip.set(0, EnumChatFormatting.WHITE.toString() + Helper.translate(CWTFCItems.VeggySalad.getUnlocalizedName() + ".name"));

		return currenttip;
	}
	
	public ItemStack grainStack(IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		NBTTagCompound tag = accessor.getNBTData();
		ItemStack grain = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("placedGrain"));
		
		if(grain == null)
			grain = ItemFoodTFC.createTag(new ItemStack(TFCItems.wheatWhole));
		
		return grain;
	}

	public List<String> grainBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		NBTTagCompound tag = accessor.getNBTData();
		float progressCount = tag.getInteger("WorkCounter");
		
		int progress = (int) Math.min((progressCount / 20) * 100, 100);
		currenttip.add(Helper.translate("gui.progress") + ": " + progress + "%");

		return currenttip;
	}
	
	public ItemStack sandwichStack(IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		NBTTagCompound tag = accessor.getNBTData();
		String breadType = tag.getString("breadType");
		int breadMeta = 0;
		
		if(TFCItems.cornBread.getUnlocalizedName().equals(breadType))
			breadMeta = 1;
		else if(TFCItems.oatBread.getUnlocalizedName().equals(breadType))
			breadMeta = 2;
		else if(TFCItems.riceBread.getUnlocalizedName().equals(breadType))
			breadMeta = 3;
		else if(TFCItems.ryeBread.getUnlocalizedName().equals(breadType))
			breadMeta = 4;
		else if(TFCItems.wheatBread.getUnlocalizedName().equals(breadType))
			breadMeta = 5;
		
		ItemStack stack = ItemTFCMealTransform.createTag(new ItemStack(CWTFCItems.ChickenSandwich, 1, breadMeta), 999, -24, new ItemStack[]{}, new float[]{});
		
		return stack;
	}
	
	public List<String> sandwichHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) 
	{
		NBTTagCompound tag = accessor.getNBTData();
		String meatType = tag.getString("meatType");
		String sandwichName = Helper.translate("gui.Sandwich");
		int topToast = tag.getInteger("topToast");
		
		if(TFCItems.chickenRaw.getUnlocalizedName().equals(meatType) || CWTFCItems.BoiledChicken.getUnlocalizedName().equals(meatType))
			sandwichName = Helper.translate(CWTFCItems.ChickenSandwich.getUnlocalizedName() + ".name");
		else if(TFCItems.porkchopRaw.getUnlocalizedName().equals(meatType) || CWTFCItems.BoiledPork.getUnlocalizedName().equals(meatType))
			sandwichName = Helper.translate(CWTFCItems.HamSandwich.getUnlocalizedName() + ".name");
		else if(TFCItems.eggCooked.getUnlocalizedName().equals(meatType))
			sandwichName = Helper.translate(CWTFCItems.FriedEggSandwich.getUnlocalizedName() + ".name");
		else if(TFCItems.muttonRaw.getUnlocalizedName().equals(meatType))
			sandwichName = Helper.translate(CWTFCItems.MuttonSandwich.getUnlocalizedName() + ".name");
		else if(TFCItems.beefRaw.getUnlocalizedName().equals(meatType) || CWTFCItems.BoiledBeef.getUnlocalizedName().equals(meatType))
			sandwichName = Helper.translate(CWTFCItems.RoastBeefSandwich.getUnlocalizedName() + ".name");
		else if(TFCItems.fishRaw.getUnlocalizedName().equals(meatType) || CWTFCItems.BoiledFish.getUnlocalizedName().equals(meatType) ||
				TFCItems.calamariRaw.getUnlocalizedName().equals(meatType))
			sandwichName = Helper.translate(CWTFCItems.SalmonSandwich.getUnlocalizedName() + ".name");
		else if(TFCItems.venisonRaw.getUnlocalizedName().equals(meatType) || CWTFCItems.BoiledVenison.getUnlocalizedName().equals(meatType) ||
				TFCItems.horseMeatRaw.getUnlocalizedName().equals(meatType))
			sandwichName = Helper.translate(CWTFCItems.VenisonSteakSandwich.getUnlocalizedName() + ".name");
		else if(topToast == 1)
			sandwichName = Helper.translate(CWTFCItems.ToastSandwich.getUnlocalizedName() + ".name");
		
		currenttip.set(0, EnumChatFormatting.WHITE.toString()  + sandwichName);

		return currenttip;
	}
	
	public List<String> cookingPotBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		NBTTagCompound tag = accessor.getNBTData();		
		
		int time = (int)((tag.getInteger("cookTimer")/(float)TFC_Time.HOUR_LENGTH) * 60);
		
		if(time > 0)
			currenttip.add(Helper.translate("gui.cookingTime") + ": " + time + " " + Helper.translate("gui.minutes"));

		return currenttip;
	}
	
	public ItemStack cropStack(IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		NBTTagCompound tag = accessor.getNBTData();
		int cropID = tag.getInteger("cropID");
		CWTFCCropIndex crop = CropManager.getInstance().getCropFromId(cropID);
		
		if(crop != null)
			return ItemFoodTFC.createTag(new ItemStack(crop.output1));
		else
			return null;
	}
	
	public List<String> cropHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) 
	{
		NBTTagCompound tag = accessor.getNBTData();
		
		int cropID = tag.getInteger("cropID");		
		CWTFCCropIndex crop = CropManager.getInstance().getCropFromId(cropID);
		if(crop != null)
		{
			String cropName = Helper.translate("item." + crop.cropName + ".name");
		
			currenttip.set(0, EnumChatFormatting.WHITE.toString() + cropName);
		}

		return currenttip;
	}
	
	public List<String> cropBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		NBTTagCompound tag = accessor.getNBTData();
		float growth = tag.getFloat("growth");
		int cropID = tag.getInteger("cropID");
		CWTFCCropIndex crop = CropManager.getInstance().getCropFromId(cropID);
		int percentGrowth = (int) Math.min((growth / (crop.numGrowthStages - 1)) * 100, 100);
		
		if (percentGrowth < 100)
			currenttip.add(Helper.translate("gui.growth") + " : " + percentGrowth + "%");
		else
			currenttip.add(Helper.translate("gui.growth") + " : " + Helper.translate("gui.mature"));
		
		return currenttip;
	}

	public List<String> fruitLeavesHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor,
			IWailaConfigHandler config)
	{
		NBTTagCompound tag = accessor.getNBTData();
		boolean hasFruit = tag.getBoolean("hasFruit");
		String type;
		type = BlockCWTFCFruitLeaves.getType(accessor.getBlock(), accessor.getMetadata());
		if (!hasFruit)
			currenttip.set(0, EnumChatFormatting.WHITE.toString() + TFC_Core.translate("gui." + type));

		return currenttip;
	}

	public ItemStack fruitLeavesStack(IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		TileEntity te = accessor.getTileEntity();
		if (te instanceof TECWTFCFruitLeaves)
		{

			FloraIndex index = FloraManager.getInstance()
					.findMatchingIndex(BlockCWTFCFruitLeaves.getType(accessor.getBlock(), ((TECWTFCFruitLeaves)te).fruitType));

			if (index != null)
			{
				return ItemFoodTFC.createTag(index.getOutput());
			}
		}
		return null;
	}

	public List<String> fruitLeavesBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor,
			IWailaConfigHandler config)
	{
		FloraIndex index = FloraManager.getInstance()
				.findMatchingIndex(BlockCWTFCFruitLeaves.getType(accessor.getBlock(), accessor.getMetadata()));
		if (index != null)
			currenttip.add(TFC_Time.SEASONS[index.harvestStart] + " - " + TFC_Time.SEASONS[index.harvestFinish]);
		return currenttip;
	}
	
	public List<String> fruitTreeHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		String type;
		NBTTagCompound tag = accessor.getNBTData();
		type = BlockCWTFCFruitWood.getType( accessor.getMetadata());

		currenttip.set(0, EnumChatFormatting.WHITE.toString() + TFC_Core.translate("gui." + type));

		return currenttip;
	}
	
	public ItemStack fruitTreeStack(IWailaDataAccessor accessor, IWailaConfigHandler config)
	{
		FloraIndex index = FloraManager.getInstance().findMatchingIndex(BlockCWTFCFruitWood.getType(accessor.getMetadata()));

		if (index != null)
			return ItemFoodTFC.createTag(index.getOutput());
		else
			return null;
	}
}
