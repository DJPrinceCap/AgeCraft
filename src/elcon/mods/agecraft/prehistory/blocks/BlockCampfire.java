package elcon.mods.agecraft.prehistory.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import elcon.mods.agecraft.prehistory.Campfire;
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
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.3F, 1.0F);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityCampfire();
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int i, float f1, float f2, float f3) {
		if(player.inventory.getCurrentItem() != null) {
			if(player.inventory.getCurrentItem().itemID == Item.flintAndSteel.itemID) {
				TileEntityCampfire tile = (TileEntityCampfire) world.getBlockTileEntity(x, y, z);
				if(tile != null) {
					if(tile.fuel > 0) {
						tile.canBurn = true;
						tile.hasFuel = true;
						tile.isBurning = true;
						tile.tick = 30;
						player.inventory.getCurrentItem().setItemDamage(player.inventory.getCurrentItem().getItemDamage() + 1);
						updateCampfireBlockState(true, world, x, y, z);
					}
					return true;
				} else {
					world.setBlockTileEntity(x, y, z, new TileEntityCampfire());
				}
			} else {
				if(Campfire.isFuel(player.inventory.getCurrentItem())) {
					int value = Campfire.getFuel(player.inventory.getCurrentItem().itemID);
					if(value > 0) {
						TileEntityCampfire tile = (TileEntityCampfire) world.getBlockTileEntity(x, y, z);
						if(tile != null) {
							if(tile.isBurning) {
								tile.fuel += value;
								tile.canBurn = true;
								tile.isBurning = true;
								tile.hasFuel = true;
								player.inventory.consumeInventoryItem(player.inventory.getCurrentItem().itemID);
								return true;
							} else {
								tile.fuel += value;
								tile.hasFuel = true;
								tile.canBurn = true;
								player.inventory.consumeInventoryItem(player.inventory.getCurrentItem().itemID);
								return true;
							}
						} else {
							world.setBlockTileEntity(x, y, z, new TileEntityCampfire());
						}
					}
				}
			}
		}
		return false;
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
		return 100;
	}
	
	public static void updateCampfireBlockState(boolean enabled, World world, int x, int y, int z) {
		TileEntity tile = world.getBlockTileEntity(x, y, z);
		if(enabled) {
			world.setBlock(x, y, z, PrehistoryAge.campfireOn.blockID);
		} else {
			world.setBlock(x, y, z, PrehistoryAge.campfireOff.blockID);
		}
		
		if(tile != null) {
			tile.validate();
			world.setBlockTileEntity(x, y, z, tile);
		}
	}
}
