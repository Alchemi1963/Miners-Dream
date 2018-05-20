package com.lavaingot.minersdream.util.compat.jei.AlloyingFurnace;

import java.util.Collection;
import java.util.List;

import com.google.common.collect.Lists;
import com.google.common.collect.Table;
import com.lavaingot.minersdream.objects.recipes.AlloyingFurnaceRecipes;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.IStackHelper;
import net.minecraft.item.ItemStack;

public class AlloyingFurnaceRecipeMaker {

	public static List<AlloyingFurnaceRecipe> getRecipes(IJeiHelpers helpers){
		
		IStackHelper stackHelper = helpers.getStackHelper();
		AlloyingFurnaceRecipes instance = AlloyingFurnaceRecipes.getInstance();
		Table<ItemStack[], ItemStack, ItemStack> recipes = instance.getRecipes();
		List<AlloyingFurnaceRecipe> JEIRecipes = Lists.newArrayList();
		
		for (ItemStack output : recipes.values()) {
			
			ItemStack input0 = instance.getRecipeQuants().get(output)[0];
			ItemStack input1 = instance.getRecipeQuants().get(output)[1];
			ItemStack input2 = instance.getRecipeQuants().get(output)[2];
			
			List<ItemStack> inputs = Lists.newArrayList(input0, input1, input2);
			AlloyingFurnaceRecipe recipe = new AlloyingFurnaceRecipe(inputs, output);
			JEIRecipes.add(recipe);
		}
		
		return JEIRecipes;
	}
}
