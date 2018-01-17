package com.lavaingot.minersdream.util;

import java.util.List;

import net.minecraft.item.ItemStack;

public class Reference {
	
	public static final String MOD_ID = "minersdream";
	public static final String NAME = "Miners Dream";
	public static final String VERSION = "1.0";
	public static final String ACCEPTED_MC = "[1.12.2]";
	
	public static final String CLIENT_PROXY_CLASS = "com.lavaingot.minersdream.proxy.ClientProxy";
	public static final String SERVER_PROXY_CLASS = "com.lavaingot.minersdream.proxy.ServerProxy";
	
	public static boolean areAllTrue(boolean[] array) {
		for (boolean b : array) if(!b) { return false; }
		return true;
	}
	
	public static boolean areAllItemsSame(List<ItemStack> stack1, List<ItemStack> stack2) {
	
		for (ItemStack stack : stack1) {
			boolean equality = false;
			for (ItemStack stackCheck : stack2) {
				
				if (stackCheck.isItemEqual(stack) && stackCheck.getCount() <= stack.getCount() || stackCheck.isEmpty() && stack.isEmpty()){
					equality = true;
					break;
				}
			}
			
			if (!equality) {
				return false;
			}
		}
		
		return true;
		
		
	}
}
