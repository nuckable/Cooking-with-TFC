package straywolfe.cookingwithtfc.common;

import com.dunk.tfc.Food.ItemFoodTFC;
import com.dunk.tfc.api.TFCItems;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import straywolfe.cookingwithtfc.api.CWTFCFluids;
import straywolfe.cookingwithtfc.api.CWTFCItems;
import straywolfe.cookingwithtfc.common.tileentity.TECWTFCFruitLeaves;
import straywolfe.cookingwithtfc.common.tileentity.TECWTFCFruitTreeWood;
import straywolfe.cookingwithtfc.common.tileentity.TileBowl;
import straywolfe.cookingwithtfc.common.tileentity.TileClayOven;
import straywolfe.cookingwithtfc.common.tileentity.TileCookingPot;
import straywolfe.cookingwithtfc.common.tileentity.TileCrop;
import straywolfe.cookingwithtfc.common.tileentity.TileGrains;
import straywolfe.cookingwithtfc.common.tileentity.TileHopperCWTFC;
import straywolfe.cookingwithtfc.common.tileentity.TileLumberConstruct;
import straywolfe.cookingwithtfc.common.tileentity.TileMeat;
import straywolfe.cookingwithtfc.common.tileentity.TileMixBowl;
import straywolfe.cookingwithtfc.common.tileentity.TileSandwich;
import straywolfe.cookingwithtfc.common.tileentity.TileTableStorage;
import straywolfe.cookingwithtfc.common.worldgen.WorldGenCWTFCPlants;
import straywolfe.cookingwithtfc.common.worldgen.WorldGenCrops;
import straywolfe.cookingwithtfc.common.worldgen.WorldGenTrees;

public class CommonProxyCWTFC 
{
	public void registerTileEntities(boolean clientReg)
	{		
		GameRegistry.registerTileEntity(TileGrains.class, "TileGrains");
		GameRegistry.registerTileEntity(TileMixBowl.class, "TileMixBowl");
		GameRegistry.registerTileEntity(TileHopperCWTFC.class, "TileHopperCWTFC");
		GameRegistry.registerTileEntity(TileBowl.class, "TileBowl");
		GameRegistry.registerTileEntity(TileMeat.class, "TileMeat");
		GameRegistry.registerTileEntity(TileCookingPot.class, "TileCookingPot");
		GameRegistry.registerTileEntity(TileSandwich.class, "TileSandwich");
		GameRegistry.registerTileEntity(TileTableStorage.class, "TileTableStorage");
		GameRegistry.registerTileEntity(TileCrop.class, "TileCrop");
		GameRegistry.registerTileEntity(TECWTFCFruitTreeWood.class, "TileFruitTree");
		GameRegistry.registerTileEntity(TECWTFCFruitLeaves.class, "TileFruitLeaves");
		GameRegistry.registerTileEntity(TileLumberConstruct.class, "TileLumberConstruct");
		
		if(clientReg)
		{
			GameRegistry.registerTileEntity(TileClayOven.class, "TileClayOven");
		}
	}
	
	void registerFluids()
	{
		FluidRegistry.registerFluid(CWTFCFluids.BROTH);
		FluidRegistry.registerFluid(CWTFCFluids.VEGETABLESOUP);
		FluidRegistry.registerFluid(CWTFCFluids.TOMATOSOUP);
		FluidRegistry.registerFluid(CWTFCFluids.CHICKENSOUP);
		FluidRegistry.registerFluid(CWTFCFluids.BEEFSTEW);
		FluidRegistry.registerFluid(CWTFCFluids.VENISONSTEW);
		FluidRegistry.registerFluid(CWTFCFluids.FISHCHOWDER);
	}
	
	void setupFluids()
	{
		FluidContainerRegistry.registerFluidContainer(new FluidStack(CWTFCFluids.BROTH, 1000), ItemFoodTFC.createTag(new ItemStack(CWTFCItems.Broth), 20, 0), new ItemStack(TFCItems.potteryJug, 1, 1));
		FluidContainerRegistry.registerFluidContainer(new FluidStack(CWTFCFluids.VEGETABLESOUP, 250), ItemFoodTFC.createTag(new ItemStack(CWTFCItems.VegetableSoup), 10, 0), new ItemStack(TFCItems.potteryBowl, 1, 1));
		FluidContainerRegistry.registerFluidContainer(new FluidStack(CWTFCFluids.TOMATOSOUP, 250), ItemFoodTFC.createTag(new ItemStack(CWTFCItems.TomatoSoup), 10, 0), new ItemStack(TFCItems.potteryBowl, 1, 1));		
		FluidContainerRegistry.registerFluidContainer(new FluidStack(CWTFCFluids.CHICKENSOUP, 250), ItemFoodTFC.createTag(new ItemStack(CWTFCItems.ChickenSoup), 10, 0), new ItemStack(TFCItems.potteryBowl, 1, 1));
		FluidContainerRegistry.registerFluidContainer(new FluidStack(CWTFCFluids.BEEFSTEW, 250), ItemFoodTFC.createTag(new ItemStack(CWTFCItems.BeefStew), 10, 0), new ItemStack(TFCItems.potteryBowl, 1, 1));
		FluidContainerRegistry.registerFluidContainer(new FluidStack(CWTFCFluids.VENISONSTEW, 250), ItemFoodTFC.createTag(new ItemStack(CWTFCItems.VenisonStew), 10, 0), new ItemStack(TFCItems.potteryBowl, 1, 1));
		FluidContainerRegistry.registerFluidContainer(new FluidStack(CWTFCFluids.FISHCHOWDER, 250), ItemFoodTFC.createTag(new ItemStack(CWTFCItems.FishChowder), 10, 0), new ItemStack(TFCItems.potteryBowl, 1, 1));
	}
	
	void registerWAILA()
	{
		FMLInterModComms.sendMessage("Waila", "register", "straywolfe.cookingwithtfc.client.waila.WAILAInfo.callbackRegister");
	}
	
	public World getCurrentWorld()
	{
		return MinecraftServer.getServer().getEntityWorld();
	}
	
	void registerWorldGen()
	{
		GameRegistry.registerWorldGenerator(new WorldGenTrees(), 9);
		GameRegistry.registerWorldGenerator(new WorldGenCrops(), 10);
		GameRegistry.registerWorldGenerator(new WorldGenCWTFCPlants(), 11);
	}
	
	public void registerRenderInformation()
	{
		//Client-side only
	}
	
	public int foliageColorMultiplier(IBlockAccess par1IBlockAccess, int i, int j, int k)
	{
		//Client-side only
		return 0;
	}
	
	public void registerClientHandlers()
	{
		//Client-side only
	}
}
