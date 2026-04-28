package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import Object.ObjectKey;

public class UI {
	
	Gamepannel gp;
	Font arial_40, arial_80B;
	BufferedImage Keypic;
	public boolean messageOn = false;
	public String message = "";
	int messageCnt = 0;
	public boolean gameFinish = false;
	
	double playTime;
	DecimalFormat dFormat = new DecimalFormat("#0.00");
	
	public UI(Gamepannel gp) {
		this.gp = gp;
		
		arial_40 = new Font ("Arial",Font.PLAIN,40);
		arial_80B = new Font ("Arial",Font.BOLD,80);
		ObjectKey key = new ObjectKey();
		Keypic = key.image;
	}
	
	public void showMessage(String text) {
		message = text;
		messageOn = true;
	}
	
	public void draw(Graphics2D g2) {
		
		if(gameFinish == true) {
			
			g2.setFont(arial_40);
			g2.setColor(Color.white);
			
			String text;
			int textLength;
			int x;
			int y;
			
			text = "You found the treasure!";
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			
			x = gp.screenWidth/2 - textLength/2;
			y = gp.screenHeight/2 - (gp.tileSize*3);
			g2.drawString(text, x, y);
			
			text = "You Time is: " + dFormat.format(playTime);
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			
			x = gp.screenWidth/2 - textLength/2;
			y = gp.screenHeight/2 + (gp.tileSize*4);
			g2.drawString(text, x, y);
			
			g2.setFont(arial_80B);
			g2.setColor(Color.yellow);
			
			text = "Congrats!";
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			
			x = gp.screenWidth/2 - textLength/2;
			y = gp.screenHeight/2 + (gp.tileSize*2);
			g2.drawString(text, x, y);
			
			gp.gameThread = null;
			
			
		}else {
			
			g2.setFont(arial_40);
			g2.setColor(Color.white);
			g2.drawImage(Keypic, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize,null);
			g2.drawString("x "+ gp.player.hasKey, 74, 65);
			
			//Time
			playTime += (double)1/60;
			g2.drawString("Time: " + dFormat.format(playTime), gp.tileSize*11, 65);
			
			//Message
			if(messageOn == true) {
				g2.setFont(g2.getFont().deriveFont(30F));
				g2.drawString(message, gp.tileSize*6, gp.tileSize*5);
				
				messageCnt++;
				
				if(messageCnt > 60) {
					messageCnt = 0;
					messageOn = false;
				}
			}
			
		}
		
		
	}

}
