package com.lavaingot.minersdream.objects.variants.metals;

import java.util.List;

import com.lavaingot.minersdream.Main;
import com.lavaingot.minersdream.init.BlockInit;
import com.lavaingot.minersdream.init.ItemInit;
import com.lavaingot.minersdream.objects.blocks.item.ItemBlockVariant;
import com.lavaingot.minersdream.util.IHasModel;
import com.lavaingot.minersdream.util.IMetaName;
import com.lavaingot.minersdream.util.handlers.EnumHandler.MetalBlockOne;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class MetalBlocks extends Block implements IHasModel, IMetaName{
	
	public static final PropertyEnum<MetalBlockOne> VARIANT = PropertyEnum.<MetalBlockOne>create("variant", MetalBlockOne.class);
	
	protected static final AxisAlignedBB BOUNDING_BOX = new AxisAlignedBB(0, 0, 0, 0.0625 * 16, 0.0625 * 16, 0.0625 * 16);
	protected static final AxisAlignedBB BOX1 = new AxisAlignedBB(0.0625 * 00, 0.0625 * 00, 0.0625 * 00, 0.0625 * 00, 0.0625 * 16, 0.0625 * 16);
	protected static final AxisAlignedBB BOX2 = new AxisAlignedBB(0.0625 * 00, 0.0625 * 00, 0.0625 * 16, 0.0625 * 16, 0.0625 * 16, 0.0625 * 16);
	protected static final AxisAlignedBB BOX3 = new AxisAlignedBB(0.0625 * 16, 0.0625 * 00, 0.0625 * 16, 0.0625 * 16, 0.0625 * 16, 0.0625 * 00);
	protected static final AxisAlignedBB BOX4 = new AxisAlignedBB(0.0625 * 16, 0.0625 * 00, 0.0625 * 00, 0.0625 * 00, 0.0625 * 16, 0.0625 * 00);
	
	protected static final AxisAlignedBB BOX5 = new AxisAlignedBB(0.0625 * 01, 0.0625 * 01, 0.0625 * 01, 0.0625 * 01, 0.0625 * 15, 0.0625 * 15);
	protected static final AxisAlignedBB BOX6 = new AxisAlignedBB(0.0625 * 01, 0.0625 * 01, 0.0625 * 15, 0.0625 * 15, 0.0625 * 15, 0.0625 * 15);
	protected static final AxisAlignedBB BOX7 = new AxisAlignedBB(0.0625 * 15, 0.0625 * 01, 0.0625 * 15, 0.0625 * 15, 0.0625 * 15, 0.0625 * 01);
	protected static final AxisAlignedBB BOX8 = new AxisAlignedBB(0.0625 * 15, 0.0625 * 01, 0.0625 * 01, 0.0625 * 01, 0.0625 * 15, 0.0625 * 01);
	
	protected static final AxisAlignedBB BOX9 = new AxisAlignedBB(0.0625 * 02, 0.0625 * 02, 0.0625 * 02, 0.0625 * 02, 0.0625 * 14, 0.0625 * 14);
	protected static final AxisAlignedBB BOX10 = new AxisAlignedBB(0.0625 * 02, 0.0625 * 02, 0.0625 * 14, 0.0625 * 14, 0.0625 * 14, 0.0625 * 14);
	protected static final AxisAlignedBB BOX11 = new AxisAlignedBB(0.0625 * 14, 0.0625 * 02, 0.0625 * 14, 0.0625 * 14, 0.0625 * 14, 0.0625 * 02);
	protected static final AxisAlignedBB BOX12 = new AxisAlignedBB(0.0625 * 14, 0.0625 * 02, 0.0625 * 02, 0.0625 * 02, 0.0625 * 14, 0.0625 * 02);
	
	protected static final AxisAlignedBB BOX13 = new AxisAlignedBB(0.0625 * 03, 0.0625 * 03, 0.0625 * 03, 0.0625 * 13, 0.0625 * 13, 0.0625 * 13);

	
	
	
	
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
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox,
			List<AxisAlignedBB> collidingBoxes, Entity entityIn, boolean isActualState) {
		
		if (state.getBlock().getMetaFromState(state) == MetalBlockOne.BISMUTH.getMeta()) {
			addCollisionBoxToList(pos, entityBox, collidingBoxes, BOX1);
			addCollisionBoxToList(pos, entityBox, collidingBoxes, BOX2);
			addCollisionBoxToList(pos, entityBox, collidingBoxes, BOX3);
			addCollisionBoxToList(pos, entityBox, collidingBoxes, BOX4);
			addCollisionBoxToList(pos, entityBox, collidingBoxes, BOX5);
			addCollisionBoxToList(pos, entityBox, collidingBoxes, BOX6);
			addCollisionBoxToList(pos, entityBox, collidingBoxes, BOX7);
			addCollisionBoxToList(pos, entityBox, collidingBoxes, BOX8);
			addCollisionBoxToList(pos, entityBox, collidingBoxes, BOX9);
			addCollisionBoxToList(pos, entityBox, collidingBoxes, BOX10);
			addCollisionBoxToList(pos, entityBox, collidingBoxes, BOX11);
			addCollisionBoxToList(pos, entityBox, collidingBoxes, BOX12);
			addCollisionBoxToList(pos, entityBox, collidingBoxes, BOX13);
		} else {
			addCollisionBoxToList(pos, entityBox, collidingBoxes, BOUNDING_BOX);
		}
		
	}
	
	
	@Override
	public void registerModels() {
		
		for(int i = 0; i < MetalBlockOne.values().length; i++) {
			
			if (i == 12) System.out.println(MetalBlockOne.values()[i].getName());
			Main.proxy.registerVariantRenderer(Item.getItemFromBlock(this), i, "block_metal_" + MetalBlockOne.values()[i].getName(), "inventory");
		}
	}
}
