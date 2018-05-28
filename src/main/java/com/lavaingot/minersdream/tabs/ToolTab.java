package com.lavaingot.minersdream.tabs;

import com.lavaingot.minersdream.init.ItemInit;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class ToolTab extends CreativeTabs {

	public ToolTab(String name) {

		super(name);
	}
	
	@Override
	public ItemStack getTabIconItem() {
		
		return new ItemStack(ItemInit.THE_DREAM);
	}

}
