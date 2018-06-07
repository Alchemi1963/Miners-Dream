package com.lavaingot.minersdream.objects.tileentities;

import java.util.HashMap;
import java.util.Map;

import com.lavaingot.minersdream.objects.blocks.BlockContainer;
import com.lavaingot.minersdream.util.handlers.MinersdreamPacketHandler;
import com.lavaingot.minersdream.util.handlers.MinersdreamPacketHandler.CodeMessage;
import com.lavaingot.minersdream.util.handlers.MinersdreamPacketHandler.OpenMessage;
import com.lavaingot.minersdream.util.handlers.MinersdreamPacketHandler.OwnerMessage;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;

public class TileContainer extends TileEntity implements ITickable, IInventory{

	private NonNullList<ItemStack> inventory = NonNullList.<ItemStack>withSize(18, ItemStack.EMPTY);
	public Map<Integer, Boolean> BUTTON_STATES = new HashMap<Integer, Boolean>();
	
	
	private int code;
	public boolean shouldOpen = false;
	public String owner = "Lava_Ingot";
	private boolean opened = false;
	
		
	private String customName;
	
	public TileContainer() {
		
		for (int i = 1; i < 10; i++) {
			BUTTON_STATES.put(i, false);
		}
	}
	
	public boolean hasCode() {
		return this.code != 0 && String.valueOf(this.code).length() == 4;  
	}
	
	public boolean checkCode(String[] input) {
		if (input.length != 4) return false;
		String s = String.join("", input);
		int code_check = Integer.parseInt(s);
		return code_check == this.code;
	}
	
	public void setCode(int input) {
		this.code = input;
	}
	
	public void setCode(String[] input) {
		if (input.length == 4) {
			String s = String.join("", input);
			this.code = Integer.parseInt(s);
			this.markDirty();
		}		
	}
	
	public int getCode() {
		return code;
	}
	
	public void clearCode() {
		this.code = 0;
		this.markDirty();
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		
		if (compound.hasKey("owner")) this.owner = compound.getString("owner");
		if (compound.hasKey("code")) this.code = compound.getInteger("code");
		if (compound.hasKey("opened")) this.opened = compound.getBoolean("opened");
		if (compound.hasKey("Items")) ItemStackHelper.loadAllItems(compound, this.inventory);
		super.readFromNBT(compound);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setString("owner", this.owner);
		compound.setInteger("code", this.code);
		compound.setBoolean("opened", this.opened);
		ItemStackHelper.saveAllItems(compound, this.inventory);
		return super.writeToNBT(compound);
	}
	
	public void setOpened(boolean opened) {
		this.opened = opened;
		this.markDirty();
	}
	
	public boolean getOpened() {
		return this.opened;
	}

	@Override
	public void update() {
		
		if (world.isRemote) System.out.println("" + shouldOpen);
		if (!world.isRemote) System.out.println("" + shouldOpen);
		
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

	public ItemStack getItemStack(Block b) {
		
		NBTTagCompound nbt = new NBTTagCompound();
		System.out.println(this.inventory);
		ItemStackHelper.saveAllItems(nbt, this.inventory);
		nbt.setInteger("code", this.code);
		
		System.out.println(this.code);
		ItemStack stack = new ItemStack(b);
		stack.setTagCompound(nbt);
		return stack;
	}
		
	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {

		ItemStack currentStack = (ItemStack)this.inventory.get(index);
		boolean flag = !stack.isEmpty() && stack.isItemEqual(currentStack) && ItemStack.areItemStackTagsEqual(stack, currentStack);
		this.inventory.set(index, stack);
		
		if (stack.getCount() > this.getInventoryStackLimit()) stack.setCount(this.getInventoryStackLimit());
		this.markDirty();
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
	public int getField(int id) { return 0; }

	@Override
	public void setField(int id, int value) {}

	@Override
	public int getFieldCount() {
		return 0;
	}

	@Override
	public void clear() {
		this.inventory.clear();
		this.markDirty();
	}
}
