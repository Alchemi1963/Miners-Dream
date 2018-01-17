package com.lavaingot.minersdream.objects.gui;

import com.lavaingot.minersdream.objects.container.ContainerBlockContainer;
import com.lavaingot.minersdream.objects.tileentities.TileEntityContainer;
import com.lavaingot.minersdream.util.Reference;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GUIBlockContainer extends GuiContainer{

	private TileEntityContainer te;
	private IInventory inventoryIn;	
	private String name;
	
	public GUIBlockContainer(IInventory inventoryIn, TileEntityContainer te) {
		super(new ContainerBlockContainer(inventoryIn, te));
		
		this.te = te;
		this.inventoryIn = inventoryIn;
		this.name = Reference.BLOCK_CONTAINER_CONTAINER;
		
		this.xSize = 176;
		this.ySize = 166;
	}

	
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/gui/container/block_container.png"));
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
		
		String s = I18n.format(this.name);
		this.mc.fontRenderer.drawString(s, this.xSize / 2 - this.mc.fontRenderer.getStringWidth(s) / 2, 6, 4210752); //Draws the block breaker name in the center on the top of the gui
		this.mc.fontRenderer.drawString(this.inventoryIn.getDisplayName().getFormattedText(), 8, 72, 4210752); //The player's inventory name
		
		int actualMouseX = mouseX - ((this.width - this.xSize) / 2);
		int actualMouseY = mouseY - ((this.height - this.ySize) / 2);
		
		this.renderHoveredToolTip(actualMouseX, actualMouseY);
	}

}
