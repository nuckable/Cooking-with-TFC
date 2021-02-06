package straywolfe.cookingwithtfc.client.render;

import java.util.Random;

import com.dunk.tfc.Blocks.Vanilla.BlockCustomLeaves2;
import com.dunk.tfc.Core.TFC_Time;
import com.dunk.tfc.Food.FloraIndex;
import com.dunk.tfc.Food.FloraManager;
import com.dunk.tfc.Render.RenderBlocksWithRotation;
import com.dunk.tfc.Render.TFC_CoreRender;
import com.dunk.tfc.TileEntities.TEFruitTreeWood;
import com.dunk.tfc.api.TFCBlocks;
import com.dunk.tfc.api.Constant.Global;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import straywolfe.cookingwithtfc.api.CWTFCBlocks;
import straywolfe.cookingwithtfc.common.block.BlockCWTFCBranch;
import straywolfe.cookingwithtfc.common.block.BlockCWTFCFruitLeaves;
import straywolfe.cookingwithtfc.common.block.BlockCWTFCFruitTreeBranch;
import straywolfe.cookingwithtfc.common.block.BlockCWTFCLeaves;
import straywolfe.cookingwithtfc.common.lib.Constants;
import straywolfe.cookingwithtfc.common.tileentity.TECWTFCFruitLeaves;
import straywolfe.cookingwithtfc.common.tileentity.TECWTFCFruitTreeWood;

public class RenderCWTFCBranch implements ISimpleBlockRenderingHandler
{
	private static final boolean renderCoconutAsBanana = false;
	private static float rot45 = (float) (-45d * (Math.PI / 180d));
	private static double sqrt2 = Math.sqrt(2);
	private static double sqrt3 = Math.sqrt(3);
	private static float diagRot = (float) -(Math.atan(sqrt2));

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId,
			RenderBlocks renderblocks)
	{
		boolean hasSnow = world.getBlock(x, y + 1, z) == TFCBlocks.snow;
		boolean hasMoss_Y, hasMoss_X, hasMoss_x, hasMoss_z, hasMoss_Z, hasMoss_y, hasMoss_Y2, hasMoss_X2, hasMoss_x2,
				hasMoss_z2, hasMoss_Z2, hasMoss_y2;

		float snowThickness = 0.125f + (0.125f * world.getBlockMetadata(x, y + 1, z));
		float mossThickness = 0.0125F;
		boolean handled = false;
		float width = 0.2f;
		if (block instanceof BlockCWTFCBranch) {
			BlockCWTFCBranch branch = (BlockCWTFCBranch) block;
			int meta = world.getBlockMetadata(x, y, z);
			float n = ((BlockCWTFCBranch) block).getDistanceToTrunk(world, x, y, z, 0);
			if (n < 0)
			{
				n = 20;
			}
			width = 0.3f - (n * 0.001f);
	
			renderblocks = new RenderBlocksWithRotation(renderblocks);
	
			width = Math.max(width, 0.1f);
	
			if (((BlockCWTFCBranch) block).isEnd())
			{
				width *= 0.5f;
			}
			if (block == CWTFCBlocks.branch__yz || block == CWTFCBlocks.branch_xy_ || block == CWTFCBlocks.branch__yZ || block == CWTFCBlocks.branch_Xy_ || block == CWTFCBlocks.branch_xyz || block == CWTFCBlocks.branch_xyZ || block == CWTFCBlocks.branch_XyZ || block == CWTFCBlocks.branch_Xyz || block == CWTFCBlocks.branchEnd__yz || block == CWTFCBlocks.branchEnd_xy_ || block == CWTFCBlocks.branchEnd__yZ || block == CWTFCBlocks.branchEnd_Xy_ || block == CWTFCBlocks.branchEnd_xyz || block == CWTFCBlocks.branchEnd_xyZ || block == CWTFCBlocks.branchEnd_XyZ || block == CWTFCBlocks.branchEnd_Xyz)
			{
				((RenderBlocksWithRotation) renderblocks).rotation = ((BlockCWTFCBranch) block).isLongDiagonal() ? diagRot
						: rot45;
				renderblocks.setRenderBounds(0.5 - width + 0.025,
						0.5 - (((BlockCWTFCBranch) block).isLongDiagonal() ? sqrt3 : sqrt2), 0.5 - width + 0.025,
						0.5 + width - 0.025, 0.5, 0.5 + width - 0.025);
				handled = true;
			}
			else if (block == CWTFCBlocks.branch__Yz || block == CWTFCBlocks.branch_xY_ || block == CWTFCBlocks.branch__YZ || block == CWTFCBlocks.branch_XY_ || block == CWTFCBlocks.branch_xYz || block == CWTFCBlocks.branch_xYZ || block == CWTFCBlocks.branch_XYZ || block == CWTFCBlocks.branch_XYz || block == CWTFCBlocks.branchEnd__Yz || block == CWTFCBlocks.branchEnd_xY_ || block == CWTFCBlocks.branchEnd__YZ || block == CWTFCBlocks.branchEnd_XY_ || block == CWTFCBlocks.branchEnd_xYz || block == CWTFCBlocks.branchEnd_xYZ || block == CWTFCBlocks.branchEnd_XYZ || block == CWTFCBlocks.branchEnd_XYz)
			{
				((RenderBlocksWithRotation) renderblocks).rotation = ((BlockCWTFCBranch) block).isLongDiagonal() ? diagRot
						: rot45;

				renderblocks.setRenderBounds(0.5 - width + 0.025, 0.5, 0.5 - width + 0.025, 0.5 + width - 0.025,
						0.5 + (((BlockCWTFCBranch) block).isLongDiagonal() ? sqrt3 : sqrt2), 0.5 + width - 0.025);
				handled = true;
			}
			if (block == CWTFCBlocks.branch___z || block == CWTFCBlocks.branch_x__ || block == CWTFCBlocks.branch___Z || block == CWTFCBlocks.branch_X__ || block == CWTFCBlocks.branch_x_z || block == CWTFCBlocks.branch_x_Z || block == CWTFCBlocks.branch_X_Z || block == CWTFCBlocks.branch_X_z || block == CWTFCBlocks.branchEnd___z || block == CWTFCBlocks.branchEnd_x__ || block == CWTFCBlocks.branchEnd___Z || block == CWTFCBlocks.branchEnd_X__ || block == CWTFCBlocks.branchEnd_x_z || block == CWTFCBlocks.branchEnd_x_Z || block == CWTFCBlocks.branchEnd_X_Z || block == CWTFCBlocks.branchEnd_X_z)
			{
				((RenderBlocksWithRotation) renderblocks).rotation = (float) (-90 * (Math.PI) / 180f);

				renderblocks.setRenderBounds(0.5 - width, 0.5 - (((BlockCWTFCBranch) block).isShortDiagonal() ? sqrt2 : 1),
						0.5 - width, 0.5 + width, 0.5, 0.5 + width);
				handled = true;
			}
			if (block == CWTFCBlocks.branch__y_ || block == CWTFCBlocks.branchEnd__y_)
			{
				((RenderBlocksWithRotation) renderblocks).rotation = 0;
				renderblocks.setRenderBounds(0.5 - width, -0.5, 0.5 - width, 0.5 + width, 0.5, 0.5 + width);
				handled = true;
			}
			// +x-z
			if (block == CWTFCBlocks.branch_X_z || block == CWTFCBlocks.branch_Xyz || block == CWTFCBlocks.branch_XYz || block == CWTFCBlocks.branchEnd_X_z || block == CWTFCBlocks.branchEnd_Xyz || block == CWTFCBlocks.branchEnd_XYz)
			{
				((RenderBlocksWithRotation) renderblocks).yRotation = (float) ((135 + (((BlockCWTFCBranch) block)
						.getSourceY() == 1 ? 0 : 180)) * (Math.PI) / 180f);
			}
			// -z
			if (block == CWTFCBlocks.branch___z || block == CWTFCBlocks.branch__yz || block == CWTFCBlocks.branch__Yz || block == CWTFCBlocks.branchEnd___z || block == CWTFCBlocks.branchEnd__yz || block == CWTFCBlocks.branchEnd__Yz)
			{
				((RenderBlocksWithRotation) renderblocks).yRotation = (float) ((180 + (((BlockCWTFCBranch) block)
						.getSourceY() == 1 ? 0 : 180)) * (Math.PI) / 180f);
			}
			// -x-z
			if (block == CWTFCBlocks.branch_x_z || block == CWTFCBlocks.branch_xyz || block == CWTFCBlocks.branch_xYz || block == CWTFCBlocks.branchEnd_x_z || block == CWTFCBlocks.branchEnd_xyz || block == CWTFCBlocks.branchEnd_xYz)
			{
				((RenderBlocksWithRotation) renderblocks).yRotation = (float) ((225 + (((BlockCWTFCBranch) block)
						.getSourceY() == 1 ? 0 : 180)) * (Math.PI) / 180f);
			}
			// +z
			if (block == CWTFCBlocks.branch___Z || block == CWTFCBlocks.branch__yZ || block == CWTFCBlocks.branch__YZ || block == CWTFCBlocks.branchEnd___Z || block == CWTFCBlocks.branchEnd__yZ || block == CWTFCBlocks.branchEnd__YZ)
			{
				((RenderBlocksWithRotation) renderblocks).yRotation = (float) ((0 + (((BlockCWTFCBranch) block).getSourceY() == 1
						? 0 : 180)) * (Math.PI) / 180f);
			}
			// -x+z
			if (block == CWTFCBlocks.branch_x_Z || block == CWTFCBlocks.branch_xyZ || block == CWTFCBlocks.branch_xYZ || block == CWTFCBlocks.branchEnd_x_Z || block == CWTFCBlocks.branchEnd_xyZ || block == CWTFCBlocks.branchEnd_xYZ)
			{
				((RenderBlocksWithRotation) renderblocks).yRotation = (float) ((315 + (((BlockCWTFCBranch) block)
						.getSourceY() == 1 ? 0 : 180)) * (Math.PI) / 180f);
			}
			// +x+z
			if (block == CWTFCBlocks.branch_X_Z || block == CWTFCBlocks.branch_XyZ || block == CWTFCBlocks.branch_XYZ || block == CWTFCBlocks.branchEnd_X_Z || block == CWTFCBlocks.branchEnd_XyZ || block == CWTFCBlocks.branchEnd_XYZ)
			{
				((RenderBlocksWithRotation) renderblocks).yRotation = (float) ((45 + (((BlockCWTFCBranch) block)
						.getSourceY() == 1 ? 0 : 180)) * (Math.PI) / 180f);
			}
			// +x
			if (block == CWTFCBlocks.branch_X__ || block == CWTFCBlocks.branch_Xy_ || block == CWTFCBlocks.branch_XY_ || block == CWTFCBlocks.branchEnd_X__ || block == CWTFCBlocks.branchEnd_Xy_ || block == CWTFCBlocks.branchEnd_XY_)
			{
				((RenderBlocksWithRotation) renderblocks).yRotation = (float) ((90 + (((BlockCWTFCBranch) block)
						.getSourceY() == 1 ? 0 : 180)) * (Math.PI) / 180f);
			}
			// -x
			if (block == CWTFCBlocks.branch_x__ || block == CWTFCBlocks.branch_xy_ || block == CWTFCBlocks.branch_xY_ || block == CWTFCBlocks.branchEnd_x__ || block == CWTFCBlocks.branchEnd_xy_ || block == CWTFCBlocks.branchEnd_xY_)
			{
				((RenderBlocksWithRotation) renderblocks).yRotation = (float) ((270 + (((BlockCWTFCBranch) block)
						.getSourceY() == 1 ? 0 : 180)) * (Math.PI) / 180f);
				;
			}
			
			double minZ;
			double maxZ;
			renderMoss(world, x, y, z, block, renderblocks, mossThickness, handled);
			
			if (hasSnow && !(((BlockCWTFCBranch) block).getSourceX() == 0 && ((BlockCWTFCBranch) block).getSourceZ() == 0))
			{
				minZ = renderblocks.renderMinZ;
				maxZ = renderblocks.renderMaxZ;
				renderblocks.renderMinZ = minZ - snowThickness;
				renderblocks.renderMaxZ = minZ;
				((RenderBlocksWithRotation) renderblocks).renderStandardBlock(Blocks.snow, x, y, z);
				renderblocks.renderMinZ = minZ;
				renderblocks.renderMaxZ = maxZ;
			}
			((RenderBlocksWithRotation) renderblocks).renderStandardBlock(branch, x, y, z);
			
		} else {
			BlockCWTFCFruitTreeBranch branch = (BlockCWTFCFruitTreeBranch) block;
			int meta = world.getBlockMetadata(x, y, z);
			float n = ((BlockCWTFCFruitTreeBranch) block).getDistanceToTrunk(world, x, y, z, 0);
			if (n < 0)
			{
				n = 20;
			}
			width = (0.3f - (n * 0.001f));
			
			if (branch.damageDropped(meta) != Constants.COCONUTID) // Fruit Tree base log thickness
			{
				width *= 0.75f;
			}
			else
			{
				Tessellator.instance.setBrightness(block.getMixedBrightnessForBlock(renderblocks.blockAccess, x, y, z));
				TECWTFCFruitTreeWood te = (TECWTFCFruitTreeWood) world.getTileEntity(x, y, z);
				if (te != null && te.fruitType == Constants.COCONUTID)
				{
					Tessellator.instance.setBrightness(block.getMixedBrightnessForBlock(renderblocks.blockAccess, x, y, z));
					renderblocks = new RenderBlocksWithRotation(renderblocks);
					// This is the date.
					int dist = branch.getDistanceToTrunk(world, x, y, z, 0);
					Random rand = new Random(x + y + z);
					rand = new Random(rand.nextInt(100000));
					if (dist == 1)
					{
						width *= 0.75f;
						Vec3 frondDist = Vec3.createVectorHelper(0, 0, -0.15);
						// Make the fronds and arms tiny
						renderblocks.setRenderBounds(0.475, 0.5, 0.375, 0.525, 0.7, 0.425);
						float dateRot = ((RenderBlocksWithRotation) renderblocks).rot45;
						double dateAngle = -3;
						((RenderBlocksWithRotation) renderblocks).rotation = ((RenderBlocksWithRotation) renderblocks).rot30 / 2f;
						((RenderBlocksWithRotation) renderblocks).renderPalmFrond(x + frondDist.xCoord,
								y + frondDist.yCoord, z + +frondDist.zCoord, Math.PI, dateRot, 0.15, 0.5, dateAngle, 0.8,
								renderCoconutAsBanana);
						// ((RenderBlocksWithRotation)renderblocks).yRotation =
						// ((RenderBlocksWithRotation)renderblocks).rot30;
						// renderblocks.renderStandardBlock(branch, x, y, z);
						for (int i = 0; i < 7; i++)
						{

							((RenderBlocksWithRotation) renderblocks).yRotation += ((RenderBlocksWithRotation) renderblocks).rot45;
							frondDist.rotateAroundY(((RenderBlocksWithRotation) renderblocks).rot45);
							((RenderBlocksWithRotation) renderblocks).renderPalmFrond(x + frondDist.xCoord,
									y + frondDist.yCoord, z + +frondDist.zCoord, Math.PI, dateRot, 0.15, 0.5, dateAngle,
									0.8, renderCoconutAsBanana);
							// renderblocks.renderStandardBlock(branch, x, y, z);
						}

					}
					else if (dist == 2)
					{
						// Make the fronds and arms a little bigger
						// width *= 0.9f;
						width *= 1.25f;
						Vec3 frondDist = Vec3.createVectorHelper(0, 0, -0.2);
						// Make the fronds and arms tiny
						renderblocks.setRenderBounds(0.45, 0.5, 0.35, 0.525, 0.8, 0.425);
						float dateRot = ((RenderBlocksWithRotation) renderblocks).rot30;
						double dateAngle = -1;
						double dateDroop = 1.5;
						((RenderBlocksWithRotation) renderblocks).rotation = ((RenderBlocksWithRotation) renderblocks).rot30 / 2f;
						((RenderBlocksWithRotation) renderblocks).renderPalmFrond(x + frondDist.xCoord,
								y + frondDist.yCoord, z + +frondDist.zCoord, Math.PI,
								dateRot + (rand.nextDouble() - 0.5) * 0.25, 0.4, 0.5, dateAngle, dateDroop, renderCoconutAsBanana);
						// ((RenderBlocksWithRotation)renderblocks).yRotation =
						// ((RenderBlocksWithRotation)renderblocks).rot30;
						// renderblocks.renderStandardBlock(branch, x, y, z);
						for (int i = 0; i < 7; i++)
						{
							((RenderBlocksWithRotation) renderblocks).yRotation += ((RenderBlocksWithRotation) renderblocks).rot45;
							frondDist.rotateAroundY(((RenderBlocksWithRotation) renderblocks).rot45);
							((RenderBlocksWithRotation) renderblocks).renderPalmFrond(x + frondDist.xCoord,
									y + frondDist.yCoord, z + +frondDist.zCoord, Math.PI,
									dateRot + (rand.nextDouble() - 0.5) * 0.25, 0.4, 0.5, dateAngle, dateDroop, renderCoconutAsBanana);
							// renderblocks.renderStandardBlock(branch, x, y, z);
						}
					}
					else if (dist >= 3)
					{
						// Make the fronds full size and have a place for dates to
						// grow.
						width *= 1.5f;
						Vec3 frondDist = Vec3.createVectorHelper(0, 0, -0.2);
						// Make the fronds and arms tiny
						renderblocks.setRenderBounds(0.45, 0.5, 0.35, 0.525, 1, 0.425);
						float dateRot = -((RenderBlocksWithRotation) renderblocks).rot45*1.5f;
						double dateAngle = -5;
						double dateDroop = 0.5;
						double dateScale = 0.4 + ((dist-3)/20d);
						((RenderBlocksWithRotation) renderblocks).rotation = ((RenderBlocksWithRotation) renderblocks).rot30 / 2f;
						((RenderBlocksWithRotation) renderblocks).renderPalmFrond(x + frondDist.xCoord*-1,
								y + frondDist.yCoord, z + +frondDist.zCoord*-1, Math.PI, dateRot,
								dateScale, 0.5, dateAngle, dateDroop, renderCoconutAsBanana);
						// ((RenderBlocksWithRotation)renderblocks).yRotation =
						// ((RenderBlocksWithRotation)renderblocks).rot30;
						// renderblocks.renderStandardBlock(branch, x, y, z);
						for (int i = 0; i < 32; i++)
						{
							((RenderBlocksWithRotation) renderblocks).yRotation += ((RenderBlocksWithRotation) renderblocks).rot45;
							frondDist.rotateAroundY(((RenderBlocksWithRotation) renderblocks).rot45);
							((RenderBlocksWithRotation) renderblocks).renderPalmFrond(x + frondDist.xCoord*((i*0.075)),
									y + frondDist.yCoord, z + +frondDist.zCoord*((i*0.075)), Math.PI, dateRot + (i*0.075),
									dateScale, 0.5, dateAngle, dateDroop, renderCoconutAsBanana);
							if(i%8==0)
							{
								((RenderBlocksWithRotation) renderblocks).yRotation += ((RenderBlocksWithRotation) renderblocks).rot1*15;
								frondDist.rotateAroundY(((RenderBlocksWithRotation) renderblocks).rot1*15);
							}
							// renderblocks.renderStandardBlock(branch, x, y, z);
						}
						if (dist > 7)
						{
							FloraIndex fi = FloraManager.getInstance().findMatchingIndex(Constants.FRUITTREETYPES[Constants.COCONUTID]);
							if (fi != null && TFC_Time.getSeasonAdjustedMonth(z) >= fi.bloomStart && TFC_Time
									.getSeasonAdjustedMonth(z) <= fi.harvestFinish)
							{
								((RenderBlocksWithRotation) renderblocks).yRotation = (float) Math.PI * (2f * rand
										.nextFloat());
								// Make the date spot
								renderblocks.setRenderBounds(0.425, 0.5, 0.425, 0.575, 1, 0.575);
								((RenderBlocksWithRotation) renderblocks).rotation = (float) (Math.PI / 3d);
								((RenderBlocksWithRotation) renderblocks).yRotation += ((RenderBlocksWithRotation) renderblocks).rot22_5;
								renderblocks.renderStandardBlock(branch, x, y, z);
								renderblocks.setRenderBounds(0.4475, 0.7, 0.8475, 0.5725, 1.1625, 0.9725);
								((RenderBlocksWithRotation) renderblocks).rotation = (float) (-4 * Math.PI / 3d);
								// ((RenderBlocksWithRotation)
								// renderblocks).yRotation =
								// ((RenderBlocksWithRotation)
								// renderblocks).rot22_5;
								renderblocks.renderStandardBlock(branch, x, y, z);
								renderblocks.setRenderBounds(0.45, 0.5, 1.2, 0.55, 1.2, 1.3);
								((RenderBlocksWithRotation) renderblocks).rotation = (float) (Math.PI);
								// ((RenderBlocksWithRotation)
								// renderblocks).yRotation =
								// ((RenderBlocksWithRotation)
								// renderblocks).rot22_5;
								renderblocks.renderStandardBlock(branch, x, y, z);

								Vec3 bunchLocation = Vec3.createVectorHelper(0, 0, -0.75);
								bunchLocation.rotateAroundY(((RenderBlocksWithRotation) renderblocks).yRotation);

								if (TFC_Time.getSeasonAdjustedMonth(
										z) > fi.bloomFinish && (te.dayHarvested / TFC_Time.daysInYear) != TFC_Time
												.getTotalDays() / TFC_Time.daysInYear)
								{
									IIcon dates = te.hasFruit ? BlockCWTFCFruitLeaves.iconsFruit[Constants.COCONUTID]
											: BlockCWTFCFruitLeaves.iconsFlowers[Constants.COCONUTID];
									for (int i = 0; i < 8; i++)
									{
										((RenderBlocksWithRotation) renderblocks).renderDiagonalQuad(dates,
												x + bunchLocation.xCoord, y + bunchLocation.yCoord,
												z + bunchLocation.zCoord, 0, 0, 0, 0.75, 2,
												((float) i) * (float) (Math.PI / 4), false);
									}
								}
							}
						}
					}

				}
			}
			
			
	
			renderblocks = new RenderBlocksWithRotation(renderblocks);
	
			width = Math.max(width, 0.1f);
	
			if (((BlockCWTFCFruitTreeBranch) block).isEnd())
			{
				width *= 0.5f;
			}
			
			if ((block == CWTFCBlocks.fruitTreeBranchEnd__y_ || block == CWTFCBlocks.fruitTreeBranchEnd_x__ || block == CWTFCBlocks.fruitTreeBranchEnd___z || block == CWTFCBlocks.fruitTreeBranchEnd_Xy_ || block == CWTFCBlocks.fruitTreeBranchEnd_X__ || block == CWTFCBlocks.fruitTreeBranchEnd_X_z || block == CWTFCBlocks.fruitTreeBranchEnd__yZ || block == CWTFCBlocks.fruitTreeBranchEnd_x_Z || block == CWTFCBlocks.fruitTreeBranchEnd___Z || block == CWTFCBlocks.fruitTreeBranchEnd_xyz || block == CWTFCBlocks.fruitTreeBranchEnd_x_z || block == CWTFCBlocks.fruitTreeBranchEnd_X_Z || block == CWTFCBlocks.fruitTreeBranchEnd_XyZ || block == CWTFCBlocks.fruitTreeBranchEnd_xyZ || block == CWTFCBlocks.fruitTreeBranchEnd_xy_ || block == CWTFCBlocks.fruitTreeBranchEnd__yz || block == CWTFCBlocks.fruitTreeBranchEnd_Xy_) && world
					.getBlockMetadata(x, y, z) == Constants.COCONUTID)
			{
				/*((RenderBlocksWithRotation) renderblocks).renderStandardBlock(TFCBlocks.fauxPalm, x, y, z); TODO only needed for the other type of fruit palm*/
			}
			
			if (block == CWTFCBlocks.fruitTreeBranch__yz || block == CWTFCBlocks.fruitTreeBranch_xy_ || block == CWTFCBlocks.fruitTreeBranch__yZ || block == CWTFCBlocks.fruitTreeBranch_Xy_ || block == CWTFCBlocks.fruitTreeBranch_xyz || block == CWTFCBlocks.fruitTreeBranch_xyZ || block == CWTFCBlocks.fruitTreeBranch_XyZ || block == CWTFCBlocks.fruitTreeBranch_Xyz || block == CWTFCBlocks.fruitTreeBranchEnd__yz || block == CWTFCBlocks.fruitTreeBranchEnd_xy_ || block == CWTFCBlocks.fruitTreeBranchEnd__yZ || block == CWTFCBlocks.fruitTreeBranchEnd_Xy_ || block == CWTFCBlocks.fruitTreeBranchEnd_xyz || block == CWTFCBlocks.fruitTreeBranchEnd_xyZ || block == CWTFCBlocks.fruitTreeBranchEnd_XyZ || block == CWTFCBlocks.fruitTreeBranchEnd_Xyz)
			{
				((RenderBlocksWithRotation) renderblocks).rotation = ((BlockCWTFCFruitTreeBranch) block).isLongDiagonal() ? diagRot
						: rot45;
				renderblocks.setRenderBounds(0.5 - width + 0.025,
						0.5 - (((BlockCWTFCFruitTreeBranch) block).isLongDiagonal() ? sqrt3 : sqrt2), 0.5 - width + 0.025,
						0.5 + width - 0.025, 0.5, 0.5 + width - 0.025);
				handled = true;
			}
			else if (block == CWTFCBlocks.fruitTreeBranch__Yz || block == CWTFCBlocks.fruitTreeBranch_xY_ || block == CWTFCBlocks.fruitTreeBranch__YZ || block == CWTFCBlocks.fruitTreeBranch_XY_ || block == CWTFCBlocks.fruitTreeBranch_xYz || block == CWTFCBlocks.fruitTreeBranch_xYZ || block == CWTFCBlocks.fruitTreeBranch_XYZ || block == CWTFCBlocks.fruitTreeBranch_XYz || block == CWTFCBlocks.fruitTreeBranchEnd__Yz || block == CWTFCBlocks.fruitTreeBranchEnd_xY_ || block == CWTFCBlocks.fruitTreeBranchEnd__YZ || block == CWTFCBlocks.fruitTreeBranchEnd_XY_ || block == CWTFCBlocks.fruitTreeBranchEnd_xYz || block == CWTFCBlocks.fruitTreeBranchEnd_xYZ || block == CWTFCBlocks.fruitTreeBranchEnd_XYZ || block == CWTFCBlocks.fruitTreeBranchEnd_XYz)
			{
				((RenderBlocksWithRotation) renderblocks).rotation = ((BlockCWTFCFruitTreeBranch) block).isLongDiagonal() ? diagRot
						: rot45;

				renderblocks.setRenderBounds(0.5 - width + 0.025, 0.5, 0.5 - width + 0.025, 0.5 + width - 0.025,
						0.5 + (((BlockCWTFCFruitTreeBranch) block).isLongDiagonal() ? sqrt3 : sqrt2), 0.5 + width - 0.025);
				handled = true;
			}
			if (block == CWTFCBlocks.fruitTreeBranch___z || block == CWTFCBlocks.fruitTreeBranch_x__ || block == CWTFCBlocks.fruitTreeBranch___Z || block == CWTFCBlocks.fruitTreeBranch_X__ || block == CWTFCBlocks.fruitTreeBranch_x_z || block == CWTFCBlocks.fruitTreeBranch_x_Z || block == CWTFCBlocks.fruitTreeBranch_X_Z || block == CWTFCBlocks.fruitTreeBranch_X_z || block == CWTFCBlocks.fruitTreeBranchEnd___z || block == CWTFCBlocks.fruitTreeBranchEnd_x__ || block == CWTFCBlocks.fruitTreeBranchEnd___Z || block == CWTFCBlocks.fruitTreeBranchEnd_X__ || block == CWTFCBlocks.fruitTreeBranchEnd_x_z || block == CWTFCBlocks.fruitTreeBranchEnd_x_Z || block == CWTFCBlocks.fruitTreeBranchEnd_X_Z || block == CWTFCBlocks.fruitTreeBranchEnd_X_z)
			{
				((RenderBlocksWithRotation) renderblocks).rotation = (float) (-90 * (Math.PI) / 180f);

				renderblocks.setRenderBounds(0.5 - width, 0.5 - (((BlockCWTFCFruitTreeBranch) block).isShortDiagonal() ? sqrt2 : 1),
						0.5 - width, 0.5 + width, 0.5, 0.5 + width);
				handled = true;
			}
			if (block == CWTFCBlocks.fruitTreeBranch__y_ || block == CWTFCBlocks.fruitTreeBranchEnd__y_)
			{
				((RenderBlocksWithRotation) renderblocks).rotation = 0;
				renderblocks.setRenderBounds(0.5 - width, -0.5, 0.5 - width, 0.5 + width, 0.5, 0.5 + width);
				handled = true;
			}
			// +x-z
			if (block == CWTFCBlocks.fruitTreeBranch_X_z || block == CWTFCBlocks.fruitTreeBranch_Xyz || block == CWTFCBlocks.fruitTreeBranch_XYz || block == CWTFCBlocks.fruitTreeBranchEnd_X_z || block == CWTFCBlocks.fruitTreeBranchEnd_Xyz || block == CWTFCBlocks.fruitTreeBranchEnd_XYz)
			{
				((RenderBlocksWithRotation) renderblocks).yRotation = (float) ((135 + (((BlockCWTFCFruitTreeBranch) block)
						.getSourceY() == 1 ? 0 : 180)) * (Math.PI) / 180f);
			}
			// -z
			if (block == CWTFCBlocks.fruitTreeBranch___z || block == CWTFCBlocks.fruitTreeBranch__yz || block == CWTFCBlocks.fruitTreeBranch__Yz || block == CWTFCBlocks.fruitTreeBranchEnd___z || block == CWTFCBlocks.fruitTreeBranchEnd__yz || block == CWTFCBlocks.fruitTreeBranchEnd__Yz)
			{
				((RenderBlocksWithRotation) renderblocks).yRotation = (float) ((180 + (((BlockCWTFCFruitTreeBranch) block)
						.getSourceY() == 1 ? 0 : 180)) * (Math.PI) / 180f);
			}
			// -x-z
			if (block == CWTFCBlocks.fruitTreeBranch_x_z || block == CWTFCBlocks.fruitTreeBranch_xyz || block == CWTFCBlocks.fruitTreeBranch_xYz || block == CWTFCBlocks.fruitTreeBranchEnd_x_z || block == CWTFCBlocks.fruitTreeBranchEnd_xyz || block == CWTFCBlocks.fruitTreeBranchEnd_xYz)
			{
				((RenderBlocksWithRotation) renderblocks).yRotation = (float) ((225 + (((BlockCWTFCFruitTreeBranch) block)
						.getSourceY() == 1 ? 0 : 180)) * (Math.PI) / 180f);
			}
			// +z
			if (block == CWTFCBlocks.fruitTreeBranch___Z || block == CWTFCBlocks.fruitTreeBranch__yZ || block == CWTFCBlocks.fruitTreeBranch__YZ || block == CWTFCBlocks.fruitTreeBranchEnd___Z || block == CWTFCBlocks.fruitTreeBranchEnd__yZ || block == CWTFCBlocks.fruitTreeBranchEnd__YZ)
			{
				((RenderBlocksWithRotation) renderblocks).yRotation = (float) ((0 + (((BlockCWTFCFruitTreeBranch) block).getSourceY() == 1
						? 0 : 180)) * (Math.PI) / 180f);
			}
			// -x+z
			if (block == CWTFCBlocks.fruitTreeBranch_x_Z || block == CWTFCBlocks.fruitTreeBranch_xyZ || block == CWTFCBlocks.fruitTreeBranch_xYZ || block == CWTFCBlocks.fruitTreeBranchEnd_x_Z || block == CWTFCBlocks.fruitTreeBranchEnd_xyZ || block == CWTFCBlocks.fruitTreeBranchEnd_xYZ)
			{
				((RenderBlocksWithRotation) renderblocks).yRotation = (float) ((315 + (((BlockCWTFCFruitTreeBranch) block)
						.getSourceY() == 1 ? 0 : 180)) * (Math.PI) / 180f);
			}
			// +x+z
			if (block == CWTFCBlocks.fruitTreeBranch_X_Z || block == CWTFCBlocks.fruitTreeBranch_XyZ || block == CWTFCBlocks.fruitTreeBranch_XYZ || block == CWTFCBlocks.fruitTreeBranchEnd_X_Z || block == CWTFCBlocks.fruitTreeBranchEnd_XyZ || block == CWTFCBlocks.fruitTreeBranchEnd_XYZ)
			{
				((RenderBlocksWithRotation) renderblocks).yRotation = (float) ((45 + (((BlockCWTFCFruitTreeBranch) block)
						.getSourceY() == 1 ? 0 : 180)) * (Math.PI) / 180f);
			}
			// +x
			if (block == CWTFCBlocks.fruitTreeBranch_X__ || block == CWTFCBlocks.fruitTreeBranch_Xy_ || block == CWTFCBlocks.fruitTreeBranch_XY_ || block == CWTFCBlocks.fruitTreeBranchEnd_X__ || block == CWTFCBlocks.fruitTreeBranchEnd_Xy_ || block == CWTFCBlocks.fruitTreeBranchEnd_XY_)
			{
				((RenderBlocksWithRotation) renderblocks).yRotation = (float) ((90 + (((BlockCWTFCFruitTreeBranch) block)
						.getSourceY() == 1 ? 0 : 180)) * (Math.PI) / 180f);
			}
			// -x
			if (block == CWTFCBlocks.fruitTreeBranch_x__ || block == CWTFCBlocks.fruitTreeBranch_xy_ || block == CWTFCBlocks.fruitTreeBranch_xY_ || block == CWTFCBlocks.fruitTreeBranchEnd_x__ || block == CWTFCBlocks.fruitTreeBranchEnd_xy_ || block == CWTFCBlocks.fruitTreeBranchEnd_xY_)
			{
				((RenderBlocksWithRotation) renderblocks).yRotation = (float) ((270 + (((BlockCWTFCFruitTreeBranch) block)
						.getSourceY() == 1 ? 0 : 180)) * (Math.PI) / 180f);
				;
			}
			
			double minZ;
			double maxZ;
			renderMoss(world, x, y, z, block, renderblocks, mossThickness, handled);
			
			if (hasSnow && !(((BlockCWTFCFruitTreeBranch) block).getSourceX() == 0 && ((BlockCWTFCFruitTreeBranch) block).getSourceZ() == 0))
			{
				minZ = renderblocks.renderMinZ;
				maxZ = renderblocks.renderMaxZ;
				renderblocks.renderMinZ = minZ - snowThickness;
				renderblocks.renderMaxZ = minZ;
				((RenderBlocksWithRotation) renderblocks).renderStandardBlock(Blocks.snow, x, y, z);
				renderblocks.renderMinZ = minZ;
				renderblocks.renderMaxZ = maxZ;
			}
			((RenderBlocksWithRotation) renderblocks).renderStandardBlock(branch, x, y, z);
		}
		
		((RenderBlocksWithRotation) renderblocks).yRotation = 0;
		((RenderBlocksWithRotation) renderblocks).rotation = 0;
		return true;
	}

	private void renderMoss(IBlockAccess world, int x, int y, int z, Block block, RenderBlocks renderblocks,
			float mossThickness, boolean handled) {
		boolean hasMoss_Y;
		boolean hasMoss_X;
		boolean hasMoss_x;
		boolean hasMoss_z;
		boolean hasMoss_Z;
		boolean hasMoss_y;
		boolean hasMoss_X2;
		boolean hasMoss_x2;
		boolean hasMoss_z2;
		boolean hasMoss_Z2;
		if (!handled)
		{
			System.out.println("not handled");
		}
		Vec3 mossDirection_Y = Vec3.createVectorHelper(0, 1, 0);
		Vec3 mossDirection_y = Vec3.createVectorHelper(0, -1, 0);
		Vec3 mossDirection_X = Vec3.createVectorHelper(1, 0, 0);
		Vec3 mossDirection_x = Vec3.createVectorHelper(-1, 0, 0);
		Vec3 mossDirection_Z = Vec3.createVectorHelper(0, 0, 1);
		Vec3 mossDirection_z = Vec3.createVectorHelper(0, 0, -1);

		Vec3 mossDirection_Xy = Vec3.createVectorHelper(1, -1, 0);
		Vec3 mossDirection_xy = Vec3.createVectorHelper(-1, -1, 0);
		Vec3 mossDirection_Zy = Vec3.createVectorHelper(0, -1, 1);
		Vec3 mossDirection_zy = Vec3.createVectorHelper(0, -1, -1);

		mossDirection_Y.rotateAroundX(((RenderBlocksWithRotation) renderblocks).rotation);
		mossDirection_Y.rotateAroundY(((RenderBlocksWithRotation) renderblocks).yRotation);
		mossDirection_y.rotateAroundX(((RenderBlocksWithRotation) renderblocks).rotation);
		mossDirection_y.rotateAroundY(((RenderBlocksWithRotation) renderblocks).yRotation);

		mossDirection_X.rotateAroundX(((RenderBlocksWithRotation) renderblocks).rotation);
		mossDirection_X.rotateAroundY(((RenderBlocksWithRotation) renderblocks).yRotation);
		mossDirection_x.rotateAroundX(((RenderBlocksWithRotation) renderblocks).rotation);
		mossDirection_x.rotateAroundY(((RenderBlocksWithRotation) renderblocks).yRotation);

		mossDirection_Z.rotateAroundX(((RenderBlocksWithRotation) renderblocks).rotation);
		mossDirection_Z.rotateAroundY(((RenderBlocksWithRotation) renderblocks).yRotation);
		mossDirection_z.rotateAroundX(((RenderBlocksWithRotation) renderblocks).rotation);
		mossDirection_z.rotateAroundY(((RenderBlocksWithRotation) renderblocks).yRotation);

		mossDirection_Xy.rotateAroundX(((RenderBlocksWithRotation) renderblocks).rotation);
		mossDirection_Xy.rotateAroundY(((RenderBlocksWithRotation) renderblocks).yRotation);
		mossDirection_xy.rotateAroundX(((RenderBlocksWithRotation) renderblocks).rotation);
		mossDirection_xy.rotateAroundY(((RenderBlocksWithRotation) renderblocks).yRotation);

		mossDirection_Zy.rotateAroundX(((RenderBlocksWithRotation) renderblocks).rotation);
		mossDirection_Zy.rotateAroundY(((RenderBlocksWithRotation) renderblocks).yRotation);
		mossDirection_zy.rotateAroundX(((RenderBlocksWithRotation) renderblocks).rotation);
		mossDirection_zy.rotateAroundY(((RenderBlocksWithRotation) renderblocks).yRotation);

		hasMoss_Y = world.getBlock((int) Math.round(x + mossDirection_Y.xCoord),
				(int) Math.round(y + mossDirection_Y.yCoord),
				(int) Math.round(z + mossDirection_Y.zCoord)) == TFCBlocks.moss;
		hasMoss_y = world.getBlock((int) Math.round(x + mossDirection_y.xCoord),
				(int) Math.round(y + mossDirection_y.yCoord),
				(int) Math.round(z + mossDirection_y.zCoord)) == TFCBlocks.moss;

		hasMoss_x = world.getBlock((int) Math.round(x + mossDirection_x.xCoord),
				(int) Math.round(y + mossDirection_x.yCoord),
				(int) Math.round(z + mossDirection_x.zCoord)) == TFCBlocks.moss;

		hasMoss_x2 = world.getBlock((int) Math.round(x + mossDirection_xy.xCoord),
				(int) Math.round(y + mossDirection_xy.yCoord),
				(int) Math.round(z + mossDirection_xy.zCoord)) == TFCBlocks.moss;

		hasMoss_X = world.getBlock((int) Math.round(x + mossDirection_X.xCoord),
				(int) Math.round(y + mossDirection_X.yCoord),
				(int) Math.round(z + mossDirection_X.zCoord)) == TFCBlocks.moss;

		hasMoss_X2 = world.getBlock((int) Math.round(x + mossDirection_Xy.xCoord),
				(int) Math.round(y + mossDirection_Xy.yCoord),
				(int) Math.round(z + mossDirection_Xy.zCoord)) == TFCBlocks.moss;

		hasMoss_z = world.getBlock((int) Math.round(x + mossDirection_z.xCoord),
				(int) Math.round(y + mossDirection_z.yCoord),
				(int) Math.round(z + mossDirection_z.zCoord)) == TFCBlocks.moss;

		hasMoss_z2 = world.getBlock((int) Math.round(x + mossDirection_zy.xCoord),
				(int) Math.round(y + mossDirection_zy.yCoord),
				(int) Math.round(z + mossDirection_zy.zCoord)) == TFCBlocks.moss;

		hasMoss_Z = world.getBlock((int) Math.round(x + mossDirection_Z.xCoord),
				(int) Math.round(y + mossDirection_Z.yCoord),
				(int) Math.round(z + mossDirection_Z.zCoord)) == TFCBlocks.moss;

		hasMoss_Z2 = world.getBlock((int) Math.round(x + mossDirection_Zy.xCoord),
				(int) Math.round(y + mossDirection_Zy.yCoord),
				(int) Math.round(z + mossDirection_Zy.zCoord)) == TFCBlocks.moss;

		Tessellator.instance.setColorOpaque(200, 200, 200);
		Tessellator.instance.setBrightness(block.getMixedBrightnessForBlock(renderblocks.blockAccess, x, y, z));
		double minZ, maxZ, minX, maxX, minY, maxY;

		if (hasMoss_Y)
		{
			minY = renderblocks.renderMinY;
			maxY = renderblocks.renderMaxY;
			renderblocks.renderMinY = maxY;
			renderblocks.renderMaxY = maxY + mossThickness;
			((RenderBlocksWithRotation) renderblocks).setRenderBounds(renderblocks.renderMinX - mossThickness,
					renderblocks.renderMinY, renderblocks.renderMinZ - mossThickness,
					renderblocks.renderMaxX + mossThickness, renderblocks.renderMaxY,
					renderblocks.renderMaxZ + mossThickness, 1, 0);

			boolean[][][] mosses = new boolean[3][3][3];
			for (int xx = -1; xx < 2; xx++)
			{
				for (int yy = -1; yy < 2; yy++)
				{
					for (int zz = -1; zz < 2; zz++)
					{
						int X = xx;
						int Y = yy;
						int Z = zz;
						mosses[xx + 1][yy + 1][zz + 1] = renderblocks.blockAccess.getBlock(
								x + X + (int) Math.round(mossDirection_Y.xCoord),
								y + Y + (int) Math.round(mossDirection_Y.yCoord),
								z + Z + (int) Math.round(mossDirection_Y.zCoord)) == TFCBlocks.moss;
					}
				}
			}
			int rot = 0;
			float adjX = -(float) Math.PI / 2f;
			float adj = 0;
			if (mossDirection_Y.yCoord == 1)
			{
				adjX = 0;
			}
			else if (mossDirection_Y.xCoord * mossDirection_Y.xCoord == 1)
			{
				adj = (float) (mossDirection_Y.xCoord * Math.PI / 2d);
				rot = 1 + (int) (mossDirection_Y.xCoord * 1);
			}
			int[] idTexRot = TFC_CoreRender.handleMossFace((RenderBlocksWithRotation) renderblocks,
					x + (int) Math.round(mossDirection_Y.xCoord), y + (int) Math.round(mossDirection_Y.yCoord),
					z + (int) Math.round(mossDirection_Y.zCoord), mosses,
					-((RenderBlocksWithRotation) renderblocks).rotation + adj,
					-((RenderBlocksWithRotation) renderblocks).yRotation + adj, 0, 1, mossDirection_X, mossDirection_Y,
					mossDirection_Z, rot, 0);

			((RenderBlocksWithRotation) renderblocks).renderMossFace(idTexRot[0], idTexRot[1], x, y, z, 1, 0);
			renderblocks.renderMinY = minY;
			renderblocks.renderMaxY = maxY;
		}
		if (hasMoss_y)
		{
			minY = renderblocks.renderMinY;
			maxY = renderblocks.renderMaxY;
			renderblocks.renderMinY = minY - mossThickness;
			renderblocks.renderMaxY = minY;

			boolean[][][] mosses = new boolean[3][3][3];
			for (int xx = -1; xx < 2; xx++)
			{
				for (int yy = -1; yy < 2; yy++)
				{
					for (int zz = -1; zz < 2; zz++)
					{
						int X = xx;
						int Y = yy;
						int Z = zz;
						mosses[xx + 1][yy + 1][zz + 1] = renderblocks.blockAccess.getBlock(
								x + X + (int) Math.round(mossDirection_y.xCoord),
								y + Y + (int) Math.round(mossDirection_y.yCoord),
								z + Z + (int) Math.round(mossDirection_y.zCoord)) == TFCBlocks.moss;
					}
				}
			}
			int rot = 0;
			float adj = (float) Math.PI;
			if (mossDirection_Y.yCoord == 1)
			{
				adj = (float) Math.PI / 2f;
			}
			int[] idTexRot = TFC_CoreRender.handleMossFace((RenderBlocksWithRotation) renderblocks,
					x + (int) Math.round(mossDirection_y.xCoord), y + (int) Math.round(mossDirection_y.yCoord),
					z + (int) Math.round(mossDirection_y.zCoord), mosses,
					-((RenderBlocksWithRotation) renderblocks).rotation,
					-((RenderBlocksWithRotation) renderblocks).yRotation + adj, 0, 0, mossDirection_X, mossDirection_Y,
					mossDirection_Z, rot, 0);

			((RenderBlocksWithRotation) renderblocks).setRenderBounds(renderblocks.renderMinX - mossThickness,
					renderblocks.renderMinY, renderblocks.renderMinZ - mossThickness,
					renderblocks.renderMaxX + mossThickness, renderblocks.renderMaxY,
					renderblocks.renderMaxZ + mossThickness, 0, 0);
			((RenderBlocksWithRotation) renderblocks).renderMossFace(idTexRot[0], idTexRot[1], x, y, z, 0, 0);
			renderblocks.renderMinY = minY;
			renderblocks.renderMaxY = maxY;
		}
		if (hasMoss_x)
		{
			minX = renderblocks.renderMinX;
			maxX = renderblocks.renderMaxX;
			renderblocks.renderMinX = minX - mossThickness;
			renderblocks.renderMaxX = minX;

			boolean[][][] mosses = new boolean[3][3][3];
			for (int xx = -1; xx < 2; xx++)
			{
				for (int yy = -1; yy < 2; yy++)
				{
					for (int zz = -1; zz < 2; zz++)
					{
						int X = xx;
						int Y = yy;
						int Z = zz;
						mosses[xx + 1][yy + 1][zz + 1] = renderblocks.blockAccess.getBlock(
								x + X + (int) Math.round(mossDirection_x.xCoord),
								y + Y + (int) Math.round(mossDirection_x.yCoord),
								z + Z + (int) Math.round(mossDirection_x.zCoord)) == TFCBlocks.moss;
					}
				}
			}
			int rot = 1;
			float adj = (float) -Math.PI;
			float adjZ = (float) (-Math.PI / 2d);
			float adjX = 0;
			if (mossDirection_Y.yCoord == 1)
			{
				adj = (float) -Math.PI / 2f;
			}
			else if (mossDirection_Y.xCoord * mossDirection_Y.xCoord == 1)
			{
				adjZ = (float) -Math.PI;
				adjX = mossDirection_Y.xCoord == -1 ? 0 : (float) -Math.PI;
				adj = mossDirection_Y.xCoord == 1 ? 0 : (float) Math.PI;
			}
			int[] idTexRot = TFC_CoreRender.handleMossFace((RenderBlocksWithRotation) renderblocks,
					x + (int) Math.round(mossDirection_x.xCoord), y + (int) Math.round(mossDirection_x.yCoord),
					z + (int) Math.round(mossDirection_x.zCoord), mosses,
					-((RenderBlocksWithRotation) renderblocks).rotation + adjX,
					-((RenderBlocksWithRotation) renderblocks).yRotation + adj, adjZ, 5, mossDirection_X,
					mossDirection_Y, mossDirection_Z, (int) rot, 1);

			((RenderBlocksWithRotation) renderblocks).setRenderBounds(renderblocks.renderMinX, 0 - mossThickness,
					renderblocks.renderMinZ - mossThickness, renderblocks.renderMaxX,
					renderblocks.renderMaxY + mossThickness, renderblocks.renderMaxZ + mossThickness, 5, 0);
			((RenderBlocksWithRotation) renderblocks).renderMossFace(idTexRot[0], idTexRot[1], x, y, z, 5, 0);
			renderblocks.renderMinX = minX;
			renderblocks.renderMaxX = maxX;
		}
		if (hasMoss_x2)
		{
			minX = renderblocks.renderMinX;
			maxX = renderblocks.renderMaxX;
			renderblocks.renderMinX = minX - mossThickness;
			renderblocks.renderMaxX = minX;

			boolean[][][] mosses = new boolean[3][3][3];
			for (int xx = -1; xx < 2; xx++)
			{
				for (int yy = -1; yy < 2; yy++)
				{
					for (int zz = -1; zz < 2; zz++)
					{
						int X = xx;
						int Y = yy;
						int Z = zz;
						mosses[xx + 1][yy + 1][zz + 1] = renderblocks.blockAccess.getBlock(
								x + X + (int) Math.round(mossDirection_xy.xCoord),
								y + Y + (int) Math.round(mossDirection_xy.yCoord),
								z + Z + (int) Math.round(mossDirection_xy.zCoord)) == TFCBlocks.moss;
					}
				}
			}
			int rot = 1;
			float adj = (float) -Math.PI;
			float adjZ = (float) (-Math.PI / 2d);
			float adjX = 0;
			if (mossDirection_Y.yCoord == 1)
			{
				adj = (float) -Math.PI / 2f;
			}
			else if (mossDirection_Y.xCoord * mossDirection_Y.xCoord == 1)
			{
				adjZ = (float) -Math.PI;
				adjX = mossDirection_Y.xCoord == -1 ? 0 : (float) -Math.PI;
				adj = mossDirection_Y.xCoord == 1 ? 0 : (float) Math.PI;
			}

			int[] idTexRot = TFC_CoreRender.handleMossFace((RenderBlocksWithRotation) renderblocks,
					x + (int) Math.round(mossDirection_xy.xCoord), y + (int) Math.round(mossDirection_xy.yCoord),
					z + (int) Math.round(mossDirection_xy.zCoord), mosses,
					-((RenderBlocksWithRotation) renderblocks).rotation + adjX,
					-((RenderBlocksWithRotation) renderblocks).yRotation + adj, adjZ, 5, mossDirection_X,
					mossDirection_Y, mossDirection_Z, (int) rot, 2);

			((RenderBlocksWithRotation) renderblocks).setRenderBounds(renderblocks.renderMinX,
					renderblocks.renderMinY - mossThickness, renderblocks.renderMinZ - mossThickness,
					renderblocks.renderMaxX, 0 + mossThickness, renderblocks.renderMaxZ + mossThickness, 5, 1);
			((RenderBlocksWithRotation) renderblocks).renderMossFace(idTexRot[0], idTexRot[1], x, y, z, 5, 1);

			renderblocks.renderMinX = minX;
			renderblocks.renderMaxX = maxX;
		}
		if (hasMoss_X)
		{
			minX = renderblocks.renderMinX;
			maxX = renderblocks.renderMaxX;
			renderblocks.renderMinX = maxX;
			renderblocks.renderMaxX = maxX + mossThickness;

			boolean[][][] mosses = new boolean[3][3][3];
			for (int xx = -1; xx < 2; xx++)
			{
				for (int yy = -1; yy < 2; yy++)
				{
					for (int zz = -1; zz < 2; zz++)
					{
						int X = xx;
						int Y = yy;
						int Z = zz;
						mosses[xx + 1][yy + 1][zz + 1] = renderblocks.blockAccess.getBlock(
								x + X + (int) Math.round(mossDirection_X.xCoord),
								y + Y + (int) Math.round(mossDirection_X.yCoord),
								z + Z + (int) Math.round(mossDirection_X.zCoord)) == TFCBlocks.moss;
					}
				}
			}
			int rot = 1;
			float adj = (float) Math.PI;
			float adjZ = (float) (Math.PI / 2d);
			float adjX = 0;
			if (mossDirection_Y.yCoord == 1)
			{
				adj = (float) Math.PI / 2f;
			}
			else if (mossDirection_Y.xCoord * mossDirection_Y.xCoord == 1)
			{
				adjZ = 0;
				adjX = mossDirection_Y.xCoord == -1 ? 0 : (float) -Math.PI;
				adj = mossDirection_Y.xCoord == -1 ? 0 : (float) Math.PI;
			}
			int[] idTexRot = TFC_CoreRender.handleMossFace((RenderBlocksWithRotation) renderblocks,
					x + (int) Math.round(mossDirection_X.xCoord), y + (int) Math.round(mossDirection_X.yCoord),
					z + (int) Math.round(mossDirection_X.zCoord), mosses,
					-((RenderBlocksWithRotation) renderblocks).rotation + adjX,
					-((RenderBlocksWithRotation) renderblocks).yRotation + adj, adjZ, 4, mossDirection_X,
					mossDirection_Y, mossDirection_Z, rot, hasMoss_X2 ? 1 : 0);

			((RenderBlocksWithRotation) renderblocks).setRenderBounds(renderblocks.renderMinX, 0 - mossThickness,
					renderblocks.renderMinZ - mossThickness, renderblocks.renderMaxX,
					renderblocks.renderMaxY + mossThickness, renderblocks.renderMaxZ + mossThickness, 4, 0);
			((RenderBlocksWithRotation) renderblocks).renderMossFace(idTexRot[0], idTexRot[1], x, y, z, 4, 0);
			renderblocks.renderMinX = minX;
			renderblocks.renderMaxX = maxX;
		}
		if (hasMoss_X2)
		{
			minX = renderblocks.renderMinX;
			maxX = renderblocks.renderMaxX;
			renderblocks.renderMinX = maxX;
			renderblocks.renderMaxX = maxX + mossThickness;

			boolean[][][] mosses = new boolean[3][3][3];
			for (int xx = -1; xx < 2; xx++)
			{
				for (int yy = -1; yy < 2; yy++)
				{
					for (int zz = -1; zz < 2; zz++)
					{
						int X = xx;
						int Y = yy;
						int Z = zz;
						mosses[xx + 1][yy + 1][zz + 1] = renderblocks.blockAccess.getBlock(
								x + X + (int) Math.round(mossDirection_Xy.xCoord),
								y + Y + (int) Math.round(mossDirection_Xy.yCoord),
								z + Z + (int) Math.round(mossDirection_Xy.zCoord)) == TFCBlocks.moss;
					}
				}
			}
			int rot = 1;
			float adj = (float) Math.PI;
			float adjZ = (float) (Math.PI / 2d);
			float adjX = 0;
			if (mossDirection_Y.yCoord == 1)
			{
				adj = (float) Math.PI / 2f;
			}
			else if (mossDirection_Y.xCoord * mossDirection_Y.xCoord == 1)
			{
				adjZ = 0;// (float) -Math.PI;
				adjX = mossDirection_Y.xCoord == -1 ? 0 : (float) -Math.PI;
				adj = mossDirection_Y.xCoord == -1 ? 0 : (float) Math.PI;
			}
			int[] idTexRot = TFC_CoreRender.handleMossFace((RenderBlocksWithRotation) renderblocks,
					x + (int) Math.round(mossDirection_Xy.xCoord), y + (int) Math.round(mossDirection_Xy.yCoord),
					z + (int) Math.round(mossDirection_Xy.zCoord), mosses,
					-((RenderBlocksWithRotation) renderblocks).rotation + adjX,
					-((RenderBlocksWithRotation) renderblocks).yRotation + adj, adjZ, 4, mossDirection_X,
					mossDirection_Y, mossDirection_Z, rot, 2);

			((RenderBlocksWithRotation) renderblocks).setRenderBounds(renderblocks.renderMinX,
					renderblocks.renderMinY - mossThickness, renderblocks.renderMinZ - mossThickness,
					renderblocks.renderMaxX, 0 + mossThickness, renderblocks.renderMaxZ + mossThickness, 4, 1);
			((RenderBlocksWithRotation) renderblocks).renderMossFace(idTexRot[0], idTexRot[1], x, y, z, 4, 1);
			renderblocks.renderMinX = minX;
			renderblocks.renderMaxX = maxX;
		}
		if (hasMoss_z)
		{
			minZ = renderblocks.renderMinZ;
			maxZ = renderblocks.renderMaxZ;
			renderblocks.renderMinZ = minZ - mossThickness;
			renderblocks.renderMaxZ = minZ;
			((RenderBlocksWithRotation) renderblocks).setRenderBounds(renderblocks.renderMinX - mossThickness,
					renderblocks.renderMinY - mossThickness, renderblocks.renderMinZ,
					renderblocks.renderMaxX + mossThickness, renderblocks.renderMaxY + mossThickness,
					renderblocks.renderMaxZ, 3, 0);
			((RenderBlocksWithRotation) renderblocks).renderMossFace(0, 0, x, y, z, 3, 0);
			renderblocks.renderMinZ = minZ;
			renderblocks.renderMaxZ = maxZ;
		}
		if (hasMoss_Z)
		{
			minZ = renderblocks.renderMinZ;
			maxZ = renderblocks.renderMaxZ;
			renderblocks.renderMinZ = maxZ;
			renderblocks.renderMaxZ = maxZ + mossThickness;
			((RenderBlocksWithRotation) renderblocks).setRenderBounds(renderblocks.renderMinX - mossThickness,
					renderblocks.renderMinY - mossThickness, renderblocks.renderMinZ,
					renderblocks.renderMaxX + mossThickness, renderblocks.renderMaxY + mossThickness,
					renderblocks.renderMaxZ, 2, 0);
			((RenderBlocksWithRotation) renderblocks).renderMossFace(0, 0, x, y, z, 2, 0);
			renderblocks.renderMinZ = minZ;
			renderblocks.renderMaxZ = maxZ;
		}
		if (hasMoss_Z || hasMoss_z || hasMoss_Y || hasMoss_y || hasMoss_X || hasMoss_x || hasMoss_Z2 || hasMoss_z2 || hasMoss_X2 || hasMoss_x2)
		{
			((RenderBlocksWithRotation) renderblocks).renderStandardBlock(TFCBlocks.moss, x, y, z);
		}
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getRenderId()
	{
		// TODO Auto-generated method stub
		return 0;
	}
}
