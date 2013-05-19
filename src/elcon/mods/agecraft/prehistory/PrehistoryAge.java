package elcon.mods.agecraft.prehistory;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import elcon.mods.agecraft.ACCreativeTabs;
import elcon.mods.agecraft.Age;
import elcon.mods.agecraft.prehistory.blocks.BlockCampfire;

public class PrehistoryAge extends Age {

	public static Block campfireOff;
	public static Block campfireOn;
	public static Block rockBlock;
	
	public static Item rock;
	
	public PrehistoryAge(int id) {
		super(id, "prehistory", ACCreativeTabs.prehistoryAge);
	}
	
	@Override
	public void init() {
		//init blocks
		campfireOff = new BlockCampfire(13000, false).setCreativeTab(tab).setUnlocalizedName("campfireOff");
		campfireOn = new BlockCampfire(13001, true).setCreativeTab(tab).setUnlocalizedName("campfireOn");
		
		//register blocks
		GameRegistry.registerBlock(campfireOff, "AC_prehistory_campfireOff");
		GameRegistry.registerBlock(campfireOff, "AC_prehistory_campfireOn");
		
		//init items
		
		//add block names
		LanguageRegistry.addName(campfireOff, "Campfire");
		LanguageRegistry.addName(campfireOn, "Campfire");
		
		//add item names
	}
	
	@Override
	public void postInit() {
		
	}
}
