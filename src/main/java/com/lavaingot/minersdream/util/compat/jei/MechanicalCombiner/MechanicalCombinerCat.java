package com.lavaingot.minersdream.util.compat.jei.MechanicalCombiner;

import com.lavaingot.minersdream.util.Reference;
import com.lavaingot.minersdream.util.compat.jei.RecipeCat;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.client.Minecraft;

public class MechanicalCombinerCat extends AbstractMechanicalCombinerCat<MechanicalCombinerRecipe> {

	private final IDrawable background;
	private final String name;
	
	public MechanicalCombinerCat(IGuiHelper helper) {

		super(helper);
		background = helper.createDrawable(TEXTURES, 0, 0, 110, 61);
		name = "Mechanical Combiner";
	}
	
	@Override
	public void drawExtras(Minecraft minecraft) {
		
		animatedRedstone.draw(minecraft, 80, 40);
		animatedBar.draw(minecraft, 50, 13);
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
		
		return RecipeCat.MECHANICAL_COMBINER;
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, MechanicalCombinerRecipe recipeWrapper, IIngredients ingredients) {
		

		IGuiItemStackGroup stacks = recipeLayout.getItemStacks();
		stacks.init(input0, true, 4, 4);
		stacks.init(input1, true, 22, 4);
		stacks.init(input2, true, 4, 22);
		stacks.init(input3, true, 22, 22);
		stacks.init(output, false, 86, 13);
		stacks.set(ingredients);
	}

}
