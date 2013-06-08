package elcon.mods.agecraft.core.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;

public class BlockAgeTeleporterBlock extends Block {

	private Icon icon;
	
	public BlockAgeTeleporterBlock(int i) {
		super(i, Material.iron);
		setResistance(6000000.0F);
		setLightValue(0.5F);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		return icon;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		icon = iconRegister.registerIcon("agecraft:teleporter_block");
	}
}
