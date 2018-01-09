package com.lavaingot.minersdream.util.handlers;

import com.lavaingot.minersdream.objects.container.ContainerAlloyFurnace;
import com.lavaingot.minersdream.objects.container.ContainerBlockContainer;
import com.lavaingot.minersdream.objects.gui.GUIAlloyFurnace;
import com.lavaingot.minersdream.objects.gui.GUIBlockContainer;
import com.lavaingot.minersdream.objects.tileentities.TileEntityAlloyFurnace;
import com.lavaingot.minersdream.objects.tileentities.TileEntityContainer;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GUIHandler implements IGuiHandler{

	public static final int BLOCK_CONTAINER = 0;
	public static final int ALLOY_FURNACE = 1;
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
	
		if (ID == BLOCK_CONTAINER) {
			return new ContainerBlockContainer(player.inventory, (TileEntityContainer) world.getTileEntity(new BlockPos(x, y, z)));
		} else if (ID == ALLOY_FURNACE) {
			return new ContainerAlloyFurnace(player.inventory, (TileEntityAlloyFurnace) world.getTileEntity(new BlockPos(x, y, z)), player);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

		if(ID == BLOCK_CONTAINER) {
			return new GUIBlockContainer(player.inventory, (TileEntityContainer) world.getTileEntity(new BlockPos(x, y, z)));
		} else if (ID == ALLOY_FURNACE) {
			return new GUIAlloyFurnace(player.inventory, (TileEntityAlloyFurnace) world.getTileEntity(new BlockPos(x, y, z)), player);
		}
		return null;
	}

}
