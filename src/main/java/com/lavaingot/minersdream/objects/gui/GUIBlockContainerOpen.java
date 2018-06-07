package com.lavaingot.minersdream.objects.gui;

import java.awt.Color;
import java.io.IOException;
import java.util.logging.Level;

import com.lavaingot.minersdream.Main;
import com.lavaingot.minersdream.objects.blocks.BlockContainer;
import com.lavaingot.minersdream.objects.container.ContainerBlockContainerOpen;
import com.lavaingot.minersdream.objects.tileentities.TileContainer;
import com.lavaingot.minersdream.util.Reference;
import com.lavaingot.minersdream.util.handlers.MinersdreamPacketHandler;
import com.lavaingot.minersdream.util.handlers.MinersdreamPacketHandler.OpenMessage;
import com.lavaingot.minersdream.util.handlers.MinersdreamPacketHandler.OwnerMessage;
import com.lavaingot.minersdream.util.handlers.MinersdreamPacketHandler.CodeMessage;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.WorldType;

public class GUIBlockContainerOpen extends GuiContainer{

	private TileContainer te;
	private IInventory inventoryIn;	
	private String name;
	private EntityPlayer player;
	
	private String[] code_input = new String[]{"", "", "", ""};
	private String status ="";
	private int status_color = new Color(0,0,0).getRGB();
	
	
	public GUIBlockContainerOpen(IInventory inventoryIn, TileContainer te, EntityPlayer player) {
		super(new ContainerBlockContainerOpen(inventoryIn, te));
		
		this.player = player;
		this.te = te;
		this.inventoryIn = inventoryIn;
		this.name = Reference.BLOCK_CONTAINER_CONTAINER;
		
		this.xSize = 176;
		this.ySize = 188;
	}
	
	@Override
	public void initGui() {
		super.initGui();
		
		this.buttonList.add(new MinersButton(1, 59 + this.guiLeft, 39 + this.guiTop, 15, 10, "1"));
		this.buttonList.add(new MinersButton(2, 81 + this.guiLeft, 39 + this.guiTop, 15, 10, "2"));
		this.buttonList.add(new MinersButton(3, 103+ this.guiLeft, 39 + this.guiTop, 15, 10, "3"));
		this.buttonList.add(new MinersButton(4, 59 + this.guiLeft, 56 + this.guiTop, 15, 10, "4"));
		this.buttonList.add(new MinersButton(5, 81 + this.guiLeft, 56 + this.guiTop, 15, 10, "5"));
		this.buttonList.add(new MinersButton(6, 103+ this.guiLeft, 56 + this.guiTop, 15, 10, "6"));
		this.buttonList.add(new MinersButton(7, 59 + this.guiLeft, 73 + this.guiTop, 15, 10, "7"));
		this.buttonList.add(new MinersButton(8, 81 + this.guiLeft, 73 + this.guiTop, 15, 10, "8"));
		this.buttonList.add(new MinersButton(9, 103+ this.guiLeft, 73 + this.guiTop, 15, 10, "9"));
		this.buttonList.add(new MinersButton(0, 81 + this.guiLeft, 97 + this.guiTop, 15, 10, "0"));
		
		this.buttonList.add(new MinersButton(10,59 + this.guiLeft, 97 + this.guiTop, 15, 10, "DEL"));
		this.buttonList.add(new MinersButton(11,103+ this.guiLeft, 97 + this.guiTop, 15, 10, "CLR"));
		
		this.buttonList.add(new GuiButton(12, 136 + this.guiLeft, 88 + this.guiTop, 32, 22, "DONE"));
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
				
		if (button.id < 10) {
			if (isInputEmpty(this.code_input)) this.code_input[0] = String.valueOf(button.id);
			else if (this.code_input[1] == null || this.code_input[1].isEmpty() || this.code_input[1] == "") this.code_input[1] = String.valueOf(button.id);
			else if (this.code_input[2] == null || this.code_input[2].isEmpty() || this.code_input[2] == "") this.code_input[2] = String.valueOf(button.id);
			else if (this.code_input[3] == null ||this.code_input[3].isEmpty() || this.code_input[3] == "") this.code_input[3] = String.valueOf(button.id);
		
		} else if (button.id == 10 && !isInputEmpty(code_input)) {
			
			for (int i = 3; i >= 0; i--) { 
				String s = code_input[i];
				
				if (s != null) {
					if (!s.isEmpty() && s != "") {
						code_input[i] = "";
						break;
					}
				}
			}
		
		} else if (button.id == 11) {
			this.code_input = new String[] {"", "", "", ""};
		} else if (button.id == 12) {
			
			if (isInputFull(code_input)) {
			
				if (!te.hasCode()) {
					
					te.owner = player.getName();
					te.setCode(code_input);
					this.status = "CODE SET";
					
					MinersdreamPacketHandler.INSTANCE.sendToServer(new CodeMessage(te.getCode(), te.getPos()));
					MinersdreamPacketHandler.INSTANCE.sendToServer(new OwnerMessage(te.owner, te.getPos()));
					te.owner = player.getName();
					te.setCode(code_input);
					
				}
				else if (te.checkCode(code_input)) {
					
					te.setOpened(true);
					this.status = "CORRECT";
					this.status_color = new Color(36, 142, 36).getRGB();
					
					MinersdreamPacketHandler.INSTANCE.sendToServer(new OpenMessage(true, te.getPos()));
					
					te.setOpened(true);
					
				} else if (!te.checkCode(code_input)) {
					
					this.status = "WRONG";
					this.status_color = new Color(142, 36, 36).getRGB();
				}
			}
			this.code_input = new String[] {"", "", "", ""};
		}
	}
	
	protected boolean isInputEmpty(String[] input) {
		for (String s : input) {
			if (s != null) {
				if (!s.isEmpty() || s != "") {
					return false;
				}
			}
		}
		return true;
	}
	
	protected boolean isInputFull(String[] input){
		
		for (String s : input) {
			if (s == null) {
				return false;
			} else if (s.isEmpty() || s == "") {
				return false;
			}
		}
		return true;
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/gui/container/block_container_open.png"));
		this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
		
		String s = I18n.format(this.name);
		this.mc.fontRenderer.drawString(s, this.xSize / 2 - this.mc.fontRenderer.getStringWidth(s) / 2, 4, 4210752);
		
		this.mc.fontRenderer.drawString(status, 8, 110 - this.mc.fontRenderer.FONT_HEIGHT, this.status_color);
		this.mc.fontRenderer.drawString(code_input[0], 51 - this.mc.fontRenderer.getStringWidth(code_input[0]) / 2, 15, new Color(0, 59, 111).getRGB(), true);
		this.mc.fontRenderer.drawString(code_input[1], 76 - this.mc.fontRenderer.getStringWidth(code_input[1]) / 2, 15, new Color(0, 59, 111).getRGB(), true);
		this.mc.fontRenderer.drawString(code_input[2], 99 - this.mc.fontRenderer.getStringWidth(code_input[2]) / 2, 15, new Color(0, 59, 111).getRGB(), true);
		this.mc.fontRenderer.drawString(code_input[3], 124 - this.mc.fontRenderer.getStringWidth(code_input[3]) / 2, 15, new Color(0, 59, 111).getRGB(), true);
		
		int actualMouseX = mouseX - ((this.width - this.xSize) / 2);
		int actualMouseY = mouseY - ((this.height - this.ySize) / 2);
		
		
		this.renderHoveredToolTip(actualMouseX, actualMouseY);
	}

}
