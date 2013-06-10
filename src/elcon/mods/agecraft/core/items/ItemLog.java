package elcon.mods.agecraft.core.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import elcon.mods.agecraft.core.TreeType;
import elcon.mods.agecraft.core.blocks.BlockMetadata;

public class ItemLog extends ItemBlockMetadata {
	
	public ItemLog(BlockMetadata block) {
		super(block);
		setMaxStackSize(16);
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float xx, float yy, float zz) {
		return false;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int meta) {
		return TreeType.values()[meta].planks;
	}
}
