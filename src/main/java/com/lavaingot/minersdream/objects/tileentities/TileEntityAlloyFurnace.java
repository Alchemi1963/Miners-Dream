package com.lavaingot.minersdream.objects.tileentities;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;

import com.lavaingot.minersdream.Main;
import com.lavaingot.minersdream.init.RecipeInit;
import com.lavaingot.minersdream.objects.blocks.BlockAlloyFurnace;
import com.lavaingot.minersdream.util.Reference;

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
	public int smeltTime, totalSmeltTime;
	public BlockAlloyFurnace daddy;

	private List<ItemStack> currentRecipe = new ArrayList<ItemStack>();
	private boolean[] recipeFit = new boolean[3];
	private ItemStack[] stacks = {ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY};
	
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
	
/*	@Override
	public void update() {
		
		if (totalSmeltTime > 0) {
			
			ItemStack inSlot = this.inventory.getStackInSlot(4).copy();
			
			if (inSlot.isItemEqual(currentRecipe.get(3)) || inSlot.isEmpty()) { 
				
				if (!burning) { burning = true; }
				
				smeltTime ++; 
			}
			
			if (smeltTime == this.totalSmeltTime) {
				
				if ( !inSlot.isEmpty() ) {
					
					inSlot.grow(currentRecipe.get(3).getCount());
				} else {
					
					inSlot = currentRecipe.get(3);
				}
					
				this.inventory.setStackInSlot(4, inSlot);
				
				smeltTime = 0;
				
			} else if (smeltTime == 0) {
				
				int ind = 0;
				for (ItemStack stack : stacks) {
					
					this.inventory.extractItem(ind, stack.getCount(), false);
					ind ++;
				}
			} else {
				
				smeltTime = 0;
				burning = false;
			}
		}
		
		if (totalSmeltTime > 0 && burn <= 0 && !this.inventory.extractItem(3, 1, true).isEmpty()){
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
		
		ItemStack stack0 = this.inventory.getStackInSlot(0).copy();
		ItemStack stack1 = this.inventory.getStackInSlot(1).copy();
		ItemStack stack2 = this.inventory.getStackInSlot(2).copy();
		
		if (!burning) {			
			
			for (List<ItemStack> recipe : RecipeInit.RECIPES) {
				int index = 0;
				
				for (ItemStack stack : recipe) {
					
					if (stack == recipe.get(3)) { continue;	}
					
					if (this.inventory.extractItem(0, stack.getCount(), true).isItemEqual(stack) || 
							this.inventory.extractItem(1, stack.getCount(), true).isItemEqual(stack) || 
							this.inventory.extractItem(2, stack.getCount(), true).isItemEqual(stack)) {
						
						recipeFit[index] = true;
						stacks[index] = stack;
						
					} else if (this.inventory.extractItem(0, stack.getCount(), true).equals(stack) || 
							this.inventory.extractItem(1, stack.getCount(), true).equals(stack) || 
							this.inventory.extractItem(2, stack.getCount(), true).equals(stack)) {
						
						recipeFit[index] = true;
						stacks[index] = stack;
					}
					else {
						recipeFit[index] = false;
					}
					
					index++;
					
				}

				if (Reference.areAllTrue(recipeFit)) {
					currentRecipe = recipe;
					
					this.totalSmeltTime = Main.cfgInstance.smeltTimes.get(recipe.get(3).getUnlocalizedName());
					this.smeltTime = 0;
					
					break;
				}
			}
		}
	}*/
	
	@Override
	public void update() {
		
		ItemStack stack0 = inventory.getStackInSlot(0).copy();
		ItemStack stack1 = inventory.getStackInSlot(1).copy();
		ItemStack stack2 = inventory.getStackInSlot(2).copy();
		ItemStack fuelStack = inventory.getStackInSlot(3).copy();
		ItemStack output = inventory.getStackInSlot(4).copy();
		
		List<ItemStack> items = new ArrayList<ItemStack>();
		
		items.add(stack0);
		items.add(stack1);
		items.add(stack2);
		
		Main.logger.info(String.valueOf(totalSmeltTime));
		
		if (totalSmeltTime == 0) {
			
			for (List<ItemStack> recipe : RecipeInit.RECIPES) {
				if (Reference.areAllItemsSame(items, recipe)) {
					currentRecipe = recipe;
					
					totalSmeltTime = Main.cfgInstance.smeltTimes.get(recipe.get(3).getUnlocalizedName());
				}
			}
			
		} else if ( totalSmeltTime > 0 ) {
			
			if (burn <= 0 && !fuelStack.isEmpty()) {
				
				ItemStack fuel = inventory.extractItem(3, 1, false);
				burnTime = TileEntityFurnace.getItemBurnTime(fuel);
				burn = burnTime;
				burning = true;
				
			} else if ( burn > 0 ){
				
				burn--;
				if (!burning) { burning = true; }				
				this.world.setBlockState(this.getPos(), this.world.getBlockState(this.getPos()).withProperty(this.daddy.LIT, true));
				
			} else {
				
				burning = false;
				this.world.setBlockState(this.getPos(), this.world.getBlockState(this.getPos()).withProperty(this.daddy.LIT, false));
			}
			
			
			if ( burning && smeltTime == 0) {
				
				for (ItemStack item : currentRecipe) {
					
					
					if (inventory.extractItem(0, item.getCount(), true).isItemEqual(item) && item.getCount() <= stack0.getCount()) {
						inventory.extractItem(0, item.getCount(), false);

					} else if (inventory.extractItem(1, item.getCount(), true).isItemEqual(item) && item.getCount() <= stack0.getCount()) {
						inventory.extractItem(1, item.getCount(), false);
					
					} else if (inventory.extractItem(2, item.getCount(), true).isItemEqual(item) && item.getCount() <= stack0.getCount()) {
						inventory.extractItem(2, item.getCount(), false);
					
					} else {
						
						burning = false;
						totalSmeltTime = 0;
						currentRecipe.removeAll(currentRecipe);
						break;
					}
				}
				
			} else if (burning && smeltTime == totalSmeltTime) {
				
				if (output.isEmpty()) {
					
					inventory.setStackInSlot(4, currentRecipe.get(3));
				} else if (output.isItemEqual(currentRecipe.get(3))) {
					
					output.grow(currentRecipe.get(3).getCount());
					inventory.setStackInSlot(4, output);
				}
				
				smeltTime = 0;
				
			} else if (burning) {
				
				smeltTime ++;
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
