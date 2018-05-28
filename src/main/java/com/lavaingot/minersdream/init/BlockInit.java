package com.lavaingot.minersdream.init;

import java.util.ArrayList;
import java.util.List;

import com.lavaingot.minersdream.Main;
import com.lavaingot.minersdream.objects.blocks.BlockContainer;
import com.lavaingot.minersdream.objects.blocks.BlockSupertorch;
import com.lavaingot.minersdream.objects.blocks.BlockTorchHandler;
import com.lavaingot.minersdream.objects.blocks.machines.alloyer.BlockAlloyingFurnace;
import com.lavaingot.minersdream.objects.blocks.machines.mechanical_combiner.BlockMechanicalCombiner;
import com.lavaingot.minersdream.objects.variants.alloys.BaseAlloyBlocks;
import com.lavaingot.minersdream.objects.variants.metals.MetalBlocks;
import com.lavaingot.minersdream.objects.variants.metals.MetalOres;
import com.lavaingot.minersdream.util.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockInit {
	
	public static final List<Block> BLOCKS =  new ArrayList<Block>();
	
	public static final Block ORE_END = new MetalOres(Reference.END_ORE, "end", Main.metalTab);
	public static final Block ORE_NETHER = new MetalOres(Reference.NETHER_ORE, "nether", Main.metalTab);
	public static final Block ORE_OVERWORLD = new MetalOres(Reference.OVERWORLD_ORE, "overworld", Main.metalTab);
	
	public static final Block BLOCK_METAL = new MetalBlocks(Reference.BLOCK_METAL, Main.metalTab);
	public static final Block BLOCK_ALLOY = new BaseAlloyBlocks(Reference.BLOCK_ALLOY, Main.alloyTab);
	
	public static Block BLOCK_SUPERTORCH = new BlockSupertorch(Reference.BLOCK_SUPERTORCH, Material.WOOD, Main.miscTab);
	public static Block BLOCK_TORCHHANDLER = new BlockTorchHandler(Reference.BLOCK_TORCHHANDLER, Material.AIR, Main.miscTab);
	public static Block BLOCK_CONTAINER = new BlockContainer(Reference.BLOCK_CONTAINER, Main.miscTab);
	public static Block ALLOYING_FURNACE = new BlockAlloyingFurnace(Reference.ALLOYING_FURNACE, Main.machineTab);
	public static Block MECHANICAL_COMBINER = new BlockMechanicalCombiner(Reference.MECH_COMBINER, Main.machineTab);
	
	public static void setBlockProperties() {
		
		BLOCK_TORCHHANDLER.setBlockUnbreakable();
		BLOCK_TORCHHANDLER = BLOCK_TORCHHANDLER.setLightLevel(1.0F);
		BLOCK_TORCHHANDLER = BLOCK_TORCHHANDLER.setLightOpacity(0);
		BLOCK_TORCHHANDLER = BLOCK_TORCHHANDLER.setResistance(1000000000);
		
		
		ORE_END.setHardness(25);
		ORE_END.setHarvestLevel("pickaxe", 3);
		
		ORE_NETHER.setHarvestLevel("pickaxe", 2);
		ORE_NETHER.setHarvestLevel("pickaxe", 3, ((MetalOres)ORE_NETHER).getStateFromMeta(12));
		ORE_NETHER.setHardness(10);
		
		ORE_OVERWORLD.setHarvestLevel("pickaxe", 1);
		ORE_OVERWORLD.setHarvestLevel("pickaxe", 3, ((MetalOres)ORE_OVERWORLD).getStateFromMeta(12));
		ORE_OVERWORLD.setHarvestLevel("pickaxe", 3, ((MetalOres)ORE_OVERWORLD).getStateFromMeta(2));
		ORE_OVERWORLD.setHardness(5);
		
		BLOCK_METAL.setHarvestLevel("pickaxe", 2);
		BLOCK_METAL.setHarvestLevel("pickaxe", 3, ((MetalBlocks)BLOCK_METAL).getStateFromMeta(12));
		BLOCK_METAL.setHarvestLevel("pickaxe", 3, ((MetalBlocks)BLOCK_METAL).getStateFromMeta(2));
		BLOCK_METAL.setHardness(5);
	}
}
