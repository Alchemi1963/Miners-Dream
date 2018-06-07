package com.lavaingot.minersdream.objects.blocks;

import java.awt.Color;
import java.awt.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;

import com.lavaingot.minersdream.Main;
import com.lavaingot.minersdream.init.BlockInit;
import com.lavaingot.minersdream.objects.tileentities.TileContainer;
import com.lavaingot.minersdream.util.IHasModel;
import com.lavaingot.minersdream.util.Reference;
import com.mojang.authlib.properties.PropertyMap;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyHelper;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.scoreboard.Team;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class BlockContainer extends BlockBase implements IHasModel, ITileEntityProvider{

	protected static final AxisAlignedBB BOUNDING_BOX = new AxisAlignedBB(0.0625, 0, 0.0625, 0.0625 * 15, 0.0625 * 15, 0.0625 * 15);
	protected static final AxisAlignedBB BUTTON1 = new AxisAlignedBB(0.0625 * 8, 	0.0625 * 15, 0.0625 * 06, 	0.0625 * 9.5, 	0.0625 * 15.5, 0.0625 * 7.5);
	protected static final AxisAlignedBB BUTTON2 = new AxisAlignedBB(0.0625 * 5.75, 0.0625 * 15, 0.0625 * 06, 	0.0625 * 7.25, 	0.0625 * 15.5, 0.0625 * 7.5);
	protected static final AxisAlignedBB BUTTON3 = new AxisAlignedBB(0.0625 * 3.5, 	0.0625 * 15, 0.0625 * 06, 	0.0625 * 5, 	0.0625 * 15.5, 0.0625 * 7.5);
	protected static final AxisAlignedBB BUTTON4 = new AxisAlignedBB(0.0625 * 8, 	0.0625 * 15, 0.0625 * 3.75,	0.0625 * 9.5, 	0.0625 * 15.5, 0.0625 * 5.25);
	protected static final AxisAlignedBB BUTTON5 = new AxisAlignedBB(0.0625 * 5.75, 0.0625 * 15, 0.0625 * 3.75,	0.0625 * 7.25, 	0.0625 * 15.5, 0.0625 * 5.25);
	protected static final AxisAlignedBB BUTTON6 = new AxisAlignedBB(0.0625 * 3.5, 	0.0625 * 15, 0.0625 * 3.75, 0.0625 * 5, 	0.0625 * 15.5, 0.0625 * 5.25);
	protected static final AxisAlignedBB BUTTON7 = new AxisAlignedBB(0.0625 * 8, 	0.0625 * 15, 0.0625 * 1.5, 	0.0625 * 9.5, 	0.0625 * 15.5, 0.0625 * 03);
	protected static final AxisAlignedBB BUTTON8 = new AxisAlignedBB(0.0625 * 5.75, 0.0625 * 15, 0.0625 * 1.5, 	0.0625 * 7.25, 	0.0625 * 15.5, 0.0625 * 03);
	protected static final AxisAlignedBB BUTTON9 = new AxisAlignedBB(0.0625 * 3.5, 	0.0625 * 15, 0.0625 * 1.5, 	0.0625 * 5, 	0.0625 * 15.5, 0.0625 * 03);
	
	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	public static final PropertyBool OPEN = PropertyBool.create("open");
	
	public BlockContainer(String name, CreativeTabs tab) {
		super(name, Material.IRON, tab);
				
		IBlockState defaultState = this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(OPEN, false);
		
		
		setDefaultState(defaultState);
		
	}
	
	public static class ColorHandler implements IBlockColor{

		@Override
		public int colorMultiplier(IBlockState state, IBlockAccess worldIn, BlockPos pos, int tintIndex) {

			
//			Map<Integer, Boolean> BUTTON_STATES = ((TileContainer)worldIn.getTileEntity(pos)).BUTTON_STATES;
//			
//			return BUTTON_STATES.containsKey(tintIndex) ? BUTTON_STATES.get(tintIndex) ? (new Color(0,255,0)).getRGB() : (new Color(255,0,0)).getRGB() : -1 ;
			
			TileContainer te = (TileContainer) worldIn.getTileEntity(pos);
			
			return te != null ? te.getOpened() ? (new Color(0,255,0)).getRGB() : (new Color(255,0,0)).getRGB() : -1;
		}
		
	}
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY,
			float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		
		return super.getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, meta, placer, hand).withProperty(FACING, placer.getHorizontalFacing().getOpposite()).withProperty(OPEN, false);
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		EnumFacing FACE = EnumFacing.getFront(meta);
		
		if(FACE.getAxis()==EnumFacing.Axis.Y) {
			FACE=EnumFacing.NORTH;
		}
		
		return getDefaultState().withProperty(FACING, FACE);
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		
		return ((EnumFacing) state.getValue(FACING)).getIndex();
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		
		return new BlockStateContainer(this, new IProperty[] {FACING, OPEN});
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		
		return new TileContainer();
	}
	
	@Override
	public void registerModels() {
		
		Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {

		return new TileContainer();
	}
	
	@Override
	public int quantityDropped(IBlockState state, int fortune, Random random) {
		
		return 0;
	}
	
	@Override
	public int quantityDropped(Random random) {
		
		return 0;
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		
		TileContainer te = (TileContainer) worldIn.getTileEntity(pos);
		InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), te.getItemStack(this));
		
		super.breakBlock(worldIn, pos, state);
	}

	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		
		TileContainer te = (TileContainer) worldIn.getTileEntity(pos);
		if (stack.hasTagCompound()) te.readFromNBT(stack.getTagCompound());
		
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
			
		TileContainer te = (TileContainer) worldIn.getTileEntity(pos);
		
		if(playerIn.isSneaking() && te.getOpened()) {
			te.setOpened(false);
			
		} else if (te.getOpened() && !playerIn.isSneaking()) {
			
			te.shouldOpen = true;
			System.out.println(te.shouldOpen);
			playerIn.openGui(Main.instance, Reference.GUI_CONTAINER, worldIn, pos.getX(), pos.getY(), pos.getZ());
			
		} else if (!te.getOpened() && !playerIn.isSneaking()){
			
			playerIn.openGui(Main.instance, Reference.GUI_CONTAINER_OPEN, worldIn, pos.getX(), pos.getY(), pos.getZ());
			
		}
		
		return true;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		
		return false;
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {
		
		return false;
	}
	
	public static void setState(boolean open, World worldIn, BlockPos pos) {
		
		IBlockState state = worldIn.getBlockState(pos);
		TileEntity entity = worldIn.getTileEntity(pos);
		
		worldIn.setBlockState(pos, BlockInit.BLOCK_CONTAINER.getDefaultState().withProperty(FACING, state.getValue(FACING)).withProperty(OPEN, open), 3);
		
		if (entity != null) {
			entity.validate();
			worldIn.setTileEntity(pos, entity);
		}
		
		state.getValue(OPEN);
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return BOUNDING_BOX;
    }
}
