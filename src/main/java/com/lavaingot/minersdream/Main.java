package com.lavaingot.minersdream;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.lavaingot.minersdream.client.key.KeyBindings;
import com.lavaingot.minersdream.init.BlockInit;
import com.lavaingot.minersdream.init.ItemInit;
import com.lavaingot.minersdream.objects.blocks.BlockSupertorch;
import com.lavaingot.minersdream.objects.tools.ToolMulti;
import com.lavaingot.minersdream.objects.variants.metals.MetalOres;
import com.lavaingot.minersdream.proxy.CommonProxy;
import com.lavaingot.minersdream.tabs.MineableTab;
import com.lavaingot.minersdream.util.CommandModelUpdate;
import com.lavaingot.minersdream.util.Events;
import com.lavaingot.minersdream.util.Reference;
import com.lavaingot.minersdream.util.handlers.GUIHandler;
import com.lavaingot.minersdream.util.handlers.RegisterHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION, acceptedMinecraftVersions = Reference.ACCEPTED_MC, canBeDeactivated = true)
public class Main {
	
	public static final Logger logger = Logger.getGlobal();

	public static final CreativeTabs mineabletab = new MineableTab("mineabletab");
	public static final CreativeTabs mineabletabtools = new MineableTab("mineabletabtools");
	public static final CreativeTabs alloytab = new MineableTab("alloytab");
	
	public static Configuration config;
	public static ToolMulti.ColorHandler CHANDLER;
	
	@Instance
	public static Main instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;
	
	//maak items etc aan
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		MinecraftForge.EVENT_BUS.register(this);
		RegisterHandler.otherRegistries();
		
		proxy.preInit(event);
		
		System.out.println("Hello Pre-World");
	}
	
	//maak de rest aan
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		ItemInit.registerOreOutputs();
		
		MetalOres.registerOreSmelting(BlockInit.ORE_END, "end");
		MetalOres.registerOreSmelting(BlockInit.ORE_NETHER, "nether");
		MetalOres.registerOreSmelting(BlockInit.ORE_OVERWORLD, "overworld");
		
		Events.init();
		KeyBindings.init();
		
		CHANDLER = new ToolMulti.ColorHandler();
		Minecraft.getMinecraft().getItemColors().registerItemColorHandler(CHANDLER, ItemInit.MULTI_TOOL);
		
		NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, new GUIHandler());
		
		System.out.println("Hello World");
	}
	
	//doe extra dingen
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		
		
		BlockInit.setBlockProperties();
		MinecraftForge.EVENT_BUS.register(Events.class);
		MinecraftForge.EVENT_BUS.register(BlockSupertorch.class);
		
		proxy.postInit(event);
		
		System.out.println("Hello Wasteland");
		
	}	
	
	@EventHandler
	public void onServerStart(FMLServerStartingEvent event) {
		event.registerServerCommand(new CommandModelUpdate());
	}
	
	public static void print(Object... objects) {
		
		String objs = new String();
		for (Object obj:objects) {
			
			if (objects[0].equals(obj)) {
				objs = obj.toString();
			} else {
				objs = objs + ", " + obj.toString();
				
			}
		}
		System.out.println(objs);
	}
}
