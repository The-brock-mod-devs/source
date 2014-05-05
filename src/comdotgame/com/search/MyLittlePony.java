package comdotgame.com.search;

import net.minecraft.block.Block;
import net.minecraft.block.BlockJukebox;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = "jags", name = "shovels", dependencies = "required-after:Forge@[10.12.0.1029,)")
public class MyLittlePony {
	
	@Instance("jags")
	public static MyLittlePony instance;
	
	@SidedProxy(clientSide="comdotgame.com.search.ClientProxy", serverSide="comdotgame.com.search.ServerProxy")
	public static IProxy proxy;
	
	public static Block brock_centrifuge;
	public static Block brock_centrifuge_top;
	public static Block brock_ore;
    public static Block brock_block;
    public static Block jukebrox;
    public static Block brock_furnace;
    public static Block lit_brock_furnace;
    
    public static Item pork_hammer;
    public static Item cooked_pork_hammer;
    public static Item brock_shovel;
    public static Item brarge;
    public static Item helix_fossil;
    public static Item brock_pickaxe;
    
    public static ToolMaterial material_brock;
    public static ToolMaterial material_misty;
    
    public static CreativeTabs brockTab;
    
    public static int renderingId;
    
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
		
		GameRegistry.addRecipe(new ItemStack(pork_hammer),"ppp","ppp"," b ",'p',new ItemStack(Items.porkchop),'b', new ItemStack(brarge));
		GameRegistry.addRecipe(new ItemStack(brock_shovel),"c","b", "h",'b', new ItemStack(brarge),'h', new ItemStack (helix_fossil), 'c', new ItemStack(brock_block));
		GameRegistry.addRecipe(new ItemStack(helix_fossil)," c ","bib","iii",'o',new ItemStack(Items.bone),'c',new ItemStack(Blocks.cactus),'b',new ItemStack(brock_block),'i',new ItemStack(brarge));
		GameRegistry.addRecipe(new ItemStack(jukebrox), "bbb", "bdb", "bbb", 'b', new ItemStack(brock_block), 'd', new ItemStack(Items.diamond));
		GameRegistry.addRecipe(new ItemStack(brock_block), "bb", "bb",'b',new ItemStack(brarge));
		GameRegistry.addRecipe(new ItemStack(brock_pickaxe), "bhb"," i "," i ",'i',new ItemStack(brarge),'b',new ItemStack(brock_block), 'h',new ItemStack(helix_fossil));
		GameRegistry.addRecipe(new ItemStack(brock_furnace), "bhb", "bfb", "bbb", 'b',new ItemStack(brock_block), 'h', new ItemStack(helix_fossil), 'f', new ItemStack(Blocks.furnace));
		GameRegistry.addShapelessRecipe(new ItemStack(brock_block), new ItemStack(brock_ore));
		
		GameRegistry.addSmelting(brock_ore, new ItemStack(brock_block), 1.0f);
		GameRegistry.addShapedRecipe(new ItemStack(helix_fossil, 2), "h  ", "   ", "  h", 'h', new ItemStack(helix_fossil));
		
		GameRegistry.registerWorldGenerator(new WorldGeneration(), 0);
		
		GameRegistry.registerTileEntity(TileEntityBrockFurnace.class, "brock_furnace");
		GameRegistry.registerTileEntity(TileEntityBrockCentrifuge.class, "brock_centrifuge");
		
		
		
		proxy.registerRenders();
	}
	
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	brockTab = new CreativeTabs("brock") {
			@Override
			public Item getTabIconItem() {
				return brarge;
			}
		};
    	
    	//Tool material registry
    	material_brock= EnumHelper.addToolMaterial("BROCK", 4, 10000, 24f, 20.0f, 30);
    	material_misty= EnumHelper.addToolMaterial("MISTY", 5, 20000, 34f, 40.0f, 30);
    	
    	//Item and block registry
    	pork_hammer=new ItemPorkHammer().setUnlocalizedName("pork_hammer").setCreativeTab(brockTab).setTextureName("jags:pork_hammer").setMaxDamage(75);
    	GameRegistry.registerItem(pork_hammer, "pork_hammer");
    	jukebrox=new BlockJukeBrox().setCreativeTab(brockTab).setStepSound(Block.soundTypePiston).setHardness(2.0f).setBlockName("jukebrox").setBlockTextureName("jags:jukebrox").setStepSound(Block.soundTypeStone);
    	GameRegistry.registerBlock(jukebrox, "jukebrox");
    	brock_shovel=new ItemBrockShovel(material_brock).setCreativeTab(brockTab).setTextureName("jags:brock_shovel").setUnlocalizedName("brock_shovel");
    	GameRegistry.registerItem(brock_shovel, "brock_shovel");
    	brock_ore=new BlockBrockOre(Material.rock).setCreativeTab(brockTab).setBlockTextureName("jags:brock_ore").setBlockName("brock_ore").setHardness(3.0f);
    	GameRegistry.registerBlock(brock_ore, "brock_ore");
    	brarge=new ItemFood(20,200,true).setCreativeTab(brockTab).setTextureName("jags:brarge").setUnlocalizedName("brarge");
    	GameRegistry.registerItem(brarge, "brarge");
    	helix_fossil=new ItemHelixFossil().setCreativeTab(brockTab).setTextureName("jags:helix_fossil").setUnlocalizedName("helix_fossil");
    	GameRegistry.registerItem(helix_fossil, "helix_fossil");
    	brock_pickaxe=new ItemBrockPickaxe(material_brock).setCreativeTab(brockTab).setTextureName("jags:brock_pickaxe").setUnlocalizedName("brock_pickaxe");
    	GameRegistry.registerItem(brock_pickaxe, "brock_pickaxe");
    	brock_block=new BlockBrockBlock(Material.sponge).setCreativeTab(brockTab).setBlockName("brock_block").setHardness(0.5f).setLightOpacity(3).setStepSound(Block.soundTypeStone).setBlockTextureName("jags:brock_block");
    	GameRegistry.registerBlock(brock_block, "brock_block");
    	brock_furnace=new BlockBrockFurnace(false).setCreativeTab(brockTab).setBlockName("brock_furnace").setHardness(1.0f).setStepSound(Block.soundTypeStone);
    	GameRegistry.registerBlock(brock_furnace, "brock_furnace");
    	lit_brock_furnace=new BlockBrockFurnace(true).setBlockName("lit_brock_furnace").setHardness(1.0f).setStepSound(Block.soundTypeStone);
    	GameRegistry.registerBlock(lit_brock_furnace, "lit_brock_furnace");
    	brock_centrifuge=new BlockBrockCentrifuge(false).setCreativeTab(brockTab).setBlockName("brock_centrifuge").setStepSound(Block.soundTypeStone).setHardness(1.0f);
    	GameRegistry.registerBlock(brock_centrifuge, "brock_centrifuge");
    	brock_centrifuge_top=new BlockBrockCentrifuge(true).setStepSound(Block.soundTypeStone).setHardness(1.0f);
    	GameRegistry.registerBlock(brock_centrifuge_top, "top_brock_centrifuge");
    }
    
    public static ItemStack getSmeltingResult(int furnaceTier, ItemStack stack) {
    	if(stack != null && stack.getItem() != null) {
    		Item item = stack.getItem();
    		if (Block.getBlockFromItem(item) != null) {
        		Block block = Block.getBlockFromItem(item);
	    		if(furnaceTier >= 0) {
	    			if(block == Blocks.acacia_stairs) { return new ItemStack(Blocks.cactus, 2); }
	    			//if(block == misty_ore) { return new ItemStack(misty); }
	    			//if(block == lt_surge_ore) { return new ItemStack(lt_surge); }
	    			//if(block == erika_ore) { return new ItemStack(erika); }
	    			//if(block == koga_ore) { return new ItemStack(koga); }
	    			//if(block == sabrina_ore) { return new ItemStack(sabrina); }
	    			//if(block == blaine_ore) { return new ItemStack(blaine); }
	    			//if(block == giovanni_ore) { return new ItemStack(giovanni); }
	    		}
	    		if(furnaceTier >= 1) {
	    			//if(block == lorelei_ore) { return new ItemStack(lorelei); }
	    			//if(block == bruno_ore) { return new ItemStack(bruno); }
	    			//if(block == agatha_ore) { return new ItemStack(agatha); }
	    			//if(block == lance_ore) { return new ItemStack(lance); }
	    		}
	    		if(furnaceTier >= 2) {
	    			//if(block == blue_ore) { return new ItemStack(blue); }
	    			//if(block == red_ore) { return new ItemStack(red); }
	    		}
    		}
    	}
    	return FurnaceRecipes.smelting().getSmeltingResult(stack);
    }
}
