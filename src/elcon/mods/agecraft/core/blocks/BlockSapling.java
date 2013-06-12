package elcon.mods.agecraft.core.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import elcon.mods.agecraft.core.TreeType;

public class BlockSapling extends BlockPlantMetadata {

	public BlockSapling(int i) {
		super(i);
		setHardness(0.0F);
		setStepSound(Block.soundGrassFootstep);
		setBlockBounds(0.1F, 0.0F, 0.1F, 0.9F, 0.8F, 0.9F);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random random) {
		if(!world.isRemote) {
			super.updateTick(world, x, y, z, random);
			int chance = 8;
			if(world.isRaining()) {
				chance /= 2;
			}
			if(world.getBlockLightValue(x, y + 1, z) >= 9 && random.nextInt(chance) == 0 || true) {
				markOrGrow(world, x, y, z, random);
			}
		}
	}

	public void markOrGrow(World world, int x, int y, int z, Random random) {
		int meta = world.getBlockMetadata(x, y, z);
		int state = meta & 3;

		if(state == 0 || state == 1) {
			world.setBlockMetadataWithNotify(x, y, z, state + 1, 2);
		} else {
			growTree(world, x, y, z, meta, random);
		}
	}
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess blockAccess, int x, int y, int z) {
		int meta = blockAccess.getBlockMetadata(x, y, z);
		int state = meta & 3;
		if(state == 0 || state == 1) {
			setBlockBounds(0.3F, 0.0F, 0.3F, 0.7F, 0.6F, 0.7F);
		} else {
			setBlockBounds(0.1F, 0.0F, 0.1F, 0.9F, 0.8F, 0.9F);
		}
	}

	public boolean isSameSapling(World world, int x, int y, int z, int blockMeta) {
		int meta = world.getBlockMetadata(x, y, z);
		return world.getBlockId(x, y, z) == blockID && (meta - (meta & 3)) / 4 == blockMeta;
	}
	
	public void growTree(World world, int x, int y, int z, int meta, Random random) {
		
	}

	@Override
	public String getLocalizedName(ItemStack stack) {
		return LanguageRegistry.instance().getStringLocalization(getUnlocalizedName(stack)) + " " + LanguageRegistry.instance().getStringLocalization("trees.sapling");
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return "trees." + TreeType.values()[(stack.getItemDamage() - (stack.getItemDamage() & 3)) / 4].name;
	}

	@Override
	public int damageDropped(int i) {
		return (i - (i & 3)) / 4;
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
		int state = meta & 3;
		int type = (meta - state) / 4;
		if(state == 0) {
			return TreeType.values()[type].smallSapling;
		}
		return TreeType.values()[type].sapling;
	}
}
