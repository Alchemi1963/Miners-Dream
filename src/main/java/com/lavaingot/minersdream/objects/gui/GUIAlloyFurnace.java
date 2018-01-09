package com.lavaingot.minersdream.objects.gui;

import com.lavaingot.minersdream.objects.container.ContainerAlloyFurnace;
import com.lavaingot.minersdream.objects.progressbar.ProgressBar;
import com.lavaingot.minersdream.objects.tileentities.TileEntityAlloyFurnace;
import com.lavaingot.minersdream.objects.tileentities.TileEntityContainer;
import com.lavaingot.minersdream.util.Reference;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GUIAlloyFurnace extends GuiContainer{

	private TileEntityAlloyFurnace te;
	private IInventory inventoryIn;	
	private String name;
	
	private ProgressBar burner;
	
	private int burn, burnTime = 0;
	
	public GUIAlloyFurnace(IInventory inventoryIn, TileEntityAlloyFurnace te, EntityPlayer player) {
		super(new ContainerAlloyFurnace(inventoryIn, te, player));
		
		this.te = te;
		this.inventoryIn = inventoryIn;
		
		this.name = "container.alloy_furnace";
		
		this.xSize = 176;
		this.ySize = 166;
		
		this.burner = new ProgressBar(new ResourceLocation(Reference.MOD_ID, "textures/gui/container/alloy_furnace.png"), ProgressBar.PBDirection.BOTTOM_TOP, 57, 41, 14, 14, 176, 0);
	}
	

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/gui/container/alloy_furnace.png"));
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
		
		String s = I18n.format(this.name);
		this.mc.fontRenderer.drawString(s, this.xSize / 2 - this.mc.fontRenderer.getStringWidth(s) / 2, 6, 4210752); //Draws the block breaker name in the center on the top of the gui
		this.mc.fontRenderer.drawString(this.inventoryIn.getDisplayName().getFormattedText(), 8, 72, 4210752); //The player's inventory name
		
		this.burner.setMin(te.burn).setMax(te.burnTime);
		this.burner.draw(this.mc);
		
		int actualMouseX = mouseX - ((this.width - this.xSize) / 2);
		int actualMouseY = mouseY - ((this.height - this.ySize) / 2);
	}

}
