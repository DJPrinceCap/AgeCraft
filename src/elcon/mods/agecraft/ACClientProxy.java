package elcon.mods.agecraft;

import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import elcon.mods.agecraft.core.tileentities.TileEntityAgeTeleporterChest;
import elcon.mods.agecraft.prehistory.gui.GuiSharpener;
import elcon.mods.agecraft.prehistory.gui.InventorySharpener;

public class ACClientProxy extends ACCommonProxy {

	@Override
	public void registerRenderInformation() {
		// register client tick handler
		TickRegistry.registerTickHandler(AgeCraft.instance.tickHandlerClient, Side.CLIENT);

		// register key handler
		KeyBindingRegistry.registerKeyBinding(new ACKeyHandler());

		for(int i = 0; i < Age.ages.length; i++) {
			if(Age.ages[i] != null) {
				Age.ages[i].clientProxy();
			}
		}
		for(ACComponent component : AgeCraft.instance.components) {
			component.clientProxy();
		}
	}

	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		if(id == 0) {
			return new GuiChest(player.inventory, (TileEntityAgeTeleporterChest) world.getBlockTileEntity(x, y, z));
		} else if(id == 10) {
			return new GuiSharpener(new InventorySharpener());
		}
		return null;
	}
}
