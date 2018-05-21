package com.lavaingot.minersdream.util.compat.jei;

import java.util.IllegalFormatException;

import com.lavaingot.minersdream.init.BlockInit;
import com.lavaingot.minersdream.objects.container.ContainerAlloyingFurnace;
import com.lavaingot.minersdream.objects.gui.GUIAlloyingFurnace;
import com.lavaingot.minersdream.util.compat.jei.AlloyingFurnace.AlloyingFurnaceCat;
import com.lavaingot.minersdream.util.compat.jei.AlloyingFurnace.AlloyingFurnaceRecipeMaker;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.gui.IGhostIngredientHandler;
import mezz.jei.api.ingredients.IIngredientRegistry;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.transfer.IRecipeTransferRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;

@JEIPlugin
public class JEICompat implements IModPlugin{

	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		
		final IJeiHelpers helpers = registry.getJeiHelpers();
		final IGuiHelper gui = helpers.getGuiHelper();
		
		registry.addRecipeCategories(new AlloyingFurnaceCat(gui));
	}
	
	@Override
	public void register(IModRegistry registry) {

		final IIngredientRegistry ingredientRegistry = registry.getIngredientRegistry();
		final IJeiHelpers jeiHelpers = registry.getJeiHelpers();
		IRecipeTransferRegistry recipeTransfer = registry.getRecipeTransferRegistry();
		
		registry.addRecipeCatalyst(new ItemStack(BlockInit.ALLOYING_FURNACE), RecipeCat.ALLOYING_FURNACE);
		registry.addRecipes(AlloyingFurnaceRecipeMaker.getRecipes(jeiHelpers), RecipeCat.ALLOYING_FURNACE);
		registry.addRecipeClickArea(GUIAlloyingFurnace.class, 149, 11, 16, 16, RecipeCat.ALLOYING_FURNACE);
		recipeTransfer.addRecipeTransferHandler(ContainerAlloyingFurnace.class, RecipeCat.ALLOYING_FURNACE, 0, 2, 6, 36);
	}
	
	public static String translateToLocaL(String key) {
		
		if (I18n.canTranslate(key)) return I18n.translateToLocal(key);
		else return I18n.translateToFallback(key);
	}
	
	public static String translateToLocalFormatted(String key, Object... format) {
		
		String s = translateToLocaL(key);
		
		try {
			s = String.format(s, format);
			
		} catch(IllegalFormatException e) {
			return "Format ERROR: " + s;
		}
		return s;
	}
}
