package comdotgame.com.search;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class ItemHelixFossil extends Item {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean jag)
	{
		if(jag) {
			String jagggg = "jag";
		}
		list.add("Something compels you to use this.");
	}
	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int i, int j, int k, int side, float rx, float ry, float rz)
	{
		world.playSoundEffect((double)i + 0.5D, (double)j + 0.5D, (double)k + 0.5D, "mob.pig.say", 1.0F, world.rand.nextFloat() * 0.1F +  0.9F);
		ChatComponentTranslation chatcomponenttranslation1 = new ChatComponentTranslation("message.helix_fossil", player.getDisplayName());
		chatcomponenttranslation1.getChatStyle().setColor(EnumChatFormatting.WHITE);
		player.addChatMessage(chatcomponenttranslation1);
		return true;
	}

}
