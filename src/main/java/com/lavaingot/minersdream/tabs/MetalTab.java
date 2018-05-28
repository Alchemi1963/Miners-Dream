package com.lavaingot.minersdream.tabs;

import com.lavaingot.minersdream.init.BlockInit;
import com.lavaingot.minersdream.util.handlers.EnumHandler.MetalBlockOne;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class MetalTab extends CreativeTabs {

	public MetalTab(String name) {
		super(name);
	}
	
	@Override
	public ItemStack getTabIconItem() {

		return new ItemStack(Item.getItemFromBlock(BlockInit.BLOCK_METAL), 1, MetalBlockOne.BISMUTH.getMeta());
	}

}
