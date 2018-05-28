package com.lavaingot.minersdream.util.compat.jei.MechanicalCombiner;

import com.lavaingot.minersdream.util.Reference;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.util.ResourceLocation;

public abstract class AbstractMechanicalCombinerCat<T extends IRecipeWrapper> implements IRecipeCategory<T> {

	protected static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MOD_ID + ":textures/gui/container/mechanical_combiner_jei.png");	
	
	protected static final int input0 = 0;
	protected static final int input1 = 1;
	protected static final int input2 = 2;
	protected static final int input3 = 3;
	protected static final int fuel = 4;
	protected static final int output = 5;
	
	protected final IDrawableStatic staticRedstone;
	protected final IDrawableAnimated animatedRedstone;
	
	protected final IDrawableAnimated animatedBar;
	
	public AbstractMechanicalCombinerCat(IGuiHelper gui) {
		
		staticRedstone = gui.createDrawable(TEXTURES, 139, 0, 38, 10);
		animatedRedstone = gui.createAnimatedDrawable(staticRedstone, 1000, IDrawableAnimated.StartDirection.RIGHT, true);
		
		IDrawableStatic staticBar = gui.createDrawable(TEXTURES, 139, 10, 22, 16);
		animatedBar = gui.createAnimatedDrawable(staticBar, 100, IDrawableAnimated.StartDirection.LEFT, false);
	}
}
