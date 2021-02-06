package straywolfe.cookingwithtfc.client;

import org.lwjgl.opengl.GL11;

import com.dunk.tfc.Handlers.Client.BlockRenderHandler;
import com.dunk.tfc.Render.TFC_CoreRender;
import com.dunk.tfc.Render.Blocks.RenderAnvil;
import com.dunk.tfc.Render.Blocks.RenderCrop;
import com.dunk.tfc.Render.Blocks.RenderDetailed;
import com.dunk.tfc.Render.Blocks.RenderGrass;
import com.dunk.tfc.api.TFCBlocks;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import straywolfe.cookingwithtfc.api.CWTFCBlocks;
import straywolfe.cookingwithtfc.client.render.CWTFCRenderer;

public class CWTFCBlockRenderHandler extends BlockRenderHandler {
	@Override
	public boolean renderWorldBlock(IBlockAccess world, int i, int j, int k, Block block, int modelId, RenderBlocks renderer)
	{
		if (modelId == CWTFCBlocks.fruitTreeLeavesRenderID)
		{
			return CWTFCRenderer.renderFruitLeaves(block, i, j, k, renderer);
		}
		else if (modelId == CWTFCBlocks.woodFruitRenderId)
		{
			return CWTFCRenderer.renderWoodTrunk(block, i, j, k, renderer);
		}
		return false;
	}

	private static void drawCrossedSquares(Block block, double x, double y, double z, RenderBlocks renderblocks, double width, double height)
	{
		Tessellator tess = Tessellator.instance;
		GL11.glColor3f(1, 1, 1);

		int brightness = block.getMixedBrightnessForBlock(renderblocks.blockAccess, (int)x, (int)y, (int)z);
		tess.setBrightness(brightness);
		tess.setColorOpaque_F(1, 1, 1);

		IIcon icon = block.getIcon(renderblocks.blockAccess, (int)x, (int)y, (int)z, renderblocks.blockAccess.getBlockMetadata((int)x, (int)y, (int)z));
		if (renderblocks.hasOverrideBlockTexture())
			icon = renderblocks.overrideBlockTexture;

		double minU = icon.getMinU();
		double maxU = icon.getMaxU();
		double minV = icon.getMinV();
		double maxV = icon.getMaxV();

		double minX = x + 0.5D - width;
		double maxX = x + 0.5D + width;
		double minZ = z + 0.5D - width;
		double maxZ = z + 0.5D + width;

		tess.addVertexWithUV(minX, y + height, minZ, minU, minV);
		tess.addVertexWithUV(minX, y + 0.0D, minZ, minU, maxV);
		tess.addVertexWithUV(maxX, y + 0.0D, maxZ, maxU, maxV);
		tess.addVertexWithUV(maxX, y + height, maxZ, maxU, minV);

		tess.addVertexWithUV(maxX, y + height, maxZ, minU, minV);
		tess.addVertexWithUV(maxX, y + 0.0D, maxZ, minU, maxV);
		tess.addVertexWithUV(minX, y + 0.0D, minZ, maxU, maxV);
		tess.addVertexWithUV(minX, y + height, minZ, maxU, minV);

		tess.addVertexWithUV(minX, y + height, maxZ, minU, minV);
		tess.addVertexWithUV(minX, y + 0.0D, maxZ, minU, maxV);
		tess.addVertexWithUV(maxX, y + 0.0D, minZ, maxU, maxV);
		tess.addVertexWithUV(maxX, y + height, minZ, maxU, minV);

		tess.addVertexWithUV(maxX, y + height, minZ, minU, minV);
		tess.addVertexWithUV(maxX, y + 0.0D, minZ, minU, maxV);
		tess.addVertexWithUV(minX, y + 0.0D, maxZ, maxU, maxV);
		tess.addVertexWithUV(minX, y + height, maxZ, maxU, minV);
	}

	
	private void renderInvBlock(Block block, RenderBlocks renderer, IIcon[] icons)
	{
		Tessellator tessellator = Tessellator.instance;
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, -1F, 0.0F);
		renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, icons[0]);
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 1.0F, 0.0F);
		renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, icons[1]);
		renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSide(1));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, -1F);
		renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, icons[2]);
		renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSide(2));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, 1.0F);
		renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, icons[3]);
		renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSide(3));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(-1F, 0.0F, 0.0F);
		renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, icons[4]);
		renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSide(4));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(1.0F, 0.0F, 0.0F);
		renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, icons[5]);
		renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSide(5));
		tessellator.draw();
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
	}
}
