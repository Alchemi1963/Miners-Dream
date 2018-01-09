package com.lavaingot.minersdream;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.lavaingot.minersdream.init.BlockInit;
import com.lavaingot.minersdream.init.BlockOres;
import com.lavaingot.minersdream.init.ItemInit;
import com.lavaingot.minersdream.init.RecipeInit;
import com.lavaingot.minersdream.objects.blocks.BlockSupertorch;
import com.lavaingot.minersdream.proxy.CommonProxy;
import com.lavaingot.minersdream.tabs.MineableTab;
import com.lavaingot.minersdream.util.CommandModelUpdate;
import com.lavaingot.minersdream.util.Events;
import com.lavaingot.minersdream.util.Reference;
import com.lavaingot.minersdream.util.handlers.GUIHandler;
import com.lavaingot.minersdream.util.handlers.RegisterHandler;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
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
	
	public static Config cfgInstance;
	
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
		
		new Config(event.getSuggestedConfigurationFile());
		
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
		
		RecipeInit.init();
		
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
		System.out.println("Hello Wasteland");
		
	}	
	
	@EventHandler
	public void onServerStart(FMLServerStartingEvent event) {
		event.registerServerCommand(new CommandModelUpdate());
	}
	
	public static List<ItemStack> getRecipeItems(String[] recipe) {

		List<ItemStack> ITEMS = new ArrayList<ItemStack>();
		
		int index = 0;
		for (String item : recipe) {
			if (item.contains("@")) {
				String[] item_and_meta = item.split("@");
				
				item = item_and_meta[0];
				int meta = Integer.parseInt(item_and_meta[1].replace("@", ""));
	
				Item ITEM = Item.getByNameOrId(item);
				ITEMS.add(new ItemStack(ITEM, 1, meta));
				index ++;
			} else {
				Item ITEM = Item.getByNameOrId(item);
				ITEMS.add(new ItemStack(ITEM, 1));
				index ++;
			}
		}
		
		return ITEMS;
	}
}
