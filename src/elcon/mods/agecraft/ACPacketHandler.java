package elcon.mods.agecraft;

import net.minecraft.client.Minecraft;
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
import cpw.mods.fml.common.network.Player;
import elcon.mods.agecraft.prehistory.tileentities.TileEntityCampfire;

public class ACPacketHandler implements IPacketHandler, IConnectionHandler {
	
	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
		ByteArrayDataInput dat = ByteStreams.newDataInput(packet.data);
		byte packetID = dat.readByte();
		
		World world = Minecraft.getMinecraft().theWorld;
		
		switch(packetID) {
		case 100:
			handleTileEntityCampfire(world, dat);
			break;
		}
	}

	private void handleTileEntityCampfire(World world, ByteArrayDataInput dat) {
		NBTTagCompound nbt = new NBTTagCompound();
		int x = dat.readInt();
		int y = dat.readInt();
		int z = dat.readInt();

		TileEntityCampfire te = new TileEntityCampfire();
		te.tick = dat.readInt();
		te.timeLeft = dat.readInt();
		te.fuel = dat.readInt();
		te.hasFuel = dat.readBoolean();
		te.isBurning = dat.readBoolean();
		te.canBurn = dat.readBoolean();
		
		world.setBlockTileEntity(x, y, z, te);
	}

	@Override
	//SERVER
	public void playerLoggedIn(Player player, NetHandler netHandler, INetworkManager manager) {
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
