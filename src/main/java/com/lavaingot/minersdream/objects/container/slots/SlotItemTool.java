package com.lavaingot.minersdream.objects.container.slots;

import com.lavaingot.minersdream.objects.blocks.machines.mechanical_combiner.TileMechanicalCombiner;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotItemTool extends Slot{

	private int removeCount;
	
	public SlotItemTool(IInventory inventory, int index, int x, int y) {
		super(inventory, index, x, y);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		
		return TileMechanicalCombiner.isItemTool(stack);
	}
	
	@Override
	public ItemStack onTake(EntityPlayer thePlayer, ItemStack stack) {
		
		this.onCrafting(stack);
		super.onTake(thePlayer, stack);
		return stack;
	}
	
	@Override
	public ItemStack decrStackSize(int amount) {
		
		if(this.getHasStack()) this.removeCount += Math.min(amount, this.getStack().getCount());
		return super.decrStackSize(amount);
	}
	
}
