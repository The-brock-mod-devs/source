package comdotgame.com.search;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBrockCentrifuge extends TileEntity implements IInventory {
	
	public float rotation = 0.0f;
	public float speed = 0.01f;
	public float lidRot = -90.0f;
	public boolean spinning = false;
	public boolean hasLava = false;
	public boolean glassOpen = false;
	public float glassPos = 0.0f;
	
	public ItemStack[] inventory;
	
	private int facing = 0;
	
	public TileEntityBrockCentrifuge() {
		inventory = new ItemStack[5];
		inventory[0] = new ItemStack(MyLittlePony.brarge, 1);
		inventory[1] = new ItemStack(Blocks.bookshelf, 1);
		inventory[2] = new ItemStack(Blocks.bookshelf, 1);
		inventory[3] = new ItemStack(Items.diamond, 1);
		inventory[4] = new ItemStack(Blocks.melon_stem, 1);
	}
	
	public void setFacing(byte orientation) {

		this.facing = orientation;
		
	}
	
	public int getFacing()
	{
		return this.facing;
	}
	
	public void toggleSpin()
	{
		spinning = !spinning;
	}
	
	public void toggleGlass()
	{
		glassOpen = !glassOpen;
	}
	
	@Override
	public void updateEntity()
	{
		if(glassOpen) {
			if(glassPos < 90.0f) {
				glassPos += 2.5f;
			}
		} else {
			if(glassPos > 0.0f) {
				glassPos -= 2.5f;
			}
		}
		if(spinning) {
			if(lidRot == 0.0f) {
				rotation += speed;
				if(speed < 15f) {
					speed += 0.1f;
				}
			} else {
				lidRot -= 0.3f;
			}
		} else {
			if(speed > 0.01f) {
				rotation += speed;
				speed -= 0.1f;
			} else {
				if(lidRot < 6.0f) {
					lidRot += 0.3f;
				}
			}
		}
		if(lidRot > 6.0f) {
			lidRot = 6.0f;
		}
		if(lidRot < 0.0f) {
			lidRot = 0.0f;
		}
		if(glassPos > 90.0f) {
			glassPos = 90.0f;
		}
		if(glassPos < 0.0f) {
			glassPos = 0.0f;
		}
		if(speed < 0.01f) {
			speed = 0.01f;
		}
		if(speed > 15f) {
			speed = 15f;
		}
		if(rotation >= 360f) {
			rotation = rotation - 360;
		}
	}
	
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        rotation = nbt.getFloat("TopRotation");
        speed = nbt.getFloat("SpinSpeed");
        setFacing(nbt.getByte("Facing"));
        spinning = nbt.getBoolean("IsSpinning");
        lidRot = nbt.getFloat("LidRot");
        hasLava = nbt.getBoolean("HasLava");
        glassPos = nbt.getFloat("GlassPos");
        glassOpen = nbt.getBoolean("IsTopOpen");
        
        NBTTagList list = nbt.getTagList("Items", 10);
        
        for(int i = 0; i < list.tagCount(); i++) {
        	NBTTagCompound nbt2 = list.getCompoundTagAt(i);
        	byte b0 = nbt.getByte("Slot");
        	
        	if(b0 >= 0 && b0 < this.inventory.length) {
        		this.inventory[b0] = ItemStack.loadItemStackFromNBT(nbt2);
        	}
        }
    }

    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        nbt.setFloat("TopRotation", rotation);
        nbt.setFloat("SpinSpeed", speed);
        nbt.setByte("Facing", (byte)facing);
        nbt.setBoolean("IsSpinning", spinning);
        nbt.setFloat("LidRot", lidRot);
        nbt.setBoolean("HasLava", hasLava);
        nbt.setFloat("GlassPos", glassPos);
        nbt.setBoolean("IsTopOpen", glassOpen);
        
        NBTTagList list = new NBTTagList();
        
        for(int i = 0; i < this.inventory.length; i++) {
        	if(this.inventory[i] != null) {
        		NBTTagCompound nbt2 = new NBTTagCompound();
        		nbt.setByte("Slot", (byte)i);
        		this.inventory[i].writeToNBT(nbt2);
        		list.appendTag(nbt2);
        	}
        }
        
        nbt.setTag("Items", list);
    }
    
	public Packet getDescriptionPacket() {
		NBTTagCompound tag = new NBTTagCompound();
		this.writeToNBT(tag);
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tag);
	}
	
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
		readFromNBT(packet.func_148857_g());
	}

	@Override
	public int getSizeInventory() {
		return 5;
	}

	@Override
	public ItemStack getStackInSlot(int var1) {
		if(var1 < getSizeInventory()) return inventory[var1];
		return null;
	}

	@Override
	public ItemStack decrStackSize(int var1, int var2) {
		ItemStack ret = null;
		if(var1 < getSizeInventory() && inventory[var1] != null) {
			if(var2 >= inventory[var1].stackSize) {
				ret = inventory[var1].copy();
				inventory[var1] = null;
			} else {
				ret = inventory[var1].splitStack(var2);
			}
		}
		return ret;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int var1) {
		return getStackInSlot(var1);
	}

	@Override
	public void setInventorySlotContents(int var1, ItemStack var2) {
		if(var1 < getSizeInventory()) inventory[var1] = var2;
	}

	@Override
	public String getInventoryName() {
		return "Centrifuge";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer var1) {
		return true;
	}

	@Override
	public void openInventory() { }

	@Override
	public void closeInventory() { }

	@Override
	public boolean isItemValidForSlot(int var1, ItemStack var2) {
		if(var1 < getSizeInventory() && var2 != null) {
			if(inventory[var1] == null) return true;
			if(inventory[var1].getItem() == var2.getItem()) return true;
		}
		return false;
	}

}
