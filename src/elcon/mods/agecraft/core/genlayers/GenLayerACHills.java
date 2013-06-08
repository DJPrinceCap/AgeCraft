package elcon.mods.agecraft.core.genlayers;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import elcon.mods.agecraft.prehistory.biomes.BiomeGenACPrehistory;

public class GenLayerACHills extends GenLayer {
	
	public GenLayerACHills(long seed, GenLayer genLayer) {
		super(seed);
		parent = genLayer;
	}

	@Override
	public int[] getInts(int par1, int par2, int par3, int par4) {
		int[] var5 = parent.getInts(par1 - 1, par2 - 1, par3 + 2, par4 + 2);
		int[] var6 = IntCache.getIntCache(par3 * par4);

		for(int var7 = 0; var7 < par4; ++var7) {
			for(int var8 = 0; var8 < par3; ++var8) {
				initChunkSeed((long) (var8 + par1), (long) (var7 + par2));
				int var9 = var5[var8 + 1 + (var7 + 1) * (par3 + 2)];

				if(nextInt(3) == 0) {
					int var10 = var9;

					/*if(var9 == BiomeGenBase.desert.biomeID) {
						var10 = BiomeGenBase.desertHills.biomeID;
					} else if(var9 == BiomeGenBase.forest.biomeID) {
						var10 = BiomeGenBase.forestHills.biomeID;
					} else if(var9 == BiomeGenBase.taiga.biomeID) {
						var10 = BiomeGenBase.taigaHills.biomeID;
					} else if(var9 == BiomeGenBase.plains.biomeID) {
						var10 = BiomeGenBase.forest.biomeID;
					} else if(var9 == BiomeGenBase.icePlains.biomeID) {
						var10 = BiomeGenBase.iceMountains.biomeID;
					} else if(var9 == BiomeGenBase.jungle.biomeID) {
						var10 = BiomeGenBase.jungleHills.biomeID;
					}*/	
					if(var9 == BiomeGenACPrehistory.prehistoryPlains.biomeID) {
						var10 = BiomeGenACPrehistory.prehistoryPlainsHills.biomeID;
					} else if(var9 == BiomeGenACPrehistory.prehistorySnow.biomeID) {
						var10 = BiomeGenACPrehistory.prehistoryGlacier.biomeID;
					}

					if(var10 == var9) {
						var6[var8 + var7 * par3] = var9;
					} else {
						int var11 = var5[var8 + 1 + (var7 + 1 - 1) * (par3 + 2)];
						int var12 = var5[var8 + 1 + 1 + (var7 + 1) * (par3 + 2)];
						int var13 = var5[var8 + 1 - 1 + (var7 + 1) * (par3 + 2)];
						int var14 = var5[var8 + 1 + (var7 + 1 + 1) * (par3 + 2)];

						if(var11 == var9 && var12 == var9 && var13 == var9 && var14 == var9) {
							var6[var8 + var7 * par3] = var10;
						} else {
							var6[var8 + var7 * par3] = var9;
						}
					}
				} else {
					var6[var8 + var7 * par3] = var9;
				}
			}
		}
		return var6;
	}
}
