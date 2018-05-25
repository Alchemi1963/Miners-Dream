package com.lavaingot.minersdream.util.handlers;

import com.lavaingot.minersdream.Main;
import com.lavaingot.minersdream.init.BlockInit;
import com.lavaingot.minersdream.init.ItemInit;
import com.lavaingot.minersdream.init.PotionInit;
import com.lavaingot.minersdream.objects.blocks.machines.alloyer.TileAlloyingFurnace;
import com.lavaingot.minersdream.objects.blocks.machines.mechanical_combiner.TileMechanicalCombiner;
import com.lavaingot.minersdream.objects.tileentities.TileContainer;
import com.lavaingot.minersdream.util.IHasModel;
import com.lavaingot.minersdream.util.Reference;
import com.lavaingot.minersdream.world.gen.WorldGenCustomOres;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@EventBusSubscriber
public class RegisterHandler {

	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event) {
		
		event.getRegistry().registerAll(ItemInit.ITEMS.toArray(new Item[0]));
	}
	
	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event) {
		
		event.getRegistry().registerAll(BlockInit.BLOCKS.toArray(new Block[0]));
		
		//Tile Entities
		
		GameRegistry.registerTileEntity(TileContainer.class, new ResourceLocation(Reference.MOD_ID + ":" + Reference.BLOCK_CONTAINER));
		GameRegistry.registerTileEntity(TileAlloyingFurnace.class, new ResourceLocation(Reference.MOD_ID + ":" + Reference.ALLOYING_FURNACE));
		GameRegistry.registerTileEntity(TileMechanicalCombiner.class, new ResourceLocation(Reference.MOD_ID + ":" + Reference.MECH_COMBINER));
	}
	
	@SubscribeEvent
	public static void onPotionRegister(RegistryEvent.Register<Potion> event) {
		
		event.getRegistry().registerAll(PotionInit.POTIONS.toArray(new Potion[0]));
	}
	
	
	
	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event) {

		
		
		for(Block block: BlockInit.BLOCKS) {
			
			if(block instanceof IHasModel) {
				((IHasModel)block).registerModels();
			}
		}
		
		for(Item item : ItemInit.ITEMS) {
			
			if(item instanceof IHasModel) {
				((IHasModel)item).registerModels();
			}
		}
	}
	
	public static void otherRegistries() {
		//Ore Generation
		GameRegistry.registerWorldGenerator(new WorldGenCustomOres(), 0);
	}	
	
}
