package elcon.mods.agecraft.core.blocks;

import java.util.List;

import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import elcon.mods.agecraft.core.TreeType;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockWood extends BlockMetadata {

	public BlockWood(int i) {
		super(i, Material.wood);
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public int getRenderType() {
		return 100;
	}

	@Override
	public String getLocalizedName(ItemStack stack) {
		return LanguageRegistry.instance().getStringLocalization("trees.wood." + TreeType.values()[(stack.getItemDamage() - (stack.getItemDamage() & 3)) / 4].name);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return "trees.wood." + TreeType.values()[(stack.getItemDamage() - (stack.getItemDamage() & 3)) / 4].name;
	}

	@Override
	public int damageDropped(int i) {
		return (i - (i & 3)) / 4;
	}

	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float xx, float yy, float zz, int meta) {
		switch(side) {
		case 0:
		case 1:
			return meta;
		case 2:
		case 3:
			return meta | 1;
		case 4:
		case 5:
			return meta | 2;
		}
		return meta;
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
			list.add(new ItemStack(id, 1, i * 4));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		int direction = meta & 3;
		int type = (meta - direction) / 4;
		if(direction == 0) {
			return side == 0 || side == 1 ? TreeType.values()[type].woodTop : TreeType.values()[type].wood;
		} else if(direction == 1) {
			return side == 2 || side == 3 ? TreeType.values()[type].woodTop : TreeType.values()[type].wood;
		} else if(direction == 2) {
			return side == 4 || side == 5 ? TreeType.values()[type].woodTop : TreeType.values()[type].wood;
		}
		return TreeType.values()[type].wood;
	}
}
