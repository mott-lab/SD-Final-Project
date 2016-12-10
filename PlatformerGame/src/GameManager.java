import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameManager extends Thread{
	
	private static final int DELAY = 12;	//thread delay between each repaint of game's panels
	private boolean gameIsRunning = false;
	private Protagonist protagonist;
	private GamePanel gamePanel;
	private int bulletDelay = 30;
	private int time = 0;
	private Level level;
	private int jumpcounter = 0;
	
	
	JFrame youDied = new JFrame("You died :(");
	
	public GameManager(GamePanel gamePanel){
		
		this.level = new Level(1);
		
		//initialize protagonist888////
		this.protagonist = new Protagonist();
		
		this.gamePanel = gamePanel;
		this.gamePanel.addProtagonist(protagonist);
		this.gamePanel.addLevel(level);
		
		this.gameIsRunning = true;
		
	}
	
	public void run(){
		
		while(gameIsRunning){
			manageKeys();
			
			//updates protagonist movement if jumping
			
			protagonist.checkAscending();
			protagonist.checkDescending();
			protagonist.checkIfOffScreen();
			
//			protagonist.collisionChecker();
			protagonist.platformCollisionChecker();
			
			manageKeys();
			
			gamePanel.repaintGame();
			
			try{
				Thread.sleep(DELAY);
			}catch (InterruptedException ie){
				ie.printStackTrace();
			}
			
			if (protagonist.isDead()) {
				
				gameIsRunning = false;
				
				JPanel panel = new JPanel();
								
				JLabel gameOver = new JLabel("You have lost the game");
				ImageIcon deathGif = new ImageIcon("death.gif");
				JLabel imageLabel = new JLabel();
				imageLabel.setIcon(deathGif);
				
				JButton resetButton = new JButton("I'm addicted, let me keep playing.");
				resetButton.addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						
						resetGame(gamePanel);
						youDied.setVisible(false);
					}
					
				});
				
				panel.add(gameOver, BorderLayout.NORTH);
				panel.add(imageLabel, BorderLayout.CENTER);
				panel.add(resetButton, BorderLayout.SOUTH);
				
				youDied.add(panel);
				
				youDied.setLocation(GameFrame.WIDTH / 2, GameFrame.HEIGHT / 2);
		
				youDied.setSize(600, 500);
				youDied.setVisible(true);
			}
		}
	}
	
	public void resetGame(GamePanel gamePanel){
		gamePanel.resetGame();
		gameIsRunning = true;
	}
	
	public void manageKeys(){
		//access keys being pressed
		HashSet<Integer> currentKeys = KeyboardController.getActiveKeys();
		
		//manage left/right direction
		if(currentKeys.contains(KeyEvent.VK_RIGHT)){
			//move right
			protagonist.move(KeyEvent.VK_RIGHT);
		}else if(currentKeys.contains(KeyEvent.VK_LEFT)){
			//move left
			protagonist.move(KeyEvent.VK_LEFT);
		}else if(currentKeys.isEmpty() && !(protagonist.isJumping())){
			protagonist.stop();
		}
		
		if(currentKeys.contains(KeyEvent.VK_SPACE)){

			if(!protagonist.isJumping()){
				protagonist.jump();
			}

		}
		
		if(currentKeys.contains(KeyEvent.VK_S)) //will added for firing
		{
			if (time <= 0) {
		        protagonist.shoot();
		        time = bulletDelay;  // Reset the timer
		    }else{
		    	time--;
		    }
			
		}
		
//		if(currentKeys.contains(KeyEvent.VK_A)) //will added for firing
//		{
//			protagonist.genEnemy();
//		}
		
	}
}