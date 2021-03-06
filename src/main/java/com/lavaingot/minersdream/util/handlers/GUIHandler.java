package com.lavaingot.minersdream.util.handlers;

import com.lavaingot.minersdream.objects.blocks.machines.alloyer.TileAlloyingFurnace;
import com.lavaingot.minersdream.objects.blocks.machines.mechanical_combiner.TileMechanicalCombiner;
import com.lavaingot.minersdream.objects.container.ContainerAlloyingFurnace;
import com.lavaingot.minersdream.objects.container.ContainerBlockContainer;
import com.lavaingot.minersdream.objects.container.ContainerBlockContainerOpen;
import com.lavaingot.minersdream.objects.container.ContainerMechanicalCombiner;
import com.lavaingot.minersdream.objects.gui.GUIAlloyingFurnace;
import com.lavaingot.minersdream.objects.gui.GUIBlockContainerOpen;
import com.lavaingot.minersdream.objects.gui.GUIBlockContainer;
import com.lavaingot.minersdream.objects.gui.GUIMechanicalCombiner;
import com.lavaingot.minersdream.objects.tileentities.TileContainer;
import com.lavaingot.minersdream.util.Reference;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GUIHandler implements IGuiHandler{
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
	
		if (ID == Reference.GUI_CONTAINER) {
			return new ContainerBlockContainer(player.inventory, (TileContainer) world.getTileEntity(new BlockPos(x, y, z)));
		} else if (ID == Reference.GUI_ALLOYING_FURNACE) {
			return new ContainerAlloyingFurnace(player.inventory, (TileAlloyingFurnace)world.getTileEntity(new BlockPos(x, y, z)));
		} else if (ID == Reference.GUI_MECH_COMBINER) {
			return new ContainerMechanicalCombiner(player.inventory, (TileMechanicalCombiner)world.getTileEntity(new BlockPos(x, y, z)));
		} else if (ID == Reference.GUI_CONTAINER_OPEN) {
			return new ContainerBlockContainerOpen(player.inventory, (TileContainer) world.getTileEntity(new BlockPos(x, y, z)));
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

		if(ID == Reference.GUI_CONTAINER) {
			return new GUIBlockContainer(player.inventory, (TileContainer) world.getTileEntity(new BlockPos(x, y, z)));
		} else if (ID == Reference.GUI_ALLOYING_FURNACE) {
			return new GUIAlloyingFurnace(player.inventory, (TileAlloyingFurnace) world.getTileEntity(new BlockPos(x, y, z)));
		} else if (ID == Reference.GUI_MECH_COMBINER) {
			return new GUIMechanicalCombiner(player.inventory, (TileMechanicalCombiner) world.getTileEntity(new BlockPos(x, y, z)));
		} else if (ID == Reference.GUI_CONTAINER_OPEN) {
			return new GUIBlockContainerOpen(player.inventory, (TileContainer) world.getTileEntity(new BlockPos(x, y, z)), player);
		}
		return null;
	}

}
