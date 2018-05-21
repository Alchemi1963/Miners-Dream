package com.lavaingot.minersdream.objects.variants.metals;

import com.lavaingot.minersdream.Main;
import com.lavaingot.minersdream.init.ItemInit;
import com.lavaingot.minersdream.objects.items.SubItemPropertyGetter;
import com.lavaingot.minersdream.util.IHasModel;
import com.lavaingot.minersdream.util.IMetaName;
import com.lavaingot.minersdream.util.handlers.EnumHandler.MetalOne;

import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;


public class MetalIngots extends Item implements IHasModel, IMetaName{
	
	public static final PropertyEnum<MetalOne> VARIANT = PropertyEnum.<MetalOne>create("variant", MetalOne.class);
	
	private String name;
	
	public MetalIngots(String name, CreativeTabs tab) {
		
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

		for(MetalOne variant : MetalOne.values()) {
			
			if (this.isInCreativeTab(tab)) {
				items.add(new ItemStack(this, 1, variant.getMeta()));
			
			}
		}
	}
	
	@Override
	public String getSpecialName(ItemStack stack) {

		return MetalOne.values()[stack.getItemDamage()].getName();
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {

		return super.getUnlocalizedName(stack) + "_" + getSpecialName(stack);
	}
	
	@Override
	public void registerModels() {
		
		for(int i = 0; i < MetalOne.values().length; i++) {
			
			Main.proxy.registerVariantRenderer(this, i, "ingot_" + MetalOne.values()[i].getName(), "inventory");
		}
	}
}
