package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class entity {
	
	public int worldx,worldy;
	public int speed;
	
	public BufferedImage up1,up2,down1,down2,left1,left2,right1,right2,defult;
	public String direction;
	
	public int spriteCount = 0;
	public int spriteNum = 1;
	
	public Rectangle solidArea;
	public int solidAreaDefultX , solidAreaDefultY;
	public boolean collisionOn = false;

}
