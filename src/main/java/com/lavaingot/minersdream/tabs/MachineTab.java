package com.lavaingot.minersdream.tabs;

import com.lavaingot.minersdream.init.BlockInit;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class MachineTab extends CreativeTabs {

	public MachineTab(String name) {

		super(name);
	}
	
	@Override
	public ItemStack getTabIconItem() {

		return new ItemStack(Item.getItemFromBlock(BlockInit.MECHANICAL_COMBINER));
	}

}
