import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Protagonist {
	
	//instance variables
	private int last_direction = KeyEvent.VK_RIGHT;
	
	private static final int MOVE_COUNTER_THRESH = 5;	//max number of frames of animation
	private int moveCounter = 0;
	private Rectangle2D boundingBox;	//space occupied by protagonist
	private static final int DISPLACEMENT = 4;	//space covered by single step of protagonist
	private BufferedImage currentFrame;
	private static final int BUFFER_RUN_SIZE = 6;  //sie of the run animation buffer
	
	private int currentFrameNumber = 0;	
	
	private int currentX = 128;		//current horizontal start dist
	
	private int currentY = GameFrame.HEIGHT - PlayPanel.TERRAIN_HEIGHT - BOY_HEIGHT;

	public Protagonist(){
		
		
		
	}
	
	public void move(int direction){
		
		if(direction == KeyEvent.VK_LEFT){
			
			//update position
			currentX = currentX - DISPLACEMENT;
			
			//update bounding box position
			boundingBox.setLocation(currentX, currentY);
			
			//change the current frame in animation
			setFrameNumber();
			currentFrame = run_L[currentFrameNumber];
			
			//set last direction
			last_direction = KeyEvent.VK_LEFT;
			
		}else if(direction == KeyEvent.VK_RIGHT){
			
			//update position
			currentX = currentX + DISPLACEMENT;
			
			setFrameNumber();
			currentFrame = run_R[currentFrameNumber];
			
			//set last direction
			last_direction = KeyEvent.VK_RIGHT;
			
		}else{
			return;
		}
	}
	
	moveCounter++;
	
	//set current frame when protagonist is moving. we have a total of 5 frames for 
    //each run direction. The variable moveCounter is incremented each time the gameManager
    //calls the move function on the Boy. So according to moveCounter we can choose the current
    //frame. The frame changes every MOVE_COUNTER_THRESH increments of the moveCounter variable.
    //In this case MOVE_COUNTER_THRESH is set to 5. The use of "6" instead of a variable is temporary
    //because I still don't know how many frames will be used in the final animation
	private void setFrameNumber(){
		currentFrameNumber = moveCounter / MOVE_COUNTER_THRESH;
		currentFrameNumber %= 6;
		
		if (moveCounter > MOVE_COUNTER_THRESH * 6){
			moveCounter = 0;
		}
	}
}
