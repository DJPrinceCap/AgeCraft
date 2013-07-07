package elcon.mods.agecraft.prehistory.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class ItemRockTanningTool extends Item {

	private Icon icon;
	
	public ItemRockTanningTool(int i) {
		super(i);
		setMaxDamage(32);
		setMaxStackSize(1);
		setDamage(new ItemStack(this), 1);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int par1) {
		return icon;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		icon = iconRegister.registerIcon("agecraft:ages/prehistory/rock_tanning_tool");
	}
	
	@Override
	public boolean canHarvestBlock(Block par1Block) {
		return false;
	}
	
	@Override
	public float getStrVsBlock(ItemStack par1ItemStack, Block par2Block) {
		return 0.0F;
	}
}
