package com.lavaingot.minersdream.objects.commands;

import com.lavaingot.minersdream.init.BlockInit;
import com.lavaingot.minersdream.init.CommandInit;
import com.lavaingot.minersdream.init.ItemInit;
import com.lavaingot.minersdream.util.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.item.Item;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import scala.swing.TextComponent;

public class CommandModelUpdate extends CommandBase implements ICommand{

	private String name;
	private String usage;

	public CommandModelUpdate() {
		this.name = "modelUpdate";
		this.usage = "modelUpdate [block/item/entity]";
		
		CommandInit.COMMANDS.add(this);
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
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		
		updateModels(args[0], sender);
	}
	
	@Override
	public int getRequiredPermissionLevel() {
		
		return super.getRequiredPermissionLevel();
	}
	
	@SideOnly(Side.CLIENT)
	private void updateModels(String type, ICommandSender sender) {
		
		if (type.equals("block")) {
			
			for(Block block: BlockInit.BLOCKS) {
				
				if(block instanceof IHasModel) {
					((IHasModel)block).registerModels();
				}
			}
		} else if (type.equals("item")) {
//			RenderManager.this.entityRenderMap.put(arg0, arg1)
			for(Item item : ItemInit.ITEMS) {
				
				if(item instanceof IHasModel) {
					((IHasModel)item).registerModels();
				}
			}
		} else if (type.equals("entity")) {
			if (!sender.getEntityWorld().isRemote) {sender.sendMessage(new TextComponentString("�8Not implemented yet...")); }
		} else {
			if (!sender.getEntityWorld().isRemote) {sender.sendMessage(new TextComponentString("�4Valid arguments are either block, item or entity")); }
		}
	}

}
