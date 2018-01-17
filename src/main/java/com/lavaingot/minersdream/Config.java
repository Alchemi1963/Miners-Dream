package com.lavaingot.minersdream;

import java.io.File;
import java.util.HashMap;
import java.util.logging.Level;

import com.lavaingot.minersdream.util.Reference;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class Config {

	public static Configuration config;
	public static Configuration recipes;
	
	//Recipes
	public static String[] alloyBronze = {"9#minersdream:ingot@0", "1#minersdream:ingot@11", "EMPTY", "10#minersdream:alloy_ingot@0"};
	
	//Publics
	public static HashMap<String, Integer> smeltTimes = new HashMap<String, Integer>();
	
	//Configs
	private static String[] smelting = {"item.alloy_ingot_bronze", "200"};
	private static Property smeltTimesProp;
	
	public Config(File defaultConfig) {
		
		File confFolder = new File(defaultConfig, Reference.MOD_ID);
		
		this.config = new Configuration( new File(confFolder, Reference.MOD_ID + ".cfg"));
		this.recipes = new Configuration(new File(confFolder, "recipes.cfg"));
	}
	
	public static void syncConfig() {
		try {		
			
			//Try to load config
			config.load();
			recipes.load();
			
			//Recipes
			Property alloyBronzeProp = recipes.get(Configuration.CATEGORY_GENERAL, "bronze", alloyBronze);
			alloyBronze = alloyBronzeProp.getStringList();
			
			//Config
			smeltTimesProp = config.get("Alloys", "smelting_times", smelting);
			smelting = smeltTimesProp.getStringList();
			
			
			
			
			int index = 1;
			for (String smelt : smelting) {
				if (index % 2 != 0) { smeltTimes.put(smelt, Integer.parseInt(smelting[index])); }
				index ++;
			}
			
			Main.logger.log(Level.CONFIG, "Configurations loaded!");
			
		} catch (Exception e) {
			
			Main.logger.log(Level.WARNING, "Error: " + e.getMessage());
		} finally {
			
			recipes.save();
			config.save();
		}
	}
}
