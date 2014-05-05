package comdotgame.com.search;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity te = world.getTileEntity(x, y, z);
		
		if(te != null) {
			switch(ID) {
				case 0:
					if(te instanceof TileEntityBrockFurnace) {
						return new ContainerBrockFurnace(player.inventory, (TileEntityBrockFurnace)te);
					}
			}
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity te = world.getTileEntity(x, y, z);
		
		if(te != null) {
			switch(ID) {
				case 0:
					if(te instanceof TileEntityBrockFurnace) {
						return new GuiBrockFurnace(player.inventory, (TileEntityBrockFurnace)te);
					}
			}
		}
		return null;
	}

}
