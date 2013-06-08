package elcon.mods.agecraft.prehistory;

import net.minecraft.item.ItemStack;

public class SharpenerRecipe {

	public boolean[] input = new boolean[64];
	public ItemStack output;
	
	public SharpenerRecipe(boolean[] in, ItemStack out) {
		input = in;
		output = out;
	}
}