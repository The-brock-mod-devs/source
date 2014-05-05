package comdotgame.com.search;

import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerSoundHandler() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initRenderingAndTextures() {
		// TODO Auto-generated method stub

	}

	@Override
	public void registerTileEntities() {
		// TODO Auto-generated method stub

	}

	@Override
	public void registerItemTooltipHandler() {
		// TODO Auto-generated method stub

	}

	@Override
	public void handleTileEntityPacket(byte eventType, int originX,
			int originY, int originZ, byte sideHit, byte rangeX, byte rangeY,
			byte rangeZ, String data) {
		// TODO Auto-generated method stub

	}

	@Override
	public void registerRenders() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBrockCentrifuge.class, new RendererBrockCentrifuge());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(MyLittlePony.brock_centrifuge), new RendererItemBrockCentrifuge());
		
		//MyLittlePony.renderingId = RenderingRegistry.getNextAvailableRenderId();
		//RenderingRegistry.registerBlockHandler(new RenderingHandler());
	}

}
