package elcon.mods.agecraft;

import java.util.EnumSet;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;
import elcon.mods.agecraft.core.Trees;
import elcon.mods.agecraft.core.blocks.BlockLeaves;
import elcon.mods.agecraft.tech.gui.GuiTechTreePopup;

public class ACTickHandlerClient implements ITickHandler {

	public static GuiTechTreePopup techTreePopup = null;
	
	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		Minecraft mc = Minecraft.getMinecraft();
		EntityPlayer player = (EntityPlayer) mc.thePlayer;
		long time = System.currentTimeMillis();
		
		if(techTreePopup == null) {
			techTreePopup = new GuiTechTreePopup(mc);
		}
		techTreePopup.updateTechTreeWindow();
		
		if(type.contains(TickType.RENDER)) {
			if(Block.leaves.graphicsLevel != ((BlockLeaves) Trees.leaves).graphicsLevel) {
				((BlockLeaves) Trees.leaves).graphicsLevel = Block.leaves.graphicsLevel;
			}
		}
		if(type.contains(TickType.CLIENT)) {
			
		}
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.RENDER, TickType.CLIENT);
	}

	@Override
	public String getLabel() {
		return "AgeCraftTickHandler";
	}

}
