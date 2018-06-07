package com.lavaingot.minersdream.util.handlers;

import com.lavaingot.minersdream.objects.blocks.BlockContainer;
import com.lavaingot.minersdream.objects.tileentities.TileContainer;
import com.lavaingot.minersdream.util.Reference;
import com.lavaingot.minersdream.util.handlers.MinersdreamPacketHandler.CodeMessage.CodeMessageHandler;
import com.lavaingot.minersdream.util.handlers.MinersdreamPacketHandler.OpenMessage.OpenMessageHandler;
import com.lavaingot.minersdream.util.handlers.MinersdreamPacketHandler.OwnerMessage.OwnerMessageHandler;
import com.lavaingot.minersdream.util.handlers.MinersdreamPacketHandler.ShouldOpenMessage.ShouldOpenMessageHandler;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class MinersdreamPacketHandler{

	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID);
	
	private static int ID_SERVER = 0;
	private static int ID_CLIENT = 0;
	
	public static int nextIDS() {
		return ID_SERVER++;
	}
	
	public static int nextIDC() {
		return ID_CLIENT++;
	}
	
	public static void registerMessages() {
		INSTANCE.registerMessage(CodeMessageHandler.class, CodeMessage.class, nextIDS(), Side.SERVER);
		INSTANCE.registerMessage(OwnerMessageHandler.class, OwnerMessage.class, nextIDS(), Side.SERVER);
		INSTANCE.registerMessage(OpenMessageHandler.class, OpenMessage.class, nextIDS(), Side.SERVER);
		INSTANCE.registerMessage(ShouldOpenMessageHandler.class, ShouldOpenMessage.class, nextIDC(), Side.CLIENT);
	}
	
	public static class CodeMessage implements IMessage{

		public CodeMessage(){}
		
		private int code;
		private BlockPos pos;
		public CodeMessage(int code, BlockPos pos) {
			this.code = code;
			this.pos = pos;
		}
		
		@Override
		public void toBytes(ByteBuf buf) {

			buf.writeInt(code);
			buf.writeLong(pos.toLong());
			
		}

		@Override
		public void fromBytes(ByteBuf buf) {
			
			code = buf.readInt();
			pos = BlockPos.fromLong(buf.readLong());
			
		}
		
		public static class CodeMessageHandler implements IMessageHandler<CodeMessage, IMessage>{

			@Override
			public IMessage onMessage(CodeMessage message, MessageContext ctx) {
				
				EntityPlayerMP serverplayer = ctx.getServerHandler().player;
				
				int code = message.code;
				BlockPos pos = message.pos;
				
			
				serverplayer.getServerWorld().addScheduledTask(() -> {
					
					if (serverplayer.getServerWorld().isBlockLoaded(pos) && serverplayer.getServerWorld().getBlockState(pos).getBlock() instanceof BlockContainer) {
						TileContainer te = (TileContainer) serverplayer.getServerWorld().getTileEntity(pos);
						te.setCode(code);
					}
					
				});
				return null;
			}
			
		}
		
	}
	
	public static class OwnerMessage implements IMessage{
		public OwnerMessage() {}
		
		private String owner;
		private BlockPos pos;
		
		public OwnerMessage(String owner, BlockPos pos) {
			this.owner = owner;
			this.pos = pos;
		}
		
		@Override
		public void toBytes(ByteBuf buf) {

			
			ByteBufUtils.writeUTF8String(buf, owner);
			buf.writeLong(pos.toLong());
			
		}

		@Override
		public void fromBytes(ByteBuf buf) {
			
			owner = ByteBufUtils.readUTF8String(buf);
			pos = BlockPos.fromLong(buf.readLong());
			
		}
		
		public static class OwnerMessageHandler implements IMessageHandler<OwnerMessage, IMessage>{

			@Override
			public IMessage onMessage(OwnerMessage message, MessageContext ctx) {
				
				if (ctx.side == Side.CLIENT) {
					EntityPlayer player = ctx.getServerHandler().player;
					System.out.println(player);
				}
				
				EntityPlayerMP serverplayer = ctx.getServerHandler().player;
				
				String owner = message.owner;
				BlockPos pos = message.pos;
				
				
				
				serverplayer.getServerWorld().addScheduledTask(() ->{
					
					if (serverplayer.getServerWorld().isBlockLoaded(pos) && serverplayer.getServerWorld().getBlockState(pos).getBlock() instanceof BlockContainer) {
						TileContainer te = (TileContainer) serverplayer.getServerWorld().getTileEntity(pos);
						te.owner = owner;
					}
					
				});
				
				
				
				return null;
			}
			
		
			
		}
	}
	
	public static class OpenMessage implements IMessage{
		public OpenMessage() {}
		
		private boolean open;
		private BlockPos pos;
		
		public OpenMessage(boolean open, BlockPos pos) {
			this.open = open;
			this.pos = pos;
		}
		
		@Override
		public void toBytes(ByteBuf buf) {

			
			buf.writeBoolean(open);
			buf.writeLong(pos.toLong());
			
		}

		@Override
		public void fromBytes(ByteBuf buf) {
			
			open = buf.readBoolean();
			pos = BlockPos.fromLong(buf.readLong());
			
		}
		
		public static class OpenMessageHandler implements IMessageHandler<OpenMessage, IMessage>{

			@Override
			public IMessage onMessage(OpenMessage message, MessageContext ctx) {
				
				EntityPlayerMP serverplayer = ctx.getServerHandler().player;
				
				boolean open = message.open;
				BlockPos pos = message.pos;
				
				
				serverplayer.getServerWorld().addScheduledTask(() ->{
					
					if (serverplayer.getServerWorld().isBlockLoaded(pos) && serverplayer.getServerWorld().getBlockState(pos).getBlock() instanceof BlockContainer) {
						TileContainer te = (TileContainer) serverplayer.getServerWorld().getTileEntity(pos);
						te.setOpened(open);
					}
					
				});
				
				return null;
			}
			
		}
	}
	
	public static class ShouldOpenMessage implements IMessage{
		public ShouldOpenMessage() {}
		
		private boolean open;
		private BlockPos pos;
		
		public ShouldOpenMessage(boolean open, BlockPos pos) {
			this.open = open;
			this.pos = pos;
		}
		
		@Override
		public void toBytes(ByteBuf buf) {

			
			buf.writeBoolean(open);
			buf.writeLong(pos.toLong());
			
		}

		@Override
		public void fromBytes(ByteBuf buf) {
			
			open = buf.readBoolean();
			pos = BlockPos.fromLong(buf.readLong());
			
		}
		
		public static class ShouldOpenMessageHandler implements IMessageHandler<ShouldOpenMessage, IMessage>{

			@Override
			public IMessage onMessage(ShouldOpenMessage message, MessageContext ctx) {
				
				NetHandlerPlayClient net = ctx.getClientHandler();
				World world = FMLClientHandler.instance().getWorldClient();
				
				boolean open = message.open;
				BlockPos pos = message.pos;
				
				FMLCommonHandler.instance().getWorldThread(net).addScheduledTask(() -> {
					
					if (world.isBlockLoaded(pos)) {
						TileContainer te = (TileContainer)world.getTileEntity(pos);
						te.shouldOpen = open;
					}
					
				});
				
				
				return null;
			}
			
		}
	}
	
}
