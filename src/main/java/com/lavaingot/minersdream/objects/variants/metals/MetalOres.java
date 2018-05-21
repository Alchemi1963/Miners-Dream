package com.lavaingot.minersdream.objects.variants.metals;

import java.util.ArrayList;

import com.lavaingot.minersdream.Main;
import com.lavaingot.minersdream.init.BlockInit;
import com.lavaingot.minersdream.init.ItemInit;
import com.lavaingot.minersdream.objects.blocks.item.ItemBlockVariant;
import com.lavaingot.minersdream.util.IHasModel;
import com.lavaingot.minersdream.util.IMetaName;
import com.lavaingot.minersdream.util.handlers.EnumHandler.MetalOne;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;


public class MetalOres extends Block implements IHasModel, IMetaName{

	public static final ArrayList<Item> OreOutput = new ArrayList<Item>();
	
	public static final PropertyEnum<MetalOne> VARIANT = PropertyEnum.<MetalOne>create("variant", MetalOne.class);
	
	private String name;
	private String dimension;
	
	public MetalOres(String name, String dimension, CreativeTabs tab) {
		
		super(Material.ROCK);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(tab);
		setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, MetalOne.COPPER));
		
		this.name = name;
		this.dimension = dimension;
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlockVariant(this).setRegistryName(name));

	}
	
	public String getName() {
		return name;
	}
	
	public String getDimension() {
		return dimension;
	}
	
	public static void registerOreSmelting(Block ore, String dimension) {
		
		for(MetalOne variant : MetalOne.values()) {
			String name = "ingot_" + variant.getuName();
			int amount = 0;
			
			if (dimension.equals("overworld")) {amount = 1;}
			else if (dimension.equals("nether")) {amount = 2;}
			else if (dimension.equals("end")) {amount = 4;}
			
			GameRegistry.addSmelting(new ItemStack(ore, 1, variant.getMeta()), new ItemStack(ItemInit.INGOT, amount, variant.getMeta()), 10);
		}
	}
	
	@Override
	public int damageDropped(IBlockState state) {
		
		return ((MetalOne)state.getValue(VARIANT)).getMeta();
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {

		return ((MetalOne)state.getValue(VARIANT)).getMeta();
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		
		return this.getDefaultState().withProperty(VARIANT, MetalOne.byMetaData(meta));
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos,
			EntityPlayer player) {

		return new ItemStack(Item.getItemFromBlock(this), 1, getMetaFromState(world.getBlockState(pos)));
	}
	
	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {

		for(MetalOne variant : MetalOne.values()) {
			
			items.add(new ItemStack(this, 1, variant.getMeta()));
		}
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		
		return new BlockStateContainer(this, new IProperty[] {VARIANT});
	}
	
	@Override
	public String getSpecialName(ItemStack stack) {

		return MetalOne.values()[stack.getItemDamage()].getName();
	}
	
	@Override
	public void registerModels() {
		
		for(int i = 0; i < MetalOne.values().length; i++) {
			
			Main.proxy.registerVariantRenderer(Item.getItemFromBlock(this), i, "ore_" + this.dimension + "_" + MetalOne.values()[i].getName(), "inventory");
		}
	}
}
