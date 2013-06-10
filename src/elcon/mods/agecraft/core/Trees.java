package elcon.mods.agecraft.core;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import elcon.mods.agecraft.ACComponent;
import elcon.mods.agecraft.ACCreativeTabs;
import elcon.mods.agecraft.core.blocks.BlockLeaves;
import elcon.mods.agecraft.core.blocks.BlockMetadata;
import elcon.mods.agecraft.core.blocks.BlockPlanks;
import elcon.mods.agecraft.core.blocks.BlockWood;
import elcon.mods.agecraft.core.items.ItemBlockMetadata;

public class Trees extends ACComponent {

	public static Block wood;
	public static Block leaves;
	public static Block sapling;
	public static Block log;
	public static Block planks;
		
	public static Item stick;
	
	public void preInit() {
		
	}
	
	public void init() {
		//init blocks
		wood = new BlockWood(2600).setCreativeTab(ACCreativeTabs.wood).setUnlocalizedName("wood");
		leaves = new BlockLeaves(2601).setCreativeTab(ACCreativeTabs.wood).setUnlocalizedName("leaves");
		//sapling = new BlockSapling(2602).setCreativeTab(ACCreativeTabs.wood).setUnlocalizedName("sapling");
		//log = new BlockLeaves(2603).setCreativeTab(ACCreativeTabs.wood).setUnlocalizedName("log");
		planks = new BlockPlanks(2604).setCreativeTab(ACCreativeTabs.wood).setUnlocalizedName("planks");
		
		//register blocks
		GameRegistry.registerBlock(wood, "AC_trees_log");
		GameRegistry.registerBlock(leaves, "AC_trees_leaves");
		//GameRegistry.registerBlock(sapling, "AC_trees_sapling");
		//GameRegistry.registerBlock(log, "AC_trees_log");
		GameRegistry.registerBlock(planks, "AC_trees_planks");
		
		//init items
		(new ItemBlockMetadata((BlockMetadata) wood)).setUnlocalizedName("wood");
		(new ItemBlockMetadata((BlockMetadata) leaves)).setUnlocalizedName("leaves");
		//(new ItemBlockMetadata((BlockMetadata) sapling)).setUnlocalizedName("log");
		//(new ItemBlockMetadata((BlockMetadata) log)).setUnlocalizedName("log");
		(new ItemBlockMetadata((BlockMetadata) planks)).setUnlocalizedName("planks");
		
		//add block names
		LanguageRegistry.addName(wood, "Wood");
		LanguageRegistry.addName(leaves, "Leaves");
		//LanguageRegistry.addName(sapling, "Sapling");
		//LanguageRegistry.addName(log, "Log");
		LanguageRegistry.addName(planks, "Planks");
	}
	
	public void postInit() {
		TreeType[] types = TreeType.values();
		for(int i = 0; i < types.length; i++) {
			LanguageRegistry.instance().addStringLocalization("trees.wood." + types[i].name, "en_US", types[i].displayName + " Wood");
			LanguageRegistry.instance().addStringLocalization("trees.leaves." + types[i].name, "en_US", types[i].displayName + " Leaves");
			LanguageRegistry.instance().addStringLocalization("trees.sapling." + types[i].name, "en_US", types[i].displayName + " Sapling");
			LanguageRegistry.instance().addStringLocalization("trees.log." + types[i].name, "en_US", types[i].displayName + " Log");
			LanguageRegistry.instance().addStringLocalization("trees.planks." + types[i].name, "en_US", types[i].displayName + " Planks");
		}
	}
	
	public void registerBlockIcons(IconRegister iconRegister) {
		TreeType[] types = TreeType.values();
		for(int i = 0; i < types.length; i++) {
			types[i].wood = iconRegister.registerIcon("agecraft:wood/wood" + types[i].displayName);
			types[i].woodTop = iconRegister.registerIcon("agecraft:wood/woodTop" + types[i].displayName);
			types[i].leaves = iconRegister.registerIcon("agecraft:leaves/leaves" + types[i].displayName);
			types[i].leavesFast = iconRegister.registerIcon("agecraft:leaves/leavesFast" + types[i].displayName);
			types[i].smallSapling = iconRegister.registerIcon("agecraft:sapling/smallSapling" + types[i].displayName);
			types[i].sapling = iconRegister.registerIcon("agecraft:sapling//sapling" + types[i].displayName);
			types[i].planks = iconRegister.registerIcon("agecraft:wood/planks" + types[i].displayName);
		}
	}
	
	public void registerItemIcons(IconRegister iconRegister) {
		TreeType[] types = TreeType.values();
		for(int i = 0; i < types.length; i++) {
			types[i].log = iconRegister.registerIcon("agecraft:wood/log" + types[i].displayName);
			types[i].stick = iconRegister.registerIcon("agecraft:wood/stick" + types[i].displayName);
		}
	}
}
