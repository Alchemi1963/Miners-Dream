package com.lavaingot.minersdream.objects.blocks.machines.mechanical_combiner;

import java.awt.Color;
import java.util.Random;

import com.lavaingot.minersdream.Main;
import com.lavaingot.minersdream.init.ItemInit;
import com.lavaingot.minersdream.objects.tools.ToolAxe;
import com.lavaingot.minersdream.objects.tools.ToolMulti;
import com.lavaingot.minersdream.util.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileMechanicalCombiner extends TileEntity implements ITickable, IInventory{

	private NonNullList<ItemStack> inventory = NonNullList.<ItemStack>withSize(7, ItemStack.EMPTY);
	private String customName;
	
	private int burnTime, burn, procesTimeTotal, procesTime;

	@Override
	public String getName() {

		return this.hasCustomName() ? this.customName : Reference.MECHANICAL_COMBINER_CONTAINER;
	}

	@Override
	public boolean hasCustomName() {

		return this.customName != null && !this.customName.isEmpty();
	}
	
	public void setCustomName(String customName) {
		this.customName = customName;
	}

	@Override
	public ITextComponent getDisplayName() {
		
		return this.hasCustomName() ? new TextComponentString(this.getName()) : new TextComponentTranslation(this.getName());
	}
	
	@Override
	public int getSizeInventory() {
		
		return this.inventory.size();
	}

	@Override
	public boolean isEmpty() {

		for (ItemStack stack : this.inventory) {
			if (!stack.isEmpty()) return false;
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

		ItemStack currentStack = this.inventory.get(index);
		boolean flag = !stack.isEmpty() && stack.isItemEqual(currentStack) && ItemStack.areItemStackTagsEqual(currentStack, stack);
		this.inventory.set(index, stack);
		
		if (stack.getCount() > this.getInventoryStackLimit()) stack.setCount(this.getInventoryStackLimit());
		
		if (index == 0 && index + 1 == 1 && index + 2 == 2 && !flag) {
			ItemStack stack1 = this.inventory.get(index + 1);
			ItemStack stack2 = this.inventory.get(index + 2);
			ItemStack stack3 = this.inventory.get(index + 3);
			ItemStack stack4 = this.inventory.get(index + 4);
			
			this.procesTimeTotal = this.getProcesTime(stack1, stack2, stack3, stack4);
			this.procesTime = 0;
			this.markDirty();
		}
	}

	private int getProcesTime(ItemStack stack1, ItemStack stack2, ItemStack stack3, ItemStack stack4) {

		return 100;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		
		this.inventory = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(compound, this.inventory);
		
		this.burn = compound.getInteger("burn");
		this.burnTime = 100;
		this.procesTime = compound.getInteger("proces_time");
		this.procesTimeTotal = compound.getInteger("proces_time_total");
		
		if (compound.hasKey("CustomName", 8)) this.setCustomName(compound.getString("CustomName"));
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		
		compound.setInteger("burn", this.burn);
		compound.setInteger("proces_time", this.procesTime);
		compound.setInteger("proces_time_total", this.procesTimeTotal);
		ItemStackHelper.saveAllItems(compound, this.inventory);
		
		if (this.hasCustomName()) compound.setString("CustomName", this.customName);
		
		return compound;
	}

	@Override
	public int getInventoryStackLimit() {

		return 1;
	}
	
	public boolean isBurning() {
		
		return this.burn > 0;
	}
	
	@SideOnly(Side.CLIENT)
	public boolean isBurning(IInventory inventory) {
		
		return inventory.getField(0) > 0;
	}

	@Override
	public boolean isUsableByPlayer(EntityPlayer player) {
		
		return this.world.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64;
	}

	@Override
	public void openInventory(EntityPlayer player) {}

	@Override
	public void closeInventory(EntityPlayer player) {}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {

		if (index == 0) return stack.isItemEqual(new ItemStack(Item.getByNameOrId("minecraft:redstone_block")));
		else if (index > 0 && index < 5) return isItemTool(stack);
		else return false;
		
	}
	
	public static boolean isItemTool(ItemStack stack) {

		if (stack.getItem() instanceof ItemTool) return true;
		
		return stack.getItem() instanceof ItemPickaxe || stack.getItem() instanceof ItemAxe || stack.getItem() instanceof ToolAxe || stack.getItem() instanceof ItemSpade || stack.getItem() instanceof ItemShears;
	}

	public String getGUIID() {
		
		return "minersdream:mechanical_combiner";
	}

	@Override
	public int getField(int id) {

		switch(id) {
		
		case 0:
			return this.burn;
		
		case 1:
			return this.burnTime;
			
		case 2:
			return this.procesTime;
		
		case 3:
			return this.procesTimeTotal;
		
		default:
			return 0;
		}
		
	}

	@Override
	public void setField(int id, int value) {
		
		switch(id) {
		
		case 0:
			this.burn = value;
			break;
			
		case 1:
			this.burnTime = value;
			break;
			
		case 2:
			this.procesTime = value;
			break;
		
		case 3:
			this.procesTimeTotal = value;
			break;
		}
		
	}

	@Override
	public int getFieldCount() {

		return 4;
	}

	@Override
	public void clear() {

		this.inventory.clear();
	}
	
	public void proces() {
		if (this.canProces()) {
			ItemStack input0 = this.inventory.get(1);
			ItemStack input1 = this.inventory.get(2);
			ItemStack input2 = this.inventory.get(3);
			ItemStack input3 = this.inventory.get(4);
			
			ItemStack result = new ItemStack(ItemInit.MULTI_TOOL);
			result = ToolMulti.setItems(result, input0, input1, input2, input3);
			
			ItemStack output = this.inventory.get(5);
			
			if (output.isEmpty()) this.inventory.set(5, result.copy());
			result.getItem().onCreated(result, this.world, null);
			
			input0.shrink(1);
			input1.shrink(1);
			input2.shrink(1);
			input3.shrink(1);
			
			
		}
	}

	private boolean canProces() {
		
		Item input0 = this.inventory.get(1).getItem();
		Item input1 = this.inventory.get(2).getItem();
		Item input2 = this.inventory.get(3).getItem();
		Item input3 = this.inventory.get(4).getItem();
		
									   //pickaxe, axe, shovel, shears
		boolean[] inputs = new boolean[] {false, false, false, false};
		
		if (input0 instanceof ItemPickaxe || input1 instanceof ItemPickaxe || input2 instanceof ItemPickaxe || input3 instanceof ItemPickaxe) inputs[0] = true;
		if (input0 instanceof ItemAxe || input0 instanceof ToolAxe || (input0.getUnlocalizedName().contains("axe") && !(input0 instanceof ItemPickaxe)) ||
input1 instanceof ItemAxe || input1 instanceof ToolAxe || (input1.getUnlocalizedName().contains("axe") && !(input1 instanceof ItemPickaxe)) ||
input2 instanceof ItemAxe || input2 instanceof ToolAxe || (input2.getUnlocalizedName().contains("axe") && !(input2 instanceof ItemPickaxe)) ||
input3 instanceof ItemAxe || input3 instanceof ToolAxe || (input3.getUnlocalizedName().contains("axe") && !(input3 instanceof ItemPickaxe))) inputs[1] = true;
		if (input0 instanceof ItemSpade || input1 instanceof ItemSpade || input2 instanceof ItemSpade || input3 instanceof ItemSpade) inputs[2] = true;
		if (input0 instanceof ItemShears || input1 instanceof ItemShears || input2 instanceof ItemShears || input3 instanceof ItemShears) inputs[3] = true;
		
		for (boolean flag : inputs) {
			if (!flag) return false;
		}
		return true;
	}

	@Override
	public void update() {
		
		boolean flag = this.isBurning();
		boolean flag1 = false;
		
		Random rand = this.world.rand;
		
		BlockMechanicalCombiner.setState(this.isBurning(), this.world, this.pos);
	
		if (flag) {
			this.burn--;
			
			double d0 = 0.0625D;

	        for (int i = 0; i < 6; ++i)
	        {
	            double d1 = (double)((float)pos.getX() + rand.nextFloat());
	            double d2 = (double)((float)pos.getY() + rand.nextFloat());
	            double d3 = (double)((float)pos.getZ() + rand.nextFloat());

	            if (i == 0 && !this.world.getBlockState(pos.up()).isOpaqueCube())
	            {
	                d2 = (double)pos.getY() + 0.0625D + 1.0D;
	            }

	            if (i == 1 && !this.world.getBlockState(pos.down()).isOpaqueCube())
	            {
	                d2 = (double)pos.getY() - 0.0625D;
	            }

	            if (i == 2 && !this.world.getBlockState(pos.south()).isOpaqueCube())
	            {
	                d3 = (double)pos.getZ() + 0.0625D + 1.0D;
	            }

	            if (i == 3 && !this.world.getBlockState(pos.north()).isOpaqueCube())
	            {
	                d3 = (double)pos.getZ() - 0.0625D;
	            }

	            if (i == 4 && !this.world.getBlockState(pos.east()).isOpaqueCube())
	            {
	                d1 = (double)pos.getX() + 0.0625D + 1.0D;
	            }

	            if (i == 5 && !this.world.getBlockState(pos.west()).isOpaqueCube())
	            {
	                d1 = (double)pos.getX() - 0.0625D;
	            }

	            if (d1 < (double)pos.getX() || d1 > (double)(pos.getX() + 1) || d2 < 0.0D || d2 > (double)(pos.getY() + 1) || d3 < (double)pos.getZ() || d3 > (double)(pos.getZ() + 1))
	            {
	            	this.world.spawnParticle(EnumParticleTypes.REDSTONE, d1, d2, d3, 0.0D, 0.0D, 0.0D);
	            }
	        }
		}
		
		if (!this.world.isRemote) {
			ItemStack fuel = this.inventory.get(0);
			ItemStack slot1 = this.inventory.get(1);
			ItemStack slot2 = this.inventory.get(2);
			ItemStack slot3 = this.inventory.get(3);
			ItemStack slot4 = this.inventory.get(4);
			
//			Main.print(this.canProces());
			
			if (flag || !fuel.isEmpty() && !(slot1.isEmpty() && slot2.isEmpty() && slot3.isEmpty() && slot4.isEmpty())) {
				
				if (!flag && this.canProces()) {
					this.burnTime = 1000;
					this.burn = this.burnTime;
					
					flag = this.isBurning();
					
					if (flag) {
						flag1 = true;
						
						if (!fuel.isEmpty()) {
							Item item = fuel.getItem();
							fuel.shrink(1);
							
							if (fuel.isEmpty()) {
								ItemStack item1 = item.getContainerItem(fuel);
								this.inventory.set(0, item1);
							}
						}
					}					
				}
				
				if (flag && this.canProces()) {
					++this.procesTime;
					
					if (this.procesTime >= this.procesTimeTotal) {
						
						this.proces();
						slot1 = this.inventory.get(1);
						slot2 = this.inventory.get(2);
						slot3 = this.inventory.get(3);
						slot4 = this.inventory.get(4);
						
						this.procesTime = 0;
						this.procesTimeTotal = this.getProcesTime(slot1, slot2, slot3, slot4);
						flag1 = true;
					}
				}
				
				else this.procesTime = 0;
			}
			
			else if (!flag && this.procesTime >0) this.procesTime = MathHelper.clamp(this.procesTime - 2, 0, this.procesTimeTotal);
			
			if (flag != this.isBurning()) {
				flag1 = true;
				BlockMechanicalCombiner.setState(this.isBurning(), this.world, this.pos);
			}
		}
		
		if (flag1) this.markDirty();
	}
	
	
}
