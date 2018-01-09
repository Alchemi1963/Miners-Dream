package com.lavaingot.minersdream.objects.blocks;

import java.util.Random;

import com.lavaingot.minersdream.Main;
import com.lavaingot.minersdream.init.BlockInit;
import com.lavaingot.minersdream.init.ItemInit;
import com.lavaingot.minersdream.objects.tileentities.TileEntityAlloyFurnace;
import com.lavaingot.minersdream.util.IHasModel;
import com.lavaingot.minersdream.util.handlers.GUIHandler;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
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
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class BlockAlloyFurnace extends Block implements IHasModel, ITileEntityProvider{

	protected static final AxisAlignedBB BOUNDING_BOX = new AxisAlignedBB(0.0625, 0, 0.0625, 0.0625 * 15, 0.0625 * 14, 0.0625 * 15);
	public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	public static final PropertyBool LIT = PropertyBool.create("lit");
	
	public static TileEntityAlloyFurnace TileAlloyFurnace;
	public static boolean lit = false;
	
	public BlockAlloyFurnace(String name, CreativeTabs tab) {
		
		super(Material.IRON);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(LIT, Boolean.valueOf(false)));
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(tab);
		
		
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(name));
	}
	
	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY,
			float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		
		return super.getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, meta, placer, hand).withProperty(FACING, placer.getHorizontalFacing().getOpposite()).withProperty(LIT, Boolean.valueOf(this.lit));
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta) {
		EnumFacing FACE = EnumFacing.getFront(meta);
		
		if(FACE.getAxis()==EnumFacing.Axis.Y) {
			FACE=EnumFacing.NORTH;
		}
		
		return getDefaultState().withProperty(FACING, FACE).withProperty(LIT, Boolean.valueOf(false));
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		
		return ((EnumFacing) state.getValue(FACING)).getIndex();
	}
	
	@Override
	protected BlockStateContainer createBlockState() {
		
		return new BlockStateContainer(this, new IProperty[] {FACING, LIT});
	}
	
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		
		return new TileEntityAlloyFurnace(this);
	}
	
	@Override
	public void registerModels() {
		
		Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {

		TileAlloyFurnace = new TileEntityAlloyFurnace(this);
		return TileAlloyFurnace;
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		
		TileEntityAlloyFurnace te = (TileEntityAlloyFurnace) worldIn.getTileEntity(pos);
		IItemHandler handler = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		for(int slot = 0; slot < handler.getSlots() -1; slot++) {
			ItemStack stack = handler.getStackInSlot(slot);
			InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), stack);
		}
		
		super.breakBlock(worldIn, pos, state);
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		
		if(!worldIn.isRemote) {
			playerIn.openGui(Main.instance, GUIHandler.ALLOY_FURNACE, worldIn, pos.getX(), pos.getY(), pos.getZ());
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
	
	@Override
	public BlockRenderLayer getBlockLayer() {
		
		return BlockRenderLayer.CUTOUT;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return BOUNDING_BOX;
    }
}
