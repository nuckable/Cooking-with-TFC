package straywolfe.cookingwithtfc.common.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dunk.tfc.TerraFirmaCraft;
import com.dunk.tfc.Blocks.Flora.BlockBranch;
import com.dunk.tfc.Blocks.Flora.BlockBranch2;
import com.dunk.tfc.Blocks.Flora.BlockLogNatural;
import com.dunk.tfc.Blocks.Flora.BlockLogNatural2;
import com.dunk.tfc.Core.TFC_Core;
import com.dunk.tfc.Core.Player.FoodStatsTFC;
import com.dunk.tfc.api.Food;
import com.dunk.tfc.api.FoodRegistry;
import com.dunk.tfc.api.Interfaces.IFood;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import straywolfe.cookingwithtfc.api.managers.ChunkDataManager;
import straywolfe.cookingwithtfc.common.block.BlockCWTFCBranch;
import straywolfe.cookingwithtfc.common.block.BlockCWTFCFruitLog;
import straywolfe.cookingwithtfc.common.block.BlockCWTFCFruitTreeBranch;
import straywolfe.cookingwithtfc.common.block.BlockCWTFCLog;
import straywolfe.cookingwithtfc.common.core.helper.Helper;
import straywolfe.cookingwithtfc.common.handlers.MessageFoodRecord;
import straywolfe.cookingwithtfc.common.item.ItemTFCMealTransform;
import straywolfe.cookingwithtfc.common.lib.Settings;

public class CWTFC_Core 
{	
	private static Map<Integer, ChunkDataManager> CDMMap = new HashMap<Integer, ChunkDataManager>();
	public static Block[][][][] branchMap = new Block[3][3][3][4];
	
	public static ChunkDataManager getCDM(World world)
	{
		int key = world.isRemote ? 128 | world.provider.dimensionId : world.provider.dimensionId;
		return CDMMap.get(key);
	}
	
	public static ChunkDataManager addCDM(World world)
	{
		int key = world.isRemote ? 128 | world.provider.dimensionId : world.provider.dimensionId;
		if(!CDMMap.containsKey(key))
			return CDMMap.put(key, new ChunkDataManager(world));
		else return CDMMap.get(key);
	}

	public static ChunkDataManager removeCDM(World world)
	{
		int key = world.isRemote ? 128 | world.provider.dimensionId : world.provider.dimensionId;
		return CDMMap.remove(key);
	}
	
	public static FoodRecord getPlayerFoodRecord(EntityPlayer player)
	{
		FoodRecord foodrecord = new FoodRecord(player);
		foodrecord.readNBT(player.getEntityData());
		return foodrecord;		
	}

	public static void setPlayerFoodRecord(EntityPlayer player, FoodRecord foodrecord)
	{
		foodrecord.writeNBT(player.getEntityData());
	}
	
	public static float getFoodsCount(FoodRecord foodrecord, ItemStack is)
	{
		int repeatFoods = 0;
		for(int i = 0; i < foodrecord.RecordSize; ++i)
		{
			if(is.getUnlocalizedName().equals(foodrecord.FoodsEaten[i]))
				repeatFoods++;
		}
		
		float recordSizeMod = foodrecord.RecordSize/Settings.pickiness;
		return Math.max(recordSizeMod - repeatFoods, 0)/recordSizeMod;
	}
	
	public static void getFoodUse(ItemStack is, EntityPlayer player, List<String> arraylist)
	{
		FoodRecord foodrecord = CWTFC_Core.getPlayerFoodRecord(player);
		
		double pctConsume = Math.floor(100 * getFoodsCount(foodrecord, is));
		
		if(pctConsume >= 80)
			arraylist.add(EnumChatFormatting.DARK_GRAY + "Usefulness: " + EnumChatFormatting.GREEN + pctConsume + "%");
		else if(pctConsume >= 50 && pctConsume < 80)
			arraylist.add(EnumChatFormatting.DARK_GRAY + "Usefulness: " + EnumChatFormatting.YELLOW + pctConsume + "%");
		else if(pctConsume < 50)
			arraylist.add(EnumChatFormatting.DARK_GRAY + "Usefulness: " + EnumChatFormatting.RED + pctConsume + "%");
	}
	
	public static ItemStack processRightClick(ItemStack is, EntityPlayer player, float ConsumeSize, boolean edible)
	{
		FoodStatsTFC foodstats = TFC_Core.getPlayerFoodStats(player);
		FoodRecord foodrecord = CWTFC_Core.getPlayerFoodRecord(player);
		
		float deminEat = ConsumeSize * getFoodsCount(foodrecord, is);
		
		if (foodstats.needFood() && edible && deminEat != 0)
			player.setItemInUse(is, 32);
		else if(edible && foodstats.stomachLevel <= 0)
			player.setItemInUse(is, 32);
		else if(deminEat == 0)
		{
			ChatComponentText text = new ChatComponentText("You have grown tired of this food and cannot eat it.");
			text.getChatStyle().setColor(EnumChatFormatting.DARK_RED).setItalic(true);
			player.addChatComponentMessage(text);
		}

		return is;
	}
	
	public static ItemStack processEating(ItemStack is, World world, EntityPlayer player, float consumeSize, boolean isMeal)
	{
		FoodStatsTFC foodstats = TFC_Core.getPlayerFoodStats(player);
		
		if(!world.isRemote)
		{
			if(is.hasTagCompound())
			{			
				float weight = Food.getWeight(is);
				float decay = Math.max(Food.getDecay(is), 0);
				float eatAmount;
				float tasteFactor = foodstats.getTasteFactor(is);
	
				if(Settings.diminishingReturns)
				{
					FoodRecord foodrecord = CWTFC_Core.getPlayerFoodRecord(player);
					float deminEat = consumeSize * getFoodsCount(foodrecord, is);
					
					if(foodstats.stomachLevel <= 0 && deminEat <= 0)
					{					
						foodstats.nutrFruit = foodstats.nutrFruit * 0.9F;
						foodstats.nutrVeg = foodstats.nutrVeg * 0.9F;
						foodstats.nutrGrain = foodstats.nutrGrain * 0.9F;
						foodstats.nutrProtein =foodstats.nutrProtein * 0.9F;
						foodstats.nutrDairy = foodstats.nutrDairy * 0.9F;
						
						eatAmount = Math.min(weight - decay, consumeSize);
					}
					else
						eatAmount = Math.min(weight - decay, deminEat);
					
					float stomachDiff = foodstats.stomachLevel+eatAmount-foodstats.getMaxStomach(foodstats.player);
					if(stomachDiff > 0)
						eatAmount-=stomachDiff;
					
					if(isMeal)
					{
						int[] fg = Food.getFoodGroups(is);
						float[] foodAmounts = ((ItemTFCMealTransform)is.getItem()).getFoodPct(is);
						float[]foodWts = new float[foodAmounts.length];
						float totalWeight = 0;
						
						for(int i = 0; i < foodAmounts.length; i++)
							foodWts[i] = foodAmounts[i] * Food.getWeight(is);
						
						for(int i = 0; i < fg.length; i++)
						{
							if(fg[i] != -1)
								totalWeight += foodWts[i];
						}
						
						for (int i = 0; i < fg.length; i++ )
						{
							if (fg[i] != -1)
							{
								foodstats.addNutrition(FoodRegistry.getInstance().getFoodGroup(fg[i]), 
										eatAmount * foodWts[i]/totalWeight * 2.5f);
							}
						}
						
						foodstats.setSatisfaction(foodstats.getSatisfaction() + ((eatAmount / 3f) * tasteFactor), fg);
					}
					else
						foodstats.addNutrition(((IFood)(is.getItem())).getFoodGroup(), eatAmount*tasteFactor);	
					
					foodstats.stomachLevel += eatAmount * tasteFactor;
					
					if(FoodStatsTFC.reduceFood(is, eatAmount))
						is.stackSize = 0;
				
					foodrecord.FoodsEaten[foodrecord.FoodListRef] = is.getUnlocalizedName();
					
					if(foodrecord.FoodListRef == foodrecord.RecordSize - 1)
						foodrecord.FoodListRef = 0;
					else
						foodrecord.FoodListRef++;
					
					CWTFC_Core.setPlayerFoodRecord(player,foodrecord);
					
					if(player instanceof EntityPlayerMP)			
						TerraFirmaCraft.PACKET_PIPELINE.sendTo(new MessageFoodRecord(player, foodrecord), (EntityPlayerMP) player);
				}
				else
				{					
					eatAmount = Math.min(weight - decay, consumeSize);
					
					float stomachDiff = foodstats.stomachLevel+eatAmount-foodstats.getMaxStomach(foodstats.player);
					if(stomachDiff > 0)
						eatAmount-=stomachDiff;
					
					if(isMeal)
					{
						int[] fg = Food.getFoodGroups(is);
						float[] foodAmounts = ((ItemTFCMealTransform)is.getItem()).getFoodPct(is);
						float[]foodWts = new float[foodAmounts.length];
						float totalWeight = 0;
						
						for(int i = 0; i < foodAmounts.length; i++)
							foodWts[i] = foodAmounts[i] * Food.getWeight(is);
						
						for(int i = 0; i < fg.length; i++)
						{
							if(fg[i] != -1)
								totalWeight += foodWts[i];
						}
						
						for (int i = 0; i < fg.length; i++ )
						{
							if (fg[i] != -1)
							{
								foodstats.addNutrition(FoodRegistry.getInstance().getFoodGroup(fg[i]), 
										eatAmount * foodWts[i]/totalWeight * 2.5f);
							}
						}
						
						foodstats.setSatisfaction(foodstats.getSatisfaction() + ((eatAmount / 3f) * tasteFactor), fg);
					}
					else
						foodstats.addNutrition(((IFood)(is.getItem())).getFoodGroup(), eatAmount*tasteFactor);
					
					foodstats.stomachLevel += eatAmount * tasteFactor;
					
					if(FoodStatsTFC.reduceFood(is, eatAmount))
						is.stackSize = 0;
				}
			}
			else
				Helper.postNBTError(player, is);
		}

		world.playSoundAtEntity(player, "random.burp", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
		TFC_Core.setPlayerFoodStats(player, foodstats);

		return is;
	}
	
	//Given an initial branch, return the branch that would be in that direction
	public static Block getSourcedBranchForBranch(Block initialBranch, int x, int y, int z)
	{
		//If the branch given wasn't even a branch, we just return null.
		if(!(initialBranch instanceof BlockCWTFCBranch|| initialBranch instanceof BlockCWTFCLog) || x < -1 || x > 1 || y < -1 || y > 1 || z < -1 || z > 1)
		{
			return null;
		}
		return branchMap[2-(x+1)][2-(y+1)][2-(z+1)][0];
	}
	
	//Given an initial branch, return the branch that would be in that direction
	public static Block getSourcedTerminalBranchForBranch(Block initialBranch, int x, int y, int z)
	{
		//If the branch given wasn't even a branch, we just return null.
		if(!(initialBranch instanceof BlockCWTFCBranch || initialBranch instanceof BlockCWTFCLog) || x < -1 || x > 1 || y < -1 || y > 1 || z < -1 || z > 1)
		{
			return null;
		}
		return branchMap[2-(x+1)][2-(y+1)][2-(z+1)][2];
	}
	
	//Given an initial branch, return the branch that would be in that direction
	public static Block getSourcedFruitBranchForBranch(Block initialBranch, int x, int y, int z)
	{
		//If the branch given wasn't even a branch, we just return null.
		if(!(initialBranch instanceof BlockCWTFCFruitTreeBranch || initialBranch instanceof BlockCWTFCFruitLog) || x < -1 || x > 1 || y < -1 || y > 1 || z < -1 || z > 1)
		{
			return null;
		}
		return branchMap[2-(x+1)][2-(y+1)][2-(z+1)][1];
	}
	
	//Given an initial branch, return the branch that would be in that direction
	public static Block getSourcedTerminalFruitBranchForBranch(Block initialBranch, int x, int y, int z)
	{
		//If the branch given wasn't even a branch, we just return null.
		if(!(initialBranch instanceof BlockCWTFCFruitTreeBranch || initialBranch instanceof BlockCWTFCFruitLog) || x < -1 || x > 1 || y < -1 || y > 1 || z < -1 || z > 1)
		{
			return null;
		}
		return branchMap[2-(x+1)][2-(y+1)][2-(z+1)][3];
	}
}
