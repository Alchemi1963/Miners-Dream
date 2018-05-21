package com.lavaingot.minersdream.world.gen;

import java.util.Random;

import com.lavaingot.minersdream.init.BlockInit;
import com.lavaingot.minersdream.objects.variants.metals.MetalOres;
import com.lavaingot.minersdream.util.handlers.EnumHandler.MetalOne;

import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGenCustomOres implements IWorldGenerator{

	private WorldGenerator ore_nether_aluminium, ore_overworld_aluminium, ore_end_aluminium;
	private WorldGenerator ore_nether_bismuth, ore_overworld_bismuth, ore_end_bismuth;
	private WorldGenerator ore_nether_cadmium, ore_overworld_cadmium, ore_end_cadmium;
	private WorldGenerator ore_nether_calcium, ore_overworld_calcium, ore_end_calcium;
	private WorldGenerator ore_nether_cobalt, ore_overworld_cobalt, ore_end_cobalt;
	private WorldGenerator ore_nether_copper, ore_overworld_copper, ore_end_copper;
	private WorldGenerator ore_nether_mercury, ore_overworld_mercury, ore_end_mercury;
	private WorldGenerator ore_nether_nickel, ore_overworld_nickel, ore_end_nickel;
	private WorldGenerator ore_nether_platinum, ore_overworld_platinum, ore_end_platinum;
	private WorldGenerator ore_nether_potassium, ore_overworld_potassium, ore_end_potassium;
	private WorldGenerator ore_nether_silver, ore_overworld_silver, ore_end_silver;
	private WorldGenerator ore_nether_sodium, ore_overworld_sodium, ore_end_sodium;
	private WorldGenerator ore_nether_tin, ore_overworld_tin, ore_end_tin;
	private WorldGenerator ore_nether_tungsten, ore_overworld_tungsten, ore_end_tungsten;
	private WorldGenerator ore_nether_uranium, ore_overworld_uranium, ore_end_uranium;
	private WorldGenerator ore_nether_zinc, ore_overworld_zinc, ore_end_zinc;

	
	public WorldGenCustomOres() {
		
		ore_end_aluminium = new WorldGenMinable(BlockInit.ORE_END.getDefaultState().withProperty(MetalOres.VARIANT, MetalOne.ALUMINIUM), 5, BlockMatcher.forBlock(Blocks.END_STONE));
		ore_end_bismuth = new WorldGenMinable(BlockInit.ORE_END.getDefaultState().withProperty(MetalOres.VARIANT, MetalOne.BISMUTH), 5, BlockMatcher.forBlock(Blocks.END_STONE));
		ore_end_cadmium = new WorldGenMinable(BlockInit.ORE_END.getDefaultState().withProperty(MetalOres.VARIANT, MetalOne.CADMIUM), 5, BlockMatcher.forBlock(Blocks.END_STONE));
		ore_end_calcium = new WorldGenMinable(BlockInit.ORE_END.getDefaultState().withProperty(MetalOres.VARIANT, MetalOne.CALCIUM), 5, BlockMatcher.forBlock(Blocks.END_STONE));
		ore_end_cobalt = new WorldGenMinable(BlockInit.ORE_END.getDefaultState().withProperty(MetalOres.VARIANT, MetalOne.COBALT), 5, BlockMatcher.forBlock(Blocks.END_STONE));
		ore_end_copper = new WorldGenMinable(BlockInit.ORE_END.getDefaultState().withProperty(MetalOres.VARIANT, MetalOne.COPPER), 5, BlockMatcher.forBlock(Blocks.END_STONE));
		ore_end_mercury = new WorldGenMinable(BlockInit.ORE_END.getDefaultState().withProperty(MetalOres.VARIANT, MetalOne.MERCURY), 5, BlockMatcher.forBlock(Blocks.END_STONE));
		ore_end_nickel = new WorldGenMinable(BlockInit.ORE_END.getDefaultState().withProperty(MetalOres.VARIANT, MetalOne.NICKEL), 5, BlockMatcher.forBlock(Blocks.END_STONE));
		ore_end_platinum = new WorldGenMinable(BlockInit.ORE_END.getDefaultState().withProperty(MetalOres.VARIANT, MetalOne.PLATINUM), 5, BlockMatcher.forBlock(Blocks.END_STONE));
		ore_end_potassium = new WorldGenMinable(BlockInit.ORE_END.getDefaultState().withProperty(MetalOres.VARIANT, MetalOne.POTASSIUM), 5, BlockMatcher.forBlock(Blocks.END_STONE));
		ore_end_silver = new WorldGenMinable(BlockInit.ORE_END.getDefaultState().withProperty(MetalOres.VARIANT, MetalOne.SILVER), 5, BlockMatcher.forBlock(Blocks.END_STONE));
		ore_end_sodium = new WorldGenMinable(BlockInit.ORE_END.getDefaultState().withProperty(MetalOres.VARIANT, MetalOne.SODIUM), 5, BlockMatcher.forBlock(Blocks.END_STONE));
		ore_end_tin = new WorldGenMinable(BlockInit.ORE_END.getDefaultState().withProperty(MetalOres.VARIANT, MetalOne.TIN), 5, BlockMatcher.forBlock(Blocks.END_STONE));
		ore_end_tungsten = new WorldGenMinable(BlockInit.ORE_END.getDefaultState().withProperty(MetalOres.VARIANT, MetalOne.TUNGSTEN), 5, BlockMatcher.forBlock(Blocks.END_STONE));
		ore_end_uranium = new WorldGenMinable(BlockInit.ORE_END.getDefaultState().withProperty(MetalOres.VARIANT, MetalOne.URANIUM), 5, BlockMatcher.forBlock(Blocks.END_STONE));
		ore_end_zinc = new WorldGenMinable(BlockInit.ORE_END.getDefaultState().withProperty(MetalOres.VARIANT, MetalOne.ZINC), 5, BlockMatcher.forBlock(Blocks.END_STONE));
		ore_nether_aluminium = new WorldGenMinable(BlockInit.ORE_NETHER.getDefaultState().withProperty(MetalOres.VARIANT, MetalOne.ALUMINIUM), 5, BlockMatcher.forBlock(Blocks.NETHERRACK));
		ore_nether_bismuth = new WorldGenMinable(BlockInit.ORE_NETHER.getDefaultState().withProperty(MetalOres.VARIANT, MetalOne.BISMUTH), 5, BlockMatcher.forBlock(Blocks.NETHERRACK));
		ore_nether_cadmium = new WorldGenMinable(BlockInit.ORE_NETHER.getDefaultState().withProperty(MetalOres.VARIANT, MetalOne.CADMIUM), 5, BlockMatcher.forBlock(Blocks.NETHERRACK));
		ore_nether_calcium = new WorldGenMinable(BlockInit.ORE_NETHER.getDefaultState().withProperty(MetalOres.VARIANT, MetalOne.CALCIUM), 5, BlockMatcher.forBlock(Blocks.NETHERRACK));
		ore_nether_cobalt = new WorldGenMinable(BlockInit.ORE_NETHER.getDefaultState().withProperty(MetalOres.VARIANT, MetalOne.COBALT), 5, BlockMatcher.forBlock(Blocks.NETHERRACK));
		ore_nether_copper = new WorldGenMinable(BlockInit.ORE_NETHER.getDefaultState().withProperty(MetalOres.VARIANT, MetalOne.COPPER), 5, BlockMatcher.forBlock(Blocks.NETHERRACK));
		ore_nether_mercury = new WorldGenMinable(BlockInit.ORE_NETHER.getDefaultState().withProperty(MetalOres.VARIANT, MetalOne.MERCURY), 5, BlockMatcher.forBlock(Blocks.NETHERRACK));
		ore_nether_nickel = new WorldGenMinable(BlockInit.ORE_NETHER.getDefaultState().withProperty(MetalOres.VARIANT, MetalOne.NICKEL), 5, BlockMatcher.forBlock(Blocks.NETHERRACK));
		ore_nether_platinum = new WorldGenMinable(BlockInit.ORE_NETHER.getDefaultState().withProperty(MetalOres.VARIANT, MetalOne.PLATINUM), 5, BlockMatcher.forBlock(Blocks.NETHERRACK));
		ore_nether_potassium = new WorldGenMinable(BlockInit.ORE_NETHER.getDefaultState().withProperty(MetalOres.VARIANT, MetalOne.POTASSIUM), 5, BlockMatcher.forBlock(Blocks.NETHERRACK));
		ore_nether_silver = new WorldGenMinable(BlockInit.ORE_NETHER.getDefaultState().withProperty(MetalOres.VARIANT, MetalOne.SILVER), 5, BlockMatcher.forBlock(Blocks.NETHERRACK));
		ore_nether_sodium = new WorldGenMinable(BlockInit.ORE_NETHER.getDefaultState().withProperty(MetalOres.VARIANT, MetalOne.SODIUM), 5, BlockMatcher.forBlock(Blocks.NETHERRACK));
		ore_nether_tin = new WorldGenMinable(BlockInit.ORE_NETHER.getDefaultState().withProperty(MetalOres.VARIANT, MetalOne.TIN), 5, BlockMatcher.forBlock(Blocks.NETHERRACK));
		ore_nether_tungsten = new WorldGenMinable(BlockInit.ORE_NETHER.getDefaultState().withProperty(MetalOres.VARIANT, MetalOne.TUNGSTEN), 5, BlockMatcher.forBlock(Blocks.NETHERRACK));
		ore_nether_uranium = new WorldGenMinable(BlockInit.ORE_NETHER.getDefaultState().withProperty(MetalOres.VARIANT, MetalOne.URANIUM), 5, BlockMatcher.forBlock(Blocks.NETHERRACK));
		ore_nether_zinc = new WorldGenMinable(BlockInit.ORE_NETHER.getDefaultState().withProperty(MetalOres.VARIANT, MetalOne.ZINC), 5, BlockMatcher.forBlock(Blocks.NETHERRACK));
		ore_overworld_aluminium = new WorldGenMinable(BlockInit.ORE_OVERWORLD.getDefaultState().withProperty(MetalOres.VARIANT, MetalOne.ALUMINIUM), 5, BlockMatcher.forBlock(Blocks.STONE));
		ore_overworld_bismuth = new WorldGenMinable(BlockInit.ORE_OVERWORLD.getDefaultState().withProperty(MetalOres.VARIANT, MetalOne.BISMUTH), 5, BlockMatcher.forBlock(Blocks.STONE));
		ore_overworld_cadmium = new WorldGenMinable(BlockInit.ORE_OVERWORLD.getDefaultState().withProperty(MetalOres.VARIANT, MetalOne.CADMIUM), 5, BlockMatcher.forBlock(Blocks.STONE));
		ore_overworld_calcium = new WorldGenMinable(BlockInit.ORE_OVERWORLD.getDefaultState().withProperty(MetalOres.VARIANT, MetalOne.CALCIUM), 5, BlockMatcher.forBlock(Blocks.STONE));
		ore_overworld_cobalt = new WorldGenMinable(BlockInit.ORE_OVERWORLD.getDefaultState().withProperty(MetalOres.VARIANT, MetalOne.COBALT), 5, BlockMatcher.forBlock(Blocks.STONE));
		ore_overworld_copper = new WorldGenMinable(BlockInit.ORE_OVERWORLD.getDefaultState().withProperty(MetalOres.VARIANT, MetalOne.COPPER), 5, BlockMatcher.forBlock(Blocks.STONE));
		ore_overworld_mercury = new WorldGenMinable(BlockInit.ORE_OVERWORLD.getDefaultState().withProperty(MetalOres.VARIANT, MetalOne.MERCURY), 5, BlockMatcher.forBlock(Blocks.STONE));
		ore_overworld_nickel = new WorldGenMinable(BlockInit.ORE_OVERWORLD.getDefaultState().withProperty(MetalOres.VARIANT, MetalOne.NICKEL), 5, BlockMatcher.forBlock(Blocks.STONE));
		ore_overworld_platinum = new WorldGenMinable(BlockInit.ORE_OVERWORLD.getDefaultState().withProperty(MetalOres.VARIANT, MetalOne.PLATINUM), 5, BlockMatcher.forBlock(Blocks.STONE));
		ore_overworld_potassium = new WorldGenMinable(BlockInit.ORE_OVERWORLD.getDefaultState().withProperty(MetalOres.VARIANT, MetalOne.POTASSIUM), 5, BlockMatcher.forBlock(Blocks.STONE));
		ore_overworld_silver = new WorldGenMinable(BlockInit.ORE_OVERWORLD.getDefaultState().withProperty(MetalOres.VARIANT, MetalOne.SILVER), 5, BlockMatcher.forBlock(Blocks.STONE));
		ore_overworld_sodium = new WorldGenMinable(BlockInit.ORE_OVERWORLD.getDefaultState().withProperty(MetalOres.VARIANT, MetalOne.SODIUM), 5, BlockMatcher.forBlock(Blocks.STONE));
		ore_overworld_tin = new WorldGenMinable(BlockInit.ORE_OVERWORLD.getDefaultState().withProperty(MetalOres.VARIANT, MetalOne.TIN), 5, BlockMatcher.forBlock(Blocks.STONE));
		ore_overworld_tungsten = new WorldGenMinable(BlockInit.ORE_OVERWORLD.getDefaultState().withProperty(MetalOres.VARIANT, MetalOne.TUNGSTEN), 5, BlockMatcher.forBlock(Blocks.STONE));
		ore_overworld_uranium = new WorldGenMinable(BlockInit.ORE_OVERWORLD.getDefaultState().withProperty(MetalOres.VARIANT, MetalOne.URANIUM), 5, BlockMatcher.forBlock(Blocks.STONE));
		ore_overworld_zinc = new WorldGenMinable(BlockInit.ORE_OVERWORLD.getDefaultState().withProperty(MetalOres.VARIANT, MetalOne.ZINC), 5, BlockMatcher.forBlock(Blocks.STONE));



	}
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		
		switch(world.provider.getDimension()) {
		
		case - 1:
			
			runGenerator(ore_nether_aluminium, world, random, chunkX, chunkZ, 35, 0, 256);
			runGenerator(ore_nether_bismuth, world, random, chunkX, chunkZ, 15, 0, 256);
			runGenerator(ore_nether_cadmium, world, random, chunkX, chunkZ, 20, 0, 256);
			runGenerator(ore_nether_calcium, world, random, chunkX, chunkZ, 50, 0, 256);
			runGenerator(ore_nether_cobalt, world, random, chunkX, chunkZ, 5, 0, 256);
			runGenerator(ore_nether_copper, world, random, chunkX, chunkZ, 40, 0, 256);
			runGenerator(ore_nether_mercury, world, random, chunkX, chunkZ, 10, 0, 256);
			runGenerator(ore_nether_nickel, world, random, chunkX, chunkZ, 60, 0, 256);
			runGenerator(ore_nether_platinum, world, random, chunkX, chunkZ, 2, 0, 256);
			runGenerator(ore_nether_potassium, world, random, chunkX, chunkZ, 75, 0, 256);
			runGenerator(ore_nether_silver, world, random, chunkX, chunkZ, 10, 0, 256);
			runGenerator(ore_nether_sodium, world, random, chunkX, chunkZ, 45, 0, 256);
			runGenerator(ore_nether_tin, world, random, chunkX, chunkZ, 10, 0, 256);
			runGenerator(ore_nether_tungsten, world, random, chunkX, chunkZ, 25, 0, 256);
			runGenerator(ore_nether_uranium, world, random, chunkX, chunkZ, 4, 0, 256);
			runGenerator(ore_nether_zinc, world, random, chunkX, chunkZ, 44, 0, 256);
			
			break;
			
		case 0:
			
			runGenerator(ore_overworld_aluminium, world, random, chunkX, chunkZ, 35, 0, 70);
			runGenerator(ore_overworld_bismuth, world, random, chunkX, chunkZ, 15, 0, 50);
			runGenerator(ore_overworld_cadmium, world, random, chunkX, chunkZ, 20, 0, 62);
			runGenerator(ore_overworld_calcium, world, random, chunkX, chunkZ, 50, 0, 256);
			runGenerator(ore_overworld_cobalt, world, random, chunkX, chunkZ, 5, 0, 20);
			runGenerator(ore_overworld_copper, world, random, chunkX, chunkZ, 40, 0, 90);
			runGenerator(ore_overworld_mercury, world, random, chunkX, chunkZ, 10, 0, 30);
			runGenerator(ore_overworld_nickel, world, random, chunkX, chunkZ, 60, 0, 100);
			runGenerator(ore_overworld_platinum, world, random, chunkX, chunkZ, 2, 0, 10);
			runGenerator(ore_overworld_potassium, world, random, chunkX, chunkZ, 75, 0, 60);
			runGenerator(ore_overworld_silver, world, random, chunkX, chunkZ, 10, 0, 42);
			runGenerator(ore_overworld_sodium, world, random, chunkX, chunkZ, 45, 0, 65);
			runGenerator(ore_overworld_tin, world, random, chunkX, chunkZ, 10, 0, 38);
			runGenerator(ore_overworld_tungsten, world, random, chunkX, chunkZ, 25, 0, 40);
			runGenerator(ore_overworld_uranium, world, random, chunkX, chunkZ, 2, 0, 15);
			runGenerator(ore_overworld_zinc, world, random, chunkX, chunkZ, 25, 0, 60);
			
			break;
			
		case 1:
			
			runGenerator(ore_end_aluminium, world, random, chunkX, chunkZ, 35, 0, 70);
			runGenerator(ore_end_bismuth, world, random, chunkX, chunkZ, 15, 0, 50);
			runGenerator(ore_end_cadmium, world, random, chunkX, chunkZ, 20, 0, 62);
			runGenerator(ore_end_calcium, world, random, chunkX, chunkZ, 50, 0, 256);
			runGenerator(ore_end_cobalt, world, random, chunkX, chunkZ, 5, 0, 20);
			runGenerator(ore_end_copper, world, random, chunkX, chunkZ, 40, 0, 90);
			runGenerator(ore_end_mercury, world, random, chunkX, chunkZ, 10, 0, 30);
			runGenerator(ore_end_nickel, world, random, chunkX, chunkZ, 60, 0, 100);
			runGenerator(ore_end_platinum, world, random, chunkX, chunkZ, 2, 0, 10);
			runGenerator(ore_end_potassium, world, random, chunkX, chunkZ, 75, 0, 60);
			runGenerator(ore_end_silver, world, random, chunkX, chunkZ, 10, 0, 42);
			runGenerator(ore_end_sodium, world, random, chunkX, chunkZ, 45, 0, 65);
			runGenerator(ore_end_tin, world, random, chunkX, chunkZ, 10, 0, 38);
			runGenerator(ore_end_tungsten, world, random, chunkX, chunkZ, 25, 0, 40);
			runGenerator(ore_end_uranium, world, random, chunkX, chunkZ, 4, 0, 15);
			runGenerator(ore_end_zinc, world, random, chunkX, chunkZ, 44, 0, 60);
			
			break;
		}
	}
	
	private void runGenerator(WorldGenerator gen, World world, Random rand, int chunkX, int chunkZ, int chance, int minHeight, int maxHeight) {
		
		if(minHeight > maxHeight) throw new IllegalArgumentException("The minimum height cannot be higher than the maximum height.");
		if(minHeight < 0) throw new IllegalArgumentException("The minimum height cannot be below zero.");
		if(maxHeight > 256) throw new IllegalArgumentException("The maximum height cannot exceed the sky limit.");
		
		int heightDiff = maxHeight - minHeight + 1;
		
		for(int i = 0; i < chance; i++) {
			
			int x = chunkX * 16 + rand.nextInt(16); 
			int y = minHeight + rand.nextInt(heightDiff);
			int z = chunkZ * 16 + rand.nextInt(16); 
			
			gen.generate(world, rand, new BlockPos(x, y, z));
		}
	}
}
