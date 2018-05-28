package com.lavaingot.minersdream.tabs;

import com.lavaingot.minersdream.init.ItemInit;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class ArmourTab extends CreativeTabs {

	public ArmourTab(String name) {
		super(name);
	}
	
	@Override
	public ItemStack getTabIconItem() {
		
		return new ItemStack(ItemInit.CHESTPLATE_BISMUTH);
	}

}
