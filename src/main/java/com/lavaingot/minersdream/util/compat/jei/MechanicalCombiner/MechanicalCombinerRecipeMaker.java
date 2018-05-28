package com.lavaingot.minersdream.util.compat.jei.MechanicalCombiner;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.lavaingot.minersdream.init.ItemInit;
import com.lavaingot.minersdream.objects.tools.ToolAxe;
import com.lavaingot.minersdream.objects.tools.ToolMulti;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.IStackHelper;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;

public class MechanicalCombinerRecipeMaker {

	public static List<MechanicalCombinerRecipe> getRecipes(IJeiHelpers helpers){
		
		IStackHelper stackHelper = helpers.getStackHelper();
		List<MechanicalCombinerRecipe> JEIRecipes = Lists.newArrayList();
		
		List<Item> pickaxes = new ArrayList<Item>();
		List<Item> axes = new ArrayList<Item>();
		List<Item> shovels = new ArrayList<Item>();
		
		for (Item item : ImmutableList.copyOf(Item.REGISTRY)) {
			
			if (item instanceof ItemPickaxe) {
				pickaxes.add(item);
				continue;
			} else if (item instanceof ItemAxe || item instanceof ToolAxe || (item.getUnlocalizedName().contains("axe") && !(item instanceof ItemPickaxe))) {
				axes.add(item);
				continue;
			} else if (item instanceof ItemSpade) {
				shovels.add(item);
				continue;
			}
		}
		
		for (Item pickaxe : pickaxes) {
			for (Item axe : axes) {
				for (Item shovel : shovels) {
					List<ItemStack> items = new ArrayList<ItemStack>();
					
					ItemStack pickaxeStack = new ItemStack(pickaxe); 
					ItemStack axeStack = new ItemStack(axe); 
					ItemStack shovelStack = new ItemStack(shovel);
					ItemStack shears = new ItemStack(Items.SHEARS);
					
					ItemStack output = ToolMulti.setItems(new ItemStack(ItemInit.MULTI_TOOL), pickaxeStack, axeStack, shovelStack, shears);
					items.add(pickaxeStack);
					items.add(axeStack);
					items.add(shovelStack);
					items.add(shears);
					
					MechanicalCombinerRecipe recipe = new MechanicalCombinerRecipe(items, output);
					JEIRecipes.add(recipe);
					
				}
			}
		}
		
		return JEIRecipes;
	}
}
