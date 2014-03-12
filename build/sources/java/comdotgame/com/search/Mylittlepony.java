package comdotgame.com.search;

import net.minecraft.block.Block;
import net.minecraft.block.BlockJukebox;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = "jags", name = "shovels", dependencies = "required-after:Forge@[10.12.0.1029,)")
public class Mylittlepony {
	@EventHandler
    public void init(FMLInitializationEvent event)
    {
		GameRegistry.addRecipe(new ItemStack(PorkHammer),"ppp","ppp"," b ",'p',new ItemStack(Items.porkchop),'b', new ItemStack(Brarge));
		GameRegistry.addRecipe(new ItemStack(BrockShovel),"c","b", "h",'b', new ItemStack(Brarge),'h', new ItemStack (Helix), 'c', new ItemStack(brock));
		GameRegistry.addRecipe(new ItemStack(Helix),
				" c ", "bib", "iii",'c',new ItemStack(Blocks.cactus),'b',new ItemStack(brock),'i',new ItemStack(Brarge));
		GameRegistry.addRecipe(new ItemStack(BrockBox), "bbb", "bdb", "bbb", 'b', new ItemStack(brock), 'd', new ItemStack(Items.diamond));
		GameRegistry.addRecipe(new ItemStack(brock), "bb", "bb",'b',new ItemStack(Brarge));
		GameRegistry.addRecipe(new ItemStack(BrockPick), "bhb"," i "," i ",'i',new ItemStack(Brarge),'b',new ItemStack(brock), 'h',new ItemStack(Helix));
		
		GameRegistry.registerWorldGenerator(new WorldGeneration(), 0);
     }
	public static Block brock;
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	PorkHammer=new PorkHammer().setUnlocalizedName("PorkHammer").setCreativeTab(CreativeTabs.tabFood).setTextureName("jags:porkhammer").setMaxDamage(75);
    	GameRegistry.registerItem(PorkHammer, "PorkHammer");
    	BrockBox=new BrockBox().setStepSound(Block.soundTypePiston).setHardness(2.0f).setBlockName("brockbox").setBlockTextureName("jags:brockbox");
    	GameRegistry.registerBlock(BrockBox, "BrockBox");
    	BrockTool= EnumHelper.addToolMaterial("BROCK", 4, 10000, 24f, 100.0f, 30);
    	BrockShovel=new BrockSpade(BrockTool).setCreativeTab(CreativeTabs.tabTools).setTextureName("jags:BrockShovel").setUnlocalizedName("BrockShovel");
    	GameRegistry.registerItem(BrockShovel, "BrockShovel");
    	brock=new BrockBlock(Material.rock).setCreativeTab(CreativeTabs.tabFood).setBlockName("brock").setHardness(3.0f).setLightOpacity(3).setStepSound(Block.soundTypeStone).setBlockTextureName("jags:brock");
    	GameRegistry.registerBlock(brock, "brock");
    	Brarge=new ItemFood(20,200,true).setCreativeTab(CreativeTabs.tabRedstone).setTextureName("jags:brock2").setUnlocalizedName("Brarge");
    	GameRegistry.registerItem(Brarge, "BrargE");
    	Helix=new ItemHelix().setCreativeTab(CreativeTabs.tabRedstone).setTextureName("jags:helix").setUnlocalizedName("Helix");
    	GameRegistry.registerItem(Helix, "HeliX");
    	BrockPick=new ItemBrockPick(BrockTool).setCreativeTab(CreativeTabs.tabRedstone).setTextureName("jags:BrockPick").setUnlocalizedName("BrockPick");
    	GameRegistry.registerItem(BrockPick, "BrockPick");
    }
    public static Block BrockBox;
    public static Item PorkHammer;
    public static Item BrockShovel;
    public static Item Brarge;
    public static ToolMaterial BrockTool;
    public static Item Helix;
    public static Item BrockPick;

}
