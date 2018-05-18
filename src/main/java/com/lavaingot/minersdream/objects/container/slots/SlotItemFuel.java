package com.lavaingot.minersdream.objects.container.slots;

import com.lavaingot.minersdream.objects.tileentities.TileAlloyingFurnace;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotItemFuel extends Slot{
	
	public SlotItemFuel(IInventory inventory, int index, int x, int y) {

		super(inventory, index, x, y);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		
		return TileAlloyingFurnace.isItemFuel(stack);
	}
	
	@Override
	public int getItemStackLimit(ItemStack stack) {
		
		return super.getItemStackLimit(stack);
	}
}
