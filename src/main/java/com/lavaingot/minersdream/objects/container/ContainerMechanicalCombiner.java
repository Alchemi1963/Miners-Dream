package com.lavaingot.minersdream.objects.container;

import com.lavaingot.minersdream.init.BlockInit;
import com.lavaingot.minersdream.objects.blocks.machines.mechanical_combiner.TileMechanicalCombiner;
import com.lavaingot.minersdream.objects.container.slots.SlotItemFuelMech;
import com.lavaingot.minersdream.objects.container.slots.SlotItemOutput;
import com.lavaingot.minersdream.objects.container.slots.SlotItemTool;
import com.lavaingot.minersdream.objects.container.slots.SlotPreview;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerMechanicalCombiner extends Container{

	private final TileMechanicalCombiner tileentity;
	private int burn, burnTime, procesTime, procesTimeTotal;
	
	public ContainerMechanicalCombiner(InventoryPlayer player, TileMechanicalCombiner tile) {
		
		this.tileentity = tile;
		
		this.addSlotToContainer(new SlotItemFuelMech(this.tileentity, 0, 149, 63)); //fuel
		this.addSlotToContainer(new SlotItemTool(this.tileentity, 1, 34, 30));
		this.addSlotToContainer(new SlotItemTool(this.tileentity, 2, 52, 30));
		this.addSlotToContainer(new SlotItemTool(this.tileentity, 3, 34, 48));
		this.addSlotToContainer(new SlotItemTool(this.tileentity, 4, 52, 48));
		this.addSlotToContainer(new SlotItemOutput(player.player, this.tileentity, 5, 116, 39));
		
		Slot preview = new SlotPreview(player.player, this.tileentity, 6, 149, 11);
		preview.putStack(new ItemStack(BlockInit.MECHANICAL_COMBINER));
		
		this.addSlotToContainer(preview);
		
		int xPos = 8;
		int yPos = 84;
		
		
		for(int y = 0; y < 3; y++) {
			for(int x = 0; x < 9; x++) {
				this.addSlotToContainer(new Slot(player, x + y * 9 + 9, xPos + x * 18, yPos + y * 18));
			}
		}
		
		for(int x = 0; x < 9; x++) {
			this.addSlotToContainer(new Slot(player, x, xPos + x * 18, 142));
		}
	}
	
	@Override
	public void addListener(IContainerListener listener) {
		
		super.addListener(listener);
		listener.sendAllWindowProperties(this, this.tileentity);
	}
	
	@Override
	public void detectAndSendChanges() {
		
		super.detectAndSendChanges();
		
		for (int i = 0; i < this.listeners.size(); i++) {
			IContainerListener listener = (IContainerListener)this.listeners.get(i);
			
			if(this.burn != this.tileentity.getField(0)) listener.sendWindowProperty(this, 0, this.tileentity.getField(0));
			if(this.burnTime != this.tileentity.getField(1)) listener.sendWindowProperty(this, 1, this.tileentity.getField(1));
			if(this.procesTime != this.tileentity.getField(2)) listener.sendWindowProperty(this, 2, this.tileentity.getField(2));
			if(this.procesTimeTotal != this.tileentity.getField(3)) listener.sendWindowProperty(this, 3, this.tileentity.getField(3));
		}
		
		this.burn = this.tileentity.getField(0);
		this.burnTime = this.tileentity.getField(1);
		this.procesTime = this.tileentity.getField(2);
		this.procesTimeTotal = this.tileentity.getField(3);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data) {
		
		this.tileentity.setField(id, data);
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		
		return this.tileentity.isUsableByPlayer(playerIn);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = (Slot)this.inventorySlots.get(index);
		
		if (slot != null && slot.getHasStack()) {
			
			ItemStack stack1 = slot.getStack();
			stack = stack1.copy();
			
			if (index == 5) {
				
				if(!this.mergeItemStack(stack1, 7, 42, true)) return ItemStack.EMPTY;
				slot.onSlotChange(stack1, stack);
				
			} else if (index != 4 && index != 3 && index != 2 && index != 1 && index != 0) {
				
				Slot slot1 = (Slot)this.inventorySlots.get(index + 1);
				
				if (stack1.getItem().equals(Item.getByNameOrId("minecraft:redstone_block"))) this.mergeItemStack(stack1, 0, 1, false); 
				
				if(!this.mergeItemStack(stack1, 1, 5, false)) return ItemStack.EMPTY;
				
				else if (stack1.isItemEqual(new ItemStack(Blocks.REDSTONE_BLOCK))) {
				
					if (!this.mergeItemStack(stack1, 0, 1, false)) return ItemStack.EMPTY;
				
				} else if (index >= 7 && index < 33) {
					
					if (!this.mergeItemStack(stack1, 33, 42, false)) return ItemStack.EMPTY;			
				} else if (index >= 33 && index < 42) {
					
					if (!this.mergeItemStack(stack1, 7, 33, false)) return ItemStack.EMPTY;
				}
			} else if (!this.mergeItemStack(stack1, 7, 42, false)) return ItemStack.EMPTY;
			
			if (stack1.isEmpty()) slot.putStack(ItemStack.EMPTY);
			else slot.onSlotChanged();
			
			if (stack1.getCount() == stack.getCount()) return ItemStack.EMPTY;
			
			slot.onTake(playerIn, stack1);
		}
		
		return stack;
	}
	
}
