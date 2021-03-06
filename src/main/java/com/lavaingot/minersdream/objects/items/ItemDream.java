package com.lavaingot.minersdream.objects.items;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.common.collect.ImmutableMap;
import com.lavaingot.minersdream.Main;
import com.lavaingot.minersdream.init.BlockInit;
import com.lavaingot.minersdream.init.ItemInit;
import com.lavaingot.minersdream.util.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ItemDream extends Item implements IHasModel{
	
	public final ArrayList<Block> BLOCKS = new ArrayList<Block>();
	
	public ItemDream(String name, CreativeTabs tab) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(tab);
		
		BLOCKS.add(Blocks.STONE);
		BLOCKS.add(Blocks.SANDSTONE);
		BLOCKS.add(Blocks.GRAVEL);
		BLOCKS.add(Blocks.SAND);
		BLOCKS.add(Blocks.DIRT);
		BLOCKS.add(Blocks.COBBLESTONE);
		BLOCKS.add(Blocks.WATER);
		BLOCKS.add(Blocks.FLOWING_WATER);
		BLOCKS.add(Blocks.LAVA);
		BLOCKS.add(Blocks.FLOWING_LAVA);
		
		ItemInit.ITEMS.add((Item)this);
	}
	
	@Override
	public void registerModels() {
		
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}
	
	
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ) {
		if ((Item)this == player.getHeldItemMainhand().getItem()) {
			String face = "down";
			if (BLOCKS.contains(worldIn.getBlockState(pos).getBlock())){worldIn.setBlockState(pos, BlockInit.BLOCK_TORCHHANDLER.getDefaultState());;}
			face = player.getHorizontalFacing().getName();
			
			System.out.println(player.world.isRemote);
			if (player.world.isRemote) {worldIn.playSound(player, pos, SoundEvents.ENTITY_WITHER_BREAK_BLOCK, SoundCategory.HOSTILE, 1.0F, 1.0F);}
			
			if (face.equals("north")) {
				for (int x = -5; x < 6; x++) {
					for (int y = -1; y < 5; y++) {
						for (int z = 0; z > -100; z--) {
							if (BLOCKS.contains(worldIn.getBlockState(pos.add(x, y, z)).getBlock())){worldIn.setBlockState(pos.add(x, y, z), BlockInit.BLOCK_TORCHHANDLER.getDefaultState());}
							if (y == 4 && worldIn.getBlockState(pos.add(x, y+2, z)).getBlock().equals(Blocks.SAND) || y == 4 && worldIn.getBlockState(pos.add(x, y+2, z)).getBlock().equals(Blocks.GRAVEL)) {
								worldIn.setBlockState(pos.add(x, y+1, z), Blocks.GLASS.getDefaultState());
							}
						}
					}
				}
			} else if (face.equals("south")) {
				for (int x = -5; x < 6; x++) {
					for (int y = -1; y < 5; y++) {
						for (int z = 0; z < 100; z++) {
							if (BLOCKS.contains(worldIn.getBlockState(pos.add(x, y, z)).getBlock())){worldIn.setBlockState(pos.add(x, y, z), BlockInit.BLOCK_TORCHHANDLER.getDefaultState());}
							if (y == 4 && worldIn.getBlockState(pos.add(x, y+2, z)).getBlock().equals(Blocks.SAND) || y == 4 && worldIn.getBlockState(pos.add(x, y+2, z)).getBlock().equals(Blocks.GRAVEL)) {
								worldIn.setBlockState(pos.add(x, y+1, z), Blocks.GLASS.getDefaultState());
							}
						}
					}
				}
			} else if (face.equals("west")) {
				for (int x = 0; x > -100; x--) {
					for (int y = -1; y < 5; y++) {
						for (int z = -5; z < 6; z++) {
							if (BLOCKS.contains(worldIn.getBlockState(pos.add(x, y, z)).getBlock())){worldIn.setBlockState(pos.add(x, y, z), BlockInit.BLOCK_TORCHHANDLER.getDefaultState());}
							if (y == 4 && worldIn.getBlockState(pos.add(x, y+2, z)).getBlock().equals(Blocks.SAND) || y == 4 && worldIn.getBlockState(pos.add(x, y+2, z)).getBlock().equals(Blocks.GRAVEL)) {
								worldIn.setBlockState(pos.add(x, y+1, z), Blocks.GLASS.getDefaultState());
							}
						}
					}
				}
			} else if (face.equals("east")) {
				for (int x = 0; x < 100; x++) {
					for (int y = -1; y < 5; y++) {
						for (int z = -5; z < 6; z++) {
							if (BLOCKS.contains(worldIn.getBlockState(pos.add(x, y, z)).getBlock())){worldIn.setBlockState(pos.add(x, y, z), BlockInit.BLOCK_TORCHHANDLER.getDefaultState());}
							if (y == 4 && worldIn.getBlockState(pos.add(x, y+2, z)).getBlock().equals(Blocks.SAND) || y == 4 && worldIn.getBlockState(pos.add(x, y+2, z)).getBlock().equals(Blocks.GRAVEL)) {
								worldIn.setBlockState(pos.add(x, y+1, z), Blocks.GLASS.getDefaultState());
							}
						}
					}
				}
			}
		}
		
		return EnumActionResult.SUCCESS;
	}
}
