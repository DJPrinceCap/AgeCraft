package elcon.mods.agecraft.structure;

import elcon.mods.agecraft.util.INBTTagable;

public abstract interface IStructureLogic extends INBTTagable {

	public abstract String getTypeUID();

	public abstract void validateStructure();
}
