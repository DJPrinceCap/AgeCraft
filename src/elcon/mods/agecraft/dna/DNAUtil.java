package elcon.mods.agecraft.dna;

public class DNAUtil {

	public static String intToSpeed(int i) {
		switch(i) {
			case 0: return "Slowest";
			case 1: return "Slow";
			case 2: return "Normal";
			case 3: return "Fast";
			case 4: return "Fastest";
		}
		return "Normal";
	}
	
	public static String intToPlusMin(int i) {
		switch(i) {
			case 0: return "\u00A74-2";
			case 1: return "\u00A74-1";
			case 2: return "\u00A770";
			case 3: return "\u00A72+1";
			case 4: return "\u00A72+2";
		}
		return "0";
	}
	
	public static int intToPlusMinInt(int i) {
		switch(i) {
			case 0: return -2;
			case 1: return -1;
			case 2: return 0;
			case 3: return 1;
			case 4: return 2;
		}
		return 0;
	}
}
