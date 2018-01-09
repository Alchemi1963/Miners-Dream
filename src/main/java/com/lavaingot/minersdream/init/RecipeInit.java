package com.lavaingot.minersdream.init;

import java.util.ArrayList;
import java.util.List;

import com.lavaingot.minersdream.Main;

import net.minecraft.item.ItemStack;

public class RecipeInit {
	
	public static final List<List<ItemStack>> RECIPES =  new ArrayList<List<ItemStack>>();
	
	public static List<ItemStack> itemDreamRecipe = new ArrayList<ItemStack>();
	
	public static void init() {
		
		itemDreamRecipe = Main.getRecipeItems(Main.cfgInstance.itemDream);
		RECIPES.add(itemDreamRecipe);
		
	}

}
