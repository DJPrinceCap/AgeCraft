package elcon.mods.agecraft.prehistory;

import java.util.HashMap;

import net.minecraft.item.ItemStack;

public class Campfire {

	private static HashMap<Integer, Integer> fuelValues = new HashMap<Integer, Integer>();

	public static int getFuel(int id) {
		return fuelValues.get(id);
	}
	
	public static void addFuel(int id, int value) {
		fuelValues.put(id, value);
	}
	
	public static void removeFuel(int id) {
		fuelValues.remove(id);
	}
	
	public static boolean isFuel(ItemStack stack) {
		if(stack != null) {
			for(int s : fuelValues.keySet()) {
				if(s != 0) {
					if(s == stack.itemID) {
						return true;
					}
				}
			}
		}
		return false;
	}
}
