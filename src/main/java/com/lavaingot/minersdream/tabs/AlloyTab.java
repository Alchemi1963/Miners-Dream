package com.lavaingot.minersdream.tabs;

import com.lavaingot.minersdream.init.BlockInit;
import com.lavaingot.minersdream.util.handlers.EnumHandler.BaseAlloy;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class AlloyTab extends CreativeTabs {

	public AlloyTab(String name) {

		super(name);
	}
		
	@Override
	public ItemStack getTabIconItem() {

		return new ItemStack(Item.getItemFromBlock(BlockInit.BLOCK_ALLOY), 1, BaseAlloy.BRASS.getMeta());
	}

}
