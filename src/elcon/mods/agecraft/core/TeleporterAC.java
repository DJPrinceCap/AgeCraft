package elcon.mods.agecraft.core;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;
import elcon.mods.core.ElConCore;

public class TeleporterAC extends Teleporter {

	public WorldServer world;
	public int dimension = 0;
	public boolean chests = false;
	
	public TeleporterAC(WorldServer worldServer, int dim, boolean c) {
		super(worldServer);
		world = worldServer;
		dimension = dim;
		chests = c;
	}

	@Override
	public void placeInPortal(Entity entity, double xx, double yy, double zz, float par8) {
		ChunkCoordinates spawn = world.getSpawnPoint();
		int firstBlock = ElConCore.getFirstUncoveredBlock(world, MathHelper.floor_double(entity.posX), MathHelper.floor_double(entity.posZ));
		EntityPlayerMP player = null;
		if(entity instanceof EntityPlayerMP) {
			player = (EntityPlayerMP) entity;
			player.playerNetServerHandler.setPlayerLocation(MathHelper.floor_double(entity.posX) + 0.5D, MathHelper.floor_double(firstBlock + 3), MathHelper.floor_double(entity.posZ) + 0.5D, entity.rotationYaw, entity.rotationPitch);
		}
		int x = MathHelper.floor_double(entity.posX);
		int y = world.getSpawnPoint().posY + 1;
		//int y = MathHelper.floor_double(firstBlock) + 3;
		int z = MathHelper.floor_double(entity.posZ);
		
		
		if(player != null && chests) {
			AgeTeleport t = AgeTeleport.teleportList.get(player.username);
			if(t != null) {
				//TODO: add old source to AgeTeleport
				//t.placeChests(world, x - 2, y - 1, x - 2);
			}
			AgeTeleport.teleportList.remove(t);
		}
	}
}
