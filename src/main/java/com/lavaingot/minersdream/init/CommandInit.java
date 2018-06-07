package com.lavaingot.minersdream.init;

import java.util.ArrayList;
import java.util.List;

import com.lavaingot.minersdream.objects.commands.CommandModelUpdate;

import net.minecraft.block.Block;
import net.minecraft.command.ICommand;

public class CommandInit {
	public static final List<ICommand> COMMANDS =  new ArrayList<ICommand>();
	
	public static final ICommand MODEL_UPDATE = new CommandModelUpdate();
}
