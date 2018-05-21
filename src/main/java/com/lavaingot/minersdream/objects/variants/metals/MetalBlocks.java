package com.lavaingot.minersdream.objects.variants.metals;

import com.lavaingot.minersdream.Main;
import com.lavaingot.minersdream.init.BlockInit;
import com.lavaingot.minersdream.init.ItemInit;
import com.lavaingot.minersdream.objects.blocks.item.ItemBlockVariant;
import com.lavaingot.minersdream.util.IHasModel;
import com.lavaingot.minersdream.util.IMetaName;
import com.lavaingot.minersdream.util.handlers.EnumHandler.MetalBlockOne;

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

public class MetalBlocks extends Block implements IHasModel, IMetaName{
	
	public static final PropertyEnum<MetalBlockOne> VARIANT = PropertyEnum.<MetalBlockOne>create("variant", MetalBlockOne.class);
	
	private String name;
	
	public MetalBlocks(String name, CreativeTabs tab) {
		super(Material.IRON);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(tab);
		setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, MetalBlockOne.COPPER));
		
		this.name = name;
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlockVariant(this).setRegistryName(name));

	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public int damageDropped(IBlockState state) {
		
		return ((MetalBlockOne)state.getValue(VARIANT)).getMeta();
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {

		return ((MetalBlockOne)state.getValue(VARIANT)).getMeta();
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		
		return this.getDefaultState().withProperty(VARIANT, MetalBlockOne.byMetaData(meta));
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos,
			EntityPlayer player) {

		return new ItemStack(Item.getItemFromBlock(this), 1, getMetaFromState(world.getBlockState(pos)));
	}
	
	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {

		for(MetalBlockOne variant : MetalBlockOne.values()) {
			
			items.add(new ItemStack(this, 1, variant.getMeta()));
			
		}
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		
		return new BlockStateContainer(this, new IProperty[] {VARIANT});
	}
	
	@Override
	public String getSpecialName(ItemStack stack) {
		if (stack.getItemDamage() >= 12) {return MetalBlockOne.values()[0].getName();}
		return MetalBlockOne.values()[stack.getItemDamage()].getName();
	}
	
	@Override
	public void registerModels() {
		
		for(int i = 0; i < MetalBlockOne.values().length; i++) {
			
			Main.proxy.registerVariantRenderer(Item.getItemFromBlock(this), i, "block_metal_" + MetalBlockOne.values()[i].getName(), "inventory");
		}
	}
}
