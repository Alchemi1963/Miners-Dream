package com.lavaingot.minersdream.objects.container;

import com.lavaingot.minersdream.objects.container.slots.SlotItemFuel;
import com.lavaingot.minersdream.objects.container.slots.SlotItemOutput;
import com.lavaingot.minersdream.objects.tileentities.TileAlloyingFurnace;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerAlloyingFurnace extends Container{

	private final TileAlloyingFurnace tileentity;
	private int smelt, smeltTime, burn, burnTime;
	
	public ContainerAlloyingFurnace(InventoryPlayer player, TileAlloyingFurnace tileentity) {
		
		this.tileentity = tileentity;
		
		this.addSlotToContainer(new Slot(this.tileentity, 0, 34, 17));
		this.addSlotToContainer(new Slot(this.tileentity, 1, 56, 17));
		this.addSlotToContainer(new Slot(this.tileentity, 2, 78, 17));
		this.addSlotToContainer(new SlotItemFuel(this.tileentity, 3, 56, 57));
		this.addSlotToContainer(new SlotItemOutput(player.player, this.tileentity, 4, 116, 39));
		
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
			if(this.smelt != this.tileentity.getField(2)) listener.sendWindowProperty(this, 2, this.tileentity.getField(2));
			if(this.smeltTime != this.tileentity.getField(3)) listener.sendWindowProperty(this, 3, this.tileentity.getField(3));
		}
		
		this.burn = this.tileentity.getField(0);
		this.burnTime = this.tileentity.getField(1);
		this.smelt = this.tileentity.getField(2);
		this.smeltTime = this.tileentity.getField(3);
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
			
			if (index == 4) {
				
				if(!this.mergeItemStack(stack1, 5, 41, true)) return ItemStack.EMPTY;
				slot.onSlotChange(stack1, stack);
			} else if (index != 3 && index != 2 && index != 1 && index != 0) {
				
				Slot slot1 = (Slot)this.inventorySlots.get(index + 1);
				
				if(!this.mergeItemStack(stack1, 0, 3, false)) return ItemStack.EMPTY;
				else if (TileAlloyingFurnace.isItemFuel(stack1)) {
					if (!this.mergeItemStack(stack1, 3, 4, false)) return ItemStack.EMPTY;
				} else if (index >= 5 && index < 32) {
					if (!this.mergeItemStack(stack1, 32, 41, false)) return ItemStack.EMPTY;			
				} else if (index >= 32 && index < 41) {
					if (!this.mergeItemStack(stack1, 5, 32, false)) return ItemStack.EMPTY;
				}
			} else if (!this.mergeItemStack(stack1, 5, 41, false)) return ItemStack.EMPTY;
			
			if (stack1.isEmpty()) slot.putStack(ItemStack.EMPTY);
			else slot.onSlotChanged();
			
			if (stack1.getCount() == stack.getCount()) return ItemStack.EMPTY;
			
			slot.onTake(playerIn, stack1);
		}
		
		return stack;
	}
}
