package elcon.mods.agecraft.core;

import net.minecraft.block.Block;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import elcon.mods.agecraft.ACComponent;
import elcon.mods.agecraft.core.blocks.BlockOre;
import elcon.mods.core.ElConCore;
import elcon.mods.core.render.BlockOverlayRenderingHandler;
import elcon.mods.core.render.ItemBlockOverlayRenderer;

public class Metals extends ACComponent {

	public static Block oreCoal;
	public static Block oreCopper;
	public static Block oreTin;
	public static Block oreIron;
	public static Block oreSilver;
	public static Block oreGold;
	public static Block oreRedstone;
	public static Block oreDiamond;
	public static Block oreAluminium;
	public static Block oreBauxite;
	public static Block oreCobalt;
	public static Block oreLead;
	public static Block oreManganese;
	public static Block oreMercury;
	public static Block oreNickel;
	public static Block orePlatinum;
	public static Block oreSulfur;
	public static Block oreTungsten;
	public static Block oreUranium;
	public static Block oreZinc;
	public static Block oreMithril;
	public static Block oreAdamantite;
	
	public static Block oreAmethyst;
	public static Block oreBerylRed;
	public static Block oreBerylYellow;
	public static Block oreBerylBlue;
	public static Block oreBerylGreen;
	public static Block oreEmerald;
	public static Block oreJade;
	public static Block oreOnyx;
	public static Block oreOpal;
	public static Block orePyrite;
	public static Block oreQuartz;
	public static Block oreRuby;
	public static Block oreSapphire;
	public static Block oreTigerEye;
	public static Block oreTopaz;
	public static Block oreTurquoise;
	
	public static Block oreAmber;
	
	public void preInit() {
		
	}
	
	public void init() {
		//init blocks
		oreCoal = new BlockOre(2500).setUnlocalizedName("oreCoal");
		oreCopper = new BlockOre(2501).setUnlocalizedName("oreCopper");
		oreTin = new BlockOre(2502).setUnlocalizedName("oreTin");
		oreIron = new BlockOre(2503).setUnlocalizedName("oreIron");
		oreSilver = new BlockOre(2504).setUnlocalizedName("oreSilver");
		oreGold = new BlockOre(2505).setUnlocalizedName("oreGold");
		oreRedstone = new BlockOre(2506).setUnlocalizedName("oreRedstone");
		oreDiamond = new BlockOre(2507).setUnlocalizedName("oreDiamond");
		oreAluminium = new BlockOre(2508).setUnlocalizedName("oreAluminium");
		oreBauxite = new BlockOre(2509).setUnlocalizedName("oreBauxite");
		oreCobalt = new BlockOre(2510).setUnlocalizedName("oreCobalt");
		oreLead = new BlockOre(2511).setUnlocalizedName("oreLead");
		oreManganese = new BlockOre(2512).setUnlocalizedName("oreManganese");
		oreMercury = new BlockOre(2513).setUnlocalizedName("oreMercury");
		oreNickel = new BlockOre(2514).setUnlocalizedName("oreNickel");
		orePlatinum = new BlockOre(2515).setUnlocalizedName("orePlatinum");
		oreSulfur = new BlockOre(2516).setUnlocalizedName("oreSulfur");
		oreTungsten = new BlockOre(2517).setUnlocalizedName("oreTungsten");
		oreUranium = new BlockOre(2518).setUnlocalizedName("oreUranium");
		oreZinc = new BlockOre(2519).setUnlocalizedName("oreZinc");
		oreMithril = new BlockOre(2520).setUnlocalizedName("oreMithril");
		oreAdamantite = new BlockOre(2521).setUnlocalizedName("oreAdamantite");
		
		oreAmethyst = new BlockOre(2522).setUnlocalizedName("oreAmethyst");
		oreBerylRed = new BlockOre(2523).setUnlocalizedName("oreBerylRed");
		oreBerylYellow = new BlockOre(2524).setUnlocalizedName("oreBerylYellow");
		oreBerylBlue = new BlockOre(2525).setUnlocalizedName("oreBerylBlue");
		oreBerylGreen = new BlockOre(2526).setUnlocalizedName("oreBerylGreen");
		oreEmerald = new BlockOre(2527).setUnlocalizedName("oreEmerald");
		oreJade = new BlockOre(2528).setUnlocalizedName("oreJade");
		oreOnyx = new BlockOre(2529).setUnlocalizedName("oreOnyx");
		oreOpal = new BlockOre(2530).setUnlocalizedName("oreOpal");
		orePyrite = new BlockOre(2531).setUnlocalizedName("orePyrite");
		oreQuartz = new BlockOre(2532).setUnlocalizedName("oreQuartz");
		oreRuby = new BlockOre(2533).setUnlocalizedName("oreRuby");
		oreSapphire = new BlockOre(2534).setUnlocalizedName("oreSapphire");
		oreTigerEye = new BlockOre(2535).setUnlocalizedName("oreTigerEye");
		oreTopaz = new BlockOre(2536).setUnlocalizedName("oreTopaz");
		oreTurquoise = new BlockOre(2537).setUnlocalizedName("oreTurquoise");
		
		oreAmber = new BlockOre(2538).setUnlocalizedName("oreAmber");
		
		//register blocks
		GameRegistry.registerBlock(oreCoal, "AC_oreCoal");
		GameRegistry.registerBlock(oreCopper, "AC_oreCopper");
		GameRegistry.registerBlock(oreTin, "AC_oreTin");
		GameRegistry.registerBlock(oreIron, "AC_oreIron");
		GameRegistry.registerBlock(oreSilver, "AC_oreSilver");
		GameRegistry.registerBlock(oreGold, "AC_oreGold");
		GameRegistry.registerBlock(oreRedstone, "AC_oreRedstone");
		GameRegistry.registerBlock(oreDiamond, "AC_oreDiamond");
		GameRegistry.registerBlock(oreAluminium, "AC_oreAluminium");
		GameRegistry.registerBlock(oreBauxite, "AC_oreBauxite");
		GameRegistry.registerBlock(oreCobalt, "AC_oreCobalt");
		GameRegistry.registerBlock(oreLead, "AC_oreLead");
		GameRegistry.registerBlock(oreManganese, "AC_oreManganese");
		GameRegistry.registerBlock(oreMercury, "AC_oreMercury");
		GameRegistry.registerBlock(oreNickel, "AC_oreNickel");
		GameRegistry.registerBlock(orePlatinum, "AC_orePlatinum");
		GameRegistry.registerBlock(oreSulfur, "AC_oreSulfer");
		GameRegistry.registerBlock(oreTungsten, "AC_oreTungsten");
		GameRegistry.registerBlock(oreUranium, "AC_oreUranium");
		GameRegistry.registerBlock(oreZinc, "AC_oreZinc");
		GameRegistry.registerBlock(oreMithril, "AC_oreMithril");
		GameRegistry.registerBlock(oreAdamantite, "AC_oreAdamantite");
		
		GameRegistry.registerBlock(oreAmethyst, "AC_oreAmethyst");
		GameRegistry.registerBlock(oreBerylRed, "AC_oreBerylRed");
		GameRegistry.registerBlock(oreBerylYellow, "AC_oreBerylYellow");
		GameRegistry.registerBlock(oreBerylBlue, "AC_oreBerylBlue");
		GameRegistry.registerBlock(oreBerylGreen, "AC_oreBerylGreen");
		GameRegistry.registerBlock(oreEmerald, "AC_oreEmerald");
		GameRegistry.registerBlock(oreJade, "AC_oreJade");
		GameRegistry.registerBlock(oreOnyx, "AC_oreOnyx");
		GameRegistry.registerBlock(oreOpal, "AC_oreOpal");
		GameRegistry.registerBlock(orePyrite, "AC_orePyrite");
		GameRegistry.registerBlock(oreQuartz, "AC_oreQuartz");
		GameRegistry.registerBlock(oreRuby, "AC_oreRuby");
		GameRegistry.registerBlock(oreSapphire, "AC_oreSapphire");
		GameRegistry.registerBlock(oreTigerEye, "AC_oreTigerEye");
		GameRegistry.registerBlock(oreTopaz, "AC_oreTopaz");
		GameRegistry.registerBlock(oreTurquoise, "AC_oreTurquoise");
		
		GameRegistry.registerBlock(oreAmber, "AC_oreAmber");
		
		//add block names
		LanguageRegistry.addName(oreCoal, "Coal Ore");
		LanguageRegistry.addName(oreCopper, "Copper Ore");
		LanguageRegistry.addName(oreTin, "Tin Ore");
		LanguageRegistry.addName(oreIron, "Iron Ore");
		LanguageRegistry.addName(oreSilver, "Silver Ore");
		LanguageRegistry.addName(oreGold, "Gold Ore");
		LanguageRegistry.addName(oreRedstone, "Redstone Ore");
		LanguageRegistry.addName(oreDiamond, "Diamond Ore");
		LanguageRegistry.addName(oreAluminium, "Aluminium Ore");
		LanguageRegistry.addName(oreBauxite, "Bauxite Ore");
		LanguageRegistry.addName(oreCobalt, "Cobalt Ore");
		LanguageRegistry.addName(oreLead, "Lead Ore");
		LanguageRegistry.addName(oreManganese, "Manganese Ore");
		LanguageRegistry.addName(oreMercury, "Mercury Ore");
		LanguageRegistry.addName(oreNickel, "Nickel Ore");
		LanguageRegistry.addName(orePlatinum, "Platinum Ore");
		LanguageRegistry.addName(oreSulfur, "Sulfur Ore");
		LanguageRegistry.addName(oreTungsten, "Tungsten Ore");
		LanguageRegistry.addName(oreUranium, "Uranium Ore");
		LanguageRegistry.addName(oreZinc, "Zinc Ore");
		LanguageRegistry.addName(oreMithril, "Mithril Ore");
		LanguageRegistry.addName(oreAdamantite, "Adamantite Ore");
		
		LanguageRegistry.addName(oreAmethyst, "Amethyst Ore");
		LanguageRegistry.addName(oreBerylRed, "BerylRed Ore");
		LanguageRegistry.addName(oreBerylYellow, "BerylYellow Ore");
		LanguageRegistry.addName(oreBerylBlue, "BerylBlue Ore");
		LanguageRegistry.addName(oreBerylGreen, "BerylGreen Ore");
		LanguageRegistry.addName(oreEmerald, "Emerald Ore");
		LanguageRegistry.addName(oreJade, "Jade Ore");
		LanguageRegistry.addName(oreOnyx, "Onyx Ore");
		LanguageRegistry.addName(oreOpal, "Opal Ore");
		LanguageRegistry.addName(orePyrite, "Pyrite Ore");
		LanguageRegistry.addName(oreQuartz, "Quartz Ore");
		LanguageRegistry.addName(oreRuby, "Ruby Ore");
		LanguageRegistry.addName(oreSapphire, "Sapphire Ore");
		LanguageRegistry.addName(oreTigerEye, "TigerEye Ore");
		LanguageRegistry.addName(oreTopaz, "Topaz Ore");
		LanguageRegistry.addName(oreTurquoise, "Turquoise Ore");
		
		LanguageRegistry.addName(oreAmber, "Amber Ore");
		
		//register ores
		OreDictionary.registerOre("oreCoal", oreCoal);
		OreDictionary.registerOre("oreCopper", oreCopper);
		OreDictionary.registerOre("oreTin", oreTin);
		OreDictionary.registerOre("oreIron", oreIron);
		OreDictionary.registerOre("oreSilver", oreSilver);
		OreDictionary.registerOre("oreGold", oreGold);
		OreDictionary.registerOre("oreRedstone", oreRedstone);
		OreDictionary.registerOre("oreDiamond", oreDiamond);
		OreDictionary.registerOre("oreAluminium", oreAluminium);
		OreDictionary.registerOre("oreBauxite", oreBauxite);
		OreDictionary.registerOre("oreCobalt", oreCobalt);
		OreDictionary.registerOre("oreLead", oreLead);
		OreDictionary.registerOre("oreManganese", oreManganese);
		OreDictionary.registerOre("oreMercury", oreMercury);
		OreDictionary.registerOre("oreNickel", oreNickel);
		OreDictionary.registerOre("orePlatinum", orePlatinum);
		OreDictionary.registerOre("oreSulfur", oreSulfur);
		OreDictionary.registerOre("oreTungsten", oreTungsten);
		OreDictionary.registerOre("oreUranium", oreUranium);
		OreDictionary.registerOre("oreZinc", oreZinc);
		
		OreDictionary.registerOre("oreAmethyst", oreAmethyst);
		OreDictionary.registerOre("oreBerylRed", oreBerylRed);
		OreDictionary.registerOre("oreBerylYellow", oreBerylYellow);
		OreDictionary.registerOre("oreBerylBlue", oreBerylBlue);
		OreDictionary.registerOre("oreBerylGreen", oreBerylGreen);
		OreDictionary.registerOre("oreEmerald", oreEmerald);
		OreDictionary.registerOre("oreJade", oreJade);
		OreDictionary.registerOre("oreOnyx", oreOnyx);
		OreDictionary.registerOre("oreOpal", oreOpal);
		OreDictionary.registerOre("orePyrite", orePyrite);
		OreDictionary.registerOre("oreQuartz", oreQuartz);
		OreDictionary.registerOre("oreRuby", oreRuby);
		OreDictionary.registerOre("oreSapphire", oreSapphire);
		OreDictionary.registerOre("oreTigerEye", oreTigerEye);
		OreDictionary.registerOre("oreTopaz", oreTopaz);
		OreDictionary.registerOre("oreTurquoise", oreTurquoise);
		
		OreDictionary.registerOre("oreAmber", oreAmber);
	}
	
	public void postInit() {
		
	}
	
	public void clientProxy() {
		for(int i = 2500; i <= 2538; i++){
			MinecraftForgeClient.registerItemRenderer(i, new ItemBlockOverlayRenderer());
		}
	}
}
