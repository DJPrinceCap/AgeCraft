package elcon.mods.agecraft.core;

import elcon.mods.agecraft.ACLog;

public class OreRegistry {

	public class Ore {
		
		public int id;
		public String name;
		public OreType type;
		
		public Ore(int id, String name, OreType type) {
			this.id = id;
			this.name = name;
		}
	}
	
	public enum OreType {
		METAL(),
		GEM();
	}
	
	public static Ore[] ores = new Ore[64];
	
	public static void registerMetal(Ore ore) {
		if(ores[ore.id] != null) {
			ACLog.warning("[OreRegistry] Overriding existing ore (" + ores[ore.id] + ": " + ores[ore.id].name.toUpperCase() + ") with new ore (" + ore.id + ": " + ore.name.toUpperCase() + ")");
		}
		ores[ore.id]= ore;
	}
}
