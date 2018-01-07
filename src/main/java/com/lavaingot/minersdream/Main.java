package com.lavaingot.minersdream;

import com.lavaingot.minersdream.init.BlockInit;
import com.lavaingot.minersdream.init.BlockOres;
import com.lavaingot.minersdream.init.ItemInit;
import com.lavaingot.minersdream.objects.blocks.BlockSupertorch;
import com.lavaingot.minersdream.proxy.CommonProxy;
import com.lavaingot.minersdream.tabs.MineableTab;
import com.lavaingot.minersdream.util.CommandModelUpdate;
import com.lavaingot.minersdream.util.Events;
import com.lavaingot.minersdream.util.Reference;
import com.lavaingot.minersdream.util.handlers.RegisterHandler;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION, acceptedMinecraftVersions = Reference.ACCEPTED_MC, canBeDeactivated = true)
public class Main {

	public static final CreativeTabs mineabletab = new MineableTab("mineabletab");
	public static final CreativeTabs mineabletabtools = new MineableTab("mineabletabtools");
	
	@Instance
	public static Main instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy; {} 
	
	//maak items etc aan
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		MinecraftForge.EVENT_BUS.register(this);
		RegisterHandler.otherRegistries();
		
		System.out.println("Hello Pre-World");
	}
	
	//maak de rest aan
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		ItemInit.registerOreOutputs();
		
		BlockOres.registerOreSmelting(BlockInit.ORE_END, "end");
		BlockOres.registerOreSmelting(BlockInit.ORE_NETHER, "nether");
		BlockOres.registerOreSmelting(BlockInit.ORE_OVERWORLD, "overworld");
		
		Events.init();
		
		System.out.println("Hello World");
	}
	
	//doe extra dingen
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		
		BlockInit.setBlockProperties();
		MinecraftForge.EVENT_BUS.register(Events.class);
		MinecraftForge.EVENT_BUS.register(BlockSupertorch.class);
		System.out.println("Hello Wasteland");
		
	}	
	
	@EventHandler
	public void onServerStart(FMLServerStartingEvent event) {
		event.registerServerCommand(new CommandModelUpdate());
	}
}