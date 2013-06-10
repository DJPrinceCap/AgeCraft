package elcon.mods.agecraft.core.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import elcon.mods.agecraft.core.TreeType;

public class BlockPlanks extends BlockMetadata {

	public BlockPlanks(int i) {
		super(i, Material.wood);
		setHardness(2.0F);
		setResistance(5.0F);
		setStepSound(Block.soundWoodFootstep);
	}
	
	@Override
	public String getLocalizedName(ItemStack stack) {
		return LanguageRegistry.instance().getStringLocalization(getUnlocalizedName(stack)) + " " + LanguageRegistry.instance().getStringLocalization("trees.planks");
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
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int id, CreativeTabs creativeTabs, List list) {
		for(int i = 0; i < TreeType.values().length; i++) {
			list.add(new ItemStack(id, 1, i));
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		return TreeType.values()[meta].planks;
	}
}
