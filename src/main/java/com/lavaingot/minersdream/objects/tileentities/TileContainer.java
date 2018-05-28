package com.lavaingot.minersdream.objects.tileentities;

import java.util.HashMap;
import java.util.Map;

import com.lavaingot.minersdream.objects.blocks.BlockContainer;
import com.lavaingot.minersdream.objects.gui.GUIBlockContainerOpen;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;

public class TileContainer extends TileEntity implements ITickable, IInventory{

	private NonNullList<ItemStack> inventory = NonNullList.<ItemStack>withSize(18, ItemStack.EMPTY);
	public static Map<Integer, Boolean> BUTTON_STATES = new HashMap<Integer, Boolean>();
	
	private String customName;
	
	public TileContainer() {
		
		for (int i = 1; i < 10; i++) {
			BUTTON_STATES.put(i, false);
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		ItemStackHelper.loadAllItems(compound, this.inventory);
		super.readFromNBT(compound);
	}
	
	
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		
		ItemStackHelper.saveAllItems(compound, this.inventory);
		return super.writeToNBT(compound);
	}

	@Override
	public void update() {
		
	}
	
	public boolean isUsableByPlayer(EntityPlayer playerIn) {

		return this.world.getTileEntity(this.getPos()) == this && playerIn.getDistanceSq(this.pos.add(0.5, 0.5, 0.5)) <= 64;
	}

	@Override
	public String getName() {

		return this.hasCustomName() ? this.customName : "container.container";
	}

	@Override
	public boolean hasCustomName() {

		return this.customName != null && this.customName != "";
	}

	@Override
	public int getSizeInventory() {

		return this.inventory.size();
	}

	@Override
	public boolean isEmpty() {

		for (ItemStack slot : this.inventory) {
			if (!slot.isEmpty()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public ItemStack getStackInSlot(int index) {

		return this.inventory.get(index);
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {

		return ItemStackHelper.getAndSplit(this.inventory, index, count);
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {

		return ItemStackHelper.getAndRemove(this.inventory, index);
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {

		ItemStack currentStack = (ItemStack)this.inventory.get(index);
		boolean flag = !stack.isEmpty() && stack.isItemEqual(currentStack) && ItemStack.areItemStackTagsEqual(stack, currentStack);
		this.inventory.set(index, stack);
		
		if (stack.getCount() > this.getInventoryStackLimit()) stack.setCount(this.getInventoryStackLimit());	
	}

	@Override
	public int getInventoryStackLimit() {

		return 64;
	}

	@Override
	public void openInventory(EntityPlayer player) {}

	@Override
	public void closeInventory(EntityPlayer player) {}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {

		return true;
	}

	@Override
	public int getField(int id) {
		return 0;
	}

	@Override
	public void setField(int id, int value) {}

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public void clear() {
		this.inventory.clear();
		
	}
}
