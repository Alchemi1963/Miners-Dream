package com.lavaingot.minersdream.client.key;

import java.util.List;

import org.lwjgl.input.Keyboard;

import com.google.common.collect.ImmutableList;
import com.lavaingot.minersdream.util.Reference;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public final class KeyBindings {
	private static final String category = Reference.NAME;
	
	public static KeyBinding showTooltip;
	
	private static final List<KeyBinding> allBindings;
	
	static {
		allBindings = ImmutableList.of(
				showTooltip = new KeyBinding("key.minersdream.tooltip", KeyConflictContext.GUI, Keyboard.KEY_LSHIFT, category));
	}
	
	private KeyBindings() {}

	@SideOnly(Side.CLIENT)
	public static void init() {
		for (KeyBinding binding : allBindings) {
			ClientRegistry.registerKeyBinding(binding);
		}
	}
}
