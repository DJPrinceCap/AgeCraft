package elcon.mods.agecraft;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.NetLoginHandler;
import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet1Login;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.common.network.IConnectionHandler;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import elcon.mods.agecraft.prehistory.tileentities.TileEntityCampfire;
import elcon.mods.agecraft.tech.TechTreeClient;
import elcon.mods.agecraft.tech.TechTreeServer;

public class ACPacketHandler implements IPacketHandler, IConnectionHandler {
	
	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
		ByteArrayDataInput dat = ByteStreams.newDataInput(packet.data);
		byte packetID = dat.readByte();
		
		World world = Minecraft.getMinecraft().theWorld;
		
		switch(packetID) {
		case 1:
			handleTechTreeComponentPacket(dat);
			break;
		case 2:
			handleAllTechTreeComponentsPacket(dat);
			break;
		case 100:
			handleTileEntityCampfire(world, dat);
			break;
		}
	}
	
	private void handleTechTreeComponentPacket(ByteArrayDataInput dat) {
		short size = dat.readShort();
		StringBuilder var3 = new StringBuilder();
		for(int var4 = 0; var4 < size; var4++) {
			var3.append(dat.readChar());
		}
		String key = var3.toString();
		boolean unlock = dat.readBoolean();

		if(unlock) {
			TechTreeClient.unlockComponent(key);
		} else {
			TechTreeClient.lockComponent(key);
		}
	}

	public static void sendTechTreeComponentPacket(String key, boolean unlock, EntityPlayerMP player) {
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			DataOutputStream dos = new DataOutputStream(bos);
			Packet250CustomPayload packet = new Packet250CustomPayload();
			dos.writeByte(1);
			dos.writeShort(key.length());
			dos.writeChars(key);
			dos.writeBoolean(unlock);
			dos.close();
			packet.channel = "ACTech";
			packet.data = bos.toByteArray();
			packet.length = bos.size();
			packet.isChunkDataPacket = false;
			player.playerNetServerHandler.sendPacketToPlayer(packet);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	private void handleAllTechTreeComponentsPacket(ByteArrayDataInput dat) {
		short size = dat.readShort();
		ArrayList<String> ll = new ArrayList<String>();
		for(int a = 0; a < size; a++) {
			short ss = dat.readShort();
			StringBuilder var3 = new StringBuilder();
			for(int var4 = 0; var4 < ss; var4++) {
				var3.append(dat.readChar());
			}
			ll.add(var3.toString());
		}
		for(String key : ll) {
			TechTreeClient.unlockComponentStartup(key);
		}
	}

	public static void sendAllTechTreeComponentsPacket(EntityPlayerMP player) {
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			DataOutputStream dos = new DataOutputStream(bos);
			Packet250CustomPayload packet = new Packet250CustomPayload();
			dos.writeByte(2);
			ArrayList<String> ll = TechTreeServer.unlockedTechComponents;
			if((ll != null) && (ll.size() > 0)) {
				dos.writeShort(ll.size());
				for(String s : ll) {
					if(s != null) {
						dos.writeShort(s.length());
						dos.writeChars(s);
					}
				}
				dos.close();
				packet.channel = "ACTech";
				packet.data = bos.toByteArray();
				packet.length = bos.size();
				packet.isChunkDataPacket = false;
				player.playerNetServerHandler.sendPacketToPlayer(packet);
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	private void handleTileEntityCampfire(World world, ByteArrayDataInput dat) {
		NBTTagCompound nbt = new NBTTagCompound();
		int x = dat.readInt();
		int y = dat.readInt();
		int z = dat.readInt();

		TileEntityCampfire tile = new TileEntityCampfire();
		tile.tick = dat.readInt();
		tile.timeLeft = dat.readInt();
		tile.fuel = dat.readInt();
		tile.hasFuel = dat.readBoolean();
		tile.isBurning = dat.readBoolean();
		tile.canBurn = dat.readBoolean();
		tile.logType = dat.readByte();
		
		tile.hasSpit = dat.readBoolean();
		tile.spitStage = dat.readByte();
		tile.spitDirection = dat.readByte();
		if(dat.readBoolean()) {
			tile.spitStack = new ItemStack(dat.readInt(), 1, dat.readInt());
		}
		tile.spitRotation = dat.readInt();
		tile.cookTime = dat.readInt();
		tile.cooked = dat.readBoolean();
		
		world.setBlockTileEntity(x, y, z, tile);
	}

	@Override
	//SERVER
	public void playerLoggedIn(Player player, NetHandler netHandler, INetworkManager manager) {
		try {
			sendAllTechTreeComponentsPacket((EntityPlayerMP) netHandler.getPlayer());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	//SERVER
	public String connectionReceived(NetLoginHandler netHandler, INetworkManager manager) {
		return null;
	}

	@Override
	//CLIENT
	public void connectionOpened(NetHandler netClientHandler, String server, int port, INetworkManager manager) {
	}

	@Override
	//CLIENT
	public void connectionOpened(NetHandler netClientHandler, MinecraftServer server, INetworkManager manager) {
	}

	@Override
	//BOTH
	public void connectionClosed(INetworkManager manager) {
	}

	@Override
	//CLIENT
	public void clientLoggedIn(NetHandler clientHandler, INetworkManager manager, Packet1Login login) {
	}
}
