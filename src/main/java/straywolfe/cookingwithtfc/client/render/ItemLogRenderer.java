package straywolfe.cookingwithtfc.client.render;

import com.dunk.tfc.Core.TFC_Core;
import net.minecraft.block.Block;
import net.minecraft.client.model.TexturedQuad;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import straywolfe.cookingwithtfc.api.CWTFCBlocks;

public class ItemLogRenderer implements IItemRenderer {

	private TexturedQuad[] quadList;

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;//type == ItemRenderType.INVENTORY || type == ItemRenderType.EQUIPPED;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return true;

	} 	

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		//Get the block we want
		int meta = item.getItemDamage();
		Block b;
		b = CWTFCBlocks.woodVert;
		TFC_Core.bindTexture(TextureMap.locationBlocksTexture);

		if(type == ItemRenderType.INVENTORY){
			GL11.glPushMatrix();
			//GL11.glTranslatef(8, 8, 8);
			//GL11.glScalef(10, 10, 10);
			//GL11.glRotatef(45, 0, 1, 0);
			//GL11.glPushMatrix();
			//GL11.glRotatef(30, 1, 0, 1);
			RenderBlocks.getInstance().renderBlockAsItem(b, meta, 1F);
			//GL11.glPopMatrix();
			GL11.glPopMatrix();
		}
		else if(type == ItemRenderType.EQUIPPED)
		{

			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glPushMatrix();
			GL11.glTranslatef(0.5f, 0.5f, 0.5f);
			//GL11.glTranslatef(0, 0.2f,0);
			//GL11.glPushAttrib(GL11.GL_ENABLE_BIT);
			//GL11.glEnable(GL11.GL_DEPTH_TEST);
			//GL11.glEnable(GL11.GL_BLEND);
			//GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			RenderBlocks.getInstance().renderBlockAsItem(b, meta, 1F);
			//GL11.glPopAttrib();
			GL11.glPopMatrix();
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		}
		else{
			/*
			 * This is what causes the problem: the item is an ITEM so it's automatically given this:
			 * 	GL11.glRotatef(50.0F, 0.0F, 1.0F, 0.0F);
            	GL11.glRotatef(335.0F, 0.0F, 0.0F, 1.0F);
            	GL11.glTranslatef(-0.9375F, -0.0625F, 0.0F);
			 * 
			 */

			//GL11.glRotatef(90, 1, 0, 0);
			//GL11.glRotatef(-335.0F, 0, 0, 1);
			//GL11.glRotatef(-50, 0, 1, 0);
			//GL11.glPopMatrix();
			GL11.glPushMatrix();
			//GL11.glScalef(0.5F, 0.5F, 0.5F);
			GL11.glTranslatef(0.5f, 0.5f, 0.5f);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			//GL11.glPushAttrib(GL11.GL_ENABLE_BIT);
			//GL11.glEnable(GL11.GL_DEPTH_TEST);
			//GL11.glEnable(GL11.GL_BLEND);
			//GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			RenderBlocks.getInstance().renderBlockAsItem(b, meta, 1F);
			//GL11.glPopAttrib();
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glPopMatrix();
			//GL11.glPushMatrix();
		}

		//renderInvBlock(b,meta,RenderBlocks.getInstance());
	}

	public static void renderInvBlock(Block block, int m, RenderBlocks renderer)
	{
		/*Tessellator var14 = Tessellator.instance;
		GL11.glPushMatrix();

		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		var14.startDrawingQuads();
		var14.setNormal(0.0F, -1.0F, 0.0F);
		renderer.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(0, m));
		var14.draw();
		var14.startDrawingQuads();
		var14.setNormal(0.0F, 1.0F, 0.0F);
		renderer.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(1, m));
		var14.draw();
		var14.startDrawingQuads();
		var14.setNormal(0.0F, 0.0F, -1.0F);
		renderer.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(2, m));
		var14.draw();
		var14.startDrawingQuads();
		var14.setNormal(0.0F, 0.0F, 1.0F);
		renderer.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(3, m));
		var14.draw();
		var14.startDrawingQuads();
		var14.setNormal(-1.0F, 0.0F, 0.0F);
		renderer.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(4, m));
		var14.draw();
		var14.startDrawingQuads();
		var14.setNormal(1.0F, 0.0F, 0.0F);
		renderer.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(5, m));
		var14.draw();
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
		GL11.glPopMatrix();*/
	}

}
