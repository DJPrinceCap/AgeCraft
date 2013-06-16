package elcon.mods.agecraft.util;

import net.minecraft.nbt.NBTTagCompound;

public abstract interface INBTTagable {
	
	public abstract void readFromNBT(NBTTagCompound nbt);

	public abstract void writeToNBT(NBTTagCompound nbt);
}
