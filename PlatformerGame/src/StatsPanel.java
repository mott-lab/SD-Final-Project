import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

//public class StatsPanel extends JPanel{
//	
//	private static final long serialVersionUID = 1L;
//	private int LevelNumber = 0;
//
//	public StatsPanel(){
//		this.setSize(GameFrame.WIDTH, STATS_HEIGHT);
//		this.setBackground(Color.WHITE);
//		this.setLayout(null);
//	}
//	
//	public static final int STATS_HEIGHT=40;
//}

public class StatsPanel extends JPanel{

	private BufferedImage livingHeart;
	private BufferedImage statsPanel;
	public static final int STATS_HEIGHT=50;
	private Protagonist bb8;
	
	public StatsPanel(){
		this.setSize(GameFrame.WIDTH, STATS_HEIGHT);
		this.setBackground(Color.BLACK);
		this.setLayout(null);
		loadInformations();
	}
	
	private void loadInformations() {
		try {
//			statsPanel=ImageIO.read(getClass().getResource("statsBar.png"));
			livingHeart=ImageIO.read(new File("bb8_0.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2=(Graphics2D)g;
		
		g2.drawImage(statsPanel,0,0,GameFrame.WIDTH-5,STATS_HEIGHT,null);
		
		for(int i=0; i<bb8.MAX_LIVES; i++){
			if(bb8.getCurrentLives()>i){
				g2.drawImage(livingHeart,144 + 60*i,4 , 32, 42,null);
			}
		}
		
		g.setColor(Color.WHITE);
		g.drawString("Level: " + bb8.getLevel().getLevel(), 84, 24);
	}
	

	public void addProtagonist(Protagonist bb8) {
		this.bb8=bb8;
	}
	
	
}