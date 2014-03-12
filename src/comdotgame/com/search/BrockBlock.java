package comdotgame.com.search;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class BrockBlock extends Block {

	public BrockBlock(Material p_i45394_1_) {
		super(p_i45394_1_);
		this.setHarvestLevel("pickaxe", 3);
	}
    public Item getItemDropped(int par1, Random random, int par2)
    {
        return Mylittlepony.Brarge;
    }

}
