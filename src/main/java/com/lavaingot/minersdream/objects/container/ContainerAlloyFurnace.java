package com.lavaingot.minersdream.objects.container;

import com.lavaingot.minersdream.objects.container.slots.SlotItemFuel;
import com.lavaingot.minersdream.objects.container.slots.SlotItemOutput;
import com.lavaingot.minersdream.objects.tileentities.TileEntityAlloyFurnace;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerAlloyFurnace extends Container{

	private TileEntityAlloyFurnace te;
	private IItemHandler handler;
	
	public ContainerAlloyFurnace(IInventory inventoryIn, TileEntityAlloyFurnace te, EntityPlayer player) {
		
		this.te = te;
		
		this.handler = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		
		this.addSlotToContainer(new SlotItemHandler(handler, 0, 34, 17));
		this.addSlotToContainer(new SlotItemHandler(handler, 1, 56, 17));
		this.addSlotToContainer(new SlotItemHandler(handler, 2, 78, 17));
		this.addSlotToContainer(new SlotItemFuel(handler, 3, 56, 57));
		this.addSlotToContainer(new SlotItemOutput(player, handler, 4, 112, 35));
		
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
	        	//From alloy furnace to inventory
	        	if (!this.mergeItemStack(current, handler.getSlots(), handler.getSlots() + 36, true))
	                return ItemStack.EMPTY;
	        	
	        } else {
	        	//From inventory to alloy furnace	        	
	        	
	        	
	        	if( TileEntityFurnace.isItemFuel(current) ) {
	        		
	        		if(!this.mergeItemStack(current, 3, handler.getSlots(), false))
	        			return ItemStack.EMPTY;
	        	
	        	}
	        	
	        	if (!this.mergeItemStack(current, 0, handler.getSlots(), false)) {
	                return ItemStack.EMPTY;
	        	}
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
