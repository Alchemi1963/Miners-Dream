package com.lavaingot.minersdream.objects.container;

import com.lavaingot.minersdream.objects.tileentities.TileContainer;
import com.lavaingot.minersdream.util.handlers.MinersdreamPacketHandler;
import com.lavaingot.minersdream.util.handlers.MinersdreamPacketHandler.ShouldOpenMessage;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

public class ContainerBlockContainer extends Container{

	private TileContainer te;
	private IItemHandler handler;
	
	private int code = 0;
	
	public ContainerBlockContainer(IInventory inventoryIn, TileContainer te) {
		
		this.te = te;
		
		this.addSlotToContainer(new Slot(te, 0, 34, 17));
		this.addSlotToContainer(new Slot(te, 1, 52, 17));
		this.addSlotToContainer(new Slot(te, 2, 70, 17));
		this.addSlotToContainer(new Slot(te, 3, 88, 17));
		this.addSlotToContainer(new Slot(te, 4, 106, 17));
		this.addSlotToContainer(new Slot(te, 5, 124, 17));
		this.addSlotToContainer(new Slot(te, 6, 34, 35));
		this.addSlotToContainer(new Slot(te, 7, 52, 35));
		this.addSlotToContainer(new Slot(te, 8, 70, 35));
		this.addSlotToContainer(new Slot(te, 9, 88, 35));
		this.addSlotToContainer(new Slot(te, 10, 106, 35));
		this.addSlotToContainer(new Slot(te, 11, 124, 35));
		this.addSlotToContainer(new Slot(te, 12, 34, 53));
		this.addSlotToContainer(new Slot(te, 13, 52, 53));
		this.addSlotToContainer(new Slot(te, 14, 70, 53));
		this.addSlotToContainer(new Slot(te, 15, 88, 53));
		this.addSlotToContainer(new Slot(te, 16, 106, 53));
		this.addSlotToContainer(new Slot(te, 17, 124, 53));
		
		
		
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
		
		MinersdreamPacketHandler.INSTANCE.sendToAll(new ShouldOpenMessage(true, te.getPos()));
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {

		return this.te.isUsableByPlayer(playerIn);
	}
	
	@Override
	public void addListener(IContainerListener listener) {
		
		super.addListener(listener);
		listener.sendAllWindowProperties(this, this.te);
	}
	
	@Override
	public void onContainerClosed(EntityPlayer playerIn) {
		te.shouldOpen = false;
		MinersdreamPacketHandler.INSTANCE.sendToAll(new ShouldOpenMessage(false, te.getPos()));
		super.onContainerClosed(playerIn);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int fromSlot) {
	    
		ItemStack previous = ItemStack.EMPTY;
	    Slot slot = (Slot) this.inventorySlots.get(fromSlot);

	    if (slot != null && slot.getHasStack()) {
	        ItemStack current = slot.getStack();
	        previous = current.copy();

	        if (fromSlot < this.te.getSizeInventory()) {
	            
	            if (!this.mergeItemStack(current, this.te.getSizeInventory(), this.te.getSizeInventory() + 36, true))
	                return ItemStack.EMPTY;
	            
	        } else if (!this.mergeItemStack(current, 0, this.te.getSizeInventory(), false)) {
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
