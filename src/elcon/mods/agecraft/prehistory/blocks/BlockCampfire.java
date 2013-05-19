package elcon.mods.agecraft.prehistory.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import elcon.mods.agecraft.prehistory.PrehistoryAge;
import elcon.mods.agecraft.prehistory.tileentities.TileEntityCampfire;

public class BlockCampfire extends BlockContainer {

	public boolean onFire = false;
	
	public BlockCampfire(int i, boolean on) {
		super(i, Material.wood);
		onFire = on;
		setLightValue(onFire ? 1.0F : 0.0F);
		setStepSound(Block.soundWoodFootstep);
		setHardness(2.0F);
		setResistance(5.0F);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityCampfire();
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		super.onEntityCollidedWithBlock(world, x, y, z, entity);
		if(onFire) {
			entity.setFire(10);
		}
	}
	
	@Override
	public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player) {
		super.onBlockClicked(world, x, y, z, player);
		if(onFire) {
			player.setFire(5);
		}
	}
	
	@Override
	public int idDropped(int i, Random random, int j) {
		return PrehistoryAge.campfireOff.blockID;
	}
}
