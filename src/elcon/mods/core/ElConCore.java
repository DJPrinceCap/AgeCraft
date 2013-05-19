package elcon.mods.core;

import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class ElConCore {
	
	/**
	 * Register a TileEntity renderer
	 * 
	 * @param tileEntityClass - The TileEntity Class
	 * @param tileEntityRenderer - Your renderer object, that extends TileEntitySpecialRenderer
	 */
	public static void registerTileEntityRenderer(Class<? extends TileEntity> tileEntityClass, TileEntitySpecialRenderer tileEntityRenderer) {
		tileEntityRenderer.setTileEntityRenderer(TileEntityRenderer.instance);
		TileEntityRenderer.instance.specialRendererMap.put(tileEntityClass, tileEntityRenderer);
	}
	
	/**
	 * Get the y-coord of the first uncovered block
	 * 
	 * @param world
	 * @param x
	 * @param z
	 * @return The y-coord of the first uncovered block
	 */
	public static int getFirstUncoveredBlock(World world, int x, int z) {
		int i;
        for(i = 60; !world.isAirBlock(x + 3, i + 1, z + 3); i++) {
            ;
        }
        return i;
	}
}
