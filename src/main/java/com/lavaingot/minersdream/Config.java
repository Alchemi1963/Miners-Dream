package com.lavaingot.minersdream;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class Config {

	public static Configuration config;
	public static Configuration recipes;
	
	public static Map<String[], String> recipeGetter;
	
	public static String[] itemDream = {"minersdream:ingot@1", "minecraft:iron_ingot", "minersdream:the_dream"};
	
	public Config(File defaultConfig) {
		this.config = new Configuration(defaultConfig);
		this.recipes = new Configuration(new File("recipes.cfg"));
	}
	
	public static void syncConfig() {
		try {
			//Try to load config
			config.load();
			recipes.load();
			
			Property itemDreamProp  = recipes.get(Configuration.CATEGORY_GENERAL, "minersdream:the_dream", itemDream);
			itemDream = itemDreamProp.getStringList();
			
			recipeGetter.put(itemDream, itemDreamProp.getName());
		} catch (Exception e) {
			Main.logger.log(Level.WARNING, "Error: " + e.getMessage());
		} finally {
			recipes.save();
			config.save();
		}
	}
}
