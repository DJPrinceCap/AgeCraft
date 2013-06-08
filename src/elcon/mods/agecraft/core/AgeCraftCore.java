package elcon.mods.agecraft.core;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import elcon.mods.agecraft.ACComponent;
import elcon.mods.agecraft.ACCreativeTabs;
import elcon.mods.agecraft.core.blocks.BlockAgeTeleporter;
import elcon.mods.agecraft.core.blocks.BlockAgeTeleporterBeam;
import elcon.mods.agecraft.core.blocks.BlockAgeTeleporterBlock;
import elcon.mods.agecraft.core.blocks.BlockStoneLayered;
import elcon.mods.agecraft.core.tileentities.TileEntityAgeTeleporterBeam;
import elcon.mods.agecraft.core.tileentities.renderers.TileEntityAgeTeleportBeamRenderer;

public class AgeCraftCore extends ACComponent {
	
	public Metals ores;
	public Trees trees;
	
	public static Block ageTeleporter;
	public static Block ageTeleporterBlock;
	public static Block ageTeleporterBeam;
	public static Block ageTeleporterChest;
	
	public AgeCraftCore() {
		super();
		ores = new Metals();
		trees = new Trees();
	}
	
	public void preInit() {
		
	}
	
	public void init() {
		//init block
		ageTeleporter = new BlockAgeTeleporter(2996).setCreativeTab(ACCreativeTabs.ageCraft).setUnlocalizedName("ageTeleporter");
		ageTeleporterBlock = new BlockAgeTeleporterBlock(2997).setCreativeTab(ACCreativeTabs.ageCraft).setUnlocalizedName("ageTeleporterBlock");
		ageTeleporterBeam = new BlockAgeTeleporterBeam(2998).setCreativeTab(ACCreativeTabs.ageCraft).setUnlocalizedName("ageTeleporterBeam");
		
		//register blocks
		GameRegistry.registerBlock(ageTeleporter, "AC_ageTeleporter");
		GameRegistry.registerBlock(ageTeleporterBlock, "AC_ageTeleporterBlock");
		GameRegistry.registerBlock(ageTeleporterBeam, "AC_ageTeleporterBeam");
		
		//add block names
		LanguageRegistry.addName(ageTeleporter, "Age Teleporter");
		LanguageRegistry.addName(ageTeleporterBlock, "Age Teleporter Block");
		LanguageRegistry.addName(ageTeleporterBeam, "Age Teleporter Beam");
		
		//register tile entities
		GameRegistry.registerTileEntity(TileEntityAgeTeleporterBeam.class, "AgeTeleporterBeam");
	}
	
	public void postInit() {
		Block.blocksList[1] = null;
		Block.stone = new BlockStoneLayered(1).setUnlocalizedName("stone");
		Item.itemsList[1] = new ItemBlockWithMetadata(1 - 256, Block.stone).setUnlocalizedName("stone");
	}
	
	public void clientProxy() {
		//register tile entity renderers
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAgeTeleporterBeam.class, new TileEntityAgeTeleportBeamRenderer());
	}
	
	public void serverProxy() {
		
	}
	
	public void generateWorld(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		chunkX *= 16;
		chunkZ *= 16;
		for(int i = 0; i < 16; i++) {
			for(int j = 0; j < 128; j++) {
				for(int k = 0; k < 16; k++) {
					if(world.getBlockId(chunkX + i, j, chunkZ + k) == Block.stone.blockID) {
						((BlockStoneLayered) Block.stone).updateHeight(world, chunkX + i, j, chunkZ + k, random);
					}
				}
			}
		}
	}
}
