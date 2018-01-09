package com.lavaingot.minersdream.objects.tileentities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityContainer extends TileEntity implements ITickable, ICapabilityProvider{

	private ItemStackHandler inventory;
	
	
	public TileEntityContainer() {
		
		this.inventory = new ItemStackHandler(18);
		
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		this.inventory.deserializeNBT(compound.getCompoundTag("ItemStackHandler"));
		super.readFromNBT(compound);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		
		compound.setTag("ItemStackHandler", this.inventory.serializeNBT());
		return super.writeToNBT(compound);
	}
	
	@Override
	public void update() {
	}
	
	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		
		NBTTagCompound compound = new NBTTagCompound();
		this.writeToNBT(compound);
		int meta = getBlockMetadata();
		return new SPacketUpdateTileEntity(this.pos, meta, compound);
	}
	
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		
		this.readFromNBT(pkt.getNbtCompound());
	}
	
	@Override
	public NBTTagCompound getUpdateTag() {
		
		NBTTagCompound compound = new NBTTagCompound();
		this.writeToNBT(compound);
		return compound;
	}
	
	@Override
	public void handleUpdateTag(NBTTagCompound tag) {
		
		this.readFromNBT(tag);
	}
	
	@Override
	public NBTTagCompound getTileData() {
		
		NBTTagCompound compound = new NBTTagCompound();
		this.writeToNBT(compound);
		return compound;
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		
		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) { return (T) this.inventory; }
		return super.getCapability(capability, facing);
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		
		if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) { return true; }
		return super.hasCapability(capability, facing);
	}
	
	public boolean isUsableByPlayer(EntityPlayer playerIn) {

		return this.world.getTileEntity(this.getPos()) == this && playerIn.getDistanceSq(this.pos.add(0.5, 0.5, 0.5)) <= 64;
	}
}
