package com.lavaingot.minersdream.proxy;

import java.io.File;

import com.lavaingot.minersdream.Config;
import com.lavaingot.minersdream.util.Reference;

import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

	public void registerItemRenderer(Item item, int meta, String id) {}
	public void registerVariantRenderer(Item item, int meta, String filename, String id) {}
	public void registerItemVariantRenderer(Item item, int meta, String filename, String id) {}
	
	
	public static Configuration config;
	
	public void preInit(FMLPreInitializationEvent event) {
		File directory = new File(event.getModConfigurationDirectory(), Reference.MOD_ID);
		config = new Configuration(new File(directory.getPath(), "minersdream.cfg"));
		
		Config.readConfig();
	}
	
	public void postInit(FMLPostInitializationEvent event) {
		if (config.hasChanged()) {
			config.save();
		}
	}
}
