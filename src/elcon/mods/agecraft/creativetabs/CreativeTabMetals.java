package elcon.mods.agecraft.creativetabs;

import elcon.mods.agecraft.core.Metals;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CreativeTabMetals extends CreativeTabs {

	public CreativeTabMetals(int id) {
		super(id, "Metals");
	}

	@Override
	public ItemStack getIconItemStack() {
		return new ItemStack(Metals.oreCoal.blockID, 1, 0);
	}
}
