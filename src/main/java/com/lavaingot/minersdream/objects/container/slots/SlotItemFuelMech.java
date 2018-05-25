package com.lavaingot.minersdream.objects.container.slots;

import com.lavaingot.minersdream.objects.blocks.machines.alloyer.TileAlloyingFurnace;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SlotItemFuelMech extends Slot{
	
	public SlotItemFuelMech(IInventory inventory, int index, int x, int y) {

		super(inventory, index, x, y);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		
		return stack.isItemEqual(new ItemStack(Item.getByNameOrId("minecraft:redstone_block")));
	}
	
	@Override
	public int getItemStackLimit(ItemStack stack) {
		
		return super.getItemStackLimit(stack);
	}
}
