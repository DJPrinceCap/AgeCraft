package elcon.mods.agecraft.prehistory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.WorldTypeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import elcon.mods.agecraft.core.genlayers.GenLayerAC;
import elcon.mods.agecraft.prehistory.biomes.BiomeGenACPrehistory;

public class PrehistoryChunkManager extends WorldChunkManager {
	
	private GenLayer genBiomes;
	private GenLayer biomeIndexLayer;
	private BiomeCache biomeCache;

	private List biomesToSpawnIn;

	protected PrehistoryChunkManager() {
		biomeCache = new BiomeCache(this);
		biomesToSpawnIn = new ArrayList();
		biomesToSpawnIn.add(BiomeGenACPrehistory.prehistoryPlains);
	}

	public PrehistoryChunkManager(long seed, WorldType worldType) {
		this();
		GenLayer[] genlayer = GenLayerAC.createPrehistoryAge(seed, worldType);
		genlayer = getModdedBiomeGenerators(worldType, seed, genlayer);
		genBiomes = genlayer[0];
		biomeIndexLayer = genlayer[1];
	}

	public PrehistoryChunkManager(World world) {
		this(world.getSeed(), world.getWorldInfo().getTerrainType());
	}

	public List getBiomesToSpawnIn() {
		return biomesToSpawnIn;
	}

	public BiomeGenBase getBiomeGenAt(int par1, int par2) {
		return biomeCache.getBiomeGenAt(par1, par2);
	}

	public float[] getRainfall(float[] par1ArrayOfFloat, int par2, int par3, int par4, int par5) {
		IntCache.resetIntCache();

		if(par1ArrayOfFloat == null || par1ArrayOfFloat.length < par4 * par5) {
			par1ArrayOfFloat = new float[par4 * par5];
		}

		int[] aint = biomeIndexLayer.getInts(par2, par3, par4, par5);

		for(int i1 = 0; i1 < par4 * par5; ++i1) {
			float f = (float) BiomeGenBase.biomeList[aint[i1]].getIntRainfall() / 65536.0F;

			if(f > 1.0F) {
				f = 1.0F;
			}

			par1ArrayOfFloat[i1] = f;
		}

		return par1ArrayOfFloat;
	}

	@SideOnly(Side.CLIENT)
	public float getTemperatureAtHeight(float par1, int par2) {
		return par1;
	}

	public float[] getTemperatures(float[] par1ArrayOfFloat, int par2, int par3, int par4, int par5) {
		IntCache.resetIntCache();

		if(par1ArrayOfFloat == null || par1ArrayOfFloat.length < par4 * par5) {
			par1ArrayOfFloat = new float[par4 * par5];
		}

		int[] aint = biomeIndexLayer.getInts(par2, par3, par4, par5);

		for(int i1 = 0; i1 < par4 * par5; ++i1) {
			float f = (float) BiomeGenBase.biomeList[aint[i1]].getIntTemperature() / 65536.0F;

			if(f > 1.0F) {
				f = 1.0F;
			}

			par1ArrayOfFloat[i1] = f;
		}

		return par1ArrayOfFloat;
	}

	public BiomeGenBase[] getBiomesForGeneration(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5) {
		IntCache.resetIntCache();

		if(par1ArrayOfBiomeGenBase == null || par1ArrayOfBiomeGenBase.length < par4 * par5) {
			par1ArrayOfBiomeGenBase = new BiomeGenBase[par4 * par5];
		}

		int[] aint = genBiomes.getInts(par2, par3, par4, par5);

		for(int i1 = 0; i1 < par4 * par5; ++i1) {
			par1ArrayOfBiomeGenBase[i1] = BiomeGenBase.biomeList[aint[i1]];
		}

		return par1ArrayOfBiomeGenBase;
	}

	public BiomeGenBase[] loadBlockGeneratorData(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5) {
		return getBiomeGenAt(par1ArrayOfBiomeGenBase, par2, par3, par4, par5, true);
	}

	public BiomeGenBase[] getBiomeGenAt(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5, boolean par6) {
		IntCache.resetIntCache();

		if(par1ArrayOfBiomeGenBase == null || par1ArrayOfBiomeGenBase.length < par4 * par5) {
			par1ArrayOfBiomeGenBase = new BiomeGenBase[par4 * par5];
		}

		if(par6 && par4 == 16 && par5 == 16 && (par2 & 15) == 0 && (par3 & 15) == 0) {
			BiomeGenBase[] abiomegenbase1 = biomeCache.getCachedBiomes(par2, par3);
			System.arraycopy(abiomegenbase1, 0, par1ArrayOfBiomeGenBase, 0, par4 * par5);
			return par1ArrayOfBiomeGenBase;
		} else {
			int[] aint = biomeIndexLayer.getInts(par2, par3, par4, par5);

			for(int i1 = 0; i1 < par4 * par5; ++i1) {
				par1ArrayOfBiomeGenBase[i1] = BiomeGenBase.biomeList[aint[i1]];
			}

			return par1ArrayOfBiomeGenBase;
		}
	}

	public boolean areBiomesViable(int par1, int par2, int par3, List par4List) {
		IntCache.resetIntCache();
		int l = par1 - par3 >> 2;
		int i1 = par2 - par3 >> 2;
		int j1 = par1 + par3 >> 2;
		int k1 = par2 + par3 >> 2;
		int l1 = j1 - l + 1;
		int i2 = k1 - i1 + 1;
		int[] aint = genBiomes.getInts(l, i1, l1, i2);

		for(int j2 = 0; j2 < l1 * i2; ++j2) {
			BiomeGenBase biomegenbase = BiomeGenBase.biomeList[aint[j2]];

			if(!par4List.contains(biomegenbase)) {
				return false;
			}
		}

		return true;
	}

	public ChunkPosition findBiomePosition(int par1, int par2, int par3, List par4List, Random par5Random) {
		IntCache.resetIntCache();
		int l = par1 - par3 >> 2;
		int i1 = par2 - par3 >> 2;
		int j1 = par1 + par3 >> 2;
		int k1 = par2 + par3 >> 2;
		int l1 = j1 - l + 1;
		int i2 = k1 - i1 + 1;
		int[] aint = genBiomes.getInts(l, i1, l1, i2);
		ChunkPosition chunkposition = null;
		int j2 = 0;

		for(int k2 = 0; k2 < l1 * i2; ++k2) {
			int l2 = l + k2 % l1 << 2;
			int i3 = i1 + k2 / l1 << 2;
			BiomeGenBase biomegenbase = BiomeGenBase.biomeList[aint[k2]];

			if(par4List.contains(biomegenbase) && (chunkposition == null || par5Random.nextInt(j2 + 1) == 0)) {
				chunkposition = new ChunkPosition(l2, 0, i3);
				++j2;
			}
		}

		return chunkposition;
	}

	public void cleanupCache() {
		biomeCache.cleanupCache();
	}

	public GenLayer[] getModdedBiomeGenerators(WorldType worldType, long seed, GenLayer[] original) {
		WorldTypeEvent.InitBiomeGens event = new WorldTypeEvent.InitBiomeGens(worldType, seed, original);
		MinecraftForge.TERRAIN_GEN_BUS.post(event);
		return event.newBiomeGens;
	}
}
