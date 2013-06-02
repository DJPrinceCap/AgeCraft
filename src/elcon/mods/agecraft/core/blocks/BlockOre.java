package elcon.mods.agecraft.core.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import elcon.mods.agecraft.ACCreativeTabs;
import elcon.mods.agecraft.creativetabs.CreativeTabAgeCraft;
import elcon.mods.core.block.BlockOverlay;

public class BlockOre extends BlockOverlay {

	private Icon oreOverlay;
	
	public BlockOre(int i) {
		super(i, Material.rock);
		setHardness(3.0F);
		setResistance(5.0F);
		setStepSound(Block.soundStoneFootstep);
		setCreativeTab(ACCreativeTabs.metals);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess blockAccess, int x, int y, int z) {		
		int i;
		for(i = 0; y > (i * BlockStoneLayered.LAYER_SIZE); i++) {
		}
		switch(i)  {
			case 1: return 0x878787;
			case 2: return 0xA7A7A7;
			case 3: return 0xC7C7C7;
			case 4: return 0xE7E7E7;
			default: return 0xFFFFFF;
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		oreOverlay = iconRegister.registerIcon("agecraft:ores/" + getUnlocalizedName2());
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int metadata) {
		return Block.stone.getIcon(side, metadata);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getOverlayTextureForBlock(int side, int metadata) {
		return oreOverlay;
	}
}
