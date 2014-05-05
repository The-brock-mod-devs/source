package comdotgame.com.search;

import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

public class RendererItemBrockCentrifuge implements IItemRenderer {
	
	//public ModelBrockCentrifuge model;
	public RendererBrockCentrifuge renderer;
	
	public RendererItemBrockCentrifuge()
	{
		//model = new ModelBrockCentrifuge();
		renderer = new RendererBrockCentrifuge();
		renderer.func_147497_a(TileEntityRendererDispatcher.instance);
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		renderer.renderItem(new TileEntityBrockCentrifuge());
	}

}
