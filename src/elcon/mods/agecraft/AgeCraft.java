package elcon.mods.agecraft;

import java.io.File;

import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = ACReference.MOD_ID, name = ACReference.NAME, version = ACReference.VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class AgeCraft {

	@Instance(ACReference.MOD_ID)
	public static AgeCraft instance;
	
	@SidedProxy(clientSide = ACReference.CLIENT_PROXY_CLASS, serverSide = ACReference.SERVER_PROXY_CLASS)
    public static ACCommonProxy proxy;
	
	public static File minecraftDir;
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		minecraftDir = new File(event.getSuggestedConfigurationFile().getPath().replace("config\\AgeCraft.cfg", ""));
		
		ACLog.init();		
		
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		ACConfig.load(config);
		config.save();
		
		LanguageRegistry.instance().addStringLocalization("agecraft.version.init_log_message", "en_US", "Initializing remote version check against remote version authority, located at");
		LanguageRegistry.instance().addStringLocalization("agecraft.version.uninitialized", "en_US", "Remote version check failed to initialize properly");
		LanguageRegistry.instance().addStringLocalization("agecraft.version.current", "en_US", "Currently using the most up to date version (@REMOTE_MOD_VERSION@) of AgeCraft for @MINECRAFT_VERSION@");
		LanguageRegistry.instance().addStringLocalization("agecraft.version.outdated", "en_US", "A new @MOD_NAME@ version exists (@REMOTE_MOD_VERSION@) for @MINECRAFT_VERSION@. Get it here: @MOD_UPDATE_LOCATION@");
		LanguageRegistry.instance().addStringLocalization("agecraft.version.general_error", "en_US", "Error while connecting to remote version authority file; trying again");
		LanguageRegistry.instance().addStringLocalization("agecraft.version.final_error", "en_US", "Version check stopping after three unsuccessful connection attempts");
		LanguageRegistry.instance().addStringLocalization("agecraft.version.mc_version_not_found", "en_US", "Unable to find a version of @MOD_NAME@ for @MINECRAFT_VERSION@ in the remote version authority");
		
		ACVersion.execute();
	}
	
	@Init
	public void init(FMLInitializationEvent event) {
		
	}
	
	@PostInit
	public void postInit(FMLPostInitializationEvent event) {
		
	}
}
