package com.lavaingot.minersdream.objects.variants.alloys;

import com.lavaingot.minersdream.Main;
import com.lavaingot.minersdream.init.ItemInit;
import com.lavaingot.minersdream.objects.items.SubItemPropertyGetter;
import com.lavaingot.minersdream.util.IHasModel;
import com.lavaingot.minersdream.util.IMetaName;
import com.lavaingot.minersdream.util.Reference;
import com.lavaingot.minersdream.util.handlers.EnumHandler.BaseAlloy;

import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;

public class BaseAlloyIngots extends Item implements IHasModel, IMetaName{

	public static final PropertyEnum<BaseAlloy> VARIANT = PropertyEnum.<BaseAlloy>create("variant", BaseAlloy.class);
	
	private String name;
	
	public BaseAlloyIngots(String name, CreativeTabs tab) {
	
		super();
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(tab);
		
		setMaxDamage(0);
		setHasSubtypes(true);
		addPropertyOverride(new ResourceLocation("type"), new SubItemPropertyGetter());
		
		this.name = name;
		ItemInit.ITEMS.add(this);
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		
		for(BaseAlloy variant : BaseAlloy.values()) {
			
			if (this.isInCreativeTab(tab)) {
				items.add(new ItemStack(this, 1, variant.getMeta()));
			}
			
		}
		
	}
	
	@Override
	public String getSpecialName(ItemStack stack) {
		
		return BaseAlloy.values()[stack.getItemDamage()].getName();
		
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		
		return super.getUnlocalizedName(stack) + "_" + getSpecialName(stack);
	}
	
	@Override
	public void registerModels() {
	
		for (int i = 0; i < BaseAlloy.values().length; i++) {
		
			ModelLoader.setCustomModelResourceLocation(this, i, new ModelResourceLocation(new ResourceLocation(Reference.MOD_ID, "alloy_ingot"), "inventory"));
			
		}

	}
	
}
