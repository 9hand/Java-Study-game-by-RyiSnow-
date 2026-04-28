package main;

import javax.swing.JFrame;
public class mainclass {

	public static void main(String[] args) {
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("Find Da Chest");
		
		
		Gamepannel Gamepannel = new Gamepannel();
		window.add(Gamepannel);
		
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		Gamepannel.setupGame();
		Gamepannel.startGameThread();
	}
	

		
	

}
