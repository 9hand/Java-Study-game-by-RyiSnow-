package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Gamepannel;
import main.KeyHandler;

public class player extends entity{
	
	Gamepannel gp;
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	public int hasKey = 0;
	
	public player(Gamepannel gp,KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		screenX = gp.screenWidth / 2 - (gp.tileSize/2);
		screenY = gp.screenHeight / 2 - (gp.tileSize/2);
		
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidAreaDefultX = solidArea.x;
		solidAreaDefultY = solidArea.y;
		solidArea.width = 32;
		solidArea.height = 32;
		
		setDefultValue();
		getPlayerImage();
	}
	
	public void setDefultValue() {
		worldx =  gp.tileSize*23;
		worldy =  gp.tileSize*21;
		speed = 4;
		direction = "defult";
	}
	
	public void getPlayerImage() {
		try {
			
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/Player up1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/Player up2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/Player left1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/Player left2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/Player right1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/Player right 2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/Player down1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/Player down2.png"));
			defult = ImageIO.read(getClass().getResourceAsStream("/player/Player defult.png"));
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		
		if(keyH.upPress == true || keyH.downPress == true || keyH.leftPress == true || keyH.rightPress == true ) {
		if(keyH.upPress == true){
			direction = "up";			
		}
		else if(keyH.downPress == true) {
			direction = "down";			
		}
		else if(keyH.leftPress == true) {
			direction = "left";			
		}
		else if(keyH.rightPress == true) {
			direction = "right";			
		}
		
		// check tile collision
		collisionOn = false;
		gp.cCheck.checkTile(this);
		
		//check object collision
		int objIndex = gp.cCheck.checkObj(this, true);
		pickupObj(objIndex);
		
		//if collision is false, player can move
		if(collisionOn == false) {
			
			switch(direction) {
			case "up":
				worldy -= speed;
				break;
			case "down":
				worldy += speed;
				break;
			case "left":
				worldx -= speed;
				break;
			case "right":
				worldx += speed;
				break;
			}
		}
		
		spriteCount++;
		if(spriteCount > 12) {
			if(spriteNum == 1) {
				spriteNum = 2;
			}else if(spriteNum == 2) {
				spriteNum = 1;
			}
			spriteCount = 0;
		}
		}
		
	}
	
	public void pickupObj(int i) {
		
		if(i != 999) {
			String objName = gp.obj[i].name;
			switch(objName) {
			case "Key":
				gp.playSoundEffect(1);
				hasKey++;
				gp.obj[i] = null;
				gp.ui.showMessage("You got a " + objName);
				break;
			case "Door":
				if(hasKey > 0) {
					gp.playSoundEffect(3);
					gp.obj[i] = null;
					hasKey--;
					
				}else {
					gp.ui.showMessage("You need a Key");
				}
				break;
			case "Boot":
				gp.playSoundEffect(2);
				speed +=2;
				gp.obj[i] = null;
				gp.ui.showMessage("Time for Speed!");
				break;
			case "Chest":
				gp.ui.gameFinish = true;
				gp.stopMusic();
				gp.playSoundEffect(4);
				break;
			}
		}
		
	}
	
	public void draw(Graphics2D g2) {
		
//		g2.setColor(Color.white);
//		
//		g2.fillRect(x,y, gp.tileSize, gp.tileSize);
		
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
			if(spriteNum == 1) {
				image = up1;
			}
			if(spriteNum == 2) {
				image = up2;
			}
			break;
			
		case "down":
			if(spriteNum == 1) {
				image = down1;
			}
			if(spriteNum == 2) {
				image = down2;
			}
			break;
			
		case "left":
			if(spriteNum == 1) {
				image = left1;
			}
			if(spriteNum == 2) {
				image = left2;
			}
			break;
			
		case "right":
			if(spriteNum == 1) {
				image = right1;
			}
			if(spriteNum == 2) {
				image = right2;
			}
			break;
			
		case "defult":
			image = defult;
		}
		g2.drawImage(image, screenX, screenY, gp.tileSize,gp.tileSize, null);
	}

}
