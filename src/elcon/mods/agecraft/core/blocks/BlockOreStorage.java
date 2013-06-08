package elcon.mods.agecraft.core.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import elcon.mods.agecraft.ACCreativeTabs;

public class BlockOreStorage extends Block {
	
	private Icon icon;
	
	public BlockOreStorage(int i) {
		super(i, Material.iron);
		setHardness(5.0F);
		setResistance(10.0F);
		setStepSound(soundMetalFootstep);
		setCreativeTab(ACCreativeTabs.metals);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		icon = iconRegister.registerIcon("agecraft:ores/" + getUnlocalizedName2());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int metadata) {
		return icon;
	}
}
