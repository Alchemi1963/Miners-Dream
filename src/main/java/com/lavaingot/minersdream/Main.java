package com.lavaingot.minersdream;

import java.util.logging.Logger;

import com.lavaingot.minersdream.client.key.KeyBindings;
import com.lavaingot.minersdream.init.BlockInit;
import com.lavaingot.minersdream.init.ItemInit;
import com.lavaingot.minersdream.objects.blocks.BlockContainer;
import com.lavaingot.minersdream.objects.blocks.BlockSupertorch;
import com.lavaingot.minersdream.objects.commands.CommandClearCode;
import com.lavaingot.minersdream.objects.commands.CommandModelUpdate;
import com.lavaingot.minersdream.objects.tools.ToolMulti;
import com.lavaingot.minersdream.objects.variants.metals.MetalOres;
import com.lavaingot.minersdream.proxy.CommonProxy;
import com.lavaingot.minersdream.tabs.AlloyTab;
import com.lavaingot.minersdream.tabs.ArmourTab;
import com.lavaingot.minersdream.tabs.MachineTab;
import com.lavaingot.minersdream.tabs.MetalTab;
import com.lavaingot.minersdream.tabs.MiscTab;
import com.lavaingot.minersdream.tabs.ToolTab;
import com.lavaingot.minersdream.util.Events;
import com.lavaingot.minersdream.util.Reference;
import com.lavaingot.minersdream.util.handlers.GUIHandler;
import com.lavaingot.minersdream.util.handlers.MinersdreamPacketHandler;
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
	
	public static Configuration config;
	public static ToolMulti.ColorHandler CHANDLER;
	public static BlockContainer.ColorHandler CHANDLER_CONTAINER;
	
	public static final CreativeTabs metalTab = new MetalTab("minersdream:metals");
	public static final CreativeTabs alloyTab = new AlloyTab("minersdream:alloys");
	public static final CreativeTabs machineTab = new MachineTab("minersdream:machines");
	public static final CreativeTabs toolTab = new ToolTab("minersdream:tools");
	public static final CreativeTabs armourTab = new ArmourTab("minersdream:armour");
	public static final CreativeTabs miscTab = new MiscTab("minersdream:miscellaneous");
	
	
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
		
		MinersdreamPacketHandler.registerMessages();
		
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
		CHANDLER_CONTAINER = new BlockContainer.ColorHandler();
		Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler(CHANDLER_CONTAINER, BlockInit.BLOCK_CONTAINER);
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
		event.registerServerCommand(new CommandClearCode());
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
