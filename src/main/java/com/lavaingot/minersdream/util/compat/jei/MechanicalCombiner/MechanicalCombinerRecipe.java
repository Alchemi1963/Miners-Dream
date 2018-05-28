package com.lavaingot.minersdream.util.compat.jei.MechanicalCombiner;

import java.awt.Color;
import java.util.List;

import com.lavaingot.minersdream.Main;
import com.lavaingot.minersdream.objects.recipes.AlloyingFurnaceRecipes;
import com.lavaingot.minersdream.util.compat.jei.JEICompat;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;

public class MechanicalCombinerRecipe implements IRecipeWrapper {
	
	private final List<ItemStack> inputs;
	private final ItemStack output;
	
	public MechanicalCombinerRecipe(List<ItemStack> inputs, ItemStack output) {
		
		this.inputs = inputs;
		this.output = output;
	}
	
	@Override
	public void getIngredients(IIngredients ingredients) {
		
		ingredients.setInputs(ItemStack.class, this.inputs);
		ingredients.setOutput(ItemStack.class, this.output);
		
	}

}
