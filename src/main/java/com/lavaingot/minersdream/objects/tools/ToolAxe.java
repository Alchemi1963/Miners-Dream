package com.lavaingot.minersdream.objects.tools;

import java.util.Set;

import com.google.common.collect.Sets;
import com.lavaingot.minersdream.Main;
import com.lavaingot.minersdream.init.ItemInit;
import com.lavaingot.minersdream.util.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;

public class ToolAxe extends ItemTool implements IHasModel{

	private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(Blocks.PLANKS, Blocks.ACACIA_DOOR, Blocks.ACACIA_FENCE, Blocks.ACACIA_FENCE_GATE, Blocks.ACACIA_STAIRS, Blocks.BIRCH_DOOR, Blocks.BIRCH_FENCE, Blocks.BIRCH_FENCE_GATE, Blocks.BIRCH_STAIRS, Blocks.BOOKSHELF, Blocks.CHEST, Blocks.CHORUS_PLANT, Blocks.COCOA, Blocks.DARK_OAK_DOOR, Blocks.DARK_OAK_FENCE, Blocks.DARK_OAK_FENCE_GATE, Blocks.DARK_OAK_STAIRS, Blocks.DAYLIGHT_DETECTOR, Blocks.DAYLIGHT_DETECTOR_INVERTED, Blocks.JUNGLE_DOOR, Blocks.JUNGLE_FENCE, Blocks.JUNGLE_FENCE_GATE, Blocks.JUNGLE_STAIRS, Blocks.LADDER, Blocks.LIT_PUMPKIN, Blocks.LOG, Blocks.LOG2, Blocks.MELON_BLOCK, Blocks.NOTEBLOCK, Blocks.OAK_DOOR, Blocks.OAK_FENCE, Blocks.OAK_FENCE_GATE, Blocks.OAK_STAIRS, Blocks.PISTON, Blocks.PISTON_EXTENSION, Blocks.PISTON_HEAD, Blocks.STICKY_PISTON, Blocks.PLANKS, Blocks.PUMPKIN, Blocks.SPRUCE_DOOR, Blocks.SPRUCE_FENCE, Blocks.SPRUCE_FENCE_GATE, Blocks.SPRUCE_STAIRS, Blocks.TRAPDOOR, Blocks.TRAPPED_CHEST, Blocks.WALL_BANNER, Blocks.STANDING_SIGN, Blocks.WALL_SIGN, Blocks.WOODEN_BUTTON, Blocks.WOODEN_PRESSURE_PLATE, Blocks.WOODEN_SLAB, Blocks.DOUBLE_WOODEN_SLAB);
	
	public ToolAxe(String name, ToolMaterial material, CreativeTabs tab) {
		super(material, EFFECTIVE_ON);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(tab);
		
		ItemInit.ITEMS.add(this);
	}
	
	public float getStrVsBlock(ItemStack stack, IBlockState state) {
		
		Material material = state.getMaterial();
		return material != Material.WOOD && material != Material.PLANTS && material != Material.VINE ? this.getStrVsBlock(stack, state) : this.efficiency;
	}
	
	@Override
	public void registerModels() {
		
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}
}
