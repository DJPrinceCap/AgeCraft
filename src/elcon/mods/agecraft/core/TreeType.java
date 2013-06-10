package elcon.mods.agecraft.core;

import net.minecraft.util.Icon;

public enum TreeType {

	OAK(0, "oak"),
	BIRCH(1, "birch"),
	SPRUCE(2, "spruce"),
	JUNGLE(3, "jungle");
	
	public int id;
	public String name;
	public String displayName;
	
	public Icon wood;
	public Icon woodTop;
	public Icon log;
	public Icon planks;
	public Icon leaves;
	public Icon leavesFast;
	public Icon smallSapling;
	public Icon sapling;
	public Icon stick;	
	
	TreeType(int id, String name) {
		this.id = id;
		this.name = name;
		displayName = firstCapital(name);
	}

	private String firstCapital(String n) {
		return Character.toString(n.charAt(0)).toUpperCase() + n.substring(1, n.length());
	}
}
