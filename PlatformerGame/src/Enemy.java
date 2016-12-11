import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Enemy {

	Image img;
	public int x;
	public int y;
	public boolean see;
	private Rectangle collisionBox;
	private Protagonist bb8;
	private int diffY = 0;		//used in controlling how fast the enemies attack the target

	
	public Enemy(Protagonist target)
	{
		bb8 = target;
		x = 1300;
		y = (int)(Math.random()*500+150); 
		ImageIcon l = new ImageIcon("TieFighter.png");
		img = l.getImage();
		see = true;
		collisionBox = new Rectangle(x, y, 60, 60);
	}
	

	
	public void move()
	{
		x = x - 3;
		if (x-bb8.getCurrentX() < 500)
		{
			
			if (bb8.getCurrentY()>y)
			{
				y = y + diffY;
			} else {
				y = y - diffY;
			}
			
		}
		collisionBox.setLocation(x, y);
		if (x < 0)
			see = false;
		
	}
	
	public void bulletCollisionChecker() {
		for (int i = 0; i < bb8.getBullets().size(); i++) {
			if(this.collisionBox.intersects(bb8.getBullets().get(i).getCollisionBox())) {
				this.see = false;
				bb8.getBullets().remove(i);
			}
		}
	}
	
	public Rectangle getCollisionBox(){
		return collisionBox;
	}

	public int getDiffY() {
		return diffY;
	}

	public void setDiffY(int diffY) {
		this.diffY = diffY;
	}
	
	
}
