
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class Tile {
	protected int row;
	protected int col;
	protected BufferedImage image;
	protected Rectangle collisionBox;
	public static final int TILE_SIZE=64;
	
	public Tile(int i, int j){
		this.row=i;
		this.col=j;
		initializeStuff();
		
		collisionBox = new Rectangle(TILE_SIZE, TILE_SIZE);
	}
	
	protected abstract void initializeStuff();
	
	protected abstract void loadInformations();
	
	public BufferedImage getImage(){
		return image;
	}
	
	public Rectangle getCollisionBox() {
		return collisionBox;
	}
	
}
