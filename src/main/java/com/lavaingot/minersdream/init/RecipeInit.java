package com.lavaingot.minersdream.init;

import java.util.ArrayList;
import java.util.List;

import com.lavaingot.minersdream.Main;

import net.minecraft.item.ItemStack;

public class RecipeInit {
	
	public static final List<List<ItemStack>> RECIPES =  new ArrayList<List<ItemStack>>();
	
	private static List<ItemStack> alloyBronzeRecipe;
	private static List<ItemStack> alloy2Recipe;
	
	public static void init() {
		
		alloyBronzeRecipe = Main.getRecipeItems(Main.cfgInstance.alloyBronze);
		alloy2Recipe = Main.getRecipeItems(Main.cfgInstance.alloy2);
		RECIPES.add(alloyBronzeRecipe);
		RECIPES.add(alloy2Recipe);
		
	}

}
