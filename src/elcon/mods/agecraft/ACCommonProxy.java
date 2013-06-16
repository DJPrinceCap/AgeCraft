package elcon.mods.agecraft;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IGuiHandler;
import elcon.mods.agecraft.core.tileentities.TileEntityAgeTeleporterChest;
import elcon.mods.agecraft.prehistory.gui.ContainerSharpener;
import elcon.mods.agecraft.prehistory.gui.InventorySharpener;

public class ACCommonProxy implements IGuiHandler {

	public void registerRenderInformation() {
		for(int i = 0; i < Age.ages.length; i++) {
			if(Age.ages[i] != null) {
				Age.ages[i].serverProxy();
			}
		}
		for(ACComponent component : AgeCraft.instance.components) {
			component.serverProxy();
		}
	}
	
	public void registerPlayerAPI() {
		//ServerPlayerAPI.register("AgeCraft", ACServerPlayerBase.class);
	}

	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		if(id == 0) {
			return new ContainerChest(player.inventory, (TileEntityAgeTeleporterChest) world.getBlockTileEntity(x, y, z));
		} else if(id == 10) {
			return new ContainerSharpener(player, new InventorySharpener());
		} 
		return null;
	}

	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		return null;
	}

	public MinecraftServer getMCServer() {
		return FMLCommonHandler.instance().getMinecraftServerInstance();
	}
}
