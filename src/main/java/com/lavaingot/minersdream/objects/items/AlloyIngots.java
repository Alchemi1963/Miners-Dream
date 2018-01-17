package com.lavaingot.minersdream.objects.items;

import com.lavaingot.minersdream.Main;
import com.lavaingot.minersdream.init.ItemInit;
import com.lavaingot.minersdream.util.IHasModel;
import com.lavaingot.minersdream.util.IMetaName;
import com.lavaingot.minersdream.util.handlers.EnumHandler;

import akka.dispatch.sysmsg.Create;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

public class AlloyIngots extends Item implements IHasModel, IMetaName{

	public static final PropertyEnum<EnumHandler.AlloyType> VARIANT = PropertyEnum.<EnumHandler.AlloyType>create("variant", EnumHandler.AlloyType.class);
	
	private String name;
	
	public AlloyIngots(String name, CreativeTabs tab) {
	
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
		
		for(EnumHandler.AlloyType variant : EnumHandler.AlloyType.values()) {
			
			if (this.isInCreativeTab(tab)) {
				items.add(new ItemStack(this, 1, variant.getMeta()));
			}
			
		}
		
	}
	
	@Override
	public String getSpecialName(ItemStack stack) {
		
		return EnumHandler.AlloyType.values()[stack.getItemDamage()].getName();
		
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		
		return super.getUnlocalizedName(stack) + "_" + getSpecialName(stack);
	}
	
	@Override
	public void registerModels() {
	
		for (int i = 0; i < EnumHandler.AlloyType.values().length; i++) {
		
			Main.proxy.registerVariantRenderer(this, i, "ingot_" + EnumHandler.AlloyType.values()[i].getName(), "inventory");
			
		}

	}
	
}
