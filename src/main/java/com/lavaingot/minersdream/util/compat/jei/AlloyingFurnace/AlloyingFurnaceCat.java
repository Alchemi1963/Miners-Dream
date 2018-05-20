package com.lavaingot.minersdream.util.compat.jei.AlloyingFurnace;

import com.lavaingot.minersdream.util.Reference;
import com.lavaingot.minersdream.util.compat.jei.RecipeCat;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.client.Minecraft;

public class AlloyingFurnaceCat extends AbstractAlloyingFurnaceCat<AlloyingFurnaceRecipe> {

	private final IDrawable background;
	private final String name;
	
	public AlloyingFurnaceCat(IGuiHelper helper) {

		super(helper);
		background = helper.createDrawable(TEXTURES, 0, 0, 110, 61);
		name = "Alloying Furnace";
	}
	
	@Override
	public void drawExtras(Minecraft minecraft) {
		
		animatedFlame.draw(minecraft, 28, 27);
		animatedBar.draw(minecraft, 50, 25);
	}
	
	@Override
	public IDrawable getBackground() {

		return background;
	}
	
	@Override
	public String getTitle() {
		
		return name;
	}
	
	@Override
	public String getModName() {
		
		return Reference.NAME;
	}
	
	@Override
	public String getUid() {
		
		return RecipeCat.ALLOYING_FURNACE;
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, AlloyingFurnaceRecipe recipeWrapper, IIngredients ingredients) {
		

		IGuiItemStackGroup stacks = recipeLayout.getItemStacks();
		stacks.init(input0, true, 4, 2);
		stacks.init(input1, true, 26, 2);
		stacks.init(input2, true, 48, 2);
		stacks.init(output, false, 86, 24);
		stacks.set(ingredients);
	}

}
