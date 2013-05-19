package elcon.mods.agecraft;

public class ACReference {

	public static final boolean DEBUG_MODE = true;
	
	public static final String MOD_ID = "AgeCraft";
	public static final String NAME = "AgeCraft";
	public static final String VERSION = "@VERSION@ (build @BUILD_NUMBER@)";
	public static final String DEPENDENCIES = "required-after:Forge@[7.8.0.684,)";
	public static final String SERVER_PROXY_CLASS = "elcon.mods.agecraft.ACCommonProxy";
    public static final String CLIENT_PROXY_CLASS = "elcon.mods.agecraft.ACClientProxy";
    
    public static final int VERSION_CHECK_ATTEMPTS = 3;
    
    public static final int SECOND_IN_TICKS = 20;
}
