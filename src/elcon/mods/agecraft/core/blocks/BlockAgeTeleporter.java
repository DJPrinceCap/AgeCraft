package elcon.mods.agecraft.core.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import elcon.mods.agecraft.AgeCraft;
import elcon.mods.agecraft.core.AgeTeleport;
import elcon.mods.agecraft.core.TeleporterAC;

public class BlockAgeTeleporter extends BlockAgeTeleporterBlock {

	private Icon icon;
	private Icon iconFront;
	
	public BlockAgeTeleporter(int i) {
		super(i);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float xx, float yy, float zz) {
		if(!world.isRemote) {
			EntityPlayerMP playerMP = (EntityPlayerMP) player;
			if(playerMP != null) {
				if(playerMP.dimension < 10) {
					AgeTeleport.teleportList.put(player.username, AgeTeleport.create(world, x - 3, y - 3, z - 6));
					playerMP.mcServer.getConfigurationManager().transferPlayerToDimension(playerMP, 10, new TeleporterAC(playerMP.mcServer.worldServerForDimension(10), 10, true));
				} else {
					playerMP.mcServer.getConfigurationManager().transferPlayerToDimension(playerMP, 0, new TeleporterAC(playerMP.mcServer.worldServerForDimension(0), 0, true));
				}
			}
		}
		return false;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int side, int meta) {
		if(side == 2) {
			return iconFront;
		}
		return icon;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister) {
		icon = iconRegister.registerIcon("agecraft:ageTeleporterBlock");
		iconFront = iconRegister.registerIcon("agecraft:ageTeleporter");
		
		AgeCraft.instance.registerBlockIcons(iconRegister);
	}
}