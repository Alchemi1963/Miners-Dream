package com.lavaingot.minersdream.objects.gui;

import com.lavaingot.minersdream.objects.blocks.machines.mechanical_combiner.TileMechanicalCombiner;
import com.lavaingot.minersdream.objects.container.ContainerMechanicalCombiner;
import com.lavaingot.minersdream.objects.progressbar.ProgressBar;
import com.lavaingot.minersdream.objects.progressbar.ProgressBar.PBDirection;
import com.lavaingot.minersdream.util.Reference;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GUIMechanicalCombiner extends GuiContainer{

	private static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MOD_ID + ":textures/gui/container/mechanical_combiner.png");
	private final InventoryPlayer player;
	private final TileMechanicalCombiner tileentity;
	
	private ProgressBar burnProgress;
	private ProgressBar procesProgress;
	
	public GUIMechanicalCombiner(InventoryPlayer player, TileMechanicalCombiner tileentity) {
		
		super(new ContainerMechanicalCombiner(player, tileentity));
		
		this.player = player;
		this.tileentity = tileentity;
		
		this.burnProgress = new ProgressBar(TEXTURES, PBDirection.RIGHT_LEFT, 110, 66, 38, 10, 176, 0);
		this.procesProgress = new ProgressBar(TEXTURES, PBDirection.LEFT_RIGHT, 80, 39, 22, 16, 176, 10);
		
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
		
		int burn, burnTime, procesTime, procesTimeTotal;
		burn = tileentity.getField(0);
		burnTime = tileentity.getField(1);
		procesTime = tileentity.getField(2);
		procesTimeTotal = tileentity.getField(3);
		
		String tileName = this.tileentity.getDisplayName().getUnformattedText();
		this.fontRenderer.drawString(tileName, (this.xSize/2 - this.fontRenderer.getStringWidth(tileName)/2), 5, 4210752);
		this.fontRenderer.drawString(this.player.getDisplayName().getFormattedText(), 8, 72, 4210752);
		
		this.burnProgress.setMin(burn).setMax(burnTime);
		this.burnProgress.draw(this.mc);
		
		this.procesProgress.setMin(procesTime).setMax(procesTimeTotal);
		this.procesProgress.draw(this.mc);
		
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

