 package com.lavaingot.minersdream.proxy;

import com.lavaingot.minersdream.client.key.KeyBindings;
import com.lavaingot.minersdream.util.Reference;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

public class ClientProxy extends CommonProxy{
	
	@Override
	public void registerItemRenderer(Item item, int meta, String id) {
		
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));
	}
	
	@Override
	public void registerVariantRenderer(Item item, int meta, String filename, String id) {
		
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(new ResourceLocation(Reference.MOD_ID, filename), id));
	}
	
	@Override
	public void registerItemVariantRenderer(Item item, int meta, String filename, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(new ResourceLocation(Reference.MOD_ID, filename), id));
	}
}
