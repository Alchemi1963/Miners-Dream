package com.lavaingot.minersdream.init;

import java.util.ArrayList;
import java.util.List;

import com.lavaingot.minersdream.objects.effects.PotionEffects;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class PotionInit {
	public static final List<Potion> POTIONS =  new ArrayList<Potion>();
	
	public static final Potion RADIATION = new PotionEffects(true, 65280, "effect_radiation");
}
