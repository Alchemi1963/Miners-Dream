package com.lavaingot.minersdream.objects.recipes;

import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import com.lavaingot.minersdream.Main;
import com.lavaingot.minersdream.init.ItemInit;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class AlloyingFurnaceRecipes {

	private static final AlloyingFurnaceRecipes INSTANCE = new AlloyingFurnaceRecipes();
	private final Table<ItemStack[], ItemStack, ItemStack> recipes = HashBasedTable.<ItemStack[], ItemStack, ItemStack>create();
	
	private final Map<ItemStack, Float> expList = Maps.<ItemStack, Float>newHashMap();
	private final Map<ItemStack, ItemStack[]> recipeQuants = Maps.<ItemStack, ItemStack[]>newHashMap();	
	
	public static AlloyingFurnaceRecipes getInstance() {
		return INSTANCE;
	}
	
	private AlloyingFurnaceRecipes(){
		
		addRecipe(new ItemStack(ItemInit.INGOT, 9, 0), new ItemStack(ItemInit.INGOT, 1, 11), ItemStack.EMPTY, new ItemStack(ItemInit.ALLOY_INGOT, 10, 0), 200);
		addRecipe(new ItemStack(ItemInit.INGOT, 1, 0), new ItemStack(ItemInit.INGOT, 1, 9), ItemStack.EMPTY, new ItemStack(ItemInit.ALLOY_INGOT, 2, 1), 200);
		addRecipe(new ItemStack(ItemInit.INGOT, 1, 0), new ItemStack(ItemInit.INGOT, 9, 1), ItemStack.EMPTY, new ItemStack(ItemInit.ALLOY_INGOT, 10, 2), 200);
		addRecipe(new ItemStack(Item.getByNameOrId("minecraft:iron_ingot"), 3), new ItemStack(ItemInit.INGOT, 2, 9), ItemStack.EMPTY, new ItemStack(ItemInit.ALLOY_INGOT, 5, 3), 200);
		addRecipe(new ItemStack(ItemInit.INGOT, 1, 7), new ItemStack(ItemInit.INGOT, 1, 9), new ItemStack(ItemInit.INGOT, 8, 0), new ItemStack(ItemInit.ALLOY_INGOT, 10, 4), 200);
		addRecipe(new ItemStack(ItemInit.INGOT, 1, 0), new ItemStack(ItemInit.INGOT, 1, 15), ItemStack.EMPTY, new ItemStack(ItemInit.ALLOY_INGOT, 2, 5), 200);
		addRecipe(new ItemStack(ItemInit.INGOT, 1, 9), new ItemStack(ItemInit.INGOT, 1, 15), new ItemStack(ItemInit.INGOT, 3, 0), new ItemStack(ItemInit.ALLOY_INGOT, 5, 6), 200);
		addRecipe(new ItemStack(Item.getByNameOrId("minecraft:coal"), 1), new ItemStack(Item.getByNameOrId("minecraft:iron_ingot"), 3), ItemStack.EMPTY, new ItemStack(ItemInit.ALLOY_INGOT, 3, 7), 100);
		addRecipe(new ItemStack(ItemInit.INGOT, 1, 3), new ItemStack(Item.getByNameOrId("minecraft:iron_ingot"), 4), new ItemStack(Item.getByNameOrId("minecraft:coal"), 1), new ItemStack(ItemInit.ALLOY_INGOT, 5, 8), 100);
		
	}
	
	public void addRecipe(ItemStack input0, ItemStack input1, ItemStack input2, ItemStack result, float exp) {
		
		if (getAlloyResult(input0, input1, input2) != ItemStack.EMPTY) return;
		this.recipes.put(new ItemStack[] {input0,  input1},  input2, result);
		this.expList.put(result, Float.valueOf(exp));
		this.recipeQuants.put(result, new ItemStack[] {input0, input1, input2});
		
	}

	public ItemStack getAlloyResult(ItemStack input0, ItemStack input1, ItemStack input2) {
		ItemStack[] try1 = new ItemStack[] {input0, input1};
		ItemStack[] try2 = new ItemStack[] {input0, input2};
		ItemStack[] try3 = new ItemStack[] {input1, input2};
		ItemStack[] try4 = new ItemStack[] {input1, input0};
		ItemStack[] try5 = new ItemStack[] {input2, input0};
		ItemStack[] try6 = new ItemStack[] {input2, input1};
		
		ItemStack[][] tries = new ItemStack[][] {try1, try2, try3, try4, try5, try6};
		
		for (ItemStack[] attempt : tries) {
			for (Entry<ItemStack, Map<ItemStack[], ItemStack>> entry : this.recipes.columnMap().entrySet()) {
				
				if (this.compareItemStacks(input0, (ItemStack)entry.getKey()) || this.compareItemStacks(input1, (ItemStack)entry.getKey()) || this.compareItemStacks(input2, (ItemStack)entry.getKey())) {
					
					for (Entry<ItemStack[], ItemStack> ent : entry.getValue().entrySet()) {
									
						if (this.compareEntries(attempt, (ItemStack[])ent.getKey())) {
							return (ItemStack)ent.getValue();
						}
					}
				}
			}
		}
		return ItemStack.EMPTY;
	}

	private boolean compareEntries(ItemStack[] attempt, ItemStack[] itemStacks) {
		
		return (this.compareItemStacks(attempt[0], itemStacks[0]) && this.compareItemStacks(attempt[1], itemStacks[1])) || (this.compareItemStacks(attempt[1], itemStacks[0]) && this.compareItemStacks(attempt[0], itemStacks[1]));
	}

	private boolean compareItemStacks(ItemStack input0, ItemStack key) {

		return key.getItem().equals(input0.getItem()) && (key.getMetadata() == 32767 || input0.getMetadata() == key.getMetadata());
	}
	
	public Table<ItemStack[], ItemStack, ItemStack> getRecipes() {
		return this.recipes;
	}
	
	public Map<ItemStack, Float> getExpList() {
		return expList;
	}
	
	public float getEXP(ItemStack stack) {
		
		if (this.expList.containsKey(stack)) return this.expList.get(stack).floatValue();
		return 0.0F;
	}

	public int getItemQuantity(ItemStack result, ItemStack input) {

		if (this.recipeQuants.containsKey(result)) {
			
			for (ItemStack stack : this.recipeQuants.get(result)) {
				if (this.compareItemStacks(stack, input)) {
					return stack.getCount();
				}
			}
			
		}
		
		return 0;
	}
	
	public Map<ItemStack, ItemStack[]> getRecipeQuants() {
		return recipeQuants;
	}
}

