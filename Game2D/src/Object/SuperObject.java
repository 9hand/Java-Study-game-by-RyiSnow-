package Object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.Gamepannel;

public class SuperObject {
	
	public BufferedImage image;
	public String name;
	public boolean collision = false;
	public int worldX, worldY;
	public Rectangle SolidArea = new Rectangle(0,0,48,48);
	public int SolidAreaDefultX = 0;
	public int SolidAreaDefultY = 0;
	
	public void draw(Graphics2D g2, Gamepannel gp) {

		int screenX = worldX - gp.player.worldx + gp.player.screenX;
		int screenY = worldY - gp.player.worldy + gp.player.screenY;
		
		if( worldX + gp.tileSize> gp.player.worldx - gp.player.screenX  && 
			worldX - gp.tileSize< gp.player.worldx + gp.player.screenX && 
			worldY + gp.tileSize> gp.player.worldy - gp.player.screenY &&
			worldY - gp.tileSize< gp.player.worldy + gp.player.screenY) {
			g2.drawImage(image, screenX, screenY,gp.tileSize,gp.tileSize,null);
			
	}
}
}
