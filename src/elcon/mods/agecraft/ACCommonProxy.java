package elcon.mods.agecraft;

public class ACCommonProxy {

	public void registerRenderInformation() {
		for(int i = 0; i < Age.ages.length; i++) {
			if(Age.ages[i] != null) {
				Age.ages[i].serverProxy();
			}
		}
		for(ACComponent component : AgeCraft.instance.components) {
			component.serverProxy();
		}
	}
}
