package com.lavaingot.minersdream.objects.container.slots;

import net.minecraft.inventory.SlotFurnaceFuel;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotItemFuel extends SlotItemHandler{

	public SlotItemFuel(IItemHandler handler, int index, int xPosition, int yPosition) {
		super(handler, index, xPosition, yPosition);
		
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		
		return TileEntityFurnace.isItemFuel(stack) || SlotFurnaceFuel.isBucket(stack);
	}
	
	@Override
	public int getItemStackLimit(ItemStack stack) {
		
		return SlotFurnaceFuel.isBucket(stack) ? 1 : super.getItemStackLimit(stack);
	}
}
