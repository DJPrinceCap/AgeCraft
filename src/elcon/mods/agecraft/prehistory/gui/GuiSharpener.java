package elcon.mods.agecraft.prehistory.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class GuiSharpener extends GuiContainer {
	
	public GuiSharpener(InventorySharpener inv) {
		super(new ContainerSharpener(Minecraft.getMinecraft().thePlayer, inv));
		allowUserInput = false;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {		
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.func_110577_a(new ResourceLocation("agecraft", "textures/gui/sharpener.png"));
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}
}
