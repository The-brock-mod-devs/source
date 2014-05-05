package comdotgame.com.search;

import static org.lwjgl.opengl.GL11.glRotatef;

import org.lwjgl.opengl.GL11;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class RendererBrockCentrifuge extends TileEntitySpecialRenderer {
    
	public static final ResourceLocation texture = new ResourceLocation("jags", "textures/models/brock_centrifuge.png");
	
    private final ModelBrockCentrifuge model;
    
    public static RendererBrockCentrifuge instance;
    
    public RendererBrockCentrifuge() {
            this.model = new ModelBrockCentrifuge();
            instance = this;
    }
    
    @Override
    public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
        
        TileEntityBrockCentrifuge tecc = (TileEntityBrockCentrifuge) te;
        
        int facing = tecc.getFacing();
        int rotationAngle = 0;
        if (facing == 2) {
        	rotationAngle = 0;
        }
        if (facing == 3) {
        	rotationAngle = -180;
        }
        if (facing == 4) {
        	rotationAngle = -90;
        }
        if (facing == 5) {
        	rotationAngle = 90;
        }
        
        this.model.CentrifugeSide2.rotateAngleX = tecc.lidRot * 0.0174533F;
        this.model.CentrifugeTop.rotateAngleX = tecc.glassPos * 0.0174533F;
        
        GL11.glRotatef(rotationAngle + 180, 0.0F, 1.0F, 0.0F);
        //ResourceLocation textures = (new ResourceLocation("unm:textures/blocks/coreCharger.png")); 
        //Minecraft.getMinecraft().renderEngine.bindTexture(textures);         
        this.bindTexture(texture);
        GL11.glPushMatrix();
        this.model.renderBase(0.0625F);
        GL11.glRotatef(tecc.rotation, 0.0F, 1.0F, 0.0F);
        this.model.renderTop(0.0625F);
        //GL11.glTranslatef((float) x + 0.5F, (float) y + 2.5F, (float) z + 0.0F);
        
        
    	GL11.glPushMatrix();
    	GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
    	GL11.glTranslatef(0.0F, -0.625F, 0.0F);
    	float scalef = 0.5F;// / renderCount;
    	GL11.glScalef(scalef, scalef, scalef);
    	int currentItem = 0;
    	for(int i = 0; i < tecc.getSizeInventory(); i++) { if(tecc.getStackInSlot(i) != null) {
    		switch(currentItem) {
    			case 0:
    				GL11.glTranslatef(0.0F, 0.0F, 0.0F); break;
    			case 1:
    				GL11.glTranslatef(-0.3F, 0.0F, -0.3F); break;
    			case 3:
    				GL11.glTranslatef(-0.6F, 0.0F, 0.6F); break;
    			case 2:
    			case 4:
    				GL11.glTranslatef(0.6F, 0.0F, 0.05F); break;
    		}
        	EntityItem item = new EntityItem(tecc.getWorldObj(), 0.0D, 0.0D, 0.0D, tecc.getStackInSlot(i));
        	item.hoverStart = 0;
        	RenderItem.renderInFrame = true;
        	RenderManager.instance.renderEntityWithPosYaw(item, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
        	RenderItem.renderInFrame = false;
        	currentItem++;

    	}}
    	GL11.glPopMatrix();
    
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }
    
    public void renderItem(TileEntity te) {
        GL11.glPushMatrix();
        GL11.glTranslatef(0.5F, 1.2F, 0.5F);
        GL11.glScalef(0.9F, 0.9F, 0.9F);
        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
        //ResourceLocation textures = (new ResourceLocation("unm:textures/blocks/coreCharger.png")); 
        //Minecraft.getMinecraft().renderEngine.bindTexture(textures);         
        this.bindTexture(texture);
        GL11.glPushMatrix();
        this.model.renderBase(0.0625F);
        this.model.renderTop(0.0625F);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }
}