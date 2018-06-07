package com.lavaingot.minersdream.objects.container;

import com.lavaingot.minersdream.objects.blocks.BlockContainer;
import com.lavaingot.minersdream.objects.tileentities.TileContainer;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerBlockContainerOpen extends Container{

	private TileContainer te;
	
	public ContainerBlockContainerOpen(IInventory inventoryIn, TileContainer te) {
		
		this.te = te;
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		
		return this.te.isUsableByPlayer(playerIn);
	}	
}
