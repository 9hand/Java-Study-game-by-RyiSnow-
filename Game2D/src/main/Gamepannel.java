package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import entity.player;
import tiles.tileManager;

import javax.swing.JPanel;

import Object.SuperObject;

public class Gamepannel extends JPanel implements Runnable{

	//Screen setting
	final int OriginalTileSize = 16;// 16*16 tiles
	final int scale = 3;
	
	public final int tileSize = OriginalTileSize * scale;// 48*48 tile
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol;// 768 pixel
	public final int screenHeight = tileSize * maxScreenRow;// 576 pixel
	
	//world setting
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
//	public final int worldWidth = tileSize * maxWorldCol;
//	public final int worldHeight = tileSize * maxWorldRow;
	
	//FPS
	int FPS = 60;
	
	tileManager tileM = new tileManager(this);
	KeyHandler keyH = new KeyHandler();
	
	//sound
	Sound music = new Sound();
	Sound soundEff = new Sound();
	
	
	public CollisionCheck cCheck = new CollisionCheck(this);
	public AssetSetter Asetter = new AssetSetter(this);
	
	//UI
	public UI ui = new UI(this);
	Thread gameThread;
	
	//entity  and object
	public player player = new player(this,keyH);
	public SuperObject obj[] = new SuperObject[10]; 
	
	
	
	
	public Gamepannel() {
		this.setPreferredSize(new Dimension(screenWidth,screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		
	}
	
	public void setupGame() {
		Asetter.setObject();
		
		playMusic(0);
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}


	// gameloop delta method
	@Override
	public void run() {
		
		double drawInterval = 1000000000/FPS; // 0.0166666 seconds
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		while(gameThread != null) {
			
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime)/ drawInterval;
			lastTime = currentTime;
			
			if(delta >= 1) {
			// 1. Update information such as character position 
			update();
			
			//2. draw the screen with updated  information
			repaint();
			
			delta--;
			}
			
		}
}

	
	public void update() {
		player.update();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		//tile
		tileM.draw(g2);
		//object
		for(int  i = 0 ; i < obj.length; i++) {
			if(obj[i] != null) {
				obj[i].draw(g2, this);
			}
		}
		//player
		player.draw(g2);
		//UI
		ui.draw(g2);
		
		g2.dispose();
		
	}
	
	public void playMusic(int i ) {
		music.setFile(i);
		music.play();
		music.loop();
		
	}
	
	public void stopMusic() {
		music.stop();
	}
	
	public void playSoundEffect(int i) {
		soundEff.setFile(i);
		soundEff.play();
	}
}
