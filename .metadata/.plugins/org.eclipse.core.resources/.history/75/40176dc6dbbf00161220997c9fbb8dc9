import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Protagonist {
	
	//instance variables
	private int last_direction = KeyEvent.VK_RIGHT;
	
	private static final int MOVE_COUNTER_THRESH = 6;	//max number of frames of animation
	private int moveCounter = 0;
	private Rectangle collisionBox;	//space occupied by protagonist
	private static final int DISPLACEMENT = 1;	//space covered by single step of protagonist
	private BufferedImage currentImage;
	
	private int PROTAGONIST_HEIGHT = 44;
	private int PROTAGONIST_WIDTH = 32;

	private int currentFrameNumber = 0;	
	
	private final int INITIAL_X = 100;
	private final int INITIAL_Y = GameFrame.HEIGHT - PlayPanel.TERRAIN_HEIGHT - PROTAGONIST_HEIGHT;
	private int currentX = INITIAL_X;		//current horizontal start dist
	private int currentY = INITIAL_Y;
	
	private boolean jumping;
	
	private int jump_count = 0;
	
	private boolean moving = true;
	private boolean ascending = false;
	private boolean descending = true;
	private boolean dead = false;
	private boolean won = false;
	
	private boolean moveRightPossible = true;
	private boolean moveLeftPossible = true;
	
	static ArrayList<Bullet> bullets;
	boolean reloaded = false;
	int reloadedSeconds = 0;
	
	private Point topRight;
	private Point bottomRight;
	private Point topLeft;
	private Point bottomLeft;
	private Point middleRight;
	private Point middleLeft;
//	static ArrayList<Enemy> enemies;
	
	private static final int JUMP_DISPLACEMENT = 4;
	
	private BufferedImage[] run_R;
	private BufferedImage[] run_L;
	
	private Level level;

//	public static int MAX_LIVES = 3;
//	private int currentLives = 3;
	
	public Protagonist(){
		//images for movement
		run_L = new BufferedImage[35];
		run_R = new BufferedImage[35];
		
		bullets = new ArrayList();
//		enemies = new ArrayList();
		loadImages();
		
		currentImage = run_R[0];
		
		collisionBox = new Rectangle(currentX, currentY, PROTAGONIST_WIDTH, PROTAGONIST_HEIGHT);
		
		//Look at again...could optimize detecting collisions in the corners
//		Point p = new Point();
//		p = collisionBox.get
		
		topRight = new Point(currentX+PROTAGONIST_WIDTH, currentY);
		bottomRight = new Point( currentX + PROTAGONIST_WIDTH, currentY + PROTAGONIST_HEIGHT);
		topLeft = new Point(currentX, currentY);
		bottomLeft = new Point(currentX, currentY + PROTAGONIST_HEIGHT);
		middleRight = new Point(currentX + PROTAGONIST_WIDTH, ( (currentY + PROTAGONIST_HEIGHT) + currentY) / 2 );
		middleLeft = new Point(currentX, ( (currentY + PROTAGONIST_HEIGHT) + currentY) / 2 );
	}

	private void loadImages(){
		for(int i = 0; i < 35; i++){
			try {
				run_R[i] = ImageIO.read(new File("bb8_" + (i) + ".png"));
				run_L[i] = ImageIO.read(new File("bb8_" + (i) + ".png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	

	public void reset() {
		currentX = INITIAL_X;
		currentY = INITIAL_Y;
	}
	
	public void move(int direction){
		
//		moving = true;
//		collisionChecker();
		
//		if (canMove()){
		if(direction == KeyEvent.VK_LEFT){
			if(canMoveLeft()){
				//update position
				currentX = currentX - DISPLACEMENT;
				
				//update bounding box position
				updateLocations();
//				collisionBox.setLocation(currentX, currentY);
				
				//change the current frame in animation
				decrementFrameNumber();
				currentImage = run_L[currentFrameNumber];
				
				//set last direction
				last_direction = KeyEvent.VK_LEFT;
			}
			
		}else if(direction == KeyEvent.VK_RIGHT){
			if(canMoveRight()){
				//update position
				currentX = currentX + DISPLACEMENT;
				
				updateLocations();
//				collisionBox.setLocation(currentX, currentY);
				
				incrementFrameNumber();
				currentImage = run_R[currentFrameNumber];
				
				//set last direction
				last_direction = KeyEvent.VK_RIGHT;
			}
		}else{
			return;
		}
		moveCounter++;
	}
//	}
	
	public void jump() {
		
		if (jump_count == 0) {
		
			descending = false;
			ascending = true;
	
			
			if(last_direction == KeyEvent.VK_RIGHT){
				currentImage = run_R[currentFrameNumber];
			}else{
				currentImage = run_L[currentFrameNumber];
			}
		}
	}
	
	public void checkAscending() {
		if(ascending) {
			if(jump_count < 30) {
				currentY -= JUMP_DISPLACEMENT;
				updateLocations();
				collisionBox.setLocation(currentX, currentY);
				
//				jump_count++;
			} else {
				ascending = false;
				descending = true;
				jump_count = 0;
				updateLocations();
			}
			jump_count++;
		}
	}
	
	public void checkDescending () {
		if (descending) {
			//check if lowest part of protagonist's collisionBox divided by the size of each game tile is greater than the number of rows in the panel (9)
			//this checks if he is off screen at the bottom
			
				// Move character in downward direction
				currentY += JUMP_DISPLACEMENT;
//				collisionBox.setLocation(currentX, currentY);
				updateLocations();
			
		} 
	}
	
	//keyboard events
	public boolean isJumping(){
		return ascending;
	}
	

	
	//set current frame when protagonist is moving. we have a total of 5 frames for 
    //each run direction. The variable moveCounter is incremented each time the gameManager
    //calls the move function on the Boy. So according to moveCounter we can choose the current
    //frame. The frame changes every MOVE_COUNTER_THRESH increments of the moveCounter variable.
    //In this case MOVE_COUNTER_THRESH is set to 5. The use of "6" instead of a variable is temporary
    //because I still don't know how many frames will be used in the final animation
	
	private void incrementFrameNumber(){
		currentFrameNumber++;
		currentFrameNumber %= 35;
		
	}
	
	private void decrementFrameNumber(){
		if(currentFrameNumber == 0){
			currentFrameNumber = 34;
		}else{
			currentFrameNumber--;
			currentFrameNumber %= 35;
		}	
	}
	
	//updates locations of collision box and its intersection points
	public void updateLocations(){
		collisionBox.setLocation(currentX, currentY);
		
		topRight.setLocation(currentX+PROTAGONIST_WIDTH, currentY);
		bottomRight.setLocation(currentX + PROTAGONIST_WIDTH, currentY + PROTAGONIST_HEIGHT);
		topLeft.setLocation(currentX, currentY);
		bottomLeft.setLocation(currentX, currentY + PROTAGONIST_HEIGHT);
		middleRight.setLocation(currentX + PROTAGONIST_WIDTH, ( (currentY + PROTAGONIST_HEIGHT) + currentY) / 2 );
		middleLeft.setLocation(currentX, ( (currentY + PROTAGONIST_HEIGHT) + currentY) / 2 );
	}
	
	public Point gettopright(){
		return topRight;
	}
	
	public Point gettopleft(){
		return topLeft;
	}
	
	public Point getbottomright(){
		return bottomRight;
	}
	
	public Point getbottomleft(){
		return bottomLeft;
	}
	
	public void stop(){
		currentImage = run_R[0];
	}
	
	public int getCurrentX(){
		return currentX;
	}
	
	public int getCurrentY(){
		return currentY;
	}
	
	public Rectangle getCollisionBox(){
		return collisionBox;
	}
	
	public BufferedImage getCurrentImage(){
		return currentImage;
	}
	
	public boolean canMove() {
		return moving;
	}
	
	public boolean canMoveRight(){
		return moveRightPossible;
	}
	
	public boolean canMoveLeft(){
		return moveLeftPossible;
	}
	
	public void platformCollisionChecker(){
		
		int noCollide = 0;
		
		//check collision with moving blocks
		for(int i = 0; i < level.getPlatforms().size(); i++){
			
			//if the collision boxes overlap, handle each type of collision
			if(this.collisionBox.intersects(level.getPlatforms().get(i).getCollisionBox())){
				
				//get the intersecting block
				Platform collidingPlatform = level.getPlatforms().get(i);
				//check if collides from side
				if(collidingPlatform.getCollisionBox().contains(topRight) || collidingPlatform.getCollisionBox().contains(middleRight) ){
					currentX -= 1;
					moveRightPossible = false;
					
				}else{
//						descending = true;
					moveRightPossible = true;
				}
				
				if(ascending) {
					//check if hit head
					if(collidingPlatform.getCollisionBox().contains(topLeft) || collidingPlatform.getCollisionBox().contains(topRight) ){
	//					System.out.println("Collision with platform type 1");
						moveRightPossible = false;
						ascending = false;
						descending = true;
						jump_count = 0;
					}
				} else {
					descending = true;
				}
							
				if(descending){
					
					//check if lands on a platform
					if(collidingPlatform.getCollisionBox().contains(bottomLeft) || collidingPlatform.getCollisionBox().contains(bottomRight)){
						if (collidingPlatform.isEnd()==true){
							won = true;
						} else {
							currentX -= 1;		//move with platform
							descending = false;
							jump_count = 0;
						}
					} else {
						descending = true;
					}
				}
	
			} else {
				noCollide++;
			}

			
			if(noCollide == level.getPlatforms().size() && (ascending == false)){
				descending = true;
			}
			
			updateLocations();
		}
		
	}
	
	public void enemyCollisionChecker() {
		for (int i = 0; i < level.getEnemies().size(); i++) {
			if(this.collisionBox.intersects(level.getEnemies().get(i).getCollisionBox())) {
				this.died();
			}
		}
	}
	
	public void died() {
		dead = true;
	}
	
	public boolean isDead() {
		return dead;
	}
	
	public void shoot() //will added for firing
	{
		if(bullets.size() > 5){
			reloaded = false;
		}else{
			reloaded = true;
		}

		if(reloaded){
			Bullet bullet = new Bullet(currentX, currentY+10);
			bullets.add(bullet);
		}
	
	}
	
	public static ArrayList<Bullet> getBullets() //will added for firing
	{
		return bullets;
	}
	
	public void setLevel(Level level){
		this.level = level;
	}
	
	public void checkIfOffScreen(){
		//check if protagonist is out of bounds of the GameFrame
		//if moves off to the left
		if(collisionBox.getMaxX() <=0 ){
			died();
		}
		//if moves off to the right
		if(collisionBox.getMinX() > GameFrame.WIDTH){
			died();
		}
		//if jumps too high
		if(collisionBox.getMinY() <= 0){
			died();
		}
		//if falls to bottom
		if(collisionBox.getMaxY() > GameFrame.HEIGHT){
			died();
		}
	}

	public boolean isWon() {
		return won;
	}

	public void setWon(boolean won) {
		this.won = won;
	}

	public Level getLevel() {
		return level;
	}

}