package elcon.mods.agecraft.prehistory.blocks;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import elcon.mods.agecraft.prehistory.PrehistoryAge;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockRock extends Block {

	private Icon icon;
	
	public BlockRock(int i) {
		super(i, Material.rock);
		setHardness(0.4F);
		setStepSound(Block.soundStoneFootstep);
		setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 0.2F, 0.75F);
	}
	
	@Override
	public boolean canHarvestBlock(EntityPlayer player, int meta) {
		return true;
	}
	
	@Override
	public int idDropped(int i, Random random, int j) {
		return PrehistoryAge.rock.itemID;
	}
	
	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		int i = world.getBlockId(x, y, z);
		if(i != 0 && i != blockID && world.isBlockOpaqueCube(x, y, z)) {
			return true;
		}
		return false;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int i, int j) {
		return icon;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		icon = iconRegister.registerIcon("agecraft:ages/prehistory/rock");
	}
	
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	@Override
	public int getRenderType() {
		return 101;
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
}
