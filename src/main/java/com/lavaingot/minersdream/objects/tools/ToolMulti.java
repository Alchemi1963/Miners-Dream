package com.lavaingot.minersdream.objects.tools;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.lwjgl.input.Keyboard;

import com.google.common.collect.Sets;
import com.lavaingot.minersdream.Config;
import com.lavaingot.minersdream.Main;
import com.lavaingot.minersdream.client.key.KeyBindings;
import com.lavaingot.minersdream.init.ItemInit;
import com.lavaingot.minersdream.util.IHasModel;
import com.lavaingot.minersdream.util.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ToolMulti extends ItemTool implements IHasModel{

	private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(Blocks.ACTIVATOR_RAIL, Blocks.COAL_ORE, Blocks.COBBLESTONE, Blocks.DETECTOR_RAIL, Blocks.DIAMOND_BLOCK, Blocks.DIAMOND_ORE, Blocks.DOUBLE_STONE_SLAB, Blocks.GOLDEN_RAIL, Blocks.GOLD_BLOCK, Blocks.GOLD_ORE, Blocks.ICE, Blocks.IRON_BLOCK, Blocks.IRON_ORE, Blocks.LAPIS_BLOCK, Blocks.LAPIS_ORE, Blocks.LIT_REDSTONE_ORE, Blocks.MOSSY_COBBLESTONE, Blocks.NETHERRACK, Blocks.PACKED_ICE, Blocks.RAIL, Blocks.REDSTONE_ORE, Blocks.SANDSTONE, Blocks.RED_SANDSTONE, Blocks.STONE, Blocks.STONE_SLAB, Blocks.STONE_BUTTON, Blocks.STONE_PRESSURE_PLATE, Blocks.PLANKS, Blocks.ACACIA_DOOR, Blocks.ACACIA_FENCE, Blocks.ACACIA_FENCE_GATE, Blocks.ACACIA_STAIRS, Blocks.BIRCH_DOOR, Blocks.BIRCH_FENCE, Blocks.BIRCH_FENCE_GATE, Blocks.BIRCH_STAIRS, Blocks.BOOKSHELF, Blocks.CHEST, Blocks.CHORUS_PLANT, Blocks.COCOA, Blocks.DARK_OAK_DOOR, Blocks.DARK_OAK_FENCE, Blocks.DARK_OAK_FENCE_GATE, Blocks.DARK_OAK_STAIRS, Blocks.DAYLIGHT_DETECTOR, Blocks.DAYLIGHT_DETECTOR_INVERTED, Blocks.JUNGLE_DOOR, Blocks.JUNGLE_FENCE, Blocks.JUNGLE_FENCE_GATE, Blocks.JUNGLE_STAIRS, Blocks.LADDER, Blocks.LIT_PUMPKIN, Blocks.LOG, Blocks.LOG2, Blocks.MELON_BLOCK, Blocks.NOTEBLOCK, Blocks.OAK_DOOR, Blocks.OAK_FENCE, Blocks.OAK_FENCE_GATE, Blocks.OAK_STAIRS, Blocks.PISTON, Blocks.PISTON_EXTENSION, Blocks.PISTON_HEAD, Blocks.STICKY_PISTON, Blocks.PLANKS, Blocks.PUMPKIN, Blocks.SPRUCE_DOOR, Blocks.SPRUCE_FENCE, Blocks.SPRUCE_FENCE_GATE, Blocks.SPRUCE_STAIRS, Blocks.TRAPDOOR, Blocks.TRAPPED_CHEST, Blocks.WALL_BANNER, Blocks.STANDING_SIGN, Blocks.WALL_SIGN, Blocks.WOODEN_BUTTON, Blocks.WOODEN_PRESSURE_PLATE, Blocks.WOODEN_SLAB, Blocks.DOUBLE_WOODEN_SLAB, Blocks.CLAY, Blocks.DIRT, Blocks.FARMLAND, Blocks.GRASS, Blocks.GRAVEL, Blocks.MYCELIUM, Blocks.SAND, Blocks.SNOW, Blocks.SNOW_LAYER, Blocks.SOUL_SAND, Blocks.GRASS_PATH, Blocks.CONCRETE_POWDER);
	private static final Set<Material> EFFECTIVE_MATERIALS = Sets.newHashSet(Material.ANVIL, Material.CIRCUITS, Material.CLAY, Material.CORAL, Material.CRAFTED_SNOW, Material.GLASS, Material.GOURD, Material.GRASS, Material.GROUND, Material.IRON, Material.LEAVES, Material.PACKED_ICE, Material.PLANTS, Material.REDSTONE_LIGHT, Material.ROCK, Material.SAND, Material.SNOW, Material.TNT, Material.WOOD);
	private static final Set<Material> PICK_MATERIALS = Sets.newHashSet(Material.ANVIL, Material.IRON, Material.PACKED_ICE, Material.ROCK);
	
	private static Map<String, Integer> HARVEST_LEVELS = new HashMap<String, Integer>();
	private static Map<String, Integer> ITEM_MAPPING = new HashMap<String, Integer>();
	
	private static NonNullList<ItemStack> ITEMS = NonNullList.<ItemStack>withSize(4, ItemStack.EMPTY);
	
	public ToolMulti(String name, ToolMaterial material, CreativeTabs tab) {
		
		super(10.0F, 2.5F, material.setRepairItem(new ItemStack(ItemInit.INGOT, 1, 6)), EFFECTIVE_ON);
		
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(tab);
		addPropertyOverride(new ResourceLocation("mining"), new IItemPropertyGetter() {
			
			@SideOnly(Side.CLIENT)
			@Override
			public float apply(ItemStack stack, World worldIn, EntityLivingBase entityIn) {

				return ToolMulti.isMining(stack) ? 1.0F : 0.0F;
			}
		});
		
		ItemInit.ITEMS.add(this);
	}
	
	public static boolean isMining(ItemStack stack) {
		return (boolean) readFromNBT(stack, "mining", false);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {

		super.addInformation(stack, worldIn, tooltip, flagIn);
		
		tooltip.add(TextFormatting.GOLD + "Uses: " + (stack.getMaxDamage()*0.10 > stack.getMaxDamage() - stack.getItemDamage() ? TextFormatting.DARK_RED : TextFormatting.BLUE) + (stack.getMaxDamage() - stack.getItemDamage()));
		
		if (Keyboard.isKeyDown(KeyBindings.showTooltip.getKeyCode())) {
			
			tooltip.addAll((List<String>) readFromNBT(stack, "tooltip", new ArrayList<String>()));
		}
		
	}
	
	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
			
		if (HARVEST_LEVELS.isEmpty() || ITEM_MAPPING.isEmpty() || !stack.hasTagCompound() || !stack.getTagCompound().hasKey("tooltip")) {
			NonNullList<ItemStack> items = NonNullList.<ItemStack>withSize(4, ItemStack.EMPTY);
			ItemStackHelper.loadAllItems(stack.getTagCompound(), items);
			
			setItems(stack, items.get(0), items.get(1), items.get(2), items.get(3));
		}
		
		EntityPlayer p = ((EntityPlayer)entityIn);
		Vec3d vector = p.getLookVec();
		BlockPos pos = p.getPosition().add(0, p.getEyeHeight(), 0);
		
		IBlockState block = Blocks.AIR.getDefaultState();
		if (Minecraft.getMinecraft().objectMouseOver != null && Minecraft.getMinecraft().objectMouseOver.typeOfHit == RayTraceResult.Type.BLOCK && Minecraft.getMinecraft().objectMouseOver.getBlockPos() != null) block = worldIn.getBlockState(Minecraft.getMinecraft().objectMouseOver.getBlockPos());
		
		
		if (getBlockHarvestType(block) == "PICKAXE" && p.isSwingInProgress || block.getBlockHardness(p.getEntityWorld(), pos) == -1.0F && p.isSwingInProgress) {
			
			stack = writeToNBT(stack, "mining", true);
			
		} else if (stack.hasTagCompound() ? stack.getTagCompound().hasKey("mining") ? stack.getTagCompound().getBoolean("mining") : false : false) {
			
			stack = writeToNBT(stack, "mining", false);
			
		}
		
		
	}
	
	@SideOnly(Side.CLIENT)
	public static class ColorHandler implements IItemColor{
				
		@Override
		public int colorMultiplier(ItemStack stack, int tintIndex) {
			
			int col1 = 0;
			int col2 = 0;
			int col3 = 0;
						
			for (ItemStack item : ToolMulti.getItemsFromStack(stack)) {
					
				if (item.getItem() instanceof ItemPickaxe) {
					
					col3 = Config.MATERIALCOLOURS.get(((ItemTool) item.getItem()).getToolMaterialName());
					
				} else if (item.getItem() instanceof ItemAxe || item.getItem() instanceof ToolAxe || (item.getItem().getUnlocalizedName().contains("axe") && !(item.getItem() instanceof ItemPickaxe))) {
					
					col2 = Config.MATERIALCOLOURS.get(((ItemTool) item.getItem()).getToolMaterialName());
					
				} else if (item.getItem() instanceof ItemSpade) {
					
					col1 = Config.MATERIALCOLOURS.get(((ItemTool) item.getItem()).getToolMaterialName());
					
				}
			}
			
			return tintIndex == 1 ? col1 : tintIndex == 2 ? col2 : tintIndex == 3 ? col3 : -1;
			
		}
	}
	
	public static Object readFromNBT(ItemStack stack, String key, Object type) {
		
		if (!stack.hasTagCompound() || !stack.getTagCompound().hasKey(key)) return type;
		
		/*boolean
		byte
		byte[]
		double
		float
		int[]
		int
		long
		short
		string
		tag*/
		
		NBTTagCompound nbt = stack.getTagCompound();
		
		if (type instanceof Boolean) return nbt.getBoolean(key);
		else if (type instanceof Byte) return nbt.getByte(key);
		else if (type instanceof Byte[]) return nbt.getByteArray(key);
		else if (type instanceof Double) return nbt.getDouble(key);
		else if (type instanceof Float) return nbt.getFloat(key);
		else if (type instanceof Integer[]) return nbt.getIntArray(key);
		else if (type instanceof Integer) return nbt.getInteger(key);
		else if (type instanceof Long) return nbt.getLong(key);
		else if (type instanceof Short) return nbt.getShort(key);
		else if (type instanceof String) return nbt.getString(key);
		else if (type instanceof NBTBase) return nbt.getTag(key);
		
		else if (type instanceof List) {
			NBTTagList nbt2 = nbt.getTagList(key, Constants.NBT.TAG_COMPOUND);
			List<String> value = new ArrayList<String>();
			
			for (int i = 0; i < nbt2.tagCount(); i++) {
				value.add(nbt2.getCompoundTagAt(i).getString(String.valueOf(i)));
			}
			
			return value;
		}
		
		else return null;
		
	}
	
	public static ItemStack writeToNBT(ItemStack stack, String key, Object value) {
		
		NBTTagCompound nbt = new NBTTagCompound();
		if (stack.hasTagCompound()) nbt = stack.getTagCompound();
		
		/*boolean
		byte
		byte[]
		double
		float
		int[]
		int
		long
		short
		string
		tag*/
				
		if (value instanceof Boolean) nbt.setBoolean(key, (boolean) value);
		else if (value instanceof Byte) nbt.setByte(key, (byte) value);
		else if (value instanceof Byte[]) nbt.setByteArray(key, (byte[]) value);
		else if (value instanceof Double) nbt.setDouble(key, (double) value);
		else if (value instanceof Float) nbt.setFloat(key, (float) value);
		else if (value instanceof Integer[]) nbt.setIntArray(key, (int[]) value);
		else if (value instanceof Integer) nbt.setInteger(key, (int) value);
		else if (value instanceof Long) nbt.setLong(key, (long) value);
		else if (value instanceof Short) nbt.setShort(key, (short) value);
		else if (value instanceof String) nbt.setString(key, (String) value);
		else if (value instanceof NBTBase) nbt.setTag(key, (NBTBase) value);
		
		else if (value instanceof List && ((List) value).get(0) instanceof String) {
			NBTTagList nbt2 = new NBTTagList();
			
			for (int i = 0; i < ((List<String>) value).size(); i++) {
				NBTTagCompound tag = new NBTTagCompound();
				
				tag.setString(String.valueOf(i), ((List<String>) value).get(i));
				nbt2.appendTag(tag);
			}
			
			nbt.setTag(key, nbt2);			
			
		}
		
		else return stack;
		
		stack.setTagCompound(nbt);
		
		return stack;
	}
	
	public static ItemStack setItems(ItemStack input, ItemStack stack1, ItemStack stack2, ItemStack stack3, ItemStack stack4) {
		NonNullList<ItemStack> ITEMS = NonNullList.<ItemStack>withSize(4, ItemStack.EMPTY);
			
		ITEMS.set(0, stack1);
		ITEMS.set(1, stack2);
		ITEMS.set(2, stack3);
		ITEMS.set(3, stack4);
		
		int index = 0;
		int maxDamage = 0;
		int attackDamage = 0;
		
		List<String> tooltip = new ArrayList<String>() {};
		tooltip.add("");
		tooltip.add("Made out of:");
		
		for (ItemStack item : ITEMS) {
			ToolMaterial material = ToolMaterial.IRON;
			if (!(item.getItem() instanceof ItemShears)) material = ToolMaterial.valueOf(ToolMaterial.class, ((ItemTool)item.getItem()).getToolMaterialName());
			maxDamage += material.getMaxUses();
			attackDamage += material.getAttackDamage();
			
			tooltip.add(Reference.getChatColor(new Color(Config.MATERIALCOLOURS.get(material.toString()))) + item.getDisplayName());
			
			if (!(item.getItem() instanceof ItemShears)) tooltip.add(TextFormatting.DARK_BLUE + "    Harvest level: " + TextFormatting.GRAY + material.getHarvestLevel());
			tooltip.add("");
			
			if (item.getItem() instanceof ItemPickaxe) {
								
				ITEM_MAPPING.put("PICKAXE", index);
				HARVEST_LEVELS.put("PICKAXE", material.getHarvestLevel());
				
			} else if (item.getItem() instanceof ItemAxe || item.getItem() instanceof ToolAxe || (item.getItem().getUnlocalizedName().contains("axe") && !(item.getItem() instanceof ItemPickaxe))) {
				
				ITEM_MAPPING.put("AXE", index);
				HARVEST_LEVELS.put("AXE", material.getHarvestLevel());
				
			} else if (item.getItem() instanceof ItemSpade) {
				
				ITEM_MAPPING.put("SHOVEL", index);
				HARVEST_LEVELS.put("SHOVEL", material.getHarvestLevel());
				
			} else ITEM_MAPPING.put("SHEARS", index);
		}
		
		NBTTagCompound nbt = new NBTTagCompound();

		ItemStackHelper.saveAllItems(nbt, ITEMS);
		
		
		input.setTagCompound(nbt);
		
		input.getItem().setMaxDamage(maxDamage);
		
		input = ToolMulti.writeToNBT(input, "tooltip", tooltip);
		
		return input;
	}
	
	public static NonNullList<ItemStack> getItemsFromStack(ItemStack stack) {
		
		NonNullList<ItemStack> ITEMS = NonNullList.<ItemStack>withSize(4, ItemStack.EMPTY);
		
		if (stack.hasTagCompound()) {
			ItemStackHelper.loadAllItems(stack.getTagCompound(), ITEMS);
		
		}
		
		return ITEMS;
	}
	
	public boolean isEmpty() {
		
		for (ItemStack stack : this.ITEMS) {
			if (!stack.isEmpty()) return false;
		}
		return true;
		
	}
	
	private String getBlockHarvestType(IBlockState state) {
		
		if (ItemStack.EMPTY.canHarvestBlock(state)) {
			if (getDestroySpeed(new ItemStack(Items.DIAMOND_AXE), state) > getDestroySpeed(ItemStack.EMPTY, state)) return "AXE";
			else if (getDestroySpeed(new ItemStack(Items.DIAMOND_PICKAXE), state) > getDestroySpeed(ItemStack.EMPTY, state)) return "PICKAXE";
			else if (getDestroySpeed(new ItemStack(Items.DIAMOND_SHOVEL), state) > getDestroySpeed(ItemStack.EMPTY, state)) return "SHOVEL";
		}
		
		if (Items.DIAMOND_AXE.canHarvestBlock(state)) return "AXE";
		else if (Items.DIAMOND_PICKAXE.canHarvestBlock(state)) return "PICKAXE";
		else if (Items.DIAMOND_SHOVEL.canHarvestBlock(state)) return "SHOVEL";
		
		return "PICKAXE";
	}
	
	@Override
	public boolean canHarvestBlock(IBlockState blockIn) {

		Block block = blockIn.getBlock();
		
		if (EFFECTIVE_MATERIALS.contains(blockIn.getMaterial()) || block.getHarvestTool(blockIn) == null || EFFECTIVE_ON.contains(block)) {
			
			String type = this.getBlockHarvestType(blockIn);
			
			return this.HARVEST_LEVELS.get(type) >= block.getHarvestLevel(blockIn);
		}
		
		return false;
	}
	
	@Override
	public float getDestroySpeed(ItemStack stack, IBlockState state) {
	
		String type = this.getBlockHarvestType(state);
		
		return canHarvestBlock(state) ? ToolMaterial.valueOf(ToolMaterial.class, ((ItemTool)stack.getItem()).getToolMaterialName()).getEfficiency() : 0.0F;
	}

	@Override
	public void registerModels() {

		Main.proxy.registerItemRenderer(this, 0, "inventory");		
	}
	
	@Override
	public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {

		writeToNBT(stack, "mining", false);
	}

}
