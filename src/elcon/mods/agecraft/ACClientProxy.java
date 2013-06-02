package elcon.mods.agecraft;

public class ACClientProxy extends ACCommonProxy {

	@Override
	public void registerRenderInformation() {
		AgeCraft.instance.core.clientProxy();
		for(int i = 0; i < Age.ages.length; i++) {
			if(Age.ages[i] != null) {
				Age.ages[i].clientProxy();
			}
		}
	}
}
