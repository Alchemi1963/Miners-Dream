package com.lavaingot.minersdream.objects.items;

import com.lavaingot.minersdream.Main;
import com.lavaingot.minersdream.init.ItemInit;
import com.lavaingot.minersdream.util.IHasModel;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item implements IHasModel{

	public ItemBase(String name, CreativeTabs tab) {

		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(tab);
		
		ItemInit.ITEMS.add(this);
	}
	
	
	@Override
	public void registerModels() {
		
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}
}
