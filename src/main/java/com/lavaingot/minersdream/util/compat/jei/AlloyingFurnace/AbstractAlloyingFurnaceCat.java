package com.lavaingot.minersdream.util.compat.jei.AlloyingFurnace;

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

public abstract class AbstractAlloyingFurnaceCat<T extends IRecipeWrapper> implements IRecipeCategory<T> {

	protected static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MOD_ID + ":textures/gui/container/alloying_furnace_jei.png");	
	
	protected static final int input0 = 0;
	protected static final int input1 = 1;
	protected static final int input2 = 2;
	protected static final int fuel = 3;
	protected static final int output = 4;
	
	protected final IDrawableStatic staticFlame;
	protected final IDrawableAnimated animatedFlame;
	
	protected final IDrawableAnimated animatedBar;
	
	public AbstractAlloyingFurnaceCat(IGuiHelper gui) {
		
		staticFlame = gui.createDrawable(TEXTURES, 111, 0, 14, 14);
		animatedFlame = gui.createAnimatedDrawable(staticFlame, 200, IDrawableAnimated.StartDirection.TOP, true);
		
		IDrawableStatic staticBar = gui.createDrawable(TEXTURES, 111, 14, 24, 17);
		animatedBar = gui.createAnimatedDrawable(staticBar, 200, IDrawableAnimated.StartDirection.LEFT, false);
	}
}
