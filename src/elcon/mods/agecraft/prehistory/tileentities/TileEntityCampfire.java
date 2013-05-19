package elcon.mods.agecraft.prehistory.tileentities;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import elcon.mods.agecraft.prehistory.blocks.BlockCampfire;

public class TileEntityCampfire extends TileEntity {

	public int tick = 0;
	public int timeLeft = 0;
	public int fuel = 0;
	public boolean hasFuel = false;
	public boolean isBurning = false;
	public boolean canBurn = false;
	
	@Override
	public Packet getDescriptionPacket() {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(bos);
		try {
			dos.writeByte(100);
			dos.writeInt(xCoord);
			dos.writeInt(yCoord);
			dos.writeInt(zCoord);

			dos.writeInt(tick);
			dos.writeInt(timeLeft);
			dos.writeInt(fuel);
			
			dos.writeBoolean(hasFuel);
			dos.writeBoolean(isBurning);
			dos.writeBoolean(canBurn);
		} catch(Exception e) {
			e.printStackTrace();
		}
		Packet250CustomPayload pkt = new Packet250CustomPayload();
		pkt.channel = "ACTile";
		pkt.data = bos.toByteArray();
		pkt.length = bos.size();
		pkt.isChunkDataPacket = true;
		return pkt;
	}
	
	@Override
	public boolean canUpdate() {
		return true;
	}
	
	@Override
	public void updateEntity() {
		super.updateEntity();
		if(isBurning && tick % 7 == 0) {
			double d = xCoord + 0.5F;
		    double d1 = yCoord + 0.37F;
		    double d2 = zCoord + 0.5F;
		    double d3 = 0.22D;
		    double d4 = 0.27D;
		    worldObj.spawnParticle("smoke", d, d1, d2, 0.0D, 0.0D, 0.0D);
		    worldObj.spawnParticle("flame", d + 0.1D, d1, d2, 0.0D, 0.0D, 0.0D);
		    worldObj.spawnParticle("flame", d, d1, d2 + 0.1D, 0.0D, 0.0D, 0.0D);
		    worldObj.spawnParticle("flame", d, d1, d2 - 0.1D, 0.0D, 0.0D, 0.0D);
		    worldObj.spawnParticle("flame", d - 0.1D, d1, d2, 0.0D, 0.0D, 0.0D);
		    worldObj.spawnParticle("smoke", d + 0.1D, d1, d2 + 0.1D, 0.0D, 0.0D, 0.0D);
		    worldObj.spawnParticle("smoke", d - 0.1D, d1, d2 - 0.1D, 0.0D, 0.0D, 0.0D);
		    worldObj.spawnParticle("flame", d, d1, d2, 0.0D, 0.0D, 0.0D);
		}
		if(canBurn) {
			if(tick == 30 && isBurning) {
				if(timeLeft > 0) {
					isBurning = true;
					timeLeft --;
				} else {
					timeLeft = 0;
					if(fuel > 0) {
						fuel--;
						isBurning = true;
						timeLeft = 30;
						if(fuel <= 0) {
							hasFuel = false;
							fuel = 0;
						}
					} else {
						fuel = 0;
						hasFuel = false;
						isBurning = false;
						canBurn = false;
						BlockCampfire.updateCampfireBlockState(false, worldObj, xCoord, yCoord, zCoord);
					}
				}
				tick = 0;
			} else {
				tick++;
			}
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		timeLeft = nbt.getInteger("TimeLeft");
		fuel = nbt.getInteger("Fuel");
		hasFuel = nbt.getBoolean("HasFuel");
		isBurning = nbt.getBoolean("Burning");
		canBurn = nbt.getBoolean("CanBurn");
	}	
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setInteger("TimeLeft", timeLeft);
		nbt.setInteger("Fuel", fuel);
		nbt.setBoolean("HasFuel", hasFuel);
		nbt.setBoolean("Burning", isBurning);
		nbt.setBoolean("CanBurn", canBurn);
	}
}
