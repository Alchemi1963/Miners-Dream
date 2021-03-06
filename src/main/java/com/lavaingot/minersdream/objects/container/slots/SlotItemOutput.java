package com.lavaingot.minersdream.objects.container.slots;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotItemOutput extends Slot{

	private final EntityPlayer player;
	private int removeCount;
	
	public SlotItemOutput(EntityPlayer player, IInventory inventory, int index, int x, int y) {
		super(inventory, index, x, y);
		this.player = player;
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		
		return false;
	}
	
	@Override
	public int getItemStackLimit(ItemStack stack) {
		
		return super.getItemStackLimit(stack);
	}
}
