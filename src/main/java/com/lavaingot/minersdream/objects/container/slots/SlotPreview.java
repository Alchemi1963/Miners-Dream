package com.lavaingot.minersdream.objects.container.slots;

import com.lavaingot.minersdream.init.BlockInit;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotPreview extends Slot{

	private final EntityPlayer player;

	public SlotPreview(EntityPlayer player, IInventory inventory, int index, int xPos, int yPos) {
		super(inventory, index, xPos, yPos);
		this.player = player;
		
		
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		
		return false;
	}
	
	@Override
	public boolean canTakeStack(EntityPlayer playerIn) {
		
		return false;
	}
	
	@Override
	public int getSlotStackLimit() {
		
		return 1;
	}
}
