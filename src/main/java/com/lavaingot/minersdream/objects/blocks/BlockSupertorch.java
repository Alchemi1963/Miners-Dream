package com.lavaingot.minersdream.objects.blocks;

import java.util.List;

import com.lavaingot.minersdream.Main;
import com.lavaingot.minersdream.init.BlockInit;
import com.lavaingot.minersdream.init.ItemInit;
import com.lavaingot.minersdream.util.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.event.world.BlockEvent.PlaceEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class BlockSupertorch extends Block implements IHasModel{

	private static final AxisAlignedBB BOUNDING_BOX = new AxisAlignedBB(0, 0, 0, 1, 0.5625, 1);
	
	public BlockSupertorch(String name, Material material, CreativeTabs tab) {
		
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(tab);
		setLightLevel(1.0F);
		
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(name));
	}
	
	@SubscribeEvent
	public static void onBlockPlace(PlaceEvent event) {
		if (event.getPlacedBlock().getBlock().equals(BlockInit.BLOCK_SUPERTORCH)){
			BlockPos pos = event.getPos();
			World world = event.getWorld();
			
			if (!world.getBlockState(pos.add(0, -1, 0)).getBlock().equals(Blocks.AIR)) {
				
				for (int x = -2; x < 3; x++) {
					for (int z = -2; z < 3; z++) {
						if (world.getBlockState(pos.add(x, 0, z)).getBlock().equals(Blocks.AIR) && !world.getBlockState(pos.add(x, -1, z)).getBlock().equals(Blocks.AIR)) {world.setBlockState(pos.add(x, 0, z), BlockInit.BLOCK_TORCHHANDLER.getDefaultState());}
					
					}
				}
			} else {
				event.setCanceled(true);
			}
		}
	}
	
	@SubscribeEvent
	public static void onBlockDestroy(BreakEvent event) {
		if (event.getState().getBlock().equals(BlockInit.BLOCK_SUPERTORCH)){
			BlockPos pos = event.getPos();
			World world = event.getWorld();
			
			for (int x = -2; x < 3; x++) {
				for (int z = -2; z < 3; z++) {
					if (world.getBlockState(pos.add(x, 0, z)).getBlock().equals(BlockInit.BLOCK_TORCHHANDLER)) {world.setBlockToAir(pos.add(x, 0, z));}
				}
			}
		}
	}
	
	@Override
	public boolean isFullCube(IBlockState state) {

		return false;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		
		return false;
	}
	
	@Override
	public BlockRenderLayer getBlockLayer() {
		
		return BlockRenderLayer.TRANSLUCENT;
	}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		
		return BOUNDING_BOX;
	}
	
	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox,
			List<AxisAlignedBB> collidingBoxes, Entity entityIn, boolean isActualState) {
		
		super.addCollisionBoxToList(pos, entityBox, collidingBoxes, BOUNDING_BOX);
	}
	
	@Override
	public void registerModels() {
		
		Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}
}
