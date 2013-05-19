package elcon.mods.agecraft;

import net.minecraftforge.common.Configuration;

public class ACConfig {
	
	public static Configuration configuration = null;
	
	public static String CATEGORY_GENERAL = "General";
	public static String CATEGORY_VERSION = "Version";
	
	public static boolean DISPLAY_VERSION_RESULT = true;
    public static String LAST_DISCOVERED_VERSION = "";
    public static String LAST_DISCOVERED_VERSION_TYPE = "";
    
	public static void load(Configuration config) {		
		DISPLAY_VERSION_RESULT = config.get(CATEGORY_VERSION, "display_results", true).getBoolean(true);
		LAST_DISCOVERED_VERSION = config.get(CATEGORY_VERSION, "last_discovered_version", "").getString();
        LAST_DISCOVERED_VERSION_TYPE = config.get(CATEGORY_VERSION, "last_discovered_version_type", "").getString();
        
		configuration = config;
	}
	
	public static void set(String categoryName, String propertyName, String newValue) {
        configuration.load();
        if (configuration.getCategoryNames().contains(categoryName)) {
            if (configuration.getCategory(categoryName).containsKey(propertyName)) {
                configuration.getCategory(categoryName).get(propertyName).set(newValue);
            }
        }
        configuration.save();
    }
}
