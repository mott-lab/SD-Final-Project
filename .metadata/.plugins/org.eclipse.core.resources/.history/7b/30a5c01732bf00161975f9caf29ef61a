public class Main {
	
	private Level level;
	public GamePanel gamePanel;
	private GameManager gameManager;
	private GameFrame gameFrame;
	
	public static void main(String[] args) {
		boolean running = true;
		
		//initialize the gamePanel
		GamePanel gamePanel=new GamePanel();
		
		//initialize and start the main thread of the game
		GameManager gameManager=new GameManager(gamePanel);
		gameManager.start();
		
		//start-up the game main frame 
		GameFrame gameFrame=new GameFrame(gamePanel);
	}
	
}
