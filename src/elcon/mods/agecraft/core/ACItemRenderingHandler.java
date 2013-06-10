package elcon.mods.agecraft.core;

import org.lwjgl.opengl.GL11;

import elcon.mods.core.block.BlockOverlay;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;

public class ACItemRenderingHandler implements IItemRenderer {

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		if(item.itemID == Trees.wood.blockID) {
			switch(type.ordinal()) {
			case 1:
				return true;
			case 2:
				return true;
			case 3:
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		if(item.itemID == Trees.wood.blockID) {
			switch(type.ordinal()) {
			case 1:
				renderWoodBlock((RenderBlocks) data[0], item, -0.5F, -0.5F, -0.5F);
				break;
			case 2:
				renderWoodBlock((RenderBlocks) data[0], item, 0.0F, 0.0F, 0.0F);
				break;
			case 3:
				renderWoodBlock((RenderBlocks) data[0], item, -0.5F, -0.5F, -0.5F);
				break;
			}
		}
	}

	private void renderWoodBlock(RenderBlocks render, ItemStack item, float translateX, float translateY, float translateZ) {
		Tessellator tessellator = Tessellator.instance;
		Block block = Block.blocksList[item.itemID];

		block.setBlockBoundsForItemRender();
		render.setRenderBoundsFromBlock(block);

		GL11.glTranslatef(translateX, translateY, translateZ);

		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, -1.0F, 0.0F);
		render.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(0, item.getItemDamage()));
		tessellator.draw();

		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 1.0F, 0.0F);
		render.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(1, item.getItemDamage()));
		tessellator.draw();

		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, -1.0F);
		render.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(2, item.getItemDamage()));
		tessellator.draw();

		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, 1.0F);
		render.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(3, item.getItemDamage()));
		tessellator.draw();

		tessellator.startDrawingQuads();
		tessellator.setNormal(-1.0F, 0.0F, 0.0F);
		render.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, block.getIcon(4, item.getItemDamage()));
		tessellator.draw();

		tessellator.startDrawingQuads();
		tessellator.setNormal(1.0F, 0.0F, 0.0F);
		render.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, block.getIcon(5, item.getItemDamage()));
		tessellator.draw();

		GL11.glTranslatef(0.5F, 0.5F, 0.5F);

		block.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}
}
