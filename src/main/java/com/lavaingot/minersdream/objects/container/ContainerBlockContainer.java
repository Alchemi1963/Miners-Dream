package com.lavaingot.minersdream.objects.container;

import com.lavaingot.minersdream.objects.tileentities.TileEntityContainer;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerBlockContainer extends Container{

	private TileEntityContainer te;
	private IItemHandler handler;
	
	public ContainerBlockContainer(IInventory inventoryIn, TileEntityContainer te) {
		
		this.te = te;
		
		this.handler = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		
		this.addSlotToContainer(new SlotItemHandler(handler, 0, 34, 17));
		this.addSlotToContainer(new SlotItemHandler(handler, 1, 52, 17));
		this.addSlotToContainer(new SlotItemHandler(handler, 2, 70, 17));
		this.addSlotToContainer(new SlotItemHandler(handler, 3, 88, 17));
		this.addSlotToContainer(new SlotItemHandler(handler, 4, 106, 17));
		this.addSlotToContainer(new SlotItemHandler(handler, 5, 124, 17));
		this.addSlotToContainer(new SlotItemHandler(handler, 6, 34, 35));
		this.addSlotToContainer(new SlotItemHandler(handler, 7, 52, 35));
		this.addSlotToContainer(new SlotItemHandler(handler, 8, 70, 35));
		this.addSlotToContainer(new SlotItemHandler(handler, 9, 88, 35));
		this.addSlotToContainer(new SlotItemHandler(handler, 10, 106, 35));
		this.addSlotToContainer(new SlotItemHandler(handler, 11, 124, 35));
		this.addSlotToContainer(new SlotItemHandler(handler, 12, 34, 53));
		this.addSlotToContainer(new SlotItemHandler(handler, 13, 52, 53));
		this.addSlotToContainer(new SlotItemHandler(handler, 14, 70, 53));
		this.addSlotToContainer(new SlotItemHandler(handler, 15, 88, 53));
		this.addSlotToContainer(new SlotItemHandler(handler, 16, 106, 53));
		this.addSlotToContainer(new SlotItemHandler(handler, 17, 124, 53));
		
		int xPos = 8;
		int yPos = 84;
		
		
		for(int y = 0; y < 3; y++) {
			for(int x = 0; x < 9; x++) {
				this.addSlotToContainer(new Slot(inventoryIn, x + y * 9 + 9, xPos + x * 18, yPos + y * 18));
			}
		}
		
		for(int x = 0; x < 9; x++) {
			this.addSlotToContainer(new Slot(inventoryIn, x, xPos + x * 18, 142));
		}
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {

		return this.te.isUsableByPlayer(playerIn);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int fromSlot) {
	    
		ItemStack previous = ItemStack.EMPTY;
	    Slot slot = (Slot) this.inventorySlots.get(fromSlot);

	    if (slot != null && slot.getHasStack()) {
	        ItemStack current = slot.getStack();
	        previous = current.copy();

	        if (fromSlot < this.handler.getSlots()) {
	            
	            if (!this.mergeItemStack(current, handler.getSlots(), handler.getSlots() + 36, true))
	                return ItemStack.EMPTY;
	            
	        } else if (!this.mergeItemStack(current, 0, handler.getSlots(), false)) {
	                return ItemStack.EMPTY;
	        }

	        if (current.getCount() == 0)
	            slot.putStack(ItemStack.EMPTY);
	        else
	            slot.onSlotChanged();

	        if (current.getCount() == previous.getCount())
	            return null;
	        slot.onTake(playerIn, current);
	    }
	    return previous;
}
}
