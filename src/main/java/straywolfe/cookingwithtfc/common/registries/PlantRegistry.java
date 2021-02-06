package straywolfe.cookingwithtfc.common.registries;

import com.dunk.tfc.Core.TFC_Time;
import com.dunk.tfc.Food.FloraIndex;
import com.dunk.tfc.Food.FloraManager;
import com.dunk.tfc.api.TFCItems;
import com.dunk.tfc.api.Constant.Global;
import com.dunk.tfc.api.Enums.EnumRegion;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import straywolfe.cookingwithtfc.api.CWTFCItems;
import straywolfe.cookingwithtfc.api.managers.CWTFCCropIndex;
import straywolfe.cookingwithtfc.api.managers.CropManager;
import straywolfe.cookingwithtfc.common.lib.Constants;
import straywolfe.cookingwithtfc.common.lib.Settings;

public class PlantRegistry 
{
	public static final int CELERY = 54;
	public static final int LETTUCE = 55;
	public static final int PEANUT = 56;
	
	public static void registerCrops()
	{		
		CropManager cropmanager = CropManager.getInstance();
		
		if(!Settings.disableCelery)
			cropmanager.addWildCrop(new CWTFCCropIndex(/*ID*/CELERY, /*Name*/"Celery", /*type*/2, /*time*/39, /*stages*/7, /*minGTemp*/18, /*minATemp*/12, /*nutrientUsage*/0.75f, CWTFCItems.seedsCelery, new EnumRegion[]{EnumRegion.EUROPE,EnumRegion.ASIA},/*Minimum Rainfall*/500,/*Maximum Rainfall*/6000,/*Minimum Natural BioTemp*/4,/*Maximum Natural Bio Temp*/25).setOutput1(CWTFCItems.celery, 16f).setWaterUsage(1.5f));
		
		if(!Settings.disableLettuce)
			cropmanager.addWildCrop(new CWTFCCropIndex(/*ID*/LETTUCE, /*Name*/"Lettuce", /*type*/1, /*time*/25, /*stages*/6, /*minGTemp*/4, /*minATemp*/0, /*nutrientUsage*/1.1f, CWTFCItems.seedsLettuce, new EnumRegion[]{EnumRegion.EUROPE,EnumRegion.ASIA},/*Minimum Rainfall*/250,/*Maximum Rainfall*/4000,/*Minimum Natural BioTemp*/8,/*Maximum Natural Bio Temp*/29).setOutput1(CWTFCItems.lettuce, 17f).setWaterUsage(0.9f));
		
		if(!Settings.disablePeanut)
			cropmanager.addWildCrop(new CWTFCCropIndex(/*ID*/PEANUT, /*Name*/"Peanut", /*type*/0, /*time*/32, /*stages*/6, /*minGTemp*/8, /*minATemp*/0, /*nutrientUsage*/0.9f, CWTFCItems.seedsPeanut, new EnumRegion[]{EnumRegion.AMERICAS,EnumRegion.AFRICA},/*Minimum Rainfall*/80,/*Maximum Rainfall*/600,/*Minimum Natural BioTemp*/14,/*Maximum Natural Bio Temp*/36).setOutput1(CWTFCItems.peanut, 8f).setGoesDormant(true).setWaterUsage(0.75f));
	}
	
	public static void registerFruitTrees()
	{
		FloraManager manager = FloraManager.getInstance();
		
		manager.addIndex(new FloraIndex(Constants.FRUITTREETYPES[0], // Almond
				TFC_Time.MARCH, TFC_Time.APRIL,
				TFC_Time.MAY, TFC_Time.SEPTEMBER,
				new ItemStack(CWTFCItems.fruitTreeSapling, 1, 0),
				new ItemStack(CWTFCItems.almond)));
		manager.addIndex(new FloraIndex(Constants.FRUITTREETYPES[1], // Cashew
				TFC_Time.MARCH, TFC_Time.JULY,
				TFC_Time.AUGUST, TFC_Time.SEPTEMBER,
				new ItemStack(CWTFCItems.fruitTreeSapling, 1, 1),
				new ItemStack(CWTFCItems.cashew)));
		manager.addIndex(new FloraIndex(Constants.FRUITTREETYPES[2], // Coconut
				TFC_Time.MARCH, TFC_Time.APRIL,
				TFC_Time.JULY, TFC_Time.SEPTEMBER,
				new ItemStack(CWTFCItems.fruitTreeSapling, 1, 2),
				new ItemStack(CWTFCItems.coconut)));
		manager.addIndex(new FloraIndex(Constants.FRUITTREETYPES[3], // Hazelnut
				TFC_Time.MARCH, TFC_Time.APRIL,
				TFC_Time.JULY, TFC_Time.SEPTEMBER,
				new ItemStack(CWTFCItems.fruitTreeSapling, 1, 3),
				new ItemStack(CWTFCItems.hazelnut)));
		manager.addIndex(new FloraIndex(Constants.FRUITTREETYPES[4], // Macadamia
				TFC_Time.MARCH, TFC_Time.APRIL,
				TFC_Time.JULY, TFC_Time.SEPTEMBER,
				new ItemStack(CWTFCItems.fruitTreeSapling, 1, 4),
				new ItemStack(CWTFCItems.macadamia)));
		manager.addIndex(new FloraIndex(Constants.FRUITTREETYPES[5], // Pistachio
				TFC_Time.MARCH, TFC_Time.APRIL,
				TFC_Time.JULY, TFC_Time.SEPTEMBER,
				new ItemStack(CWTFCItems.fruitTreeSapling, 1, 5),
				new ItemStack(CWTFCItems.pistachio)));

	}
}
