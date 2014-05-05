package comdotgame.com.search;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemPorkHammer extends Item {
	
	@Override
	public boolean onBlockStartBreak(ItemStack itemstack, int X, int Y, int Z, EntityPlayer player)
	{
		World world = player.worldObj;
		if (!world.isRemote && world.getGameRules().getGameRuleBooleanValue("doTileDrops"))
		{
		    float f = 0.7F;
		    double d0 = (double)(world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
		    double d1 = (double)(world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
		    double d2 = (double)(world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
		    ItemStack pork = new ItemStack(Items.porkchop);
		    EntityItem entityitem = new EntityItem(world, (double)X + d0, (double)Y + d1, (double)Z + d2, pork);
		    entityitem.delayBeforeCanPickup = 10;
		    world.spawnEntityInWorld(entityitem);
		}
		itemstack.damageItem(1, player);
		return false;
	}
	
	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
		if(entity instanceof EntityLiving) {
			World world = entity.worldObj;
			if(!world.isRemote) {
			    float f = 0.7F;
			    double d0 = (double)(world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
			    double d1 = (double)(world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
			    double d2 = (double)(world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
			    ItemStack pork = new ItemStack(Items.porkchop);
			    EntityItem entityitem = new EntityItem(world, entity.posX + d0, entity.posY + d1, entity.posZ + d2, pork);
			    entityitem.delayBeforeCanPickup = 10;
			    world.spawnEntityInWorld(entityitem);
			    //entity.worldObj.removeEntity(entity);
			}
			stack.damageItem(1, player);
			return false;
		}
		return false;
	}
	
    @SideOnly(Side.CLIENT)
    public boolean isFull3D()
    {
        return true;
    }

}
