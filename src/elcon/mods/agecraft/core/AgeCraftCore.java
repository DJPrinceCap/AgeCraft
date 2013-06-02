package elcon.mods.agecraft.core;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import elcon.mods.agecraft.core.blocks.BlockStoneLayered;

public class AgeCraftCore {
	
	public void preInit() {
		
	}
	
	public void init() {
		
	}
	
	public void postInit() {
		Block.blocksList[1] = null;
		Block.stone = new BlockStoneLayered(1).setUnlocalizedName("stone");
		Item.itemsList[1] = new ItemBlockWithMetadata(1 - 256, Block.stone).setUnlocalizedName("stone");
	}
	
	public void clientProxy() {
		
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
