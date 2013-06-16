package elcon.mods.agecraft.util;

public class Coordinates {

	public double x;
	public double y;
	public double z;

	public Coordinates(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public double distanceTo(Coordinates coords) {
		return Math.sqrt(Math.pow(x - coords.x, 2) + Math.pow(y - coords.y, 2) + Math.pow(z - coords.z, 2));
	}

	public static double distance(Coordinates coord1, Coordinates coord2) {
		return Math.sqrt(Math.pow(coord1.x - coord2.x, 2) + Math.pow(coord1.y - coord2.y, 2) + Math.pow(coord1.z - coord2.z, 2));
	}
}
