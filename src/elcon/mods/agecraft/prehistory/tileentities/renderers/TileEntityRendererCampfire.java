package elcon.mods.agecraft.prehistory.tileentities.renderers;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import elcon.mods.agecraft.prehistory.blocks.models.BlockModelCampfire;
import elcon.mods.agecraft.prehistory.tileentities.TileEntityCampfire;

public class TileEntityRendererCampfire extends TileEntitySpecialRenderer {

	public BlockModelCampfire model;

	public TileEntityRendererCampfire() {
		model = new BlockModelCampfire();
	}
	
	public void renderAModelAt(TileEntityCampfire tile, double x, double y, double z, float f) {
		GL11.glPushMatrix();
		GL11.glTranslated(x, y, z);
		GL11.glTranslatef(0.5F, -0.5F, 0.5F);
		bindTextureByName("/mods/agecraft/textures/tile/campfire.png");
		GL11.glPushMatrix();
		model.renderModel(0.0625F);
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}

	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f) {
		renderAModelAt((TileEntityCampfire) tile, x, y, z, f);
	}
}
