package com.lavaingot.minersdream.objects.tileentities;

import java.util.ArrayList;
import java.util.List;

import com.lavaingot.minersdream.Main;
import com.lavaingot.minersdream.init.RecipeInit;
import com.lavaingot.minersdream.objects.blocks.BlockAlloyFurnace;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.oredict.RecipeSorter.Category;

public class TileEntityAlloyFurnace extends TileEntity implements ITickable, ICapabilityProvider{

	private ItemStackHandler inventory;
	public int burn, burnTime;
	public BlockAlloyFurnace daddy;
	
	public boolean burning = false;
	
	public TileEntityAlloyFurnace(BlockAlloyFurnace daddy) {
		this.daddy = daddy;
		this.inventory = new ItemStackHandler(5);
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
		
		if (burn <= 0 && !this.inventory.extractItem(3, 1, true).isEmpty()){
			ItemStack fuel = this.inventory.extractItem(3, 1, false);
			this.burnTime = TileEntityFurnace.getItemBurnTime(fuel);
			this.burn = this.burnTime;
			if (!burning) { burning = true; }
			
		} else if (burn > 0){
			burn--;
			this.world.setBlockState(this.getPos(), this.world.getBlockState(this.getPos()).withProperty(this.daddy.LIT, true));
			
		} else {
			burning = false;
			this.world.setBlockState(this.getPos(), this.world.getBlockState(this.getPos()).withProperty(this.daddy.LIT, false));
		}
		
		if (burning) {
			ItemStack stack0 = this.inventory.getStackInSlot(0);
			ItemStack stack1 = this.inventory.getStackInSlot(1);
			ItemStack stack2 = this.inventory.getStackInSlot(2);
			
			List<ItemStack> stacks = new ArrayList<ItemStack>();
			if (!stack0.equals(ItemStack.EMPTY)) {stacks.add(stack0);}
			if (!stack1.equals(ItemStack.EMPTY)) {stacks.add(stack1);}
			if (!stack2.equals(ItemStack.EMPTY)) {stacks.add(stack2);}
			
			List<ItemStack> currentRecipe = new ArrayList<ItemStack>();
			
			ItemStack output = ItemStack.EMPTY;
			int recipeSize = 0;
			
			for (List<ItemStack> recipe : RecipeInit.RECIPES) {
//				System.out.println(stacks);
//				System.out.println(recipe);
				
				if (recipeSize == 0) { output = recipe.remove(recipe.size()-1);}
				recipeSize = recipe.size();
				
				System.out.println(recipeSize);
				System.out.println(recipe);
				System.out.println(stacks);
				if (recipe.equals(stacks)) {
					currentRecipe = recipe;
					break;
				} else {
					output = ItemStack.EMPTY;
				}
				
			}
			System.out.println(currentRecipe);
			
			for (ItemStack stack : currentRecipe) {
				if (stack.equals(stack0)) {
					this.inventory.extractItem(0, 1, false);
				} else if (stack.equals(stack1)) {
					this.inventory.extractItem(1, 1, false);
				} else if (stack.equals(stack2)) {
					this.inventory.extractItem(2, 1, false);
				}
			}
			
			if (RecipeInit.RECIPES.contains(currentRecipe)) {
				this.inventory.insertItem(4, output, false);
			}
		}
	
	}
	
	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
		
		return (oldState.getBlock() != newState.getBlock());
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
	
	public static boolean isItemFuel(ItemStack stack) {
		
		return stack.getItem().getItemBurnTime(stack) > 0;
	}
	
	public boolean isUsableByPlayer(EntityPlayer playerIn) {

		return this.world.getTileEntity(this.getPos()) == this && playerIn.getDistanceSq(this.pos.add(0.5, 0.5, 0.5)) <= 64;
	}
}
