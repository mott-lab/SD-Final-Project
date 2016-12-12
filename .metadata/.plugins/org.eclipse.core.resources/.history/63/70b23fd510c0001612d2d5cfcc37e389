import java.awt.Color;
import java.awt.LayoutManager;

import javax.swing.JPanel;


//the game panel on which we will draw the true panels of the game
//it serves just as an interlayer between the frame and the mosaic 
//of panels the player will see, it comunicates with the statsPanel
//and the playPanel throwing them informations coming from the logic
//side of the game
public class GamePanel extends JPanel{
	
	private KeyboardController keyboardController;
	private StatsPanel statsPanel;
	private PlayPanel playPanel;
	
	private Protagonist bb8;
	private Level level;
	private int levelCount = 1;
	private int lifeCount = 3;
	
	public GamePanel(){
		this.setRequestFocusEnabled(true);
		this.setSize(WIDTH, HEIGHT);
		this.setLayout(null);
		this.setBackground(Color.BLACK);

		statsPanel = new StatsPanel();
		playPanel = new PlayPanel(levelCount);
		
		this.add(statsPanel);
		statsPanel.setLocation(0, 0);

		this.add(playPanel);
		playPanel.setLocation(0, StatsPanel.STATS_HEIGHT);
		
		keyboardController=new KeyboardController();
		this.addKeyListener(keyboardController);
		
		//initialize and start the main thread of the game
		GameManager gameManager=new GameManager(this);
		gameManager.start();
	}
	
	public void addProtagonist(Protagonist bb8) {
		this.bb8=bb8;
		playPanel.addProtagonist(bb8);
		statsPanel.addProtagonist(bb8);
	}
	
	public void addLevel(Level level) {
		this.level=level;
		level.addProtagonist(bb8);
		playPanel.addLevel(level);
		statsPanel.addProtagonist(bb8);
	}
	
	public void increaseLevel() {
		levelCount++;
	}
	
	public int getLevelCount() {
		return levelCount;
	}
	
	public int getLifeCount() {
		return statsPanel.getLifeCount();
	}
	
	public void restartGame() {
		levelCount = 1;
		statsPanel.resetLives();
	}
	
	public void decreaseLives() {
		statsPanel.decreaseLifeCount();
	}
	
	public boolean gameOver() {
		return statsPanel.gameOver();
	}
	
	public boolean gameWon(){
		return getLevelCount() == 3;
	}
	
	public void repaintGame(){
		playPanel.repaint();
		statsPanel.repaint();
	}
	
	public void exitGame(){
		this.setVisible(false);
	}
	
	public Protagonist getProtagonist(){
		return bb8;
	}
	
	public void resetGame(){
		
		this.remove(playPanel);
				
		playPanel = new PlayPanel(levelCount);

		this.add(playPanel);
		playPanel.setLocation(0, StatsPanel.STATS_HEIGHT);
		
		this.repaint();
		this.repaintGame();
		bb8.reset();
		
		keyboardController=new KeyboardController();
		this.addKeyListener(keyboardController);
		
		GameManager gameManager=new GameManager(this);
		gameManager.start();
		
	}
}