package elcon.mods.agecraft.prehistory.gui;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import elcon.mods.agecraft.prehistory.PrehistoryAge;
import elcon.mods.agecraft.prehistory.SharpenerRecipe;
import elcon.mods.agecraft.prehistory.SharpenerRecipes;

public class ContainerSharpener extends Container {
	
	public EntityPlayer player;
	public boolean[] blocks = new boolean[64];
	public InventorySharpener inventory;
	boolean initDone = false;
	boolean rockRemoved = false;
	
	public ContainerSharpener(EntityPlayer p, InventorySharpener inv) {
		player = p;
		inventory = inv;
		inv.setContainer(this);

		addSlotToContainer(new SlotStoneInput(inventory, 0, 150, 135));
		addSlotToContainer(new SlotStoneOutput(inventory, 1, 151, 75));
		
		inventory.setInventorySlotContents(0, new ItemStack(PrehistoryAge.rock));
		
		for(int i = 0; i < 64; i++) {
			blocks[i] = true;
			int xx = i % 8;
			int yy = (int) Math.floor(i / 8);
			addSlotToContainer(new SlotStone(inventory, 2 + i, 7 + (16 * xx) + xx, 15 + (16 * yy) + yy));
		}		
		initDone = true;
	}

	@Override
	public void onCraftMatrixChanged(IInventory inv) {
		super.onCraftMatrixChanged(inv);
		if(!rockRemoved) {
			player.inventory.consumeInventoryItem(PrehistoryAge.rock.itemID);
			rockRemoved = true;
		}		
		if(initDone && inventory.getStackInSlot(1) == null) {
			SharpenerRecipe r = SharpenerRecipes.getRecipe(blocks);
			if(r != null) {
				Random rand = new Random();
				inventory.setInventorySlotContents(1, new ItemStack(r.output.itemID, 1, rand.nextInt(8)));
			} else {
				inventory.setInventorySlotContents(1, null);
			}
		}
	}
	
	@Override
	public ItemStack slotClick(int par1, int par2, int par3, EntityPlayer par4EntityPlayer) {
		return super.slotClick(par1, par2, par3, par4EntityPlayer);
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}
	
	@Override
	public void onCraftGuiClosed(EntityPlayer player) {
		super.onCraftGuiClosed(player);
		if(!rockRemoved) {
			player.inventory.consumeInventoryItem(PrehistoryAge.rock.itemID);
			rockRemoved = true;
		}	
		if(inventory.getStackInSlot(1) != null) {
			player.inventory.addItemStackToInventory(inventory.getStackInSlot(1));
		}
	}
}
