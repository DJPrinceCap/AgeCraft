package elcon.mods.agecraft.core;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class ACBlockRenderingHandler implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
		
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess blockAccess, int x, int y, int z, Block block, int modelID, RenderBlocks renderer) {
		if(modelID == 100) {
			int direction = blockAccess.getBlockMetadata(x, y, z) & 3;
			if(direction == 2) {
				renderer.uvRotateEast = 1;
				renderer.uvRotateWest = 1;
				renderer.uvRotateTop = 1;
				renderer.uvRotateBottom = 1;
			} else if(direction == 1) {
				renderer.uvRotateSouth = 1;
				renderer.uvRotateNorth = 1;
			}

			boolean flag = renderer.renderStandardBlock(block, x, y, z);
			renderer.uvRotateSouth = 0;
			renderer.uvRotateEast = 0;
			renderer.uvRotateWest = 0;
			renderer.uvRotateNorth = 0;
			renderer.uvRotateTop = 0;
			renderer.uvRotateBottom = 0;
			return flag;
		} else if(modelID == 101) {
			renderer.setRenderBounds(0.35D, 0.0D, 0.35D, 0.65D, 1.0D, 0.65D);
			renderer.renderStandardBlock(block, x, y, z);
		} else if(modelID == 102) {
			
		}
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory() {
		return true;
	}

	@Override
	public int getRenderId() {
		return 100;
	}

}
