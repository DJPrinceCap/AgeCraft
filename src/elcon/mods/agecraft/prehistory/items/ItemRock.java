package elcon.mods.agecraft.prehistory.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import elcon.mods.agecraft.AgeCraft;
import elcon.mods.agecraft.prehistory.PrehistoryAge;

public class ItemRock extends ItemBlock {

	private Icon icon;
	
	public ItemRock(int i) {
		super(i);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		if(!world.isRemote) {
			if(player.isSneaking()) {
				if(player.inventory.hasItemStack(new ItemStack(PrehistoryAge.rock.itemID, 2, 0))) {
					player.openGui(AgeCraft.instance, 10, world, (int) player.posX, (int) player.posY, (int) player.posZ);
					return stack;
				}
			}
		}
		return stack;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getSpriteNumber() {
		return 1;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int i) {
		return icon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		icon = iconRegister.registerIcon("agecraft:ages/prehistory/rock");
	}
}
