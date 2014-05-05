package comdotgame.com.search;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockBrockCentrifuge extends BlockContainer {
	
	public boolean attachment;

	protected BlockBrockCentrifuge(boolean attachment) {
		super(Material.anvil);
		//this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.25F, 1.0F);
		this.attachment = attachment;
		if(attachment) {
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.25F, 1.0F);
		}
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		if(attachment) return null;
		return new TileEntityBrockCentrifuge();
	}
	
	@Override
	public void onNeighborBlockChange(World world, int i, int j, int k, Block block)
	{
		if(attachment) {
			if(world.getBlock(i, j - 1, k) != MyLittlePony.brock_centrifuge) {
				world.setBlockToAir(i, j, k);
			}
		} else {
			if(world.getBlock(i, j + 1, k) != MyLittlePony.brock_centrifuge_top) {
				world.setBlockToAir(i, j, k);
			}
		}
	}
	
	public TileEntity getTileEntity(World world, int i, int j, int k) {
		return world.getTileEntity(i, j - (attachment ? 1 : 0), k);
	}
	
	@Override
	public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		TileEntity tileEntity = getTileEntity(world, i, j, k);
		if(tileEntity != null && tileEntity instanceof TileEntityBrockCentrifuge)
		{
			TileEntityBrockCentrifuge centrifuge = (TileEntityBrockCentrifuge)tileEntity;
			if(side != 1) {
				if((player.getEquipmentInSlot(0) == null && centrifuge.lidRot > 0.0f) || (centrifuge.lidRot == 0.0f)) {
					centrifuge.toggleSpin();
					return true;
				} else {
					for(int i2 = 0; i2 < centrifuge.getSizeInventory(); i2++) {
						if(centrifuge.isItemValidForSlot(i2, player.getEquipmentInSlot(0))) {
							if(centrifuge.inventory[i2] != null) {
								int spaceAvailable = 64 - centrifuge.inventory[i2].stackSize;
							}
							return true;
						}
					}
				}
			} else if (side == 1) {
				centrifuge.toggleGlass();
				if(centrifuge.lidRot >= 3.0f) {
					
				}
				return true;
			}
		}
		return false;
	}
	
	@Override
    public void onBlockPlacedBy(World world, int i, int j, int k, EntityLivingBase entityliving, ItemStack itemStack)
    {
		if(!attachment) {
			world.setBlock(i, j + 1, k, MyLittlePony.brock_centrifuge_top);
		}
		byte facing = 0;
		int orientation = MathHelper.floor_double((double) ((entityliving.rotationYaw * 4F) / 360F) + 0.5D) & 3;
		if (orientation == 0) {
			facing = 2;
		}
		if (orientation == 1) {
			facing = 5;
		}
		if (orientation == 2) {
			facing = 3;
		}
		if (orientation == 3) {
			facing = 4;
		}
		TileEntity te = getTileEntity(world, i, j, k);
		if (te != null && te instanceof TileEntityBrockCentrifuge)
		{
			TileEntityBrockCentrifuge tecc = (TileEntityBrockCentrifuge) te;
			//tecc.wasPlaced(entityliving, itemStack);
			tecc.setFacing(facing);
			world.markBlockForUpdate(i, j, k);
		}
    }
	
	public int getRenderType()
	{
		return -1;
	}
	
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	public boolean renderAsNormalBlock()
	{
		return false;
	}

}
