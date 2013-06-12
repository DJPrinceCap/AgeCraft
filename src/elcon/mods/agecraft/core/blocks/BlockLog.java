package elcon.mods.agecraft.core.blocks;

import java.util.List;

import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import elcon.mods.agecraft.core.TreeType;
import elcon.mods.agecraft.core.Trees;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockLog extends BlockMetadata {

	public BlockLog(int i) {
		super(i, Material.wood);
		setHardness(2.0F);
		setStepSound(Block.soundWoodFootstep);
		setBlockBounds(0.35F, 0.0F, 0.35F, 0.65F, 1.0F, 0.65F);
	}
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess blockAccess, int x, int y, int z) {
		float minX = 0.35F;
		float minY = 0.0F;
		float minZ = 0.35F;
		float maxX = 0.65F;
		float maxY = 0.75F;
		float maxZ = 0.65F;
		
		if(isConnected(blockAccess, x - 1, y, z, false)) {
			minX = 0.0F;
		}
		if(isConnected(blockAccess, x + 1, y, z, false)) {
			maxX = 1.0F;
		}
		if(isConnected(blockAccess, x, y - 1, z, false)) {
			minY = 0.0F;
		}
		if(isConnected(blockAccess, x, y + 1, z, true)) {
			maxY = 1.0F;
		}
		if(isConnected(blockAccess, x, y, z - 1, false)) {
			minZ = 0.0F;
		}
		if(isConnected(blockAccess, x, y, z + 1, false)) {
			maxZ = 1.0F;
		}
		setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
	}
	
	public boolean isConnected(IBlockAccess blockAccess, int x, int y, int z, boolean leaves) {
		if(blockAccess.getBlockId(x, y, z) == blockID || (leaves && blockAccess.getBlockId(x, y, z) == Trees.leaves.blockID)) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
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
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess blockAccess, int x, int y, int z, int side) {
		return true;
	}
	
	@Override
	public String getLocalizedName(ItemStack stack) {
		return LanguageRegistry.instance().getStringLocalization(getUnlocalizedName(stack)) + " " + LanguageRegistry.instance().getStringLocalization("trees.log");
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return "trees." + TreeType.values()[stack.getItemDamage()].name;
	}

	@Override
	public int damageDropped(int i) {
		return i;
	}

	@Override
	public boolean canSustainLeaves(World world, int x, int y, int z) {
		return true;
	}

	@Override
	public boolean isWood(World world, int x, int y, int z) {
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int id, CreativeTabs creativeTabs, List list) {
		for(int i = 0; i < TreeType.values().length; i++) {
			list.add(new ItemStack(id, 1, i));
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		if(side == 0 || side == 1) {
			return TreeType.values()[meta].woodTop;
		} 
		return TreeType.values()[meta].wood;
	}
}
