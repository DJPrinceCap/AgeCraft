package elcon.mods.agecraft;

import elcon.mods.agecraft.prehistory.PrehistoryAge;

public class Age {

	public static Age[] ages = new Age[32];
	
	public static Age prehistory = new PrehistoryAge(0);
	
	public int ageID;
	public String ageName;
	
	public Age(int id, String name) {
		ageID = id;
		ageName = name;
		
		ages[ageID] = this;
	}
	
	public void preInit() {
		
	}
	
	public void init() {
		
	}
	
	public void postInit() {
		
	}
}
