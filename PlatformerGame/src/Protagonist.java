import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Protagonist {
	
	//instance variables
	private int last_direction = KeyEvent.VK_RIGHT;
	
	private static final int MOVE_COUNTER_THRESH = 6;	//max number of frames of animation
	private int moveCounter = 0;
	private Rectangle collisionBox;	//space occupied by protagonist
	private static final int DISPLACEMENT = 4;	//space covered by single step of protagonist
	private BufferedImage currentImage;
	
	private int PROTAGONIST_HEIGHT = 44;
	private int PROTAGONIST_WIDTH = 32;

	private int currentFrameNumber = 0;	
	
	private int currentX = 100;		//current horizontal start dist
	
	private int currentY = GameFrame.HEIGHT - PlayPanel.TERRAIN_HEIGHT - PROTAGONIST_HEIGHT;
	
	private boolean jumping;
	
	private int jump_count = 0;
	
	private boolean moving = true;
	private boolean ascending;
	private boolean descending;
	
	static ArrayList bullets;
	
	private static final int JUMP_DISPLACEMENT = 6;
	
	private BufferedImage[] run_R;
	private BufferedImage[] run_L;

	public Protagonist(){
		//images for movement
		run_L = new BufferedImage[35];
		run_R = new BufferedImage[35];
		
		bullets = new ArrayList();
		loadImages();
		
		currentImage = run_R[0];
		
		collisionBox = new Rectangle(currentX, currentY, PROTAGONIST_WIDTH, PROTAGONIST_HEIGHT);
		
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
	
	public void move(int direction){
		
		moving = true;
//		collisionChecker();
		
		if(direction == KeyEvent.VK_LEFT){
			
			//update position
			currentX = currentX - DISPLACEMENT;
			
			//update bounding box position
			collisionBox.setLocation(currentX, currentY);
			
			//change the current frame in animation
			decrementFrameNumber();
			currentImage = run_L[currentFrameNumber];
			
			//set last direction
			last_direction = KeyEvent.VK_LEFT;
			
		}else if(direction == KeyEvent.VK_RIGHT){
			
			//update position
			currentX = currentX + DISPLACEMENT;
			
			collisionBox.setLocation(currentX, currentY);
			
			incrementFrameNumber();
			currentImage = run_R[currentFrameNumber];
			
			//set last direction
			last_direction = KeyEvent.VK_RIGHT;
			
		}else{
			return;
		}
		moveCounter++;
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
	
	public void jump() {
		this.jump_count = 0;
		
		descending = false;
		ascending = true;

		
		if(last_direction == KeyEvent.VK_RIGHT){
			currentImage = run_R[currentFrameNumber];
		}else{
			currentImage = run_L[currentFrameNumber];
		}
	}
	
	public void checkAscending() {
		if(ascending) {
			if(jump_count < 15) {
				currentY -= JUMP_DISPLACEMENT;
				collisionBox.setLocation(currentX, currentY);
//				jump_count++;
			} else {
				ascending = false;
				descending = true;
				jump_count = 0;
			}
			jump_count++;
		}
	}
	
	public void checkDescending () {
		if (descending) {
			if(collisionBox.getMaxY()/Tile.TILE_SIZE>=Tiles.ROWS){
				die();
			} else {
				// Move character in downward direction
				currentY += JUMP_DISPLACEMENT;
				collisionBox.setLocation(currentX, currentY);
			}
		}
	}
	
	//keyboard events
	public boolean isJumping(){
		return ascending;
	}
	
// 	public void checkJumpState(){
// 		if(jumping){
// 			if(jump_count < 15){
// 				currentY -= 6;
// 				collisionBox.setLocation(currentX, currentY);
// 			}else{
// 				currentY += 6;
// 				collisionBox.setLocation(currentX, currentY);
// 			}
// 		}
		
// 		jump_count++;
		
// 		if(jump_count >= 30){
// 			jumping = false;
// 			jump_count = 0;
// 		}
// 	}

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
	
	public void collisionChecker () {
		
//		int footPos = (int)collisionBox.getMaxY();
		
		int rowCurrent = (int)((currentY+100)/Tile.TILE_SIZE);
		int rowUp = (int)((collisionBox.getMinY()-1)/Tile.TILE_SIZE);
		int rowDown = (int)((collisionBox.getMaxY()+1)/Tile.TILE_SIZE);
		int colCurrent = (int)(currentX/Tile.TILE_SIZE);
		int colRight = (int)((collisionBox.getMaxX()+1)/Tile.TILE_SIZE);
		
		// Check moving first
//		if (moving) {
		moving = true;
			if (Tiles.tiles[rowCurrent][colRight] != null) {
				if (Tiles.tiles[rowCurrent][colRight].getCollisionBox().intersects(this.collisionBox)) {
					moving = false;
				} else {
					moving = true;
				}
			}
//		}
			
		// If jumping, check for blocks above character's head
		// If touch, start descending
		if (ascending) {
			if (Tiles.tiles[rowUp][colRight] != null) {
				if (Tiles.tiles[rowUp][colRight].getCollisionBox().intersects(collisionBox)) {
					ascending = false;
					descending = true;
				}
			}
			if (Tiles.tiles[rowUp][colCurrent] != null) {
				if (Tiles.tiles[rowUp][colCurrent].getCollisionBox().intersects(collisionBox)) {
					ascending = false;
					descending = true;
				}
			}
		} else {
			descending = true;
		}
		
		if (Tiles.tiles[rowDown][colRight] != null) {
			if (Tiles.tiles[rowDown][colRight].getCollisionBox().intersects(collisionBox)) {
				descending = false;
			} 
		}
		if (Tiles.tiles[rowDown][colCurrent] != null) {
			if (Tiles.tiles[rowDown][colCurrent].getCollisionBox().intersects(collisionBox)) {
				descending = false;
			}
		}
	}
	
		
	public void die () {
		//Re-start game? Take away life? Create new map?
	}
	
	public void shoot() //will added for firing
	{
		Bullet bullet = new Bullet(currentX, currentY);
		bullets.add(bullet);
	}
	
	public static ArrayList getBullets() //will added for firing
	{
		return bullets;
	}
	
	public boolean canMove(){
		return moving;
	}

}
