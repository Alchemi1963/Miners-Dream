package com.lavaingot.minersdream.objects.gui;

import com.lavaingot.minersdream.objects.container.ContainerAlloyingFurnace;
import com.lavaingot.minersdream.objects.progressbar.ProgressBar;
import com.lavaingot.minersdream.objects.progressbar.ProgressBar.PBDirection;
import com.lavaingot.minersdream.objects.tileentities.TileAlloyingFurnace;
import com.lavaingot.minersdream.util.Reference;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GUIAlloyingFurnace extends GuiContainer{

	private static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MOD_ID + ":textures/gui/container/alloying_furnace.png");
	private final InventoryPlayer player;
	private final TileAlloyingFurnace tileentity;
	
	private ProgressBar burnProgress;
	private ProgressBar smeltProgress;
	
	public GUIAlloyingFurnace(InventoryPlayer player, TileAlloyingFurnace tileentity) {
		
		super(new ContainerAlloyingFurnace(player, tileentity));
		this.player = player;
		this.tileentity = tileentity;
		
		this.burnProgress = new ProgressBar(TEXTURES, PBDirection.BOTTOM_TOP, 57, 41, 14, 14, 176, 0);
		this.smeltProgress = new ProgressBar(TEXTURES, PBDirection.LEFT_RIGHT, 79, 39, 24, 17, 176, 14);
		
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
		
		int burn, burnTime, smelt, smeltTime;
		burn = tileentity.getField(0);
		burnTime = tileentity.getField(1);
		smelt = tileentity.getField(2);
		smeltTime = tileentity.getField(3);
		
		String tileName = this.tileentity.getDisplayName().getUnformattedText();
		this.fontRenderer.drawString(tileName, (this.xSize/2 - this.fontRenderer.getStringWidth(tileName)/2), 5, 4210752);
		this.fontRenderer.drawString(this.player.getDisplayName().getFormattedText(), 8, 74, 4210752);
		
		this.burnProgress.setMin(burn).setMax(burnTime);
		this.burnProgress.draw(this.mc);
		
		this.smeltProgress.setMin(smelt).setMax(smeltTime);
		this.smeltProgress.draw(this.mc);
		
		int actualMouseX = mouseX - ((this.width - this.xSize) / 2);
		int actualMouseY = mouseY - ((this.height - this.ySize) / 2);
		
		if (!(actualMouseX >= 148 && actualMouseY >= 10 && actualMouseX <= 165 && actualMouseY <= 27)) this.renderHoveredToolTip(actualMouseX, actualMouseY);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		
		GlStateManager.color(1.0F, 1.0F, 1.0F);
		
		this.mc.getTextureManager().bindTexture(TEXTURES);
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
	}
}
