package com.lavaingot.minersdream.util.handlers;

import net.minecraft.util.IStringSerializable;

public class EnumHandler {

	public static enum OreType implements IStringSerializable{
		
		COPPER(0, "copper"),
		ALUMINIUM(1, "aluminium"),
		BISMUTH(2, "bismuth"),
		CADMIUM(3, "cadmium"),
		CALCIUM(4, "calcium"),
		POTASSIUM(5, "potassium"),
		COBALT(6, "cobalt"),
		MERCURY(7, "mercury"),
		SODIUM(8, "sodium"),
		NICKEL(9, "nickel"),
		PLATINUM(10, "platinum"),
		TIN(11, "tin"),
		URANIUM(12, "uranium"),
		TUNGSTEN(13, "tungsten"),
		SILVER(14, "silver"),
		ZINC(15, "zinc");
		
		private static final OreType[] META_LOOKUP = new OreType[values().length];
		private final int meta;
		private final String name, uName, dimension;
		
		private OreType(int meta, String name) {
			
			this(meta, name, name);
		}
		
		private OreType(int meta, String name, String uName) {
			
			this.meta = meta;
			this.name = name;
			this.uName = uName;
			this.dimension = name.replaceAll("ore_", "");
			
		}
		
		@Override
		public String getName() {
			
			return this.name;
		}
		
		public int getMeta() {
			return this.meta;
		}
		
		public String getuName() {
			return this.uName;
		}
		
		public String getDimension() {
			return dimension;
		}
		
		@Override
		public String toString() {
			
			return this.name;
		}
		
		public static OreType byMetaData(int meta) {
			return META_LOOKUP[meta];
		}
		
		static {
			for(OreType oretype : values()) {
				META_LOOKUP[oretype.getMeta()] = oretype;
			}
		}
	}
	
	public static enum BlockType implements IStringSerializable{
		
		COPPER(0, "copper"),
		ALUMINIUM(1, "aluminium"),
		BISMUTH(2, "bismuth"),
		CADMIUM(3, "cadmium"),
		POTASSIUM(4, "potassium"),
		COBALT(5, "cobalt"),
		NICKEL(6, "nickel"),
		PLATINUM(7, "platinum"),
		TIN(8, "tin"),
		URANIUM(9, "uranium"),
		TUNGSTEN(10, "tungsten"),
		SILVER(11, "silver"),
		ZINC(12, "zinc");
		
		private static final BlockType[] META_LOOKUP = new BlockType[values().length];
		private final int meta;
		private final String name, uName, dimension;
		
		private BlockType(int meta, String name) {
			
			this(meta, name, name);
		}
		
		private BlockType(int meta, String name, String uName) {
			
			this.meta = meta;
			this.name = name;
			this.uName = uName;
			this.dimension = name.replaceAll("ore_", "");
			
		}
		
		@Override
		public String getName() {
			
			return this.name;
		}
		
		public int getMeta() {
			return this.meta;
		}
		
		public String getuName() {
			return this.uName;
		}
		
		public String getDimension() {
			return dimension;
		}
		
		@Override
		public String toString() {
			
			return this.name;
		}
		
		public static BlockType byMetaData(int meta) {
			return META_LOOKUP[meta];
		}
		
		static {
			for(BlockType blocktype : values()) {
				META_LOOKUP[blocktype.getMeta()] = blocktype;
			}
		}
	}


	public static enum AlloyType implements IStringSerializable{
		
		BRONZE(0, "bronze"),
		CONSTANTAN(1, "constantan"),
		DURAL(2, "dural"),
		INVAR(3, "invar"),
		MANGANIN(4, "manganin"),
		BRASS(5, "brass"),
		ALPACCA(6, "alpacca"),
		STEEL(7, "carbon_steel"),
		RVS(8, "stainless_steel");
		
		private static final AlloyType[] META_LOOKUP = new AlloyType[values().length];
		private final int meta;
		private final String name, uName, dimension;
		
		private AlloyType(int meta, String name) {
			
			this(meta, name, name);
		}
		
		private AlloyType(int meta, String name, String uName) {
			
			this.meta = meta;
			this.name = name;
			this.uName = uName;
			this.dimension = name.replaceAll("ore_", "");
			
		}
		
		@Override
		public String getName() {
			
			return this.name;
		}
		
		public int getMeta() {
			return this.meta;
		}
		
		public String getuName() {
			return this.uName;
		}
		
		public String getDimension() {
			return dimension;
		}
		
		@Override
		public String toString() {
			
			return this.name;
		}
		
		public static AlloyType byMetaData(int meta) {
			return META_LOOKUP[meta];
		}
		
		static {
			for(AlloyType AlloyType : values()) {
				META_LOOKUP[AlloyType.getMeta()] = AlloyType;
			}
		}
	}
}
