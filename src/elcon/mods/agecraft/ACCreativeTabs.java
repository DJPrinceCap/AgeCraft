package elcon.mods.agecraft;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import elcon.mods.agecraft.creativetabs.CreativeTabAge;
import elcon.mods.agecraft.creativetabs.CreativeTabAgeCraft;

public class ACCreativeTabs {

	public static CreativeTabs ageCraft = new CreativeTabAgeCraft(12);
	
	public static CreativeTabs prehistoryAge = new CreativeTabAge(13, "Prehistory", new ItemStack(Block.dirt.blockID, 1, 0));
	public static CreativeTabs agricultureAge;
	public static CreativeTabs ancientEgyptAge;
	public static CreativeTabs ancientChinaAge;
	public static CreativeTabs romanGreekAge;
	public static CreativeTabs mediavalAge;
	public static CreativeTabs earlyModernAge;
	public static CreativeTabs industrialAge;
	public static CreativeTabs modernAge;
	public static CreativeTabs futureAge;
}
