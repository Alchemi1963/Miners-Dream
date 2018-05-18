package com.lavaingot.minersdream.objects.tileentities;

import com.lavaingot.minersdream.Main;
import com.lavaingot.minersdream.objects.blocks.machines.alloyer.BlockAlloyingFurnace;
import com.lavaingot.minersdream.objects.recipes.AlloyingFurnaceRecipes;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBoat;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileAlloyingFurnace extends TileEntity implements IInventory, ITickable{

	private NonNullList<ItemStack> inventory = NonNullList.<ItemStack>withSize(5, ItemStack.EMPTY);
	private String customName;
	
	private int burnTime;
	private int burn;
	private int smeltTime;
	private int smelt;	
	
	public TileAlloyingFurnace() {
		super();
	}
	
	@Override
	public String getName() {
		
		return this.hasCustomName() ? this.customName : "container.alloying_furnace";
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
		
		return (ItemStack)this.inventory.get(index);
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
		
		if (index == 0 && index + 1 == 1 && index + 2 == 2 && !flag) {
			ItemStack stack1 = (ItemStack)this.inventory.get(index + 1);
			ItemStack stack2 = (ItemStack)this.inventory.get(index + 2);
			this.smeltTime = this.getSmeltTime(stack, stack1, stack2);
			this.smelt = 0;
			this.markDirty();
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.inventory = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(compound, this.inventory);
		this.burn = compound.getInteger("burn");
		this.burnTime = getItemBurnTime((ItemStack)this.inventory.get(3));
		this.smeltTime = compound.getInteger("smelt_time");
		this.smelt = compound.getInteger("smelt");
		
		if (compound.hasKey("CustomName", 8)) this.setCustomName(compound.getString("CustomName"));
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		
		compound.setInteger("burn", (short)this.burn);
		compound.setInteger("smelt_time", (short)this.smeltTime);
		compound.setInteger("smelt", (short)this.smelt);
		ItemStackHelper.saveAllItems(compound, this.inventory);
		
		if (this.hasCustomName()) compound.setString("CustomName", this.customName);
		
		return compound;
	}
	
	@Override
	public int getInventoryStackLimit() {
		
		return 64;
	}
	
	public boolean isBurning() {
		
		return this.burn > 0;
	}
	
	@SideOnly(Side.CLIENT)
	public static boolean isBurning(IInventory inventory) {
		
		return inventory.getField(0) > 0;
	}
	
	
	@Override
	public void update() {
		boolean flag = this.isBurning();
		boolean flag1 = false;
		
		if (flag) --this.burn;

		if (!this.world.isRemote) {
			
			ItemStack fuel = (ItemStack)this.inventory.get(3);
			ItemStack slot0 = (ItemStack)this.inventory.get(0);
			ItemStack slot1 = (ItemStack)this.inventory.get(1);
			ItemStack slot2 = (ItemStack)this.inventory.get(2);
			
			
			
			if (flag || !fuel.isEmpty() && !(slot0.isEmpty() && slot1.isEmpty() && slot2.isEmpty())) {
						
				if (!flag && this.canSmelt()) {
					this.burnTime = getItemBurnTime(fuel);
					this.burn = this.burnTime;
					
					flag = this.isBurning();
					if (flag) {
						flag1 = true;
						
						if(!fuel.isEmpty()) {
							Item item = fuel.getItem();
							fuel.shrink(1);
							
							if(fuel.isEmpty()) {
								ItemStack item1 = item.getContainerItem(fuel);
								this.inventory.set(3, item1);
							}
						}
					}
				}
				
				if(flag && this.canSmelt()) {
					
					++this.smelt;
					
					if(this.smelt >= this.smeltTime) {
						
						this.proces();
						slot0 = (ItemStack)this.inventory.get(0);
						slot1 = (ItemStack)this.inventory.get(1);
						slot2 = (ItemStack)this.inventory.get(2);
						
						this.smelt = 0;
						this.smeltTime = this.getSmeltTime(slot0, slot1, slot2);
						flag1 = true;
					}
				}
				
				else this.smelt = 0;
			}
			
			else if (!flag && this.smelt > 0) this.smelt = MathHelper.clamp(this.smelt - 2, 0, this.smeltTime);
			
			if (flag != this.isBurning()) {
				flag1 = true;
				BlockAlloyingFurnace.setState(this.isBurning(), this.world, this.pos);
			}
		}
		
		if (flag1) this.markDirty();
	}
	

	public int getSmeltTime(ItemStack input0, ItemStack input1, ItemStack input2) {
		
		return 200;
		
	}
	
	private boolean canSmelt() {
		
		ItemStack result = AlloyingFurnaceRecipes.getInstance().getAlloyResult((ItemStack)this.inventory.get(0), (ItemStack)this.inventory.get(1), (ItemStack)this.inventory.get(2));
		
		if (result.isEmpty()) return false;
		else {
			ItemStack output = (ItemStack)this.inventory.get(4);
			if (output.isEmpty()) return true;
			else if (!output.isItemEqual(result) || !ItemStack.areItemStackTagsEqual(output, result)) return false;
			
			int res = output.getCount() + result.getCount();
			return res <= getInventoryStackLimit() && res <= output.getMaxStackSize() && ((ItemStack)this.inventory.get(0)).getCount() >= AlloyingFurnaceRecipes.getInstance().getItemQuantity(result, (ItemStack)this.inventory.get(0)) && ((ItemStack)this.inventory.get(1)).getCount() >= AlloyingFurnaceRecipes.getInstance().getItemQuantity(result, (ItemStack)this.inventory.get(1)) && ((ItemStack)this.inventory.get(2)).getCount() >= AlloyingFurnaceRecipes.getInstance().getItemQuantity(result, (ItemStack)this.inventory.get(2));
		}
	}
	
	public void proces() {
		if (this.canSmelt()) {
			ItemStack input0 = (ItemStack)this.inventory.get(0);
			ItemStack input1 = (ItemStack)this.inventory.get(1);
			ItemStack input2 = (ItemStack)this.inventory.get(2);
			
			ItemStack result = AlloyingFurnaceRecipes.getInstance().getAlloyResult(input0, input1, input2);
			ItemStack output = (ItemStack)this.inventory.get(4);
			
			if (output.isEmpty()) this.inventory.set(4, result.copy());
			else if (output.isItemEqual(result) && ItemStack.areItemStackTagsEqual(output, result)) output.grow(result.getCount());
			
			input0.shrink(AlloyingFurnaceRecipes.getInstance().getItemQuantity(result, input0));
			input1.shrink(AlloyingFurnaceRecipes.getInstance().getItemQuantity(result, input1));
			input2.shrink(AlloyingFurnaceRecipes.getInstance().getItemQuantity(result, input2));
		}
	}
	
	public static int getItemBurnTime(ItemStack stack)
    {
        if (stack.isEmpty())
        {
            return 0;
        }
        else
        {
            int burnTime = net.minecraftforge.event.ForgeEventFactory.getItemBurnTime(stack);
            if (burnTime >= 0) return burnTime;
            Item item = stack.getItem();

            if (item == Item.getItemFromBlock(Blocks.WOODEN_SLAB))
            {
                return 150;
            }
            else if (item == Item.getItemFromBlock(Blocks.WOOL))
            {
                return 100;
            }
            else if (item == Item.getItemFromBlock(Blocks.CARPET))
            {
                return 67;
            }
            else if (item == Item.getItemFromBlock(Blocks.LADDER))
            {
                return 300;
            }
            else if (item == Item.getItemFromBlock(Blocks.WOODEN_BUTTON))
            {
                return 100;
            }
            else if (Block.getBlockFromItem(item).getDefaultState().getMaterial() == Material.WOOD)
            {
                return 300;
            }
            else if (item == Item.getItemFromBlock(Blocks.COAL_BLOCK))
            {
                return 16000;
            }
            else if (item instanceof ItemTool && "WOOD".equals(((ItemTool)item).getToolMaterialName()))
            {
                return 200;
            }
            else if (item instanceof ItemSword && "WOOD".equals(((ItemSword)item).getToolMaterialName()))
            {
                return 200;
            }
            else if (item instanceof ItemHoe && "WOOD".equals(((ItemHoe)item).getMaterialName()))
            {
                return 200;
            }
            else if (item == Items.STICK)
            {
                return 100;
            }
            else if (item != Items.BOW && item != Items.FISHING_ROD)
            {
                if (item == Items.SIGN)
                {
                    return 200;
                }
                else if (item == Items.COAL)
                {
                    return 1600;
                }
                else if (item == Items.LAVA_BUCKET)
                {
                    return 20000;
                }
                else if (item != Item.getItemFromBlock(Blocks.SAPLING) && item != Items.BOWL)
                {
                    if (item == Items.BLAZE_ROD)
                    {
                        return 2400;
                    }
                    else if (item instanceof ItemDoor && item != Items.IRON_DOOR)
                    {
                        return 200;
                    }
                    else
                    {
                        return item instanceof ItemBoat ? 400 : 0;
                    }
                }
                else
                {
                    return 100;
                }
            }
            else
            {
                return 300;
            }
        }
    }

	public static boolean isItemFuel(ItemStack fuel) {
		
		return getItemBurnTime(fuel) > 0;
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
		
		if (index == 4) return false;
		else if (index != 3) return true;
		else return isItemFuel(stack);
	}
	
	public String getGUIID() {
		
		return "minersdream:alloying_furnace";
	}
	
	@Override
	public int getField(int id) {
		
		switch(id) {
		
		case 0:
			return this.burn;
		
		case 1:
			return this.burnTime;
			
		case 2:
			return this.smelt;
			
		case 3:
			return this.smeltTime;
		
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
			this.smelt = value;
			break;
			
		case 3:
			this.smeltTime = value;
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
}

