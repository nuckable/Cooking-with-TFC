package straywolfe.cookingwithtfc.api;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import straywolfe.cookingwithtfc.common.block.BlockBowl;
import straywolfe.cookingwithtfc.common.block.BlockCWTFCBranch;
import straywolfe.cookingwithtfc.common.block.BlockCWTFCFruitLeaves;
import straywolfe.cookingwithtfc.common.block.BlockCWTFCFruitLog;
import straywolfe.cookingwithtfc.common.block.BlockCWTFCFruitTreeBranch;
import straywolfe.cookingwithtfc.common.block.BlockCWTFCLeaves;
import straywolfe.cookingwithtfc.common.block.BlockCWTFCLog;
import straywolfe.cookingwithtfc.common.block.BlockCWTFCSapling;
import straywolfe.cookingwithtfc.common.block.BlockClayOven;
import straywolfe.cookingwithtfc.common.block.BlockCookingPot;
import straywolfe.cookingwithtfc.common.block.BlockCrop;
import straywolfe.cookingwithtfc.common.block.BlockGrains;
import straywolfe.cookingwithtfc.common.block.BlockHopperCWTFC;
import straywolfe.cookingwithtfc.common.block.BlockHorzEW;
import straywolfe.cookingwithtfc.common.block.BlockHorzNS;
import straywolfe.cookingwithtfc.common.block.BlockLumberConstruct;
import straywolfe.cookingwithtfc.common.block.BlockMeat;
import straywolfe.cookingwithtfc.common.block.BlockMixBowl;
import straywolfe.cookingwithtfc.common.block.BlockPlank;
import straywolfe.cookingwithtfc.common.block.BlockPrepTable;
import straywolfe.cookingwithtfc.common.block.BlockPrepTable2;
import straywolfe.cookingwithtfc.common.block.BlockSandwich;
import straywolfe.cookingwithtfc.common.block.BlockTableStorage;
import straywolfe.cookingwithtfc.common.block.BlockVertLog;
import straywolfe.cookingwithtfc.common.core.CWTFC_Core;
import straywolfe.cookingwithtfc.common.item.itemblock.ItemCWTFCBranch;
import straywolfe.cookingwithtfc.common.item.itemblock.ItemCWTFCFruitTreeBranch;
import straywolfe.cookingwithtfc.common.item.itemblock.ItemCWTFCSapling;
import straywolfe.cookingwithtfc.common.item.itemblock.ItemCustomWood;
import straywolfe.cookingwithtfc.common.item.itemblock.ItemMixingBowl;
import straywolfe.cookingwithtfc.common.item.itemblock.ItemPrepTable;
import straywolfe.cookingwithtfc.common.item.itemblock.ItemPrepTable2;

public class CWTFCBlocks 
{
	public static Block GrainsBlock;
	public static Block mixingBowl;
	public static Block hopperCWTFC;
	public static Block cookingPot;
	public static Block meatCWTFC;
	public static Block sandwichCWTFC;
	public static Block bowlCWTFC;
	public static Block clayOven;
	public static Block tableStorage;
	
	public static Block prepTableN;
	public static Block prepTable2N;
	public static Block prepTableS;
	public static Block prepTable2S;
	public static Block prepTableE;
	public static Block prepTable2E;
	public static Block prepTableW;
	public static Block prepTable2W;
	
	public static Block customCrop;
	public static Block treeSapling;
	public static Block fruitTreeSapling;
	
	public static Block woodVert;
	public static Block woodHorizNS;
	public static Block woodHorizEW;
	
	public static Block treeLog;
	public static Block treeLeaves;
	
	public static Block fruitTreeLeaves;
	public static Block fruitTreeWood;
	public static Block fruitTreeLog;
	
	public static Block woodPlank;
	public static Block lumberConstruct;
	public static Block logNaturalCWTFC; /*TODO ??? */
	
	public static Block branch_xyz;
	public static Block branch_xyZ;
	public static Block branch_xYz;
	public static Block branch_xYZ;
	public static Block branch_Xyz;
	public static Block branch_XyZ;
	public static Block branch_XYz;
	public static Block branch_XYZ;
	public static Block branch_x_z;
	public static Block branch_x_Z;
	public static Block branch_X_z;
	public static Block branch_X_Z;
	public static Block branch__Yz;
	public static Block branch__YZ;
	public static Block branch__yz;
	public static Block branch__yZ;
	public static Block branch___z;
	public static Block branch___Z;
	public static Block branch_x__;
	public static Block branch_X__;
	public static Block branch_xy_;
	public static Block branch_Xy_;
	public static Block branch_xY_;
	public static Block branch_XY_;
	public static Block branch__y_;
	// Terminal branches
	public static Block branchEnd_xyz;
	public static Block branchEnd_xyZ;
	public static Block branchEnd_xYz;
	public static Block branchEnd_xYZ;
	public static Block branchEnd_Xyz;
	public static Block branchEnd_XyZ;
	public static Block branchEnd_XYz;
	public static Block branchEnd_XYZ;
	public static Block branchEnd_x_z;
	public static Block branchEnd_x_Z;
	public static Block branchEnd_X_z;
	public static Block branchEnd_X_Z;
	public static Block branchEnd__Yz;
	public static Block branchEnd__YZ;
	public static Block branchEnd__yz;
	public static Block branchEnd__yZ;
	public static Block branchEnd___z;
	public static Block branchEnd___Z;
	public static Block branchEnd_x__;
	public static Block branchEnd_X__;
	public static Block branchEnd_xy_;
	public static Block branchEnd_Xy_;
	public static Block branchEnd_xY_;
	public static Block branchEnd_XY_;
	public static Block branchEnd__y_;
	
	public static Block fruitTreeBranch_xyz;
	public static Block fruitTreeBranch_xyZ;
	public static Block fruitTreeBranch_xYz;
	public static Block fruitTreeBranch_xYZ;
	public static Block fruitTreeBranch_Xyz;
	public static Block fruitTreeBranch_XyZ;
	public static Block fruitTreeBranch_XYz;
	public static Block fruitTreeBranch_XYZ;
	public static Block fruitTreeBranch_x_z;
	public static Block fruitTreeBranch_x_Z;
	public static Block fruitTreeBranch_X_z;
	public static Block fruitTreeBranch_X_Z;
	public static Block fruitTreeBranch__Yz;
	public static Block fruitTreeBranch__YZ;
	public static Block fruitTreeBranch__yz;
	public static Block fruitTreeBranch__yZ;
	public static Block fruitTreeBranch___z;
	public static Block fruitTreeBranch___Z;
	public static Block fruitTreeBranch_x__;
	public static Block fruitTreeBranch_X__;
	public static Block fruitTreeBranch_xy_;
	public static Block fruitTreeBranch_Xy_;
	public static Block fruitTreeBranch_xY_;
	public static Block fruitTreeBranch_XY_;
	public static Block fruitTreeBranch__y_;
	// Terminal branches
	public static Block fruitTreeBranchEnd_xyz;
	public static Block fruitTreeBranchEnd_xyZ;
	public static Block fruitTreeBranchEnd_xYz;
	public static Block fruitTreeBranchEnd_xYZ;
	public static Block fruitTreeBranchEnd_Xyz;
	public static Block fruitTreeBranchEnd_XyZ;
	public static Block fruitTreeBranchEnd_XYz;
	public static Block fruitTreeBranchEnd_XYZ;
	public static Block fruitTreeBranchEnd_x_z;
	public static Block fruitTreeBranchEnd_x_Z;
	public static Block fruitTreeBranchEnd_X_z;
	public static Block fruitTreeBranchEnd_X_Z;
	public static Block fruitTreeBranchEnd__Yz;
	public static Block fruitTreeBranchEnd__YZ;
	public static Block fruitTreeBranchEnd__yz;
	public static Block fruitTreeBranchEnd__yZ;
	public static Block fruitTreeBranchEnd___z;
	public static Block fruitTreeBranchEnd___Z;
	public static Block fruitTreeBranchEnd_x__;
	public static Block fruitTreeBranchEnd_X__;
	public static Block fruitTreeBranchEnd_xy_;
	public static Block fruitTreeBranchEnd_Xy_;
	public static Block fruitTreeBranchEnd_xY_;
	public static Block fruitTreeBranchEnd_XY_;
	public static Block fruitTreeBranchEnd__y_;

	public static int mixingBowlRenderID;
	public static int prepTableRenderID;
	public static int meatRenderID;
	public static int cookingPotRenderID;
	public static int bowlRenderID;
	public static int clayOvenRenderID;
	public static int sandwichRenderID;
	public static int tableStorageRenderID;
	public static int gourdRenderID;
	public static int gourdCropRenderID;
	public static int customCropRenderID;
	public static int lumberConstructRenderID;
	
	public static int branchRenderID;
	public static int leavesRenderID;
	public static int woodFruitRenderId;
	public static int fruitTreeBranchRenderID;
	public static int fruitTreeLeavesRenderID;
	
	public static void setup()
	{
		loadBlocks();
		
		registerBlocks();
		
		setupFire();
	}
	
	public static void loadBlocks()
	{
		GrainsBlock = new BlockGrains();
		mixingBowl = new BlockMixBowl();
		hopperCWTFC = new BlockHopperCWTFC();
		cookingPot = new BlockCookingPot();
		meatCWTFC = new BlockMeat();
		sandwichCWTFC = new BlockSandwich();
		bowlCWTFC = new BlockBowl();
		clayOven = new BlockClayOven();
		tableStorage = new BlockTableStorage();
		
		prepTableN = new BlockPrepTable();
		prepTable2N = new BlockPrepTable2();
		prepTableS = new BlockPrepTable();
		prepTable2S = new BlockPrepTable2();
		prepTableE = new BlockPrepTable();
		prepTable2E = new BlockPrepTable2();
		prepTableW = new BlockPrepTable();
		prepTable2W = new BlockPrepTable2();
		
		customCrop = new BlockCrop();

		treeLeaves = new BlockCWTFCLeaves().setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundTypeGrass)
				.setBlockName("leaves");
		treeLog = new BlockCWTFCLog();
		treeSapling = new BlockCWTFCSapling();
		//fruitTreeSapling = new BlockCWTFCFruitSapling();
		woodVert = new BlockVertLog();
		woodHorizNS = new BlockHorzNS();
		woodHorizEW = new BlockHorzEW();
		fruitTreeWood = new BlockCWTFCFruitTreeBranch().setBlockName("fruitTreeWood").setHardness(5.5F).setResistance(2F);
		fruitTreeLeaves = new BlockCWTFCFruitLeaves().setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundTypeGrass).setBlockName("fruitTreeLeaves");
		fruitTreeLog = new BlockCWTFCFruitLog();
		
		woodPlank = new BlockPlank();
		lumberConstruct = new BlockLumberConstruct();
	}
	
	public static void registerBlocks()
	{
		GameRegistry.registerBlock(GrainsBlock, "GrainsBlock");
		GameRegistry.registerBlock(mixingBowl, ItemMixingBowl.class, "MixingBowl");
		GameRegistry.registerBlock(hopperCWTFC, "Hopper");
		GameRegistry.registerBlock(cookingPot, "CookingPot");
		GameRegistry.registerBlock(meatCWTFC, "meatCWTFC");
		GameRegistry.registerBlock(sandwichCWTFC, "sandwichCWTFC");
		GameRegistry.registerBlock(bowlCWTFC, "bowlCWTFC");
		GameRegistry.registerBlock(clayOven, "clayOven");
		GameRegistry.registerBlock(tableStorage, "tableStorage");
		GameRegistry.registerBlock(customCrop, "customCrop");
		GameRegistry.registerBlock(treeLeaves, ItemCustomWood.class, "customLeaves");
		GameRegistry.registerBlock(treeLog, ItemCustomWood.class, "naturalLog");
		GameRegistry.registerBlock(woodVert, ItemCustomWood.class, "WoodVert");
		GameRegistry.registerBlock(woodHorizNS, ItemCustomWood.class, "woodHorizNS");
		GameRegistry.registerBlock(woodHorizEW, ItemCustomWood.class, "woodHorizEW");
		GameRegistry.registerBlock(treeSapling, ItemCWTFCSapling.class, "treeSapling");
		GameRegistry.registerBlock(fruitTreeWood, ItemCWTFCFruitTreeBranch.class, "fruitTreeWood");
		GameRegistry.registerBlock(fruitTreeLeaves, ItemCWTFCFruitTreeBranch.class, "fruitTreeLeaves");
		//GameRegistry.registerBlock(fruitTreeSapling, ItemCWTFCFruitTreeSapling.class, "fruitTreeSapling");
		
		GameRegistry.registerBlock(woodPlank, ItemCustomWood.class, "woodPlank");
		GameRegistry.registerBlock(lumberConstruct, "lumberConstruct");
		
		GameRegistry.registerBlock(prepTableN, ItemPrepTable.class, "PrepTableN");
		GameRegistry.registerBlock(prepTable2N, ItemPrepTable2.class, "PrepTable2N");
		GameRegistry.registerBlock(prepTableS, ItemPrepTable.class, "PrepTableS");
		GameRegistry.registerBlock(prepTable2S, ItemPrepTable2.class, "PrepTable2S");
		GameRegistry.registerBlock(prepTableE, ItemPrepTable.class, "PrepTableE");
		GameRegistry.registerBlock(prepTable2E, ItemPrepTable2.class, "PrepTable2E");
		GameRegistry.registerBlock(prepTableW, ItemPrepTable.class, "PrepTableW");
		GameRegistry.registerBlock(prepTable2W, ItemPrepTable2.class, "PrepTable2W");
		
		registerBranches();
		
		registerFruitTreeBranches();

		registerBranchMap();
	}

	private static void registerBranches() {
		// Tree branches
		branch_xyz = ((BlockCWTFCBranch) new BlockCWTFCBranch().setHardness(30.0f)).setSourceXYZ(-1, -1, -1)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		branch_xyZ = ((BlockCWTFCBranch) new BlockCWTFCBranch().setHardness(30.0f)).setSourceXYZ(-1, -1, 1)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		branch_xYz = ((BlockCWTFCBranch) new BlockCWTFCBranch().setHardness(30.0f)).setSourceXYZ(-1, 1, -1)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		branch_xYZ = ((BlockCWTFCBranch) new BlockCWTFCBranch().setHardness(30.0f)).setSourceXYZ(-1, 1, 1)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		branch_Xyz = ((BlockCWTFCBranch) new BlockCWTFCBranch().setHardness(30.0f)).setSourceXYZ(1, -1, -1)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		branch_XyZ = ((BlockCWTFCBranch) new BlockCWTFCBranch().setHardness(30.0f)).setSourceXYZ(1, -1, 1)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		branch_XYz = ((BlockCWTFCBranch) new BlockCWTFCBranch().setHardness(30.0f)).setSourceXYZ(1, 1, -1)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		branch_XYZ = ((BlockCWTFCBranch) new BlockCWTFCBranch().setHardness(30.0f)).setSourceXYZ(1, 1, 1)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		branch_x_z = ((BlockCWTFCBranch) new BlockCWTFCBranch().setHardness(30.0f)).setSourceXYZ(-1, 0, -1)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		branch_x_Z = ((BlockCWTFCBranch) new BlockCWTFCBranch().setHardness(30.0f)).setSourceXYZ(-1, 0, 1)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		branch_X_z = ((BlockCWTFCBranch) new BlockCWTFCBranch().setHardness(30.0f)).setSourceXYZ(1, 0, -1)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		branch_X_Z = ((BlockCWTFCBranch) new BlockCWTFCBranch().setHardness(30.0f)).setSourceXYZ(1, 0, 1)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		branch__Yz = ((BlockCWTFCBranch) new BlockCWTFCBranch().setHardness(30.0f)).setSourceXYZ(0, 1, -1)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		branch__YZ = ((BlockCWTFCBranch) new BlockCWTFCBranch().setHardness(30.0f)).setSourceXYZ(0, 1, 1)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		branch__yz = ((BlockCWTFCBranch) new BlockCWTFCBranch().setHardness(30.0f)).setSourceXYZ(0, -1, -1)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		branch__yZ = ((BlockCWTFCBranch) new BlockCWTFCBranch().setHardness(30.0f)).setSourceXYZ(0, -1, 1)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		branch___z = ((BlockCWTFCBranch) new BlockCWTFCBranch().setHardness(30.0f)).setSourceXYZ(0, 0, -1)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		branch___Z = ((BlockCWTFCBranch) new BlockCWTFCBranch().setHardness(30.0f)).setSourceXYZ(0, 0, 1)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		branch_x__ = ((BlockCWTFCBranch) new BlockCWTFCBranch().setHardness(30.0f)).setSourceXYZ(-1, 0, 0)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		branch_X__ = ((BlockCWTFCBranch) new BlockCWTFCBranch().setHardness(30.0f)).setSourceXYZ(1, 0, 0)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		branch_xy_ = ((BlockCWTFCBranch) new BlockCWTFCBranch().setHardness(30.0f)).setSourceXYZ(-1, -1, 0)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		branch_Xy_ = ((BlockCWTFCBranch) new BlockCWTFCBranch().setHardness(30.0f)).setSourceXYZ(1, -1, 0)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		branch_xY_ = ((BlockCWTFCBranch) new BlockCWTFCBranch().setHardness(30.0f)).setSourceXYZ(-1, 1, 0)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		branch_XY_ = ((BlockCWTFCBranch) new BlockCWTFCBranch().setHardness(30.0f)).setSourceXYZ(1, 1, 0)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		branch__y_ = ((BlockCWTFCBranch) new BlockCWTFCBranch().setHardness(30.0f)).setSourceXYZ(0, -1, 0)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		// Terminal tree branches
		branchEnd_xyz = ((BlockCWTFCBranch) new BlockCWTFCBranch().setHardness(18.0f)).setSourceXYZ(-1, -1, -1).setEnd(true)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		branchEnd_xyZ = ((BlockCWTFCBranch) new BlockCWTFCBranch().setHardness(18.0f)).setSourceXYZ(-1, -1, 1).setEnd(true)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		branchEnd_xYz = ((BlockCWTFCBranch) new BlockCWTFCBranch().setHardness(18.0f)).setSourceXYZ(-1, 1, -1).setEnd(true)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		branchEnd_xYZ = ((BlockCWTFCBranch) new BlockCWTFCBranch().setHardness(18.0f)).setSourceXYZ(-1, 1, 1).setEnd(true)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		branchEnd_Xyz = ((BlockCWTFCBranch) new BlockCWTFCBranch().setHardness(18.0f)).setSourceXYZ(1, -1, -1).setEnd(true)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		branchEnd_XyZ = ((BlockCWTFCBranch) new BlockCWTFCBranch().setHardness(18.0f)).setSourceXYZ(1, -1, 1).setEnd(true)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		branchEnd_XYz = ((BlockCWTFCBranch) new BlockCWTFCBranch().setHardness(18.0f)).setSourceXYZ(1, 1, -1).setEnd(true)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		branchEnd_XYZ = ((BlockCWTFCBranch) new BlockCWTFCBranch().setHardness(18.0f)).setSourceXYZ(1, 1, 1).setEnd(true)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		branchEnd_x_z = ((BlockCWTFCBranch) new BlockCWTFCBranch().setHardness(18.0f)).setSourceXYZ(-1, 0, -1).setEnd(true)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		branchEnd_x_Z = ((BlockCWTFCBranch) new BlockCWTFCBranch().setHardness(18.0f)).setSourceXYZ(-1, 0, 1).setEnd(true)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		branchEnd_X_z = ((BlockCWTFCBranch) new BlockCWTFCBranch().setHardness(18.0f)).setSourceXYZ(1, 0, -1).setEnd(true)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		branchEnd_X_Z = ((BlockCWTFCBranch) new BlockCWTFCBranch().setHardness(18.0f)).setSourceXYZ(1, 0, 1).setEnd(true)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		branchEnd__Yz = ((BlockCWTFCBranch) new BlockCWTFCBranch().setHardness(18.0f)).setSourceXYZ(0, 1, -1).setEnd(true)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		branchEnd__YZ = ((BlockCWTFCBranch) new BlockCWTFCBranch().setHardness(18.0f)).setSourceXYZ(0, 1, 1).setEnd(true)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		branchEnd__yz = ((BlockCWTFCBranch) new BlockCWTFCBranch().setHardness(18.0f)).setSourceXYZ(0, -1, -1).setEnd(true)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		branchEnd__yZ = ((BlockCWTFCBranch) new BlockCWTFCBranch().setHardness(18.0f)).setSourceXYZ(0, -1, 1).setEnd(true)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		branchEnd___z = ((BlockCWTFCBranch) new BlockCWTFCBranch().setHardness(18.0f)).setSourceXYZ(0, 0, -1).setEnd(true)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		branchEnd___Z = ((BlockCWTFCBranch) new BlockCWTFCBranch().setHardness(18.0f)).setSourceXYZ(0, 0, 1).setEnd(true)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		branchEnd_x__ = ((BlockCWTFCBranch) new BlockCWTFCBranch().setHardness(18.0f)).setSourceXYZ(-1, 0, 0).setEnd(true)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		branchEnd_X__ = ((BlockCWTFCBranch) new BlockCWTFCBranch().setHardness(18.0f)).setSourceXYZ(1, 0, 0).setEnd(true)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		branchEnd_xy_ = ((BlockCWTFCBranch) new BlockCWTFCBranch().setHardness(18.0f)).setSourceXYZ(-1, -1, 0).setEnd(true)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		branchEnd_Xy_ = ((BlockCWTFCBranch) new BlockCWTFCBranch().setHardness(18.0f)).setSourceXYZ(1, -1, 0).setEnd(true)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		branchEnd_xY_ = ((BlockCWTFCBranch) new BlockCWTFCBranch().setHardness(18.0f)).setSourceXYZ(-1, 1, 0).setEnd(true)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		branchEnd_XY_ = ((BlockCWTFCBranch) new BlockCWTFCBranch().setHardness(18.0f)).setSourceXYZ(1, 1, 0).setEnd(true)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		branchEnd__y_ = ((BlockCWTFCBranch) new BlockCWTFCBranch().setHardness(18.0f)).setSourceXYZ(0, -1, 0).setEnd(true)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		
		// Tree branches
		GameRegistry.registerBlock(branch_xyz, ItemCWTFCBranch.class, "Branch_xyz");
		GameRegistry.registerBlock(branch_xyZ, ItemCWTFCBranch.class, "Branch_xyZ");
		GameRegistry.registerBlock(branch_xYz, ItemCWTFCBranch.class, "Branch_xYz");
		GameRegistry.registerBlock(branch_xYZ, ItemCWTFCBranch.class, "Branch_xYZ");
		GameRegistry.registerBlock(branch_Xyz, ItemCWTFCBranch.class, "Branch_Xyz");
		GameRegistry.registerBlock(branch_XyZ, ItemCWTFCBranch.class, "Branch_XyZ");
		GameRegistry.registerBlock(branch_XYz, ItemCWTFCBranch.class, "Branch_XYz");
		GameRegistry.registerBlock(branch_XYZ, ItemCWTFCBranch.class, "Branch_XYZ");
		GameRegistry.registerBlock(branch_x_z, ItemCWTFCBranch.class, "Branch_x_z");
		GameRegistry.registerBlock(branch_x_Z, ItemCWTFCBranch.class, "Branch_x_Z");
		GameRegistry.registerBlock(branch_X_z, ItemCWTFCBranch.class, "Branch_X_z");
		GameRegistry.registerBlock(branch_X_Z, ItemCWTFCBranch.class, "Branch_X_Z");
		GameRegistry.registerBlock(branch__Yz, ItemCWTFCBranch.class, "Branch__Yz");
		GameRegistry.registerBlock(branch__YZ, ItemCWTFCBranch.class, "Branch__YZ");
		GameRegistry.registerBlock(branch__yz, ItemCWTFCBranch.class, "Branch__yz");
		GameRegistry.registerBlock(branch__yZ, ItemCWTFCBranch.class, "Branch__yZ");
		GameRegistry.registerBlock(branch___z, ItemCWTFCBranch.class, "Branch___z");
		GameRegistry.registerBlock(branch___Z, ItemCWTFCBranch.class, "Branch___Z");
		GameRegistry.registerBlock(branch_x__, ItemCWTFCBranch.class, "Branch_x__");
		GameRegistry.registerBlock(branch_X__, ItemCWTFCBranch.class, "Branch_X__");
		GameRegistry.registerBlock(branch_xy_, ItemCWTFCBranch.class, "Branch_xy_");
		GameRegistry.registerBlock(branch_Xy_, ItemCWTFCBranch.class, "Branch_Xy_");
		GameRegistry.registerBlock(branch_xY_, ItemCWTFCBranch.class, "Branch_xY_");
		GameRegistry.registerBlock(branch_XY_, ItemCWTFCBranch.class, "Branch_XY_");
		GameRegistry.registerBlock(branch__y_, ItemCWTFCBranch.class, "Branch__y_");
		
		// Terminal tree branches
		GameRegistry.registerBlock(branchEnd_xyz, ItemCWTFCBranch.class, "BranchEnd_xyz");
		GameRegistry.registerBlock(branchEnd_xyZ, ItemCWTFCBranch.class, "BranchEnd_xyZ");
		GameRegistry.registerBlock(branchEnd_xYz, ItemCWTFCBranch.class, "BranchEnd_xYz");
		GameRegistry.registerBlock(branchEnd_xYZ, ItemCWTFCBranch.class, "BranchEnd_xYZ");
		GameRegistry.registerBlock(branchEnd_Xyz, ItemCWTFCBranch.class, "BranchEnd_Xyz");
		GameRegistry.registerBlock(branchEnd_XyZ, ItemCWTFCBranch.class, "BranchEnd_XyZ");
		GameRegistry.registerBlock(branchEnd_XYz, ItemCWTFCBranch.class, "BranchEnd_XYz");
		GameRegistry.registerBlock(branchEnd_XYZ, ItemCWTFCBranch.class, "BranchEnd_XYZ");
		GameRegistry.registerBlock(branchEnd_x_z, ItemCWTFCBranch.class, "BranchEnd_x_z");
		GameRegistry.registerBlock(branchEnd_x_Z, ItemCWTFCBranch.class, "BranchEnd_x_Z");
		GameRegistry.registerBlock(branchEnd_X_z, ItemCWTFCBranch.class, "BranchEnd_X_z");
		GameRegistry.registerBlock(branchEnd_X_Z, ItemCWTFCBranch.class, "BranchEnd_X_Z");
		GameRegistry.registerBlock(branchEnd__Yz, ItemCWTFCBranch.class, "BranchEnd__Yz");
		GameRegistry.registerBlock(branchEnd__YZ, ItemCWTFCBranch.class, "BranchEnd__YZ");
		GameRegistry.registerBlock(branchEnd__yz, ItemCWTFCBranch.class, "BranchEnd__yz");
		GameRegistry.registerBlock(branchEnd__yZ, ItemCWTFCBranch.class, "BranchEnd__yZ");
		GameRegistry.registerBlock(branchEnd___z, ItemCWTFCBranch.class, "BranchEnd___z");
		GameRegistry.registerBlock(branchEnd___Z, ItemCWTFCBranch.class, "BranchEnd___Z");
		GameRegistry.registerBlock(branchEnd_x__, ItemCWTFCBranch.class, "BranchEnd_x__");
		GameRegistry.registerBlock(branchEnd_X__, ItemCWTFCBranch.class, "BranchEnd_X__");
		GameRegistry.registerBlock(branchEnd_xy_, ItemCWTFCBranch.class, "BranchEnd_xy_");
		GameRegistry.registerBlock(branchEnd_Xy_, ItemCWTFCBranch.class, "BranchEnd_Xy_");
		GameRegistry.registerBlock(branchEnd_xY_, ItemCWTFCBranch.class, "BranchEnd_xY_");
		GameRegistry.registerBlock(branchEnd_XY_, ItemCWTFCBranch.class, "BranchEnd_XY_");
		GameRegistry.registerBlock(branchEnd__y_, ItemCWTFCBranch.class, "BranchEnd__y_");
	}

	private static void registerFruitTreeBranches() {
		// Fruit tree branches
		fruitTreeBranch_xyz = ((BlockCWTFCFruitTreeBranch) new BlockCWTFCFruitTreeBranch().setHardness(30.0f)).setSourceXYZ(-1, -1, -1)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		fruitTreeBranch_xyZ = ((BlockCWTFCFruitTreeBranch) new BlockCWTFCFruitTreeBranch().setHardness(30.0f)).setSourceXYZ(-1, -1, 1)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		fruitTreeBranch_xYz = ((BlockCWTFCFruitTreeBranch) new BlockCWTFCFruitTreeBranch().setHardness(30.0f)).setSourceXYZ(-1, 1, -1)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		fruitTreeBranch_xYZ = ((BlockCWTFCFruitTreeBranch) new BlockCWTFCFruitTreeBranch().setHardness(30.0f)).setSourceXYZ(-1, 1, 1)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		fruitTreeBranch_Xyz = ((BlockCWTFCFruitTreeBranch) new BlockCWTFCFruitTreeBranch().setHardness(30.0f)).setSourceXYZ(1, -1, -1)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		fruitTreeBranch_XyZ = ((BlockCWTFCFruitTreeBranch) new BlockCWTFCFruitTreeBranch().setHardness(30.0f)).setSourceXYZ(1, -1, 1)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		fruitTreeBranch_XYz = ((BlockCWTFCFruitTreeBranch) new BlockCWTFCFruitTreeBranch().setHardness(30.0f)).setSourceXYZ(1, 1, -1)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		fruitTreeBranch_XYZ = ((BlockCWTFCFruitTreeBranch) new BlockCWTFCFruitTreeBranch().setHardness(30.0f)).setSourceXYZ(1, 1, 1)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		fruitTreeBranch_x_z = ((BlockCWTFCFruitTreeBranch) new BlockCWTFCFruitTreeBranch().setHardness(30.0f)).setSourceXYZ(-1, 0, -1)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		fruitTreeBranch_x_Z = ((BlockCWTFCFruitTreeBranch) new BlockCWTFCFruitTreeBranch().setHardness(30.0f)).setSourceXYZ(-1, 0, 1)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		fruitTreeBranch_X_z = ((BlockCWTFCFruitTreeBranch) new BlockCWTFCFruitTreeBranch().setHardness(30.0f)).setSourceXYZ(1, 0, -1)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		fruitTreeBranch_X_Z = ((BlockCWTFCFruitTreeBranch) new BlockCWTFCFruitTreeBranch().setHardness(30.0f)).setSourceXYZ(1, 0, 1)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		fruitTreeBranch__Yz = ((BlockCWTFCFruitTreeBranch) new BlockCWTFCFruitTreeBranch().setHardness(30.0f)).setSourceXYZ(0, 1, -1)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		fruitTreeBranch__YZ = ((BlockCWTFCFruitTreeBranch) new BlockCWTFCFruitTreeBranch().setHardness(30.0f)).setSourceXYZ(0, 1, 1)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		fruitTreeBranch__yz = ((BlockCWTFCFruitTreeBranch) new BlockCWTFCFruitTreeBranch().setHardness(30.0f)).setSourceXYZ(0, -1, -1)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		fruitTreeBranch__yZ = ((BlockCWTFCFruitTreeBranch) new BlockCWTFCFruitTreeBranch().setHardness(30.0f)).setSourceXYZ(0, -1, 1)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		fruitTreeBranch___z = ((BlockCWTFCFruitTreeBranch) new BlockCWTFCFruitTreeBranch().setHardness(30.0f)).setSourceXYZ(0, 0, -1)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		fruitTreeBranch___Z = ((BlockCWTFCFruitTreeBranch) new BlockCWTFCFruitTreeBranch().setHardness(30.0f)).setSourceXYZ(0, 0, 1)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		fruitTreeBranch_x__ = ((BlockCWTFCFruitTreeBranch) new BlockCWTFCFruitTreeBranch().setHardness(30.0f)).setSourceXYZ(-1, 0, 0)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		fruitTreeBranch_X__ = ((BlockCWTFCFruitTreeBranch) new BlockCWTFCFruitTreeBranch().setHardness(30.0f)).setSourceXYZ(1, 0, 0)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		fruitTreeBranch_xy_ = ((BlockCWTFCFruitTreeBranch) new BlockCWTFCFruitTreeBranch().setHardness(30.0f)).setSourceXYZ(-1, -1, 0)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		fruitTreeBranch_Xy_ = ((BlockCWTFCFruitTreeBranch) new BlockCWTFCFruitTreeBranch().setHardness(30.0f)).setSourceXYZ(1, -1, 0)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		fruitTreeBranch_xY_ = ((BlockCWTFCFruitTreeBranch) new BlockCWTFCFruitTreeBranch().setHardness(30.0f)).setSourceXYZ(-1, 1, 0)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		fruitTreeBranch_XY_ = ((BlockCWTFCFruitTreeBranch) new BlockCWTFCFruitTreeBranch().setHardness(30.0f)).setSourceXYZ(1, 1, 0)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		fruitTreeBranch__y_ = ((BlockCWTFCFruitTreeBranch) new BlockCWTFCFruitTreeBranch().setHardness(30.0f)).setSourceXYZ(0, -1, 0)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		// Terminal fruit tree branches
		fruitTreeBranchEnd_xyz = ((BlockCWTFCFruitTreeBranch) new BlockCWTFCFruitTreeBranch().setHardness(18.0f)).setSourceXYZ(-1, -1, -1).setEnd(true)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		fruitTreeBranchEnd_xyZ = ((BlockCWTFCFruitTreeBranch) new BlockCWTFCFruitTreeBranch().setHardness(18.0f)).setSourceXYZ(-1, -1, 1).setEnd(true)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		fruitTreeBranchEnd_xYz = ((BlockCWTFCFruitTreeBranch) new BlockCWTFCFruitTreeBranch().setHardness(18.0f)).setSourceXYZ(-1, 1, -1).setEnd(true)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		fruitTreeBranchEnd_xYZ = ((BlockCWTFCFruitTreeBranch) new BlockCWTFCFruitTreeBranch().setHardness(18.0f)).setSourceXYZ(-1, 1, 1).setEnd(true)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		fruitTreeBranchEnd_Xyz = ((BlockCWTFCFruitTreeBranch) new BlockCWTFCFruitTreeBranch().setHardness(18.0f)).setSourceXYZ(1, -1, -1).setEnd(true)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		fruitTreeBranchEnd_XyZ = ((BlockCWTFCFruitTreeBranch) new BlockCWTFCFruitTreeBranch().setHardness(18.0f)).setSourceXYZ(1, -1, 1).setEnd(true)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		fruitTreeBranchEnd_XYz = ((BlockCWTFCFruitTreeBranch) new BlockCWTFCFruitTreeBranch().setHardness(18.0f)).setSourceXYZ(1, 1, -1).setEnd(true)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		fruitTreeBranchEnd_XYZ = ((BlockCWTFCFruitTreeBranch) new BlockCWTFCFruitTreeBranch().setHardness(18.0f)).setSourceXYZ(1, 1, 1).setEnd(true)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		fruitTreeBranchEnd_x_z = ((BlockCWTFCFruitTreeBranch) new BlockCWTFCFruitTreeBranch().setHardness(18.0f)).setSourceXYZ(-1, 0, -1).setEnd(true)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		fruitTreeBranchEnd_x_Z = ((BlockCWTFCFruitTreeBranch) new BlockCWTFCFruitTreeBranch().setHardness(18.0f)).setSourceXYZ(-1, 0, 1).setEnd(true)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		fruitTreeBranchEnd_X_z = ((BlockCWTFCFruitTreeBranch) new BlockCWTFCFruitTreeBranch().setHardness(18.0f)).setSourceXYZ(1, 0, -1).setEnd(true)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		fruitTreeBranchEnd_X_Z = ((BlockCWTFCFruitTreeBranch) new BlockCWTFCFruitTreeBranch().setHardness(18.0f)).setSourceXYZ(1, 0, 1).setEnd(true)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		fruitTreeBranchEnd__Yz = ((BlockCWTFCFruitTreeBranch) new BlockCWTFCFruitTreeBranch().setHardness(18.0f)).setSourceXYZ(0, 1, -1).setEnd(true)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		fruitTreeBranchEnd__YZ = ((BlockCWTFCFruitTreeBranch) new BlockCWTFCFruitTreeBranch().setHardness(18.0f)).setSourceXYZ(0, 1, 1).setEnd(true)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		fruitTreeBranchEnd__yz = ((BlockCWTFCFruitTreeBranch) new BlockCWTFCFruitTreeBranch().setHardness(18.0f)).setSourceXYZ(0, -1, -1).setEnd(true)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		fruitTreeBranchEnd__yZ = ((BlockCWTFCFruitTreeBranch) new BlockCWTFCFruitTreeBranch().setHardness(18.0f)).setSourceXYZ(0, -1, 1).setEnd(true)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		fruitTreeBranchEnd___z = ((BlockCWTFCFruitTreeBranch) new BlockCWTFCFruitTreeBranch().setHardness(18.0f)).setSourceXYZ(0, 0, -1).setEnd(true)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		fruitTreeBranchEnd___Z = ((BlockCWTFCFruitTreeBranch) new BlockCWTFCFruitTreeBranch().setHardness(18.0f)).setSourceXYZ(0, 0, 1).setEnd(true)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		fruitTreeBranchEnd_x__ = ((BlockCWTFCFruitTreeBranch) new BlockCWTFCFruitTreeBranch().setHardness(18.0f)).setSourceXYZ(-1, 0, 0).setEnd(true)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		fruitTreeBranchEnd_X__ = ((BlockCWTFCFruitTreeBranch) new BlockCWTFCFruitTreeBranch().setHardness(18.0f)).setSourceXYZ(1, 0, 0).setEnd(true)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		fruitTreeBranchEnd_xy_ = ((BlockCWTFCFruitTreeBranch) new BlockCWTFCFruitTreeBranch().setHardness(18.0f)).setSourceXYZ(-1, -1, 0).setEnd(true)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		fruitTreeBranchEnd_Xy_ = ((BlockCWTFCFruitTreeBranch) new BlockCWTFCFruitTreeBranch().setHardness(18.0f)).setSourceXYZ(1, -1, 0).setEnd(true)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		fruitTreeBranchEnd_xY_ = ((BlockCWTFCFruitTreeBranch) new BlockCWTFCFruitTreeBranch().setHardness(18.0f)).setSourceXYZ(-1, 1, 0).setEnd(true)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		fruitTreeBranchEnd_XY_ = ((BlockCWTFCFruitTreeBranch) new BlockCWTFCFruitTreeBranch().setHardness(18.0f)).setSourceXYZ(1, 1, 0).setEnd(true)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		fruitTreeBranchEnd__y_ = ((BlockCWTFCFruitTreeBranch) new BlockCWTFCFruitTreeBranch().setHardness(18.0f)).setSourceXYZ(0, -1, 0).setEnd(true)
				.setStepSound(Block.soundTypeWood).setBlockName("Branch");
		
		// Fruit tree branches
		GameRegistry.registerBlock(fruitTreeBranch_xyz, ItemCWTFCFruitTreeBranch.class, "FruitTreeBranch_xyz");
		GameRegistry.registerBlock(fruitTreeBranch_xyZ, ItemCWTFCFruitTreeBranch.class, "FruitTreeBranch_xyZ");
		GameRegistry.registerBlock(fruitTreeBranch_xYz, ItemCWTFCFruitTreeBranch.class, "FruitTreeBranch_xYz");
		GameRegistry.registerBlock(fruitTreeBranch_xYZ, ItemCWTFCFruitTreeBranch.class, "FruitTreeBranch_xYZ");
		GameRegistry.registerBlock(fruitTreeBranch_Xyz, ItemCWTFCFruitTreeBranch.class, "FruitTreeBranch_Xyz");
		GameRegistry.registerBlock(fruitTreeBranch_XyZ, ItemCWTFCFruitTreeBranch.class, "FruitTreeBranch_XyZ");
		GameRegistry.registerBlock(fruitTreeBranch_XYz, ItemCWTFCFruitTreeBranch.class, "FruitTreeBranch_XYz");
		GameRegistry.registerBlock(fruitTreeBranch_XYZ, ItemCWTFCFruitTreeBranch.class, "FruitTreeBranch_XYZ");
		GameRegistry.registerBlock(fruitTreeBranch_x_z, ItemCWTFCFruitTreeBranch.class, "FruitTreeBranch_x_z");
		GameRegistry.registerBlock(fruitTreeBranch_x_Z, ItemCWTFCFruitTreeBranch.class, "FruitTreeBranch_x_Z");
		GameRegistry.registerBlock(fruitTreeBranch_X_z, ItemCWTFCFruitTreeBranch.class, "FruitTreeBranch_X_z");
		GameRegistry.registerBlock(fruitTreeBranch_X_Z, ItemCWTFCFruitTreeBranch.class, "FruitTreeBranch_X_Z");
		GameRegistry.registerBlock(fruitTreeBranch__Yz, ItemCWTFCFruitTreeBranch.class, "FruitTreeBranch__Yz");
		GameRegistry.registerBlock(fruitTreeBranch__YZ, ItemCWTFCFruitTreeBranch.class, "FruitTreeBranch__YZ");
		GameRegistry.registerBlock(fruitTreeBranch__yz, ItemCWTFCFruitTreeBranch.class, "FruitTreeBranch__yz");
		GameRegistry.registerBlock(fruitTreeBranch__yZ, ItemCWTFCFruitTreeBranch.class, "FruitTreeBranch__yZ");
		GameRegistry.registerBlock(fruitTreeBranch___z, ItemCWTFCFruitTreeBranch.class, "FruitTreeBranch___z");
		GameRegistry.registerBlock(fruitTreeBranch___Z, ItemCWTFCFruitTreeBranch.class, "FruitTreeBranch___Z");
		GameRegistry.registerBlock(fruitTreeBranch_x__, ItemCWTFCFruitTreeBranch.class, "FruitTreeBranch_x__");
		GameRegistry.registerBlock(fruitTreeBranch_X__, ItemCWTFCFruitTreeBranch.class, "FruitTreeBranch_X__");
		GameRegistry.registerBlock(fruitTreeBranch_xy_, ItemCWTFCFruitTreeBranch.class, "FruitTreeBranch_xy_");
		GameRegistry.registerBlock(fruitTreeBranch_Xy_, ItemCWTFCFruitTreeBranch.class, "FruitTreeBranch_Xy_");
		GameRegistry.registerBlock(fruitTreeBranch_xY_, ItemCWTFCFruitTreeBranch.class, "FruitTreeBranch_xY_");
		GameRegistry.registerBlock(fruitTreeBranch_XY_, ItemCWTFCFruitTreeBranch.class, "FruitTreeBranch_XY_");
		GameRegistry.registerBlock(fruitTreeBranch__y_, ItemCWTFCFruitTreeBranch.class, "FruitTreeBranch__y_");
		
		// Terminal fruit tree branches
		GameRegistry.registerBlock(fruitTreeBranchEnd_xyz, ItemCWTFCFruitTreeBranch.class, "FruitTreeBranchEnd_xyz");
		GameRegistry.registerBlock(fruitTreeBranchEnd_xyZ, ItemCWTFCFruitTreeBranch.class, "FruitTreeBranchEnd_xyZ");
		GameRegistry.registerBlock(fruitTreeBranchEnd_xYz, ItemCWTFCFruitTreeBranch.class, "FruitTreeBranchEnd_xYz");
		GameRegistry.registerBlock(fruitTreeBranchEnd_xYZ, ItemCWTFCFruitTreeBranch.class, "FruitTreeBranchEnd_xYZ");
		GameRegistry.registerBlock(fruitTreeBranchEnd_Xyz, ItemCWTFCFruitTreeBranch.class, "FruitTreeBranchEnd_Xyz");
		GameRegistry.registerBlock(fruitTreeBranchEnd_XyZ, ItemCWTFCFruitTreeBranch.class, "FruitTreeBranchEnd_XyZ");
		GameRegistry.registerBlock(fruitTreeBranchEnd_XYz, ItemCWTFCFruitTreeBranch.class, "FruitTreeBranchEnd_XYz");
		GameRegistry.registerBlock(fruitTreeBranchEnd_XYZ, ItemCWTFCFruitTreeBranch.class, "FruitTreeBranchEnd_XYZ");
		GameRegistry.registerBlock(fruitTreeBranchEnd_x_z, ItemCWTFCFruitTreeBranch.class, "FruitTreeBranchEnd_x_z");
		GameRegistry.registerBlock(fruitTreeBranchEnd_x_Z, ItemCWTFCFruitTreeBranch.class, "FruitTreeBranchEnd_x_Z");
		GameRegistry.registerBlock(fruitTreeBranchEnd_X_z, ItemCWTFCFruitTreeBranch.class, "FruitTreeBranchEnd_X_z");
		GameRegistry.registerBlock(fruitTreeBranchEnd_X_Z, ItemCWTFCFruitTreeBranch.class, "FruitTreeBranchEnd_X_Z");
		GameRegistry.registerBlock(fruitTreeBranchEnd__Yz, ItemCWTFCFruitTreeBranch.class, "FruitTreeBranchEnd__Yz");
		GameRegistry.registerBlock(fruitTreeBranchEnd__YZ, ItemCWTFCFruitTreeBranch.class, "FruitTreeBranchEnd__YZ");
		GameRegistry.registerBlock(fruitTreeBranchEnd__yz, ItemCWTFCFruitTreeBranch.class, "FruitTreeBranchEnd__yz");
		GameRegistry.registerBlock(fruitTreeBranchEnd__yZ, ItemCWTFCFruitTreeBranch.class, "FruitTreeBranchEnd__yZ");
		GameRegistry.registerBlock(fruitTreeBranchEnd___z, ItemCWTFCFruitTreeBranch.class, "FruitTreeBranchEnd___z");
		GameRegistry.registerBlock(fruitTreeBranchEnd___Z, ItemCWTFCFruitTreeBranch.class, "FruitTreeBranchEnd___Z");
		GameRegistry.registerBlock(fruitTreeBranchEnd_x__, ItemCWTFCFruitTreeBranch.class, "FruitTreeBranchEnd_x__");
		GameRegistry.registerBlock(fruitTreeBranchEnd_X__, ItemCWTFCFruitTreeBranch.class, "FruitTreeBranchEnd_X__");
		GameRegistry.registerBlock(fruitTreeBranchEnd_xy_, ItemCWTFCFruitTreeBranch.class, "FruitTreeBranchEnd_xy_");
		GameRegistry.registerBlock(fruitTreeBranchEnd_Xy_, ItemCWTFCFruitTreeBranch.class, "FruitTreeBranchEnd_Xy_");
		GameRegistry.registerBlock(fruitTreeBranchEnd_xY_, ItemCWTFCFruitTreeBranch.class, "FruitTreeBranchEnd_xY_");
		GameRegistry.registerBlock(fruitTreeBranchEnd_XY_, ItemCWTFCFruitTreeBranch.class, "FruitTreeBranchEnd_XY_");
		GameRegistry.registerBlock(fruitTreeBranchEnd__y_, ItemCWTFCFruitTreeBranch.class, "FruitTreeBranchEnd__y_");
	}

	private static void registerBranchMap() {
		// The way this map works is that the X,Y,Z spaces are the directions in
		// which that branch is. N represents regular, 2, terminal, terminal 2
		// X, Y, Z, N
		CWTFC_Core.branchMap[0][0][0][0] = branch_xyz;
		CWTFC_Core.branchMap[0][0][2][0] = branch_xyZ;
		CWTFC_Core.branchMap[0][2][0][0] = branch_xYz;
		CWTFC_Core.branchMap[0][2][2][0] = branch_xYZ;
		CWTFC_Core.branchMap[2][0][0][0] = branch_Xyz;
		CWTFC_Core.branchMap[2][0][2][0] = branch_XyZ;
		CWTFC_Core.branchMap[2][2][0][0] = branch_XYz;
		CWTFC_Core.branchMap[2][2][2][0] = branch_XYZ;
		CWTFC_Core.branchMap[0][1][0][0] = branch_x_z;
		CWTFC_Core.branchMap[0][1][2][0] = branch_x_Z;
		CWTFC_Core.branchMap[2][1][0][0] = branch_X_z;
		CWTFC_Core.branchMap[2][1][2][0] = branch_X_Z;
		CWTFC_Core.branchMap[1][2][0][0] = branch__Yz;
		CWTFC_Core.branchMap[1][2][2][0] = branch__YZ;
		CWTFC_Core.branchMap[1][0][0][0] = branch__yz;
		CWTFC_Core.branchMap[1][0][2][0] = branch__yZ;
		CWTFC_Core.branchMap[1][1][0][0] = branch___z;
		CWTFC_Core.branchMap[1][1][2][0] = branch___Z;
		CWTFC_Core.branchMap[0][1][1][0] = branch_x__;
		CWTFC_Core.branchMap[2][1][1][0] = branch_X__;
		CWTFC_Core.branchMap[0][0][1][0] = branch_xy_;
		CWTFC_Core.branchMap[2][0][1][0] = branch_Xy_;
		CWTFC_Core.branchMap[0][2][1][0] = branch_xY_;
		CWTFC_Core.branchMap[2][2][1][0] = branch_XY_;
		CWTFC_Core.branchMap[1][0][1][0] = branch__y_;

		CWTFC_Core.branchMap[0][0][0][2] = branchEnd_xyz;
		CWTFC_Core.branchMap[0][0][2][2] = branchEnd_xyZ;
		CWTFC_Core.branchMap[0][2][0][2] = branchEnd_xYz;
		CWTFC_Core.branchMap[0][2][2][2] = branchEnd_xYZ;
		CWTFC_Core.branchMap[2][0][0][2] = branchEnd_Xyz;
		CWTFC_Core.branchMap[2][0][2][2] = branchEnd_XyZ;
		CWTFC_Core.branchMap[2][2][0][2] = branchEnd_XYz;
		CWTFC_Core.branchMap[2][2][2][2] = branchEnd_XYZ;
		CWTFC_Core.branchMap[0][1][0][2] = branchEnd_x_z;
		CWTFC_Core.branchMap[0][1][2][2] = branchEnd_x_Z;
		CWTFC_Core.branchMap[2][1][0][2] = branchEnd_X_z;
		CWTFC_Core.branchMap[2][1][2][2] = branchEnd_X_Z;
		CWTFC_Core.branchMap[1][2][0][2] = branchEnd__Yz;
		CWTFC_Core.branchMap[1][2][2][2] = branchEnd__YZ;
		CWTFC_Core.branchMap[1][0][0][2] = branchEnd__yz;
		CWTFC_Core.branchMap[1][0][2][2] = branchEnd__yZ;
		CWTFC_Core.branchMap[1][1][0][2] = branchEnd___z;
		CWTFC_Core.branchMap[1][1][2][2] = branchEnd___Z;
		CWTFC_Core.branchMap[0][1][1][2] = branchEnd_x__;
		CWTFC_Core.branchMap[2][1][1][2] = branchEnd_X__;
		CWTFC_Core.branchMap[0][0][1][2] = branchEnd_xy_;
		CWTFC_Core.branchMap[2][0][1][2] = branchEnd_Xy_;
		CWTFC_Core.branchMap[0][2][1][2] = branchEnd_xY_;
		CWTFC_Core.branchMap[2][2][1][2] = branchEnd_XY_;
		CWTFC_Core.branchMap[1][0][1][2] = branchEnd__y_;
		
		CWTFC_Core.branchMap[0][0][0][1] = fruitTreeBranch_xyz;
		CWTFC_Core.branchMap[0][0][2][1] = fruitTreeBranch_xyZ;
		CWTFC_Core.branchMap[0][2][0][1] = fruitTreeBranch_xYz;
		CWTFC_Core.branchMap[0][2][2][1] = fruitTreeBranch_xYZ;
		CWTFC_Core.branchMap[2][0][0][1] = fruitTreeBranch_Xyz;
		CWTFC_Core.branchMap[2][0][2][1] = fruitTreeBranch_XyZ;
		CWTFC_Core.branchMap[2][2][0][1] = fruitTreeBranch_XYz;
		CWTFC_Core.branchMap[2][2][2][1] = fruitTreeBranch_XYZ;
		CWTFC_Core.branchMap[0][1][0][1] = fruitTreeBranch_x_z;
		CWTFC_Core.branchMap[0][1][2][1] = fruitTreeBranch_x_Z;
		CWTFC_Core.branchMap[2][1][0][1] = fruitTreeBranch_X_z;
		CWTFC_Core.branchMap[2][1][2][1] = fruitTreeBranch_X_Z;
		CWTFC_Core.branchMap[1][2][0][1] = fruitTreeBranch__Yz;
		CWTFC_Core.branchMap[1][2][2][1] = fruitTreeBranch__YZ;
		CWTFC_Core.branchMap[1][0][0][1] = fruitTreeBranch__yz;
		CWTFC_Core.branchMap[1][0][2][1] = fruitTreeBranch__yZ;
		CWTFC_Core.branchMap[1][1][0][1] = fruitTreeBranch___z;
		CWTFC_Core.branchMap[1][1][2][1] = fruitTreeBranch___Z;
		CWTFC_Core.branchMap[0][1][1][1] = fruitTreeBranch_x__;
		CWTFC_Core.branchMap[2][1][1][1] = fruitTreeBranch_X__;
		CWTFC_Core.branchMap[0][0][1][1] = fruitTreeBranch_xy_;
		CWTFC_Core.branchMap[2][0][1][1] = fruitTreeBranch_Xy_;
		CWTFC_Core.branchMap[0][2][1][1] = fruitTreeBranch_xY_;
		CWTFC_Core.branchMap[2][2][1][1] = fruitTreeBranch_XY_;
		CWTFC_Core.branchMap[1][0][1][1] = fruitTreeBranch__y_;

		CWTFC_Core.branchMap[0][0][0][3] = fruitTreeBranchEnd_xyz;
		CWTFC_Core.branchMap[0][0][2][3] = fruitTreeBranchEnd_xyZ;
		CWTFC_Core.branchMap[0][2][0][3] = fruitTreeBranchEnd_xYz;
		CWTFC_Core.branchMap[0][2][2][3] = fruitTreeBranchEnd_xYZ;
		CWTFC_Core.branchMap[2][0][0][3] = fruitTreeBranchEnd_Xyz;
		CWTFC_Core.branchMap[2][0][2][3] = fruitTreeBranchEnd_XyZ;
		CWTFC_Core.branchMap[2][2][0][3] = fruitTreeBranchEnd_XYz;
		CWTFC_Core.branchMap[2][2][2][3] = fruitTreeBranchEnd_XYZ;
		CWTFC_Core.branchMap[0][1][0][3] = fruitTreeBranchEnd_x_z;
		CWTFC_Core.branchMap[0][1][2][3] = fruitTreeBranchEnd_x_Z;
		CWTFC_Core.branchMap[2][1][0][3] = fruitTreeBranchEnd_X_z;
		CWTFC_Core.branchMap[2][1][2][3] = fruitTreeBranchEnd_X_Z;
		CWTFC_Core.branchMap[1][2][0][3] = fruitTreeBranchEnd__Yz;
		CWTFC_Core.branchMap[1][2][2][3] = fruitTreeBranchEnd__YZ;
		CWTFC_Core.branchMap[1][0][0][3] = fruitTreeBranchEnd__yz;
		CWTFC_Core.branchMap[1][0][2][3] = fruitTreeBranchEnd__yZ;
		CWTFC_Core.branchMap[1][1][0][3] = fruitTreeBranchEnd___z;
		CWTFC_Core.branchMap[1][1][2][3] = fruitTreeBranchEnd___Z;
		CWTFC_Core.branchMap[0][1][1][3] = fruitTreeBranchEnd_x__;
		CWTFC_Core.branchMap[2][1][1][3] = fruitTreeBranchEnd_X__;
		CWTFC_Core.branchMap[0][0][1][3] = fruitTreeBranchEnd_xy_;
		CWTFC_Core.branchMap[2][0][1][3] = fruitTreeBranchEnd_Xy_;
		CWTFC_Core.branchMap[0][2][1][3] = fruitTreeBranchEnd_xY_;
		CWTFC_Core.branchMap[2][2][1][3] = fruitTreeBranchEnd_XY_;
		CWTFC_Core.branchMap[1][0][1][3] = fruitTreeBranchEnd__y_;
	}
	
	public static void setupFire()
	{
		//Organic blocks
		Blocks.fire.setFireInfo(treeLeaves, 20, 20);
		Blocks.fire.setFireInfo(GrainsBlock, 20, 20);
		Blocks.fire.setFireInfo(customCrop, 20, 20);
		Blocks.fire.setFireInfo(treeSapling, 20, 20);
		//Blocks.fire.setFireInfo(fruitTreeSapling, 20, 20);
		Blocks.fire.setFireInfo(fruitTreeLeaves, 20, 20);
		
		//Wood blocks
		Blocks.fire.setFireInfo(woodPlank, 5, 5);
		Blocks.fire.setFireInfo(lumberConstruct, 5, 5);
		Blocks.fire.setFireInfo(treeLog, 5, 5);
		Blocks.fire.setFireInfo(woodVert, 5, 5);
		Blocks.fire.setFireInfo(woodHorizNS, 5, 5);
		Blocks.fire.setFireInfo(woodHorizEW, 5, 5);
		Blocks.fire.setFireInfo(fruitTreeWood, 5, 5);
		Blocks.fire.setFireInfo(prepTableN, 5, 5);
		Blocks.fire.setFireInfo(prepTable2N, 5, 5);
		Blocks.fire.setFireInfo(prepTableS, 5, 5);
		Blocks.fire.setFireInfo(prepTable2S, 5, 5);
		Blocks.fire.setFireInfo(prepTableE, 5, 5);
		Blocks.fire.setFireInfo(prepTable2E, 5, 5);
		Blocks.fire.setFireInfo(prepTableW, 5, 5);
		Blocks.fire.setFireInfo(prepTable2W, 5, 5);
	}

	public static boolean isBranchTerminal(Block block)
	{
		return block == branchEnd_xyz || block == branchEnd_xyZ || block == branchEnd_xYz || block == branchEnd_xYZ
				|| block == branchEnd_Xyz || block == branchEnd_XyZ || block == branchEnd_XYz || block == branchEnd_XYZ
				|| block == branchEnd_x_z || block == branchEnd_x_Z || block == branchEnd_X_z || block == branchEnd_X_Z
				|| block == branchEnd__Yz || block == branchEnd__YZ || block == branchEnd__yz || block == branchEnd__yZ
				|| block == branchEnd___z || block == branchEnd___Z || block == branchEnd_x__ || block == branchEnd_X__
				|| block == branchEnd_xy_ || block == branchEnd_Xy_ || block == branchEnd_xY_ || block == branchEnd_XY_
				|| block == branchEnd__y_ || block == fruitTreeBranchEnd_xyz || block == fruitTreeBranchEnd_xyZ
				|| block == fruitTreeBranchEnd_xYz || block == fruitTreeBranchEnd_xYZ || block == fruitTreeBranchEnd_Xyz
				|| block == fruitTreeBranchEnd_XyZ || block == fruitTreeBranchEnd_XYz || block == fruitTreeBranchEnd_XYZ
				|| block == fruitTreeBranchEnd_x_z || block == fruitTreeBranchEnd_x_Z || block == fruitTreeBranchEnd_X_z
				|| block == fruitTreeBranchEnd_X_Z || block == fruitTreeBranchEnd__Yz || block == fruitTreeBranchEnd__YZ
				|| block == fruitTreeBranchEnd__yz || block == fruitTreeBranchEnd__yZ || block == fruitTreeBranchEnd___z
				|| block == fruitTreeBranchEnd___Z || block == fruitTreeBranchEnd_x__ || block == fruitTreeBranchEnd_X__
				|| block == fruitTreeBranchEnd_xy_ || block == fruitTreeBranchEnd_Xy_ || block == fruitTreeBranchEnd_xY_
				|| block == fruitTreeBranchEnd_XY_ || block == fruitTreeBranchEnd__y_;
	}
}
