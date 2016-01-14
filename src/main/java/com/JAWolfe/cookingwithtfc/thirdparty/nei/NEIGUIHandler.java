package com.JAWolfe.cookingwithtfc.thirdparty.nei;

import com.bioxx.tfc.GUI.GuiHealth;
import com.bioxx.tfc.GUI.GuiInventoryTFC;
import com.bioxx.tfc.GUI.GuiSkills;

import codechicken.nei.api.INEIGuiAdapter;
import net.minecraft.client.gui.inventory.GuiContainer;

public class NEIGUIHandler extends INEIGuiAdapter
{
	@Override
    public boolean hideItemPanelSlot(GuiContainer gui, int x, int y, int w, int h)
    {
        	return (gui instanceof GuiInventoryTFC || gui instanceof GuiSkills || gui instanceof GuiHealth) &&
        			x < (gui.width - 176)/2 + 201 && y + h > (gui.height - 172)/2 + 3 && y < (gui.height - 172)/2 + 80;
    }
}
