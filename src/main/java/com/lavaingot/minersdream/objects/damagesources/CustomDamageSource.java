package com.lavaingot.minersdream.objects.damagesources;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;

public class CustomDamageSource extends DamageSource{

	public CustomDamageSource(String damageTypeIn) {
		
		super(damageTypeIn);
	}

	public static final DamageSource RADIATION = (new CustomDamageSource("radiation")).setDamageBypassesArmor();
	
}

