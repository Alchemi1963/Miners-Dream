package com.lavaingot.minersdream.init;

import java.util.ArrayList;
import java.util.List;

import com.lavaingot.minersdream.Main;

import net.minecraft.item.ItemStack;

public class RecipeInit {
	
	public static final List<List<ItemStack>> RECIPES =  new ArrayList<List<ItemStack>>();
	
	public static List<ItemStack> alloyBronzeRecipe;
	
	public static void init() {
		
		alloyBronzeRecipe = Main.getRecipeItems(Main.cfgInstance.alloyBronze);
		RECIPES.add(alloyBronzeRecipe);
		
	}

}
