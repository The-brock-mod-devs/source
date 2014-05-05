package comdotgame.com.search;

public interface IProxy {

	public abstract void registerSoundHandler();
	
	public abstract void initRenderingAndTextures();
	
	public abstract void registerTileEntities();
	
	public abstract void registerItemTooltipHandler();
	
	public abstract void handleTileEntityPacket(byte eventType, int originX, int originY, int originZ, byte sideHit, byte rangeX, byte rangeY, byte rangeZ, String data);

	public abstract void registerRenders();
}
