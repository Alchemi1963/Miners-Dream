package com.lavaingot.minersdream.util;

import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.unimi.dsi.fastutil.booleans.BooleanList;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;

public class Reference {
	
	public static final String MOD_ID = "minersdream";
	public static final String NAME = "Miners Dream";
	public static final String VERSION = "1.0";
	public static final String ACCEPTED_MC = "[1.12.2]";
	
	public static final String CLIENT_PROXY_CLASS = "com.lavaingot.minersdream.proxy.ClientProxy";
	public static final String SERVER_PROXY_CLASS = "com.lavaingot.minersdream.proxy.ServerProxy";
	
	//Unlocalized Names
	//Ores
	public static final String END_ORE = "ore_end";
	public static final String NETHER_ORE = "ore_nether";
	public static final String OVERWORLD_ORE = "ore_overworld";
	
	//Blocks
	public static final String BLOCK_METAL = "block_metal";
	public static final String BLOCK_SUPERTORCH = "block_supertorch";
	public static final String BLOCK_TORCHHANDLER = "block_torchhandler";
	public static final String BLOCK_CONTAINER = "block_container";
	public static final String ALLOYING_FURNACE = "alloying_furnace";
	public static final String BLOCK_ALLOY = "block_alloy";
	public static final String MECH_COMBINER = "mechanical_combiner"; 
			
	//Items
	public static final String ALLOY_INGOT = "alloy_ingot";
	public static final String STICK_COBALT = "stick_cobalt";
	public static final String THE_DREAM = "the_dream";
	
	public static final String COPPER_PICKAXE = "pickaxe_copper";
	public static final String COPPER_SWORD = "sword_copper";
	public static final String COPPER_HOE = "hoe_copper";
	public static final String COPPER_AXE = "axe_copper";
	public static final String COPPER_SHOVEL = "shovel_copper";
	
	public static final String ALUMINIUM_PICKAXE = "pickaxe_aluminium";
	public static final String ALUMINIUM_SWORD = "sword_aluminium";
	public static final String ALUMINIUM_HOE = "hoe_aluminium";
	public static final String ALUMINIUM_AXE = "axe_aluminium";
	public static final String ALUMINIUM_SHOVEL = "shovel_aluminium";
	
	public static final String BISMUTH_PICKAXE = "pickaxe_bismuth";
	public static final String BISMUTH_SWORD = "sword_bismuth";
	public static final String BISMUTH_HOE = "hoe_bismuth";
	public static final String BISMUTH_AXE = "axe_bismuth";
	public static final String BISMUTH_SHOVEL = "shovel_bismuth";
	
	public static final String CADMIUM_PICKAXE = "pickaxe_cadmium";
	public static final String CADMIUM_SWORD = "sword_cadmium";
	public static final String CADMIUM_HOE = "hoe_cadmium";
	public static final String CADMIUM_AXE = "axe_cadmium";
	public static final String CADMIUM_SHOVEL = "shovel_cadmium";
	
	public static final String POTASSIUM_PICKAXE = "pickaxe_potassium";
	public static final String POTASSIUM_SWORD = "sword_potassium";
	public static final String POTASSIUM_HOE = "hoe_potassium";
	public static final String POTASSIUM_AXE = "axe_potassium";
	public static final String POTASSIUM_SHOVEL = "shovel_potassium";
	
	public static final String COBALT_PICKAXE = "pickaxe_cobalt";
	public static final String COBALT_SWORD = "sword_cobalt";
	public static final String COBALT_HOE = "hoe_cobalt";
	public static final String COBALT_AXE = "axe_cobalt";
	public static final String COBALT_SHOVEL = "shovel_cobalt";
	
	public static final String PLATINUM_PICKAXE = "pickaxe_platinum";
	public static final String PLATINUM_SWORD = "sword_platinum";
	public static final String PLATINUM_HOE = "hoe_platinum";
	public static final String PLATINUM_AXE = "axe_platinum";
	public static final String PLATINUM_SHOVEL = "shovel_platinum";
	
	public static final String SILVER_PICKAXE = "pickaxe_silver";
	public static final String SILVER_SWORD = "sword_silver";
	public static final String SILVER_HOE = "hoe_silver";
	public static final String SILVER_AXE = "axe_silver";
	public static final String SILVER_SHOVEL = "shovel_silver";
	
	public static final String TIN_PICKAXE = "pickaxe_tin";
	public static final String TIN_SWORD = "sword_tin";
	public static final String TIN_HOE = "hoe_tin";
	public static final String TIN_AXE = "axe_tin";
	public static final String TIN_SHOVEL = "shovel_tin";
	
	public static final String URANIUM_PICKAXE = "pickaxe_uranium";
	public static final String URANIUM_SWORD = "sword_uranium";
	public static final String URANIUM_HOE = "hoe_uranium";
	public static final String URANIUM_AXE = "axe_uranium";
	public static final String URANIUM_SHOVEL = "shovel_uranium";
	
	public static final String TUNGSTEN_PICKAXE = "pickaxe_tungsten";
	public static final String TUNGSTEN_SWORD = "sword_tungsten";
	public static final String TUNGSTEN_HOE = "hoe_tungsten";
	public static final String TUNGSTEN_AXE = "axe_tungsten";
	public static final String TUNGSTEN_SHOVEL = "shovel_tungsten";
	
	public static final String ZINC_PICKAXE = "pickaxe_zinc";
	public static final String ZINC_SWORD = "sword_zinc";
	public static final String ZINC_HOE = "hoe_zinc";
	public static final String ZINC_AXE = "axe_zinc";
	public static final String ZINC_SHOVEL = "shovel_zinc";
	
	
	public static final String HELMET_COPPER = "helmet_copper";
	public static final String CHESTPLATE_COPPER = "chestplate_copper";
	public static final String LEGGINGS_COPPER = "leggings_copper";
	public static final String BOOTS_COPPER = "boots_copper";
	
	public static final String HELMET_ALUMINIUM = "helmet_aluminium";
	public static final String CHESTPLATE_ALUMINIUM = "chestplate_aluminium";
	public static final String LEGGINGS_ALUMINIUM = "leggings_aluminium";
	public static final String BOOTS_ALUMINIUM = "boots_aluminium";
	
	public static final String HELMET_BISMUTH = "helmet_bismuth";
	public static final String CHESTPLATE_BISMUTH = "chestplate_bismuth";
	public static final String LEGGINGS_BISMUTH = "leggings_bismuth";
	public static final String BOOTS_BISMUTH = "boots_bismuth";
	
	public static final String HELMET_PLATINUM = "helmet_platinum";
	public static final String CHESTPLATE_PLATINUM = "chestplate_platinum";
	public static final String LEGGINGS_PLATINUM = "leggings_platinum";
	public static final String BOOTS_PLATINUM = "boots_platinum";
	
	public static final String HELMET_TIN = "helmet_tin";
	public static final String CHESTPLATE_TIN = "chestplate_tin";
	public static final String LEGGINGS_TIN = "leggings_tin";
	public static final String BOOTS_TIN = "boots_tin";
	
	public static final String HELMET_URANIUM = "helmet_uranium";
	public static final String CHESTPLATE_URANIUM = "chestplate_uranium";
	public static final String LEGGINGS_URANIUM = "leggings_uranium";
	public static final String BOOTS_URANIUM = "boots_uranium";
	
	public static final String HELMET_TUNGSTEN = "helmet_tungsten";
	public static final String CHESTPLATE_TUNGSTEN = "chestplate_tungsten";
	public static final String LEGGINGS_TUNGSTEN = "leggings_tungsten";
	public static final String BOOTS_TUNGSTEN = "boots_tungsten";
	
	public static final String MULTI_TOOL = "multi_tool";
	
	//Containers
	public static final String ALLOY_FURNACE_CONTAINER = "container.alloy_furnace";
	public static final String BLOCK_CONTAINER_CONTAINER = "container.block_container";
	public static final String MECHANICAL_COMBINER_CONTAINER = "container.mechanical_combiner";
	
	
	//GUI ids
	public static final int GUI_CONTAINER = 0;
	public static final int GUI_ALLOYING_FURNACE = 1;
	public static final int GUI_MECH_COMBINER = 2;
	
	private final static Color dark_blue = new Color(0  , 0  , 170);
	private final static Color blue = new Color(83 , 85 , 255);
	private final static Color dark_aqua = new Color(3  , 169, 169);
	private final static Color aqua = new Color(83 , 255, 255);
	private final static Color dark_red = new Color(171, 1  , 1  );
	private final static Color red = new Color(255, 81 , 82 );
	private final static Color yellow = new Color(253, 255, 87 );
	private final static Color gold = new Color(255, 172, 4  );
	private final static Color dark_green = new Color(0  , 170, 3  );
	private final static Color green = new Color(85 , 255, 88 );
	private final static Color dark_purple = new Color(171, 0  , 167);
	private final static Color light_purple = new Color(255, 85 , 252);
	private final static Color white = new Color(255, 255, 255);
	private final static Color gray = new Color(170, 170, 170);
	private final static Color dark_gray = new Color(85 , 85 , 85 );
	private final static Color black = new Color(1  , 1  , 1  );
	
	private final static Map<Color, TextFormatting> map_colour = new HashMap<Color, TextFormatting>();
	private final static Color[] colours = new Color[] {black, dark_blue, dark_green, dark_aqua, dark_red, dark_purple, gold, gray, dark_gray, blue, green, aqua, red, light_purple, yellow, white};
	
	public static TextFormatting getChatColor(Color colour) {
		
		double dr, dg, db;
		double dc = -1;
		Color output = black; 
		
		int index = 0;
		for (Color col : colours) {
			index ++;
			map_colour.put(col, TextFormatting.values()[index]);
			dr = Math.abs(colour.getRed() - col.getRed());
			dg = Math.abs(colour.getGreen() - col.getGreen());
			db = Math.abs(colour.getBlue() - col.getBlue());
			
			if ( dc == -1 || Math.sqrt(Math.pow(dr, 2) + Math.pow(dg, 2) + Math.pow(db, 2)) < dc) {
				output = col;
				dc = Math.sqrt(Math.pow(dr, 2) + Math.pow(dg, 2) + Math.pow(db, 2));
			}
		}
		
		return map_colour.get(output);
	}
}
