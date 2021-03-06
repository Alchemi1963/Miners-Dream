package com.lavaingot.minersdream.util;

import java.util.ArrayList;
import java.util.List;

import com.lavaingot.minersdream.init.BlockInit;
import com.lavaingot.minersdream.init.ItemInit;
import com.lavaingot.minersdream.init.PotionInit;
import com.lavaingot.minersdream.objects.tools.ToolMulti;

import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.client.util.ITooltipFlag.TooltipFlags;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerDestroyItemEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import scala.actors.threadpool.Arrays;

@EventBusSubscriber
public class Events {
	
	public static boolean CANCEL_DESTROY = false;
	
	public static Events instance;
	public static ArrayList<Item> specialItems = new ArrayList<Item>();
	public static ArrayList<Item> specialItemsComplement = new ArrayList<Item>();
	
	public static final ArrayList<PotionEffect> EFFECTS = new ArrayList<PotionEffect>();
	public static PotionEffect RADIATION = new PotionEffect(PotionInit.RADIATION);
	
	public static void init() {
	
		specialItems.add(ItemInit.AXE_URANIUM);
		specialItems.add(ItemInit.BOOTS_URANIUM);
		specialItems.add(ItemInit.CHESTPLATE_URANIUM);
		specialItems.add(ItemInit.HELMET_URANIUM);
		specialItems.add(ItemInit.HOE_URANIUM);
		specialItems.add(ItemInit.LEGGINGS_URANIUM);
		specialItems.add(ItemInit.PICKAXE_URANIUM);
		specialItems.add(ItemInit.SHOVEL_URANIUM);
		specialItems.add(ItemInit.SWORD_URANIUM);
		
		
		specialItemsComplement.add(ItemInit.AXE_BISMUTH);
		specialItemsComplement.add(ItemInit.BOOTS_BISMUTH);
		specialItemsComplement.add(ItemInit.CHESTPLATE_BISMUTH);
		specialItemsComplement.add(ItemInit.HELMET_BISMUTH);
		specialItemsComplement.add(ItemInit.HOE_BISMUTH);
		specialItemsComplement.add(ItemInit.LEGGINGS_BISMUTH);
		specialItemsComplement.add(ItemInit.PICKAXE_BISMUTH);
		specialItemsComplement.add(ItemInit.SHOVEL_BISMUTH);
		specialItemsComplement.add(ItemInit.SWORD_BISMUTH);
	}
			
	@SubscribeEvent
	public static void onItemDestroy(PlayerDestroyItemEvent event) {
		if (specialItems.contains(event.getOriginal().getItem()) && !CANCEL_DESTROY) {
			NBTTagList ench = event.getOriginal().getEnchantmentTagList();
			int index = specialItems.indexOf(event.getOriginal().getItem());
			ItemStack bismuth = new ItemStack(specialItemsComplement.get(index));
		
			for (int i = 0; i < ench.tagCount();i++) {
				int id = ench.getCompoundTagAt(i).getInteger("id");
				int lvl = ench.getCompoundTagAt(i).getInteger("lvl");
			
				bismuth.addEnchantment(Enchantment.getEnchantmentByID(id), lvl);
			}
			event.getEntityPlayer().addItemStackToInventory(bismuth);
		}
	}
	
	@SubscribeEvent
	public static void onPlayerInteraction(PlayerInteractEvent event) {
		
		if (event.getEntity().isEntityEqual(new EntityItemFrame(event.getWorld())) && specialItems.contains(event.getItemStack().getItem())) {
			CANCEL_DESTROY = true;
		}
	}
	
	@SubscribeEvent
	public static void onBlockInteraction(RightClickBlock event) {
				
		if (!event.getEntityPlayer().world.isRemote){ItemInit.THE_DREAM.onItemUse(event.getEntityPlayer(), event.getWorld(), event.getPos(), event.getEntityPlayer().getActiveHand(), event.getEntityPlayer().getHorizontalFacing(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ());}
	}
	
	@SubscribeEvent
	public static void onPlayerTick(PlayerTickEvent event) {
				
		EntityPlayer player = event.player;
		
		int amplifier = -1;
		
		for (ItemStack items : player.inventory.mainInventory) {
			if (specialItems.contains(items.getItem())) {
				amplifier += items.getCount();
			}
		}		
		
		
		for (ItemStack items : player.inventory.armorInventory) {
			if (specialItems.contains(items.getItem())) {
				amplifier ++;
			}
		}
		if (amplifier > -1) { RADIATION = new PotionEffect(PotionInit.RADIATION, 160, amplifier); player.addPotionEffect(RADIATION); EFFECTS.add(RADIATION);}
		
		if (player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem().equals(ItemInit.HELMET_URANIUM) && 
				player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem().equals(ItemInit.CHESTPLATE_URANIUM) && 
				player.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem().equals(ItemInit.LEGGINGS_URANIUM) && 
				player.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem().equals(ItemInit.BOOTS_URANIUM) ) {
			if (!player.isGlowing()) {player.setGlowing(true);}
			
			if (!player.capabilities.allowFlying && !player.isCreative()) { 
				if (!player.world.isRemote) {player.sendMessage(new TextComponentString("�2You have the ability to fly now!")); }
				player.capabilities.allowFlying = true;
				
			}
		}
		else {
			if (player.isGlowing()) {player.setGlowing(false);}
			
			if (player.capabilities.allowFlying && !player.isCreative()) { 
				if (!player.world.isRemote) {player.sendMessage(new TextComponentString("�4You cannot fly anymore...")); }
				player.capabilities.allowFlying = false;
				
				if (player.world.getHeight((int)player.posX, (int)player.posZ) != player.posY) {player.attemptTeleport(player.posX, player.world.getHeight((int)player.posX, (int)player.posZ), player.posZ);}
				player.setVelocity(0D, -1D, 0D);
				
			}
		}
		
		for (PotionEffect effect : player.getActivePotionEffects()) { effect.performEffect(player); }
	}
}

