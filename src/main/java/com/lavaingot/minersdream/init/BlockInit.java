package com.lavaingot.minersdream.init;

import java.util.ArrayList;
import java.util.List;

import com.lavaingot.minersdream.Main;
import com.lavaingot.minersdream.objects.blocks.BlockContainer;
import com.lavaingot.minersdream.objects.blocks.BlockSupertorch;
import com.lavaingot.minersdream.objects.blocks.BlockTorchHandler;
import com.lavaingot.minersdream.objects.blocks.machines.alloyer.BlockAlloyingFurnace;
import com.lavaingot.minersdream.objects.items.BlockOres;
import com.lavaingot.minersdream.objects.items.OreBlocks;
import com.lavaingot.minersdream.util.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockInit {
	
	public static final List<Block> BLOCKS =  new ArrayList<Block>();
	
	public static final Block ORE_END = new BlockOres(Reference.END_ORE, "end", Main.mineabletab);
	public static final Block ORE_NETHER = new BlockOres(Reference.NETHER_ORE, "nether", Main.mineabletab);
	public static final Block ORE_OVERWORLD = new BlockOres(Reference.OVERWORLD_ORE, "overworld", Main.mineabletab);
	
	public static final Block BLOCK_METAL = new OreBlocks(Reference.BLOCK_METAL, Main.mineabletab);
	
	public static Block BLOCK_SUPERTORCH = new BlockSupertorch(Reference.BLOCK_SUPERTORCH, Material.WOOD, Main.mineabletabtools);
	public static Block BLOCK_TORCHHANDLER = new BlockTorchHandler(Reference.BLOCK_TORCHHANDLER, Material.AIR, Main.mineabletabtools);
	public static Block BLOCK_CONTAINER = new BlockContainer(Reference.BLOCK_CONTAINER, CreativeTabs.DECORATIONS);
	public static Block ALLOYING_FURNACE = new BlockAlloyingFurnace(Reference.ALLOYING_FURNACE, Main.mineabletabtools);
	
	public static void setBlockProperties() {
		
		BLOCK_TORCHHANDLER.setBlockUnbreakable();
		BLOCK_TORCHHANDLER = BLOCK_TORCHHANDLER.setLightLevel(1.0F);
		BLOCK_TORCHHANDLER = BLOCK_TORCHHANDLER.setLightOpacity(0);
		BLOCK_TORCHHANDLER = BLOCK_TORCHHANDLER.setResistance(1000000000);
		
		
		ORE_END.setHardness(25);
		ORE_END.setHarvestLevel("pickaxe", 3);
		
		ORE_NETHER.setHarvestLevel("pickaxe", 2);
		ORE_NETHER.setHarvestLevel("pickaxe", 3, ((BlockOres)ORE_NETHER).getStateFromMeta(12));
		ORE_NETHER.setHardness(10);
		
		ORE_OVERWORLD.setHarvestLevel("pickaxe", 1);
		ORE_OVERWORLD.setHarvestLevel("pickaxe", 3, ((BlockOres)ORE_OVERWORLD).getStateFromMeta(12));
		ORE_OVERWORLD.setHarvestLevel("pickaxe", 3, ((BlockOres)ORE_OVERWORLD).getStateFromMeta(2));
		ORE_OVERWORLD.setHardness(5);
		
		BLOCK_METAL.setHarvestLevel("pickaxe", 2);
		BLOCK_METAL.setHarvestLevel("pickaxe", 3, ((OreBlocks)BLOCK_METAL).getStateFromMeta(12));
		BLOCK_METAL.setHarvestLevel("pickaxe", 3, ((OreBlocks)BLOCK_METAL).getStateFromMeta(2));
		BLOCK_METAL.setHardness(5);
	}
}
