package elcon.mods.agecraft.core.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import elcon.mods.agecraft.core.TreeType;
import elcon.mods.agecraft.core.Trees;

public class BlockLeaves extends BlockMetadata {

	public boolean graphicsLevel = true;
	public int[] adjacentTreeBlocks;

	public BlockLeaves(int i) {
		super(i, Material.leaves);
		setTickRandomly(true);
		setHardness(0.2F);
		setLightOpacity(1);
		setStepSound(Block.soundGrassFootstep);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random random) {
		if(!world.isRemote) {
			int meta = world.getBlockMetadata(x, y, z);
			int state = meta & 3;
			int type = (meta - (state)) / 4;
			
			if((state & 2) != 0 && (state & 1) == 0) {
				byte r = 4;
				int radius = r + 1;
				byte size = 32;
				int j1 = size * size;
				int k1 = size / 2;

				if(adjacentTreeBlocks == null) {
					adjacentTreeBlocks = new int[size * size * size];
				}

				int i1;
				if(world.checkChunksExist(x - radius, y - radius, z - radius, x + radius, y + radius, z + radius)) {
					int i;
					int j;
					int k;

					for(i1 = -r; i1 <= r; ++i1) {
						for(i = -r; i <= r; ++i) {
							for(j = -r; j <= r; ++j) {
								k = world.getBlockId(x + i1, y + i, z + j);
								Block block = Block.blocksList[k];
								if(block != null && block.canSustainLeaves(world, x + i1, y + i, z + j)) {
									adjacentTreeBlocks[(i1 + k1) * j1 + (i + k1) * size + j + k1] = 0;
								} else if(block != null && block.isLeaves(world, x + i1, y + i, z + j)) {
									adjacentTreeBlocks[(i1 + k1) * j1 + (i + k1) * size + j + k1] = -2;
								} else {
									adjacentTreeBlocks[(i1 + k1) * j1 + (i + k1) * size + j + k1] = -1;
								}
							}
						}
					}

					for(i1 = 1; i1 <= 4; ++i1) {
						for(i = -r; i <= r; ++i) {
							for(j = -r; j <= r; ++j) {
								for(k = -r; k <= r; ++k) {
									if(adjacentTreeBlocks[(i + k1) * j1 + (j + k1) * size + k + k1] == i1 - 1) {
										if(adjacentTreeBlocks[(i + k1 - 1) * j1 + (j + k1) * size + k + k1] == -2) {
											adjacentTreeBlocks[(i + k1 - 1) * j1 + (j + k1) * size + k + k1] = i1;
										}
										if(adjacentTreeBlocks[(i + k1 + 1) * j1 + (j + k1) * size + k + k1] == -2) {
											adjacentTreeBlocks[(i + k1 + 1) * j1 + (j + k1) * size + k + k1] = i1;
										}
										if(adjacentTreeBlocks[(i + k1) * j1 + (j + k1 - 1) * size + k + k1] == -2) {
											adjacentTreeBlocks[(i + k1) * j1 + (j + k1 - 1) * size + k + k1] = i1;
										}
										if(adjacentTreeBlocks[(i + k1) * j1 + (j + k1 + 1) * size + k + k1] == -2) {
											adjacentTreeBlocks[(i + k1) * j1 + (j + k1 + 1) * size + k + k1] = i1;
										}
										if(adjacentTreeBlocks[(i + k1) * j1 + (j + k1) * size + (k + k1 - 1)] == -2) {
											adjacentTreeBlocks[(i + k1) * j1 + (j + k1) * size + (k + k1 - 1)] = i1;
										}
										if(adjacentTreeBlocks[(i + k1) * j1 + (j + k1) * size + k + k1 + 1] == -2) {
											adjacentTreeBlocks[(i + k1) * j1 + (j + k1) * size + k + k1 + 1] = i1;
										}
									}
								}
							}
						}
					}
				}
				i1 = adjacentTreeBlocks[k1 * j1 + k1 * size + k1];
				if(i1 >= 0) {
					world.setBlockMetadataWithNotify(x, y, z, meta - 2, 4);
				} else {
					removeLeaves(world, x, y, z);
				}
			}
		}
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, int par5, int meta) {
		byte r = 1;
		int radius = r + 1;

		if(world.checkChunksExist(x - radius, y - radius, z - radius, x + radius, y + radius, z + radius)) {
			for(int xx = -r; xx <= r; ++xx) {
				for(int yy = -r; yy <= r; ++yy) {
					for(int zz = -r; zz <= r; ++zz) {
						int id = world.getBlockId(x + xx, y + yy, z + zz);

						if(Block.blocksList[id] != null) {
							Block.blocksList[id].beginLeavesDecay(world, x + xx, y + yy, z + zz);
						}
					}
				}
			}
		}
	}

	private void removeLeaves(World world, int x, int y, int z) {
		dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
		world.setBlockToAir(x, y, z);
	}

	@Override
	public int idDropped(int i, Random random, int j) {
		return Trees.sapling.blockID;
	}

	@Override
	public int quantityDropped(Random random) {
		return random.nextInt(20) == 0 ? 1 : 0;
	}

	@Override
	public int damageDropped(int i) {
		return (i - (i & 3)) / 4;
	}

	@Override
	public void dropBlockAsItemWithChance(World world, int x, int y, int z, int meta, float f, int fortune) {
		if(!world.isRemote) {
			int state = meta & 3;
			int type = (meta - (state)) / 4;
			int chance = 20;

			if(TreeType.values()[type] == TreeType.JUNGLE) {
				chance = 40;
			}
			if(fortune > 0) {
				chance -= 2 << fortune;
				if(chance < 10) {
					chance = 10;
				}
			}

			if(world.rand.nextInt(chance) == 0) {
				int id = idDropped(meta, world.rand, fortune);
				dropBlockAsItem_do(world, x, y, z, new ItemStack(id, 1, damageDropped(meta)));
			}

			chance = 200;
			if(fortune > 0) {
				chance -= 10 << fortune;
				if(chance < 40) {
					chance = 40;
				}
			}

			if(TreeType.values()[type] == TreeType.OAK && world.rand.nextInt(chance) == 0) {
				dropBlockAsItem_do(world, x, y, z, new ItemStack(Item.appleRed, 1, 0));
			}
		}
	}

	@Override
	public void harvestBlock(World world, EntityPlayer player, int x, int y, int z, int meta) {
		super.harvestBlock(world, player, x, y, z, meta);
	}

	@Override
	public void beginLeavesDecay(World world, int x, int y, int z) {
		world.setBlockMetadataWithNotify(x, y, z, world.getBlockMetadata(x, y, z) | 2, 4);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getBlockColor() {
		double d0 = 0.5D;
		double d1 = 1.0D;
		return ColorizerFoliage.getFoliageColor(d0, d1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderColor(int meta) {
		int type = (meta - (meta & 3)) / 4;
		return TreeType.values()[type] == TreeType.SPRUCE ? ColorizerFoliage.getFoliageColorPine() : (TreeType.values()[type] == TreeType.BIRCH ? ColorizerFoliage.getFoliageColorBirch() : ColorizerFoliage.getFoliageColorBasic());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess blockAccess, int x, int y, int z) {
		int meta = blockAccess.getBlockMetadata(x, y, z);
		int type = (meta - (meta & 3)) / 4;

		if(TreeType.values()[type] == TreeType.BIRCH) {
			return ColorizerFoliage.getFoliageColorBirch();
		} else if(TreeType.values()[type] == TreeType.SPRUCE) {
			return ColorizerFoliage.getFoliageColorPine();
		} else {
			int r = 0;
			int g = 0;
			int b = 0;

			for(int xx = -1; xx <= 1; ++xx) {
				for(int zz = -1; zz <= 1; ++zz) {
					int color = blockAccess.getBiomeGenForCoords(x + zz, z + xx).getBiomeFoliageColor();
					r += (color & 16711680) >> 16;
					g += (color & 65280) >> 8;
					b += color & 255;
				}
			}
			return (r / 9 & 255) << 16 | (g / 9 & 255) << 8 | b / 9 & 255;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random random) {
		if(world.canLightningStrikeAt(x, y + 1, z) && !world.doesBlockHaveSolidTopSurface(x, y - 1, z) && random.nextInt(15) == 1) {
			double d0 = (double) ((float) x + random.nextFloat());
			double d1 = (double) y - 0.05D;
			double d2 = (double) ((float) z + random.nextFloat());
			world.spawnParticle("dripWater", d0, d1, d2, 0.0D, 0.0D, 0.0D);
		}
	}

	@SideOnly(Side.CLIENT)
	public void setGraphicsLevel(boolean graphicsLevel) {
		this.graphicsLevel = graphicsLevel;
	}

	@Override
	public boolean isOpaqueCube() {
		return !graphicsLevel;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess blockAccess, int x, int y, int z, int side) {
		int id = blockAccess.getBlockId(x, y, z);
		return !graphicsLevel && id == blockID ? false : super.shouldSideBeRendered(blockAccess, x, y, z, side);
	}

	@Override
	public String getLocalizedName(ItemStack stack) {
		return LanguageRegistry.instance().getStringLocalization(getUnlocalizedName(stack)) + " " + LanguageRegistry.instance().getStringLocalization("trees.leaves");
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return "trees." + TreeType.values()[(stack.getItemDamage() - (stack.getItemDamage() & 3)) / 4].name;
	}

	@Override
	public boolean isLeaves(World world, int x, int y, int z) {
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
		int type = (meta - (meta & 3)) / 4;
		return graphicsLevel ? TreeType.values()[type].leaves : TreeType.values()[type].leavesFast;
	}
}
