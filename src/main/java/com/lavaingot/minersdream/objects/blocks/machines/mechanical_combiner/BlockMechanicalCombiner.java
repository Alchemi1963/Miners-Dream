package com.lavaingot.minersdream.objects.blocks.machines.mechanical_combiner;

import java.util.Random;

import com.lavaingot.minersdream.Main;
import com.lavaingot.minersdream.init.BlockInit;
import com.lavaingot.minersdream.objects.blocks.BlockBase;
import com.lavaingot.minersdream.util.IHasModel;
import com.lavaingot.minersdream.util.Reference;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockMechanicalCombiner extends BlockBase implements IHasModel, ITileEntityProvider{

	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	public static final PropertyBool PROCESSING = PropertyBool.create("processing");
	
	public BlockMechanicalCombiner(String name, CreativeTabs tab) {
		super(name, Material.IRON, tab);
		setSoundType(SoundType.ANVIL);
		this.setDefaultState(this.blockState.getBaseState());
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileMechanicalCombiner();
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		
		return Item.getItemFromBlock(BlockInit.MECHANICAL_COMBINER);
	}
	
	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
	
		return new ItemStack(BlockInit.ALLOYING_FURNACE);
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		
		if (!worldIn.isRemote) playerIn.openGui(Main.instance, Reference.GUI_MECH_COMBINER, worldIn, pos.getX(), pos.getY(), pos.getZ());
		
		return true;
	}
	
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		
		if (!worldIn.isRemote) {
			
			IBlockState north = worldIn.getBlockState(pos.north());
			IBlockState south = worldIn.getBlockState(pos.south());
			IBlockState west = worldIn.getBlockState(pos.west());
			IBlockState east = worldIn.getBlockState(pos.east());
			EnumFacing face = (EnumFacing)state.getValue(FACING);
			
			if (face == EnumFacing.NORTH && north.isFullBlock() && !south.isFullBlock()) face = EnumFacing.SOUTH;
			else if (face == EnumFacing.SOUTH && south.isFullBlock() && !north.isFullBlock()) face = EnumFacing.NORTH;
			else if (face == EnumFacing.WEST && west.isFullBlock() && !east.isFullBlock()) face = EnumFacing.EAST;
			else if (face == EnumFacing.EAST && east.isFullBlock() && !west.isFullBlock()) face = EnumFacing.WEST;
			worldIn.setBlockState(pos, state.withProperty(FACING, face), 2);
		}
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		
		return new TileMechanicalCombiner();
	}
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY,
			float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		
		worldIn.setBlockState(pos, this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite()));
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		
		TileMechanicalCombiner tile = (TileMechanicalCombiner)worldIn.getTileEntity(pos);
		InventoryHelper.dropInventoryItems(worldIn, pos, tile);
		super.breakBlock(worldIn, pos, state);
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		
		return EnumBlockRenderType.MODEL;
	}
	
	@Override
	public IBlockState withRotation(IBlockState state, Rotation rot) {
		
		return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
	}
	
	@Override
	public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {

		return state.withRotation(mirrorIn.toRotation(state.getValue(FACING)));
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		
		return new BlockStateContainer(this, new IProperty[] {FACING, PROCESSING});
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		
		EnumFacing facing = EnumFacing.getFront(meta);
		if (facing.getAxis() == EnumFacing.Axis.Y) facing = EnumFacing.NORTH;
		return this.getDefaultState().withProperty(FACING, facing);
		
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		
		return state.getValue(FACING).getIndex();
	}
	
	public static void setState(boolean active, World worldIn, BlockPos pos) {
		IBlockState state = worldIn.getBlockState(pos);
		TileEntity entity = worldIn.getTileEntity(pos);
		
		if (active) worldIn.setBlockState(pos, BlockInit.MECHANICAL_COMBINER.getDefaultState().withProperty(FACING, state.getValue(FACING)).withProperty(PROCESSING, true), 3);
		else worldIn.setBlockState(pos, BlockInit.MECHANICAL_COMBINER.getDefaultState().withProperty(FACING, state.getValue(FACING)).withProperty(PROCESSING, false), 3);
		
		if (entity != null) {
			entity.validate();
			worldIn.setTileEntity(pos, entity);
		}
	}
}
