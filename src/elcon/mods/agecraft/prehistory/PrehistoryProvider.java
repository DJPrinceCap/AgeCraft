package elcon.mods.agecraft.prehistory;

import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkProvider;

public class PrehistoryProvider extends WorldProvider {

	public PrehistoryProvider() {
		setDimension(10);
	}
	
	@Override
	protected void registerWorldChunkManager() {
		worldChunkMgr = new PrehistoryChunkManager(worldObj);
	}
	
	@Override
	public IChunkProvider createChunkGenerator() {
		return new PrehistoryChunkProvider(worldObj, worldObj.getSeed(), worldObj.getWorldInfo().isMapFeaturesEnabled());
	}	
	
	@Override
	public boolean canRespawnHere() {
		return true;
	}
	
	@Override
	public boolean isSurfaceWorld() {
		return true;
	}
	
	@Override
	public String getDimensionName() {
		return "Prehistory";
	}
}
