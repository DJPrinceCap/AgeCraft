package elcon.mods.agecraft.core;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import elcon.mods.agecraft.core.blocks.BlockLog;

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
			BlockLog blockLog = (BlockLog) block;
			double minX = 0.35D;
			double minY = 0.0D;
			double minZ = 0.35D;
			double maxX = 0.65D;
			double maxY = 0.75D;
			double maxZ = 0.65D;
			
			if(blockLog.isConnected(blockAccess, x - 1, y, z, false)) {
				minX = 0.0D;
			}
			if(blockLog.isConnected(blockAccess, x + 1, y, z, false)) {
				maxX = 1.0D;
			}
			if(blockLog.isConnected(blockAccess, x, y - 1, z, false)) {
				minY = 0.0D;
			}
			if(blockLog.isConnected(blockAccess, x, y + 1, z, true)) {
				maxY = 1.0D;
			}
			if(blockLog.isConnected(blockAccess, x, y, z - 1, false)) {
				minZ = 0.0D;
			}
			if(blockLog.isConnected(blockAccess, x, y, z + 1, false)) {
				maxZ = 1.0D;
			}

			renderer.setRenderBounds(minX, minY, minZ, maxX, maxY, maxZ);
			renderer.renderStandardBlock(block, x, y, z);
		} else if(modelID == 102) {
			renderCrossedSquares(blockAccess, x, y, z, block, renderer);
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

	public boolean renderCrossedSquares(IBlockAccess blockAccess, int x, int y, int z, Block block, RenderBlocks renderer) {
		Tessellator tessellator = Tessellator.instance;
		tessellator.setBrightness(block.getMixedBrightnessForBlock(blockAccess, x, y, z));
		float f = 1.0F;
		int l = block.colorMultiplier(blockAccess, x, y, z);
		float f1 = (float) (l >> 16 & 255) / 255.0F;
		float f2 = (float) (l >> 8 & 255) / 255.0F;
		float f3 = (float) (l & 255) / 255.0F;

		if(EntityRenderer.anaglyphEnable) {
			float f4 = (f1 * 30.0F + f2 * 59.0F + f3 * 11.0F) / 100.0F;
			float f5 = (f1 * 30.0F + f2 * 70.0F) / 100.0F;
			float f6 = (f1 * 30.0F + f3 * 70.0F) / 100.0F;
			f1 = f4;
			f2 = f5;
			f3 = f6;
		}

		tessellator.setColorOpaque_F(f * f1, f * f2, f * f3);
		double d0 = (double) x;
		double d1 = (double) y;
		double d2 = (double) z;

		if(block == Block.tallGrass) {
			long i1 = (long) (x * 3129871) ^ (long) z * 116129781L ^ (long) y;
			i1 = i1 * i1 * 42317861L + i1 * 11L;
			d0 += ((double) ((float) (i1 >> 16 & 15L) / 15.0F) - 0.5D) * 0.5D;
			d1 += ((double) ((float) (i1 >> 20 & 15L) / 15.0F) - 1.0D) * 0.2D;
			d2 += ((double) ((float) (i1 >> 24 & 15L) / 15.0F) - 0.5D) * 0.5D;
		}

		renderer.drawCrossedSquares(block, blockAccess.getBlockMetadata(x, y, z), d0, d1, d2, 1.0F);
		return true;
	}
}
