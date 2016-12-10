
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

//PlayPanel - Is the panel where you see the actual game in motion,
//all the big part under the stats panel 
public class PlayPanel extends JPanel{
	
	//height of the terrain in pixels - this is basically the distance of the boy's feet 
	//from the bottom border of the window you play the game in
	public static final int TERRAIN_HEIGHT=192;
	
	//height of the PlayPanel 
	public static final int PLAY_PANEL_HEIGHT=1280;
	
	public BufferedImage BACKGROUND;
	
	GridLayout grid = new GridLayout(20, 9);
	
	//size of each tile in a 20x9 grid
	public static final int TILE_SIZE = 64;
	
	static ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	static ArrayList<Platform> platforms = new ArrayList<Platform>();
	
	//reference to the protagonist of the game
	private Protagonist bb8;
	
//	private Tiles tiles;

	private Level level;

	public PlayPanel(){
		
		super();

		try{
			BACKGROUND = ImageIO.read(new File("space_background.png"));
		}catch (IOException e){
			e.printStackTrace();
		}
		
		
		//set the size of the play panel
		this.setSize(GameFrame.WIDTH, PLAY_PANEL_HEIGHT);
		
		//set a background color in case background image does not load
		this.setBackground(Color.DARK_GRAY);
		
		//set layout
		this.setLayout(grid);
		
		//double buffering should improve animations
		this.setDoubleBuffered(true);
		
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2=(Graphics2D)g;

		
		g2.drawImage(BACKGROUND, level.getBackx_one(), 0, GameFrame.WIDTH, 640, null);
		g2.drawImage(BACKGROUND, level.getBackx_two(), 0, GameFrame.WIDTH, 640, null);
		level.setBackx_one(level.getBackx_one()-1);
		if (level.getBackx_one()+1250 < 0)
		{
			level.setBackx_one(1250);
		}
		level.setBackx_two(level.getBackx_two()-1);
		if (level.getBackx_two()+1250 < 0)
		{
			level.setBackx_one(1250);
		}
		
		//draw the protagonist of the game
//		if(!bb8.getRestoring()){
		
		
			g2.drawImage(bb8.getCurrentImage(), bb8.getCurrentX(), bb8.getCurrentY(), null);
			g2.draw(bb8.getCollisionBox());
			g2.drawOval((int)bb8.gettopleft().getX(), (int)bb8.gettopleft().getY(), 5, 5);
			g2.drawOval((int)bb8.getbottomleft().getX(), (int)bb8.getbottomleft().getY(), 5, 5);
			g2.drawOval((int)bb8.gettopright().getX(), (int)bb8.gettopright().getY(), 5, 5);
			g2.drawOval((int)bb8.getbottomright().getX(), (int)bb8.getbottomright().getY(), 5, 5);
			g2.setColor(Color.RED);
//		}
			ArrayList<Bullet> bullets = bb8.getBullets(); //will added to add bullets
			for (int i = 0; i < bullets.size(); i++)
			{
				Bullet bull = (Bullet) bullets.get(i);
				if (bull.see == true){
					bull.move();
					g2.drawImage(bull.img, bull.x, bull.y, null);
					g2.draw(bull.getCollisionBox());
				}

				else
					bullets.remove(i);	
			}
			
			//Generates enemies randomly
			int random = (int)(Math.random()*50+1);
			if (random%50 == 0)	{
				Enemy enemy = new Enemy();
				enemies.add(enemy);
			}
			
			if (enemies.size() != 0)
			{	
				for (int j = 0; j < enemies.size(); j++)
				{
					boolean removal = false;
					Enemy enemy = (Enemy) enemies.get(j);
					if (enemy.see == true){
						enemy.move();
						g2.drawImage(enemy.img, enemy.x, enemy.y, null);
						g2.draw(enemy.getCollisionBox());
						if (enemies.get(j).getCollisionBox().intersects(bb8.getCollisionBox()))
						{
							bb8.died();
							return;
						}
						for(int r = 0; r < bullets.size(); r++)
						{
							if (!bb8.isDead()) 
							{
								if (enemies.get(j).getCollisionBox().intersects(bullets.get(r).getCollisionBox()))
								{
//									enemies.remove(j);
									removal = true;
									bullets.remove(r);
								}
							}
						}
						if (removal)
						{
							enemies.remove(j);
						}
					}
					else
						enemies.remove(j);	
				}
			}
			
			
			
//			int randomBlock = (int)(Math.random()*100+1);

			
			
			for (int i=0; i < level.getPlatforms().size(); i++)
			{
				Platform platform = (Platform) level.getPlatforms().get(i);
				platform.move();
				if (platform.see == true) {
					g2.drawImage(platform.img, platform.getX(), platform.getY(), null);
					g2.draw(platform.getCollisionBox());
					System.out.println("Drawing platform number: " + i);
					System.out.println("Platform x: " + platform.getX());
					System.out.println("Platform y: " + platform.getY());
				}
			}
			
			
	}
	
	public int getRows(){
		return grid.getRows();
	}
	
	public int getCols(){
		return grid.getColumns();
	}
	
	//function called by the GameManager to add the protagonist to the play panel at runtime
	//the PlayPanel needs a reference to the protagonist because he's drawn a LOT of times 
	public void addProtagonist(Protagonist bb8) {
		this.bb8=bb8;
	}
	
	public void addLevel(Level level){
		this.level = level;
		bb8.setLevel(level);
	}
	
	public int getLevel(){
		return level.getLevel();
	}
}