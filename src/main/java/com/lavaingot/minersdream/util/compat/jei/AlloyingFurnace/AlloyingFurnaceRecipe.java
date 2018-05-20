package com.lavaingot.minersdream.util.compat.jei.AlloyingFurnace;

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

public class AlloyingFurnaceRecipe implements IRecipeWrapper {
	
	private final List<ItemStack> inputs;
	private final ItemStack output;
	
	public AlloyingFurnaceRecipe(List<ItemStack> inputs, ItemStack output) {
		
		this.inputs = inputs;
		this.output = output;
	}
	
	@Override
	public void getIngredients(IIngredients ingredients) {
		
		ingredients.setInputs(ItemStack.class, this.inputs);
		ingredients.setOutput(ItemStack.class, this.output);
		
	}
	
	@Override
	public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
	
		AlloyingFurnaceRecipes recipes = AlloyingFurnaceRecipes.getInstance();
		float exp = recipes.getEXP(output);
		
		if (exp > 0) {
			String expString = JEICompat.translateToLocalFormatted("gui.jei.category.smelting.experience", exp);
			FontRenderer renderer = minecraft.fontRenderer;
			
			int stringWidth = renderer.getStringWidth(expString);
			renderer.drawString(expString, 110 - stringWidth, 10 - renderer.FONT_HEIGHT/2, new Color(0, 50, 0).getRGB());
		}
	}

}
