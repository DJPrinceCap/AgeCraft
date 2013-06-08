package elcon.mods.agecraft.prehistory;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;

public class SharpenerRecipes {

	public static ArrayList<SharpenerRecipe> recipes = new ArrayList<SharpenerRecipe>();
	
	public static SharpenerRecipe getRecipe(boolean[] input) {
		for(SharpenerRecipe r : recipes) {
			if(r != null) {
				boolean good = true;
				for(int i = 0; i < 64; i++) {
					if(input[i] && !r.input[i]) {
						good = false;
					}
				}
				if(good) {
					return r;
				}
			}
		}
		return null;
	}
	
	public static void addRecipe(boolean[] input, ItemStack output) {
		recipes.add(new SharpenerRecipe(input, output));
	}
}
