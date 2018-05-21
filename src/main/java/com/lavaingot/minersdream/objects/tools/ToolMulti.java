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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

public class ToolMulti extends ItemTool implements IHasModel{

	private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(Blocks.ACTIVATOR_RAIL, Blocks.COAL_ORE, Blocks.COBBLESTONE, Blocks.DETECTOR_RAIL, Blocks.DIAMOND_BLOCK, Blocks.DIAMOND_ORE, Blocks.DOUBLE_STONE_SLAB, Blocks.GOLDEN_RAIL, Blocks.GOLD_BLOCK, Blocks.GOLD_ORE, Blocks.ICE, Blocks.IRON_BLOCK, Blocks.IRON_ORE, Blocks.LAPIS_BLOCK, Blocks.LAPIS_ORE, Blocks.LIT_REDSTONE_ORE, Blocks.MOSSY_COBBLESTONE, Blocks.NETHERRACK, Blocks.PACKED_ICE, Blocks.RAIL, Blocks.REDSTONE_ORE, Blocks.SANDSTONE, Blocks.RED_SANDSTONE, Blocks.STONE, Blocks.STONE_SLAB, Blocks.STONE_BUTTON, Blocks.STONE_PRESSURE_PLATE, Blocks.PLANKS, Blocks.ACACIA_DOOR, Blocks.ACACIA_FENCE, Blocks.ACACIA_FENCE_GATE, Blocks.ACACIA_STAIRS, Blocks.BIRCH_DOOR, Blocks.BIRCH_FENCE, Blocks.BIRCH_FENCE_GATE, Blocks.BIRCH_STAIRS, Blocks.BOOKSHELF, Blocks.CHEST, Blocks.CHORUS_PLANT, Blocks.COCOA, Blocks.DARK_OAK_DOOR, Blocks.DARK_OAK_FENCE, Blocks.DARK_OAK_FENCE_GATE, Blocks.DARK_OAK_STAIRS, Blocks.DAYLIGHT_DETECTOR, Blocks.DAYLIGHT_DETECTOR_INVERTED, Blocks.JUNGLE_DOOR, Blocks.JUNGLE_FENCE, Blocks.JUNGLE_FENCE_GATE, Blocks.JUNGLE_STAIRS, Blocks.LADDER, Blocks.LIT_PUMPKIN, Blocks.LOG, Blocks.LOG2, Blocks.MELON_BLOCK, Blocks.NOTEBLOCK, Blocks.OAK_DOOR, Blocks.OAK_FENCE, Blocks.OAK_FENCE_GATE, Blocks.OAK_STAIRS, Blocks.PISTON, Blocks.PISTON_EXTENSION, Blocks.PISTON_HEAD, Blocks.STICKY_PISTON, Blocks.PLANKS, Blocks.PUMPKIN, Blocks.SPRUCE_DOOR, Blocks.SPRUCE_FENCE, Blocks.SPRUCE_FENCE_GATE, Blocks.SPRUCE_STAIRS, Blocks.TRAPDOOR, Blocks.TRAPPED_CHEST, Blocks.WALL_BANNER, Blocks.STANDING_SIGN, Blocks.WALL_SIGN, Blocks.WOODEN_BUTTON, Blocks.WOODEN_PRESSURE_PLATE, Blocks.WOODEN_SLAB, Blocks.DOUBLE_WOODEN_SLAB, Blocks.CLAY, Blocks.DIRT, Blocks.FARMLAND, Blocks.GRASS, Blocks.GRAVEL, Blocks.MYCELIUM, Blocks.SAND, Blocks.SNOW, Blocks.SNOW_LAYER, Blocks.SOUL_SAND, Blocks.GRASS_PATH, Blocks.CONCRETE_POWDER);
	private static final Set<Material> EFFECTIVE_MATERIALS = Sets.newHashSet(Material.ANVIL, Material.CIRCUITS, Material.CLAY, Material.CORAL, Material.CRAFTED_SNOW, Material.GLASS, Material.GOURD, Material.GRASS, Material.GROUND, Material.IRON, Material.LEAVES, Material.PACKED_ICE, Material.PISTON, Material.PLANTS, Material.REDSTONE_LIGHT, Material.ROCK, Material.SAND, Material.SNOW, Material.TNT, Material.WOOD);
	private NonNullList<ItemStack> ITEMS = NonNullList.<ItemStack>withSize(4, ItemStack.EMPTY);
	
	public ToolMulti(String name, ToolMaterial material, CreativeTabs tab) {
		
		super(3.0F, 2.5F, material, EFFECTIVE_ON);
		
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(tab);
		
		ItemInit.ITEMS.add(this);
		
	}
	
	public ToolMulti setItems(ItemStack stack1, ItemStack stack2, ItemStack stack3, ItemStack stack4) {
		if (this.isEmpty()) {
			ITEMS.add(0, stack1);
			ITEMS.add(1, stack2);
			ITEMS.add(2, stack3);
			ITEMS.add(3, stack4);
		}
		
		return this;
	}
	
	public boolean isEmpty() {
		
		for (ItemStack stack : this.ITEMS) {
			if (!stack.isEmpty()) return false;
		}
		return true;
		
	}
	
	@Override
	public boolean canHarvestBlock(IBlockState blockIn) {

		Block block = blockIn.getBlock();
		
		if (EFFECTIVE_MATERIALS.contains(blockIn.getMaterial()) || block.getHarvestTool(blockIn) == null) {
			return this.toolMaterial.getHarvestLevel() >= block.getHarvestLevel(blockIn);
		}
		
		return true;
	}
	
	@Override
	public float getDestroySpeed(ItemStack stack, IBlockState state) {
	
		return canHarvestBlock(state) ? this.efficiency : 0.0F;
	}

	@Override
	public void registerModels() {

		Main.proxy.registerItemRenderer(this, 0, "inventory");		
	}
	
	@Override
	public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {

		Main.print("HELLO WORLD");
		Main.print(stack);
	}

}
