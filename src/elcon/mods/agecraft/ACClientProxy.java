package elcon.mods.agecraft;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import elcon.mods.agecraft.prehistory.gui.GuiSharpener;
import elcon.mods.agecraft.prehistory.gui.InventorySharpener;

public class ACClientProxy extends ACCommonProxy {

	@Override
	public void registerRenderInformation() {
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
		if(id == 10) {
			return new GuiSharpener(new InventorySharpener());
		}
		return null;
	}
}
