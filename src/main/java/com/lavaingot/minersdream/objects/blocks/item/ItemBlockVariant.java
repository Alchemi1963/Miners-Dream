package com.lavaingot.minersdream.objects.blocks.item;

import com.lavaingot.minersdream.util.IMetaName;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockVariant extends ItemBlock{

	public ItemBlockVariant(Block block) {
		
		super(block);
		setHasSubtypes(true);
		setMaxDamage(0);
		
	}
	
	@Override
	public int getMetadata(int damage) {
		
		return (damage);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		
		return super.getUnlocalizedName() + "_" + ((IMetaName)this.block).getSpecialName(stack);
	}
}
