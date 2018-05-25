package com.lavaingot.minersdream.init;

import java.util.ArrayList;
import java.util.List;

import com.lavaingot.minersdream.Main;
import com.lavaingot.minersdream.objects.armour.ArmourBase;
import com.lavaingot.minersdream.objects.items.ItemBase;
import com.lavaingot.minersdream.objects.items.ItemDream;
import com.lavaingot.minersdream.objects.tools.ToolAxe;
import com.lavaingot.minersdream.objects.tools.ToolHoe;
import com.lavaingot.minersdream.objects.tools.ToolMulti;
import com.lavaingot.minersdream.objects.tools.ToolPickaxe;
import com.lavaingot.minersdream.objects.tools.ToolShovel;
import com.lavaingot.minersdream.objects.tools.ToolSword;
import com.lavaingot.minersdream.objects.variants.alloys.BaseAlloyIngots;
import com.lavaingot.minersdream.objects.variants.metals.MetalIngots;
import com.lavaingot.minersdream.objects.variants.metals.MetalOres;
import com.lavaingot.minersdream.util.Reference;
import com.lavaingot.minersdream.util.handlers.EnumHandler.MetalOne;

import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

public class ItemInit {
	
	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	//Materials (name, harvestLevel, maxUses, efficiency, damage, enchantability)
	public static final ToolMaterial TOOL_COPPER = 		EnumHelper.addToolMaterial("COPPER", 2, 200, 4.0F, 1.5F, 15);
	public static final ToolMaterial TOOL_ALUMINIUM =		EnumHelper.addToolMaterial("ALUMINIUM", 1, 150, 8.0F, 1.0F, 50);
	public static final ToolMaterial TOOL_BISMUTH =		EnumHelper.addToolMaterial("BISMUTH", 3, 300, 12.0F, 4.0F, 60);
	public static final ToolMaterial TOOL_CADMIUM =		EnumHelper.addToolMaterial("CADMIUM", 1, 50, 1.2F, 0.5F, 5);
	public static final ToolMaterial TOOL_POTASSIUM =		EnumHelper.addToolMaterial("POTASSIUM", 1, 60, 2.0F, 0.5F, 5);
	public static final ToolMaterial TOOL_COBALT =		EnumHelper.addToolMaterial("COBALT", 3, 250, 6.0F, 1.4F, 25);
	public static final ToolMaterial TOOL_PLATINUM =		EnumHelper.addToolMaterial("PLATINUM", 3, 400, 10.0F, 10.0F, 20);
	public static final ToolMaterial TOOL_SILVER =		EnumHelper.addToolMaterial("SILVER", 2, 400, 6.0F, 8.0F, 100);
	public static final ToolMaterial TOOL_TIN =			EnumHelper.addToolMaterial("TIN", 1, 100, 2.0F, 2.0F, 10);
	public static final ToolMaterial TOOL_URANIUM =		EnumHelper.addToolMaterial("URANIUM", 3, 1000, 16.0F, 10.0F, 64);
	public static final ToolMaterial TOOL_TUNGSTEN =		EnumHelper.addToolMaterial("TUNGSTEN", 2, 600, 6.0F, 5.0F, 10);
	public static final ToolMaterial TOOL_ZINC =			EnumHelper.addToolMaterial("ZINC", 1, 30, 6.0F, 1.0F, 50);
	
	//ArmourMaterials (name, textureName, durability, reductionAmounts, enchantability, soundOnEquip, toughness)
	public static final ArmorMaterial ARMOUR_COPPER = 		EnumHelper.addArmorMaterial("armour_copper", Reference.MOD_ID + ":copper", 12, new int[]{2, 4, 9, 3}, 15, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F);
	public static final ArmorMaterial ARMOUR_ALUMINIUM = 		EnumHelper.addArmorMaterial("armour_aluminium", Reference.MOD_ID + ":aluminium", 10, new int[]{2, 4, 4, 4}, 50, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F);
	public static final ArmorMaterial ARMOUR_BISMUTH = 		EnumHelper.addArmorMaterial("armour_bismuth", Reference.MOD_ID + ":bismuth", 15, new int[]{5, 10, 5, 5}, 60, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 2.0F);
	public static final ArmorMaterial ARMOUR_PLATINUM =		EnumHelper.addArmorMaterial("armour_platinum", Reference.MOD_ID + ":platinum", 25, new int[]{6, 12, 5, 5}, 20, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 3.0F);
	public static final ArmorMaterial ARMOUR_TIN =			EnumHelper.addArmorMaterial("armour_tin", Reference.MOD_ID + ":tin", 10, new int[]{2, 4, 1, 3}, 10, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.5F);
	public static final ArmorMaterial ARMOUR_URANIUM = 		EnumHelper.addArmorMaterial("armour_uranium", Reference.MOD_ID + ":uranium", 20, new int[]{5, 10, 5, 5}, 64, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 4.0F);
	public static final ArmorMaterial ARMOUR_TUNGSTEN =		EnumHelper.addArmorMaterial("armour_tungsten", Reference.MOD_ID + ":tungsten", 14, new int[]{3, 4, 5, 4}, 10, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.8F);
	
	//Items
	
	public static final Item INGOT = new MetalIngots("ingot", Main.mineabletab);
	
	public static final Item ALLOY_INGOT = new BaseAlloyIngots(Reference.ALLOY_INGOT, Main.alloytab);
	
	public static final Item STICK_COBALT = 		new ItemBase(Reference.STICK_COBALT, Main.mineabletabtools);
	public static final ItemDream THE_DREAM = 		new ItemDream(Reference.THE_DREAM, Main.mineabletabtools);
	
	
	//Tools
	public static final Item PICKAXE_COPPER = 		new ToolPickaxe(Reference.COPPER_PICKAXE, TOOL_COPPER, Main.mineabletabtools);
	public static final Item SWORD_COPPER = 		new ToolSword(Reference.COPPER_SWORD, TOOL_COPPER, Main.mineabletabtools);
	public static final Item HOE_COPPER = 			new ToolHoe(Reference.COPPER_HOE, TOOL_COPPER, Main.mineabletabtools);
	public static final Item AXE_COPPER = 			new ToolAxe(Reference.COPPER_AXE, TOOL_COPPER, Main.mineabletabtools);
	public static final Item SHOVEL_COPPER = 		new ToolShovel(Reference.COPPER_SHOVEL, TOOL_COPPER, Main.mineabletabtools);

	public static final Item PICKAXE_ALUMINIUM = 	new ToolPickaxe(Reference.ALUMINIUM_PICKAXE, TOOL_ALUMINIUM, Main.mineabletabtools);
	public static final Item SWORD_ALUMINIUM = 		new ToolSword(Reference.ALUMINIUM_SWORD, TOOL_ALUMINIUM, Main.mineabletabtools);
	public static final Item HOE_ALUMINIUM = 		new ToolHoe(Reference.ALUMINIUM_HOE, TOOL_ALUMINIUM, Main.mineabletabtools);
	public static final Item AXE_ALUMINIUM = 		new ToolAxe(Reference.ALUMINIUM_AXE, TOOL_ALUMINIUM, Main.mineabletabtools);
	public static final Item SHOVEL_ALUMINIUM = 	new ToolShovel(Reference.ALUMINIUM_SHOVEL, TOOL_ALUMINIUM, Main.mineabletabtools);

	public static final Item PICKAXE_BISMUTH = 		new ToolPickaxe(Reference.BISMUTH_PICKAXE, TOOL_BISMUTH, Main.mineabletabtools);
	public static final Item SWORD_BISMUTH = 		new ToolSword(Reference.BISMUTH_SWORD, TOOL_BISMUTH, Main.mineabletabtools);
	public static final Item HOE_BISMUTH = 			new ToolHoe(Reference.BISMUTH_HOE, TOOL_BISMUTH, Main.mineabletabtools);
	public static final Item AXE_BISMUTH = 			new ToolAxe(Reference.BISMUTH_AXE, TOOL_BISMUTH, Main.mineabletabtools);
	public static final Item SHOVEL_BISMUTH = 		new ToolShovel(Reference.BISMUTH_SHOVEL, TOOL_BISMUTH, Main.mineabletabtools);
	
	public static final Item PICKAXE_CADMIUM = 		new ToolPickaxe(Reference.CADMIUM_PICKAXE, TOOL_CADMIUM, Main.mineabletabtools);
	public static final Item SWORD_CADMIUM = 		new ToolSword(Reference.CADMIUM_SWORD, TOOL_CADMIUM, Main.mineabletabtools);
	public static final Item HOE_CADMIUM = 			new ToolHoe(Reference.CADMIUM_HOE, TOOL_CADMIUM, Main.mineabletabtools);
	public static final Item AXE_CADMIUM = 			new ToolAxe(Reference.CADMIUM_AXE, TOOL_CADMIUM, Main.mineabletabtools);
	public static final Item SHOVEL_CADMIUM = 		new ToolShovel(Reference.CADMIUM_SHOVEL, TOOL_CADMIUM, Main.mineabletabtools);
	
	public static final Item PICKAXE_POTASSIUM = 	new ToolPickaxe(Reference.POTASSIUM_PICKAXE, TOOL_POTASSIUM, Main.mineabletabtools);
	public static final Item SWORD_POTASSIUM = 		new ToolSword(Reference.POTASSIUM_SWORD, TOOL_POTASSIUM, Main.mineabletabtools);
	public static final Item HOE_POTASSIUM = 		new ToolHoe(Reference.POTASSIUM_HOE, TOOL_POTASSIUM, Main.mineabletabtools);
	public static final Item AXE_POTASSIUM = 		new ToolAxe(Reference.POTASSIUM_AXE, TOOL_POTASSIUM, Main.mineabletabtools);
	public static final Item SHOVEL_POTASSIUM = 	new ToolShovel(Reference.POTASSIUM_SHOVEL, TOOL_POTASSIUM, Main.mineabletabtools);
	
	public static final Item PICKAXE_COBALT = 		new ToolPickaxe(Reference.COBALT_PICKAXE, TOOL_COBALT, Main.mineabletabtools);
	public static final Item SWORD_COBALT = 		new ToolSword(Reference.COBALT_SWORD, TOOL_COBALT, Main.mineabletabtools);
	public static final Item HOE_COBALT = 			new ToolHoe(Reference.COBALT_HOE, TOOL_COBALT, Main.mineabletabtools);
	public static final Item AXE_COBALT = 			new ToolAxe(Reference.COBALT_AXE, TOOL_COBALT, Main.mineabletabtools);
	public static final Item SHOVEL_COBALT = 		new ToolShovel(Reference.COBALT_SHOVEL, TOOL_COBALT, Main.mineabletabtools);
	
	public static final Item PICKAXE_PLATINUM = 	new ToolPickaxe(Reference.PLATINUM_PICKAXE, TOOL_PLATINUM, Main.mineabletabtools);
	public static final Item SWORD_PLATINUM = 		new ToolSword(Reference.PLATINUM_SWORD, TOOL_PLATINUM, Main.mineabletabtools);
	public static final Item HOE_PLATINUM = 		new ToolHoe(Reference.PLATINUM_HOE, TOOL_PLATINUM, Main.mineabletabtools);
	public static final Item AXE_PLATINUM = 		new ToolAxe(Reference.PLATINUM_AXE, TOOL_PLATINUM, Main.mineabletabtools);
	public static final Item SHOVEL_PLATINUM = 		new ToolShovel(Reference.PLATINUM_SHOVEL, TOOL_PLATINUM, Main.mineabletabtools);
	
	public static final Item PICKAXE_SILVER = 		new ToolPickaxe(Reference.SILVER_PICKAXE, TOOL_SILVER, Main.mineabletabtools);
	public static final Item SWORD_SILVER = 		new ToolSword(Reference.SILVER_SWORD, TOOL_SILVER, Main.mineabletabtools);
	public static final Item HOE_SILVER = 			new ToolHoe(Reference.SILVER_HOE, TOOL_SILVER, Main.mineabletabtools);
	public static final Item AXE_SILVER = 			new ToolAxe(Reference.SILVER_AXE, TOOL_SILVER, Main.mineabletabtools);
	public static final Item SHOVEL_SILVER = 		new ToolShovel(Reference.SILVER_SHOVEL, TOOL_SILVER, Main.mineabletabtools);
	
	public static final Item PICKAXE_TIN = 			new ToolPickaxe(Reference.TIN_PICKAXE, TOOL_TIN, Main.mineabletabtools);
	public static final Item SWORD_TIN = 			new ToolSword(Reference.TIN_SWORD, TOOL_TIN, Main.mineabletabtools);
	public static final Item HOE_TIN = 				new ToolHoe(Reference.TIN_HOE, TOOL_TIN, Main.mineabletabtools);
	public static final Item AXE_TIN = 				new ToolAxe(Reference.TIN_AXE, TOOL_TIN, Main.mineabletabtools);
	public static final Item SHOVEL_TIN = 			new ToolShovel(Reference.TIN_SHOVEL, TOOL_TIN, Main.mineabletabtools);
	
	public static final Item PICKAXE_URANIUM = 		new ToolPickaxe(Reference.URANIUM_PICKAXE, TOOL_URANIUM, Main.mineabletabtools);
	public static final Item SWORD_URANIUM = 		new ToolSword(Reference.URANIUM_SWORD, TOOL_URANIUM, Main.mineabletabtools);
	public static final Item HOE_URANIUM = 			new ToolHoe(Reference.URANIUM_HOE, TOOL_URANIUM, Main.mineabletabtools);
	public static final Item AXE_URANIUM = 			new ToolAxe(Reference.URANIUM_AXE, TOOL_URANIUM, Main.mineabletabtools);
	public static final Item SHOVEL_URANIUM = 		new ToolShovel(Reference.URANIUM_SHOVEL, TOOL_URANIUM, Main.mineabletabtools);
	
	public static final Item PICKAXE_TUNGSTEN = 	new ToolPickaxe(Reference.TUNGSTEN_PICKAXE, TOOL_TUNGSTEN, Main.mineabletabtools);
	public static final Item SWORD_TUNGSTEN = 		new ToolSword(Reference.TUNGSTEN_SWORD, TOOL_TUNGSTEN, Main.mineabletabtools);
	public static final Item HOE_TUNGSTEN = 		new ToolHoe(Reference.TUNGSTEN_HOE, TOOL_TUNGSTEN, Main.mineabletabtools);
	public static final Item AXE_TUNGSTEN = 		new ToolAxe(Reference.TUNGSTEN_AXE, TOOL_TUNGSTEN, Main.mineabletabtools);
	public static final Item SHOVEL_TUNGSTEN = 		new ToolShovel(Reference.TUNGSTEN_SHOVEL, TOOL_TUNGSTEN, Main.mineabletabtools);
	
	public static final Item PICKAXE_ZINC = 		new ToolPickaxe(Reference.ZINC_PICKAXE, TOOL_ZINC, Main.mineabletabtools);
	public static final Item SWORD_ZINC = 			new ToolSword(Reference.ZINC_SWORD, TOOL_ZINC, Main.mineabletabtools);
	public static final Item HOE_ZINC = 			new ToolHoe(Reference.ZINC_HOE, TOOL_ZINC, Main.mineabletabtools);
	public static final Item AXE_ZINC = 			new ToolAxe(Reference.ZINC_AXE, TOOL_ZINC, Main.mineabletabtools);
	public static final Item SHOVEL_ZINC = 			new ToolShovel(Reference.ZINC_SHOVEL, TOOL_ZINC, Main.mineabletabtools);
	
	//Armour
	public static final Item HELMET_COPPER = 		new ArmourBase(Reference.HELMET_COPPER, ARMOUR_COPPER, 1, EntityEquipmentSlot.HEAD, Main.mineabletab);
	public static final Item CHESTPLATE_COPPER = 	new ArmourBase(Reference.CHESTPLATE_COPPER, ARMOUR_COPPER, 1, EntityEquipmentSlot.CHEST, Main.mineabletab);
	public static final Item LEGGINGS_COPPER = 		new ArmourBase(Reference.LEGGINGS_COPPER, ARMOUR_COPPER, 2, EntityEquipmentSlot.LEGS, Main.mineabletab);
	public static final Item BOOTS_COPPER = 		new ArmourBase(Reference.BOOTS_COPPER, ARMOUR_COPPER, 1, EntityEquipmentSlot.FEET, Main.mineabletab);
	
	public static final Item HELMET_ALUMINIUM = 	new ArmourBase(Reference.HELMET_ALUMINIUM, ARMOUR_ALUMINIUM, 1, EntityEquipmentSlot.HEAD, Main.mineabletab);
	public static final Item CHESTPLATE_ALUMINIUM = new ArmourBase(Reference.CHESTPLATE_ALUMINIUM, ARMOUR_ALUMINIUM, 1, EntityEquipmentSlot.CHEST, Main.mineabletab);
	public static final Item LEGGINGS_ALUMINIUM = 	new ArmourBase(Reference.LEGGINGS_ALUMINIUM, ARMOUR_ALUMINIUM, 2, EntityEquipmentSlot.LEGS, Main.mineabletab);
	public static final Item BOOTS_ALUMINIUM = 		new ArmourBase(Reference.BOOTS_ALUMINIUM, ARMOUR_ALUMINIUM, 1, EntityEquipmentSlot.FEET, Main.mineabletab);
	
	public static final Item HELMET_BISMUTH = 		new ArmourBase(Reference.HELMET_BISMUTH, ARMOUR_BISMUTH, 1, EntityEquipmentSlot.HEAD, Main.mineabletab);
	public static final Item CHESTPLATE_BISMUTH = 	new ArmourBase(Reference.CHESTPLATE_BISMUTH, ARMOUR_BISMUTH, 1, EntityEquipmentSlot.CHEST, Main.mineabletab);
	public static final Item LEGGINGS_BISMUTH = 	new ArmourBase(Reference.LEGGINGS_BISMUTH, ARMOUR_BISMUTH, 2, EntityEquipmentSlot.LEGS, Main.mineabletab);
	public static final Item BOOTS_BISMUTH = 		new ArmourBase(Reference.BOOTS_BISMUTH, ARMOUR_BISMUTH, 1, EntityEquipmentSlot.FEET, Main.mineabletab);
	
	public static final Item HELMET_PLATINUM = 		new ArmourBase(Reference.HELMET_PLATINUM, ARMOUR_PLATINUM, 1, EntityEquipmentSlot.HEAD, Main.mineabletab);
	public static final Item CHESTPLATE_PLATINUM = 	new ArmourBase(Reference.CHESTPLATE_PLATINUM, ARMOUR_PLATINUM, 1, EntityEquipmentSlot.CHEST, Main.mineabletab);
	public static final Item LEGGINGS_PLATINUM = 	new ArmourBase(Reference.LEGGINGS_PLATINUM, ARMOUR_PLATINUM, 2, EntityEquipmentSlot.LEGS, Main.mineabletab);
	public static final Item BOOTS_PLATINUM = 		new ArmourBase(Reference.BOOTS_PLATINUM, ARMOUR_PLATINUM, 1, EntityEquipmentSlot.FEET, Main.mineabletab);
	
	public static final Item HELMET_TIN = 			new ArmourBase(Reference.HELMET_TIN, ARMOUR_TIN, 1, EntityEquipmentSlot.HEAD, Main.mineabletab);
	public static final Item CHESTPLATE_TIN = 		new ArmourBase(Reference.CHESTPLATE_TIN, ARMOUR_TIN, 1, EntityEquipmentSlot.CHEST, Main.mineabletab);
	public static final Item LEGGINGS_TIN = 		new ArmourBase(Reference.LEGGINGS_TIN, ARMOUR_TIN, 2, EntityEquipmentSlot.LEGS, Main.mineabletab);
	public static final Item BOOTS_TIN = 			new ArmourBase(Reference.BOOTS_TIN, ARMOUR_TIN, 1, EntityEquipmentSlot.FEET, Main.mineabletab);
	
	public static final Item HELMET_URANIUM = 		new ArmourBase(Reference.HELMET_URANIUM, ARMOUR_URANIUM, 1, EntityEquipmentSlot.HEAD, Main.mineabletab);
	public static final Item CHESTPLATE_URANIUM = 	new ArmourBase(Reference.CHESTPLATE_URANIUM, ARMOUR_URANIUM, 1, EntityEquipmentSlot.CHEST, Main.mineabletab);
	public static final Item LEGGINGS_URANIUM = 	new ArmourBase(Reference.LEGGINGS_URANIUM, ARMOUR_URANIUM, 2, EntityEquipmentSlot.LEGS, Main.mineabletab);
	public static final Item BOOTS_URANIUM = 		new ArmourBase(Reference.BOOTS_URANIUM, ARMOUR_URANIUM, 1, EntityEquipmentSlot.FEET, Main.mineabletab);
	
	public static final Item HELMET_TUNGSTEN = 		new ArmourBase(Reference.HELMET_TUNGSTEN, ARMOUR_TUNGSTEN, 1, EntityEquipmentSlot.HEAD, Main.mineabletab);
	public static final Item CHESTPLATE_TUNGSTEN = 	new ArmourBase(Reference.CHESTPLATE_TUNGSTEN, ARMOUR_TUNGSTEN, 1, EntityEquipmentSlot.CHEST, Main.mineabletab);
	public static final Item LEGGINGS_TUNGSTEN = 	new ArmourBase(Reference.LEGGINGS_TUNGSTEN, ARMOUR_TUNGSTEN, 2, EntityEquipmentSlot.LEGS, Main.mineabletab);
	public static final Item BOOTS_TUNGSTEN = 		new ArmourBase(Reference.BOOTS_TUNGSTEN, ARMOUR_TUNGSTEN, 1, EntityEquipmentSlot.FEET, Main.mineabletab);
	
	//Custom Tools
	public static final Item MULTI_TOOL = 			new ToolMulti(Reference.MULTI_TOOL, TOOL_COBALT, Main.mineabletabtools);
	
	
	public static void registerOreOutputs() {
		
		for (MetalOne variant : MetalOne.values()) {
			MetalOres.OreOutput.add((new ItemStack(INGOT, 1, variant.getMeta()).getItem()));
		}
	}
}
