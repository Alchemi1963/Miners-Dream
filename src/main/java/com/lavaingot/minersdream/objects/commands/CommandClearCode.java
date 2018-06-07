package com.lavaingot.minersdream.objects.commands;

import com.lavaingot.minersdream.objects.blocks.BlockContainer;
import com.lavaingot.minersdream.objects.tileentities.TileContainer;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentString;

public class CommandClearCode extends CommandBase {

	private String name;
	private String usage;
	
	public CommandClearCode() {
		this.name = "clearCode";
		this.usage = "clearCode {newCode}";
	}
	
	@Override
	public String getName() {

		return this.name;
	}

	@Override
	public String getUsage(ICommandSender sender) {

		return this.usage;
	}
	
	@Override
	public int getRequiredPermissionLevel() {
		
		return 1;
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {

		String[] newCode = new String[4];
		
		if (args.length == 1) {
			if (args[0].length() == 4) {
				
				for (int i = 0; i < 4;i++) {
					newCode[i] = args[0].substring(i, i+1);
					
				}
				
			} else {
				if (!sender.getEntityWorld().isRemote) {sender.sendMessage(new TextComponentString("§cThe new code should be 4 numbers long, clearing code instead.")); }
			}
		} else if (args.length > 1) {
			if (!sender.getEntityWorld().isRemote) {sender.sendMessage(new TextComponentString("§9I don't accept more than 1 argument.")); }
		}
		
		if (sender instanceof EntityPlayer) {
			if (Minecraft.getMinecraft().objectMouseOver != null && Minecraft.getMinecraft().objectMouseOver.typeOfHit == RayTraceResult.Type.BLOCK && Minecraft.getMinecraft().objectMouseOver.getBlockPos() != null) {
				
				if (sender.getEntityWorld().getBlockState(Minecraft.getMinecraft().objectMouseOver.getBlockPos()).getBlock() instanceof BlockContainer) {
					
					TileContainer te = (TileContainer) sender.getEntityWorld().getTileEntity(Minecraft.getMinecraft().objectMouseOver.getBlockPos());
					if (sender.getName() == te.owner || ((EntityPlayer) sender).canUseCommandBlock()) {
						if (args.length == 1 && args[0].length() == 4) {
							
							te.setCode(newCode);
							if (!sender.getEntityWorld().isRemote) {sender.sendMessage(new TextComponentString("§6Code is set to " + args[0] + ".")); }
						} else {
							
							te.clearCode();
							if (!sender.getEntityWorld().isRemote) {sender.sendMessage(new TextComponentString("§6Code is cleared.")); }
						}
					} else {
						if (!sender.getEntityWorld().isRemote) {sender.sendMessage(new TextComponentString("§4You are not the owner!")); }
					}
				} else {
					if (!sender.getEntityWorld().isRemote) {sender.sendMessage(new TextComponentString("§cSelected block is not a Container Block.")); }
				}
			} else {
				if (!sender.getEntityWorld().isRemote) {sender.sendMessage(new TextComponentString("§cYou are not looking at a block.")); }
			}
		} else {
			if (!sender.getEntityWorld().isRemote) {sender.sendMessage(new TextComponentString("§4Command is intended only for players!")); }
		}

	}

}
