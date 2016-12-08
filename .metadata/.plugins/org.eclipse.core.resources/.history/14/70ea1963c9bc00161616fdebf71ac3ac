import java.awt.event.KeyEvent;
import java.util.HashSet;

public class GameManager extends Thread{
	
	private static final int DELAY = 20;
	private boolean gameIsRunning = false;
	private Protagonist protagonist;
	private GamePanel gamePanel;
	private Tiles tiles;
	
	public GameManager(GamePanel gamePanel){
		
		this.tiles = new Tiles();
//		this.world.initializeStage(1);
		
		//initialize protagonist
		this.protagonist = new Protagonist();
		
		this.gamePanel = gamePanel;
		this.gamePanel.addProtagonist(protagonist);
		
		this.gameIsRunning = true;
	}
	
	public void run(){
		while(gameIsRunning){
			//updates protagonist movement if jumping
			protagonist.checkAscending();
			protagonist.checkDescending();
			
			manageKeys();
			
			protagonist.collisionChecker();
			//protagonist.move(KeyEvent.VK_RIGHT);
			gamePanel.repaintGame();
			
			try{
				Thread.sleep(DELAY);
			}catch (InterruptedException ie){
				ie.printStackTrace();
			}
		}
	}
	
	public void manageKeys(){
		//access keys being pressed
		HashSet<Integer> currentKeys = KeyboardController.getActiveKeys();
		
		//manage left/right direction
		if(currentKeys.contains(KeyEvent.VK_RIGHT)){
			if (protagonist.canMove()) {	
				//move right
				protagonist.move(KeyEvent.VK_RIGHT);
			}
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
			protagonist.shoot();
		}
	}
}
