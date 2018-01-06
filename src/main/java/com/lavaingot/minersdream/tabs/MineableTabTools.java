package com.lavaingot.minersdream.tabs;

import com.lavaingot.minersdream.init.BlockInit;
import com.lavaingot.minersdream.init.BlockOres;
import com.lavaingot.minersdream.init.ItemInit;
import com.lavaingot.minersdream.util.handlers.EnumHandler;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

public class MineableTabTools extends CreativeTabs{
	
	public MineableTabTools(String label) {
		super(label);
		
	}

	@Override
	public ItemStack getTabIconItem() {

		return new ItemStack(Item.getItemFromBlock(BlockInit.ORE_NETHER), 1, EnumHandler.OreType.BISMUTH.getMeta());
	}
}
