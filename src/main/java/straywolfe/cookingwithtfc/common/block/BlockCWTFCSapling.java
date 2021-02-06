package straywolfe.cookingwithtfc.common.block;

import java.util.Random;

import com.dunk.tfc.Blocks.Flora.BlockSapling;
import com.dunk.tfc.Core.TFC_Core;
import com.dunk.tfc.Core.TFC_Time;
import com.dunk.tfc.TileEntities.TESapling;
import com.dunk.tfc.WorldGen.TFCBiome;
import com.dunk.tfc.api.TFCBlocks;
import com.dunk.tfc.api.TFCOptions;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import straywolfe.cookingwithtfc.common.core.Tabs;
import straywolfe.cookingwithtfc.common.lib.Constants;
import straywolfe.cookingwithtfc.common.lib.ModInfo;
import straywolfe.cookingwithtfc.common.worldgen.CWTFCBiome;
import straywolfe.cookingwithtfc.common.worldgen.WorldGenBigTrees;

public class BlockCWTFCSapling extends BlockSapling
{
	public BlockCWTFCSapling()
	{
		super();
		woodNames = Constants.WOODTYPES;
		setHardness(0.0F);
		setStepSound(Block.soundTypeGrass);
		setBlockName("Sapling");
		setCreativeTab(Tabs.MAINTAB);
		icons = new IIcon[woodNames.length];
	}
	
	@Override
	public void registerBlockIcons(IIconRegister registerer)
	{
		for(int i = 0; i < woodNames.length; i++)
			icons[i] = registerer.registerIcon(ModInfo.ModID + ":Wood/" + woodNames[i] + " Sapling");
	}

	@Override
	public void growTree(World world, int i, int j, int k, Random rand, long timestamp)
	{
		int meta = world.getBlockMetadata(i, j, k);
		world.setBlockToAir(i, j, k);
		WorldGenerator worldGen = CWTFCBiome.getTreeGen(rand.nextBoolean(),true,CWTFCBiome.getEnumTreeFromId(meta));

		if (worldGen != null && !worldGen.generate(world, rand, i, j, k))
		{
			world.setBlock(i, j, k, this, meta, 3);
			if (world.getTileEntity(i, j, k) instanceof TESapling)
			{
				TESapling te = (TESapling) world.getTileEntity(i, j, k);
				te.growTime = timestamp;
				te.enoughSpace = false;
				te.markDirty();
			}
		}
	}

	// Set the sapling growth timer the moment it is planted, instead of the first random tick it gets after being planted.
	@Override
	public void onBlockAdded(World world, int i, int j, int k)
	{
		int meta = world.getBlockMetadata(i, j, k);
		float growSpeed = 1;
		
		/*if(meta == 1 || meta == 11) TODO: Add different growth speeds for trees
			growSpeed = 1.2f;
		else if(meta == 5 || meta == 0 || meta == 13)
			growSpeed = 1.4f;
		else if(meta == 9 || meta == 14|| meta == 15)
			growSpeed = 1.6f;*/

		if (world.getTileEntity(i, j, k) instanceof TESapling)
		{
			TESapling te = (TESapling) world.getTileEntity(i, j, k);

			// Set the growTime tick timestamp to be 7-11.2 days times config multiplier from now, plus up to an extra day.
			if (te != null && te.growTime == 0)
				te.growTime = (long) (TFC_Time.getTotalTicks() + (TFC_Time.DAY_LENGTH * 7 * growSpeed * TFCOptions.saplingTimerMultiplier) + (world.rand.nextFloat() * TFC_Time.DAY_LENGTH));
		}
	}
}
