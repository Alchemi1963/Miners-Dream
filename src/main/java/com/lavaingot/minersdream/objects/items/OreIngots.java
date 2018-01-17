package com.lavaingot.minersdream.objects.items;

import java.util.logging.Logger;

import com.lavaingot.minersdream.Main;
import com.lavaingot.minersdream.init.ItemInit;
import com.lavaingot.minersdream.util.IHasModel;
import com.lavaingot.minersdream.util.IMetaName;
import com.lavaingot.minersdream.util.handlers.EnumHandler;

import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;


public class OreIngots extends Item implements IHasModel, IMetaName{
	
	public static final PropertyEnum<EnumHandler.OreType> VARIANT = PropertyEnum.<EnumHandler.OreType>create("variant", EnumHandler.OreType.class);
	
	private String name;
	
	public OreIngots(String name, CreativeTabs tab) {
		
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

		for(EnumHandler.OreType variant : EnumHandler.OreType.values()) {
			
			if (this.isInCreativeTab(tab)) {
				items.add(new ItemStack(this, 1, variant.getMeta()));
			
			}
		}
	}
	
	@Override
	public String getSpecialName(ItemStack stack) {

		return EnumHandler.OreType.values()[stack.getItemDamage()].getName();
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {

		return super.getUnlocalizedName(stack) + "_" + getSpecialName(stack);
	}
	
	@Override
	public void registerModels() {
		
		for(int i = 0; i < EnumHandler.OreType.values().length; i++) {
			
			Main.proxy.registerVariantRenderer(this, i, "ingot_" + EnumHandler.OreType.values()[i].getName(), "inventory");
		}
	}
}
